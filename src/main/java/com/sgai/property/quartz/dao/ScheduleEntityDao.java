  
    /**    
    * @Title: ScheduleEntityDao.java  
    * @Package com.sgai.property.quartz.dao
    * @author admin
    * @date 2017年12月22日  
    * @Company 首自信--智慧城市创新中心
    * @version V1.0    
    */  
    
package com.sgai.property.quartz.dao;

import org.apache.ibatis.annotations.Mapper;

import com.sgai.common.persistence.CrudDao;
import com.sgai.property.quartz.entity.ScheduleEntity;

/**  
    * @ClassName: ScheduleEntityDao  
    * @author admin
    * @date 2017年12月22日  
    * @Company 首自信--智慧城市创新中心  
    */
@Mapper
public interface ScheduleEntityDao extends CrudDao<ScheduleEntity> {

}
