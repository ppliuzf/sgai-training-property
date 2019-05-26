package com.sgai.property.mdm.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgai.common.persistence.Page;
import com.sgai.common.service.CrudServiceExt;
import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.mdm.dao.MdmDeviceKpiInfoDao;
import com.sgai.property.mdm.entity.MdmDeviceKpiInfo;

/**
 * 标准值Service
 * @author admin
 * @version 2018-07-27
 */
@Service
@Transactional
public class MdmDeviceKpiInfoService extends CrudServiceExt<MdmDeviceKpiInfoDao, MdmDeviceKpiInfo> {

	public MdmDeviceKpiInfo get(String id) {
		return super.get(id);
	}

	public List<MdmDeviceKpiInfo> findList(MdmDeviceKpiInfo mdmDeviceKpiInfo) {
		return super.findList(mdmDeviceKpiInfo);
	}

	public Page<MdmDeviceKpiInfo> findPage(Page<MdmDeviceKpiInfo> page, MdmDeviceKpiInfo mdmDeviceKpiInfo) {
		return super.findPage(page, mdmDeviceKpiInfo);
	}

	@Transactional(readOnly = false)
	public void save(MdmDeviceKpiInfo mdmDeviceKpiInfo) {
		super.save(mdmDeviceKpiInfo);
	}

	@Transactional(readOnly = false)
	public void delete(MdmDeviceKpiInfo mdmDeviceKpiInfo) {
		super.delete(mdmDeviceKpiInfo);
	}
	@Transactional
	public List<String> getPaths(String spaceCode,String profCode){
		LoginUser user = UserServletContext.getUserInfo();
		Map<String,String> params = new HashMap<String,String>();
		params.put("spaceCode", spaceCode);
		params.put("profCode", profCode);
		params.put("comCode", "neiwang");
		params.put("moduCode", "");

		return dao.findPaths(params);

	}

}
