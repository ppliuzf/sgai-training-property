package com.sgai.property.commonService.dao;

import com.sgai.property.commonService.entity.EmpInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IEmpInfoCompareDao {

	void insertEmpInfoBatch(List<EmpInfo> empInfoList);
	
	List<EmpInfo> getAddEmpInfo();
	
	List<EmpInfo> getDeleteEmpInfo();
	
	List<EmpInfo> getUpdateEmpInfo();
	
	void deleteAll();
	
	List<String> getAllIds();
}