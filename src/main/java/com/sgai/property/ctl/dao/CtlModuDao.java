package com.sgai.property.ctl.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sgai.common.persistence.CrudDao;
import com.sgai.property.ctl.entity.CtlModu;

    /**  
    * @ClassName: CtlModuDao  
    * @Description: 模块维护DAO接口 
    * @author guanze  
    * @date 2017年11月18日  
    * @Company 首自信--智慧城市创新中心  
    */  
    
@Mapper
public interface CtlModuDao extends CrudDao<CtlModu> {
	List<CtlModu> checkModu(CtlModu ctlModu);
}