package com.sgai.property.mdm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sgai.common.persistence.CrudDao;
import com.sgai.property.mdm.entity.MdmMatClass;
import com.sgai.property.mdm.entity.MdmSuppMatClassRelation;
import com.sgai.property.mdm.entity.MdmSupplierInfo;

/**
 * 供应商&mdash;物料分类关系表DAO接口
 * @author liushang
 * @version 2017-11-24
 */
@Mapper
public interface MdmSuppMatClassRelationDao extends CrudDao<MdmSuppMatClassRelation> {
	List<MdmSupplierInfo> getSupplierInfo();

	  
	    /**  
	    * @Title: getMatClass  
	    * @com.sgai.property.commonService.vo;(这里用一句话描述这个方法的作用)
	    * @param @return    参数  
	    * @return List<MdmMatClass>    返回类型  
	    * @throws  
	    */

        List<MdmMatClass> getMatClass();


		  
		    /**  
		    * @Title: findRestPage  
		    * @com.sgai.property.commonService.vo;(这里用一句话描述这个方法的作用)
		    * @param @param page
		    * @param @param mdmSuppMatClassRelation
		    * @param @return    参数  
		    * @return Page<MdmSuppMatClassRelation>    返回类型  
		    * @throws  
		    */

            List<MdmSuppMatClassRelation> findRestPage(MdmSuppMatClassRelation mdmSuppMatClassRelation);
}