package com.sgai.property.ctl.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgai.common.persistence.Page;
import com.sgai.common.service.CrudServiceExt;
import com.sgai.property.ctl.dao.CtlBusiModeRelationDao;
import com.sgai.property.ctl.dao.CtlBusinessDefineDao;
import com.sgai.property.ctl.dao.CtlModuDao;
import com.sgai.property.ctl.entity.CtlBusiModeRelation;
import com.sgai.property.ctl.entity.CtlBusinessDefine;
import com.sgai.property.ctl.entity.CtlModu;

/**
 *
    * ClassName: CtlBusiModeRelationService
    * Description: (业务模块打包service)
    * @author 王天尧
    * Date 2017年11月21日
    * Company 首自信--智慧城市创新中心
 */
@Service
@Transactional
public class CtlBusiModeRelationService extends CrudServiceExt<CtlBusiModeRelationDao, CtlBusiModeRelation> {
	@Autowired
	private CtlModuDao ctlModuDao;
	@Autowired
	private CtlBusinessDefineDao ctlBusinessDefineDao;
	public CtlBusiModeRelation get(String id) {
		return super.get(id);
	}

	public List<CtlBusiModeRelation> findList(CtlBusiModeRelation ctlBusiModeRelation) {
		return super.findList(ctlBusiModeRelation);
	}

	public Page<CtlBusiModeRelation> findPage(Page<CtlBusiModeRelation> page, CtlBusiModeRelation ctlBusiModeRelation) {
		return super.findPage(page, ctlBusiModeRelation);
	}

	@Transactional(readOnly = false)
	public void save(CtlBusiModeRelation ctlBusiModeRelation) {
		super.save(ctlBusiModeRelation);
	}

	@Transactional(readOnly = false)
	public void delete(CtlBusiModeRelation ctlBusiModeRelation) {
		super.delete(ctlBusiModeRelation);
	}

/**
 *
 * save:(保存子系统配置结果).
 * @param ctlComBusiRelation
 * @param busiCode 子系统代码
 * @param sbsCodes 模块代码集合
 * @since JDK 1.8
 * @author 王天尧
 */
@Transactional(readOnly = false)
public void saveMode(CtlBusiModeRelation ctlBusiModeRelation,String busiCode,String sbsCodes) {
	ctlBusiModeRelation.setBusiCode(busiCode);
	List<CtlBusiModeRelation> findList = findList(ctlBusiModeRelation);
	for (CtlBusiModeRelation ctlBusiModeRelation2 : findList) {
		delete(ctlBusiModeRelation2);
		}
	//如果sbsCode等于空，删除ctl_com_busi_relation对应机构代码记录。
	if(sbsCodes.equals("")){
	}else {
		//将字符串解析为数组
		String[]sbsCodeArr = sbsCodes.split(",");
		CtlBusinessDefine BussinessDefine = new CtlBusinessDefine();
		BussinessDefine.setBusiCode(busiCode);
		List<CtlBusinessDefine> findList3 = ctlBusinessDefineDao.findList(BussinessDefine);
		//保存已经选中的模块
		for (String sbsCode : sbsCodeArr) {
			CtlModu ctlModu = new CtlModu();
			ctlModu.setSbsCode(sbsCode);
			List<CtlModu> findList2 = ctlModuDao.findList(ctlModu);
			CtlBusiModeRelation ctlBusiModeRelationNew=new CtlBusiModeRelation();
			ctlBusiModeRelationNew.setBusiCode(busiCode);
			ctlBusiModeRelationNew.setSbsCode(sbsCode);
			ctlBusiModeRelationNew.setBusiName(findList3.get(0).getBusiName());
			ctlBusiModeRelationNew.setSbsName(findList2.get(0).getSbsName());
			ctlBusiModeRelationNew.setEnabledFlag("Y");
			super.save(ctlBusiModeRelationNew);
		}
	}
   }
/**
 *
 * getListByCode:(通过机构代码获取模块信息).
 * @param ctlComBusiRelation
 * @param busiCode 机构代码
 * @return :List<CtlModu>
 * @since JDK 1.8
 * @author 王天尧
 */
@Transactional
public List<CtlModu> getListByCode(CtlBusiModeRelation ctlBusiModeRelation,String busiCode){

	//通过子系统代码查询关联的用户
	ctlBusiModeRelation.setBusiCode(busiCode);
	List<CtlBusiModeRelation> moduBusiList = findList(ctlBusiModeRelation);
	//建立一个集合盛放CtlModu对象
	List<CtlModu> ctlModus=new ArrayList<CtlModu>();
	//遍历comBusiList获取sbsCode
	for (CtlBusiModeRelation moduBusi : moduBusiList) {
		CtlModu ctlModu=new CtlModu();
		ctlModu.setSbsCode(moduBusi.getSbsCode());
		List<CtlModu> findList = ctlModuDao.findList(ctlModu);
		ctlModus.addAll(findList);
	}
	return ctlModus;
}
}
