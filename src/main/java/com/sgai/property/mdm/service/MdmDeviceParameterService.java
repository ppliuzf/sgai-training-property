package com.sgai.property.mdm.service;

import com.sgai.common.persistence.Page;
import com.sgai.common.service.CrudServiceExt;
import com.sgai.property.ctl.service.DeleteRulesUtils;
import com.sgai.property.mdm.dao.MdmDeviceParameterDao;
import com.sgai.property.mdm.entity.MdmBrandInfo;
import com.sgai.property.mdm.entity.MdmDeviceParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName: MdmDeviceParameterService
 * com.sgai.property.commonService.vo;(设备运行参数Service)
 *
 * @author yangyz
 * Date 2017年12月30日
 * Company 首自信--智慧城市创新中心
 */
@Service
@Transactional
public class MdmDeviceParameterService extends CrudServiceExt<MdmDeviceParameterDao, MdmDeviceParameter> {

    @Autowired
    private DeleteRulesUtils deleteRulesUtils;

    public MdmDeviceParameter get(String id) {
        return super.get(id);
    }

    public List<MdmDeviceParameter> findList(MdmDeviceParameter mdmDeviceParameter) {
        return super.findList(mdmDeviceParameter);
    }

    public Page<MdmDeviceParameter> findPage(Page<MdmDeviceParameter> page, MdmDeviceParameter mdmDeviceParameter) {
        return super.findPage(page, mdmDeviceParameter);
    }

    @Transactional(readOnly = false)
    public void save(MdmDeviceParameter mdmDeviceParameter) {
        super.save(mdmDeviceParameter);
    }

    @Transactional(readOnly = false)
    public void delete(MdmDeviceParameter mdmDeviceParameter) {
        super.delete(mdmDeviceParameter);
    }

    /**
     * saveDeviceParameter:(保存设备运行参数).
     *
     * @param profCode
     * @param profName
     * @param classCode
     * @param className
     * @param deviceParams
     * @param remarks
     * @return :Map<String,Object>
     * @author yangyz
     * @since JDK 1.8
     */
    public Map<String, Object> saveDeviceParameter(String profCode, String profName, String classCode, String className, String deviceParams, String remarks) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<String> codeList = new ArrayList<String>();
        List<String> namesList = new ArrayList<String>();
        String[] content = deviceParams.split(";");
        for (String str : content) {
            String[] params = str.split(",");
            MdmDeviceParameter info = new MdmDeviceParameter();
            info.setDeviceProfCode(profCode);
            info.setDeviceClassCode(classCode);
            info.setParamCode(params[0]);
            List<MdmDeviceParameter> list = super.findList(info);
            info.setParamCode("");
            info.setParamName(params[1]);
            List<MdmDeviceParameter> nameList = super.findList(info);


            codeList.add(params[0]);
            namesList.add(params[1]);
        }
        for (String str : content) {
            String[] params = str.split(",");
            MdmDeviceParameter info = new MdmDeviceParameter();
            info.setDeviceProfCode(profCode);
            info.setDeviceProfName(profName);
            info.setDeviceClassCode(classCode);
            info.setDeviceClassName(className);
            info.setParamCode(params[0]);
            info.setParamName(params[1]);
            info.setSwitchFlag(params[2]);
            info.setEnabledFlag("Y");
            info.setRemarks(remarks);
            save(info);
        }
        map.put("msg", "success");
        map.put("classCode", classCode);
        return map;
    }

    /**
     * deleteDeviceParameter:(删除设备运行参数).
     *
     * @param ids
     * @return :Map<String,Object>
     * @author yangyz
     * @since JDK 1.8
     */
    public Map<String, Object> deleteDeviceParameter(String ids) {
        Map<String, Object> map = new HashMap<String, Object>();
        String idss[] = ids.split(",");
        List<MdmDeviceParameter> list = new ArrayList<MdmDeviceParameter>();
        List<String> newList = new ArrayList<String>();
        for (String id : idss) {
            if (id != null && !id.equals("")) {
                newList.add(id);
                MdmDeviceParameter info = super.get(id);
                list.add(info);
            }
        }
        Map<String, String> resultMap = deleteRulesUtils.checkBeforeDelete(MdmBrandInfo.class, newList);
        if ("true".equals(resultMap.get("value"))) {
            List<MdmDeviceParameter> finalList = batchDelete(list);
            if (finalList.size() > 0) {
                map.put("classCode", finalList.get(0).getDeviceClassCode());
                map.put("msg", "删除成功!");
            } else {
                map.put("msg", "删除失败！");
            }
            map.put("result", "success");
        } else {
            map.put("msg", resultMap.get("description"));
            map.put("result", "fail");
        }
        return map;
    }

}
