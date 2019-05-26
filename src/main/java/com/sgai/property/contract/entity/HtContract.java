package com.sgai.property.contract.entity;

import com.sgai.common.persistence.BoEntity;
import io.swagger.annotations.ApiModelProperty;
public class HtContract extends BoEntity<HtContract>{
     
    @ApiModelProperty(value = "合同名称")
    private String name; //合同名称
    @ApiModelProperty(value = "合同类型")
    private String typeId; //合同类型
    @ApiModelProperty(value = "甲方名称")
    private String ownerName; //甲方名称
    @ApiModelProperty(value = "乙方名称")
    private String secondPartyName; //乙方名称
    @ApiModelProperty(value = "生效日期")
    private Long effectiveDate; //生效日期
    @ApiModelProperty(value = "描述")
    private String description; //描述
    @ApiModelProperty(value = "图片一")
    private String urlA; //图片一
    @ApiModelProperty(value = "图片二")
    private String urlB; //图片二
    @ApiModelProperty(value = "FEED_ID")
    private String feedId; //FEED_ID
    @ApiModelProperty(value = "签订日期")
    private Long singDate; //签订日期
    @ApiModelProperty(value = "合同创建人")
    private String creater; //合同创建人
    @ApiModelProperty(value = "COM_NAME")
    private String comName; //COM_NAME
    @ApiModelProperty(value = "合同编号")
    private String no; //合同编号
    @ApiModelProperty(value = "手机号")
    private String phone; //手机号
    @ApiModelProperty(value = "TOON_USER_ID")
    private Long toonUserId; //TOON_USER_ID
    @ApiModelProperty(value = "合同总额")
    private Double amount; //合同总额
    @ApiModelProperty(value = "COM_ID")
    private Long comId; //COM_ID
    @ApiModelProperty(value = "合同类型名称")
    private String typeName; //合同类型名称
    @ApiModelProperty(value = "是否删除；1:是;0:否")
    private Long isDelete; //是否删除；1:是;0:否
    @ApiModelProperty(value = "图片三")
    private String urlC; //图片三
    @ApiModelProperty(value = "合同状态 : 1 未签约  2 已签约")
    private Long status; //合同状态 : 1 未签约  2 已签约
    @ApiModelProperty(value = "供应商ID")
    private String supplierIds; //供应商ID

    public String getName(){  
        return name;  
    }
      
   public void setName(String name){  
     this.name = name;  
    }  
    public String getTypeId(){  
        return typeId;  
    }
      
   public void setTypeId(String typeId){  
     this.typeId = typeId;  
    }  
    public String getOwnerName(){  
        return ownerName;  
    }
      
   public void setOwnerName(String ownerName){  
     this.ownerName = ownerName;  
    }  
    public String getSecondPartyName(){  
        return secondPartyName;  
    }
      
   public void setSecondPartyName(String secondPartyName){  
     this.secondPartyName = secondPartyName;  
    }  
    public Long getEffectiveDate(){  
        return effectiveDate;  
    }
      
   public void setEffectiveDate(Long effectiveDate){  
     this.effectiveDate = effectiveDate;  
    }  
    public String getDescription(){  
        return description;  
    }
      
   public void setDescription(String description){  
     this.description = description;  
    }  
    public String getUrlA(){  
        return urlA;  
    }
      
   public void setUrlA(String urlA){  
     this.urlA = urlA;  
    }  
    public String getUrlB(){  
        return urlB;  
    }
      
   public void setUrlB(String urlB){  
     this.urlB = urlB;  
    }  
    public String getFeedId(){  
        return feedId;  
    }
      
   public void setFeedId(String feedId){  
     this.feedId = feedId;  
    }  
    public Long getSingDate(){  
        return singDate;  
    }
      
   public void setSingDate(Long singDate){  
     this.singDate = singDate;  
    }  
    public String getCreater(){  
        return creater;  
    }
      
   public void setCreater(String creater){  
     this.creater = creater;  
    }  
    public String getComName(){  
        return comName;  
    }
      
   public void setComName(String comName){  
     this.comName = comName;  
    }  
    public String getNo(){  
        return no;  
    }
      
   public void setNo(String no){  
     this.no = no;  
    }  
    public String getPhone(){  
        return phone;  
    }
      
   public void setPhone(String phone){  
     this.phone = phone;  
    }  
    public Long getToonUserId(){  
        return toonUserId;  
    }
      
   public void setToonUserId(Long toonUserId){  
     this.toonUserId = toonUserId;  
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Long getComId(){
        return comId;  
    }
      
   public void setComId(Long comId){  
     this.comId = comId;  
    }  
    public String getTypeName(){  
        return typeName;  
    }
      
   public void setTypeName(String typeName){  
     this.typeName = typeName;  
    }  
    public Long getIsDelete(){  
        return isDelete;  
    }
      
   public void setIsDelete(Long isDelete){  
     this.isDelete = isDelete;  
    }  
    public String getUrlC(){  
        return urlC;  
    }
      
   public void setUrlC(String urlC){  
     this.urlC = urlC;  
    }  
    public Long getStatus(){  
        return status;  
    }
      
   public void setStatus(Long status){  
     this.status = status;  
    }

    public String getSupplierIds() {
        return supplierIds;
    }

    public void setSupplierIds(String supplierIds) {
        this.supplierIds = supplierIds;
    }
}