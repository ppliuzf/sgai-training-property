package com.sgai.property.meeting.dao;
import com.sgai.common.persistence.CrudDao;
import com.sgai.property.commonService.dao.MoreDataSourceDao;
import com.sgai.property.meeting.entity.Summary;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ISummaryDao extends MoreDataSourceDao<Summary> {
 
	List<Summary> getByMiId(@Param("miId") String miId);

	List<Summary> getByMiIdAndEiId(@Param("miId") String miId, @Param("eiId") String eiId);
}