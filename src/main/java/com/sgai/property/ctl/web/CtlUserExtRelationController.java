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
import com.sgai.property.ctl.entity.CtlUserExtRelation;
import com.sgai.property.ctl.service.CtlUserExtRelationService;

/**
 * 用户Controller
 * @author 李伟
 * @version 2018-02-10
 */
@RestController
@Api(description = "用户")
@RequestMapping(value = "${adminPath}/ctl/ctlUserExtRelation")
public class CtlUserExtRelationController extends BaseController {

	@Autowired
	private CtlUserExtRelationService ctlUserExtRelationService;
	
	@ApiOperation(value = "根据id找到所需要修改的对象", notes = "根据id找到所需要修改的对象")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "想要修改的对象的id", required = false, dataType = "String"), })
	@RequestMapping(value = "/findById", method = RequestMethod.POST)
	public CommonResponse findById(String id,  HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		CtlUserExtRelation ctlUserExtRelation = ctlUserExtRelationService.get(id);
		return ResponseUtil.successResponse(ctlUserExtRelation);
	}
	
	
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public CommonResponse list(
			@RequestParam(value = "pageNo", required = true, defaultValue = "0") Integer pageNo,
			@RequestParam(value = "pageSize", required = true, defaultValue = "10") Integer pageSize,
			HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		CtlUserExtRelation  ctlUserExtRelation = new CtlUserExtRelation();
		Page<CtlUserExtRelation> page = ctlUserExtRelationService.findPage(new Page<CtlUserExtRelation>(request, response),
				ctlUserExtRelation);
		return ResponseUtil.successResponse(page);
	}

	
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public CommonResponse save(CtlUserExtRelation ctlUserExtRelation,
			HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
	    Map<String, String> result = new HashMap<String, String>();
		ctlUserExtRelationService.save(ctlUserExtRelation);
		result.put("msg", "success");// 成功返回msg值为success的map
		return ResponseUtil.successResponse(result);
	}
	
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ApiOperation(value = "移动模块删除", notes = "移动模块删除")
	public CommonResponse delete(String primaryKeys,
			RedirectAttributes redirectAttributes) throws JsonProcessingException {
		Map<String, String> map = new HashMap<String, String>();
		CtlUserExtRelation ctlUserExtRelation = null;
		List<CtlUserExtRelation> deleteList = new ArrayList<CtlUserExtRelation>();
		String ids[] = primaryKeys.split(",");
		for (String id : ids) {
			if (StringUtils.isNotEmpty(id)) {
			 	ctlUserExtRelation = new CtlUserExtRelation();
				ctlUserExtRelation.setId(id);
				deleteList.add(ctlUserExtRelation);
			}
		}
		ctlUserExtRelationService.batchDelete(deleteList);
		map.put("msg", "success");
		return ResponseUtil.successResponse(map);
	}
	}