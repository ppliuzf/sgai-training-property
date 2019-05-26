package com.sgai.property.ctl.web;

import java.io.IOException;
import java.text.ParseException;
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

import com.sgai.common.persistence.Page;
import com.sgai.common.web.BaseController;
import com.sgai.property.ctl.entity.CtlAllocMenu;
import com.sgai.property.ctl.entity.CtlUser;
import com.sgai.property.ctl.service.CtlUserService;
import com.sgai.modules.login.consts.SysConsts;
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
    * ClassName: CtlUserController  
    * Description: (用户管理控制层)
    * @author 王天尧  
    * Date 2017年11月18日  
    * Company 首自信--智慧城市创新中心
 */
@RestController
@Api(description = "用户管理")
@RequestMapping(value = "${adminPath}/user/ctlUser")
public class CtlUserController extends BaseController {

	@Autowired
	private CtlUserService ctlUserService;
	
	/*@ModelAttribute
	public CtlUser get(@RequestParam(required=false) String id) {
		CtlUser entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ctlUserService.get(id);
		}
		if (entity == null){
			entity = new CtlUser();
		}
		return entity;
	}*/
	
	/**
	 * 
	 * save:(保存机构管理员/模块管理员).
	 * @param ctlUser
	 * @param userPass 用户密码
	 * @param userCode 用户代码
	 * @param startDate 开始时间
	 * @param endDate   结束时间
	 * @param model
	 * @param redirectAttributes
	 * @return
	 * @since JDK 1.8
	 * @author 王天尧
	 * @throws IOException
     */
	@ApiOperation(value = "保存机构管理员/模块管理员", notes = "保存机构管理员/模块管理员")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userPass", value = "用户密码", required = false, dataType = "String"),
            @ApiImplicitParam(name = "userCode", value = "用户代码", required = false, dataType = "String"),
            @ApiImplicitParam(name = "startDate", value = "有效开始时间", required = false, dataType = "String"),
            @ApiImplicitParam(name = "endDate", value = "有效结束时间", required = false, dataType = "String")
    })
	@RequestMapping(value = "/save",method=RequestMethod.POST)
	public CommonResponse save(
			CtlUser ctlUser,
			String userPass,
			String userCode,
			Model model,

			RedirectAttributes redirectAttributes) throws IOException {
		Map<String,String> map=new HashMap<String,String>();
		return ResponseUtil.successResponse(ctlUserService.saveMUser(ctlUser, userCode,userPass, map));
	}
	/**
	 * @throws IOException 
	 * @Title: saveIVuser
	    * @Description: (保存机构用户)
	    * @param @param ctlUser
	    * @param @param userCode
	    * @param @param userType
	    * @param @param startDate
	    * @param @param endDate
	    * @param @param empId
	    * @param @param model
	    * @param @param redirectAttributes
	    * @param @return
	    * @param @throws ParseException    参数  
	    * @return Map<String,String>    返回类型  
	    * @throws
	 */
	@ApiOperation(value = "保存机构用户", notes = "保存机构用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userPass", value = "用户密码", required = false, dataType = "String"),
            @ApiImplicitParam(name = "userCode", value = "用户代码", required = false, dataType = "String"),
            @ApiImplicitParam(name = "startDate", value = "有效开始时间", required = false, dataType = "String"),
            @ApiImplicitParam(name = "endDate", value = "有效结束时间", required = false, dataType = "String"),
            @ApiImplicitParam(name = "userType", value = "用户类型", required = false, dataType = "String"),
            @ApiImplicitParam(name = "empCode", value = "员工代码", required = false, dataType = "String")
    })
	@RequestMapping(value = "saveCUser",method=RequestMethod.POST)
	public CommonResponse saveCUser(
			CtlUser ctlUser,
			String userPass, 
			String userCode,
			String userType,
			String startDate,
			String endDate,
			String empCode,
			Model model,

			RedirectAttributes redirectAttributes) throws IOException {
		Map<String,String> map=new HashMap<String,String>();
		try {
			map=ctlUserService.saveCUser(ctlUser, userPass, userCode, userType, empCode, map);
		} catch (Exception e) {
			// : handle exception
			e.printStackTrace();
			map.put("msg", "faild");
		}
		return ResponseUtil.successResponse(map);
	}
	/**
	 * 
	 * delete:(删除用户).
	 * @param userIds 用户id集合
	 * @param redirectAttributes
	 * @return :Map<String,String> 
	 * @since JDK 1.8
	 * @author 王天尧
	 * @throws IOException
     */
	@ApiOperation(value = "删除用户", notes = "删除用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userIds", value = "用户id", required = false, dataType = "String"),
    })
	@RequestMapping(value = "delete",method=RequestMethod.POST)
	public CommonResponse delete(
			String userIds,

			RedirectAttributes redirectAttributes) throws IOException {
		Map<String,String> map=new HashMap<String,String>();
		try {
			System.err.println(userIds);
			 Map<String, String> result = ctlUserService.delete(userIds);
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
	 * getListUserM:(查询机构管理员列表).
	 * @param ctlUser
	 * @param userName 用户名称
	 * @param enabledFlag 可用标识
	 * @param request 
	 * @param response
	 * @return
	 * @throws IOException :Page<CtlUser> 
	 * @since JDK 1.8
	 * @author 王天尧
     */
	@ApiOperation(value = "查询机构管理员列表", notes = "查询机构管理员列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "用户名称", required = false, dataType = "String"),
    })
	@RequestMapping(value = "/getListUserM",method=RequestMethod.POST)
	public CommonResponse getListUserM(
			String userName,
			String enabledFlag,

			HttpServletRequest request, 
			HttpServletResponse response) throws IOException {
		CtlUser ctlUser = new CtlUser();
		ctlUser.setEnabledFlag(enabledFlag);
		return ResponseUtil.successResponse(ctlUserService.getListUserM(ctlUser,userName,new Page<CtlUser>(request, response)));
	}
	/**
	 * 
	    * @Title: getListModuUser  
	    * @Description: (查询模块管理员列表)
	    * @param @param userName
	    * @param @param token
	    * @param @param request
	    * @param @param response
	    * @param @return
	    * @param @throws IOException
	    * @param @throws ServletException    参数  
	    * @return CommonResponse    返回类型  
	    * @throws
	 */
	@ApiOperation(value = "查询模块管理员列表", notes = "查询模块管理员列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "用户名称", required = false, dataType = "String"),
    })
	@RequestMapping(value = "/getListModuUser",method=RequestMethod.POST)
	public CommonResponse getListModuUser(
			String userCode,
			String userName,
			String relevance,//非空则查询已关联模块的管理员
			String sbsCode,//

			HttpServletRequest request, 
			HttpServletResponse response) throws IOException {
		CtlUser ctlUser = new CtlUser();
		return ResponseUtil.successResponse(ctlUserService.getListModuUser(ctlUser,userCode,userName,relevance,sbsCode,new Page<CtlUser>(request, response)));
	}
	
	  
    /**  
    * @Title: getUserForM  
    * @Description: 密码修改专用  获得M级用户
    * @param @param userName  是userCode字段
    * @param @param enabledFlag
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
	@ApiOperation(value = "查询结构管理员", notes = "查询机构管理员")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "用户名称", required = false, dataType = "String"),
    })
	@RequestMapping(value = "/getUserForM",method=RequestMethod.POST)
	public CommonResponse getUserForM(LoginUser user,
			String userName,
			String enabledFlag,

			HttpServletRequest request, 
			HttpServletResponse response) throws IOException {
		CtlUser ctlUser = new CtlUser();
		ctlUser.setComCode(user.getComCode());
		ctlUser.setUserType("M");
		ctlUser.setUserCode(userName);
		ctlUser.setEnabledFlag(enabledFlag);
		return ResponseUtil.successResponse(ctlUserService.getUserM(ctlUser,new Page<CtlUser>(request, response)));
	}
	
	/**
	 * 
	 * getListCUse:(查询机构用户列表).
	 * @param ctlUser
	 * @param userCode 用户代码
	 * @param enabledFlag 可用标识
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException :Page<CtlUser> 
	 * @since JDK 1.8
	 * @author 王天尧
     */
	@ApiOperation(value = "查询机构用户列表", notes = "查询机构用户列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "用户名称", required = false, dataType = "String"),
    })
	@RequestMapping(value = "/getListCUser",method=RequestMethod.POST)
	public CommonResponse getListCUser(
			String userName,
			String corrCode,

			HttpServletRequest request, 
			HttpServletResponse response) throws IOException {
		CtlUser ctlUser = new CtlUser();
		return ResponseUtil.successResponse(ctlUserService.getListCUser(ctlUser, userName,corrCode,new Page<CtlUser>(request, response)));
	}
	@ApiOperation(value = "查询机构用户列表", notes = "查询机构用户列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userCode", value = "用户代码", required = false,paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "userName", value = "用户名称", required = false,paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "empCode", value = "员工代码", required = false,paramType = "query", dataType = "String")
           
    })
	@RequestMapping(value = "/getListIUser",method=RequestMethod.POST)
	public CommonResponse getListIUser(
			String userName,
			String userCode,
			String empCode,

			HttpServletRequest request, 
			HttpServletResponse response) throws IOException {
		CtlUser ctlUser = new CtlUser();
		return ResponseUtil.successResponse(ctlUserService.getListIUser(ctlUser, userCode,userName,empCode,new Page<CtlUser>(request, response)));
	}
	/**
	 * 
	 * findById:(通过id寻找用户).
	 * @param userIds 用户id集合
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException :Map<String,Object> 
	 * @since JDK 1.8
	 * @author 王天尧
     */
	@ApiOperation(value = "通过id寻找用户", notes = "通过id寻找用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userIds", value = "用户id", required = false, dataType = "String"),
    })
	@RequestMapping(value = "/findById",method=RequestMethod.POST)
	
	public CommonResponse findById(
			String userIds,

			HttpServletRequest request, 
			HttpServletResponse response) throws IOException {
		return ResponseUtil.successResponse(ctlUserService.findById(userIds));
	}
    /**
     * modifyPass:(修改指定用户的密码).
     * @param id 用户id
     * @param passOriginal
     * @param passNew1 新密码
     * @param passNew2 再输一次密码
     * @param request
     * @param response
     * @return
     * @since JDK 1.8
     * @author 王天尧
     * @throws IOException
     */
	@ApiOperation(value = "修改指定用户的密码", notes = "修改指定用户的密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", required = false, dataType = "String"),
            @ApiImplicitParam(name = "passOriginal", value = "原密码", required = false, dataType = "String"),
            @ApiImplicitParam(name = "passNew1", value = "新密码", required = false, dataType = "String"),
            @ApiImplicitParam(name = "passNew2", value = "再输一次密码", required = false, dataType = "String"),
    })
    @RequestMapping(value = "/modifyPass",method=RequestMethod.POST)
	public CommonResponse  modifyPass(
			String id ,
			String passOriginal,
			String passNew1,
			String passNew2,

			HttpServletRequest request, 
			HttpServletResponse response) throws IOException {
		if("".equals(id) || null ==id || "null".equals(id)) 
			//被修改密码用户的id 
			return ResponseUtil.customResponse(SysConsts.RESCODE_CUSTOM_MSG, "用户不正确！");			
		if("".equals(passNew1) || "".equals(passNew2)) 
			//新密码不能为空
			return ResponseUtil.customResponse(SysConsts.RESCODE_CUSTOM_MSG, "新密码不能为空值！");	
		if(!passNew1.equals(passNew2)) 
			return ResponseUtil.customResponse(SysConsts.RESCODE_CUSTOM_MSG, "两次输入的新密码不一样！");	
		boolean fg = ctlUserService.verificationAndSave(id, passOriginal, passNew1, passNew2);
		if(fg) 
			return ResponseUtil.successResponse("密码修改完成！");
		else
			return ResponseUtil.customResponse(SysConsts.RESCODE_CUSTOM_MSG, "原密码输入错误，不能修改当前用户密码！");	
		
	}
    
    
	/**
	 * @throws IOException 
	 * @Title: getList
	    * @Description: (查询C级用户)
	    * @param @return    参数  
	    * @return List<CtlUser>    返回类型  
	    * @throws
	 */
	@ApiOperation(value = "查询C级用户", notes = "查询C级用户")
	@RequestMapping(value = "/getUserBytype",method=RequestMethod.POST)
	public CommonResponse getList(@RequestHeader("token") String token) throws IOException {
		return ResponseUtil.successResponse(ctlUserService.getUserBytype());
	}
    
}