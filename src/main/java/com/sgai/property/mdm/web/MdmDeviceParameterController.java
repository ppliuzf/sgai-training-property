package com.sgai.property.mdm.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.sgai.common.persistence.Page;
import com.sgai.common.web.BaseController;
import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.modules.login.jwt.util.CommonResponse;
import com.sgai.modules.login.jwt.util.ResponseUtil;
import com.sgai.property.mdm.entity.MdmDeviceParameter;
import com.sgai.property.mdm.service.MdmDeviceParameterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 
    * ClassName: MdmDeviceParameterController  
    * com.sgai.property.commonService.vo;(设备运行参数Controller)
    * @author yangyz  
    * Date 2017年12月30日  
    * Company 首自信--智慧城市创新中心
 */
@RestController
@RequestMapping(value = "${adminPath}/mdm/mdmdeviceparameter/mdmDeviceParameter")
@Api(description = "设备运行参数接口")
public class MdmDeviceParameterController extends BaseController {

	@Autowired
	private MdmDeviceParameterService mdmDeviceParameterService;

	/**
	 * 
	 * getListDeviceParameter:(获得设备运行参数分页列表信息).
	 * @param user
	 * @param classCode
	 * @param token
	 * @param pageNo
	 * @param pageSize
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException :CommonResponse 
	 * @since JDK 1.8
	 * @author yangyz
	 */
	@ApiOperation(value = "获得设备运行参数分页列表信息", httpMethod = "POST", notes = "获得设备运行参数分页列表信息")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "classCode", value = "设备分类代码", required = false,paramType = "query", dataType = "String")
	})
	@RequestMapping(value = "/getListDeviceParameter", method=RequestMethod.POST)
	public CommonResponse getListDeviceParameter(
			LoginUser user,
			String classCode,
			String profCode,

			@RequestParam(value = "pageNo", required = true,defaultValue="0") Integer pageNo ,
			@RequestParam(value = "pageSize", required = true,defaultValue="10") Integer pageSize,
			HttpServletRequest request, 
			HttpServletResponse response
			) throws IOException {
		MdmDeviceParameter mdmDeviceParameter = new MdmDeviceParameter();
		mdmDeviceParameter.setDeviceClassCode(classCode);
		mdmDeviceParameter.setDeviceProfCode(profCode);
		
		Page<MdmDeviceParameter> page = mdmDeviceParameterService.findPage(new Page<MdmDeviceParameter>(pageNo, pageSize), mdmDeviceParameter);
		return ResponseUtil.successResponse(page);
	}
	
	/**
	 * 
	 * getDeviceParameter:(获得设备运行参数信息).
	 * @param id
	 * @param token
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException :CommonResponse 
	 * @since JDK 1.8
	 * @author yangyz
	 */
	@ApiOperation(value = "获得设备运行参数信息", httpMethod = "POST", notes = "获得设备运行参数信息")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "主键", required = false,paramType = "query", dataType = "String")
	})
	@RequestMapping(value = "/getDeviceParameter",method=RequestMethod.POST)
	public CommonResponse getDeviceParameter(
			String id,

			HttpServletRequest request, 
			HttpServletResponse response
			) throws IOException {
		MdmDeviceParameter info = mdmDeviceParameterService.get(id);
		return ResponseUtil.successResponse(info);
	}
	
	/**
	 * 
	 * save:(保存设备运行参数信息).
	 * @param deviceProfCode
	 * @param deviceProfName
	 * @param deviceClassCode
	 * @param deviceClassName
	 * @param deviceParams
	 * @param remarks
	 * @param token
	 * @param redirectAttributes
	 * @return
	 * @throws JsonProcessingException :CommonResponse 
	 * @since JDK 1.8
	 * @author yangyz
	 */
	@ApiOperation(value = "保存设备运行参数信息", httpMethod = "POST", notes = "保存设备运行参数信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "profCode", value = "设备专业代码", required = false,paramType = "query", dataType = "String"),
	    @ApiImplicitParam(name = "profName", value = "设备专业名称", required = false,paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "classCode", value = "设备分类代码", required = false,paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "className", value = "设备分类名称", required = false,paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "deviceParams", value = "设备运行参数组", required = false,paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "remarks", value = "设备运行参数备注", required = false,paramType = "query", dataType = "String")
	})
	@RequestMapping(value = "save",method=RequestMethod.POST)
	public CommonResponse save(
			String deviceProfCode,
			String deviceProfName,
			String deviceClassCode,
			String deviceClassName,
			String deviceParams,
			String remarks,

			RedirectAttributes redirectAttributes
			) throws JsonProcessingException {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map = mdmDeviceParameterService.saveDeviceParameter(deviceProfCode, deviceProfName ,deviceClassCode, deviceClassName, deviceParams, remarks);
		} catch (Exception e) {

			e.printStackTrace();
			map.put("msg", "fail");
		}
		return ResponseUtil.successResponse(map);
	}
	
	/**
	 * 
	 * update:(这里用一句话描述这个方法的作用).
	 * @param id
	 * @param deviceProfCode
	 * @param deviceProfName
	 * @param deviceClassCode
	 * @param deviceClassName
	 * @param paramCode
	 * @param paramName
	 * @param remarks
	 * @param token
	 * @param redirectAttributes
	 * @return
	 * @throws JsonProcessingException :CommonResponse 
	 * @since JDK 1.8
	 * @author lenovo
	 */
	@ApiOperation(value = "修改设备运行参数信息", httpMethod = "POST", notes = "修改设备运行参数信息")
	@RequestMapping(value = "update",method=RequestMethod.POST)
	public CommonResponse update(
			String id,
			String paramCode,
			String paramName,
			String remarks,

			RedirectAttributes redirectAttributes
			) throws JsonProcessingException {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			MdmDeviceParameter info = mdmDeviceParameterService.get(id);
			info.setParamCode(paramCode);
			info.setParamName(paramName);
			info.setRemarks(remarks);
			mdmDeviceParameterService.save(info);
			map.put("classCode", info.getDeviceClassCode());
			map.put("msg", "success");
		} catch (Exception e) {

			e.printStackTrace();
			map.put("msg", "fail");
		}
		return ResponseUtil.successResponse(map);
	}
	
	/**
	 * 
	 * delete:(删除设备运行参数信息).
	 * @param ids
	 * @param token
	 * @param redirectAttributes
	 * @return
	 * @throws JsonProcessingException :CommonResponse 
	 * @since JDK 1.8
	 * @author yangyz
	 */
	@ApiOperation(value = "删除设备运行参数信息", httpMethod = "POST", notes = "删除设备运行参数信息")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "ids", value = "多个主键，逗号分隔", required = false,paramType = "query", dataType = "String")
	})
	@RequestMapping(value = "delete",method=RequestMethod.POST)
	public CommonResponse delete(
			String ids, 

			RedirectAttributes redirectAttributes
			) throws JsonProcessingException {
		Map<String, Object> map = new HashMap<String, Object>(); 
		try {
			map = mdmDeviceParameterService.deleteDeviceParameter(ids);
		} catch (Exception e) {

			e.printStackTrace();
			map.put("msg", "出错了！");
			map.put("result", "fail");
		}
		return ResponseUtil.successResponse(map);
	}
	@ApiOperation(value = "根据设备代码查询", httpMethod = "POST", notes = "根据设备代码查询")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "deviceClassCode", value = "设备类型代码", required = false,paramType = "query", dataType = "String")
	})
	@RequestMapping(value = "findByDeviceClassCode",method=RequestMethod.POST)
	public CommonResponse findByDeviceClassCode(
			String deviceClassCode,

			RedirectAttributes redirectAttributes
			) throws JsonProcessingException {
		Map<String, Object> map = new HashMap<String, Object>();
		MdmDeviceParameter info = new MdmDeviceParameter();
		info.setDeviceClassCode(deviceClassCode);
		try {
			List<MdmDeviceParameter>  list = mdmDeviceParameterService.findList(info);
			map.put("data", list);
			map.put("msg", "success");
		} catch (Exception e) {

			e.printStackTrace();
			map.put("msg", "fail");
		}
		return ResponseUtil.successResponse(map);
	}
}