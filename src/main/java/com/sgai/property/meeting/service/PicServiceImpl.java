package com.sgai.property.meeting.service;
import com.sgai.property.meeting.dao.IPicDao;
import com.sgai.property.meeting.entity.Pic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PicServiceImpl extends MoreDataSourceCrudServiceImpl<IPicDao,Pic>{
	@Autowired
	private IPicDao picDao;

}