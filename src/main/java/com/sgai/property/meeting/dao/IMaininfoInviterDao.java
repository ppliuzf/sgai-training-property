package com.sgai.property.meeting.dao;

import com.sgai.common.persistence.CrudDao;
import com.sgai.property.commonService.dao.MoreDataSourceDao;
import com.sgai.property.meeting.entity.MaininfoInviter;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IMaininfoInviterDao extends MoreDataSourceDao<MaininfoInviter> {

    int deleteByMiId(@Param("miId") String miId);

    List<MaininfoInviter> getByMiId(@Param("miId") String miId);
}