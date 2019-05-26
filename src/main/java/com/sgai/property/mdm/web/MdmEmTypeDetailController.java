package com.sgai.property.mdm.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import com.google.common.collect.Maps;
import com.sgai.common.persistence.Page;
import com.sgai.common.utils.StringUtils;
import com.sgai.common.web.BaseController;
import com.sgai.modules.login.jwt.util.CommonResponse;
import com.sgai.modules.login.jwt.util.ResponseUtil;
import com.sgai.property.mdm.entity.MdmBrandInfo;
import com.sgai.property.mdm.entity.MdmEmTypeDetail;
import com.sgai.property.mdm.service.MdmEmTypeDetailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 事件详细类别维护Controller
 * @author liushang
 * @version 2017-12-05
 */
@RestController
@RequestMapping(value ="${adminPath}/mdmEmTypeDetail")
@Api(description = "事件详细类别维护接口")
public class MdmEmTypeDetailController extends BaseController {

	@Autowired
	private MdmEmTypeDetailService mdmEmTypeDetailService;
	
	@ModelAttribute
	public MdmEmTypeDetail get(@RequestParam(required=false) String id) {
		MdmEmTypeDetail entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = mdmEmTypeDetailService.get(id);
		}
		if (entity == null){
			entity = new MdmEmTypeDetail();
		}
		return entity;
	}
	
	//@RequiresPermissions("emtypedetail:事件详细类别:mdmEmTypeDetail:view")
	@RequestMapping(value = {"list", ""})
	public String list(MdmEmTypeDetail mdmEmTypeDetail, HttpServletRequest request, HttpServletResponse response, Model model) {
		if(mdmEmTypeDetail==null){
			mdmEmTypeDetail=new MdmEmTypeDetail();
		}
		Page<MdmEmTypeDetail> page = mdmEmTypeDetailService.findPage(new Page<MdmEmTypeDetail>(request, response), mdmEmTypeDetail); 
		model.addAttribute("page", page);
		return "mdm/emtypedetail/事件详细类别/mdmEmTypeDetailList";
	}

	//@RequiresPermissions("emtypedetail:事件详细类别:mdmEmTypeDetail:view")
	@RequestMapping(value = "form")
	public String form(MdmEmTypeDetail mdmEmTypeDetail, Model model) {
		model.addAttribute("mdmEmTypeDetail", mdmEmTypeDetail);
		return "mdm/emtypedetail/事件详细类别/mdmEmTypeDetailForm";
	}

	//@RequiresPermissions("emtypedetail:事件详细类别:mdmEmTypeDetail:edit")
	@RequestMapping(value = "save",method=RequestMethod.POST)
	@ApiOperation(value = "事件详细类别信息保存", notes = "事件详细类别信息保存")
	public CommonResponse save(MdmEmTypeDetail mdmEmTypeDetail,HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String,Object> result = Maps.newHashMap();
	//	try {
		mdmEmTypeDetailService.save(mdmEmTypeDetail);
		result.put("msg", "success");//成功返回msg值为success的map
		//}
		//catch(Exception e) {
		//	result.put("msg", "fail");//失败返回msg值为fail的map
	//	}
		return ResponseUtil.successResponse(result);
		
	}
	
	//@RequiresPermissions("emtypedetail:事件详细类别:mdmEmTypeDetail:edit")
	@RequestMapping(value = "delete",method=RequestMethod.POST)
	@ApiOperation(value = "事件详细类别删除", notes = "事件详细类别删除")
	public CommonResponse delete(String ids, RedirectAttributes redirectAttributes) throws JsonProcessingException {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map = mdmEmTypeDetailService.delete(ids);
		} catch (Exception e) {

			e.printStackTrace();
			map.put("msg", "error");
		}
		return ResponseUtil.successResponse(map);
	}
	
	@RequestMapping(value = "/getListEmType",method=RequestMethod.POST)
	@ApiOperation(value = "获取事件类别列表", notes = "获取事件类别列表") 
	public CommonResponse getListEmType(MdmEmTypeDetail mdmEmTypeDetail,
			 @RequestParam(value = "pageNo", required = true,defaultValue="0") Integer pageNo ,
			 @RequestParam(value = "pageSize", required = true,defaultValue="10") Integer pageSize,

			 HttpServletRequest request, HttpServletResponse response) throws IOException {
		String emType_code = request.getParameter("emType_code");
		//根据ajax传入的参数代码为entity设置对应查询属性
		if(emType_code!=null && !"".equals(emType_code)) {
			mdmEmTypeDetail.setEmTypeCode(emType_code);
		}	
		mdmEmTypeDetail.setEnabledFlag("Y");
		List<MdmEmTypeDetail> page = mdmEmTypeDetailService.getListEmType(new Page<MdmEmTypeDetail>(request, response), mdmEmTypeDetail); //使用entity入参findpage
		return ResponseUtil.successResponse(page);
	}
	
	@ApiOperation(value = "获得事件详细类型信息", httpMethod = "POST", notes = "获得事件详细类型信息")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "主键", required = false,paramType = "query", dataType = "String")
	})
	@RequestMapping(value = "/getEmTypeDetail",method=RequestMethod.POST)
	public CommonResponse getEmTypeDetail(
			String id,

			HttpServletRequest request, 
			HttpServletResponse response
			) throws IOException {
		MdmEmTypeDetail mdmEmTypeDetail = mdmEmTypeDetailService.get(id);
		return ResponseUtil.successResponse(mdmEmTypeDetail);
	}
	
	@RequestMapping(value = "/getListEmTypeDetail",method=RequestMethod.POST)
	@ApiOperation(value = "获取事件详细类别列表", notes = "获取事件详细类别列表")  
		@ApiImplicitParams({
            @ApiImplicitParam(name = "emType_detail_name", value = "事件详细类别名称", required = false,paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "id", value = "id", required = false,paramType = "query", dataType = "String"),
    })
	public CommonResponse getListEmTypeDetail(
			 String emType_detail_name, String id,
			 @RequestParam(value = "pageNo", required = true,defaultValue="0") Integer pageNo ,
			 @RequestParam(value = "pageSize", required = true,defaultValue="10") Integer pageSize,

			 HttpServletRequest request, HttpServletResponse response) throws IOException {
		MdmEmTypeDetail mdmEmTypeDetail = new MdmEmTypeDetail();
		mdmEmTypeDetail.setId(id);
		mdmEmTypeDetail.setEmTypeDetailName(emType_detail_name);
		Page<MdmEmTypeDetail> page = mdmEmTypeDetailService.findPage(new Page<MdmEmTypeDetail>(pageNo, pageSize), mdmEmTypeDetail); //使用entity入参findpage
		return ResponseUtil.successResponse(page);
	}

}