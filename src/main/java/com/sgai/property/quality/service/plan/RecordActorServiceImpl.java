package com.sgai.property.quality.service.plan;


import com.sgai.property.quality.dao.plan.IRecordActorDao;
import com.sgai.property.quality.entity.plan.RecordActor;
import com.sgai.property.quality.service.MoreDataSourceCrudServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecordActorServiceImpl extends MoreDataSourceCrudServiceImpl<IRecordActorDao,RecordActor> {
	@Autowired
	private IRecordActorDao recordActorDao;

}