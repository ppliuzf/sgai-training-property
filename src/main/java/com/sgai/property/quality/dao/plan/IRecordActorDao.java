package com.sgai.property.quality.dao.plan;

import com.sgai.property.commonService.dao.MoreDataSourceDao;
import com.sgai.property.quality.entity.plan.RecordActor;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IRecordActorDao extends MoreDataSourceDao<RecordActor> {

    List<RecordActor> findListByRecordId(RecordActor entity);
 
}