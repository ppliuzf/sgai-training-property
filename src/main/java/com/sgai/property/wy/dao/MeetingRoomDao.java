package com.sgai.property.wy.dao;

import com.sgai.common.persistence.CrudDao;
import com.sgai.property.wy.entity.MeetingOrder;
import com.sgai.property.wy.entity.MeetingRoom;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by ppliu on 2018/1/19.
 *
 *
 */
@Mapper
public interface MeetingRoomDao extends CrudDao<MeetingRoom> {

     List<MeetingOrder> selectMeetingOrder(MeetingRoom meetingRoom);
}
