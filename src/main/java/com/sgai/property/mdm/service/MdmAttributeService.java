package com.sgai.property.mdm.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sgai.common.persistence.Page;
import com.sgai.common.service.CrudServiceExt;
import com.sgai.common.utils.StringUtils;
import com.sgai.property.ctl.service.CtlComRuleService;
import com.sgai.property.ctl.service.DeleteRulesUtils;
import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.mdm.dao.MdmAttributeDao;
import com.sgai.property.mdm.dao.MdmDevicesUseInfoDao;
import com.sgai.property.mdm.dto.MdmDevicesUseInfoVo;
import com.sgai.property.mdm.entity.MdmAttribute;
import com.sgai.property.mdm.entity.MdmDeviceClass;
import com.sgai.property.mdm.entity.MdmDeviceParameter;
import com.sgai.property.mdm.entity.MdmDeviceProf;
import com.sgai.property.mdm.entity.MdmDevicesUseInfo;

/**
 *
    * ClassName: MdmDevicesUseInfoService
    * com.sgai.property.commonService.vo;(设备主数据业务层)
    * @author yangyz
    * Date 2017年11月24日
    * Company 首自信--智慧城市创新中心
 */
@Service
@Transactional
public class MdmAttributeService extends CrudServiceExt<MdmAttributeDao, MdmAttribute> {


	public MdmAttribute get(String id) {
		return super.get(id);
	}
	public MdmDevicesUseInfoVo getAttribute(String id) {
		return dao.getAttribute(id);
	}

	public List<MdmAttribute> findList(MdmAttribute mdmAttribute) {
		return super.findList(mdmAttribute);
	}

	public Page<MdmAttribute> findPage(Page<MdmAttribute> page, MdmAttribute mdmAttribute) {
		return super.findPage(page, mdmAttribute);
	}

	@Transactional(readOnly = false)
	public void save(MdmAttribute mdmAttribute) {
		super.save(mdmAttribute);
	}

	@Transactional(readOnly = false)
	public void delete(MdmAttribute mdmAttribute) {
		super.delete(mdmAttribute);
	}

	/**
	 *
	 * saveDeviceParameter:(保存设备运行参数).
	 * @param profCode
	 * @param profName
	 * @param classCode
	 * @param className
	 * @param deviceParams
	 * @param remarks
	 * @return :Map<String,Object>
	 * @since JDK 1.8
	 * @author yangyz
	 */
	public Map<String, Object> saveDeviceAttrParameter(String devicesCode, String deviceParams){
		Map<String, Object> map = new HashMap<String, Object>();
		List<String> codeList = new ArrayList<String>();
		List<String> namesList = new ArrayList<String>();
		String[] content = deviceParams.split(";");
		for(String str:content) {
			String[] params = str.split(",");
			MdmAttribute info = new MdmAttribute();
			info.setDevicesCode(devicesCode);
			info.setAttrValue(params[1]);

			List<MdmAttribute> list = super.findList(info);
			info.setAttrValue("");
			info.setAttrName(params[0]);
			List<MdmAttribute> nameList = super.findList(info);

			if (list.size() > 0) {
				map.put("msg", "haveCode");
				return map;
			}else if(nameList.size() > 0){
				map.put("msg", "haveName");
				return map;
			}else{

				codeList.add(params[0]);
				namesList.add(params[1]);
			}
		}
		//判断要提交的数据是否代码重复
		for(int i=0;i<codeList.size();i++) {
			for(int j=i+1;j<codeList.size();j++) {
				if (codeList.get(i).equals(codeList.get(j))) {
					map.put("msg", "haveNames");
					return map;
				}
			}
		}
		//判断要提交的数据是否名称重复
	/*	for(int i=0;i<namesList.size();i++) {
			for(int j=i+1;j<namesList.size();j++) {
				if (namesList.get(i).equals(namesList.get(j))) {
					map.put("msg", "haveCodes");
					return map;
				}
			}
		}*/
		for(String str:content) {
			String[] params = str.split(",");
			MdmAttribute info = new MdmAttribute();
			info.setDevicesCode(devicesCode);
			info.setAttrName(params[0]);
			info.setAttrValue(params[1]);

			save(info);
		}
		map.put("msg", "success");
		return map;
	}

}
