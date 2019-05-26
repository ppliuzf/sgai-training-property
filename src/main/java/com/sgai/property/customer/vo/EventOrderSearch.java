package com.sgai.property.customer.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @author Hou
 * @create 2018-03-21 13:51
 */
public class EventOrderSearch implements Serializable {

    @ApiModelProperty(value = "事件联系人")
    private String contactPerson;
    @ApiModelProperty(value = "联系人电话(支持多个)")
    private List<String> telephones;

    public String getContactPerson() {
        return contactPerson;
    }

    public EventOrderSearch setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
        return this;
    }

    public List<String> getTelephones() {
        return telephones;
    }

    public EventOrderSearch setTelephones(List<String> telephones) {
        this.telephones = telephones;
        return this;
    }
}
