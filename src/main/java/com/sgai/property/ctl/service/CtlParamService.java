
package com.sgai.property.ctl.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgai.common.persistence.Page;
import com.sgai.common.service.CrudServiceExt;
//import com.sgai.common.utils.DeleteRulesUtils;
import com.sgai.property.ctl.dao.CtlParamDao;
import com.sgai.property.ctl.entity.CtlComp;
import com.sgai.property.ctl.entity.CtlParam;


    /**
    * @ClassName: CtlParamService
    * @Description: (系统参数定义Service)
    * @author shang
    * @date 2017年11月18日
    * @Company 首自信--智慧城市创新中心
    */

@Service
@Transactional
public class CtlParamService extends CrudServiceExt<CtlParamDao, CtlParam> {

	@Autowired
	private DeleteRulesUtils deleteRulesUtils;

	public CtlParam get(String id) {
		return super.get(id);
	}


	public List<CtlParam> findList(CtlParam ctlParam) {
		return super.findList(ctlParam);
	}

	public Page<CtlParam> findPage(Page<CtlParam> page, CtlParam ctlParam) {
		ctlParam.setPage(page);
		List<CtlParam> p= super.findList(ctlParam);
		page.setList(p);
		return page;
	}
	/**
	 *
	    * @Title: getCodeType
	    * @Description: (从ctl_code_det表中获取值类型全部信息用作选项)
	    * @param @return    参数  值类型字符串数组
	    * @return List<String>    返回类型
	    * @throws
	 */
	public List<String> getCodeType(){
		return super.dao.getCodeType();
	}


	@Transactional(readOnly = false)
	public void save(CtlParam ctlParam) {

			super.save(ctlParam);

	}

	@Transactional(readOnly = false)
	public void delete(CtlParam ctlParam) {
		super.delete(ctlParam);
	}



	    /**
	    * @Title: deleteParam
	    * @Description: (这里用一句话描述这个方法的作用)
	    * @param @param ids
	    * @param @return    参数
	    * @return Map<String,Object>    返回类型
	    * @throws
	    */

	public Map<String, Object> deleteParam(String ids) {
		Map<String, Object> map = new HashMap<String, Object>();
		String idss[]=ids.split(",");

		List<CtlParam> list = new ArrayList<CtlParam>();
		List<String> newList = new ArrayList<String>();
		for(String id:idss){
			if(id!=null&&!id.equals("")){
				newList.add(id);
				CtlParam ctlParam = super.get(id);
				list.add(ctlParam);
			}
		}
		Map<String,String> resultMap = deleteRulesUtils.checkBeforeDelete(CtlParam.class,newList);
		if("true".equals(resultMap.get("value"))) {
			List<CtlParam> finalList = batchDelete(list);
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
