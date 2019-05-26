package com.sgai.property.mdm.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgai.property.mdm.entity.MdmParkInfo;
import com.sgai.property.mdm.entity.MdmSpaceInfo;
import com.sgai.common.persistence.Page;
import com.sgai.common.service.CrudServiceExt;
import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.mdm.dao.MdmParkInfoDao;

/**
 * 园区描述 ---空间Service
 * @author zhb
 * @version 2017-11-24
 */
@Service
@Transactional
public class MdmParkInfoService extends CrudServiceExt<MdmParkInfoDao, MdmParkInfo> {

	public MdmParkInfo get(String id) {
		return super.get(id);
	}

	public MdmParkInfo getByCode(String arg0,String arg1) {
		return dao.getByCode(arg0,arg1);
	}

	public List<MdmParkInfo> findList(MdmParkInfo mdmParkInfo) {
		return super.findList(mdmParkInfo);
	}

	public Page<MdmParkInfo> findPage(Page<MdmParkInfo> page, MdmParkInfo mdmParkInfo) {
		return super.findPage(page, mdmParkInfo);
	}

	@Transactional(readOnly = false)
	public void save(MdmParkInfo mdmParkInfo) {
		super.save(mdmParkInfo);
	}

	@Transactional(readOnly = false)
	public void delete(MdmParkInfo mdmParkInfo) {
		super.delete(mdmParkInfo);
	}

	@Transactional(readOnly = false)
	public String saveParkInfo(MdmSpaceInfo  mdmSpaceInfo) {
		LoginUser user = UserServletContext.getUserInfo();
		String nodeCode = mdmSpaceInfo.getSpaceNodeCode();
		MdmParkInfo mdmParkInfo  = this.getByCode(nodeCode,user.getComCode());
		if(mdmParkInfo!=null) {
			// 修改原来的信息
			mdmParkInfo.setParkName(mdmSpaceInfo.getSpaceNodeName());
			mdmParkInfo.setParkDesc(mdmSpaceInfo.getSpaceNodeDesc());
			mdmParkInfo.setPlanChar(mdmSpaceInfo.getSpaceNodePlanChar());
			mdmParkInfo.setPlanYear(mdmSpaceInfo.getSpaceNodePlanYear());
			mdmParkInfo.setLatiTude(mdmSpaceInfo.getLatiTude());
			mdmParkInfo.setLongiTude(mdmSpaceInfo.getLongiTude());
			mdmParkInfo.setParkProperty(mdmSpaceInfo.getSpaceNodeProperty());
			mdmParkInfo.setViewImge(mdmSpaceInfo.getViewImge());
			this.save(mdmParkInfo);
			return "";
		}else {

		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmssSSSS");
//			//nodeCode的编码生成
			String code= "";
			if (mdmSpaceInfo.getSpaceNodeCode().equals("") || mdmSpaceInfo.getSpaceNodeCode() == null) {
				 code =    sdf.format(new Date());
			} else {
				 code= mdmSpaceInfo.getSpaceNodeCode();
			}
			MdmParkInfo mdmParkInfo1 = new MdmParkInfo();
			//自动生成code
			mdmParkInfo1.setParkCode(code);
			mdmParkInfo1.setParkName(mdmSpaceInfo.getSpaceNodeName());
			mdmParkInfo1.setParkDesc(mdmSpaceInfo.getSpaceNodeDesc());
			mdmParkInfo1.setPlanChar(mdmSpaceInfo.getSpaceNodePlanChar());
			mdmParkInfo1.setPlanYear(mdmSpaceInfo.getSpaceNodePlanYear());
			mdmParkInfo1.setLatiTude(mdmSpaceInfo.getLatiTude());
			mdmParkInfo1.setLongiTude(mdmSpaceInfo.getLongiTude());
			mdmParkInfo1.setEnabledFlag('Y');
			mdmParkInfo1.setParkProperty(mdmSpaceInfo.getSpaceNodeProperty());
			this.save(mdmParkInfo1);
			return code;
		}

	}


}
