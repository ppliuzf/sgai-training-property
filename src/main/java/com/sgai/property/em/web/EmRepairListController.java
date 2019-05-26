package com.sgai.property.em.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
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
import com.sgai.property.ctl.entity.CtlAttachfile;
import com.sgai.property.ctl.entity.CtlCodeDet;
import com.sgai.property.ctl.service.CtlAttachfileService;
import com.sgai.property.ctl.service.CtlCodeDetService;
import com.sgai.property.ctl.service.CtlComRuleService;
import com.sgai.property.ctl.service.DeleteRulesUtils;
import com.sgai.property.em.dto.EmRepairListVo;
import com.sgai.property.em.entity.EmRepairList;
import com.sgai.property.em.service.EmRepairListService;
import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.modules.login.jwt.util.CommonResponse;
import com.sgai.modules.login.jwt.util.ResponseUtil;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.wf.service.WfUserRightService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;



/**
 * 修理事件维护Controller
 * @author guanze
 * @version 2017-12-05
 */
@RestController
@RequestMapping(value = "${adminPath}/emRepairList")
@Api(description = "修理事件接口")
public class EmRepairListController extends BaseController {

	@Autowired
	private EmRepairListService emRepairListService;
	@Autowired
	private WfUserRightService wfUserRightService;
	@Autowired
	private DeleteRulesUtils deleteRulesUtils;
	@Autowired
	private CtlAttachfileService ctlAttachfileService;
	@Autowired
	private CtlComRuleService ctlComRuleService;
	@Autowired
	private CtlCodeDetService ctlCodeDetService;
	
	SimpleDateFormat formatParam = new SimpleDateFormat("yyyyMMdd");
	

	@ApiOperation(value = "修理事件form页面", notes = "修理事件form页面")
	@RequestMapping(value = "form",method=RequestMethod.GET)
	public String form(@RequestHeader(value="token")String token,EmRepairList emRepairList, Model model) {
		model.addAttribute("emRepairList", emRepairList);
		return "em/emrepairlist/emRepairListForm";
	}

	@ApiOperation(value = "保存修理事件并跟新流程实例", notes = "保存修理事件并跟新流程实例")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "repairPhoto", value = "图片，|线分割多张", required = false,paramType = "query", dataType = "String")
	})
	@RequestMapping(value = "save",method=RequestMethod.POST)
	public Map<String,String> save(
			@RequestHeader(value="token")String token,
			@RequestBody EmRepairList emRepairList,
			HttpServletRequest request
			) {
		Map<String,String> data = new HashMap<String,String>();
		emRepairList.setEnabledFlag("Y");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		LoginUser user = UserServletContext.getUserInfo();
		try {
			if (StringUtils.isBlank(emRepairList.getId())) {
				String emCode = ctlComRuleService.getNext("EMCODE-WX");
				emRepairList.setEmCode(emCode);
				emRepairList.setFromNum(emCode);
				emRepairList.setRepairFrom("手动事件");
				emRepairListService.saveRepair(emRepairList,user);
				//String path="";//图片服务器地址
				if (emRepairList.getRepairPhoto()!=null && !"".equals(emRepairList.getRepairPhoto())) {
					String[] images = emRepairList.getRepairPhoto().split("\\|");
					for(String str:images) {
						CtlAttachfile p = new CtlAttachfile();
				        p.setMasterFileType("报修事件");//模块名称
				        p.setKeyDesc("报修图片");//文件描述
				        p.setMasterFileId(emRepairList.getId());//主表id
				        p.setUploadSession(token);//用户token
				        String filePath = str.substring(0, 31) + user.getUserId() + "/";
				        String fileName = str.substring(filePath.length());
				        p.setFileName(fileName);//文件名称
				        p.setFileLoc(filePath);//文件存储路径
				        p.setUploadTime(format.format(new Date()));//上传时间
				        p.setContentType("multipart/form-data; charset=utf-8");//文件MIME类型
				        //File file = new File(path + str);
				        //p.setFileSize(String.valueOf(file.length()));//文件大小,单位byte
				        ctlAttachfileService.saveAttachfile(p);
					}
				}
				data.put("message", "success");
			} else {
				//如果是更新数据，直接执行save方法
				emRepairListService.save(emRepairList);
				data.put("message", "success");
			}
		} catch (Exception e) {
			data.put("message", "failed");
			e.printStackTrace();
		}
		return data;
	}
	
	@ApiOperation(value = "修理事件删除", notes = "修理事件删除")
	@RequestMapping(value = "delete",method=RequestMethod.POST)
	public Map<String,String> delete(@RequestHeader(value="token")String token,String ids) {
		String[] idArr=ids.split(",");
		List<String> idList = new ArrayList<String>();
		List<EmRepairList> emList = new ArrayList<EmRepairList>();
		for(String id:idArr){
			if(id!=null&&!id.equals("")){
				idList.add(id);
				EmRepairList emRepairList = emRepairListService.get(id);
				emList.add(emRepairList);
			}
		}
		// 检查是否满足删除条件
		Map<String, String> data = deleteRulesUtils.checkBeforeDelete(EmRepairList.class, idList);
		if ("true".equals(data.get("value"))) {
			List<EmRepairList> finalList = emRepairListService.batchDelete(emList);
			if (finalList.size() > 0) {
				data.put("result", "删除成功");
			} else {
				data.put("result", "删除失败");
			}
		}
		return data;
	}

	@ApiOperation(value = "修理事件分页", notes = "修理事件分页")
	@RequestMapping(value = "/getListEmRepairList",method=RequestMethod.POST)
	public Page<EmRepairList> getListEmRepairList(EmRepairList emRepairList,
			LoginUser userInfo,
			@RequestHeader(value="token")String token,
			@RequestParam(value="pageNo",required = true ,defaultValue = "0" )Integer pageNo,
			@RequestParam(value="pageSize",required = true ,defaultValue = "10" )Integer pageSize,
			HttpServletRequest request, HttpServletResponse response) {
		emRepairList.setUserCode(userInfo.getUserId());
		Page<EmRepairList> page = emRepairListService.findPage(new Page<EmRepairList>(pageNo, pageSize), emRepairList);
		return page;
	}
	@ApiOperation(value = "修理事件不分页", notes = "修理事件不分页")
	@RequestMapping(value = "/getList",method=RequestMethod.POST)
	public List<EmRepairList> getList(EmRepairList emRepairList,
			@RequestHeader(value="token")String token,
			@RequestParam(value="pageNo",required = true ,defaultValue = "0" )Integer pageNo,
			@RequestParam(value="pageSize",required = true ,defaultValue = "10" )Integer pageSize,
			HttpServletRequest request, HttpServletResponse response) {
		return emRepairListService.findList(emRepairList);
	}
	@ApiOperation(value = "根据id查询修理事件", notes = "根据id查询修理事件")
	@RequestMapping(value = "/findByIdEmRepairList",method=RequestMethod.POST)
	public EmRepairList findByIdEmRepairList(@RequestHeader(value="token")String token,EmRepairList emRepairList,HttpServletRequest request, HttpServletResponse response) {
		return emRepairListService.get(emRepairList.getId());
	}
	
	@ApiOperation(value = "修理事件下一个", notes = "修理事件下一个")
	@RequestMapping(value = "/findNextCodeEmRepairList",method=RequestMethod.POST)
	public EmRepairList findNextCodeEmRepairList(@RequestHeader(value="token")String token,HttpServletRequest request, HttpServletResponse response) {
		return emRepairListService.findNextCodeEmRepairList();
	}
	/**
	 * 
	 * getLoginUser:(获取当前登录用户).
	 * @return :List<CtlUser> 
	 * @since JDK 1.8
	 * @author 王天尧
	 */
	@ApiOperation(value = "获取当前登录用户", notes = "获取当前登录用户")
	@RequestMapping(value = "/getLoginUser",method=RequestMethod.POST)
	public LoginUser getLoginUser(){
		LoginUser userInfo = UserServletContext.getUserInfo();
		return userInfo;
	}
	/**
	 * 
	 * updateRepair:(更新事件状态).
	 * @param emRepairList
	 * @return :Map<String,String> 
	 * @since JDK 1.8
	 * @author 王天尧
	 */
	@ApiOperation(value = "更新事件状态", notes = "更新事件状态")
	@RequestMapping(value = "updateRepair",method=RequestMethod.POST)
	public Map<String,String> updateRepair(@RequestHeader(value="token")String token,
			LoginUser userInfo,
			EmRepairList emRepairList) {
		Map<String,String> data = new HashMap<String,String>();
		
		try {
			EmRepairList info = emRepairListService.get(emRepairList.getId());
			info.setStates(emRepairList.getStates());
			//info.setProcPerson(userInfo.getUserName());
			emRepairListService.save(info);
			data.put("result", "已终止");
		} catch (Exception e) {

			e.printStackTrace();
			data.put("result", "终止失败");
		}
		return data;
	}
	/**
	 * 
	 * getListByUser:(根据用户过滤流程实例).
	 * @param emRepairList
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException :Page<EmRepairList> 
	 * @since JDK 1.8
	 * @author 王天尧
	 */
	@ApiOperation(value = "根据用户过滤流程实例", notes = "根据用户过滤流程实例")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "emCode", value = "事件编号", required = false,paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "repairType", value = "事件类别", required = false,paramType = "query", dataType = "String")
	})
	@RequestMapping(value = "/getListByUser",method=RequestMethod.POST)
	public CommonResponse getListByUser(
			String emCode,
			String repairType,
			String repairPerson,
			String startDate,
			String endDate,
			LoginUser userInfo,
			@RequestHeader(value="token")String token,
			@RequestParam(value="pageNo",required = true ,defaultValue = "0" )Integer pageNo,
			@RequestParam(value="pageSize",required = true ,defaultValue = "10" )Integer pageSize,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		EmRepairList emRepairList = new EmRepairList();
		emRepairList.setEmCode(emCode);
		//emRepairList.setRepairPerson(repairPerson);
		emRepairList.setRepairType(repairType);
		Map<String,String> param = new HashMap<String,String>();
		param.put("flowCode", "WX");
		param.put("userCode", userInfo.getUserId());
		param.put("comCode", userInfo.getComCode());
		Map<String, String> userMap = wfUserRightService.getListsByUser(param);
		if (startDate != null && !"".equals(startDate)) {
			String sql = userMap.get("sqlMap") + " and a.REPAIR_DATE >= " +  "TO_DATE ('"+startDate +"','yyyy-mm-dd')";
			userMap.put("sqlMap",sql);
		}
		if (endDate != null && !"".equals(endDate)) {
			String sql = userMap.get("sqlMap") + " and a.REPAIR_DATE < " +  "TO_DATE ('"+endDate +"','yyyy-mm-dd')+1";
			userMap.put("sqlMap",sql);
		}
		emRepairList.setSqlMap(userMap);
		Page<EmRepairList> page = emRepairListService.findRepairPage(new Page<EmRepairList>(pageNo, pageSize), emRepairList);
		for(EmRepairList info:page.getList()) {
			CtlCodeDet ctlCodeDet = new CtlCodeDet();
			ctlCodeDet.setCodeCode(info.getRepairType());
			ctlCodeDet.setCodeType("WxCategory");
			CtlCodeDet codeDet = ctlCodeDetService.getCodeDet(ctlCodeDet);
			if(codeDet != null) {
				info.setRepairType(codeDet.getCodeName());
			}
		}
		return ResponseUtil.successResponse(page);
	}
	/**
	 * 
	 * getById:(根据id获取维修事件).
	 * @param id
	 * @return :EmRepairList 
	 * @since JDK 1.8
	 * @author 王天尧
	 */
	@ApiOperation(value = "根据id获取维修事件", notes = "根据id获取维修事件")
	@RequestMapping(value = "/getByCode",method=RequestMethod.POST)
	public EmRepairListVo getById(
			@RequestHeader(value="token")String token,
			@RequestBody EmRepairListVo emRepairList,
			HttpServletRequest request,
			HttpServletResponse response) {
		EmRepairListVo emRepairListVo = emRepairListService.getByCode(emRepairList);
		if (emRepairListVo != null) {
			CtlCodeDet ctlCodeDet = new CtlCodeDet();
			ctlCodeDet.setCodeCode(emRepairListVo.getRepairType());
			ctlCodeDet.setCodeType("WxCategory");
			CtlCodeDet codeDet = ctlCodeDetService.getCodeDet(ctlCodeDet);
			if(codeDet != null) {
				emRepairListVo.setRepairType(codeDet.getCodeName());
			}
			CtlAttachfile ctlAttachfile = new CtlAttachfile();
			ctlAttachfile.setMasterFileId(emRepairListVo.getId());
			List<CtlAttachfile> files = ctlAttachfileService.findList(ctlAttachfile);
			if (files != null && files.size() > 0) {
				emRepairListVo.setFiles(files);
			}
		}
		return emRepairListVo;
	}
	 /**
	  * 
	  * getEndList:(获取已完成或是已终止的事件).
	  * @param model
	  * @param redirectAttributes
	  * @return :List<EmRepairList> 
	  * @since JDK 1.8
	  * @author 王天尧
	  */
	@ApiOperation(value = "获取已完成或是已终止的事件", notes = "获取已完成或是已终止的事件")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "emCode", value = "事件编号", required = false,paramType = "query", dataType = "String")
	})
	@RequestMapping(value = "/getEndList",method=RequestMethod.POST)
	public CommonResponse getEndList(
			String emCode,
			String repairType,
			String repairPerson,
			String startDate,
			String endDate,
			@RequestHeader(value="token")String token,
			@RequestParam(value="pageNo",required = true ,defaultValue = "0" )Integer pageNo,
			@RequestParam(value="pageSize",required = true ,defaultValue = "10" )Integer pageSize,
			Model model, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) throws IOException {
		EmRepairList emRepairList = new EmRepairList();
		emRepairList.setEmCode(emCode);
		emRepairList.setRepairType(repairType);
		//emRepairList.setRepairPerson(repairPerson);
		Map<String,String> sqlMap = new HashMap<String,String>();
		String sql="a.states IN ('3','4')";
		if (startDate != null && !"".equals(startDate)) {
			sql += " and a.REPAIR_DATE >= " +  "TO_DATE ('"+startDate +"','yyyy-mm-dd')";
		}
		if (endDate != null && !"".equals(endDate)) {
			sql += " and a.REPAIR_DATE < " +  "TO_DATE ('"+endDate +"','yyyy-mm-dd')+1";
		}
		sqlMap.put("sqlMap", sql);
		emRepairList.setSqlMap(sqlMap);
		Page<EmRepairList> page = emRepairListService.findPage(new Page<EmRepairList>(pageNo, pageSize), emRepairList);
		for(EmRepairList info:page.getList()) {
			CtlCodeDet ctlCodeDet = new CtlCodeDet();
			ctlCodeDet.setCodeCode(info.getRepairType());
			ctlCodeDet.setCodeType("WxCategory");
			CtlCodeDet codeDet = ctlCodeDetService.getCodeDet(ctlCodeDet);
			if(codeDet != null) {
				info.setRepairType(codeDet.getCodeName());
			}
		}
		return ResponseUtil.successResponse(page);
	}
	
	@ApiOperation(value = "查询事件列表", notes = "查询事件列表")
	@RequestMapping(value = "/getRepairList",method=RequestMethod.POST)
	public CommonResponse getRepairList(
			@RequestBody EmRepairList emRepairList,
			@RequestHeader(value="token")String token,
			String startDate,
			String endDate,
			HttpServletRequest request, 
			HttpServletResponse response
			) throws JsonProcessingException {
		Map<String,String> sqlMap = new HashMap<String,String>();
		String sql="1=1";
		if (startDate != null && !"".equals(startDate)) {
			startDate = startDate + " 00:00:00";
			sql += " and a.REPAIR_DATE >= " +  "TO_DATE ('"+startDate +"','yyyy-mm-dd hh24:mi:ss')";
		}
		if (endDate != null && !"".equals(endDate)) {
			endDate = endDate + " 23:59:59";
			sql += " and a.REPAIR_DATE <= " +  "TO_DATE ('"+endDate +"','yyyy-mm-dd hh24:mi:ss')";
		}
		sqlMap.put("sqlMap", sql);
		emRepairList.setSqlMap(sqlMap);
		List<EmRepairList> list = emRepairListService.findList(emRepairList);
		return ResponseUtil.successResponse(list);
	}
}