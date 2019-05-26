package com.sgai.property.mdm.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.collect.Maps;
import com.sgai.common.persistence.Page;
import com.sgai.common.utils.StringUtils;
import com.sgai.common.web.BaseController;
import com.sgai.modules.login.jwt.util.CommonResponse;
import com.sgai.modules.login.jwt.util.ResponseUtil;
import com.sgai.property.mdm.entity.MdmSuppMatClassRelation;
import com.sgai.property.mdm.service.MdmSuppMatClassRelationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 供应商&mdash;物料分类关系表Controller
 * 
 * @author liushang
 * @version 2017-11-24
 */
@RestController
@RequestMapping(value = "${adminPath}/mdmSuppMatClassRelation")
@Api(description = "供应商物料关系接口")
public class MdmSuppMatClassRelationController extends BaseController {

	@Autowired
	private MdmSuppMatClassRelationService mdmSuppMatClassRelationService;

	@ModelAttribute
	public MdmSuppMatClassRelation get(@RequestParam(required = false) String id) {
		MdmSuppMatClassRelation entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = mdmSuppMatClassRelationService.get(id);
		}
		if (entity == null) {
			entity = new MdmSuppMatClassRelation();
		}
		return entity;
	}

	// @RequiresPermissions("suppmatclassrelation:mdmSuppMatClassRelation:view")
	@RequestMapping(value = { "list", "" })
	public String list(@RequestBody MdmSuppMatClassRelation mdmSuppMatClassRelation, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		if (mdmSuppMatClassRelation == null) {
			mdmSuppMatClassRelation = new MdmSuppMatClassRelation();
		}
		Page<MdmSuppMatClassRelation> page = mdmSuppMatClassRelationService
				.findPage(new Page<MdmSuppMatClassRelation>(request, response), mdmSuppMatClassRelation);
		model.addAttribute("page", page);
		return "mdm/suppmatclassrelation/mdmSuppMatClassRelationList";
	}

	// @RequiresPermissions("suppmatclassrelation:mdmSuppMatClassRelation:view")
	@RequestMapping(value = "form")
	public String form(MdmSuppMatClassRelation mdmSuppMatClassRelation, Model model) {
		model.addAttribute("mdmSuppMatClassRelation", mdmSuppMatClassRelation);
		return "mdm/suppmatclassrelation/mdmSuppMatClassRelationForm";
	}

	// @RequiresPermissions("suppmatclassrelation:mdmSuppMatClassRelation:edit")
	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ApiOperation(value = "保存", notes = "保存")
	public CommonResponse save(MdmSuppMatClassRelation mdmSuppMatClassRelation,
			Model model, RedirectAttributes redirectAttributes) throws JsonProcessingException {
		Map<String, Object> result = Maps.newHashMap();
		try {
			mdmSuppMatClassRelationService.save(mdmSuppMatClassRelation);
			result.put("msg", "success");// 成功返回msg值为success的map
		} catch (Exception e) {
			result.put("msg", "fail");// 失败返回msg值为fail的map
		}
		return ResponseUtil.successResponse(result);

	}

	@RequestMapping(value = "addData", method = RequestMethod.POST)
	@ApiOperation(value = "关联新物料类型", notes = "关联新物料类型")
	public CommonResponse addData(MdmSuppMatClassRelation mdmSuppMatClassRelation,
			@RequestParam(value = "supplier_no", required = false) String supplier_no,
			@RequestParam(value = "mat_type_code", required = false) String mat_type_code,
			@RequestParam(value = "mat_type_name", required = false) String mat_type_name,
			 Model model, HttpServletRequest request,
			RedirectAttributes redirectAttributes) throws JsonProcessingException {
		Map<String, Object> result = Maps.newHashMap();
		try {
			if (mat_type_code != "" && mat_type_name != "" && supplier_no != "") {
				mdmSuppMatClassRelation.setMatTypeCode(mat_type_code);
				mdmSuppMatClassRelation.setMatTypeName(mat_type_name);
				mdmSuppMatClassRelation.setSupplierNo(supplier_no);
				mdmSuppMatClassRelation.setEnabledFlag("Y");
				mdmSuppMatClassRelationService.save(mdmSuppMatClassRelation);
				result.put("msg", "success");// 成功返回msg值为success的map
			}

		} catch (Exception e) {
			result.put("msg", "fail");// 失败返回msg值为fail的map
		}
		return ResponseUtil.successResponse(result);

	}

	// @RequiresPermissions("suppmatclassrelation:mdmSuppMatClassRelation:edit")
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ApiOperation(value = "批量删除", notes = "批量删除")
	public CommonResponse delete(@RequestParam(value = "ids", required = true) String ids,
			 RedirectAttributes redirectAttributes)
			throws JsonProcessingException {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map = mdmSuppMatClassRelationService.delete(ids);
		} catch (Exception e) {

			e.printStackTrace();
			map.put("msg", "error");
		}
		return ResponseUtil.successResponse(map);
	}

	@RequestMapping(value = "/getListSupplierMatRelation", method = RequestMethod.POST)
	@ApiOperation(value = "分页查询", notes = "分页查询")
	public CommonResponse getListSupplierMatRelation(MdmSuppMatClassRelation mdmSuppMatClassRelation,
			@RequestParam(value = "supplier_no", required = false) String supplier_no,
			@RequestParam(value = "mat_type_code", required = false) String mat_type_code,
			@RequestParam(value = "pageNo", required = true, defaultValue = "0") Integer pageNo,
			@RequestParam(value = "pageSize", required = true, defaultValue = "10") Integer pageSize,
			 HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		if (StringUtils.isNotBlank(supplier_no)) 
			mdmSuppMatClassRelation.setSupplierNo(supplier_no);
		if (StringUtils.isNotBlank(mat_type_code)) 
			mdmSuppMatClassRelation.setMatTypeCode(mat_type_code);
		Page<MdmSuppMatClassRelation> page = mdmSuppMatClassRelationService
				.findPage(new Page<MdmSuppMatClassRelation>(request, response), mdmSuppMatClassRelation); // 使用entity入参findpage
		return ResponseUtil.successResponse(page);
	}

	@RequestMapping(value = "getUnselectedMatType", method = RequestMethod.POST) // 获取关系表中未和供应商有关联的物料分类信息
	@ApiOperation(value = "获取未关联物料类型", notes = "获取未关联物料类型")
	public CommonResponse getMatClass(MdmSuppMatClassRelation mdmSuppMatClassRelation,
			@RequestParam(value = "supplier_no", required = false) String supplier_no,
			 HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		mdmSuppMatClassRelation.setSupplierNo(supplier_no);
		Page<MdmSuppMatClassRelation> page = mdmSuppMatClassRelationService
				.findRestPage(new Page<MdmSuppMatClassRelation>(request, response), mdmSuppMatClassRelation); // 使用entity入参findpage
		return ResponseUtil.successResponse(page);

	}

}