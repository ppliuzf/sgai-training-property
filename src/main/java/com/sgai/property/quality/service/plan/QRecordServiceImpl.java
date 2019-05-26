package com.sgai.property.quality.service.plan;

import com.sgai.property.quality.dao.plan.PIRecordDao;
import com.sgai.property.quality.entity.plan.Record;
import com.sgai.property.quality.service.MoreDataSourceCrudServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QRecordServiceImpl extends MoreDataSourceCrudServiceImpl<PIRecordDao,Record> {

}