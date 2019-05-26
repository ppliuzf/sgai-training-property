package com.sgai.property.common.service;


/**
 * @author ppliu
 * created in 2018/12/25 16:06
 */
public interface BaseTaskStatusUpdateService {
    Boolean updateTaskQualityStatus(String taskId, Long status, String eiIds);
}
