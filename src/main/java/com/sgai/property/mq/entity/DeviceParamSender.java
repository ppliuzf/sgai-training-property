package com.sgai.property.mq.entity;

import com.alibaba.fastjson.JSONObject;
import com.sgai.property.ruag.entity.RuagDeviceCalendarInstction;

/**
 * 设备指令发送类.
 *
 * @author ppliu
 * created in 2019/3/18 9:41
 */
public class DeviceParamSender {
    private String path;
    private String value;

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

    /**
     * 指令格式转化.
     */
    public static String instructionGenerate(RuagDeviceCalendarInstction instction) {
        DeviceParamSender sender = new DeviceParamSender();
        sender.setPath(instction.getDeviceCode() + "." + instction.getParameterId());
        sender.setValue(instction.getParameterValue());
        return sender.toString();
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
