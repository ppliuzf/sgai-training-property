package com.sgai.property.alm.dao;

import com.sgai.common.persistence.CrudDao;
import com.sgai.property.alm.entity.AlmRecordList;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 
    * ClassName: AlmRecordListDao  
    * com.sgai.property.commonService.vo;(报警记录列表DAO接口)
    * @author 王天尧  
    * Date 2017年11月26日  
    * Company 首自信--智慧城市创新中心
 */
@Mapper
public interface AlmRecordListDao extends CrudDao<AlmRecordList> {
	List<AlmRecordList>  findIndexAlmForPage(AlmRecordList almRecordList);
	
	List<AlmRecordList> getAlarmListByUser(AlmRecordList almRecordList);
	
	List<AlmRecordList> findListOrderByStates(AlmRecordList almRecordList);
	
	Integer findCount(AlmRecordList almRecordList);
	
	List<Map<String,String>> almCountsByProf();
}