
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
import com.sgai.common.persistence.Page;
import com.sgai.common.utils.StringUtils;
import com.sgai.common.web.BaseController;
import com.sgai.property.ctl.entity.CtlCodeDet;
import com.sgai.property.ctl.service.CtlCodeDetService;
import com.sgai.modules.login.jwt.util.CommonResponse;
import com.sgai.modules.login.jwt.util.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 
    * @ClassName: CtlCodeDetController  
    * @Description: (基础代码表维护controller)
    * @author liushang  
    * @date 2017年11月18日  
    * @Company 首自信--智慧城市创新中心
 */
@RestController
@RequestMapping(value = "${adminPath}/ctlCodeDet")
@Api(description = "详细字典表接口")
public class CtlCodeDetController extends BaseController {

	@Autowired
	private CtlCodeDetService ctlCodeDetService;
	/**
	 * 
	    * @Title: get  
	    * @Description: (获取单行)
	    * @param @param id 单行主键id字段
	    * @param @return     查询结果实体
	    * @return CtlCodeDet    返回类型  
	    * @throws
	 */
	@ModelAttribute
	public CtlCodeDet get(@RequestParam(required=false) String id) {
		CtlCodeDet entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ctlCodeDetService.get(id);
		}
		if (entity == null){
			entity = new CtlCodeDet();
		}
		return entity;
	}
	
	//@RequiresPermissions("groupcorpcodedefine:det:ctlCodeDet:view")
	@RequestMapping(value = {"list", ""})
	public String list( CtlCodeDet ctlCodeDet, HttpServletRequest request, HttpServletResponse response, Model model) {
		if(ctlCodeDet==null){
			ctlCodeDet=new CtlCodeDet();
		}
		Page<CtlCodeDet> page = ctlCodeDetService.findPage(new Page<CtlCodeDet>(request, response), ctlCodeDet); 
		model.addAttribute("page", page);
		return "ctl/groupcorpcodedefine/det/ctlCodeDetList";
	}

	//@RequiresPermissions("groupcorpcodedefine:det:ctlCodeDet:view")
	@RequestMapping(value = "form")
	public String form( CtlCodeDet ctlCodeDet, Model model) {
		model.addAttribute("ctlCodeDet", ctlCodeDet);
		return "ctl/groupcorpcodedefine/det/ctlCodeDetForm";
	}

	//@RequiresPermissions("groupcorpcodedefine:det:ctlCodeDet:edit")
	@RequestMapping(value = "save")
	@ApiOperation(value = "保存", notes = "保存")
	public Map<String, Object> save( CtlCodeDet ctlCodeDet, Model model, RedirectAttributes redirectAttributes) {
		Map<String,Object> map=new HashMap<String,Object>();
		/*if (!beanValidator(model, ctlCodeDet)){
			return form(ctlCodeDet, model);
		}*/
		ctlCodeDet.setComCode("0000");
		try {
		map=ctlCodeDetService.saveDet(ctlCodeDet);
		map.put("msg", "success");
		}catch(Exception e){
			e.printStackTrace();
			map.put("msg", "faild");
			
		}
		return map;
	}
	
	//@RequiresPermissions("groupcorpcodedefine:det:ctlCodeDet:edit")
	@RequestMapping(value = "delete",method=RequestMethod.POST)
	@ApiOperation(value = "批量删除", notes = "批量删除")
	public CommonResponse delete(String ids,  RedirectAttributes redirectAttributes) throws JsonProcessingException {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map = ctlCodeDetService.deleteUserIp(ids);
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
	    * @Description: (根据代码类型批量查询机构代码为“0000”的条目)
	    * @param @param ctlCodeDet
	    * @param @param request
	    * @param @param response
	    * @param @return 
	    * @param @throws IOException    参数  
	    * @return Page<CtlCodeDet>    返回类型  携带信息的实体数组和分页信息
	    * @throws
	 */
	@RequestMapping(value = "/getListType",method=RequestMethod.POST)
	@ApiOperation(value = "根据类型分页查询", notes = "根据类型分页查询")
	public CommonResponse getListType(CtlCodeDet ctlCodeDet,
			 @RequestParam(value = "code_type", required = false) String  code_type,
			 @RequestParam(value = "pageNo", required = true,defaultValue="0") Integer pageNo ,
			 @RequestParam(value = "pageSize", required = true,defaultValue="10") Integer pageSize,

			HttpServletRequest request, HttpServletResponse response) throws IOException {
		if(request.getParameter("id")!="")
		ctlCodeDet.setId(request.getParameter("id"));

			ctlCodeDet.setCodeType(code_type);
		//ctlCodeDet.setComCode("0000");
		Page<CtlCodeDet> page = ctlCodeDetService.findPage(new Page<CtlCodeDet>(request, response), ctlCodeDet);
		
		return ResponseUtil.successResponse(page);
	}
	/**
	 * 
	    * @Title: getTypeList  
	    * @Description: (根据类型查询类别，不分页)
	    * @param @param code_type
	    * @param @param token
	    * @param @param request
	    * @param @param response
	    * @param @return
	    * @param @throws IOException    参数  
	    * @return CommonResponse    返回类型  
	    * @throws
	 */
	@RequestMapping(value = "/getTypeList",method=RequestMethod.POST)
	@ApiOperation(value = "根据类型查询", notes = "根据类型查询")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "code_type", value = "类型代码", required = false,paramType = "query", dataType = "String")
	})
	public CommonResponse getTypeList(
			 @RequestParam(value = "code_type", required = false) String  code_type,

			HttpServletRequest request, HttpServletResponse response) throws IOException {
		    CtlCodeDet  ctlCodeDet = new CtlCodeDet();
			ctlCodeDet.setCodeType(code_type);
		return ResponseUtil.successResponse(ctlCodeDetService.findList(ctlCodeDet));
	}
	/**
	 * getListTypeCode:(空间用的主数据).
	 * @param ctlCodeDet
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException :Page<CtlCodeDet> 
	 * @since JDK 1.8
	 * @author admin
	 */
	@RequestMapping(value = "/getListTypeCode",method=RequestMethod.POST)
	@ApiOperation(value = "空间用的主数据", notes = "空间用的主数据")
	public CommonResponse getListTypeCode(CtlCodeDet ctlCodeDet,
			
			 @RequestParam(value = "pageNo", required = true,defaultValue="0") Integer pageNo ,
			 @RequestParam(value = "pageSize", required = true,defaultValue="10") Integer pageSize,

			 HttpServletRequest request, HttpServletResponse response) throws IOException {

		String codeType = request.getParameter("code_type");
		String nodeCode = request.getParameter("node_code");
		Map<String,String>   map = new HashMap<String,String>();
		map.put("codeType", codeType);
		map.put("nodeCode", nodeCode);
		map.put("comCode", "0000");
		Page<CtlCodeDet> page = new Page<CtlCodeDet>(request, response);
		ctlCodeDet.setPage(page);
		List<CtlCodeDet>  list = ctlCodeDetService.findCodeDetForSpace(map);
		page.setList(list);
		return ResponseUtil.successResponse(page);
	}
	
	

	
	/**
	 * 
	    * @Title: getCodeType  
	    * @Description: (从基础代码类别表ctl_code_type中获取全部代码类型做为菜单下拉项)
	    * @param @param request
	    * @param @param response
	    * @param @return
	    * @param @throws IOException    参数  
	    * @return List<String>    返回类型  
	    * @throws
	 */
	@RequestMapping(value = "getCodeType",method=RequestMethod.POST)
	@ApiOperation(value = "获取代码类型", notes = "获取代码类型")
	public CommonResponse getCodeType(HttpServletRequest request,  HttpServletResponse response) throws IOException {
		return ResponseUtil.successResponse(ctlCodeDetService.getCodeType());
		
	}
	
	/**
	 * 
	 * findAllCodeDet:(查询字典表数据).
	 * @param code_type
	 * @param token
	 * @param request
	 * @param response
	 * @return
	 * @throws JsonProcessingException :CommonResponse 
	 * @since JDK 1.8
	 * @author yangyz
	 */
	@RequestMapping(value = "findAllCodeDet",method=RequestMethod.POST)
	@ApiOperation(value = "查询字典表数据", notes = "查询字典表数据")
	public synchronized List<CtlCodeDet> findAllCodeDet(
			 @RequestParam(value = "code_type", required = false) String  code_type,

			HttpServletRequest request, 
			HttpServletResponse response) {
		CtlCodeDet ctlCodeDet = new CtlCodeDet();
		ctlCodeDet.setCodeType(code_type);
		return ctlCodeDetService.findList(ctlCodeDet);
	}
	
}