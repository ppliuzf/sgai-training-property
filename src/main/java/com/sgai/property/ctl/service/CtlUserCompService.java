package com.sgai.property.ctl.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgai.common.persistence.Page;
import com.sgai.common.service.CrudServiceExt;
import com.sgai.property.ctl.dao.CtlAllocMenuDao;
import com.sgai.property.ctl.dao.CtlUserCompDao;
import com.sgai.property.ctl.entity.CtlAllocMenu;
import com.sgai.property.ctl.entity.CtlComp;
import com.sgai.property.ctl.entity.CtlUser;
import com.sgai.property.ctl.entity.CtlUserComp;

/**
 *
    * ClassName: CtlUserCompService
    * Description: (定义用户关联机构service)
    * @author 王天尧
    * Date 2017年11月21日
    * Company 首自信--智慧城市创新中心
 */
@Service
@Transactional
public class CtlUserCompService extends CrudServiceExt<CtlUserCompDao, CtlUserComp> {
	@Autowired
	private CtlUserService ctlUserService;
	@Autowired
	private CtlCompService ctlCompService;
	@Autowired
	private CtlAllocMenuService ctlAllocMenuService;
	@Autowired
	private CtlAllocMenuDao ctlAllocMenuDao;
	public CtlUserComp get(String id) {
		return super.get(id);
	}

	public List<CtlUserComp> findList(CtlUserComp ctlUserComp) {
		return super.findList(ctlUserComp);
	}

	public Page<CtlUserComp> findPage(Page<CtlUserComp> page, CtlUserComp ctlUserComp) {
		return super.findPage(page, ctlUserComp);
	}

	@Transactional(readOnly = false)
	public void save(CtlUserComp ctlUserComp) {
		super.save(ctlUserComp);
	}

	@Transactional(readOnly = false)
	public void delete(CtlUserComp ctlUserComp) {
		super.delete(ctlUserComp);
	}
	/**
	 *
	 * saveUserCom:(用户与机构关联).
	 * @param ctlUserComp
	 * @param userIds 用户id集合
	 * @param compIds 机构id集合
	 * @param map
	 * @return :Map<String,String>
	 * @since JDK 1.8
	 * @author 王天尧
	 */
	@Transactional(readOnly = false)
	public Map<String,String> saveUserCom(CtlUserComp ctlUserComp,String userCode,String compIds,String oldComCode,Map<String,String> map){
		//先把用户id和等级维护id拆分出来
		String[] comCodes= compIds.split(",");
		//如果compId等于空，删除ctl_user_comp对应机构代码记录。同时从ctl_alloc_menu表，清除用户对应菜单分配记录。
		if(compIds.equals("")){
			ctlUserComp.setUserCode(userCode);
			delete(ctlUserComp);
			CtlUser ctlUserNew = new CtlUser();
			ctlUserNew.setUserCode(userCode);
			List<CtlUser> ctlUser = ctlUserService.findCompUserList(ctlUserNew);
			ctlUser.get(0).setComCode("");
			ctlUserService.save(ctlUser.get(0));
			CtlAllocMenu ctlAllocMenu = new CtlAllocMenu();
			ctlAllocMenu.setCorrCode(userCode);
			ctlAllocMenu.setComCode(oldComCode);
			ctlAllocMenuDao.deleteByCode(ctlAllocMenu);
			map.put("msg", "success");
		}else {
			//将用户与机构代码关联
			String comCode = comCodes[0];
			if(!(comCode.equals(oldComCode))) {
				CtlUserComp ctlUserCompNew = new CtlUserComp();
				CtlAllocMenu ctlAllocMenu = new CtlAllocMenu();
				ctlAllocMenu.setCorrCode(userCode);
				ctlAllocMenu.setComCode(oldComCode);
				ctlAllocMenuDao.deleteByCode(ctlAllocMenu);
				ctlUserCompNew.setComCode(oldComCode);
				ctlUserCompNew.setUserCode(userCode);
				delete(ctlUserCompNew);
				ctlUserComp.setComCode(comCode);
				ctlUserComp.setUserCode(userCode);
				save(ctlUserComp);
				CtlUser ctlUserNew = new CtlUser();
				ctlUserNew.setUserCode(userCode);
				List<CtlUser> ctlUser = ctlUserService.findList(ctlUserNew);
				ctlUser.get(0).setComCode(comCode);
				ctlUserService.save(ctlUser.get(0));
				map.put("msg", "success");
			}else {
				map.put("msg", "success");
			}

		}
		return map;
	}
	/**
	 *
	 * getUserCompList:(获取用户所关联的机构).
	 * @param ctlUserComp
	 * @param userIds 用户id
	 * @return :List<CtlUserComp>
	 * @since JDK 1.8
	 * @author 王天尧
	 */
	public List<CtlUserComp> getUserCompList(CtlUserComp ctlUserComp,String userCode) {
		ctlUserComp.setUserCode(userCode);
		List<CtlUserComp> ctlUserCompList =findList(ctlUserComp);
		return ctlUserCompList;
	}
	/**
	 *
	 * findLackList:(获得该机构管理员已拥有的机构以及未拥有的机构，不包括别的机构管理员已选择的机构).
	 * @param ctlUserComp
	 * @return :Page<CtlUserComp>
	 * @since JDK 1.8
	 * @author 王天尧
	 */
	public Page<CtlComp> findLackList(CtlComp ctlComp,Page<CtlComp> page){
		ctlComp.setPage(page);
		page.setList(dao.findLackList(ctlComp));
		return page;
	}
}
