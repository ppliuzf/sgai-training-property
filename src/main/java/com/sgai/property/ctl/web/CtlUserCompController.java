package com.sgai.property.ctl.web;

import java.io.IOException;
import java.util.HashMap;
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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sgai.common.persistence.Page;
import com.sgai.common.utils.StringUtils;
import com.sgai.common.web.BaseController;
import com.sgai.property.ctl.entity.CtlComp;
import com.sgai.property.ctl.entity.CtlUserComp;
import com.sgai.property.ctl.service.CtlUserCompService;
import com.sgai.modules.login.jwt.util.CommonResponse;
import com.sgai.modules.login.jwt.util.ResponseUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * ClassName: CtlUserCompController Description: (用户机构关联)
 * 
 * @author 王天尧 Date 2017年11月18日 Company 首自信--智慧城市创新中心
 */
@RestController
@Api(description = "用户机构关联")
@RequestMapping(value = "${adminPath}/ctl/ctlUserComp")
public class CtlUserCompController extends BaseController {

	@Autowired
	private CtlUserCompService ctlUserCompService;
	/*
	 * @ModelAttribute public CtlUserComp get(@RequestParam(required=false) String
	 * id) { CtlUserComp entity = null; if (StringUtils.isNotBlank(id)){ entity =
	 * ctlUserCompService.get(id); } if (entity == null){ entity = new
	 * CtlUserComp(); } return entity; }
	 */

	/**
	 * 
	 * save:(保存用户关联的机构).
	 * 
	 * @param ctlUserComp
	 * @param userIds
	 *            用户代码集合
	 * @param compIds
	 *            机构代码集合
	 * @param model
	 * @param redirectAttributes
	 * @return :Map<String,String>
	 * @since JDK 1.8
	 * @author 王天尧
	 * @throws IOException
     */
	@ApiOperation(value = "保存用户关联的机构", notes = "保存用户关联的机构")
	@ApiImplicitParams({ @ApiImplicitParam(name = "userCode", value = "用户代码", required = false, dataType = "String"),
			@ApiImplicitParam(name = "compIds", value = "机构代码", required = false, dataType = "String"),
			@ApiImplicitParam(name = "oldComCode", value = "原来关联的机构代码", required = false, dataType = "String"), })
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public CommonResponse save(String userCode, String compIds, String oldComCode,
			Model model, RedirectAttributes redirectAttributes) throws IOException {
		Map<String, String> map = new HashMap<String, String>();
		try {
			CtlUserComp ctlUserComp = new CtlUserComp();
			map = ctlUserCompService.saveUserCom(ctlUserComp, userCode, compIds, oldComCode, map);
		} catch (Exception e) {
			// : handle exception
			e.printStackTrace();
			map.put("msg", "faild");
		}
		return ResponseUtil.successResponse(map);
	}

	/**
	 * 
	 * getUserCompList:(获取用户所关联的机构).
	 * 
	 * @param ctlUserComp
	 * @param userIds
	 *            用户id
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 *             :List<CtlUserComp>
	 * @since JDK 1.8
	 * @author 王天尧
     */
	@ApiOperation(value = "获取用户所关联的机构", notes = "获取用户所关联的机构")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "userCode", value = "机构管理员代码", required = false, dataType = "String"), })
	@RequestMapping(value = "/getUserCompList", method = RequestMethod.POST)
	public CommonResponse getUserCompList(String userCode,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		CtlUserComp ctlUserComp = new CtlUserComp();
		return ResponseUtil.successResponse(ctlUserCompService.getUserCompList(ctlUserComp, userCode));
	}

	/**
	 * 
	 * findLackList:(获取机构列表，过滤掉被其他机构管理员选择的机构).
	 * 
	 * @param ctlComp
	 * @param userCode
	 *            机构管理员代码
	 * @param request
	 * @param response
	 * @param token
	 * @return
	 * @throws JsonProcessingException
	 *             :CommonResponse
	 * @since JDK 1.8
	 * @author ASUS
	 */
	@ApiOperation(value = "获取用户所关联的机构(过滤掉已被其他机构管理员选择的机构)", notes = "获取用户所关联的机构(过滤掉已被其他机构管理员选择的机构)")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "remarks", value = "机构管理员代码(借用一下这个字段传递机构代码)", required = false, dataType = "String"), })
	@RequestMapping(value = "/findLackList", method = RequestMethod.POST)
	public CommonResponse findLackList(String remarks, HttpServletRequest request, HttpServletResponse response,
			@RequestHeader("token") String token) throws JsonProcessingException {
		CtlComp ctlComp = new CtlComp();
		ctlComp.setRemarks(remarks);
		return ResponseUtil
				.successResponse(ctlUserCompService.findLackList(ctlComp, new Page<CtlComp>(request, response)));
	}
}