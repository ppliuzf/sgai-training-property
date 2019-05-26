package com.sgai.property.supplier.entity;

import com.sgai.common.persistence.BoEntity;
import io.swagger.annotations.ApiModelProperty;
public class GysSupplier extends BoEntity<GysSupplier>{
     
    @ApiModelProperty(value = "开户银行")
    private String bankName; //开户银行
    @ApiModelProperty(value = "认证名称")
    private String approveName; //认证名称
    @ApiModelProperty(value = "纳税人类别: 1 一般纳税人; 2 小规模纳税人")
    private Long taxpayerType; //纳税人类别: 1 一般纳税人; 2 小规模纳税人
    @ApiModelProperty(value = "法人身份证正面")
    private String cardAUrl; //法人身份证正面
    @ApiModelProperty(value = "FEED_ID")
    private String feedId; //FEED_ID
    @ApiModelProperty(value = "银行账号")
    private String bankAccount; //银行账号
    @ApiModelProperty(value = "税率(最多支持2位小数)")
    private Double rate; //税率(最多支持2位小数)
    @ApiModelProperty(value = "是否启用: 1启用; 0禁用")
    private Long isEnabled; //是否启用: 1启用; 0禁用
    @ApiModelProperty(value = "供应商等级ID")
    private String levelId; //供应商等级ID
    @ApiModelProperty(value = "COM_NAME")
    private String comName; //COM_NAME
    @ApiModelProperty(value = "联系人")
    private String contact; //联系人
    @ApiModelProperty(value = "联系电话")
    private String phone; //联系电话
    @ApiModelProperty(value = "是否已认证:  1,已通过; 2未审核,3,未通过,4,未认证")
    private Long isApprove; //是否已认证:  1,已通过; 2未审核,3,未通过,4,未认证
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
    @ApiModelProperty(value = "营业执照号")
    private String licenseNo; //营业执照号
    @ApiModelProperty(value = "营业执照正面")
    private String licenseUrl; //营业执照正面
    @ApiModelProperty(value = "TOON_USER_ID")
    private Long toonUserId; //TOON_USER_ID
    @ApiModelProperty(value = "法人姓名")
    private String legalName; //法人姓名
    @ApiModelProperty(value = "法人身份证反面")
    private String cardBUrl; //法人身份证反面
    @ApiModelProperty(value = "COM_ID")
    private Long comId; //COM_ID
    @ApiModelProperty(value = "供应商类型id")
    private String typeId; //供应商类型id
    @ApiModelProperty(value = "地址")
    private String address; //地址
    @ApiModelProperty(value = "是否删除；1:是;0:否")
    private Long isDelete; //是否删除；1:是;0:否

    public String getBankName(){  
        return bankName;  
    }
      
   public void setBankName(String bankName){  
     this.bankName = bankName;  
    }  
    public String getApproveName(){  
        return approveName;  
    }
      
   public void setApproveName(String approveName){  
     this.approveName = approveName;  
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
    public String getFeedId(){  
        return feedId;  
    }
      
   public void setFeedId(String feedId){  
     this.feedId = feedId;  
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
    public Long getIsEnabled(){  
        return isEnabled;  
    }
      
   public void setIsEnabled(Long isEnabled){  
     this.isEnabled = isEnabled;  
    }  
    public String getLevelId(){  
        return levelId;  
    }
      
   public void setLevelId(String levelId){  
     this.levelId = levelId;  
    }  
    public String getComName(){  
        return comName;  
    }
      
   public void setComName(String comName){  
     this.comName = comName;  
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
    public Long getIsApprove(){  
        return isApprove;  
    }
      
   public void setIsApprove(Long isApprove){  
     this.isApprove = isApprove;  
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
    public Long getToonUserId(){  
        return toonUserId;  
    }
      
   public void setToonUserId(Long toonUserId){  
     this.toonUserId = toonUserId;  
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
    public Long getComId(){  
        return comId;  
    }
      
   public void setComId(Long comId){  
     this.comId = comId;  
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
    public Long getIsDelete(){  
        return isDelete;  
    }
      
   public void setIsDelete(Long isDelete){  
     this.isDelete = isDelete;  
    }
}