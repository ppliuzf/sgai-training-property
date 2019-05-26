package com.sgai.property.customer.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class OrgRecordCardVo implements Serializable {

    /**
     * serialVersionUID:(用一句话描述这个变量表示什么).
     */
    private static final long serialVersionUID = -2918262038000295056L;
    @ApiModelProperty(value = "机构证件id")
    private String orcId; //机构证件id
    @ApiModelProperty(value = "机构信息id")
    private String orId; //机构信息id
    @ApiModelProperty(value = "机构证件类型名称")
    private String orcCertificateName; //机构证件类型名称
    @ApiModelProperty(value = "机构证件号")
    private String orcCertificateNo; //机构证件号

    public String getOrcId() {
        return orcId;
    }

    public OrgRecordCardVo setOrcId(String orcId) {
        this.orcId = orcId;
        return this;
    }

    public String getOrId() {
        return orId;
    }

    public OrgRecordCardVo setOrId(String orId) {
        this.orId = orId;
        return this;
    }

    public String getOrcCertificateName() {
        return orcCertificateName;
    }

    public void setOrcCertificateName(String orcCertificateName) {
        this.orcCertificateName = orcCertificateName;
    }

    public String getOrcCertificateNo() {
        return orcCertificateNo;
    }

    public void setOrcCertificateNo(String orcCertificateNo) {
        this.orcCertificateNo = orcCertificateNo;
    }
}