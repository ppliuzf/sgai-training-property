
package com.sgai.property.mq;

/**
 * 接收到消息时的处理类.
 *
 * @author ppliu
 * created in 2018/5/22 17:21
 */

import com.sgai.property.common.configuration.RabbitMqConfiguration;
import com.sgai.property.common.util.RedisUtil;
import com.sgai.property.mq.entity.DeviceParamReceiver;
import com.sgai.property.ruag.service.RuagLinkaageFrontDeviceService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import java.io.IOException;
import java.text.ParseException;

@Component
public class Receiver {
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private RuagLinkaageFrontDeviceService ruagLinkaageFrontDeviceService;

    @RabbitListener(queues = RabbitMqConfiguration.INSTRUCTION_RECEIVER_QUEUE)
    @RabbitHandler
    public void deviceHandler(String str) throws ServletException, ParseException, IOException {
        //todo json解析. redis 安装.
        DeviceParamReceiver paramEntity = DeviceParamReceiver.jsonToEntity(str);
        redisUtil.set(paramEntity.getDeviceCode().trim() + "-" + paramEntity.getParamCode().trim(), paramEntity.getValue());
        ruagLinkaageFrontDeviceService.scanRules(paramEntity.getDeviceCode());
    }
}

