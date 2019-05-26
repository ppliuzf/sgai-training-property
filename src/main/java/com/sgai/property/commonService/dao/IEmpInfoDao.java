package com.sgai.property.commonService.dao;

import com.sgai.property.commonService.entity.EmpInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IEmpInfoDao extends MoreDataSourceDao<EmpInfo> {

	void insertEmpInfoBatch(List<EmpInfo> empInfoList);

	void updateEmpInfo(EmpInfo empInfo);

	/**
	 * 根据名称搜索用户信息
	 * @return
	 */
	List<EmpInfo> searchEmpInfoList(EmpInfo empInfo);

	EmpInfo getByCode(String code);
}
