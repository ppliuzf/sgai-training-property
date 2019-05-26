package com.sgai.property.mdm.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.sgai.property.mdm.entity.MdmAreaStruct;
import com.sgai.property.mdm.entity.MdmParkInfo;
import com.sgai.property.mdm.entity.MdmSpaceInfo;
import com.sgai.common.persistence.Page;
import com.sgai.common.service.CrudServiceExt;
import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.mdm.dao.MdmAreaStructDao;

/**
 * 区域描述 ---空间Service
 * @author zhb
 * @version 2017-11-24
 */
@Service
@Transactional
public class MdmAreaStructService extends CrudServiceExt<MdmAreaStructDao, MdmAreaStruct> {

	public MdmAreaStruct get(String id) {
		return super.get(id);
	}

	public MdmAreaStruct getByCode(String arg0,String arg1) {
		return dao.getByCode(arg0,arg1);
	}

	public List<MdmAreaStruct> findList(MdmAreaStruct mdmAreaStruct) {
		return super.findList(mdmAreaStruct);
	}

	public Page<MdmAreaStruct> findPage(Page<MdmAreaStruct> page, MdmAreaStruct mdmAreaStruct) {
		return super.findPage(page, mdmAreaStruct);
	}

	@Transactional(readOnly = false)
	public void save(MdmAreaStruct mdmAreaStruct) {
		super.save(mdmAreaStruct);
	}

	@Transactional(readOnly = false)
	public void delete(MdmAreaStruct mdmAreaStruct) {
		super.delete(mdmAreaStruct);
	}


	@Transactional(readOnly = false)
	public String saveAreaInfo(MdmSpaceInfo  mdmSpaceInfo) {
		LoginUser user = UserServletContext.getUserInfo();

		String nodeCode = mdmSpaceInfo.getSpaceNodeCode();
		MdmAreaStruct mdmAreaStruct = this.getByCode(nodeCode,user.getComCode());
		if(mdmAreaStruct!=null) {
			// 修改原来的信息
			mdmAreaStruct.setAreaName(mdmSpaceInfo.getSpaceNodeName());
			mdmAreaStruct.setAreaLevel(mdmSpaceInfo.getSpaceNodeLevel());
			mdmAreaStruct.setAreaType(mdmSpaceInfo.getSpaceNodeType());
			mdmAreaStruct.setAreaUse(mdmSpaceInfo.getSpaceNodeUse());
			mdmAreaStruct.setPlanChar(mdmSpaceInfo.getSpaceNodePlanChar());
			mdmAreaStruct.setPlanYear(mdmSpaceInfo.getSpaceNodePlanYear());
			mdmAreaStruct.setAreaProperty(mdmSpaceInfo.getSpaceNodeProperty());
			this.save(mdmAreaStruct);
			return "";
		}else {
			String code = "";
			SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmssSSSS");
			//nodeCode的编码生成
			if (mdmSpaceInfo.getSpaceNodeCode().equals("") || mdmSpaceInfo.getSpaceNodeCode() == null) {
				 code =    sdf.format(new Date());
			} else {
				 code= mdmSpaceInfo.getSpaceNodeCode();
			}
			MdmAreaStruct mdmAreaStruct1 = new MdmAreaStruct();
			//自动生成code
			mdmAreaStruct1.setAreaCode(code);
			mdmAreaStruct1.setAreaName(mdmSpaceInfo.getSpaceNodeName());
			mdmAreaStruct1.setAreaLevel(mdmSpaceInfo.getSpaceNodeLevel());
			mdmAreaStruct1.setAreaType(mdmSpaceInfo.getSpaceNodeType());
			mdmAreaStruct1.setAreaUse(mdmSpaceInfo.getSpaceNodeUse());
			mdmAreaStruct1.setPlanChar(mdmSpaceInfo.getSpaceNodePlanChar());
			mdmAreaStruct1.setPlanYear(mdmSpaceInfo.getSpaceNodePlanYear());
			mdmAreaStruct1.setEnabledFlag('Y');
			mdmAreaStruct1.setAreaProperty(mdmSpaceInfo.getSpaceNodeProperty());
			this.save(mdmAreaStruct1);
			return code;
		}

	}

}
