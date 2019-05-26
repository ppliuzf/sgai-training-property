package com.sgai.property.ctl.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author admin
 * @ClassName: IndexMenus
 * @Description: (这里用一句话描述这个类的作用)
 * @date 2017年11月11日
 * @Company 首自信--智慧城市创新中心
 */

public class IndexMenus implements Serializable {

    private static final long serialVersionUID = 100L;

    private String menuCode;    //菜单编码
    private String menuName;    //菜单名称
    private String parnetMenuCode;  //父菜单编码
    private String progPath;   //页面访问路径
    private String defineName;//页面自定义菜单
    private Long displayOrder;
    private List<IndexMenus> childrenMenus;   //子菜单

    private String icon;//菜单图标


    public IndexMenus() {
    }

    public IndexMenus(String menuCode, String menuName, String parnetMenuCode, String progPath, String icon) {
        this.menuCode = menuCode;
        this.menuName = menuName;
        this.parnetMenuCode = parnetMenuCode;
        this.progPath = progPath;
        this.icon = icon;
    }

    public IndexMenus(String menuCode, String menuName, String parnetMenuCode, String progPath, String defineName, String icon) {

        this.menuCode = menuCode;
        this.menuName = menuName;
        this.parnetMenuCode = parnetMenuCode;
        this.progPath = progPath;
        this.defineName = defineName;
        this.icon = icon;

        this.childrenMenus = new ArrayList<IndexMenus>();
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getParnetMenuCode() {
        return parnetMenuCode;
    }

    public void setParnetMenuCode(String parnetMenuCode) {
        this.parnetMenuCode = parnetMenuCode;
    }

    public String getProgPath() {
        return progPath;
    }

    public void setProgPath(String progPath) {
        this.progPath = progPath;
    }

    public List<IndexMenus> getChildrenMenus() {
        return childrenMenus;
    }

    public void setChildrenMenus(List<IndexMenus> childrenMenus) {
        this.childrenMenus = childrenMenus;
    }

    public String getDefineName() {
        return defineName;
    }

    public void setDefineName(String defineName) {
        this.defineName = defineName;
    }

    /**
     * icon.
     *
     * @return the icon
     * @since JDK 1.8
     */
    public String getIcon() {
        return icon;
    }

    /**
     * icon.
     *
     * @param icon the icon to set
     * @since JDK 1.8
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Long getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Long displayOrder) {
        this.displayOrder = displayOrder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IndexMenus)) return false;
        IndexMenus that = (IndexMenus) o;
        return Objects.equals(menuCode, that.menuCode);
    }

    @Override
    public int hashCode() {

        return Objects.hash(menuCode);
    }
}
