package com.sgai.property.wf.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.sgai.common.persistence.Page;
import com.sgai.common.web.BaseController;
import com.sgai.property.ctl.entity.CtlUser;
import com.sgai.property.ctl.service.CtlUserService;
import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.modules.login.jwt.util.CommonResponse;
import com.sgai.modules.login.jwt.util.ResponseUtil;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.wf.entity.WfInstanceRecord;
import com.sgai.property.wf.service.WfInstanceRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 
    * ClassName: WfInstanceRecordController  
    * com.sgai.property.commonService.vo;(事件流程实例维护)
    * @author yangyz  
    * Date 2017年12月5日  
    * Company 首自信--智慧城市创新中心
 */
@RestController
@RequestMapping(value = "${adminPath}/wf/wfinstancerecord/wfInstanceRecord")
@Api(description = "事件流程实例接口")
public class WfInstanceRecordController extends BaseController {

	@Autowired
	private WfInstanceRecordService wfInstanceRecordService;
	@Autowired
	private CtlUserService ctlUserService;
	
	/**
	 * 
	 * getListInstanceRecord:(查询事件流程实例列表).
	 * @param wfInstanceRecord
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException :Page<WfInstanceRecord> 
	 * @since JDK 1.8
	 * @author yangyz
	 */
	@ApiOperation(value = "获得事件流程实例分页列表信息", httpMethod = "POST", notes = "获得事件流程实例分页列表信息")
	@RequestMapping(value = "/getListInstanceRecord",method=RequestMethod.POST)
	public CommonResponse getListInstanceRecord(
			LoginUser user,

			@RequestParam(value = "pageNo", required = true,defaultValue="0") Integer pageNo ,
			@RequestParam(value = "pageSize", required = true,defaultValue="10") Integer pageSize,
			HttpServletRequest request, 
			HttpServletResponse response
			) throws IOException {
		WfInstanceRecord wfInstanceRecord = new WfInstanceRecord();
		Page<WfInstanceRecord> page = wfInstanceRecordService.findPage(new Page<WfInstanceRecord>(pageNo, pageSize), wfInstanceRecord);
		return ResponseUtil.successResponse(page);
	}
	
	/**
	 * 
	 * getInstanceRecord:(查询一条事件流程实例记录).
	 * @param request
	 * @param id
	 * @param response
	 * @return
	 * @throws IOException :WfInstanceRecord 
	 * @since JDK 1.8
	 * @author yangyz
	 */
	@ApiOperation(value = "获得事件流程实例信息", httpMethod = "POST", notes = "获得事件流程实例信息")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "主键", required = false,paramType = "query", dataType = "String")
	})
	@RequestMapping(value = "/getInstanceRecord",method=RequestMethod.POST)
	public CommonResponse getInstanceRecord(
			String id, 

			HttpServletRequest request, 
			HttpServletResponse response
			) throws IOException {
		WfInstanceRecord wfInstanceRecord = wfInstanceRecordService.get(id);
		return ResponseUtil.successResponse(wfInstanceRecord);
	}

	/**
	 * 
	 * save:(新增或者修改保存事件实例记录).
	 * @param wfInstanceRecord
	 * @param model
	 * @param redirectAttributes
	 * @return :Map<String,Object> 
	 * @since JDK 1.8
	 * @author yangyz
	 * @throws JsonProcessingException 
	 */
	@ApiOperation(value = "保存事件流程实例信息", httpMethod = "POST", notes = "保存事件流程实例信息")
	@RequestMapping(value = "save",method=RequestMethod.POST)
	public CommonResponse save(
			WfInstanceRecord wfInstanceRecord,

			RedirectAttributes redirectAttributes
			) throws JsonProcessingException {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			wfInstanceRecordService.save(wfInstanceRecord);
			map.put("msg", "success");
		} catch (Exception e) {

			e.printStackTrace();
			map.put("msg", "fail");
		}
		return ResponseUtil.successResponse(map);
	}
	
	/**
	 * 
	 * delete:(批量删除事件流程实例).
	 * @param ids
	 * @param redirectAttributes
	 * @return :Map<String,Object> 
	 * @since JDK 1.8
	 * @author yangyz
	 * @throws JsonProcessingException 
	 */
	@ApiOperation(value = "删除事件流程实例信息", httpMethod = "POST", notes = "删除事件流程实例信息")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "ids", value = "多个主键，逗号分隔", required = false,paramType = "query", dataType = "String")
	})
	@RequestMapping(value = "delete",method=RequestMethod.POST)
	public CommonResponse delete(
			String ids, 

			RedirectAttributes redirectAttributes
			) throws JsonProcessingException {
		Map<String, Object> map = new HashMap<String, Object>(); 
		try {
			map = wfInstanceRecordService.deleteInstanceRecord(ids);
		} catch (Exception e) {

			e.printStackTrace();
			map.put("msg", "出错了！");
			map.put("result", "fail");
		}
		return ResponseUtil.successResponse(map);
	}
	/**
	 * 
	 * getListProcess:(查询某事件处理流程).
	 * @param wfInstanceRecord
	 * @return :List<WfInstanceRecord> 
	 * @since JDK 1.8
	 * @author lenovo
	 * @throws JsonProcessingException 
	 */
	@ApiOperation(value = "查询事件流程实例列表信息", httpMethod = "POST", notes = "查询事件流程实例列表信息")
	@RequestMapping(value = "getListProcess",method=RequestMethod.POST)
	public CommonResponse getListProcess(
			@RequestBody WfInstanceRecord wfInstanceRecord,

			HttpServletRequest request,
			HttpServletResponse response
			) throws JsonProcessingException{
		List<WfInstanceRecord> list = wfInstanceRecordService.findWfInstanceRecordList(wfInstanceRecord);
		return ResponseUtil.successResponse(list);
	}
	
	/**
	 * 
	 * findNextNodeUserList:(查询节点处理user列表通用接口).
	 * @param flowCode
	 * @param stepPos
	 * @return :List<CtlUser> 
	 * @since JDK 1.8
	 * @author yangyz
	 * @throws JsonProcessingException 
	 */
	@ApiOperation(value = "查询节点处理user列表通用接口", httpMethod = "POST", notes = "查询节点处理user列表通用接口")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "flowCode", value = "事件类型", required = false,paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "stepPos", value = "级别", required = false,paramType = "query", dataType = "String")
	})
	@RequestMapping(value = "/findNextNodeUserList",method=RequestMethod.POST)
	public CommonResponse findNextNodeUserList(
			String flowCode, 
			String stepPos,
			String deptCode,
			@RequestHeader("token") String token
			) throws JsonProcessingException{
		Map<String, String> map = new HashMap<String, String>();
		LoginUser user = UserServletContext.getUserInfo();
		map.put("flowCode", flowCode);
		map.put("stepPos", stepPos);
		map.put("deptCode", deptCode);
		map.put("comCode", user.getComCode());
		map.put("moduCode", user.getModuCode());
		List<CtlUser> list = ctlUserService.findNextNodeUserList(map);
		return ResponseUtil.successResponse(list);
	}
	
	/**
	 * 
	 * getInstanceRecordByCode:(查询实例).
	 * @param request
	 * @param id
	 * @param response
	 * @return
	 * @throws IOException :WfInstanceRecord 
	 * @since JDK 1.8
	 * @author yangyz
	 */
	@ApiOperation(value = "查询实例", httpMethod = "POST", notes = "查询实例")
	@RequestMapping(value = "/getInstanceRecordByCode",method=RequestMethod.POST)
	public CommonResponse getInstanceRecordByCode(
			WfInstanceRecord wfInstanceRecord,

			HttpServletRequest request, 
			HttpServletResponse response
			) throws IOException {
		WfInstanceRecord info = wfInstanceRecordService.getInstanceRecord(wfInstanceRecord);
		return ResponseUtil.successResponse(info);
	}
	
	/**
	 * 
	 * saveFlow:(保存应急事件流程实例).
	 * @param emCode
	 * @param stepCode
	 * @param type
	 * @param flowCode
	 * @return
	 * @throws Exception :Map<String,String> 
	 * @since JDK 1.8
	 * @author yangyz
	 */
	@RequestMapping(value = "saveFlow")
	public synchronized Map<String, String> saveFlow(String emCode, String stepCode, String type, String flowCode) throws Exception {
		LoginUser user = UserServletContext.getUserInfo();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("emCode", emCode);
		params.put("stepCode", stepCode);
		params.put("type", type);
		params.put("flowCode", flowCode);
		return wfInstanceRecordService.changeStatus(params, user);
	}
	
	/**
	 * 
	 * findFlow:(查找应急事件流程实例).
	 * @param emCode
	 * @return
	 * @since JDK 1.8
	 * @author yangyz
	 */
	@RequestMapping(value = "findFlow")
	public synchronized List<WfInstanceRecord> findFlow(String emCode,String comCode) {
		WfInstanceRecord wfInstanceRecord = new WfInstanceRecord();
		wfInstanceRecord.setEmCode(emCode);
		wfInstanceRecord.setComCode(comCode);
		List<WfInstanceRecord> list = wfInstanceRecordService.findWfInstanceRecordList(wfInstanceRecord);
		return list;
	}
}