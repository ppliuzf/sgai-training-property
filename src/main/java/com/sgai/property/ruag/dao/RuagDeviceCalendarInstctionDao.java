package com.sgai.property.ruag.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.sgai.common.persistence.CrudDao;
import com.sgai.property.ruag.entity.RuagCalendarWorkModel;
import com.sgai.property.ruag.entity.RuagDeviceCalendarInstction;

/**
 * 
    * @ClassName: RuagDeviceCalendarInstctionDao  
    * @com.sgai.property.commonService.vo;(设备指令状态DAO接口)
    * @author 王天尧  
    * @date 2018年1月5日  
    * @Company 首自信--智慧城市创新中心
 */
@Mapper
public interface RuagDeviceCalendarInstctionDao extends CrudDao<RuagDeviceCalendarInstction> {
	/**
	 * 
	    * @Title: findInsAfterToday  
	    * @com.sgai.property.commonService.vo;(查找某一运行策略从明天到结束日期的指令)
	    * @param @param RuagDeviceCalendarInstction
	    * @param @return    参数  
	    * @return List<RuagDeviceCalendarInstction>    返回类型  
	    * @throws
	 */
	List<RuagDeviceCalendarInstction> findInsAfterToday(RuagDeviceCalendarInstction RuagDeviceCalendarInstction);
	/**
	 * 
	    * @Title: findInsLatest  
	    * @com.sgai.property.commonService.vo;(查找当天最近五分钟内的指令集)
	    * @param @param params
	    * @param @return    参数  
	    * @return List<RuagDeviceCalendarInstction>    返回类型  
	    * @throws
	 */
	List<RuagDeviceCalendarInstction> findInsLatest(Map<String, Object> params);
	/**
	 * 
	    * @Title: deleteByDate  
	    * @com.sgai.property.commonService.vo;(删除某个时间段内的指令)
	    * @param @param params    参数  
	    * @return void    返回类型  
	    * @throws
	 */
	void deleteByDate(Map<String, Object> params) ;
	/**
	 * 
	    * @Title: findRecentTime  
	    * @com.sgai.property.commonService.vo;(查询指令集中最近的时间点)
	    * @param @return    参数  
	    * @return String    返回类型  
	    * @throws
	 */
	String findRecentTime();
	List<RuagDeviceCalendarInstction> findAllOfList(RuagDeviceCalendarInstction ruagDeviceCalendarInstction);
}