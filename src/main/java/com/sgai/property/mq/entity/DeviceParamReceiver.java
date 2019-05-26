package com.sgai.property.mq.entity;

import com.alibaba.fastjson.JSONObject;

/**
 * 点位值.
 *
 * @author ppliu
 * created in 2019/3/18 8:52
 */
public class DeviceParamReceiver {
    /** 设备编码. */
    private String deviceCode;
    /** 参数编码. */
    private String paramCode;
    /** 参数值. */
    private String value;
    /** 点位索引. */
    private String path;

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public String getParamCode() {
        return paramCode;
    }

    public void setParamCode(String paramCode) {
        this.paramCode = paramCode;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public static DeviceParamReceiver jsonToEntity(String json) {
        DeviceParamReceiver paramEntity = JSONObject.parseObject(json, DeviceParamReceiver.class);
        String index = paramEntity.getPath();
        String paramCode = index.substring(index.lastIndexOf(".") + 1);
        String deviceCode = index.substring(0, index.lastIndexOf("."));
        paramEntity.setDeviceCode(deviceCode);
        paramEntity.setParamCode(paramCode);
        return paramEntity;
    }
}
