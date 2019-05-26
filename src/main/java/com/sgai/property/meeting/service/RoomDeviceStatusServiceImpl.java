package com.sgai.property.meeting.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sgai.property.meeting.dao.IRoomDeviceStatusDao;
import com.sgai.property.meeting.entity.RoomDeviceStatus;

@Service
public class RoomDeviceStatusServiceImpl extends MoreDataSourceCrudServiceImpl<IRoomDeviceStatusDao,RoomDeviceStatus>{
	@Autowired
	private IRoomDeviceStatusDao roomDeviceStatusDao;

}