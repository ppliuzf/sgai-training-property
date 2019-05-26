
package com.sgai.property.ctl.service;

import com.sgai.common.persistence.Page;
import com.sgai.common.service.CrudServiceExt;
import com.sgai.property.ctl.dao.CtlCodeDetDao;
import com.sgai.property.ctl.entity.CtlCodeDet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *
    * ClassName: CtlCodeDetService
    * Description: (维护基础代码表ctl_code_det的Service层)
    * @author liushang
    * Date 2017年11月18日
    * Company 首自信--智慧城市创新中心
 */

@Service
@Transactional
public class CtlCodeDetService extends CrudServiceExt<CtlCodeDetDao, CtlCodeDet> {
	@Autowired
	private DeleteRulesUtils deleteRulesUtils;
/**
 *
 *  简单描述该方法的实现功能（可选）.
 * @see CrudServiceExt#get(String)
 */
	public CtlCodeDet get(String id) {
		return super.get(id);
	}
	/**
	 *
	 *  简单描述该方法的实现功能（可选）.
	 * @see CrudServiceExt#findList(com.sgai.common.persistence.BoEntity)
	 */
	public List<CtlCodeDet> findList(CtlCodeDet ctlCodeDet) {
		return super.findList(ctlCodeDet);
	}


	/**
	 *
	 *  简单描述该方法的实现功能（可选）.
	 * @see CrudServiceExt#findPage(Page, com.sgai.common.persistence.BoEntity)
	 */
	public Page<CtlCodeDet> findPage(Page<CtlCodeDet> page, CtlCodeDet ctlCodeDet) {
		return super.findPage(page, ctlCodeDet);
	}


	/**
	 * findCodeDetForSpace:(空间用的主数据).
	 * @param map
	 * @return :List<CtlCodeDet>
	 * @since JDK 1.8
	 * @author admin
	 */
	public List<CtlCodeDet> findCodeDetForSpace(Map<String,String> map){
		return dao.findCodeDetForSpace(map);
	}



	/**
	 *
	 *  简单描述该方法的实现功能（可选）.
	 * @see CrudServiceExt#save(com.sgai.common.persistence.BoEntity)
	 */
	@Transactional(readOnly = false)
	public void save(CtlCodeDet ctlCodeDet) {
			super.save(ctlCodeDet);
			//CacheChannel cache = J2Cache.getChannel();
			//cache.evict("CtlCodeDet", ctlCodeDet.getCodeType());
	}

	@Transactional(readOnly = false)
	public Map<String, Object> saveDet (CtlCodeDet ctlCodeDet)  {

		Map<String, Object> map= new HashMap<String, Object>();
		if (ctlCodeDet.getId() != null && !"".equals(ctlCodeDet.getId())) {
			super.save(ctlCodeDet);
			map.put("msg", "success");
		}
		else {
			List<CtlCodeDet> list = super.findList(ctlCodeDet);
			if (list.size() > 0) {
				map.put("msg", "exists");
			}else {

				super.save(ctlCodeDet);
				map.put("msg", "success");
			}
		}
			return map;
	}
	/**
	 *
	 *  简单描述该方法的实现功能（可选）.
	 * @see CrudServiceExt#delete(com.sgai.common.persistence.BoEntity)
	 */
	@Transactional(readOnly = false)
	public void delete(CtlCodeDet ctlCodeDet) {
		super.delete(ctlCodeDet);
	}


	/**
	 *
	 * getCodeType:(这里用一句话描述这个方法的作用).
	 * @return :List<String>
	 * @since JDK 1.8
	 * @author liushang
	 */
	public List<String> getCodeType() {
		//  Auto-generated method stub
		return super.dao.getCodeType();
	}

	    /**
	    * @Title: deleteUserIp
	    * @Description: (这里用一句话描述这个方法的作用)
	    * @param @param ids
	    * @param @return    参数
	    * @return Map<String,Object>    返回类型
	    * @throws
	    */

	public Map<String, Object> deleteUserIp(String ids) {
		Map<String, Object> map = new HashMap<String, Object>();
		String idss[]=ids.split(",");

		List<CtlCodeDet> list = new ArrayList<CtlCodeDet>();
		List<String> newList = new ArrayList<String>();
		for(String id:idss){
			if(id!=null&&!id.equals("")){
				newList.add(id);
				CtlCodeDet ctlCodeDet = super.get(id);
				list.add(ctlCodeDet);
			}
		}
		Map<String,String> resultMap = deleteRulesUtils.checkBeforeDelete(CtlCodeDet.class,newList);
		if("true".equals(resultMap.get("value"))) {
			List<CtlCodeDet> finalList = batchDelete(list);
			if(finalList.size() > 0) {
				map.put("msg", "success");
			}else {
				map.put("msg", "fail");
			}
		}else {
			map.put("msg", resultMap.get("description"));
		}
	return map;
	}

	/**
	 *
	 * getCodeDet:(根据特定条件查找字典表数据).
	 * @param ctlCodeDet
	 * @return :List<CtlCodeDet>
	 * @since JDK 1.8
	 * @author 王天尧
	 */
	public CtlCodeDet getCodeDet(CtlCodeDet ctlCodeDet){
		return dao.getCodeDet(ctlCodeDet);
	}
}
