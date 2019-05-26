package com.sgai.property.meeting.service;
import com.sgai.property.meeting.dao.IInviteDeptDao;
import com.sgai.property.meeting.dao.IInviterDao;
import com.sgai.property.meeting.entity.InviteDept;
import com.sgai.property.meeting.entity.Inviter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class InviterDeptServiceImpl extends MoreDataSourceCrudServiceImpl<IInviteDeptDao,InviteDept>{
	@Autowired
	private IInviteDeptDao inviteDeptDao;

}