package com.sgai.property.mdm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sgai.common.persistence.CrudDao;
import com.sgai.common.persistence.Page;
import com.sgai.property.mdm.entity.MdmEmTypeDetail;

/**
 * 事件详细类别维护DAO接口
 * @author liushang
 * @version 2017-12-05
 */
@Mapper
public interface MdmEmTypeDetailDao extends CrudDao<MdmEmTypeDetail> {

	  
	    /**  
	    * @Title: getListEmType  
	    * @com.sgai.property.commonService.vo;(这里用一句话描述这个方法的作用)
	    * @param @param mdmEmTypeDetail
	    * @param @return    参数  
	    * @return Page<MdmEmTypeDetail>    返回类型  
	    * @throws  
	    */  
	    
	List<MdmEmTypeDetail> getListEmType(MdmEmTypeDetail mdmEmTypeDetail);
	
}