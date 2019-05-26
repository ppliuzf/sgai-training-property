package com.sgai.property.commonService.rabbitmq;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.sgai.property.common.util.SendToonMessageUtil;
import com.sgai.property.commonService.vo.MessageEntity;
import com.sgai.property.commonService.vo.Response;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 发送toon消息listener监听器
 *
 * @author 146584
 * @date 2017-11-13 11:59
 */
@Service
public class ToonMessageListener {
    private static final Logger logger = LogManager.getLogger(ToonMessageListener.class);


    public void listenerAndSendToonMessage(String message) {
        logger.info("----监听者消费Message----->" + JSON.toJSONString(message));
        Long startTime = System.currentTimeMillis();
        List<MessageEntity> messageEntityList = JSON.parseObject(message,new TypeReference<List<MessageEntity>>(){});
        for(MessageEntity messageEntity:messageEntityList){
            Response<Boolean> result = SendToonMessageUtil.sendToonMessage(messageEntity);
            if(result.getData()){
                logger.info("---发送成功1--->");
            }else {
                logger.error("----消息发送失败!!!---->"+ JSON.toJSONString(messageEntity));
            }
        }
        Long endTime = System.currentTimeMillis();
        logger.info("====>监听者消费完毕,共计"+messageEntityList.size()+"条,耗时:"+(endTime-startTime)+"ms");
    }
}
