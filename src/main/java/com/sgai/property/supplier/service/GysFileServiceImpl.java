package com.sgai.property.supplier.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sgai.property.supplier.dao.IGysFileDao;
import com.sgai.property.supplier.entity.GysFile;

@Service
public class GysFileServiceImpl extends MoreDataSourceCrudServiceImpl<IGysFileDao,GysFile>{
	@Autowired
	private IGysFileDao gysFileDao;

}