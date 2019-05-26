package com.sgai.property.ruag.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sgai.common.persistence.Page;
import com.sgai.common.utils.StringUtils;
import com.sgai.common.web.BaseController;
import com.sgai.modules.login.jwt.util.CommonResponse;
import com.sgai.modules.login.jwt.util.ResponseUtil;
import com.sgai.property.mdm.entity.MdmDevicesUseInfo;
import com.sgai.property.mdm.service.MdmDevicesUseInfoService;
import com.sgai.property.ruag.entity.RuagModelDeviceParamerSet;
import com.sgai.property.ruag.entity.RuagWorkModelDatail;
import com.sgai.property.ruag.service.RuagWorkModelDatailService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 
    * @ClassName: RuagWorkModelDatailController  
    * @com.sgai.property.commonService.vo;(设备运行策略配置Controller)
    * @author ASUS  
    * @date 2018年1月3日  
    * @Company 首自信--智慧城市创新中心
 */
@RestController
@RequestMapping(value = "ruag/ruag/ruagWorkModelDatail")
public class RuagWorkModelDatailController extends BaseController {
	@Autowired
	private RuagWorkModelDatailService ruagWorkModelDatailService;
	@Autowired
	private MdmDevicesUseInfoService  mdmDevicesUseInfoService;
	
	
	
	
	
	/**
	 * 
	    * @Title: getList  
	    * @com.sgai.property.commonService.vo;(加载运行策略设置列表带分页)
	    * @param @param modelTemplateId 运行策略id
	    * @param @param profCode 专业代码
	    * @param @param deviceCode 设备编码
	    * @param @param spaceCode 位置编码
	    * @param @param token
	    * @param @param request
	    * @param @param response
	    * @param @return
	    * @param @throws IOException
	    * @param @throws ServletException    参数  
	    * @return CommonResponse    返回类型  
	    * @throws
	 */
	@ApiOperation(value = "加载运行策略设置列表带分页", notes = "加载运行策略设置列表带分页")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "modelTemplateId", value = "运行策略id", required = false, dataType = "String"),
        @ApiImplicitParam(name = "profCode", value = "专业代码", required = false, dataType = "String"),
        @ApiImplicitParam(name = "deviceCode", value = "设备编码", required = false, dataType = "String"),
        @ApiImplicitParam(name = "spaceCode", value = "位置编码", required = false, dataType = "String"),
        @ApiImplicitParam(name = "classCode", value = "类型编码", required = false, dataType = "String"),
    })
	@RequestMapping(value = "/getList",method=RequestMethod.POST)
	public CommonResponse getList(
			String modelTemplateId,
			String profCode,
			String deviceCode,
			String spaceCode,
			String classCode,

			HttpServletRequest request, HttpServletResponse response) throws IOException {
		RuagWorkModelDatail workModel = new RuagWorkModelDatail();
		//设置查询条件
		Map<String,String> sqlMap = new HashMap<String,String>();
		if(!("".equals(spaceCode))&&!(spaceCode.equals(null))) {
			sqlMap.put("sql", " AND A.space_code IN("+spaceCode.substring(0, spaceCode.length()-1)+")");
			workModel.setSqlMap(sqlMap);
		}
		workModel.setDeviceCode(deviceCode);
		workModel.setModelTemplateId(modelTemplateId);
		workModel.setProfCode(profCode);
		workModel.setClassCode(classCode);
		return ResponseUtil.successResponse(ruagWorkModelDatailService.getList(workModel,new Page<RuagWorkModelDatail>(request, response)));
	}
	
	
	@RequestMapping(value = "/getListById",method=RequestMethod.POST)
	public CommonResponse getListById(
			String modelTemplateId,
			String profName ,
			String deviceName,

			@RequestParam(value = "pageNo", required = true,defaultValue="0") Integer pageNo ,
			@RequestParam(value = "pageSize", required = true,defaultValue="10") Integer pageSize,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		RuagWorkModelDatail workModel = new RuagWorkModelDatail();
		String ids="";
		if(StringUtils.isNotBlank(modelTemplateId)){
			String modelTemplateIds[]=modelTemplateId.split(",");
			for(String  id:modelTemplateIds) {
				ids+="'"+id+"',";
			}
				ids="("+ids.substring(0, ids.length()-1)+")";
		}
		workModel.setModelTemplateId(ids);
		workModel.setProfName(profName);
		workModel.setDeviceName(deviceName);
	//	Page  page1  =ruagWorkModelDatailService.findPage(new Page<RuagWorkModelDatail>(pageNo,pageSize), workModel);
		
		
		Page<RuagWorkModelDatail> page =new Page<RuagWorkModelDatail>(pageNo,pageSize);
		workModel.setPage(page);
		workModel.preGet();
		List<RuagWorkModelDatail>  list  =ruagWorkModelDatailService.getListByIds(workModel);
		page.setList(list);
		return ResponseUtil.successResponse(page);
	}
	/**
	 * 
	    * @Title: delete  
	    * @com.sgai.property.commonService.vo;(删除运行策略设置)
	    * @param @param ids 运行策略设置id集合（表现为字符串，以逗号隔开）
	    * @param @param token
	    * @param @param request
	    * @param @param response
	    * @param @return
	    * @param @throws IOException
	    * @param @throws ServletException    参数  
	    * @return CommonResponse    返回类型  
	    * @throws
	 */
		@ApiOperation(value = "删除运行策略设置", notes = "删除运行策略设置")
		@ApiImplicitParams({
	        @ApiImplicitParam(name = "ids", value = "运行策略设置id集合（表现为字符串，以逗号隔开）", required = false, dataType = "String"),
	    })
		@RequestMapping(value = "/delete",method=RequestMethod.POST)
	public CommonResponse delete(
			String ids,

			HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String,String> result = new HashMap<String,String>();
		try {
			ruagWorkModelDatailService.delete(ids);
			result.put("msg", "success");
		} catch (Exception e) {

			result.put("msg", "faild");
		}
		return ResponseUtil.successResponse(result);
	}
		/**
		 * 
		    * @Title: getWorkmodelParameters  
		    * @com.sgai.property.commonService.vo;(查看某个运行策略设置下的参数配置)
		    * @param @param id  运行策略设置id集合（表现为字符串，以逗号隔开）
		    * @param @param token
		    * @param @param request
		    * @param @param response
		    * @param @return
		    * @param @throws IOException
		    * @param @throws ServletException    参数  
		    * @return CommonResponse    返回类型  
		    * @throws
		 */
		@ApiOperation(value = "查看某个运行策略设置下的参数配置", notes = "查看某个运行策略设置下的参数配置")
		@ApiImplicitParams({
	        @ApiImplicitParam(name = "id", value = "运行策略设置id集合（表现为字符串，以逗号隔开）", required = false, dataType = "String"),
	    })
		@RequestMapping(value = "/getWorkmodelParameters",method=RequestMethod.POST)
		public CommonResponse getWorkmodelParameters(
			String id,

			HttpServletRequest request, HttpServletResponse response) throws IOException {
		return ResponseUtil.successResponse(ruagWorkModelDatailService.getWorkmodelParameters(id));
	}
	/**
	 * 
	    * @Title: getParametersOfEqmBelongOneType  
	    * @com.sgai.property.commonService.vo;(查找某个设备类型的参数)
	    * @param @param classCode 设备类型代码
	    * @param @param token
	    * @param @param request
	    * @param @param response
	    * @param @return
	    * @param @throws IOException
	    * @param @throws ServletException    参数  
	    * @return CommonResponse    返回类型  
	    * @throws
	 */
		@ApiOperation(value = "查找某个设备类型的参数", notes = "查找某个设备类型的参数")
		@ApiImplicitParams({
	        @ApiImplicitParam(name = "deviceCodes", value = "设备代码", required = false, dataType = "String"),
	    })
		@RequestMapping(value = "/getParametersOfEqmBelongOneType",method=RequestMethod.POST)
		public CommonResponse getParametersOfEqmBelongOneType(
			String deviceCodes,

			HttpServletRequest request, HttpServletResponse response) throws IOException {
		return ResponseUtil.successResponse(ruagWorkModelDatailService.getParametersOfEqmBelongOneType(deviceCodes));
	}
		/**
		 * 
		    * @Title: setAndSaveModels  
		    * @com.sgai.property.commonService.vo;(保存运行策略设置)
		    * @param @param deviceCodes
		    * @param @param onOffParameterCode
		    * @param @param onOffParameterName
		    * @param @param openTime
		    * @param @param closeTime
		    * @param @param parameterNames
		    * @param @param parameterIds
		    * @param @param parameterValues
		    * @param @param timePoints
		    * @param @param ruagWorkModelDatail
		    * @param @param remark
		    * @param @param token
		    * @param @param request
		    * @param @param response
		    * @param @return
		    * @param @throws IOException
		    * @param @throws ServletException    参数  
		    * @return CommonResponse    返回类型  
		    * @throws
		 */
		@ApiOperation(value = "保存运行策略设置", notes = "保存运行策略设置")
	/*	@ApiImplicitParams({
	        @ApiImplicitParam(name = "deviceCodes", value = "设备代码(字符串形式，以逗号隔开)", required = false, dataType = "String"),
	        @ApiImplicitParam(name = "onOffParameterCode", value = "开关状态代码", required = false, dataType = "String"),
	        @ApiImplicitParam(name = "openTime", value = "设备类型代码", required = false, dataType = "String"),
	        @ApiImplicitParam(name = "closeTime", value = "设备类型代码", required = false, dataType = "String"),
	    })*/
		@RequestMapping(value = "/setAndSaveModels",method=RequestMethod.POST)
		public CommonResponse setAndSaveModels(
				String deviceCodes,String onOffParameterCode,String onOffParameterName,
	    		String openTime,String closeTime,
	    		String parameterNames,
				String parameterIds,
				String parameterValues,
				String timePoints,
	    		RuagWorkModelDatail ruagWorkModelDatail,
	    		String remark,

			    HttpServletRequest request, HttpServletResponse response) throws IOException {
			Map<String,String> result = new HashMap<String,String>();
			try {
				result=ruagWorkModelDatailService.setAndSaveModels(deviceCodes, onOffParameterCode, onOffParameterName,
						openTime, closeTime,
						parameterNames,
						parameterIds,
						parameterValues,
						timePoints, ruagWorkModelDatail);
			} catch (Exception e) {

				e.printStackTrace();
				result.put("msg", "faild");
			}
		
			return ResponseUtil.successResponse(result);
	}
	/**
	 * 
	 * getDeviceList:(根据条件查询设备).
	 * @param mdmDevicesUseInfo
	 * @param token
	 * @return
	 * @throws JsonProcessingException :CommonResponse 
	 * @since JDK 1.8
	 * @author 王天尧
	 */
    @RequestMapping(value = "/getDeviceList",method=RequestMethod.POST)
    public CommonResponse getDeviceList(
    		MdmDevicesUseInfo mdmDevicesUseInfo,

    		HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException{
    	return	ResponseUtil.successResponse(mdmDevicesUseInfoService.findPage(new Page<MdmDevicesUseInfo>(request, response),mdmDevicesUseInfo));
    }
    /**
     * 
     * getWorkModelTree:(得到所有的运行策略设置).
     * @param id 运行策略id
     * @param token
     * @param request
     * @param response
     * @return
     * @throws JsonProcessingException :CommonResponse 
     * @since JDK 1.8
     * @author 王天尧
     */
    @RequestMapping(value = "/getWorkModelTree",method=RequestMethod.POST)
    public CommonResponse getWorkModelTree(
    		String id,

    		HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException{
    	return	ResponseUtil.successResponse(ruagWorkModelDatailService.getWorkModelTree(id));
    }
    /**
     * 
     * getOwnTree:(得到已经拥有的运行策略设置).
     * @param id 运行策略id
     * @param token
     * @param request
     * @param response
     * @return
     * @throws JsonProcessingException :CommonResponse 
     * @since JDK 1.8
     * @author 王天尧
     */
    @RequestMapping(value = "/getOwnTree",method=RequestMethod.POST)
    public CommonResponse getOwnTree(
    		String id,

    		HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException{
    	return	ResponseUtil.successResponse(ruagWorkModelDatailService.getOwnModels(id));
    }
    /**
     * 
        * @Title: getParametersByClassCode  
        * @com.sgai.property.commonService.vo;(根据设备类型查找参数)
        * @param @param classCode 设备类型代码
        * @param @param token
        * @param @param request
        * @param @param response
        * @param @return
        * @param @throws JsonProcessingException    参数  
        * @return CommonResponse    返回类型  
        * @throws
     */
    @RequestMapping(value = "/getParametersByClassCode",method=RequestMethod.POST)
    public CommonResponse getParametersByClassCode(
    		String classCode,

    		HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException{
    	return	ResponseUtil.successResponse(ruagWorkModelDatailService.getParametersByClassCode(classCode));
    }
    /**
     * 
        * @Title: updateModels  
        * @com.sgai.property.commonService.vo;(更新运行策略设置参数)
        * @param @param ids
        * @param @param onOffParameterCode
        * @param @param onOffParameterName
        * @param @param openTime
        * @param @param closeTime
        * @param @param parameterNames
        * @param @param parameterIds
        * @param @param parameterValues
        * @param @param timePoints
        * @param @param ruagWorkModelDatail
        * @param @param remark
        * @param @param token
        * @param @param request
        * @param @param response
        * @param @return
        * @param @throws JsonProcessingException    参数  
        * @return CommonResponse    返回类型  
        * @throws
     */
    @RequestMapping(value = "/updateModels",method=RequestMethod.POST)
    public CommonResponse updateModels(
    		String modelIds,String onOffParameterCode,String onOffParameterName,
    		String openTime,String closeTime,
    		String parameterNames,
			String parameterIds,
			String parameterValues,
			String timePoints,
    		RuagWorkModelDatail ruagWorkModelDatail,
    		String remark,

    		HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException{
    	Map<String,String> result = new HashMap<String,String>();
    	try {
    		ruagWorkModelDatailService.updateModels(modelIds, onOffParameterCode, onOffParameterName, 
    				openTime, closeTime, parameterNames, parameterIds,
    				parameterValues, timePoints, ruagWorkModelDatail);
    		result.put("msg", "success");
		} catch (Exception e) {

			e.printStackTrace();
			result.put("msg", "faild");
		}
    	return	ResponseUtil.successResponse(result);
    }
}