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

import com.sgai.common.persistence.Page;
import com.sgai.common.utils.StringUtils;
import com.sgai.common.web.BaseController;
import com.sgai.property.ctl.entity.CtlRole;
import com.sgai.property.ctl.entity.CtlUser;
import com.sgai.property.ctl.entity.CtlUserRole;
import com.sgai.property.ctl.service.CtlRoleService;
import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.modules.login.jwt.util.CommonResponse;
import com.sgai.modules.login.jwt.util.ResponseUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


/**
 * 
    * ClassName: CtlRoleController  
    * Description: (角色管理控制层)
    * @author 王天尧  
    * Date 2017年11月18日  
    * Company 首自信--智慧城市创新中心getRoleByComCode
 */
@RestController
@Api(description = "角色管理控制层")
@RequestMapping(value = "${adminPath}/ctl/ctlRole")
public class CtlRoleController extends BaseController {

	@Autowired
	private CtlRoleService ctlRoleService;
	/*@ModelAttribute
	public CtlRole get(@RequestParam(required=false) String id) {
		CtlRole entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ctlRoleService.get(id);
		}
		if (entity == null){
			entity = new CtlRole();
		}
		return entity;
	}*/
	
	  
    /**  
    * @Title: getRoleByComCode  
    * @Description: 首页展示角色   当前用户对应comCode 下面的角色
    * @param @param comCode
    * @param @param token
    * @param @param request
    * @param @param response
    * @param @return
    * @param @throws IOException
    * @param @throws ServletException    参数  
    * @return CommonResponse    返回类型  
    * @author admin
    * @throws  
    */  
	@ApiOperation(value = "根据机构代码获得角色", notes = "根据机构代码获得角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "comCode", value = "机构代码", required = false, dataType = "String"),
    })
	@RequestMapping(value = "/getRoleByComCode",method=RequestMethod.POST)
	public CommonResponse getRoleByComCode(
			LoginUser user,
			String roleCode1,
			String roleDesc1,

			HttpServletRequest request, 
			HttpServletResponse response) throws IOException {
		CtlRole ctlRole = new CtlRole();
		ctlRole.setRoleCode(roleCode1);
		ctlRole.setRoleDesc(roleDesc1);
		return ResponseUtil.successResponse(ctlRoleService.findPage(new Page<CtlRole>(request, response),ctlRole));
	}
	
	
	/**
	 * 
	 * save:(保存角色，包括插入和更新).
	 * @param ctlRole
	 * @param roleCode 角色代码
	 * @param model
	 * @param redirectAttributes
	 * @return :Map<String,String> 
	 * @since JDK 1.8
	 * @author 王天尧
	 * @throws IOException
     */
	@ApiOperation(value = "保存角色，包括插入和更新", notes = "保存角色，包括插入和更新")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleCode", value = "角色代码", required = false, dataType = "String"),
    })
	@RequestMapping(value = "save",method=RequestMethod.POST)
	public CommonResponse save(
			CtlRole ctlRole, 
			String roleCode, 
			Model model, 

			RedirectAttributes redirectAttributes) throws IOException {
		Map<String,String> map=new HashMap<String,String>();
		try {
			 map=ctlRoleService.saveRole(map, roleCode, ctlRole);
		} catch (Exception e) {
			// : handle exception
			e.printStackTrace();
			map.put("msg", "faild");
		}
		return ResponseUtil.successResponse(map);
	}
	
	/**
	 * 
	 * delete:(删除角色).
	 * @param roleIds 角色id集合
	 * @param redirectAttributes
	 * @return :Map<String,String> 
	 * @since JDK 1.8
	 * @author 王天尧
	 * @throws IOException
     */
	@ApiOperation(value = "删除角色", notes = "删除角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleIds", value = "角色代码id集合(主键id以逗号隔开)", required = false, dataType = "String"),
    })
	@RequestMapping(value = "delete",method=RequestMethod.POST)
	public CommonResponse delete(
			String roleIds,

			RedirectAttributes redirectAttributes) throws IOException {
		Map<String,String> map=new HashMap<String,String>();
		try {
			Map<String, String> result = ctlRoleService.deleteRole(roleIds);
			if(result.get("value")=="true") {
				map.put("msg", "success");
			}else {
				//代表与其他表有关联关系
				map.put("msg", "connection");
				map.put("desc", result.get("desc"));
			}
		} catch (Exception e) {
			// : handle exception
			e.printStackTrace();
			map.put("msg", "faild");
		}
		return ResponseUtil.successResponse(map);
	}
	/**
	 * 
	 * getListRole:(获取角色列表，带分页).
	 * @param ctlRole
	 * @param roleCode 角色代码
	 * @param enabledFlag 可用标识
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException :Page<CtlRole> 
	 * @since JDK 1.8
	 * @author 王天尧
     */
	@ApiOperation(value = "获取角色列表，带分页", notes = "获取角色列表，带分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleCode", value = "角色代码", required = false, dataType = "String"),
    })
	@RequestMapping(value = "/getListRole",method=RequestMethod.POST)
	public CommonResponse getListRole(
			String roleCode,

			HttpServletRequest request, 
			HttpServletResponse response) throws IOException {
		CtlRole ctlRole = new CtlRole();
		ctlRole.setRoleCode(roleCode);
		return ResponseUtil.successResponse(ctlRoleService.getListRole(ctlRole,roleCode, new Page<CtlRole>(request, response)));
	}
	/**
	 * 
	 * findById:(通过id获取角色).
	 * @param roleIds 角色id集合
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException :CtlRole 
	 * @since JDK 1.8
	 * @author 王天尧
     */
	@ApiOperation(value = "通过id获取角色", notes = "通过id获取角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleIds", value = "角色id", required = false, dataType = "String"),
    })
	@RequestMapping(value = "/findById",method=RequestMethod.POST)
	public CommonResponse findById(
			String roleIds,

			HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String[] ids = roleIds.split(",");
		CtlRole ctlRole = ctlRoleService.get(ids[0]);
		return ResponseUtil.successResponse(ctlRole);
	}
	/**
	 * 
	 * getRoleUser:(获取角色的用户信息).
	 * @param roleCode 角色代码
	 * @param ctlUserRole
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException :Page<CtlUser> 
	 * @since JDK 1.8
	 * @author 王天尧
     */
	@ApiOperation(value = "获取角色的用户信息", notes = "获取角色的用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleCode", value = "角色代码", required = false, dataType = "String"),
    })
	@RequestMapping(value = "/getRoleUser",method=RequestMethod.POST)
	public CommonResponse getRoleUser(
			String roleCode,

			HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		CtlUserRole ctlUserRole = new CtlUserRole();
		ctlUserRole.setRoleCode(roleCode);
		return ResponseUtil.successResponse(ctlRoleService.getRoleUser(roleCode, ctlUserRole, new Page<CtlUser>(request, response)));
	}
	/**
	 * 
	 * getUnUser:(获取没有得到该角色的用户).
	 * @param roleCode 角色代码
	 * @param ctlUserRole 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException :Page<CtlUser> 
	 * @since JDK 1.8
	 * @author 王天尧
     */
	@ApiOperation(value = "获取没有得到该角色的用户", notes = "获取没有得到该角色的用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleCode", value = "角色代码", required = false, dataType = "String"),
    })
	@RequestMapping(value = "/getUnUser",method=RequestMethod.POST)
	public CommonResponse getUnUser(
			String roleCode,
			HttpServletRequest request, 

			HttpServletResponse response) throws IOException {
		CtlUserRole ctlUserRole = new CtlUserRole();
		ctlUserRole.setRoleCode(roleCode);
		return ResponseUtil.successResponse(ctlRoleService.getUnUser(roleCode, ctlUserRole,new Page<CtlUser>(request, response)));
	}
}
