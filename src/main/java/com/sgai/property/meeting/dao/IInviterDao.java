package com.sgai.property.meeting.dao;
import com.sgai.common.persistence.CrudDao;
import com.sgai.property.commonService.dao.MoreDataSourceDao;
import com.sgai.property.meeting.entity.Inviter;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IInviterDao extends MoreDataSourceDao<Inviter> {
 
	List<Inviter> getByMiId(@Param("miId") String miId);
	Long getCountByMiId(@Param("miId") String miId);

	int deleteByMiId(@Param("miId") String miId);
	
	int updateByMiIdAndInviterEiId(Inviter inviter);
}