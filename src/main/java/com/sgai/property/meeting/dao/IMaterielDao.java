package com.sgai.property.meeting.dao;
import com.sgai.property.commonService.dao.MoreDataSourceDao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sgai.property.meeting.entity.Materiel;
@Mapper
public interface IMaterielDao extends MoreDataSourceDao<Materiel> {
 
	int deleteByMiId(@Param("miId") String miId);
}