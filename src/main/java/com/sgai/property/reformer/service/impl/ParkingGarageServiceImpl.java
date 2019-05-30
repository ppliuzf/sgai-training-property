package com.sgai.property.reformer.service.impl;

import com.sgai.property.alm.vo.Packing;
import com.sgai.property.park.entity.InOutRecord;
import com.sgai.property.park.request.ParkingLotInfoRequest;
import com.sgai.property.park.response.ParkingLotInfoResponse;
import com.sgai.property.park.service.InOutRecordService;
import com.sgai.property.park.service.ParkSpaceService;
import com.sgai.property.reformer.service.ParkingGarageService;
import com.sgai.property.reformer.service.RestTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author ppliu
 * created in 2019/1/15 14:05
 */
@Service
public class ParkingGarageServiceImpl implements ParkingGarageService {
    @Autowired
    private ParkSpaceService parkSpaceService;
    @Autowired
    private RestTemplateService restTemplateService;
    @Autowired
    private InOutRecordService inOutRecordService;
    private static final String BASE_URL = "http://10.111.1.193:8001/obix/config/Folder/Pushdata/PARK/%s/out/";

    @Override
    public BigDecimal totalflowDay() {
        return restTemplateService.getWithUrl(String.format(BASE_URL, "totalflowDay"));
    }

    @Override
    public BigDecimal enter() {
        return restTemplateService.getWithUrl(String.format(BASE_URL, "enter"));
    }

    @Override
    public BigDecimal out() {
        return restTemplateService.getWithUrl(String.format(BASE_URL, "out"));
    }

    @Override
    public BigDecimal totalNum() {
        return restTemplateService.getWithUrl(String.format(BASE_URL, "totalNum"));
    }

    @Override
    public BigDecimal freeNum() {
        return restTemplateService.getWithUrl(String.format(BASE_URL, "freeNum"));
    }

    @Override
    public BigDecimal useNum() {
        return restTemplateService.getWithUrl(String.format(BASE_URL, "useNum"));
    }

    @Override
    public Packing getData() {
        try {
            ParkingLotInfoResponse parkingLotInfoResponse = parkSpaceService.getParkingLotInfo(new ParkingLotInfoRequest(""));
            Example example = new Example(InOutRecord.class);
            example.createCriteria().andCondition("to_days(pass_time) = to_days(now())");
            List<InOutRecord> inOutRecordList = inOutRecordService.selectByExample(example);
            Packing packing = new Packing();
            packing.setTotalNum(new BigDecimal(parkingLotInfoResponse.getTotalNum()));
            packing.setFreeNum(new BigDecimal(parkingLotInfoResponse.getTotalRemainNum()));
            packing.setUseNum(new BigDecimal(parkingLotInfoResponse.getTotalStopNum()));
            packing.setTotalflowDay(new BigDecimal(inOutRecordList.size()));
            packing.setEnter(new BigDecimal(inOutRecordList.stream().filter(inOutRecord -> "0".equals(inOutRecord.getInOrOut())).count()));
            packing.setOut(new BigDecimal(inOutRecordList.stream().filter(inOutRecord -> "1".equals(inOutRecord.getInOrOut())).count()));
            return packing;
        } catch (Exception e) {
            System.out.println("停车服务又关了！！！！！！！！！！！！！！！！！！");
            return Packing.exception();
        }
    }
}
