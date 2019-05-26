package com.sgai.property.ctl.dao;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sgai.common.persistence.CrudDao;
import com.sgai.property.ctl.entity.CtlComp;
import com.sgai.property.ctl.entity.CtlUserComp;
/**
 * 
    * ClassName: CtlUserCompDao  
    * Description: (机构管理员关联机构)
    * @author 王天尧  
    * Date 2017年12月27日  
    * Company 首自信--智慧城市创新中心
 */
@Mapper
public interface CtlUserCompDao extends CrudDao<CtlUserComp> {
	List<CtlComp> findLackList(CtlComp ctlComp);
}