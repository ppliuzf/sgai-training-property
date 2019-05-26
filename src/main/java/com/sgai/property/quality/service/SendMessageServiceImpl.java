package com.sgai.property.quality.service;

import com.alibaba.fastjson.JSON;
import com.sgai.property.common.service.BaseEmployeeService;
import com.sgai.property.commonService.service.QueueProductServiceImpl;
import com.sgai.property.commonService.vo.EmpInfoVo;
import com.sgai.property.commonService.vo.MessageEntity;
import com.sgai.property.commonService.vo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SendMessageServiceImpl {


    @Autowired
    private BaseEmployeeService baseEmployeeService;
    @Autowired
    private QueueProductServiceImpl queueProductService;
    @Value("${wytSendMessage.appId}")
    private String appId;

    @Value("${wytSendMessage.appSecret}")
    private String appSecret;

    public Response<Boolean> sendMessage(Long comId, Long eiId, String message, String subCatalog, String bizNo, String toonUrl) {
        Response<Boolean> response = null;
        List<MessageEntity> msList = new ArrayList<>();
        MessageEntity entity = new MessageEntity();
        try {
            EmpInfoVo emp = baseEmployeeService.getEmpInfoById(comId, eiId + "");
            entity.setAppId(appId);
            entity.setAppSecret(appSecret);
            entity.setBizNo(bizNo);
            entity.setToFeedId(emp.getFeedId());
            entity.setToonUserId(emp.getToonUserId().toString());
            entity.setMessage(message);
            entity.setShowHeadFlag("0");
            entity.setSubCatalog(subCatalog);
            entity.setMsgType("51");
            entity.setCatalogId(132);
            entity.setToonUrl(toonUrl);
            msList.add(entity);
            Response<Boolean> result = new Response<>();
            queueProductService.send(JSON.toJSONString(JSON.toJSONString(msList)));
            result.setData(true);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Response<>();
    }


}
