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

import com.sgai.common.persistence.Page;
import com.sgai.common.utils.StringUtils;
import com.sgai.common.web.BaseController;
import com.sgai.property.ctl.service.CtlComRuleService;
import com.sgai.property.ctl.service.DeleteRulesUtils;
import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.modules.login.jwt.util.CommonResponse;
import com.sgai.modules.login.jwt.util.ResponseUtil;
import com.sgai.property.mdm.entity.MdmMatClass;
import com.sgai.property.mdm.entity.MdmMatInfo;
import com.sgai.property.mdm.service.MdmMatInfoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * 物料信息表Controller
 * @author liushang
 * @version 2017-11-24
 */
@RestController
@RequestMapping(value = "${adminPath}/mdmMatInfo")
@Api(description = "物料主数据接口")
public class MdmMatInfoController extends BaseController {

	@Autowired
	private MdmMatInfoService mdmMatInfoService;
	
	@Autowired
	private DeleteRulesUtils deleteRulesUtils;
	@Autowired
	private CtlComRuleService ctlComRuleService;
	
	@ModelAttribute
	public MdmMatInfo get(@RequestParam(required=false) String id) {
		MdmMatInfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = mdmMatInfoService.get(id);
		}
		if (entity == null){
			entity = new MdmMatInfo();
		}
		return entity;
	}
	
	//@RequiresPermissions("matinfo:mdmMatInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(MdmMatInfo mdmMatInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		if(mdmMatInfo==null){
			mdmMatInfo=new MdmMatInfo();
		}
		Page<MdmMatInfo> page = mdmMatInfoService.findPage(new Page<MdmMatInfo>(request, response), mdmMatInfo); 
		model.addAttribute("page", page);
		return "mdm/matinfo/mdmMatInfoList";
	}

	//@RequiresPermissions("matinfo:mdmMatInfo:view")
	@RequestMapping(value = "form")
	public String form(MdmMatInfo mdmMatInfo, Model model) {
		model.addAttribute("mdmMatInfo", mdmMatInfo);
		return "mdm/matinfo/mdmMatInfoForm";
	}

	//@RequiresPermissions("matinfo:mdmMatInfo:edit")
	@RequestMapping(value = "save")
	public Map<String,String> save(MdmMatInfo mdmMatInfo) {
		Map<String,String> data = new HashMap<String,String>();
		mdmMatInfo.setEnabledFlag("Y");
		if (StringUtils.isBlank(mdmMatInfo.getId())) {
			//如果是新增的数据，校验是否和库中数据重复
			String code = ctlComRuleService.getNext("MAT-INFO");
			mdmMatInfo.setMatCode(code);
			List<MdmMatInfo> resultlist = mdmMatInfoService.findRepeatList(mdmMatInfo);
			try {
				if (resultlist.size()==0) {
					mdmMatInfoService.save(mdmMatInfo);
					data.put("message", "保存成功");
				} else {
					data.put("message", "数据重复");
				}
			} catch (Exception e) {
				data.put("message", "保存失败");
				e.printStackTrace();
			}
		} else {
			//如果是更新数据，直接执行save方法
			//System.out.println(mdmMatInfo.toString());
			try {
				mdmMatInfoService.save(mdmMatInfo);
				data.put("message", "保存成功");
			} catch (Exception e) {
				data.put("message", "保存失败");
				e.printStackTrace();
			}
		}
		return data;
	}
	
	//@RequiresPermissions("matinfo:mdmMatInfo:edit")
	@RequestMapping(value = "delete")
	public Map<String,String> delete(String ids, RedirectAttributes redirectAttributes) {
		String[] idArr=ids.split(",");
		List<String> idList = new ArrayList<String>();
		List<MdmMatInfo> cdcList = new ArrayList<MdmMatInfo>();
		for(String id:idArr){
			if(id!=null&&!id.equals("")){
				idList.add(id);
				MdmMatInfo mdmMatInfo = mdmMatInfoService.get(id);
				cdcList.add(mdmMatInfo);
			}
		}
		// 检查是否满足删除条件
		Map<String, String> data = deleteRulesUtils.checkBeforeDelete(MdmMatInfo.class, idList);
		if ("true".equals(data.get("value"))) {
			List<MdmMatInfo> finalList = mdmMatInfoService.batchDelete(cdcList);
			if (finalList.size() > 0) {
				data.put("result", "删除成功");
			} else {
				data.put("result", "删除失败");
			}
		}
		return data;
	}

	@ApiOperation(value = "获取物料主数据列表", notes = "获取物料主数据列表")
	@RequestMapping(value = "/getListMdmMatInfo",method=RequestMethod.POST)
	public CommonResponse getListMdmMatInfo(
			@RequestParam(value = "matTypeCode",required=false) String matTypeCode,
			@RequestParam(value = "matName",required=false) String matName,
			@RequestParam(value = "pageNo", required = false, defaultValue = "0") Integer pageNo,
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
			@RequestHeader("token") String token) throws IOException {
		MdmMatInfo mdmMatInfo = new MdmMatInfo();
		mdmMatInfo.setMatTypeCode(matTypeCode);
		mdmMatInfo.setMatName(matName);
		//TokenUtil.parseToken(token);
		Page<MdmMatInfo> page = mdmMatInfoService.findPage(new Page<MdmMatInfo>(pageNo, pageSize), mdmMatInfo);
		return ResponseUtil.successResponse(page);
	}
	
	@RequestMapping(value = "/getListMdmMatInfoByMatName")
	public Page<MdmMatInfo> getListMdmMatInfoByMatName(MdmMatInfo mdmMatInfo,HttpServletRequest request, HttpServletResponse response) {
		Page<MdmMatInfo> page = mdmMatInfoService.getListMdmMatInfoByMatName(new Page<MdmMatInfo>(request, response), mdmMatInfo);
		return page;
	}
	
	@RequestMapping(value = "/getMatTypeList")
	public List<Map<String,String>> getMatTypeList(LoginUser user,HttpServletRequest request, HttpServletResponse response) {
		MdmMatClass mdmMatClass = new MdmMatClass();
		mdmMatClass.setComCode(user.getComCode());
		mdmMatClass.setModuCode(user.getModuCode());
		return mdmMatInfoService.getMatTypeList(mdmMatClass);
	}
	
	@RequestMapping(value = "/getSpecList")
	public List<Map<String,String>> getSpecList(HttpServletRequest request, HttpServletResponse response) {
		return mdmMatInfoService.getSpecList();
	}
	@RequestMapping(value = "/getUnitList")
	public List<Map<String,String>> getUnitList(HttpServletRequest request, HttpServletResponse response) {
		return mdmMatInfoService.getUnitList();
	}
	@RequestMapping(value = "/getMatUseList")
	public List<Map<String,String>> getMatUseList(HttpServletRequest request, HttpServletResponse response) {
		return mdmMatInfoService.getMatUseList();
	}
	
	@ApiOperation(value = "根据id获取物料主数据实体", notes = "根据id获取物料主数据实体")
	@RequestMapping(value = "/findByIdMdmMatInfo",method=RequestMethod.POST)
	public CommonResponse findByIdMdmMatInfo(
			@RequestParam(value = "id",required=false) String id,
			@RequestHeader("token") String token) throws IOException {
		MdmMatInfo mdmMatInfo = mdmMatInfoService.get(id);
		//TokenUtil.parseToken(token);
		return ResponseUtil.successResponse(mdmMatInfo);
	}
}