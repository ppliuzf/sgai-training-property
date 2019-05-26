package com.sgai.property.meeting.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.sgai.common.persistence.Page;
import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.common.exception.BusinessException;
import com.sgai.property.common.exception.ReturnType;
import com.sgai.property.common.util.StringsUtil;
import com.sgai.property.common.util.TimeUtil;
import com.sgai.property.commonService.dao.IDictGeneralDao;
import com.sgai.property.commonService.entity.DictGeneral;
import com.sgai.property.commonService.vo.UserInfoVo;
import com.sgai.property.meeting.constants.Constants;
import com.sgai.property.meeting.dao.*;
import com.sgai.property.meeting.entity.*;
import com.sgai.property.meeting.vo.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author 146584
 * @date 2017-11-6 10:43
 */
@Service
public class RoomResourceServiceImpl extends MoreDataSourceCrudServiceImpl<IRoomResourceDao,RoomResource>{

	@Autowired
	private RoomPositionServiceImpl roomPositionService;
	@Autowired
	private MaininfoServiceImpl meetingService;
	@Autowired
	private TokenServiceImpl tokenService;
	@Autowired
	private RoomDeviceStatusServiceImpl roomDeviceStatusService;

	@Autowired
	private IDictGeneralDao dictGeneralDao;
	@Autowired
	private IMaininfoDao meetingInfoDao;
	@Autowired
	private IRoomResourceDao roomResourceDao;
	@Autowired
	private IInviterDao iInviterDao;
	@Autowired
	private IMaterielDao materielDao;
	@Autowired
	private IRoomPositionDao roomPositionDao;
	@Autowired
	private IRoomDeviceStatusDao roomDeviceStatusDao;


	@Value("${meetings.room.icon}")
	private String roomIcon;
	@Value("${mat.List-url}")
	private String matListUrl;
	@Value("${mat.type-url}")
	private String matTypeUrl;


//	public Page<RoomResourceVo> findAllPage(Long comId,int pageNo, int pageSize) {
//		Page<RoomResourceVo> page = new Page<>(pageNo,pageSize);
//		List<RoomResourceVo> voList = new ArrayList<>();
//
//		Page<RoomResource> roomResourcePage = new Page<>(pageNo,pageSize);
//		roomResourcePage.setOrderBy(Constants.SqlString.CreateTimeDesc);
//		RoomResource roomResourceEntity = new RoomResource();
//		roomResourceEntity.setComId(comId);
//		roomResourceEntity.setIsDelete(Constants.IsDelete.NO);
//		roomResourcePage = super.findPage(roomResourcePage,roomResourceEntity);
//
//		BeanUtils.copyProperties(roomResourcePage,page);
//		if(null != roomResourcePage && null != roomResourcePage.getList() && !roomResourcePage.getList().isEmpty()){
//			for(RoomResource roomResource : roomResourcePage.getList()){
//				//copy list对象数据
//				RoomResourceVo vo = new RoomResourceVo();
//				BeanUtils.copyProperties(roomResource,vo);
//				//处理id
//				vo.setRrId(roomResource.getId());
//
//				//获取设备信息
//				RoomDeviceStatus rds = new RoomDeviceStatus();
//				rds.setIsDelete(0L);
//				rds.setRrId(roomResource.getId());
//				List<RoomDeviceStatus> rdsList = roomDeviceStatusDao.findList(rds);
//				if(null != rdsList && rdsList.isEmpty()){
//					List<RoomDeviceDetailVo> deviceDetailVoList = new ArrayList<>();
//					rdsList.forEach(roomDeviceStatus -> {
//						RoomDeviceDetailVo deviceDetailVo = new RoomDeviceDetailVo();
//						BeanUtils.copyProperties(roomDeviceStatus,deviceDetailVo);
//						deviceDetailVo.setRdsId(roomDeviceStatus.getId());
//						deviceDetailVoList.add(deviceDetailVo);
//					});
//					vo.setDeviceDetailVoList(deviceDetailVoList);
//				}
//
//				voList.add(vo);
//			}
//		}
//		page.setList(voList);
//
//		return page;
//	}


	public Page<RoomResourceVo>searchRoomResourcePage(String comId,String keyWord, Integer rrRoomState ,int pageNo, int pageSize){ //根据 名称,位置,设备 搜索
		if(null != keyWord && keyWord.trim().length() > Constants.room.nameLenght){
			throw new BusinessException(ReturnType.ParamIllegal,"关键字长度不能超过20字");
		}
		Page<RoomResource> roomResourcePage = new Page<>(pageNo,pageSize);
		roomResourcePage.setOrderBy(Constants.SqlString.CreateTimeDesc);

		//判断是否查询房间 启用 禁用 状态
		if(null != rrRoomState && Constants.RoomUserStatus.Nome.equals(rrRoomState)){
			rrRoomState = null;
		}
		RoomResource roomResourceEntity = new RoomResource();
		roomResourceEntity.setComId(comId);
		roomResourceEntity.setIsDelete(Constants.IsDelete.NO);
		roomResourceEntity.setRrRoomName(StringsUtil.fuzzySearchStr(keyWord));
		roomResourceEntity.setRrRoomState(rrRoomState);
		roomResourceEntity.preGet();
		roomResourcePage = super.findPage(roomResourcePage,roomResourceEntity);

		List<RoomResourceVo> roomResourceVoList = new ArrayList<>();
		if(null != roomResourcePage && null != roomResourcePage.getList() && !roomResourcePage.getList().isEmpty()){
			for(RoomResource roomResource : roomResourcePage.getList()){
				RoomResourceVo vo = new RoomResourceVo();
				BeanUtils.copyProperties(roomResource,vo);
				vo.setRrId(roomResource.getId());
				//获取设备信息
				vo.setDeviceDetailVoList(this.getRoomDeviceDetailList(roomResource.getId()));
				roomResourceVoList.add(vo);
			}
		}
		Page<RoomResourceVo> page = new Page<>(pageNo,pageSize);
		BeanUtils.copyProperties(roomResourcePage,page);
		page.setList(roomResourceVoList);
		return page;
	}

	/**
	 * 获取会议室对应的设备信息
	 * @param rrId 会议室id
	 * @return
	 */
	public List<RoomDeviceDetailVo> getRoomDeviceDetailList(String rrId){
		List<RoomDeviceDetailVo> deviceDetailVoList = new ArrayList<>();
		if(null != rrId){
			RoomDeviceStatus rds = new RoomDeviceStatus();
			rds.setIsDelete(0L);
			rds.setRrId(rrId);
			rds.preGet();
			List<RoomDeviceStatus> rdsList = roomDeviceStatusDao.findListDev(rds);
			if(null != rdsList && !rdsList.isEmpty()){
				rdsList.forEach(roomDeviceStatus -> {

					RoomDeviceDetailVo deviceDetailVo = new RoomDeviceDetailVo();
					BeanUtils.copyProperties(roomDeviceStatus,deviceDetailVo);
					deviceDetailVo.setRdsId(roomDeviceStatus.getId());
					deviceDetailVoList.add(deviceDetailVo);
				});
			}
		}
		return deviceDetailVoList;
	}


	public Page<RoomPositionVo> findRoomPositionPage(String comId,int pageNo, int pageSize) {
		Page<RoomPositionVo> page = new Page<>();
		Page<RoomPosition> roomPositionPage = new Page<>();
		roomPositionPage.setOrderBy(Constants.SqlString.CreateTimeDesc);
		roomPositionPage.setPageNo(pageNo);
		roomPositionPage.setPageSize(pageSize);
		RoomPosition roomPositionEntity = new RoomPosition();
		roomPositionEntity.setComId(comId);
		roomPositionEntity.setIsDelete(Constants.IsDelete.NO);
		roomPositionEntity.preGet();
		roomPositionPage = roomPositionService.findPage(roomPositionPage, roomPositionEntity);

		List<RoomPositionVo> voList = new ArrayList<>();
		if(null != roomPositionPage && null != roomPositionPage.getList() && !roomPositionPage.getList().isEmpty()){
			for(RoomPosition roomPosition : roomPositionPage.getList()){
				RoomPositionVo vo = new RoomPositionVo();
				BeanUtils.copyProperties(roomPosition,vo);
				vo.setRpId(roomPosition.getId());
				voList.add(vo);
			}
		}
		BeanUtils.copyProperties(roomPositionPage,page);
		page.setList(voList);

		return page;
	}

	/**
	 * 获取我预定的会议室列表
	 * @param myReserveRoomDto
	 * @return
	 */
	public Page<MyReserveRoomVo> getMyReserve(MyReserveRoomDto myReserveRoomDto, int pageNo, int pageSize, String falg){
		Page<MyReserveRoomDto> result = new Page<>(pageNo,pageSize);

		if(null != myReserveRoomDto.getRrRoomName() && !"".equals(myReserveRoomDto.getRrRoomName())){
			//处理字符串特殊字符
			myReserveRoomDto.setRrRoomName(StringsUtil.fuzzySearchStr(myReserveRoomDto.getRrRoomName().trim()));
		}else {
			myReserveRoomDto.setRrRoomName(null);
		}

        if("".equals(myReserveRoomDto.getCreateEiName())){
			myReserveRoomDto.setCreateEiName(null);
		}

		if("".equals(falg) || falg==null){
			myReserveRoomDto.setCreateBy(UserServletContext.getUserInfo().getUserId());
		}
		
		  
			//个人预定列表
		 result.setOrderBy(" ma.mi_status ASC");
			myReserveRoomDto.setPage(result);
			List<MyReserveRoomVo> myReserveRoomVoList = null;
			try {
				myReserveRoomVoList = meetingInfoDao.getMyReserve(myReserveRoomDto);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(null != myReserveRoomVoList && !myReserveRoomVoList.isEmpty()){
				for(MyReserveRoomVo vo : myReserveRoomVoList){

					vo.setMiMtTimeSeg(getMeetingStartTime(vo.getMiMtDate(),vo.getMiMtTimeSeg()));
					Inviter inviter = new Inviter();
					inviter.setInviterEiId(UserServletContext.getUserInfo().getUserId());
					inviter.setMiId(vo.getId());
					try {
						inviter = iInviterDao.get(inviter);
					} catch (Exception e) {
						e.printStackTrace();
					}
					if(vo.getMiStatus().equals(Constants.MT_INVITE_3)
							&& inviter!= null && inviter.getIsInvite().equals(Constants.MT_INVITE_0)) {
						vo.setMiStatus(Constants.MT_STATUS_5);//已逾期
					}
					vo.setDeviceDetailVoList(this.getRoomDeviceDetailList(vo.getRrId()));
				}
			}


			Page<MyReserveRoomVo> roomVoPage=new Page<>(pageNo,pageSize);
			BeanUtils.copyProperties(result,roomVoPage);
			roomVoPage.setList(myReserveRoomVoList);
			return roomVoPage;
	}



	/**
	 * 获取预定会议信息列表
	 * @param myReserveRoomDto
	 * @return
	 */
	public SchMessageVo getReserveDetail(MyReserveRoomDto myReserveRoomDto, String falg){
		SchMessageVo schMessageVo = new SchMessageVo();

		Map<String,Object> map = new HashMap<>();

		myReserveRoomDto.setComCode(UserServletContext.getUserInfo().getComCode());
		myReserveRoomDto.setModuCode(UserServletContext.getUserInfo().getModuCode());

		Date miStartTime = new Date(myReserveRoomDto.getStartTime()); // 根据long类型的毫秒数生命一个date类型的时间
		String startTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(miStartTime);
		Date miEndTime = new Date(myReserveRoomDto.getEndTime()); // 根据long类型的毫秒数生命一个date类型的时间
		String endTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(miEndTime);

		map.put("rrId",myReserveRoomDto.getRrId());
		map.put("comCode",myReserveRoomDto.getComCode());
		map.put("moduCode",myReserveRoomDto.getModuCode());
		map.put("startTime",startTime);
		map.put("endTime",endTime);

		//获取预定会议信息列表
		List<MyReserveRoomVo> myReserveRoomVoList = meetingInfoDao.getReserveDetail(map);
		if(null != myReserveRoomVoList && !myReserveRoomVoList.isEmpty()){
			for(MyReserveRoomVo vo : myReserveRoomVoList){
				vo.setMiMtTimeSeg(getMeetingStartTime(vo.getMiMtDate(),vo.getMiMtTimeSeg()));
				Inviter inviter = new Inviter();
				inviter.setInviterEiId(UserServletContext.getUserInfo().getUserNo());
				inviter.setMiId(vo.getId());
				inviter = iInviterDao.get(inviter);
				if(vo.getMiStatus().equals(Constants.MT_INVITE_3)
						&& inviter!= null && inviter.getIsInvite().equals(Constants.MT_INVITE_0)) {
					vo.setMiStatus(Constants.MT_STATUS_5);//已逾期
				}
				vo.setDeviceDetailVoList(this.getRoomDeviceDetailList(vo.getRrId()));
			}
		}
		RoomResource roomResource = roomResourceDao.getById(myReserveRoomDto.getRrId());
		schMessageVo.setRoomName(roomResource.getRrRoomName());
		schMessageVo.setReserveRoomVoList(myReserveRoomVoList);
		return schMessageVo;
	}

	/**
	 * 获取会议预定列表
	 * @param myReserveRoomDto
	 * @param pageNo
	 * @param pageSize
	 * @param falg
	 * @return
	 */
	public Page<MyReserveRoomVo> getReserve(MyReserveRoomDto myReserveRoomDto, int pageNo, int pageSize, String falg){
		Page<MyReserveRoomDto> result = new Page<>(pageNo,pageSize);

		if(null != myReserveRoomDto.getRrRoomName() && !"".equals(myReserveRoomDto.getRrRoomName())){
			//处理字符串特殊字符
			myReserveRoomDto.setRrRoomName(StringsUtil.fuzzySearchStr(myReserveRoomDto.getRrRoomName().trim()));
		}else {
			myReserveRoomDto.setRrRoomName(null);
		}

		if("".equals(myReserveRoomDto.getCreateEiName())){
			myReserveRoomDto.setCreateEiName(null);
		}

		myReserveRoomDto.setPage(result);
		List<MyReserveRoomVo> myReserveRoomVoList = meetingInfoDao.getReserve(myReserveRoomDto);
		if(null != myReserveRoomVoList && !myReserveRoomVoList.isEmpty()){
			for(MyReserveRoomVo vo : myReserveRoomVoList){
					vo.setMiMtTimeSeg(getMeetingStartTime(vo.getMiMtDate(),vo.getMiMtTimeSeg()));
					Inviter inviter = new Inviter();
					inviter.setInviterEiId(UserServletContext.getUserInfo().getUserNo());
					inviter.setMiId(vo.getId());
					//这里查出来是多个参会人,怎么会是对象去接收 搞不懂
					/*inviter = iInviterDao.get(inviter);
					if(vo.getMiStatus().equals(Constants.MT_INVITE_3)
							&& inviter!= null && inviter.getIsInvite().equals(Constants.MT_INVITE_0)) {
						vo.setMiStatus(Constants.MT_STATUS_5);//已逾期

					}*/
					List<Inviter> inList = iInviterDao.findList(inviter);
					if(vo.getMiStatus().equals(Constants.MT_INVITE_3)
							&& inList.size()!= 0 && inviter.getIsInvite().equals(Constants.MT_INVITE_0)) {
						vo.setMiStatus(Constants.MT_STATUS_5);//已逾期

					}
					vo.setMiMtTimeSeg("2018-12-20 10:00-2018-12-20 12:00");
					vo.setDeviceDetailVoList(this.getRoomDeviceDetailList(vo.getRrId()));
			}
		}
		Page<MyReserveRoomVo> roomVoPage=new Page<>(pageNo,pageSize);
		BeanUtils.copyProperties(result,roomVoPage);
		roomVoPage.setList(myReserveRoomVoList);
		return roomVoPage;
	}

	/**
	 * 处理时间段方法
	 * @param miMiMtDate
	 * @param miMtTimeSeg
	 * @return
	 */
	private String getMeetingStartTime(Long miMiMtDate, String miMtTimeSeg) {
		String result = new String();
		try {
			/*if(true) {
				return "";
			}*/

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm");

			//处理起始时间
			String[] miMtTimeSegArr = miMtTimeSeg.split(",");
			DictGeneral dictGeneralStart = new DictGeneral();
			dictGeneralStart.setDgKey(miMtTimeSegArr[0]);
			dictGeneralStart.setDgCode("BIZ_MT_TIME_SEG");
			dictGeneralStart = dictGeneralDao.get(dictGeneralStart);
			TimeUtil timeStart = new TimeUtil(dictGeneralStart.getDgValue() + ":00");

			//处理结束时间
			String endKey = miMtTimeSegArr[miMtTimeSegArr.length - 1];
			if(miMtTimeSegArr[0].equals(miMtTimeSegArr[miMtTimeSegArr.length - 1])){
				//处理开始时间key和结束时间key相同的问题
				endKey = (Integer.parseInt(endKey))+"";
			}
			DictGeneral dictGeneralEnd = new DictGeneral();
			if("30".equals(endKey)){
				dictGeneralEnd.setDgValue("22:00");
			}else{
				dictGeneralEnd.setDgKey(endKey);
				dictGeneralEnd.setDgCode("BIZ_MT_TIME_SEG");
				dictGeneralEnd = dictGeneralDao.get(dictGeneralEnd);
			}

			TimeUtil timeEnd = new TimeUtil(dictGeneralEnd.getDgValue() + ":00").addTime(new TimeUtil(0, 30));

			Date startTime = DateUtils.parseDate(DateFormatUtils.format(miMiMtDate, Constants.DATEFORMAT_YYYYMMDD) + " " + timeStart + ":00", Constants.DATEFORMAT_YYYYMMDDHHMMSS);
			Date endTime = DateUtils.parseDate(DateFormatUtils.format(miMiMtDate, Constants.DATEFORMAT_YYYYMMDD) + " " + timeEnd + ":00", Constants.DATEFORMAT_YYYYMMDDHHMMSS);
			result = sdf.format(startTime) + "-" + sdf1.format(endTime);
		} catch (ParseException e) {
			logger.error("日期转换错误！");
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 获取会议详情页
	 * @param comId
	 * @param id
	 * @return
	 */
	public MeetingDetailsVo getMeetingDetails(Long comId,String id){
		MeetingDetailsVo result = new MeetingDetailsVo();
		List<UserInfoVo> userInfoVoList = new ArrayList<>();
		List<MaterielDto> materielDtoList = new ArrayList<>();

		//根据会议详情 获取信息
		Maininfo maininfo = meetingInfoDao.getById(id);
		if(null != maininfo){
			BeanUtils.copyProperties(maininfo,result);
			//处理时间
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			result.setMiMtTimeSeg(sdf.format(getMeetingStartTime(maininfo.getMiMtDate(),maininfo.getMiMtTimeSeg())));

			//根据rrId获取当前会议室的详情
			if(null != maininfo.getRrId()) {
				RoomResource roomResource = roomResourceDao.getById(maininfo.getRrId());
				if(null != roomResource){
					BeanUtils.copyProperties(roomResource,result);
				}
			}

			if(null != maininfo.getId()){
				//获取参会人信息
				Inviter inviter = new Inviter();
				inviter.setMiId(maininfo.getId());
				inviter.setIsDelete(Constants.IsDelete.NO);
				List<Inviter> inviterList = iInviterDao.findList(inviter);
				if(null != inviterList && !inviterList.isEmpty()){
					for(Inviter inviter1 : inviterList){
						UserInfoVo userInfoVo = new UserInfoVo();
						userInfoVo.setUserName(inviter1.getInviterEiName());
						userInfoVo.setIsInvite(inviter1.getIsInvite());
						//获取员工头像
//						List<Long> eiIds = new ArrayList<>();
//						eiIds.add(inviter1.getInviterEiId());
//						Response<List<EmpInfo>> listResponse = commonsRomeotService.getEmpInfoByEiIds(comId,eiIds);
//						if(null != listResponse && null != listResponse.getData() && !listResponse.getData().isEmpty()){
//							if(null != listResponse.getData().get(0).getEiHeadPicture()){
//								userInfoVo.setImageUrl(listResponse.getData().get(0).getEiHeadPicture());
//							}
//						}
						userInfoVoList.add(userInfoVo);
					}
				}
				//获取物料信息
				Materiel materiel = new Materiel();
				materiel.setMtId(maininfo.getId());
				materiel.setIsDelete(0L);
				List<Materiel> materielList = materielDao.findList(materiel);
				if(null != materielList && !materielList.isEmpty()){
					for(Materiel materiel1 : materielList){
						MaterielDto materielDto = new MaterielDto();
						BeanUtils.copyProperties(materiel1,materielDto);
						materielDto.setMaId(materiel1.getId());
						materielDtoList.add(materielDto);
					}
				}
			}
		}
		result.setMaterielDtoList(materielDtoList);
		result.setUserInfoVoList(userInfoVoList);
		return result;
	}


	/**
	 * 更新会议室资源
	 * @param roomResourceModifyDto
	 * @return
	 */
	public Boolean updateRoomResourceById(RoomResourceModifyDto roomResourceModifyDto) {
		RoomResource roomResourceInfo = roomResourceDao.getById(roomResourceModifyDto.getRrId());
		if(null == roomResourceInfo){
			throw new BusinessException(ReturnType.TokenError,"id有误");
		}
		RoomResource roomResource = new RoomResource();
		BeanUtils.copyProperties(roomResourceModifyDto,roomResource);
		roomResource.setUpdateTime(System.currentTimeMillis());
		roomResource.setId(roomResourceModifyDto.getRrId());
		return roomResourceDao.updateById(roomResource) > 0;
	}

    /**
     * 新增会议室
     * @param roomResourceDto
     * @return
     */
    public Boolean saveRoomAdd(RoomResourceDto roomResourceDto) {
		logger.info("》》》》》》》》》》》》来到了业务层 判断有没有选中位置》》》》》》》》》》》");
        if(null == roomResourceDto.getRrRoomPosition() || roomResourceDto.getRrRoomPosition().length() == 0){
            throw new BusinessException(ReturnType.TokenError,"需要创建一个会议室位置");
        }
		logger.info("11111111111111111111111111111111111");

		RoomResource roomResource = new RoomResource();
        //相同位置不能出现同名会议室
        roomResource.setRpId(roomResourceDto.getRpId());
        roomResource.setRrRoomPosition(roomResourceDto.getRrRoomPosition());
        roomResource.setRrRoomName(roomResourceDto.getRrRoomName());
        roomResource.setRrRoomState(Constants.RoomUserStatus.Able);
		logger.info("2222222222222222222222222222222222");
        List<RoomDeviceDetailParam> getDeviceDetailParamList =	roomResourceDto.getDeviceDetailParamList();
        if (getDeviceDetailParamList != null){
            for (RoomDeviceDetailParam  dev :getDeviceDetailParamList){
                roomResource.setRdRoomDevice(dev.getRdRoomDevice());
                roomResource.setRdDeviceId(dev.getRdId());
            }
        }


		logger.info("333333333333333333333333333333333");
       /* if(list != null){
            throw new BusinessException(ReturnType.ParamIllegal," \""+roomResourceDto.getRrRoomName()+"\"已存在，请勿重复添加");
        }*/
        if(roomResourceDto.getRrRoomName().length() > Constants.room.nameLenght){
            throw new BusinessException(ReturnType.ParamIllegal,"会议室名称不得超过20字");
        }
        if(roomResourceDto.getRrRoomDesc().length() > Constants.room.descLenght){
            throw new BusinessException(ReturnType.ParamIllegal,"会议室备注不得超过200字");
        }
        BeanUtils.copyProperties(roomResourceDto,roomResource);
        if(null == roomResource.getRrRoomPicMain() || roomResource.getRrRoomPicMain().isEmpty()){
            roomResource.setRrRoomPicMain(roomIcon);
        }
		logger.info("444444444444444444444444444444444");
        roomResource.setCreateTime(System.currentTimeMillis());
        roomResource.setCreateUser(UserServletContext.getUserInfo().getUserName());
        roomResource.setCreateUserId(UserServletContext.getUserInfo().getUserNo()+"");
        roomResource.setComId(UserServletContext.getUserInfo().getComCode());
        roomResource.setComCode(UserServletContext.getUserInfo().getComCode());
        roomResource.setModuCode(UserServletContext.getUserInfo().getModuCode());
        this.save(roomResource);
        RoomResource list = get(roomResource);
		RoomResource bdNemes = roomResourceDao.getByName(roomResourceDto.getRrRoomName());

		//保存到设备状态表
        if(null != roomResourceDto.getDeviceDetailParamList() && !roomResourceDto.getDeviceDetailParamList().isEmpty()){
            List<RoomDeviceDetailParam> roomDeviceDetailParamList = roomResourceDto.getDeviceDetailParamList();
            if(roomDeviceDetailParamList!=null){

                roomDeviceDetailParamList.forEach(roomDeviceDetailParam -> {
                    RoomDeviceStatus rds = new RoomDeviceStatus();
                    rds.setIsDelete(0L);
                    rds.setRdsState(roomDeviceDetailParam.getRdsState());

                    rds.setRdRoomDevice(roomDeviceDetailParam.getRdRoomDevice());
                    rds.setRdId(roomDeviceDetailParam.getRdId());
                    logger.info("《》《》《》《》《》《》《》《》《》《》《》《》《》《》id开始》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》");
                    rds.setRrId(bdNemes.getId());
                    logger.info("《》《》《》《》《》《》《》《》《》《》《》《》《》《》id结束》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》");
                    roomDeviceStatusService.saveEntity(rds);
                });
            }
        }
		logger.info("《》《》《》《》《》《》《》《》《》《》《》《》《》《》回去了》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》");
        return true;
    }
	/**
	 * 新建/编辑会议室
	 * @param roomResourceDto
	 * @return
	 */
	@Transactional(rollbackFor = BusinessException.class)
	public Boolean saveRoom(RoomResourceDto roomResourceDto) {

		if(null == roomResourceDto.getRrRoomPosition() || roomResourceDto.getRrRoomPosition().length() == 0){
			throw new BusinessException(ReturnType.TokenError,"需要创建一个会议室位置");
		}
		RoomResource roomResource = new RoomResource();
		//相同位置不能出现同名会议室
		roomResource.setRpId(roomResourceDto.getRpId());
		roomResource.setRrRoomPosition(roomResourceDto.getRrRoomPosition());
		roomResource.setRrRoomName(roomResourceDto.getRrRoomName());
		roomResource.setRrRoomState(Constants.RoomUserStatus.Able);

		List<RoomDeviceDetailParam> getDeviceDetailParamList =	roomResourceDto.getDeviceDetailParamList();
	     if (getDeviceDetailParamList != null){
			 for (RoomDeviceDetailParam  dev :getDeviceDetailParamList){
				 roomResource.setRdRoomDevice(dev.getRdRoomDevice());
				 roomResource.setRdDeviceId(dev.getRdId());
			 }
		 }

		RoomResource list = get(roomResource);
	     if(list!=null && "".equals(roomResourceDto.getRrId()) && roomResourceDto.getRrId()==null){
				throw new BusinessException(ReturnType.ParamIllegal," \""+roomResourceDto.getRrRoomName()+"\"已存在，请勿重复添加");
			}

		RoomResource bdNemes = roomResourceDao.getByName(roomResourceDto.getRrRoomName());


		if(roomResourceDto.getRrRoomName().length() > Constants.room.nameLenght){
			throw new BusinessException(ReturnType.ParamIllegal,"会议室名称不得超过20字");
		}
		if(roomResourceDto.getRrRoomDesc().length() > Constants.room.descLenght){
			throw new BusinessException(ReturnType.ParamIllegal,"会议室备注不得超过200字");
		}
		BeanUtils.copyProperties(roomResourceDto,roomResource);
		if(null == roomResource.getRrRoomPicMain() || roomResource.getRrRoomPicMain().isEmpty()){
			roomResource.setRrRoomPicMain(roomIcon);
		}




		//判断是否点击修改功能
		if(!"".equals(roomResourceDto.getRrId()) && roomResourceDto.getRrId()!=null) {
			roomResource.setId(roomResourceDto.getRrId());
			RoomResource bdNemeId = roomResourceDao.getByNameAndId(roomResourceDto.getRrRoomName(),roomResourceDto.getRrId());
			if(bdNemeId != null){
				//允许修改

				roomResource.setCreateTime(System.currentTimeMillis());
				roomResource.setCreateUser(UserServletContext.getUserInfo().getUserName());
				roomResource.setCreateUserId(UserServletContext.getUserInfo().getUserNo()+"");
				roomResource.setComId(UserServletContext.getUserInfo().getComCode());
				roomResource.setComCode(UserServletContext.getUserInfo().getComCode());
				roomResource.setModuCode(UserServletContext.getUserInfo().getModuCode());
				this.save(roomResource);

				//保存到设备状态表
				if(null != roomResourceDto.getDeviceDetailParamList() && !roomResourceDto.getDeviceDetailParamList().isEmpty()){

					RoomDeviceStatus roomDeviceStatus = new RoomDeviceStatus();
					roomDeviceStatus.setRrId(roomResourceDto.getRrId());
					roomDeviceStatus.setComCode(UserServletContext.getUserInfo().getComCode());
					List<RoomDeviceStatus> roomDeviceStatusList = roomDeviceStatusDao.findList(roomDeviceStatus);
//				roomDeviceStatusList.stream().forEach(roomDeviceStatus1 -> {
//					roomDeviceStatusDao.deleteById(roomDeviceStatus1.getId());
//				});


					if(roomDeviceStatusList!=null && roomDeviceStatusList.size()>0){
						roomDeviceStatusList.stream().forEach(roomDeviceStatus1 -> {
							roomDeviceStatusDao.deleteById(roomDeviceStatus1.getId());
						});
					}


					List<RoomDeviceDetailParam> roomDeviceDetailParamList = roomResourceDto.getDeviceDetailParamList();
					roomDeviceDetailParamList.forEach(roomDeviceDetailParam -> {
						RoomDeviceStatus rds = new RoomDeviceStatus();
						rds.setIsDelete(0L);
						rds.setRdsState(roomDeviceDetailParam.getRdsState());
						rds.setRrId(roomResourceDto.getRrId());
						rds.setRdRoomDevice(roomDeviceDetailParam.getRdRoomDevice());
						rds.setRdId(roomDeviceDetailParam.getRdId());
						roomDeviceStatusService.saveEntity(rds);
					});
				}

				return true;
			}
			RoomResource bdNeme = roomResourceDao.getByName(roomResourceDto.getRrRoomName());

			if( bdNeme!=null){
				//不允许
				throw new BusinessException(ReturnType.ParamIllegal," \""+roomResourceDto.getRrRoomName()+"\"已存在，请勿重复添加");

			}else {

				//允许修改
				roomResource.setCreateTime(System.currentTimeMillis());
				roomResource.setCreateUser(UserServletContext.getUserInfo().getUserName());
				roomResource.setCreateUserId(UserServletContext.getUserInfo().getUserNo()+"");
				roomResource.setComId(UserServletContext.getUserInfo().getComCode());
				roomResource.setComCode(UserServletContext.getUserInfo().getComCode());
				roomResource.setModuCode(UserServletContext.getUserInfo().getModuCode());
				this.save(roomResource);

				//保存到设备状态表
				if(null != roomResourceDto.getDeviceDetailParamList() && !roomResourceDto.getDeviceDetailParamList().isEmpty()){

					RoomDeviceStatus roomDeviceStatus = new RoomDeviceStatus();
					roomDeviceStatus.setRrId(roomResourceDto.getRrId());
					roomDeviceStatus.setComCode(UserServletContext.getUserInfo().getComCode());
					List<RoomDeviceStatus> roomDeviceStatusList = roomDeviceStatusDao.findList(roomDeviceStatus);
//				roomDeviceStatusList.stream().forEach(roomDeviceStatus1 -> {
//					roomDeviceStatusDao.deleteById(roomDeviceStatus1.getId());
//				});


					if(roomDeviceStatusList!=null && roomDeviceStatusList.size()>0){
						roomDeviceStatusList.stream().forEach(roomDeviceStatus1 -> {
							roomDeviceStatusDao.deleteById(roomDeviceStatus1.getId());
						});
					}

					List<RoomDeviceDetailParam> roomDeviceDetailParamList = roomResourceDto.getDeviceDetailParamList();
					roomDeviceDetailParamList.forEach(roomDeviceDetailParam -> {
						RoomDeviceStatus rds = new RoomDeviceStatus();
						rds.setIsDelete(0L);
						rds.setRdsState(roomDeviceDetailParam.getRdsState());
						rds.setRdRoomDevice(roomDeviceDetailParam.getRdRoomDevice());
						rds.setRdId(roomDeviceDetailParam.getRdId());
						rds.setRrId(roomResourceDto.getRrId());
						roomDeviceStatusService.saveEntity(rds);

					});
				}

				return true;


			}
		}

		//新增
		if(null != bdNemes ){
			throw new BusinessException(ReturnType.ParamIllegal," \""+roomResourceDto.getRrRoomName()+"\"已存在，请勿重复添加");
		}
		roomResource.setCreateTime(System.currentTimeMillis());
		roomResource.setCreateUser(UserServletContext.getUserInfo().getUserName());
		roomResource.setCreateUserId(UserServletContext.getUserInfo().getUserNo()+"");
		roomResource.setComId(UserServletContext.getUserInfo().getComCode());
		roomResource.setComCode(UserServletContext.getUserInfo().getComCode());
		roomResource.setModuCode(UserServletContext.getUserInfo().getModuCode());
		this.save(roomResource);

		RoomResource roomResources =roomResourceDao.getByName(roomResourceDto.getRrRoomName());


		//保存到设备状态表
		if(null != roomResourceDto.getDeviceDetailParamList() && !roomResourceDto.getDeviceDetailParamList().isEmpty()){
			List<RoomDeviceDetailParam> roomDeviceDetailParamList = roomResourceDto.getDeviceDetailParamList();
			roomDeviceDetailParamList.forEach(roomDeviceDetailParam -> {
				RoomDeviceStatus rds = new RoomDeviceStatus();
				rds.setIsDelete(0L);
				rds.setRdsState(roomDeviceDetailParam.getRdsState());

				rds.setRdRoomDevice(roomDeviceDetailParam.getRdRoomDevice());
				rds.setRdId(roomDeviceDetailParam.getRdId());
				rds.setRrId(roomResources.getId());
				rds.setRdRoomDevice(roomDeviceDetailParam.getRdRoomDevice());
				roomDeviceStatusService.saveEntity(rds);
			});
		}

		return true;
	}


	/**
	 * 获取物料列表
	 * @return
	 */
	public List<MaterielVo> getMaterielList(){
        List<MaterielVo> result = new ArrayList<>();
        //if (true) return result;
        
        String url = matListUrl;
        Integer loopCount = 0;
        Integer responseCount = 0;
        do {
			loopCount++;
            MultiValueMap<String, String> param = new LinkedMultiValueMap<>();
            //将请求参数放入map中
            param.add("pageSize", "10");
            param.add("pageNo", loopCount.toString());
            param.add("matName", "");
            param.add("matTypeCode", "");
            param.add("utf8", "✓");
            
            ResponseEntity<String> responseEntity = tokenService.sendRequest(url, UserServletContext.getUserInfo(), param);
            logger.info("=== 返回参数:" + loopCount + " ====>" + responseEntity.getBody());
            JSONObject jsonObject = JSON.parseObject(responseEntity.getBody());
            if (Constants.Http.Status_Code_Normal.equals(jsonObject.get(Constants.Http.String_Code).toString())) {
                List<MaterielDto> materielDtoList = JSON.parseObject(JSON.parseObject(
                        jsonObject.get(Constants.Http.String_Data) + "").get("list") + "", new TypeReference<List<MaterielDto>>() {
                });
                responseCount = Integer.parseInt(JSON.parseObject(jsonObject.get(Constants.Http.String_Data) + "").get("count") + "");
                for (MaterielDto materielDto : materielDtoList) {
                    MaterielVo materielVo = new MaterielVo();
                    materielVo.setMaCount(materielDto.getMaCount());
                    materielVo.setMaName(materielDto.getMatName());
                    materielVo.setMaTypeId(materielDto.getMatTypeCode());
                    materielVo.setMaTypeName(materielDto.getMatTypeName());
                    materielVo.setMaId(materielDto.getId());
                    materielVo.setComId(UserServletContext.getUserInfo().getCompanyId() + "");
                    result.add(materielVo);
                }
            } else {
                logger.error("== 返回的responseEntity ====>" + responseEntity);
                throw new BusinessException(ReturnType.ParamIllegal, "获取物料列表异常!");
            }
        } while (10 < getCount(responseCount,loopCount,10));

        return result;
	}

	/**
	 * 处理循环条数
	 * @param responseCount 总计条数
	 * @param loopCount 循环次数
	 * @param pageSize 每次循环获取的条数
	 * @return
	 */
	private Integer getCount(Integer responseCount,Integer loopCount,Integer pageSize){
		Integer result = new Integer(0);
		if(null != responseCount && null != loopCount){
			result = responseCount - (pageSize * loopCount);
		}
		return result;
	}

	/**
	 * 根据物料品类,获取物料列表
	 * @param materielInfoDto 对应的dto
	 * @return
	 */
	public MaterielInfoVo getMaterielInfoByType(MaterielInfoDto materielInfoDto){
		MaterielInfoVo result = new MaterielInfoVo();
		List<MaterielTypeInfo> materielTypeInfoList = new ArrayList<>();
		List<MaterielVo> materielVoList = new ArrayList<>();

		//处理登陆人信息
		LoginUser loginUser = UserServletContext.getUserInfo();

		if(null == materielInfoDto || (StringUtils.isEmpty(materielInfoDto.getMatName()) && StringUtils.isEmpty(materielInfoDto.getMatTypeCode()))){
			//则查询类型列表
			Integer responseCount = 0;
			Integer loopCount = 0;
			do{
				loopCount++;
				MultiValueMap<String, String> param = new LinkedMultiValueMap<>();
				param.add("pageSize", "10");
				param.add("pageNo", loopCount.toString());
				param.add("utf8", "✓");
				ResponseEntity<String> responseEntity = tokenService.sendRequest(matTypeUrl,loginUser, param);
				logger.info("=== 物料类型列表:" + loopCount + " ====>" + responseEntity.getBody());
				JSONObject jsonObject = JSON.parseObject(responseEntity.getBody());
				if (Constants.Http.Status_Code_Normal.equals(jsonObject.get(Constants.Http.String_Code).toString())) {
					materielTypeInfoList = JSON.parseObject(JSON.parseObject(
							jsonObject.get(Constants.Http.String_Data) + "").get("list") + "", new TypeReference<List<MaterielTypeInfo>>() {
					});
					responseCount = Integer.parseInt(JSON.parseObject(jsonObject.get(Constants.Http.String_Data) + "").get("count") + "");
				} else {
					logger.error("== 物料类型列表返回的responseEntity ====>" + responseEntity);
					throw new BusinessException(ReturnType.ParamIllegal, "获取物料类型列表异常!");
				}
			}while (10 < getCount(responseCount,loopCount,10));
		}

		if(null != materielInfoDto && (!StringUtils.isEmpty(materielInfoDto.getMatName()) || !StringUtils.isEmpty(materielInfoDto.getMatTypeCode()))){
			//获取物料列表
			Integer responseCount;
			Integer loopCount = 0;
			do {
				loopCount++;
				MultiValueMap<String, String> param = new LinkedMultiValueMap<>();
				//将请求参数放入map中
				param.add("pageSize","10");
				param.add("pageNo",loopCount.toString());
				param.add("matName",StringUtils.isEmpty(materielInfoDto.getMatName())?null:materielInfoDto.getMatName());
				param.add("matTypeCode",StringUtils.isEmpty(materielInfoDto.getMatTypeCode())?null:materielInfoDto.getMatTypeCode());
				param.add("utf8", "✓");
				ResponseEntity<String> responseEntity = tokenService.sendRequest(matListUrl, loginUser, param);
				logger.info("=== 返回参数:" + loopCount + " ====>" + responseEntity.getBody());
				JSONObject jsonObject = JSON.parseObject(responseEntity.getBody());
				if (Constants.Http.Status_Code_Normal.equals(jsonObject.get(Constants.Http.String_Code).toString())) {
					List<MaterielDto> materielDtoList = JSON.parseObject(JSON.parseObject(
							jsonObject.get(Constants.Http.String_Data) + "").get("list") + "", new TypeReference<List<MaterielDto>>() {
					});
					responseCount = Integer.parseInt(JSON.parseObject(jsonObject.get(Constants.Http.String_Data) + "").get("count") + "");
					for (MaterielDto materielDto : materielDtoList) {
						MaterielVo materielVo = new MaterielVo();
						materielVo.setMaCount(materielDto.getMaCount());
						materielVo.setMaName(materielDto.getMatName());
						materielVo.setComId(UserServletContext.getUserInfo().getCompanyId() + "");
						materielVo.setMaTypeId(materielDto.getMatTypeCode());
						materielVo.setMaTypeName(materielDto.getMatTypeName());
						materielVo.setMaId(materielDto.getId());
						materielVoList.add(materielVo);
					}
				} else {
					logger.error("== return 获取物料列表: ====>" + responseEntity);
					throw new BusinessException(ReturnType.ParamIllegal, "获取物料列表异常!");
				}
			} while (10 < getCount(responseCount,loopCount,10));
		}
		result.setMaterielTypeInfoList(materielTypeInfoList);
		result.setMaterielVoList(materielVoList);
		return result;
	}

	/**
	 * 获取会议室位置
	 * @param comId
	 * @return
	 */
	public List<RoomPositionVo> getRoomPositionList(String comId){
		List<RoomPositionVo> result = new ArrayList<>();
		List<RoomPosition> roomPositionList = roomPositionDao.findRoomPositionList(comId);
		if(null != roomPositionList && !roomPositionList.isEmpty()){
			for(RoomPosition roomPosition : roomPositionList){
				RoomPositionVo roomPositionVo = new RoomPositionVo();
				BeanUtils.copyProperties(roomPosition,roomPositionVo);
				roomPositionVo.setRpId(roomPosition.getId());
				result.add(roomPositionVo);
			}
		}
		return result;
	}

	/**
	 * 获取会议室详情
	 * @param id
	 * @return
	 */
	public RoomResourceVo getRoomDetailById(String id){
		RoomResourceVo result = new RoomResourceVo();
		RoomResource roomResource = roomResourceDao.getById(id);
		if(null != roomResource){
			BeanUtils.copyProperties(roomResource,result);
			result.setRrId(roomResource.getId());
			result.setRrRoomType(roomResource.getRrRoomType());
			result.setRtId(roomResource.getRtId());
			result.setRrRoomDesc(roomResource.getRrRoomDesc());
		}
		result.setDeviceDetailVoList(this.getRoomDeviceDetailList(id));
		return result;
	}


	public Boolean updateRoomDeviceStatus(String rdsId, Long status){
		RoomDeviceStatus roomDeviceStatus = new RoomDeviceStatus();
		roomDeviceStatus.setId(rdsId);
		roomDeviceStatus.setRdsState(status);
		roomDeviceStatus.setUpdatedBy(UserServletContext.getUserInfo().getUserNo()+"");
		roomDeviceStatus.setUpdatedDt(new Date());
		return roomDeviceStatusDao.updateById(roomDeviceStatus) > 0;
	}

	//根据id获取会议详情
	public RoomResource getRoomListById(String id) {

	 return roomResourceDao.getById(id);
	}


}