package com.sgai.property.quality.dao.plan;

import com.sgai.property.commonService.dao.MoreDataSourceDao;
import com.sgai.property.quality.entity.plan.Record;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IRecordPCDao extends MoreDataSourceDao<Record> {

	List<Record> getListByParam(Record record);
 
}