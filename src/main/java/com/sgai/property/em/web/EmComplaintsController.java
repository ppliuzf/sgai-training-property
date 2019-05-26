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
import com.sgai.property.em.dto.EmComplaintsVo;
import com.sgai.property.em.entity.EmComplaints;
import com.sgai.property.em.service.EmComplaintsService;
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
 * 投诉事件维护Controller
 * @author guanze
 * @version 2017-12-05
 */
@RestController
@RequestMapping(value = "${adminPath}/emComplaints")
@Api(description = "投诉事件接口")
public class EmComplaintsController extends BaseController {

	@Autowired
	private EmComplaintsService emComplaintsService;
	@Autowired
	private DeleteRulesUtils deleteRulesUtils;
	@Autowired
	private WfUserRightService wfUserRightService;
	@Autowired
	private CtlAttachfileService ctlAttachfileService;
	@Autowired
	private CtlComRuleService ctlComRuleService;
	@Autowired
	private CtlCodeDetService ctlCodeDetService;
	
	SimpleDateFormat formatParam = new SimpleDateFormat("yyyyMMdd");
	
	
	@ApiOperation(value = "投诉事件列表页面", notes = "投诉事件列表页面")
	@RequestMapping(value = {"list", ""},method=RequestMethod.GET)
	public String list(EmComplaints emComplaints, 
			@RequestHeader(value="token")String token,
			@RequestParam(value="pageNo",required = true ,defaultValue = "0" )Integer pageNo,
			@RequestParam(value="pageSize",required = true ,defaultValue = "10" )Integer pageSize,
			HttpServletRequest request, HttpServletResponse response, Model model) {
		if(emComplaints==null){
			emComplaints=new EmComplaints();
		}
		Page<EmComplaints> page = emComplaintsService.findPage(new Page<EmComplaints>(pageNo, pageSize), emComplaints); 
		model.addAttribute("page", page);
		return "em/emcomplaints/emComplaintsList";
	}
	
	@ApiOperation(value = "投诉事件form页面", notes = "投诉事件form页面")
	@RequestMapping(value = "form",method=RequestMethod.GET)
	public String form(EmComplaints emComplaints, @RequestHeader(value="token")String token,Model model) {
		model.addAttribute("emComplaints", emComplaints);
		return "em/emcomplaints/emComplaintsForm";
	}

	@ApiOperation(value = "保存投诉事件", notes = "保存投诉事件")
	@RequestMapping(value = "save",method=RequestMethod.POST)
	public CommonResponse save(
			@RequestBody EmComplaints emComplaints,
			@RequestHeader(value="token")String token,
			HttpServletRequest request
			) throws JsonProcessingException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Map<String,String> data = new HashMap<String,String>();
		LoginUser userInfo = UserServletContext.getUserInfo();
		try {
			if (StringUtils.isBlank(emComplaints.getId())) {
				String complCode = ctlComRuleService.getNext("EMCODE-TS");
				emComplaints.setComplCode(complCode);
				emComplaints.setComplTime(format.format(new Date()));
				emComplaints.setStates("0");
				emComplaints.setComplType("TS");
				emComplaints.setEnabledFlag("Y");
				emComplaintsService.saveComplaint(emComplaints,userInfo);
				//String path = "http://211.94.132.78";//图片服务器地址
				if (emComplaints.getRepairPhoto()!=null && !"".equals(emComplaints.getRepairPhoto())) {
					String[] images = emComplaints.getRepairPhoto().split("\\|");
					for(String str:images) {
						CtlAttachfile p = new CtlAttachfile();
				        p.setMasterFileType("投诉事件");//模块名称
				        p.setKeyDesc("投诉图片");//文件描述
				        p.setMasterFileId(emComplaints.getId());//主表id
				        p.setUploadSession(token);//用户token
				        String filePath = str.substring(0, 31) + userInfo.getUserId() + "/";
				        String fileName = str.substring(filePath.length());
				        p.setFileName(fileName);//文件名称
				        p.setFileLoc(filePath);//文件存储路径
				        p.setUploadTime(format.format(new Date()));//上传时间
				        p.setContentType("multipart/form-data; charset=utf-8");//文件MIME类型
				        //File file = new File(path + File.separator+ str);
				        //p.setFileSize(String.valueOf(file.length()));//文件大小,单位byte
				        ctlAttachfileService.saveAttachfile(p);
					}
				}
				data.put("message", "success");
			} else {
				//如果是更新数据，直接执行save方法
				emComplaintsService.save(emComplaints);
				data.put("message", "success");
			}
		} catch (Exception e) {

			data.put("message", "failed");
			e.printStackTrace();
		}
		return ResponseUtil.successResponse(data);
	}
	
	@ApiOperation(value = "删除投诉事件", notes = "删除投诉事件")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "投诉事件id", required = false,paramType = "query", dataType = "String")
	})
	@RequestMapping(value = "delete",method=RequestMethod.POST)
	public CommonResponse delete(String ids,@RequestHeader(value="token")String token) throws JsonProcessingException {
		String[] idArr=ids.split(",");
		List<String> idList = new ArrayList<String>();
		List<EmComplaints> emList = new ArrayList<EmComplaints>();
		for(String id:idArr){
			if(id!=null&&!id.equals("")){
				idList.add(id);
				EmComplaints emComplaints = emComplaintsService.get(id);
				emList.add(emComplaints);
			}
		}
		// 检查是否满足删除条件
		Map<String, String> data = deleteRulesUtils.checkBeforeDelete(EmComplaints.class, idList);
		if ("true".equals(data.get("value"))) {
			List<EmComplaints> finalList = emComplaintsService.batchDelete(emList);
			if (finalList.size() > 0) {
				data.put("result", "删除成功");
			} else {
				data.put("result", "删除失败");
			}
		}
		return ResponseUtil.successResponse(data);
	}

	@ApiOperation(value = "投诉事件处理分页", notes = "投诉事件处理分页")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "complCode", value = "投诉事件id", required = false,paramType = "query", dataType = "String")
	})
	@RequestMapping(value = "/getListEmComplaints",method=RequestMethod.POST)
	public CommonResponse getListEmComplaints(
			String complCode,
			String complPerson,
			String startDate,
			String endDate,
			LoginUser userInfo,
			@RequestHeader(value="token")String token,
			@RequestParam(value="pageNo",required = true ,defaultValue = "0" )Integer pageNo,
			@RequestParam(value="pageSize",required = true ,defaultValue = "10" )Integer pageSize,
			HttpServletRequest request, 
			HttpServletResponse response) throws IOException {
		EmComplaints emComplaints = new EmComplaints();
		emComplaints.setComplCode(complCode);
		emComplaints.setComplPerson(complPerson);
		//emComplaints.setComCode(userInfo.getComCode());
		Map<String, String> map = new HashMap<String, String>();
		map.put("flowCode", "TS");
		map.put("userCode", userInfo.getUserId());
		map.put("comCode", userInfo.getComCode());
		Map<String, String> userMap = wfUserRightService.getListsByUser(map);
		if (startDate != null && !"".equals(startDate)) {
			String sql = userMap.get("sqlMap") + " and a.COMPL_TIME >= " + "'" + startDate + "'";
			userMap.put("sqlMap",sql);
		}
		if (endDate != null && !"".equals(endDate)) {
			String sql = userMap.get("sqlMap") + " and a.COMPL_TIME <= " + "'" + endDate + " 23:59:59" + "'";
			userMap.put("sqlMap",sql);
		}
		emComplaints.setSqlMap(userMap);
		Page<EmComplaints> page = emComplaintsService.findComplaintsPage(new Page<EmComplaints>(pageNo, pageSize), emComplaints);
		for(EmComplaints info:page.getList()) {
			CtlCodeDet ctlCodeDet = new CtlCodeDet();
			ctlCodeDet.setCodeCode(info.getCompCategory());
			ctlCodeDet.setCodeType("TsCategory");
			CtlCodeDet codeDet = ctlCodeDetService.getCodeDet(ctlCodeDet);
			if(codeDet != null) {
				info.setCompCategory(codeDet.getCodeName());
			}
		}
		return ResponseUtil.successResponse(page);
	}
	
	/**
	 * 
	 * getListEmComplaintsSkan:(查询已完成和已终止事件).
	 * @param emComplaints
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException :Page<EmComplaints> 
	 * @since JDK 1.8
	 * @author yangyz
	 */
	@ApiOperation(value = "投诉事件查询分页", notes = "投诉事件查询分页")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "complCode", value = "投诉事件id", required = false,paramType = "query", dataType = "String")
	})
	@RequestMapping(value = "/getListEmComplaintsSkan",method=RequestMethod.POST)
	public CommonResponse getListEmComplaintsSkan(
			String complCode,
			String complPerson,
			String startDate,
			String endDate,
			@RequestHeader(value="token")String token,
			@RequestParam(value="pageNo",required = true ,defaultValue = "0" )Integer pageNo,
			@RequestParam(value="pageSize",required = true ,defaultValue = "10" )Integer pageSize,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		EmComplaints emComplaints = new EmComplaints();
		if (complCode != null && !"".equals(complCode)) {
			emComplaints.setComplCode(complCode);
		}
		if (complPerson != null && !"".equals(complPerson)) {
			emComplaints.setComplPerson(complPerson);
		}
		String sql = "";
		Map<String, String> userMap = new HashMap<String, String>();
		if (startDate != null && !"".equals(startDate)) {
			sql += " and a.COMPL_TIME >= " + "'" + startDate + "'";
		}
		if (endDate != null && !"".equals(endDate)) {
			sql += " and a.COMPL_TIME <= " + "'" + endDate + " 23:59:59" + "'";
		}
		if (!"".equals(sql)) {
			userMap.put("sqlMap",sql);
		}
		emComplaints.setSqlMap(userMap);
		Page<EmComplaints> page = emComplaintsService.findSkanPage(new Page<EmComplaints>(pageNo, pageSize), emComplaints);
		for(EmComplaints info:page.getList()) {
			CtlCodeDet ctlCodeDet = new CtlCodeDet();
			ctlCodeDet.setCodeCode(info.getCompCategory());
			ctlCodeDet.setCodeType("TsCategory");
			CtlCodeDet codeDet = ctlCodeDetService.getCodeDet(ctlCodeDet);
			if(codeDet != null) {
				info.setCompCategory(codeDet.getCodeName());
			}
		}
		return ResponseUtil.successResponse(page);
	}
	
	@ApiOperation(value = "查询一条投诉事件", notes = "查询一条投诉事件")
	@RequestMapping(value = "/findByIdEmComplaints",method=RequestMethod.POST)
	public EmComplaints findByIdEmComplaints(EmComplaints emComplaints,@RequestHeader(value="token")String token,HttpServletRequest request, HttpServletResponse response) {
		return emComplaintsService.get(emComplaints.getId());
	}
	
	@ApiOperation(value = "查询事件下一个编号", notes = "查询事件下一个编号")
	@RequestMapping(value = "/findNextCodeEmComplaints",method=RequestMethod.POST)
	public CommonResponse findNextCodeEmComplaints(@RequestHeader(value="token")String token,HttpServletRequest request, HttpServletResponse response) throws IOException {
		EmComplaints emComplaints = emComplaintsService.findNextCodeEmComplaints();
		return ResponseUtil.successResponse(emComplaints);
	}
	
	/**
	 * 
	 * getEmComplaints:(查询投诉事件).
	 * @param emComplaints
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException :EmComplaints 
	 * @since JDK 1.8
	 * @author yangyz
	 */
	@ApiOperation(value = "查询投诉事件", notes = "查询投诉事件")
	@RequestMapping(value = "/getEmComplaints",method=RequestMethod.POST)
	public CommonResponse getEmComplaints(
			@RequestBody EmComplaintsVo emComplaintsVo,
			@RequestHeader(value="token")String token,
			HttpServletRequest request, 
			HttpServletResponse response
			) throws IOException {
		EmComplaintsVo info = emComplaintsService.getEmComplaints(emComplaintsVo);
		if (info != null) {
			CtlCodeDet ctlCodeDet = new CtlCodeDet();
			ctlCodeDet.setCodeCode(info.getCompCategory());
			ctlCodeDet.setCodeType("TsCategory");
			CtlCodeDet codeDet = ctlCodeDetService.getCodeDet(ctlCodeDet);
			if(codeDet != null) {
				info.setCompCategory(codeDet.getCodeName());
			}
			CtlAttachfile file = new CtlAttachfile();
			file.setMasterFileId(info.getId());
			List<CtlAttachfile> list = ctlAttachfileService.findList(file);
			if (list != null && list.size() > 0) {
				 info.setFiles(list);
			}
		}
		return ResponseUtil.successResponse(info);
	}
	
	@ApiOperation(value = "查询投诉事件列表", notes = "查询投诉事件列表")
	@RequestMapping(value = "/getEmComplaintList",method=RequestMethod.POST)
	public CommonResponse getEmComplaintList(
			@RequestBody EmComplaints emComplaints,
			@RequestHeader(value="token")String token,
			String startDate,
			String endDate,
			HttpServletRequest request, 
			HttpServletResponse response) throws JsonProcessingException {
		String sql = "1=1";
		Map<String, String> userMap = new HashMap<String, String>();
		if (startDate != null && !"".equals(startDate)) {
			sql += " and a.COMPL_TIME >= " + "'" + startDate + "'";
		}
		if (endDate != null && !"".equals(endDate)) {
			sql += " and a.COMPL_TIME <= " + "'" + endDate + " 23:59:59" + "'";
		}
		if (!"".equals(sql)) {
			userMap.put("sqlMap",sql);
		}
		emComplaints.setSqlMap(userMap);
		List<EmComplaints> list = emComplaintsService.findList(emComplaints);
		return ResponseUtil.successResponse(list);
	}
}