package com.sgai.property.budget.dao;
import com.sgai.property.budget.entity.Record;
import com.sgai.property.commonService.dao.MoreDataSourceDao;
import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface IRecordDao extends MoreDataSourceDao<Record> {
 
}