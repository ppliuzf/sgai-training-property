package com.sgai.property.mdm.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.sgai.common.persistence.CrudDao;
import com.sgai.property.mdm.entity.MdmDeviceKpiInfo;

/**
 * 标准值DAO接口
 * @author admin
 * @version 2018-07-27
 */
@Mapper
public interface MdmDeviceKpiInfoDao extends CrudDao<MdmDeviceKpiInfo> {
	
	List<String> findPaths(Map<String, String> params);
}