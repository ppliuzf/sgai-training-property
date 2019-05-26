package com.sgai.property.common.service.impl;

import com.alibaba.fastjson.JSON;
import com.sgai.property.common.service.BaseDepartmentService;
import com.sgai.property.commonService.client.RedisClient;
import com.sgai.property.common.constants.Constants;
import com.sgai.property.commonService.vo.DeptEmpVo;
import com.sgai.property.commonService.vo.DeptVo;
import com.sgai.property.ctl.entity.CtlDept;
import com.sgai.property.ctl.service.CtlDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 部门基础数据.
 *
 * @author ppliu
 * created in 2018/12/21 13:32
 */
@Service
public class BaseDepartmentServiceImpl implements BaseDepartmentService {
    @Autowired
    private RedisClient redisClient;
    @Autowired
    private CtlDeptService ctlDeptService;

    @Override
    public DeptVo getRootDeptInfo(Long companyId) {
        Object result = redisClient.get(MessageFormat.format(Constants.DEPT_ROOT_KEY, companyId.toString()));
        return JSON.parseObject((String) result, DeptVo.class);
    }

    @Override
    public DeptVo getDeptInfoById(Long companyId, Long departmentId) {
        Object result = redisClient.get(MessageFormat.format(Constants.DEPT_INFO_KEY, companyId.toString()) + departmentId);
        return JSON.parseObject((String) result, DeptVo.class);
    }

    @Override
    public List<DeptVo> getDeptInfoByIds(Long companyId, List<Long> departmentIds) {
        List<DeptVo> deptVoList = new LinkedList<>();
        for (Long departmentId : departmentIds) {
            Object result = redisClient.get(MessageFormat.format(Constants.DEPT_INFO_KEY, companyId.toString()) + departmentId);
            if (result != null) {
                deptVoList.add(JSON.parseObject((String) result, DeptVo.class));
            }
        }
        return deptVoList;
    }

    @Override
    public Map<Long, Integer> getDeptEmpNumByIds(Long companyId, List<Long> departmentIds) {
        Map<Long, Integer> resultMap = new LinkedHashMap<>();
        for (Long departmentId : departmentIds) {
            Object result = redisClient.get(MessageFormat.format(Constants.DEPT_INFO_KEY, companyId.toString()) + departmentId);
            if (result != null) {
                DeptVo deptVo = JSON.parseObject((String) result, DeptVo.class);
                resultMap.put(departmentId, deptVo.getDeptNum());
            }
        }
        return resultMap;
    }

    @Override
    public DeptEmpVo getDeptListById(Long companyId, Long departmentId) {
        DeptEmpVo deptEmpVo = null;
        if (departmentId == null || departmentId == -1) {
            Object result = redisClient.get(MessageFormat.format(Constants.DEPT_ROOT_KEY, companyId.toString()));
            if (result != null) {
                deptEmpVo = JSON.parseObject((String) result, DeptEmpVo.class);
            }
        } else if (departmentId == 0) {  //1:取一级部门
            Object result = redisClient.get(MessageFormat.format(Constants.DEPT_ROOT_KEY, companyId.toString()));
            if (result != null) {
                DeptVo rootDept = JSON.parseObject((String) result, DeptVo.class);
                Object result1 = redisClient.get(MessageFormat.format(Constants.DEPT_LIST_KEY, companyId.toString()) + rootDept.getDeptId());
                if (result1 != null) {
                    return JSON.parseObject((String) result1, DeptEmpVo.class);
                }
            }
        } else {
            Object result = redisClient.get(MessageFormat.format(Constants.DEPT_LIST_KEY, companyId.toString()) + departmentId);
            if (result != null) {
                return JSON.parseObject((String) result, DeptEmpVo.class);
            }
        }
        return deptEmpVo;
    }

    @Override
    public List<CtlDept> findDeptsByCodes(String departmentCodes) {
        return ctlDeptService.findDeptsByCodes(departmentCodes);
    }
}
