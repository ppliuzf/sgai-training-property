package com.sgai.property.mdm.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.sgai.common.persistence.Page;
import com.sgai.common.web.BaseController;
import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.modules.login.jwt.util.CommonResponse;
import com.sgai.modules.login.jwt.util.ResponseUtil;
import com.sgai.property.mdm.entity.MdmBrandInfo;
import com.sgai.property.mdm.service.MdmBrandInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 
    * ClassName: MdmBrandInfoController  
    * com.sgai.property.commonService.vo;(品牌信息维护)
    * @author yangyz 
    * Date 2017年11月24日  getBrandInfo
    * Company 首自信--智慧城市创新中心
 */
@RestController
@RequestMapping(value = "${adminPath}/mdm/brandinfo/mdmBrandInfo")
@Api(description = "品牌接口")
public class MdmBrandInfoController extends BaseController {

	@Autowired
	private MdmBrandInfoService mdmBrandInfoService;
	
	/**
	 * 
	 * getListBrandInfo:(查询设备品牌信息列表).
	 * @param mdmBrandInfo
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException :Page<MdmBrandInfo> 
	 * @since JDK 1.8
	 * @author yangyz
	 */
	@ApiOperation(value = "获得设备品牌分页列表信息", httpMethod = "POST", notes = "获得设备品牌分页列表信息")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "brandName", value = "品牌名称", required = false,paramType = "query", dataType = "String")
	})
	@RequestMapping(value = "/getListBrandInfo",method=RequestMethod.POST)
	public CommonResponse getListBrandInfo(
			LoginUser user,
			String brandName,

			@RequestParam(value = "pageNo", required = true,defaultValue="0") Integer pageNo ,
			@RequestParam(value = "pageSize", required = true,defaultValue="10") Integer pageSize,
			HttpServletRequest request, 
			HttpServletResponse response
			) throws IOException {
		MdmBrandInfo mdmBrandInfo = new MdmBrandInfo();
		mdmBrandInfo.setBrandName(brandName);
		Page<MdmBrandInfo> page = mdmBrandInfoService.findPage(new Page<MdmBrandInfo>(pageNo, pageSize), mdmBrandInfo);
		return ResponseUtil.successResponse(page);
	}
	
	/**
	 * 
	 * getBrandInfo:(查询一条品牌信息).
	 * @param request
	 * @param id
	 * @param response
	 * @return
	 * @throws IOException :MdmBrandInfo 
	 * @since JDK 1.8
	 * @author yangyz
	 */
	@ApiOperation(value = "获得设备品牌信息", httpMethod = "POST", notes = "获得设备品牌信息")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "主键", required = false,paramType = "query", dataType = "String")
	})
	@RequestMapping(value = "/getBrandInfo",method=RequestMethod.POST)
	public CommonResponse getBrandInfo(
			String id,

			HttpServletRequest request, 
			HttpServletResponse response
			) throws IOException {
		MdmBrandInfo brandInfo = mdmBrandInfoService.get(id);
		return ResponseUtil.successResponse(brandInfo);
	}
	
	/**
	 * 
	 * save:(新增或修改保存品牌信息).
	 * @param mdmBrandInfo
	 * @param oldClassCode
	 * @param model
	 * @param redirectAttributes
	 * @return :Map<String,Object> 
	 * @since JDK 1.8
	 * @author yangyz
	 * @throws JsonProcessingException 
	 */
	@ApiOperation(value = "保存设备品牌信息", httpMethod = "POST", notes = "保存设备品牌信息")
	@RequestMapping(value = "save",method=RequestMethod.POST)
	public CommonResponse save(
			MdmBrandInfo mdmBrandInfo, 

			RedirectAttributes redirectAttributes
			) throws JsonProcessingException {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map = mdmBrandInfoService.saveBrandInfo(mdmBrandInfo);
		} catch (Exception e) {

			e.printStackTrace();
			map.put("msg", "fail");
		}
		return ResponseUtil.successResponse(map);
	}
	
	/**
	 * 
	 * delete:(批量删除品牌信息).
	 * @param ids
	 * @param redirectAttributes
	 * @return :Map<String,Object> 
	 * @since JDK 1.8
	 * @author yangyz
	 * @throws JsonProcessingException 
	 */
	@ApiOperation(value = "删除设备品牌信息", httpMethod = "POST", notes = "删除设备品牌信息")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "ids", value = "多个主键，逗号分隔", required = false,paramType = "query", dataType = "String")
	})
	@RequestMapping(value = "delete",method=RequestMethod.POST)
	public CommonResponse delete(
			String ids, 

			RedirectAttributes redirectAttributes
			) throws JsonProcessingException {
		Map<String, Object> map = new HashMap<String, Object>(); 
		try {
			map = mdmBrandInfoService.deleteBrandInfo(ids);
		} catch (Exception e) {

			e.printStackTrace();
			map.put("msg", "出错了！");
			map.put("result", "fail");
		}
		return ResponseUtil.successResponse(map);
	}

}