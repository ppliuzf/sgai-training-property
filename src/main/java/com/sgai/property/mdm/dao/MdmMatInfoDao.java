package com.sgai.property.mdm.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.sgai.common.persistence.CrudDao;
import com.sgai.property.mdm.entity.MdmMatClass;
import com.sgai.property.mdm.entity.MdmMatInfo;

/**
 * 物料信息表DAO接口
 * @author liushang
 * @version 2017-11-24
 */
@Mapper
public interface MdmMatInfoDao extends CrudDao<MdmMatInfo> {
	List<Map<String, String>> getMatTypeList(MdmMatClass mdmMatClass);
	List<Map<String, String>> getSpecList();
	List<Map<String, String>> getUnitList();
	List<Map<String, String>> getMatUseList();
	List<MdmMatInfo> findRepeatList(MdmMatInfo mdmMatInfo);
}