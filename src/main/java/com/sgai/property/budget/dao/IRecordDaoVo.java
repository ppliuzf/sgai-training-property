package com.sgai.property.budget.dao;
import java.util.List;

import com.sgai.property.commonService.dao.MoreDataSourceDao;
import org.apache.ibatis.annotations.Mapper;

import com.sgai.property.budget.entity.Record;
@Mapper
public interface IRecordDaoVo extends MoreDataSourceDao<Record> {

	List<Record> getRecordBySearch(Record record);
}