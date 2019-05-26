
package com.sgai.property.video.service.impl;

import com.sgai.property.socket.VideoSocket;
import com.sgai.property.video.service.RtspProcessor;
import com.sgai.property.video.vo.ImageDataVo;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.Java2DFrameConverter;

import java.awt.image.BufferedImage;

import static com.sgai.property.video.util.ImgeUtil.imageToBase64;


/**
 * 描述:rtsp处理类.
 *
 * @author ppliu
 * created in 2019/5/1 18:24
 */
public class RtspProcessorImpl implements RtspProcessor {
    /** 路径. */
    private String path;
    /** 主键. */
    private String key;
    private FFmpegFrameGrabber grabber;


    public RtspProcessorImpl(String path, String key) {
        this.path = path;
        this.key = key;
        this.grabber = new FFmpegFrameGrabber(path);
        grabber.setOption("rtsp_transport", "tcp");
        grabber.setFrameRate(25);
        grabber.setVideoBitrate(3000000);
        grabber.setImageWidth(746);
        grabber.setImageHeight(480);
        try {
            grabber.start();
        } catch (FrameGrabber.Exception e) {
            e.printStackTrace();
        }

    }


    public void sendBase64() {
        try {//建议在线程中使用该方法
            Frame frame = grabber.grabFrame();
            if (frame != null) {
                Java2DFrameConverter javaconverter = new Java2DFrameConverter();
                BufferedImage buffImg = javaconverter.convert(frame);
                ImageDataVo vo = new ImageDataVo(key, "data:image/png;base64," + imageToBase64(buffImg, "jpg"));
                VideoSocket.sendInfo(vo.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                grabber.stop();
            } catch (FrameGrabber.Exception e1) {
                e1.printStackTrace();
            }
        }
    }
}
