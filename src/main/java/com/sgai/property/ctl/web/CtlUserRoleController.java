package com.sgai.property.ctl.web;

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
import com.sgai.property.ctl.entity.CtlUser;
import com.sgai.property.ctl.entity.CtlUserRole;
import com.sgai.property.ctl.service.CtlUserRoleService;
import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.modules.login.jwt.util.CommonResponse;
import com.sgai.modules.login.jwt.util.ResponseUtil;
import com.sgai.modules.login.support.UserServletContext;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 *  
    * ClassName: CtlUserRoleController  
    * Description: (定义用户和角色关联的Controller)
    * @author ASUS  
    * Date 2017年11月20日  
    * Company 首自信--智慧城市创新中心
 */
@RestController
@Api(description = "定义用户和角色关联的Controller")
@RequestMapping(value = "${adminPath}/userrole/ctlUserRole")
public class CtlUserRoleController extends BaseController {

	@Autowired
	private CtlUserRoleService ctlUserRoleService;
	/**
	 * 
	 * save:(给角色分配用户).
	 * @param ctlUserRole
	 * @param userCodes 用户代码集合
	 * @param roleCode 角色代码
	 * @param model
	 * @param redirectAttributes
	 * @return :Map<String,String> 
	 * @since JDK 1.8
	 * @author 王天尧
	 * @throws IOException
     */
	@ApiOperation(value = "给角色分配用户", notes = "给角色分配用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userCodes", value = "用户代码", required = false, dataType = "String"),
            @ApiImplicitParam(name = "roleCode", value = "角色代码", required = false, dataType = "String"),
    })
	@RequestMapping(value = "save",method=RequestMethod.POST)
	public CommonResponse save(
			String userCodes,
			String roleCode,

			Model model, 
			RedirectAttributes redirectAttributes) throws IOException {
		Map<String,Object> result = Maps.newHashMap();
		try {
			CtlUserRole ctlUserRole = new CtlUserRole();
			Map<String,String> param = new HashMap<String,String>();
			param.put("roleCode", roleCode);
			ctlUserRole.setRoleCode(roleCode);
			ctlUserRoleService.delete(ctlUserRole);
			List<String>userCodeList = Lists.newArrayList();
			String[]userCodeArray =userCodes.split(",");
			for(String s : userCodeArray) {
				if(s!=null && !s.equals("")) {
					userCodeList.add(s);
				}
			}
			ctlUserRoleService.saveRoleUsers(userCodeList,roleCode);
			result.put("state", true);
			result.put("msg", "保存成功!");
		}catch(Exception e) {
			e.printStackTrace();
			result.put("state", false);
			result.put("msg", "保存失败!");
		}
		return ResponseUtil.successResponse(result);
	}
	/**
	 * 
	 * delete:(移除某个角色的用户).
	 * @param ctlUserRole
	 * @param userCodes 用户代码集合
	 * @param roleCode 角色代码
	 * @param redirectAttributes
	 * @return :Map<String,String> 
	 * @since JDK 1.8
	 * @author 王天尧
	 * @throws IOException
     */
	@ApiOperation(value = "移除某个角色的用户", notes = "移除某个角色的用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userCodes", value = "用户代码", required = false, dataType = "String"),
            @ApiImplicitParam(name = "roleCode", value = "角色代码", required = false, dataType = "String"),
    })
	@RequestMapping(value = "/delete",method=RequestMethod.POST)
	public  CommonResponse delete(
			String userCodes,
			String roleCode, 

			RedirectAttributes redirectAttributes) throws IOException {
		Map<String,String> map=new HashMap<String,String>();
		try {
			CtlUserRole ctlUserRole = new CtlUserRole();
			ctlUserRoleService.delete(ctlUserRole, userCodes, roleCode);
			map.put("msg", "success");
		} catch (Exception e) {
			// : handle exception
			e.printStackTrace();
			map.put("msg", "faild");
		}
		return ResponseUtil.successResponse(map);
	}
	/**
	 * 
	 * getLackList:(获取未拥有的用户).
	 * @param roleCode 角色代码
	 * @return :Map<String,Object> 
	 * @since JDK 1.8
	 * @author 王天尧
	 * @throws IOException 
	 * @throws ServletException 
	 */
	@ApiOperation(value = "获取未拥有的用户", notes = "获取未拥有的用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageSize", value = "每页显示数", required = false, dataType = "String"),
            @ApiImplicitParam(name = "roleCode", value = "角色代码", required = false, dataType = "String"),
            @ApiImplicitParam(name = "pageNo", value = "页码", required = false, dataType = "String"),
    })
	@RequestMapping(value = "/getLackList",method=RequestMethod.POST)
	public CommonResponse getLackList(
			String roleCode, 
			String userCode, 
			String userName, 
			int pageSize,
			int pageNo,
			Model model, 

			HttpServletRequest request, 
			HttpServletResponse response) throws Exception, IOException {

			Page<CtlUser> page = new Page<>(request, response);
			page.setPageNo(pageNo);
			page.setPageSize(pageSize);
			//获取当前登录用户
			LoginUser userInfo = UserServletContext.getUserInfo();
			Map<String,Object> param = new HashMap<>();
			param.put("roleCode", roleCode);
			param.put("comCode", userInfo.getComCode());
			param.put("moduCode", userInfo.getModuCode());
			param.put("userType", "I");
			param.put("userCode", userCode);
			param.put("userName", userName);

			page = ctlUserRoleService.getUnUsers(param,page);
		return ResponseUtil.successResponse(page);
	}
	/**
	 * 
	 * getOwnUsers:(获得该角色已经拥有的用户).
	 * @param roleCode
	 * @return :List<CtlUser> 
	 * @since JDK 1.8
	 * @author 王天尧
	 * @throws IOException
     */
	@ApiOperation(value = "获得该角色已经拥有的用户", notes = "获得该角色已经拥有的用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleCode", value = "角色代码", required = false, dataType = "String")
    })
	@RequestMapping(value = "getOwnUsers",method=RequestMethod.POST)
	public CommonResponse getOwnUsers(
			String roleCode, 
			String userCode, 
			String userName,
			@RequestHeader("token") String token
			) throws IOException{
		//获取当前登录用户
		LoginUser userInfo = UserServletContext.getUserInfo();
		Map<String,String> param = new HashMap<String,String>();
		param.put("roleCode", roleCode);
		param.put("comCode", userInfo.getComCode());
		param.put("moduCode", userInfo.getModuCode());
		param.put("userCode", userCode);
		param.put("userName", userName);
		param.put("userType", "I");
		return ResponseUtil.successResponse(ctlUserRoleService.getOwnUsers(param));
	}
	/**
	 * 
	 * getRoleTree:(得到角色树).
	 * @param model
	 * @param redirectAttributes
	 * @return :List<Map<String,String>> 
	 * @since JDK 1.8
	 * @author 王天尧
	 * @throws IOException
     */
	@ApiOperation(value = "得到角色树", notes = "得到角色树")
	@RequestMapping(value = "/getRoleTree",method=RequestMethod.POST)
	public CommonResponse getRoleTree(

			Model model, 
			RedirectAttributes redirectAttributes) throws IOException {
	    List<Map<String,String>> list =ctlUserRoleService.getRoleTree();
		return ResponseUtil.successResponse(list);
	}
}