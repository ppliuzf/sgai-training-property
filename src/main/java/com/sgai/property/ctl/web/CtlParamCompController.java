
package com.sgai.property.ctl.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.collect.Maps;
import com.sgai.common.persistence.Page;
import com.sgai.common.utils.StringUtils;
import com.sgai.common.web.BaseController;
import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.modules.login.jwt.util.CommonResponse;
import com.sgai.modules.login.jwt.util.ResponseUtil;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.ctl.entity.CtlParamComp;
import com.sgai.property.ctl.service.CtlParamCompService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

  
    /**  
    * @ClassName: CtlParamCompController  
    * @Description: (机构参数维护Controller)
    * @author liushang  
    * @date 2017年11月18日  
    * @Company 首自信--智慧城市创新中心  
    */  
    
@RestController
@RequestMapping(value = "${adminPath}/ctlParamComp")
@Api(description = "机构参数接口")
public class CtlParamCompController extends BaseController {

	@Autowired
	private CtlParamCompService ctlParamCompService; //调用的service实例
	
	/**
	 * 
	    * @Title: get  
	    * @Description: (获取单行)
	    * @param @param ctl_param_comp 条目的id字段
	    * @param @return    参数  
	    * @return CtlParamComp    返回类型  
	    * @throws
	 */
	@ModelAttribute
	public CtlParamComp get(@RequestParam(required=false) String id) {
		CtlParamComp entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ctlParamCompService.get(id);
		}
		if (entity == null){
			entity = new CtlParamComp();
		}
		return entity;
	}
	
	
	//@RequiresPermissions("complevelparam:ctlParamComp:view")
	@RequestMapping(value = {"list", ""})
	public String list(CtlParamComp ctlParamComp,
			HttpServletRequest request, HttpServletResponse response, Model model) {
		if(ctlParamComp==null){
			ctlParamComp=new CtlParamComp();
		}
		Page<CtlParamComp> page = ctlParamCompService.findPage(new Page<CtlParamComp>(request, response), ctlParamComp); 
		model.addAttribute("page", page);
		return "ctl/complevelparam/ctlParamCompList";
	}

	//@RequiresPermissions("complevelparam:ctlParamComp:view")
	@RequestMapping(value = "form")
	public String form(CtlParamComp ctlParamComp, Model model) {
		model.addAttribute("ctlParamComp", ctlParamComp);
		return "ctl/complevelparam/ctlParamCompForm";
	}
	


	/**
	 * 
	    * @Title: ParamSave  
	    * @Description: (插入新数据)
	    * @param @param ctlParamComp 携带用于插入的字段信息的实例
	    * @param @param request ajax表单请求
	    * @param @param response
	    * @param @return
	    * @param @throws IOException    参数  
	    * @return Map<String,Object>    返回类型  
	    * @throws
	 */
	 
	@RequestMapping(value = "ParaSave",method=RequestMethod.POST)
	@ApiOperation(value = "保存", notes = "保存")
	public CommonResponse ParamSave( CtlParamComp ctlParamComp,

			HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String,Object> result = Maps.newHashMap();
		try {
		ctlParamCompService.save(ctlParamComp);
		result.put("msg", "success");
		}
		catch(Exception e) {
			result.put("msg", "fail");
		}
		return ResponseUtil.successResponse(result);
		
	}
	
	/**
	 * @throws JsonProcessingException 
	 * 
	    * @Title: save  
	    * @Description: (更新数据)
	    * @param @param ctlParamComp 携带更新和插入字段信息的实例
	    * @param @param model
	    * @param @param redirectAttributes
	    * @param @return    返回地址  
	    * @return String    返回类型  
	    * @throws
	 */
	
	@RequestMapping(value = "ParaUpdate",method=RequestMethod.POST)
	@ApiOperation(value = "更新", notes = "更新")
	public CommonResponse update( CtlParamComp ctlParamComp,

			 Model model, RedirectAttributes redirectAttributes) throws JsonProcessingException {
		Map<String,Object> result = Maps.newHashMap();
		try {
		ctlParamCompService.save(ctlParamComp);
		result.put("msg", "success");
		}
		catch(Exception e) {
			result.put("msg", "fail");
		}
		return ResponseUtil.successResponse(result);
	}
	

	/**
	 * 
	    * @Title: getComp  
	    * @Description: (从ctl_comp表获取所有机构代码作为下拉菜单选项)
	    * @param @param request ajax表单请求
	    * @param @param response 
	    * @param @return
	    * @param @throws IOException    参数  
	    * @return List<String>    返回类型  
	    * @throws
	 */
	@RequestMapping(value = "getComp",method=RequestMethod.POST)
	@ApiOperation(value = "查询所有机构选项", notes = "查询所有机构选项")
	public CommonResponse getComp(HttpServletRequest request,

			 HttpServletResponse response) throws IOException {
		String comCode=null;
		 try {
			 LoginUser user = UserServletContext.getUserInfo();
			 comCode= user.getComCode();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseUtil.successResponse(ctlParamCompService.getCompByCode(comCode));
		
	}
	
	/**
	 * 
	    * @Title: getSbs  
	    * @Description: (从ctl_modu表获取全部子系统代码)
	    * @param @param request ajax表单请求
	    * @param @param response
	    * @param @return
	    * @param @throws IOException    参数  
	    * @return List<String>    返回类型  
	    * @throws
	 */
	@RequestMapping(value = "getBusiByCode",method=RequestMethod.POST)
	@ApiOperation(value = "新增时查询所有子系统选项", notes = "新增时查询所有子系统选项")
	public CommonResponse getBusiByCode(HttpServletRequest request,

			HttpServletResponse response) throws IOException {
		String comCode=null;
		 try {
			 LoginUser user = UserServletContext.getUserInfo();
			 comCode= user.getComCode();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseUtil.successResponse(ctlParamCompService.getBusiByComCode(comCode));
		
	}
	
	/**
	 * 
	    * @Title: getSbs  
	    * @Description: (根据前端传入的机构代码从ctl_param_comp中读取条目)
	    * @param @param request ajax表单请求
	    * @param @param response
	    * @param @return
	    * @param @throws IOException    参数  
	    * @return List<String>    返回类型  
	    * @throws
	 */
	@RequestMapping(value = "getSbsList", method=RequestMethod.POST)
	@ApiOperation(value = "级联获取子系统列表", notes = "级联获取子系统列表")
	public CommonResponse getSbsList( CtlParamComp ctlComp,
			 @RequestParam(value = "pageNo", required = true,defaultValue="0") Integer pageNo ,
			 @RequestParam(value = "pageSize", required = true,defaultValue="10") Integer pageSize,

			HttpServletRequest request, HttpServletResponse response) throws IOException {
		if(request.getParameter("com_code")!="")
		ctlComp.setComCode(request.getParameter("com_code"));
		Page<CtlParamComp> page = ctlParamCompService.getSbsList(new Page<CtlParamComp>(request, response), ctlComp);
		
		return ResponseUtil.successResponse(page);
	}
	
	/**
	 * 
	    * @Title: getSbs  
	    * @Description: (根据前端传入的机构代码和子系统代码从ctl_param_comp中读取条目)
	    * @param @param request ajax表单请求
	    * @param @param response
	    * @param @return
	    * @param @throws IOException    参数  
	    * @return List<String>    返回类型  
	    * @throws
	 */
	
	@RequestMapping(value = "/getCompParamList",method=RequestMethod.POST)
	@ApiOperation(value = "分页查询", notes = "分页查询")
	public CommonResponse getCompParamList( CtlParamComp ctlComp,
			 @RequestParam(value = "pageNo", required = true,defaultValue="0") Integer pageNo ,
			 @RequestParam(value = "pageSize", required = true,defaultValue="10") Integer pageSize,

			 HttpServletRequest request, HttpServletResponse response) throws IOException {
		/*if(!request.getParameter("sbs_code").equals("选择子系统")&&!request.getParameter("com_code").equals("选择机构")) {
		ctlComp.setSbsCode(request.getParameter("sbs_code"));
		ctlComp.setComCode(request.getParameter("com_code"));
		ctlComp.setParaCode(request.getParameter("para_code"));
	}	*/
		ctlComp.setParaCode(request.getParameter("para_code"));
		String comCode=null;
		 try {
			 LoginUser user = UserServletContext.getUserInfo();
			 comCode= user.getComCode();
		} catch (Exception e) {
			e.printStackTrace();
		}
		 ctlComp.setComCode(comCode);
		Page<CtlParamComp> page = ctlParamCompService.findPage(new Page<CtlParamComp>(request, response), ctlComp);
		for(int i=0;i<page.getList().size();i++) { //将系统级别公共参数存入机构参数库
			if(page.getList().get(i).getComCode()==null) {
				CtlParamComp common =new CtlParamComp();
				for(int j=0;j<page.getList().size();j++) { 
					if(page.getList().get(j).getComCode()!=null) {
						common.setSbsCode(page.getList().get(i).getSbsCode());
						common.setParaCode(page.getList().get(i).getParaCode());
						common.setParaValue(page.getList().get(i).getParaValue());
						common.setComCode(page.getList().get(j).getComCode());
					}
			
				ctlParamCompService.save(common);
				}
			}
		
		}
		return  ResponseUtil.successResponse(ctlParamCompService.findPage(new Page<CtlParamComp>(request, response), ctlComp));/*ctlParamCompService.findPage(new Page<CtlParamComp>(request, response), ctlComp);*/
	}
	
	/**
	 * 
	    * @Title: getSbs  
	    * @Description: (根据前端传入的机构代码，子系统代码和参数代码从ctl_param_comp中读取条目)
	    * @param @param request ajax表单请求
	    * @param @param response
	    * @param @return
	    * @param @throws IOException    参数  
	    * @return List<String>    返回类型  
	    * @throws
	 */
	@RequestMapping(value = "/findLocalList",method=RequestMethod.POST)
	@ApiOperation(value = "更新用分页查询", notes = "更新用分页查询")
	public CommonResponse findLocalList( CtlParamComp ctlComp,
			@RequestParam(value = "pageNo", required = true,defaultValue="0") Integer pageNo ,
			 @RequestParam(value = "pageSize", required = true,defaultValue="10") Integer pageSize,

			 HttpServletRequest request, HttpServletResponse response) throws IOException {
		if(request.getParameter("id")!="")
		ctlComp.setId(request.getParameter("id"));
		Page<CtlParamComp> page = ctlParamCompService.findLocalList(new Page<CtlParamComp>(request, response), ctlComp);
		
		return ResponseUtil.successResponse(page);
	}

}