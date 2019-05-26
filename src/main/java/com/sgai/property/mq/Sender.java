package com.sgai.property.mq;

import com.sgai.property.common.configuration.RabbitMqConfiguration;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * 消息发送类.
 *
 * @author ppliu
 * created in 2018/5/22 17:18
 */
@Component
public class Sender {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void sendMessage(String str) {
        this.rabbitTemplate.convertAndSend(RabbitMqConfiguration.ALM_SENDER_EXCHANGE, RabbitMqConfiguration.ALM_SENDER_ROUTING_KEY, str);
    }

    public void sendEnergyMessage(String str) {
        this.rabbitTemplate.convertAndSend(RabbitMqConfiguration.ALM_SENDER_EXCHANGE, RabbitMqConfiguration.ENERGY_ROUTING_KEY, str);
    }

    public void sendWaterMessage(String str) {
        this.rabbitTemplate.convertAndSend(RabbitMqConfiguration.ALM_SENDER_EXCHANGE, RabbitMqConfiguration.WATER_ROUTING_KEY, str);
    }
    public void sendPackingMessage(String str) {
        this.rabbitTemplate.convertAndSend(RabbitMqConfiguration.ALM_SENDER_EXCHANGE, RabbitMqConfiguration.PACK_ROUTING_KEY, str);
    }
    public void sendInstructionMessage(String str) {
        this.rabbitTemplate.convertAndSend(RabbitMqConfiguration.ALM_SENDER_EXCHANGE, RabbitMqConfiguration.INSTRUCTION_SENDER_ROUTING_KEY, str);
    }
}
