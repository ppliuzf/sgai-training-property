
package com.sgai.property.ctl.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sgai.common.persistence.CrudDao;
import com.sgai.property.ctl.entity.CtlParam;

/**
 * 
    * @ClassName: CtlParamDao  
    * @Description: (系统参数定义dao)
    * @author shang  
    * @date 2017年11月18日  
    * @Company 首自信--智慧城市创新中心
 */

    
@Mapper
public interface CtlParamDao extends CrudDao<CtlParam> {
	/**
	 * 
	    * @Title: getCodeType  
	    * @Description: (从ctl_code_det中获取全部值类型)
	    * @param @return    参数  
	    * @return List<String>    返回类型  值类型字符串数组
	    * @throws
	 */
	List<String> getCodeType();
		 
}