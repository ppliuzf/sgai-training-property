package com.sgai.property.car.service;

import com.sgai.common.persistence.Page;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.car.constants.Constants;
import com.sgai.property.car.dao.ICarMaintainInfoDao;
import com.sgai.property.car.entity.CarMaintainInfo;
import com.sgai.property.car.vo.CarMaintainInfoVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Service
public class CarMaintainInfoServiceImpl extends MoreDataSourceCrudServiceImpl<ICarMaintainInfoDao,CarMaintainInfo>{
	@Autowired
	private ICarMaintainInfoDao carMaintainInfoDao;

	public Page<CarMaintainInfoVo> getMaintainDetail(String carId, int pageNum, int pageSize){
		String moduCode = UserServletContext.getUserInfo().getModuCode();
		String comCode = UserServletContext.getUserInfo().getComCode();
		Page<CarMaintainInfoVo> pageRuselt = new Page<>();
		List<CarMaintainInfoVo> cmVoList = new LinkedList<>();
		Page<CarMaintainInfo> page=new Page<>(pageNum,pageSize);

		CarMaintainInfo carMaintainInfo = new CarMaintainInfo();
		page.setOrderBy("updated_dt");
		carMaintainInfo.setPage(page);
		carMaintainInfo.setIsDelete(Constants.NO);
		carMaintainInfo.setCarId(carId);
		carMaintainInfo.setComCode(comCode);
		carMaintainInfo.setModuCode(moduCode);
		List<CarMaintainInfo> cmList = carMaintainInfoDao.findList(carMaintainInfo);
		if(null != cmList && !cmList.isEmpty()){
			cmList.forEach(cm -> {
				CarMaintainInfoVo vo = new CarMaintainInfoVo();
				BeanUtils.copyProperties(cm,vo);
				cmVoList.add(vo);
			});
		}
		BeanUtils.copyProperties(page,pageRuselt);
		pageRuselt.setList(cmVoList);
		return pageRuselt;
	}

	@Transactional(rollbackFor = RuntimeException.class)
	public Boolean addMaintain(CarMaintainInfoVo carMaintainInfoVo){
		String moduCode = UserServletContext.getUserInfo().getModuCode();
		String comCode = UserServletContext.getUserInfo().getComCode();
		CarMaintainInfo carMaintainInfo = new CarMaintainInfo();
		BeanUtils.copyProperties(carMaintainInfoVo,carMaintainInfo);
		carMaintainInfo.setIsDelete(Constants.NO);
		carMaintainInfo.setModuCode(moduCode);
		carMaintainInfo.setComCode(comCode);
		return this.saveEntity(carMaintainInfo);
	}
}