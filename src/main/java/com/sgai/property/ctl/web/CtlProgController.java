package com.sgai.property.ctl.web;

import java.io.IOException;
import java.util.HashMap;
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
import com.sgai.common.persistence.Page;
import com.sgai.common.web.BaseController;
import com.sgai.property.ctl.entity.CtlProg;
import com.sgai.property.ctl.service.CtlProgService;
import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.modules.login.jwt.util.CommonResponse;
import com.sgai.modules.login.jwt.util.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 
    * ClassName: CtlProgController  
    * Description: (功能维护)
    * @author yangyz  
    * Date 2017年11月18日  
    * Company 首自信--智慧城市创新中心
 */
@RestController
@RequestMapping(value = "${adminPath}/prog/ctlProg")
@Api(description = "功能维护接口")
public class CtlProgController extends BaseController {

	@Autowired
	private CtlProgService ctlProgService;

	/**
	 * 
	 * getListProg:(为List页面返回数据列表).
	 * @param ctlProg
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException :Page<CtlProg> 
	 * @since JDK 1.8
	 * @author yangyz
     */
	@ApiOperation(value = "获得功能分页列表信息", httpMethod = "POST", notes = "获得功能分页列表信息")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "progCode", value = "功能代码", required = false,paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "progName", value = "功能名称", required = false,paramType = "query", dataType = "String")
	})
	@RequestMapping(value = "/getListProg", method=RequestMethod.POST)
	public CommonResponse getListProg(
			LoginUser user,
			String progCode,
			String progName,

			@RequestParam(value = "pageNo", required = true,defaultValue="0") Integer pageNo ,
			@RequestParam(value = "pageSize", required = true,defaultValue="10") Integer pageSize,
			HttpServletRequest request, 
			HttpServletResponse response
			) throws IOException {
		CtlProg ctlProg = new CtlProg();
		ctlProg.setProgCode(progCode);
		ctlProg.setProgName(progName);
		Page<CtlProg> page = ctlProgService.findPage(new Page<CtlProg>(pageNo, pageSize), ctlProg);
		return ResponseUtil.successResponse(page);
	}

	/**
	 * 
	 * getProg:(获取一个实体对象).
	 * @param request
	 * @param id
	 * @param response
	 * @return
	 * @throws IOException :Map<String,Object> 
	 * @since JDK 1.8
	 * @author yangyz
     */
	@ApiOperation(value = "获得功能信息", httpMethod = "POST", notes = "获得功能信息")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "主键", required = false,paramType = "query", dataType = "String")
	})
	@RequestMapping(value = "/getProg", method=RequestMethod.POST)
	public CommonResponse getProg(
			String id,
			HttpServletRequest request, 

			HttpServletResponse response
			) throws IOException {
		CtlProg ctlProg = ctlProgService.get(id);
		return ResponseUtil.successResponse(ctlProg);
	}

	/**
	 * 
	 * save:(新增保存功能项).
	 * @param ctlProg
	 * @param model
	 * @param redirectAttributes
	 * @return :Map<String,Object> 
	 * @since JDK 1.8
	 * @author yangyz
	 * @throws IOException
     */
	@ApiOperation(value = "保存功能信息", httpMethod = "POST", notes = "保存功能信息")
	@RequestMapping(value = "save", method=RequestMethod.POST)
	public CommonResponse save(
			CtlProg ctlProg, 

			RedirectAttributes redirectAttributes
			) throws IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map = ctlProgService.saveProg(ctlProg);
		} catch (Exception e) {
			// : handle exception
			e.printStackTrace();
			map.put("msg", "fail");
		}
		return ResponseUtil.successResponse(map);
	}

	/**
	 * 
	 * delete:(删除功能项).
	 * @param ctlProg
	 * @param ids
	 * @param redirectAttributes
	 * @return :Map<String,Object> 
	 * @since JDK 1.8
	 * @author yangyz
	 * @throws IOException
     */
	@ApiOperation(value = "删除功能信息", httpMethod = "POST", notes = "删除功能信息")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "ids", value = "多个主键，逗号分隔", required = false,paramType = "query", dataType = "String")
	})
	@RequestMapping(value = "delete",method=RequestMethod.POST)
	public CommonResponse delete(
			String ids, 

			RedirectAttributes redirectAttributes
			) throws IOException {
		Map<String, Object> map = new HashMap<String, Object>(); 
		try {
			map = ctlProgService.deleteProg(ids);
		} catch (Exception e) {
			// : handle exception
			e.printStackTrace();
			map.put("msg", "出错了！");
			map.put("result", "fail");
		}
		return ResponseUtil.successResponse(map);
	}

	/**
	 * 
	 * getAllListProg:(公共下拉列表使用).
	 * @param ctlProg
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException :Map<Object,Object> 
	 * @since JDK 1.8
	 * @author yangyz
     */
	@ApiOperation(value = "查询功能列表信息", httpMethod = "POST", notes = "查询功能列表信息")
	@RequestMapping(value = "/getAllListProg",method=RequestMethod.POST)
	public CommonResponse getAllListProg(
			CtlProg ctlProg, 

			HttpServletRequest request, 
			HttpServletResponse response
			) throws IOException {
		java.util.List<CtlProg> list = ctlProgService.findList(ctlProg);
		Map<Object, Object> map = new HashMap<Object, Object>();
		for (CtlProg prog:list) {
			map.put(prog.getId(), prog.getProgName());
		}
		return ResponseUtil.successResponse(map);
	}
}