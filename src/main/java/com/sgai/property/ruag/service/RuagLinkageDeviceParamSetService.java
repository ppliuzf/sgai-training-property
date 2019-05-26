package com.sgai.property.ruag.service;

import com.sgai.common.persistence.Page;
import com.sgai.common.service.CrudServiceExt;
import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.mdm.entity.MdmDeviceParameter;
import com.sgai.property.mdm.entity.MdmDevicesUseInfo;
import com.sgai.property.mdm.service.MdmDeviceParameterService;
import com.sgai.property.mdm.service.MdmDevicesUseInfoService;
import com.sgai.property.ruag.dao.RuagLinkageDeviceParamSetDao;
import com.sgai.property.ruag.dto.RuagLinkageDeviceParamSetVo;
import com.sgai.property.ruag.entity.RuagLinkaageFrontDevice;
import com.sgai.property.ruag.entity.RuagLinkaageNextDevice;
import com.sgai.property.ruag.entity.RuagLinkageDeviceParamSet;
import com.sgai.property.ruag.entity.RuagLinkageRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 模式设备参数设置Service
 * @author yangyz
 * @version 2018-01-02
 */
@Service
@Transactional
public class RuagLinkageDeviceParamSetService extends CrudServiceExt<RuagLinkageDeviceParamSetDao, RuagLinkageDeviceParamSet> {

	@Autowired
	private RuagLinkaageFrontDeviceService ruagLinkaageFrontDeviceService;
	@Autowired
	private RuagLinkaageNextDeviceService ruagLinkaageNextDeviceService;
	@Autowired
	private RuagLinkageRuleService ruagLinkageRuleService;
	@Autowired
	private MdmDeviceParameterService mdmDeviceParameterService;
	@Autowired
	private MdmDevicesUseInfoService mdmDevicesUseInfoService;
	public RuagLinkageDeviceParamSet get(String id) {
		return super.get(id);
	}

	public List<RuagLinkageDeviceParamSet> findList(RuagLinkageDeviceParamSet ruagLinkageDeviceParamSet) {
		return super.findList(ruagLinkageDeviceParamSet);
	}

	public Page<RuagLinkageDeviceParamSet> findPage(Page<RuagLinkageDeviceParamSet> page, RuagLinkageDeviceParamSet ruagLinkageDeviceParamSet) {
		return super.findPage(page, ruagLinkageDeviceParamSet);
	}

	@Transactional(readOnly = false)
	public void save(RuagLinkageDeviceParamSet ruagLinkageDeviceParamSet) {
		super.save(ruagLinkageDeviceParamSet);
	}

	@Transactional(readOnly = false)
	public void delete(RuagLinkageDeviceParamSet ruagLinkageDeviceParamSet) {
		super.delete(ruagLinkageDeviceParamSet);
	}


	public Map<String, Object> saveDeviceParamSet(String linkageRuleId, String masterName,String profCode,String profName, String deviceClassCode ,String deviceCode, String deviceParams,String switchFlag){
		LoginUser user = UserServletContext.getUserInfo();
		Map<String, Object> map = new HashMap<String, Object>();
		String[] content = deviceParams.split("]");
		String[] deviceCodes = deviceCode.split(";");
		RuagLinkageRule ruagLinkageRule = ruagLinkageRuleService.getLinkageCode(linkageRuleId,user.getComCode(),user.getModuCode());
		for(String s:deviceCodes) {
			String[] params = s.split(",");
			MdmDevicesUseInfo device = new MdmDevicesUseInfo();
			device.setDevicesCode(params[0]);
			List<MdmDevicesUseInfo> findList = mdmDevicesUseInfoService.findList(device);
			if("RUSG_LINKAAGE_FRONT_DEVICE".equals(masterName)) {
				RuagLinkaageFrontDevice ruagLinkaageFrontDevice =new RuagLinkaageFrontDevice();
				ruagLinkaageFrontDevice.setLinkageCode(linkageRuleId);
				ruagLinkaageFrontDevice.setLinkageName(ruagLinkageRule.getLinkageName());
				ruagLinkaageFrontDevice.setSpaceCode(findList.get(0).getSpaceCode());
				ruagLinkaageFrontDevice.setSpaceName(findList.get(0).getSpaceName());
				ruagLinkaageFrontDevice.setRuleSet(getRuleSet(params[0],deviceParams));
				ruagLinkaageFrontDevice.setProfCode(profCode);
				ruagLinkaageFrontDevice.setProfName(profName);
				ruagLinkaageFrontDevice.setDeviceName(params[1]);
				ruagLinkaageFrontDevice.setStatus("Y");
				ruagLinkaageFrontDevice.setDeviceCode(params[0]);
				ruagLinkaageFrontDevice.setEnabledFlag("Y");
				ruagLinkaageFrontDevice.setClassCode(deviceClassCode);
				ruagLinkaageFrontDeviceService.save(ruagLinkaageFrontDevice);
				for(String str:content) {
					String[] params1 = str.split(",");
					if(params1.length>2) {
						RuagLinkageDeviceParamSet info = new RuagLinkageDeviceParamSet();
						info.setLinkageRuleId(linkageRuleId);
						info.setMasterName(masterName);
						info.setMasterId(ruagLinkaageFrontDevice.getId());
						info.setDeviceClassCode(deviceClassCode);
						info.setDeviceCode(params[0]);
						info.setParameterId(params1[0]);
						String operator="";
						if(params1[1].equals("&gt;")) {
							operator=">";
						}else if(params1[1].equals("&lt;")){
							operator="<";
						}else {
							operator="==";
						}
						info.setParameterValue(operator+params1[2]);
						info.setEnabledFlag("Y");
						info.setFrontNextFlag("F");
						save(info);
					}else {
						continue;
					}

				}
			}else if("RUSG_LINKAAGE_NEXT_DEVICE".equals(masterName)) {
				RuagLinkaageNextDevice ruagLinkaageNextDevice = new RuagLinkaageNextDevice();
				ruagLinkaageNextDevice.setLinkageCode(linkageRuleId);
				ruagLinkaageNextDevice.setLinkageName(ruagLinkageRule.getLinkageName());
				ruagLinkaageNextDevice.setSpaceCode(findList.get(0).getSpaceCode());
				ruagLinkaageNextDevice.setSpaceName(findList.get(0).getSpaceName());
				//ruagLinkaageNextDevice.setRuleSet(getRuleSet(params[0],deviceParams));
				ruagLinkaageNextDevice.setProfCode(profCode);
				ruagLinkaageNextDevice.setProfName(profName);
				ruagLinkaageNextDevice.setDeviceCode(params[0]);
				ruagLinkaageNextDevice.setDeviceName(params[1]);
				ruagLinkaageNextDevice.setStatus("Y");
				ruagLinkaageNextDevice.setEnabledFlag("Y");
				ruagLinkaageNextDevice.setClassCode(deviceClassCode);
				ruagLinkaageNextDeviceService.save(ruagLinkaageNextDevice);
				for(String str:content) {
					String[] params1 = str.split(",");
					if(params1.length>2) {
						RuagLinkageDeviceParamSet info = new RuagLinkageDeviceParamSet();
						info.setLinkageRuleId(linkageRuleId);
						info.setMasterName(masterName);
						info.setMasterId(ruagLinkaageNextDevice.getId());
						info.setDeviceClassCode(deviceClassCode);
						info.setDeviceCode(params[0]);
						info.setParameterId(params1[0]);
						info.setParameterValue(params1[2]);
						info.setEnabledFlag("Y");
						info.setFrontNextFlag("N");
						info.setSwitchFlag(switchFlag);
						save(info);
					}else {
						continue;
					}

				}
			}
		}
		map.put("msg", "success");
		return map;
	}
	/**
	 *
	    * @Title: saveCheckedParamSet
	    * @com.sgai.property.commonService.vo;(设置选中联动设备的参数)
	    * @param @param masterName 判断是前置设备还是后置设备
	    * @param @param classCode  设备类型编码
	    * @param @param linkageRuleId 联动规则id
	    * @param @param LinkDeviceIds 联动设备id
	    * @param @param deviceParams  参数信息
	    * @param @return    参数
	    * @return Map<String,Object>    返回类型
	    * @throws
	 */
	public Map<String, Object> saveCheckedParamSet(String masterName,String classCode,String linkageRuleId,String LinkDeviceIds, String deviceParams){
		Map<String, Object> map = new HashMap<String, Object>();
		String[] content = deviceParams.split("]");
		String[] ids = LinkDeviceIds.split(",");
		for(String id:ids) {
			RuagLinkageDeviceParamSet infoDe = new RuagLinkageDeviceParamSet();
			infoDe.setMasterId(id);
			dao.deleteByMasterId(infoDe);
			if("RUSG_LINKAAGE_FRONT_DEVICE".equals(masterName)) {
				RuagLinkaageFrontDevice ruagLinkaageFrontDevice = ruagLinkaageFrontDeviceService.get(id);
				ruagLinkaageFrontDevice.setRuleSet(getRuleSet(ruagLinkaageFrontDevice.getDeviceCode(),deviceParams));
				ruagLinkaageFrontDeviceService.save(ruagLinkaageFrontDevice);
				for(String str:content) {
					String[] params1 = str.split(",");
					if(params1.length>2) {
						RuagLinkageDeviceParamSet info = new RuagLinkageDeviceParamSet();
						info.setLinkageRuleId(linkageRuleId);
						info.setMasterName(masterName);
						info.setMasterId(id);
						info.setDeviceClassCode(classCode);
						info.setDeviceCode(ruagLinkaageFrontDevice.getDeviceCode());
						info.setParameterId(params1[0]);
						String operator="";
						if(params1[1].equals("&gt;")) {
							operator=">";
						}else if(params1[1].equals("&lt;")){
							operator="<";
						}else {
							operator="==";
						}
						info.setParameterValue(operator+params1[2]);
						info.setFrontNextFlag("F");
						info.setEnabledFlag("Y");
						save(info);
					}else {
						continue;
					}

				}
			}else if("RUSG_LINKAAGE_NEXT_DEVICE".equals(masterName)) {
				RuagLinkaageNextDevice ruagLinkaageNextDevice = ruagLinkaageNextDeviceService.get(id);
				ruagLinkaageNextDevice.setRuleSet(getRuleSet(ruagLinkaageNextDevice.getDeviceCode(),deviceParams));
				ruagLinkaageNextDeviceService.save(ruagLinkaageNextDevice);
				for(String str:content) {
					String[] params1 = str.split(",");
					if(params1.length>2) {
						RuagLinkageDeviceParamSet info = new RuagLinkageDeviceParamSet();
						info.setLinkageRuleId(linkageRuleId);
						info.setMasterName(masterName);
						info.setMasterId(ruagLinkaageNextDevice.getId());
						info.setDeviceClassCode(classCode);
						info.setDeviceCode(ruagLinkaageNextDevice.getDeviceCode());
						info.setParameterId(params1[0]);
						info.setParameterValue(params1[2]);
						info.setEnabledFlag("Y");
						info.setFrontNextFlag("N");
						save(info);
					}else {
						continue;
					}

				}
			}
		}
		map.put("msg", "success");
		return map;
	}

	/**
	 *
	    * @Title: getRuleSet
	    * @com.sgai.property.commonService.vo;(规则设置)
	    * @param @param deviceCode
	    * @param @param deviceParams
	    * @param @param operator
	    * @param @return    参数
	    * @return String    返回类型
	    * @throws
	 */
	public String getRuleSet(String deviceCode,String deviceParams) {
		String[] content = deviceParams.split("]");
		StringBuffer sb = new StringBuffer();
		String s = "";
		String operator="";
		if(content.length > 1) {
			for (String string : content) {
				String[] params1 = string.split(",");
				//（device100001.p001>50）&&（device100002.p002>10）
				if(params1.length>2) {
					if(params1[1].equals("&gt;")) {
						operator=">";
					}else if(params1[1].equals("&lt;")){
						operator="<";
					}else {
						operator="==";
					}
					sb.append("(").append(deviceCode).append(".").append(params1[0]).append(".#").append(operator).append(params1[2]).append(")").append("&&");
				}else {
					continue;
				}
			}
			s = sb.substring(0, sb.length()-2);
		}else if(content.length == 1) {
			String[] params1 = content[0].split(",");
			if(params1[1].equals("&gt;")) {
				operator=">";
			}else if(params1[1].equals("&lt;")){
				operator="<";
			}else {
				operator="==";
			}
			sb.append(deviceCode).append(".").append(params1[0]).append(".#").append(operator).append(params1[2]);
			s = sb.toString();
		}
		return s;
	}
	/**
	 *
	    * @Title: getLinkParameters
	    * @com.sgai.property.commonService.vo;(查找设备的参数)
	    * @param @param id 联动设备业务id
	    * @param @return    参数
	    * @return List<RuagLinkageDeviceParamSet>    返回类型
	    * @throws
	 */
	 public List<RuagLinkageDeviceParamSetVo> getLinkParameters(String id){
		 List<RuagLinkageDeviceParamSetVo> voList = new ArrayList<RuagLinkageDeviceParamSetVo>();
		 RuagLinkageDeviceParamSet ruagLinkageDeviceParamSet = new RuagLinkageDeviceParamSet();
		 ruagLinkageDeviceParamSet.setMasterId(id);
		 List<RuagLinkageDeviceParamSet> findList = dao.findList(ruagLinkageDeviceParamSet);
		 for (RuagLinkageDeviceParamSet ruagLinkageDeviceParamSet2 : findList) {
			 MdmDeviceParameter  mdmDeviceParameter = new MdmDeviceParameter();
			 mdmDeviceParameter.setParamCode(ruagLinkageDeviceParamSet2.getParameterId());
			 List<MdmDeviceParameter> findList2 = mdmDeviceParameterService.findList(mdmDeviceParameter);
			 RuagLinkageDeviceParamSetVo ruagLinkageDeviceParamSetVo = new RuagLinkageDeviceParamSetVo();
			 ruagLinkageDeviceParamSetVo.setParameterName(findList2.get(0).getParamName());
			 ruagLinkageDeviceParamSetVo.setParameterValue(ruagLinkageDeviceParamSet2.getParameterValue());
			 voList.add(ruagLinkageDeviceParamSetVo);
		}
		 return voList;
	    }
	 public List<RuagLinkageDeviceParamSet> findAllOfList(RuagLinkageDeviceParamSet ruagLinkageDeviceParamSet) {
			return dao.findAllOfList(ruagLinkageDeviceParamSet);
		}
}
