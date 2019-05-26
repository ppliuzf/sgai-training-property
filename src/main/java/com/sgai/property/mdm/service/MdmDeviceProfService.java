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
import com.sgai.property.ctl.service.CtlComRuleService;
import com.sgai.property.ctl.service.DeleteRulesUtils;
import com.sgai.property.mdm.dao.MdmDeviceProfDao;
import com.sgai.property.mdm.entity.MdmDeviceProf;

/**
 *
    * ClassName: MdmDeviceProfService
    * com.sgai.property.commonService.vo;(设备专业业务层)
    * @author yangyz
    * Date 2017年11月24日
    * Company 首自信--智慧城市创新中心
 */
@Service
@Transactional
public class MdmDeviceProfService extends CrudServiceExt<MdmDeviceProfDao, MdmDeviceProf> {

	@Autowired
	private DeleteRulesUtils deleteRulesUtils;
	@Autowired
	private CtlComRuleService ctlComRuleService;

	public MdmDeviceProf get(String id) {
		return super.get(id);
	}

	public List<MdmDeviceProf> findList(MdmDeviceProf mdmDeviceProf) {
		return super.findList(mdmDeviceProf);
	}

	public Page<MdmDeviceProf> findPage(Page<MdmDeviceProf> page, MdmDeviceProf mdmDeviceProf) {
		page.setOrderBy("CREATED_DT");
		return super.findPage(page, mdmDeviceProf);
	}

	@Transactional(readOnly = false)
	public void save(MdmDeviceProf mdmDeviceProf) {
		super.save(mdmDeviceProf);
	}

	@Transactional(readOnly = false)
	public void delete(MdmDeviceProf mdmDeviceProf) {
		super.delete(mdmDeviceProf);
	}

	/**
	 *
	 * saveDeviceProf:(添加或者修改保存设备专业数据).
	 * @param mdmDeviceProf
	 * @return :Map<String,Object>
	 * @since JDK 1.8
	 * @author yangyz
	 */
	public Map<String, Object> saveDeviceProf(MdmDeviceProf mdmDeviceProf){
		Map<String, Object> map = new HashMap<String, Object>();
		if (mdmDeviceProf.getId()!=null && !"".equals(mdmDeviceProf.getId())) {
			super.save(mdmDeviceProf);
			map.put("msg", "success");
		}else {
			MdmDeviceProf info = new MdmDeviceProf();
			String code = ctlComRuleService.getNext("DEVICE-PROF");
			info.setProfCode(code);
			List<MdmDeviceProf> list = super.findList(info);
			if (list.size() > 0) {
				map.put("msg", "haveData");
			}else {
				mdmDeviceProf.setProfCode(code);
				super.save(mdmDeviceProf);
				map.put("msg", "success");
			}
		}
		return map;
	}

	/**
	 *
	 * deleteDeviceProfs:(批量删除设备专业数据).
	 * @param ids
	 * @return :Map<String,Object>
	 * @since JDK 1.8
	 * @author yangyz
	 */
	public Map<String, Object> deleteDeviceProfs(String ids){
		Map<String, Object> map = new HashMap<String, Object>();
		String idss[]=ids.split(",");
		List<MdmDeviceProf> list = new ArrayList<MdmDeviceProf>();
		List<String> newList = new ArrayList<String>();
		for(String id:idss){
			if(id!=null&&!id.equals("")){
				newList.add(id);
				MdmDeviceProf info=super.get(id);
				list.add(info);
			}
		}
		Map<String,String> resultMap = deleteRulesUtils.checkBeforeDelete(MdmDeviceProf.class,newList);
		if("true".equals(resultMap.get("value"))) {
			List<MdmDeviceProf> finalList = batchDelete(list);
			if (finalList.size() > 0) {
				map.put("msg", "删除成功!");
			}else {
				map.put("msg", "删除失败！");
			}
			map.put("result", "success");
		}else {
			map.put("msg", resultMap.get("description"));
			map.put("result", "fail");
		}
		return map;
	}
}
