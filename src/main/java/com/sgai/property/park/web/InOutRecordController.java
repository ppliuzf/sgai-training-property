package com.sgai.property.park.web;

import com.sgai.modules.login.annotation.PermessionLimit;
import com.sgai.property.alm.vo.ScreenVo;
import com.sgai.property.park.dto.IntervalRecord;
import com.sgai.property.park.entity.InOutRecord;
import com.sgai.property.park.response.InOutResponse;
import com.sgai.property.park.service.InOutRecordService;
import com.sgai.property.reformer.utils.JsonUtil;
import com.sgai.property.reformer.utils.PostUtils;
import com.sgai.property.socket.SocketResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * 车辆进出场记录接收接口.
 *
 * @author ppliu
 * created in 2019/1/18 9:18
 */
@RestController
@RequestMapping("admin/vehicle/upload")
public class InOutRecordController {
    @Autowired
    private InOutRecordService inOutRecordService;

    /**
     * 记录进出场.
     */
    @RequestMapping("/recordInOut")
    @PermessionLimit(limit = false)
    public String recordInOut(HttpServletRequest request) {
        try {
            String jsonString = PostUtils.fetchPostByTextPlain(request);
            InOutRecord inOutRecord = JsonUtil.jsonFormatToBean(jsonString, InOutRecord.class);
            inOutRecordService.insertSelective(inOutRecord);
            ScreenVo screenVo = new ScreenVo();
            screenVo.setType(3);
            screenVo.setIntervalOrderTime(inOutRecordService.getIntervalRecordToday().stream().sorted(Comparator.comparing(IntervalRecord::getTimeSorter)).collect(Collectors.toList()));
            screenVo.setIntervalOrderFlow(inOutRecordService.getIntervalRecordToday().stream().sorted(Comparator.comparing(IntervalRecord::getFlow).reversed()).limit(5).collect(Collectors.toList()));
            screenVo.setInOutRecord(inOutRecord);
            SocketResolver.sendInfo(screenVo.toString());
            return InOutResponse.success();
        } catch (Exception e) {
            e.printStackTrace();
            return InOutResponse.failed();
        }
    }
}