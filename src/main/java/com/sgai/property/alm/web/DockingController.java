package com.sgai.property.alm.web;

import com.sgai.modules.login.annotation.PermessionLimit;
import com.sgai.property.alm.entity.AlmDockingData;
import com.sgai.property.alm.service.AlmDockingDataService;
import com.sgai.property.alm.vo.DockingResponse;
import com.sgai.property.alm.vo.ScreenVo;
import com.sgai.property.mq.Sender;
import com.sgai.property.socket.SocketResolver;
import com.sgai.property.wy.service.AlarmRepairDisposeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * 报警推送接收器.
 *
 * @author ppliu
 * created in 2019/1/11 10:26
 */
@RestController
@RequestMapping("/admin/alm/docking")
public class DockingController {

    @Autowired
    private Sender sender;
    @Autowired
    private AlmDockingDataService almDockingDataService;
    @Autowired
    private AlarmRepairDisposeService alarmRepairDisposeService;

    @PermessionLimit(limit = false)
    @RequestMapping(value = "/getData", method = RequestMethod.POST)
    public DockingResponse getData(@RequestBody AlmDockingData almDockingData) {
        if (StringUtils.isEmpty(almDockingData.getMsgText())) {
            almDockingData.setMsgTextDetail("");
        } else {
            String[] msgArray = almDockingData.getMsgText().split(" ");
            if (msgArray.length == 2) {
                almDockingData.setMsgText(msgArray[0]);
                almDockingData.setMsgTextDetail(msgArray[1]);
            } else {
                almDockingData.setMsgTextDetail("");
            }
        }
        //发送到大屏
        sender.sendMessage(almDockingData.toString());
        try {
            //保存
            almDockingData.setUuid(UUID.randomUUID().toString());
            almDockingData.setSource(getDeviceCode(almDockingData.getSource()));
            almDockingDataService.save(almDockingData);
            alarmRepairDisposeService.alarmDispose(almDockingData);
            //发送到socket
            ScreenVo screenVo = new ScreenVo();
            screenVo.setType(1);
            screenVo.setAlmDockingData(almDockingDataService.getRecentList(5));
            SocketResolver.sendInfo(screenVo.toString());
            return DockingResponse.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return DockingResponse.error();
        }
    }

    private String getDeviceCode(String source) {
        if (source.contains("$2d")) {
            source = source.replace("$2d", "-");
        }
        return StringUtils.substringBeforeLast(source, ".");
    }
}
