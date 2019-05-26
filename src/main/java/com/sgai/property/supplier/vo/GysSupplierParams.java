package com.sgai.property.supplier.vo;

import com.sgai.property.supplier.entity.GysFile;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class GysSupplierParams{

    @ApiModelProperty(value = "开户银行")
    private String bankName; //开户银行
    @ApiModelProperty(value = "纳税人类别: 1 一般纳税人; 2 小规模纳税人")
    private Long taxpayerType; //纳税人类别: 1 一般纳税人; 2 小规模纳税人
    @ApiModelProperty(value = "法人身份证正面")
    private String cardAUrl; //法人身份证正面
    @ApiModelProperty(value = "银行账号")
    private String bankAccount; //银行账号
    @ApiModelProperty(value = "税率(最多支持2位小数)")
    private Double rate; //税率(最多支持2位小数)
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
    @ApiModelProperty(value = "供应商等级ID")
    private String levelId; //供应商等级ID
    @ApiModelProperty(value = "营业执照号")
    private String licenseNo; //营业执照号
    @ApiModelProperty(value = "营业执照正面")
    private String licenseUrl; //营业执照正面
    @ApiModelProperty(value = "法人姓名")
    private String legalName; //法人姓名
    @ApiModelProperty(value = "法人身份证反面")
    private String cardBUrl; //法人身份证反面
    @ApiModelProperty(value = "供应商类型id")
    private String typeId; //供应商类型id
    @ApiModelProperty(value = "地址")
    private String address; //地址
    @ApiModelProperty(value = "相关附件")
    private List<GysFile> GysFileList; //相关附件
	
    public String getBankName(){
    return bankName;
    }

    public void setBankName(String bankName){
    this.bankName = bankName;
    }

    public Long getTaxpayerType(){
    return taxpayerType;
    }

    public void setTaxpayerType(Long taxpayerType){
    this.taxpayerType = taxpayerType;
    }
    public String getCardAUrl(){
    return cardAUrl;
    }

    public void setCardAUrl(String cardAUrl){
    this.cardAUrl = cardAUrl;
    }

    public String getBankAccount(){
    return bankAccount;
    }

    public void setBankAccount(String bankAccount){
    this.bankAccount = bankAccount;
    }
    public Double getRate(){
    return rate;
    }

    public void setRate(Double rate){
    this.rate = rate;
    }

    public String getContact(){
    return contact;
    }

    public void setContact(String contact){
    this.contact = contact;
    }
    public String getPhone(){
    return phone;
    }

    public void setPhone(String phone){
    this.phone = phone;
    }

    public String getName(){
    return name;
    }

    public void setName(String name){
    this.name = name;
    }
    public String getNo(){
    return no;
    }

    public void setNo(String no){
    this.no = no;
    }
    public String getTypeName(){
    return typeName;
    }

    public void setTypeName(String typeName){
    this.typeName = typeName;
    }
    public String getContentId(){
    return contentId;
    }

    public void setContentId(String contentId){
    this.contentId = contentId;
    }
    public String getContentName(){
    return contentName;
    }

    public void setContentName(String contentName){
    this.contentName = contentName;
    }
    public String getLicenseNo(){
    return licenseNo;
    }

    public void setLicenseNo(String licenseNo){
    this.licenseNo = licenseNo;
    }
    public String getLicenseUrl(){
    return licenseUrl;
    }

    public void setLicenseUrl(String licenseUrl){
    this.licenseUrl = licenseUrl;
    }

    public String getLegalName(){
    return legalName;
    }

    public void setLegalName(String legalName){
    this.legalName = legalName;
    }
    public String getCardBUrl(){
    return cardBUrl;
    }

    public void setCardBUrl(String cardBUrl){
    this.cardBUrl = cardBUrl;
    }

    public String getTypeId(){
    return typeId;
    }

    public void setTypeId(String typeId){
    this.typeId = typeId;
    }
    public String getAddress(){
    return address;
    }

    public void setAddress(String address){
    this.address = address;
    }

    public List<GysFile> getGysFileList() {
        return GysFileList;
    }

    public void setGysFileList(List<GysFile> gysFileList) {
        GysFileList = gysFileList;
    }

    public String getLevelId() {
        return levelId;
    }

    public void setLevelId(String levelId) {
        this.levelId = levelId;
    }
}