package com.sgai.property.quality.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sgai.common.persistence.Page;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.common.exception.BusinessException;
import com.sgai.property.common.exception.ReturnType;
import com.sgai.property.common.util.QtBeanMapper;
import com.sgai.property.commonService.service.EmpInfoServiceImpl;
import com.sgai.property.commonService.vo.Response;
import com.sgai.property.quality.constants.Constants;
import com.sgai.property.quality.dao.*;
import com.sgai.property.quality.entity.*;
import com.sgai.property.quality.vo.DefectOrderDetailVo;
import com.sgai.property.quality.vo.DefectOrderVo;
import com.sgai.property.quality.vo.EmpInfo;
import com.sgai.property.quality.vo.OrderDetail;
import com.sgai.property.quality.vo.dto.DealOrderDto;
import com.sgai.property.quality.vo.dto.DefectOrderDto;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;


@Service
public class DefectOrderServiceImpl{
	
	private static final Logger logger = LogManager.getLogger(DefectOrderServiceImpl.class);

	@Autowired
	IQtDefectOrderDao defectOrderDao;
	@Autowired
	IQtDefectOrderDetailDao defectOrderDetailDao;
	@Autowired
	IQtDefectAttachmentDao defectAttachmentDao;
	@Autowired
	IQtDefectLabelDao defectLabelDao;
	@Autowired
	IQtDictGeneralDao dicGeneralDao;
	@Autowired
    QCommonServicelmpl commonService;
	@Autowired
	SendMessageServiceImpl sendMessageService;
	@Autowired
	EmpInfoServiceImpl empInfoService;
	@Autowired
	private IQtTaskItemDao taskItemDao;
	@Autowired
	private IQtProfessionalCategoryDao categoryDao;
	@Value("${sendMessage.orderUrl}")
    private String orderUrl;
	@Value("${wytSendMessage.accessId}")
	private String accessId;
	@Value("${wytSendMessage.accessSecret}")
	private String accessSecret;
	
	
	
	//工单撤消原因数据字典对应的code
	private static final String CANCEL_REASON = "quality_cancel_reason";
	//发起工单通知标题
	private static final String CREATE_MESSAGE_TITLE = "整改通知";
	//改派工单通知标题
	private static final String REDISTRIBUTE_MESSAGE_TITLE = "整改改派通知";
	//反馈工单通知标题
	private static final String FEEDBACK_MESSAGE_TITLE = "整改完成通知";
	//发起工单通知模版
	private String create_message_model = "检验内容：%s\n检验时间：%s\n检验项：%s\n发起人：%s";
	//改派工单通知模版
	private String redistribute_message_model = "检验内容：%s\n改派时间：%s\n检验项：%s\n原整改人：%s";
	//反馈工单通知模版
	private String feedback_message_model = "检验内容：%s\n整改时间：%s\n检验项：%s\n整改人：%s";
	
	@Transactional
	public boolean createOrder(DefectOrderDto defectOrderDto) {
		
		//工单信息
		QtDefectOrder qtDefectOrder = new QtDefectOrder();
			
			QtBeanMapper.copy(defectOrderDto, qtDefectOrder);
			
			fillCreateInfo(qtDefectOrder);
			
			
			//解析token,获取执行人信息
			String executorFeedId = defectOrderDto.getDoExecutorId();
			Response<EmpInfo> excutorEmpInfo = null;/*commonsRomeotService.getEmpInfoByfeedId(qtDefectOrder.getComId(), executorFeedId)*/
        if(excutorEmpInfo == null || excutorEmpInfo.getData() == null){
				throw new BusinessException(ReturnType.ParamIllegal, "feedId无法解析！");
			}
			String executorName = excutorEmpInfo.getData().getEiEmpName();
		    String executorIcon = excutorEmpInfo.getData().getEiHeadPicture();
		    String executorDept = excutorEmpInfo.getData().getDeptName();
		    String executorPosotion = excutorEmpInfo.getData().getPositionName();
			//执行人信息
			qtDefectOrder.setDoExecutorId(executorFeedId);
			qtDefectOrder.setDoExecutorName(executorName);
			
			//当前处理人
			qtDefectOrder.setDoUpdateId(executorFeedId);
			qtDefectOrder.setDoUpdateName(executorName);
			qtDefectOrder.setDoUpdateTime(System.currentTimeMillis());
			qtDefectOrder.setDoUpdateIcon(executorIcon);
			qtDefectOrder.setDoUpdateDept(executorDept);
			qtDefectOrder.setDoUpdatePosition(executorPosotion);
			qtDefectOrder.setDoStatus(Constants.OrderStatus.DEELING);//处理中
			//工单列表显示的图片，如果工单有图片就取第一张，没有就为空，界面上给个默认图片
			if(CollectionUtils.isNotEmpty(defectOrderDto.getUrlList())){
				qtDefectOrder.setDoIcon(defectOrderDto.getUrlList().get(0));
			}
			
		
		//工单处理信息
		QtDefectOrderDetail qtDefectOrderDetail = new QtDefectOrderDetail();
		
			fillCreateInfo(qtDefectOrderDetail);
			
			qtDefectOrderDetail.setOdExecutorId(executorFeedId);
			qtDefectOrderDetail.setOdExecutorName(executorName);
			
			qtDefectOrderDetail.setUpdateTime(System.currentTimeMillis());
			qtDefectOrderDetail.setOdContent(defectOrderDto.getDoContent());
			
			qtDefectOrderDetail.setOdOperation(Constants.OrderOperate.NEW);
		
		insertOrderAndDetail(qtDefectOrder, qtDefectOrderDetail,defectOrderDto.getUrlList());
		//将检查项设置为发起过整改
		QtTaskItem taskItem=new QtTaskItem();
		taskItem.setId(defectOrderDto.getItemId());
		taskItem.setTiHasDefect(QtTaskItem.HAS_DEFACT);
		taskItemDao.updateById(taskItem);
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				//给执行人发送通知
				String message = String.format(create_message_model, 
						qtDefectOrder.getDoName(),
						getCurrentTime(),
						qtDefectOrder.getObjectName(),
						qtDefectOrder.getDoCreateName());
				
				String code = commonService.getNewCode(qtDefectOrder.getComId(), excutorEmpInfo.getData().getUserNo(),accessId,accessSecret);
				String toonUrl = orderUrl +"?code="+ code +"&orderId=" + qtDefectOrder.getId() ;
				sendMessage(qtDefectOrder.getComId(), excutorEmpInfo.getData().getUserNo(), message, CREATE_MESSAGE_TITLE, qtDefectOrder.getId(),toonUrl);
			}
		}).start();
		
		
		return true;
	}

	@Transactional
	public boolean redistributeOrder(String orderId, String feedId, String content){
		
		

		Long comId = UserServletContext.getUserInfo().getCompanyId();
		
		Response<EmpInfo> excutorEmpInfo = /*commonsRomeotService.getEmpInfoByfeedId(comId, feedId);*/null;
		if(excutorEmpInfo == null || excutorEmpInfo.getData() == null){
			throw new BusinessException(ReturnType.ParamIllegal, "feedId无法解析！");
		}
		String executorName = excutorEmpInfo.getData().getEiEmpName();
		String executorIcon = excutorEmpInfo.getData().getEiHeadPicture();
		String executorDept = excutorEmpInfo.getData().getDeptName();
		String executorPosotion = excutorEmpInfo.getData().getPositionName();
		
		QtDefectOrder qtDefectOrder = defectOrderDao.getById(orderId);
		if(qtDefectOrder == null){
			throw new BusinessException(ReturnType.ParamIllegal,"找不到该工单！");
		}
			qtDefectOrder.setId(orderId);
			qtDefectOrder.setDoUpdateId(feedId);
			qtDefectOrder.setDoUpdateName(executorName);
			qtDefectOrder.setDoUpdateIcon(executorIcon);
			qtDefectOrder.setDoUpdateDept(executorDept);
			qtDefectOrder.setDoUpdatePosition(executorPosotion);
			qtDefectOrder.setDoUpdateTime(System.currentTimeMillis());
			qtDefectOrder.setDoStatus(Constants.OrderStatus.DEELING);
		
		QtDefectOrderDetail qtDefectOrderDetail = new QtDefectOrderDetail();
			fillCreateInfo(qtDefectOrderDetail);
			
			qtDefectOrderDetail.setOId(orderId);
			qtDefectOrderDetail.setOdExecutorId(feedId);
			qtDefectOrderDetail.setOdExecutorName(executorName);
			qtDefectOrderDetail.setUpdateTime(System.currentTimeMillis());
			
			qtDefectOrderDetail.setOdContent(content);
			qtDefectOrderDetail.setOdOperation(Constants.OrderOperate.REDISTRIBUTE);
		
			
//		updateOrderAndDetail(Constants.OrderOperate.REDISTRIBUTE,qtDefectOrder, qtDefectOrderDetail,null);
		redistributeOrderAndDetail(Constants.OrderOperate.REDISTRIBUTE,qtDefectOrder, qtDefectOrderDetail,qtDefectOrderDetail.getOdCreateId());
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				//给被改派人发送通知
				String message = String.format(redistribute_message_model,
						qtDefectOrder.getDoName(),
						getCurrentTime(),
						qtDefectOrder.getObjectName(),
						qtDefectOrderDetail.getOdCreateName());
				String code = commonService.getNewCode(qtDefectOrder.getComId(), excutorEmpInfo.getData().getUserNo(),accessId,accessSecret);
				String toonUrl = orderUrl +"?code="+ code +"&orderId=" + qtDefectOrder.getId();
				sendMessage(qtDefectOrder.getComId(), excutorEmpInfo.getData().getUserNo(), message, REDISTRIBUTE_MESSAGE_TITLE, qtDefectOrder.getId(),toonUrl);

			}
		}).start();
		
		return true;
	}

	@Transactional
	public boolean feedbackOrder(DealOrderDto dealOrderDto) {
		
		QtDefectOrderDetail qtDefectOrderDetail = new QtDefectOrderDetail();
		qtDefectOrderDetail.setOdOperation(Constants.OrderOperate.FEEDBACK);
			
		QtDefectOrder qtDefectOrder = defectOrderDao.getById(dealOrderDto.getoId());
		if(qtDefectOrder == null){
			throw new BusinessException(ReturnType.ParamIllegal,"找不到该工单！");
		}
		//工单反馈后自动变成已完成状态，不需要确认完成动作
		qtDefectOrder.setDoStatus(Constants.OrderStatus.FINISHED);
		
		fillDealInfo(dealOrderDto, qtDefectOrderDetail, qtDefectOrder);
			
		updateOrderAndDetail(Constants.OrderStatus.DEELING, qtDefectOrder, qtDefectOrderDetail,dealOrderDto.getUrlList());
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				//给发起人发送通知
				String message = String.format(feedback_message_model,
						qtDefectOrder.getDoName(),
						getCurrentTime(),
						qtDefectOrder.getObjectName(),
						qtDefectOrder.getDoCreateName());
				String creatorFeedId = qtDefectOrder.getDoCreateId();
				Response<EmpInfo> excutorEmpInfo = /*commonsRomeotService.getEmpInfoByfeedId(qtDefectOrder.getComId(), creatorFeedId);*/null;
				if(excutorEmpInfo == null || excutorEmpInfo.getData() == null){
					throw new BusinessException(ReturnType.ParamIllegal, "feedId无法解析！");
				}
				//发通知
				String code = commonService.getNewCode(qtDefectOrder.getComId(), excutorEmpInfo.getData().getUserNo(),accessId,accessSecret);
				String toonUrl = orderUrl +"?code="+ code +"&orderId=" + qtDefectOrder.getId();
				sendMessage(qtDefectOrder.getComId(), excutorEmpInfo.getData().getUserNo(), message, FEEDBACK_MESSAGE_TITLE, qtDefectOrder.getId(),toonUrl);

			}
		}).start();
		
		return true;
	}

	@Transactional
	public boolean evaluateOrder(DealOrderDto dealOrderDto) {
		QtDefectOrderDetail qtDefectOrderDetail = new QtDefectOrderDetail();
		qtDefectOrderDetail.setOdOperation(Constants.OrderOperate.EVALUTE);
		
		QtDefectOrder qtDefectOrder = new QtDefectOrder();
		qtDefectOrder.setDoStatus(Constants.OrderStatus.EVALUTED);
		
		fillDealInfo(dealOrderDto, qtDefectOrderDetail, qtDefectOrder);
			
		updateOrderAndDetail(Constants.OrderStatus.FINISHED, qtDefectOrder, qtDefectOrderDetail,dealOrderDto.getUrlList());
	
		return true;
	}

	@Transactional
	public boolean cancelOrder(String reason, String orderId) {
		
		QtDefectOrderDetail qtDefectOrderDetail = new QtDefectOrderDetail();
		qtDefectOrderDetail.setOdOperation(Constants.OrderOperate.CANCEL);
		
		fillCreateInfo(qtDefectOrderDetail);
		
		qtDefectOrderDetail.setOdExecutorId(qtDefectOrderDetail.getOdCreateId());//实际处理人
		qtDefectOrderDetail.setOdExecutorName(qtDefectOrderDetail.getOdCreateName());
		qtDefectOrderDetail.setOdContent(reason);
		qtDefectOrderDetail.setOId(orderId);
		qtDefectOrderDetail.setUpdateTime(System.currentTimeMillis());
		
		QtDefectOrder qtDefectOrder = new QtDefectOrder();
		qtDefectOrder.setDoStatus(Constants.OrderStatus.CLOSED);
		qtDefectOrder.setId(orderId);
		qtDefectOrder.setDoUpdateTime(System.currentTimeMillis());
			
		updateOrderAndDetail(Constants.OrderStatus.DEELING, qtDefectOrder, qtDefectOrderDetail,null);
	
		return true;
	}

	public Page<DefectOrderVo> listOrder(String categoryId, Integer pageNum, Integer pageSize, Integer status) {
		if(pageNum == null){
			pageNum=0;
		}
		if(pageSize == null){
			pageSize=10;
		}
		if(status == null){
			status = 0;
		}
        Page<DefectOrderVo> pageInfo=new Page<>(pageNum,pageSize);

		String creatorFeedId = UserServletContext.getUserInfo().getFeeId();
		DefectOrderVo defectOrderVo=new DefectOrderVo();
		defectOrderVo.setStatus(status);
		defectOrderVo.setCreateId(creatorFeedId);
		if(StringUtils.isNotEmpty(categoryId)){
			defectOrderVo.setCategoryId(categoryId);
		}
		defectOrderVo.setPage(pageInfo);
		pageInfo.setList(defectOrderDao.getOrdersByStatus(defectOrderVo));
		return pageInfo;
	}

	public DefectOrderDetailVo orderDetail(String orderId) {
		
		//缺陷工单信息
		QtDefectOrder defectOrder = defectOrderDao.getById(orderId);
		
		if(defectOrder == null){
			return null;
		}

		QtDefectOrderDetail qtDefectOrderDetail=new QtDefectOrderDetail();
		qtDefectOrderDetail.setoId(orderId);
		Page<QtDefectOrderDetail> itemGroupPage=new Page<>(1,Integer.MAX_VALUE);
		itemGroupPage.setOrderBy("update_time desc");
		qtDefectOrderDetail.setPage(itemGroupPage);
        List<QtDefectOrderDetail> details = defectOrderDetailDao.findList(qtDefectOrderDetail);
        DefectOrderDetailVo defectOrderDetailVo = new DefectOrderDetailVo();
        QtBeanMapper.copy(defectOrder, defectOrderDetailVo);
        defectOrderDetailVo.setOrderUrls(getAttchments(defectOrder.getId(), Constants.OrderOperate.NEW));
        List<OrderDetail> detailVos = new ArrayList<>();
        for(QtDefectOrderDetail detail : details){
        	OrderDetail detailVo = new OrderDetail();
        	QtBeanMapper.copy(detail, detailVo);
        	if(detail!=null && (detail.getOdOperation() == Constants.OrderOperate.FEEDBACK || detail.getOdOperation() == Constants.OrderOperate.EVALUTE)){
        		detailVo.setDetailUrls(getAttchments(detail.getId(), detail.getOdOperation()));
        	}
			detailVo.setOdId(detail.getId());
        	detailVos.add(detailVo);
        }
        defectOrderDetailVo.setDetailList(detailVos);
		//工单详情头部显示的是专业范畴的图片
		String pcId = defectOrder.getCategoryId();
		defectOrderDetailVo.setPcUrl(categoryDao.getById(pcId).getPcIcon());
		defectOrderDetailVo.setDoId(defectOrder.getId());
		if(StringUtils.isEmpty(defectOrderDetailVo.getDoIcon())){
			defectOrderDetailVo.setDoIcon("");
		}
		return defectOrderDetailVo;
	}
	/**
	 * 
	 * @param bussinessid 业务ID
	 * @param source 工单来源，参照工单操作状态
	 * @return
	 */
	List<Map<String, Object>> getAttchments(String bussinessid,Integer source){
		QtDefectAttachment attachment = new QtDefectAttachment();
		attachment.setBusinessId(bussinessid);
		attachment.setDaSource(source);
		//附件信息
		List<QtDefectAttachment> attachments = defectAttachmentDao.findList(attachment);
		
		return convertAttchment(attachments);
	}
	
	/**
	 * 精简附件列表字段，转换成Map
	 * @param attachments
	 * @return
	 */
	List<Map<String, Object>> convertAttchment(List<QtDefectAttachment> attachments){
		List<Map<String, Object>> listMap = new ArrayList<>();
		if(CollectionUtils.isEmpty(attachments)){
			return listMap;
		}
		for(QtDefectAttachment attachment : attachments){
			Map<String, Object> map = new HashMap<>();
			map.put("id", attachment.getId());
			map.put("url", attachment.getDaUrl());
			listMap.add(map);
		}
		return listMap;
	}

	public List<Map<String, Object>> getUserLabels() {

		String creatorFeedId = UserServletContext.getUserInfo().getFeeId();
		return defectLabelDao.getUserLabels(creatorFeedId);
	}

	public boolean saveUserLabel(String label) {

		String creatorFeedId = UserServletContext.getUserInfo().getFeeId();
		
		Map<String, Object> map = new HashMap<>();
		map.put("feedId", creatorFeedId);
		map.put("label", label);
		map.put("timeStamp", System.currentTimeMillis());
		int count = defectLabelDao.updateLabel(map);
		if(count>0){
			return false;
		}
		
		QtDefectLabel defectLabel = new QtDefectLabel();
		defectLabel.setDlCreateId(creatorFeedId);
		defectLabel.setDlName(label);
		defectLabel.setDlCreateTime(System.currentTimeMillis());
		defectLabel.setUpdateTime(System.currentTimeMillis());
		defectLabel.setDlValid(Constants.VALID_YES);
		defectLabel.preInsert();
		defectLabelDao.insert(defectLabel);
		
		return true;
	}

	public List<Map<String, Object>> getCancelReasons() {
		
		QtDictGeneral dictGeneral = new QtDictGeneral();
		dictGeneral.setDgCode(CANCEL_REASON);
		List<QtDictGeneral> dictGenerals = dicGeneralDao.findList(dictGeneral);
		List<Map<String, Object>> listMap = new ArrayList<>();
		for(QtDictGeneral dic : dictGenerals){
			Map<String, Object> map = new HashMap<>();
			map.put("reasonId", dic.getId());
			map.put("reason", dic.getDgValue());
			listMap.add(map);
		}
		return listMap;
	}
	
	void fillDealInfo(DealOrderDto dealOrderDto,
					  QtDefectOrderDetail qtDefectOrderDetail,
					  QtDefectOrder qtDefectOrder){
		
		QtBeanMapper.copy(dealOrderDto, qtDefectOrderDetail);
		fillCreateInfo(qtDefectOrderDetail);
		
		qtDefectOrderDetail.setOdExecutorId(qtDefectOrderDetail.getOdCreateId());//实际处理人
		qtDefectOrderDetail.setOdExecutorName(qtDefectOrderDetail.getOdCreateName());
		qtDefectOrderDetail.setUpdateTime(System.currentTimeMillis());
		
		qtDefectOrder.setId(dealOrderDto.getoId());
//		qtDefectOrder.setDoUpdateId(qtDefectOrderDetail.getOdCreateId());
//		qtDefectOrder.setDoUpdateName(qtDefectOrderDetail.getOdCreateName());
		qtDefectOrder.setDoUpdateTime(System.currentTimeMillis());
		
	}
	
	/**
	 * 解析token，填充创建人ID、创建人姓名、创建时间
	 * @param object
	 */
	void fillCreateInfo(Object object){

		String creatorFeedId = UserServletContext.getUserInfo().getFeeId();
		String creatorName = UserServletContext.getUserInfo().getUserName();
		String dept = UserServletContext.getUserInfo().getComName();
		String position = UserServletContext.getUserInfo().getPositionName();
		
		if(object instanceof QtDefectOrder){
			((QtDefectOrder) object).setDoCreateId(creatorFeedId);
			((QtDefectOrder) object).setDoCreateName(creatorName);
			
			if(((QtDefectOrder) object).getDoCreateTime() == null || ((QtDefectOrder) object).getDoCreateTime() ==0){
				((QtDefectOrder) object).setDoCreateTime(System.currentTimeMillis());
			}
			
			((QtDefectOrder) object).setDoCreateDept(dept);
			((QtDefectOrder) object).setDoCreatePosition(position);
			if(StringUtils.isNotEmpty(UserServletContext.getUserInfo().getUserIcon())){
				((QtDefectOrder) object).setDoCreateIcon(UserServletContext.getUserInfo().getUserIcon());
			}
		}
		
		if(object instanceof QtDefectOrderDetail){
			((QtDefectOrderDetail) object).setOdCreateId(creatorFeedId);
			((QtDefectOrderDetail) object).setOdCreateName(creatorName);
			((QtDefectOrderDetail) object).setOdCreateTime(System.currentTimeMillis());
			if(StringUtils.isNotEmpty(UserServletContext.getUserInfo().getUserIcon())){
				((QtDefectOrderDetail) object).setOdCreateIcon(UserServletContext.getUserInfo().getUserIcon());
			}
		}
	}
	
	/**
	 * 批量插入附件
	 * @param qtDefectOrderDetail
	 * @param urlList
	 */
	void batchInsertAttchment(QtDefectOrderDetail qtDefectOrderDetail, List<String> urlList){
		if(CollectionUtils.isNotEmpty(urlList)){
			List<QtDefectAttachment> attachments = new ArrayList<>();
			for(String urlObj : urlList){
				QtDefectAttachment attachment = new QtDefectAttachment();
				attachment.setBusinessId(qtDefectOrderDetail.getId());
				//如果是新发起的工单，则附件保存工单的ID，如果是反馈和评价工单则保存详情ID
				if(qtDefectOrderDetail.getOdOperation() == 0){
					attachment.setBusinessId(qtDefectOrderDetail.getOId());
				}
				attachment.setDaCreateTime(System.currentTimeMillis());
				attachment.setDaSource(qtDefectOrderDetail.getOdOperation());
				attachment.setDaUrl(urlObj);
				attachment.preInsert();
				attachments.add(attachment);
			}
			
			//批量插入
			defectAttachmentDao.batchInsert(attachments);
		}
	}
	
	/**
	 * 
	 * @param qtDefectOrder
	 * @param qtDefectOrderDetail
	 * @param urlList 附件地址列表
	 */
	void insertOrderAndDetail(QtDefectOrder qtDefectOrder, QtDefectOrderDetail qtDefectOrderDetail, List<String> urlList){
		qtDefectOrder.preInsert();
		defectOrderDao.insert(qtDefectOrder);
		qtDefectOrderDetail.setOId(qtDefectOrder.getId());
		qtDefectOrderDetail.preInsert();
		defectOrderDetailDao.insert(qtDefectOrderDetail);

		batchInsertAttchment(qtDefectOrderDetail,urlList);
		
	}
	
	/**
	 * 
	 * @param oldStatus 针对当前操作，工单所处的上一个状态,可避免重复提交的问题
	 * 只有处于处理中的工单才可以改派
	 * 只有处于处理中的工单才可以反馈
	 * 只有处于已完成的工单才可以评价
	 * 只有处于处理中的工单才可以撤消
	 * @param qtDefectOrder
	 * @param qtDefectOrderDetail
	 * @param urlList 附件地址列表
	 */
	synchronized void updateOrderAndDetail(int oldStatus, QtDefectOrder qtDefectOrder, QtDefectOrderDetail qtDefectOrderDetail, List<String> urlList){
		//修改工单状态
		JSONObject jsonObject = (JSONObject) JSON.toJSON(qtDefectOrder);
		jsonObject.put("oldStatus", oldStatus);
		jsonObject.put("newStatus", qtDefectOrder.getDoStatus());
		Map<String, Object> paramMap = JSONObject.toJavaObject(jsonObject, Map.class);
		int i = defectOrderDao.updateByIdAndStatus(paramMap);
		if(i>0){
			//插入明细信息
			qtDefectOrderDetail.preInsert();
			defectOrderDetailDao.insert(qtDefectOrderDetail);
			
			batchInsertAttchment(qtDefectOrderDetail,urlList);
			
		}else{
			throw new BusinessException(ReturnType.Error, "工单状态已发生变化");
		}
	}

	/**
	 * 单独处理改派，只有工单处于处理中，且当前操作人是发起人或处理人时，才可以撤消，避免多人改派的问题
	 * @param oldStatus
	 * @param qtDefectOrder
	 * @param qtDefectOrderDetail
	 */
	synchronized void redistributeOrderAndDetail(int oldStatus,QtDefectOrder qtDefectOrder,QtDefectOrderDetail qtDefectOrderDetail,String currentFeedId){
		//修改工单状态
		JSONObject jsonObject = (JSONObject) JSON.toJSON(qtDefectOrder);
		jsonObject.put("oldStatus", oldStatus);
		jsonObject.put("currentFeedId", currentFeedId);
		Map<String, Object> paramMap = JSONObject.toJavaObject(jsonObject, Map.class);
		int i = defectOrderDao.redistributeByStatusAndUser(paramMap);
		if(i>0){
			//插入明细信息
			qtDefectOrderDetail.preInsert();
			defectOrderDetailDao.insert(qtDefectOrderDetail);

		}else{
			throw new BusinessException(ReturnType.Error, "工单状态已发生变化");
		}
	}
	
	/**
	 * 
	 * @param comId 组织ID
	 * @param eiId 员工ID
	 * @param message 消息内容
	 * @param subCatalog 消息标题
	 */
	void sendMessage(Long comId, Long eiId, String message, String subCatalog, String bizNo,String toonUrl){
		sendMessageService.sendMessage(comId, eiId, message, subCatalog, bizNo,toonUrl);
		logger.info("发送消息成功！\n"+message);
		
	}
	
	/**
	 * 获取当前时间 yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	String getCurrentTime(){
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}

}
