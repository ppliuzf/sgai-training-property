package com.sgai.property.wf.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
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

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sgai.common.persistence.Page;
import com.sgai.common.utils.StringUtils;
import com.sgai.common.web.BaseController;
import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.modules.login.jwt.util.CommonResponse;
import com.sgai.modules.login.jwt.util.ResponseUtil;
import com.sgai.property.wf.entity.WfFlowDefine;
import com.sgai.property.wf.entity.WfUserRight;
import com.sgai.property.wf.service.WfInstanceRecordService;
import com.sgai.property.wf.service.WfUserRightService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 					
    * ClassName: WfUserRightController  
    * com.sgai.property.commonService.vo;(流程权限Controller)
    * @author 王天尧  
    * Date 2017年12月5日  
    * Company 首自信--智慧城市创新中心
 */
@RestController
@Api(description = "流程权限Controller")
@RequestMapping(value = "${adminPath}/wf/wfUserRight")
public class WfUserRightController extends BaseController {

	@Autowired
	private WfUserRightService wfUserRightService;
	@Autowired
	private WfInstanceRecordService wfInstanceRecordService;
	/*@ModelAttribute
	public WfUserRight get(@RequestParam(required=false) String id) {
		WfUserRight entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = wfUserRightService.get(id);
		}
		if (entity == null){
			entity = new WfUserRight();
		}
		return entity;
	}*/
	/**
	 * 
	 * getLackList:(获取未拥有的流程权限).
	 * @param corrCode 用户或角色代码
	 * @return :Map<String,Object> 
	 * @since JDK 1.8
	 * @author 王天尧
	 * @throws IOException
     */
	@ApiOperation(value = "获取未拥有的流程权限", notes = "获取未拥有的流程权限")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "corrCode", value = "用户或角色代码", required = false, dataType = "String"),
            @ApiImplicitParam(name = "pageSize", value = "每页显示数", required = false, dataType = "String"),
            @ApiImplicitParam(name = "pageNo", value = "页码", required = false, dataType = "String"),
    })
	@RequestMapping(value = "/getLackList")
	public CommonResponse getLackList(
			@RequestParam(required=true) String corrCode,
			Model model, 

			LoginUser user,
			HttpServletRequest request, 
			HttpServletResponse response) throws IOException {
		List<WfFlowDefine> stepList=null;
		try {
			Map<String,String> param = new HashMap<String,String>();
			param.put("corrCode", corrCode);
			param.put("comCode",user.getComCode());
			stepList = wfUserRightService.getFlowListLackPage(param);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return ResponseUtil.successResponse(stepList);
	}
	/**
	 * 
	 * getOwnList:(获取已拥有的流程权限).
	 * @param corrCode
	 * @param model
	 * @param request
	 * @param response
	 * @return :List<WfFlowDefine> 
	 * @since JDK 1.8
	 * @author 王天尧
	 * @throws IOException
     */
	@ApiOperation(value = "获取已拥有的流程权限", notes = "获取已拥有的流程权限")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "corrCode", value = "用户或角色代码", required = false, dataType = "String"),
            @ApiImplicitParam(name = "pageSize", value = "每页显示数", required = false, dataType = "String"),
            @ApiImplicitParam(name = "pageNo", value = "页码", required = false, dataType = "String"),
    })
	@RequestMapping(value = "/getOwnList")
	public CommonResponse getOwnList(
			@RequestParam(required=true) String corrCode,
			String pageSize,
			String pageNo,
			Model model, 

			LoginUser user,
			HttpServletRequest request, 
			HttpServletResponse response) throws IOException {
		Map<String,String> param = new HashMap<String,String>();
		param.put("corrCode", corrCode);
		param.put("comCode", user.getComCode());
		List<WfFlowDefine> flowListOwn = wfUserRightService.getFlowListOwn(param);
		return ResponseUtil.successResponse(flowListOwn);
	}
	//验证当前用户权限
	@RequestMapping(value = "/getOwnList1")
	public CommonResponse getOwnList1(
			LoginUser user,

			HttpServletRequest request, 
			HttpServletResponse response) throws IOException {
		Map<String,String> param = new HashMap<String,String>();
		param.put("corrCode", user.getUserId());
		param.put("comCode", user.getComCode());
		List<WfFlowDefine> flowListOwn = wfUserRightService.getFlowListOwn(param);
		return ResponseUtil.successResponse(flowListOwn);
	}
	/**
	 * 
	 * saveBusiTree:(保存该用户或角色的流程).
	 * @param stepCodes 流程代码集合
	 * @param corrCode  用户或角色代码
	 * @return :Map<String,Object> 
	 * @since JDK 1.8
	 * @author 王天尧
	 * @throws IOException
     */
	@ApiOperation(value = "保存该用户或角色的流程", notes = "保存该用户或角色的流程")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "stepCodes", value = "流程代码集合", required = false, dataType = "String"),
            @ApiImplicitParam(name = "category", value = "类型（U是用户，R是角色）", required = false, dataType = "String"),
            @ApiImplicitParam(name = "corrCode", value = "用户或角色代码", required = false, dataType = "String"),
    })
	@RequestMapping(value = "saveFlowTree")
	public CommonResponse saveFlowTree(@RequestParam(required=true) String stepCodes,
			                               @RequestParam(required=true) String corrCode,
			                               @RequestParam(required=true) String category,

			                               LoginUser user,
			                               Model model, RedirectAttributes redirectAttributes) throws IOException {
		Map<String,String> result = Maps.newHashMap();
		try {
			Map<String,String> param = new HashMap<String,String>();
			param.put("corrCode", corrCode);
			wfUserRightService.deleteStepTree(param);
			List<String> stepCodeList = Lists.newArrayList();
			String[] stepCodeArray = stepCodes.split(",");
			for(String s : stepCodeArray) {
				if(s!=null && !s.equals("")) {
					stepCodeList.add(s);
				}
			}
			Map<String, String> saveStepTree = wfUserRightService.saveStepTree(stepCodeList,corrCode,category,user.getComCode());
			result=saveStepTree;
		}catch(Exception e) {
			e.printStackTrace();
			result.put("msg", "faild!");
		}
		return ResponseUtil.successResponse(result);
	}
	/**
	 * 
	 * getUserRoleTree:(构建角色用户树结构).
	 * @param model
	 * @param redirectAttributes
	 * @return :List<Map<String,String>> 
	 * @since JDK 1.8
	 * @author 王天尧
	 * @throws IOException
     */
	@ApiOperation(value = "构建角色用户树结构", notes = "构建角色用户树结构")
	@RequestMapping(value = "getUserRoleTree")
	public CommonResponse getUserRoleTree(
			LoginUser user,
			Model model,

			RedirectAttributes redirectAttributes) throws IOException {
	    String deptCode = user.getDeptCode();
	    String comCode = user.getComCode();
	    String moduCode = user.getModuCode();
		List<Map<String,String>> list = wfUserRightService.getUserRoleTree(deptCode,comCode,moduCode);
		return ResponseUtil.successResponse(list);
	}
	
	/**
	 * 
	 * findLoginUserAuthority:(获取当前登陆用户是否有创建事件权限).
	 * @param stepCode
	 * @param user
	 * @param token
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException :CommonResponse
	 * @since JDK 1.8
	 * @author yangyz
	 */
	@RequestMapping(value = "/findLoginUserAuthority", method=RequestMethod.POST)
	public CommonResponse findLoginUserAuthority(
			String flowCode,
			LoginUser user,

			HttpServletRequest request, 
			HttpServletResponse response
			) throws IOException {

		Map<String,String> param = new HashMap<String,String>();
		Map<String, String> map = new HashMap<String, String>();
		map.put("flowCode", flowCode);
		map.put("userCode", user.getUserId());
		map.put("comCode", user.getComCode());
		try {
			List<WfFlowDefine> wfFlowDefines = wfInstanceRecordService.getStepssByCode(map);
			if(wfFlowDefines != null && wfFlowDefines.size() > 0) {
				param.put("createCode", "ok");
			}else {
				param.put("createCode", "no");
			}
		}catch(Exception e) {
			param.put("createCode", "fail");
			e.printStackTrace();
		}
		return ResponseUtil.successResponse(param);
	}
}