package com.sgai.property.quality.dao;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.sgai.property.commonService.dao.MoreDataSourceDao;
import com.sgai.property.quality.entity.QtTaskResult;
@Mapper
public interface IQtTaskResultDao extends MoreDataSourceDao<QtTaskResult>{
	List<QtTaskResult> findListByRecordId_tpId_dateTime(Map map);

	QtTaskResult getDisEntity(QtTaskResult qtTaskResult);
	
}