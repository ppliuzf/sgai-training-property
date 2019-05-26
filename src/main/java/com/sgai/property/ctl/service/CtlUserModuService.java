package com.sgai.property.ctl.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgai.common.persistence.Page;
import com.sgai.common.service.CrudServiceExt;
import com.sgai.property.ctl.dao.CtlAllocMenuDao;
import com.sgai.property.ctl.dao.CtlUserModuDao;
import com.sgai.property.ctl.entity.CtlAllocMenu;
import com.sgai.property.ctl.entity.CtlComp;
import com.sgai.property.ctl.entity.CtlModu;
import com.sgai.property.ctl.entity.CtlUser;
import com.sgai.property.ctl.entity.CtlUserComp;
import com.sgai.property.ctl.entity.CtlUserModu;
import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.modules.login.support.UserServletContext;

/**
 * 用户模块关系Service
 * @author admin
 * @version 2018-03-28
 */
@Service
@Transactional
public class CtlUserModuService extends CrudServiceExt<CtlUserModuDao, CtlUserModu> {
	@Autowired
	private CtlUserService ctlUserService;
	@Autowired
	private CtlAllocMenuDao ctlAllocMenuDao;
	public CtlUserModu get(String id) {
		return super.get(id);
	}

	public List<CtlUserModu> findList(CtlUserModu ctlUserModu) {
		return super.findList(ctlUserModu);
	}

	public Page<CtlUserModu> findPage(Page<CtlUserModu> page, CtlUserModu ctlUserModu) {
		return super.findPage(page, ctlUserModu);
	}

	@Transactional(readOnly = false)
	public void save(CtlUserModu ctlUserModu) {
		super.save(ctlUserModu);
	}

	@Transactional(readOnly = false)
	public void delete(CtlUserModu ctlUserModu) {
		super.delete(ctlUserModu);
	}
	/**
	 *
	    * @Title: saveUserModu
	    * @Description: (保存用户和模块的关系)
	    * @param @param ctlUserModu
	    * @param @param userCode
	    * @param @param moduIds
	    * @param @param oldModuCode
	    * @param @param map
	    * @param @return    参数
	    * @return Map<String,String>    返回类型
	    * @throws
	 */
	@Transactional(readOnly = false)
	public Map<String,String> saveUserModu(CtlUserModu ctlUserModu,
			String userCode,String moduIds,
			String oldModuCode,
			Map<String,String> map
			){
		//先把用户id和等级维护id拆分出来
		LoginUser user = UserServletContext.getUserInfo();

		String[] moduCodes= moduIds.split(",");
		if(moduIds.equals("")){
			ctlUserModu.setUserCode(userCode);
			List<CtlUserModu> findList = findList(ctlUserModu);
			if(findList.size()>0) {
				delete(findList.get(0));
			}
		    CtlUser ctlUserNew = new CtlUser();
			ctlUserNew.setUserCode(userCode);
			ctlUserNew.setUserType("MO");
			List<CtlUser> ctlUser = ctlUserService.findList(ctlUserNew);
			CtlUser ctlUser2 = ctlUser.get(0);
			ctlUser2.setModuCode("");
			ctlUserService.save(ctlUser2);
			CtlAllocMenu ctlAllocMenu = new CtlAllocMenu();
			ctlAllocMenu.setCorrCode(userCode);
			ctlAllocMenu.setComCode(user.getComCode());
			ctlAllocMenuDao.deleteByCode(ctlAllocMenu);
			map.put("msg", "success");
		}else {
			//将用户与模块代码关联
			String moduCode = moduCodes[0];
			if(!(moduCode.equals(oldModuCode))) {
				CtlAllocMenu ctlAllocMenu = new CtlAllocMenu();
				ctlAllocMenu.setCorrCode(userCode);
				ctlAllocMenu.setComCode(user.getComCode());
				ctlAllocMenuDao.deleteByCode(ctlAllocMenu);
				CtlUserModu ctlUserModuNew = new CtlUserModu();
				ctlUserModuNew.setModuCode(oldModuCode);
				ctlUserModuNew.setUserCode(userCode);
				List<CtlUserModu> findList = findList(ctlUserModuNew);
				if(findList.size()>0) {
					delete(findList.get(0));
				}
				ctlUserModu.setModuCode(moduCode);
				ctlUserModu.setUserCode(userCode);
				save(ctlUserModu);
				CtlUser ctlUserNew = new CtlUser();
				ctlUserNew.setUserCode(userCode);
				ctlUserNew.setComCode(user.getComCode());
				ctlUserNew.setModuCode(user.getModuCode());
				ctlUserNew.setUserType("MO");
				List<CtlUser> ctlUser = ctlUserService.findCompGLUserList(ctlUserNew);
				CtlUser ctlUser2 = ctlUser.get(0);
				ctlUser2.setModuCode(moduCode);
				ctlUserService.save(ctlUser2);
				map.put("msg", "success");
			}else {
				map.put("msg", "success");
			}

		}
		return map;
	}
	/**
	 *
	    * @Title: getUserCompList
	    * @Description: (查找模块管理员关联的模块)
	    * @param @param ctlUserModu
	    * @param @param userCode
	    * @param @return    参数
	    * @return List<CtlUserModu>    返回类型
	    * @throws
	 */
	public List<CtlUserModu> getUserModuList(CtlUserModu ctlUserModu,String userCode) {
		ctlUserModu.setUserCode(userCode);
		List<CtlUserModu> ctlUserModuList =findList(ctlUserModu);
		return ctlUserModuList;
	}
	/**
	 *
	    * @Title: findLackList
	    * @Description: (查询已拥有的模块和未被他人选择的模块)
	    * @param @param ctlModu
	    * @param @param page
	    * @param @return    参数
	    * @return Page<CtlModu>    返回类型
	    * @throws
	 */
	public Page<CtlModu> findLackList(CtlModu ctlModu,Page<CtlModu> page){
		ctlModu.setPage(page);
		page.setList(dao.findLackList(ctlModu));
		return page;
	}
}
