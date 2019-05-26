package com.sgai.property.common.service;

import com.sgai.property.commonService.dto.SpaceDto;

import java.util.List;

/**
 * 基础空间数据.
 *
 * @author ppliu
 * created in 2018/12/21 14:02
 */
public interface BaseSpaceService {
    /**
     * 获取所有空间主数据
     */
    List<SpaceDto> registAndCallBack();

    /**
     *获取用户的空间权限.
     */
    List<String> getAuthSpaceList(String userCode);

}
