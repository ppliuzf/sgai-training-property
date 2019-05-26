package com.sgai.property.video.service.impl;

import com.sgai.common.utils.StringUtils;
import com.sgai.property.video.entity.VideoDevice;
import com.sgai.property.video.service.VideoDeviceService;
import com.szx.core.service.AbstractMapperService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ppliu
 * created in 2019/4/2 14:52
 */
@Service
public class VideoDeviceServiceImpl extends AbstractMapperService<VideoDevice> implements VideoDeviceService {
    @Override
    public List<VideoDevice> getByIds(String ids) {
        if(StringUtils.isEmpty(ids)){
            return new ArrayList<>();
        }
        List<String> idList = Arrays.asList(ids.split(","));
        Example example = new Example(VideoDevice.class);
        example.createCriteria().andIn("id",idList);
        return selectByExample(example);
    }
}
