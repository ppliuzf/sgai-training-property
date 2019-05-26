package com.sgai.property.ruag.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sgai.common.persistence.Page;
import com.sgai.common.utils.StringUtils;
import com.sgai.common.web.BaseController;
import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.modules.login.jwt.util.CommonResponse;
import com.sgai.modules.login.jwt.util.ResponseUtil;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.mdm.dto.MdmDevicesUseInfoVo;
import com.sgai.property.mdm.service.MdmDevicesUseInfoService;
import com.sgai.property.ruag.entity.RuagModelTemplate;
import com.sgai.property.ruag.entity.RuagWorkModelDatail;
import com.sgai.property.ruag.service.RuagModelTemplateService;
import com.sgai.property.ruag.service.RuagOptimizationParameterSetService;
import com.sgai.property.ruag.service.RuagWorkModelDatailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *
    * @ClassName: RuagModelTemplateController
    * @com.sgai.property.commonService.vo;(运行模式定义Controller)
    * @author 王天尧
    * @date 2018年1月2日
    * @Company 首自信--智慧城市创新中心
 */
@RestController
@Api(description = "运行模式定义")
@RequestMapping(value = "ruag/ruag/ruagModelTemplate")
public class RuagModelTemplateController extends BaseController {

	@Autowired
	private RuagModelTemplateService ruagModelTemplateService;
	@Autowired
	private RuagWorkModelDatailService  ruagWorkModelDatailService;
	@Autowired
	private MdmDevicesUseInfoService mdmDevicesUseInfoService;
	@Autowired
	private RuagOptimizationParameterSetService ruagOptimizationParameterSetService;
	/**
	 * @Title: getListCtlBusinessDefine
	    * @com.sgai.property.commonService.vo;(加载运行模式列表不带分页)
	    * @param @param workModeName 模式名称
	    * @param @param controlCode  控制类型
	    * @param @param strategyCode 策略类型
	    * @param @param token
	    * @param @param request
	    * @param @param response
	    * @param @return
	    * @param @throws IOException
	    * @param @throws ServletException    参数
	    * @return CommonResponse    返回类型
	    * @throws
	 */
	@ApiOperation(value = "加载运行模式列表不带分页", notes = "加载运行模式列表不带分页")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "workModeName", value = "模式名称", required = false, dataType = "String"),
        @ApiImplicitParam(name = "controlCode", value = "控制类型", required = false, dataType = "String"),
        @ApiImplicitParam(name = "strategyCode", value = "策略类型", required = false, dataType = "String"),
    })
	@RequestMapping(value = "/getList",method=RequestMethod.POST)

	public CommonResponse getList(
			String workModeName,
			String controlCode,
			String strategyCode,

			HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<RuagModelTemplate> findList = new ArrayList<RuagModelTemplate>();
		if(ruagModelTemplateService.checkEffictive()) {
			RuagModelTemplate ruagModelTemplate = new RuagModelTemplate();
			//设置查询条件
			ruagModelTemplate.setControlCode(controlCode);
			ruagModelTemplate.setWorkModeName(workModeName);
			ruagModelTemplate.setStrategyCode(strategyCode);
			findList = ruagModelTemplateService.findList(ruagModelTemplate);
		}
		/*return ResponseUtil.successResponse(ruagModelTemplateService.getList(ruagModelTemplate,new Page<RuagModelTemplate>(request, response)));*/
		return ResponseUtil.successResponse(findList);
	}



	@RequestMapping(value = "/getListYouHua",method=RequestMethod.POST)

	public CommonResponse getListYouHua(
			String workModeName,
			String status,

			@RequestParam(value = "pageNo", required = true,defaultValue="0") Integer pageNo ,
			@RequestParam(value = "pageSize", required = true,defaultValue="10") Integer pageSize,
			HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
		List<RuagModelTemplate> findList = new ArrayList<RuagModelTemplate>();
		LoginUser user  = UserServletContext.getUserInfo();
			RuagModelTemplate ruagModelTemplate = new RuagModelTemplate();
			//设置查询条件
			ruagModelTemplate.setWorkModeName(workModeName);
			ruagModelTemplate.setStatus(status);
			//findList = ruagModelTemplateService.findList(ruagModelTemplate);
			Page<RuagModelTemplate> page = ruagModelTemplateService.findPage(new Page<RuagModelTemplate>(pageNo, pageSize), ruagModelTemplate);

			//将新的参数表清空
			ruagOptimizationParameterSetService.deleteAll();
			//将策略对应的设备的参数  添加到新的参数表
			ruagOptimizationParameterSetService.saveParameter(user);
		return ResponseUtil.successResponse(page);
	}
	/**
	 * @Title: getListCtlBusinessDefine
	    * @com.sgai.property.commonService.vo;(加载运行模式列表带分页)
	    * @param @param workModeName 模式名称
	    * @param @param controlCode  控制类型
	    * @param @param strategyCode 策略类型
	    * @param @param token
	    * @param @param request
	    * @param @param response
	    * @param @return
	    * @param @throws IOException
	    * @param @throws ServletException    参数
	    * @return CommonResponse    返回类型
	    * @throws
	 */
	@ApiOperation(value = "加载运行模式列表带分页", notes = "加载运行模式列表带分页")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "workModeName", value = "模式名称", required = false, dataType = "String"),
        @ApiImplicitParam(name = "controlCode", value = "控制类型", required = false, dataType = "String"),
        @ApiImplicitParam(name = "strategyCode", value = "策略类型", required = false, dataType = "String"),
    })
	@RequestMapping(value = "/getPageList",method=RequestMethod.POST)

	public CommonResponse getPageList(
			String workModeName,
			String controlCode,
			String strategyCode,

			HttpServletRequest request, HttpServletResponse response) throws IOException {
		Page<RuagModelTemplate> page = new Page<RuagModelTemplate>();
		if(ruagModelTemplateService.checkEffictive()) {
			RuagModelTemplate ruagModelTemplate = new RuagModelTemplate();
			//设置查询条件
			ruagModelTemplate.setControlCode(controlCode);
			ruagModelTemplate.setWorkModeName(workModeName);
			ruagModelTemplate.setStrategyCode(strategyCode);
			page = ruagModelTemplateService.getList(ruagModelTemplate,new Page<RuagModelTemplate>(request, response));
		}
		return ResponseUtil.successResponse(page);
	}
	/**
	 *
	    * @Title: getTimeList
	    * @com.sgai.property.commonService.vo;(查询出所有的时间控制策略)
	    * @param @param token
	    * @param @param request
	    * @param @param response
	    * @param @return
	    * @param @throws IOException
	    * @param @throws ServletException    参数
	    * @return CommonResponse    返回类型
	    * @throws
	 */
@RequestMapping(value = "/getTimeList",method=RequestMethod.POST)

	public CommonResponse getTimeList(

			HttpServletRequest request, HttpServletResponse response) throws IOException {
		Page<RuagModelTemplate> page = new Page<RuagModelTemplate>();
			RuagModelTemplate ruagModelTemplate = new RuagModelTemplate();
			//设置查询条件
			ruagModelTemplate.setControlCode("time");
		return ResponseUtil.successResponse(ruagModelTemplateService.findList(ruagModelTemplate));
	}
	/**
	 *
	    * @Title: delete
	    * @com.sgai.property.commonService.vo;(删除运行模式)
	    * @param @param id 模式id
	    * @param @param token
	    * @param @param request
	    * @param @param response
	    * @param @return
	    * @param @throws IOException
	    * @param @throws ServletException    参数
	    * @return CommonResponse    返回类型
	    * @throws
	 */
	@ApiOperation(value = "删除运行模式", notes = "删除运行模式")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "模式id", required = true, dataType = "String"),
    })

	@RequestMapping(value = "/delete",method=RequestMethod.POST)
	public CommonResponse delete(
			String id,

			HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String,String> result = new HashMap<String,String>();
		try {
			result=ruagModelTemplateService.deleteModel(id);
		} catch (Exception e) {

			e.printStackTrace();
			result.put("msg", "faild");
		}

		return ResponseUtil.successResponse(result);
	}
	/**
	 *
	    * @Title: save
	    * @com.sgai.property.commonService.vo;(保存运行策略)
	    * @param @param id 运行策略id
	    * @param @param controlCode 控制类型编码
	    * @param @param strategyCode 策略类型编码
	    * @param @param controlType 控制类型
	    * @param @param strategyType 策略类型
	    * @param @param modelDegree  运行策略等级
	    * @param @param workModeName 运行策略名称
	    * @param @param status       运行策略状态
	    * @param @param selectDates  起止时间数组
	    * @param @param seDate       起止时间字符串
	    * @param @param workTime     星期一到星期日字符串
	    * @param @param token
	    * @param @return
	    * @param @throws JsonProcessingException    参数
	    * @return CommonResponse    返回类型
	    * @throws
	 */
	@ApiOperation(value = "保存运行策略", notes = "保存运行策略")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "模式id", required = false, dataType = "String"),
        @ApiImplicitParam(name = "controlCode", value = "控制类型编码", required = false, dataType = "String"),
        @ApiImplicitParam(name = "strategyCode", value = "策略类型编码", required = false, dataType = "String"),
        @ApiImplicitParam(name = "controlType", value = "控制类型", required = false, dataType = "String"),
        @ApiImplicitParam(name = "strategyType", value = "策略类型", required = false, dataType = "String"),
        @ApiImplicitParam(name = "modelDegree", value = "运行策略等级", required = false, dataType = "String"),
        @ApiImplicitParam(name = "workModeName", value = "运行策略名称", required = false, dataType = "String"),
        @ApiImplicitParam(name = "status", value = "运行策略状态", required = false, dataType = "String"),
        @ApiImplicitParam(name = "seDate", value = "起止时间字符串", required = false, dataType = "String"),
        @ApiImplicitParam(name = "workTime", value = "星期一到星期日字符串", required = false, dataType = "String"),
    })

	@RequestMapping(value = "/save",method=RequestMethod.POST)
	public CommonResponse save(
			String id,
			String controlCode,
			String strategyCode,
			String modelDegree,
			String workModeName,
			String seDate,
			String workTime
			) throws JsonProcessingException {
		Map<String,String> result = new HashMap<String,String>();
		try {
			result=ruagModelTemplateService.saveMT(id,controlCode,strategyCode,
												 modelDegree,workModeName,seDate,workTime);

		} catch (Exception e) {

			e.printStackTrace();
			result.put("msg", "faild");
		}
		return ResponseUtil.successResponse(result);
	}
	/**
	 *
	    * @Title: getById
	    * @com.sgai.property.commonService.vo;(通过id查找运行策略)
	    * @param @param id 运行策略id
	    * @param @param token
	    * @param @return
	    * @param @throws JsonProcessingException    参数
	    * @return CommonResponse    返回类型
	    * @throws
	 */
	@ApiOperation(value = "通过id查找运行策略", notes = "通过id查找运行策略")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "运行策略id", required = false, dataType = "String"),
    })

	@RequestMapping(value = "/getById",method=RequestMethod.POST)
	public CommonResponse getById(
			String id,
			@RequestHeader("token") String token) throws JsonProcessingException {
		return ResponseUtil.successResponse(ruagModelTemplateService.get(id));
	}
	/**
	 *
	    * @Title: startModel
	    * @com.sgai.property.commonService.vo;(开启运行策略（日期控制）)
	    * @param @param id
	    * @param @param startTime
	    * @param @param endTime
	    * @param @param token
	    * @param @return
	    * @param @throws JsonProcessingException    参数
	    * @return CommonResponse    返回类型
	    * @throws
	 */
	@ApiOperation(value = "开启运行策略", notes = "开启运行策略")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "运行策略id", required = false, dataType = "String"),
        @ApiImplicitParam(name = "startTime", value = "开始时间", required = false, dataType = "String"),
        @ApiImplicitParam(name = "endTime", value = "结束时间", required = false, dataType = "String"),
    })
	@RequestMapping(value = "/startModel",method=RequestMethod.POST)
	public CommonResponse startModel(
			String id,
			String startTime,
			String endTime,
			@RequestHeader("token") String token) throws JsonProcessingException {
		Map<String,String> result = new HashMap<String,String>();
		try {
			RuagWorkModelDatail ruagWorkModelDatail = new RuagWorkModelDatail();
			ruagWorkModelDatail.setModelTemplateId(id);
			List<RuagWorkModelDatail> findList = ruagWorkModelDatailService.findList(ruagWorkModelDatail);
			if(findList.size()!=0) {
			  ruagModelTemplateService.startModel(id, startTime, endTime);
			  result.put("msg", "success");
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
	    * @Title: stopModel
	    * @com.sgai.property.commonService.vo;(关闭运行策略)
	    * @param @param id 运行策略id
	    * @param @param startTime 开始时间
	    * @param @param endTime   结束时间
	    * @param @param token
	    * @param @return
	    * @param @throws JsonProcessingException    参数
	    * @return CommonResponse    返回类型
	    * @throws
	 */
	@ApiOperation(value = "关闭运行策略", notes = "关闭运行策略")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "运行策略id", required = false, dataType = "String"),
        @ApiImplicitParam(name = "startTime", value = "开始时间", required = false, dataType = "String"),
        @ApiImplicitParam(name = "endTime", value = "结束时间", required = false, dataType = "String"),
    })

	@RequestMapping(value = "/stopModel",method=RequestMethod.POST)
	public CommonResponse stopModel(
			String id,
			String startTime,
			String endTime,
			@RequestHeader("token") String token) throws JsonProcessingException {
		Map<String,String> result = new HashMap<String,String>();
		try {
			ruagModelTemplateService.stopModel(id, startTime, endTime);
			result.put("msg", "success");
		} catch (Exception e) {

			result.put("msg", "faild");
			e.printStackTrace();
		}
		return ResponseUtil.successResponse(result);
	}
	/**
	 *
	 * list:(跳转到模式详情页面).
	 * @param token
	 * @param request
	 * @param response
	 * @param model
	 * @return :String
	 * @since JDK 1.8
	 * @author 王天尧
	 */
	@ApiOperation(value = "跳转到策略详情页面", notes = "跳转到策略详情页面")
	@RequestMapping(value = {"modelDatil", ""},method=RequestMethod.GET)
	public String modelDatil( @RequestHeader(value="token")String token,HttpServletRequest request, HttpServletResponse response, Model model) {
		return "/ruag/ruagModelDetail";
	}
	@RequestMapping(value = "getLackList",method=RequestMethod.POST)
	public CommonResponse getLackList(
			String id,
			String spaceCodes,
			String profCodes,
			MdmDevicesUseInfoVo mdmDevicesUseInfoVo,
			HttpServletRequest request,
			HttpServletResponse response
			) throws JsonProcessingException {
		Map<String,String> sqlMap = new HashMap<String,String>();
		String sql = "AND NOT EXISTS (SELECT * FROM RUAG_WORK_MODEL_DATAIL D WHERE A .DEVICES_CODE = D .DEVICE_CODE AND D .MODEL_TEMPLATE_ID ='"+id+"')";
		if(StringUtils.isNotEmpty(spaceCodes)) {
			sql = sql + " AND b.prof_code in("+profCodes.substring(0, profCodes.length()-1)+")";
			sqlMap.put("sqlMap", sql+" AND a.space_code in("+spaceCodes.substring(0, spaceCodes.length()-1)+")");
			mdmDevicesUseInfoVo.setSqlMap(sqlMap);
		}else {
			sqlMap.put("sqlMap", sql);
		}
		mdmDevicesUseInfoVo.setSqlMap(sqlMap);
	    Page<MdmDevicesUseInfoVo> page = mdmDevicesUseInfoService.findPageVoByModel(new Page<MdmDevicesUseInfoVo>(request, response), mdmDevicesUseInfoVo);
		return ResponseUtil.successResponse(page);
	}
}
