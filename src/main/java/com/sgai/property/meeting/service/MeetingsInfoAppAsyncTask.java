/**
 * Project Name:meetings-service
 * File Name:MeetingsInfoAppAsyncTask.java
 * Package Name:com.qitoon.app.meetings.api.service.impl
 * Date:2017年11月16日上午10:32:20
 * Copyright (c) 2017, zhangxiang All Rights Reserved.
 *
 */

package com.sgai.property.meeting.service;

import com.alibaba.fastjson.JSON;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.common.exception.BusinessException;
import com.sgai.property.common.exception.ReturnType;
import com.sgai.property.common.util.TimeUtil;
import com.sgai.property.common.util.ToonOwner;
import com.sgai.property.common.util.ToonVisitor;
import com.sgai.property.commonService.dao.IDictGeneralDao;
import com.sgai.property.commonService.entity.DictGeneral;
import com.sgai.property.commonService.vo.EmpInfoVo;
import com.sgai.property.commonService.vo.MessageEntity;
import com.sgai.property.commonService.vo.Response;
import com.sgai.property.meeting.constants.Constants;
import com.sgai.property.meeting.dao.*;
import com.sgai.property.meeting.entity.*;
import com.sgai.property.meeting.vo.MailVo;
import com.sgai.property.meeting.vo.MaininfoVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * ClassName: MeetingsInfoAppAsyncTask
 * @Description :
 * @date: 2017年11月16日 上午10:32:20
 * @author zhangxiang3
 * @version
 */
@Component
@Async("meetingsInfoTaskAsyncPool")
public class MeetingsInfoAppAsyncTask {

	private static final Logger logger = LogManager.getLogger(MeetingsInfoAppAsyncTask.class);

	@Autowired
	private IMaininfoDao maininfoDao;

	@Autowired
	private IInviterDao inviterDao;
	
	@Autowired
	private IPicDao picDao;
	
	@Autowired
	private PicServiceImpl picServiceImpl;

	@Autowired
	private IRoomPositionDao roomPositionDao;

	@Autowired
	private IRoomResourceDao roomResourceDao;

	@Autowired
	private IMailConfigureDao mailConfigureDao;

	@Autowired
	private IDictGeneralDao dictGeneralDao;

	@Autowired
	private CommonServicelmpl commonServicelmpl;

	@Autowired
	private IMaterielDao materielDao;
	
	@Autowired
	private MaterielServiceImpl materielServiceImpl;
	

	@Autowired
	private MeetingEmpInfoServiceImpl empInfoService;

	@Autowired
	private MaininfoServiceImpl maininfoService;
	@Value("${mt.serviceUrl}")
	private String serviceUrl;

	@Value("${appInfo.accessId}")
	private String accessId;
	@Value("${appInfo.accessSecret}")
	private String accessSecret;

	@Value("${sendMessage.appId}")
	private String appId;

	@Value("${sendMessage.appKey}")
	private String appKey;

	@Value("${mt.appUrl}")
	private String appUrl;

	@Value("${mt.inviteImageUrl}")
	private String inviteImageUrl;

	@Value("${mt.leaveImageUrl}")
	private String leaveImageUrl;

	@Value("${meetings.sendMessage.startMeeting}")
	private String startMeeting;

	@Value("${meetings.sendMessage.leavingMeeting}")
	private String leavingMeeting;

	@Value("${meetings.sendMessage.editMeeting}")
	private String editMeeting;

	@Value("${meetings.sendMessage.editMeetingTime}")
	private String editMeetingTime;

	@Value("${meetings.sendMessage.editMeetingRoom}")
	private String editMeetingRoom;

	@Value("${meetings.sendMessage.cancelMeeting}")
	private String cancelMeeting;

	@Value("${meetings.sendMessage.summaryMeeting}")
	private String summaryMeeting;

	@Value("${meetings.email.placeImage}")
	private String emailPlaceImage;

	@Value("${meetings.email.timeImage}")
	private String emailTimeImage;
	

	/**
	 * MethodName : sendMessageEmail
	 *
	 * @Description : 发送通知和邮件信息
	 * @param inviters
	 * @param maininfo
	 * @return
	 * @author zhangxiang
	 * @date: 2017年11月15日 下午1:51:17
	 */
	public void sendMessageEmail(List<Inviter> inviters, Maininfo maininfo) throws BusinessException {
		EmpInfoVo empInfoVo = empInfoService.getEmpInfoByEiId(maininfo.getComId(), maininfo.getCreateEiId());
		List<MessageEntity> messageEntityList = new ArrayList<>();
		for(Inviter inviter : inviters) {
//			//发起人不发送通知
//			if(maininfo.getCreateEiId()!=null && inviter.getInviterEiId().equals(maininfo.getCreateEiId())) {
//				continue;
//			}
			MessageEntity messageEntity = new MessageEntity();
			messageEntity.setToFeedId(empInfoVo.getFeedId());
			String date = DateFormatUtils.format(getMeetingStarttime(maininfo), Constants.DATEFORMAT_YYYYMMDDHHMM);
			RoomResource roomResource = roomResourceDao.getById(maininfo.getRrId());
			RoomPosition roomPosition = new RoomPosition();
			roomPosition.setId(roomResource.getRpId());
			String rpPositionName = roomPositionDao.get(roomPosition).getRpPositionName();
			String position = rpPositionName+" "+roomResource.getRrRoomName();
			messageEntity.setSubCatalog("会议通知");
			messageEntity.setMessage(MessageFormat.format(startMeeting, maininfo.getMiMtSubject(),date,position,maininfo.getCreateEiName()));
			EmpInfoVo empInfo = empInfoService.getEmpInfoByEiId(maininfo.getComId(), inviter.getInviterEiId());
			
			if(!checkEmpInfo(empInfo)){
				continue;
			}
			
			String code = findCodeByToken();
			messageEntity.setToonUrl(MessageFormat.format(appUrl, maininfo.getId(),maininfo.getMiMtSubject(),code));
			messageEntity.setBizNo(maininfo.getId()+""+maininfo.getMiMtDate()+inviter.getInviterEiId());
			messageEntity.setAppId(appId);
			messageEntity.setAppSecret(appKey);
			messageEntity.setToonUserId(empInfo.getToonUserId()+"");
			messageEntityList.add(messageEntity);
			List<String> toEmailList = new ArrayList<>();
			if(StringUtils.isNotEmpty(empInfo.getEiEmail()) && !"null".equalsIgnoreCase(empInfo.getEiEmail())) {
				toEmailList.add(empInfo.getEiEmail());
			}

			//发送发起会议邮件
			MailConfigure mailConfigure = new MailConfigure();
//			mailConfigure.setComId(maininfo.getComId());
			mailConfigure.setIsSend(Constants.TRUE);
			mailConfigure = mailConfigureDao.get(mailConfigure);
			if(null!=mailConfigure){
				MailVo mail = new MailVo();
				mail.setHost(mailConfigure.getMcIp());
				mail.setPort(mailConfigure.getMcPort());
				mail.setAuth("true");
				mail.setFrom(mailConfigure.getMcAccount());
				mail.setPassword(mailConfigure.getMcPassword());
				mail.setName(mailConfigure.getMcEmailName());
				mail.setToList(toEmailList);
				mail.setSubject("【会议邀请】"+maininfo.getMiMtSubject());
				mail.setContent(getContent(Constants.meetingType.ADD, maininfo, empInfoVo, inviter,empInfo, position));
				if(toEmailList != null && toEmailList.size() > 0) {
					commonServicelmpl.sendEmail(mail);
				}
			}

		}
		sendMessage(messageEntityList);


	}

	/**
	 * MethodName : getMeetingStarttime
	 *
	 * @Description : 获取会议开始时间
	 * @param maininfo
	 * @return
	 *
	 * @author zhangxiang
	 * @date: 2017年11月27日 上午10:32:20
	 */
	private Date getMeetingStarttime(Maininfo maininfo) {
		Date date = null;
		String[] miMtTimeSegArr = maininfo.getMiMtTimeSeg().split(",");
		DictGeneral dictGeneralStart = new DictGeneral();
		dictGeneralStart.setDgCode("BIZ_MT_TIME_SEG");
		dictGeneralStart.setDgKey(miMtTimeSegArr[0]);
		dictGeneralStart = dictGeneralDao.get(dictGeneralStart);
		TimeUtil timeStart = new TimeUtil(dictGeneralStart.getDgValue()+":00" );
		try {
			date = DateUtils.parseDate(DateFormatUtils.format(maininfo.getMiMtDate(),  Constants.DATEFORMAT_YYYYMMDD) +" "+timeStart+":00",Constants.DATEFORMAT_YYYYMMDDHHMMSS);
		} catch (ParseException e) {
			logger.error("日期转换错误！");
		}
		return date;
	}

	/**
	 * MethodName : editSendMessage
	 *
	 * @Description : 修改会议信息发送通知
	 * @param maininfo
	 * @param maininfoOld
	 * @param inviters
	 * @return
	 *
	 * @author zhangxiang
	 * @date: 2017年11月14日 下午1:40:11
	 */
	public void editSendMessage(Maininfo maininfo, Maininfo maininfoOld, List<Inviter> inviters) {
		EmpInfoVo empInfoVo = empInfoService.getEmpInfoByEiId(maininfo.getComId(), UserServletContext.getUserInfo().getUserNo());
		
		if(!checkEmpInfo(empInfoVo)){
			return;
		}
		
		List<MessageEntity> messageEntities = new ArrayList<MessageEntity>();
		String dateSource = DateFormatUtils.format(getMeetingStarttime(maininfoOld),Constants.DATEFORMAT_YYYYMMDDHHMM);
		String dateDest = DateFormatUtils.format(getMeetingStarttime(maininfo), Constants.DATEFORMAT_YYYYMMDDHHMM);
		//新地点
		RoomResource roomResource = roomResourceDao.getById(maininfo.getRrId());
		RoomPosition roomPosition = new RoomPosition();
		roomPosition.setId(roomResource.getRpId());
		String rpPositionName = roomPositionDao.get(roomPosition).getRpPositionName();
		String position = rpPositionName+" "+roomResource.getRrRoomName();

		//原始地址
		RoomResource roomResourceOld = roomResourceDao.getById(maininfoOld.getRrId());
		RoomPosition roomPositionOld = new RoomPosition();
		roomPositionOld.setId(roomResourceOld.getRpId());
		String rpPositionNameOld = roomPositionDao.get(roomPositionOld).getRpPositionName();
		String positionOld = rpPositionNameOld+" "+roomResourceOld.getRrRoomName();
		//不发送通知
		if(position.equals(positionOld) && dateDest.equals(dateSource)) {
			return ;
		}

		for(Inviter inviter : inviters) {
			//发起人不发送通知
//			if(inviter.getInviterEiId().equals(UserServletContext.getUserInfo().getUserNo())) {
//				continue;
//			}
			MessageEntity messageEntity = new MessageEntity();
			messageEntity.setToFeedId(empInfoVo.getFeedId());


			if(position.equals(positionOld) && !dateDest.equals(dateSource)) {
				messageEntity.setMessage(MessageFormat.format(editMeetingTime, maininfo.getMiMtSubject(),dateSource,dateDest,
						positionOld,
						UserServletContext.getUserInfo().getUserName()));
			}else if(!position.equals(positionOld) && !dateDest.equals(dateSource)) {
				messageEntity.setMessage(MessageFormat.format(editMeeting, maininfo.getMiMtSubject(),dateSource,dateDest,
						positionOld,position,
						UserServletContext.getUserInfo().getUserName()));
			}else if(!position.equals(positionOld) && dateDest.equals(dateSource)) {
				messageEntity.setMessage(MessageFormat.format(editMeetingRoom, maininfo.getMiMtSubject(),dateSource,
						positionOld,position,
						UserServletContext.getUserInfo().getUserName()));
			}else {
				continue;
			}


			messageEntity.setSubCatalog("会议变更");
			messageEntity.setAppId(appId);
			messageEntity.setAppSecret(appKey);
			ToonOwner owner = new ToonOwner();
			EmpInfoVo empInfo = empInfoService.getEmpInfoByEiId(maininfo.getComId(), inviter.getInviterEiId());
			
			if(!checkEmpInfo(empInfo)){
				continue;
			}
			owner.setFeed_id(empInfo.getFeedId());
			owner.setCompany_id(UserServletContext.getUserInfo().getCompanyId());
			String code = findCodeByToken();
			messageEntity.setToonUrl(MessageFormat.format(appUrl, maininfo.getId(),maininfo.getMiMtSubject(),code));
			messageEntity.setBizNo(maininfo.getId()+""+System.currentTimeMillis()+inviter.getInviterEiId());

			messageEntity.setToonUserId(empInfo.getToonUserId()+"");
			messageEntities.add(messageEntity);
		}
		sendMessage(messageEntities);
	}

	/**
	 * MethodName : loopWeekMeeting
	 *
	 * @Description : 处理周例会信息
	 * @param flag
	 * @param maininfoVo

	 * @return
	 * @throws Exception
	 *
	 * @author zhangxiang
	 * @date: 2017年11月15日 下午1:51:33
	 */
	@Transactional(rollbackFor=Exception.class)
	public void loopWeekMeeting(boolean flag, MaininfoVo maininfoVo, String accessToken) throws Exception {
		Integer isWeekMeeting = maininfoVo.getMiIsWeekMeeting();
		Integer count = maininfoVo.getMiRepeatNum();
		Long miDate = maininfoVo.getMiMtDate();
		if(isWeekMeeting != null && isWeekMeeting == 1 && count != null && count < 5) {
			for(int i=1;i<count+1;i++) {
				maininfoVo.setMiIsWeekMeeting(Constants.FALSE);
				maininfoVo.setMiRepeatNum(Constants.FALSE);
				Long loopDate = DateUtils.addDays(new Date(miDate),i*7).getTime();
				maininfoVo.setMiMtDate(loopDate);
				flag = maininfoService.saveMeeting(maininfoVo);
				if(flag) {
					continue;
				}else {
					throw new BusinessException(ReturnType.Error,"保存会议信息失败！");
				}
			}
		}
	}

	/**
	 * MethodName : sendMessage
	 *
	 * @Description : 发送消息通知
	 * @param messageEntityList
	 * @return
	 *
	 * @author zhangxiang
	 * @date: 2017年11月14日 下午1:42:59
	 */
	private boolean sendMessage(List<MessageEntity> messageEntityList) {
		if(messageEntityList != null && messageEntityList.size() > 0) {
			try {
				Response<Boolean> response = null;//commonsRomeotService.sendMessageByQueue(messageEntityList);
				logger.info("sendMessage:res:{"+response.getCode()+"}");
                return response != null && "0".equals(response.getCode());
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
		return true;
	}

	/**
	 * MethodName : getContent
	 *
	 * @Description : 获取邮件内容
	 * @param meetingType
	 * @param meetingInfo
	 * @param from
	 * @param inviter
	 * @param to
	 * @param localtion
	 * @return
	 *
	 * @author zhangxiang1
	 * @date: 2017年11月13日 下午3:52:40
	 */
	private String getContent(String meetingType, Maininfo meetingInfo, EmpInfoVo from, Inviter inviter, EmpInfoVo to, String localtion) {
		//同意的url
		String acceptURL = serviceUrl + "/meetings/meetingInviterAccept?meetingId=" + meetingInfo.getId() + "&meetingInviter=" + inviter.getInviterEiId();
		//请假的url
		String refuseURL = serviceUrl + "/meetings/meetingInviterRefuse?meetingId=" + meetingInfo.getId() + "&meetingInviter=" + inviter.getInviterEiId();
		StringBuilder content = new StringBuilder();
		if (Constants.meetingType.ADD.equals(meetingType)) {
			content.append("<!DOCTYPE html><html lang='en'><head><meta charset='UTF-8'><meta name='viewport' content='width=device-width,initial-scale=1.0,user-scalable=no charset=utf-8'>");
			content.append("</head><body>");
			content.append("<table border=\"0\" style=\"border-bottom:solid #ddd 1px;width:100%\"><tr>" +
					"<td style=\"padding-left:20px\">您好，" + to.getEiEmpName() + "：</td></tr><tr><td style=\"padding-left:20px;text-indent:28px\">" +
					from.getPositionName() + " " + from.getEiEmpName() + " 邀请您参加 " + meetingInfo.getMiMtSubject() + "，请您准时参加！</td></tr><tr><td style=\"height:20px\">&nbsp;</td></tr></table><table border=\"0\"><tr><td style=\"padding:20px\">" +
					"<img src=\"" + emailTimeImage + "\" alt=\"时间\" width=\"53\"><p>" +
					DateFormatUtils.format(meetingInfo.getMiMtDate(), "yyyy年MM月dd日") + " " +
					TimeUtil.getWeekDay(meetingInfo.getMiMtDate()) + " " +
					maininfoService.getMeetingStartEndTime(meetingInfo.getMiMtTimeSeg()) +
					"</p></td><td style=\"padding:20px\"><img src=\"" + emailPlaceImage + "\" alt=\"地点\" width=\"52\"><p>" +
					localtion + "</p></td></tr><tr><td colspan=\"2\"><table><tr><td style=\"padding-left:20px;padding-right:20px\">" +
					"<a href=\""+acceptURL+"\"><img src=\"" + inviteImageUrl + "\" alt=\"参加\"></a></td><td>" +
					"<a href=\""+refuseURL+"\"><img src=\"" + leaveImageUrl + "\" alt=\"请假\"></a></td></tr></table></td></tr></table>");
			content.append("</body></html>");
		}
		return content.toString();
	}

//	private String getContent(String meetingType, Maininfo meetingInfo, EmpInfoVo from, Inviter inviter, EmpInfoVo to, String localtion) {
//		String acceptURL = serviceUrl + "meetings/meetingInviterAccept?meetingId=" + meetingInfo.getMiId() + "&meetingInviter=" + inviter.getInviterEiId();//同意的url
//		String refuseURL = serviceUrl + "meetings/meetingInviterRefuse?meetingId=" + meetingInfo.getMiId() + "&meetingInviter=" + inviter.getInviterEiId();//请假的url
//		StringBuilder content = new StringBuilder();
//		if (Constants.meetingType.ADD.equals(meetingType)) {
//			content.append("<!DOCTYPE html><html lang='en'><head><meta charset='UTF-8'><meta name='viewport' content='width=device-width,initial-scale=1.0,user-scalable=no charset=utf-8'>");
//			content.append("</head><body><table width='100%'  border='0' cellspacing='0' cellpadding='0'><tr><td><table border='0' cellspacing='0' cellpadding='0' width=''><tr style='height:40px'></tr><tr>");
//			content.append("<td style='width:30px;height:18px'></td><td style='font-family: STHeitiSC-Light;font-size: 16px;color:#000000;line-height:16px'>");
//			content.append("您好，" + to.getEiEmpName() + "：");
//			content.append("</td><td style='width:30px;height:18px'></td></tr>");
//			content.append("<tr style='height:10px'></tr><tr><td style='width:30px;height:46px'></td>");
//			content.append("<td style='font-family: STHeitiSC-Light;font-size: 14px;color: #000000;line-height:22px;'>");
//			content.append(from.getPositionName() + " " + from.getEiEmpName() + " 邀请您参加" + meetingInfo.getMiMtSubject() + "，请您准时参加！");
//			content.append("</td><td style='width:30px;height:46px'></td></tr></table>");
//			content.append("<table width='100%' border='0' align='center' cellpadding='0' cellspacing='0'><tr><td width='30'></td><td>");
//			content.append("<table width='275' cellpadding='0' cellspacing='0' align='left' style='margin-bottom:14px'>");
//			content.append("<tr style='height:14px'><td style='font-size:14px;line-height:14px'>");
//			content.append(DateFormatUtils.format(meetingInfo.getMiMtDate(), "yyyy年MM月dd日") + " " + TimeUtil.getWeekDay(meetingInfo.getMiMtDate()) + " " + maininfoService.getMeetingStartEndTime(meetingInfo.getMiMtTimeSeg()));
//			content.append("</td></tr></table></td>");
//			content.append("</tr><tr><td width='30'></td><td><table width='275' cellpadding='0' cellspacing='0' align='left'>");
//			content.append("<tr style='height:14px;line-height:14px'><td style='font-size:14px;line-height:14px'>");
//			content.append(localtion);
//			content.append("</td></tr></table>");
//			content.append("</td></tr></table><table style='height:40px'></table><table cellpadding='0' cellspacing='0'><tr><td style='width:30px'></td>");
//			content.append("<td ><a href='" + acceptURL + "'><img width='80' src='" + inviteImageUrl + "' alt=''></a></td><td width='5%'></td>");
//			content.append("<td width='130'><a href='" + refuseURL + "'><img width='80' src='" + leaveImageUrl + "' alt='' ></a></td></tr><tr height='50'></tr></table></td></tr></table></body></html>");
//		}
//		return content.toString();
//	}
	/**
	 * MethodName : sendMeetingNotice
	 *
	 * @Description : 定时任务发送通知
	 * @param maininfo
	 *
	 * @author zhangxiang
	 * @date: 2017年11月27日 上午10:33:06
	 */
	public void sendMeetingNotice(Maininfo maininfo) {
		EmpInfoVo empInfoVo = empInfoService.getEmpInfoByEiId(maininfo.getComId(), maininfo.getCreateEiId());
		List<Inviter> inviters = inviterDao.getByMiId(maininfo.getId());
		List<MessageEntity> messageEntityList = new ArrayList<>();
		for(Inviter inviter : inviters) {
			//发起人不发送通知
			MessageEntity messageEntity = new MessageEntity();
			messageEntity.setToFeedId(empInfoVo.getFeedId());
			String date = DateFormatUtils.format(getMeetingStarttime(maininfo), Constants.DATEFORMAT_YYYYMMDDHHMM);
			RoomResource roomResource = roomResourceDao.getById(maininfo.getRrId());
			RoomPosition roomPosition = new RoomPosition();
			roomPosition.setId(roomResource.getRpId());
			String rpPositionName = roomPositionDao.get(roomPosition).getRpPositionName();
			String position = rpPositionName+" "+roomResource.getRrRoomName();
			messageEntity.setSubCatalog("会议通知");
			messageEntity.setMessage(MessageFormat.format(startMeeting, maininfo.getMiMtSubject(),date,position,maininfo.getCreateEiName()));
			EmpInfoVo empInfo = empInfoService.getEmpInfoByEiId(maininfo.getComId(), inviter.getInviterEiId());
			
			if(!checkEmpInfo(empInfo)){
				continue;
			}
			
			String code = findCodeByToken();
			messageEntity.setToonUrl(MessageFormat.format(appUrl, maininfo.getId(),maininfo.getMiMtSubject(),code));
			messageEntity.setBizNo(maininfo.getId()+""+maininfo.getMiMtDate()+inviter.getInviterEiId());
			messageEntity.setAppId(appId);
			messageEntity.setAppSecret(appKey);
			messageEntity.setToonUserId(empInfo.getToonUserId()+"");
			messageEntityList.add(messageEntity);
		}
		sendMessage(messageEntityList);
	}

	/**
	 * MethodName : leaveMeetingMessage
	 *
	 * @Description : 请假通知
	 * @param inviter
	 * @param miId
	 * @param leaveReason
	 *
	 * @author zhangxiang
	 * @date: 2017年11月27日 上午10:33:27
	 */
	public void leaveMeetingMessage(Inviter inviter, String miId, String leaveReason) {
		Maininfo maininfo = maininfoDao.getById(miId);
		EmpInfoVo empInfoVo = empInfoService.getEmpInfoByEiId(maininfo.getComId(),inviter.getInviterEiId() );
		String date = DateFormatUtils.format(System.currentTimeMillis(), Constants.DATEFORMAT_YYYYMMDDHHMM);
		//发送通知
		MessageEntity messageEntity = new MessageEntity();
		messageEntity.setToFeedId(empInfoVo.getFeedId());
		messageEntity.setMessage(MessageFormat.format(leavingMeeting, maininfo.getMiMtSubject(),date,UserServletContext.getUserInfo().getUserName(),leaveReason));
		EmpInfoVo empInfo = empInfoService.getEmpInfoByEiId(maininfo.getComId(),maininfo.getCreateEiId() );
		
		if(!checkEmpInfo(empInfo)){
			return;
		}
		String code = findCodeByToken();
		messageEntity.setToonUrl(MessageFormat.format(appUrl, maininfo.getId(),maininfo.getMiMtSubject(),code));
		messageEntity.setBizNo(maininfo.getId()+""+System.currentTimeMillis()+inviter.getInviterEiId());
		messageEntity.setSubCatalog("请假通知");
		messageEntity.setAppId(appId);
		messageEntity.setAppSecret(appKey);

		messageEntity.setToonUserId(empInfo.getToonUserId()+"");
		//主持人
		Inviter inviterCompere = new Inviter();
		inviterCompere.setMiId(miId);
		inviterCompere.setIsCompere(Constants.TRUE);
		inviterCompere = inviterDao.get(inviterCompere);

		MessageEntity messageEntityCom = new MessageEntity();
		BeanUtils.copyProperties(messageEntity, messageEntityCom);
		if(inviterCompere != null) {
			empInfo = empInfoService.getEmpInfoByEiId(maininfo.getComId(),inviterCompere.getInviterEiId() );
			
			if(!checkEmpInfo(empInfo)){
				return;
			}
			
			messageEntityCom.setToonUserId(empInfo.getToonUserId()+"");
			String intCode = findCodeByToken();
			messageEntityCom.setToonUrl(MessageFormat.format(appUrl, maininfo.getId(),maininfo.getMiMtSubject(),intCode));
		}

		List<MessageEntity> messageEntities = new ArrayList<MessageEntity>();
		messageEntities.add(messageEntity);
		messageEntities.add(messageEntityCom);
		sendMessage(messageEntities);
	}

	/**
	 * MethodName : saveSummaryMessage
	 *
	 * @Description : 发送会议纪要通知
	 * @param summary
	 *
	 * @author zhangxiang
	 * @date: 2017年11月27日 上午10:33:31
	 */
	public void saveSummaryMessage(Summary summary) {
		Maininfo maininfo = maininfoDao.getById(summary.getMiId());
		EmpInfoVo empInfoVo = empInfoService.getEmpInfoByEiId(maininfo.getComId(), UserServletContext.getUserInfo().getUserNo());
		List<Inviter> inviters = inviterDao.getByMiId(summary.getMiId());
		List<MessageEntity> messageEntities = new ArrayList<MessageEntity>();
		String date = DateFormatUtils.format(summary.getCreateTime(), Constants.DATEFORMAT_YYYYMMDDHHMM);
		//如果可见方式 全员可见
		if(Constants.TRUE.equals(summary.getMsShow())) {
			for(Inviter inviter : inviters) {
				//发起人发送通知
				if(inviter.getInviterEiId().equals(maininfo.getCreateEiId())) {
					continue;
				}
				MessageEntity messageEntity = new MessageEntity();
				messageEntity.setToFeedId(empInfoVo.getFeedId());
				messageEntity.setMessage(MessageFormat.format(summaryMeeting, maininfo.getMiMtSubject(),date,UserServletContext.getUserInfo().getUserName(),summary.getMtContent()));
				ToonOwner owner = new ToonOwner();
				EmpInfoVo empInfo = empInfoService.getEmpInfoByEiId(maininfo.getComId(), inviter.getInviterEiId());
				
				if(!checkEmpInfo(empInfo)){
					continue;
				}
				
				owner.setFeed_id(empInfo.getFeedId());
				owner.setCompany_id(UserServletContext.getUserInfo().getCompanyId());
				String code = findCodeByToken();
				messageEntity.setToonUrl(MessageFormat.format(appUrl, maininfo.getId(),maininfo.getMiMtSubject(),code));
				messageEntity.setBizNo(maininfo.getId()+""+System.currentTimeMillis()+inviter.getInviterEiId());
				messageEntity.setSubCatalog("会议纪要");
				messageEntity.setAppId(appId);
				messageEntity.setAppSecret(appKey);

				messageEntity.setToonUserId(empInfo.getToonUserId()+"");

				messageEntities.add(messageEntity);
			}
			MessageEntity messageEntity = new MessageEntity();
			//发起人通知
			messageEntity.setToFeedId(empInfoVo.getFeedId());
			messageEntity.setMessage(MessageFormat.format(summaryMeeting, maininfo.getMiMtSubject(),date,UserServletContext.getUserInfo().getUserName(),summary.getMtContent()));
			ToonOwner owner = new ToonOwner();
			EmpInfoVo empInfo = empInfoService.getEmpInfoByEiId(maininfo.getComId(), maininfo.getCreateEiId());
			
			if(!checkEmpInfo(empInfo)){
				return;
			}
			
			owner.setFeed_id(empInfo.getFeedId());
			owner.setCompany_id(UserServletContext.getUserInfo().getCompanyId());
			String code = findCodeByToken();
			messageEntity.setToonUrl(MessageFormat.format(appUrl, maininfo.getId(),maininfo.getMiMtSubject(),code));
			messageEntity.setBizNo(maininfo.getId()+""+System.currentTimeMillis()+maininfo.getCreateEiId());
			messageEntity.setSubCatalog("会议纪要");
			messageEntity.setAppId(appId);
			messageEntity.setAppSecret(appKey);

			messageEntity.setToonUserId(empInfo.getToonUserId()+"");

			messageEntities.add(messageEntity);
		}else {
			MessageEntity messageEntity = new MessageEntity();
			messageEntity.setToFeedId(empInfoVo.getFeedId());
			messageEntity.setMessage(MessageFormat.format(summaryMeeting, maininfo.getMiMtSubject(),date,UserServletContext.getUserInfo().getUserName(),summary.getMtContent()));
			EmpInfoVo empInfo = empInfoService.getEmpInfoByEiId(maininfo.getComId(), UserServletContext.getUserInfo().getUserNo());
			
			if(!checkEmpInfo(empInfo)){
				return;
			}
			
			String code = findCodeByToken();
			messageEntity.setToonUrl(MessageFormat.format(appUrl, maininfo.getId(),maininfo.getMiMtSubject(),code));
			messageEntity.setBizNo(maininfo.getId()+""+System.currentTimeMillis()+UserServletContext.getUserInfo().getUserNo());
			messageEntity.setSubCatalog("会议纪要");
			messageEntity.setAppId(appId);
			messageEntity.setAppSecret(appKey);

			messageEntity.setToonUserId(empInfo.getToonUserId()+"");

			messageEntities.add(messageEntity);
		}
		
		sendMessage(messageEntities);
	}
	
	private boolean checkEmpInfo(EmpInfoVo empInfo){
		//如果是部门，并且没有人的话，直接过去
        return empInfo != null && !StringUtils.isEmpty(empInfo.getFeedId());
    }

	private String findCodeByToken() {
		ToonVisitor visitor = new ToonVisitor();
		visitor.setFeed_id(UserServletContext.getUserInfo().getFeeId());
		Response<String> response =null;//commonsRomeotService.getCode(accessId, accessSecret, JSON.toJSONString(toonCode));
		logger.info("findCodeByToken:{}"+  JSON.toJSONString(response));
		if(response != null) {
			return response.getData();
		}
		return "";
	}

	/**
	 * MethodName : cancelMeetingMessage
	 *
	 * @Description : 取消会议通知
	 * @param miId
	 *
	 * @author zhangxiang
	 * @date: 2017年11月27日 上午10:34:04 
	 */
	public void cancelMeetingMessage(String miId) {
		Maininfo maininfo = maininfoDao.getById(miId);
		EmpInfoVo empInfoVo = empInfoService.getEmpInfoByEiId(maininfo.getComId(), maininfo.getCreateEiId());
		List<Inviter> inviters = inviterDao.getByMiId(miId);
		List<MessageEntity> messageEntities = new ArrayList<MessageEntity>();
		for(Inviter inviter : inviters) {
			//发送通知
			String date = DateFormatUtils.format(maininfoService.getMeetingStarttime(maininfo.getId()), Constants.DATEFORMAT_YYYYMMDDHHMM);
			RoomResource roomResource = roomResourceDao.getById(maininfo.getRrId());
			RoomPosition roomPosition = new RoomPosition();
			roomPosition.setId(roomResource.getRpId());
			String rpPositionName = roomPositionDao.get(roomPosition).getRpPositionName();
			String position = rpPositionName+" "+roomResource.getRrRoomName();
			MessageEntity messageEntity = new MessageEntity();
			messageEntity.setToFeedId(empInfoVo.getFeedId());
			messageEntity.setMessage(MessageFormat.format(cancelMeeting, maininfo.getMiMtSubject(),date,position,UserServletContext.getUserInfo().getUserName()));
			EmpInfoVo empInfo = empInfoService.getEmpInfoByEiId(maininfo.getComId(), inviter.getInviterEiId());
			
			if(!checkEmpInfo(empInfo)){
				continue;
			}
			
			String code = findCodeByToken();
			messageEntity.setToonUrl(MessageFormat.format(appUrl, maininfo.getId(),maininfo.getMiMtSubject(),code));
			messageEntity.setSubCatalog("会议取消");
			messageEntity.setAppId(appId);
			messageEntity.setAppSecret(appKey);
			messageEntity.setBizNo(maininfo.getId()+""+System.currentTimeMillis()+maininfo.getCreateEiId());
			messageEntity.setToonUserId(empInfo.getToonUserId()+"");
			messageEntities.add(messageEntity);
		}
		sendMessage(messageEntities);
	}
}

