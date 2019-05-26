package com.sgai.property.ctl.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.sgai.common.persistence.CrudDao;
import com.sgai.property.ctl.entity.CtlProg;

/**
 * 
    * ClassName: CtlProgDao  
    * Description: (功能Dao)
    * @author yangyz  
    * Date 2017年11月18日  
    * Company 首自信--智慧城市创新中心
 */
@Mapper
public interface CtlProgDao extends CrudDao<CtlProg> {
	
	/**
	 * 
	 * findCtlProg:(查询功能列表).
	 * @param arg0
	 * @param arg1
	 * @return :List<CtlProg> 
	 * @since JDK 1.8
	 * @author yangyz
	 */
    List<CtlProg>  findCtlProg(String arg0, String arg1);
}