package com.sgai.property.ctl.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sgai.common.config.Global;
import com.sgai.common.persistence.Page;
import com.sgai.common.utils.StringUtils;
import com.sgai.common.web.BaseController;
import com.sgai.property.ctl.entity.CtlBusinessDefine;
import com.sgai.property.ctl.entity.CtlComp;
import com.sgai.property.ctl.entity.CtlCompBusi;
import com.sgai.property.ctl.entity.CtlCompMenu;
import com.sgai.property.ctl.entity.CtlUserComp;
import com.sgai.property.ctl.service.CtlCompBusiService;
import com.sgai.modules.login.jwt.util.CommonResponse;
import com.sgai.modules.login.jwt.util.ResponseUtil;

/**
 * 机构子系统关系Controller
 * @author admin
 * @version 2018-03-28
 */
@RestController
@RequestMapping(value = "${adminPath}/ctl/ctlCompBusi")
public class CtlCompBusiController extends BaseController {

	@Autowired
	private CtlCompBusiService ctlCompBusiService;
	
	
	/**
	 * 为机构提供未被选择的子系统
	 * findBusiList:(这里用一句话描述这个方法的作用).
	 * @param remarks
	 * @param request
	 * @param response
	 * @param token
	 * @return
	 * @throws JsonProcessingException :CommonResponse 
	 * @since JDK 1.8
	 * @author admin
	 */
	@RequestMapping(value = "/findBusiList",method=RequestMethod.POST)
	public CommonResponse findBusiList(
			String comCode,
			HttpServletRequest request, 
			HttpServletResponse response,
			@RequestHeader("token") String token
			) throws JsonProcessingException {
		CtlBusinessDefine ctlBusinessDefine = new CtlBusinessDefine();
		ctlBusinessDefine.setRemarks(comCode);
		Page<CtlBusinessDefine> page = ctlCompBusiService.findBusiPageList(new Page<CtlBusinessDefine>(request, response), ctlBusinessDefine);
		return ResponseUtil.successResponse(page);
	}
	
	
	/**
	 * 保存机构和子系统的关联关系
	 * saveBusi:(这里用一句话描述这个方法的作用).
	 * @param comCode
	 * @param busiCode
	 * @param previousBusiCode
	 * @param token
	 * @return
	 * @throws IOException :CommonResponse
	 * @since JDK 1.8
	 * @author admin
	 */
	@RequestMapping(value = "/saveBusi",method=RequestMethod.POST)
	public CommonResponse saveBusi(
			String comCode,String busiCode,String previousBusiCode,
			@RequestHeader("token") String token) throws IOException {
		Map<String,String> map=new HashMap<String,String>();
		try {
			map = ctlCompBusiService.saveCompBusi(comCode, busiCode, previousBusiCode);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", "faild");
		}
		return ResponseUtil.successResponse(map);
	}

}