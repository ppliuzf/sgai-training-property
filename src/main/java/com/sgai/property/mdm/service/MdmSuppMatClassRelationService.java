package com.sgai.property.mdm.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgai.common.persistence.Page;
import com.sgai.common.service.CrudServiceExt;
import com.sgai.property.ctl.entity.CtlComp;
import com.sgai.property.ctl.service.DeleteRulesUtils;
import com.sgai.property.mdm.dao.MdmSuppMatClassRelationDao;
import com.sgai.property.mdm.entity.MdmSuppMatClassRelation;
import com.sgai.property.mdm.entity.MdmSupplierInfo;
import com.sgai.property.mdm.entity.MdmMatClass;

/**
 * 供应商&mdash;物料分类关系表Service
 * @author liushang
 * @version 2017-11-24
 */
@Service
@Transactional
public class MdmSuppMatClassRelationService extends CrudServiceExt<MdmSuppMatClassRelationDao, MdmSuppMatClassRelation> {

	@Autowired
	private DeleteRulesUtils deleteRulesUtils;

	public MdmSuppMatClassRelation get(String id) {
		return super.get(id);
	}

	public List<MdmSuppMatClassRelation> findList(MdmSuppMatClassRelation mdmSuppMatClassRelation) {
		return super.findList(mdmSuppMatClassRelation);
	}

	public Page<MdmSuppMatClassRelation> findPage(Page<MdmSuppMatClassRelation> page, MdmSuppMatClassRelation mdmSuppMatClassRelation) {
		return super.findPage(page, mdmSuppMatClassRelation);
	}

	@Transactional(readOnly = false)
	public void save(MdmSuppMatClassRelation mdmSuppMatClassRelation) {
		super.save(mdmSuppMatClassRelation);
	}

	@Transactional(readOnly = false)
	public Map<String, Object> delete(String ids) {
		Map<String, Object> map = new HashMap<String, Object>();
		String idss[]=ids.split(",");

		List<MdmSuppMatClassRelation> list = new ArrayList<MdmSuppMatClassRelation>();
		List<String> newList = new ArrayList<String>();
		for(String id:idss){
			if(id!=null&&!id.equals("")){
				newList.add(id);
				MdmSuppMatClassRelation mdmSuppMatClassRelation = super.get(id);
				list.add(mdmSuppMatClassRelation);
			}
		}
		Map<String,String> resultMap = deleteRulesUtils.checkBeforeDelete(MdmSuppMatClassRelation.class,newList);
		if("true".equals(resultMap.get("value"))) {
			List<MdmSuppMatClassRelation> finalList = batchDelete(list);
			if(finalList.size() > 0) {
				map.put("msg", "success");
			}else {
				map.put("msg", "delete fail");
			}
		}else {
			map.put("msg", resultMap.get("description"));
		}
	return map;
	}


	    /**
	    * @Title: getSupplierInfo
	    * @com.sgai.property.commonService.vo;(这里用一句话描述这个方法的作用)
	    * @param @return    参数
	    * @return List<MdmSupplierInfo>    返回类型
	    * @throws
	    */

	public List<MdmSupplierInfo> getSupplierInfo() {

		return super.dao.getSupplierInfo();
	}


		    /**
		    * @Title: getMatClass
		    * @com.sgai.property.commonService.vo;(这里用一句话描述这个方法的作用)
		    * @param @return    参数
		    * @return List<MdmMatClass>    返回类型
		    * @throws
		    */

		public List<MdmMatClass> getMatClass() {

			return super.dao.getMatClass();
		}


			    /**
			    * @Title: findRestPage
			    * @com.sgai.property.commonService.vo;(这里用一句话描述这个方法的作用)
			    * @param @param page
			    * @param @param mdmSuppMatClassRelation
			    * @param @return    参数
			    * @return Page<MdmSuppMatClassRelation>    返回类型
			    * @throws
			    */

			public Page<MdmSuppMatClassRelation> findRestPage(Page<MdmSuppMatClassRelation> page,
					MdmSuppMatClassRelation mdmSuppMatClassRelation) {

				mdmSuppMatClassRelation.setPage(page);
				mdmSuppMatClassRelation.preGet();
				page.setList(super.dao.findRestPage(mdmSuppMatClassRelation));
				return page;

			}

}
