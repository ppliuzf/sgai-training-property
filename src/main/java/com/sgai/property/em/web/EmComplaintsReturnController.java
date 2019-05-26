package com.sgai.property.em.web;

import com.sgai.common.web.BaseController;
import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.em.entity.EmComplaintsReturn;
import com.sgai.property.em.entity.EmRepairList;
import com.sgai.property.em.service.EmComplaintsReturnService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 事件回访模板Controller
 * @author yangyz
 * @version 2017-12-13
 */
@Controller
@RequestMapping(value = "${adminPath}/em/emComplaintsReturn")
@Api(description = "事件回访接口")
public class EmComplaintsReturnController extends BaseController {

	@Autowired
	private EmComplaintsReturnService emComplaintsReturnService;

	@ApiOperation(value = "查询一条事件回访", notes = "查询一条事件回访")
	@RequestMapping(value = "/getComplaintsReturn",method=RequestMethod.POST)
	public @ResponseBody EmComplaintsReturn getComplaintsReturn(
			HttpServletRequest request,
			@RequestHeader(value="token")String token,
			@RequestBody EmComplaintsReturn emComplaintsReturn,
			HttpServletResponse response) {
		EmComplaintsReturn complaintsReturn = emComplaintsReturnService.getComplaintsReturn(emComplaintsReturn);
		return complaintsReturn;
	}

	@ApiOperation(value = "事件回访form页面", notes = "事件回访form页面")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "instanceId", value = "投诉事件id", required = false,paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "emCode", value = "事件编号", required = false,paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "emType", value = "事件类型", required = false,paramType = "query", dataType = "String")
	})
	@RequestMapping(value = "form",method=RequestMethod.GET)
	public String form(EmComplaintsReturn emComplaintsReturn,@RequestHeader(value="token")String token, String instanceId, String emCode, String emType, Model model) {
		model.addAttribute("emComplaintsReturn", emComplaintsReturn);
		return "/em/emComplaintsReturnForm";
	}

	@ApiOperation(value = "事件回访创建页面", notes = "事件回访创建页面")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "instanceId", value = "投诉事件id", required = false,paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "emCode", value = "事件编号", required = false,paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "emType", value = "事件类型", required = false,paramType = "query", dataType = "String")
	})
	@RequestMapping(value = "emComplaintsForm",method=RequestMethod.GET)
	public String emComplaints(EmComplaintsReturn emComplaintsReturn,@RequestHeader(value="token")String token, String instanceId, String emCode, String emType, Model model) {
		model.addAttribute("emComplaintsReturn", emComplaintsReturn);
		return "/em/emComplaintsCreateForm";
	}

	@ApiOperation(value = "事件回访修改页面", notes = "事件回访修改页面")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "instanceId", value = "投诉事件id", required = false,paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "emCode", value = "事件编号", required = false,paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "emType", value = "事件类型", required = false,paramType = "query", dataType = "String")
	})
	@RequestMapping(value = "emRepairForm",method=RequestMethod.GET)
	public String emRepairForm(EmRepairList emRepairList, @RequestHeader(value="token")String token,String instanceId, String emCode, String emType, Model model) {
		model.addAttribute("emRepairList", emRepairList);
		return "/em/emRepairListEdit";
	}

	@ApiOperation(value = "保存事件回访", notes = "保存事件回访")
	@RequestMapping(value = "saveComplaintsReturn",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> saveComplaintsReturn(
			@RequestBody EmComplaintsReturn emComplaintsReturn,
			@RequestHeader(value="token")String token,
			HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		LoginUser user = UserServletContext.getUserInfo();
		try {
			emComplaintsReturn.setEnabledFlag("Y");
			map = emComplaintsReturnService.saveReturn(emComplaintsReturn,user);
		} catch (Exception e) {

			e.printStackTrace();
			map.put("result", "fail");
		}
		return map;
	}

}