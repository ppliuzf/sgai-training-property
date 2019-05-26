package com.sgai.property.car.service;


import com.sgai.property.car.dao.ICarRepairRecodeDao;
import com.sgai.property.car.dao.ICarUserRelationInfoDao;
import com.sgai.property.car.entity.CarRepairRecode;
import com.sgai.property.car.entity.CarUserRelationInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarRepairRecodeServiceImpl extends MoreDataSourceCrudServiceImpl<ICarRepairRecodeDao,CarRepairRecode>{
	@Autowired
	private ICarRepairRecodeDao carRepairRecodeDao;


	//============校验参数=======================================
	public Boolean validateParams(CarUserRelationInfo carUserRelationInfo){
        return !StringUtils.isEmpty(carUserRelationInfo.getRiUserName()) && !StringUtils.isEmpty(carUserRelationInfo.getRiUserPhone()) && !StringUtils.isEmpty(carUserRelationInfo.getRiUseTimes()) && !StringUtils.isEmpty(carUserRelationInfo.getRiUsePurpose());
    }
}