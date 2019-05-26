package com.sgai.property.meeting.service;
import com.sgai.property.meeting.dao.IInviterDao;
import com.sgai.property.meeting.dao.IMaininfoInviterDao;
import com.sgai.property.meeting.entity.Inviter;
import com.sgai.property.meeting.entity.MaininfoInviter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MaininfoInviterServiceImpl extends MoreDataSourceCrudServiceImpl<IMaininfoInviterDao,MaininfoInviter>{
	@Autowired
	private IMaininfoInviterDao maininfoInviterDao;

}