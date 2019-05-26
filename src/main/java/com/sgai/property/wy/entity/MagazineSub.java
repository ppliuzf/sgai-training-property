
/**
 * @Title: MagazineSub.java
 * @Package com.sgai.property.wy.entity
 * (用一句话描述该文件做什么)
 * @author cui
 * @date 2018年2月1日
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
 * @author cui
 * @ClassName: MagazineSub
 * (杂志订阅实体)
 * @date 2018年2月1日
 * @Company 首自信--智慧城市创新中心
 */

public class MagazineSub extends BoEntity<MagazineSub> {

    private static final long serialVersionUID = 1L;

    private String signName; // 经办人

    private String signDept; // 经办部门

    private String magazine; // 杂志

    private String receiveName; // 接收人

    private String area; // 区域

    private Integer phr; // 杂志份数
    private String phrs; // 多个杂志

    private Date beginTime; // 开始时间

    private Date endTime; // 结束时间

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd ")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date signDate; // 日期

    private String repairAddress; // 区域地址
    private String repairAddressCode; // 区域地址

    private String checkNumber;// 核对份数
    private String loginUserCode = UserServletContext.getUserInfo().getUserId();

    private String checkUser;// 核对人


    public String getPhrs() {
        return phrs;
    }

    public void setPhrs(String phrs) {
        this.phrs = phrs;
    }

    public String getCheckNumber() {
        return checkNumber;
    }

    public void setCheckNumber(String checkNumber) {
        this.checkNumber = checkNumber;
    }

    public String getCheckUser() {
        return checkUser;
    }

    public void setCheckUser(String checkUser) {
        this.checkUser = checkUser;
    }

    public String getRepairAddress() {
        return repairAddress;
    }

    public void setRepairAddress(String repairAddress) {
        this.repairAddress = repairAddress;
    }

    public String getSignName() {
        return signName;
    }

    public void setSignName(String signName) {
        this.signName = signName;
    }

    public String getSignDept() {
        return signDept;
    }

    public void setSignDept(String signDept) {
        this.signDept = signDept;
    }

    public String getMagazine() {
        return magazine;
    }

    public void setMagazine(String magazine) {
        this.magazine = magazine;
    }

    public String getReceiveName() {
        return receiveName;
    }

    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName;
    }

    public Date getSignDate() {
        return signDate;
    }

    public void setSignDate(Date signDate) {
        this.signDate = signDate;
    }

    public Integer getPhr() {
        return phr;
    }

    public void setPhr(Integer phr) {
        this.phr = phr;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
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

    public String getRepairAddressCode() {
        return repairAddressCode;
    }

    public void setRepairAddressCode(String repairAddressCode) {
        this.repairAddressCode = repairAddressCode;
    }

    public String getLoginUserCode() {
        return loginUserCode;
    }

    public void setLoginUserCode(String loginUserCode) {
        this.loginUserCode = loginUserCode;
    }
}
