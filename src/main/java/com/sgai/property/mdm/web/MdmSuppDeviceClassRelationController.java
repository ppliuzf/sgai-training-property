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
import com.sgai.property.mdm.entity.MdmSuppDeviceClassRelation;
import com.sgai.property.mdm.service.MdmSuppDeviceClassRelationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 供应商设备型号关联表Controller
 * 
 * @author liushang
 * @version 2017-11-27
 */
@RestController
@RequestMapping(value = "${adminPath}/mdmSuppDeviceClassRelation")
@Api(description = "供应商设备类型接口")
public class MdmSuppDeviceClassRelationController extends BaseController {

	@Autowired
	private MdmSuppDeviceClassRelationService mdmSuppDeviceClassRelationService;

	@ModelAttribute
	public MdmSuppDeviceClassRelation get(@RequestParam(required = false) String id) {
		MdmSuppDeviceClassRelation entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = mdmSuppDeviceClassRelationService.get(id);
		}
		if (entity == null) {
			entity = new MdmSuppDeviceClassRelation();
		}
		return entity;
	}

	// @RequiresPermissions("mdmsuppdeviceclassrelation:mdmSuppDeviceClassRelation:view")
	@RequestMapping(value = { "list", "" })
	public String list(@RequestBody MdmSuppDeviceClassRelation mdmSuppDeviceClassRelation, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		if (mdmSuppDeviceClassRelation == null) {
			mdmSuppDeviceClassRelation = new MdmSuppDeviceClassRelation();
		}
		Page<MdmSuppDeviceClassRelation> page = mdmSuppDeviceClassRelationService
				.findPage(new Page<MdmSuppDeviceClassRelation>(request, response), mdmSuppDeviceClassRelation);
		model.addAttribute("page", page);
		return "mdm/mdmsuppdeviceclassrelation/mdmSuppDeviceClassRelationList";
	}

	// @RequiresPermissions("mdmsuppdeviceclassrelation:mdmSuppDeviceClassRelation:view")
	@RequestMapping(value = "form")
	public String form(@RequestBody MdmSuppDeviceClassRelation mdmSuppDeviceClassRelation, Model model) {
		model.addAttribute("mdmSuppDeviceClassRelation", mdmSuppDeviceClassRelation);
		return "mdm/mdmsuppdeviceclassrelation/mdmSuppDeviceClassRelationForm";
	}

	// @RequiresPermissions("mdmsuppdeviceclassrelation:mdmSuppDeviceClassRelation:edit")
	@RequestMapping(value = "save")
	@ApiOperation(value = "保存和更新", notes = "保存和更新")
	public CommonResponse save(MdmSuppDeviceClassRelation mdmSuppDeviceClassRelation,
			 Model model, RedirectAttributes redirectAttributes)
			throws JsonProcessingException {
		Map<String, Object> result = Maps.newHashMap();
		try {
			mdmSuppDeviceClassRelationService.save(mdmSuppDeviceClassRelation);
			result.put("msg", "success");// 成功返回msg值为success的map
		} catch (Exception e) {
			result.put("msg", "fail");// 失败返回msg值为fail的map
		}
		return ResponseUtil.successResponse(result);

	}

	@RequestMapping(value = "addData", method = RequestMethod.POST)
	@ApiOperation(value = "为供应商增加关联", notes = "为供应商增加关联")
	public CommonResponse addData(MdmSuppDeviceClassRelation mdmSuppDeviceClassRelation,
			@RequestParam(value = "supplier_no", required = false) String supplier_no,
			@RequestParam(value = "Device_type_code", required = false) String Device_type_code,
			 Model model, HttpServletRequest request,
			RedirectAttributes redirectAttributes) throws JsonProcessingException {
		Map<String, Object> result = Maps.newHashMap();
		try {
			if (Device_type_code != "" && supplier_no != "") {
				mdmSuppDeviceClassRelation.setModelCode(Device_type_code);
				mdmSuppDeviceClassRelation.setSupplierNo(supplier_no);
				mdmSuppDeviceClassRelationService.save(mdmSuppDeviceClassRelation);
				result.put("msg", "success");// 成功返回msg值为success的map
			}

		} catch (Exception e) {
			result.put("msg", "fail");// 失败返回msg值为fail的map
		}
		return ResponseUtil.successResponse(result);

	}

	// @RequiresPermissions("suppDeviceclassrelation:mdmSuppDeviceClassRelation:edit")
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ApiOperation(value = "批量删除", notes = "批量删除")
	public CommonResponse delete(String ids,
			RedirectAttributes redirectAttributes) throws JsonProcessingException {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map = mdmSuppDeviceClassRelationService.delete(ids);
		} catch (Exception e) {

			e.printStackTrace();
			map.put("msg", "error");
		}
		return ResponseUtil.successResponse(map);
	}

	@RequestMapping(value = "getListSupplierDeviceRelation", method = RequestMethod.POST)
	@ApiOperation(value = "分页查询已关联类别", notes = "分页查询已关联类别")
	public CommonResponse getListSupplierDeviceRelation(MdmSuppDeviceClassRelation mdmSuppDeviceClassRelation,
			@RequestParam(value = "supplier_no", required = false) String supplier_no,
			@RequestParam(value = "Device_type_code", required = false) String Device_type_code,
			@RequestParam(value = "pageNo", required = true, defaultValue = "0") Integer pageNo,
			@RequestParam(value = "pageSize", required = true, defaultValue = "10") Integer pageSize,
			 HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		if (StringUtils.isNotBlank(supplier_no))
			mdmSuppDeviceClassRelation.setSupplierNo(supplier_no);
		if (StringUtils.isNotBlank(Device_type_code)) // 根据ajax传入的参数代码为entity设置对应查询属性
			mdmSuppDeviceClassRelation.setModelCode(Device_type_code);
		Page<MdmSuppDeviceClassRelation> page = mdmSuppDeviceClassRelationService
				.findPage(new Page<MdmSuppDeviceClassRelation>(request, response), mdmSuppDeviceClassRelation); // 使用entity入参findpage
		return ResponseUtil.successResponse(page);
	}

	@RequestMapping(value = "getUnselectedDeviceType", method = RequestMethod.POST) // 获取关系表中未和供应商有关联的物料分类信息
	@ApiOperation(value = "分页查询未关联类别", notes = "分页查询未关联类别")
	public CommonResponse getDeviceClass(MdmSuppDeviceClassRelation mdmSuppDeviceClassRelation,
			 HttpServletRequest request,
			@RequestParam(value = "supplier_no", required = false) String supplier_no, HttpServletResponse response)
			throws IOException {
		mdmSuppDeviceClassRelation.setSupplierNo(supplier_no);
		Page<MdmSuppDeviceClassRelation> page = mdmSuppDeviceClassRelationService
				.findRestPage(new Page<MdmSuppDeviceClassRelation>(request, response), mdmSuppDeviceClassRelation); // 使用entity入参findpage
		return ResponseUtil.successResponse(page);

	}

}