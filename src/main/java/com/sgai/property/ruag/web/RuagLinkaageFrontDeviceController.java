package com.sgai.property.ruag.web;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sgai.common.persistence.Page;
import com.sgai.common.web.BaseController;
import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.modules.login.jwt.util.CommonResponse;
import com.sgai.modules.login.jwt.util.ResponseUtil;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.mdm.dao.MdmDevicesUseInfoDao;
import com.sgai.property.mdm.dto.MdmDevicesUseInfoVo;
import com.sgai.property.mdm.service.MdmDevicesUseInfoService;
import com.sgai.property.ruag.dao.RuagLinkageDeviceParamSetDao;
import com.sgai.property.ruag.entity.RuagLinkaageFrontDevice;
import com.sgai.property.ruag.entity.RuagLinkageDeviceParamSet;
import com.sgai.property.ruag.entity.RuagLinkageRule;
import com.sgai.property.ruag.service.RuagLinkaageFrontDeviceService;
import com.sgai.property.ruag.service.RuagLinkageDeviceParamSetService;
import com.sgai.property.ruag.service.RuagLinkageRuleService;
import com.sgai.property.ruag.service.RuagLinkageRuleSpaceService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 联动前置设备Controller
 * @author yangyz
 * @version 2018-01-02
 */
@RestController
@RequestMapping(value = "ruag/ruag/ruagLinkaageFrontDevice")
public class RuagLinkaageFrontDeviceController extends BaseController {

	@Autowired
	private RuagLinkaageFrontDeviceService ruagLinkaageFrontDeviceService;
	@Autowired
	private MdmDevicesUseInfoService mdmDevicesUseInfoService;
	@Autowired
	private RuagLinkageRuleSpaceService ruagLinkageRuleSpaceService;
	@Autowired
	private RuagLinkageRuleService ruagLinkageRuleService;
	@Autowired
	private RuagLinkageDeviceParamSetDao ruagLinkageDeviceParamSetDao;
	@ApiOperation(value = "联动前置设备分页", notes = "联动前置设备分页")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "linkageCode", value = "联动规则id", required = false,paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "spaceName", value = "位置", required = false,paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "profCode", value = "专业", required = false,paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "classCode", value = "设备类型", required = false,paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "deviceName", value = "设备名称", required = false,paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "spaceCode", value = "位置编码", required = false,paramType = "query", dataType = "String")
	})
	@RequestMapping(value = "/getListLinkaageFrontDevice",method=RequestMethod.POST)
	public CommonResponse getListComp(
			String linkageCode,
			String spaceName,
			String profCode,
			String classCode,
			String deviceName,
			String spaceCode,

			@RequestParam(value = "pageNo", required = true,defaultValue="0") Integer pageNo ,
			@RequestParam(value = "pageSize", required = true,defaultValue="10") Integer pageSize,
			HttpServletRequest request, 
			HttpServletResponse response
			) throws IOException {
		RuagLinkaageFrontDevice ruagLinkaageFrontDevice = new RuagLinkaageFrontDevice();
		Map<String,String> sqlMap = new HashMap<String,String>();
		if(StringUtils.isNotBlank(spaceCode)) {
			sqlMap.put("sql", "A.space_code IN("+spaceCode.substring(0, spaceCode.length()-1)+")");
			ruagLinkaageFrontDevice.setSqlMap(sqlMap);
		}
		ruagLinkaageFrontDevice.setLinkageCode(linkageCode);
		ruagLinkaageFrontDevice.setSpaceName(spaceName);
		ruagLinkaageFrontDevice.setProfCode(profCode);
		ruagLinkaageFrontDevice.setClassCode(classCode);
		ruagLinkaageFrontDevice.setDeviceName(deviceName);
		Page<RuagLinkaageFrontDevice> page = ruagLinkaageFrontDeviceService.findPage(new Page<RuagLinkaageFrontDevice>(pageNo, pageSize), ruagLinkaageFrontDevice);
		return ResponseUtil.successResponse(page);
	}
	
	@ApiOperation(value = "删除联动前置设备", notes = "删除联动前置设备")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "主键", required = false,paramType = "query", dataType = "String")
	})
	@RequestMapping(value = "delete",method=RequestMethod.POST)
	public CommonResponse delete(
			String ids, 

			RedirectAttributes redirectAttributes
			) throws IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String[] arr = ids.split(",");
		    for (String id : arr) {
		    	RuagLinkageDeviceParamSet ruagLinkageDeviceParamSet = new RuagLinkageDeviceParamSet();
				ruagLinkageDeviceParamSet.setMasterId(id);
				ruagLinkageDeviceParamSetDao.deleteByMasterId(ruagLinkageDeviceParamSet);
				RuagLinkaageFrontDevice ruagLinkaageFrontDevice = new RuagLinkaageFrontDevice();
				ruagLinkaageFrontDevice.setId(id);
				ruagLinkaageFrontDeviceService.delete(ruagLinkaageFrontDevice);
			}
			map.put("msg", "success");
		} catch (Exception e) {

			map.put("msg", "faild");
			e.printStackTrace();
		}  
		
		return ResponseUtil.successResponse(map);
	}
	
	@ApiOperation(value = "保存联动前置设备", notes = "保存联动前置设备")
	@RequestMapping(value = "save",method=RequestMethod.POST)
	public CommonResponse save(
			RuagLinkaageFrontDevice ruagLinkaageFrontDevice, 

			RedirectAttributes redirectAttributes
			) throws IOException {
		
		Map<String,Object> data = new HashMap<String,Object>();
		ruagLinkaageFrontDevice.setStatus("N");
		try {
			data = ruagLinkaageFrontDeviceService.saveLinkaageFrontDevice(ruagLinkaageFrontDevice);
			data.put("msg", "success");
		} catch (Exception e) {
			data.put("msg", "failed");
			e.printStackTrace();
		}
		return ResponseUtil.successResponse(data);
	}
	
	@ApiOperation(value = "根据前置设备查询主设备数据", notes = "根据前置设备查询主设备数据")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "linkageCode", value = "联动规则id", required = false,paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "profName", value = "设备专业", required = false,paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "devicesName", value = "设备名称", required = false,paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "className", value = "类型", required = false,paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "spaceName", value = "设备位置", required = false,paramType = "query", dataType = "String")
	})
	@RequestMapping(value = "/getByLinkageCode",method=RequestMethod.POST)
	public CommonResponse getByLinkageCode(
			String linkageCode,
			String profCode,
			String devicesName,
			String spaceName,
			String classCode,
			String spaceCode,

			@RequestParam(value = "pageNo", required = true,defaultValue="0") Integer pageNo ,
			@RequestParam(value = "pageSize", required = true,defaultValue="10") Integer pageSize,
			HttpServletRequest request, 
			HttpServletResponse response
			) throws IOException {
		LoginUser user = UserServletContext.getUserInfo();
		String spaces = "";
		//查询主设备中不包含前置设备的数据
		MdmDevicesUseInfoVo mdmDevicesUseInfoVo = new MdmDevicesUseInfoVo();
		mdmDevicesUseInfoVo.setProfCode(profCode);
		mdmDevicesUseInfoVo.setDevicesName(devicesName);
		mdmDevicesUseInfoVo.setSpaceName(spaceName);
		mdmDevicesUseInfoVo.setClassCode(classCode);
		mdmDevicesUseInfoVo.setModuCode(user.getModuCode());
		mdmDevicesUseInfoVo.setComCode(user.getComCode());
		Map<String,String> sqlMap = new HashMap<String,String>();
		if(StringUtils.isNotBlank(spaceCode)) {
			spaces = spaceCode;
		}else {
			spaces = ruagLinkageRuleSpaceService.getSpace(linkageCode);
		}
		String sql ="AND A .SPACE_CODE IN ("+spaces.substring(0,spaces.length()-1)+")AND NOT EXISTS (SELECT * FROM( SELECT D .DEVICE_CODE FROM RUAG_LINKAAGE_FRONT_DEVICE D WHERE D .LINKAGE_CODE = '"+linkageCode
				    +"'UNION SELECT F.DEVICE_CODE FROM RUAG_LINKAAGE_NEXT_DEVICE F WHERE F.LINKAGE_CODE = '"+linkageCode
				    +"' ) G WHERE A .DEVICES_CODE = G .DEVICE_CODE)";
		sqlMap.put("sqlMap", sql);
		mdmDevicesUseInfoVo.setSqlMap(sqlMap);
		Page<MdmDevicesUseInfoVo> page = mdmDevicesUseInfoService.findPageVoByModel(new Page<MdmDevicesUseInfoVo>(pageNo, pageSize), mdmDevicesUseInfoVo);
		return ResponseUtil.successResponse(page);
	}
	@RequestMapping(value = "/getSpacesByLinkageCode",method=RequestMethod.POST)
	public String getByLinkageCode(

			String linkageCode,
			HttpServletRequest request, 
			HttpServletResponse response
			) {
		return ruagLinkageRuleSpaceService.getSpace(linkageCode);
	}
	/**
	 * @throws ParseException 
	 * @throws ServletException 
	 * @throws IOException 
	 * 
	    * @Title: checkLinkage  
	    * @com.sgai.property.commonService.vo;(这里用一句话描述这个方法的作用)
	    * @param @param comCode
	    * @param @param sequCode    参数  
	    * @return void    返回类型  
	    * @throws
	 */
	@RequestMapping(value = "checkLinkage",method=RequestMethod.POST)
	public synchronized void checkLinkage(String path) throws IOException, ServletException, ParseException {
		ruagLinkaageFrontDeviceService.scanRules(path);
	}
	
}