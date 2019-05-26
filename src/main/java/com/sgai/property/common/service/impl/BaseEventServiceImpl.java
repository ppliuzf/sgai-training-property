package com.sgai.property.common.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sgai.common.persistence.Page;
import com.sgai.common.utils.StringUtils;
import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.modules.login.jwt.util.CommonResponse;
import com.sgai.modules.login.jwt.util.ResponseUtil;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.alm.service.AlmRecordListService;
import com.sgai.property.common.service.BaseEventService;
import com.sgai.property.ctl.entity.CtlAttachfile;
import com.sgai.property.ctl.entity.CtlCodeDet;
import com.sgai.property.ctl.service.CtlAttachfileService;
import com.sgai.property.ctl.service.CtlCodeDetService;
import com.sgai.property.ctl.service.CtlComRuleService;
import com.sgai.property.em.dto.EmComplaintsVo;
import com.sgai.property.em.dto.EmRepairListVo;
import com.sgai.property.em.dto.EmSecuRecordVo;
import com.sgai.property.em.entity.*;
import com.sgai.property.em.service.*;
import com.sgai.property.wf.entity.WfInstanceRecord;
import com.sgai.property.wf.service.WfInstanceRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ppliu
 * created in 2018/12/25 16:52
 */
@Service
public class BaseEventServiceImpl implements BaseEventService {
    @Autowired
    private EmConfirmService emConfirmService;
    @Autowired
    private EmProcessService emProcessService;
    @Autowired
    private EmAssignService emAssignService;
    @Autowired
    private EmComplaintsReturnService emComplaintsReturnService;
    @Autowired
    private EmComplaintsService emComplaintsService;
    @Autowired
    private EmRepairListService emRepairListService;
    @Autowired
    private AlmRecordListService almRecordListService;
    @Autowired
    private EmSecuRecordService emSecuRecordService;
    @Autowired
    private WfInstanceRecordService wfInstanceRecordService;
    @Autowired
    private EmEndedService emEndedService;
    @Autowired
    private EmEmergencyRecordService emEmergencyRecordService;
    @Autowired
    private CtlCodeDetService ctlCodeDetService;
    @Autowired
    private CtlAttachfileService ctlAttachfileService;
    @Autowired
    private CtlComRuleService ctlComRuleService;


    public Map<String, Object> saveConfirm(EmConfirm emConfirm, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        LoginUser user = UserServletContext.getUserInfo();
        try {
            emConfirm.setEnabledFlag("Y");
            if (emConfirm.getConfirmDate() == null) {
                emConfirm.setConfirmDate(new Date());
            }
            emConfirmService.saveConfirm(emConfirm, user);
            map.put("msg", "success");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg", "fail");
        }
        return map;
    }

    @Override
    public Map<String, Object> save(EmProcess emProcess, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        LoginUser userInfo = UserServletContext.getUserInfo();
        try {
            emProcess.setEnabledFlag("Y");
            emProcess.setProcPerson(userInfo.getUserId());
            emProcessService.save(emProcess);
            map.put("msg", "success");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg", "fail");
        }
        return map;
    }

    @Override
    public Map<String, String> endEm(EmEnded emEnded, HttpServletRequest request) {
        Map<String, String> data = new HashMap<String, String>();
        LoginUser userInfo = UserServletContext.getUserInfo();
        try {
            //投诉终止
            if ("TS".equals(emEnded.getEmType())) {
                emComplaintsService.updateComplaint(emEnded.getEmCode(), "4", userInfo.getUserId(), userInfo.getUserName());
                //维修事件终止
            } else if ("WX".equals(emEnded.getEmType())) {
                emRepairListService.updateRepairList(userInfo.getUserId(), emEnded.getEmCode(), "4");
                //若是报警事件生成的同步报警事件的状态
                almRecordListService.updateAlmRecord(emEnded.getEmCode(), "4");
                //安保事件终止
            } else if ("AB".equals(emEnded.getEmType())) {
                emSecuRecordService.updateSecuRecord(emEnded.getEmCode(), "4", userInfo.getUserId(), userInfo.getUserName());
                emEnded.setEmCode(emEnded.getEmCode());
                //应急事件终止
            } else if ("YJ".equals(emEnded.getEmType())) {
                emEmergencyRecordService.updateEmergencyRecord(emEnded.getEmCode(), "3", userInfo.getUserId(), userInfo.getUserName());
            }
            //保存终止事件
            WfInstanceRecord wfInstanceRecord = new WfInstanceRecord();
            wfInstanceRecord.setEmCode(emEnded.getEmCode());
            List<WfInstanceRecord> list = wfInstanceRecordService.findWfInstanceRecordList(wfInstanceRecord);
            if (list.size() > 0) {
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

    @Override
    public CommonResponse getListProcess(WfInstanceRecord wfInstanceRecord) throws JsonProcessingException {
        List<WfInstanceRecord> list = wfInstanceRecordService.findWfInstanceRecordList(wfInstanceRecord);
        return ResponseUtil.successResponse(list);
    }

    @Override
    public CommonResponse getListType(CtlCodeDet ctlCodeDet, int pageNo, int pageSize, HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
        if (request.getParameter("id") != "")
            ctlCodeDet.setId(request.getParameter("id"));

        Page<com.sgai.property.ctl.entity.CtlCodeDet> page = ctlCodeDetService.findPage(new Page<com.sgai.property.ctl.entity.CtlCodeDet>(request, response), ctlCodeDet);

        return ResponseUtil.successResponse(page);
    }

    @Override
    public CommonResponse getEmComplaints(EmComplaintsVo emComplaintsVo, HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
        EmComplaintsVo info = emComplaintsService.getEmComplaints(emComplaintsVo);
        if (info != null) {
            com.sgai.property.ctl.entity.CtlCodeDet ctlCodeDet = new com.sgai.property.ctl.entity.CtlCodeDet();
            ctlCodeDet.setCodeCode(info.getCompCategory());
            ctlCodeDet.setCodeType("TsCategory");
            com.sgai.property.ctl.entity.CtlCodeDet codeDet = ctlCodeDetService.getCodeDet(ctlCodeDet);
            if (codeDet != null) {
                info.setCompCategory(codeDet.getCodeName());
            }
            CtlAttachfile file = new CtlAttachfile();
            file.setMasterFileId(info.getId());
            List<CtlAttachfile> list = ctlAttachfileService.findList(file);
            if (list != null && list.size() > 0) {
                info.setFiles(list);
            }
        }
        return ResponseUtil.successResponse(info);
    }

    @Override
    public EmRepairListVo getByCode(EmRepairListVo emRepairList) {
        if (emRepairList != null) {
            com.sgai.property.ctl.entity.CtlCodeDet ctlCodeDet = new com.sgai.property.ctl.entity.CtlCodeDet();
            ctlCodeDet.setCodeCode(emRepairList.getRepairType());
            ctlCodeDet.setCodeType("WxCategory");
            com.sgai.property.ctl.entity.CtlCodeDet codeDet = ctlCodeDetService.getCodeDet(ctlCodeDet);
            if (codeDet != null) {
                emRepairList.setRepairType(codeDet.getCodeName());
            }
            CtlAttachfile ctlAttachfile = new CtlAttachfile();
            ctlAttachfile.setMasterFileId(emRepairList.getId());
            List<CtlAttachfile> files = ctlAttachfileService.findList(ctlAttachfile);
            if (files != null && files.size() > 0) {
                emRepairList.setFiles(files);
            }
        }
        return emRepairList;
    }

    @Override
    public CommonResponse save(EmComplaints emComplaints, String repairPhoto, HttpServletRequest request) throws JsonProcessingException {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Map<String, String> data = new HashMap<String, String>();
        LoginUser userInfo = UserServletContext.getUserInfo();
        try {
            if (StringUtils.isBlank(emComplaints.getId())) {
                String complCode = ctlComRuleService.getNext("EMCODE-TS");
                emComplaints.setComplCode(complCode);
                emComplaints.setComplTime(format.format(new Date()));
                emComplaints.setStates("0");
                emComplaints.setComplType("TS");
                emComplaints.setEnabledFlag("Y");
                emComplaintsService.saveComplaint(emComplaints, userInfo);
                //String path = "http://211.94.132.78";//图片服务器地址
                if (emComplaints.getRepairPhoto() != null && !"".equals(emComplaints.getRepairPhoto())) {
                    String[] images = emComplaints.getRepairPhoto().split("\\|");
                    for (String str : images) {
                        CtlAttachfile p = new CtlAttachfile();
                        p.setMasterFileType("投诉事件");//模块名称
                        p.setKeyDesc("投诉图片");//文件描述
                        p.setMasterFileId(emComplaints.getId());//主表id
                        String filePath = str.substring(0, 31) + userInfo.getUserId() + "/";
                        String fileName = str.substring(filePath.length());
                        p.setFileName(fileName);//文件名称
                        p.setFileLoc(filePath);//文件存储路径
                        p.setUploadTime(format.format(new Date()));//上传时间
                        p.setContentType("multipart/form-data; charset=utf-8");//文件MIME类型
                        //File file = new File(path + File.separator+ str);
                        //p.setFileSize(String.valueOf(file.length()));//文件大小,单位byte
                        ctlAttachfileService.saveAttachfile(p);
                    }
                }
                data.put("message", "success");
            } else {
                //如果是更新数据，直接执行save方法
                emComplaintsService.save(emComplaints);
                data.put("message", "success");
            }
        } catch (Exception e) {

            data.put("message", "failed");
            e.printStackTrace();
        }
        return ResponseUtil.successResponse(data);
    }

    @Override
    public EmSecuRecordVo getEmSecuRecord(EmSecuRecordVo emSecuRecord, HttpServletRequest request, HttpServletResponse response) {

        EmSecuRecordVo emSecuRecordVo = emSecuRecordService.getEmSecuRecord(emSecuRecord);
        if (emSecuRecordVo != null) {
            CtlCodeDet ctlCodeDet = new CtlCodeDet();
            ctlCodeDet.setCodeCode(emSecuRecordVo.getEmCategory());
            ctlCodeDet.setCodeType("AbCategory");
            CtlCodeDet codeDet = ctlCodeDetService.getCodeDet(ctlCodeDet);
            if (codeDet != null) {
                emSecuRecordVo.setEmCategory(codeDet.getCodeName());
            }
            CtlAttachfile file = new CtlAttachfile();
            file.setMasterFileId(emSecuRecordVo.getId());
            List<CtlAttachfile> files = ctlAttachfileService.findList(file);
            if (files != null && files.size() > 0) {
                emSecuRecordVo.setFiles(files);
            }
        }
        return emSecuRecordVo;
    }

    @Override
    public EmAssign getAssignByCode(HttpServletRequest request, EmAssign emAssign, HttpServletResponse response) {
        return emAssignService.getAssignByCode(emAssign);
    }

    @Override
    public List<EmProcess> getProcess(HttpServletRequest request, EmProcess emProcess, HttpServletResponse response) {
        return emProcessService.findList(emProcess);
    }

    @Override
    public EmComplaintsReturn getComplaintsReturn(HttpServletRequest request, EmComplaintsReturn emComplaintsReturn, HttpServletResponse response) {
        return emComplaintsReturnService.getComplaintsReturn(emComplaintsReturn);
    }

    @Override
    public EmEnded getByCode(EmEnded emEnded, HttpServletRequest request, HttpServletResponse response) {
        return emEndedService.getByCode(emEnded);
    }

    @Override
    public Map<String, String> save(String repairPhoto, EmSecuRecord emSecuRecord) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Map<String, String> data = new HashMap<String, String>();
        emSecuRecord.setEnabledFlag("Y");
        //emSecuRecord.setComCode(user.getComCode());
        LoginUser user = UserServletContext.getUserInfo();
        try {
            if (StringUtils.isBlank(emSecuRecord.getId())) {
                String emCode = ctlComRuleService.getNext("EMCODE-AB");
                emSecuRecord.setEmCode(emCode);
                emSecuRecord.setReportDatetime(new Date());
                emSecuRecord.setEmType("AB");
                emSecuRecord.setStates("0");
                emSecuRecordService.saveSecuRecord(emSecuRecord, user);
                //String path="";//图片服务器地址
                if (emSecuRecord.getRepairPhoto() != null && !"".equals(emSecuRecord.getRepairPhoto())) {
                    String[] images = emSecuRecord.getRepairPhoto().split("\\|");
                    for (String str : images) {
                        CtlAttachfile p = new CtlAttachfile();
                        p.setMasterFileType("安保事件");//模块名称
                        p.setKeyDesc("安保图片");//文件描述
                        p.setMasterFileId(emSecuRecord.getId());//主表id
                        String filePath = str.substring(0, 31) + user.getUserId() + "/";
                        String fileName = str.substring(filePath.length());
                        p.setFileName(fileName);//文件名称
                        p.setFileLoc(filePath);//文件存储路径
                        p.setUploadTime(format.format(new Date()));//上传时间
                        p.setContentType("multipart/form-data; charset=utf-8");//文件MIME类型
                        //File file = new File(path + str);
                        //p.setFileSize(String.valueOf(file.length()));//文件大小,单位byte
                        ctlAttachfileService.saveAttachfile(p);
                    }
                }
                data.put("message", "success");
            } else {
                //如果是更新数据，直接执行save方法
                emSecuRecordService.save(emSecuRecord);
                data.put("message", "success");
            }
        } catch (Exception e) {
            data.put("message", "failed");
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public EmConfirm getByCode(EmConfirm emConfirm, HttpServletRequest request, HttpServletResponse response) {
        return emConfirmService.getCode(emConfirm);
    }

    @Override
    public Map<String, String> save(EmRepairList emRepairList, String eiId) {
        Map<String,String> data = new HashMap<String,String>();
        emRepairList.setEnabledFlag("Y");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LoginUser user = UserServletContext.getUserInfo();
        try {
            if (StringUtils.isBlank(emRepairList.getId())) {
                String emCode = ctlComRuleService.getNext("EMCODE-WX");
                emRepairList.setEmCode(emCode);
                emRepairList.setFromNum(emCode);
                emRepairList.setRepairFrom("手动事件");
                emRepairListService.saveRepair(emRepairList,user);
                //String path="";//图片服务器地址
                if (emRepairList.getRepairPhoto()!=null && !"".equals(emRepairList.getRepairPhoto())) {
                    String[] images = emRepairList.getRepairPhoto().split("\\|");
                    for(String str:images) {
                        CtlAttachfile p = new CtlAttachfile();
                        p.setMasterFileType("报修事件");//模块名称
                        p.setKeyDesc("报修图片");//文件描述
                        p.setMasterFileId(emRepairList.getId());//主表id
                        String filePath = str.substring(0, 31) + user.getUserId() + "/";
                        String fileName = str.substring(filePath.length());
                        p.setFileName(fileName);//文件名称
                        p.setFileLoc(filePath);//文件存储路径
                        p.setUploadTime(format.format(new Date()));//上传时间
                        p.setContentType("multipart/form-data; charset=utf-8");//文件MIME类型
                        //File file = new File(path + str);
                        //p.setFileSize(String.valueOf(file.length()));//文件大小,单位byte
                        ctlAttachfileService.saveAttachfile(p);
                    }
                }
                data.put("message", "success");
            } else {
                //如果是更新数据，直接执行save方法
                emRepairListService.save(emRepairList);
                data.put("message", "success");
            }
        } catch (Exception e) {
            data.put("message", "failed");
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public Map<String, Object> saveComplaintsReturn(EmComplaintsReturn emComplaintsReturn, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        LoginUser user = UserServletContext.getUserInfo();
        try {
            emComplaintsReturn.setEnabledFlag("Y");
            map = emComplaintsReturnService.saveReturn(emComplaintsReturn, user);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("result", "fail");
        }
        return map;
    }

    @Override
    public Map<String, Object> saveAssignAndProcess(EmAssign emAssign, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        LoginUser user = UserServletContext.getUserInfo();
        try {
            emAssign.setProcPerson(user.getUserId());
            emAssign.setEnabledFlag("Y");
            if (emAssign.getAssignDatetime() == null) {
                emAssign.setAssignDatetime(new Date());
            }
            emAssignService.saveAssignAndProcess(emAssign, user);
            map.put("msg", "success");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg", "fail");
        }
        return map;
    }

    @Override
    public Map<String, Object> saveComplete(EmProcess emProcess, HttpServletRequest request) {
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
