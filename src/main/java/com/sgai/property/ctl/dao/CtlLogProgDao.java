package com.sgai.property.ctl.dao;

import com.sgai.common.persistence.CrudDao;
import com.sgai.property.ctl.entity.CtlLogProg;

import org.apache.ibatis.annotations.Mapper;


   /** 
    * ClassName: CtlLogProgDao  
    * Description: (这里用一句话描述这个类的作用)
    * @author admin  
    * Date 2017年11月18日  
    * Company 首自信--智慧城市创新中心
    */  
@Mapper
public interface CtlLogProgDao extends CrudDao<CtlLogProg> {
	
}