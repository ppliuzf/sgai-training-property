package com.sgai.property.customer.service;

import com.sgai.property.customer.dao.IUploadRecordInfoDao;
import com.sgai.property.customer.entity.UploadRecordInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UploadRecordInfoServiceImpl extends MoreDataSourceCrudServiceImpl<IUploadRecordInfoDao,UploadRecordInfo>{
	@Autowired
	private IUploadRecordInfoDao uploadRecordInfoDao;

}