package com.sgai.property.customer.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class CustomCardInfoVo implements Serializable {
     
    /**
	 * serialVersionUID:(用一句话描述这个变量表示什么).
	 */
	private static final long serialVersionUID = 5570529424118257082L;
	@ApiModelProperty(value = "客户证件详情id")
    private String ccId; //客户证件详情id
    @ApiModelProperty(value = "客户信息主键ID")
    private String prId; //客户信息主键ID
    @ApiModelProperty(value = "客户证件类型名称")
    private String ccCertificateName; //客户证件类型名称
    @ApiModelProperty(value = "客户证件号")
    private String ccCertificateNo; //客户证件号

    @ApiModelProperty(value = "客户证件详情id")
    private String ccnId; //客户证件详情id

    public String getCcnId() {
        return ccnId;
    }

    public CustomCardInfoVo setCcnId(String ccnId) {
        this.ccnId = ccnId;
        return this;
    }

    public String getCcId() {
        return ccId;
    }

    public CustomCardInfoVo setCcId(String ccId) {
        this.ccId = ccId;
        return this;
    }

    public String getPrId() {
        return prId;
    }

    public CustomCardInfoVo setPrId(String prId) {
        this.prId = prId;
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