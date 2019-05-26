package com.sgai.property.meeting.dao;
import com.sgai.common.persistence.CrudDao;
import com.sgai.property.commonService.dao.MoreDataSourceDao;
import com.sgai.property.meeting.entity.InviteDept;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IInviteDeptDao extends MoreDataSourceDao<InviteDept> {
 
	List<InviteDept> getByMiId(@Param("miId") String miId);

	int deleteByMiId(@Param("miId") String miId);
}