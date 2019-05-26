package com.sgai.property.mdm.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sgai.common.persistence.Page;
import com.sgai.common.utils.StringUtils;
import com.sgai.common.web.BaseController;
import com.sgai.property.ctl.service.CtlComRuleService;
import com.sgai.property.ctl.service.DeleteRulesUtils;
import com.sgai.modules.login.jwt.util.CommonResponse;
import com.sgai.modules.login.jwt.util.ResponseUtil;
import com.sgai.property.mdm.entity.MdmMatClass;
import com.sgai.property.mdm.service.MdmMatClassService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 物料分类表Controller
 * @author liushang
 * @version 2017-11-24
 */
@RestController
@RequestMapping(value = "${adminPath}/mdmMatClass")
@Api(description = "物料分类接口")
public class MdmMatClassController extends BaseController {

	@Autowired
	private MdmMatClassService mdmMatClassService;
	@Autowired
	private CtlComRuleService ctlComRuleService;
	
	@Autowired
	private DeleteRulesUtils deleteRulesUtils;
	
	@ModelAttribute
	public MdmMatClass get(@RequestParam(required=false) String id) {
		MdmMatClass entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = mdmMatClassService.get(id);
		}
		if (entity == null){
			entity = new MdmMatClass();
		}

		return entity;
	}
	
	//@RequiresPermissions("matclass:mdmMatClass:view")
	@RequestMapping(value = {"list", ""})
	public String list(MdmMatClass mdmMatClass, HttpServletRequest request, HttpServletResponse response, Model model) {
		if(mdmMatClass==null){
			mdmMatClass=new MdmMatClass();
		}
		Page<MdmMatClass> page = mdmMatClassService.findPage(new Page<MdmMatClass>(request, response), mdmMatClass); 
		model.addAttribute("page", page);
		return "mdm/matclass/mdmMatClassList";
	}

	//@RequiresPermissions("matclass:mdmMatClass:view")
	@RequestMapping(value = "form")
	public String form(MdmMatClass mdmMatClass, Model model) {
		model.addAttribute("mdmMatClass", mdmMatClass);
		return "mdm/matclass/mdmMatClassForm";
	}

	@ApiOperation(value = "保存信息", httpMethod = "POST", notes = "保存信息")
	@RequestMapping(value = "save", method=RequestMethod.POST)
	public CommonResponse save(
			MdmMatClass mdmMatClass,
			@RequestHeader("token") String token
			) throws JsonProcessingException {
		Map<String,String> data = new HashMap<String,String>();
		try {
			mdmMatClass.setEnabledFlag("Y");
			if (StringUtils.isBlank(mdmMatClass.getId())) {
				//如果是新增的数据，校验是否和库中数据重复
				String code = ctlComRuleService.getNext("MAT-CLASS");
				mdmMatClass.setMatTypeCode(code);
				List<MdmMatClass> resultlist = mdmMatClassService.findRepeatList(mdmMatClass);
				if (resultlist.size()==0) {
					mdmMatClassService.save(mdmMatClass);
					data.put("msg", "success");
				} else {
					data.put("msg", "havaData");
				}
			} else {
				//如果是更新数据，直接执行save方法
				mdmMatClassService.save(mdmMatClass);
				data.put("msg", "success");
			}
		} catch (Exception e) {

			e.printStackTrace();
			data.put("msg", "fail");
		}
		return ResponseUtil.successResponse(data);
	}
	
	//@RequiresPermissions("matclass:mdmMatClass:edit")
	@RequestMapping(value = "delete")
	public Map<String,String> delete(String ids, RedirectAttributes redirectAttributes) {
		String[] idArr=ids.split(",");
		List<String> idList = new ArrayList<String>();
		List<MdmMatClass> cdcList = new ArrayList<MdmMatClass>();
		for(String id:idArr){
			if(id!=null&&!id.equals("")){
				idList.add(id);
				MdmMatClass mdmMatClass = mdmMatClassService.get(id);
				cdcList.add(mdmMatClass);
			}
		}
		// 检查是否满足删除条件
		Map<String, String> data = deleteRulesUtils.checkBeforeDelete(MdmMatClass.class, idList);
		if ("true".equals(data.get("value"))) {
			List<MdmMatClass> finalList = mdmMatClassService.batchDelete(cdcList);
			if (finalList.size() > 0) {
				data.put("result", "删除成功");
			} else {
				data.put("result", "删除失败");
			}
		}
		return data;
	}

	@ApiOperation(value = "获取物料分类列表", notes = "获取物料分类列表")
	@RequestMapping(value = "/getListMdmMatClass",method=RequestMethod.POST)
	public CommonResponse getListMdmMatClass(
			@RequestParam(value = "matTypeCode",required=false) String matTypeCode,
			@RequestParam(value = "matTypeName",required=false) String matTypeName,
			@RequestParam(value = "pageNo", required = false, defaultValue = "0") Integer pageNo,
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
			@RequestHeader("token") String token) throws IOException {
		MdmMatClass mdmMatClass = new MdmMatClass();
		mdmMatClass.setMatTypeCode(matTypeCode);
		mdmMatClass.setMatTypeName(matTypeName);
		//TokenUtil.parseToken(token);
		Page<MdmMatClass> page = mdmMatClassService.findPage(new Page<MdmMatClass>(pageNo,pageSize), mdmMatClass);
		return ResponseUtil.successResponse(page);
	}
	
	@ApiOperation(value = "根据id获取物料分类实体", notes = "根据id获取物料分类实体")
	@RequestMapping(value = "/findByIdMdmMatClass",method=RequestMethod.POST)
	public CommonResponse findByIdMdmMatClass(
			@RequestParam(value = "id",required=false) String id,
			@RequestHeader("token") String token) throws IOException {
		MdmMatClass mdmMatClass = mdmMatClassService.get(id);
		//TokenUtil.parseToken(token);
		return ResponseUtil.successResponse(mdmMatClass);

	}
}