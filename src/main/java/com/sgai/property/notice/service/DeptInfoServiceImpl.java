package com.sgai.property.notice.service;

import java.util.ArrayList;
import java.util.List;

import com.sgai.property.common.service.BaseDepartmentService;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sgai.property.commonService.vo.DeptVo;
import com.sgai.property.commonService.vo.Response;

@Service
public class DeptInfoServiceImpl {
    private static final Logger logger = LogManager.getLogger(DeptInfoServiceImpl.class);

    @Autowired
    private BaseDepartmentService baseDepartmentService;
//	@Value("${commons.service}")
//	private String commonsUrl;


    public List<DeptVo> getDeptInfoByIds(String comId, List<String> deptIds) {
        List<Long> ids = new ArrayList<>();
        if (!deptIds.isEmpty()) {
            for (String id : deptIds) {
                if (StringUtils.isNotEmpty(id)) {
                    ids.add(Long.parseLong(id));
                }
            }
        }
        return baseDepartmentService.getDeptInfoByIds(Long.valueOf(comId), ids);
    }

    public DeptVo getRootDeptInfo(Long comId) {
        return baseDepartmentService.getRootDeptInfo(comId);
    }

    public DeptVo getDeptInfoById(String comId, String deptId) {
        return baseDepartmentService.getDeptInfoById(Long.valueOf(comId), Long.valueOf(deptId));
    }

}