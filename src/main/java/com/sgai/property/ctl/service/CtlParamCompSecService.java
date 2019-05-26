package com.sgai.property.ctl.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sgai.common.persistence.Page;
import com.sgai.common.service.CrudServiceExt;
import com.sgai.property.ctl.dao.CtlParamCompSecDao;
import com.sgai.property.ctl.entity.CtlParamCompSec;

   /**
* ClassName: CtlParamCompSecService
* Description: (这里用一句话描述这个类的作用)
* @author admin
* Date 2017年11月18日
* Company 首自信--智慧城市创新中心
*/
@Service
@Transactional
public class CtlParamCompSecService extends CrudServiceExt<CtlParamCompSecDao, CtlParamCompSec> {

	public CtlParamCompSec get(String id) {
		return super.get(id);
	}

	public List<CtlParamCompSec> findList(CtlParamCompSec ctlParamComp) {
		return super.findList(ctlParamComp);
	}

	public Page<CtlParamCompSec> findPage(Page<CtlParamCompSec> page, CtlParamCompSec ctlParamComp) {
		return super.findPage(page, ctlParamComp);
	}

	@Transactional(readOnly = false)
	public void save(CtlParamCompSec ctlParamComp) {
		super.save(ctlParamComp);
	}

	@Transactional(readOnly = false)
	public void delete(CtlParamCompSec ctlParamComp) {
		super.delete(ctlParamComp);
	}

	/**
	 * getModuList:(这里用一句话描述这个方法的作用).
	 * @return :List<Map<String,Object>>
	 * @since JDK 1.8
	 * @author admin
	 */
	public List<Map<String,Object>> getModuList(){
		List<Map<String,Object>> result = Lists.newArrayList();
		List<Map<String,Object>> list = dao.getModuList();
		for(Map<String,Object> map : list) {
			Map<String,Object> newMap = Maps.newHashMap();
			newMap.put("id", map.get("ID"));
			newMap.put("pId", map.get("PID"));
			newMap.put("name", map.get("ID"));
			newMap.put("plevel", map.get("PLEVEL"));
			result.add(newMap);
		}
		return result;
	}

}
