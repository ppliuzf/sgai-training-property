package com.sgai.property.commonService.service;
import com.sgai.common.persistence.Page;
import com.sgai.property.commonService.dao.IEmpInfoDao;
import com.sgai.property.commonService.entity.EmpInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CommonEmpInfoServiceImpl extends MoreDataSourceCrudServiceImpl<IEmpInfoDao,EmpInfo>{
	@Autowired
	private IEmpInfoDao empInfoDao;

	public Page<EmpInfo> findListPage(EmpInfo entity, int pageNumber, int pageSize) {
		return super.findPage(new Page<EmpInfo>(pageNumber,pageSize),entity);
	}

	public Page<EmpInfo> searchEmpInfoList(EmpInfo empInfo, int pageNumber, int pageSize){
		Page<EmpInfo> page=new Page<EmpInfo>(pageNumber,pageSize);
		page.setList(empInfoDao.searchEmpInfoList(empInfo));
		return page;
	}




}