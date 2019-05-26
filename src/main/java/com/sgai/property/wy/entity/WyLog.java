
/**
 * @Title: Log.java
 * @Package com.sgai.property.wy.entity
 * (用一句话描述该文件做什么)
 * @author Lenovo
 * @date 2018年1月29日
 * @Company 首自信--智慧城市创新中心
 * @version V1.0
 */

package com.sgai.property.wy.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sgai.common.persistence.BoEntity;
import com.sgai.modules.login.support.UserServletContext;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author heibin
 * @ClassName: Log
 * (这里用一句话描述这个类的作用)
 * @date 2018年1月29日
 * @Company 首自信--智慧城市创新中心
 */

public class WyLog extends BoEntity<WyLog> {

    /**
     * @Fields field:field:(用一句话描述这个变量表示什么)
     */

    private static final long serialVersionUID = 1L;
    private String userId;// 记录人id
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd ")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date createTime;// 记录时间
    private String createTime1;
    private String area;// 区域
    private String areaCode;// 区域编码
    private String hour;// 记录小时分钟
    private String content;// 工作内容
    private String processingPersonId;// 处理人id
    private String processingPersonName;
    private String describes;// 处理描述
    private String remarks;// 备注
    private String delFlag;// 0正常 1删除
    private String comCode; // 机构代码
    private String deptCode; // 部门代码
    private String userName;// 处理人姓名
    private String userRecordName;// 记录人姓名
    private Integer type;// 类型1:日志记录 2:日志处理
    private Date startTime;// 开始时间
    private Date endTime;// 结束时间
    private String minute;// 分钟

    private String hour1;// 记录小时分钟
    private String content1;// 工作内容
    private String minute1;// 分钟
    private String hour2;// 记录小时分钟
    private String content2;// 工作内容
    private String minute2;// 分钟

    private String hour3;// 记录小时分钟
    private String content3;// 工作内容
    private String minute3;// 分钟
    private String hour4;// 记录小时分钟
    private String content4;// 工作内容
    private String minute4;// 分钟

    private String hour5;// 记录小时分钟
    private String content5;// 工作内容
    private String minute5;// 分钟
    private String hour6;// 记录小时分钟
    private String content6;// 工作内容
    private String minute6;// 分钟

    private String repairAddress1;// 区域1
    private String repairAddress2;// 区域2
    private String repairAddress3;// 区域3

    private String repairAddress;

    private String logNowToday;//
    private String cho;//首页跳转标识
    private String loginUserCode = UserServletContext.getUserInfo().getUserId();

    public String getCho() {
        return cho;
    }

    public void setCho(String cho) {
        this.cho = cho;
    }

    public String getLogNowToday() {
        return logNowToday;
    }

    public void setLogNowToday(String logNowToday) {
        this.logNowToday = logNowToday;
    }

    public String getRepairAddress() {
        return repairAddress;
    }

    public void setRepairAddress(String repairAddress) {
        this.repairAddress = repairAddress;
    }

    public String getRepairAddress1() {
        return repairAddress1;
    }

    public void setRepairAddress1(String repairAddress1) {
        this.repairAddress1 = repairAddress1;
    }

    public String getRepairAddress2() {
        return repairAddress2;
    }

    public void setRepairAddress2(String repairAddress2) {
        this.repairAddress2 = repairAddress2;
    }

    public String getRepairAddress3() {
        return repairAddress3;
    }

    public void setRepairAddress3(String repairAddress3) {
        this.repairAddress3 = repairAddress3;
    }

    public String getHour3() {
        return hour3;
    }

    public void setHour3(String hour3) {
        this.hour3 = hour3;
    }

    public String getContent3() {
        return content3;
    }

    public void setContent3(String content3) {
        this.content3 = content3;
    }

    public String getMinute3() {
        return minute3;
    }

    public void setMinute3(String minute3) {
        this.minute3 = minute3;
    }

    public String getHour4() {
        return hour4;
    }

    public void setHour4(String hour4) {
        this.hour4 = hour4;
    }

    public String getContent4() {
        return content4;
    }

    public void setContent4(String content4) {
        this.content4 = content4;
    }

    public String getMinute4() {
        return minute4;
    }

    public void setMinute4(String minute4) {
        this.minute4 = minute4;
    }

    public String getHour5() {
        return hour5;
    }

    public void setHour5(String hour5) {
        this.hour5 = hour5;
    }

    public String getContent5() {
        return content5;
    }

    public void setContent5(String content5) {
        this.content5 = content5;
    }

    public String getMinute5() {
        return minute5;
    }

    public void setMinute5(String minute5) {
        this.minute5 = minute5;
    }

    public String getHour6() {
        return hour6;
    }

    public void setHour6(String hour6) {
        this.hour6 = hour6;
    }

    public String getContent6() {
        return content6;
    }

    public void setContent6(String content6) {
        this.content6 = content6;
    }

    public String getMinute6() {
        return minute6;
    }

    public void setMinute6(String minute6) {
        this.minute6 = minute6;
    }

    public String getHour1() {
        return hour1;
    }

    public void setHour1(String hour1) {
        this.hour1 = hour1;
    }

    public String getContent1() {
        return content1;
    }

    public void setContent1(String content1) {
        this.content1 = content1;
    }

    public String getMinute1() {
        return minute1;
    }

    public void setMinute1(String minute1) {
        this.minute1 = minute1;
    }

    public String getHour2() {
        return hour2;
    }

    public void setHour2(String hour2) {
        this.hour2 = hour2;
    }

    public String getContent2() {
        return content2;
    }

    public void setContent2(String content2) {
        this.content2 = content2;
    }

    public String getMinute2() {
        return minute2;
    }

    public void setMinute2(String minute2) {
        this.minute2 = minute2;
    }

    public String getMinute() {
        return minute;
    }

    public void setMinute(String minute) {
        this.minute = minute;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    private String telePhone;// 电话

    public String getTelePhone() {
        return telePhone;
    }

    public void setTelePhone(String telePhone) {
        this.telePhone = telePhone;
    }

    public String getUserRecordName() {
        return userRecordName;
    }

    public void setUserRecordName(String userRecordName) {
        this.userRecordName = userRecordName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCreateTime1() {
        return createTime1;
    }

    public void setCreateTime1(String createTime1) {
        this.createTime1 = createTime1;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getProcessingPersonId() {
        return processingPersonId;
    }

    public void setProcessingPersonId(String processingPersonId) {
        this.processingPersonId = processingPersonId;
    }

    public String getDescribes() {
        return describes;
    }

    public void setDescribes(String describes) {
        this.describes = describes;
    }

    public String getComCode() {
        return comCode;
    }

    public void setComCode(String comCode) {
        this.comCode = comCode;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getLoginUserCode() {
        return loginUserCode;
    }

    public void setLoginUserCode(String loginUserCode) {
        this.loginUserCode = loginUserCode;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getProcessingPersonName() {
        return processingPersonName;
    }

    public void setProcessingPersonName(String processingPersonName) {
        this.processingPersonName = processingPersonName;
    }
}
