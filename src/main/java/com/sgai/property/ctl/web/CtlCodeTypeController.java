
package com.sgai.property.ctl.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sgai.common.config.Global;
import com.sgai.common.persistence.Page;
import com.sgai.common.utils.StringUtils;
import com.sgai.common.web.BaseController;
import com.sgai.property.ctl.entity.CtlCodeType;
import com.sgai.property.ctl.service.CtlCodeTypeService;
import com.sgai.property.ctl.service.CtlDeptService;
import com.sgai.modules.login.jwt.util.CommonResponse;
import com.sgai.modules.login.jwt.util.ResponseUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 系统基础代码类别表Controller
 * @author liushang
 * @version 2017-11-13
 */
@RestController
@RequestMapping(value = "${adminPath}/ctlCodeType")
@Api(description = "字典表接口")
public class CtlCodeTypeController extends BaseController {

	@Autowired
	private CtlCodeTypeService ctlCodeTypeService;
	
	@ModelAttribute
	public CtlCodeType get(@RequestParam(required=false) String id) {
		CtlCodeType entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ctlCodeTypeService.get(id);
		}
		if (entity == null){
			entity = new CtlCodeType();
		}
		return entity;
	}
	
	//@RequiresPermissions("groupcorpcodedefine:type:ctlCodeType:view")
	@RequestMapping(value = {"list", ""})
	public String list( CtlCodeType ctlCodeType, HttpServletRequest request, HttpServletResponse response, Model model) {
		if(ctlCodeType==null){
			ctlCodeType=new CtlCodeType();
		}
		Page<CtlCodeType> page = ctlCodeTypeService.findPage(new Page<CtlCodeType>(request, response), ctlCodeType); 
		model.addAttribute("page", page);
		return "ctl/groupcorpcodedefine/type/ctlCodeTypeList";
	}

	//@RequiresPermissions("groupcorpcodedefine:type:ctlCodeType:view")
	
	@RequestMapping(value = "form")
	public String form( CtlCodeType ctlCodeType, Model model) {
		model.addAttribute("ctlCodeType", ctlCodeType);
		return "ctl/groupcorpcodedefine/type/ctlCodeTypeForm";
	}

	//@RequiresPermissions("groupcorpcodedefine:type:ctlCodeType:edit")
	@RequestMapping(value = "save")
	public String save(CtlCodeType ctlCodeType, Model model, RedirectAttributes redirectAttributes) {
		Map<String,Object> map=new HashMap<String,Object>();
		if (!beanValidator(model, ctlCodeType)){
			return form(ctlCodeType, model);
		}
		ctlCodeType.setGcFlag("G");
		try {
			map=ctlCodeTypeService.saveType(ctlCodeType);
		}
		catch(Exception e) {
			return "Conflict";
		}
		return map.get("msg").toString();
	}
	
	/**
	 * 
	    * @Title: TypeUpdate  
	    * @Description: (更新条目)
	    * @param @param ctlCodeType 携带更新信息的实例
	    * @param @param model
	    * @param @param redirectAttributes
	    * @param @return    参数  
	    * @return String    返回类型  
	    * @throws
	 */
	
	@RequestMapping(value = "TypeUpdate",method=RequestMethod.POST)
	@ApiOperation(value = "更新", notes = "更新")
	public String TypeUpdate( CtlCodeType ctlCodeType, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ctlCodeType)){
			return form(ctlCodeType, model);
		}
		ctlCodeTypeService.save(ctlCodeType);
		addMessage(redirectAttributes, "机构参数维护成功");
		return "redirect:"+Global.getAdminPath()+"/complevelparam/ctlParamComp/?repage";
	}
	
	/**
	 * 
	    * @Title: getCodeType  
	    * @Description: (从ctl_code_type中获取参数值类型的全部值)
	    * @param @param request
	    * @param @param response
	    * @param @return
	    * @param @throws IOException    参数  
	    * @return List<String>    参数值类型的字符串数组 
	    * @throws
	 */
	@RequestMapping(value = "getCodeType")
	@ApiOperation(value = "获取代码类型", notes = "获取代码类型")
	public CommonResponse getCodeType(HttpServletRequest request, HttpServletResponse response) throws IOException {
		return ResponseUtil.successResponse(ctlCodeTypeService.getCodeType());
		
	}
	
	//@RequiresPermissions("groupcorpcodedefine:type:ctlCodeType:edit")
	@RequestMapping(value = "delete")
	@ApiOperation(value = "批量删除", notes = "批量删除")
	public CommonResponse delete(String ids, RedirectAttributes redirectAttributes) throws JsonProcessingException {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map = ctlCodeTypeService.deleteParam(ids);
		} catch (Exception e) {
			// : handle exception
			e.printStackTrace();
			map.put("msg", "error");
		}
		return ResponseUtil.successResponse(map);
	}
	

	
	/**
	 * 
	    * @Title: getListType  
	    * @Description: (批量查询，根据代码类型和 GcFlag(机构组织类型标识)为"G"的条目）
	    * @param @param ctlCodeType 携带查询类型信息的实体
	    * @param @param request ajax表单请求
	    * @param @param response
	    * @param @return
	    * @param @throws IOException    参数  
	    * @return Page<CtlCodeType>    查询结果实例数组和分页信息
	    * @throws
	 */
	@RequestMapping(value = "/getListType",method=RequestMethod.POST)
	@ApiOperation(value = "分页查询", notes = "分页查询")
	public CommonResponse getListType(
			 @RequestParam(value = "code_type", required = false) String codeTypeName,
			 @RequestParam(value = "pageNo", required = true,defaultValue="0") Integer pageNo ,
			 @RequestParam(value = "pageSize", required = true,defaultValue="10") Integer pageSize,
			 HttpServletRequest request, HttpServletResponse response) throws IOException {
		CtlCodeType ctlCodeType = new CtlCodeType();
		
		System.out.println(codeTypeName);
		//System.out.println(request.getParameter("code_type").toString());
		ctlCodeType.setCodeTypeName(codeTypeName);
		if (request.getParameter("id") != "") {
			ctlCodeType.setId(request.getParameter("id"));
		}
		ctlCodeType.setGcFlag("G");
		Page<CtlCodeType> page = ctlCodeTypeService.findPage(new Page<CtlCodeType>(pageNo, pageSize), ctlCodeType);

		return ResponseUtil.successResponse(page);
	}
}