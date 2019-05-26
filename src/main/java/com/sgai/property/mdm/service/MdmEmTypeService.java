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
import com.sgai.property.mdm.dao.MdmEmTypeDao;
import com.sgai.property.mdm.entity.MdmEmType;
import com.sgai.property.mdm.entity.MdmEmType;

/**
 * 事件类别维护Service
 * @author liushang
 * @version 2017-12-05
 */
@Service
@Transactional
public class MdmEmTypeService extends CrudServiceExt<MdmEmTypeDao, MdmEmType> {
	@Autowired
	private DeleteRulesUtils deleteRulesUtils;
	public MdmEmType get(String id) {
		return super.get(id);
	}

	public List<MdmEmType> findList(MdmEmType mdmEmType) {
		return super.findList(mdmEmType);
	}

	public Page<MdmEmType> findPage(Page<MdmEmType> page, MdmEmType mdmEmType) {
		return super.findPage(page, mdmEmType);
	}

	@Transactional(readOnly = false)
	public void save(MdmEmType mdmEmType) {
		super.save(mdmEmType);
	}

	@Transactional(readOnly = false)
	public void delete(MdmEmType mdmEmType) {
		super.delete(mdmEmType);
	}

	@Transactional(readOnly = false)
	public Map<String, Object> delete(String ids) {
		Map<String, Object> map = new HashMap<String, Object>();
		String idss[]=ids.split(",");

		List<MdmEmType> list = new ArrayList<MdmEmType>();
		List<String> newList = new ArrayList<String>();
		for(String id:idss){
			if(id!=null&&!id.equals("")){
				newList.add(id);
				MdmEmType MdmEmType = super.get(id);
				list.add(MdmEmType);
			}
		}
		Map<String,String> resultMap = deleteRulesUtils.checkBeforeDelete(MdmEmType.class,newList);
		if("true".equals(resultMap.get("value"))) {
			List<MdmEmType> finalList = batchDelete(list);
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
