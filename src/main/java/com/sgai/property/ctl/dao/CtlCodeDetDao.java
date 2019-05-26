
package com.sgai.property.ctl.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.sgai.common.persistence.CrudDao;
import com.sgai.property.ctl.entity.CtlCodeDet;

/**
 * 
    * @ClassName: CtlCodeDetDao  
    * @Description: (系统基础代码表维护dao)
    * @author shang  
    * @date 2017年11月18日  
    * @Company 首自信--智慧城市创新中心
 */
@Mapper
public interface CtlCodeDetDao extends CrudDao<CtlCodeDet> {
	/**
	 * 
	    * @Title: getCodeType  
	    * @Description: (从ctl_code_type中获取全部代码类型)
	    * @param @return    参数  
	    * @return List<String>    返回类型  代码类型字符串数组
	    * @throws
	 */
	List<String> getCodeType();
	
	/**
	 * findCodeDetForSpace:(空间用的主数据).
	 * @param map
	 * @return :List<CtlCodeDet> 
	 * @since JDK 1.8
	 * @author admin
	 */
    List<CtlCodeDet> findCodeDetForSpace(Map<String, String> map);
	
	/**
	 * 查询指定类型的数据
	 * @author chenxing
	 */
    List<Map<String,String>> getCodeInfoList(String codeType);
	/**
	 * 
	 * getCodeDet:(根据特定条件查找字典表数据).
	 * @param ctlCodeDet
	 * @return :List<CtlCodeDet> 
	 * @since JDK 1.8
	 * @author 王天尧
	 */
	CtlCodeDet getCodeDet(CtlCodeDet ctlCodeDet);
		
	
}