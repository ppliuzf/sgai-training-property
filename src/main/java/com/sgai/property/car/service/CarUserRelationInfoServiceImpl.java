package com.sgai.property.car.service;


import com.sgai.common.persistence.Page;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.car.constants.Constants;
import com.sgai.property.car.dao.ICarUserRelationInfoDao;
import com.sgai.property.car.entity.CarUserRelationInfo;
import com.sgai.property.car.vo.CarRelationInfoParam;
import com.sgai.property.car.vo.CarUserRelationInfoParam;
import com.sgai.property.car.vo.CarUserRelationInfoParam2;
import com.sgai.property.car.vo.CarUserRelationInfoVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class CarUserRelationInfoServiceImpl extends MoreDataSourceCrudServiceImpl<ICarUserRelationInfoDao,CarUserRelationInfo>{
	@Autowired
	private ICarUserRelationInfoDao carUserRelationInfoDao;

	/**
	 *修改CarUserRelationInfo
	 * @param entity
	 * @return
	 */
	public boolean updateCarUserRelationInfo(CarUserRelationInfo carUserRelationInfoNew,CarUserRelationInfo entity) {
		if(entity.getRiUseTimes() !=null && entity.getRiUseStart() !=null){
			entity.setRiUseEnd(entity.getRiUseStart()+tranferSeconds(entity.getRiUseTimes()));
		}else if(entity.getRiUseTimes() !=null && entity.getRiUseStart() ==null){
			entity.setRiUseEnd(carUserRelationInfoNew.getRiUseStart()+tranferSeconds(entity.getRiUseTimes()));
		}
		entity.setUpdatedDt(new Date());
		int i= dao.updateById(entity);
        return i > 0;
	}
	/**
	 * 查询车辆和预约时间关系
	 * @return
	 */
	public Boolean judge2(CarUserRelationInfoParam carUserRelationInfoParam2){
		Boolean falg=false;
		CarUserRelationInfo carUserRelationInfo=new CarUserRelationInfo();
		carUserRelationInfo.setRiCarId(carUserRelationInfoParam2.getRiCarId());
		carUserRelationInfo.setRiUseStart(carUserRelationInfoParam2.getRiUseStart());
		if(StringUtils.isNotEmpty(carUserRelationInfoParam2.getRiUseTimes())){
			carUserRelationInfo.setRiUseEnd(carUserRelationInfoParam2.getRiUseStart()+tranferSeconds(carUserRelationInfoParam2.getRiUseTimes()));
		}
		List<CarUserRelationInfo> list = carUserRelationInfoDao.findRelation(carUserRelationInfo);
		if(list !=null){
			falg =  true;
		}
		return falg;
	}
	/**
	 * 查询车辆和预约时间关系
	 * @param id
	 * @return
	 */
	public Boolean judge1(String id,CarUserRelationInfoParam2 carUserRelationInfoParam2){
		Boolean falg=false;
		CarUserRelationInfo carUserRelationInfo = carUserRelationInfoDao.getById(id);
		if(carUserRelationInfoParam2.getRiUseStart()==null){
			carUserRelationInfoParam2.setRiUseStart(carUserRelationInfo.getRiUseStart());
		}
		CarUserRelationInfo carUserRelationInfoNew = new CarUserRelationInfo();
		BeanUtils.copyProperties(carUserRelationInfoParam2,carUserRelationInfoNew);
		if(StringUtils.isNotEmpty(carUserRelationInfoNew.getRiUseTimes())){
			carUserRelationInfoNew.setRiUseEnd(carUserRelationInfoNew.getRiUseStart()+tranferSeconds(carUserRelationInfoNew.getRiUseTimes()));
		}else{
			if(StringUtils.isNotEmpty(carUserRelationInfo.getRiUseTimes())){
				carUserRelationInfoNew.setRiUseEnd(carUserRelationInfo.getRiUseEnd());
			}
		}
		if(carUserRelationInfoParam2.getRiCarId() == null){
			carUserRelationInfoNew.setRiCarId(carUserRelationInfo.getRiCarId());
		}

		List<CarUserRelationInfo> list = carUserRelationInfoDao.findRelation(carUserRelationInfoNew);


		if(list !=null && list.size()>0){
			Iterator<CarUserRelationInfo> it = list.iterator();
			while(it.hasNext()){
				CarUserRelationInfo carUserRelationInfo1 = it.next();
				if(!carUserRelationInfo.getRiCarId().equals(carUserRelationInfoParam2.getRiCarId())){
					if(carUserRelationInfo1.getRiCarId().equals(carUserRelationInfoParam2.getRiCarId())){
						falg =  true;
					}
				}
				if(carUserRelationInfoParam2.getRiCarId().equals(carUserRelationInfo1.getRiCarId())){
					it.remove();
				}
			}
		}
		return falg;
	}


	public List<CarUserRelationInfo> findRelation(CarUserRelationInfo entity) {

		return carUserRelationInfoDao.findRelation(entity);
	}
	/**
	 * 车联管理下的预约记录
	 * @param entity
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public Page<CarUserRelationInfo> findListNew(CarUserRelationInfo entity, int pageNumber, int pageSize) {
		Page<CarUserRelationInfo> page=new Page<>(pageNumber,pageSize);
		page.setOrderBy("updated_dt");
		entity.setPage(page);
		List<CarUserRelationInfo> list =carUserRelationInfoDao.findListNew(entity);
		List<String> idsList=new ArrayList<>();
		for (CarUserRelationInfo carUserRelationInfo:list) {
			if(Constants.AUDIT_STATUS_5.equals(carUserRelationInfo.getRiAuditStatus()) && System.currentTimeMillis() >= carUserRelationInfo.getRiUseEnd()){
				carUserRelationInfo.setRiAuditStatus(Constants.AUDIT_STATUS_4);
				idsList.add(carUserRelationInfo.getId());
			}
		}
		if(idsList.size()>0){
			CarUserRelationInfo carUserRelationInfo =new CarUserRelationInfo();
			carUserRelationInfo.setRiAuditStatus(Constants.AUDIT_STATUS_4);
			String[] arr = idsList.toArray(new String[idsList.size()]);
			carUserRelationInfoDao.updateByIds(carUserRelationInfo,arr);
		}
		page.setList(list);
		return page;
	}
	/**
	 * 分页查询登录管理员(申请人)下的审核车辆信息
	 * @param entity
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public Page<CarUserRelationInfoVo> findListPageByAuditStatus(CarUserRelationInfoVo entity, int pageNumber, int pageSize) {
		Page<CarUserRelationInfoVo> page=new Page<>(pageNumber,pageSize);
		page.setOrderBy("updated_dt");
		entity.setPage(page);
		List<CarUserRelationInfoVo> list= carUserRelationInfoDao.findListPageByAuditStatus(entity);
		for (int i =0;i<list.size();i++) {
			list.get(i).setCreatedDtLong(list.get(i).getCreatedDt().getTime());
		}
		List<CarUserRelationInfoVo> curList = carUserRelationInfoDao.findListPageByAuditStatus(entity);
		if(null != curList  && !curList.isEmpty()){
			curList.stream().filter(vo -> Constants.AUDIT_STATUS_5.equals(vo.getRiAuditStatus())
					&& System.currentTimeMillis() >= vo.getRiUseEnd()).forEach(vo ->vo.setRiAuditStatus(Constants.AUDIT_STATUS_4));
		}
		page.setList(curList);
		return page;
	}

	/**
	 * 分页查询登录管理员(申请人)下的审核车辆信息 app端
	 * @param entity
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public Page<CarUserRelationInfoVo> findListPageByAuditStatusForApp(CarUserRelationInfoVo entity, int pageNumber, int pageSize) {
		Page<CarUserRelationInfoVo> page=new Page<>(pageNumber,pageSize);
		page.setOrderBy("updated_dt");
		entity.setPage(page);
		page.setList(carUserRelationInfoDao.findListPageByAuditStatusForApp(entity));
		return page;
	}
	/**
	 * 分页查询登录管理员(申请人)下的车辆预约信息
	 * @param entity
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public Page<CarUserRelationInfoVo> findListInnerCarInfo(CarUserRelationInfoVo entity,
															CarRelationInfoParam carRelationInfoParam, int pageNumber, int pageSize) {
		String comCode = UserServletContext.getUserInfo().getComCode();
		String moduCode = UserServletContext.getUserInfo().getModuCode();
		Page<CarUserRelationInfoVo> page=new Page<>(pageNumber,pageSize);
		page.setOrderBy("updated_dt");
		entity.setPage(page);
		BeanUtils.copyProperties(carRelationInfoParam,entity);
		if(null != carRelationInfoParam && null != carRelationInfoParam.getRiUseStart()){
			SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATEFORMAT_YYYYMMDD);
			String timeStr = sdf.format(new Date(carRelationInfoParam.getRiUseStart()));
			SimpleDateFormat sdf1 = new SimpleDateFormat(Constants.DATEFORMAT_YYYYMMDDHHMMSS);
			try {//yyyy-MM-dd HH:mm:ss
				entity.setRiUseStart(sdf1.parse((timeStr+" 00:00:00")).getTime());
				entity.setRiUseEnd(sdf1.parse((timeStr+" 23:59:59")).getTime());
			}catch (ParseException e){
				e.printStackTrace();
			}
		}
		entity.setComCode(comCode);
		entity.setModuCode(moduCode);
		List<CarUserRelationInfoVo> list = carUserRelationInfoDao.findListInnerCarInfo(entity);
		for (int i=0;i<list.size();i++) {
			list.get(i).setCreatedDtLong(list.get(i).getCreatedDt().getTime());
		}
		List<String> idsList=new ArrayList<>();
		for (CarUserRelationInfoVo carUserRelationInfoVo:list) {
			if(Constants.AUDIT_STATUS_5.equals(carUserRelationInfoVo.getRiAuditStatus()) && System.currentTimeMillis() >= carUserRelationInfoVo.getRiUseEnd()){
				carUserRelationInfoVo.setRiAuditStatus(Constants.AUDIT_STATUS_4);
				idsList.add(carUserRelationInfoVo.getId());
			}
			carUserRelationInfoVo.setEndJourney(carUserRelationInfoVo.getEndJourney()==null?0L:carUserRelationInfoVo.getEndJourney());
		}
		if(idsList.size()>0){
			CarUserRelationInfo carUserRelationInfo =new CarUserRelationInfo();
			carUserRelationInfo.setRiAuditStatus(Constants.AUDIT_STATUS_4);
			String[] arr = idsList.toArray(new String[idsList.size()]);
			carUserRelationInfoDao.updateByIds(carUserRelationInfo,arr);
		}

		page.setList(list);
		return page;
	}
	/**
	 * 分页查询登录管理员(申请人)下的车辆预约信息
	 * @param entity
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public Page<CarUserRelationInfoVo> findListInnerCarInfoForAPP(CarUserRelationInfoVo entity, int pageNumber, int pageSize) {
		Page<CarUserRelationInfoVo> page=new Page<>(pageNumber,pageSize);
		page.setOrderBy("updated_dt");
		if(entity.getRiIsDelete() == null){
			entity.setRiIsDelete(Constants.NO);
		}
		entity.setPage(page);
		List<CarUserRelationInfoVo> list = carUserRelationInfoDao.findListInnerCarInfoForAPP(entity);
		for (CarUserRelationInfoVo carUserRelationInfoVo:list) {
			carUserRelationInfoVo.setCreatedDtLong(carUserRelationInfoVo.getCreatedDt().getTime());
		}
		page.setList(list);
		return page;
	}

	/**
	 * 预约者的预约详情
	 * @param id
	 * @return
	 */
	public CarUserRelationInfoVo detailCarUserRelationInfo(String id) {
		return carUserRelationInfoDao.detailCarUserRelationInfo(id);
	}

	/**
	 * 添加 车辆-用户 预约信息
	 * @param carUserRelationInfo
	 * @return
	 */
	public void addCarUserRelationInfo(CarUserRelationInfo carUserRelationInfo) {
		/*if(!validateParams(carUserRelationInfo)){
			return;
		}*/
		if(StringUtils.isNotEmpty(carUserRelationInfo.getRiUseTimes())){
			carUserRelationInfo.setRiUseEnd(carUserRelationInfo.getRiUseStart()+tranferSeconds(carUserRelationInfo.getRiUseTimes()));//小时转换成
		}
		carUserRelationInfo.setRiIsDelete(Constants.NO);
		super.save(carUserRelationInfo);
	}
	/**
	 * 添加 车辆-用户 预约信息
	 * @param carUserRelationInfo
	 * @return
	 */
	public void addCarUserRelationInfoForApp(CarUserRelationInfo carUserRelationInfo) {
		if(StringUtils.isNotEmpty(carUserRelationInfo.getRiUseTimes())){
			carUserRelationInfo.setRiUseEnd(carUserRelationInfo.getRiUseStart()+tranferSeconds(carUserRelationInfo.getRiUseTimes()));//小时转换成
		}
		//Long startTime = DateUtils.parseDate(carUserRelationInfo.getRiUseStart()).getTime();
		carUserRelationInfo.setRiIsDelete(Constants.NO);
		super.save(carUserRelationInfo);
	}
	/**
	 * 我已提交状态的预约申请个数
	 * @param entity
	 * @return
	 */
	public Integer getApplyCount(CarUserRelationInfo entity){
		return carUserRelationInfoDao.getApplyCount(entity);
	}

	/**
	 * 需要我审核的预约申请个数
	 * @param entity
	 * @return
	 */
	public Integer getAuditCount(CarUserRelationInfo entity){
		return carUserRelationInfoDao.getAuditCount(entity);
	}
	//============校验参数=======================================
	/*public Boolean validateParams(CarUserRelationInfo carUserRelationInfo){
		if(StringUtils.isEmpty(carUserRelationInfo.getRiUserName()) || StringUtils.isEmpty(carUserRelationInfo.getRiUserPhone()) || StringUtils.isEmpty(carUserRelationInfo.getRiUseTimes()) || StringUtils.isEmpty(carUserRelationInfo.getRiUsePurpose())){
			return false;
		}
		return true;
	}*/
	public Long tranferSeconds(String time){
		return (Long.parseLong(time)*60*60*1000);
	}
}