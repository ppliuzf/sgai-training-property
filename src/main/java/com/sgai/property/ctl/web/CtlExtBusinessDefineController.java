package com.sgai.property.ctl.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.collect.Maps;
import com.sgai.common.config.Global;
import com.sgai.common.persistence.Page;
import com.sgai.common.utils.StringUtils;
import com.sgai.common.web.BaseController;
import com.sgai.property.ctl.entity.CtlExtBusinessDefine;
import com.sgai.property.ctl.service.CtlExtBusinessDefineService;
import com.sgai.modules.login.jwt.util.CommonResponse;
import com.sgai.modules.login.jwt.util.ResponseUtil;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 外部对接系统定义Controller
 * 
 * @author 李伟
 * @version 2018-02-07
 */
@RestController
@RequestMapping(value = "${adminPath}/ctl/ctlExtBusinessDefine")
public class CtlExtBusinessDefineController extends BaseController {

	@Autowired
	private CtlExtBusinessDefineService ctlExtBusinessDefineService;

	@ApiOperation(value = "根据id找到所需要修改的对象", notes = "根据id找到所需要修改的对象")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "想要修改的对象的id", required = false, dataType = "String"), })
	@RequestMapping(value = "/findById", method = RequestMethod.POST)
	public CommonResponse findById(String id,  HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		CtlExtBusinessDefine ctlExtBusinessDefine = ctlExtBusinessDefineService.get(id);
		return ResponseUtil.successResponse(ctlExtBusinessDefine);
	}

	// @RequiresPermissions("mobile:mobileModuleInfo:view")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public CommonResponse list(
			@RequestParam(value = "pageNo", required = true, defaultValue = "0") Integer pageNo,
			@RequestParam(value = "pageSize", required = true, defaultValue = "10") Integer pageSize,
			HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		CtlExtBusinessDefine ctlExtBusinessDefine = new CtlExtBusinessDefine();
		Page<CtlExtBusinessDefine> page = ctlExtBusinessDefineService
				.findPage(new Page<CtlExtBusinessDefine>(request, response), ctlExtBusinessDefine);
		return ResponseUtil.successResponse(page);
	}

	// @RequiresPermissions("mobile:mobileModuleInfo:edit")
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public CommonResponse save(CtlExtBusinessDefine ctlExtBusinessDefine,
			HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		Map<String, Object> result = Maps.newHashMap();
		ctlExtBusinessDefine.setEnabledFlag("Y");
		ctlExtBusinessDefineService.save(ctlExtBusinessDefine);
		result.put("msg", "success");// 成功返回msg值为success的map
		return ResponseUtil.successResponse(result);
	}

	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ApiOperation(value = "对接系统删除", notes = "对接外部系统删除")
	public CommonResponse delete(String primaryKeys,
			RedirectAttributes redirectAttributes) throws JsonProcessingException {
		Map<String, Object> map = new HashMap<String, Object>();
		CtlExtBusinessDefine ctlExtBusinessDefine = null;
		List<CtlExtBusinessDefine> deleteList = new ArrayList<CtlExtBusinessDefine>();
		String ids[] = primaryKeys.split(",");
		for (String id : ids) {
			if (StringUtils.isNotEmpty(id)) {
				ctlExtBusinessDefine = new CtlExtBusinessDefine();
				ctlExtBusinessDefine.setId(id);
				deleteList.add(ctlExtBusinessDefine);
			}
		}
		ctlExtBusinessDefineService.batchDelete(deleteList);
		map.put("msg", "success");
		return ResponseUtil.successResponse(map);
	}
}