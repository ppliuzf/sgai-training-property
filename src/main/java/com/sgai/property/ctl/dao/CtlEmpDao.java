package com.sgai.property.ctl.dao;

import com.sgai.common.persistence.CrudDao;
import com.sgai.property.ctl.entity.CtlEmp;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 *
    * ClassName: CtlEmpDao
    * Description: (员工Dao)
    * @author yangyz
    * Date 2017年11月18日
    * Company 首自信--智慧城市创新中心
 */
@Mapper
public interface CtlEmpDao extends CrudDao<CtlEmp> {

	/**
	 *
	 * getEmpByComCodeAndEmpCode:(查询某机构下一个员工信息).
	 * @param arg0
	 * @param arg1
	 * @return :CtlEmp
	 * @since JDK 1.8
	 * @author yangyz
	 */
    CtlEmp   getEmpByComCodeAndEmpCode(String arg0, String arg1);

	List<CtlEmp> findEmpList(CtlEmp ctlEmp);

	List<CtlEmp> findEmpAllList(CtlEmp ctlEmp);

	List<CtlEmp> findLackList(CtlEmp ctlEmp);

	CtlEmp getByCode(String code);
}
