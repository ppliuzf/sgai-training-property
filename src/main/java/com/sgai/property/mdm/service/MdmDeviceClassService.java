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
import com.sgai.property.mdm.dao.MdmDeviceClassDao;
import com.sgai.property.mdm.entity.MdmDeviceClass;

/**
 *
    * ClassName: MdmDeviceClassService
    * com.sgai.property.commonService.vo;(设备类型业务层)
    * @author yangyz
    * Date 2017年11月24日
    * Company 首自信--智慧城市创新中心
 */
@Service
@Transactional
public class MdmDeviceClassService extends CrudServiceExt<MdmDeviceClassDao, MdmDeviceClass> {

	@Autowired
	private DeleteRulesUtils deleteRulesUtils;
	@Autowired
	private CtlComRuleService ctlComRuleService;

	public MdmDeviceClass get(String id) {
		return super.get(id);
	}

	public List<MdmDeviceClass> findList(MdmDeviceClass mdmDeviceClass) {
		return super.findList(mdmDeviceClass);
	}

	public Page<MdmDeviceClass> findPage(Page<MdmDeviceClass> page, MdmDeviceClass mdmDeviceClass) {
		return super.findPage(page, mdmDeviceClass);
	}

	@Transactional(readOnly = false)
	public void save(MdmDeviceClass mdmDeviceClass) {
		super.save(mdmDeviceClass);
	}

	@Transactional(readOnly = false)
	public void delete(MdmDeviceClass mdmDeviceClass) {
		super.delete(mdmDeviceClass);
	}

	/**
	 *
	 * saveDeviceClass:(新增或修改保存设备类型数据).
	 * @param mdmDeviceClass
	 * @return :Map<String,Object>
	 * @since JDK 1.8
	 * @author yangyz
	 */
	public Map<String, Object> saveDeviceClass(MdmDeviceClass mdmDeviceClass){
		Map<String, Object> map = new HashMap<String, Object>();
		if (mdmDeviceClass.getId()!=null && !"".equals(mdmDeviceClass.getId())) {
			super.save(mdmDeviceClass);
			map.put("msg", "success");
		}else {
			MdmDeviceClass info = new MdmDeviceClass();
			String code = ctlComRuleService.getNext("DEVICE-CLASS");
			info.setClassCode(code);
			List<MdmDeviceClass> list = super.findList(info);
			if (list.size() > 0) {
				map.put("msg", "haveData");
			}else {
				mdmDeviceClass.setClassCode(code);
				super.save(mdmDeviceClass);
				map.put("msg", "success");
			}
		}

		return map;
	}

	/**
	 *
	 * deleteDeviceClass:(批量删除设备类型).
	 * @param ids
	 * @return :Map<String,Object>
	 * @since JDK 1.8
	 * @author lenovo
	 */
	public Map<String, Object> deleteDeviceClass(String ids){
		Map<String, Object> map = new HashMap<String, Object>();
		String idss[]=ids.split(",");
		List<MdmDeviceClass> list = new ArrayList<MdmDeviceClass>();
		List<String> newList = new ArrayList<String>();
		for(String id:idss){
			if(id!=null&&!id.equals("")){
				newList.add(id);
				MdmDeviceClass info=super.get(id);
				list.add(info);
			}
		}
		Map<String,String> resultMap = deleteRulesUtils.checkBeforeDelete(MdmDeviceClass.class,newList);
		if("true".equals(resultMap.get("value"))) {
			List<MdmDeviceClass> finalList = batchDelete(list);
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

	/**
	 *
	 * getDeviceClass:(根据实体查询设备分类，可传参数).
	 * @param mdmDeviceClass
	 * @return :MdmDeviceClass
	 * @since JDK 1.8
	 * @author yangyz
	 */
	public MdmDeviceClass getDeviceClass(MdmDeviceClass mdmDeviceClass) {
		mdmDeviceClass.preGet();
		return dao.getDeviceClass(mdmDeviceClass);
	}

}
