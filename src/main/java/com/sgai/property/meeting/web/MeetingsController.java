package com.sgai.property.meeting.web;

import com.sgai.common.persistence.Page;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.common.exception.BusinessException;
import com.sgai.property.common.exception.ReturnType;
import com.sgai.property.common.service.BaseSgaiOrgTreeService;
import com.sgai.property.common.service.BaseSpaceService;
import com.sgai.property.common.web.BaseController;
import com.sgai.property.commonService.dto.SpaceDto;
import com.sgai.property.commonService.entity.DictGeneral;
import com.sgai.property.commonService.vo.*;
import com.sgai.property.ctl.entity.CtlEmp;
import com.sgai.property.meeting.entity.Inviter;
import com.sgai.property.meeting.entity.Maininfo;
import com.sgai.property.meeting.service.InviterServiceImpl;
import com.sgai.property.meeting.service.MaininfoServiceImpl;
import com.sgai.property.meeting.service.MeetingEmpInfoServiceImpl;
import com.sgai.property.meeting.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;


/**
 * 会议应用控制类
 * ClassName: MeetingsController
 *
 * @author zhangxiang3
 * @Description :
 * @date: 2017年11月9日 上午10:47:16
 */
@RestController
@RequestMapping(value = "/meetings")
@Api(description = "会议")
public class MeetingsController extends BaseController {


    @Autowired
    private MaininfoServiceImpl maininfoService;
    @Autowired
    private BaseSgaiOrgTreeService baseSgaiOrgTreeService;
    @Autowired
    private BaseSpaceService baseSpaceService;
    private static final Logger logger = LogManager.getLogger(MeetingsController.class);


    //接受会议
    @Value("${mt.ACCEPT_SUCCESS_MSG}")
    private String ACCEPT_SUCCESS_MSG;
    @Value("${mt.ACCEPT_INVALID_MSG}")
    private String ACCEPT_INVALID_MSG;
    @Value("${mt.ACCEPT_REPEAT_MSG}")
    private String ACCEPT_REPEAT_MSG;

    //拒绝会议
    @Value("${mt.REFUSE_SUCCESS_MSG}")
    private String REFUSE_SUCCESS_MSG;
    @Value("${mt.REFUSE_INVALID_MSG}")
    private String REFUSE_INVALID_MSG;
    @Value("${mt.REFUSE_REPEAT_MSG}")
    private String REFUSE_REPEAT_MSG;


    @Autowired
    private InviterServiceImpl inviterService;

    @Autowired
    private MeetingEmpInfoServiceImpl empInfoService;

    @ApiOperation(value = "开启提醒操作列表", httpMethod = "POST", notes = "开启提醒操作列表")
    @RequestMapping(value = "/getRemindMins", method = RequestMethod.POST)
    public Response<List<DictGeneral>> getRemindMins() {
        Response<List<DictGeneral>> result = new Response<>();
        result.setData(maininfoService.getRemindMins());
        return result;
    }

    @ApiOperation(value = "PC端发起会议保存接口/修改保存接口", httpMethod = "POST", notes = "PC端发起会议保存接口/修改保存接口")
    @RequestMapping(value = "/saveMeetingPC", method = RequestMethod.POST)
    public Response<Boolean> saveMeetingPC(@RequestBody MaininfoPCVo maininfoPCVo, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Response<Boolean> result = new Response<>();
        if (maininfoPCVo == null || "".equals(maininfoPCVo.getMiMtSubject())) {
            throw new BusinessException(ReturnType.ParamIllegal, "参数非法");
        }
        result.setData(maininfoService.saveMeetingPC(maininfoPCVo, request, response));
        return result;
    }

    @ApiOperation(value = "PC端会议室选择", httpMethod = "POST", notes = "PC端会议室选择")
    @RequestMapping(value = "/selectMeetingRoomPC", method = RequestMethod.POST)
    public Response<Page<RoomResourceVo>> selectMeetingRoomPC(
            @RequestParam(name = "currtDate") Long currtDate,
            @RequestParam(name = "timeSeg") String timeSeg,
            @RequestParam(name = "pageNo") int pageNo,
            @RequestParam(name = "pageSize") int pageSize) {
        Response<Page<RoomResourceVo>> result = new Response<Page<RoomResourceVo>>();

        if (pageNo == 0) {
            pageNo = 1;
        }
        if (pageSize == 0) {
            pageSize = 10;
        }
        result.setData(maininfoService.selectMeetingRoomPC(currtDate, timeSeg, pageNo, pageSize));
        return result;
    }

    @ApiOperation(value = "会议查询列表", notes = "会议查询列表")
    @RequestMapping(value = "/getAllMeetingsList", method = RequestMethod.POST)
    public Response<List<MeetingsListDto>> getMeetingList(@RequestParam("currtDate") Long currtDate) throws Exception {
        Response<List<MeetingsListDto>> response = new Response<List<MeetingsListDto>>();
        response.setData(maininfoService.getAllMeetingsList(currtDate));
        return response;
    }


    @ApiOperation(value = "获取当周状态接口", httpMethod = "POST", notes = "获取当周状态接口")
    @RequestMapping(value = "/getCurrtWeekStatus", method = RequestMethod.POST)
    public Response<List<CurrtDaysStatusDto>> getCurrtWeekStatus(@RequestParam("currtDays") List<Long> currtDays) throws Exception {
        Response<List<CurrtDaysStatusDto>> result = new Response<List<CurrtDaysStatusDto>>();
        result.setData(maininfoService.getCurrtWeekStatus(currtDays));
        return result;
    }


    @ApiOperation(value = "获取时间段状态接口", httpMethod = "POST", notes = "获取时间段状态接口")
    @RequestMapping(value = "/getTimeSegStatus", method = RequestMethod.POST)
    public Response<List<TimeSegStatusDto>> getTimeSegStatus(
            @RequestParam("currtDate") Long currtDate,
            @RequestParam(required = false, name = "rrId") String rrId,
            @RequestParam(required = false, name = "miId") String miId) throws Exception {
        Response<List<TimeSegStatusDto>> result = new Response<List<TimeSegStatusDto>>();
        result.setData(maininfoService.getTimeSegStatus(currtDate, rrId, miId));
        return result;
    }


    @ApiOperation(value = "发起会议保存接口/修改保存接口", httpMethod = "POST", notes = "发起会议保存接口/修改保存接口")
    @RequestMapping(value = "/saveMeeting", method = RequestMethod.POST)
    public Response<Boolean> saveMeeting(@RequestBody MaininfoVo maininfoVo) throws Exception {
        Response<Boolean> result = new Response<>();
        if (maininfoVo == null || "".equals(maininfoVo.getMiMtSubject())) {
            throw new BusinessException(ReturnType.ParamIllegal, "参数非法");
        }
        result.setData(maininfoService.saveMeeting(maininfoVo));
        return result;
    }


    @ApiOperation(value = "修改会议信息接口", httpMethod = "POST", notes = "修改会议信息接口")
    @RequestMapping(value = "/editMeeting", method = RequestMethod.POST)
    public Response<MeetingInfoDto> editMeeting(@RequestParam("id") String miId) throws Exception {
        Response<MeetingInfoDto> result = new Response<MeetingInfoDto>();
        result.setData(maininfoService.editMeeting(miId));
        return result;
    }


    @ApiOperation(value = "时间段确定接口", httpMethod = "POST", notes = "时间段确定接口")
    @RequestMapping(value = "/confirmTimeSeg", method = RequestMethod.POST)
    public Response<Boolean> confirmTimeSeg(@RequestParam("currtDate") Long currtDate,
                                            @RequestParam("timeSeg") String timeSeg, @RequestParam(required = false, name = "rrId") String rrId) throws Exception {
        Response<Boolean> result = new Response<Boolean>();
        Maininfo maininfo = new Maininfo();
        maininfo.setMiMtDate(currtDate);
        maininfo.setMiMtTimeSeg(timeSeg);
        maininfo.setRrId(rrId);
        result.setData(maininfoService.confirmTimeSeg(maininfo));
        return result;
    }


    @ApiOperation(value = "取消会议操作", httpMethod = "POST", notes = "取消会议操作")
    @RequestMapping(value = "/cancelMeeting", method = RequestMethod.POST)
    public Response<Boolean> cancelMeeting(@RequestParam("id") String miId) throws Exception {
        Response<Boolean> result = new Response<Boolean>();
        result.setData(maininfoService.cancelMeeting(miId));
        return result;
    }

    @ApiOperation(value = "结束会议操作", httpMethod = "POST", notes = "结束会议操作")
    @RequestMapping(value = "/finishMeeting", method = RequestMethod.POST)
    public Response<Boolean> finishMeeting(@RequestParam("id") String miId) throws Exception {
        Response<Boolean> result = new Response<Boolean>();
        result.setData(maininfoService.finishMeeting(miId));
        return result;
    }


    @ApiOperation(value = "获取会议详情", httpMethod = "POST", notes = "获取会议详情")
    @RequestMapping(value = "/getMeetingDetail", method = RequestMethod.POST)
    public Response<MeetingDetailDto> getMeetingDetail(@RequestParam("id") String miId) throws Exception {
        Response<MeetingDetailDto> result = new Response<MeetingDetailDto>();
        result.setData(maininfoService.getMeetingDetail(miId));
        return result;
    }


    @ApiOperation(value = "参加操作", httpMethod = "POST", notes = "参加操作")
    @RequestMapping(value = "/inviteMeeting", method = RequestMethod.POST)
    public Response<Boolean> inviteMeeting(@RequestParam("miId") String miId) throws Exception {
        Response<Boolean> result = new Response<Boolean>();
        result.setData(maininfoService.inviteMeeting(miId));
        return result;
    }


    @ApiOperation(value = "请假操作", httpMethod = "POST", notes = "参加/请假操作")
    @RequestMapping(value = "/leaveMeeting", method = RequestMethod.POST)
    public Response<Boolean> leaveMeeting(@RequestParam("miId") String miId,
                                          @RequestParam("leaveReason") String leaveReason) throws Exception {
        Response<Boolean> result = new Response<Boolean>();
        result.setData(maininfoService.leaveMeeting(miId, leaveReason));
        return result;
    }

    @ApiOperation(value = "派遣操作", httpMethod = "POST", notes = "派遣操作")
    @RequestMapping(value = "/send", method = RequestMethod.POST)
    public Response<Boolean> send(@RequestParam("miId") String miId,
                                  @RequestParam("eiIds") List<String> eiIds) {
        Response<Boolean> result = new Response<Boolean>();
        result.setData(maininfoService.send(miId, eiIds));
        return result;
    }

    @ApiOperation(value = "新建会议纪要", httpMethod = "POST", notes = "新建会议纪要")
    @RequestMapping(value = "/saveSummary", method = RequestMethod.POST)
    public Response<Boolean> saveSummary(@RequestBody SummaryVo summaryVo) throws Exception {
        Response<Boolean> result = new Response<Boolean>();
        result.setData(maininfoService.saveSummary(summaryVo));
        return result;
    }


    @ApiOperation(value = "会议室选择", httpMethod = "POST", notes = "会议室选择")
    @RequestMapping(value = "/selectMeetingRoom", method = RequestMethod.POST)
    public Response<List<MeetingRoomListDto>> selectMeetingRoom(@RequestParam(name = "rpId", required = false) String rpId,
                                                                @RequestParam(name = "currtDate", required = false) Long currtDate,
                                                                @RequestParam(name = "timeSeg", required = false) String timeSeg,
                                                                @RequestParam(name = "miId", required = false) String miId) throws Exception {
        Response<List<MeetingRoomListDto>> result = new Response<List<MeetingRoomListDto>>();
        result.setData(maininfoService.selectMeetingRoom(rpId, currtDate, timeSeg, miId));
        return result;
    }

    @ApiOperation(value = "会议室选择App", httpMethod = "POST", notes = "会议室选择App")
    @RequestMapping(value = "/selectMeetingRoomBL", method = RequestMethod.POST)
    public Response<List<MeetingRoomListDto>> selectMeetingRoomBL(
            @RequestParam(name = "currtDate", required = false) Long currtDate,
            @RequestParam(name = "timeSeg", required = false) String timeSeg,
            @RequestParam(name = "miId", required = false) String miId) throws Exception {
        Response<List<MeetingRoomListDto>> result = new Response<List<MeetingRoomListDto>>();
        result.setData(maininfoService.selectMeetingRoomBL(currtDate, timeSeg, miId));
        return result;
    }

    /**
     * 邮件中调用请假邀请会议
     *
     * @param meetingId,meetingInviter
     * @return
     */
    @ApiOperation(value = "邮件中参加邀请会议", notes = "邮件中参加邀请会议")
    @RequestMapping(value = "/meetingInviterAccept", method = RequestMethod.GET)
    public void meetingInviterAccept(String meetingId, String meetingInviter, HttpServletResponse response) {
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            Maininfo meeting = new Maininfo();
            meeting.setId(meetingId);
            Maininfo resMeeting = maininfoService.get(meeting);
            if (null == resMeeting || (1 != resMeeting.getMiStatus()) || 1 == resMeeting.getIsDelete()) {
                out.print(ACCEPT_INVALID_MSG);
                return;
            }
            Date meetingEndTime = maininfoService.getMeetingEndtime(meetingId);
            Date systemDateTime = new Date();
            if (systemDateTime.getTime() > meetingEndTime.getTime()) {
                out.print(ACCEPT_INVALID_MSG);
                return;
            }
            Inviter bean = new Inviter();
            bean.setMiId(meetingId);
            bean.setInviterEiId(meetingInviter);
            Inviter resMmeetingInviter = inviterService.get(bean);
            if (resMmeetingInviter == null) {
                out.print(REFUSE_INVALID_MSG);
                return;
            }
            if (1 == resMmeetingInviter.getIsInvite().intValue() || 3 == resMmeetingInviter.getIsInvite().intValue()) {
                out.print(ACCEPT_REPEAT_MSG);
                return;
            }
            boolean flag = maininfoService.inviteMeetingFromEmail(meetingId, meetingInviter);
            if (flag) {
                out.print(ACCEPT_SUCCESS_MSG);
            } else {
                throw new BusinessException(ReturnType.Error, "参加会议异常");
            }

        } catch (Exception e) {
            logger.error("参加会议异常", e);
        } finally {
            if (null != out) {
                out.flush();
                out.close();
            }
        }
    }

    /**
     * 邮件中调用请假邀请会议
     *
     * @param meetingId ,meetingInviter
     * @return
     */
    @ApiOperation(value = "邮件中调用请假邀请会议", notes = "邮件中调用请假邀请会议")
    @RequestMapping(value = "/meetingInviterRefuse", method = RequestMethod.GET)
    public void meetingInviterRefuse(String meetingId, String meetingInviter, HttpServletResponse response) {
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            Maininfo meeting = new Maininfo();
            meeting.setId(meetingId);
            Maininfo resMeeting = maininfoService.get(meeting);
            if (null == resMeeting || 1 != resMeeting.getMiStatus() || 1 == resMeeting.getIsDelete()) {
                out.print(REFUSE_INVALID_MSG);
                return;
            }
            Date meetingEndTime = maininfoService.getMeetingEndtime(meetingId);
            Date systemDateTime = new Date();
            if (systemDateTime.getTime() > meetingEndTime.getTime()) {
                out.print(REFUSE_INVALID_MSG);
                return;
            }
            Inviter bean = new Inviter();
            bean.setMiId(meetingId);
            bean.setInviterEiId(meetingInviter);
            Inviter resMmeetingInviter = inviterService.get(bean);
            if (resMmeetingInviter == null) {
                out.print(REFUSE_INVALID_MSG);
                return;
            }
            if (0 != resMmeetingInviter.getIsInvite().intValue()) {
                out.print(REFUSE_REPEAT_MSG);
                return;
            }
            boolean flag = maininfoService.leaveMeetingFromEmail(meetingId, meetingInviter);
            if (flag) {
                out.print(REFUSE_SUCCESS_MSG);
            } else {
                throw new BusinessException(ReturnType.Error, "请假会议异常");
            }
        } catch (Exception e) {
            logger.error("请假会议异常", e);
        } finally {
            if (null != out) {
                out.flush();
                out.close();
            }
        }
    }


    @ApiOperation(value = "获取部门Id获取子部门集合信息", notes = "获取部门Id获取子部门集合信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "deptId", value = "-1:根部门,0:获取一级部门", required = true, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "isIncludeEmp", value = "是否查询员工0:不是1是 ", required = false, paramType = "query", dataType = "Integer"),
    })
    @PostMapping(value = "/getOrgTreeById")
    public Response<List<OrgTreeNode>> getOrgTreeById(@RequestParam("deptId") Long deptId,
                                                      @RequestParam(required = false) Integer isIncludeEmp) {
        Response<List<OrgTreeNode>> result = new Response<>();


        result.setData(empInfoService.getOrgTreeById(UserServletContext.getUserInfo().getCompanyId(), deptId, isIncludeEmp));
        return result;
    }


    @ApiOperation(value = "根据用户名称关键字搜索组织树", notes = "根据用户名称关键字搜索组织树")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "keyword", value = "搜索用户关键字", required = true, paramType = "query", dataType = "String")
    })
    @PostMapping(value = "/searchOrgTree")
    public Response<List<OrgTreeNode>> searchOrgTree(@RequestParam("keyword") String keyword) {
        Response<List<OrgTreeNode>> result = new Response<>();


        result.setData(empInfoService.searchOrgTree(UserServletContext.getUserInfo().getCompanyId(), keyword));
        return result;
    }

    @ApiOperation(value = "根据用户名称搜索用户信息", httpMethod = "POST", notes = "根据用户名称搜索用户信息")
    @RequestMapping(value = "/searchEmpInfoByName", method = RequestMethod.POST)
    public Response<EmpSearchResult> searchEmpInfoByName(@RequestParam("keyword") String keyword) {

        return empInfoService.searchSgaiEmpInfoByName( keyword, 1, 10);
    }


    @ApiOperation(value = "根据部门id集合返回前三条数据", notes = "根据部门id集合返回前三条数据")
    @RequestMapping(value = "/findEmpInfoByDeptId", method = RequestMethod.POST)
    public Response<List<EmpSimpleInfoVo>> findEmpInfoByDeptId(@RequestParam("deptIds") List<Long> deptIds) {
        Response<List<EmpSimpleInfoVo>> result = new Response<List<EmpSimpleInfoVo>>();
        result.setData(maininfoService.findEmpInfoByDeptId(deptIds));
        return result;
    }

    @ApiOperation(value = "根据部门id返回人员数据", notes = "根据部门id返回人员数据")
    @RequestMapping(value = "/getEmpInfosByDeptId", method = RequestMethod.POST)
    public Response<List<CtlEmp>> getEmpInfosByDeptId(@RequestParam("deptId") String deptId) {


        Response<List<CtlEmp>> response = new Response<>();
        Page<CtlEmp> page = baseSgaiOrgTreeService.getSgaiEmp(1, 10, deptId, "", "");
        response.setData(page.getList());
        return response;
    }


    @ApiOperation(value = "定时任务更新会议状态接口", httpMethod = "GET", notes = "定时任务更新会议状态接口")
    @RequestMapping(value = "/schedulerUpdateMeetingState", method = RequestMethod.GET)
    public Response<Boolean> schedulerUpdateMeetingState() throws Exception {
        Response<Boolean> result = new Response<Boolean>();
        result.setData(maininfoService.schedulerUpdateMeetingState());
        return result;
    }


    @ApiOperation(value = "定时任务提前15分钟发送通知", httpMethod = "GET", notes = "定时任务提前15分钟发送通知")
    @RequestMapping(value = "/sendMeetingNotice", method = RequestMethod.GET)
    public Response<Boolean> sendMeetingNotice() throws Exception {
        Response<Boolean> result = new Response<Boolean>();
        result.setData(maininfoService.sendMeetingNotice());
        return result;
    }

    @ApiOperation(value = "可设置提醒时间定时任务", httpMethod = "GET", notes = "可设置提醒时间定时任务")
    @RequestMapping(value = "/sendMeetingNoticeForSet", method = RequestMethod.GET)
    public Response<Boolean> sendMeetingNoticeForSet() throws Exception {
        Response<Boolean> result = new Response<Boolean>();
        result.setData(maininfoService.sendMeetingNoticeForSet());
        return result;
    }


    @ApiOperation(value = "检查参会人是否被移除", httpMethod = "POST", notes = "检查参会人是否被移除")
    @RequestMapping(value = "/checkeInivterInfo", method = RequestMethod.POST)
    public Response<Boolean> checkeInivterInfo(@RequestParam("miId") String miId) throws Exception {
        Response<Boolean> result = new Response<Boolean>();


        result.setData(maininfoService.checkeInivterInfo(miId));
        return result;
    }

    @PostMapping("/spaceTree")
    @ApiOperation(value = "获取所有空间主数据", notes = "获取所有空间主数据接口")
    public Response<List<SpaceDto>> registAndCallBack() {
        Response<List<SpaceDto>> response = new Response<>();

        List<SpaceDto> spaceDtoList = baseSpaceService.registAndCallBack();

        if (!spaceDtoList.isEmpty()) {
            response.setData(spaceDtoList);
        } else {
            throw new BusinessException(ReturnType.ParamIllegal, "获取所有空间主数据接口异常!");
        }
        return response;
    }

    @ApiOperation(value = "根据用户名称关键字搜索组织树(首自信)", httpMethod = "POST", notes = "根据用户名称关键字搜索组织树(首自信)")
    @RequestMapping(value = "/searchSgaiOrgTree", method = RequestMethod.POST)
    public Response<List<OrgSgaiTreeNode>> searchSgaiOrgTree(@RequestParam("keyword") String keyword) {
        return null;//empInfoService.searchSgaiEmp( keyword);
    }


    @ApiOperation(value = "根据用户名称搜索用户信息(首自信)", httpMethod = "POST", notes = "根据用户名称搜索用户信息(首自信)")
    @RequestMapping(value = "/searchSgaiEmpInfoByName", method = RequestMethod.POST)
    public Response<EmpSearchResult> searchSgaiEmpInfoByName(@RequestParam("keyword") String keyword) {
        return null;//empInfoService.searchSgaiEmpInfoByName( keyword,1,10);
    }

}
