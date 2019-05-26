package com.sgai.property.em.web;

import java.io.File;
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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sgai.common.persistence.Page;
import com.sgai.common.utils.StringUtils;
import com.sgai.common.web.BaseController;
import com.sgai.property.ctl.entity.CtlAttachfile;
import com.sgai.property.ctl.service.CtlAttachfileService;
import com.sgai.property.ctl.service.CtlComRuleService;
import com.sgai.property.ctl.service.DeleteRulesUtils;
import com.sgai.property.em.entity.EmEmergencyRecord;
import com.sgai.property.em.service.EmEmergencyRecordService;
import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.modules.login.jwt.util.CommonResponse;
import com.sgai.modules.login.jwt.util.ResponseUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 应急事件列表维护Controller
 * @author guanze
 * @version 2017-12-05
 */
@RestController
@RequestMapping(value = "${adminPath}/emEmergencyRecord")
@Api(description = "应急事件接口")
public class EmEmergencyRecordController extends BaseController {

	@Autowired
	private EmEmergencyRecordService emEmergencyRecordService;
//	@Autowired
//	private WfUserRightService wfUserRightService;
	@Autowired
	private DeleteRulesUtils deleteRulesUtils;
	@Autowired
	private CtlAttachfileService ctlAttachfileService;
	@Autowired
	private CtlComRuleService ctlComRuleService;
	
	SimpleDateFormat formatParam = new SimpleDateFormat("yyyyMMdd");
	
	@ApiOperation(value = "应急事件列表", notes = "应急事件列表")
	@RequestMapping(value = {"list", ""},method=RequestMethod.GET)
	public String list(EmEmergencyRecord emEmergencyRecord,
			@RequestHeader(value="token")String token,
			@RequestParam(value="pageNo",required = true ,defaultValue = "0" )Integer pageNo,
			@RequestParam(value="pageSize",required = true ,defaultValue = "10" )Integer pageSize,
			HttpServletRequest request, HttpServletResponse response, Model model) {
		if(emEmergencyRecord==null){
			emEmergencyRecord=new EmEmergencyRecord();
		}
		Page<EmEmergencyRecord> page = emEmergencyRecordService.findPage(new Page<EmEmergencyRecord>(pageNo, pageSize), emEmergencyRecord); 
		model.addAttribute("page", page);
		return "em/ememergencyrecord/emEmergencyRecordList";
	}

	@ApiOperation(value = "应急事件form页面", notes = "应急事件form页面")
	@RequestMapping(value = "form",method=RequestMethod.GET)
	public String form(EmEmergencyRecord emEmergencyRecord, @RequestHeader(value="token")String token, Model model) {
		model.addAttribute("emEmergencyRecord", emEmergencyRecord);
		return "em/ememergencyrecord/emEmergencyRecordForm";
	}

	@ApiOperation(value = "保存应急事件", notes = "保存应急事件")
	@RequestMapping(value = "save",method=RequestMethod.POST)
	public Map<String,String> save(LoginUser user,String repairPhoto, @RequestHeader(value="token")String token,EmEmergencyRecord emEmergencyRecord) {
		Map<String,String> data = new HashMap<String,String>();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		emEmergencyRecord.setEnabledFlag("Y");
		try {
			if (StringUtils.isBlank(emEmergencyRecord.getId())) {
				String emCode = ctlComRuleService.getNext("EMCODE-YJ");
				emEmergencyRecord.setEmCode(emCode);
				emEmergencyRecord.setEmType("YJ");
				emEmergencyRecord.setReportDatetime(format.format(new Date()));
				emEmergencyRecordService.saveEmergencyRecord(emEmergencyRecord,user);
				String path="";//图片服务器地址
				if (repairPhoto!=null && !"".equals(repairPhoto)) {
					String[] images = repairPhoto.split("\\|");
					for(String str:images) {
						CtlAttachfile p = new CtlAttachfile();
				        p.setMasterFileType("应急事件");//模块名称
				        p.setKeyDesc("应急图片");//文件描述
				        p.setMasterFileId(emEmergencyRecord.getId());//主表id
				        p.setUploadSession(token);//用户token
				        String filePath = str.substring(0, 31);
				        String fileName = str.substring(32);
				        p.setFileName(fileName);//文件名称
				        p.setFileLoc(filePath);//文件存储路径
				        p.setUploadTime(format.format(new Date()));//上传时间
				        p.setContentType("multipart/form-data; charset=utf-8");//文件MIME类型
				        File file = new File(path + str);
				        p.setFileSize(String.valueOf(file.length()));//文件大小,单位byte
				        ctlAttachfileService.saveAttachfile(p);
					}
				}
				data.put("message", "success");
			} else {
				//如果是更新数据，直接执行save方法
				emEmergencyRecordService.save(emEmergencyRecord);
				data.put("message", "success");
			}
		} catch (Exception e) {
			data.put("message", "failed");
			e.printStackTrace();
		}
		
		return data;
	}
	
	@ApiOperation(value = "批量删除应急事件", notes = "批量删除应急事件")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "ids", value = "应急事件id集合", required = false,paramType = "query", dataType = "String")
	})
	@RequestMapping(value = "delete",method=RequestMethod.POST)
	public Map<String,String> delete(@RequestHeader(value="token")String token,String ids) {
		String[] idArr=ids.split(",");
		List<String> idList = new ArrayList<String>();
		List<EmEmergencyRecord> emList = new ArrayList<EmEmergencyRecord>();
		for(String id:idArr){
			if(id!=null&&!id.equals("")){
				idList.add(id);
				EmEmergencyRecord emEmergencyRecord = emEmergencyRecordService.get(id);
				emList.add(emEmergencyRecord);
			}
		}
		// 检查是否满足删除条件
		Map<String, String> data = deleteRulesUtils.checkBeforeDelete(EmEmergencyRecord.class, idList);
		if ("true".equals(data.get("value"))) {
			List<EmEmergencyRecord> finalList = emEmergencyRecordService.batchDelete(emList);
			if (finalList.size() > 0) {
				data.put("result", "删除成功");
			} else {
				data.put("result", "删除失败");
			}
		}
		return data;
	}
	
	@ApiOperation(value = "应急事件处理分页", notes = "应急事件处理分页")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "emCode", value = "事件编号", required = false,paramType = "query", dataType = "String")
	})
	@RequestMapping(value = "/getListEmEmergencyRecord",method=RequestMethod.POST)
	public CommonResponse getListEmEmergencyRecord(
			String emCode,
			LoginUser userInfo,
			@RequestHeader(value="token")String token,
			@RequestParam(value="pageNo",required = true ,defaultValue = "0" )Integer pageNo,
			@RequestParam(value="pageSize",required = true ,defaultValue = "10" )Integer pageSize,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		EmEmergencyRecord emEmergencyRecord = new EmEmergencyRecord();
		emEmergencyRecord.setEmCode(emCode);
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("flowCode", "TS");
//		map.put("userCode", userInfo.getUserId());
//		Map<String, String> userMap = wfUserRightService.getListByUser(map);
//		emEmergencyRecord.setSqlMap(userMap);
		Page<EmEmergencyRecord> page = emEmergencyRecordService.findMainPage(new Page<EmEmergencyRecord>(pageNo, pageSize), emEmergencyRecord);
		for(EmEmergencyRecord info:page.getList()) {
			if ("0".equals(info.getStates())) {
				info.setStates("待核实");
			}else if ("1".equals(info.getStates())) {
				info.setStates("待处理");
			}else if ("2".equals(info.getStates())) {
				info.setStates("已完成");
			}else if ("3".equals(info.getStates())) {
				info.setStates("已终止");
			}
		}
		return ResponseUtil.successResponse(page);
	}
	
	@ApiOperation(value = "应急事件查询分页", notes = "应急事件查询分页")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "emCode", value = "事件编号", required = false,paramType = "query", dataType = "String")
	})
	@RequestMapping(value = "/getListEmEmergencyRecordSkan",method=RequestMethod.POST)
	public CommonResponse getListEmEmergencyRecordSkan(
			String emCode,
			@RequestHeader(value="token")String token,
			@RequestParam(value="pageNo",required = true ,defaultValue = "0" )Integer pageNo,
			@RequestParam(value="pageSize",required = true ,defaultValue = "10" )Integer pageSize,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		EmEmergencyRecord emEmergencyRecord = new EmEmergencyRecord();
		emEmergencyRecord.setEmCode(emCode);
		Page<EmEmergencyRecord> page = emEmergencyRecordService.findSkanPage(new Page<EmEmergencyRecord>(pageNo, pageSize), emEmergencyRecord);
		for(EmEmergencyRecord info:page.getList()) {
			if ("0".equals(info.getStates())) {
				info.setStates("待核实");
			}else if ("1".equals(info.getStates())) {
				info.setStates("待处理");
			}else if ("2".equals(info.getStates())) {
				info.setStates("已完成");
			}else if ("3".equals(info.getStates())) {
				info.setStates("已终止");
			}
		}
		return ResponseUtil.successResponse(page);
	}
	
	@ApiOperation(value = "根据id查询应急事件", notes = "根据id查询应急事件")
	@RequestMapping(value = "/findByIdEmEmergencyRecord",method=RequestMethod.POST)
	public EmEmergencyRecord findByIdEmEmergencyRecord(EmEmergencyRecord emEmergencyRecord,@RequestHeader(value="token")String token,HttpServletRequest request, HttpServletResponse response) {
		return emEmergencyRecordService.get(emEmergencyRecord.getId());
	}
	
	@ApiOperation(value = "应急事件下一个编号", notes = "应急事件下一个编号")
	@RequestMapping(value = "/findNextCodeEmEmergencyRecord",method=RequestMethod.POST)
	public EmEmergencyRecord findNextCodeEmEmergencyRecord(@RequestHeader(value="token")String token,HttpServletRequest request, HttpServletResponse response) {
		return emEmergencyRecordService.findNextCodeEmEmergencyRecord();
	}
	
	@ApiOperation(value = "查询应急事件", notes = "查询应急事件")
	@RequestMapping(value = "/getEmEmergencyRecord",method=RequestMethod.POST)
	public EmEmergencyRecord getEmEmergencyRecord(@RequestHeader(value="token")String token,EmEmergencyRecord emEmergencyRecord,HttpServletRequest request, HttpServletResponse response) {
		return emEmergencyRecordService.getEmEmergencyRecord(emEmergencyRecord);
	}
}