package com.sgai.property.ctl.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sgai.common.persistence.CrudDao;
import com.sgai.property.ctl.entity.CtlParamComp;

/**
 * 
    * @ClassName: CtlParamCompDao  
    * @Description: (机构参数维护dao)
    * @author shang  
    * @date 2017年11月18日  
    * @Company 首自信--智慧城市创新中心
 */
@Mapper
public interface CtlParamCompDao extends CrudDao<CtlParamComp> {
	List<CtlParamComp> getComp();
	List<CtlParamComp> getCompByCode(String comCode); 
	List<CtlParamComp> getSbs();
	List<CtlParamComp> getBusiByComCode(String comCode);
	List<CtlParamComp> getSbsList(CtlParamComp ctlParamComp);
	  

	      
		    /**  
		    * @Title: findLocalList  
		    * @Description: (根据机构、子系统、参数查询条目)
		    * @param @param ctlComp
		    * @param @return    参数  
		    * @return List<CtlParamComp>    返回类型  携带条目信息的实例的数组
		    * @throws  
		    */  
		    
		List<CtlParamComp> findLocalList(CtlParamComp ctlComp);
}