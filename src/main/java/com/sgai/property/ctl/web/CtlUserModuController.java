package com.sgai.property.ctl.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sgai.common.persistence.Page;
import com.sgai.common.web.BaseController;
import com.sgai.property.ctl.entity.CtlModu;
import com.sgai.property.ctl.entity.CtlUserModu;
import com.sgai.property.ctl.service.CtlUserModuService;
import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.modules.login.jwt.util.CommonResponse;
import com.sgai.modules.login.jwt.util.ResponseUtil;
import com.sgai.modules.login.support.UserServletContext;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 用户模块关系Controller
 * 
 * @author admin
 * @version 2018-03-28
 */
@RestController
@RequestMapping(value = "${adminPath}/ctl/ctlUserModu")
public class CtlUserModuController extends BaseController {

	@Autowired
	private CtlUserModuService ctlUserModuService;

	/**
	 * 
	 * @Title: save @Description: (保存用户关联的模块) @param @param userCode
	 * 用户代码 @param @param moduIds 模块代码集合 @param @param oldModuCode
	 * 原来关联的模块 @param @param token @param @param model @param @param
	 * redirectAttributes @param @return @param @throws
	 * ServletException @param @throws IOException 参数 @return CommonResponse
	 * 返回类型 @throws
	 */
	@ApiOperation(value = "保存用户关联的模块", notes = "保存用户关联的模块")
	@ApiImplicitParams({ @ApiImplicitParam(name = "userCode", value = "用户代码", required = false, dataType = "String"),
			@ApiImplicitParam(name = "moduIds", value = "模块代码", required = false, dataType = "String"),
			@ApiImplicitParam(name = "oldModuCode", value = "原来关联的模块代码", required = false, dataType = "String"), })
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public CommonResponse save(String userCode, String moduIds, String oldModuCode,
			 Model model, RedirectAttributes redirectAttributes)
			throws IOException {
		Map<String, String> map = new HashMap<String, String>();
		try {
			CtlUserModu ctlUserModu = new CtlUserModu();
			map = ctlUserModuService.saveUserModu(ctlUserModu, userCode, moduIds, oldModuCode, map);
		} catch (Exception e) {
			// : handle exception
			e.printStackTrace();
			map.put("msg", "faild");
		}
		return ResponseUtil.successResponse(map);
	}

	/**
	 * 
	 * @Title: getUserCompList @Description: (获取用户所关联的模块) @param @param
	 * userCode @param @param token @param @param request @param @param
	 * response @param @return @param @throws IOException @param @throws
	 * ServletException 参数 @return CommonResponse 返回类型 @throws
	 */
	@ApiOperation(value = "获取用户所关联的模块", notes = "获取用户所关联的模块")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "userCode", value = "模块管理员代码", required = false, dataType = "String"), })
	@RequestMapping(value = "/getUserModuList", method = RequestMethod.POST)
	public CommonResponse getUserCompList(String userCode,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		CtlUserModu ctlUserModu = new CtlUserModu();
		return ResponseUtil.successResponse(ctlUserModuService.getUserModuList(ctlUserModu, userCode));
	}

	/**
	 * 
	 * @Title: findLackList @Description:
	 * (获取用户所关联的机构(过滤掉已被其他机构管理员选择的机构) @param @param remarks @param @param
	 * request @param @param response @param @param
	 * token @param @return @param @throws JsonProcessingException 参数 @return
	 * CommonResponse 返回类型 @throws
	 */
	@ApiOperation(value = "获取用户所关联的机构(过滤掉已被其他机构管理员选择的机构)", notes = "获取用户所关联的机构(过滤掉已被其他机构管理员选择的机构)")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "remarks", value = "模块管理员代码(借用一下这个字段传递模块代码)", required = false, dataType = "String"), })
	@RequestMapping(value = "/findLackList", method = RequestMethod.POST)
	public CommonResponse findLackList(String remarks, HttpServletRequest request, HttpServletResponse response,
			@RequestHeader("token") String token) throws JsonProcessingException {
		LoginUser user = UserServletContext.getUserInfo();
		CtlModu ctlModu = new CtlModu();
		ctlModu.setRemarks(remarks);
		ctlModu.setComCode(user.getComCode());
		return ResponseUtil.successResponse(ctlUserModuService.findLackList(ctlModu, new Page<CtlModu>(request, response)));
	}
}