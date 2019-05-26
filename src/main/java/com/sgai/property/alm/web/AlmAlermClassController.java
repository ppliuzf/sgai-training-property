package com.sgai.property.alm.web;

import com.sgai.common.persistence.Page;
import com.sgai.common.web.BaseController;
import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.modules.login.jwt.util.CommonResponse;
import com.sgai.modules.login.jwt.util.ResponseUtil;
import com.sgai.property.alm.entity.AlmAlermClass;
import com.sgai.property.alm.entity.AlmDeviceLevelRelation;
import com.sgai.property.alm.service.AlmAlermClassService;
import com.sgai.property.alm.service.AlmDeviceLevelRelationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
    * ClassName: AlmAlermClassController  
    * com.sgai.property.commonService.vo;(报警分类Controller)
    * @author 王天尧  
    * Date 2017年11月24日  
    * Company 首自信--智慧城市创新中心
 */
@RestController
@Api(description = "报警分类Controller")
@RequestMapping(value = "${adminPath}/alm/almAlermClass")
public class AlmAlermClassController extends BaseController {

	@Autowired
	private AlmAlermClassService almAlermClassService;
	@Autowired
	private AlmDeviceLevelRelationService almDeviceLevelRelationService;
	/**
	 * 
	 * list:(获取报警分类列表).
	 * @param request
	 * @param response
	 * @param token
	 * @param model
	 * @return
	 * @throws IOException :CommonResponse
	 * @since JDK 1.8
	 * @author ASUS
	 */
	@ApiOperation(value = "获取报警分类列表", notes = "获取报警分类列表")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "alermTypeName", value = "报警分类名称", required = false, dataType = "String"),
	})
	@RequestMapping(value="/getList",method=RequestMethod.POST)
	public CommonResponse list(
			String alermTypeName,
			HttpServletRequest request, 
			HttpServletResponse response,

			Model model) throws IOException {
		AlmAlermClass almAlermClass = new AlmAlermClass();
		almAlermClass.setAlermTypeName(alermTypeName);
		almAlermClass.setEnabledFlag("Y");
		Page<AlmAlermClass> page = almAlermClassService.findPage(new Page<AlmAlermClass>(request, response), almAlermClass); 
		return ResponseUtil.successResponse(page);
	}
	/**
	 * 
	 * save:(保存报警分类).
	 * @param almAlermClass
	 * @param model
	 * @param redirectAttributes
	 * @return :String 
	 * @since JDK 1.8
	 * @author 王天尧
	 * @throws IOException
	 */
	@ApiOperation(value = "保存报警分类", notes = "保存报警分类")
	@RequestMapping(value = "/save",method=RequestMethod.POST)
	public CommonResponse save(
			AlmAlermClass almAlermClass, 
			Model model, 

			RedirectAttributes redirectAttributes) throws IOException {
		Map<String,String> result = new HashMap<String,String>();
		try {
			result=almAlermClassService.saveAlmAlerm(almAlermClass, result);
		} catch (Exception e) {

			e.printStackTrace();
			result.put("msg", "faild");
		}
		return ResponseUtil.successResponse(result);
		
	}
	/**
	 * 
	 * delete:(删除报警类别).
	 * @param ids 报警分类id集合（字符串拼而成）
	 * @param redirectAttributes
	 * @return :Map<String,String> 
	 * @since JDK 1.8
	 * @author 王天尧
	 * @throws IOException
	 */
	@ApiOperation(value = "删除报警类别", notes = "删除报警类别")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "报警分类id集合", required = false, dataType = "String"),
    })
	@RequestMapping(value = "/delete",method=RequestMethod.POST)
	public CommonResponse delete(
			String ids,

			RedirectAttributes redirectAttributes) throws IOException {
		Map<String,String> result = new HashMap<String,String>();
		AlmAlermClass almAlermClass = new AlmAlermClass();
		String idArr[]=ids.split(",");
		try {
			for(String id:idArr){
				if(id!=null&&!id.equals("")){
					AlmAlermClass almAlermClass2 = almAlermClassService.get(id);
					AlmDeviceLevelRelation almDeviceLevelRelation = new AlmDeviceLevelRelation();
					almDeviceLevelRelation.setAlermTypeCode(almAlermClass2.getAlermTypeCode());
					List<AlmDeviceLevelRelation> findList2 = almDeviceLevelRelationService.findList(almDeviceLevelRelation);
					if(findList2.size()>0) {
						result.put("msg", "connection");
						break;
					}else {
						almAlermClass.setId(id);
						almAlermClassService.delete(almAlermClass);
						result.put("msg", "success");
					}
				}
			}
		} catch (Exception e) {

			e.printStackTrace();
			result.put("msg", "faild");
		}
		return ResponseUtil.successResponse(result);
		
	}
	/**
	 * 
	 * getById:(通过id查找).
	 * @param id
	 * @param model
	 * @param redirectAttributes
	 * @return :AlmAlermClass 
	 * @since JDK 1.8
	 * @author 王天尧
	 * @throws IOException
	 */
	@ApiOperation(value = "通过id查找报警类别", notes = "通过id查找报警类别")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "报警分类id", required = false, dataType = "String"),
    })
	@RequestMapping(value = "/getById",method=RequestMethod.POST)
	public CommonResponse getById(
			String id, 
			Model model,

			RedirectAttributes redirectAttributes) throws IOException {
		return ResponseUtil.successResponse(almAlermClassService.get(id));
	}
	/**
	 * 
	 * getAllList:(获取所有的分类不带分页).
	 * @param model
	 * @param redirectAttributes
	 * @return :List<AlmAlermClass> 
	 * @since JDK 1.8
	 * @author 王天尧
	 * @throws IOException
	 */
	@ApiOperation(value = "获取所有的分类不带分页", notes = "获取所有的分类不带分页")
	@RequestMapping(value = "/getAllList",method=RequestMethod.POST)
	public CommonResponse getAllList(
			LoginUser user,
			Model model, 

			RedirectAttributes redirectAttributes) throws IOException {
		AlmAlermClass almAlermClass = new AlmAlermClass();
		return ResponseUtil.successResponse(almAlermClassService.findList(almAlermClass));
	}
}