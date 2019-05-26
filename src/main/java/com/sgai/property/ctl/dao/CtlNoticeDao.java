
package com.sgai.property.ctl.dao;


import org.apache.ibatis.annotations.Mapper;

import com.sgai.common.persistence.CrudDao;
import com.sgai.property.ctl.entity.CtlNotice;

/**
 * 消息通知DAO接口
 * @author admin
 * @version 2018-06-15
 */
@Mapper
public interface CtlNoticeDao extends CrudDao<CtlNotice> {
	
}