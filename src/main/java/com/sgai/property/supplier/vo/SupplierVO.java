package com.sgai.property.supplier.vo;

import com.sgai.common.persistence.BoEntity;
import io.swagger.annotations.ApiModelProperty;

public class SupplierVO{

    private String id;
    @ApiModelProperty(value = "联系人")
    private String contact; //联系人
    @ApiModelProperty(value = "联系电话")
    private String phone; //联系电话
    @ApiModelProperty(value = "公司名称")
    private String name; //公司名称
    @ApiModelProperty(value = "供应商编号")
    private String no; //供应商编号
    @ApiModelProperty(value = "供应商类型名称")
    private String typeName; //供应商类型名称
    @ApiModelProperty(value = "供应商内容ID")
    private String contentId; //供应商内容ID
    @ApiModelProperty(value = "供应商内容名称")
    private String contentName; //供应商内容名称
    @ApiModelProperty(value = "供应商类型id")
    private String typeId; //供应商类型id
    @ApiModelProperty(value = "供应商等级id")
    private String levelId;
    @ApiModelProperty(value = "供应商等级名称")
    private String levelName;
    @ApiModelProperty(value = "地址")
    private String address; //地址
    @ApiModelProperty(value = "是否启用: 1启用; 0禁用")
    private Long isEnabled; //是否启用: 1启用; 0禁用

    public String getLevelId() {
        return levelId;
    }

    public void setLevelId(String levelId) {
        this.levelId = levelId;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(Long isEnabled) {
        this.isEnabled = isEnabled;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public String getContentName() {
        return contentName;
    }

    public void setContentName(String contentName) {
        this.contentName = contentName;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}