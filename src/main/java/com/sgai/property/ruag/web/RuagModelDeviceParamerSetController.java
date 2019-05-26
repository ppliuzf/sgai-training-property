package com.sgai.property.ruag.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sgai.common.config.Global;
import com.sgai.common.persistence.Page;
import com.sgai.common.utils.StringUtils;
import com.sgai.common.web.BaseController;
import com.sgai.property.ruag.entity.RuagModelDeviceParamerSet;
import com.sgai.property.ruag.service.RuagModelDeviceParamerSetService;

/**
 * 模式设备参数设置Controller
 * @author yangyz
 * @version 2018-01-02
 */
@RestController
@RequestMapping(value = "ruag/ruag/ruagModelDeviceParamerSet")
public class RuagModelDeviceParamerSetController extends BaseController {

	@Autowired
	private RuagModelDeviceParamerSetService ruagModelDeviceParamerSetService;
}