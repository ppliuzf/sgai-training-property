package com.sgai.property.ctl.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sgai.common.service.CrudServiceExt;
import com.sgai.property.common.util.FunctionDtoConvertUtil;
import com.sgai.property.ctl.dao.CtlMenuDao;
import com.sgai.property.ctl.dto.FunctionDto;
import com.sgai.property.ctl.entity.CtlMenu;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 菜单Service
 *
 * @author chenxing
 * @version 2017-11-07
 */
@Service
@Transactional
public class CtlMenuService extends CrudServiceExt<CtlMenuDao, CtlMenu> {
    @Autowired
    private CtlCompBusiService ctlCompBusiService;

    public CtlMenu get(String id) {
        return super.get(id);
    }

    public CtlMenu getCtlMenu(CtlMenu ctlMenu) {
        return dao.getCtlMenu(ctlMenu);
    }

    public List<CtlMenu> findList(CtlMenu ctlMenu) {
        return super.findList(ctlMenu);
    }

    /**
     * 统计除自己之外的地方，总共出现了几个同样的menuCode
     */
    public Integer countMenuCodeExceptSelf(CtlMenu entity) {
        return dao.countMenuCodeExceptSelf(entity);
    }

    @Transactional(readOnly = false)
    public void save(CtlMenu ctlMenu) {
        if (StringUtils.isBlank(ctlMenu.getId())) {//id为空，表示新节点
            ctlMenu.setEnabledFlag("Y");
            ctlMenu.setFinalLevFlag("Y");
            String parentCode = ctlMenu.getParentMenuCode();
            if (parentCode != null && !"0".equals(parentCode)) {//父节点存在
                CtlMenu param = new CtlMenu();
                param.setMenuCode(parentCode);
                CtlMenu parentMenu = getCtlMenu(param);
                parentMenu.setFinalLevFlag("N");//父节点不再是叶子节点
                super.save(parentMenu);
                Long parentMenuLevel = parentMenu.getMenuLevel();
                ctlMenu.setMenuLevel(parentMenuLevel + 1);//新节点的level
            } else {
                ctlMenu.setMenuLevel(1L);
            }
        }
        if (ctlMenu.getDisplayOrder() == null) {
            ctlMenu.setDisplayOrder(1L);
        }
        super.save(ctlMenu);
    }

    @Transactional(readOnly = false)
    public void delete(CtlMenu ctlMenu) {
        String parentCode = ctlMenu.getParentMenuCode();
        if (parentCode != null && !"0".equals(parentCode)) {
            //父节点存在
            CtlMenu param = new CtlMenu();
            param.setMenuCode(parentCode);
            CtlMenu parentMenu = getCtlMenu(param);
            parentMenu.setFinalLevFlag("N");//父节点变成叶子节点
            super.save(parentMenu);
        }
        super.delete(ctlMenu);
    }

    public List<FunctionDto> getMenuList() {
        return dao.getMenuList();
    }

    public List<Map<String, Object>> getProgList() {
        List<Map<String, Object>> result = Lists.newArrayList();
        List<Map<String, Object>> list = dao.getProgList();
        for (Map<String, Object> map : list) {
            Map<String, Object> newMap = Maps.newHashMap();
            newMap.put("id", map.get("id"));
            newMap.put("pId", map.get("pid"));
            newMap.put("name", map.get("name"));
            newMap.put("plevel", map.get("plevel"));
            result.add(newMap);
        }
        return result;
    }

    public List<Map<String, String>> getCompDefineMenuList(String comCode) {
        List<Map<String, Object>> result = Lists.newArrayList();
        List<FunctionDto> list = dao.getCompDefineMenuList();
        return FunctionDtoConvertUtil.convertDtoToMap(list);
    }

}
