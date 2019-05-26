package com.sgai.property.customer.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.google.common.collect.Lists;
import com.sgai.common.mapper.BeanMapper;
import com.sgai.common.utils.StringUtils;
import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.modules.login.jwt.util.CommonResponse;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.common.exception.BusinessException;
import com.sgai.property.common.exception.ReturnType;
import com.sgai.property.common.service.BaseEventService;
import com.sgai.property.common.web.BaseController;
import com.sgai.property.commonService.vo.Response;
import com.sgai.property.ctl.entity.CtlCodeDet;
import com.sgai.property.ctl.entity.CtlUser;
import com.sgai.property.customer.constants.Constants;
import com.sgai.property.customer.dao.IPersonalRecordInfoDao;
import com.sgai.property.customer.entity.EventListVo;
import com.sgai.property.customer.entity.PersonalRecordInfo;
import com.sgai.property.customer.vo.EmEventCreateVo;
import com.sgai.property.customer.vo.EmEventInfoVo;
import com.sgai.property.customer.vo.EventDetailInfoVo;
import com.sgai.property.customer.vo.EventOrder;
import com.sgai.property.em.dto.EmComplaintsVo;
import com.sgai.property.em.dto.EmRepairListVo;
import com.sgai.property.em.dto.EmSecuRecordVo;
import com.sgai.property.em.entity.*;
import com.sgai.property.em.service.EmProcessService;
import com.sgai.property.wf.entity.WfInstanceRecord;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;


/**
 * 事件信息相关接口
 *
 * @author hou
 * @create 2018年3月1日11:33:10
 */


@SuppressWarnings({"rawtypes", "unchecked"})
@RestController
@RequestMapping("/event")
@Api(description = "事件信息相关接口")
public class EventController extends BaseController {

    private Logger logger = LogManager.getLogger(EventController.class);

    @Autowired
    private EmProcessService emProcessService;
    @Autowired
    private IPersonalRecordInfoDao personalRecordInfoDao;
    @Autowired
    private BaseEventService baseEventService;

    @RequestMapping(value = "/findNextNodeUserList", method = RequestMethod.POST)
    @ApiOperation(value = "获取指派人", notes = "获取指派人")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "flowCode", value = "flowCode（事件类型TS、AB、WX）", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "stepPos", value = "stepPos（节点步骤，维修传D，其他传C）", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "deptCode", value = "部门ID", paramType = "query", dataType = "String")
    })
    public Response<List<CtlUser>> findNextNodeUserList(
                                                        @RequestParam(value = "flowCode") String flowCode,
                                                        @RequestParam(value = "stepPos") String stepPos,
                                                        @RequestParam(value = "deptCode") String deptCode) {

        logger.info("核实操作----------------toonCode:" + UserServletContext.getUserInfo().getUserName());
        Response<List<CtlUser>> result = new Response<>();
        //CommonResponse commonResponse = smartFrameworkRemoteService.findNextNodeUserList(flowCode, stepPos, deptCode, accessToken);
        List<CtlUser> ctlUsers = new ArrayList<>();//JSON.parseObject(JSON.toJSONString(commonResponse.getData()), new TypeReference<List<CtlUser>>() { });
        result.setData(ctlUsers);
        return result;
    }

    @RequestMapping(value = "/getProceEventTime", method = RequestMethod.POST)
    @ApiOperation(value = "获取处理节点时间", notes = "获取处理节点时间")
    public Response<String> getProceEventTime(
                                              @RequestBody EmEventInfoVo emEventInfoVo) {

        logger.info("获取当前登录人信息----------------toonCode:" + UserServletContext.getUserInfo().getUserName());
        Response<String> result = new Response<>();
        EmProcess emProcess = new EmProcess();
        emProcess = BeanMapper.map(emEventInfoVo, EmProcess.class);
        emProcess.setProcPerson(emEventInfoVo.getPerson());
        emProcess.setRepairDatetime(emEventInfoVo.getDate() == null ? new Date() : emEventInfoVo.getDate());
        emProcess.setRepairContent(emEventInfoVo.getContent());
        Map<String, Object> map = saveComplete(emProcess);
        logger.info("map:" + JSON.toJSONString(map));
        if ("success".equals(map.get("msg").toString())) {
            String repairDatetime = DateFormatUtils.format((Date) map.get("repairDatetime"), Constants.DATEFORMAT_YYYYMMDDHHMMSS);
            result.setData(repairDatetime);
        }

        return result;
    }


    @RequestMapping(value = "/getLoginUserInfo", method = RequestMethod.POST)
    @ApiOperation(value = "获取当前登录人信息", notes = "获取当前登录人信息")
    public Response<LoginUser> getLoginUserInfo() {

        logger.info("获取当前登录人信息----------------toonCode:" + UserServletContext.getUserInfo().getUserName());
        Response<LoginUser> result = new Response<>();
        LoginUser loginUser = UserServletContext.getUserInfo();
        result.setData(loginUser);
        return result;
    }

    @RequestMapping(value = "/saveProcessInfo", method = RequestMethod.POST)
    @ApiOperation(value = "处理保存操作", notes = "处理保存操作")
    public Response<Boolean> saveProcessInfo(
                                             @RequestBody EmEventInfoVo emEventInfoVo) {

        logger.info("处理操作----------------toonCode:" + UserServletContext.getUserInfo().getUserName());
        String instanceId = emEventInfoVo.getInstanceId();
        if (StringUtils.isEmpty(instanceId)) {
            throw new BusinessException(ReturnType.Error, "instanceId 不能为空");
        }
        String emCode = emEventInfoVo.getEmCode();
        if (StringUtils.isEmpty(emCode)) {
            throw new BusinessException(ReturnType.Error, "emCode 不能为空");
        }
        String emType = emEventInfoVo.getEmType();
        if (StringUtils.isEmpty(emType)) {
            throw new BusinessException(ReturnType.Error, "emType 不能为空");
        }
        Response<Boolean> result = new Response<>();
        boolean flag = false;
        EmProcess emProcess = new EmProcess();
        emProcess = BeanMapper.map(emEventInfoVo, EmProcess.class);
        emProcess.setProcPerson(emEventInfoVo.getPerson());
        emProcess.setRepairDatetime(emEventInfoVo.getDate());
        emProcess.setRepairContent(emEventInfoVo.getContent());

        Map<String, Object> map = baseEventService.save(emProcess, request);
        logger.info("处理操作保存----------------map:" + JSON.toJSONString(map));
        if ("success".equals(map.get("msg").toString())) {
            flag = true;
        }
        result.setData(flag);
        return result;
    }

    @RequestMapping(value = "/proceEventInfo", method = RequestMethod.POST)
    @ApiOperation(value = "处理操作", notes = "处理操作")
    public Response<Boolean> saveConfirm(
                                         @RequestParam(value = "node") String node,
                                         @RequestBody EmEventInfoVo emEventInfoVo) {

        logger.info("处理操作----------------toonCode:" + UserServletContext.getUserInfo().getUserName());
        String instanceId = emEventInfoVo.getInstanceId();
        if (StringUtils.isEmpty(instanceId)) {
            throw new BusinessException(ReturnType.Error, "instanceId 不能为空");
        }
        String emCode = emEventInfoVo.getEmCode();
        if (StringUtils.isEmpty(emCode)) {
            throw new BusinessException(ReturnType.Error, "emCode 不能为空");
        }
        String emType = emEventInfoVo.getEmType();
        if (StringUtils.isEmpty(emType)) {
            throw new BusinessException(ReturnType.Error, "emType 不能为空");
        }

        Response<Boolean> result = new Response<>();
        boolean flag = false;
        if ("HS".equals(node)) {
            EmConfirm emConfirm = new EmConfirm();
            emConfirm = BeanMapper.map(emEventInfoVo, EmConfirm.class);
            emConfirm.setConfirmPerson(emEventInfoVo.getPerson());
            emConfirm.setConfirmDate(emEventInfoVo.getDate() == null ? new Date() : emEventInfoVo.getDate());
            emConfirm.setConfirmContent(emEventInfoVo.getContent());
            Map<String, Object> map = baseEventService.saveConfirm(emConfirm, request);
            logger.info("处理操作node：" + node + "----------------map:" + JSON.toJSONString(map));
            if ("success".equals(map.get("msg").toString())) {
                flag = true;
            }
        }
        if ("ZP".equals(node)) {
            EmAssign emAssign = new EmAssign();
            emAssign = BeanMapper.map(emEventInfoVo, EmAssign.class);
            emAssign.setAssignDatetime(emEventInfoVo.getDate() == null ? new Date() : emEventInfoVo.getDate());
            emAssign.setAssignPerson(emEventInfoVo.getPerson());
            emAssign.setAssignDesc(emEventInfoVo.getContent());
            Map<String, Object> map = baseEventService.saveAssignAndProcess(emAssign, request);
            logger.info("处理操作node：" + node + "----------------map:" + JSON.toJSONString(map));
            if ("success".equals(map.get("msg").toString())) {
                flag = true;
            }
        }
        if ("CL".equals(node)) {
            EmProcess emProcess = new EmProcess();
            emProcess = BeanMapper.map(emEventInfoVo, EmProcess.class);
            emProcess.setProcPerson(emEventInfoVo.getPerson());
            emProcess.setRepairDatetime(emEventInfoVo.getDate() == null ? new Date() : emEventInfoVo.getDate());
            emProcess.setRepairContent(emEventInfoVo.getContent());
            Map<String, Object> map = baseEventService.saveComplete(emProcess, request);
            logger.info("处理操作node：" + node + "----------------map:" + JSON.toJSONString(map));
            if ("success".equals(map.get("msg").toString())) {
                flag = true;
            }
        }
        if ("HF".equals(node)) {
            EmComplaintsReturn emComplaintsReturn = new EmComplaintsReturn();
            emComplaintsReturn = BeanMapper.map(emEventInfoVo, EmComplaintsReturn.class);
            emComplaintsReturn.setProcPerson(emEventInfoVo.getPerson());
            emComplaintsReturn.setReturnTime(emEventInfoVo.getDate() == null ? new Date() : emEventInfoVo.getDate());
            emComplaintsReturn.setReturnContent(emEventInfoVo.getContent());
            Map<String, Object> map = baseEventService.saveComplaintsReturn(emComplaintsReturn, request);

            logger.info("处理操作node：" + node + "----------------map:" + JSON.toJSONString(map));
            if ("success".equals(map.get("result").toString())) {
                flag = true;
            }
        }
        if ("ZZ".equals(node)) {
            EmEnded emEnded = new EmEnded();
            emEnded = BeanMapper.map(emEventInfoVo, EmEnded.class);
            emEnded.setEndReason(emEventInfoVo.getContent());
            emEnded.setEndTime(DateFormatUtils.format(emEventInfoVo.getDate() == null ? new Date() : emEventInfoVo.getDate(), Constants.DATEFORMAT_YYYYMMDDHHMMSS));
            emEnded.setOperator(emEventInfoVo.getPerson());
            Map<String, String> map = baseEventService.endEm(emEnded, request);

            logger.info("处理操作node：" + node + "----------------map:" + JSON.toJSONString(map));
            if ("success".equals(map.get("msg"))) {
                flag = true;
            }
        }

        if (!flag) {
            result.setCode(ReturnType.Error.getCode());
            result.setMessage("失败");
        }
        result.setData(flag);
        return result;
    }

    @RequestMapping(value = "/getEventInfoByCode", method = RequestMethod.POST)
    @ApiOperation(value = "5、根据流程状态查询节点信息接口", notes = "5、根据流程状态查询节点信息接口")
    public Response<EventDetailInfoVo> getEventInfoByCode(
            @RequestParam(value = "code") String code,
            @RequestParam(value = "codeType") String codeType,
             EventListVo eventListVo) throws IOException {

        logger.info("根据流程状态查询节点信息接口----------------toonCode:" + UserServletContext.getUserInfo().getUserName());
        String token = "";
        logger.info("根据流程状态查询节点信息接口----------------code:" + code + "---------codeType:" + codeType);
        Response<EventDetailInfoVo> result = new Response<>();
        EventDetailInfoVo eventDetailInfoVo = new EventDetailInfoVo();
        WfInstanceRecord wfInstanceRecord = new WfInstanceRecord();
        wfInstanceRecord.setEmCode(code);
        CommonResponse commonResponse = baseEventService.getListProcess(wfInstanceRecord);
        if (commonResponse != null) {
            List<WfInstanceRecord> wfInstanceRecords = JSON.parseObject(JSON.toJSONString(commonResponse.getData()), new TypeReference<List<WfInstanceRecord>>() {
            });
            eventDetailInfoVo.setWfInstanceRecords(wfInstanceRecords);
        }

        //替换类别名字
        CtlCodeDet ctlCodeDet = new CtlCodeDet();
        CommonResponse reCommonResponse = baseEventService.getListType(ctlCodeDet, pageNo, pageSize, request, response);
        Map returnMap = (Map) reCommonResponse.getData();
        List<CtlCodeDet> page = Lists.newArrayList();
        if (returnMap != null) {
            if (returnMap.get("list") != null) {
                page = JSON.parseObject(JSON.toJSONString(returnMap.get("list")), new TypeReference<List<CtlCodeDet>>() {
                });
            }
        }

        EmEventCreateVo emEventCreateVo = new EmEventCreateVo();
        if (Constants.CODE_TYPE_TSCATEGORY.equals(codeType)) {
            EmComplaintsVo emComplaintsVo = new EmComplaintsVo();
            emComplaintsVo.setComplCode(code);
            commonResponse = baseEventService.getEmComplaints(emComplaintsVo, request, response);
            emComplaintsVo = JSON.parseObject(JSON.toJSONString(commonResponse.getData()), new TypeReference<EmComplaintsVo>() {
            });
//			eventDetailInfoVo.setEmComplaintsVo(emComplaintsVo);
            emEventCreateVo.setEmCode(emComplaintsVo.getComplCode());
            emEventCreateVo.setEmPerson(emComplaintsVo.getComplPerson());
            logger.info("根据流程状态查询节点信息接口----------------emComplaintsVo.getCompCategory():" + JSON.toJSONString(emComplaintsVo.getCompCategory()));
            for (CtlCodeDet codeDet : page) {
                if (emComplaintsVo.getCompCategory().equals(codeDet.getCodeCode())) {
                    emEventCreateVo.setEmType(codeDet.getCodeName());
                    break;
                }
            }
            if (StringUtils.isEmpty(emEventCreateVo.getEmType())) {
                emEventCreateVo.setEmType(emComplaintsVo.getCompCategory());
            }

            emEventCreateVo.setEmContent(emComplaintsVo.getComplContent());
            emEventCreateVo.setAddress(emComplaintsVo.getAddress());
            emEventCreateVo.setDesc(emComplaintsVo.getDesc());
            emEventCreateVo.setEmTime(emComplaintsVo.getComplTime());
            emEventCreateVo.setFiles(emComplaintsVo.getFiles());
            emEventCreateVo.setTelphone(emComplaintsVo.getTelphone());
            eventDetailInfoVo.setEmEventCreateVo(emEventCreateVo);
        }
        if (Constants.CODE_TYPE_WXCATEGORY.equals(codeType)) {
            EmRepairListVo emRepairList = new EmRepairListVo();
            emRepairList.setEmCode(code);
            emRepairList = baseEventService.getByCode(emRepairList);
//    		eventDetailInfoVo.setEmRepairListVo(emRepairList);
            emEventCreateVo.setEmCode(emRepairList.getEmCode());
            emEventCreateVo.setEmPerson(emRepairList.getRepairPerson());
            logger.info("根据流程状态查询节点信息接口----------------emRepairList.getRepairType():" + JSON.toJSONString(emRepairList.getRepairType()));
            for (CtlCodeDet codeDet : page) {
                if (emRepairList.getRepairType().equals(codeDet.getCodeCode())) {
                    emEventCreateVo.setEmType(codeDet.getCodeName());
                    break;
                }
            }
            if (StringUtils.isEmpty(emEventCreateVo.getEmType())) {
                emEventCreateVo.setEmType(emRepairList.getRepairType());
            }
            emEventCreateVo.setEmContent(emRepairList.getRepairContent());
            emEventCreateVo.setAddress(emRepairList.getAddress());
            emEventCreateVo.setDesc(emRepairList.getRepairDesc());
            emEventCreateVo.setEmTime(DateFormatUtils.format(emRepairList.getRepairDate(), Constants.DATEFORMAT_YYYYMMDDHHMMSS));
            emEventCreateVo.setFiles(emRepairList.getFiles());
            emEventCreateVo.setTelphone(emRepairList.getTelphone());
            eventDetailInfoVo.setEmEventCreateVo(emEventCreateVo);
        }
        if (Constants.CODE_TYPE_ABCATEGORY.equals(codeType)) {
            EmSecuRecordVo emSecuRecord = new EmSecuRecordVo();
            emSecuRecord.setEmCode(code);
            emSecuRecord = baseEventService.getEmSecuRecord(emSecuRecord, request, response);
//    		eventDetailInfoVo.setEmSecuRecordVo(emSecuRecord);
            emEventCreateVo.setEmCode(emSecuRecord.getEmCode());
            emEventCreateVo.setEmPerson(emSecuRecord.getReportPerson());
            logger.info("根据流程状态查询节点信息接口----------------emSecuRecord.getEmCategory():" + JSON.toJSONString(emSecuRecord.getEmCategory()));
            for (CtlCodeDet codeDet : page) {
                if (emSecuRecord.getEmCategory().equals(codeDet.getCodeCode())) {
                    emEventCreateVo.setEmType(codeDet.getCodeName());
                    break;
                }
            }
            if (StringUtils.isEmpty(emEventCreateVo.getEmType())) {
                emEventCreateVo.setEmType(emSecuRecord.getEmType());
            }
            emEventCreateVo.setEmContent(emSecuRecord.getReportContent());
            emEventCreateVo.setAddress(emSecuRecord.getAddress());
            emEventCreateVo.setDesc(emSecuRecord.getRemarks());
            emEventCreateVo.setEmTime(DateFormatUtils.format(emSecuRecord.getReportDatetime(), Constants.DATEFORMAT_YYYYMMDDHHMMSS));
            emEventCreateVo.setFiles(emSecuRecord.getFiles());
            emEventCreateVo.setTelphone(emSecuRecord.getTelphone());
            eventDetailInfoVo.setEmEventCreateVo(emEventCreateVo);
        }
        EmAssign emAssign = new EmAssign();
        emAssign.setEmCode(code);
        emAssign = baseEventService.getAssignByCode(request, emAssign, response);
        eventDetailInfoVo.setEmAssign(emAssign);

        EmConfirm emConfirm = new EmConfirm();
        emConfirm.setEmCode(code);
        emConfirm = baseEventService.getByCode(emConfirm, request, response);
        eventDetailInfoVo.setEmConfirm(emConfirm);

        EmProcess emProcess = new EmProcess();
        emProcess.setEmCode(code);
        List<EmProcess> emProcesses = baseEventService.getProcess(request, emProcess, response);
        eventDetailInfoVo.setEmProcesses(emProcesses);

        EmComplaintsReturn emComplaintsReturn = new EmComplaintsReturn();
        emComplaintsReturn.setEmCode(code);
        emComplaintsReturn = baseEventService.getComplaintsReturn(request, emComplaintsReturn, response);
        eventDetailInfoVo.setEmComplaintsReturn(emComplaintsReturn);

        EmEnded emEnded = new EmEnded();
        emEnded.setEmCode(code);
        emEnded = baseEventService.getByCode(emEnded, request, response);
        eventDetailInfoVo.setEmEnded(emEnded);
        logger.info("根据流程状态查询节点信息接口----------------eventDetailInfoVo:" + JSON.toJSONString(eventDetailInfoVo));
        result.setData(eventDetailInfoVo);
        return result;
    }

    @RequestMapping(value = "/getEventInfoByCodes", method = RequestMethod.POST)
    @ApiOperation(value = "5、根据流程状态查询节点信息接口", notes = "5、根据流程状态查询节点信息接口")
    public Response<EmProcess> getEventInfoByCodes(
            @RequestParam(value = "code") String code,
            @RequestParam(value = "codeType") String codeType,
             HttpServletRequest request, HttpServletResponse response) {

        logger.info("根据流程状态查询节点信息接口----------------toonCode:" + UserServletContext.getUserInfo().getUserName());
        String token = "";
        logger.info("根据流程状态查询节点信息接口----------------code:" + code + "---------codeType:" + codeType);
        Response<EmProcess> result = new Response<>();

        EmProcess emProcess = new EmProcess();
        emProcess.setEmCode(code);
        List<EmProcess> emProcesses = baseEventService.getProcess(request, emProcess, response);
        EmProcess ss = new EmProcess();
        for (EmProcess ems : emProcesses) {
            ss.setRepairDatetime(ems.getRepairDatetime());
        }
        result.setData(ss);
        return result;
    }

    @RequestMapping(value = "/getListType", method = RequestMethod.POST)
    @ApiOperation(value = "获取事件类型下的事件类别", notes = "获取事件类型下的事件类别")
    public Response getListType(
            @RequestParam(value = "code_type", required = false) String codeTypeName,
            @RequestParam(value = "pageNo", required = true, defaultValue = "0") Integer pageNo,
            @RequestParam(value = "pageSize", required = true, defaultValue = "10") Integer pageSize,
             HttpServletRequest request, HttpServletResponse response) throws IOException {

        logger.info("获取事件类型下的事件类别----------------toonCode:" + UserServletContext.getUserInfo().getUserName());
        Response response2 = new Response<>();

        CtlCodeDet ctlCodeDet = new CtlCodeDet();
        CommonResponse commonResponse = baseEventService.getListType(ctlCodeDet, pageNo, pageSize, request, response);
        Map returnMap = (Map) commonResponse.getData();
        List<CtlCodeDet> page = (List<CtlCodeDet>) returnMap.get("list");
        response2.setData(page);
        return response2;
    }

    @RequestMapping(value = "/getEventLists", method = RequestMethod.POST)
    @ApiOperation(value = "获取当前客户事件列表", notes = "获取当前客户事件列表")
    public Response<List<EventListVo>> getEventLists(
                                                     String personId,
                                                     HttpServletRequest request, HttpServletResponse response) throws Exception {
        Response<List<EventListVo>> response2 = new Response<>();

        logger.info("获取当前客户事件列表----------------toonCode:" + UserServletContext.getUserInfo().getUserName());

        List<EventListVo> eventListVos = new ArrayList<>();
        EmComplaints emComplaints = new EmComplaints();
        emComplaints.setComplPerson(personId);
        emComplaints.setId(null);
        CommonResponse commonResponse = new CommonResponse();
        commonResponse = null;//smartFrameworkRemoteService.getEmComplaintList(emComplaints,accessToken,request,response);
        if (commonResponse != null && commonResponse.getData() != null) {
            //客户对象
            PersonalRecordInfo personalRecordInfos = new PersonalRecordInfo();
            //事件联系人id赋值客户id  关联关系
            personalRecordInfos.setId(personId);
            //根据客户id查询客户名称（即联系人名称）
            PersonalRecordInfo personalRecordInfo = personalRecordInfoDao.getById(personalRecordInfos.getId());
            List<EmComplaints> complaints = JSON.parseObject(JSON.toJSONString(commonResponse.getData()), new TypeReference<List<EmComplaints>>() {
            });
            for (EmComplaints complaint : complaints) {
                EventListVo eventListVo = new EventListVo();
                eventListVo.setCode(complaint.getComplCode());

                //替换类别名字
                CtlCodeDet ctlCodeDet = new CtlCodeDet();
                CommonResponse reCommonResponse = baseEventService.getListType(ctlCodeDet, pageNo, pageSize, request, response);
                Map returnMap = (Map) reCommonResponse.getData();
                List<CtlCodeDet> page = JSON.parseObject(JSON.toJSONString(returnMap.get("list")), new TypeReference<List<CtlCodeDet>>() {
                });
                for (CtlCodeDet codeDet : page) {
                    if (complaint.getCompCategory().equals(codeDet.getCodeCode())) {
                        eventListVo.setCategory(codeDet.getCodeName());
                        break;
                    }
                }

                eventListVo.setType(complaint.getComplType());
                //关联
                eventListVo.setPerson(personalRecordInfo.getPrName());
                eventListVo.setTelphone(complaint.getTelphone());
                eventListVo.setAddress(complaint.getAddress());
                eventListVo.setTime(complaint.getComplTime());
                eventListVo.setProcTime(complaint.getComplTime());
                eventListVo.setStates(complaint.getStates());
                eventListVo.setCodeType(Constants.CODE_TYPE_TSCATEGORY);
                eventListVos.add(eventListVo);
            }

        }

        EmSecuRecord emSecuRecord = new EmSecuRecord();
        emSecuRecord.setReportPerson(personId);
        commonResponse = new CommonResponse();
        List<EmSecuRecord> emSecuRecords = new ArrayList<>();//smartFrameworkRemoteService.getEmSecuRecordList(emSecuRecord,accessToken,request,response);
        emSecuRecords = JSON.parseObject(JSON.toJSONString(emSecuRecords), new TypeReference<List<EmSecuRecord>>() {
        });
        for (EmSecuRecord record : emSecuRecords) {
            EventListVo eventListVo = new EventListVo();
            eventListVo.setCode(record.getEmCode());

            //替换类别名字
            CtlCodeDet ctlCodeDet = new CtlCodeDet();
            //CommonResponse reCommonResponse = smartFrameworkRemoteService.getListType(ctlCodeDet,Constants.CODE_TYPE_ABCATEGORY,pageNo, pageSize,accessToken,request,response);
            //Map	returnMap = 	(Map) reCommonResponse.getData();
            List<CtlCodeDet> page = new ArrayList<>();//JSON.parseObject(JSON.toJSONString(returnMap.get("list")), new TypeReference<List<CtlCodeDet>>() { });
            for (CtlCodeDet codeDet : page) {
                if (record.getEmCategory().equals(codeDet.getCodeCode())) {
                    eventListVo.setCategory(codeDet.getCodeName());
                    break;
                }
            }

//			eventListVo.setCategory(record.getEmCategory());
            eventListVo.setType(record.getEmType());
            eventListVo.setPerson(record.getReportPerson());
            eventListVo.setTelphone(record.getTelphone());
            eventListVo.setAddress(record.getAddress());
            eventListVo.setTime(DateFormatUtils.format(record.getReportDatetime(), Constants.DATEFORMAT_YYYYMMDDHHMMSS));
            eventListVo.setProcTime(DateFormatUtils.format(record.getReportDatetime(), Constants.DATEFORMAT_YYYYMMDDHHMMSS));
            eventListVo.setCodeType(Constants.CODE_TYPE_ABCATEGORY);
            eventListVo.setStates(record.getStates());

            eventListVos.add(eventListVo);
        }

        EmRepairList emRepairList = new EmRepairList();
        emRepairList.setRepairPerson(personId);
        //commonResponse =new CommonResponse();
        //commonResponse = smartFrameworkRemoteService.getRepairList(emRepairList, accessToken, request, response);

        List<EmRepairList> emRepairLists = new ArrayList<>();//JSON.parseObject(JSON.toJSONString(commonResponse.getData()), new TypeReference<List<EmRepairList>>() { });
        for (EmRepairList record : emRepairLists) {
            EventListVo eventListVo = new EventListVo();
            eventListVo.setCode(record.getEmCode());

            //替换类别名字
            CtlCodeDet ctlCodeDet = new CtlCodeDet();
            CommonResponse reCommonResponse = baseEventService.getListType(ctlCodeDet, pageNo, pageSize, request, response);
            Map returnMap = (Map) reCommonResponse.getData();
            List<CtlCodeDet> page = JSON.parseObject(JSON.toJSONString(returnMap.get("list")), new TypeReference<List<CtlCodeDet>>() {
            });
            for (CtlCodeDet codeDet : page) {
                if (record.getRepairType().equals(codeDet.getCodeCode())) {
                    eventListVo.setCategory(codeDet.getCodeName());
                    break;
                }
            }

            eventListVo.setType(record.getEmType());
            eventListVo.setPerson(record.getRepairPerson());
            eventListVo.setTelphone(record.getTelphone());
            eventListVo.setAddress(record.getAddress());
            eventListVo.setTime(DateFormatUtils.format(record.getRepairDate(), Constants.DATEFORMAT_YYYYMMDDHHMMSS));
            eventListVo.setProcTime("");
            eventListVo.setCodeType(Constants.CODE_TYPE_WXCATEGORY);
            eventListVo.setStates(record.getStates());

            eventListVos.add(eventListVo);
        }
        response2.setData(eventListVos);
        return response2;
    }

    @RequestMapping(value = "/saveEvent", method = RequestMethod.POST)
    @ApiOperation(value = "保存事件", notes = "保存事件")
    public Response<Boolean> saveEvent(

            @RequestBody EventOrder eventOrder, HttpServletRequest request) throws IOException {

        Response<Boolean> response = new Response<>();
        LoginUser loginUser = new LoginUser();
        loginUser.setComCode(UserServletContext.getUserInfo().getComCode());
        loginUser.setComName(UserServletContext.getUserInfo().getComName());
        loginUser.setUserId(UserServletContext.getUserInfo().getUserNo());
        loginUser.setUserName(UserServletContext.getUserInfo().getUserName());
        loginUser.setUserType(UserServletContext.getUserInfo().getUserType());
        CommonResponse commonResponse = new CommonResponse();
        if (Constants.CODE_TYPE_TSCATEGORY.equals(eventOrder.getEventType())) {
            EmComplaints emComplaints = new EmComplaints();
            emComplaints.setComplCode(eventOrder.getEventCode());
            emComplaints.setCompCategory(eventOrder.getEventClass());
//			emComplaints.setUserCode(UserServletContext.getUserInfo().getUserNo());
            emComplaints.setComplType(Constants.EventType.Complaints);
            emComplaints.setComplPerson(eventOrder.getPrId());
            emComplaints.setTelphone(eventOrder.getTelephone());
            emComplaints.setAddress(eventOrder.getAddress());
            emComplaints.setComplContent(eventOrder.getComplContent());
            emComplaints.setDesc(eventOrder.getDesc());
            emComplaints.setStates(Constants.ComplaintsEventStatus.WaitAssign.getId() + "");

            commonResponse = baseEventService.save(emComplaints, eventOrder.getRepairPhoto(), request);
        }

        if (Constants.CODE_TYPE_WXCATEGORY.equals(eventOrder.getEventType())) {
            EmRepairList emRepairList = new EmRepairList();
            emRepairList.setEmCode(eventOrder.getEventCode());
            emRepairList.setRepairType(eventOrder.getEventClass());
//			emRepairList.setUserCode(UserServletContext.getUserInfo().getUserNo());
            emRepairList.setEmType(Constants.EventType.Repair);
            emRepairList.setRepairPerson(eventOrder.getPrId());
            emRepairList.setTelphone(eventOrder.getTelephone());
            emRepairList.setRepairPhoto(eventOrder.getRepairPhoto());
            emRepairList.setAddress(eventOrder.getAddress());
            emRepairList.setRepairContent(eventOrder.getComplContent());
            emRepairList.setRepairDesc(eventOrder.getDesc());
            emRepairList.setStates(Constants.RepairEventStatus.WaitOrders.getId() + "");
            Map<String, String> map = baseEventService.save(emRepairList, UserServletContext.getUserInfo().getUserNo());
            commonResponse.setMsg(map.get("message"));
            if ("success".equals(map.get("message"))) {
                commonResponse.setSuccess(true);
            } else {
                commonResponse.setSuccess(false);
            }
        }
        if (Constants.CODE_TYPE_ABCATEGORY.equals(eventOrder.getEventType())) {
            EmSecuRecord emSecuRecord = new EmSecuRecord();
            emSecuRecord.setEmCode(eventOrder.getEventCode());
            emSecuRecord.setEmCategory(eventOrder.getEventClass());
            emSecuRecord.setEmType(Constants.EventType.Security);
//			emSecuRecord.setUserCode(UserServletContext.getUserInfo().getEmCode());
            emSecuRecord.setReportPerson(eventOrder.getPrId());
            emSecuRecord.setReportContent(eventOrder.getComplContent());
            emSecuRecord.setAddress(eventOrder.getAddress());
            emSecuRecord.setTelphone(eventOrder.getTelephone());
            emSecuRecord.setStates(Constants.SecurityEventStatus.WaitAssign.getId() + "");
            emSecuRecord.setRemarks(eventOrder.getDesc());
            Map<String, String> map = baseEventService.save(eventOrder.getRepairPhoto(), emSecuRecord);
            commonResponse.setMsg(map.get("message"));
            if ("success".equals(map.get("message"))) {
                commonResponse.setSuccess(true);
            } else {
                commonResponse.setSuccess(false);
            }
        }
        response.setData(true);
        return response;
    }

    private Map<String, Object> saveComplete(EmProcess emProcess) {
        Map<String, Object> map = new HashMap<String, Object>();
        LoginUser userInfo = UserServletContext.getUserInfo();
        try {
            emProcess.setEnabledFlag("Y");
            emProcess.setProcPerson(userInfo.getUserId());
            emProcessService.saveComplete(emProcess, userInfo);
            map.put("msg", "success");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg", "fail");
        }
        return map;
    }
}
