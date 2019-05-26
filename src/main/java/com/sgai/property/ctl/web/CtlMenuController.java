package com.sgai.property.ctl.web;

import com.google.common.collect.Maps;
import com.sgai.common.utils.StringUtils;
import com.sgai.common.web.BaseController;
import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.property.common.util.FunctionDtoConvertUtil;
import com.sgai.property.ctl.entity.CtlBusiMenu;
import com.sgai.property.ctl.entity.CtlCompBusi;
import com.sgai.property.ctl.entity.CtlMenu;
import com.sgai.property.ctl.service.CtlBusiMenuService;
import com.sgai.property.ctl.service.CtlCompBusiService;
import com.sgai.property.ctl.service.CtlMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

/**
 * 菜单Controller
 *
 * @author chenxing
 * @version 2017-11-07
 */
@RestController
@RequestMapping(value = "${adminPath}/menu/ctlMenu")
public class CtlMenuController extends BaseController {

    @Autowired
    private CtlMenuService ctlMenuService;
    @Autowired
    private CtlBusiMenuService ctlBusiMenuService;
    @Autowired
    private CtlCompBusiService ctlCompBusiService;

    @ModelAttribute
    public CtlMenu get(@RequestParam(required = false) String id) {
        CtlMenu entity = null;
        if (StringUtils.isNotBlank(id)) {
            entity = ctlMenuService.get(id);
        }
        if (entity == null) {
            entity = new CtlMenu();
        }
        return entity;
    }

    @RequestMapping(value = "form")
    public CtlMenu form(CtlMenu ctlMenu, Model model) {
        return ctlMenuService.getCtlMenu(ctlMenu);
    }

    @RequestMapping(value = "getCompMenu")
    public Map<String, Object> getCompMenu(CtlMenu ctlMenu, LoginUser user, Model model) {
        Map<String, Object> result = Maps.newHashMap();
        CtlMenu info = ctlMenuService.getCtlMenu(ctlMenu);
        CtlCompBusi ctlCompBusi = new CtlCompBusi();
        ctlCompBusi.setComCode(user.getComCode());
        List<CtlCompBusi> list = ctlCompBusiService.findList(ctlCompBusi);
        if (list != null && list.size() > 0) {
            CtlBusiMenu menu = new CtlBusiMenu();
            menu.setMenuCode(info.getMenuCode());
            menu.setBusiCode(list.get(0).getBusiCode());
            CtlBusiMenu busiMenu = ctlBusiMenuService.getBusiMenuByMenuCode(menu);
            if (busiMenu != null) {
                result.put("proId", busiMenu.getId());
                result.put("defineName", busiMenu.getDefineName());
                result.put("defineSort", busiMenu.getDefineSort());
            }
        }

        result.put("id", info.getId());
        result.put("menuCode", info.getMenuCode());
        result.put("menuName", info.getMenuName());
        result.put("parentMenuCode", info.getParentMenuCode());
        result.put("parentMenuName", info.getParentMenuName());
        result.put("progName", info.getProgName());
        result.put("progCode", info.getProgCode());
        result.put("progLevel", info.getProgLevel());
        result.put("sbsName", info.getSbsName());
        result.put("displayOrder", info.getDisplayOrder());


        return result;
    }

    @RequestMapping(value = "save")
    public Map<String, Object> save(CtlMenu ctlMenu) {
        Map<String, Object> result = Maps.newHashMap();
        if (!particularBeanValidator(ctlMenu, result)) {
            return result;
        }
        ctlMenuService.save(ctlMenu);
        result.put("state", true);
        result.put("msg", "保存成功!");
        return result;
    }

    public boolean particularBeanValidator(CtlMenu entity, Map<String, Object> result) {
        if (StringUtils.isBlank(entity.getMenuCode())) {
            result.put("state", false);
            result.put("msg", "菜单代码不能为空!");
            return false;
        }
        if (StringUtils.isBlank(entity.getMenuName())) {
            result.put("state", false);
            result.put("msg", "菜单名称不能为空!");
            return false;
        }
        int count = ctlMenuService.countMenuCodeExceptSelf(entity);
        if (count >= 1) {
            result.put("state", false);
            result.put("msg", "菜单代码不能重复!");
            return false;
        }
        return true;
    }

    @RequestMapping(value = "delete")
    public Map<String, Object> delete(CtlMenu ctlMenu, RedirectAttributes redirectAttributes) {
        Map<String, Object> result = Maps.newHashMap();
        try {
            ctlMenuService.delete(ctlMenu);
            result.put("state", true);
            result.put("msg", "删除成功!");
        } catch (Exception e) {
            e.printStackTrace();
            result.put("state", false);
            result.put("msg", "删除失败!");
        }
        return result;
    }

    @RequestMapping(value = "getMenuList")
    public List<Map<String, String>> getMenuList() {
        return FunctionDtoConvertUtil.convertDtoToMap(ctlMenuService.getMenuList());
    }

    @RequestMapping(value = "getProgList")
    public List<Map<String, Object>> getProgList() {
        return ctlMenuService.getProgList();
    }

    @RequestMapping(value = "getCompDefineMenuList")
    public List<Map<String, String>> getCompDefineMenuList(LoginUser user) {
        return ctlMenuService.getCompDefineMenuList(user.getComCode());
    }

}