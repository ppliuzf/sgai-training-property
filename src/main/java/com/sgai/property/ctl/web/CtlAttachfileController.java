
/**
 * @Title: CtlAttachfileController.java
 * @Package com.sgai.modules.ctl.web
 * @Description: (用一句话描述该文件做什么)
 * @author smartcity
 * @date 2017年12月31日
 * @Company 首自信--智慧城市创新中心
 * @version V1.0
 */

package com.sgai.property.ctl.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sgai.common.persistence.Page;
import com.sgai.property.ctl.entity.CtlAttachfile;
import com.sgai.property.ctl.service.CtlAttachfileService;

/**
 * @ClassName: CtlAttachfileController
 * @Description: (这里用一句话描述这个类的作用)
 * @author chenxing
 * @date 2017年12月31日
 * @Company 首自信--智慧城市创新中心
 */
@RestController
@RequestMapping(value = "${adminPath}/ctl/ctlAttachfile")
public class CtlAttachfileController {

    @Autowired
    private CtlAttachfileService ctlAttachfileService;

    @RequestMapping(value = "getPageAttachfile")
    public Page<CtlAttachfile> getPageAttachfile(CtlAttachfile param, HttpServletRequest request, HttpServletResponse response) {
        Page<CtlAttachfile> page = ctlAttachfileService.getAttachfilePage(new Page<CtlAttachfile>(request, response), param);
        return page;
    }

    @RequestMapping(value = "getAttachfileList")
    public List<CtlAttachfile> getAttachfileList(@RequestBody CtlAttachfile entity) {
        return ctlAttachfileService.getAttachfileList(entity);
    }

    @RequestMapping(value = "saveAttachfile")
    public void saveAttachfile(@RequestBody CtlAttachfile entity) {
        ctlAttachfileService.saveAttachfile(entity);
    }

    @RequestMapping(value = "saveAttachfiles")
    public void saveAttachfiles(@RequestBody List<CtlAttachfile> list) {
        ctlAttachfileService.saveAttachfiles(list);
    }

    @RequestMapping(value = "deleteAttachfile")
    public void deleteAttachfile(@RequestBody String attachfileId) {
        ctlAttachfileService.deleteAttachfile(attachfileId);
    }

    @RequestMapping(value = "deleteAttachfiles")
    public void deleteAttachfile(@RequestBody List<String> list) {
        ctlAttachfileService.deleteAttachfiles(list);
    }

    @RequestMapping(value = "deleteByMasterFileId")
    public void deleteByMasterFileId(@RequestBody Map<String, Object> params) {
        ctlAttachfileService.deleteByMasterFileId(params);
    }
}
