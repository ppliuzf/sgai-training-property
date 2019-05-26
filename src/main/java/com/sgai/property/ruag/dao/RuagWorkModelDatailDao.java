package com.sgai.property.ruag.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.sgai.common.persistence.CrudDao;
import com.sgai.property.ruag.dto.RuagWorkModelDatailVo;
import com.sgai.property.ruag.entity.RuagModelTemplate;
import com.sgai.property.ruag.entity.RuagWorkModelDatail;

/**
 * 
    * @ClassName: RuagWorkModelDatailDao  
    * @com.sgai.property.commonService.vo;(设备运行策略配置DAO接口)
    * @author 王天尧  
    * @date 2018年1月6日  
    * @Company 首自信--智慧城市创新中心
 */
@Mapper
public interface RuagWorkModelDatailDao extends CrudDao<RuagWorkModelDatail> {
	/**
	 * 
	    * @Title: findDatilByDay  
	    * @com.sgai.property.commonService.vo;(查询某天某个运行策略的具体设置)
	    * @param @param params （装有时间，运行策略id的map集合）
	    * @param @return    
	    * @return List<RuagWorkModelDatail>    返回类型  
	    * @throws
	 */
	List<RuagWorkModelDatail> findDatilByDay(Map<String, String> params);
	
	List<RuagWorkModelDatail> findOwnModels(Map<String, String> params);
	
	List<String> findDevicesCodes(Map<String, String> params);
	
	List<RuagWorkModelDatail> findAllOfList(RuagWorkModelDatail ruagWorkModelDatail);
	
	List<RuagWorkModelDatail> getListByIds(RuagWorkModelDatail ruagWorkModelDatail);
	
	List<RuagWorkModelDatail> getListGroupByProfCode(RuagWorkModelDatail ruagWorkModelDatail);
	
	List<RuagWorkModelDatail> findDevicesById(String id);
	
	

}