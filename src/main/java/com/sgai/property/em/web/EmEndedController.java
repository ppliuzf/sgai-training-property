package com.sgai.property.em.web;

import java.io.IOException;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sgai.common.persistence.Page;
import com.sgai.common.web.BaseController;
import com.sgai.property.alm.service.AlmRecordListService;
import com.sgai.property.em.entity.EmEnded;
import com.sgai.property.em.service.EmComplaintsService;
import com.sgai.property.em.service.EmEmergencyRecordService;
import com.sgai.property.em.service.EmEndedService;
import com.sgai.property.em.service.EmRepairListService;
import com.sgai.property.em.service.EmSecuRecordService;
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
    * @ClassName: EmEndedController  
    * @com.sgai.property.commonService.vo;(事件终止Controller)
    * @author LiuYang  
    * @date 2017年12月5日  
    * @Company 首自信--智慧城市创新中心
 */
@Controller
@RequestMapping(value = "${adminPath}/em/emended/emEnded")
@Api(description = "事件终止接口")
public class EmEndedController extends BaseController {

	@Autowired
	private EmEndedService emEndedService;
	@Autowired
	private EmComplaintsService emComplaintsService;
	@Autowired
	private EmRepairListService emRepairListService;
	@Autowired
	private EmEmergencyRecordService emEmergencyRecordService;
	@Autowired
	private WfInstanceRecordService wfInstanceRecordService;
	@Autowired
	private EmSecuRecordService emSecuRecordService;
	@Autowired
	private AlmRecordListService almRecordListService;
	@ApiOperation(value = "获得事件终止", notes = "获得事件终止")
	@RequestMapping(value = "list",method=RequestMethod.GET)
	public String list(EmEnded emEnded, 
			@RequestHeader(value="token")String token,
			@RequestParam(value="pageNo",required = true ,defaultValue = "0" )Integer pageNo,
			@RequestParam(value="pageSize",required = true ,defaultValue = "10" )Integer pageSize,
			HttpServletRequest request, HttpServletResponse response, Model model) {
		if(emEnded==null){
			emEnded=new EmEnded();
		}
		Page<EmEnded> page = emEndedService.findPage(new Page<EmEnded>(pageNo, pageSize), emEnded); 
		model.addAttribute("page", page);
		return "/em/emEndedList";
	}
	
	@ApiOperation(value = "跳转事件终止表单", notes = "跳转事件终止表单")
	@RequestMapping(value = "form",method=RequestMethod.GET)
	public String form(EmEnded emEnded,@RequestHeader(value="token")String token, Model model) {
		model.addAttribute("emEnded", emEnded);
		return "/em/emEndedForm";
	}
	
	@ApiOperation(value = "获取事件终止", notes = "获取事件终止")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "事件终止id", required = false,paramType = "query", dataType = "String"),
	})
	@RequestMapping(value = "/getEnded",method=RequestMethod.POST)
	public EmEnded getEnded(HttpServletRequest request,@RequestHeader(value="token")String token, String id, HttpServletResponse response) {
		EmEnded emEnded = emEndedService.get(id);
		return emEnded;
	}
	
	@ApiOperation(value = "保存事件终止", notes = "保存事件终止")
	@RequestMapping(value = "save",method=RequestMethod.POST)
	public Map<String,String> save(EmEnded emEnded, @RequestHeader(value="token")String token,Model model, RedirectAttributes redirectAttributes) {
		Map<String,String> data = new HashMap<String,String>();
		try {
			emEndedService.save(emEnded);
			data.put("message", "success");
		} catch (Exception e) {
			data.put("message", "failed");
			e.printStackTrace();
		}
		return data;
	}
	
	@ApiOperation(value = "终止流程并保存事件终止记录", notes = "终止流程并保存事件终止记录")
	@RequestMapping(value = "endEm",method=RequestMethod.POST)
	public @ResponseBody Map<String,String> endEm(
			@RequestBody EmEnded emEnded,
			@RequestHeader(value="token")String token, 
			HttpServletRequest request
			) {
		Map<String,String> data = new HashMap<String,String>();
		LoginUser userInfo = UserServletContext.getUserInfo();
		try {
			//投诉终止
			if ("TS".equals(emEnded.getEmType())) {
				emComplaintsService.updateComplaint(emEnded.getEmCode(), "4", userInfo.getUserId(), userInfo.getUserName());
			//维修事件终止
			}else if ("WX".equals(emEnded.getEmType())) {
				emRepairListService.updateRepairList( userInfo.getUserId(), emEnded.getEmCode(),"4");
				//若是报警事件生成的同步报警事件的状态
				almRecordListService.updateAlmRecord(emEnded.getEmCode(), "4");
			//安保事件终止
			}else if ("AB".equals(emEnded.getEmType())) {
				emSecuRecordService.updateSecuRecord(emEnded.getEmCode(),"4", userInfo.getUserId(),userInfo.getUserName());
				emEnded.setEmCode(emEnded.getEmCode());
			//应急事件终止
			}else if ("YJ".equals(emEnded.getEmType())) {
				emEmergencyRecordService.updateEmergencyRecord(emEnded.getEmCode(), "3", userInfo.getUserId(), userInfo.getUserName());
			}
			//保存终止事件
			WfInstanceRecord wfInstanceRecord = new WfInstanceRecord();
			wfInstanceRecord.setEmCode(emEnded.getEmCode());
			List<WfInstanceRecord> list =wfInstanceRecordService.findWfInstanceRecordList(wfInstanceRecord);
			if(list.size()>0) {
				emEnded.setInstanceId(list.get(0).getInstanceId());
			}
			emEnded.setOperator(userInfo.getUserId());
			emEnded.setEnabledFlag("Y");
			emEndedService.save(emEnded);
			data.put("msg", "success");
		} catch (Exception e) {
			data.put("msg", "failed");
			e.printStackTrace();
		}
		return data;
	}
	
	@ApiOperation(value = "事件终止分页", notes = "事件终止分页")
	@RequestMapping(value = "/getList",method=RequestMethod.POST)
	public CommonResponse getList(EmEnded emEnded,
			@RequestHeader(value="token")String token,
			@RequestParam(value="pageNo",required = true ,defaultValue = "0" )Integer pageNo,
			@RequestParam(value="pageSize",required = true ,defaultValue = "10" )Integer pageSize,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		Page<EmEnded> page = emEndedService.findPage(new Page<EmEnded>(pageNo, pageSize), emEnded);
		return ResponseUtil.successResponse(page);
	}
	/**
	 * 
	 * getByCode:(这里用一句话描述这个方法的作用).
	 * @param emEnded
	 * @return :EmEnded 
	 * @since JDK 1.8
	 * @author ASUS
	 */
	@ApiOperation(value = "根据事件编号查询事件终止", notes = "根据事件编号查询事件终止")
	@RequestMapping(value = "/getByCode",method=RequestMethod.POST)
	@ResponseBody
	public EmEnded getByCode(
			@RequestHeader(value="token")String token,
			@RequestBody EmEnded emEnded,
			HttpServletRequest request,
			HttpServletResponse response){
		return emEndedService.getByCode(emEnded);
	}
}