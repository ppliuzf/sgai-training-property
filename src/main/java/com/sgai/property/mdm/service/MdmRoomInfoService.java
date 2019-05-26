package com.sgai.property.mdm.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgai.common.persistence.Page;
import com.sgai.common.service.CrudServiceExt;
import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.mdm.dao.MdmRoomInfoDao;
import com.sgai.property.mdm.entity.MdmAreaStruct;
import com.sgai.property.mdm.entity.MdmRoomInfo;
import com.sgai.property.mdm.entity.MdmSpaceInfo;


/**
 * 房间描述 ---空间Service
 * @author zhb
 * @version 2017-11-24
 */
@Service
@Transactional
public class MdmRoomInfoService extends CrudServiceExt<MdmRoomInfoDao, MdmRoomInfo> {

	public MdmRoomInfo get(String id) {
		return super.get(id);
	}

	public MdmRoomInfo getByCode(String roomCode,String comCode) {
		return dao.getByCode(roomCode,comCode);
	}

	public List<MdmRoomInfo> findList(MdmRoomInfo mdmRoomInfo) {
		return super.findList(mdmRoomInfo);
	}

	public Page<MdmRoomInfo> findPage(Page<MdmRoomInfo> page, MdmRoomInfo mdmRoomInfo) {
		return super.findPage(page, mdmRoomInfo);
	}

	@Transactional(readOnly = false)
	public void save(MdmRoomInfo mdmRoomInfo) {
		super.save(mdmRoomInfo);
	}

	@Transactional(readOnly = false)
	public void delete(MdmRoomInfo mdmRoomInfo) {
		super.delete(mdmRoomInfo);
	}


	@Transactional(readOnly = false)
	public String saveRoomInfo(MdmSpaceInfo  mdmSpaceInfo) {
		LoginUser user = UserServletContext.getUserInfo();
		String nodeCode = mdmSpaceInfo.getSpaceNodeCode();
		MdmRoomInfo mdmRoomInfo = this.getByCode(nodeCode,user.getComCode());
		if(mdmRoomInfo!=null) {
			// 修改原来的信息
			mdmRoomInfo.setRoomArea(Double.valueOf(mdmSpaceInfo.getSpaceNodeBuildArea()));
			mdmRoomInfo.setRoomName(mdmSpaceInfo.getSpaceNodeName());
			mdmRoomInfo.setRoomProperty(mdmSpaceInfo.getSpaceNodeProperty());
			this.save(mdmRoomInfo);
			return "";
		}else {
			SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmssSSSS");
			//nodeCode的编码生成
			String code ="";
			if (mdmSpaceInfo.getSpaceNodeCode().equals("") || mdmSpaceInfo.getSpaceNodeCode() == null) {
				 code =    sdf.format(new Date());
			} else {
				 code= mdmSpaceInfo.getSpaceNodeCode();
			}
			//String code= mdmSpaceInfo.getSpaceNodeCode();
			MdmRoomInfo mdmRoomInfo1 = new MdmRoomInfo();
			//自动生成code
			mdmRoomInfo1.setRoomCode(code);
			mdmRoomInfo1.setBuildingCode(mdmSpaceInfo.getSpaceGrantParentNodeCode());
			mdmRoomInfo1.setRoomArea(Double.valueOf(mdmSpaceInfo.getSpaceNodeBuildArea()));
			mdmRoomInfo1.setRoomName(mdmSpaceInfo.getSpaceNodeName());
			mdmRoomInfo1.setEnabledFlag('Y');
			mdmRoomInfo1.setRoomProperty(mdmSpaceInfo.getSpaceNodeProperty());
			this.save(mdmRoomInfo1);
			return code;
		}

	}
}
