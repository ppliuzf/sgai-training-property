package com.sgai.property.ctl.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import com.sgai.common.persistence.CrudDao;
import com.sgai.property.ctl.entity.CtlParamCompSec;


   /** 
    * ClassName: CtlParamCompSecDao  
    * Description: (这里用一句话描述这个类的作用)
    * @author admin  
    * Date 2017年11月18日  
    * Company 首自信--智慧城市创新中心
    */  
@Mapper
public interface CtlParamCompSecDao extends CrudDao<CtlParamCompSec> {


	/**
	 * getModuList:(这里用一句话描述这个方法的作用).
	 * @return :List<Map<String,Object>> 
	 * @since JDK 1.8
	 * @author admin
	 */
    List<Map<String,Object>> getModuList();
}