package com.sgai.property.meeting.dao;
import com.sgai.common.persistence.CrudDao;
import com.sgai.property.commonService.dao.MoreDataSourceDao;
import com.sgai.property.meeting.entity.SummaryPic;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ISummaryPicDao extends MoreDataSourceDao<SummaryPic> {
 
	List<SummaryPic> getByMsId(@Param("msId") String msId);
}