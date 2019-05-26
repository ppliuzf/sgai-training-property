package com.sgai.property.em.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sgai.common.persistence.Page;
import com.sgai.common.web.BaseController;
import com.sgai.property.em.entity.EmInfoRelease;
import com.sgai.property.em.service.EmInfoReleaseService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * 
    * @ClassName: EmInfoReleaseController  
    * @com.sgai.property.commonService.vo;(信息发布Controller)
    * @author LiuYang  
    * @date 2017年12月5日  
    * @Company 首自信--智慧城市创新中心
 */
@RestController
@RequestMapping(value = "${adminPath}/em/eminforelease/emInfoRelease")
@Api(description = "信息发布接口")
public class EmInfoReleaseController extends BaseController {

	@Autowired
	private EmInfoReleaseService emInfoReleaseService;
	
	@ApiOperation(value = "信息发布列表", notes = "信息发布列表")
	@RequestMapping(value = {"list", ""},method=RequestMethod.GET)
	public String list(EmInfoRelease emInfoRelease, 
			@RequestHeader(value="token")String token,
			@RequestParam(value="pageNo",required = true ,defaultValue = "0" )Integer pageNo,
			@RequestParam(value="pageSize",required = true ,defaultValue = "10" )Integer pageSize,
			HttpServletRequest request, HttpServletResponse response, Model model) {
		if(emInfoRelease==null){
			emInfoRelease=new EmInfoRelease();
		}
		Page<EmInfoRelease> page = emInfoReleaseService.findPage(new Page<EmInfoRelease>(pageNo, pageSize), emInfoRelease); 
		model.addAttribute("page", page);
		return "em/emInfoReleaseList";
	}

	@ApiOperation(value = "信息发布form页面", notes = "信息发布form页面")
	@RequestMapping(value = "form",method=RequestMethod.GET)
	public String form(EmInfoRelease emInfoRelease, @RequestHeader(value="token")String token,Model model) {
		model.addAttribute("emInfoRelease", emInfoRelease);
		return "em/emInfoReleaseForm";
	}
	
	@ApiOperation(value = "保存信息发布", notes = "保存信息发布")
	@RequestMapping(value = "save",method=RequestMethod.POST)
	public Map<String,String> save(EmInfoRelease emInfoRelease, @RequestHeader(value="token")String token,Model model, RedirectAttributes redirectAttributes) {
		Map<String,String> data = new HashMap<String,String>();
		try {
			emInfoReleaseService.save(emInfoRelease);
			data.put("message", "success");
		} catch (Exception e) {
			data.put("message", "failed");
			e.printStackTrace();
		}
		return data;
	}
	
	@ApiOperation(value = "信息发布分页", notes = "信息发布分页")
	@RequestMapping(value = "/getList",method=RequestMethod.POST)
	public Page<EmInfoRelease> getList(EmInfoRelease emInfoRelease,
			@RequestHeader(value="token")String token,
			@RequestParam(value="pageNo",required = true ,defaultValue = "0" )Integer pageNo,
			@RequestParam(value="pageSize",required = true ,defaultValue = "10" )Integer pageSize,
			HttpServletRequest request, HttpServletResponse response) {
		Page<EmInfoRelease> page = emInfoReleaseService.findPage(new Page<EmInfoRelease>(pageNo, pageSize), emInfoRelease);
		return page;
	}

}