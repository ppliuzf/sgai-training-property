package com.sgai.property.car.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sgai.property.car.dao.IGearBoxTypeDao;
import com.sgai.property.car.entity.GearBoxType;

@Service
public class GearBoxTypeServiceImpl extends MoreDataSourceCrudServiceImpl<IGearBoxTypeDao,GearBoxType>{
	@Autowired
	private IGearBoxTypeDao gearBoxTypeDao;

}