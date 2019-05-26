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
import com.sgai.property.mdm.dao.MdmDevicesModelDao;
import com.sgai.property.mdm.dto.MdmDevicesModelVo;
import com.sgai.property.mdm.entity.MdmDevicesModel;

/**
 * 
    * ClassName: MdmDevicesModelService  
    * com.sgai.property.commonService.vo;(设备型号业务层)
    * @author yangyz  
    * Date 2017年11月24日  
    * Company 首自信--智慧城市创新中心
 */
@Service
@Transactional
public class MdmDevicesModelService extends CrudServiceExt<MdmDevicesModelDao, MdmDevicesModel> {

	@Autowired
	private DeleteRulesUtils deleteRulesUtils;
	@Autowired
	private CtlComRuleService ctlComRuleService;
	
	public MdmDevicesModel get(String id) {
		return super.get(id);
	}
	public MdmDevicesModelVo getGevicesModelVo(String id) {
		return dao.getDevicesModelVo(id);//getDevicesModelVo
	}
	public List<MdmDevicesModel> findList(MdmDevicesModel mdmDevicesModel) {
		return super.findList(mdmDevicesModel);
	}
	
	public Page<MdmDevicesModel> findPage(Page<MdmDevicesModel> page, MdmDevicesModel mdmDevicesModel) {
		return super.findPage(page, mdmDevicesModel);
	}
	
	@Transactional(readOnly = false)
	public void save(MdmDevicesModel mdmDevicesModel) {
		super.save(mdmDevicesModel);
	}
	
	@Transactional(readOnly = false)
	public void delete(MdmDevicesModel mdmDevicesModel) {
		super.delete(mdmDevicesModel);
	}
	
	/**
	 * 
	 * saveDevicesModel:(新增或修改保存设备型号信息).
	 * @param mdmDevicesModel
	 * @param oldModelCode
	 * @return :Map<String,Object> 
	 * @since JDK 1.8
	 * @author yangyz
	 */
	public Map<String, Object> saveDevicesModel(MdmDevicesModel mdmDevicesModel){
		Map<String, Object> map = new HashMap<String, Object>();
		if (mdmDevicesModel.getId()!=null && !"".equals(mdmDevicesModel.getId())) {
			super.save(mdmDevicesModel);
			map.put("msg", "success");
		}else {
			MdmDevicesModel info = new MdmDevicesModel();
			String code = ctlComRuleService.getNext("DEVICE-MODEL");
			info.setModelCode(code);
			List<MdmDevicesModel> list = super.findList(info);
			if (list.size() > 0) {
				map.put("msg", "haveData");
			}else {
				mdmDevicesModel.setModelCode(code);
				super.save(mdmDevicesModel);
				map.put("msg", "success");
			}
		}
		
		return map;
	}
	
	/**
	 * 
	 * deleteDevicesModel:(批量删除设备型号数据).
	 * @param ids
	 * @return :Map<String,Object> 
	 * @since JDK 1.8
	 * @author yangyz
	 */
	public Map<String, Object> deleteDevicesModel(String ids){
		Map<String, Object> map = new HashMap<String, Object>(); 
		String idss[]=ids.split(",");
		List<MdmDevicesModel> list = new ArrayList<MdmDevicesModel>();
		List<String> newList = new ArrayList<String>();
		for(String id:idss){
			if(id!=null&&!id.equals("")){
				newList.add(id);
				MdmDevicesModel info=super.get(id);
				list.add(info);
			}
		}
		Map<String,String> resultMap = deleteRulesUtils.checkBeforeDelete(MdmDevicesModel.class,newList);
		if("true".equals(resultMap.get("value"))) {
			List<MdmDevicesModel> finalList = batchDelete(list);
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
	 * getDevicesModel:(根据实体查询设备型号，可传参数).
	 * @param mdmDevicesModel
	 * @return :MdmDevicesModel 
	 * @since JDK 1.8
	 * @author yangyz
	 */
	public MdmDevicesModel getDevicesModel(MdmDevicesModel mdmDevicesModel) {
		mdmDevicesModel.preGet();
		return dao.getDevicesModel(mdmDevicesModel);
	}
	
}