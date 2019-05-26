package com.sgai.property.park.entity;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;

import javax.persistence.*;
import java.util.Date;

/**
 * 进出场记录表.
 *
 * @author zhangzhixin
 */
@Entity
@Table(name = "in_out_record")
public class InOutRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String parkId;    //车场id
    private String carCode; //车牌号码
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date inTime; //进场时间
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date passTime; //过场时间
    private String inOrOut; //0:进场，1：出场
    private String guid; //车辆本次进场出场标识
    private String channelId;//通道ID
    private String channelName;//通道名称
    private String imagePath;//图片路径地址


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getParkId() {
        return parkId;
    }

    public void setParkId(String parkId) {
        this.parkId = parkId;
    }

    public String getCarCode() {
        return carCode;
    }

    public void setCarCode(String carCode) {
        this.carCode = carCode;
    }

    public Date getInTime() {
        return inTime;
    }

    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }

    public Date getPassTime() {
        return passTime;
    }

    public void setPassTime(Date passTime) {
        this.passTime = passTime;
    }

    public String getInOrOut() {
        return inOrOut;
    }

    public void setInOrOut(String inOrOut) {
        this.inOrOut = inOrOut;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this, SerializerFeature.WRITE_MAP_NULL_FEATURES);
    }
}
