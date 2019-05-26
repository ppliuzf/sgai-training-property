package com.sgai.property.common.service.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.sgai.common.persistence.Page;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.common.service.BaseEmployeeService;
import com.sgai.property.commonService.client.RedisClient;
import com.sgai.property.commonService.entity.EmpInfo;
import com.sgai.property.commonService.service.OrganServiceImpl;
import com.sgai.property.common.constants.Constants;
import com.sgai.property.commonService.vo.DeptVo;
import com.sgai.property.commonService.vo.EmpInfoVo;
import com.sgai.property.commonService.vo.EmpSimpleInfoVo;
import com.sgai.property.commonService.service.EmpInfoServiceImpl;
import com.sgai.property.commonService.vo.organ.OrganUserVo;
import com.sgai.property.ctl.entity.CtlEmp;
import com.sgai.property.ctl.service.CtlEmpService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 用户基础数据.
 *
 * @author ppliu
 * created in 2018/12/21 13:33
 */
@Service
public class BaseEmployeeServiceImpl implements BaseEmployeeService {
    @Autowired
    private EmpInfoServiceImpl empInfoService;
    @Autowired
    private OrganServiceImpl organService;
    @Autowired
    private RedisClient redisClient;
    @Autowired
    private CtlEmpService ctlEmpService;

    @Override
    public EmpInfoVo getEmpInfoById(Long comId, String eiId) {
        Object object = redisClient.get(MessageFormat.format(Constants.EMP_KEY, comId.toString()) + eiId);
        if (object != null) {
            return JSON.parseObject((String) object, EmpInfoVo.class);
        }
        EmpInfoVo empInfoVo = new EmpInfoVo();
        EmpInfo empInfo = empInfoService.getById(eiId);
        if (empInfo != null) {
            BeanUtils.copyProperties(empInfo, empInfoVo);
            Object result = redisClient.get(MessageFormat.format(Constants.DEPT_KEY, empInfo.getOrgId()) + empInfo.getDeptId());
            if (result != null) {
                DeptVo deptVo = JSON.parseObject((String) result, DeptVo.class);
                empInfoVo.setPathDeptId(deptVo.getPathDeptId());
                empInfoVo.setPathDeptName(deptVo.getPathDeptName());
            }
            redisClient.set(MessageFormat.format(Constants.EMP_KEY, comId.toString()) + eiId, JSON.toJSONString(empInfoVo), Constants.ORGAN_EXPIRE);
        }
        return empInfoVo;
    }

    @Override
    public List<EmpInfo> getEmpInfoByEiIds(Long comId, List<Long> eiIds) {
        List<String> eiIdstr = Lists.newArrayList();
        for (Long eiId : eiIds) {
            eiIdstr.add(eiId.toString());
        }
        List<EmpInfo> empInfoList = empInfoService.getByListId(eiIdstr);
        return empInfoList;
    }

    @Override
    public EmpInfo getEmpInfoByfeedId(Long comId, String feedId) {
        Object object = redisClient.get(MessageFormat.format(Constants.EMP_FEED_KEY, comId.toString()) + feedId);
        if (object != null) {
            return JSON.parseObject((String) object, EmpInfo.class);
        }
        EmpInfo empInfo = new EmpInfo();
        empInfo.setFeedId(feedId);
        empInfo = empInfoService.get(empInfo);
        if (empInfo != null) {
            redisClient.set(MessageFormat.format(Constants.EMP_FEED_KEY, comId.toString()) + feedId, JSON.toJSONString(empInfo), Constants.ORGAN_EXPIRE);
            return empInfoService.get(empInfo);
        }
        return null;

    }

    @Override
    public Page<EmpInfo> searchEmpInfoByName(Long comId, String eiEmpName, Integer pageNum, Integer pageSize) {
        EmpInfo empInfo = new EmpInfo();
        empInfo.setEiEmpName(eiEmpName);
        empInfo.setOrgId(comId);
        Page<EmpInfo> pageInfo = empInfoService.searchEmpInfoList(empInfo, pageNum.intValue(), pageSize.intValue());
        return pageInfo;
    }

    @Override
    public List<EmpSimpleInfoVo> findEmpInfoByDeptId(Long comId, List<Long> deptIds, Integer cascade) {
        boolean cascadebool = true;
        if (cascade == null || cascade == 0) {
            cascadebool = false;
        }
        Map<Long, EmpSimpleInfoVo> userMap = new HashMap(20);
        EmpSimpleInfoVo empInfo = null;
        for (Long deptId : deptIds) {
            List<OrganUserVo> userVoList = organService.getUserByDeptId(comId.toString(), deptId, cascadebool);
            if (userVoList != null && userVoList.size() > 0) {
                for (OrganUserVo organUserVo : userVoList) {
                    empInfo = new EmpSimpleInfoVo();
                    empInfo.setEiId(organUserVo.getId());
                    empInfo.setEmail(organUserVo.getEmail());
                    empInfo.setPhoto(organUserVo.getPhoto());
                    empInfo.setUserName(organUserVo.getUserName());
                    empInfo.setUserCode(organUserVo.getUserCode());
                    if (organUserVo.getFeedIds() != null && organUserVo.getFeedIds().size() > 0) {
                        empInfo.setFeedId(organUserVo.getFeedIds().get(0));
                    }
                    userMap.put(organUserVo.getId(), empInfo);
                }
            }
        }
        List<EmpSimpleInfoVo> empInfoList = new LinkedList<>();
        empInfoList.addAll(userMap.values());
        return empInfoList;
    }

    @Override
    public List<EmpInfo> getOrgEveryOne(String orgId, String appId, String accessId, String accessSecret) {
        return organService.getOrgEveryOne(orgId, appId, accessId, accessSecret);
    }

    @Override
    public Page<CtlEmp> getSgaiEmpDtoList(String sgaiToken, Integer pageNo, Integer pageSize, String deptCode, String empCode, String lastname) {
        CtlEmp ctlEmp = new CtlEmp();
        ctlEmp.setDeptCode(deptCode);
        ctlEmp.setEmpCode(empCode);
        ctlEmp.setLastname(lastname);
        ctlEmp.setComCode(UserServletContext.getUserInfo().getComCode());
        Page<CtlEmp> page = ctlEmpService.findPage(new Page<>(pageNo, pageSize), ctlEmp);
        return page;
    }
}
