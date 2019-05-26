package com.sgai.property.customer.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 客户详情信息入参
 *
 * @author Hou
 * @create 2018-03-16 13:34
 */
public class CustomInfoParam implements Serializable{

    @ApiModelProperty(value = "客户姓名")
    private String ciOwnerName;
    @ApiModelProperty(value = "客户电话1")
    private Long ciOwnerPhone1;
    @ApiModelProperty(value = "客户电话2")
    private Long ciOwnerPhone2;

    public String getCiOwnerName() {
        return ciOwnerName;
    }

    public CustomInfoParam setCiOwnerName(String ciOwnerName) {
        this.ciOwnerName = ciOwnerName;
        return this;
    }

    public Long getCiOwnerPhone1() {
        return ciOwnerPhone1;
    }

    public CustomInfoParam setCiOwnerPhone1(Long ciOwnerPhone1) {
        this.ciOwnerPhone1 = ciOwnerPhone1;
        return this;
    }

    public Long getCiOwnerPhone2() {
        return ciOwnerPhone2;
    }

    public CustomInfoParam setCiOwnerPhone2(Long ciOwnerPhone2) {
        this.ciOwnerPhone2 = ciOwnerPhone2;
        return this;
    }
}
