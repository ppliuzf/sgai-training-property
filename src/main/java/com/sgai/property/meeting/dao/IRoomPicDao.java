package com.sgai.property.meeting.dao;
import com.sgai.common.persistence.CrudDao;
import com.sgai.property.commonService.dao.MoreDataSourceDao;
import com.sgai.property.meeting.entity.RoomPic;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IRoomPicDao extends MoreDataSourceDao<RoomPic> {
 
}