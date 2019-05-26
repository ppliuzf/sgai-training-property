package com.sgai.property.ruag.web;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sgai.common.web.BaseController;
import com.sgai.modules.login.jwt.util.CommonResponse;
import com.sgai.modules.login.jwt.util.ResponseUtil;
import com.sgai.property.ruag.entity.RuagWorkModelDatail;
import com.sgai.property.ruag.service.RuagModelCalendarService;
import com.sgai.property.ruag.service.RuagWorkModelDatailService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 
    * @ClassName: RuagModelCalendarController  
    * @com.sgai.property.commonService.vo;(策略运行日程Controller)
    * @author 王天尧  
    * @date 2018年1月5日  
    * @Company 首自信--智慧城市创新中心
 */
@RestController
@Api(description = "策略运行日程")
@RequestMapping(value = "ruag/ruag/ruagModelCalendar")
public class RuagModelCalendarController extends BaseController {

	@Autowired
	private RuagModelCalendarService ruagModelCalendarService;
	@Autowired
	private RuagWorkModelDatailService  ruagWorkModelDatailService;
	/**
	 * 
	    * @Title: getList  
	    * @com.sgai.property.commonService.vo;(加载策略日程记录)
	    * @param @param token
	    * @param @return
	    * @param @throws JsonProcessingException
	    * @param @throws ParseException    参数  
	    * @return CommonResponse    返回类型  
	    * @throws
	 */
	@ApiOperation(value = "加载策略日程记录", notes = "加载策略日程记录")
	@RequestMapping(value = "/getList",method=RequestMethod.POST)
	public CommonResponse getList(
			@RequestHeader("token") String token) throws JsonProcessingException, ParseException {
		return ResponseUtil.successResponse(ruagModelCalendarService.getList());
	}
	/**
	 * 
	    * @Title: saveTimeModel  
	    * @com.sgai.property.commonService.vo;(保存时间控制类型)
	    * @param @param date 日期
	    * @param @param modelTemplateId 运行策略id
	    * @param @param timeStart 开始时间
	    * @param @param timeEnd 结束时间
	    * @param @param token
	    * @param @return
	    * @param @throws JsonProcessingException
	    * @param @throws ParseException    参数  
	    * @return CommonResponse    返回类型  
	    * @throws
	 */
	@ApiOperation(value = "保存时间控制类型", notes = "保存时间控制类型")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "date", value = "日期", required = false, dataType = "String"),
        @ApiImplicitParam(name = "modelTemplateId", value = "运行策略id", required = false, dataType = "String"),
        @ApiImplicitParam(name = "timeStart", value = "开始时间", required = false, dataType = "String"),
        @ApiImplicitParam(name = "timeEnd", value = "结束时间", required = false, dataType = "String"),
    })
	@RequestMapping(value = "/saveTimeModel",method=RequestMethod.POST)
	public CommonResponse saveTimeModel(
			String date,
			String modelTemplateId,
			String timeStart,
			String timeEnd,
			@RequestHeader("token") String token) throws JsonProcessingException {
		Map<String,String> result = new HashMap<String,String>();
		try {
			result=ruagModelCalendarService.saveTimeModel(date, modelTemplateId, timeStart, timeEnd);
		} catch (Exception e) {

			result.put("msg", "faild");
		}
		return ResponseUtil.successResponse(result);
	}
	/**
	 * 
	    * @Title: startModelByCalendar  
	    * @com.sgai.property.commonService.vo;(开启某一天的某个运行策略)
	    * @param @param id 策略日程id
	    * @param @param token
	    * @param @return
	    * @param @throws JsonProcessingException
	    * @param @throws ParseException    参数  
	    * @return CommonResponse    返回类型  
	    * @throws
	 */
	@ApiOperation(value = "开启某一天的某个运行策略", notes = "开启某一天的某个运行策略")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "策略日程id", required = false, dataType = "String"),
    })
	@RequestMapping(value = "/startModelByCalendar",method=RequestMethod.POST)
	public CommonResponse startModelByCalendar(
			String id,
			@RequestHeader("token") String token) throws JsonProcessingException {
		Map<String,String> result = new HashMap<String,String>();
		try {
			RuagWorkModelDatail ruagWorkModelDatail = new RuagWorkModelDatail();
			ruagWorkModelDatail.setModelTemplateId(ruagModelCalendarService.get(id).getModelId());
			List<RuagWorkModelDatail> findList = ruagWorkModelDatailService.findList(ruagWorkModelDatail);
			if(findList.size()!=0) {
				result=ruagModelCalendarService.startModelByCalendar(id);
			}else {
				result.put("msg", "empty");
			}
			
		} catch (Exception e) {

			e.printStackTrace();
			result.put("msg", "faild");
		}
		return ResponseUtil.successResponse(result);
	}
	/**
	 * 
	    * @Title: stopModelByCalendar  
	    * @com.sgai.property.commonService.vo;(关闭某一天的某个运行策略)
	    * @param @param id 策略日程id
	    * @param @param token 
	    * @param @return
	    * @param @throws JsonProcessingException
	    * @param @throws ParseException    参数  
	    * @return CommonResponse    返回类型  
	    * @throws
	 */
	@ApiOperation(value = "关闭某一天的某个运行策略", notes = "关闭某一天的某个运行策略")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "策略日程id", required = false, dataType = "String"),
    })
	@RequestMapping(value = "/stopModelByCalendar",method=RequestMethod.POST)
	public CommonResponse stopModelByCalendar(
			String id,
			@RequestHeader("token") String token) throws JsonProcessingException {
		Map<String,String> result = new HashMap<String,String>();
		try {
			result=ruagModelCalendarService.stopModelByCalendar(id);
		} catch (Exception e) {

			result.put("msg", "faild");
		}
		return ResponseUtil.successResponse(result);
	}
	/**
	 * 
	    * @Title: deleteMdoelByCalendar  
	    * @com.sgai.property.commonService.vo;(删除某一天的某个运行策略)
	    * @param @param id 策略日程id
	    * @param @param token
	    * @param @return
	    * @param @throws JsonProcessingException
	    * @param @throws ParseException    参数  
	    * @return CommonResponse    返回类型  
	    * @throws
	 */
	@ApiOperation(value = "删除某一天的某个运行策略", notes = "删除某一天的某个运行策略")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "策略日程id", required = false, dataType = "String"),
    })
	@RequestMapping(value = "/deleteMdoelByCalendar",method=RequestMethod.POST)
	public CommonResponse deleteMdoelByCalendar(
			String id,
			@RequestHeader("token") String token) throws JsonProcessingException {
		Map<String,String> result = new HashMap<String,String>();
		try {
			ruagModelCalendarService.deleteMdoelByCalendar(id);
			result.put("msg", "success");
		} catch (Exception e) {

			result.put("msg", "faild");
		}
		return ResponseUtil.successResponse(result);
	}
	/**
	 * 
	    * @Title: startChecked  
	    * @com.sgai.property.commonService.vo;(保存某天某个运行策略的修改)
	    * @param @param ids 选中的运行策略配置id集合,字符串形式，以逗号隔开
	    * @param @param id 策略日程id
	    * @param @param token
	    * @param @return
	    * @param @throws JsonProcessingException
	    * @param @throws ParseException    参数  
	    * @return CommonResponse    返回类型  
	    * @throws
	 */
	@ApiOperation(value = "保存某天某个运行策略的修改", notes = "保存某天某个运行策略的修改")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "ids", value = "选中的运行策略配置id集合", required = false, dataType = "String"),
        @ApiImplicitParam(name = "id", value = "策略日程id", required = false, dataType = "String"),
    })
	@RequestMapping(value = "/startChecked",method=RequestMethod.POST)
	public CommonResponse startChecked(
			String ids,
			String id,
			@RequestHeader("token") String token) throws JsonProcessingException {
		Map<String,String> result = new HashMap<String,String>();
		try {
			result=ruagModelCalendarService.startChecked(ids,id);
		} catch (Exception e) {

			result.put("msg", "faild");
		}
		return ResponseUtil.successResponse(result);
	}
	/**
	 * 
	 * findById:(通过id寻找日程策略).
	 * @param id 策略日程id
	 * @param token
	 * @return
	 * @throws JsonProcessingException
	 * @since JDK 1.8
	 * @author 王天尧
	 */
	@RequestMapping(value = "/findById",method=RequestMethod.POST)
	public CommonResponse findById(
			String id,
			@RequestHeader("token") String token) throws JsonProcessingException {
		return ResponseUtil.successResponse(ruagModelCalendarService.get(id));
	}
	
}