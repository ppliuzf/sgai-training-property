package com.sgai.property.meeting.service;
import com.sgai.property.common.exception.BusinessException;
import com.sgai.property.common.exception.ReturnType;
import com.sgai.property.commonService.vo.Response;
import com.sgai.property.meeting.constants.Constants;
import com.sgai.property.meeting.vo.MailConfigureDto;
import com.sgai.property.meeting.vo.MailVo;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * 工具实现类1
 *
 * @author 146584
 * @create 2017-11-03 14:24
 */
@Service
public class CommonServicelmpl {
    private static final Logger logger = LogManager.getLogger(CommonServicelmpl.class);
/*
    @Value("${scloud.cloudAppId}")
    private int cloudAppId;
    @Value("${scloud.accessKeyId}")
    private String accessKeyId;
    @Value("${scloud.accessKeySecret}")
    private String accessKeySecret;
    @Value("${scloud.cloudDomainHost}")
    private String cloudDomainHost;
    @Value("${mt.appUrl}")
    private String appUrl;*/
    @Value("${sendMessage.appId}")
    private String sendMsgAppId;
    @Value("${sendMessage.appKey}")
    private String sendMsgAppKey;


    public Response<String> uploadImage(HttpServletRequest request) {
        Response<String> result = new Response<>();
        try {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
            StringBuilder stringBuilder = new StringBuilder();
            for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
                // 上传文件
                MultipartFile mf = entity.getValue();
                if (mf.getSize() > Constants.IMAGE_UPLOAD_SIZE_LIMIT) {
                    throw new BusinessException(ReturnType.ParamIllegal,"您上传的图片大于10M,请重新选择！");
                }
                String fileName = mf.getOriginalFilename();
                String fileExt = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
                String extName = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase().trim();
                //判断是否为允许上传的文件类型1
                if (!Constants.imgType.contains(extName)) {
                    throw new BusinessException(ReturnType.ParamIllegal,"图片类型不正确，必须为" + Constants.imgType + "的文件！");
                }
//                SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);
//                long id = idWorker.nextId();
//                String newFileName = id + fileExt;
//                String url = ToonUploadDownApi.getInstance().uploadCloudReturnUrl(cloudAppId, accessKeyId,
//                        accessKeySecret, cloudDomainHost, newFileName, mf.getBytes());
//                stringBuilder.append(url);
            }
            result.setData(stringBuilder.toString());
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(ReturnType.ParamIllegal.getCode());
            result.setMessage(e.getMessage());
            result.setData("error");
            return result;

        }
        return result;
    }

    public Boolean testSendEmail(MailConfigureDto mailConfigureDto) {
        try {
            MailVo mail = new MailVo();
            mail.setHost(mailConfigureDto.getMcIp());
            mail.setPort(mailConfigureDto.getMcPort());
            mail.setFrom(mailConfigureDto.getMcAccount());
            mail.setPassword(mailConfigureDto.getMcPassword());
            mail.setAuth("true");
            mail.setName("会议配置测试邮件");
            List<String> toList = new ArrayList<>();
            toList.add(mailConfigureDto.getMcAccount());
            mail.setToList(toList);
            mail.setSubject("会议配置测试邮件 - MeetingApp");
            mail.setContent("======> meeting app");

            return sendEmail(mail);
        }catch (Exception e){
            logger.error(e.getMessage());
            return false;
        }
    }

    /**
     * 邮件发送方法
     * @param mail
     * @return
     */
    public Boolean sendEmail(MailVo mail) {
        Transport transport = null;
        mail.setAuth("true");
        try {
            Properties props = new Properties();
            props.put("mail.smtp.host", mail.getHost());
            props.put("mail.smtp.port", mail.getPort());
            props.put("mail.smtp.auth", mail.getAuth());
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.timeout", "5000");
            props.put("mail.smtp.connectiontimeout", "5000");
            Session session = Session.getInstance(props);
            session.setDebug(false);
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(mail.getFrom(), mail.getName()));
            for (String to : mail.getToList()) {
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            }
            if(null!=mail.getCcList()){
                for (String cc : mail.getCcList()) {
                    message.addRecipient(Message.RecipientType.CC, new InternetAddress(cc));
                }
            }

            //整封邮件的MINE消息体
//			MimeMultipart msgMultipart = new MimeMultipart("mixed");//混合的组合关系

            MimeMultipart mainPart = new MimeMultipart();
            // 创建一个包含HTML内容的MimeBodyPart
            BodyPart html = new MimeBodyPart();
            // 设置HTML内容
            html.setContent(mail.getContent(), "text/html; charset=utf-8");
            mainPart.addBodyPart(html);

            message.setSubject(mail.getSubject());
            message.setContent(mainPart);

            message.saveChanges();
            transport = session.getTransport();
            if("".equals(mail.getAuth())||"true".equals(mail.getAuth())){
                transport.connect(mail.getHost(),mail.getPort(),mail.getFrom(), mail.getPassword());
            }else{
                transport.connect();
            }
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            return true;
        } catch (Exception e) {
            logger.error("邮件发送失败", e);
            return false;
        }finally {
            if (transport != null){
                try {
                    transport.close();
                } catch (MessagingException ex) {
                    logger.error("关闭transport异常", ex);
                }
            }
        }
    }


//    /**
//     * 获取邮件正文
//     * @return
//     */
//    private String getContent(String meetingType , Maininfo meetingInfo, EmpInfo from  , EmpInfo to, String localtion){
//        String acceptURL = appUrl ; //同意的url
//        String refuseURL = appUrl ; //请假的url
//        StringBuilder content = new StringBuilder();
//        if(Constants.meetingType.ADD.equals(meetingType)){
//            content.append("<!DOCTYPE html><html lang='en'><head><meta charset='UTF-8'><meta name='viewport' content='width=device-width,initial-scale=1.0,user-scalable=no charset=utf-8'>");
//            content.append("</head><body><table width='100%'  border='0' cellspacing='0' cellpadding='0'><tr><td><table border='0' cellspacing='0' cellpadding='0' width=''><tr style='height:40px'></tr><tr>");
//            content.append("<td style='width:30px;height:18px'></td><td style='font-family: STHeitiSC-Light;font-size: 16px;color:#000000;line-height:16px'>");
//            content.append("您好，"+to.getEiEmpName()+"：");
//            content.append("</td><td style='width:30px;height:18px'></td></tr>");
//            content.append("<tr style='height:10px'></tr><tr><td style='width:30px;height:46px'></td>");
//            content.append("<td style='font-family: STHeitiSC-Light;font-size: 14px;color: #000000;line-height:22px;'>");
//            content.append(from.getPostName()+" "+from.getEiEmpName()+" 邀请您参加"+meetingInfo.getMiMtSubject()+"，请您准时参加！");
//            content.append("</td><td style='width:30px;height:46px'></td></tr><tr style='height:25px'></tr></table><table cellspacing='0' cellpadding='0' style='height:1px;width:100%;border-bottom:1px solid #eee'></table>");
//            content.append("<table><tr style='height:30px;'></tr></table><table width='100%' border='0' align='center' cellpadding='0' cellspacing='0'><tr><td width='30'></td><td>");
//            content.append("<table width='275' cellpadding='0' cellspacing='0' align='left' style='margin-bottom:25px'>");
//            content.append("<tr style='height:16px ;line-height:16px'><td><img width='60' src='"+appUrl+"app/img/time.png' alt=''></td></tr><tr style='height:16px'></tr>");
//            content.append("<tr style='height:14px'><td style='font-size:14px;line-height:14px'>");
////            content.append(formatDate(meeting.getMeetingStarttime()));
//            content.append("-");
////            content.append(formatDate(meeting.getMeetingEndtime(),"HH:mm"));
//            content.append("</td></tr></table>");
//            content.append("<table width='275' cellpadding='0' cellspacing='0' align='left'>");
//            content.append("<tr style='height:16px ;line-height:16px'><td><img width='60' src='"+appUrl+"app/img/site.png' alt=''></td></tr><tr style='height:16px'></tr>");
//            content.append("<tr style='height:14px;line-height:14px'><td style='font-size:14px;line-height:14px'>");
//            content.append(localtion);
//            content.append("</td></tr></table>");
//            content.append("</td></tr></table><table style='height:40px'></table><table cellpadding='0' cellspacing='0'><tr><td style='width:30px'></td>");
//            content.append("<td ><a href='"+acceptURL+"'><img width='130' src='"+appUrl+"app/img/jieshou.jpg' alt=''></a></td><td width='5%'></td>");
//            content.append("<td width='130'><a href='"+refuseURL+"'><img width='130' src='"+appUrl+"app/img/jujue.jpg' alt='' ></a></td></tr><tr height='50'></tr></table></td></tr></table></body></html>");
//        }else if(Constants.meetingType.MOD.equals(meetingType)){
//            content.append("<!DOCTYPE html><html lang='en'><head><meta charset='UTF-8'><meta name='viewport' content='width=device-width,initial-scale=1.0,user-scalable=no charset=utf-8'>");
//            content.append("</head><body><table width='100%'  border='0' cellspacing='0' cellpadding='0'><tr>");
//            content.append("<td><table border='0' cellspacing='0' cellpadding='0'><tr><td style='width:30px;height:18px'></td>");
//            content.append("<td style='font-family: STHeitiSC-Light;font-size: 16px;color:#000000;line-height:16px'>");
//            content.append("您好，"+to.getEiEmpName()+"：");
//            content.append("</td><td style='width:30px;height:18px'><img src='"+appUrl+"app/img/1.png' alt=''></td></tr></table><table  border='0' cellspacing='0' cellpadding='0'><tr>");
//            content.append("<td style='width:30px;height:46px'></td><td style='font-family: STHeitiSC-Light;font-size: 14px;color: #000000;line-height:22px;'>");
//            content.append(from.getPostName()+" "+from.getEiEmpName()+" 邀请您参加"+meetingInfo.getMiMtSubject()+"，请您准时参加！");
//            content.append("</td><td style='width:30px'></td></tr></table><table><tr style='height:25px'></tr></table><table cellspacing='0' cellpadding='0' style='height:1px;width:100%;border-bottom:1px solid #eee'></table>");
//            content.append("<table><tr style='height:30px;'></tr></table>");
//            content.append("<table width='100%' border='0' align='center' cellpadding='0' cellspacing='0'><tr><td width='30'></td><td><table width='275' cellpadding='0' cellspacing='0' align='left' style='margin-bottom:25px'>");
//            content.append("<tr style='height:16px ;line-height:16px'><td><img width='60' src='"+appUrl+"app/img/time.png' alt=''></td></tr><tr style='height:16px'></tr>");
//            content.append("<tr style='height:14px;line-height:14px'><td style='font-size:14px;line-height:14px;text-decoration: line-through'>");
////            content.append(formatDate(meeting.getMeetingStarttime()));
//            content.append("-");
////            content.append(formatDate(meeting.getMeetingEndtime(),"HH:mm"));
//            content.append("</td></tr><tr style='height:10px;line-height:10px'></tr>");
//            content.append("<tr style='height:14px;line-height: 14px'><td style='font-size:14px;line-height:14px'>");
////            content.append(formatDate(meeting.getNewMeetingStarttime()));
//            content.append("-");
////            content.append(formatDate(meeting.getNewMeetingEndtime(),"HH:mm"));
//            content.append("</td></tr></table>");
//            content.append("<table width='275' cellpadding='0' cellspacing='0' align='left' style='margin-bottom:25px'>");
//            content.append("<tr style='height:16px;line-height:16px'><td><img width='60' src='"+appUrl+"app/img/site.png' alt=''></td></tr><tr style='height:16px;line-height:16px'></tr>");
//            content.append("<tr style='height:14px;line-height:14px'><td style='font-size:14px;line-height:14px'>");
//            content.append(localtion);
//            content.append("</td></tr></table>");
//            content.append("</td></tr></table></td></tr><tr height='30'></tr></table></body></html>");
//        }else if(Constants.meetingType.DEL.equals(meetingType)){
//            content.append("<!DOCTYPE html><html lang='en'><head><meta charset='UTF-8'><meta name='viewport' content='width=device-width,initial-scale=1.0,user-scalable=no charset=utf-8'>");
//            content.append("</head><body><table width='100%'  border='0' cellspacing='0' cellpadding='0'><tr>");
//            content.append("<td><table border='0' cellspacing='0' cellpadding='0'><tr><td style='width:30px;height:18px'></td>");
//            content.append("<td style='font-family: STHeitiSC-Light;font-size: 16px;color:#000000;line-height:16px'>");
//            content.append("您好，"+to.getEiEmpName()+"：");
//            content.append("</td><td style='width:30px;height:18px'><img src='"+appUrl+"app/img/2.png' alt=''></td></table><table  border='0' cellspacing='0' cellpadding='0'><tr><td style='width:30px;height:46px'></td>");
//            content.append("<td style='font-family: STHeitiSC-Light;font-size: 14px;color: #000000;line-height:22px;'>");
//            content.append(from.getPostName()+" "+from.getEiEmpName()+" 邀请您参加"+meetingInfo.getMiMtSubject()+"，请您准时参加！");
//            content.append("</td><td style='width:30px'></td></tr></table><table><tr style='height:25px'></tr></table><table cellspacing='0' cellpadding='0' style='height:1px;width:100%;border-bottom:1px solid #eee'></table>");
//            content.append("<table><tr style='height:30px;'></tr></table><table width='100%' border='0' align='center' cellpadding='0' cellspacing='0'><tr><td width='30'></td><td>");
//            content.append("<table width='275' cellpadding='0' cellspacing='0' align='left' style='margin-bottom:25px'>");
//            content.append("<tr style='height:16px ;line-height:16px'><td><img width='60' src='"+appUrl+"app/img/time.png' alt=''></td></tr><tr style='height:16px'></tr>");
//            content.append("<tr style='height:14px;line-height:14px'><td style='font-size:14px;line-height:14px'>");
////            content.append(formatDate(meeting.getMeetingStarttime()));
//            content.append("-");
////            content.append(formatDate(meeting.getMeetingEndtime(),"HH:mm"));
//            content.append("</td></tr></table>");
//            content.append(" <table width='275' cellpadding='0' cellspacing='0' align='left' style='margin-bottom:25px'>");
//            content.append("<tr style='height:16px ;line-height:16px'><td><img width='60' src='"+appUrl+"app/img/site.png' alt=''></td></tr><tr style='height:16px'></tr>");
//            content.append(" <tr style='height:14px;line-height:14px'><td style='font-size:14px;line-height:14px'>");
//            content.append(localtion);
//            content.append("</td></tr></table>");
//            content.append("</td></tr></table></td></tr><tr height='50'></tr></table></body></html>");
//        }
//        return content.toString();
//    }

}
