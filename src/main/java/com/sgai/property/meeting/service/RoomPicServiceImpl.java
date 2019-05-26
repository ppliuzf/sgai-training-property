package com.sgai.property.meeting.service;
import com.sgai.property.meeting.dao.IRoomPicDao;
import com.sgai.property.meeting.entity.RoomPic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RoomPicServiceImpl extends MoreDataSourceCrudServiceImpl<IRoomPicDao,RoomPic>{
	@Autowired
	private IRoomPicDao roomPicDao;

}