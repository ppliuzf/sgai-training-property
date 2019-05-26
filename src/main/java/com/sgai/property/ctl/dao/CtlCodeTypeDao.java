
package com.sgai.property.ctl.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sgai.common.persistence.CrudDao;
import com.sgai.property.ctl.entity.CtlCodeType;

/**
 * 
    * @ClassName: CtlCodeTypeDao  
    * @Description: (系统基础代码类型表维护dao)
    * @author shang  
    * @date 2017年11月18日  
    * @Company 首自信--智慧城市创新中心
 */
@Mapper
public interface CtlCodeTypeDao extends CrudDao<CtlCodeType> {
	/**
	 * 
	    * @Title: getCodeType  
	    * @Description: (从ctl_code_type中获取全部代码类型)
	    * @param @return    参数  
	    * @return List<String>    返回类型  代码类型字符串数组
	    * @throws
	 */
	List<String> getCodeType();
}