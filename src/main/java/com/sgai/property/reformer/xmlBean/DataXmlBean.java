package com.sgai.property.reformer.xmlBean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

/**
 * @author ppliu
 * created in 2019/1/18 20:33
 */
@XmlRootElement(name = "real")
@XmlAccessorType(XmlAccessType.FIELD)
public class DataXmlBean {
    @XmlAttribute
    private BigDecimal val;

    public BigDecimal getVal() {
        return val;
    }

    public void setVal(BigDecimal val) {
        this.val = val;
    }
}
