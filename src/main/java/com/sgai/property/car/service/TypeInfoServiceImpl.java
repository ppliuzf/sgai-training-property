package com.sgai.property.car.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sgai.property.car.dao.ITypeInfoDao;
import com.sgai.property.car.entity.TypeInfo;

@Service
public class TypeInfoServiceImpl extends MoreDataSourceCrudServiceImpl<ITypeInfoDao,TypeInfo>{
	@Autowired
	private ITypeInfoDao typeInfoDao;

}