package com.sgai.property.mdm.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgai.property.mdm.entity.MdmBuildInfo;
import com.sgai.property.mdm.entity.MdmParkInfo;
import com.sgai.property.mdm.entity.MdmSpaceInfo;
import com.sgai.common.persistence.Page;
import com.sgai.common.service.CrudServiceExt;
import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.mdm.dao.MdmBuildInfoDao;

/**
 * 楼宇描述 ---空间Service
 * @author zhb
 * @version 2017-11-24
 */
@Service
@Transactional
public class MdmBuildInfoService extends CrudServiceExt<MdmBuildInfoDao, MdmBuildInfo> {

	public MdmBuildInfo get(String id) {
		return super.get(id);
	}

	public MdmBuildInfo getByCode(String buildingCode , String comCode) {
		return dao.getByCode(buildingCode,comCode);
	}


	public List<MdmBuildInfo> findList(MdmBuildInfo mdmBuildInfo) {
		return super.findList(mdmBuildInfo);
	}

	public Page<MdmBuildInfo> findPage(Page<MdmBuildInfo> page, MdmBuildInfo mdmBuildInfo) {
		return super.findPage(page, mdmBuildInfo);
	}

	@Transactional(readOnly = false)
	public void save(MdmBuildInfo mdmBuildInfo) {
		super.save(mdmBuildInfo);
	}

	@Transactional(readOnly = false)
	public void delete(MdmBuildInfo mdmBuildInfo) {
		super.delete(mdmBuildInfo);
	}

	@Transactional(readOnly = false)
	public String saveBuildInfo(MdmSpaceInfo  mdmSpaceInfo) {
		LoginUser user = UserServletContext.getUserInfo();

		String nodeCode = mdmSpaceInfo.getSpaceNodeCode();
		MdmBuildInfo mdmBuildInfo  = this.getByCode(nodeCode,user.getComCode());
		if(mdmBuildInfo!=null) {
			// 修改原来的信息
			mdmBuildInfo.setBuildingName(mdmSpaceInfo.getSpaceNodeName());
			mdmBuildInfo.setBuildArea(mdmSpaceInfo.getSpaceNodeBuildArea());
			mdmBuildInfo.setFloorCount(mdmSpaceInfo.getSpaceNodeFloorCount());
			mdmBuildInfo.setLatiTude(mdmSpaceInfo.getLatiTude());
			mdmBuildInfo.setLongiTude(mdmSpaceInfo.getLongiTude());
			mdmBuildInfo.setUseDesc(mdmSpaceInfo.getSpaceNodeUse());
			mdmBuildInfo.setBuildProperty(mdmSpaceInfo.getSpaceNodeProperty());
			this.save(mdmBuildInfo);
			return "";
		}else {
			SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmssSSSS");
			String code = "";
			if (mdmSpaceInfo.getSpaceNodeCode().equals("") || mdmSpaceInfo.getSpaceNodeCode() == null) {
				 code =    sdf.format(new Date());
			} else {
				 code= mdmSpaceInfo.getSpaceNodeCode();
			}

			//nodeCode的编码生成
			//String code= mdmSpaceInfo.getSpaceNodeCode();
			MdmBuildInfo mdmBuildInfo1  = new MdmBuildInfo();
			//自动生成code
			mdmBuildInfo1.setBuildingCode(code);
			mdmBuildInfo1.setBuildingName(mdmSpaceInfo.getSpaceNodeName());
			mdmBuildInfo1.setBuildArea(mdmSpaceInfo.getSpaceNodeBuildArea());
			mdmBuildInfo1.setFloorCount(mdmSpaceInfo.getSpaceNodeFloorCount());
			mdmBuildInfo1.setLatiTude(mdmSpaceInfo.getLatiTude());
			mdmBuildInfo1.setLongiTude(mdmSpaceInfo.getLongiTude());
			mdmBuildInfo1.setUseDesc(mdmSpaceInfo.getSpaceNodeUse());
			mdmBuildInfo1.setEnabledFlag('Y');
			mdmBuildInfo1.setBuildProperty(mdmSpaceInfo.getSpaceNodeProperty());
			this.save(mdmBuildInfo1);
			return code;
		}
	}

}
