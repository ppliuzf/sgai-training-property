package com.sgai.property.ruag.service;

import com.sgai.common.persistence.Page;
import com.sgai.common.service.CrudServiceExt;
import com.sgai.property.mdm.entity.MdmDeviceParameter;
import com.sgai.property.mdm.service.MdmDeviceParameterService;
import com.sgai.property.mq.Sender;
import com.sgai.property.mq.entity.DeviceParamSender;
import com.sgai.property.ruag.dao.RuagLinkaageNextDeviceDao;
import com.sgai.property.ruag.entity.RuagDeviceCalendarInstction;
import com.sgai.property.ruag.entity.RuagLinkaageNextDevice;
import com.sgai.property.ruag.entity.RuagLinkageDeviceParamSet;
import com.sgai.property.ruag.entity.RuagLinkageRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 联动后置设备Service
 *
 * @author yangyz
 * @version 2018-01-02
 */
@Service
@Transactional
public class RuagLinkaageNextDeviceService extends CrudServiceExt<RuagLinkaageNextDeviceDao, RuagLinkaageNextDevice> {
    @Autowired
    private Sender sender;
    @Autowired
    private RuagLinkageDeviceParamSetService ruagLinkageDeviceParamSetService;
    @Autowired
    private RuagDeviceCalendarInstctionService ruagDeviceCalendarInstctionService;
    @Autowired
    private RuagLinkageRuleService RuagLinkageRuleService;
    @Autowired
    private MdmDeviceParameterService mdmDeviceParameterService;
    private static final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
    private static final SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");

    public RuagLinkaageNextDevice get(String id) {
        return super.get(id);
    }

    public List<RuagLinkaageNextDevice> findList(RuagLinkaageNextDevice ruagLinkaageNextDevice) {
        return super.findList(ruagLinkaageNextDevice);
    }

    public Page<RuagLinkaageNextDevice> findPage(Page<RuagLinkaageNextDevice> page, RuagLinkaageNextDevice ruagLinkaageNextDevice) {
        return super.findPage(page, ruagLinkaageNextDevice);
    }

    @Transactional(readOnly = false)
    public void save(RuagLinkaageNextDevice ruagLinkaageNextDevice) {
        super.save(ruagLinkaageNextDevice);
    }

    @Transactional(readOnly = false)
    public void delete(RuagLinkaageNextDevice ruagLinkaageNextDevice) {
        super.delete(ruagLinkaageNextDevice);
    }

    public Map<String, Object> saveLinkaageNextDevice(RuagLinkaageNextDevice ruagLinkaageNextDevice) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (ruagLinkaageNextDevice.getId() != null && !"".equals(ruagLinkaageNextDevice.getId())) {
            super.save(ruagLinkaageNextDevice);
            map.put("msg", "success");
        } else {
            RuagLinkaageNextDevice info = new RuagLinkaageNextDevice();
            info.setLinkageName(ruagLinkaageNextDevice.getLinkageName());
            List<RuagLinkaageNextDevice> list = super.findList(info);
            if (list.size() > 0) {
                map.put("msg", "havaData");
            } else {
                ruagLinkaageNextDevice.setEnabledFlag("Y");
                super.save(ruagLinkaageNextDevice);
                map.put("msg", "success");
            }
        }
        return map;
    }

    public List<RuagLinkaageNextDevice> findJoinFrontList(RuagLinkaageNextDevice ruagLinkaageNextDevice) {
        return dao.findJoinFrontList(ruagLinkaageNextDevice);
    }

    /**
     * @param @param rule    参数
     * @return void    返回类型
     * @throws ParseException
     * @throws
     * @Title: sendNextDeviceIns
     * @com.sgai.property.commonService.vo;(发送后置设备指令)
     */
    public boolean SendDevicesNextIns(RuagLinkageRule rule) throws ParseException {
        //后置设备
        RuagLinkaageNextDevice ruagLinkaageNextDevice = new RuagLinkaageNextDevice();
        ruagLinkaageNextDevice.setLinkageCode(rule.getLinkageCode());
        List<RuagLinkaageNextDevice> list = findList(ruagLinkaageNextDevice);
        //

        for (RuagLinkaageNextDevice ruagLinkaageNextDevice2 : list) {
            //后置设备参数
            RuagLinkageDeviceParamSet ruagLinkageDeviceParamSet = new RuagLinkageDeviceParamSet();
            ruagLinkageDeviceParamSet.setLinkageRuleId(rule.getLinkageCode());
            ruagLinkageDeviceParamSet.setDeviceCode(ruagLinkaageNextDevice2.getDeviceCode());
            List<RuagLinkageDeviceParamSet> listParamSet = ruagLinkageDeviceParamSetService.findList(ruagLinkageDeviceParamSet);
            //排序,开关属性优先.
            listParamSet = listParamSet.stream().sorted(Comparator.comparing(RuagLinkageDeviceParamSet::getSwitchFlag).reversed()).collect(Collectors.toList());

            for (RuagLinkageDeviceParamSet ruagLinkageDeviceParamSet2 : listParamSet) {
                RuagDeviceCalendarInstction ruagDeviceCalendarInstction = new RuagDeviceCalendarInstction();
                MdmDeviceParameter mdmDeviceParameter = new MdmDeviceParameter();
                mdmDeviceParameter.setParamCode(ruagLinkageDeviceParamSet2.getParameterId());
                List<MdmDeviceParameter> findList = mdmDeviceParameterService.findList(mdmDeviceParameter);
                ruagDeviceCalendarInstction.setModelId(rule.getLinkageCode());
                ruagDeviceCalendarInstction.setModelName(rule.getLinkageName());
                ruagDeviceCalendarInstction.setControlCode("link");
                ruagDeviceCalendarInstction.setControlType("联动规则");
                ruagDeviceCalendarInstction.setSpaceCode(ruagLinkaageNextDevice2.getSpaceCode());
                ruagDeviceCalendarInstction.setSpaceName(ruagLinkaageNextDevice2.getSpaceName());
                ruagDeviceCalendarInstction.setProfCode(ruagLinkaageNextDevice2.getProfCode());
                ruagDeviceCalendarInstction.setProfName(ruagLinkaageNextDevice2.getProfName());
                ruagDeviceCalendarInstction.setDeviceCode(ruagLinkaageNextDevice2.getDeviceCode());
                ruagDeviceCalendarInstction.setDeviceName(ruagLinkaageNextDevice2.getDeviceName());
                ruagDeviceCalendarInstction.setParameterId(ruagLinkageDeviceParamSet2.getParameterId());
                ruagDeviceCalendarInstction.setParameterName(findList.get(0).getParamName());
                ruagDeviceCalendarInstction.setInstructionStatus(1L);
                ruagDeviceCalendarInstction.setEnabledFlag("Y");
                ruagDeviceCalendarInstction.setParameterValue(ruagLinkageDeviceParamSet2.getParameterValue());
                ruagDeviceCalendarInstction.setDciDate(sdf1.parse(sdf1.format(new Date())));
                ruagDeviceCalendarInstction.setTimePoint(sdf.format(new Date()));
                ruagDeviceCalendarInstction.setModelDegree("0");
                ruagDeviceCalendarInstction.setEffectiveStatus1("1");
                ruagDeviceCalendarInstction.setComCode(rule.getComCode());
                ruagDeviceCalendarInstction.setModuCode(rule.getModuCode());
                //todo 发送指令.
                //指令格式转化.
                sender.sendInstructionMessage(DeviceParamSender.instructionGenerate(ruagDeviceCalendarInstction));
                ruagDeviceCalendarInstctionService.save(ruagDeviceCalendarInstction);
            }

        }
        return true;
    }

    /**
     * @param @param  linkageCode
     * @param @return
     * @param @throws IOException
     * @param @throws ServletException
     * @param @throws ParseException    参数
     * @return boolean    返回类型
     * @throws
     * @Title: SelectDevicesNextIns
     * @com.sgai.property.commonService.vo;(将有冲突的指令置为无效)
     */
    public boolean SelectDevicesNextIns(String linkageCode) throws ParseException {
        //后置设备
        RuagLinkaageNextDevice ruagLinkaageNextDevice = new RuagLinkaageNextDevice();
        ruagLinkaageNextDevice.setLinkageCode(linkageCode);
        List<RuagLinkaageNextDevice> list = findList(ruagLinkaageNextDevice);

        for (RuagLinkaageNextDevice ruagLinkaageNextDevice2 : list) {
            //后置设备参数
            RuagLinkageDeviceParamSet ruagLinkageDeviceParamSet = new RuagLinkageDeviceParamSet();
            ruagLinkageDeviceParamSet.setLinkageRuleId(linkageCode);
            ruagLinkageDeviceParamSet.setDeviceCode(ruagLinkaageNextDevice2.getDeviceCode());
            List<RuagLinkageDeviceParamSet> listParamSet = ruagLinkageDeviceParamSetService.findList(ruagLinkageDeviceParamSet);
            //查找是否有其他模式相同设备相同参数未发送的指令存在，若存在，皆置为无效，以联动规则指令为主
            for (RuagLinkageDeviceParamSet ruagLinkageDeviceParamSet2 : listParamSet) {
                RuagDeviceCalendarInstction ruagDeviceCalendarInstction = new RuagDeviceCalendarInstction();
                ruagDeviceCalendarInstction.setSpaceCode(ruagLinkaageNextDevice2.getSpaceCode());
                ruagDeviceCalendarInstction.setProfCode(ruagLinkaageNextDevice2.getProfCode());
                ruagDeviceCalendarInstction.setDeviceCode(ruagLinkaageNextDevice2.getDeviceCode());
                ruagDeviceCalendarInstction.setParameterId(ruagLinkageDeviceParamSet2.getParameterId());
                ruagDeviceCalendarInstction.setInstructionStatus(0L);
                ruagDeviceCalendarInstction.setEnabledFlag("Y");
                ruagDeviceCalendarInstction.setDciDate(sdf1.parse(sdf1.format(new Date())));
                ruagDeviceCalendarInstction.setEffectiveStatus1("1");
                ruagDeviceCalendarInstction.setComCode(ruagLinkaageNextDevice2.getComCode());
                ruagDeviceCalendarInstction.setModuCode(ruagLinkaageNextDevice2.getModuCode());
                Map<String, String> sqlMap = new HashMap<String, String>();
                sqlMap.put("sql", "a.model_id!=" + "'" + linkageCode + "'");
                ruagDeviceCalendarInstction.setSqlMap(sqlMap);
                //查找出有冲突的指令，若是存在设置为无效
                List<RuagDeviceCalendarInstction> conflictList = ruagDeviceCalendarInstctionService.findList(ruagDeviceCalendarInstction);
                if (conflictList.size() > 0) {
                    for (RuagDeviceCalendarInstction ruagDeviceCalendarInstction2 : conflictList) {
                        ruagDeviceCalendarInstction2.setEffectiveStatus1("0");
                        ruagDeviceCalendarInstctionService.save(ruagDeviceCalendarInstction2);
                    }
                }

            }

        }
        return true;
    }
}
