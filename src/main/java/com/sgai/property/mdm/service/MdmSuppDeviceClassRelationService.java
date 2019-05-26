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
import com.sgai.property.mdm.dao.MdmSuppDeviceClassRelationDao;
import com.sgai.property.mdm.entity.MdmSuppDeviceClassRelation;
import com.sgai.property.mdm.entity.MdmSuppMatClassRelation;

/**
 * 供应商设备型号关联表Service
 * @author liushang
 * @version 2017-11-27
 */
@Service
@Transactional
public class MdmSuppDeviceClassRelationService extends CrudServiceExt<MdmSuppDeviceClassRelationDao, MdmSuppDeviceClassRelation> {

	@Autowired
	private DeleteRulesUtils deleteRulesUtils;
	public MdmSuppDeviceClassRelation get(String id) {
		return super.get(id);
	}

	public List<MdmSuppDeviceClassRelation> findList(MdmSuppDeviceClassRelation mdmSuppDeviceClassRelation) {
		return super.findList(mdmSuppDeviceClassRelation);
	}

	public Page<MdmSuppDeviceClassRelation> findPage(Page<MdmSuppDeviceClassRelation> page, MdmSuppDeviceClassRelation mdmSuppDeviceClassRelation) {
		return super.findPage(page, mdmSuppDeviceClassRelation);
	}

	@Transactional(readOnly = false)
	public void save(MdmSuppDeviceClassRelation mdmSuppDeviceClassRelation) {
		super.save(mdmSuppDeviceClassRelation);
	}

	@Transactional(readOnly = false)
	public void delete(MdmSuppDeviceClassRelation mdmSuppDeviceClassRelation) {
		super.delete(mdmSuppDeviceClassRelation);
	}
	@Transactional(readOnly = false)
	public Map<String, Object> delete(String ids) {
		Map<String, Object> map = new HashMap<String, Object>();
		String idss[]=ids.split(",");

		List<MdmSuppDeviceClassRelation> list = new ArrayList<MdmSuppDeviceClassRelation>();
		List<String> newList = new ArrayList<String>();
		for(String id:idss){
			if(id!=null&&!id.equals("")){
				newList.add(id);
				MdmSuppDeviceClassRelation mdmSuppDeviceClassRelation = super.get(id);
				list.add(mdmSuppDeviceClassRelation);
			}
		}
		Map<String,String> resultMap = deleteRulesUtils.checkBeforeDelete(MdmSuppDeviceClassRelation.class,newList);
		if("true".equals(resultMap.get("value"))) {
			List<MdmSuppDeviceClassRelation> finalList = batchDelete(list);
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

	public Page<MdmSuppDeviceClassRelation> findRestPage(Page<MdmSuppDeviceClassRelation> page,
			MdmSuppDeviceClassRelation mdmSuppDeviceClassRelation) {

		mdmSuppDeviceClassRelation.setPage(page);
		mdmSuppDeviceClassRelation.preGet();
		page.setList(super.dao.findRestPage(mdmSuppDeviceClassRelation));
		return page;

	}

	/**
	 *
	 * findPageByModelCode:(根据设备型号查询供应商).
	 * @param page
	 * @param mdmSuppDeviceClassRelation
	 * @return :Page<MdmSuppDeviceClassRelation>
	 * @since JDK 1.8
	 * @author yangyz
	 */
	public Page<MdmSuppDeviceClassRelation> findPageByModelCode(Page<MdmSuppDeviceClassRelation> page, MdmSuppDeviceClassRelation mdmSuppDeviceClassRelation) {
		mdmSuppDeviceClassRelation.setPage(page);
		mdmSuppDeviceClassRelation.preGet();
		page.setList(dao.findListByModelCode(mdmSuppDeviceClassRelation));
		return page;
	}
}
