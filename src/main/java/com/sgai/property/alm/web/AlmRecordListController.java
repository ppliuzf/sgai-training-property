package com.sgai.property.alm.web;

import com.sgai.common.persistence.Page;
import com.sgai.common.utils.StringUtils;
import com.sgai.common.web.BaseController;
import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.modules.login.jwt.util.CommonResponse;
import com.sgai.modules.login.jwt.util.ResponseUtil;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.alm.entity.AlmRecordList;
import com.sgai.property.alm.service.AlmRecordListService;
import com.sgai.property.em.service.EmRepairListService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * ClassName: AlmRecordListController com.sgai.property.commonService.vo;(报警记录列表Controller)
 *
 * @author 王天尧 Date 2017年11月24日 Company 首自信--智慧城市创新中心
 */
@RestController
@Api(description = "报警记录列表Controller")
@RequestMapping(value = "${adminPath}/alm/almRecordList")
public class AlmRecordListController extends BaseController {

	@Autowired
	private AlmRecordListService almRecordListService;
	@Autowired
	private EmRepairListService emRepairListService;

	/**
	 *
	 * list:(获取报警记录列表).
	 *
	 * @param request
	 * @param response
	 * @param model
	 * @return :Page<AlmRecordList>
	 * @since JDK 1.8
	 * @author 王天尧
	 * @throws IOException
     */
	@ApiOperation(value = "获取设备与报警等级关系列表", notes = "获取设备与报警等级关系列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "alermTypeCode", value = "报警分类代码", required = false, dataType = "String"),
			@ApiImplicitParam(name = "levelCode", value = "报警等级代码", required = false, dataType = "String"),
			@ApiImplicitParam(name = "profCode", value = "专业代码", required = false, dataType = "String"),
			@ApiImplicitParam(name = "states", value = "状态", required = false, dataType = "String"), })
	@RequestMapping(value = "/getList", method = RequestMethod.POST)
	public CommonResponse list(String devicesCode, String alermTypeCode, String levelCode, String profCode,
			String states, String recordNum, String spaceCode, LoginUser userInfo, HttpServletRequest request,
			 HttpServletResponse response, Model model)
			throws IOException {
		AlmRecordList almRecordList = new AlmRecordList();
		almRecordList.setRecordNum(recordNum);
		almRecordList.setAlermTypeCode(alermTypeCode);
		almRecordList.setProfCode(profCode);
		almRecordList.setStates(states);
		almRecordList.setLevelCode(levelCode);
		almRecordList.setDevicesCode(devicesCode);
		almRecordList.setEnabledFlag("Y");
		almRecordList.setSpaceId(spaceCode);
		Map<String, String> sqlMap = new HashMap<String, String>();
		String emCodes = emRepairListService.getEmCodes(userInfo);
		String sql = "";
		if (StringUtils.isBlank(emCodes)) {
			sql = "a.em_code is null";
		} else {
			sql = "(a.em_code in (" + emRepairListService.getEmCodes(userInfo) + ")or a.em_code is null)";
		}
		if (StringUtils.isNotBlank(sql)) {
			sqlMap.put("sql", sql);
		}
		almRecordList.setSqlMap(sqlMap);
		Page<AlmRecordList> page = almRecordListService.findPage(new Page<AlmRecordList>(request, response),
				almRecordList);
		return ResponseUtil.successResponse(page);
	}
	@ApiOperation(value = "获取未确认的报警", notes = "获取未确认的报警")
	@RequestMapping(value = "/getCountsWithOutConfirm", method = RequestMethod.POST)
	public CommonResponse getCountsWithOutConfirm( String profCode, String spaceCode, LoginUser userInfo, HttpServletRequest request,
			 HttpServletResponse response, Model model)
			throws IOException {
		AlmRecordList almRecordList = new AlmRecordList();
		almRecordList.setStates("10");
		almRecordList.setProfCode(profCode);
		almRecordList.setEnabledFlag("Y");
		almRecordList.setSpaceId(spaceCode);
		almRecordList.setComCode(userInfo.getComCode());
		almRecordList.setModuCode(userInfo.getModuCode());
		Map<String, String> sqlMap = new HashMap<String, String>();
		String emCodes = emRepairListService.getEmCodes(userInfo);
		String sql = "";
		if (StringUtils.isBlank(emCodes)) {
			sql = "a.em_code is null";
		} else {
			sql = "(a.em_code in (" + emRepairListService.getEmCodes(userInfo) + ")or a.em_code is null)";
		}
		if (StringUtils.isNotBlank(sql)) {
			sqlMap.put("sql", sql);
		}
		almRecordList.setSqlMap(sqlMap);
		return ResponseUtil.successResponse(almRecordListService.findCount(almRecordList));
	}
	/**
	 *
	 * @Title: getById @com.sgai.property.commonService.vo;(通过id获取报警记录) @param @param id @param @param
	 * request @param @param token @param @param response @param @param
	 * model @param @return @param @throws ServletException @param @throws
	 * IOException 参数 @return CommonResponse 返回类型 @throws
	 */
	@ApiOperation(value = "获取设备与报警等级关系列表", notes = "获取设备与报警等级关系列表")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "报警记录id", required = false, dataType = "String"), })
	@RequestMapping(value = "/getById", method = RequestMethod.POST)
	public CommonResponse getById(String id, HttpServletRequest request,
			HttpServletResponse response, Model model) throws IOException {

		return ResponseUtil.successResponse(almRecordListService.get(id));
	}

	/**
	 *
	 * @Title: getMsgById @com.sgai.property.commonService.vo;(获取处理详情) @param @param id @param @param
	 * request @param @param token @param @param response @param @param
	 * model @param @return @param @throws ServletException @param @throws
	 * IOException 参数 @return CommonResponse 返回类型 @throws
	 */
	@ApiOperation(value = "获取处理详情", notes = "获取处理详情")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "报警记录id", required = false, dataType = "String"), })
	@RequestMapping(value = "/getMsgById", method = RequestMethod.POST)
	public CommonResponse getMsgId(String id, HttpServletRequest request,
			HttpServletResponse response, Model model) throws IOException {

		return ResponseUtil.successResponse(almRecordListService.getMsgById(id));
	}

	/**
	 *
	 * delete:(删除报警记录).
	 *
	 * @param almRecordList
	 * @param ids
	 *            报警记录id集合（由字符串拼接而成）
	 * @param redirectAttributes
	 * @return :Map<String,String>
	 * @since JDK 1.8
	 * @author 王天尧
	 */
	@RequestMapping(value = "delete")
	public Map<String, String> delete(AlmRecordList almRecordList, String ids, RedirectAttributes redirectAttributes) {
		Map<String, String> result = new HashMap<String, String>();
		String idArr[] = ids.split("~");
		try {
			for (String id : idArr) {
				if (id != null && !id.equals("")) {
					almRecordList.setId(id);
					almRecordListService.delete(almRecordList);
				}
			}
			result.put("msg", "success");
		} catch (Exception e) {

			e.printStackTrace();
			result.put("msg", "faild");
		}

		return result;
	}
	/**
	 *
	 * @Title: generateEvents @com.sgai.property.commonService.vo;(生成维修事件) @param @param id
	 * 报警记录主键 @param @param user @param @param token @param @param
	 * request @param @param response @param @param model @param @throws
	 * ParseException @param @throws ServletException @param @throws IOException
	 * 参数 @return void 返回类型 @throws
	 */
	@ApiOperation(value = "生成维修事件", notes = "生成维修事件")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "报警记录主键", required = false, dataType = "String"), })
	@RequestMapping(value = "/generateEvents", method = RequestMethod.POST)
	public CommonResponse generateEvents(String id, LoginUser user,
			HttpServletRequest request, HttpServletResponse response, Model model)
			throws IOException {
		Map<String, String> result = new HashMap<String, String>();
		try {
			String generateEvents = almRecordListService.generateEvents(id, user);
			result.put("msg", "success");
			result.put("emCode", generateEvents);
		} catch (Exception e) {

			e.printStackTrace();
			result.put("msg", "faild");
		}
		return ResponseUtil.successResponse(result);
	}

	@ApiOperation(value = "处理报警", notes = "处理报警")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "报警记录主键", required = false, dataType = "String"),
			@ApiImplicitParam(name = "states", value = "报警状态", required = false, dataType = "String"), })
	@RequestMapping(value = "/almProcess", method = RequestMethod.POST)
	public CommonResponse almProcess(String id, String states, String remarks, LoginUser user,
			 HttpServletRequest request, HttpServletResponse response, Model model)
			throws IOException {
		Map<String, String> result = new HashMap<String, String>();
		try {
			if ("20".equals(states)) {
				almRecordListService.almConfirm(id);
			} else {
				almRecordListService.almProcess(id, remarks, states);
			}
			result.put("msg", "success");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("msg", "faild");
		}
		return ResponseUtil.successResponse(result);
	}

	/**
	 *
	 * list:(根据专业获取报警记录).
	 *
	 * @param request
	 * @param response
	 * @param model
	 * @return :Page<AlmRecordList>
	 * @since JDK 1.8
	 * @author 李明月
     */
	@ApiOperation(value = "运行监控获取设备与报警等级关系列表", notes = "获取设备与报警等级关系列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "alermTypeCode", value = "报警分类代码", required = false, dataType = "String"),
			@ApiImplicitParam(name = "levelCode", value = "报警等级代码", required = false, dataType = "String"),
			@ApiImplicitParam(name = "profCode", value = "专业代码", required = false, dataType = "String"),
			@ApiImplicitParam(name = "states", value = "状态", required = false, dataType = "String"), })
	@RequestMapping(value = "/getAlarmList", method = RequestMethod.POST)
	public List<AlmRecordList> getAlarmList(String profCode, // 专业code
			HttpServletRequest request,  HttpServletResponse response, Model model) {
		AlmRecordList almRecordList = new AlmRecordList();
		almRecordList.setProfCode(profCode);
		almRecordList.setEnabledFlag("Y");

		String comCode = null;
		try {
			LoginUser loginUser = UserServletContext.getUserInfo();
			comCode = loginUser.getComCode();
		} catch (Exception e) {
			e.printStackTrace();
		}
		almRecordList.setComCode(comCode);

		List<AlmRecordList> list = almRecordListService.findListOrderByStates(almRecordList);
		return list;
	}

	/**
	 *
	 * list:(根据楼号获取报警记录).
	 *
	 * @param request
	 * @param response
	 * @param model
	 * @return :Page<AlmRecordList>
	 * @since JDK 1.8
	 * @author 李明月
     */
	@ApiOperation(value = "运行监控获取设备与报警等级关系列表", notes = "获取设备与报警等级关系列表")
	@ApiImplicitParams({ @ApiImplicitParam(name = "profCode", value = "专业代码", required = false, dataType = "String"),
			@ApiImplicitParam(name = "states", value = "状态", required = false, dataType = "String"), })
	@RequestMapping(value = "/getAlarmListByArea", method = RequestMethod.POST)
	public List<AlmRecordList> getAlarmListByArea(String area, // 楼号code
			HttpServletRequest request,  HttpServletResponse response, Model model) {
		AlmRecordList almRecordList = new AlmRecordList();
		almRecordList.setArea(area);
		almRecordList.setEnabledFlag("Y");

		String comCode = null;
		try {
			LoginUser loginUser = UserServletContext.getUserInfo();
			comCode = loginUser.getComCode();
		} catch (Exception e) {
			e.printStackTrace();
		}
		almRecordList.setComCode(comCode);

		List<AlmRecordList> list = almRecordListService.findListOrderByStates(almRecordList);
		return list;
	}

	/**
	 *
	 * list:(根据comCode获取所有报警记录).
	 *
	 * @param almRecordList
	 * @param request
	 * @param response
	 * @param model
	 * @return :Page<AlmRecordList>
	 * @since JDK 1.8
	 * @author 李明月
	 * @throws IOException
     */
	@ApiOperation(value = "运行监控获取设备与报警等级关系列表", notes = "获取设备与报警等级关系列表")
	@RequestMapping(value = "/getAlarmListByUser", method = RequestMethod.POST)
	public CommonResponse getAlarmListByUser(AlmRecordList almRecordList, HttpServletRequest request,

			@RequestParam(value = "pageNo", required = true, defaultValue = "0") Integer pageNo,
			@RequestParam(value = "pageSize", required = true, defaultValue = "10") Integer pageSize,
			HttpServletResponse response, Model model) throws IOException {

		String comCode = null;
		try {
			LoginUser loginUser = UserServletContext.getUserInfo();
			comCode = loginUser.getComCode();
		} catch (Exception e) {
			e.printStackTrace();
		}
		almRecordList.setComCode(comCode);

		Page<AlmRecordList> page = almRecordListService.getAlarmListByUser(new Page<AlmRecordList>(pageNo, pageSize),
				almRecordList);

		return ResponseUtil.successResponse(page);
	}

	/**
	 *
	 * @Title: updateAlm @com.sgai.property.commonService.vo; @param @param
	 * id @param @return @param @throws ParseException @param @throws
	 * ServletException @param @throws IOException 参数 @return CommonResponse
	 * 返回类型 @throws
	 */
	@ApiOperation(value = "处理报警", notes = "处理报警")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "报警记录主键", required = false, dataType = "String"),
			@ApiImplicitParam(name = "states", value = "报警状态", required = false, dataType = "String"), })
	@RequestMapping(value = "/updateAlm", method = RequestMethod.POST)
	public CommonResponse updateAlm(String id, String states, LoginUser user,
			HttpServletRequest request, HttpServletResponse response, Model model)
			throws IOException {
		Map<String, String> result = new HashMap<String, String>();
		try {
			almRecordListService.updateAlm(id, states);
			result.put("msg", "success");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("msg", "faild");
		}
		return ResponseUtil.successResponse(result);
	}
	@ApiOperation(value = "获取报警条数", notes = "获取报警条数")
	@RequestMapping(value = "/almCountsByProf", method = RequestMethod.POST)
	public CommonResponse almCountsByProf(
			HttpServletRequest request, HttpServletResponse response, Model model)
			throws IOException {
		return ResponseUtil.successResponse(almRecordListService.almCountsByProf());
	}

}
