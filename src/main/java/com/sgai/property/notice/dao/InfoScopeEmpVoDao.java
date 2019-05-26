package com.sgai.property.notice.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sgai.property.notice.entity.NoticeInfoScopeEmp;

@Mapper
public interface InfoScopeEmpVoDao {

	void batchInsert(List<NoticeInfoScopeEmp> infoScopeEmpList);

}
