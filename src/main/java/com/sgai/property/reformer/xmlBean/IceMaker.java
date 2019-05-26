package com.sgai.property.reformer.xmlBean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author ppliu
 * created in 2019/1/25 11:36
 * 制冰机.
 */
@XmlRootElement(name = "real")
@XmlAccessorType(XmlAccessType.FIELD)
public class IceMaker {
    /** 编号 */
    private Integer number;
    /** 名字. */
    private String name;
    /** 状态.【0运行，1停止】 */
    private String status;
    @XmlAttribute
    private Boolean val;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getVal() {
        return val;
    }

    public void setVal(Boolean val) {
        this.val = val;
    }
}
