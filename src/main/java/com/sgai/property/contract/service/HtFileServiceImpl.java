package com.sgai.property.contract.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sgai.property.contract.dao.ICHtFileDao;
import com.sgai.property.contract.entity.HtFile;

@Service
public class HtFileServiceImpl extends MoreDataSourceCrudServiceImpl<ICHtFileDao,HtFile>{
	@Autowired
	private ICHtFileDao htFileDao;

}