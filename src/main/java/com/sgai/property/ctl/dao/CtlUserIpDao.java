
package com.sgai.property.ctl.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sgai.common.persistence.CrudDao;
import com.sgai.property.ctl.entity.CtlUserIp;

/**
 * 用户IP管理DAO接口
 * @author liushang
 * @version 2017-11-09
 */
@Mapper
public interface CtlUserIpDao extends CrudDao<CtlUserIp> {

	  

		  
		    /**  
		    * @Title: getComp  
		    * @Description: (从ctl_comp中获取全部机构代码)
		    * @param @return    参数  
		    * @return List<String>    返回类型  机构代码字符串数组
		    * @throws  
		    */  
		    
		List<String> getComp();

			  
			 /**
			  *  
			     * @Title: getUser  
			     * @Description: (根据选择的机构从ctl_user中查询用户条目)
			     * @param @param ctlUserIp
			     * @param @return    参数  
			     * @return List<CtlUserIp>    返回类型   携带用户条目信息的实例数组
			     * @throws
			  */
			List<CtlUserIp> getUser(CtlUserIp ctlUserIp);

			  

	
}