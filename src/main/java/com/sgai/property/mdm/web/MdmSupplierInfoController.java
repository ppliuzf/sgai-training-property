package com.sgai.property.mdm.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.collect.Maps;
import com.sgai.common.persistence.Page;
import com.sgai.common.utils.StringUtils;
import com.sgai.common.web.BaseController;
import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.modules.login.jwt.util.CommonResponse;
import com.sgai.modules.login.jwt.util.ResponseUtil;
import com.sgai.property.ctl.service.CtlComRuleService;
import com.sgai.property.mdm.entity.MdmSuppDeviceClassRelation;
import com.sgai.property.mdm.entity.MdmSupplierInfo;
import com.sgai.property.mdm.service.MdmSuppDeviceClassRelationService;
import com.sgai.property.mdm.service.MdmSupplierInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;




/**
 * 供应商数据Controller
 * @author liushang
 * @version 2017-11-24
 */
@RestController
@RequestMapping(value = "${adminPath}/mdmSupplierInfo")
@Api(description = "供应商数据接口")
public class MdmSupplierInfoController extends BaseController {

	@Autowired
	private MdmSupplierInfoService mdmSupplierInfoService;
	@Autowired
	private MdmSuppDeviceClassRelationService mdmSuppDeviceClassRelationService;
	@Autowired
	private CtlComRuleService ctlComRuleService;
	
	@ModelAttribute
	public MdmSupplierInfo get(@RequestParam(required=false) String id) {
		MdmSupplierInfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = mdmSupplierInfoService.get(id);
		}
		if (entity == null){
			entity = new MdmSupplierInfo();
		}
		return entity;
	}
	
	//@RequiresPermissions("supplierinfo:mdmSupplierInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(@RequestBody MdmSupplierInfo mdmSupplierInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		if(mdmSupplierInfo==null){
			mdmSupplierInfo=new MdmSupplierInfo();
		}
		Page<MdmSupplierInfo> page = mdmSupplierInfoService.findPage(new Page<MdmSupplierInfo>(request, response), mdmSupplierInfo); 
		model.addAttribute("page", page);
		return "mdm/supplierinfo/mdmSupplierInfoList";
	}

	//@RequiresPermissions("supplierinfo:mdmSupplierInfo:view")
	@RequestMapping(value = "form")
	public String form(@RequestBody MdmSupplierInfo mdmSupplierInfo, Model model) {
		model.addAttribute("mdmSupplierInfo", mdmSupplierInfo);
		return "mdm/supplierinfo/mdmSupplierInfoForm";
	}

	@RequestMapping(value = "save",method=RequestMethod.POST)
	@ApiOperation(value = "供应商信息保存", notes = "供应商信息保存")//ajax自定义请求参数的保存（新增）方法
	public CommonResponse ParamSave(MdmSupplierInfo mdmSupplierInfo,HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String,Object> result = Maps.newHashMap();
	//	try {
		if (mdmSupplierInfo.getId() == null || "".equals(mdmSupplierInfo.getId())) {
			String code = ctlComRuleService.getNext("SUPPLIER-INFO");
			mdmSupplierInfo.setSupplierNo(code);
		}
			mdmSupplierInfoService.save(mdmSupplierInfo);
		result.put("msg", "success");//成功返回msg值为success的map
		//}
		//catch(Exception e) {
		//	result.put("msg", "fail");//失败返回msg值为fail的map
	//	}
		return ResponseUtil.successResponse(result);
		
	}
	
	//@RequiresPermissions("supplierinfo:mdmSupplierInfo:edit")
	@RequestMapping(value = "delete",method=RequestMethod.POST)
	@ApiOperation(value = "供应商信息删除", notes = "供应商信息删除")
	public CommonResponse delete(String ids, RedirectAttributes redirectAttributes) throws JsonProcessingException {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map = mdmSupplierInfoService.delete(ids);
		} catch (Exception e) {

			e.printStackTrace();
			map.put("msg", "error");
		}
		return ResponseUtil.successResponse(map);
	}
	
	@RequestMapping(value = "/getListSupplierInfo",method=RequestMethod.POST)
	@ApiOperation(value = "获取供应商信息列表", notes = "获取供应商信息列表")
	@ApiImplicitParams({
            @ApiImplicitParam(name = "com_type", value = "公司类别", required = false,paramType = "query", dataType = "String"),
	        @ApiImplicitParam(name = "supplier_name", value = "供应商名称", required = false,paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "id", value = "id", required = false,paramType = "query", dataType = "String"),
    })
	public CommonResponse getListSupplierInfo(
			 LoginUser user,
			 String com_type, String supplier_name, String id,
			 @RequestParam(value = "pageNo", required = true,defaultValue="0") Integer pageNo ,
			 @RequestParam(value = "pageSize", required = true,defaultValue="10") Integer pageSize) throws IOException {
		MdmSupplierInfo mdmSupplierInfo = new MdmSupplierInfo();
		if (id !=null && !"".equals(id)) {
			mdmSupplierInfo.setId(id);
		}
		if (com_type != null && !"".equals(com_type)) {
			mdmSupplierInfo.setComType(com_type);
		}
		if (supplier_name != null && !"".equals(supplier_name)) {
			mdmSupplierInfo.setSupplierName(supplier_name);
		}
		Page<MdmSupplierInfo> page = mdmSupplierInfoService.findPage(new Page<MdmSupplierInfo>(pageNo, pageSize), mdmSupplierInfo); //使用entity入参findpage
		return ResponseUtil.successResponse(page);
	}
	
	@ApiOperation(value = "获取供应商信息", httpMethod = "POST", notes = "获取供应商信息")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "主键", required = false,paramType = "query", dataType = "String")
	})
	@RequestMapping(value = "/getSupplierInfo", method=RequestMethod.POST)
	public CommonResponse getSupplierInfo(
			String id, 

			HttpServletRequest request, 
			HttpServletResponse response
			) throws IOException {
		MdmSupplierInfo supplierInfo = mdmSupplierInfoService.get(id);
		return ResponseUtil.successResponse(supplierInfo);
	}
	
	/**
	 * 
	 * getListSupplierByModel:(根据设备类型代码查询供应商列表).
	 * @param mdmSupplierInfo
	 * @param modelCode
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException :Page<MdmSupplerInfo> 
	 * @since JDK 1.8
	 * @author yangyz
	 */
	@RequestMapping(value = "/getListSupplierByModel",method=RequestMethod.POST)
	@ApiOperation(value = "根据设备类型代码获取供应商信息列表", notes = "根据设备类型代码获取供应商信息列表") 
	public CommonResponse getListSupplierByModel(MdmSupplierInfo mdmSupplierInfo, 
			 @RequestParam(value = "pageNo", required = true,defaultValue="0") Integer pageNo ,
			 @RequestParam(value = "pageSize", required = true,defaultValue="10") Integer pageSize,

			 String modelCode, HttpServletRequest request, HttpServletResponse response) throws IOException {
		MdmSuppDeviceClassRelation mdmSuppDeviceClassRelation = new MdmSuppDeviceClassRelation();
		mdmSuppDeviceClassRelation.setModelCode(modelCode);
		Page<MdmSuppDeviceClassRelation> pages = mdmSuppDeviceClassRelationService.findPageByModelCode(new Page<MdmSuppDeviceClassRelation>(request, response), mdmSuppDeviceClassRelation);
		List<MdmSupplierInfo> list = new ArrayList<MdmSupplierInfo>();
		for(MdmSuppDeviceClassRelation info : pages.getList()) {
			mdmSupplierInfo.setSupplierNo(info.getSupplierNo());
			MdmSupplierInfo supplierInfo = mdmSupplierInfoService.getSuppByCode(mdmSupplierInfo);
			list.add(supplierInfo);
		}
		Page<MdmSupplierInfo> page = new Page<MdmSupplierInfo>();
		page.setCount(pages.getCount());
		page.setList(list);
		page.setPageNo(pages.getPageNo());
		page.setPageSize(pages.getPageSize());
		return ResponseUtil.successResponse(page);
	}

}