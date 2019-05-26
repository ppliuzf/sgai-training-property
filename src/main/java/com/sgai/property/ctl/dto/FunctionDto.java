package com.sgai.property.ctl.dto;

import java.util.Objects;

/**
 * @author ppliu
 * created in 2018/12/27 15:30
 */
public class FunctionDto {
    private String parentMenuCode;
    private String menuName;
    private String menuCode;

    public String getParentMenuCode() {
        return parentMenuCode;
    }

    public void setParentMenuCode(String parentMenuCode) {
        this.parentMenuCode = parentMenuCode;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FunctionDto)) return false;
        FunctionDto that = (FunctionDto) o;
        return Objects.equals(menuCode, that.menuCode);
    }

    @Override
    public int hashCode() {

        return Objects.hash(menuCode);
    }
}
