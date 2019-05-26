package com.sgai.property.customer.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class OrgRecordLinkmanVo implements Serializable {

    /**
     * serialVersionUID:(用一句话描述这个变量表示什么).
     */
    private static final long serialVersionUID = -6562608653461532717L;
    @ApiModelProperty(value = "机构联系人详情")
    private Long orlId; //机构联系人详情
    @ApiModelProperty(value = "机构信息id")
    private String orId; //机构信息id
    @ApiModelProperty(value = "联系人名称")
    private String orlLinkman; //联系人名称
    @ApiModelProperty(value = "联系人电话")
    private String orlPhone; //联系人电话

    public String getOrId() {
        return orId;
    }

    public OrgRecordLinkmanVo setOrId(String orId) {
        this.orId = orId;
        return this;
    }

    public Long getOrlId() {
        return orlId;
    }

    public void setOrlId(Long orlId) {
        this.orlId = orlId;
    }

    public String getOrlLinkman() {
        return orlLinkman;
    }

    public void setOrlLinkman(String orlLinkman) {
        this.orlLinkman = orlLinkman;
    }

    public String getOrlPhone() {
        return orlPhone;
    }

    public void setOrlPhone(String orlPhone) {
        this.orlPhone = orlPhone;
    }
}