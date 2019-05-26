package com.sgai.property.video.web;

import com.sgai.modules.login.annotation.PermessionLimit;
import com.sgai.property.video.entity.VideoDevice;
import com.sgai.property.video.service.VideoDeviceService;
import com.szx.core.web.support.CommonResponse;
import com.szx.core.web.support.CommonResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author ppliu
 * created in 2019/4/2 14:55
 */
@RestController
@RequestMapping("/admin/video")
public class VideoController {
    @Autowired
    private VideoDeviceService videoDeviceService;

    @RequestMapping("/getAllDevice")
    @PermessionLimit(limit = false)
    public CommonResponse getAllDevice() {
        List<VideoDevice> videoDeviceList = videoDeviceService.selectAll();
        Map<String, List<VideoDevice>> map = videoDeviceList.stream().collect(Collectors.groupingBy(VideoDevice::getPosition));
        return CommonResponseUtil.success(map);
    }
}
