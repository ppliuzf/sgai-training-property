package com.sgai.property.depot.vo;

import com.sgai.property.depot.entity.Inventories;

import java.util.Date;

/**
 * Created by 145811 on 2018/1/25.
 */
public class InventoriesExt extends Inventories {
    private Date bbTime;
    private Date eeTime;

    public Date getBbTime() {
        return bbTime;
    }

    public void setBbTime(Date bbTime) {
        this.bbTime = bbTime;
    }

    public Date getEeTime() {
        return eeTime;
    }

    public void setEeTime(Date eeTime) {
        this.eeTime = eeTime;
    }
}
