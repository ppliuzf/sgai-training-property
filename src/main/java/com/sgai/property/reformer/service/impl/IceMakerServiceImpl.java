package com.sgai.property.reformer.service.impl;

import com.sgai.property.reformer.service.IceMakerService;
import com.sgai.property.reformer.service.RestTemplateService;
import com.sgai.property.reformer.xmlBean.IceMaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ppliu
 * created in 2019/1/25 11:44
 */
@Service
public class IceMakerServiceImpl implements IceMakerService {
    @Autowired
    private RestTemplateService restTemplateService;

    private IceMaker getByUrl(String url) {
        return restTemplateService.objGet(url, IceMaker.class);
    }

    @Override
    public List<IceMaker> getAllData() {
        List<IceMaker> iceMakerList = new ArrayList<>();
        iceMakerList.add(getIceMaker1());
        iceMakerList.add(getIceMaker2());
        iceMakerList.add(getIceMaker3());
        iceMakerList.add(getIceMaker4());
        iceMakerList.add(getIceMaker5());
        iceMakerList.add(getIceMaker6());
        iceMakerList.add(getIceMaker7());
        iceMakerList.add(getIceMaker8());
        return iceMakerList;
    }

    @Override
    public IceMaker getIceMaker1() {
        IceMaker iceMaker = getByUrl("http://10.111.1.193:8001/obix/config/Drivers/AST_OPC/OpcClientDevice/points/RoHall/yunxing1/out/");
        iceMaker.setName("1号制冰机");
        iceMaker.setStatus(iceMaker.getVal() ? "0" : "1");
        return iceMaker;
    }

    @Override
    public IceMaker getIceMaker2() {
        IceMaker iceMaker = getByUrl("http://10.111.1.193:8001/obix/config/Drivers/AST_OPC/OpcClientDevice/points/RoHall/yunxing2/out/");
        iceMaker.setName("2号制冰机");
        iceMaker.setStatus(iceMaker.getVal() ? "0" : "1");
        return iceMaker;
    }

    @Override
    public IceMaker getIceMaker3() {
        IceMaker iceMaker = getByUrl("http://10.111.1.193:8001/obix/config/Drivers/AST_OPC/OpcClientDevice/points/FsHall/yunxing1/out/");
        iceMaker.setName("3号制冰机");
        iceMaker.setStatus(iceMaker.getVal() ? "0" : "1");
        return iceMaker;
    }

    @Override
    public IceMaker getIceMaker4() {
        IceMaker iceMaker = getByUrl("http://10.111.1.193:8001/obix/config/Drivers/AST_OPC/OpcClientDevice/points/FsHall/yunxing2/out/");
        iceMaker.setName("4号制冰机");
        iceMaker.setStatus(iceMaker.getVal() ? "0" : "1");
        return iceMaker;
    }

    @Override
    public IceMaker getIceMaker5() {
        IceMaker iceMaker = getByUrl("http://10.111.1.193:8001/obix/config/Drivers/AST_OPC/OpcClientDevice/points/CHall/yunxing1/out/");
        iceMaker.setName("5号制冰机");
        iceMaker.setStatus(iceMaker.getVal() ? "0" : "1");
        return iceMaker;
    }

    @Override
    public IceMaker getIceMaker6() {
        IceMaker iceMaker = getByUrl("http://10.111.1.193:8001/obix/config/Drivers/AST_OPC/OpcClientDevice/points/CHall/yunxing2/out/");
        iceMaker.setName("6号制冰机");
        iceMaker.setStatus(iceMaker.getVal() ? "0" : "1");
        return iceMaker;
    }

    @Override
    public IceMaker getIceMaker7() {
        IceMaker iceMaker = getByUrl("http://10.111.1.193:8001/obix/config/Drivers/AST_OPC/OpcClientDevice/points/IhHall/yunxing1/out/");
        iceMaker.setName("7号制冰机");
        iceMaker.setStatus(iceMaker.getVal() ? "0" : "1");
        return iceMaker;
    }

    @Override
    public IceMaker getIceMaker8() {
        IceMaker iceMaker = getByUrl("http://10.111.1.193:8001/obix/config/Drivers/AST_OPC/OpcClientDevice/points/IhHall/yunxing2/out/");
        iceMaker.setName("8号制冰机");
        iceMaker.setStatus(iceMaker.getVal() ? "0" : "1");
        return iceMaker;
    }
}
