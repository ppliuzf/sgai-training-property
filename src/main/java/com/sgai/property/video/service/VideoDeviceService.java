package com.sgai.property.video.service;

import com.sgai.property.video.entity.VideoDevice;
import com.szx.core.service.MapperService;

import java.util.List;

/**
 * @author ppliu
 * created in 2019/4/2 14:51
 */
public interface VideoDeviceService extends MapperService<VideoDevice> {
    List<VideoDevice> getByIds(String ids);
}
