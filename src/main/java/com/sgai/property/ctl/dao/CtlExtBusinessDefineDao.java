package com.sgai.property.ctl.dao;

import com.sgai.common.persistence.CrudDao;
import com.sgai.property.ctl.entity.CtlExtBusinessDefine;
import org.apache.ibatis.annotations.Mapper;

/**
 * 外部对接系统定义DAO接口
 * @author 李伟
 * @version 2018-02-07
 */
@Mapper
public interface CtlExtBusinessDefineDao extends CrudDao<CtlExtBusinessDefine> {
	
}