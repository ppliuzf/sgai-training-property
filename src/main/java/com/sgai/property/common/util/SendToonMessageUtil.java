package com.sgai.property.common.util;

import com.alibaba.fastjson.JSONObject;
import com.sgai.common.utils.IdGen;
import com.sgai.property.common.exception.ReturnType;
import com.sgai.property.commonService.client.RestClient;
import com.sgai.property.commonService.vo.MessageEntity;
import com.sgai.property.commonService.vo.Response;
import com.sgai.property.commonService.vo.toon.InstanceMessageContent;
import com.sgai.property.commonService.vo.toon.SendInstanceMessageReq;
import com.sgai.property.commonService.vo.toon.SendInstanceMessageResp;
import com.sgai.property.quality.constants.Constants;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 发送toon消息的工具类
 *
 * @author 146584
 * @create 2017-11-07 15:58
 */
public class SendToonMessageUtil {
    private static final Logger logger = LogManager.getLogger(SendToonMessageUtil.class);
    private final static Long expireTime = -1L;


    /**
     *  toFeedId 接收人的名片ID  显示谁的名片
     *  toonUserId 接收人的toonUserId  决定发给谁的凭证
     *  message 消息内容
     *  appId 应用ID
     *  appSecret 应用密钥
     *  toonUrl toon通知 点击访问地址
     *  showHeadFlag 是否显示用户头像
     *  bizNo 消息编号
     *  finishFlag  1 显示已完成 0 不显示
     *  subCatalog 消息头名称
     */
    public static Response<Boolean> sendToonMessage(MessageEntity messageEntity) {
        Response<Boolean> response=new Response<>();
        SendInstanceMessageReq req = new SendInstanceMessageReq();
        req.setAuthAppId(messageEntity.getAppId());
        req.setAuthAppSecret(messageEntity.getAppSecret());
        req.setFrom(messageEntity.getAppId());
        req.setTo(messageEntity.getToFeedId());
        req.setToClient(messageEntity.getToonUserId());
        String msgId = IdGen.uuid();
        req.setMsgid(msgId);
        if(StringUtils.isEmpty(messageEntity.getMsgType())) {
            req.setMsgType("51");
        }else{
            req.setMsgType(messageEntity.getMsgType());
        }
        req.setPriority("0");
        req.setPushInfo("");
        InstanceMessageContent content = new InstanceMessageContent();
        content.setHeadFeed(messageEntity.getToFeedId());
        if(StringUtils.isNotEmpty(messageEntity.getShowHeadFlag()) && Constants.NO.equals(messageEntity.getShowHeadFlag())){ // N 是不显示
            content.setHeadFlag(0);   //不显示头像
        }else{
            content.setHeadFlag(1);  //显示用户头像
        }
        if(StringUtils.isNotEmpty(messageEntity.getToonUrl())){
            content.setActionType(1);
        }else{
            content.setActionType(0);
        }
        content.setCatalog("会议");
        if(messageEntity.getCatalogId()==null) {
            content.setCatalogId(142);
        }else{
            content.setCatalogId(messageEntity.getCatalogId());
        }
        content.setSubCatalog(messageEntity.getSubCatalog());
        content.setSubCatalogId(0);
        content.setSummary("");
        content.setContentType(6);
        content.setShowFlag(2);
        content.setExpireTime(expireTime); //显示失效
        content.setBubbleFlag(0);
        content.setFinishFlag(messageEntity.getFinishFlag()); //显示已完成
        content.setBizNo(messageEntity.getBizNo());

        Map<String, String> args = new HashMap<String, String>();
        Map<String,String> contentMap = new HashMap<>(8);
        contentMap.put("args", JSONObject.toJSONString(args));
        contentMap.put("msg", messageEntity.getMessage());
        if(StringUtils.isNotEmpty(messageEntity.getToonUrl())){
            contentMap.put("url",messageEntity.getToonUrl());
            contentMap.put("finishUrl", messageEntity.getToonUrl());
        }
        contentMap.put("img","");
        content.setContent(JSONObject.toJSONString(contentMap));
        req.setContent(content);
        logger.info("发送toon消息，请求参数：" + JSONObject.toJSONString(req));
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("msgid", req.getMsgid());
        params.put("from", req.getFrom());
        params.put("to", req.getTo());
        params.put("toClient", req.getToClient());
        params.put("pushInfo", req.getPushInfo());
        params.put("content", JSONObject.toJSONString(req.getContent()));
        params.put("msgType", req.getMsgType());
        params.put("priority", req.getPriority());
        params.put("appid", req.getAuthAppId());
        params.put("appcode", req.getAuthAppSecret());
        RestTemplate restTemplate=new RestTemplate();
        try {
            String jsonStr = RestClient.getInstance().postMapRequest("https://webserviceim.systoon.com/sendmsg", params,restTemplate);
            SendInstanceMessageResp resp =JSONObject.parseObject(jsonStr, SendInstanceMessageResp.class);
            logger.info("发送toon消息返回，返回值：" + JSONObject.toJSONString(resp));
            if(resp != null && resp.getStatus() != null && resp.getStatus() == 0){
                response.setData(true);
            }else if(resp != null && resp.getStatus() != null){
                logger.error("消息推送接口调动失败；错误码：" + resp.getStatus());
                response.setCode(ReturnType.Error.getCode());
                response.setMessage("消息推送接口调动失败；错误码：" + resp.getStatus());
                response.setData(false);
            }else{
                response.setCode(ReturnType.Error.getCode());
                response.setMessage("消息推送接口调动失败；请求：" + JSONObject.toJSONString(req));
                response.setData(false);
            }
        } catch (Exception e) {
            response.setCode(ReturnType.Error.getCode());
            response.setMessage("消息推送接口调动失败；请求：" + JSONObject.toJSONString(req));
            response.setData(false);
        }
        return response;
    }


    /**
     * 正则表达式校验邮箱
     * @param emaile 待匹配的邮箱
     * @return 匹配成功返回true 否则返回false;
     */
    public static boolean checkEmaile(String emaile){
        String RULE_EMAIL = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";
        //正则表达式的模式
        Pattern p = Pattern.compile(RULE_EMAIL);
        //正则表达式的匹配器
        Matcher m = p.matcher(emaile);
        //进行正则匹配
        return m.matches();
    }

    /**
     * 验证IP地址
     *
     * @param str
     * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean isIP(String str) {
        String num = "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)";
        String regex = "^" + num + "\\." + num + "\\." + num + "\\." + num + "$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }


    public static boolean isPort(Integer port){
        String regex = "([0-9]|[1-9]\\d{1,3}|[1-5]\\d{4}|6[0-5]{2}[0-3][0-5])";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(port.toString());
        return matcher.matches();
    }
}
