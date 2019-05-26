package com.sgai.property.ruag.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sgai.common.web.BaseController;
import com.sgai.modules.login.jwt.util.CommonResponse;
import com.sgai.modules.login.jwt.util.ResponseUtil;
import com.sgai.property.ruag.service.RuagLinkageDeviceParamSetService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 模式设备参数设置Controller
 * @author yangyz
 * @version 2018-01-02
 */
@RestController
@RequestMapping(value = "ruag/ruag/ruagLinkageDeviceParamSet")
public class RuagLinkageDeviceParamSetController extends BaseController {

	@Autowired
	private RuagLinkageDeviceParamSetService ruagLinkageDeviceParamSetService;

	@ApiOperation(value = "保存设备运行参数信息", httpMethod = "POST", notes = "保存设备运行参数信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "linkageRuleId", value = "联动id", required = false,paramType = "query", dataType = "String"),
	    @ApiImplicitParam(name = "masterName", value = "表名", required = false,paramType = "query", dataType = "String"),
	    @ApiImplicitParam(name = "profCode", value = "专业代码", required = false,paramType = "query", dataType = "String"),
	    @ApiImplicitParam(name = "profName", value = "专业名称", required = false,paramType = "query", dataType = "String"),
	    @ApiImplicitParam(name = "deviceClassCode", value = "设备类型", required = false,paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "deviceCode", value = "设备代码", required = false,paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "deviceParams", value = "设备运行参数", required = false,paramType = "query", dataType = "String")
	})
	@RequestMapping(value = "save",method=RequestMethod.POST)
	public CommonResponse save(
			String linkageRuleId,
			String masterName,
			String profCode,
			String profName,
			String deviceCode,
			String deviceClassCode,
			String deviceParams,
			String switchFlag,
			RedirectAttributes redirectAttributes
			) throws JsonProcessingException {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map = ruagLinkageDeviceParamSetService.saveDeviceParamSet( linkageRuleId,  masterName, profCode, profName, deviceClassCode,  deviceCode,  deviceParams,switchFlag);
		} catch (Exception e) {

			e.printStackTrace();
			map.put("msg", "fail");
		}
		return ResponseUtil.successResponse(map);
	}
	/**
	 *
	    * @Title: getWorkmodelParameters
	    * @com.sgai.property.commonService.vo;(查看联动规则设备的参数配置)
	    * @param @param id
	    * @param @param token
	    * @param @param request
	    * @param @param response
	    * @param @return
	    * @param @throws IOException
	    * @param @throws ServletException    参数
	    * @return CommonResponse    返回类型
	    * @throws
	 */
	@ApiOperation(value = "查看联动规则设备的参数配置", notes = "查看联动规则设备的参数配置")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "联动设备业务id", required = false, dataType = "String"),
    })
	@RequestMapping(value = "/getLinkParameters",method=RequestMethod.POST)
	public CommonResponse getWorkmodelParameters(
		String id,

		HttpServletRequest request, HttpServletResponse response) throws IOException {
	return ResponseUtil.successResponse(ruagLinkageDeviceParamSetService.getLinkParameters(id));
}
	/**
	 *
	    * @Title: saveCheckedParameters
	    * @com.sgai.property.commonService.vo;(保存联动规则设备的参数配置)
	    * @param @param masterName
	    * @param @param classCode
	    * @param @param linkageRuleId
	    * @param @param LinkDeviceIds
	    * @param @param deviceParams
	    * @param @param token
	    * @param @param request
	    * @param @param response
	    * @param @return
	    * @param @throws IOException
	    * @param @throws ServletException    参数
	    * @return CommonResponse    返回类型
	    * @throws
	 */
	@ApiOperation(value = "保存联动规则设备的参数配置", notes = "保存联动规则设备的参数配置")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "masterName", value = "联动设备标识，前置设备是RUSG_LINKAAGE_FRONT_DEVICE，联动设备是RUSG_LINKAAGE_NEXT_DEVICE", required = false, dataType = "String"),
        @ApiImplicitParam(name = "classCode", value = "设备类型编码", required = false, dataType = "String"),
        @ApiImplicitParam(name = "linkageRuleId", value = "联动规则id", required = false, dataType = "String"),
        @ApiImplicitParam(name = "LinkDeviceIds", value = "联动设备id集合", required = false, dataType = "String"),
        @ApiImplicitParam(name = "deviceParams", value = "参数设置", required = false, dataType = "String"),
    })
	@RequestMapping(value = "/saveCheckedParameters",method=RequestMethod.POST)
	public CommonResponse saveCheckedParameters(
		String masterName,String classCode,String linkageRuleId,String LinkDeviceIds, String deviceParams,

		HttpServletRequest request, HttpServletResponse response) throws IOException {
	return ResponseUtil.successResponse(ruagLinkageDeviceParamSetService.saveCheckedParamSet(masterName, classCode, linkageRuleId, LinkDeviceIds, deviceParams));
}
}
