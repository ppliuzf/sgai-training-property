package com.sgai.property.wy.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sgai.common.persistence.BoEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by ppliu on 2018/1/23.
 * 会议预约，即用于会议预约，也用于健身预约.
 */
public class MeetingOrder extends BoEntity<MeetingOrder> {
    /**来源类型.*/
    private String  sourceType;
    /**如果是会议的话是预约还是服务.*/
    private String  serviceType;
    /**会议室id.*/
    private String  meetingRoomId;
    /**会议室名称.*/
    private String  meetingRoomName;
    /**预约时间.*/
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date    orderTime;
    /**电话.*/
    private String  phone;
    /**联系人.*/
    private String  contactPeople;
    /**会议名称.*/
    private String  meetingName;
    /**预约部门.*/
    private String  orderDepartment;
    /**预约人数.*/
    private Integer orderPeopleNum;
    /**预约人数.*/
    private Integer realPeopleNum;
    /**会议规模.*/
    private String  meetingScale;
    /**会议类型.*/
    private String  meetingType;
    /**会议级别.*/
    private String  meetingLeval;
    /**热毛巾.*/
    private Integer towelNum;
    /**签字笔.*/
    private Integer signPenNum;
    /**A4纸.*/
    private Integer paperNum;
    /**红蓝铅笔.*/
    private Integer pencilsNum;
    /**2b铅笔.*/
    private Integer pencils2bNum;
    /**玻璃杯.*/
    private Integer glassNum;
    /**备注.*/
    private String  remark;
    /**是否选中茶.*/
    private String teaFlag;
     /**是否选中瓶装水.*/
    private String waterBottleFlag;
     /**是否选中咖啡.*/
    private String coffeeFlag;
    /**茶的数量.*/
    private Integer teaNum;
    /**拼装水数量.*/
    private Integer waterBottleNum;
    /**咖啡数量.*/
    private Integer coffeeNum;
    /**白板数量.*/
    private Integer whiteBoardNum;
    /**消耗品数量.*/
    private Integer consumableNum;
    /**一次性纸杯数量.*/
    private Integer dixieCupNum;
    /**桶装水数量.*/
    private Integer waterBucketNum;
    /**纸巾数量.*/
    private Integer tissueNum;
    private String normalTime;
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date beginTime;
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date endTime;
    private String recordPeople;

    public String getRecordPeople() {
        return recordPeople;
    }

    public void setRecordPeople(String recordPeople) {
        this.recordPeople = recordPeople;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getMeetingRoomId() {
        return meetingRoomId;
    }

    public void setMeetingRoomId(String meetingRoomId) {
        this.meetingRoomId = meetingRoomId;
    }

    public String getMeetingRoomName() {
        return meetingRoomName;
    }

    public void setMeetingRoomName(String meetingRoomName) {
        this.meetingRoomName = meetingRoomName;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getContactPeople() {
        return contactPeople;
    }

    public void setContactPeople(String contactPeople) {
        this.contactPeople = contactPeople;
    }

    public String getMeetingName() {
        return meetingName;
    }

    public void setMeetingName(String meetingName) {
        this.meetingName = meetingName;
    }

    public String getOrderDepartment() {
        return orderDepartment;
    }

    public void setOrderDepartment(String orderDepartment) {
        this.orderDepartment = orderDepartment;
    }

    public Integer getOrderPeopleNum() {
        return orderPeopleNum;
    }

    public void setOrderPeopleNum(Integer orderPeopleNum) {
        this.orderPeopleNum = orderPeopleNum;
    }

    public String getMeetingScale() {
        return meetingScale;
    }

    public void setMeetingScale(String meetingScale) {
        this.meetingScale = meetingScale;
    }

    public String getMeetingType() {
        return meetingType;
    }

    public void setMeetingType(String meetingType) {
        this.meetingType = meetingType;
    }

    public String getMeetingLeval() {
        return meetingLeval;
    }

    public void setMeetingLeval(String meetingLeval) {
        this.meetingLeval = meetingLeval;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getTeaFlag() {
        return teaFlag;
    }

    public void setTeaFlag(String teaFlag) {
        this.teaFlag = teaFlag;
    }

    public String getWaterBottleFlag() {
        return waterBottleFlag;
    }

    public void setWaterBottleFlag(String waterBottleFlag) {
        this.waterBottleFlag = waterBottleFlag;
    }

    public String getCoffeeFlag() {
        return coffeeFlag;
    }

    public void setCoffeeFlag(String coffeeFlag) {
        this.coffeeFlag = coffeeFlag;
    }

    public Integer getWaterBottleNum() {
        return waterBottleNum;
    }

    public void setWaterBottleNum(Integer waterBottleNum) {
        this.waterBottleNum = waterBottleNum;
    }

    public Integer getCoffeeNum() {
        return coffeeNum;
    }

    public void setCoffeeNum(Integer coffeeNum) {
        this.coffeeNum = coffeeNum;
    }

    public Integer getWhiteBoardNum() {
        return whiteBoardNum;
    }

    public void setWhiteBoardNum(Integer whiteBoardNum) {
        this.whiteBoardNum = whiteBoardNum;
    }

    public Integer getConsumableNum() {
        return consumableNum;
    }

    public void setConsumableNum(Integer consumableNum) {
        this.consumableNum = consumableNum;
    }

    public Integer getDixieCupNum() {
        return dixieCupNum;
    }

    public void setDixieCupNum(Integer dixieCupNum) {
        this.dixieCupNum = dixieCupNum;
    }

    public Integer getWaterBucketNum() {
        return waterBucketNum;
    }

    public void setWaterBucketNum(Integer waterBucketNum) {
        this.waterBucketNum = waterBucketNum;
    }

    public Integer getTissueNum() {
        return tissueNum;
    }

    public void setTissueNum(Integer tissueNum) {
        this.tissueNum = tissueNum;
    }

    public Integer getTowelNum() {
        return towelNum;
    }

    public void setTowelNum(Integer towelNum) {
        this.towelNum = towelNum;
    }

    public Integer getSignPenNum() {
        return signPenNum;
    }

    public void setSignPenNum(Integer signPenNum) {
        this.signPenNum = signPenNum;
    }

    public Integer getPaperNum() {
        return paperNum;
    }

    public void setPaperNum(Integer paperNum) {
        this.paperNum = paperNum;
    }

    public Integer getPencilsNum() {
        return pencilsNum;
    }

    public void setPencilsNum(Integer pencilsNum) {
        this.pencilsNum = pencilsNum;
    }

    public Integer getPencils2bNum() {
        return pencils2bNum;
    }

    public void setPencils2bNum(Integer pencils2bNum) {
        this.pencils2bNum = pencils2bNum;
    }

    public Integer getGlassNum() {
        return glassNum;
    }

    public void setGlassNum(Integer glassNum) {
        this.glassNum = glassNum;
    }



    public Integer getTeaNum() {
        return teaNum;
    }

    public void setTeaNum(Integer teaNum) {
        this.teaNum = teaNum;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getRealPeopleNum() {
        return realPeopleNum;
    }

    public void setRealPeopleNum(Integer realPeopleNum) {
        this.realPeopleNum = realPeopleNum;
    }

    public String getNormalTime() {
        return normalTime;
    }

    public void setNormalTime(String normalTime) {
        this.normalTime = normalTime;
    }
}
