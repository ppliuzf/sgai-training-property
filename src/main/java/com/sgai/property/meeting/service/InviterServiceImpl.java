package com.sgai.property.meeting.service;
import com.sgai.property.meeting.dao.IInviterDao;
import com.sgai.property.meeting.entity.Inviter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class InviterServiceImpl extends MoreDataSourceCrudServiceImpl<IInviterDao,Inviter>{
	@Autowired
	private IInviterDao inviterDao;

}