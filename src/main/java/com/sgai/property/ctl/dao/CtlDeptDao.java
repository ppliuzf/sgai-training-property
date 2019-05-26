package com.sgai.property.ctl.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import com.sgai.common.persistence.CrudDao;
import com.sgai.property.ctl.entity.CtlDept;

/**
 * 
    * ClassName: CtlDeptDao  
    * Description: (部门Dao)
    * @author yangyz  
    * Date 2017年11月18日  
    * Company 首自信--智慧城市创新中心
 */
@Mapper
public interface CtlDeptDao extends CrudDao<CtlDept> {

	/**
	 * 
	 * getCtlDept:(查询一条部门信息).
	 * @param ctlDept
	 * @return :CtlDept 
	 * @since JDK 1.8
	 * @author yangyz
	 */
    CtlDept getCtlDept(CtlDept ctlDept);

	/**
	 * 
	 * getDeptList:(查询部门树机构数据).
	 * @return :List<Map<String,Object>> 
	 * @since JDK 1.8
	 * @author yangyz
	 */
    List<Map<String,Object>> getDeptList(CtlDept ctlDept);

	/**
	 * 
	 * countDeptCodeExceptSelf:(查询重复部门代码记录条数).
	 * @param ctlDept
	 * @return :Integer 
	 * @since JDK 1.8
	 * @author yangyz
	 */
    Integer countDeptCodeExceptSelf(CtlDept ctlDept);
}