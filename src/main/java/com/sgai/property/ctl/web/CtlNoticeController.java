package com.sgai.property.ctl.web;

import com.sgai.common.persistence.Page;
import com.sgai.common.web.BaseController;
import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.modules.login.jwt.util.CommonResponse;
import com.sgai.modules.login.jwt.util.ResponseUtil;
import com.sgai.property.ctl.entity.CtlNotice;
import com.sgai.property.ctl.service.CtlNoticeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 消息通知Controller
 * @author admin
 * @version 2018-06-15
 */
@RestController
@Api(description = "通知信息")
@RequestMapping(value = "${adminPath}/ctl/ctlNotice")
public class CtlNoticeController extends BaseController {

	@Autowired
	private CtlNoticeService ctlNoticeService;
	/**
	 * 
	    * @Title: save  
	    * @Description: (保存通知信息)
	    * @param @param userCode
	    * @param @param noticeTime
	    * @param @param noticeInfo
	    * @param @param token
	    * @param @param redirectAttributes
	    * @param @return
	    * @param @throws ServletException
	    * @param @throws IOException    参数  
	    * @return CommonResponse    返回类型  
	    * @throws
	 */
	@ApiOperation(value = "保存通知信息，包括插入和更新", notes = "保存通知信息，包括插入和更新")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userCode", value = "接收通知的用户代码", required = false,  paramType = "query" ,dataType = "String"),
            @ApiImplicitParam(name = "noticeTime", value = "发送通知的时间", required = false, paramType = "query" ,dataType = "Date"),
            @ApiImplicitParam(name = "noticeInfo", value = "发送通知的内容", required = false, paramType = "query" ,dataType = "String"),
    })
	@RequestMapping(value = "save",method=RequestMethod.POST)
	public CommonResponse save(
			String userCode,
			Date noticeTime,
			String noticeInfo,
			@RequestHeader("token") String token
			) throws IOException {
		Map<String,String> map=new HashMap<String,String>();
		try {
			ctlNoticeService.saveNotice(userCode, noticeTime, noticeInfo);
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
	    * @Title: findByUserCode  
	    * @Description: (根据当前登录用户查询)
	    * @param @param user
	    * @param @param token
	    * @param @param redirectAttributes
	    * @param @return
	    * @param @throws ServletException
	    * @param @throws IOException    参数  
	    * @return CommonResponse    返回类型  
	    * @throws
	 */
	@ApiOperation(value = "根据当前登录用户查询未读通知", notes = "根据当前登录用户查询未读通知")
	@RequestMapping(value = "findByUserCode",method=RequestMethod.POST)
	public CommonResponse findByUserCode(
			LoginUser user,
			String readFlag,

			HttpServletRequest request, 
			HttpServletResponse response) throws IOException {
		    Long count = null;
		    Page<CtlNotice> findPage = null;
			CtlNotice ctlNotice = new CtlNotice();
			ctlNotice.setUserCode(user.getUserId());
			ctlNotice.setEnabledFlag("Y");
			if("N".equals(readFlag)) {
				ctlNotice.setReadFlag("N");
				 findPage = ctlNoticeService.findPage(new Page<CtlNotice>(request, response),ctlNotice);
			}else {
				CtlNotice ctlNoticeNew = new CtlNotice();
				ctlNoticeNew.setUserCode(user.getUserId());
				ctlNoticeNew.setEnabledFlag("Y");
				ctlNoticeNew.setReadFlag("N");
				List<CtlNotice> findList = ctlNoticeService.findList(ctlNoticeNew);
				count=(long)findList.size();
				findPage = ctlNoticeService.findPage(new Page<CtlNotice>(request, response),ctlNotice);
				findPage.setCount(count);
			}
		return ResponseUtil.successResponse(findPage);
	}
	/**
	 * 
	    * @Title: findAllByUserCode  
	    * @Description: (根据当前登录用户查询所有通知)
	    * @param @param user
	    * @param @param token
	    * @param @param request
	    * @param @param response
	    * @param @return
	    * @param @throws ServletException
	    * @param @throws IOException    参数  
	    * @return CommonResponse    返回类型  
	    * @throws
	 */
	@ApiOperation(value = "根据当前登录用户查询所有通知", notes = "根据当前登录用户查询所有通知")
	@RequestMapping(value = "findAllByUserCode",method=RequestMethod.POST)
	public CommonResponse findAllByUserCode(
			LoginUser user,

			HttpServletRequest request, 
			HttpServletResponse response) throws IOException {
			CtlNotice ctlNotice = new CtlNotice();
			ctlNotice.setNoticeInfo(user.getUserId());
			ctlNotice.setEnabledFlag("Y");
		return ResponseUtil.successResponse(ctlNoticeService.findPage(new Page<CtlNotice>(request, response),ctlNotice));
	}
	@ApiOperation(value = "已读操作", notes = "已读操作")
	 @ApiImplicitParams({
         @ApiImplicitParam(name = "id", value = "通知id", required = false, dataType = "String"),
	 })
	@RequestMapping(value = "haveRead",method=RequestMethod.POST)
	public CommonResponse haveRead(
			String id,

			RedirectAttributes redirectAttributes) throws IOException {
		Map<String,String> map=new HashMap<String,String>();
		try {
			ctlNoticeService.haveRead(id);
			map.put("msg", "success");
		} catch (Exception e) {
			// : handle exception
			e.printStackTrace();
			map.put("msg", "faild");
		}
		return ResponseUtil.successResponse(map);
	}
	@ApiOperation(value = "已读操作", notes = "已读操作")
	 @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "通知id", required = false, dataType = "String"),
	 })
	/**
	 * 
	    * @Title: findById  
	    * @Description: (通过id查找通知)
	    * @param @param id
	    * @param @param token
	    * @param @param redirectAttributes
	    * @param @return
	    * @param @throws ServletException
	    * @param @throws IOException    参数  
	    * @return CommonResponse    返回类型  
	    * @throws
	 */
	@RequestMapping(value = "findById",method=RequestMethod.POST)
	public CommonResponse findById(
			String id,

			RedirectAttributes redirectAttributes) throws IOException {
		return ResponseUtil.successResponse(ctlNoticeService.get(id));
	}
	/*@ApiOperation(value = "接口调用", notes = "接口调用")
	@RequestMapping(value = "returnResult",method=RequestMethod.POST)
	public CommonResponse returnResult(

			RedirectAttributes redirectAttributes) throws ServletException, IOException, JSONException {
		OkHttpClient client = new OkHttpClient();

		MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
		Request request = new Request.Builder()
		  .url("https://cps.7gwifi.cn/API/My")
		  .addHeader("authorization", "TOS username=\"ibms\", uri=\"https://cps.7gwifi.cn/API/My\", response=\"e61f742461c88a52cc853a45f535c0f3\"")
		  .addHeader("content-type", "application/x-www-form-urlencoded")
		  .build();

		Response response = client.newCall(request).execute();
		String json = response.body().string();
		return ResponseUtil.successResponse(json);
	}*/

	
}