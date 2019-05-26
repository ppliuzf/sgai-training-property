package com.sgai.property.quality.vo.plan;

import java.io.Serializable;

/**
 * @author hou
 * @date 2018-01-09 19:48
 */
public class SpaceTreeVo implements Serializable {

    private String name;
    private String value;
    private String parent;

    public String getName() {
        return name;
    }

    public SpaceTreeVo setName(String name) {
        this.name = name;
        return this;
    }

    public String getValue() {
        return value;
    }

    public SpaceTreeVo setValue(String value) {
        this.value = value;
        return this;
    }

    public String getParent() {
        return parent;
    }

    public SpaceTreeVo setParent(String parent) {
        this.parent = parent;
        return this;
    }
}
