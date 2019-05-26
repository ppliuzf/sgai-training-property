package com.sgai.property.supplier.vo;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class ContractDetailsVO {


    @ApiModelProperty(value = "id")
    private String id;
    @ApiModelProperty(value = "合同编号")
    private String no; //合同编号
    @ApiModelProperty(value = "合同名称")
    private String name; //合同名称
    @ApiModelProperty(value = "合同类型")
    private String typeId; //合同类型
    @ApiModelProperty(value = "合同类型名称")
    private String typeName; //合同类型名称
    @ApiModelProperty(value = "甲方名称")
    private String ownerName; //甲方名称
    @ApiModelProperty(value = "乙方名称")
    private String secondPartyName; //乙方名称
    @ApiModelProperty(value = "签订日期")
    private Long singDate; //签订日期
    @ApiModelProperty(value = "生效日期")
    private Long effectiveDate; //生效日期
    @ApiModelProperty(value = "合同创建人")
    private String creater; //合同创建人
    @ApiModelProperty(value = "手机号")
    private String phone; //手机号
    @ApiModelProperty(value = "描述")
    private String description; //描述
    @ApiModelProperty(value = "合同总额")
    private Double amount; //合同总额
    @ApiModelProperty(value = "合同状态 : 1 未签约  2 已签约")
    private Long status; //合同状态 : 1 未签约  2 已签约
    @ApiModelProperty(value = "供应商名称")
    private List<String> supplierNames; //供应商名称

    @ApiModelProperty(value = "合同图片")
    private HtImageVO imageVO;//合同图片
    @ApiModelProperty(value = "合同图片")
    private String[] images;//

    //附件
    List<HtFileVO> files;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getSecondPartyName() {
        return secondPartyName;
    }

    public void setSecondPartyName(String secondPartyName) {
        this.secondPartyName = secondPartyName;
    }

    public Long getSingDate() {
        return singDate;
    }

    public void setSingDate(Long singDate) {
        this.singDate = singDate;
    }

    public Long getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Long effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public List<String> getSupplierNames() {
        return supplierNames;
    }

    public void setSupplierNames(List<String> supplierNames) {
        this.supplierNames = supplierNames;
    }

    public HtImageVO getImageVO() {
        return imageVO;
    }

    public void setImageVO(HtImageVO imageVO) {
        this.imageVO = imageVO;
    }

    public String[] getImages() {
        return images;
    }

    public void setImages(String[] images) {
        this.images = images;
    }

    public List<HtFileVO> getFiles() {
        return files;
    }

    public void setFiles(List<HtFileVO> files) {
        this.files = files;
    }
}
