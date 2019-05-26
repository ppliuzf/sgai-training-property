package com.sgai.property.em.dao;

import org.apache.ibatis.annotations.Mapper;
import com.sgai.common.persistence.CrudDao;
import com.sgai.property.em.entity.EmComplaintsReturn;

/**
 * 
    * ClassName: EmComplaintsReturnDao  
    * com.sgai.property.commonService.vo;(事件回访接口)
    * @author yangyz  
    * Date 2017年12月13日  
    * Company 首自信--智慧城市创新中心
 */
@Mapper
public interface EmComplaintsReturnDao extends CrudDao<EmComplaintsReturn> {
	
	/**
	 * 
	 * getComplaintsReturn:(查询回访内容).
	 * @param emComplaintsReturn
	 * @return :EmComplaintsReturn 
	 * @since JDK 1.8
	 * @author yangyz
	 */
    EmComplaintsReturn getComplaintsReturn(EmComplaintsReturn emComplaintsReturn);
}