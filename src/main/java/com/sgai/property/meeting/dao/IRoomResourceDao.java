package com.sgai.property.meeting.dao;

import com.sgai.common.persistence.CrudDao;
import com.sgai.property.commonService.dao.MoreDataSourceDao;
import com.sgai.property.meeting.entity.RoomResource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 146584
 * @date 2017-11-6 10:35
 */
@Mapper
public interface IRoomResourceDao extends MoreDataSourceDao<RoomResource> {

    /**
     * 获取room列表信息
     * @return
     */
    List<RoomResource> findAll(@Param("comId") Long comId);
    
  /**
   * 根据类型回去会议室列表
   * @param rtId
   * @return
   */
    List<RoomResource> findRtAll(@Param("rtId") String rtId);

    /**
     * 模糊搜索
     * @param comId
     * @param keyWord
     * @param rrRoomState
     * @return
     */
    List<RoomResource>searchRoom(@Param("comId") Long comId, @Param("keyWord") String keyWord, @Param("rrRoomState") Integer rrRoomState);

    List<RoomResource> getByRpIdOrCurrtDateAndTimeSeg(@Param("rpId") String rpId, @Param("currtDate") Long currtDate,
                                                      @Param("timeSeg") String timeSeg, @Param("createEiId") String createEiId);

    RoomResource getByNameAndId(@Param(value = "rrRoomName") String rrRoomName, @Param(value = "rrId") String rrId);

    RoomResource getByName(@Param(value = "rrRoomName") String rrRoomName);
}