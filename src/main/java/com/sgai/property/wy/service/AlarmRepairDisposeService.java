package com.sgai.property.wy.service;

import com.sgai.property.alm.entity.AlmDockingData;
import com.sgai.property.mdm.entity.MdmDevicesUseInfo;
import com.sgai.property.mdm.entity.MdmSpaceTree;
import com.sgai.property.mdm.service.MdmDevicesUseInfoService;
import com.sgai.property.mdm.service.MdmSpaceTreeService;
import com.sgai.property.wy.dto.RepairConst;
import com.sgai.property.wy.entity.RepairInformation;
import com.sgai.property.wy.entity.RepairInformationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Service
public class AlarmRepairDisposeService {

    private static final String PARENT_CODE = "201801241622310335";

    @Autowired
    private RepairInformationService repairInformationService;
    @Autowired
    private MdmDevicesUseInfoService mdmDevicesUseInfoService;
    @Autowired
    private MdmSpaceTreeService mdmSpaceTreeService;

    public void alarmDispose(AlmDockingData alm) {
        MdmDevicesUseInfo mdmDevicesUseInfo = getMdmDevicesUserInfo(alm.getSource());
        List<String> spaceList = getSpaceBySpaceCode(mdmDevicesUseInfo.getSpaceCode());
        List<String> typeList = getRepairType(alm);
        RepairInformation r = new RepairInformation();
        r.setRepairPeopleId("000");
        r.setRepairPeopleName("系统");
        r.setRepairPhone("0000000");
        r.setRepairAddress(spaceList.get(1));
        r.setRepairAddressCode(spaceList.get(0));
        r.setRepairDetailAddress(spaceList.get(1));
        r.setIncidentRank("紧急");
        r.setRepairType(typeList.get(1));
        r.setRepairTypeCode(typeList.get(0));
        r.setRepairEquipment(mdmDevicesUseInfo.getDevicesName());
        r.setRepairEquipmentIds(mdmDevicesUseInfo.getDevicesCode());
        r.setFaultDescription(alm.getMsgText());
        r.setRepairStatus(RepairConst.repairSubmit.getIndex());
        r.setIncidentSource("4");
        r.setAppointmentTime(new Date());
        r.setAppointmentTimeGo(getNextDay(new Date()));
        r.setOrderNumber(getOrderNumber());
        repairInformationService.save(r);
    }

    private List<String> getRepairType(AlmDockingData alm) {
        String typeIds = "";
        String typeCodes = "";
        RepairInformationType type = repairInformationService.findByName(alm.getAlarmClass());
        if(type != null){
            if(!"0".equals(type.getParentCode())){
                RepairInformationType parentType = repairInformationService.findById(type.getParentCode());
                typeIds = parentType.getTypeId() + "-" + type.getTypeId();
                typeCodes = parentType.getTypeCode() + "-" + type.getTypeCode();
            }
        }
        List<String> list = new ArrayList<>();
        if("".equals(typeCodes)){
            typeCodes = alm.getAlarmClass();
        }
        list.add(typeIds);
        list.add(typeCodes);
        return list;
    }

    private List<String> getSpaceBySpaceCode(String spaceCode) {
        String spaceCodes = "";
        String spaceNames = "";
        MdmSpaceTree mdmSpaceTree = getMdmSpaceTree(spaceCode);
        if(PARENT_CODE.equals(mdmSpaceTree.getParentCode())){
            spaceCodes = mdmSpaceTree.getSpaceCode();
            spaceNames = mdmSpaceTree.getSpaceName();
        }else{
            MdmSpaceTree mdm = getMdmSpaceTree(mdmSpaceTree.getParentCode());
            if(PARENT_CODE.equals(mdm.getParentCode())){
                spaceCodes = mdm.getSpaceCode() + "-" + mdmSpaceTree.getSpaceCode();
                spaceNames = mdm.getSpaceCode() + "-" + mdmSpaceTree.getSpaceName();
            }else{
                MdmSpaceTree mdm2 = getMdmSpaceTree(mdm.getParentCode());
                if(PARENT_CODE.equals(mdm2.getParentCode())){
                    spaceCodes = mdm2.getSpaceCode() + "-" + mdm.getSpaceCode() + "-" + mdmSpaceTree.getSpaceCode();
                    spaceNames = mdm2.getSpaceName() + "-" +mdm.getSpaceName() + "-" + mdmSpaceTree.getSpaceName();
                }
            }
        }
       List<String> list = new ArrayList<>();
        list.add(spaceCodes);
        list.add(spaceNames);
        return list;
    }
    private MdmSpaceTree getMdmSpaceTree(String spaceCode){
        MdmSpaceTree mdmSpaceTree = new MdmSpaceTree();
        mdmSpaceTree.setSpaceCode(spaceCode);
        return mdmSpaceTreeService.getSpaceByCode(mdmSpaceTree);
    }

    public MdmDevicesUseInfo getMdmDevicesUserInfo(String driverCode) {
        MdmDevicesUseInfo mdm = new MdmDevicesUseInfo();
        mdm.setDevicesCode(driverCode);
        return mdmDevicesUseInfoService.getMdmDevicesUseInfo(mdm);
    }

    private String getOrderNumber() {
        Date d = new Date();
        SimpleDateFormat s= new SimpleDateFormat("yyyyMMddHHmmss");
        return s.format(d);
    }
    private Date getNextDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, +2);//+1今天的时间加一天
        date = calendar.getTime();
        return date;
    }

}
