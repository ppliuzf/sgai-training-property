package com.sgai.property.commonService.service;

import com.sgai.property.commonService.constants.Constants;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * 队列消费者实现类
 *
 * @author 146584
 * @date 2017-11-13 11:19
 */
@Service
public class QueueProductServiceImpl implements  RabbitTemplate.ConfirmCallback{
    private static final Logger logger = LogManager.getLogger(QueueProductServiceImpl.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public QueueProductServiceImpl(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate=rabbitTemplate;
        this.rabbitTemplate.setConfirmCallback(this);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        logger.info(":===>消息id:" + correlationData);

        if (ack) {
            logger.info("放入队列成功");
        } else {
            logger.info("放入队列失败:" + cause);
        }
    }

    /**
     * 发送消息，不需要实现任何接口，供外部调用
     * @param msg
     */
    public void send(String msg){
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
        String result = rabbitTemplate.convertSendAndReceive(Constants.EXCHANGE_NAME_MESSAGE,Constants.ROUTING_KEY_MESSAGE, msg, correlationId)+"";
    }



}
