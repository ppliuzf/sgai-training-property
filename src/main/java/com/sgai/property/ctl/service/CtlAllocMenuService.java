package com.sgai.property.ctl.service;

import com.google.common.collect.Lists;
import com.sgai.common.persistence.Page;
import com.sgai.common.service.CrudServiceExt;
import com.sgai.property.common.util.FunctionDtoConvertUtil;
import com.sgai.property.ctl.dao.CtlAllocMenuDao;
import com.sgai.property.ctl.dao.CtlBusiMenuDao;
import com.sgai.property.ctl.dto.FunctionDto;
import com.sgai.property.ctl.entity.CtlAllocMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 机构管理员菜单分配Service
 *
 * @author chenxing
 * @version 2017-11-10
 */
@Service
@Transactional
public class CtlAllocMenuService extends CrudServiceExt<CtlAllocMenuDao, CtlAllocMenu> {
    @Autowired
    private CtlBusiMenuDao ctlBusiMenuDao;

    public CtlAllocMenu get(String id) {
        return super.get(id);
    }

    public List<CtlAllocMenu> findList(CtlAllocMenu ctlAllocMenu) {
        return super.findList(ctlAllocMenu);
    }

    public Page<CtlAllocMenu> findPage(Page<CtlAllocMenu> page, CtlAllocMenu ctlAllocMenu) {
        return super.findPage(page, ctlAllocMenu);
    }

    @Transactional()
    public void save(CtlAllocMenu ctlAllocMenu) {
        super.save(ctlAllocMenu);
    }

    @Transactional()
    public void delete(CtlAllocMenu ctlAllocMenu) {
        super.delete(ctlAllocMenu);
    }

    public Page<CtlAllocMenu> getRolePage(Page<CtlAllocMenu> page, CtlAllocMenu param) {
        param.setPage(page);
        page.setList(dao.getRolePage(param));
        return page;
    }

    public Page<CtlAllocMenu> getUserPage(Page<CtlAllocMenu> page, CtlAllocMenu param) {
        param.setPage(page);
        page.setList(dao.getUserPage(param));
        return page;
    }


    /**
     * 获取该角色/用户没有的菜单
     */
    public List<Map<String, String>> getMenuListLack(Map<String, String> param) {
        List<FunctionDto> allList = dao.getMenuList();
        List<FunctionDto> compList = dao.getCompDefineMenuList(param);
        List<FunctionDto> ownList = dao.getMenuListOwn(param);
        List<FunctionDto> unOwnedList = allList.stream()
                .filter(all -> !ownList.stream()
                        .map(FunctionDto::getMenuCode)
                        .collect(Collectors.toList())
                        .contains(all.getMenuCode()))
                .distinct().collect(Collectors.toList());
        return FunctionDtoConvertUtil.convertDtoToMap(unOwnedList);
    }

    /**
     * 获取该模块管理员没有的菜单
     */
    public List<Map<String, String>> getModuUserMenuListLack(Map<String, String> param) {
        List<FunctionDto> allList = dao.getMenuList();
        List<FunctionDto> compList = dao.getMenuListByModu(param);
        List<FunctionDto> ownList = dao.getMenuListOwn(param);
        List<FunctionDto> unOwnedList = allList.stream()
                .filter(all -> !ownList.stream()
                        .map(FunctionDto::getMenuCode)
                        .collect(Collectors.toList())
                        .contains(all.getMenuCode()))
                .collect(Collectors.toList());
        return FunctionDtoConvertUtil.convertDtoToMap(unOwnedList);
    }

    /**
     * 获取该模块没有的菜单
     */
    public List<Map<String, String>> getModuMenuListLack(Map<String, String> param, String busiCode) {
        List<Map<String, String>> result = Lists.newArrayList();
        List<FunctionDto> allList = dao.getMenuList();
        List<FunctionDto> compList = ctlBusiMenuDao.getMenuListOwn(busiCode);
        List<FunctionDto> ownList = dao.getModuMenuListOwn(param);
        List<FunctionDto> unOwnedList = allList.stream().filter(functionDto -> !ownList.contains(functionDto)).distinct().collect(Collectors.toList());
        return FunctionDtoConvertUtil.convertDtoToMap(unOwnedList);
    }

    private void iteratedAddParent(String menuCode,
                                   Map<String, Map<String, String>> all,
                                   Set<String> set) {
        Map<String, String> map = all.get(menuCode);
        if (map == null)
            return;
        String parentMenuCode = map.get("PARENT_MENU_CODE");
        set.add(parentMenuCode);
        iteratedAddParent(parentMenuCode, all, set);
    }

    /**
     * 获取该角色/用户拥有的菜单
     */
    public List<Map<String, String>> getMenuListOwn(Map<String, String> param) {
        List<FunctionDto> list = dao.getMenuListOwn(param);
        return FunctionDtoConvertUtil.convertDtoToMap(list.stream().distinct().collect(Collectors.toList()));
    }

    /**
     * 获取该模块拥有的菜单
     */
    public List<Map<String, String>> getModuMenuListOwn(Map<String, String> param) {
        List<FunctionDto> list = dao.getModuMenuListOwn(param);
        return FunctionDtoConvertUtil.convertDtoToMap(list);
    }

    @Transactional(readOnly = false)
    public void deleteMenuTree(Map<String, String> param) {
        dao.deleteMenuTree(param);
    }

    @Transactional(readOnly = false)
    public void saveMenuTree(String corrCode, List<String> menuCodeList,
                             String category, String comCode) {
        for (String menuCode : menuCodeList) {
            CtlAllocMenu entity = new CtlAllocMenu();
            entity.setCorrCode(corrCode);
            entity.setMenuCode(menuCode);
            entity.setCategory(category);
            entity.setComCode(comCode);
            save(entity);
        }
    }

    /**
     * @param @param  param
     * @param @return 参数
     * @return int    返回类型
     * @throws
     * @Title: findMenuByUserCode
     * @Description: (查看用户是否拥有某个菜单 ， 返回0是未拥有 ， 返回1是拥有)
     */
    @Transactional
    public int findMenuByUserCode(Map<String, String> param) {
        return dao.findMenuByUserCode(param);
    }
}
