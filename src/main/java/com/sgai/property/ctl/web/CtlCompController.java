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
import com.sgai.property.ctl.dto.CtlCompVo;
import com.sgai.property.ctl.entity.CtlComp;
import com.sgai.property.ctl.service.CtlCompService;
import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.modules.login.jwt.util.CommonResponse;
import com.sgai.modules.login.jwt.util.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 
    * ClassName: CtlCompController  
    * Description: (机构维护)
    * @author yangyz  
    * Date 2017年11月18日  
    * Company 首自信--智慧城市创新中心
 */
@RestController
@RequestMapping(value = "${adminPath}/ctl/comp/ctlComp")
@Api(description = "机构接口")
public class CtlCompController extends BaseController {

	@Autowired
	private CtlCompService ctlCompService;
	
	/**
	 * 
	 * getListComp:(为List页面返回数据列表).
	 * @param ctlComp
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException :Page<CtlComp> 
	 * @since JDK 1.8
	 * @author yangyz
     */
	@ApiOperation(value = "获得机构分页列表信息", httpMethod = "POST", notes = "获得机构分页列表信息")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "comCode", value = "机构代码", required = false,paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "comName", value = "机构名称", required = false,paramType = "query", dataType = "String")
	})
	@RequestMapping(value = "/getListComp",method=RequestMethod.POST)
	public CommonResponse getListComp(
			LoginUser user,
			String comCode,
			String comName,

			@RequestParam(value = "pageNo", required = true,defaultValue="0") Integer pageNo ,
			@RequestParam(value = "pageSize", required = true,defaultValue="10") Integer pageSize,
			HttpServletRequest request, 
			HttpServletResponse response
			) throws IOException {
		CtlComp ctlComp = new CtlComp();
		ctlComp.setComCode(comCode);
		ctlComp.setComName(comName);
		Page<CtlCompVo> page = ctlCompService.findPageForVo(new Page<CtlComp>(pageNo, pageSize), ctlComp);
		return ResponseUtil.successResponse(page);
	}
	
	/**
	 * 
	 * getComp:(获取一个实体对象).
	 * @param request
	 * @param id
	 * @param response
	 * @return
	 * @throws IOException :CtlComp 
	 * @since JDK 1.8
	 * @author yangyz
     */
	@ApiOperation(value = "获得机构信息", httpMethod = "POST", notes = "获得机构信息")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "主键", required = false,paramType = "query", dataType = "String")
	})
	@RequestMapping(value = "/getComp",method=RequestMethod.POST)
	public CommonResponse getComp(
			String id, 

			HttpServletRequest request,
			HttpServletResponse response
			) throws IOException {
		CtlComp ctlComp = ctlCompService.get(id);
		return ResponseUtil.successResponse(ctlComp);
	}

	/**
	 * 
	 * save:(新增保存功能项).
	 * @param ctlComp
	 * @param model
	 * @param redirectAttributes
	 * @return :Map<String,Object> 
	 * @since JDK 1.8
	 * @author yangyz
	 * @throws IOException
     */
	@ApiOperation(value = "保存机构信息", httpMethod = "POST", notes = "保存机构信息")
	@RequestMapping(value = "save",method=RequestMethod.POST)
	public CommonResponse save(
			CtlComp ctlComp, 

			RedirectAttributes redirectAttributes
			) throws IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		ctlComp.setEnabledFlag("Y");
		try {
			map = ctlCompService.saveComp(ctlComp);
		} catch (Exception e) {
			// : handle exception
			e.printStackTrace();
			map.put("msg", "fail");
		}
		return ResponseUtil.successResponse(map);
	}

	/**
	 * 
	 * delete:(删除机构项).
	 * @param ids
	 * @param redirectAttributes
	 * @return :Map<String,Object> 
	 * @since JDK 1.8
	 * @author yangyz
	 * @throws IOException
     */
	@ApiOperation(value = "删除机构信息", httpMethod = "POST", notes = "删除机构信息")
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
			map = ctlCompService.deleteComp(ids);
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
	 * getAllListComp:(公共下拉列表使用).
	 * @param ctlComp
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException :Map<Object,Object> 
	 * @since JDK 1.8
	 * @author yangyz
     */
	@ApiOperation(value = "获得所有机构信息", httpMethod = "POST", notes = "获得所有机构信息")
	@RequestMapping(value = "/getAllListComp",method=RequestMethod.POST)
	public CommonResponse getAllListComp(
			CtlComp ctlComp, 

			HttpServletRequest request, 
			HttpServletResponse response
			) throws IOException {
		Map<Object, Object> map = ctlCompService.getAllListComp(ctlComp);
		return ResponseUtil.successResponse(map);
	}

	/**
	 * 
	 * getYListComp:(获得可用机构列表信息).
	 * @param ctlComp
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException :Page<CtlComp> 
	 * @since JDK 1.8
	 * @author yangyz
     */
	@ApiOperation(value = "获得可用机构列表信息", httpMethod = "POST", notes = "获得可用机构列表信息")
	@RequestMapping(value = "/getYListComp",method=RequestMethod.POST)
	public CommonResponse getYListComp(
			CtlComp ctlComp, 

			HttpServletRequest request, 
			HttpServletResponse response
			) throws IOException {
		Page<CtlComp> page = ctlCompService.getYListComp(ctlComp,request, response);
		return ResponseUtil.successResponse(page);
	}
	/**
	 * 
	 * getComTree:(返回机构树结构数据).
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException :List<Map<String,String>> 
	 * @since JDK 1.8
	 * @author 王天尧
     */
	@ApiOperation(value = "获得机构列表信息", httpMethod = "POST", notes = "获得机构列表信息")
	@RequestMapping(value = "/getComTree",method=RequestMethod.POST)
	public CommonResponse getComTree(
			HttpServletRequest request, 

			HttpServletResponse response
			) throws IOException {
		return ResponseUtil.successResponse(ctlCompService.getComTree());
	}
}