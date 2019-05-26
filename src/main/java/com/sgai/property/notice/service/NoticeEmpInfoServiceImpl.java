package com.sgai.property.notice.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sgai.common.persistence.Page;
import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.common.service.BaseEmployeeService;
import com.sgai.property.commonService.UserInfo;
import com.sgai.property.commonService.entity.EmpInfo;
import com.sgai.property.commonService.service.OrganServiceImpl;
import com.sgai.property.commonService.vo.EmpInfoVo;
import com.sgai.property.commonService.vo.EmpSimpleInfoVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 146584
 * @date 2017-11-8 17:44
 */

@Service
public class NoticeEmpInfoServiceImpl {
    private static final Logger logger = LogManager.getLogger(NoticeEmpInfoServiceImpl.class);

    @Value("${appInfo.accessId}")
    private String accessId;
    @Value("${appInfo.accessSecret}")
    private String accessSecret;
    @Autowired
    private BaseEmployeeService baseEmployeeService;
    @Autowired
    private OrganServiceImpl organService;

    public EmpInfoVo getEmpInfoByEiId(String comId, Long eiId) {
        return baseEmployeeService.getEmpInfoById(Long.valueOf(comId), eiId + "");
    }

    public UserInfo getUserInfoByCode(String code) {
        String aToken = organService.getAToken(code, accessId, accessSecret);
        JSONObject json = organService.getUserInfo(code, aToken);
        UserInfo userInfo = JSON.parseObject(json.toJSONString(), UserInfo.class);
        userInfo.setUserId(userInfo.getId());
        return userInfo;
    }

    public LoginUser getLoginUserInfoByCode() {
        return UserServletContext.getUserInfo();
    }

    public EmpInfo getEmpInfoByFeedId(Long comId, String feedId) {
        return baseEmployeeService.getEmpInfoByfeedId(comId, feedId);
    }

    public List<EmpSimpleInfoVo> getEmpByDeptIds(String comId, List<String> deptIds, Integer cascade) {
//        RestTemplate restTemplate = new RestTemplate();
//        Response result = restTemplate.postForObject(url + "empInfo/findEmpInfoByDeptId?comId={comId}&cascade={cascade}", deptIds, Response.class, comId, cascade);
//        return JSON.parseArray(JSON.toJSONString(result.getData()), EmpSimpleInfoVo.class);
        List<Long> ids = new ArrayList<>();
        if (!deptIds.isEmpty()) {
            for (String id : deptIds) {
                if (StringUtils.isNotEmpty(id)) {
                    ids.add(Long.parseLong(id));
                }
            }
        }
        return baseEmployeeService.findEmpInfoByDeptId(Long.valueOf(comId), ids, cascade);
    }

    public List<EmpInfo> getEmpInfoByEiIds(String comId, List<String> eiIds) {
//        RestTemplate restTemplate = new RestTemplate();
//        Response result = restTemplate.postForObject(url + "empInfo/getEmpInfoByEiIds?comId={comId}", eiIds, Response.class, comId);
//        Object obj = result.getData();
//        return JSON.parseArray(JSON.toJSONString(result.getData()), EmpInfo.class);
        List<Long> ids = new ArrayList<>();
        if (!eiIds.isEmpty()) {
            for (String id : eiIds) {
                if (StringUtils.isNotEmpty(id)) {
                    ids.add(Long.parseLong(id));
                }
            }
        }
        return baseEmployeeService.getEmpInfoByEiIds(Long.valueOf(comId), ids);
    }

    public Page<EmpInfo> searchEmpInfoByName(String comId, String eiEmpName, Integer pageNum, Integer pageSize) {
        return baseEmployeeService.searchEmpInfoByName(Long.valueOf(comId), eiEmpName, pageNum, pageSize);
    }

}