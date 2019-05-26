package com.sgai.property.ctl.web;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sgai.common.persistence.Page;
import com.sgai.common.utils.StringUtils;
import com.sgai.common.web.BaseController;
import com.sgai.property.ctl.entity.CtlBusinessDefine;
import com.sgai.property.ctl.service.CtlBusinessDefineService;
import com.sgai.modules.login.jwt.util.CommonResponse;
import com.sgai.modules.login.jwt.util.ResponseUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


/**
 * 
    * ClassName: CtlBusinessDefineController  
    * Description: (子系统定义web层)
    * @author 王天尧  
    * Date 2017年11月18日  
    * Company 首自信--智慧城市创新中心
 */
@RestController
@RequestMapping(value = "${adminPath}/ctl/ctlBusinessDefine")
@Api(description = "子系统定义")
public class CtlBusinessDefineController extends BaseController {

	@Autowired
	private CtlBusinessDefineService ctlBusinessDefineService;
	
	/*@ModelAttribute
	public CtlBusinessDefine get(@RequestParam(required=false) String id) {
		CtlBusinessDefine entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ctlBusinessDefineService.get(id);
		}
		if (entity == null){
			entity = new CtlBusinessDefine();
		}
		return entity;
	}*/
	/**
	 * 
	 * save:(保存子系统).
	 * @param ctlBusinessDefine
	 * @param busiCode 子系统代码
	 * @param model
	 * @param redirectAttributes
	 * @return :Map<String,String> 
	 * @since JDK 1.8
	 * @author 王天尧
	 * @throws IOException
     */
	@ApiOperation(value = "保存子系统", notes = "保存子系统")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderOld", value = "修改前的显示顺序", required = false, dataType = "Long"),
    })
	@RequestMapping(value = "/save",method=RequestMethod.POST)
	public CommonResponse addSave(
			Long orderOld,
			CtlBusinessDefine  ctlBusinessDefine,
			@RequestHeader("token") String token
			 ) throws IOException {
		
		String busiIoc=ctlBusinessDefine.getBusiIoc();
		String bannerImg=ctlBusinessDefine.getBannerImg();
		String backImg=ctlBusinessDefine.getBackImg();
		busiIoc=URLDecoder.decode(busiIoc, "utf-8");
		bannerImg=URLDecoder.decode(bannerImg, "utf-8");
		backImg=URLDecoder.decode(backImg, "utf-8");
		ctlBusinessDefine.setBusiIoc(busiIoc);
		ctlBusinessDefine.setBannerImg(bannerImg);
		ctlBusinessDefine.setBackImg(backImg);
		
		Map<String,String> map = new HashMap<String,String>();
		try {
			map=ctlBusinessDefineService.saveBD(ctlBusinessDefine, orderOld,map);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg","faild");
		}
		return ResponseUtil.successResponse(map);
	}
	/**
	 * 
	 * delete:(这里用一句话描述这个方法的作用).
	 * @param ids 子系统的id集合拼成的字符串
	 * @param request
	 * @param response
	 * @return :Map<String,String> 
	 * @since JDK 1.8
	 * @author 王天尧
	 * @throws IOException
     */
	@ApiOperation(value = "删除子系统", notes = "删除子系统")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "ids", value = "想要删除的对象的id集合", required = false, dataType = "String"),
    })
	@RequestMapping(value = "delete",method=RequestMethod.POST)
	public CommonResponse delete(
			String ids, 

			HttpServletRequest request,HttpServletResponse response) throws IOException {
		Map<String,String> map=new HashMap<String,String>();
		try {
			ctlBusinessDefineService.delete(ids);
			map.put("msg", "success");
		} catch (Exception e) {
			// : handle exception
			e.printStackTrace();
			map.put("msg", "faild");
		}
		return ResponseUtil.successResponse(map);
	}
	/**
	 * 
	 * getListCtlBusinessDefine:(加载子系统列表带分页).
	 * @param ctlBusinessDefine 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException :Page<CtlBusinessDefine> 
	 * @since JDK 1.8
	 * @author 王天尧
     */
	@ApiOperation(value = "加载子系统列表带分页", notes = "加载子系统列表带分页")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "busiName", value = "子系统名称", required = false, dataType = "String"),
    })
	@RequestMapping(value = "/getListBusinessDefine",method=RequestMethod.POST)
	public CommonResponse getListCtlBusinessDefine(
			String busiName,

			HttpServletRequest request, HttpServletResponse response) throws IOException {
		CtlBusinessDefine ctlBusinessDefine = new CtlBusinessDefine();
		ctlBusinessDefine.setBusiName(busiName);
		return ResponseUtil.successResponse(ctlBusinessDefineService.getList(ctlBusinessDefine,new Page<CtlBusinessDefine>(request, response)));
	}
	/**
	 * @Title: findById
	    * @Description: (根据id找到所需要修改的对象)
	    * @param @param id
	    * @param @param request
	    * @param @param response
	    * @param @return
	    * @param @throws IOException    参数  
	    * @return CtlBusinessDefine    返回类型  
	    * @throws
	 */
	@ApiOperation(value = "根据id找到所需要修改的对象", notes = "根据id找到所需要修改的对象")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "想要修改的对象的id", required = false, dataType = "String"),
    })
	@RequestMapping(value = "/findById",method=RequestMethod.POST)
	public CommonResponse findById(
			String id,

			HttpServletRequest request, HttpServletResponse response) throws IOException {
		String[] ids = id.split(",");
		CtlBusinessDefine ctlBusinessDefine = ctlBusinessDefineService.get(ids[0]);
		return ResponseUtil.successResponse(ctlBusinessDefine);
	}
}