package com.sgai.property.ctl.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sgai.common.persistence.Page;
import com.sgai.common.service.CrudServiceExt;
import com.sgai.property.ctl.dao.CtlComBusiRelationDao;
import com.sgai.property.ctl.entity.CtlBusinessDefine;
import com.sgai.property.ctl.entity.CtlComBusiRelation;
import com.sgai.property.ctl.entity.CtlComp;
import com.sgai.property.ctl.entity.CtlCompMenu;

/**
 *
    * ClassName: CtlComBusiRelationService
    * Description: (子系统配置服务层)
    * @author 王天尧
    * Date 2017年11月18日
    * Company 首自信--智慧城市创新中心
 */
@Service
@Transactional
public class CtlComBusiRelationService extends CrudServiceExt<CtlComBusiRelationDao, CtlComBusiRelation> {
	@Autowired
	private CtlCompService ctlCompService;
	@Autowired
	private CtlCompMenuService ctlCompMenuService;
	@Autowired
	private CtlBusinessDefineService ctlBusinessDefineService;
	public CtlComBusiRelation get(String id) {
		return super.get(id);
	}

	public List<CtlComBusiRelation> findList(CtlComBusiRelation ctlComBusiRelation) {
		return super.findList(ctlComBusiRelation);
	}

	public Page<CtlComBusiRelation> findPage(Page<CtlComBusiRelation> page, CtlComBusiRelation ctlComBusiRelation) {
		return super.findPage(page, ctlComBusiRelation);
	}

	@Transactional(readOnly = false)
	public void delete(CtlComBusiRelation ctlComBusiRelation) {
		super.delete(ctlComBusiRelation);
	}
	/**
	 *
	 * getBusiListLack:(获取机构没有的子系统).
	 * @param param 传递所需要的参数
	 * @return :List<Map<String,String>>
	 * @since JDK 1.8
	 * @author 王天尧
	 */
	public List<CtlBusinessDefine> getBusiListLack(Map<String,String> param){
		// 存放所有子系统的集合
		List<CtlBusinessDefine> allList = dao.getAllBusiList();
		// 存放所拥有的子系统集合
		List<CtlBusinessDefine> ownList = dao.getBusiListByCom(param);
		//得到未拥有的子系统
		allList.removeAll(ownList);
		return allList;
	}
	/**
	 *
	 * getBusiListLackPage:(获取未拥有的子系统带分页).
	 * @param param
	 * @return :List<CtlBusinessDefine>
	 * @since JDK 1.8
	 * @author 王天尧
	 */
	public List<CtlBusinessDefine> getBusiListLackPage(Map<String,String> param){
		return dao.getBusiListLack(param);
	}
	/**
	 *
	 * getBusiListOwn:(得到该机构已拥有的子系统).
	 * @param param
	 * @return :List<Map<String,String>>
	 * @since JDK 1.8
	 * @author 王天尧
	 */
	public List<CtlBusinessDefine> getBusiListOwn(Map<String,String> param){
		List<CtlBusinessDefine> list = dao.getBusiListByCom(param);
		return list;
	}
	/**
	 *
	 * deleteBusiTree:(将该机构的关联信息删除).
	 * @param param :void
	 * @since JDK 1.8
	 * @author 王天尧
	 */
	@Transactional(readOnly = false)
	public void deleteBusiTree(Map<String,String> param){
		dao.deleteBusiTree(param);
		//将该机构关联的菜单信息移除
		ctlCompMenuService.deleteMenuTreeByComCode(param.get("comCode"));
	}
	/**
	 *
	 * saveBusiTree:(保存机构关联的子系统).
	 * @param busiCodeList
	 * @param comCode :void
	 * @since JDK 1.8
	 * @author 王天尧
	 */
	@Transactional(readOnly = false)
	public void saveBusiTree(List<String> busiCodeList, String comCode) {
		CtlComp ctlComp = new CtlComp();
		CtlBusinessDefine ctlBusinessDefine = new CtlBusinessDefine();
		Map<String,String> param = new HashMap<String,String>();
		for (String busiCode : busiCodeList) {
			CtlComBusiRelation entity = new CtlComBusiRelation();
			ctlComp.setComCode(comCode);
			ctlBusinessDefine.setBusiCode(busiCode);
			List<CtlComp> findList = ctlCompService.findList(ctlComp);
			List<CtlBusinessDefine> findList2 = ctlBusinessDefineService.findList(ctlBusinessDefine);
			entity.setComCode(comCode);
			entity.setComName(findList.get(0).getComName());
			entity.setBusiCode(busiCode);
			entity.setBusiName(findList2.get(0).getBusiName());
			super.save(entity);
			//将模块打包的菜单存到ctl_com_menu表中
			 param.clear();
			 param.put("busiCode", busiCode);
			 List<Map<String, String>> menuByBusi = dao.getMenuByBusi(param);
			 for (Map<String, String> map : menuByBusi) {
				CtlCompMenu ctlCompMenu = new CtlCompMenu();
				ctlCompMenu.setComCode(comCode);
				ctlCompMenu.setMenuCode(map.get("MENU_CODE"));
				ctlCompMenuService.save(ctlCompMenu);
			}

		}
	}
}
