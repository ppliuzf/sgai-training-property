package com.sgai.property.meeting.dao;
import com.sgai.property.commonService.dao.MoreDataSourceDao;
import com.sgai.property.meeting.entity.RoomDeviceStatus;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IRoomDeviceStatusDao extends MoreDataSourceDao<RoomDeviceStatus> {


    List<RoomDeviceStatus> findListDev(RoomDeviceStatus rds);
}