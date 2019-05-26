package com.sgai.property.ruag.web;

import com.sgai.common.persistence.Page;
import com.sgai.common.web.BaseController;
import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.modules.login.jwt.util.CommonResponse;
import com.sgai.modules.login.jwt.util.ResponseUtil;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.mdm.dto.MdmDevicesUseInfoVo;
import com.sgai.property.mdm.service.MdmDevicesUseInfoService;
import com.sgai.property.ruag.dao.RuagLinkageDeviceParamSetDao;
import com.sgai.property.ruag.entity.RuagDeviceCalendarInstction;
import com.sgai.property.ruag.entity.RuagLinkaageNextDevice;
import com.sgai.property.ruag.entity.RuagLinkageDeviceParamSet;
import com.sgai.property.ruag.service.RuagDeviceCalendarInstctionService;
import com.sgai.property.ruag.service.RuagLinkaageNextDeviceService;
import com.sgai.property.ruag.service.RuagLinkageDeviceParamSetService;
import com.sgai.property.ruag.service.RuagLinkageRuleSpaceService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 联动后置设备Controller
 *
 * @author yangyz
 * @version 2018-01-02
 */
@RestController
@RequestMapping(value = "ruag/ruag/ruagLinkaageNextDevice")
public class RuagLinkaageNextDeviceController extends BaseController {

    @Autowired
    private RuagLinkaageNextDeviceService ruagLinkaageNextDeviceService;
    @Autowired
    private MdmDevicesUseInfoService mdmDevicesUseInfoService;
    @Autowired
    private RuagDeviceCalendarInstctionService ruagDeviceCalendarInstctionService;
    @Autowired
    private RuagLinkageDeviceParamSetService ruagLinkageDeviceParamSetService;
    @Autowired
    private RuagLinkageDeviceParamSetDao ruagLinkageDeviceParamSetDao;
    @Autowired
    private RuagLinkageRuleSpaceService ruagLinkageRuleSpaceService;

    @ApiOperation(value = "联动后置设备分页", notes = "联动后置设备分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "linkageCode", value = "联动规则id", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "spaceName", value = "位置", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "profName", value = "专业", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "deviceName", value = "设备名称", required = false, paramType = "query", dataType = "String")
    })
    @RequestMapping(value = "/getListLinkaageNextDevice", method = RequestMethod.POST)
    public CommonResponse getListComp(
            String linkageCode,
            String spaceName,
            String profCode,
            String classCode,
            String deviceName,
            String spaceCodeNext,

            @RequestParam(value = "pageNo", required = true, defaultValue = "0") Integer pageNo,
            @RequestParam(value = "pageSize", required = true, defaultValue = "10") Integer pageSize,
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        RuagLinkaageNextDevice ruagLinkaageNextDevice = new RuagLinkaageNextDevice();
        Map<String, String> sqlMap = new HashMap<String, String>();
        if (StringUtils.isNotBlank(spaceCodeNext)) {
            sqlMap.put("sql", "A.space_code IN(" + spaceCodeNext.substring(0, spaceCodeNext.length() - 1) + ")");
            ruagLinkaageNextDevice.setSqlMap(sqlMap);
        }
        ruagLinkaageNextDevice.setLinkageCode(linkageCode);
        ruagLinkaageNextDevice.setSpaceName(spaceName);
        ruagLinkaageNextDevice.setProfCode(profCode);
        ruagLinkaageNextDevice.setClassCode(classCode);
        ruagLinkaageNextDevice.setDeviceName(deviceName);
        Page<RuagLinkaageNextDevice> page = ruagLinkaageNextDeviceService.findPage(new Page<RuagLinkaageNextDevice>(pageNo, pageSize), ruagLinkaageNextDevice);
        return ResponseUtil.successResponse(page);
    }

    @ApiOperation(value = "删除联动后置设备", notes = "删除联动后置设备")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键", required = false, paramType = "query", dataType = "String")
    })
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public CommonResponse delete(
            String ids,

            RedirectAttributes redirectAttributes
    ) throws IOException {
        Map<String, Object> map = new HashMap<String, Object>();
        String[] arr = ids.split(",");
        try {
            for (String id : arr) {
                RuagLinkageDeviceParamSet ruagLinkageDeviceParamSet = new RuagLinkageDeviceParamSet();
                ruagLinkageDeviceParamSet.setMasterId(id);
                ruagLinkageDeviceParamSetDao.deleteByMasterId(ruagLinkageDeviceParamSet);
                RuagLinkaageNextDevice ruagLinkaageNextDevice = new RuagLinkaageNextDevice();
                ruagLinkaageNextDevice.setId(id);
                ruagLinkaageNextDeviceService.delete(ruagLinkaageNextDevice);
            }
            map.put("msg", "success");
        } catch (Exception e) {

            map.put("msg", "faild");
            e.printStackTrace();
        }

        return ResponseUtil.successResponse(map);
    }

    @ApiOperation(value = "保存联动后置设备", notes = "保存联动后置设备")
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public CommonResponse save(
            RuagLinkaageNextDevice ruagLinkaageNextDevice,

            RedirectAttributes redirectAttributes
    ) throws IOException {

        Map<String, Object> data = new HashMap<String, Object>();
        ruagLinkaageNextDevice.setStatus("N");
        try {
            data = ruagLinkaageNextDeviceService.saveLinkaageNextDevice(ruagLinkaageNextDevice);
            data.put("msg", "success");
        } catch (Exception e) {
            data.put("msg", "failed");
            e.printStackTrace();
        }
        return ResponseUtil.successResponse(data);
    }

    @ApiOperation(value = "", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "linkageCode", value = "联动规则id", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "profName", value = "设备专业", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "devicesName", value = "设备名称", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "spaceName", value = "设备位置", required = false, paramType = "query", dataType = "String")
    })
    @RequestMapping(value = "/getNextByLinkageCode", method = RequestMethod.POST)
    public CommonResponse getNextByLinkageCode(
            String linkageCode,
            String profCode,
            String devicesName,
            String spaceName,
            String classCode,
            String spaceCode,
            String profCodes,

            @RequestParam(value = "pageNo", required = true, defaultValue = "0") Integer pageNo,
            @RequestParam(value = "pageSize", required = true, defaultValue = "10") Integer pageSize,
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        LoginUser user = UserServletContext.getUserInfo();
        String spaces = "";
        MdmDevicesUseInfoVo mdmDevicesUseInfoVo = new MdmDevicesUseInfoVo();
        mdmDevicesUseInfoVo.setModuCode(user.getModuCode());
        mdmDevicesUseInfoVo.setComCode(user.getComCode());
        mdmDevicesUseInfoVo.setProfCode(profCode);
        mdmDevicesUseInfoVo.setDevicesName(devicesName);
        mdmDevicesUseInfoVo.setSpaceName(spaceName);
        mdmDevicesUseInfoVo.setClassCode(classCode);
        //查询主设备中不包含前置设备的数据
        if (StringUtils.isNotBlank(spaceCode)) {
            spaces = spaceCode;
        } else {
            spaces = ruagLinkageRuleSpaceService.getSpace(linkageCode);
        }
        Map<String, String> sqlMap = new HashMap<String, String>();
        String sql = "AND A .SPACE_CODE IN (" + spaces.substring(0, spaces.length() - 1) + ")AND NOT EXISTS (SELECT * FROM( SELECT D .DEVICE_CODE FROM RUAG_LINKAAGE_FRONT_DEVICE D WHERE D .LINKAGE_CODE = '" + linkageCode
                + "'UNION SELECT F.DEVICE_CODE FROM RUAG_LINKAAGE_NEXT_DEVICE F WHERE F.LINKAGE_CODE = '" + linkageCode
                + "' ) G WHERE A .DEVICES_CODE = G .DEVICE_CODE)";
        if (StringUtils.isBlank(profCode) && StringUtils.isNotBlank(profCodes)) {
            sql = sql + " AND b.prof_code in(" + profCodes.substring(0, profCodes.length() - 1) + ")";
        }
        sqlMap.put("sqlMap", sql);
        mdmDevicesUseInfoVo.setSqlMap(sqlMap);
        Page<MdmDevicesUseInfoVo> page = mdmDevicesUseInfoService.findPageVoByModel(new Page<MdmDevicesUseInfoVo>(pageNo, pageSize), mdmDevicesUseInfoVo);
        return ResponseUtil.successResponse(page);
    }

    @ApiOperation(value = "发送后置设备指令", notes = "发送后置设备指令")
    @RequestMapping(value = "/SendDevicesNext", method = RequestMethod.POST)
    public boolean SendDevicesNext(String linkageCode, String linkageName) {
        //后置设备
        RuagLinkaageNextDevice ruagLinkaageNextDevice = new RuagLinkaageNextDevice();
        ruagLinkaageNextDevice.setLinkageCode(linkageCode);
        List<RuagLinkaageNextDevice> list = ruagLinkaageNextDeviceService.findList(ruagLinkaageNextDevice);

        for (RuagLinkaageNextDevice ruagLinkaageNextDevice2 : list) {
            //后置设备参数
            RuagLinkageDeviceParamSet ruagLinkageDeviceParamSet = new RuagLinkageDeviceParamSet();
            ruagLinkageDeviceParamSet.setLinkageRuleId(linkageCode);
            ruagLinkageDeviceParamSet.setDeviceCode(ruagLinkaageNextDevice2.getDeviceCode());
            List<RuagLinkageDeviceParamSet> listParamSet = ruagLinkageDeviceParamSetService.findList(ruagLinkageDeviceParamSet);

            for (RuagLinkageDeviceParamSet ruagLinkageDeviceParamSet2 : listParamSet) {
                RuagDeviceCalendarInstction ruagDeviceCalendarInstction = new RuagDeviceCalendarInstction();
                ruagDeviceCalendarInstction.setModelId(linkageCode);
                ruagDeviceCalendarInstction.setModelName(linkageName);
                ruagDeviceCalendarInstction.setControlType("link_model");
                ruagDeviceCalendarInstction.setSpaceCode(ruagLinkaageNextDevice2.getSpaceCode());
                ruagDeviceCalendarInstction.setSpaceName(ruagLinkaageNextDevice2.getSpaceName());
                ruagDeviceCalendarInstction.setProfCode(ruagLinkaageNextDevice2.getProfCode());
                ruagDeviceCalendarInstction.setProfName(ruagLinkaageNextDevice2.getProfName());
                ruagDeviceCalendarInstction.setDeviceCode(ruagLinkaageNextDevice2.getDeviceCode());
                ruagDeviceCalendarInstction.setDeviceName(ruagLinkaageNextDevice2.getDeviceName());
                ruagDeviceCalendarInstction.setParameterId(ruagLinkageDeviceParamSet2.getParameterId());
                ruagDeviceCalendarInstction.setInstructionStatus(1L);
                ruagDeviceCalendarInstction.setEnabledFlag("Y");
                ruagDeviceCalendarInstction.setModelDegree("0");
                ruagDeviceCalendarInstction.setParameterValue(ruagLinkageDeviceParamSet2.getParameterValue());
                ruagDeviceCalendarInstctionService.save(ruagDeviceCalendarInstction);
            }

        }
        return true;
    }


}
