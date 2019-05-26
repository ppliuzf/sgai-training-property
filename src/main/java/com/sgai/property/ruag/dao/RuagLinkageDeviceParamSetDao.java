package com.sgai.property.ruag.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sgai.common.persistence.CrudDao;
import com.sgai.property.ruag.entity.RuagLinkaageNextDevice;
import com.sgai.property.ruag.entity.RuagLinkageDeviceParamSet;

/**
 * 模式设备参数设置DAO接口
 * @author yangyz
 * @version 2018-01-02
 */
@Mapper
public interface RuagLinkageDeviceParamSetDao extends CrudDao<RuagLinkageDeviceParamSet> {
	
	void deleteByMasterId(RuagLinkageDeviceParamSet ruagLinkageDeviceParamSet);
	
	List<RuagLinkageDeviceParamSet> findAllOfList(RuagLinkageDeviceParamSet ruagLinkageDeviceParamSet);
}