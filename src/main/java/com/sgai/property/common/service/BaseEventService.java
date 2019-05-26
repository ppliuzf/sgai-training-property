package com.sgai.property.common.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sgai.modules.login.jwt.util.CommonResponse;
import com.sgai.property.ctl.entity.CtlCodeDet;
import com.sgai.property.em.dto.EmComplaintsVo;
import com.sgai.property.em.dto.EmRepairListVo;
import com.sgai.property.em.dto.EmSecuRecordVo;
import com.sgai.property.em.entity.*;
import com.sgai.property.wf.entity.WfInstanceRecord;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @author ppliu
 * created in 2018/12/25 16:52
 */
public interface BaseEventService {
    Map<String, Object> save(EmProcess emProcess, HttpServletRequest request);

    Map<String, Object> saveConfirm(EmConfirm emConfirm, HttpServletRequest request);


    Map<String, Object> saveAssignAndProcess(EmAssign emAssign, HttpServletRequest request);

    Map<String, Object> saveComplete(EmProcess emProcess, HttpServletRequest request);

    Map<String, Object> saveComplaintsReturn(EmComplaintsReturn emComplaintsReturn, HttpServletRequest request);

    Map<String, String> endEm(EmEnded emEnded, HttpServletRequest request);

    CommonResponse getListProcess(WfInstanceRecord wfInstanceRecord) throws JsonProcessingException;

    CommonResponse getListType(CtlCodeDet ctlCodeDet, int pageNo, int pageSize, HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException;

    CommonResponse getEmComplaints(EmComplaintsVo emComplaintsVo, HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException;

    EmRepairListVo getByCode(EmRepairListVo emRepairList);


    CommonResponse save(EmComplaints emComplaints, String repairPhoto, HttpServletRequest request) throws JsonProcessingException;

    EmSecuRecordVo getEmSecuRecord(EmSecuRecordVo emSecuRecord, HttpServletRequest request, HttpServletResponse response);

    EmAssign getAssignByCode(HttpServletRequest request, EmAssign emAssign, HttpServletResponse response);

    List<EmProcess> getProcess(HttpServletRequest request, EmProcess emProcess, HttpServletResponse response);

    EmComplaintsReturn getComplaintsReturn(HttpServletRequest request, EmComplaintsReturn emComplaintsReturn, HttpServletResponse response);

    EmEnded getByCode(EmEnded emEnded, HttpServletRequest request, HttpServletResponse response);

    Map<String, String> save(String repairPhoto, EmSecuRecord emSecuRecord);

    EmConfirm getByCode(EmConfirm emConfirm, HttpServletRequest request, HttpServletResponse response);

    Map<String, String> save(EmRepairList emRepairList, String eiId);
}
