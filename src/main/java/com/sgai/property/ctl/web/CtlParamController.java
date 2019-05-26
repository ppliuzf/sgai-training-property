
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
import com.sgai.property.ctl.entity.CtlParam;
import com.sgai.property.ctl.service.CtlParamService;
import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.modules.login.jwt.util.CommonResponse;
import com.sgai.modules.login.jwt.util.ResponseUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 参数定义Controller
 * @author liushang
 * @version 2017-11-08
 */

 /**
  *    
     * @ClassName: CtlParamController  
     * @Description: (系统参数定义controller)
     * @author liushang  
     * @date 2017年11月17日  
     * @Company 首自信--智慧城市创新中心
  */
@RestController
@RequestMapping(value = "${adminPath}/ctlParam")
@Api(description = "系统参数接口")
public class CtlParamController extends BaseController {

	@Autowired
	private CtlParamService ctlParamService;
	
	/**
	 * 
	    * @Title: get  
	    * @Description: (这读取单行)
	    * @param @param id 条目的主键id字段值
	    * @param @return    参数  
	    * @return CtlParam    携带信息的实体 
	    * @throws
	 */
	/*@ModelAttribute
	public CtlParam get(@RequestParam(required=false) String id) {
		CtlParam entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ctlParamService.get(id);
		}
		if (entity == null){
			entity = new CtlParam();
		}
		return entity;
	}*/
	

	@RequestMapping(value = {"list", ""})
	public String list(CtlParam ctlParam, HttpServletRequest request, HttpServletResponse response, Model model) {
		if(ctlParam==null){
			ctlParam=new CtlParam();
		}
		Page<CtlParam> page = ctlParamService.findPage(new Page<CtlParam>(request, response), ctlParam); 
		model.addAttribute("page", page);
		return "ctl/parameter.define/ctlParamList";
	}

	
	@RequestMapping(value = "form")
	public String form(CtlParam ctlParam, Model model) {
		model.addAttribute("ctlParam", ctlParam);
		return "ctl/parameter.define/ctlParamForm";
	}

	
	@RequestMapping(value = "save")
	public String save(CtlParam ctlParam, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ctlParam)){
			return form(ctlParam, model);
		}
		ctlParamService.save(ctlParam);
		addMessage(redirectAttributes, "保存维护参数成功");
		return "redirect:"+Global.getAdminPath()+"/parameter.define/ctlParam/?repage";
	}
	
	/**
	 * 
	    * @Title: ParamSave  
	    * @Description: (插入新数据)
	    * @param @param ctlParam 携带用于插入的字段信息的实例
	    * @param @param request ajax表单请求
	    * @param @param response
	    * @param @return
	    * @param @throws IOException    参数  
	    * @return Map<String,Object>    键：msg，成功值为success 失败值为fail
	    * @throws
	 */
	@RequestMapping(value = "ParamSave",method=RequestMethod.POST)//ajax自定义请求参数的保存（新增）方法
	@ApiOperation(value = "系统参数保存", notes = "系统参数保存")
	public CommonResponse ParamSave(CtlParam ctlParam,HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String,Object> result = Maps.newHashMap();
		try {
		ctlParamService.save(ctlParam);
		result.put("msg", "success");//成功返回msg值为success的map
		}
		catch(Exception e) {
			result.put("msg", "fail");//失败返回msg值为fail的map
		}
		return ResponseUtil.successResponse(result);
		
	}
	
	/**
	 * 
	    * @Title: ParamUpdate  
	    * @Description: (更新数据)
	    * @param @param ctlParam 携带更新和插入字段信息的实例
	    * @param @param model
	    * @param @param redirectAttributes
	    * @param @return    键：msg，成功值为success 失败值为fail
	    * @return String    返回类型  
	    * @throws
	 */
	
	@RequestMapping(value = "ParamUpdate",method=RequestMethod.POST) //ajax自定义请求参数的维护方法
	@ApiOperation(value = "系统参数更新", notes = "系统参数更新")
	public CommonResponse ParamUpdate(CtlParam ctlParam,HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String,Object> result = Maps.newHashMap();
		try {
		System.out.println(ctlParam.toString());
		ctlParamService.save(ctlParam);
		
		result.put("msg", "success");//成功返回msg值为success的map
		}
		catch(Exception e) {
			result.put("msg", "fail");//失败返回msg值为fail的map
		}
		
		return ResponseUtil.successResponse(result);
		
	}
	
	/**
	 * 
	    * @Title: getCodeType  
	    * @Description: (从统一系统参数字典表ctl_code_det中获取参数值类型，作为前端下拉菜单的选项)
	    * @param @param request ajax表单请求
	    * @param @param response
	    * @param @return
	    * @param @throws IOException    参数  
	    * @return List<String>    参数值类型的字符串数组  
	    * @throws
	 */
	@RequestMapping(value = "getCodeType",method=RequestMethod.POST)
	@ApiOperation(value = "获取参数值类型", notes = "获取参数值类型")//从ctl_code_det字典表中获取全部参数类型的值，作为前端下拉菜单选项值
	public CommonResponse getCodeType(HttpServletRequest request, HttpServletResponse response) throws IOException {
		return ResponseUtil.successResponse(ctlParamService.getCodeType());
		
	}
/**
 * 
    * @Title: paraDelete  
    * @Description: (根据子系统代码和参数代码删除特定条目)
    * @param @param ctlParam 携带删除字段值信息的实例
    * @param @param request ajax表单请求
    * @param @param response
    * @param @throws IOException    参数  
    * @return void    返回类型  
    * @throws
 */
	@RequestMapping(value = "ParamDelete") //ajax自定义请求参数的删除方法
	public void paraDelete(CtlParam ctlParam,HttpServletRequest request, HttpServletResponse response) {
		ctlParam.setSbsCode(request.getParameter("sbs_code"));
		ctlParam.setParaCode(request.getParameter("param_code"));
		ctlParamService.delete(ctlParam);
	}
	
	

	@RequestMapping(value = "delete",method=RequestMethod.POST)
	@ApiOperation(value = "系统参数删除", notes = "系统参数删除")
	public CommonResponse delete(String ids, RedirectAttributes redirectAttributes) throws JsonProcessingException {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map = ctlParamService.deleteParam(ids);
		} catch (Exception e) {
			// : handle exception
			e.printStackTrace();
			map.put("msg", "error");
		}
		return ResponseUtil.successResponse(map);
	}
	
/**
 * 
    * @Title: getListParam  
    * @Description: (根据子系统代码和参数代码从ctl_param中批量查询条目)
    * @param @param ctlParam 携带查询信息的实例
    * @param @param request
    * @param @param response
    * @param @return
    * @param @throws IOException    参数  
    * @return Page<CtlParam>    查询结果实体数组和分页信息  
    * @throws
 */
	@RequestMapping(value = "/getListParam",method=RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sbs_code", value = "子系统代码", required = false,paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "id", value = "id", required = false,paramType = "query", dataType = "String"),
    })
	@ApiOperation(value = "获取系统参数列表", notes = "获取系统参数列表")
	public CommonResponse getListParam(
		     String sbs_code,String id,
			 @RequestParam(value = "pageNo", required = true,defaultValue="0") Integer pageNo ,
			 @RequestParam(value = "pageSize", required = true,defaultValue="10") Integer pageSize,

			 HttpServletRequest request, HttpServletResponse response) throws IOException {
		CtlParam ctlParam = new CtlParam();
		ctlParam.setSbsName(sbs_code);
		ctlParam.setId(id);
		Page<CtlParam> page = ctlParamService.findPage(new Page<CtlParam>(pageNo, pageSize), ctlParam); //使用entity入参findpage
		
		return ResponseUtil.successResponse(page);
	}
	
	
}