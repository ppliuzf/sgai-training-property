package com.sgai.property.meeting.dao;

import com.sgai.property.commonService.dao.MoreDataSourceDao;
import com.sgai.property.meeting.entity.RoomPosition;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IRoomPositionDao extends MoreDataSourceDao<RoomPosition> {

    /**
     * 获取位置列表
     * @return
     */
    List<RoomPosition> findRoomPositionList(String comId);
    
    /**
     * 获取存在可用会议室的位置列表
     * @return
     */
    List<RoomPosition> findRoomPositionListExistsRoom(@Param("currtDate") Long currtDate, @Param("timeSeg") String timeSeg, @Param("createEiId") String createEiId);
}