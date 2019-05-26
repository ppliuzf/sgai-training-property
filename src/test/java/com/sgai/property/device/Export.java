package com.sgai.property.device;

import com.sgai.common.persistence.Page;
import com.sgai.property.application.SgaiPropertyApplication;
import com.sgai.property.mdm.dto.MdmDevicesUseInfoVo;
import com.sgai.property.mdm.entity.MdmMatInfo;
import com.sgai.property.mdm.service.MdmDevicesUseInfoService;
import com.sgai.property.mdm.service.MdmMatInfoService;
import com.sgai.property.util.ExportExcel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * 描述:
 *
 * @author ppliu
 * created in 2019/5/17 18:34
 */
@SpringBootTest(classes = SgaiPropertyApplication.class)
@RunWith(SpringRunner.class)
public class Export {

    @Autowired
    MdmDevicesUseInfoService mdmDevicesUseInfoService;
    @Autowired
    MdmMatInfoService mdmMatInfoService;


    @Test
    public void exportDevice() throws IOException {
        List<MdmDevicesUseInfoVo> voList = mdmDevicesUseInfoService.findPageVoByModel(new Page<MdmDevicesUseInfoVo>(1, 500000), new MdmDevicesUseInfoVo()).getList();
        try {
            new ExportExcel("设备基础数据", MdmDevicesUseInfoVo.class)
                    .setDataList(voList)
                    //.writeFile("E:设备基础数据.xlsx")
                    .write(new FileOutputStream("E:设备基础数据.xlsx"))
                    .dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void exportMat() throws IOException {
        List<MdmMatInfo> infoList = mdmMatInfoService.findPage(new Page<MdmMatInfo>(1, 5000), new MdmMatInfo()).getList();
        try {
            new ExportExcel("设备基础数据", MdmMatInfo.class)
                    .setDataList(infoList)
                    //.writeFile("E:设备基础数据.xlsx")
                    .write(new FileOutputStream("E:物料基础数据.xlsx"))
                    .dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
