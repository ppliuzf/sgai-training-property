package com.sgai.property.socket;

import com.sgai.property.video.entity.VideoDevice;
import com.sgai.property.video.service.VideoDeviceService;
import com.sgai.property.video.service.impl.RtspProcessorImpl;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author ppliu
 * created in 2019/1/15 14:45
 */
@Component
@ServerEndpoint("/admin/socket/rtsp/{ids}")
public class VideoSocket implements ApplicationContextAware {
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;

    //concurrent包的线程安全Set，用来存放每个客户端对应的SocketResolver对象。
    private static CopyOnWriteArraySet<VideoSocket> webSocketSet = new CopyOnWriteArraySet<>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;
    private static VideoDeviceService videoDeviceService;
    private List<Thread> threadList = new ArrayList<>();

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(@PathParam("ids") String ids, Session session) {
        this.session = session;
        webSocketSet.add(this);     //加入set中
        addOnlineCount();           //在线数加1
        System.out.println("有新连接加入！当前在线人数为" + getOnlineCount());

        List<VideoDevice> videoDeviceList = videoDeviceService.getByIds(ids);
        ;
        videoDeviceList.forEach(videoDevice -> {
            Thread thread;
            if ("冰球馆".equals(videoDevice.getPosition())) {
                thread = new RtspThread(new RtspProcessorImpl("rtsp://admin:123456@" + videoDevice.getIp() + ":554/unicast/" + videoDevice.getAisle().replace("D", "c") + "/s0/live", videoDevice.getId() + ""));
            } else {
                thread = new RtspThread(new RtspProcessorImpl("rtsp://admin:123456@" + videoDevice.getIp() + ":554/cam/realmonitor?channel="+videoDevice.getAisle()+"&subtype=0", videoDevice.getId() + ""));
            }
            threadList.add(thread);
            thread.start();
        });

    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        webSocketSet.remove(this);  //从set中删除
        subOnlineCount();           //在线数减1
        System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
        threadList.forEach(thread -> {
            thread.interrupt();
        });
        threadList.clear();
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("来自客户端的消息:" + message);
        //群发消息
        for (VideoSocket item : webSocketSet) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
    }


    public void sendMessage(String message) throws IOException {
        synchronized (session) {
            this.session.getBasicRemote().sendText(message);
        }
    }


    /**
     * 群发自定义消息
     */
    public static void sendInfo(String message) {
        for (VideoSocket item : webSocketSet) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static synchronized int getOnlineCount() {
        return onlineCount;
    }

    private static synchronized void addOnlineCount() {
        VideoSocket.onlineCount++;
    }

    private static synchronized void subOnlineCount() {
        VideoSocket.onlineCount--;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        videoDeviceService = applicationContext.getBean(VideoDeviceService.class);
    }
}
