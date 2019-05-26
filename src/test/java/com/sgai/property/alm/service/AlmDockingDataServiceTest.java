package com.sgai.property.alm.service;

import com.sgai.property.alm.entity.AlmDockingData;
import com.sgai.property.application.SgaiPropertyApplication;
import com.sgai.property.mq.Sender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author ppliu
 * created in 2019/3/27 15:22
 */
@SpringBootTest(classes = SgaiPropertyApplication.class)
@RunWith(SpringRunner.class)
public class AlmDockingDataServiceTest {

    @Autowired
    private AlmDockingDataService almDockingDataService;
    @Autowired
    private Sender sender;

    @Test
    public void test1() {
        List<AlmDockingData> almDockingDataList = almDockingDataService.selectAll();
        almDockingDataList.forEach(almDockingData -> System.out.println(almDockingData.toString()));
    }
}
