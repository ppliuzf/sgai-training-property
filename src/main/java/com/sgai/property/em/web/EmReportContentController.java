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
import com.sgai.property.em.entity.EmReportContent;
import com.sgai.property.em.service.EmReportContentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * 
    * @ClassName: EmReportContentController  
    * @com.sgai.property.commonService.vo;(报案内容Controller)
    * @author LiuYang  
    * @date 2017年12月5日  
    * @Company 首自信--智慧城市创新中心
 */
@RestController
@RequestMapping(value = "${adminPath}/em/emreportcontent/emReportContent")
@Api(description = "报案内容接口")
public class EmReportContentController extends BaseController {

	@Autowired
	private EmReportContentService emReportContentService;
	
	@ApiOperation(value = "报案内容列表", notes = "报案内容列表")
	@RequestMapping(value = {"list", ""},method=RequestMethod.GET)
	public String list(EmReportContent emReportContent, 
			@RequestHeader(value="token")String token,
			@RequestParam(value="pageNo",required = true ,defaultValue = "0" )Integer pageNo,
			@RequestParam(value="pageSize",required = true ,defaultValue = "10" )Integer pageSize,
			HttpServletRequest request, HttpServletResponse response, Model model) {
		if(emReportContent==null){
			emReportContent=new EmReportContent();
		}
		Page<EmReportContent> page = emReportContentService.findPage(new Page<EmReportContent>(pageNo, pageSize), emReportContent); 
		model.addAttribute("page", page);
		return "em/emReportContentList";
	}

	@ApiOperation(value = "报案内容form页面", notes = "报案内容form页面")
	@RequestMapping(value = "form",method=RequestMethod.GET)
	public String form(EmReportContent emReportContent,@RequestHeader(value="token")String token, Model model) {
		model.addAttribute("emReportContent", emReportContent);
		return "em/emReportContentForm";
	}
	
	@ApiOperation(value = "报案内容保存", notes = "报案内容保存")
	@RequestMapping(value = "save",method=RequestMethod.POST)
	public Map<String,String> save(EmReportContent emReportContent,@RequestHeader(value="token")String token, Model model, RedirectAttributes redirectAttributes) {
		Map<String,String> data = new HashMap<String,String>();
		try {
			emReportContentService.save(emReportContent);
			data.put("message", "success");
		} catch (Exception e) {
			data.put("message", "failed");
			e.printStackTrace();
		}
		return data;
	}
	
	@ApiOperation(value = "报案内容分页", notes = "报案内容分页")
	@RequestMapping(value = "/getList",method=RequestMethod.POST)
	public Page<EmReportContent> getList(EmReportContent emReportContent,
			@RequestHeader(value="token")String token,
			@RequestParam(value="pageNo",required = true ,defaultValue = "0" )Integer pageNo,
			@RequestParam(value="pageSize",required = true ,defaultValue = "10" )Integer pageSize,
			HttpServletRequest request, HttpServletResponse response) {
		Page<EmReportContent> page = emReportContentService.findPage(new Page<EmReportContent>(pageNo, pageSize), emReportContent);
		return page;
	}

}