
package com.sgai.property.ctl.service;

import com.sgai.common.persistence.Page;
import com.sgai.common.service.CrudServiceExt;
import com.sgai.property.ctl.dao.CtlCodeTypeDao;
import com.sgai.property.ctl.entity.CtlCodeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import com.sgai.common.utils.DeleteRulesUtils;



    /**
    * @ClassName: CtlCodeTypeService
    * @Description: (基础代码类别表维护service)
    * @author shang
    * @date 2017年11月18日
    * @Company 首自信--智慧城市创新中心
    */

@Service
@Transactional
public class CtlCodeTypeService extends CrudServiceExt<CtlCodeTypeDao, CtlCodeType> {

	@Autowired
	private DeleteRulesUtils deleteRulesUtils;

	public CtlCodeType get(String id) {
		return super.get(id);
	}

	public List<CtlCodeType> findList(CtlCodeType ctlCodeType) {
		return super.findList(ctlCodeType);
	}

	public Page<CtlCodeType> findPage(Page<CtlCodeType> page, CtlCodeType ctlCodeType) {
		return super.findPage(page, ctlCodeType);
	}
		@Transactional(readOnly = false)
	public void save(CtlCodeType ctlCodeType) {

			super.save(ctlCodeType);

	}
	@Transactional(readOnly = false)
	public Map<String, Object> saveType (CtlCodeType ctlCodeType)  {
		Map<String, Object> map= new HashMap<String, Object>();
		if (ctlCodeType.getId() != null && !"".equals(ctlCodeType.getId())) {
			super.save(ctlCodeType);
			map.put("msg", "success");
		}
		else {
			List<CtlCodeType> list = super.findList(ctlCodeType);
			if (list.size() > 0) {
				map.put("msg", "exists");
			}else {

				super.save(ctlCodeType);
				map.put("msg", "success");
			}
		}
			return map;
	}

	@Transactional(readOnly = false)
	public void delete(CtlCodeType ctlCodeType) {
		super.delete(ctlCodeType);
	}


	    /**
	    * @Title: getCodeType
	    * @Description: (从ctl_code_type表中获取全部代码类型)
	    * @param @return    参数
	    * @return List<String>    返回类型   代码类型字符串数组
	    * @throws
	    */

	public  List<String> getCodeType() {
		//  Auto-generated method stub
		return super.dao.getCodeType();
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

			List<CtlCodeType> list = new ArrayList<CtlCodeType>();
			List<String> newList = new ArrayList<String>();
			for(String id:idss){
				if(id!=null&&!id.equals("")){
					newList.add(id);
					CtlCodeType ctlCodeType = super.get(id);
					list.add(ctlCodeType);
				}
			}
			//Map<String,String> resultMap = deleteRulesUtils.checkBeforeDelete(CtlCodeType.class,newList);
			List<CtlCodeType> finalList = batchDelete(list);
			if(finalList.size() > 0) {
				map.put("msg", "success");
			}else {
				map.put("msg", "fail");
			}

			/*if("true".equals(resultMap.get("value"))) {
				List<CtlCodeType> finalList = batchDelete(list);
				if(finalList.size() > 0) {
					map.put("msg", "success");
				}else {
					map.put("msg", "fail");
				}
			}else {
				map.put("msg", resultMap.get("description"));
			}*/
		return map;
		}

}
