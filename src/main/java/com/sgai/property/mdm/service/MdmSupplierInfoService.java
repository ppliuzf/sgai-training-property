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
import com.sgai.property.ctl.service.DeleteRulesUtils;
import com.sgai.property.mdm.dao.MdmSupplierInfoDao;
import com.sgai.property.mdm.entity.MdmSupplierInfo;

/**
 * 供应商数据Service
 * @author liushang
 * @version 2017-11-24
 */
@Service
@Transactional
public class MdmSupplierInfoService extends CrudServiceExt<MdmSupplierInfoDao, MdmSupplierInfo> {


	@Autowired
	private DeleteRulesUtils deleteRulesUtils;

	public MdmSupplierInfo get(String id) {
		return super.get(id);
	}

	public List<MdmSupplierInfo> findList(MdmSupplierInfo mdmSupplierInfo) {
		return super.findList(mdmSupplierInfo);
	}


	public Page<MdmSupplierInfo> findPage(Page<MdmSupplierInfo> page, MdmSupplierInfo mdmSupplierInfo) {
		return super.findPage(page, mdmSupplierInfo);
	}

	@Transactional(readOnly = false)
	public void save(MdmSupplierInfo mdmSupplierInfo) {
		super.save(mdmSupplierInfo);
	}

	@Transactional(readOnly = false)
	public Map<String, Object> delete(String ids) {
		Map<String, Object> map = new HashMap<String, Object>();
		String idss[]=ids.split(",");

		List<MdmSupplierInfo> list = new ArrayList<MdmSupplierInfo>();
		List<String> newList = new ArrayList<String>();
		for(String id:idss){
			if(id!=null&&!id.equals("")){
				newList.add(id);
				MdmSupplierInfo mdmSupplierInfo = super.get(id);
				list.add(mdmSupplierInfo);
			}
		}
		Map<String,String> resultMap = deleteRulesUtils.checkBeforeDelete(MdmSupplierInfo.class,newList);
		if("true".equals(resultMap.get("value"))) {
			List<MdmSupplierInfo> finalList = batchDelete(list);
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
	 *
	 * getSuppByCode:(根据供应商编码查询供应商信息).
	 * @param mdmSupplierInfo
	 * @return :MdmSupplierInfo
	 * @since JDK 1.8
	 * @author yangyz
	 */
	public MdmSupplierInfo getSuppByCode(MdmSupplierInfo mdmSupplierInfo) {
		mdmSupplierInfo.preGet();
		return dao.getSuppByCode(mdmSupplierInfo);
	}


}
