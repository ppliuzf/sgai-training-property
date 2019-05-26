package com.sgai.property.reformer.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.sgai.property.application.SgaiPropertyApplication;
import com.sgai.property.reformer.consts.EnergyPosition;
import com.sgai.property.video.entity.VideoDevice;
import com.sgai.property.video.service.VideoDeviceService;
import com.szx.core.web.support.CommonResponse;
import com.szx.core.web.support.CommonResponseUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * @author ppliu
 * created in 2019/3/25 13:30
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SgaiPropertyApplication.class)
public class EnergyWeekServiceTest {
    @Autowired
    private EnergyWeekService energyWeekService;
    @Autowired
    private VideoDeviceService videoDeviceService;

    @Test
    public void test1() {
        List<VideoDevice> videoDeviceList = videoDeviceService.selectAll();
        Map<String, List<VideoDevice>> map = videoDeviceList.stream().collect(Collectors.groupingBy(VideoDevice::getPosition));
        CommonResponse commonResponse = CommonResponseUtil.success(map);
        System.out.println(JSON.toJSONString(commonResponse, SerializerFeature.WRITE_MAP_NULL_FEATURES));
        System.out.println(1);

    }
}
