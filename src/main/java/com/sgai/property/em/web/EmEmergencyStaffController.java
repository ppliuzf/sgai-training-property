package com.sgai.property.em.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
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
import com.fasterxml.jackson.core.JsonProcessingException;
import com.sgai.common.persistence.Page;
import com.sgai.common.web.BaseController;
import com.sgai.property.em.entity.EmEmergencyStaff;
import com.sgai.property.em.service.EmEmergencyStaffService;
import com.sgai.modules.login.jwt.util.CommonResponse;
import com.sgai.modules.login.jwt.util.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 
    * ClassName: EmEmergencyStaffController  
    * com.sgai.property.commonService.vo;(应急人员表Controller)
    * @author yangyz  
    * Date 2017年12月14日  
    * Company 首自信--智慧城市创新中心
 */
@RestController
@RequestMapping(value = "${adminPath}/em/ememergencystaff/emEmergencyStaff")
@Api(description = "应急人员接口")
public class EmEmergencyStaffController extends BaseController {

	@Autowired
	private EmEmergencyStaffService emEmergencyStaffService;
	
	/**
	 * 
	 * getListEmergencyPerson:(查询应急人员列表).
	 * @param emEmergencyStaff
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException :Page<EmEmergencyStaff> 
	 * @since JDK 1.8
	 * @author yangyz
	 */
	@ApiOperation(value = "获得应急人员分页列表信息", notes = "获得应急人员分页列表信息")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "typeCode", value = "类型编号", required = false,paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "staffType", value = "人员类型", required = false,paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "staffName", value = "人员名称", required = false,paramType = "query", dataType = "String")
	})
	@RequestMapping(value = "/getListEmergencyStaff",method=RequestMethod.POST)
	public CommonResponse getListEmergencyStaff(
			String staffName,
			String typeCode,
			String staffType,
			@RequestHeader(value="token")String token,
			@RequestParam(value="pageNo",required = true ,defaultValue = "0" )Integer pageNo,
			@RequestParam(value="pageSize",required = true ,defaultValue = "10" )Integer pageSize,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		EmEmergencyStaff emEmergencyStaff = new EmEmergencyStaff();
		emEmergencyStaff.setTypeCode(typeCode);
		emEmergencyStaff.setStaffType(staffType);
		Page<EmEmergencyStaff> page = emEmergencyStaffService.findPage(new Page<EmEmergencyStaff>(pageNo, pageSize), emEmergencyStaff);
		return ResponseUtil.successResponse(page);
	}
	
	/**
	 * 
	 * save:(保存人员信息).
	 * @param emEmergencyStaff
	 * @param model
	 * @param redirectAttributes
	 * @return :Map<String,Object> 
	 * @since JDK 1.8
	 * @author yangyz
	 * @throws JsonProcessingException 
	 */
	@ApiOperation(value = "保存应急人员信息", notes = "保存应急人员信息")
	@RequestMapping(value = "save",method=RequestMethod.POST)
	public CommonResponse save(EmEmergencyStaff emEmergencyStaff, @RequestHeader(value="token")String token,String oldStaffCode, Model model, RedirectAttributes redirectAttributes) throws JsonProcessingException {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map = emEmergencyStaffService.saveStaff(emEmergencyStaff,oldStaffCode);
		} catch (Exception e) {

			e.printStackTrace();
			map.put("msg", "fail");
		}
		return ResponseUtil.successResponse(map);
	}
	/**
	 * 
	 * getById:(通过id获取人员对象).
	 * @param id
	 * @return :EmEmergencyStaff 
	 * @since JDK 1.8
	 * @author 王天尧
	 * @throws JsonProcessingException 
	 */
	@ApiOperation(value = "获得应急人员信息", notes = "获得应急人员信息")
	@RequestMapping(value = "getById",method=RequestMethod.POST)
	public CommonResponse getById(@RequestHeader(value="token")String token,String id) throws JsonProcessingException {
		EmEmergencyStaff staff = emEmergencyStaffService.get(id);
		return ResponseUtil.successResponse(staff);
	}
	/**
	 * 
	 * delete:(删除人员).
	 * @param emEmergencyStaff
	 * @param ids 人员id集合
	 * @param redirectAttributes
	 * @return :Map<String,String> 
	 * @since JDK 1.8
	 * @author 王天尧
	 * @throws JsonProcessingException 
	 */
	@ApiOperation(value = "删除应急人员信息", notes = "删除应急人员信息")
	@RequestMapping(value = "/delete",method=RequestMethod.POST)
	public CommonResponse delete(@RequestHeader(value="token")String token,EmEmergencyStaff emEmergencyStaff,String ids, RedirectAttributes redirectAttributes) throws JsonProcessingException {
		Map<String,String> result = new HashMap<String,String>();
		String idArr[]=ids.split(",");
		try {
			for(String id:idArr){
				if(id!=null&&!id.equals("")){
					emEmergencyStaff.setId(id);
					emEmergencyStaffService.delete(emEmergencyStaff);
				}
			}
			result.put("msg", "success");
			result.put("result", "删除成功");
		} catch (Exception e) {

			e.printStackTrace();
			result.put("msg", "faild");
			result.put("result", "删除失败");
		}
		return ResponseUtil.successResponse(result);
		
	}
	
	/**
	 * 
	 * getStaffList:(封装应急专家和应急人员树结构).
	 * @return :List<Map<String,Object>> 
	 * @since JDK 1.8
	 * @author yangyz
	 * @throws JsonProcessingException 
	 */
	@ApiOperation(value = "获得应急人员列表信息", notes = "获得应急人员列表信息")
	@RequestMapping(value = "getStaffList",method=RequestMethod.POST)
	public CommonResponse getStaffList(@RequestHeader(value="token")String token,String staffType, String codeType, String staffCodes) throws JsonProcessingException{
		List<Map<String,String>> list = emEmergencyStaffService.getStaffList(staffType, codeType,staffCodes);
		return ResponseUtil.successResponse(list);
	}
}