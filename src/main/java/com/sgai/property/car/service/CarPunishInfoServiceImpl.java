package com.sgai.property.car.service;

import com.sgai.common.persistence.Page;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.car.constants.Constants;
import com.sgai.property.car.dao.ICarPunishInfoDao;
import com.sgai.property.car.entity.CarPunishInfo;
import com.sgai.property.car.vo.CarPunishInfoVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Service
public class CarPunishInfoServiceImpl extends MoreDataSourceCrudServiceImpl<ICarPunishInfoDao,CarPunishInfo>{
	@Autowired
	private ICarPunishInfoDao carPunishInfoDao;


	public Page<CarPunishInfoVo> getPunishPageInfo(String carId, int pageNum, int pageSize) {
		String comCode = UserServletContext.getUserInfo().getComCode();
		String moduCode = UserServletContext.getUserInfo().getModuCode();
		Page<CarPunishInfoVo> pageRuselt = new Page<>();
		List<CarPunishInfoVo> cpVoList = new LinkedList<>();
		Page<CarPunishInfo> page = new Page<>(pageNum, pageSize);

		CarPunishInfo carPunishInfo = new CarPunishInfo();
		page.setOrderBy("updated_dt");
		carPunishInfo.setPage(page);
		carPunishInfo.setIsDelete(Constants.NO);
		carPunishInfo.setCarId(carId);
		carPunishInfo.setModuCode(moduCode);
		carPunishInfo.setComCode(comCode);
		List<CarPunishInfo> cpList = carPunishInfoDao.findList(carPunishInfo);
		if (null != cpList && !cpList.isEmpty()) {
			cpList.forEach(cp -> {
				CarPunishInfoVo vo = new CarPunishInfoVo();
				BeanUtils.copyProperties(cp, vo);
				cpVoList.add(vo);
			});
		}
		BeanUtils.copyProperties(page, pageRuselt);
		pageRuselt.setList(cpVoList);
		return pageRuselt;
	}

	@Transactional(rollbackFor = RuntimeException.class)
	public Boolean addPunish(CarPunishInfoVo carPunishInfoVo){
		String comCode = UserServletContext.getUserInfo().getComCode();
		String moduCode = UserServletContext.getUserInfo().getModuCode();
		CarPunishInfo cp = new CarPunishInfo();
		BeanUtils.copyProperties(carPunishInfoVo,cp);
		cp.setIsDelete(Constants.NO);
		cp.setComCode(comCode);
		cp.setModuCode(moduCode);
		return this.saveEntity(cp);
	}
}