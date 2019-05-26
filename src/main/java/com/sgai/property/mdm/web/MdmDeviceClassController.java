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
import com.sgai.property.mdm.entity.MdmDeviceClass;
import com.sgai.property.mdm.service.MdmDeviceClassService;
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
@RequestMapping(value = "${adminPath}/mdm/deviceclass/mdmDeviceClass")
@Api(description = "设备分类接口")
public class MdmDeviceClassController extends BaseController {

	@Autowired
	private MdmDeviceClassService mdmDeviceClassService;
	
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
	@RequestMapping(value = "/getListDeviceClass", method=RequestMethod.POST)
	public CommonResponse getListDeviceClass(
			LoginUser user,
			String className,
			String profCode,
			String classCode,

			@RequestParam(value = "pageNo", required = true,defaultValue="0") Integer pageNo ,
			@RequestParam(value = "pageSize", required = true,defaultValue="10") Integer pageSize,
			HttpServletRequest request, 
			HttpServletResponse response
			) throws IOException {
		MdmDeviceClass mdmDeviceClass = new MdmDeviceClass();
		mdmDeviceClass.setClassName(className);
		mdmDeviceClass.setClassCode(classCode);
		mdmDeviceClass.setProfCode(profCode);
		Page<MdmDeviceClass> page = mdmDeviceClassService.findPage(new Page<MdmDeviceClass>(pageNo, pageSize), mdmDeviceClass);
		return ResponseUtil.successResponse(page);
	}
	/**
	 * 
	 * getAllList:(获取所有的类型).
	 * @param mdmDeviceClass
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException :List<MdmDeviceClass> 
	 * @since JDK 1.8
	 * @author yangyz
	 */
	@ApiOperation(value = "获得设备分类列表信息", httpMethod = "POST", notes = "获得设备专业列表信息")
	@RequestMapping(value = "/getAllList", method=RequestMethod.POST)
	public CommonResponse getAllList(
			MdmDeviceClass mdmDeviceClass,

			HttpServletRequest request, 
			HttpServletResponse response) throws IOException {
		 mdmDeviceClass.setEnabledFlag("Y");
		 List<MdmDeviceClass> findList = mdmDeviceClassService.findList(mdmDeviceClass);
		return ResponseUtil.successResponse(findList);
	}
	/**
	 * 
	 * getDeviceClass:(查询一条设备类型信息).
	 * @param request
	 * @param id
	 * @param response
	 * @return
	 * @throws IOException :MdmDeviceClass 
	 * @since JDK 1.8
	 * @author yangyz
	 */
	@ApiOperation(value = "获得设备分类信息", httpMethod = "POST", notes = "获得设备专业信息")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "设备分类主键", required = false,paramType = "query", dataType = "String")
	})
	@RequestMapping(value = "/getDeviceClass", method=RequestMethod.POST)
	public CommonResponse getDeviceClass(
			String id,

			HttpServletRequest request, 
			HttpServletResponse response
			) throws IOException {
		MdmDeviceClass deviceClass = mdmDeviceClassService.get(id);
		return ResponseUtil.successResponse(deviceClass);
	}
	
	/**
	 * 
	 * save:(新增或修改保存设备类型数据).
	 * @param mdmDeviceClass
	 * @param model
	 * @param redirectAttributes
	 * @return :Map<String,Object> 
	 * @since JDK 1.8
	 * @author yangyz
	 * @throws JsonProcessingException 
	 */
	@ApiOperation(value = "保存设备分类信息", httpMethod = "POST", notes = "保存设备专业信息")
	@RequestMapping(value = "save", method=RequestMethod.POST)
	public CommonResponse save(
			MdmDeviceClass mdmDeviceClass, 

			RedirectAttributes redirectAttributes
			) throws JsonProcessingException {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map = mdmDeviceClassService.saveDeviceClass(mdmDeviceClass);
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
			String ids, 

			RedirectAttributes redirectAttributes
			) throws JsonProcessingException {
		Map<String, Object> map = new HashMap<String, Object>(); 
		try {
			map = mdmDeviceClassService.deleteDeviceClass(ids);
		} catch (Exception e) {

			e.printStackTrace();
			map.put("msg", "出错了！");
			map.put("result", "fail");
		}
		return ResponseUtil.successResponse(map);
	}

}