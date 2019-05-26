package com.sgai.property.car.vo;

import com.sgai.common.persistence.BoEntity;
import io.swagger.annotations.ApiModelProperty;

public class CarInfoParam extends BoEntity<CarInfoParam>{
    @ApiModelProperty(value = "车辆品牌")
    private String ciBrand; //车辆品牌
    @ApiModelProperty(value = "车辆id")
    private String id; //车辆id
    @ApiModelProperty(value = "车牌号码")
    private String ciNumber; //车牌号码
    @ApiModelProperty(value = "所属部门id")
    private String ciDepartmentId; //所属部门id
    @ApiModelProperty(value = "车主姓名")
    private String ciOwnerName; //车主姓名
    @ApiModelProperty(value = "车辆截图")
    private String ciImage; //车辆截图
    @ApiModelProperty(value = "车辆排量")
    private String ciDisplacement; //车辆排量
    @ApiModelProperty(value = "备注信息")
    private String ciRemark; //备注信息
    @ApiModelProperty(value = "发动机号码")
    private String ciEngineNumber; //发动机号码
    @ApiModelProperty(value = "车辆颜色")
    private String ciColor; //车辆颜色
    @ApiModelProperty(value = "变速箱类型id")
    private String ciBoxTypeId; //变速箱类型id
    @ApiModelProperty(value = "荷载人数")
    private Long ciLoadNumber; //荷载人数
    @ApiModelProperty(value = "变速箱类型名称")
    private String ciBoxTypeName; //变速箱类型名称
    @ApiModelProperty(value = "购置日期")
    private Long ciBuyDate; //购置日期
    @ApiModelProperty(value = "所属部门")
    private String ciDepartment; //所属部门
    @ApiModelProperty(value = "车辆型号")
    private String ciModel; //车辆型号
    @ApiModelProperty(value = "车辆类型id")
    private String ciTypeId; //车辆类型ID
    @ApiModelProperty(value = "车辆类型名称")
    private String ciTypeName; //车辆类型名称
    @ApiModelProperty(value = "车主手机号")
    private Long ciOwnerPhone; //车主手机号

    @ApiModelProperty(value = "车辆行驶公里数")
    private Long journeyAmount;

    public Long getJourneyAmount() {
        return journeyAmount;
    }

    public CarInfoParam setJourneyAmount(Long journeyAmount) {
        this.journeyAmount = journeyAmount;
        return this;
    }

    public String getCiBrand(){
        return ciBrand;  
    }

    public void setCiBrand(String ciBrand) {
        this.ciBrand = ciBrand;
    }

    public String getCiNumber() {
        return ciNumber;
    }

    public void setCiNumber(String ciNumber) {
        this.ciNumber = ciNumber;
    }

    public String getCiDepartmentId() {
        return ciDepartmentId;
    }

    public void setCiDepartmentId(String ciDepartmentId) {
        this.ciDepartmentId = ciDepartmentId;
    }

    public String getCiOwnerName() {
        return ciOwnerName;
    }

    public void setCiOwnerName(String ciOwnerName) {
        this.ciOwnerName = ciOwnerName;
    }

    public String getCiImage() {
        return ciImage;
    }

    public void setCiImage(String ciImage) {
        this.ciImage = ciImage;
    }

    public String getCiDisplacement() {
        return ciDisplacement;
    }

    public void setCiDisplacement(String ciDisplacement) {
        this.ciDisplacement = ciDisplacement;
    }

    public String getCiRemark() {
        return ciRemark;
    }

    public void setCiRemark(String ciRemark) {
        this.ciRemark = ciRemark;
    }

    public String getCiEngineNumber() {
        return ciEngineNumber;
    }

    public void setCiEngineNumber(String ciEngineNumber) {
        this.ciEngineNumber = ciEngineNumber;
    }

    public String getCiColor() {
        return ciColor;
    }

    public void setCiColor(String ciColor) {
        this.ciColor = ciColor;
    }

    public String getCiBoxTypeId() {
        return ciBoxTypeId;
    }

    public void setCiBoxTypeId(String ciBoxTypeId) {
        this.ciBoxTypeId = ciBoxTypeId;
    }

    public Long getCiLoadNumber() {
        return ciLoadNumber;
    }

    public void setCiLoadNumber(Long ciLoadNumber) {
        this.ciLoadNumber = ciLoadNumber;
    }

    public String getCiBoxTypeName() {
        return ciBoxTypeName;
    }

    public void setCiBoxTypeName(String ciBoxTypeName) {
        this.ciBoxTypeName = ciBoxTypeName;
    }

    public Long getCiBuyDate() {
        return ciBuyDate;
    }

    public void setCiBuyDate(Long ciBuyDate) {
        this.ciBuyDate = ciBuyDate;
    }

    public String getCiDepartment() {
        return ciDepartment;
    }

    public void setCiDepartment(String ciDepartment) {
        this.ciDepartment = ciDepartment;
    }

    public String getCiModel() {
        return ciModel;
    }

    public void setCiModel(String ciModel) {
        this.ciModel = ciModel;
    }

    public String getCiTypeId() {
        return ciTypeId;
    }

    public void setCiTypeId(String ciTypeId) {
        this.ciTypeId = ciTypeId;
    }

    public String getCiTypeName() {
        return ciTypeName;
    }

    public void setCiTypeName(String ciTypeName) {
        this.ciTypeName = ciTypeName;
    }

    public Long getCiOwnerPhone() {
        return ciOwnerPhone;
    }

    public void setCiOwnerPhone(Long ciOwnerPhone) {
        this.ciOwnerPhone = ciOwnerPhone;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }
}