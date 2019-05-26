package com.sgai.property.wy.dao;

import com.sgai.common.persistence.CrudDao;
import com.sgai.property.wy.entity.SysFile;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by ppliu on 2018/1/31.
 * 物业里面的邮件系统.
 *
 */
@Mapper
public interface SysFileDao extends CrudDao<SysFile> {

}
