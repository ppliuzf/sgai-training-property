package com.sgai.property.mdm.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgai.property.mdm.entity.MdmFloorInfo;
import com.sgai.property.mdm.entity.MdmParkInfo;
import com.sgai.property.mdm.entity.MdmSpaceInfo;
import com.sgai.common.persistence.Page;
import com.sgai.common.service.CrudServiceExt;
import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.mdm.dao.MdmFloorInfoDao;

/**
 * 楼层描述 ---空间Service
 * @author zhb
 * @version 2017-11-24
 */
@Service
@Transactional
public class MdmFloorInfoService extends CrudServiceExt<MdmFloorInfoDao, MdmFloorInfo> {

	public MdmFloorInfo get(String id) {
		return super.get(id);
	}

	public MdmFloorInfo getByCode(String floorCode,String arg0) {
		return dao.getByCode(floorCode,arg0);
	}

	public List<MdmFloorInfo> findList(MdmFloorInfo mdmFloorInfo) {
		return super.findList(mdmFloorInfo);
	}

	public Page<MdmFloorInfo> findPage(Page<MdmFloorInfo> page, MdmFloorInfo mdmFloorInfo) {
		return super.findPage(page, mdmFloorInfo);
	}

	@Transactional(readOnly = false)
	public void save(MdmFloorInfo mdmFloorInfo) {
		super.save(mdmFloorInfo);
	}

	@Transactional(readOnly = false)
	public void delete(MdmFloorInfo mdmFloorInfo) {
		super.delete(mdmFloorInfo);
	}

	@Transactional(readOnly = false)
	public String saveFloorInfo(MdmSpaceInfo  mdmSpaceInfo) {
		LoginUser user = UserServletContext.getUserInfo();
		String nodeCode = mdmSpaceInfo.getSpaceNodeCode();
		MdmFloorInfo mdmFloorInfo  = this.getByCode(nodeCode,user.getComCode());
		if(mdmFloorInfo!=null) {
			// 修改原来的信息
			mdmFloorInfo.setAreaCode(mdmSpaceInfo.getSpaceGrantParentNodeCode());
			mdmFloorInfo.setBuildingCode(mdmSpaceInfo.getSpaceParentNodeCode());
			mdmFloorInfo.setFloorName(mdmSpaceInfo.getSpaceNodeName());
			mdmFloorInfo.setFloorDesc(mdmSpaceInfo.getSpaceNodeDesc());
			mdmFloorInfo.setFloorProperty(mdmSpaceInfo.getSpaceNodeProperty());
			this.save(mdmFloorInfo);
			return "";
		}else{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmssSSSS");
		String code  = "";
			//nodeCode的编码生成
		if (mdmSpaceInfo.getSpaceNodeCode().equals("") || mdmSpaceInfo.getSpaceNodeCode() == null) {
			 code =    sdf.format(new Date());
		} else {
			 code= mdmSpaceInfo.getSpaceNodeCode();
		}
			//String code= mdmSpaceInfo.getSpaceNodeCode();
			MdmFloorInfo mdmFloorInfo1 = new MdmFloorInfo();
			//自动生成code
			mdmFloorInfo1.setFloorCode(code);
			mdmFloorInfo1.setAreaCode(mdmSpaceInfo.getSpaceGrantParentNodeCode());
			mdmFloorInfo1.setBuildingCode(mdmSpaceInfo.getSpaceParentNodeCode());
			mdmFloorInfo1.setFloorName(mdmSpaceInfo.getSpaceNodeName());
			mdmFloorInfo1.setFloorDesc(mdmSpaceInfo.getSpaceNodeDesc());
			mdmFloorInfo1.setEnabledFlag('Y');
			mdmFloorInfo1.setFloorProperty(mdmSpaceInfo.getSpaceNodeProperty());
			this.save(mdmFloorInfo1);
			return code;
		}
	}

}
