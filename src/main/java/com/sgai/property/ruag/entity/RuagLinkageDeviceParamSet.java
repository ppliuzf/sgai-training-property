package com.sgai.property.ruag.entity;

import com.sgai.common.persistence.BoEntity;
import org.hibernate.validator.constraints.Length;


/**
 * 模式设备参数设置Entity
 *
 * @author yangyz
 * @version 2018-01-02
 */
public class RuagLinkageDeviceParamSet extends BoEntity<RuagLinkageDeviceParamSet> {

    private static final long serialVersionUID = 1L;
    private String linkageRuleId;        // 联动规则id
    private String masterId;        // 联动设备业务id
    private String masterName;        // 表名：RUSG_LINKAAGE_FRONT_DEVICE：前置设备表RUSG_LINKAAGE_NEXT_DEVICE：后置设备表
    private String deviceClassCode;        // 设备类型
    private String deviceCode;        // 设备代码
    private String parameterId;        // 参数ID
    private String parameterValue;        // 参数值
    private String timePoint;        // 时间点
    private String enabledFlag;        // 1.选项为:'Y':是'N':否默认为'Y'
    private String frontNextFlag; //前置设备参数还是后置设备参数. F前N后
    private String switchFlag;

    public RuagLinkageDeviceParamSet() {
        super();
    }

    public RuagLinkageDeviceParamSet(String id) {
        super(id);
    }

    @Length(min = 0, max = 60, message = "联动规则id长度必须介于 0 和 60 之间")
    public String getLinkageRuleId() {
        return linkageRuleId;
    }

    public void setLinkageRuleId(String linkageRuleId) {
        this.linkageRuleId = linkageRuleId;
    }

    @Length(min = 0, max = 60, message = "联动设备业务id长度必须介于 0 和 60 之间")
    public String getMasterId() {
        return masterId;
    }

    public void setMasterId(String masterId) {
        this.masterId = masterId;
    }

    @Length(min = 0, max = 60, message = "表名：RUSG_LINKAAGE_FRONT_DEVICE：前置设备表RUSG_LINKAAGE_NEXT_DEVICE：后置设备表长度必须介于 0 和 60 之间")
    public String getMasterName() {
        return masterName;
    }

    public void setMasterName(String masterName) {
        this.masterName = masterName;
    }

    @Length(min = 0, max = 60, message = "设备代码长度必须介于 0 和 60 之间")
    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    @Length(min = 0, max = 60, message = "参数ID长度必须介于 0 和 60 之间")
    public String getParameterId() {
        return parameterId;
    }

    public void setParameterId(String parameterId) {
        this.parameterId = parameterId;
    }

    @Length(min = 0, max = 60, message = "参数值长度必须介于 0 和 60 之间")
    public String getParameterValue() {
        return parameterValue;
    }

    public void setParameterValue(String parameterValue) {
        this.parameterValue = parameterValue;
    }

    @Length(min = 0, max = 60, message = "时间点长度必须介于 0 和 60 之间")
    public String getTimePoint() {
        return timePoint;
    }

    public void setTimePoint(String timePoint) {
        this.timePoint = timePoint;
    }

    public String getEnabledFlag() {
        return enabledFlag;
    }

    public void setEnabledFlag(String enabledFlag) {
        this.enabledFlag = enabledFlag;
    }

    public String getDeviceClassCode() {
        return deviceClassCode;
    }

    public void setDeviceClassCode(String deviceClassCode) {
        this.deviceClassCode = deviceClassCode;
    }

    public String getFrontNextFlag() {
        return frontNextFlag;
    }

    public void setFrontNextFlag(String frontNextFlag) {
        this.frontNextFlag = frontNextFlag;
    }

    public String getSwitchFlag() {
        return switchFlag;
    }

    public void setSwitchFlag(String switchFlag) {
        this.switchFlag = switchFlag;
    }
}
