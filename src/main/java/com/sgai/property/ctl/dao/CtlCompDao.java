package com.sgai.property.ctl.dao;

import org.apache.ibatis.annotations.Mapper;
import com.sgai.common.persistence.CrudDao;
import com.sgai.property.ctl.entity.CtlComp;

/**
 * 
    * ClassName: CtlCompDao  
    * Description: (机构Dao)
    * @author yangyz  
    * Date 2017年11月18日  
    * Company 首自信--智慧城市创新中心
 */
@Mapper
public interface CtlCompDao extends CrudDao<CtlComp> {
	CtlComp getComp(CtlComp ctlComp);
}