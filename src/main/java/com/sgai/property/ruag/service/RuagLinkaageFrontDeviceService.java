package com.sgai.property.ruag.service;

import com.sgai.common.persistence.Page;
import com.sgai.common.service.CrudServiceExt;
import com.sgai.property.common.util.RedisUtil;
import com.sgai.property.ruag.dao.RuagLinkaageFrontDeviceDao;
import com.sgai.property.ruag.entity.RuagLinkaageFrontDevice;
import com.sgai.property.ruag.entity.RuagLinkageDeviceParamSet;
import com.sgai.property.ruag.entity.RuagLinkageRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 联动前置设备Service
 *
 * @author yangyz
 * @version 2018-01-02
 */
@Service
@Transactional
public class RuagLinkaageFrontDeviceService extends CrudServiceExt<RuagLinkaageFrontDeviceDao, RuagLinkaageFrontDevice> {
    @Autowired
    private RuagLinkaageNextDeviceService ruagLinkaageNextDeviceService;
    @Autowired
    private RuagLinkageRuleService ruagLinkageRuleService;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private RuagLinkageDeviceParamSetService ruagLinkageDeviceParamSetService;
    private SpelExpressionParser parser = new SpelExpressionParser();

    public RuagLinkaageFrontDevice get(String id) {
        return super.get(id);
    }

    public List<RuagLinkaageFrontDevice> findList(RuagLinkaageFrontDevice ruagLinkaageFrontDevice) {
        return super.findList(ruagLinkaageFrontDevice);
    }

    public Page<RuagLinkaageFrontDevice> findPage(Page<RuagLinkaageFrontDevice> page, RuagLinkaageFrontDevice ruagLinkaageFrontDevice) {
        return super.findPage(page, ruagLinkaageFrontDevice);
    }

    @Transactional(readOnly = false)
    public void save(RuagLinkaageFrontDevice ruagLinkaageFrontDevice) {
        super.save(ruagLinkaageFrontDevice);
    }

    @Transactional(readOnly = false)
    public void delete(RuagLinkaageFrontDevice ruagLinkaageFrontDevice) {
        super.delete(ruagLinkaageFrontDevice);
    }


    public Map<String, Object> saveLinkaageFrontDevice(RuagLinkaageFrontDevice ruagLinkaageFrontDevice) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (ruagLinkaageFrontDevice.getId() != null && !"".equals(ruagLinkaageFrontDevice.getId())) {
            super.save(ruagLinkaageFrontDevice);
            map.put("msg", "success");
        } else {
            RuagLinkaageFrontDevice info = new RuagLinkaageFrontDevice();
            info.setLinkageName(ruagLinkaageFrontDevice.getLinkageName());
            List<RuagLinkaageFrontDevice> list = super.findList(info);
            if (list.size() > 0) {
                map.put("msg", "havaData");
            } else {
                ruagLinkaageFrontDevice.setEnabledFlag("Y");
                super.save(ruagLinkaageFrontDevice);
                map.put("msg", "success");
            }
        }

        return map;
    }

    /**
     * @param @param  rule 联动规则对象
     * @param @throws IOException
     * @param @throws ServletException    参数
     * @return void    返回类型
     * @throws ParseException
     * @throws
     * @Title: analysisRule
     * @com.sgai.property.commonService.vo;(解析联动规则是否符合触发条件)
     */
    public boolean analysisRule(RuagLinkageRule rule) throws ParseException {
        //获取所有前置设备参数
        RuagLinkageDeviceParamSet paramSet = new RuagLinkageDeviceParamSet();
        paramSet.setLinkageRuleId(rule.getLinkageCode());
        paramSet.setFrontNextFlag("F");
        List<RuagLinkageDeviceParamSet> paramSetList = ruagLinkageDeviceParamSetService.findList(paramSet);

        for (RuagLinkageDeviceParamSet param : paramSetList) {
            String key = param.getDeviceCode().trim() + "-" + param.getParameterId().trim();
            Object redisData = redisUtil.get(key);
            String strRedisData = redisData == null ? "" : redisData.toString();
            String expression = strRedisData + param.getParameterValue();
            try {
                Boolean flag = parser.parseExpression(expression).getValue(Boolean.class) ;
                if (flag!= null && !flag) {
                    return false;
                }
            } catch (Exception e) {
                return false;
            }
        }

        //执行相关方法
        //如果前置设备的所有规则都符合就将后置设备的指令发送
        //todo 同时判断是否已经是发送过了
        boolean sendDevicesNextIns = ruagLinkaageNextDeviceService.SendDevicesNextIns(rule);
        return true;
    }

    public void scanRules(String path) throws IOException, ServletException, ParseException {
        //todo 联动规则
        List<Map<String, String>> ruleCodes = dao.findRules(path);
        if (ruleCodes.size() > 0) {
            List<RuagLinkageRule> rules = new ArrayList<>();
            //获取到符合要求的联动规则
            for (Map<String, String> code : ruleCodes) {
                RuagLinkageRule rule = ruagLinkageRuleService.getLinkageCode(code.get("LINKAGE_CODE"), code.get("COM_CODE"), code.get("MODU_CODE"));
                if (rule.getStatus().equals("Y")) {
                    rules.add(rule);
                }
            }
            if (rules.size() > 0) {
                ruagLinkageRuleService.scanRule(rules);
            }
        }

    }
}
