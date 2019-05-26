package com.sgai.property.mdm.web;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.sgai.common.persistence.Page;
import com.sgai.common.web.BaseController;
import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.modules.login.jwt.util.CommonResponse;
import com.sgai.modules.login.jwt.util.ResponseUtil;
import com.sgai.property.mdm.dto.MdmDevicesUseInfoVo;
import com.sgai.property.mdm.entity.MdmAttribute;
import com.sgai.property.mdm.service.MdmAttributeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 
    * ClassName: MdmDeviceClassController  
    * com.sgai.property.commonService.vo;(设备类型维护)
    * @author yangyz  
    * Date 2017年11月24日  
    * Company 首自信--智慧城市创新中心
 */
@RestController
@RequestMapping(value = "${adminPath}/mdm/deviceAttribute/mdmAttribute")
@Api(description = "设备分类接口")
public class MdmAttributeController extends BaseController {

	@Autowired
	private MdmAttributeService mdmAttributeService;
	
	/**
	 * 
	 * getListDeviceClass:(查询设备类型列表).
	 * @param mdmDeviceClass
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException :Page<MdmDeviceClass> 
	 * @since JDK 1.8
	 * @author yangyz
	 */
	@ApiOperation(value = "获得设备分类分页列表信息", httpMethod = "POST", notes = "获得设备专业分页列表信息")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "className", value = "设备分类名称", required = false,paramType = "query", dataType = "String")
	})
	@RequestMapping(value = "/getListDeviceAttr", method=RequestMethod.POST)
	public CommonResponse getListDeviceClass(
			LoginUser user,
			String devicesCode,

			@RequestParam(value = "pageNo", required = true,defaultValue="0") Integer pageNo ,
			@RequestParam(value = "pageSize", required = true,defaultValue="10") Integer pageSize,
			HttpServletRequest request, 
			HttpServletResponse response
			) throws IOException {
		MdmAttribute mdmAttribute = new MdmAttribute();
		mdmAttribute.setDevicesCode(devicesCode);
		Page<MdmAttribute> page = mdmAttributeService.findPage(new Page<MdmAttribute>(pageNo, pageSize), mdmAttribute);
		return ResponseUtil.successResponse(page);
	}
	
	
	@RequestMapping(value = "/getAttr", method=RequestMethod.POST)
	public CommonResponse getAttr(
			String id, 

			HttpServletRequest request, 
			HttpServletResponse response
			) throws IOException {
		MdmDevicesUseInfoVo mdmDevicesUseInfoVo = mdmAttributeService.getAttribute(id);
		return ResponseUtil.successResponse(mdmDevicesUseInfoVo);
	}
	

	@ApiOperation(value = "保存", httpMethod = "POST", notes = "保存")
	@RequestMapping(value = "save", method=RequestMethod.POST)
	public CommonResponse save(
			MdmAttribute mdmAttribute, 

			RedirectAttributes redirectAttributes
			) throws JsonProcessingException {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			 mdmAttributeService.save(mdmAttribute);
			 map.put("msg", "success");
		} catch (Exception e) {

			e.printStackTrace();
			map.put("msg", "fail");
		}
		return ResponseUtil.successResponse(map);
	}
	
	
	@ApiOperation(value = "保存", httpMethod = "POST", notes = "保存")
	@RequestMapping(value = "saveParams", method=RequestMethod.POST)
	public CommonResponse saveParams(
			String devicesCode,
			String deviceParams,
			@RequestHeader("token") String token
		//	RedirectAttributes redirectAttributes
			) throws JsonProcessingException {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map=mdmAttributeService.saveDeviceAttrParameter(devicesCode, deviceParams);
		} catch (Exception e) {

			e.printStackTrace();
			map.put("msg", "fail");
		}
		return ResponseUtil.successResponse(map);
	}
	
	/**
	 * 
	 * delete:(批量删除设备类型).
	 * @param ids
	 * @param redirectAttributes
	 * @return :Map<String,Object> 
	 * @since JDK 1.8
	 * @author lenovo
	 * @throws JsonProcessingException 
	 */
	@ApiOperation(value = "删除设备分类信息", httpMethod = "POST", notes = "删除设备专业信息")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "ids", value = "多个主键，逗号分隔", required = false,paramType = "query", dataType = "String")
	})
	@RequestMapping(value = "delete", method=RequestMethod.POST)
	public CommonResponse delete(
			String id, 

			RedirectAttributes redirectAttributes
			) throws JsonProcessingException {
		Map<String, Object> map = new HashMap<String, Object>(); 
		try {
			MdmAttribute mdmAttribute=new MdmAttribute();
			mdmAttribute.setId(id);
			 mdmAttributeService.delete(mdmAttribute);
			 map.put("msg", "success");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", "出错了！");
			map.put("result", "fail");
		}
		return ResponseUtil.successResponse(map);
	}

}