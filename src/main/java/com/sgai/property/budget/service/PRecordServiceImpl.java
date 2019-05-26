package com.sgai.property.budget.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sgai.property.budget.dao.IRecordDao;
import com.sgai.property.budget.entity.Record;

@Service
public class PRecordServiceImpl extends MoreDataSourceCrudServiceImpl<IRecordDao,Record>{

}