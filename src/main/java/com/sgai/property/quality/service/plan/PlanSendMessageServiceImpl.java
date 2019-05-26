package com.sgai.property.quality.service.plan;

import com.alibaba.fastjson.JSON;
import com.sgai.property.common.service.BaseCodeService;
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
public class PlanSendMessageServiceImpl {


    @Value("${planAppInfo.accessId}")
    private String accessId;
    @Value("${planAppInfo.accessSecret}")
    private String accessSecret;
    @Value("${planSendMessage.appId}")
    private String appId;
    @Value("${planSendMessage.appSecret}")
    private String appSecret;
    @Value("${planSendMessage.toonUrl}")
    private String toonUrl;
    @Autowired
    private BaseEmployeeService baseEmployeeService;
    @Autowired
    private BaseCodeService baseCodeService;
    @Autowired
    private QueueProductServiceImpl queueProductService;

    public Response<Boolean> sendMessage(Long comId, Long eiId, String message, String subCatalog, String taskid, int isAppror) {
        Response<Boolean> response = null;
        List<MessageEntity> msList = new ArrayList<MessageEntity>();
        MessageEntity entity = new MessageEntity();
        try {
            EmpInfoVo emp = baseEmployeeService.getEmpInfoById(comId, eiId + "");
            entity.setAppId(appId);
            entity.setAppSecret(appSecret);
            entity.setBizNo(taskid);
            entity.setToFeedId(emp.getFeedId());
            entity.setToonUserId(emp.getToonUserId().toString());
            entity.setMessage(message);
//			entity.setShowHeadFlag("0");
            entity.setShowHeadFlag("N");
            entity.setSubCatalog(subCatalog);

            //添加责任人或审核人，用于去看看现实的按钮isAppror=(0,责任人，1审核人)
            String code = getNewCode(emp);
            String url = toonUrl + "/taskDetail?toonCode=" + code + "&tId=" + taskid + "&isAppror=" + isAppror;
            entity.setToonUrl(url);

            msList.add(entity);
            Response<Boolean> result = new Response<>();
            queueProductService.send(JSON.toJSONString(msList));
            result.setData(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getNewCode(EmpInfoVo emp) {
        String code = "";
        if (emp != null) {
            code = baseCodeService.getNumber(accessId, accessSecret);
        }
        return code;
    }
}
