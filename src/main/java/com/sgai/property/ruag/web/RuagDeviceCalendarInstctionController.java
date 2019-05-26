package com.sgai.property.ruag.web;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sgai.common.persistence.Page;
import com.sgai.common.utils.StringUtils;
import com.sgai.common.web.BaseController;
import com.sgai.modules.login.jwt.util.CommonResponse;
import com.sgai.modules.login.jwt.util.ResponseUtil;
import com.sgai.property.ruag.entity.RuagDeviceCalendarInstction;
import com.sgai.property.ruag.service.RuagDeviceCalendarInstctionService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 设备指令状态Controller
 * @author yangyz
 * @version 2018-01-02
 */
@RestController
@RequestMapping(value = "ruag/ruag/ruagDeviceCalendarInstction")
@Api(description = "设备指令接口")
public class RuagDeviceCalendarInstctionController extends BaseController {

	@Autowired
	private RuagDeviceCalendarInstctionService ruagDeviceCalendarInstctionService;
	
	@ApiOperation(value = "设备指令列表页面", notes = "投诉事件列表页面")
	@RequestMapping(value = "list",method=RequestMethod.GET)
	public String list(RuagDeviceCalendarInstction ruagDeviceCalendarInstction, 
			String spaceCodes,
			@RequestHeader(value="token")String token,
			@RequestParam(value="pageNo",required = true ,defaultValue = "0" )Integer pageNo,
			@RequestParam(value="pageSize",required = true ,defaultValue = "10" )Integer pageSize,
			HttpServletRequest request, HttpServletResponse response, Model model) {
		if(ruagDeviceCalendarInstction==null){
			ruagDeviceCalendarInstction=new RuagDeviceCalendarInstction();
		}
		if("".equals(spaceCodes)&&spaceCodes.equals(null)) {
			
		}else {
			Map<String,String> sqlMap = new HashMap<String,String>();
			String sql = "in("+spaceCodes.substring(0,spaceCodes.length()-1)+")";
			sqlMap.put("sql", sql);
			ruagDeviceCalendarInstction.setSqlMap(sqlMap);
		}
		Page<RuagDeviceCalendarInstction> page = ruagDeviceCalendarInstctionService.findPage(new Page<RuagDeviceCalendarInstction>(pageNo, pageSize), ruagDeviceCalendarInstction); 
		model.addAttribute("page", page);
		return "ruag/ruagDeviceCalendarInstctionList";
	}	                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           
	
	@ApiOperation(value = "投诉事件form页面", notes = "投诉事件form页面")
	@RequestMapping(value = "form",method=RequestMethod.GET)
	public String form(RuagDeviceCalendarInstction ruagDeviceCalendarInstction, @RequestHeader(value="token")String token,Model model) {
		model.addAttribute("ruagDeviceCalendarInstction", ruagDeviceCalendarInstction);
		return "ruag/ruagDeviceCalendarInstctionForm";
	}

	@ApiOperation(value = "保存投诉事件", notes = "保存投诉事件")
	@RequestMapping(value = "save",method=RequestMethod.POST)
	public CommonResponse save(RuagDeviceCalendarInstction ruagDeviceCalendarInstction,@RequestHeader(value="token")String token) throws JsonProcessingException {
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Map<String,String> data = new HashMap<String,String>();
		ruagDeviceCalendarInstction.setEnabledFlag("Y");
		if (StringUtils.isBlank(ruagDeviceCalendarInstction.getId())) {
			//如果是新增的数据，校验是否和库中数据重复
			RuagDeviceCalendarInstction info = new RuagDeviceCalendarInstction();
//			info.setComplCode(ruagDeviceCalendarInstction.getComplCode());
			List<RuagDeviceCalendarInstction> resultlist = ruagDeviceCalendarInstctionService.findList(info);
			try {
				if (resultlist.size()==0) {
//					ruagDeviceCalendarInstction.setComplTime(format.format(new Date()));
//					ruagDeviceCalendarInstctionService.saveComplaint(ruagDeviceCalendarInstction);
					data.put("message", "success");
				} else {
					data.put("message", "repeat");
				}
			} catch (Exception e) {
				data.put("message", "failed");
				e.printStackTrace();
			}
		} else {
			//如果是更新数据，直接执行save方法
			try {
				ruagDeviceCalendarInstctionService.save(ruagDeviceCalendarInstction);
				data.put("message", "success");
			} catch (Exception e) {
				data.put("message", "failed");
				e.printStackTrace();
			}
		}
		return ResponseUtil.successResponse(data);
	}
	

	@ApiOperation(value = "设备指令分页", notes = "设备指令分页")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "dciDate", value = "日期", required = false,paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "spaceName", value = "位置", required = false,paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "profName", value = "专业", required = false,paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "deviceName", value = "设备名称", required = false,paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "deviceCode", value = "设备编号", required = false,paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "instructionStatus", value = "发送状态", required = false,paramType = "query", dataType = "String")
	})
	@RequestMapping(value = "/getListRuagDeviceCalendarInstction",method=RequestMethod.POST)
	public CommonResponse getListRuagDeviceCalendarInstction(
			String spaceCodes,
			String dciDate,
			String spaceName,
			String profCode,
			String deviceName,
			String deviceCode,
			String instructionStatus,
			String modelName,
			@RequestHeader(value="token")String token,
			@RequestParam(value="pageNo",required = true ,defaultValue = "0" )Integer pageNo,
			@RequestParam(value="pageSize",required = true ,defaultValue = "10" )Integer pageSize,
			HttpServletRequest request, 
			HttpServletResponse response) throws IOException, ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Map<String,String> sqlMap = new HashMap<String,String>();
		
		RuagDeviceCalendarInstction ruagDeviceCalendarInstction = new RuagDeviceCalendarInstction();
		Page<RuagDeviceCalendarInstction> page = new Page<RuagDeviceCalendarInstction>(pageNo, pageSize);
		page.setOrderBy("created_dt DESC");
		ruagDeviceCalendarInstction.setPage(page);
		if(StringUtils.isNotBlank(dciDate)) {
			ruagDeviceCalendarInstction.setDciDate(format.parse(dciDate));
		}
		if(StringUtils.isNotBlank(spaceCodes)) {
			String sql = "a.space_code in("+spaceCodes.substring(0, spaceCodes.length()-1)+")";
			sqlMap.put("sqlMap", sql);
			ruagDeviceCalendarInstction.setSqlMap(sqlMap);
		}
		ruagDeviceCalendarInstction.setSpaceName(spaceName);
		ruagDeviceCalendarInstction.setProfCode(profCode);
		ruagDeviceCalendarInstction.setDeviceName(deviceName);
		ruagDeviceCalendarInstction.setDeviceCode(deviceCode);
		ruagDeviceCalendarInstction.setModelName(modelName);
		ruagDeviceCalendarInstction.setEffectiveStatus1("1");
		ruagDeviceCalendarInstction.setEnabledFlag("Y");
		if(StringUtils.isNotBlank(instructionStatus)) {
			ruagDeviceCalendarInstction.setInstructionStatus(Long.valueOf(instructionStatus));
		}
		Page<RuagDeviceCalendarInstction> page2 = ruagDeviceCalendarInstctionService.findPage(new Page<RuagDeviceCalendarInstction>(pageNo, pageSize), ruagDeviceCalendarInstction);
		return ResponseUtil.successResponse(page2);
	}
	/**
	 * @throws ServletException 
	 * @throws IOException 
	 * @throws SchedulerException 
	 * 
	    * @Title: generateInstructionsScheduleTask  
	    * @com.sgai.property.commonService.vo;(定时生成指令)
	    * @param @throws ParseException    参数  
	    * @return void    返回类型  
	    * @throws
	 */
	@ApiOperation(value = "定时生成指令", httpMethod = "POST", notes = "定时生成指令")
	@RequestMapping(value = "generateInstructionsScheduleTask")
	public synchronized void generateInstructionsScheduleTask() throws ParseException, SchedulerException, IOException, ServletException {
		ruagDeviceCalendarInstctionService.generateInstructionsScheduleTask();
	}
	@ApiOperation(value = "定时发送指令", httpMethod = "POST", notes = "定时发送指令")
	@RequestMapping(value = "sendIns")
	public synchronized void sendIns(String timePoint) throws ParseException, SchedulerException {
		ruagDeviceCalendarInstctionService.sendInstructions(timePoint);
	}
}