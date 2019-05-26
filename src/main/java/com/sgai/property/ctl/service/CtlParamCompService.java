
package com.sgai.property.ctl.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgai.common.persistence.Page;
import com.sgai.common.service.CrudServiceExt;
import com.sgai.property.ctl.dao.CtlParamCompDao;
import com.sgai.property.ctl.entity.CtlParamComp;

/**
 *
    * @ClassName: CtlParamCompService
    * @Description: (机构参数维护service)
    * @author shang
    * @date 2017年11月18日
    * @Company 首自信--智慧城市创新中心
 */
@Service
@Transactional
public class CtlParamCompService extends CrudServiceExt<CtlParamCompDao, CtlParamComp> {

	public CtlParamComp get(String id) {
		return super.get(id);
	}

	public List<CtlParamComp> findList(CtlParamComp ctlParamComp) {
		return super.findList(ctlParamComp);
	}

	public Page<CtlParamComp> findPage(Page<CtlParamComp> page, CtlParamComp ctlParamComp) {
		return super.findPage(page, ctlParamComp);
	}

	@Transactional(readOnly = false)
	public void save(CtlParamComp ctlParamComp) {

		super.save(ctlParamComp);
	}

	@Transactional(readOnly = false)
	public void delete(CtlParamComp ctlParamComp) {
		super.delete(ctlParamComp);
	}
	public List<CtlParamComp> getComp() {
		//  Auto-generated method stub
		return super.dao.getComp();
	}
	public List<CtlParamComp> getCompByCode(String comCode) {
		return super.dao.getCompByCode(comCode);
	}


	    /**
	    * @Title: getSbs
	    * @Description: (从ctl_modu表中获取全部子系统代码)
	    * @param @return    参数
	    * @return List<String>    返回类型  子系统代码字符串数组
	    * @throws
	    */

	public List<CtlParamComp> getSbs() {
		//  Auto-generated method stub
		return super.dao.getSbs();
	}

	public List<CtlParamComp> getBusiByComCode(String comCode) {
		//  Auto-generated method stub
		return super.dao.getBusiByComCode(comCode);
	}
		    /**
		    * @Title: getSbsList
		    * @Description: (根据机构信息信息从ctl_param_comp查询条目)
		    * @param @param page
		    * @param @param ctlComp
		    * @param @return    参数
		    * @return Page<CtlParamComp>    返回类型  批量查询结果和分页信息
		    * @throws
		    */

		public Page<CtlParamComp> getSbsList(Page<CtlParamComp> page, CtlParamComp ctlComp) {
			//  Auto-generated method stub
			page.setList(super.dao.getSbsList(ctlComp));
			return page;
		}


			    /**
			    * @Title: findLocalList
			    * @Description: (根据子系统代码、机构代码和参数代码查询条目)
			    * @param @param page
			    * @param @param ctlComp
			    * @param @return    参数
			    * @return Page<CtlParamComp>    返回类型  批量查询结果和分页信息
			    * @throws
			    */

			public Page<CtlParamComp> findLocalList(Page<CtlParamComp> page, CtlParamComp ctlComp) {
				ctlComp.setPage(page);
				page.setList(dao.findLocalList(ctlComp));
				return page;
			}

}
