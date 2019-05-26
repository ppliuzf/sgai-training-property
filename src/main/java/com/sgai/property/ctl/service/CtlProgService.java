package com.sgai.property.ctl.service;

import com.sgai.common.persistence.Page;
import com.sgai.common.service.CrudServiceExt;
import com.sgai.property.ctl.dao.CtlProgDao;
import com.sgai.property.ctl.entity.CtlProg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
    * ClassName: CtlProgService  
    * Description: (功能Service)
    * @author yangyz  
    * Date 2017年11月18日  
    * Company 首自信--智慧城市创新中心
 */
@Service
@Transactional
public class CtlProgService extends CrudServiceExt<CtlProgDao, CtlProg> {

	@Autowired
	private DeleteRulesUtils deleteRulesUtils;
	
	public CtlProg get(String id) {
		return super.get(id);
	}
	
	public List<CtlProg> findList(CtlProg ctlProg) {
		return super.findList(ctlProg);
	}
	
	public Page<CtlProg> findPage(Page<CtlProg> page, CtlProg ctlProg) {
		return super.findPage(page, ctlProg);
	}
	
	@Transactional(readOnly = false)
	public void save(CtlProg ctlProg) {
		super.save(ctlProg);
	}
	
	@Transactional(readOnly = false)
	public void delete(CtlProg ctlProg) {
		super.delete(ctlProg);
	}

	/**
	 * 
	 * findCtlProg:(查询功能列表).
	 * @param arg0
	 * @param arg1
	 * @return :List<CtlProg> 
	 * @since JDK 1.8
	 * @author yangyz
	 */
	public List<CtlProg>  findCtlProg(String arg0,String arg1){
		return dao.findCtlProg(arg0, arg1);
	}
	
	/**
	 * 
	 * deleteProg:(删除功能项).
	 * @param ids
	 * @return :Map<String,Object> 
	 * @since JDK 1.8
	 * @author yangyz
	 */
	public Map<String, Object> deleteProg(String ids){
		Map<String, Object> map = new HashMap<String, Object>(); 
		String idss[]=ids.split(",");
		List<CtlProg> list = new ArrayList<CtlProg>();
		List<String> newList = new ArrayList<String>();
		for(String id:idss){
			if(id!=null&&!id.equals("")){
				newList.add(id);
				CtlProg ctlProg=super.get(id);
				list.add(ctlProg);
			}
		}
		Map<String,String> resultMap = deleteRulesUtils.checkBeforeDelete(CtlProg.class,newList);
		if("true".equals(resultMap.get("value"))) {
			List<CtlProg> finalList = batchDelete(list);
			if(finalList.size() > 0) {
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
	
	public Map<String,Object> saveProg(CtlProg ctlProg){
		Map<String,Object> result = new HashMap<String,Object>();
		if (ctlProg.getId()!=null && !"".equals(ctlProg.getId())) {
			super.save(ctlProg);		
			result.put("msg", "success");
		}else {
			CtlProg info = new CtlProg();
			info.setProgCode(ctlProg.getProgCode());
			List<CtlProg> list = super.findList(info);
			if (list.size()>0) {
				result.put("msg", "havaData");
			}else {
				super.save(ctlProg);		
				result.put("msg", "success");
			}
		}
		return result;
	} 
}