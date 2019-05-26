package com.sgai.property.meeting.dao;
import com.sgai.common.persistence.CrudDao;
import com.sgai.property.commonService.dao.MoreDataSourceDao;
import com.sgai.property.meeting.entity.RoomType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IRoomTypeDao extends MoreDataSourceDao<RoomType> {
    /**
     * 根据类型名称查询是否有重复 实现去重功能
     * @param rtName
     * @return
     */
    List<RoomType> getByName(@Param(value = "rtName") String rtName);

    List<RoomType> getByNameAndId(@Param(value = "rtName") String rtName, @Param(value = "id") String id);
}