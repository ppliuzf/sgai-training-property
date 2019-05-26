package com.sgai.property.quality.service.plan;


import com.sgai.property.quality.dao.plan.IRecordImgDao;
import com.sgai.property.quality.entity.plan.RecordImg;
import com.sgai.property.quality.service.MoreDataSourceCrudServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecordImgServiceImpl extends MoreDataSourceCrudServiceImpl<IRecordImgDao,RecordImg> {
	@Autowired
	private IRecordImgDao recordImgDao;

}