
/**    
* @Title: RepairInformationController.java  
* @Package com.sgai.modules.repair.web  
* (用一句话描述该文件做什么)
* @author XJ9001  
* @date 2018年1月20日  
* @Company 首自信--智慧城市创新中心
* @version V1.0    
*/

package com.sgai.property.wy.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sgai.common.persistence.Page;
import com.sgai.common.web.BaseController;
import com.sgai.modules.login.annotation.PermessionLimit;
import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.modules.login.jwt.util.CommonResponse;
import com.sgai.modules.login.jwt.util.ResponseUtil;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.mdm.entity.MdmSpaceTree;
import com.sgai.property.mdm.service.MdmSpaceTreeService;
import com.sgai.property.wy.dto.MaintainInformationDto;
import com.sgai.property.wy.dto.RepairConst;
import com.sgai.property.wy.entity.*;
import com.sgai.property.wy.service.RepairInformationService;
import com.sgai.property.wy.service.RepairInformationTaskService;
import com.sgai.property.wy.service.SelfMotionAcceptService;
import com.sgai.property.wy.service.SelfSendOrderService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ClassName: RepairInformationController
 * (这里用一句话描述这个类的作用)
 * @author XJ9001
 * @date 2018年1月20日
 * @Company 首自信--智慧城市创新中心
 */
@RestController
@RequestMapping("/repairInformation")
public class RepairInformationController extends BaseController {

	@Autowired
	private RepairInformationService repairInformationService;

	@Autowired
	private MdmSpaceTreeService mdmSpaceTreeService;

	@Autowired
	private RepairInformationTaskService repairInformationTaskService;

	/**
	 *
	 * getLists
	 * @throws ParseException
	 */
	@RequestMapping(value = "/getLists", method = RequestMethod.POST)
	@PermessionLimit(limit = false)
	public CommonResponse getListRepairInformation(
            RepairInformation repairInformation, String startTime1, String endTime1,
            String repairNowToday,
			String alarmFlag,
            @RequestParam(value = "pageNo", required = true, defaultValue = "0") Integer pageNo,
            @RequestParam(value = "pageSize", required = true, defaultValue = "10") Integer pageSize,
            HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd ");
		SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startTime=null;
		Date endTime=null;
		if(startTime1!=null && !startTime1.equals("")){
			startTime1+=" 00:00:00";
			startTime=formatter.parse(startTime1);
		}
		if(endTime1!=null && !endTime1.equals("")){
			endTime1+=" 23:59:59";
			endTime=formatter1.parse(endTime1);

		}
		if("1".equals(alarmFlag)){
			repairInformation.setIncidentSource("4");
			repairInformation.setRepairStatus("1");
		}
		repairInformation.setStartTime(startTime);
		repairInformation.setEndTime(endTime);
		Page<RepairInformation> page = new Page<RepairInformation>();
		if ("".equals(repairNowToday) || repairNowToday == null) {
			page = repairInformationService.findPageByParam(
					new Page<RepairInformation>(pageNo, pageSize),
					repairInformation);
		} else {
			repairInformation.setRepairNowToday(new Date());
			page = repairInformationService.findPageByToday(
					new Page<RepairInformation>(pageNo, pageSize),
					repairInformation);
		}
		return ResponseUtil.successResponse(page);
	}

    /**
     *
     * getLists
     * @throws ParseException
     */
    @RequestMapping(value = "/getCheckLists", method = RequestMethod.POST)
    @PermessionLimit(limit = false)
    public CommonResponse getCheckListRepairInformation(
            RepairInformation repairInformation,
            @RequestParam(value = "pageNo", required = true, defaultValue = "0") Integer pageNo,
            @RequestParam(value = "pageSize", required = true, defaultValue = "10") Integer pageSize,
            HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, ParseException {
        Page<RepairInformation> page = repairInformationService.findCheckPageByParam(new Page<RepairInformation>(pageNo, pageSize), repairInformation);
        return ResponseUtil.successResponse(page);
    }




    /**
	 * 
	 * save 新增报修信息
	 * 
	 * @return :Map<String,Object>
	 * @since JDK 1.8
	 * @author zxj
	 * @throws IOException
	 * @throws ServletException
	 */
	@RequestMapping(value = "/saveRepair", method = RequestMethod.POST)
	public CommonResponse saveRepair(RepairInformation repairInformation)
			throws ServletException, IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		LoginUser loginUser = UserServletContext.getUserInfo();
		try {
			repairInformation.setCreatedBy(loginUser.getUserId());
			repairInformation.setLoginUserCode(loginUser.getUserId());
			repairInformationService.saveRepair(repairInformation);
			map.put("msg", "success");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", "fail");
		}
		return ResponseUtil.successResponse(map);
	}

	/**
	 * 
	 * updateRepair 修改报修信息
	 * 
	 * @return :Map<String,Object>
	 * @since JDK 1.8
	 * @author zxj
	 * @throws IOException
	 * @throws ServletException
	 */
	@RequestMapping(value = "/updateRepair", method = RequestMethod.POST)
	public CommonResponse updateRepair(RepairInformation repairInformation)
			throws ServletException, IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			repairInformationService.updateRepair(repairInformation);
			map.put("msg", "success");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", "fail");
		}
		return ResponseUtil.successResponse(map);
	}

	/**
	 * 
	 * 获取一条报修信息
	 * 
	 * @param id
	 * @return
	 * @since JDK 1.8
	 * @author zxj
	 * @throws Exception
	 */
	@RequestMapping(value = "/findRepairById", method = RequestMethod.POST)
	public CommonResponse getRepairInformation(String id) throws Exception {
		RepairInformation repairInformation = repairInformationService.get(id);
		return ResponseUtil.successResponse(repairInformation);
	}

	/**
	 * 
	 * upRepairStatus
	 * 
	 * @param id
	 * @return
	 * @since JDK 1.8
	 * @author zxj
	 * @throws Exception
	 */
	@RequestMapping(value = "/upRepairStatus", method = RequestMethod.POST)
	public CommonResponse upRepairStatus(String id, String repairStatus, // 报修状态
                                         String maintainPersonId, // 维修人ID
                                         String maintainPerson, // 维修人
                                         Date planMaintainDate, // 计划维修时间
                                         String cause, // 原因
                                         String appraiseNorm, // 评价标准
                                         HttpServletRequest request) throws Exception {
		// 获取token
		String token = request.getHeader("token");
		// 解析token
		LoginUser loginUser = UserServletContext.getUserInfo();
		Map<String, Object> map = new HashMap<String, Object>();
		RepairInformation repairInformation = new RepairInformation();
		repairInformation.setId(id);
		repairInformation.setRepairStatus(repairStatus);
		if (maintainPerson != null) {
			repairInformation.setMaintainPerson(maintainPerson);
			repairInformation.setMaintainPersonId(maintainPersonId);
			repairInformation.setPlanMaintainDate(planMaintainDate);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date today = sdf.parse(sdf.format(new Date()));
			repairInformation.setAppointDate(today);
		}
		if (cause != null) {
			repairInformation.setCause(cause);
		}
		if (appraiseNorm != null) {
			repairInformation.setAppraiseNorm(appraiseNorm);
		}
		try {
			repairInformationService.upRepairStatus(repairInformation,
					loginUser);
			map.put("msg", "success");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", "fail");
		}

		return ResponseUtil.successResponse(map);
	}

	/**
	 * 
	 * 获取区域
	 *
	 */
	@RequestMapping(value = "/getByParentCode", method = RequestMethod.POST)
	public List<MdmSpaceTree> getByParentCode(String pCode) {
		MdmSpaceTree mdmSpaceTree = new MdmSpaceTree();
		mdmSpaceTree.setSpaceCode(pCode);
		mdmSpaceTree.setComCode("bailu");
		return mdmSpaceTreeService.getByParentCode(mdmSpaceTree);
	}

	/**
	 * 
	 * 获取人员工作量
	 * 
	 * @throws ParseException
	 *
	 */
	@RequestMapping(value = "/getWorkloadList", method = RequestMethod.POST)
	public CommonResponse getWorkloadList(String personName, Date appointDate,

                                          @RequestParam(value = "pageNo", required = true, defaultValue = "0") Integer pageNo,
                                          @RequestParam(value = "pageSize", required = true, defaultValue = "10") Integer pageSize,
                                          HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, ParseException {
		MaintainInformationDto maintainInformation = new MaintainInformationDto();
		maintainInformation.setPersonName(personName);
		if (appointDate == null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date today = sdf.parse(sdf.format(new Date()));
			maintainInformation.setAppointDate(today);
		} else {
			maintainInformation.setAppointDate(appointDate);
		}
		Page<MaintainInformationDto> page = repairInformationService.findPage(
				new Page<MaintainInformationDto>(pageNo, pageSize),
				maintainInformation);
		return ResponseUtil.successResponse(page);
	}

	/**
	 * 
	 * 模糊查询会员姓名
	 * 
	 * @throws IOException
	 *
	 */
	@RequestMapping(value = "showName")
	public CommonResponse showName(String repairPeopleName) throws IOException {
		Member m = new Member();
		m.setChineseName(repairPeopleName);
		List<Member> mlist = repairInformationService.showName(m);
		return ResponseUtil.successResponse(mlist);
	}

	/**
	 * 处理退单状态
	 * 
	 * @param id
	 * @return
	 * @since JDK 1.8
	 * @author zxj
	 * @throws Exception
	 */
	@RequestMapping(value = "/manageChargeback", method = RequestMethod.POST)
	public CommonResponse manageChargeback(String id, String repairStatus, // 报修状态
                                           String maintainPersonId, // 维修人ID
                                           String maintainPersonName, // 维修人
                                           String cause, // 原因
                                           HttpServletRequest request) throws Exception {
		// 获取token
		// String token = request.getHeader("token");
		// 解析token
		// LoginUser loginUser = UserServletContext.getUserInfo();
		Map<String, Object> map = new HashMap<String, Object>();
		RepairInformation repairInformation = new RepairInformation();
		repairInformation.setId(id);
		repairInformation.setRepairStatus(repairStatus);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date today = sdf.parse(sdf.format(new Date()));
		RepairRecord repairRecord = new RepairRecord();
		repairRecord.setRepairId(id);
		repairRecord.setMaintainPersonId(maintainPersonId);
		repairRecord.setMaintainPersonName(maintainPersonName);
		repairRecord.setCause(cause);
		repairRecord.setPresentDate(today);
		try {
			repairInformationService.manageChargeback(repairInformation,
					repairRecord);
			map.put("msg", "success");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", "fail");
		}

		return ResponseUtil.successResponse(map);
	}

	/**
	 * @return
	 * @throws IOException
	 * @throws ServletException
	 */
	@RequestMapping(value = "/findById", method = RequestMethod.POST)
	public CommonResponse findById(String typeId)
			throws IOException, ServletException {
		RepairInformationType repairInformationType = repairInformationService
				.findById(typeId);
		return ResponseUtil.successResponse(repairInformationType);
	}
	/**
	 * 
	 * 获取类型
	 *
	 */
	@RequestMapping(value = "/getTypeByCode", method = RequestMethod.POST)
	public List<RepairInformationType> getTypeByCode(String pCode) {
		return repairInformationService.getTypeByCode(pCode);
	}
	/**
	 * 
	 * 获取类型分页
	 *
	 */
	@RequestMapping(value = "/getTypeList", method = RequestMethod.POST)
	public CommonResponse getTypeList(
            @RequestParam(value = "pageNo", required = true, defaultValue = "0") Integer pageNo,
            @RequestParam(value = "pageSize", required = true, defaultValue = "10") Integer pageSize,
            HttpServletRequest request, HttpServletResponse response,
            RepairInformationType repairInformationType)
			throws IOException, ServletException {
		Page<RepairInformationType> page = repairInformationService.getTypeList(
				new Page<RepairInformationType>(pageNo, pageSize),
				repairInformationType);
		return ResponseUtil.successResponse(page);
	}
	/**
	 * 
	 * 保存类型
	 *
	 */
	@RequestMapping(value = "/saveType", method = RequestMethod.POST)
	public CommonResponse saveType(RepairInformationType repairInformationType)
			throws ServletException, IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			repairInformationService.saveType(repairInformationType);
			map.put("msg", "success");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", "fail");
		}
		return ResponseUtil.successResponse(map);
	}
	/**
	 * 
	 * 修改类型
	 *
	 */
	@RequestMapping(value = "/editType", method = RequestMethod.POST)
	public CommonResponse editType(RepairInformationType repairInformationType)
			throws ServletException, IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			repairInformationService.editType(repairInformationType);
			map.put("msg", "success");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", "fail");
		}
		return ResponseUtil.successResponse(map);
	}
	/**
	 * 
	 * 删除类型
	 *
	 */
	@RequestMapping(value = "/deleteType", method = RequestMethod.POST)
	public CommonResponse deleteType(
			RepairInformationType repairInformationType)
			throws ServletException, IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1 = repairInformationService.deleteType(repairInformationType);
		if (map1.get("data") == null || "".equals(map1.get("data"))) {
			map.put("msg", "success");
		} else {
			map.put("data", map1.get("data"));
			map.put("msg", "fail");
		}
		return ResponseUtil.successResponse(map);
	}

	/**
	 * 
	 * 自动受理
	 * 
	 * @throws Exception
	 *
	 */
	@RequestMapping(value = "/selfMotionAccept", method = RequestMethod.POST)
	public CommonResponse selfMotionAccept(HttpServletRequest request,
                                           String id) throws Exception {
		SelfMotionAcceptService.initMyTimer();
		// 获取token
		String token = request.getHeader("token");
		// 解析token
		LoginUser loginUser = UserServletContext.getUserInfo();

		Map<String, Object> map = new HashMap<>();
		try {
			SelfMotionAcceptService.startMyTimer(loginUser,
					repairInformationService);
			RepairInformationTask re = new RepairInformationTask();
			re.setId(id);
			re.setTaskStatus("1");
			repairInformationTaskService.save(re);
			map.put("msg", "success");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", "fail");
		}
		return ResponseUtil.successResponse(map);
	}
	/**
	 * 
	 * 关闭自动受理
	 *
	 */
	@RequestMapping(value = "/closeSelfMotionAccept", method = RequestMethod.POST)
	public CommonResponse closeSelfMotionAccept(String id)
			throws JsonProcessingException {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			SelfMotionAcceptService.stopMyTimer();
			RepairInformationTask re = new RepairInformationTask();
			re.setId(id);
			re.setTaskStatus("0");
			repairInformationTaskService.save(re);
			map.put("msg", "success");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", "fail");
		}
		return ResponseUtil.successResponse(map);
	}

	/**
	 * 
	 * 自动派单
	 * 
	 * @throws Exception
	 *
	 */
	@RequestMapping(value = "/selfSendOrder", method = RequestMethod.POST)
	public CommonResponse selfSendOrder(HttpServletRequest request, String id)
			throws Exception {
		SelfSendOrderService.initMyTimer();
		// 获取token
		String token = request.getHeader("token");
		// 解析token
		LoginUser loginUser = UserServletContext.getUserInfo();

		Map<String, Object> map = new HashMap<String, Object>();
		try {
			SelfSendOrderService.startMyTimer(loginUser,
					repairInformationService);
			RepairInformationTask re = new RepairInformationTask();
			re.setId(id);
			re.setTaskStatus("1");
			repairInformationTaskService.save(re);
			map.put("msg", "success");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", "fail");
		}
		return ResponseUtil.successResponse(map);
	}
	/**
	 * 
	 * 关闭自动派单
	 *
	 */
	@RequestMapping(value = "/closeSelfSendOrder", method = RequestMethod.POST)
	public CommonResponse closeSelfSendOrder(String id)
			throws JsonProcessingException {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			SelfSendOrderService.stopMyTimer();
			RepairInformationTask re = new RepairInformationTask();
			re.setId(id);
			re.setTaskStatus("0");
			repairInformationTaskService.save(re);
			map.put("msg", "success");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", "fail");
		}
		return ResponseUtil.successResponse(map);

	}

	/**
	 * upEnvironmentStatus 环境验收不合格
	 * 
	 * @param id
	 * @return
	 * @since JDK 1.8
	 * @author zxj
	 * @throws Exception
	 */
	@RequestMapping(value = "/upEnvironmentStatus", method = RequestMethod.POST)
	public CommonResponse upEnvironmentStatus(String id, String repairStatus, // 报修状态
                                              String cause, // 原因
                                              String encause, // 不合格单选按钮
                                              HttpServletRequest request) throws Exception {
		// 获取token
		String token = request.getHeader("token");
		// 解析token
		LoginUser loginUser = UserServletContext.getUserInfo();
		Map<String, Object> map = new HashMap<String, Object>();
		RepairInformation repairInformation = new RepairInformation();
		repairInformation.setId(id);
		repairInformation.setRepairStatus(repairStatus);
		if (encause != null) {
			repairInformation.setCause(encause + "-" + cause);
		}
		try {
			repairInformationService.upRepairStatus(repairInformation,
					loginUser);
			map.put("msg", "success");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", "fail");
		}

		return ResponseUtil.successResponse(map);
	}

	/**
	 * 维修事件指派
	 *
	 * @return
	 * @since JDK 1.8
	 * @author zxj
	 * @throws Exception
	 */
	@RequestMapping(value = "/informationAppoint", method = RequestMethod.POST)
	public CommonResponse informationAppoint(
			RepairInformation repairInformation,
			HttpServletRequest request) throws Exception {
		// 获取token
		String token = request.getHeader("token");
		// 解析token
		LoginUser loginUser = UserServletContext.getUserInfo();
		Map<String, Object> map = new HashMap<String, Object>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date today = sdf.parse(sdf.format(new Date()));
		repairInformation.setAppointDate(today);
		try {
			repairInformationService.upRepairStatus(repairInformation,
					loginUser);
			map.put("msg", "success");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", "fail");
		}

		return ResponseUtil.successResponse(map);
	}

	/**
	 * 维修事件(已指派&&紧急时 指定维修人到场时间)
	 *
	 * @return
	 * @since JDK 1.8
	 * @author zxj
	 * @throws Exception
	 */
	@RequestMapping(value = "/upRepairShowUpDate", method = RequestMethod.POST)
	public CommonResponse upRepairShowUpDate(
			RepairInformation repairInformation,
			HttpServletRequest request) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			repairInformationService.updateRepairShowUpDate(repairInformation);
			map.put("msg", "success");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", "fail");
		}

		return ResponseUtil.successResponse(map);
	}

	/**
	 * 
	 * 维修事件受理
	 */
	@RequestMapping(value = "/acceptRepair", method = RequestMethod.POST)
	public CommonResponse acceptRepair(
			RepairInformation repairInformation, 
			HttpServletRequest request)
			throws Exception {
		// 获取token
		String token = request.getHeader("token");
		// 解析token
		LoginUser loginUser = UserServletContext.getUserInfo();
		Map<String, Object> map = new HashMap<String, Object>();
		repairInformation.setRepairStatus(RepairConst.repairAccept.getIndex());
		try {
			repairInformationService.upRepairStatus(repairInformation,
					loginUser);
			map.put("msg", "success");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", "fail");
		}
		return ResponseUtil.successResponse(map);
	}

	/**
	 * 
	 * 开始维修
	 */
	@RequestMapping(value = "/repairStart", method = RequestMethod.POST)
	public CommonResponse repairStart(
			RepairInformation repairInformation,
			HttpServletRequest request)
			throws Exception {
		// 获取token
		String token = request.getHeader("token");
		// 解析token
		LoginUser loginUser = UserServletContext.getUserInfo();
		Map<String, Object> map = new HashMap<String, Object>();
		repairInformation.setRepairStatus(RepairConst.maintainBegin.getIndex());
		try {
			repairInformationService.upRepairStatus(repairInformation,
					loginUser);
			map.put("msg", "success");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", "fail");
		}
		return ResponseUtil.successResponse(map);
	}

	/**
	 * 
	 * 维修完成
	 */
	@RequestMapping(value = "/repairFinish", method = RequestMethod.POST)
	public CommonResponse repairFinish(
			RepairInformation repairInformation, 
			HttpServletRequest request)
			throws Exception {
		// 获取token
		String token = request.getHeader("token");
		// 解析token
		LoginUser loginUser = UserServletContext.getUserInfo();
		Map<String, Object> map = new HashMap<String, Object>();
		repairInformation.setRepairStatus(RepairConst.maintainFinish.getIndex());
		try {
			repairInformationService.upRepairStatus(repairInformation,
					loginUser);
			map.put("msg", "success");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", "fail");
		}
		return ResponseUtil.successResponse(map);
	}

	/**
	 * 
	 * getTaskLists
	 */
	@RequestMapping(value = "/getTaskLists", method = RequestMethod.POST)
	@PermessionLimit(limit = false)
	public CommonResponse getTaskLists(
			@RequestParam(value = "pageNo", required = true, defaultValue = "0") Integer pageNo,
			@RequestParam(value = "pageSize", required = true, defaultValue = "10") Integer pageSize)
			throws IOException, ServletException {
		RepairInformationTask re = new RepairInformationTask();
		Page<RepairInformationTask> page = repairInformationTaskService
				.findPage(new Page<RepairInformationTask>(pageNo, pageSize),
						re);
		return ResponseUtil.successResponse(page);
	}

	@RequestMapping(value = "/export", method = RequestMethod.GET)
	@PermessionLimit(limit = false)
	public void export(HttpServletResponse response,
			RepairInformation repairInformation) throws IOException {
		repairInformationService.export(response, repairInformation);
	}
	/**
	 * @return :返回一个Map包含时间跨度
	 * @since JDK 1.8
	 * @author heibin 
	 * @throws ParseException
	 */
	@RequestMapping(value = "getDates")
	public Map<String, Object> getDates() throws ParseException {
		String today = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
		Map<String, Object> maplist = new HashMap<>();
		// 年
		Calendar yc = Calendar.getInstance();
		yc.set(Calendar.YEAR, yc.get(Calendar.YEAR) - 1);
		Date day = yc.getTime();
		String ysStr = new SimpleDateFormat("yyyy/MM/dd").format(day);
		maplist.put("b", "近一年" + "(" + ysStr + "-" + today + ")");

		// 季度
		Calendar m3c = Calendar.getInstance();
		m3c.set(Calendar.MONTH, m3c.get(Calendar.MONTH) - 3);
		Date day1 = m3c.getTime();
		String mc3Str = new SimpleDateFormat("yyyy/MM/dd").format(day1);
		maplist.put("c", "近一个季度" + "(" + mc3Str + "-" + today + ")");

		// 月
		Calendar m1c = Calendar.getInstance();
		m1c.set(Calendar.MONTH, m1c.get(Calendar.MONTH) - 1);
		Date month = m1c.getTime();
		String m1cStr = new SimpleDateFormat("yyyy/MM/dd").format(month);
		maplist.put("d", "近一个月" + "(" + m1cStr + "-" + today + ")");
		return maplist;
	}
	/**
	 * @return :返回一个Map包含周一到周日的数据
	 * @since JDK 1.8
	 * @author heibin
	 * @throws ParseException
	 */
	@RequestMapping(value = "freshReport")
	public List<RepairInformationDto> freshReport(String id,String startTime1,String endTime1,String type) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd ");
		SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String today1 = formatter1.format(new Date());
		Date startTime=null;
		Date endTime=null;
		if("e".equals(id)){
			if(startTime1!=null && !startTime1.equals("")){
				startTime1+=" 00:00:00";
				startTime=formatter.parse(startTime1);
			}
			if(endTime1!=null && !endTime1.equals("")){
				endTime1+=" 23:59:59";
				endTime=formatter1.parse(endTime1);
				
			}
		}else if(StringUtils.isNotEmpty(id)){
			endTime=formatter1.parse(today1);
		}
		if("b".equals(id)){
			// 年
			Calendar yc = Calendar.getInstance();
			yc.set(Calendar.YEAR, yc.get(Calendar.YEAR) - 1);
			Date day = yc.getTime();
			String ysStr = formatter.format(day);
	    	startTime=formatter.parse(ysStr);
	    }
		if("c".equals(id)){
			// 季度
			Calendar m3c = Calendar.getInstance();
			m3c.set(Calendar.MONTH, m3c.get(Calendar.MONTH) - 3);
			Date day1 = m3c.getTime();
			String mc3Str = formatter.format(day1);
	    	startTime=formatter.parse(mc3Str);
	    }
		if("d".equals(id)){
			// 月
			Calendar m1c = Calendar.getInstance();
			m1c.set(Calendar.MONTH, m1c.get(Calendar.MONTH) - 1);
			Date month = m1c.getTime();
			String m1cStr = formatter.format(month);
	    	startTime=formatter.parse(m1cStr);
	    }
	    RepairInformationDto repairInformationDto=new RepairInformationDto();
	    repairInformationDto.setStartTime(startTime);
	    repairInformationDto.setEndTime(endTime);
	    repairInformationDto.setType(type);
	    List<RepairInformationDto> list=repairInformationService.selectByGroup(repairInformationDto);
		return list;
	}
	/**
	 * @return :返回一个Map(事件时间状态的统计)
	 * @author cuiwenjian
	 * @throws ParseException
	 */
	@RequestMapping(value = "pieData")
	public Map<String, Object> pieData(String id,String startTime,String endTime) throws ParseException {
		Map<String, Object> maplist = new HashMap<>();
		RepairInformation repair = new RepairInformation();
		if(StringUtils.isNotEmpty(id)){
			changDate(id, repair,startTime,endTime);
			}
		// 查询所有事件
		List<RepairInformation> rlist = repairInformationService
				.findList(repair);
		//app报修
		repair.setIncidentSource("2");
		List<RepairInformation> appList = repairInformationService
				.findList(repair);
		//电话报修
		repair.setIncidentSource("1");
		List<RepairInformation> phoneList = repairInformationService
				.findList(repair);
		//日常检修报修
		repair.setIncidentSource("3");
		List<RepairInformation> dayList = repairInformationService
				.findList(repair);
		repair.setIncidentSource("4");
		List<RepairInformation> dayLists = repairInformationService
				.findList(repair);
		
	   maplist.put("app报修",appList.size());
	   maplist.put("电话报修",phoneList.size());
	   maplist.put("日常检修报修",dayList.size());
		maplist.put("报警报修",dayLists.size());
	   maplist.put("repairSum",rlist.size());
	   return maplist;
	}
	private void changDate(String id, RepairInformation repair, String startTime, String endTime) throws ParseException {
		Calendar y = Calendar.getInstance();
		Date day=null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd ");
		SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if("e".equals(id)){
			if(startTime!=null && !startTime.equals("")){
				startTime+=" 00:00:00";
				repair.setStartTime(formatter.parse(startTime));
			}
			if(endTime!=null && !endTime.equals("")){
				endTime+=" 23:59:59";
				repair.setEndTime(formatter1.parse(endTime));
				
			}
		}
		else{
		if ("a".equals(id)) {
			day = new Date();
		}
		if ("b".equals(id)) {
			y.set(Calendar.YEAR, y.get(Calendar.YEAR) - 1);
			day = y.getTime();
		}
		if ("c".equals(id)) {
			y.set(Calendar.MONTH, y.get(Calendar.MONTH) - 3);
			day = y.getTime();
		}  
		if ("d".equals(id)){
			y.set(Calendar.MONTH, y.get(Calendar.MONTH) - 1);
			day = y.getTime();
		}
			repair.setStartTime(day);
			repair.setEndTime(new Date());
		}
	}
	/**
	 * @return :返回一个list
	 * @since JDK 1.8
	 * @author heibin
	 * @throws ParseException
	 */
	@RequestMapping(value = "freshReportComplain")
	public List<RepairInformationDto> freshReportComplain(String id,String startTime1,String endTime1,String type) throws ParseException {
		/*select a.CREATED_DT,b.COMPLAIN_TIME, (b.COMPLAIN_TIME - (to_date(to_char(a.CREATED_DT,'yyyy-MM-dd hh24:mi:ss'),'yyyy-MM-dd hh24:mi:ss'))) c from WY_EVT_REPAIR_INFORMATION a LEFT JOIN WY_CUS_COMPLAIN b on a.id=b.SOURCE_KEY
		 */
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd ");
		SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String today1 = formatter1.format(new Date());
		Date startTime=null;
		Date endTime=null;
		if("e".equals(id)){
			if(startTime1!=null && !startTime1.equals("")){
				startTime1+=" 00:00:00";
				startTime=formatter.parse(startTime1);
			}
			if(endTime1!=null && !endTime1.equals("")){
				endTime1+=" 23:59:59";
				endTime=formatter1.parse(endTime1);
				
			}
		}else if(StringUtils.isNotEmpty(id)){
			endTime=formatter1.parse(today1);
		}
		if("b".equals(id)){
			// 年
			Calendar yc = Calendar.getInstance();
			yc.set(Calendar.YEAR, yc.get(Calendar.YEAR) - 1);
			Date day = yc.getTime();
			String ysStr = formatter.format(day);
	    	startTime=formatter.parse(ysStr);
	    }
		if("c".equals(id)){
			// 季度
			Calendar m3c = Calendar.getInstance();
			m3c.set(Calendar.MONTH, m3c.get(Calendar.MONTH) - 3);
			Date day1 = m3c.getTime();
			String mc3Str = formatter.format(day1);
	    	startTime=formatter.parse(mc3Str);
	    }
		if("d".equals(id)){
			// 月
			Calendar m1c = Calendar.getInstance();
			m1c.set(Calendar.MONTH, m1c.get(Calendar.MONTH) - 1);
			Date month = m1c.getTime();
			String m1cStr = formatter.format(month);
	    	startTime=formatter.parse(m1cStr);
	    }
	    RepairInformationDto repairInformationDto=new RepairInformationDto();
	    repairInformationDto.setStartTime(startTime);
	    repairInformationDto.setEndTime(endTime);
	    repairInformationDto.setType(type);
	    List<RepairInformationDto> list=repairInformationService.selectComplainList(repairInformationDto);
		return list;
	}
	/**
	 * @return :返回一个list
	 * @since JDK 1.8
	 * @author heibin
	 * @throws ParseException
	 */
	@RequestMapping(value = "freshReportAgain")
	public Map<String, Object> freshReportAgain(String id,String startTime1,String endTime1) throws ParseException {
		Map<String, Object> maplist = new HashMap<>();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd ");
		SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String today1 = formatter1.format(new Date());
		Date startTime=null;
		Date endTime=null;
		if("e".equals(id)){
			if(startTime1!=null && !startTime1.equals("")){
				startTime1+=" 00:00:00";
				startTime=formatter.parse(startTime1);
			}
			if(endTime1!=null && !endTime1.equals("")){
				endTime1+=" 23:59:59";
				endTime=formatter1.parse(endTime1);
				
			}
		}else if(StringUtils.isNotEmpty(id)){
			endTime=formatter1.parse(today1);
		}
		if("b".equals(id)){
			// 年
			Calendar yc = Calendar.getInstance();
			yc.set(Calendar.YEAR, yc.get(Calendar.YEAR) - 1);
			Date day = yc.getTime();
			String ysStr = formatter.format(day);
	    	startTime=formatter.parse(ysStr);
	    }
		if("c".equals(id)){
			// 季度
			Calendar m3c = Calendar.getInstance();
			m3c.set(Calendar.MONTH, m3c.get(Calendar.MONTH) - 3);
			Date day1 = m3c.getTime();
			String mc3Str = formatter.format(day1);
	    	startTime=formatter.parse(mc3Str);
	    }
		if("d".equals(id)){
			// 月
			Calendar m1c = Calendar.getInstance();
			m1c.set(Calendar.MONTH, m1c.get(Calendar.MONTH) - 1);
			Date month = m1c.getTime();
			String m1cStr = formatter.format(month);
	    	startTime=formatter.parse(m1cStr);
	    }
	    RepairInformationDto repairInformationDto=new RepairInformationDto();
	    repairInformationDto.setStartTime(startTime);
	    repairInformationDto.setEndTime(endTime);
	    List<RepairInformationDto> list=repairInformationService.selectAgainList(repairInformationDto);
	    List<RepairInformationDto> listOnly=repairInformationService.selectOnlyList(repairInformationDto);
	    System.out.println(listOnly);
	    int monday=0;
	    int tuesday=0;
	    int Wednesday=0;
	    int thursday=0;
	    int friday=0;
	    int saturday=0;
	    int sunday=0;
	   // List<RepairInformationDto> result=new ArrayList<RepairInformationDto>();
	    for(RepairInformationDto r:listOnly){
	    	 List<RepairInformationDto> result=new ArrayList<RepairInformationDto>();
	    	for(RepairInformationDto re:list){
	    		if(re.getRepairAddressCode().equals(r.getRepairAddressCode()) && re.getRepairTypeCode().equals(r.getRepairTypeCode())){
	    			RepairInformationDto dto=new RepairInformationDto();
	    			dto.setCreateTime(re.getCreateTime());
	    			result.add(dto);
	    		}
	    	}
	    	if(result.size()%2!=0){
	    		result.remove(0);
	    	}
	    	for(int i=0;i<result.size();i++){
	    		Date a=result.get(i).getCreateTime();
	    	    Date b=result.get(i+1).getCreateTime();
	    	    long c=(a.getTime()-b.getTime());
	    	    if (0<c && c<=1*24*60*60*1000) {
					monday++;
				} else if (1*24*60*60*1000<c && c<=7*24*60*60*1000) {
					tuesday++;
				} else if (7*24*60*60*1000<c && c<=15*24*60*60*1000) {
					Wednesday++;
				} else if (15*24*60*60*1000<c && c<=30*24*60*60*1000) {
					thursday++;
				} else if (30*24*60*60*1000<c && c<=60*24*60*60*1000) {
					friday++;
				} else if (60*24*60*60*1000<c && c<=90*24*60*60*1000) {
					saturday++;
				} else if (90<c) {
					sunday++;
				}
	    	    
	    	    i+=1;
	    	    
	    	}
	    	
	    }
	    maplist.put("monday",monday);
		maplist.put("tuesday",tuesday);
	    maplist.put("Wednesday",Wednesday);
		maplist.put("thursday",thursday);
		maplist.put("friday",friday);
	    maplist.put("saturday",saturday);
		maplist.put("sunday",sunday);
	    return maplist;
	}
	/**
	 * @return :报修目标排名
	 * @since JDK 1.8
	 * @author heibin
	 * @throws ParseException
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getRepairTargetList", method = RequestMethod.POST)
	@PermessionLimit(limit = false)
	public CommonResponse getRepairTargetList(
            RepairTargetDto repairTargetDto,

            HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		List<RepairTargetDto> list = null;
		if(!"6".equals(repairTargetDto.getType())&&!"7".equals(repairTargetDto.getType())&&!"8".equals(repairTargetDto.getType())){
			 list=repairInformationService.getRepairTargetList(repairTargetDto);
		}
		
		if("4".equals(repairTargetDto.getType())){
			List<RepairTargetDto> listResult=new ArrayList<RepairTargetDto>();
			for(RepairTargetDto r:list){
			int result=repairInformationService.getEveryBuildCount(r);
			RepairTargetDto rd =repairInformationService.getEveryBuildName(r);
			float a=Float.parseFloat(r.getCount())/result;
			RepairTargetDto d=new RepairTargetDto();
			d.setName(rd.getName());
			d.setCount(a+"");
			d.setCount1(a);
			listResult.add(d);
			}
			//ComparatorComment comparator = new ComparatorComment();  
			//Collections.sort(listResult, comparator);  
			orderByAsc(listResult);
			for(int i=10;i<listResult.size();i++){
				listResult.remove(i);
			}
			return ResponseUtil.successResponse(listResult);
		}
		if("6".equals(repairTargetDto.getType())){
			List<RepairTargetDto> listResult=new ArrayList<RepairTargetDto>();
			RepairTargetDto repairTargetDto1=new  RepairTargetDto();
			repairTargetDto1.setType("1");
			List<RepairTargetDto> list1=repairInformationService.getRepairTargetList(repairTargetDto1);
			for(RepairTargetDto rdto:list1){
				rdto.setCount("1");//借用一下
				rdto.setType(repairTargetDto.getType());
				List<RepairTargetDto> list2=repairInformationService.getNumList(rdto);
				if(list2.size()>0){
					int num=list2.size();
					long totalDate = 0;
					for(RepairTargetDto r:list2){
						long b=r.getPresentDate().getTime()-r.getCreateDate().getTime();
						totalDate+=b;
					}
					float c=((float)totalDate)/(num*1000*60);
					RepairTargetDto repairTargetDto2=new RepairTargetDto();
					repairTargetDto2.setName(rdto.getName());
					repairTargetDto2.setCount1(c);
					repairTargetDto2.setCount(c+"");
					listResult.add(repairTargetDto2);
				}
			}
			orderByAsc(listResult);
			for(int i=10;i<listResult.size();i++){
				listResult.remove(i);
			}
			return ResponseUtil.successResponse(listResult);
		}
		if("7".equals(repairTargetDto.getType())){
			List<RepairTargetDto> listResult=new ArrayList<RepairTargetDto>();
			RepairTargetDto repairTargetDto1=new  RepairTargetDto();
			repairTargetDto1.setType("1");
			List<RepairTargetDto> list1=repairInformationService.getRepairTargetList(repairTargetDto1);
			for(RepairTargetDto rdto:list1){
				rdto.setCount("1");//借用一下
				rdto.setType(repairTargetDto.getType());
				List<RepairTargetDto> list2=repairInformationService.getNumList(rdto);
			    int num=list2.size();
			    int a = Integer.parseInt(rdto.getCount());
			    float persent=((float)num/a)*100;
			    //待完善
			    if(persent>100){
			    	persent=100;
			    }
			    RepairTargetDto repairTargetDto2=new RepairTargetDto();
			    repairTargetDto2.setCount(persent+"");
			    repairTargetDto2.setCount1(persent);
			    repairTargetDto2.setName(rdto.getName());
			    listResult.add(repairTargetDto2);
			}
			orderByAsc(listResult);
			for(int i=10;i<listResult.size();i++){
				listResult.remove(i);
			}
			return ResponseUtil.successResponse(listResult);
		}
		if("8".equals(repairTargetDto.getType())){
			List<RepairTargetDto> listResult=new ArrayList<RepairTargetDto>();
			RepairTargetDto repairTargetDto1=new  RepairTargetDto();
			repairTargetDto1.setType("1");
			List<RepairTargetDto> list1=repairInformationService.getRepairTargetList(repairTargetDto1);
			for(RepairTargetDto rdto:list1){
				rdto.setType("1");
				int num=repairInformationService.getComplainNum(rdto);
				int a = Integer.parseInt(rdto.getCount());
				float persent=((float)num/a)*100;
			    RepairTargetDto repairTargetDto2=new RepairTargetDto();
			    repairTargetDto2.setCount(persent+"");
			    repairTargetDto2.setCount1(persent);
			    repairTargetDto2.setName(rdto.getName());
			    listResult.add(repairTargetDto2);
			}
			orderByAsc(listResult);
			for(int i=10;i<listResult.size();i++){
				listResult.remove(i);
			}
			return ResponseUtil.successResponse(listResult);
			
		}
		return ResponseUtil.successResponse(list);
		
	}
	/**
	 * @return :报修类目排名
	 * @since JDK 1.8
	 * @author heibin
	 * @throws ParseException
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getRepairCategoryList", method = RequestMethod.POST)
	@PermessionLimit(limit = false)
	public CommonResponse getRepairCategoryList(
            RepairCategoryDto repairCategoryDto, String startTime1, String endTime1,

            HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd ");
		SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String today1 = formatter1.format(new Date());
		Date startTime=null;
		Date endTime=null;
		if("e".equals(repairCategoryDto.getDynamicTimeType())){
			if(startTime1!=null && !startTime1.equals("")){
				startTime1+=" 00:00:00";
				startTime=formatter.parse(startTime1);
			}
			if(endTime1!=null && !endTime1.equals("")){
				endTime1+=" 23:59:59";
				endTime=formatter1.parse(endTime1);
				
			}
		}else if(StringUtils.isNotEmpty(repairCategoryDto.getDynamicTimeType())){
			endTime=formatter1.parse(today1);
		}
		if("b".equals(repairCategoryDto.getDynamicTimeType())){
			// 年
			Calendar yc = Calendar.getInstance();
			yc.set(Calendar.YEAR, yc.get(Calendar.YEAR) - 1);
			Date day = yc.getTime();
			String ysStr = formatter.format(day);
	    	startTime=formatter.parse(ysStr);
	    }
		if("c".equals(repairCategoryDto.getDynamicTimeType())){
			// 季度
			Calendar m3c = Calendar.getInstance();
			m3c.set(Calendar.MONTH, m3c.get(Calendar.MONTH) - 3);
			Date day1 = m3c.getTime();
			String mc3Str = formatter.format(day1);
	    	startTime=formatter.parse(mc3Str);
	    }
		if("d".equals(repairCategoryDto.getDynamicTimeType())){
			// 月
			Calendar m1c = Calendar.getInstance();
			m1c.set(Calendar.MONTH, m1c.get(Calendar.MONTH) - 1);
			Date month = m1c.getTime();
			String m1cStr = formatter.format(month);
	    	startTime=formatter.parse(m1cStr);
	    }
		List<RepairCategoryDto> list = null;
		if(!"6".equals(repairCategoryDto.getType())&&!"7".equals(repairCategoryDto.getType())&&!"8".equals(repairCategoryDto.getType())){
		if("1".equals(repairCategoryDto.getDynamicCategory())){
			repairCategoryDto.setStartTime(startTime);
			repairCategoryDto.setEndTime(endTime);
			list=repairInformationService.getRepairPartList(repairCategoryDto);//报修部位
		}else if("2".equals(repairCategoryDto.getDynamicCategory())){
			repairCategoryDto.setStartTime(startTime);
			repairCategoryDto.setEndTime(endTime);
			list=repairInformationService.getRepairClassList(repairCategoryDto);//报修分类
		}
		}
		if("4".equals(repairCategoryDto.getType())){
			List<RepairCategoryDto> listResult=new ArrayList<RepairCategoryDto>();
			for(RepairCategoryDto r:list){
			RepairTargetDto	r1=new RepairTargetDto();
			r1.setName(r.getName());
			r1.setType(repairCategoryDto.getDynamicCategory());
			r1.setStartTime(startTime);
			r1.setEndTime(endTime);
			r1.setType2(repairCategoryDto.getType2());
			int result=repairInformationService.getEveryCategoryCount(r1);
			float a=Float.parseFloat(r.getCount())/result;
			RepairCategoryDto d=new RepairCategoryDto();
			d.setName(r.getName1());
			d.setCount(a+"");
			d.setCount1(a);
			d.setParentName(r.getParentName());
			listResult.add(d);
			}
			orderByAsc1(listResult);
			for(int i=10;i<listResult.size();i++){
				listResult.remove(i);
			}
			return ResponseUtil.successResponse(listResult);
		}
		if("8".equals(repairCategoryDto.getType())){
			List<RepairCategoryDto> listResult=new ArrayList<RepairCategoryDto>();
			RepairCategoryDto repairTargetDto1=new  RepairCategoryDto();
			repairTargetDto1.setType("1");
			repairTargetDto1.setStartTime(startTime);
			repairTargetDto1.setEndTime(endTime);
			List<RepairCategoryDto> list1 = null;
			if("1".equals(repairCategoryDto.getDynamicCategory())){
				list1=repairInformationService.getRepairPartList(repairTargetDto1);//报修部位
			}else if("2".equals(repairCategoryDto.getDynamicCategory())){
				list1=repairInformationService.getRepairClassList(repairTargetDto1);//报修分类
			}
			for(RepairCategoryDto rdto:list1){
				RepairTargetDto rdto1=new RepairTargetDto();
				rdto1.setType("2");
				rdto1.setName(rdto.getName());
				rdto1.setStartTime(startTime);
				rdto1.setEndTime(endTime);
				int num=repairInformationService.getComplainNum(rdto1);
				int a = Integer.parseInt(rdto.getCount());
				float persent=((float)num/a)*100;
				RepairCategoryDto repairTargetDto2=new RepairCategoryDto();
			    repairTargetDto2.setCount(persent+"");
			    repairTargetDto2.setCount1(persent);
			    repairTargetDto2.setName(rdto.getName());
			    listResult.add(repairTargetDto2);
			}
			orderByAsc1(listResult);
			for(int i=10;i<listResult.size();i++){
				listResult.remove(i);
			}
			return ResponseUtil.successResponse(listResult);
			
			
			
		}
		if("7".equals(repairCategoryDto.getType())){
			List<RepairCategoryDto> listResult=new ArrayList<RepairCategoryDto>();
			RepairCategoryDto repairTargetDto1=new  RepairCategoryDto();
			repairTargetDto1.setType("1");
			repairTargetDto1.setStartTime(startTime);
			repairTargetDto1.setEndTime(endTime);
			repairTargetDto1.setType2(repairCategoryDto.getType2());
			List<RepairCategoryDto> list1 = null;
			if("1".equals(repairCategoryDto.getDynamicCategory())){
				list1=repairInformationService.getRepairPartList(repairTargetDto1);//报修部位
			}else if("2".equals(repairCategoryDto.getDynamicCategory())){
				list1=repairInformationService.getRepairClassList(repairTargetDto1);//报修分类
			}
			for(RepairCategoryDto rdto:list1){
				RepairTargetDto rdto1=new RepairTargetDto();
				rdto1.setCount("2");//借用一下
				rdto1.setName(rdto.getName());
				rdto1.setType(repairCategoryDto.getType());
				rdto1.setStartTime(startTime);
				rdto1.setEndTime(endTime);
				rdto1.setType2(repairCategoryDto.getType2());
				List<RepairTargetDto> list2=repairInformationService.getNumList(rdto1);
			    int num=list2.size();
			    int a = Integer.parseInt(rdto.getCount());
			    float persent=((float)num/a)*100;
			    //待完善
			    if(persent>100){
			    	persent=100;
			    }
			    RepairCategoryDto repairTargetDto2=new RepairCategoryDto();
			    repairTargetDto2.setCount(persent+"");
			    repairTargetDto2.setCount1(persent);
			    repairTargetDto2.setName(rdto.getName());
			    repairTargetDto2.setParentName(rdto.getParentName());
			    listResult.add(repairTargetDto2);
			}
			orderByAsc1(listResult);
			for(int i=10;i<listResult.size();i++){
				listResult.remove(i);
			}
			return ResponseUtil.successResponse(listResult);
		}
		if("6".equals(repairCategoryDto.getType())){
			List<RepairCategoryDto> listResult=new ArrayList<RepairCategoryDto>();
			RepairCategoryDto repairTargetDto1=new  RepairCategoryDto();
			repairTargetDto1.setType("1");
			repairTargetDto1.setStartTime(startTime);
			repairTargetDto1.setEndTime(endTime);
			repairTargetDto1.setType2(repairCategoryDto.getType2());
			List<RepairCategoryDto> list1 = null;
			if("1".equals(repairCategoryDto.getDynamicCategory())){
				list1=repairInformationService.getRepairPartList(repairTargetDto1);//报修部位
			}else if("2".equals(repairCategoryDto.getDynamicCategory())){
				list1=repairInformationService.getRepairClassList(repairTargetDto1);//报修分类
			}
			for(RepairCategoryDto rdto:list1){
				RepairTargetDto rdto1=new RepairTargetDto();
				rdto1.setCount("2");//借用一下
				rdto1.setName(rdto.getName());
				rdto1.setType(repairCategoryDto.getType());
				rdto1.setStartTime(startTime);
				rdto1.setEndTime(endTime);
				rdto1.setType2(repairCategoryDto.getType2());
				List<RepairTargetDto> list2=repairInformationService.getNumList(rdto1);
				if(list2.size()>0){
					int num=list2.size();
					long totalDate = 0;
					for(RepairTargetDto r:list2){
						long b=r.getPresentDate().getTime()-r.getCreateDate().getTime();
						totalDate+=b;
					}
					float c=((float)totalDate)/(num*1000*60);
					 RepairCategoryDto repairTargetDto2=new RepairCategoryDto();
					 repairTargetDto2.setCount(c+"");
					 repairTargetDto2.setCount1(c);
					 repairTargetDto2.setName(rdto.getName());
					 repairTargetDto2.setParentName(rdto.getParentName());
					 listResult.add(repairTargetDto2);
				}
			}
			orderByAsc1(listResult);
			for(int i=10;i<listResult.size();i++){
				listResult.remove(i);
			}
			return ResponseUtil.successResponse(listResult);
		}
		return ResponseUtil.successResponse(list);
			
		}
	
	/**  
	    * @ClassName: ComparatorComment  
	    * (list 按照某一个元素倒序排序)
	    * @author heibin 
	    * @date 2018年6月11日  
	    * @Company 首自信--智慧城市创新中心  
	    */
    private List<RepairTargetDto> orderByAsc(List<RepairTargetDto> listResult){  
        
        
        for (int i = 0; i < listResult.size() - 1; i++) {  
            for (int j = 1; j < listResult.size() - i; j++) {  
                RepairTargetDto a;  
                if ((listResult.get(j - 1).getCount1())-(listResult.get(j).getCount1()) < 0) {  
      
                    a = listResult.get(j - 1);  
                    listResult.set((j - 1), listResult.get(j));  
                    listResult.set(j, a);  
                }  
            }  
        }  
        return listResult;  
    }  
  private List<RepairCategoryDto> orderByAsc1(List<RepairCategoryDto> listResult){  
        
        
        for (int i = 0; i < listResult.size() - 1; i++) {  
            for (int j = 1; j < listResult.size() - i; j++) {  
            	RepairCategoryDto a;  
                if ((listResult.get(j - 1).getCount1())-(listResult.get(j).getCount1()) < 0) {  
      
                    a = listResult.get(j - 1);  
                    listResult.set((j - 1), listResult.get(j));  
                    listResult.set(j, a);  
                }  
            }  
        }  
        return listResult;  
    } 
  /**
	 * @return :投诉高级分析 ---旭日图
	 * @since JDK 1.8
	 * @author heibin
	 * @throws ParseException
	 */
  @SuppressWarnings("unchecked")
	@RequestMapping(value = "/queryData", method = RequestMethod.POST)
	@PermessionLimit(limit = false)
	public CommonResponse queryData(
          RepairCategoryDto repairCategoryDto, String startTime1, String endTime1,

          HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, ParseException {
	  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd ");
		SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String today1 = formatter1.format(new Date());
		Date startTime=null;
		Date endTime=null;
		if("e".equals(repairCategoryDto.getDynamicTimeType())){
			if(startTime1!=null && !startTime1.equals("")){
				startTime1+=" 00:00:00";
				startTime=formatter.parse(startTime1);
			}
			if(endTime1!=null && !endTime1.equals("")){
				endTime1+=" 23:59:59";
				endTime=formatter1.parse(endTime1);
				
			}
		}else if(StringUtils.isNotEmpty(repairCategoryDto.getDynamicTimeType())){
			endTime=formatter1.parse(today1);
		}
		if("b".equals(repairCategoryDto.getDynamicTimeType())){
			// 年
			Calendar yc = Calendar.getInstance();
			yc.set(Calendar.YEAR, yc.get(Calendar.YEAR) - 1);
			Date day = yc.getTime();
			String ysStr = formatter.format(day);
	    	startTime=formatter.parse(ysStr);
	    }
		if("c".equals(repairCategoryDto.getDynamicTimeType())){
			// 季度
			Calendar m3c = Calendar.getInstance();
			m3c.set(Calendar.MONTH, m3c.get(Calendar.MONTH) - 3);
			Date day1 = m3c.getTime();
			String mc3Str = formatter.format(day1);
	    	startTime=formatter.parse(mc3Str);
	    }
		if("d".equals(repairCategoryDto.getDynamicTimeType())){
			// 月
			Calendar m1c = Calendar.getInstance();
			m1c.set(Calendar.MONTH, m1c.get(Calendar.MONTH) - 1);
			Date month = m1c.getTime();
			String m1cStr = formatter.format(month);
	    	startTime=formatter.parse(m1cStr);
	    }
		if("a".equals(repairCategoryDto.getDynamicTimeType())){
			// 天
			String today2 = formatter.format(new Date());
			startTime=formatter.parse(today2);
	    }
		repairCategoryDto.setStartTime(startTime);
		repairCategoryDto.setEndTime(endTime);
	  List<RepairCategoryDto> classlist=repairInformationService.getRepairClassList(repairCategoryDto);//报修分类
	  List<RepairCategoryDto> partlist=repairInformationService.getRepairPartList(repairCategoryDto);//报修部位
	List<RepairInformationResult> result=new ArrayList<RepairInformationResult>();
	  for(RepairCategoryDto r:classlist){
		  RepairInformationResult rr=new RepairInformationResult();
		  rr.setName(r.getName());
		  rr.setCount(r.getCount());
		  List<RepairInformationResult> result1=new ArrayList<RepairInformationResult>();
		  for(RepairCategoryDto d:partlist){
			  if(d.getParentCode().equals(r.getTypeId())){
				  RepairInformationResult i=new RepairInformationResult();
				  i.setName(d.getName());
				  i.setCount(d.getCount());
				  result1.add(i);
			  }
		  }
		  rr.setList(result1);
		  result.add(rr);
		  
	  }
	  return ResponseUtil.successResponse(result);
  }

	/**
	 * 查询实时报警项
	 * getLists
	 */
	@RequestMapping(value = "/getAlarmLists", method = RequestMethod.POST)
	@PermessionLimit(limit = false)
	public CommonResponse getAlarmLists(
			@RequestParam(value = "pageNo", required = true, defaultValue = "0") Integer pageNo,
			@RequestParam(value = "pageSize", required = true, defaultValue = "10") Integer pageSize,
			HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		RepairInformation re = new RepairInformation();
		re.setIncidentSource("4");
		re.setRepairStatus("1");
		Page<RepairInformation> page = repairInformationService.findPage(new Page<RepairInformation>(pageNo,pageSize),re);
		return ResponseUtil.successResponse(page);
	}

}
