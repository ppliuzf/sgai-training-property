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
import com.sgai.property.mdm.dao.MdmEmTypeDetailDao;
import com.sgai.property.mdm.entity.MdmEmType;
import com.sgai.property.mdm.entity.MdmEmTypeDetail;

/**
 * 事件详细类别维护Service
 * @author liushang
 * @version 2017-12-05
 */
@Service
@Transactional
public class MdmEmTypeDetailService extends CrudServiceExt<MdmEmTypeDetailDao, MdmEmTypeDetail> {
	@Autowired
	private DeleteRulesUtils deleteRulesUtils;
	public MdmEmTypeDetail get(String id) {
		return super.get(id);
	}

	public List<MdmEmTypeDetail> findList(MdmEmTypeDetail mdmEmTypeDetail) {
		return super.findList(mdmEmTypeDetail);
	}

	public Page<MdmEmTypeDetail> findPage(Page<MdmEmTypeDetail> page, MdmEmTypeDetail mdmEmTypeDetail) {
		return super.findPage(page, mdmEmTypeDetail);
	}

	@Transactional(readOnly = false)
	public void save(MdmEmTypeDetail mdmEmTypeDetail) {
		super.save(mdmEmTypeDetail);
	}

	@Transactional(readOnly = false)
	public void delete(MdmEmTypeDetail mdmEmTypeDetail) {
		super.delete(mdmEmTypeDetail);
	}


	    /**
	    * @Title: getListEmType
	    * @com.sgai.property.commonService.vo;(这里用一句话描述这个方法的作用)
	    * @param @param page
	    * @param @param mdmEmTypeDetail
	    * @param @return    参数
	    * @return Page<MdmEmTypeDetail>    返回类型
	    * @throws
	    */

	public List<MdmEmTypeDetail> getListEmType(Page<MdmEmTypeDetail> page, MdmEmTypeDetail mdmEmTypeDetail) {

		mdmEmTypeDetail.preGet();
		return super.dao.getListEmType(mdmEmTypeDetail);
	}

	@Transactional(readOnly = false)
	public Map<String, Object> delete(String ids) {
		Map<String, Object> map = new HashMap<String, Object>();
		String idss[]=ids.split(",");

		List<MdmEmTypeDetail> list = new ArrayList<MdmEmTypeDetail>();
		List<String> newList = new ArrayList<String>();
		for(String id:idss){
			if(id!=null&&!id.equals("")){
				newList.add(id);
				MdmEmTypeDetail MdmEmType = super.get(id);
				list.add(MdmEmType);
			}
		}
		Map<String,String> resultMap = deleteRulesUtils.checkBeforeDelete(MdmEmTypeDetail.class,newList);
		if("true".equals(resultMap.get("value"))) {
			List<MdmEmTypeDetail> finalList = batchDelete(list);
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

}
