package com.sgai.property.em.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.google.common.collect.Maps;
import com.sgai.common.persistence.Page;
import com.sgai.common.service.CrudServiceExt;
import com.sgai.property.ctl.entity.CtlCodeDet;
import com.sgai.property.ctl.service.CtlCodeDetService;
import com.sgai.property.em.dao.EmEmergencyStaffDao;
import com.sgai.property.em.entity.EmEmergencyStaff;
import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.modules.login.support.UserServletContext;

/**
 *
    * ClassName: EmEmergencyStaffService
    * com.sgai.property.commonService.vo;(应急人员表Service)
    * @author yangyz
    * Date 2017年12月14日
    * Company 首自信--智慧城市创新中心
 */
@Service
@Transactional
public class EmEmergencyStaffService extends CrudServiceExt<EmEmergencyStaffDao, EmEmergencyStaff> {

	@Autowired
	private CtlCodeDetService ctlCodeDetService;
	public EmEmergencyStaff get(String id) {
		return super.get(id);
	}

	public List<EmEmergencyStaff> findList(EmEmergencyStaff emEmergencyStaff) {
		return super.findList(emEmergencyStaff);
	}

	public Page<EmEmergencyStaff> findPage(Page<EmEmergencyStaff> page, EmEmergencyStaff emEmergencyStaff) {
		return super.findPage(page, emEmergencyStaff);
	}

	@Transactional(readOnly = false)
	public void save(EmEmergencyStaff emEmergencyStaff) {
		super.save(emEmergencyStaff);
	}

	@Transactional(readOnly = false)
	public void delete(EmEmergencyStaff emEmergencyStaff) {
		super.delete(emEmergencyStaff);
	}

	public Map<String, Object> saveStaff(EmEmergencyStaff emEmergencyStaff, String oldStaffCode){
		LoginUser userInfo = UserServletContext.getUserInfo();
		Map<String, Object> map = new HashMap<String, Object>();
		if (oldStaffCode.equals(emEmergencyStaff.getStaffCode())) {
			super.save(emEmergencyStaff);
			map.put("msg", "success");
		}else {
			EmEmergencyStaff info = new EmEmergencyStaff();
			info.setStaffCode(emEmergencyStaff.getStaffCode());
			List<EmEmergencyStaff> list = super.findList(info);
			emEmergencyStaff.setComCode(userInfo.getComCode());
			if (list.size() > 0) {
				map.put("msg", "haveData");
			}else {
				super.save(emEmergencyStaff);
				map.put("msg", "success");
			}
		}
		return map;
	}

	/**
	 *
	 * getStaffList:(封装应急专家和应急人员树结构).
	 * @param staffType
	 * @param codeType
	 * @return :List<Map<String,String>>
	 * @since JDK 1.8
	 * @author yangyz
	 */
	public List<Map<String,String>> getStaffList(String staffType,String codeType,String staffCodes){
		LoginUser userInfo = UserServletContext.getUserInfo();
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		CtlCodeDet ctlCodeDet = new CtlCodeDet();
		ctlCodeDet.setCodeType(codeType);
		List<CtlCodeDet> codeDets = ctlCodeDetService.findList(ctlCodeDet);
		EmEmergencyStaff entity = new EmEmergencyStaff();
		entity.setStaffType(staffType);
		entity.setComCode(userInfo.getComCode());
		List<EmEmergencyStaff> staffs = super.findList(entity);
		if (staffCodes != null && !"".equals(staffCodes)) {
			String[] staffStr = staffCodes.split(",");
			for(String code:staffStr) {
				if(code != null && !"".equals(code)) {
					for(EmEmergencyStaff info:staffs) {
						if (code.equals(info.getStaffCode())) {
							staffs.remove(info);
							break;
						}
					}
				}
			}
		}
		Map<String, String> newMap = Maps.newHashMap();
		newMap.put("id","U");
		newMap.put("pId", "0");
		if("0".equals(staffType)) {
			newMap.put("name", "应急专家");
		}else {
			newMap.put("name", "应急人员");
		}

		for(CtlCodeDet info:codeDets) {
			Map<String, String> newMaps = Maps.newHashMap();
			newMaps.put("id", info.getCodeCode());
			newMaps.put("pId", "U");
			newMaps.put("name", info.getCodeName());
			list.add(newMaps);
		}
		for (EmEmergencyStaff staff:staffs) {
			Map<String, String> sMap = Maps.newHashMap();
			sMap.put("id", staff.getStaffCode());
			sMap.put("pId", staff.getTypeCode());
			sMap.put("name", staff.getStaffName());
			list.add(sMap);
		}
		return list;
	}
}
