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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sgai.common.persistence.Page;
import com.sgai.common.utils.StringUtils;
import com.sgai.common.web.BaseController;
import com.sgai.property.ctl.entity.CtlAttachfile;
import com.sgai.property.ctl.entity.CtlCodeDet;
import com.sgai.property.ctl.service.CtlAttachfileService;
import com.sgai.property.ctl.service.CtlCodeDetService;
import com.sgai.property.ctl.service.CtlComRuleService;
import com.sgai.property.ctl.service.DeleteRulesUtils;
import com.sgai.property.em.dto.EmSecuRecordVo;
import com.sgai.property.em.entity.EmSecuRecord;
import com.sgai.property.em.service.EmSecuRecordService;
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
 * 安保事件维护Controller
 * @author guanze
 * @version 2017-12-05
 */
@Controller
@RequestMapping(value = "${adminPath}/emSecuRecord")
@Api(description = "安保事件接口")
public class EmSecuRecordController extends BaseController {

	@Autowired
	private EmSecuRecordService emSecuRecordService;
	
	@Autowired
	private DeleteRulesUtils deleteRulesUtils;
	@Autowired
	private CtlAttachfileService ctlAttachfileService;
	@Autowired
	private CtlComRuleService ctlComRuleService;
	
	SimpleDateFormat formatParam = new SimpleDateFormat("yyyyMMdd");
	@Autowired
	private WfUserRightService wfUserRightService;
	@Autowired
	private CtlCodeDetService ctlCodeDetService;
	
	@ApiOperation(value = "安保事件列表", notes = "安保事件列表")
	@RequestMapping(value = {"list", ""},method=RequestMethod.GET )
	public String list(EmSecuRecord emSecuRecord,
			@RequestHeader(value="token")String token,
			@RequestParam(value="pageNo",required = true ,defaultValue = "0" )Integer pageNo,
			@RequestParam(value="pageSize",required = true ,defaultValue = "10" )Integer pageSize,
			HttpServletRequest request, HttpServletResponse response, Model model) {
		if(emSecuRecord==null){
			emSecuRecord=new EmSecuRecord();
		}
		Page<EmSecuRecord> page = emSecuRecordService.findPage(new Page<EmSecuRecord>(pageNo, pageSize), emSecuRecord); 
		model.addAttribute("page", page);
		return "em/emsecurecord/emSecuRecordList";
	}

	@ApiOperation(value = "安保事件form", notes = "安保事件form")
	@RequestMapping(value = "form",method=RequestMethod.GET)
	public String form(EmSecuRecord emSecuRecord, Model model) {
		model.addAttribute("emSecuRecord", emSecuRecord);
		return "em/emsecurecord/emSecuRecordForm";
	}
	
	@ApiOperation(value = "安保事件创建页面", notes = "安保事件创建页面")
	@RequestMapping(value = "emSecuRecordForm",method=RequestMethod.GET)
	public String emSecuRecordForm(EmSecuRecord emSecuRecord, @RequestHeader(value="token")String token,String instanceId, String emCode, String emType, Model model) {
		model.addAttribute("emSecuRecord", emSecuRecord);
		return "/em/emSecuRecordCreateForm";
	}

	@ApiOperation(value = "安保事件保存", notes = "安保事件保存")
	@RequestMapping(value = "save",method=RequestMethod.POST )
	public @ResponseBody Map<String,String> save(
			@RequestHeader(value="token")String token,
			@RequestBody EmSecuRecord emSecuRecord) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Map<String,String> data = new HashMap<String,String>();
		emSecuRecord.setEnabledFlag("Y");
		//emSecuRecord.setComCode(user.getComCode());
		LoginUser user = UserServletContext.getUserInfo();
		try {
			if (StringUtils.isBlank(emSecuRecord.getId())) {
				String emCode = ctlComRuleService.getNext("EMCODE-AB");
				emSecuRecord.setEmCode(emCode);
				emSecuRecord.setReportDatetime(new Date());
				emSecuRecord.setEmType("AB");
				emSecuRecord.setStates("0");
				emSecuRecordService.saveSecuRecord(emSecuRecord,user);
				//String path="";//图片服务器地址
				if (emSecuRecord.getRepairPhoto()!=null && !"".equals(emSecuRecord.getRepairPhoto())) {
					String[] images = emSecuRecord.getRepairPhoto().split("\\|");
					for(String str:images) {
						CtlAttachfile p = new CtlAttachfile();
				        p.setMasterFileType("安保事件");//模块名称
				        p.setKeyDesc("安保图片");//文件描述
				        p.setMasterFileId(emSecuRecord.getId());//主表id
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
				emSecuRecordService.save(emSecuRecord);
				data.put("message", "success");
			}
		} catch (Exception e) {
			data.put("message", "failed");
			e.printStackTrace();
		}
		return data;
	}
	
	@ApiOperation(value = "安保事件删除", notes = "安保事件删除")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "ids", value = "id集合", required = false,paramType = "query", dataType = "String")
	})
	@RequestMapping(value = "delete",method=RequestMethod.POST )
	public @ResponseBody Map<String,String> delete(@RequestHeader(value="token")String token,String ids) {
		String[] idArr=ids.split(",");
		List<String> idList = new ArrayList<String>();
		List<EmSecuRecord> emList = new ArrayList<EmSecuRecord>();
		for(String id:idArr){
			if(id!=null&&!id.equals("")){
				idList.add(id);
				EmSecuRecord emSecuRecord = emSecuRecordService.get(id);
				emList.add(emSecuRecord);
			}
		}
		// 检查是否满足删除条件
		Map<String, String> data = deleteRulesUtils.checkBeforeDelete(EmSecuRecord.class, idList);
		if ("true".equals(data.get("value"))) {
			List<EmSecuRecord> finalList = emSecuRecordService.batchDelete(emList);
			if (finalList.size() > 0) {
				data.put("result", "删除成功");
			} else {
				data.put("result", "删除失败");
			}
		}
		return data;
	}

//	/**
//	 * 
//	 * updateSecuRecord:(更新事件状态).
//	 * @param emSecuRecord
//	 * @return :Map<String,String> 
//	 * @since JDK 1.8
//	 * @author guanze
//	 */
//	@RequestMapping(value = "updateSecuRecord")
//	public Map<String,String> updateSecuRecord(EmSecuRecord emSecuRecord) {
//		Map<String,String> data = new HashMap<String,String>();
//		
//		try {
//			LoginUser userInfo = UserServletContext.getUserInfo();
//			EmSecuRecord info = emSecuRecordService.get(emSecuRecord.getId());
//			info.setStates(emSecuRecord.getStates());
//			//info.setProcPerson(userInfo.getUserName());
//			emSecuRecordService.save(info);
//			data.put("result", "已终止");
//		} catch (Exception e) {
//
//			e.printStackTrace();
//			data.put("result", "终止失败");
//		}
//		return data;
//	}
//	
//	/**
//	 *  n
//	 * findNextNodeUserList:(查询user列表).
//	 * @return :List<CtlUser> 
//	 * @since JDK 1.8
//	 * @author guanze
//	 */
//	@RequestMapping(value = "/findNextNodeUserList")
//	public List<CtlUser> findNextNodeUserList(){
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("flowCode", "AB");
//		map.put("stepPos", "B");
//		List<CtlUser> list = ctlUserService.findNextNodeUserList(map);
//		return list;
//	}
	
	@ApiOperation(value = "根据用户过滤流程实例", notes = "根据用户过滤流程实例")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "emCode", value = "事件编号", required = false,paramType = "query", dataType = "String"),
	})
	@RequestMapping(value = "/getListEmSecuRecord",method=RequestMethod.POST)
	public @ResponseBody CommonResponse getListEmSecuRecord(
			String emCode,
			String reportPerson,
			String startDate,
			String endDate,
			LoginUser userInfo,
			@RequestHeader(value="token")String token,
			@RequestParam(value="pageNo",required = true ,defaultValue = "0" )Integer pageNo,
			@RequestParam(value="pageSize",required = true ,defaultValue = "10" )Integer pageSize,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		EmSecuRecord emSecuRecord =new EmSecuRecord();
		emSecuRecord.setEmCode(emCode);
		//emSecuRecord.setReportPerson(reportPerson);
		//emSecuRecord.setComCode(userInfo.getComCode());
		Map<String, String> map = new HashMap<String, String>();
		map.put("flowCode", "AB");
		map.put("userCode", userInfo.getUserId());
		map.put("comCode", userInfo.getComCode());
		Map<String, String> userMap = wfUserRightService.getListsByUser(map);
		if (startDate != null && !"".equals(startDate)) {
			String sql = userMap.get("sqlMap") + " and a.REPORT_DATETIME >= " +  "TO_DATE ('"+startDate +"','yyyy-mm-dd')";
			userMap.put("sqlMap",sql);
		}
		if (endDate != null && !"".equals(endDate)) {
			String sql = userMap.get("sqlMap") + " and a.REPORT_DATETIME < " +  "TO_DATE ('"+endDate +"','yyyy-mm-dd')+1";
			userMap.put("sqlMap",sql);
		}
		emSecuRecord.setSqlMap(userMap);
		Page<EmSecuRecord> page = emSecuRecordService.findSecuRecordPage(new Page<EmSecuRecord>(pageNo, pageSize), emSecuRecord);
		for(EmSecuRecord info:page.getList()) {
			CtlCodeDet ctlCodeDet = new CtlCodeDet();
			ctlCodeDet.setCodeCode(info.getEmCategory());
			ctlCodeDet.setCodeType("AbCategory");
			CtlCodeDet codeDet = ctlCodeDetService.getCodeDet(ctlCodeDet);
			if(codeDet != null) {
				info.setEmCategory(codeDet.getCodeName());
			}
		}
		return ResponseUtil.successResponse(page);
	}
	
	@ApiOperation(value = "安保事件查询分页", notes = "安保事件查询分页")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "emCode", value = "事件编号", required = false,paramType = "query", dataType = "String"),
	})
	@RequestMapping(value = "/getListEmSecuRecordSkan",method=RequestMethod.POST )
	public @ResponseBody CommonResponse getListEmSecuRecordSkan(
			String emCode,
			String reportPerson,
			String startDate,
			String endDate,
			@RequestHeader(value="token")String token,
			@RequestParam(value="pageNo",required = true ,defaultValue = "0" )Integer pageNo,
			@RequestParam(value="pageSize",required = true ,defaultValue = "10" )Integer pageSize,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		EmSecuRecord emSecuRecord =new EmSecuRecord();
		emSecuRecord.setEmCode(emCode);
		emSecuRecord.setReportPerson(reportPerson);
		Map<String,String> sqlMap = new HashMap<String,String>();
		String sql="a.states IN ('3','4')";
		if (startDate != null && !"".equals(startDate)) {
			sql += " and a.REPORT_DATETIME >= " +  "TO_DATE ('"+startDate +"','yyyy-mm-dd')";
		}
		if (endDate != null && !"".equals(endDate)) {
			sql += " and a.REPORT_DATETIME < " +  "TO_DATE ('"+endDate +"','yyyy-mm-dd')+1";
		}
		sqlMap.put("sqlMap", sql);
		emSecuRecord.setSqlMap(sqlMap);
		Page<EmSecuRecord> page = emSecuRecordService.findPage(new Page<EmSecuRecord>(pageNo, pageSize), emSecuRecord);
		for(EmSecuRecord info:page.getList()) {
			CtlCodeDet ctlCodeDet = new CtlCodeDet();
			ctlCodeDet.setCodeCode(info.getEmCategory());
			ctlCodeDet.setCodeType("AbCategory");
			CtlCodeDet codeDet = ctlCodeDetService.getCodeDet(ctlCodeDet);
			if(codeDet != null) {
				info.setEmCategory(codeDet.getCodeName());
			}
		}
		return ResponseUtil.successResponse(page);
	}
	
	@ApiOperation(value = "根据id查询安保事件", notes = "根据id查询安保事件")
	@RequestMapping(value = "/findByIdEmSecuRecord",method=RequestMethod.POST )
	public @ResponseBody EmSecuRecord findByIdEmSecuRecord(EmSecuRecord emSecuRecord,@RequestHeader(value="token")String token,HttpServletRequest request, HttpServletResponse response) {
		return emSecuRecordService.get(emSecuRecord.getId());
	}
	
	@ApiOperation(value = "安保事件", notes = "安保事件")
	@RequestMapping(value = "/findNextCodeEmSecuRecord",method=RequestMethod.POST )
	public @ResponseBody EmSecuRecord	findNextCodeEmSecuRecord(HttpServletRequest request,@RequestHeader(value="token")String token, HttpServletResponse response) {
		return emSecuRecordService.findNextCodeEmSecuRecord();
	}
	
	/**
	 * 
	 * getEmSecuRecord:(查询安保事件).
	 * @param emSecuRecord
	 * @param request
	 * @param response
	 * @return
	 * @since JDK 1.8
	 * @author guanze
	 */
	@ApiOperation(value = "查询安保事件", notes = "查询安保事件")
	@RequestMapping(value = "/getEmSecuRecord",method=RequestMethod.POST )
	public @ResponseBody EmSecuRecordVo getEmSecuRecord(
			@RequestBody EmSecuRecordVo emSecuRecord,
			@RequestHeader(value="token")String token,
			HttpServletRequest request, 
			HttpServletResponse response
			) {
		EmSecuRecordVo emSecuRecordVo = emSecuRecordService.getEmSecuRecord(emSecuRecord);
		if (emSecuRecordVo != null) {
			CtlCodeDet ctlCodeDet = new CtlCodeDet();
			ctlCodeDet.setCodeCode(emSecuRecordVo.getEmCategory());
			ctlCodeDet.setCodeType("AbCategory");
			CtlCodeDet codeDet = ctlCodeDetService.getCodeDet(ctlCodeDet);
			if(codeDet != null) {
				emSecuRecordVo.setEmCategory(codeDet.getCodeName());
			}
			CtlAttachfile file = new CtlAttachfile();
			file.setMasterFileId(emSecuRecordVo.getId());
			List<CtlAttachfile> files = ctlAttachfileService.findList(file);
			if (files != null && files.size() > 0) {
				emSecuRecordVo.setFiles(files);
			}
		}
		return emSecuRecordVo;
	}

	
	@ApiOperation(value = "查询安保事件列表", notes = "查询安保事件列表")
	@RequestMapping(value = "/getEmSecuRecordList",method=RequestMethod.POST )
	public @ResponseBody List<EmSecuRecord> getEmSecuRecordList(
			@RequestBody EmSecuRecord emSecuRecord,
			@RequestHeader(value="token")String token,
			String startDate,
			String endDate,
			HttpServletRequest request, 
			HttpServletResponse response
			){
		Map<String,String> sqlMap = new HashMap<String,String>();
		String sql="1=1";
		if (startDate != null && !"".equals(startDate)) {
			startDate = startDate + " 00:00:00";
			sql += " and a.REPORT_DATETIME >= " +  "TO_DATE ('"+startDate +"','yyyy-mm-dd hh24:mi:ss')";
		}
		if (endDate != null && !"".equals(endDate)) {
			endDate = endDate + " 23:59:59";
			sql += " and a.REPORT_DATETIME <= " +  "TO_DATE ('"+endDate +"','yyyy-mm-dd hh24:mi:ss')";
		}
		sqlMap.put("sqlMap", sql);
		emSecuRecord.setSqlMap(sqlMap);
		List<EmSecuRecord> list = emSecuRecordService.findList(emSecuRecord);
		return list;
	}
	
}