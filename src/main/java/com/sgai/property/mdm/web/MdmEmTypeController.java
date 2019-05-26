package com.sgai.property.mdm.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.collect.Maps;
import com.sgai.common.config.Global;
import com.sgai.common.persistence.Page;
import com.sgai.common.utils.StringUtils;
import com.sgai.common.web.BaseController;
import com.sgai.modules.login.jwt.util.CommonResponse;
import com.sgai.modules.login.jwt.util.ResponseUtil;
import com.sgai.property.mdm.entity.MdmBrandInfo;
import com.sgai.property.mdm.entity.MdmEmType;
import com.sgai.property.mdm.entity.MdmSupplierInfo;
import com.sgai.property.mdm.service.MdmEmTypeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 事件类别维护Controller
 * @author liushang
 * @version 2017-12-05
 */
@RestController
@RequestMapping(value = "${adminPath}/mdmEmType")
@Api(description = "事件类别维护接口")
public class MdmEmTypeController extends BaseController {

	@Autowired
	private MdmEmTypeService mdmEmTypeService;
	
	@ModelAttribute
	public MdmEmType get(@RequestParam(required=false) String id) {
		MdmEmType entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = mdmEmTypeService.get(id);
		}
		if (entity == null){
			entity = new MdmEmType();
		}
		return entity;
	}
	
	//@RequiresPermissions("emtype:事件类别:mdmEmType:view")
	@RequestMapping(value = {"list", ""})
	public String list(MdmEmType mdmEmType, HttpServletRequest request, HttpServletResponse response, Model model) {
		if(mdmEmType==null){
			mdmEmType=new MdmEmType();
		}
		Page<MdmEmType> page = mdmEmTypeService.findPage(new Page<MdmEmType>(request, response), mdmEmType); 
		model.addAttribute("page", page);
		return "mdm/emtype/事件类别/mdmEmTypeList";
	}

	//@RequiresPermissions("emtype:事件类别:mdmEmType:view")
	@RequestMapping(value = "form")
	public String form(MdmEmType mdmEmType, Model model) {
		model.addAttribute("mdmEmType", mdmEmType);
		return "mdm/emtype/事件类别/mdmEmTypeForm";
	}

	@ApiOperation(value = "获得事件类型信息", httpMethod = "POST", notes = "获得事件类型信息")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "主键", required = false,paramType = "query", dataType = "String")
	})
	@RequestMapping(value = "/getEmType",method=RequestMethod.POST)
	public CommonResponse getEmType(
			String id,

			HttpServletRequest request, 
			HttpServletResponse response
			) throws IOException {
		MdmEmType mdmEmType = mdmEmTypeService.get(id);
		return ResponseUtil.successResponse(mdmEmType);
	}
	
	//@RequiresPermissions("emtype:事件类别:mdmEmType:edit")
	@RequestMapping(value = "save",method=RequestMethod.POST)
	@ApiOperation(value = "事件类别信息保存", notes = "事件类别信息保存")
	public CommonResponse save(MdmEmType mdmEmType,HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String,Object> result = Maps.newHashMap();
	//	try {
		mdmEmTypeService.save(mdmEmType);
		result.put("msg", "success");//成功返回msg值为success的map
		//}
		//catch(Exception e) {
		//	result.put("msg", "fail");//失败返回msg值为fail的map
	//	}
		return ResponseUtil.successResponse(result);
		
	}
	
	//@RequiresPermissions("emtype:事件类别:mdmEmType:edit")
	@RequestMapping(value = "delete",method=RequestMethod.POST)
	@ApiOperation(value = "事件类别信息删除", notes = "事件类别信息删除")
	public CommonResponse delete(String ids, RedirectAttributes redirectAttributes) throws JsonProcessingException {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map = mdmEmTypeService.delete(ids);
		} catch (Exception e) {

			e.printStackTrace();
			map.put("msg", "error");
		}
		return ResponseUtil.successResponse(map);
	}
	
	@RequestMapping(value = "/getListEmType",method=RequestMethod.POST)
	@ApiOperation(value = "获取事件类别信息列表", notes = "获取事件类别信息列表")
	@ApiImplicitParams({
            @ApiImplicitParam(name = "emType_name", value = "事件类别名", required = false,paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "id", value = "id", required = false,paramType = "query", dataType = "String"),
    })
	public CommonResponse getListEmType(
			 String emType_name, String id,
			 @RequestParam(value = "pageNo", required = true,defaultValue="0") Integer pageNo ,
			 @RequestParam(value = "pageSize", required = true,defaultValue="10") Integer pageSize,

			 HttpServletRequest request, HttpServletResponse response) throws IOException {
		MdmEmType mdmEmType = new MdmEmType();
		mdmEmType.setId(id);
		mdmEmType.setEmTypeName(emType_name);
		Page<MdmEmType> page = mdmEmTypeService.findPage(new Page<MdmEmType>(pageNo, pageSize), mdmEmType); //使用entity入参findpage
		return ResponseUtil.successResponse(page);
	}

}