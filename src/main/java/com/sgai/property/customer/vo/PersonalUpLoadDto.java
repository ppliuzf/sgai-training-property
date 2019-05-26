package com.sgai.property.customer.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 导入的个人客户模板DTO
 *
 * @author hou
 * @date 2017-12-25 17:01
 */
public class PersonalUpLoadDto implements Serializable {


    @ApiModelProperty(value = "手机号2")
    private Long prPhoneSecond; //手机号2
    @ApiModelProperty(value = "性别(0 男 1 女)")
    private String prSex; //性别(0 男 1 女)
    @ApiModelProperty(value = "出生日期")
    private String prBirth; //出生日期

    @ApiModelProperty(value = "手机号1")
    private Long prPhoneFirst; //手机号1

    @ApiModelProperty(value = "客户名称")
    private String prName; //客户名称

    @ApiModelProperty(value = "邮箱")
    private String prEmail; //邮箱

    @ApiModelProperty(value = "类型名称")
    private String typeName; //类型名称

    @ApiModelProperty(value = "级别名称")
    private String levelName; //级别名称
    @ApiModelProperty(value = "客户证件号")
    private String ccCertificateNo; //客户证件号
    @ApiModelProperty(value = "客户证件类型名称")
    private String ccCertificateName; //客户证件类型名称
//    @ApiModelProperty(value = "部门名称")
//    private String deptName; //部门名称

    public Long getPrPhoneSecond() {
        return prPhoneSecond;
    }

    public PersonalUpLoadDto setPrPhoneSecond(Long prPhoneSecond) {
        this.prPhoneSecond = prPhoneSecond;
        return this;
    }

    public String getPrSex() {
        return prSex;
    }

    public PersonalUpLoadDto setPrSex(String prSex) {
        this.prSex = prSex;
        return this;
    }

    public String getPrBirth() {
        return prBirth;
    }

    public PersonalUpLoadDto setPrBirth(String prBirth) {
        this.prBirth = prBirth;
        return this;
    }

    public Long getPrPhoneFirst() {
        return prPhoneFirst;
    }

    public PersonalUpLoadDto setPrPhoneFirst(Long prPhoneFirst) {
        this.prPhoneFirst = prPhoneFirst;
        return this;
    }

    public String getPrName() {
        return prName;
    }

    public PersonalUpLoadDto setPrName(String prName) {
        this.prName = prName;
        return this;
    }

    public String getPrEmail() {
        return prEmail;
    }

    public PersonalUpLoadDto setPrEmail(String prEmail) {
        this.prEmail = prEmail;
        return this;
    }



    public String getTypeName() {
        return typeName;
    }

    public PersonalUpLoadDto setTypeName(String typeName) {
        this.typeName = typeName;
        return this;
    }

    public String getLevelName() {
        return levelName;
    }

    public PersonalUpLoadDto setLevelName(String levelName) {
        this.levelName = levelName;
        return this;
    }

    public String getCcCertificateNo() {
        return ccCertificateNo;
    }

    public PersonalUpLoadDto setCcCertificateNo(String ccCertificateNo) {
        this.ccCertificateNo = ccCertificateNo;
        return this;
    }

    public String getCcCertificateName() {
        return ccCertificateName;
    }

    public PersonalUpLoadDto setCcCertificateName(String ccCertificateName) {
        this.ccCertificateName = ccCertificateName;
        return this;
    }

//    public String getDeptName() {
//        return deptName;
//    }
//
//    public void setDeptName(String deptName) {
//        this.deptName = deptName;
//    }
}
