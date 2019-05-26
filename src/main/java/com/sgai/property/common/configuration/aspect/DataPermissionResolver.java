/**
 * Project Name:smart-common
 * File Name:DataAuthForMap.java
 * Package Name:com.sgai.common.persistence.interceptor
 * Date:2018年1月25日下午2:45:40
 * Copyright (c) 2018, 首自信--智慧城市创新中心.
 */

package com.sgai.property.common.configuration.aspect;

import com.sgai.common.utils.StringUtils;
import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.common.annotation.DataPermission;
import com.sgai.property.common.service.BaseSpaceService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;


/**
 * ppliu
 * 数据权限的配置.
 */
@Aspect
@Component
public class DataPermissionResolver {
    @Autowired
    private BaseSpaceService baseSpaceService;

    @Pointcut("@annotation(com.sgai.property.common.annotation.DataPermission)")
    public void dataAuthPointcut() {

    }

    @Before("dataAuthPointcut()")
    public void dataAuth(JoinPoint joinPoint) throws Throwable {
        Object obj = joinPoint.getArgs();
        //参数必须是Map类型的 否则无效
        if (obj != null && obj instanceof Map) {
            ((Map) obj).put("sql", dataAuthSql(joinPoint));
        } else {
            throw new Exception("需要数据权限过滤，需要查询方法的第一个参数为Map类型，且不能为NULL");
        }
    }

    // 拼接数据
    private String dataAuthSql(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        DataPermission annotation = signature.getMethod().getAnnotation(DataPermission.class);
        String tableAlias = annotation.tableAlias();
        if (!StringUtils.isEmpty(tableAlias)) {
            tableAlias += ".";
        } else {
            //默认情况下
            tableAlias = "p.";
        }
        StringBuilder dataAuthSql = new StringBuilder();
        LoginUser user = UserServletContext.getUserInfo();
        List<String> spaceIdList = baseSpaceService.getAuthSpaceList(user.getUserId());

        dataAuthSql.append(tableAlias);
        dataAuthSql.append("modu_code in ( ");
        spaceIdList.forEach(spaceId -> {
            dataAuthSql.append(spaceId);
            dataAuthSql.append(",");
        });
        dataAuthSql.deleteCharAt(dataAuthSql.lastIndexOf(","));
        dataAuthSql.append(")");
        System.out.println(dataAuthSql.toString());
        return dataAuthSql.toString();
    }

}

