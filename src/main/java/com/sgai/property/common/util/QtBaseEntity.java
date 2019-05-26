package com.sgai.property.common.util;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sgai.common.persistence.Page;

import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;

public class QtBaseEntity <T> implements Serializable {
    /**
     * 当前实体分页对象
     */
    protected Page<T> page;

    @JsonIgnore
    @XmlTransient
    public Page<T> getPage() {
        if (page == null) {
            page = new Page<T>();
        }
        return page;
    }

    public Page<T> setPage(Page<T> page) {
        this.page = page;
        return page;
    }
}
