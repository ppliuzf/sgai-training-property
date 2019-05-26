package com.sgai.property.customer.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class OrgRecordCardDto implements Serializable{
     
    /**
	 * serialVersionUID:(用一句话描述这个变量表示什么).
	 */
	private static final long serialVersionUID = 4862877313385586973L;
	 
    @ApiModelProperty(value = "机构信息id")
    private String orId; //机构信息id
    @ApiModelProperty(value = "客户证件类型主键ID")
    private  String ccnId; //客户证件类型主键ID
    @ApiModelProperty(value = "机构证件类型名称")
    private String orcCertificateName; //机构证件类型名称
    @ApiModelProperty(value = "机构证件号")
    private String orcCertificateNo; //机构证件号


    public OrgRecordCardDto setCcnId(String ccnId) {
        this.ccnId = ccnId;
        return this;
    }

    public String getCcnId() {
        return ccnId;
    }

    public String getOrId() {
        return orId;
    }

    public OrgRecordCardDto setOrId(String orId) {
        this.orId = orId;
        return this;
    }

    public String getOrcCertificateName(){
        return orcCertificateName;  
    }
      
   public void setOrcCertificateName(String orcCertificateName){  
     this.orcCertificateName = orcCertificateName;  
    }  
    public String getOrcCertificateNo(){  
        return orcCertificateNo;  
    }
      
   public void setOrcCertificateNo(String orcCertificateNo){  
     this.orcCertificateNo = orcCertificateNo;  
    }  
    
}