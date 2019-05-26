package com.sgai.property.mdm.web;

import com.sgai.common.config.Global;
import com.sgai.common.persistence.Page;
import com.sgai.common.utils.StringUtils;
import com.sgai.common.web.BaseController;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.mdm.entity.MdmBuildInfo;
import com.sgai.property.mdm.service.MdmBuildInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 楼宇描述 ---空间Controller
 *
 * @author zhb
 * @version 2017-11-24
 */
@RestController
@RequestMapping(value = "${adminPath}/mdm/mdmBuildInfo")
public class MdmBuildInfoController extends BaseController {

    @Autowired
    private MdmBuildInfoService mdmBuildInfoService;

    @ModelAttribute
    public MdmBuildInfo get(@RequestParam(required = false) String id) {
        MdmBuildInfo entity = null;
        if (StringUtils.isNotBlank(id)) {
            entity = mdmBuildInfoService.get(id);
        }
        if (entity == null) {
            entity = new MdmBuildInfo();
        }
        return entity;
    }

    //@RequiresPermissions("mdm:mdmBuildInfo:view")
    @RequestMapping(value = {"list", ""})
    public String list(MdmBuildInfo mdmBuildInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
        if (mdmBuildInfo == null) {
            mdmBuildInfo = new MdmBuildInfo();
        }
        Page<MdmBuildInfo> page = mdmBuildInfoService.findPage(new Page<MdmBuildInfo>(request, response), mdmBuildInfo);
        model.addAttribute("page", page);
        return "modules/mdm/mdmBuildInfoList";
    }

    //@RequiresPermissions("mdm:mdmBuildInfo:view")
    @RequestMapping(value = "form")
    public String form(MdmBuildInfo mdmBuildInfo, Model model) {
        model.addAttribute("mdmBuildInfo", mdmBuildInfo);
        return "modules/mdm/mdmBuildInfoForm";
    }

    //@RequiresPermissions("mdm:mdmBuildInfo:edit")
    @RequestMapping(value = "save")
    public String save(MdmBuildInfo mdmBuildInfo, Model model, RedirectAttributes redirectAttributes) {
        if (!beanValidator(model, mdmBuildInfo)) {
            return form(mdmBuildInfo, model);
        }
        mdmBuildInfoService.save(mdmBuildInfo);
        addMessage(redirectAttributes, "保存楼宇描述 ---空间成功");
        return "redirect:" + Global.getAdminPath() + "/mdm/mdmBuildInfo/?repage";
    }

    //@RequiresPermissions("mdm:mdmBuildInfo:edit")
    @RequestMapping(value = "delete")
    public String delete(MdmBuildInfo mdmBuildInfo, RedirectAttributes redirectAttributes) {
        String idMerge = mdmBuildInfo.getId();
        String ids[] = idMerge.split("~");
        for (String id : ids) {
            if (id != null && !id.equals("")) {
                mdmBuildInfo = new MdmBuildInfo();
                mdmBuildInfo.setId(id);
                mdmBuildInfoService.delete(mdmBuildInfo);
            }
        }
        addMessage(redirectAttributes, "删除楼宇描述 ---空间成功");
        return "redirect:" + Global.getAdminPath() + "/mdm/mdmBuildInfo/?repage";
    }


    @RequestMapping(value = "/getAllBuildList", method = RequestMethod.POST)
    public List<MdmBuildInfo> getFloorList(HttpServletRequest request, HttpServletResponse response) {
        MdmBuildInfo mdmBuildInfo = new MdmBuildInfo();
        mdmBuildInfo.setComCode(UserServletContext.getUserInfo().getComCode());
        return mdmBuildInfoService.findList(mdmBuildInfo);
    }
}