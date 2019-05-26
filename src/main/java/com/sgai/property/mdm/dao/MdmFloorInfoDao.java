package com.sgai.property.mdm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.sgai.common.persistence.CrudDao;
import com.sgai.property.mdm.entity.MdmBuildInfo;
import com.sgai.property.mdm.entity.MdmFloorInfo;

/**
 * 楼层描述 ---空间DAO接口
 * @author zhb
 * @version 2017-11-24
 */
@Mapper
public interface MdmFloorInfoDao extends CrudDao<MdmFloorInfo> {
	
	MdmFloorInfo getByCode(String arg0, String arg1);
}