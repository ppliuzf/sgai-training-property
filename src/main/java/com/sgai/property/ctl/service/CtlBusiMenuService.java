package com.sgai.property.ctl.service;

import com.sgai.common.persistence.Page;
import com.sgai.common.service.CrudServiceExt;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.common.util.FunctionDtoConvertUtil;
import com.sgai.property.ctl.dao.CtlBusiMenuDao;
import com.sgai.property.ctl.dto.FunctionDto;
import com.sgai.property.ctl.entity.CtlBusiMenu;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;


/**
 * 子系统和菜单的关联关系Service
 *
 * @author admin
 * @version 2018-03-27
 */
@Service
@Transactional
public class CtlBusiMenuService extends CrudServiceExt<CtlBusiMenuDao, CtlBusiMenu> {

    public CtlBusiMenu get(String id) {
        return super.get(id);
    }

    public List<CtlBusiMenu> findList(CtlBusiMenu ctlBusiMenu) {
        return super.findList(ctlBusiMenu);
    }

    public Page<CtlBusiMenu> findPage(Page<CtlBusiMenu> page, CtlBusiMenu ctlBusiMenu) {
        return super.findPage(page, ctlBusiMenu);
    }

    /**
     * 获得子系统分配菜单的 左侧列表
     * getBusiPage:(这里用一句话描述这个方法的作用).
     *
     * @param page
     * @param param
     * @return :Page<CtlBusiMenu>
     * @author admin
     * @since JDK 1.8
     */
    public Page<CtlBusiMenu> getBusiPage(Page<CtlBusiMenu> page, CtlBusiMenu param) {
        param.setPage(page);
        page.setList(dao.getBusiList(param));
        return page;
    }


    /**
     * 获取该机构没有的菜单
     * getMenuListLack:(这里用一句话描述这个方法的作用).
     *
     * @return :List<Map<String,String>>
     * @author admin
     * @since JDK 1.8
     */
    public List<Map<String, String>> getMenuListLack(String busiCode) {
        List<FunctionDto> allList = dao.getMenuListAll();
        List<FunctionDto> ownList = dao.getMenuListOwn(busiCode);
        List<FunctionDto> unOwnedList = allList.stream()
                .filter(all ->ownList.stream()
                        .map(FunctionDto::getMenuCode)
                        .collect(Collectors.toList())
                        .contains(all.getMenuCode()))
                .collect(Collectors.toList());
        return FunctionDtoConvertUtil.convertDtoToMap(unOwnedList);
    }


   /* private void iteratedAddParent(String menuCode,
                                   Map<String, Map<String, String>> all,
                                   Set<String> set) {
        Map<String, String> map = all.get(menuCode);
        if (map == null)
            return;
        String parentMenuCode = map.get("PARENT_MENU_CODE");
        set.add(parentMenuCode);
        iteratedAddParent(parentMenuCode, all, set);
    }*/

    /**
     * 获取该机构拥有的菜单
     * getMenuListOwn:(这里用一句话描述这个方法的作用).
     *
     * @return :List<Map<String,String>>
     * @author admin
     * @since JDK 1.8
     */
    public List<Map<String, String>> getMenuListOwn(String busiCode) {
        List<FunctionDto> list = dao.getMenuListOwn(busiCode);
        return FunctionDtoConvertUtil.convertDtoToMap(list);
    }


    /**
     * 按照子系统编码删除菜单关系
     * deleteMenuTreeByBusiCode:(这里用一句话描述这个方法的作用).
     *
     * @param busiCode :void
     * @author admin
     * @since JDK 1.8
     */
    public void deleteMenuTreeByBusiCode(String busiCode) {
        dao.deleteMenuTreeByBusiCode(busiCode);
    }


    /**
     * 保存子系统和菜单的关系
     * saveMenuTree:(这里用一句话描述这个方法的作用).
     *
     * @param menuCodeList :void
     * @author admin
     * @since JDK 1.8
     */
    @Transactional(readOnly = false)
    public void saveMenuTree(String busiCode, List<String> menuCodeList) {
        for (String menuCode : menuCodeList) {
            CtlBusiMenu ctlBusiMenu = new CtlBusiMenu();
            ctlBusiMenu.setBusiCode(busiCode);
            ctlBusiMenu.setMenuCode(menuCode);
            save(ctlBusiMenu);
        }
    }

    @Transactional(readOnly = false)
    public void save(CtlBusiMenu ctlBusiMenu) {
        super.save(ctlBusiMenu);
    }

    @Transactional(readOnly = false)
    public void delete(CtlBusiMenu ctlBusiMenu) {
        super.delete(ctlBusiMenu);
    }

    public CtlBusiMenu getBusiMenuByMenuCode(CtlBusiMenu ctlBusiMenu) {
        ctlBusiMenu.setComCode(UserServletContext.getUserInfo().getComCode());
        ctlBusiMenu.preGet();
        CtlBusiMenu defineBusiMenu = dao.getBusiMenuByMenuCode(ctlBusiMenu);
        if (defineBusiMenu == null) {
            ctlBusiMenu.setComCode(null);
            defineBusiMenu = dao.getBusiMenuByMenuCode(ctlBusiMenu);
        }
        return defineBusiMenu;
    }

    public void removeEmptyMenu(Set<String> lackSet) {
        List<String> emptyMenu = dao.getEmptyMenu();
        for (String string : emptyMenu) {
            lackSet.remove(string);
        }
    }
}
