package com.sgai.property.customer.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class CustomCardInfoDto implements Serializable {
     

	private static final long serialVersionUID = 5570529424118257082L;
    @ApiModelProperty(value = "客户证件类型主键ID")
    private  String ccnId; //客户证件类型主键ID
    @ApiModelProperty(value = "客户证件名称")
    private  String ccnName;
    @ApiModelProperty(value = "客户证件类型名称")
    private String ccCertificateName; //客户证件类型名称
    @ApiModelProperty(value = "客户证件号")
    private String ccCertificateNo; //客户证件号
    @ApiModelProperty(value = "是否删除")
    private Long ccIsDelete; //是否删除
    private String ID;
    @ApiModelProperty(value = "客户详情id")
    private String prId;

    public String getPrId() {
        return prId;
    }

    public CustomCardInfoDto setPrId(String prId) {
        this.prId = prId;
        return this;
    }

    public String getCcnName() {
        return ccnName;
    }

    public CustomCardInfoDto setCcnName(String ccnName) {
        this.ccnName = ccnName;
        return this;
    }

    public String getID() {
        return ID;
    }

    public CustomCardInfoDto setID(String ID) {
        this.ID = ID;
        return this;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getCcIsDelete() {
        return ccIsDelete;
    }

    public CustomCardInfoDto setCcIsDelete(Long ccIsDelete) {
        this.ccIsDelete = ccIsDelete;
        return this;
    }

    public String getCcnId() {
        return ccnId;
    }

    public CustomCardInfoDto setCcnId(String ccnId) {
        this.ccnId = ccnId;
        return this;
    }

    public String getCcCertificateName(){
        return ccCertificateName;  
    }
      
   public void setCcCertificateName(String ccCertificateName){  
     this.ccCertificateName = ccCertificateName;  
    }  
    public String getCcCertificateNo(){  
        return ccCertificateNo;  
    }
      
   public void setCcCertificateNo(String ccCertificateNo){  
     this.ccCertificateNo = ccCertificateNo;  
    }  
     
}