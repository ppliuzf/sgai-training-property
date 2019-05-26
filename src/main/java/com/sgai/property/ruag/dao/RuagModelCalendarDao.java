package com.sgai.property.ruag.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.sgai.common.persistence.CrudDao;
import com.sgai.property.ruag.entity.RuagLinkageRuleSpace;
import com.sgai.property.ruag.entity.RuagModelCalendar;

/**
 * 
    * @ClassName: RuagModelCalendarDao  
    * @com.sgai.property.commonService.vo;(策略运行日程DAO接口)
    * @author 王天尧  
    * @date 2018年1月4日  
    * @Company 首自信--智慧城市创新中心
 */
@Mapper
public interface RuagModelCalendarDao extends CrudDao<RuagModelCalendar> {
	/**
	 * 
	    * @Title: findByDate  
	    * @com.sgai.property.commonService.vo;(查询指定日期内的策略日程记录)
	    * @param @param ruagModelTemplate    运行策略对象（包含id,指定的开始时间，结束时间）
	    * @param @return    
	    * @return List<RuagModelCalendar>    日程记录集合  
	    * @throws
	 */
	List<RuagModelCalendar> findListByDate(Map<String, String> params);
	/**
	 * 
	    * @Title: findTodayAndTomorrow  
	    * @com.sgai.property.commonService.vo;(查询今天或明天的策略日程记录)
	    * @param @param params
	    * @param @return    参数  
	    * @return List<RuagModelCalendar>    返回类型  
	    * @throws
	 */
	List<RuagModelCalendar> findTodayAndTomorrow(Map<String, String> params);
	/**
	 * 
	    * @Title: deleteByModelId  
	    * @com.sgai.property.commonService.vo;(根据策略id删除日程)
	    * @param @param ruagModelCalendar    参数  
	    * @return void    返回类型  
	    * @throws
	 */
	void deleteByModelId(RuagModelCalendar ruagModelCalendar);
	/**
	 * 
	    * @Title: findTodayAndDate  
	    * @com.sgai.property.commonService.vo;(查找某个日期控制策略今天以后的日程，包括今天)
	    * @param @param params
	    * @param @return    参数  
	    * @return List<RuagModelCalendar>    返回类型  
	    * @throws
	 */
	List<RuagModelCalendar> findTodayAndDate(Map<Object, String> params);
	/**
	 * 
	    * @Title: findTodayAndTime  
	    * @com.sgai.property.commonService.vo;(查找某个时间控制策略今天以后的日程，包括今天)
	    * @param @param params
	    * @param @return    参数  
	    * @return List<RuagModelCalendar>    返回类型  
	    * @throws
	 */
	List<RuagModelCalendar> findTodayAndTime(Map<Object, String> params);
	/**
	 * 
	    * @Title: findTimeBySE  
	    * @com.sgai.property.commonService.vo;(根据起止时间查找同一天内有时间交集的临时模式)
	    * @param @param params
	    * @param @return    参数  
	    * @return List<RuagModelCalendar>    返回类型  
	    * @throws
	 */
	List<RuagModelCalendar> findTimeBySE(Map<String, String> params);
	
	List<RuagModelCalendar> findAllOfList(RuagModelCalendar ruagModelCalendar);
}