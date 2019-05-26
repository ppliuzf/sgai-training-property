package com.sgai.property.meeting.dao;
import com.sgai.common.persistence.CrudDao;
import com.sgai.property.commonService.dao.MoreDataSourceDao;
import com.sgai.property.meeting.entity.Pic;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IPicDao extends MoreDataSourceDao<Pic> {
 
	
	List<Pic> getByMiId(@Param("miId") String miId);

	int deleteByMiId(@Param("miId") String miId);
}