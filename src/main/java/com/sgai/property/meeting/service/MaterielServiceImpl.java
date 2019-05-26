package com.sgai.property.meeting.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sgai.property.meeting.dao.IMaterielDao;
import com.sgai.property.meeting.entity.Materiel;

@Service
public class MaterielServiceImpl extends MoreDataSourceCrudServiceImpl<IMaterielDao,Materiel>{
	@Autowired
	private IMaterielDao materielDao;

}