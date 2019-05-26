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
import com.sgai.modules.login.jwt.util.CommonResponse;
import com.sgai.modules.login.jwt.util.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import com.sgai.property.ctl.entity.CtlBusiUser;
import com.sgai.property.ctl.service.CtlBusiUserService;

/**
 * 外部系统用户维护Controller
 * @author 李伟
 * @version 2018-02-10
 */
@RestController
@Api(description = "外部系统用户维护")
@RequestMapping(value = "${adminPath}/ctl/ctlBusiUser")
public class CtlBusiUserController extends BaseController {

	@Autowired
	private CtlBusiUserService ctlBusiUserService;
	
	@ApiOperation(value = "根据id找到所需要修改的对象", notes = "根据id找到所需要修改的对象")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "想要修改的对象的id", required = false, dataType = "String"), })
	@RequestMapping(value = "/findById", method = RequestMethod.POST)
	public CommonResponse findById(String id,  HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		CtlBusiUser ctlBusiUser = ctlBusiUserService.get(id);
		return ResponseUtil.successResponse(ctlBusiUser);
	}
	
	
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public CommonResponse list(
			@RequestParam(value = "pageNo", required = true, defaultValue = "0") Integer pageNo,
			@RequestParam(value = "pageSize", required = true, defaultValue = "10") Integer pageSize,
			HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		CtlBusiUser  ctlBusiUser = new CtlBusiUser();
		Page<CtlBusiUser> page = ctlBusiUserService.findPage(new Page<CtlBusiUser>(request, response),
				ctlBusiUser);
		return ResponseUtil.successResponse(page);
	}

	
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public CommonResponse save(CtlBusiUser ctlBusiUser,
			HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
	    Map<String, String> result = new HashMap<String, String>();
		ctlBusiUserService.save(ctlBusiUser);
		result.put("msg", "success");// 成功返回msg值为success的map
		return ResponseUtil.successResponse(result);
	}
	
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ApiOperation(value = "移动模块删除", notes = "移动模块删除")
	public CommonResponse delete(String primaryKeys,
			RedirectAttributes redirectAttributes) throws JsonProcessingException {
		Map<String, String> map = new HashMap<String, String>();
		CtlBusiUser ctlBusiUser = null;
		List<CtlBusiUser> deleteList = new ArrayList<CtlBusiUser>();
		String ids[] = primaryKeys.split(",");
		for (String id : ids) {
			if (StringUtils.isNotEmpty(id)) {
			 	ctlBusiUser = new CtlBusiUser();
				ctlBusiUser.setId(id);
				deleteList.add(ctlBusiUser);
			}
		}
		ctlBusiUserService.batchDelete(deleteList);
		map.put("msg", "success");
		return ResponseUtil.successResponse(map);
	}
	}