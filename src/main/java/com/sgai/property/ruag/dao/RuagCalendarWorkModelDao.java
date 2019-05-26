package com.sgai.property.ruag.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.sgai.common.persistence.CrudDao;
import com.sgai.property.ruag.entity.RuagCalendarWorkModel;

/**
 * 
    * @ClassName: RuagCalendarWorkModelDao  
    * @com.sgai.property.commonService.vo;(策略日程与策略配置之间的关系DAO接口)
    * @author 王天尧  
    * @date 2018年1月9日  
    * @Company 首自信--智慧城市创新中心
 */
@Mapper
public interface RuagCalendarWorkModelDao extends CrudDao<RuagCalendarWorkModel> {
	List<RuagCalendarWorkModel> findAllOfList(RuagCalendarWorkModel ruagCalendarWorkModel);
	void deleteByCalendarId(String calendarId);
}