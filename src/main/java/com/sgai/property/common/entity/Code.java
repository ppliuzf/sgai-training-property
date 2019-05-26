package com.sgai.property.common.entity;

import javax.persistence.*;

/**
 * @author ppliu
 * created in 2019/1/7 15:57
 */
@Entity
@Table(name = "sys_code")
public class Code {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long codeNum;
    private String codeType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCodeNum() {
        return codeNum;
    }

    public void setCodeNum(Long codeNum) {
        this.codeNum = codeNum;
    }

    public String getCodeType() {
        return codeType;
    }

    public void setCodeType(String codeType) {
        this.codeType = codeType;
    }
}
