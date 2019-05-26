package com.sgai.property.ctl.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sgai.common.config.Global;
import com.sgai.common.persistence.Page;
import com.sgai.common.utils.StringUtils;
import com.sgai.common.web.BaseController;
import com.sgai.property.ctl.entity.CtlLog;
import com.sgai.property.ctl.service.CtlLogService;
import com.sgai.modules.login.jwt.util.CommonResponse;
import com.sgai.modules.login.jwt.util.ResponseUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 在线用户管理控制层
 * @author guanze
 * @version 2017-11-09
 * @version 首自信--智慧城市创新中心
 */
@RestController
@RequestMapping(value = "${adminPath}/ctlLog")
@Api(description = "在线用户管理接口")
public class CtlLogController extends BaseController {

	@Autowired
	private CtlLogService ctlLogService;
	
	/**
	 * get:获取单条数据
	 * @param id
	 * @return :CtlLog 
	 * @since JDK 1.8
	 * @author guanze
	 */
	@ModelAttribute
	public CtlLog get(@RequestParam(required=false) String id) {
		CtlLog entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ctlLogService.get(id);
		}
		if (entity == null){
			entity = new CtlLog();
		}
		return entity;
	}
	
	/**
	 * list:获取列表
	 * @param ctlLog
	 * @param request
	 * @param response
	 * @param model
	 * @return :String 
	 * @since JDK 1.8
	 * @author guanze
	 */
	@RequestMapping(value = {"list", ""})
	public String list(CtlLog ctlLog, HttpServletRequest request, HttpServletResponse response, Model model) {
		if(ctlLog==null){
			ctlLog=new CtlLog();
		}

		Page<CtlLog> page = ctlLogService.findPage(new Page<CtlLog>(request, response), ctlLog); 
		model.addAttribute("page", page);
		return "ctl/ctlLogList";
	}

	@RequestMapping(value = "form")
	public String form(CtlLog ctlLog, Model model) {
		model.addAttribute("ctlLog", ctlLog);
		return "ctl/ctlLogForm";
	}

	/**
	 * save:保存数据
	 * @param ctlLog
	 * @param model
	 * @param redirectAttributes
	 * @return :String 
	 * @since JDK 1.8
	 * @author guanze
	 */
	@RequestMapping(value = "save")
	public String save(CtlLog ctlLog, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ctlLog)){
			return form(ctlLog, model);
		}
		ctlLogService.save(ctlLog);
		addMessage(redirectAttributes, "保存&quot;在线用户管理&quot;成功");
		return "redirect:"+Global.getAdminPath()+"/useronline/ctlLog/?repage";
	}
	
	/**
	 * delete:删除单条数据
	 * @param ctlLog
	 * @param redirectAttributes
	 * @return :String 
	 * @since JDK 1.8
	 * @author guanze
	 */
	@RequestMapping(value = "delete")
	public String delete(CtlLog ctlLog, RedirectAttributes redirectAttributes) {
		return "";
	}

	
	
	/**
	 * update:更新单条数据
	 * @param ids
	 * @param redirectAttributes
	 * @return :String 
	 * @since JDK 1.8
	 * @author guanze
	 */
	@RequestMapping(value = "update")
	public String update(String ids, RedirectAttributes redirectAttributes) {
		String data = new String();
		String idList[]=ids.split(",");
		for(String id:idList){
			if(id!=null&&!id.equals("")){
				CtlLog ctlLog = ctlLogService.get(id);
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
				System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
				List<CtlLog> resultlist = ctlLogService.findList(ctlLog);
				
				//System.out.println("***"+ctlLog.getId()+" "+ctlLog.getOnlineFlag()+" "+(ctlLog.getOnlineFlag().equals("N")));
				try{
					if (resultlist.size()==0 || ctlLog.getOnlineFlag().equals("N")) {
						data += "机构："+ctlLog.getComName()+" 用户："+ctlLog.getUserName()+" 登陆IP:"+ctlLog.getLoginIp()+" 该记录不存在或用户已下线，请刷新页面重试或联系管理员\n";
					} else {
						//System.out.println("***"+ctlLog.getId()+" "+ctlLog.getOnlineFlag());
						ctlLog.setOnlineFlag("N");
						ctlLog.setLogoutTime((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(df.format(new Date()))));
						ctlLogService.save(ctlLog);
						//System.out.println("***"+ctlLog.getId()+" "+ctlLog.getOnlineFlag());
						data += "机构："+ctlLog.getComName()+" 用户："+ctlLog.getUserName()+" 登陆IP:"+ctlLog.getLoginIp()+" 下线成功\n";
					}
				}
				catch (Exception e) {
					data += "机构："+ctlLog.getComName()+" 用户："+ctlLog.getUserName()+" 登陆IP:"+ctlLog.getLoginIp()+" 下线失败，请刷新页面重试或联系管理员\n";
					e.printStackTrace();
				}
			}
		}
		return data;
	}
	
	/**
	 * getListCtlLog:获取日志列表
	 * @param ctlLog
	 * @param comCode
	 * @param userCode
	 * @param onlineFlag
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException :Page<CtlLog> 
	 * @since JDK 1.8
	 * @author guanze
	 */
	@ApiOperation(value = "获取在线用户日志信息", notes = "获取在线用户日志信息")
//	@ApiImplicitParams({
//		@ApiImplicitParam(name = "comCode", value = "机构编码",required = false,paramType = "query", dataType = "String"),
//		@ApiImplicitParam(name = "userCode", value = "用户编码",required = false,paramType = "query", dataType = "String"),
//		@ApiImplicitParam(name = "onlineFlag", value = "生效标识",required = false,paramType = "query", dataType = "String")
//	})
	@RequestMapping(value = "/getListCtlLog",method=RequestMethod.POST)
	public CommonResponse getListCtlLog(@RequestBody CtlLog ctlLog, 
//			@RequestParam(value = "pageNo", required = false, defaultValue = "0") Integer pageNo,
//			@RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
			@RequestHeader("token") String token)
			throws IOException {
		//TokenUtil.parseToken(token);
		//System.out.println("*** "+ctlLog.getPageNo()+" "+ctlLog.getPageSize()+" ***");
		
		Page<CtlLog> page = ctlLogService.findPage(new Page<CtlLog>(ctlLog.getPageNo(), ctlLog.getPageSize()), ctlLog);

		return ResponseUtil.successResponse(page);
	}

//	@ApiOperation(value = "获取在线用户日志信息", notes = "获取在线用户日志信息")
////	@ApiImplicitParams({
////		@ApiImplicitParam(name = "comCode", value = "机构编码",required = false,paramType = "query", dataType = "String"),
////		@ApiImplicitParam(name = "userCode", value = "用户编码",required = false,paramType = "query", dataType = "String"),
////		@ApiImplicitParam(name = "onlineFlag", value = "生效标识",required = false,paramType = "query", dataType = "String")
////	})
//	@RequestMapping(value = "/getListCtlLog",method=RequestMethod.POST)
//	public CommonResponse getListCtlLog(String comCode, String userCode, String onlineFlag,
//			@RequestParam(value = "pageNo", required = true, defaultValue = "0") Integer pageNo,
//			@RequestParam(value = "pageSize", required = true, defaultValue = "10") Integer pageSize,
//			@RequestHeader("token") String token)
//			throws ServletException, IOException {
//		TokenUtil.parseToken(token);
//		CtlLog ctlLog = new CtlLog();
//		ctlLog.setComCode(comCode);
//		ctlLog.setUserCode(userCode);
//		ctlLog.setOnlineFlag(onlineFlag);
//		// System.out.println("#####"+comCode+" "+userCode+" "+onlineFlag);
//		Page<CtlLog> page = ctlLogService.findPage(new Page<CtlLog>(pageNo, pageSize), ctlLog);
//
//		return ResponseUtil.successResponse(page);
//	}
	
	/**
	 * getListCtlLogDetail:获取日志对应的明细列表
	 * @param ids
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException :List<Map<String,String>> 
	 * @since JDK 1.8
	 * @author guanze
	 */
	@RequestMapping(value = "/getListCtlLogDetail")
	public CommonResponse getListCtlLogDetail(CtlLog ctlLog,String ids,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		ctlLog.setComCode(ids);
		Page<Map<String,String>> page = ctlLogService.findLogs(new Page<Map<String,String>>(ctlLog.getPageNo(), ctlLog.getPageSize()), ctlLog);
		return ResponseUtil.successResponse(page);
	}
	
	/**
	 * getComList:获取机构下拉菜单
	 * @param request
	 * @param response
	 * @return
	 * @since JDK 1.8
	 * @author guanze
	 */
	@RequestMapping(value = "/getComList")
	public List<Map<String,String>> getComList(HttpServletRequest request, HttpServletResponse response) {
		return ctlLogService.getComList();
	}
	
	/**
	 * getComFromUserList:获取用户下拉菜单
	 * @param comCode
	 * @param request
	 * @param response
	 * @return
	 * @since JDK 1.8
	 * @author guanze
	 */
	@RequestMapping(value = "/getComFromUser")
	public List<Map<String,String>> getComFromUserList(String comCode,HttpServletRequest request, HttpServletResponse response) {
		return ctlLogService.getComFromUserList(comCode);
	}
	
	
}