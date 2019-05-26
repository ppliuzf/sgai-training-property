
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
import com.sgai.property.ctl.dao.CtlUserIpDao;
import com.sgai.property.ctl.entity.CtlComp;
import com.sgai.property.ctl.entity.CtlUserIp;

/**
 * 用户IP管理Service
 * @author liushang
 * @version 2017-11-09
 */
@Service
@Transactional
public class CtlUserIpService extends CrudServiceExt<CtlUserIpDao, CtlUserIp> {
	@Autowired
	private DeleteRulesUtils deleteRulesUtils;
	public CtlUserIp get(String id) {
		return super.get(id);
	}

	public List<CtlUserIp> findList(CtlUserIp ctlUserIp) {
		return super.findList(ctlUserIp);
	}

	public Page<CtlUserIp> findPage(Page<CtlUserIp> page, CtlUserIp ctlUserIp) {
		return super.findPage(page, ctlUserIp);
	}

	@Transactional(readOnly = false)
	public void save(CtlUserIp ctlUserIp) {

		super.save(ctlUserIp);
	}

	@Transactional(readOnly = false)
	public void delete(CtlUserIp ctlUserIp) {
		super.delete(ctlUserIp);
	}

		    /**
		    * @Title: getComp
		    * @Description: (从ctl_comp表中获取去全部机构)
		    * @param @return    参数  机构代码字符串数组
		    * @return List<String>    返回类型
		    * @throws
		    */

		public List<String> getComp() {
			//  Auto-generated method stub
			return super.dao.getComp();
		}


			    /**
			     * @param ctlUserIp
			    * @Title: getUser
			    * @Description: (根据选取机构查询用户)
			    * @param @return    参数
			    * @return List<String>    返回类型
			    * @throws
			    */

			public List<CtlUserIp> getUser(CtlUserIp ctlUserIp) {
				//  Auto-generated method stub
				return super.dao.getUser(ctlUserIp);
			}


				    /**
				    * @Title: deleteUserIp
				    * @Description: (批量删除)
				    * @param @param ids
				    * @param @return    参数
				    * @return Map<String,Object>    返回类型
				    * @throws
				    */

				public Map<String, Object> deleteUserIp(String ids) {
					Map<String, Object> map = new HashMap<String, Object>();
					String idss[]=ids.split(",");

					List<CtlUserIp> list = new ArrayList<CtlUserIp>();
					List<String> newList = new ArrayList<String>();
					for(String id:idss){
						if(id!=null&&!id.equals("")){
							newList.add(id);
							CtlUserIp ctlUserIp = super.get(id);
							list.add(ctlUserIp);
						}
					}
					Map<String,String> resultMap = deleteRulesUtils.checkBeforeDelete(CtlUserIp.class,newList);
					if("true".equals(resultMap.get("value"))) {
						List<CtlUserIp> finalList = batchDelete(list);
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

}
