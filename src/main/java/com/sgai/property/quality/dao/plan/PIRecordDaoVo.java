package com.sgai.property.quality.dao.plan;

import com.sgai.property.commonService.dao.MoreDataSourceDao;
import com.sgai.property.quality.entity.plan.Record;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface PIRecordDaoVo extends MoreDataSourceDao<Record> {

	List<Record> taskDay(Map<String, Object> map);
}