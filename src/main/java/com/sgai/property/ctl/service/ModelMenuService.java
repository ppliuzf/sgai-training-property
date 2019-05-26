package com.sgai.property.ctl.service;

import com.sgai.property.ctl.dao.IndexMenusDao;
import com.sgai.property.ctl.entity.IndexMenus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ppliu
 * created in 2019/1/2 13:55
 */
@Service
public class ModelMenuService {
    @Autowired
    private IndexMenusDao indexMenusDao;

    /**
     * 获取模块对应的菜单.
     *
     * @param userType  用户类型.
     * @param userCode  用户编码.
     * @param modelName tab页名称.
     */
    public List<IndexMenus> getIndexMenusByModelName(String userType, String userCode, String modelName) {
        //缓存中 没有 从数据库中获得
        List<IndexMenus> userMenuList = indexMenusDao.getUserMenuList(userType, userCode);
        List<IndexMenus> roleMenuList = indexMenusDao.getRoleMenuList(userType, userCode);
        userMenuList.addAll(roleMenuList);
        List<IndexMenus> allMenuList = userMenuList.stream().distinct().sorted(Comparator.comparing(IndexMenus::getDisplayOrder)).collect(Collectors.toList());
        List<IndexMenus> modelMenuList = allMenuList.stream().filter(indexMenus -> modelName.equals(indexMenus.getMenuName())).collect(Collectors.toList());
        treeModelMenus(allMenuList, modelMenuList);
        return modelMenuList.stream().map(IndexMenus::getChildrenMenus).flatMap(List::stream).collect(Collectors.toList());
    }

    private void treeModelMenus(List<IndexMenus> allMenuList, List<IndexMenus> modelMenuList) {
        modelMenuList.forEach(indexMenus -> {
            List<IndexMenus> children = allMenuList.stream().filter(allMenu -> allMenu.getParnetMenuCode().equals(indexMenus.getMenuCode())).collect(Collectors.toList());
            if (!children.isEmpty()) {
                indexMenus.setChildrenMenus(children);
                treeModelMenus(allMenuList, children);
            } else {
                indexMenus.setChildrenMenus(new ArrayList<>());
            }
        });
    }
}
