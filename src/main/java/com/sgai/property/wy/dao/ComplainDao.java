package com.sgai.property.wy.dao;

import com.sgai.common.persistence.CrudDao;
import com.sgai.property.wy.entity.Complain;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by ppliu on 2018/1/19.
 * 物业里面的邮件系统.
 *
 */
@Mapper
public interface ComplainDao extends CrudDao<Complain> {

}
