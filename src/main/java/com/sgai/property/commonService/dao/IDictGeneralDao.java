package com.sgai.property.commonService.dao;

import com.sgai.property.commonService.entity.DictGeneral;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IDictGeneralDao extends MoreDataSourceDao<DictGeneral> {
 
	List<DictGeneral> getByDgCode(@Param("dgCode") String dgCode);
	
	List<DictGeneral> getDictGeneralsByType(@Param("dgCode") String dgCode, @Param("type") Integer type);
}