    
package com.sgai.property.wy.web;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sgai.common.persistence.Page;
import com.sgai.common.web.BaseController;
import com.sgai.modules.login.annotation.DataAuthority;
import com.sgai.modules.login.annotation.PermessionLimit;
import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.modules.login.jwt.util.CommonResponse;
import com.sgai.modules.login.jwt.util.JwtUtil;
import com.sgai.modules.login.jwt.util.ResponseUtil;
import com.sgai.property.ctl.entity.CtlUser;
import com.sgai.property.ctl.service.CtlUserService;
import com.sgai.property.wy.dao.CallDao;
import com.sgai.property.wy.entity.CallCommon;
import com.sgai.property.wy.entity.CallInformation;
import com.sgai.property.wy.entity.Member;
import com.sgai.property.wy.service.CallService;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**  
    * @ClassName: CallController  
    * (来电处理)
    * @author cui  
    * @date 2018年1月29日  
    * @Company 首自信--智慧城市创新中心  
    */
@RestController
@RequestMapping("/call")
public class CallController extends BaseController {
   
	@Autowired
	private CallService callService;
	@Autowired
	private CtlUserService userService;
	
	// 查询所有数据并分页
	@DataAuthority(tableAlias = "a")
	@RequestMapping(value = "/callList", method=RequestMethod.POST)
	public CommonResponse getListCall(
            @RequestParam(value = "pageNo", required = true,defaultValue="0") Integer pageNo ,
            @RequestParam(value = "pageSize", required = true,defaultValue="10") Integer pageSize,
            HttpServletRequest request, HttpServletResponse response, CallInformation call, LoginUser user
			)throws IOException, ServletException {
		       
		if(StringUtils.isNotBlank(call.getType())){
			 //处理页面跳转
			List<String>  role=callService.queryRole(user.getUserId()); 
			role.forEach(e->{
			       if(!"wy_jl".equals(e)){
				       if(StringUtils.isNotBlank(call.getType())){
							call.setOperatorId(user.getUserId());
						}
			       }else{
			    	   call.setOperatorId("");
			    	   call.setType("");
			       }
			});
		}
		Page<CallInformation> page = callService.findPage(new Page<CallInformation>(pageNo, pageSize), call);
		return ResponseUtil.successResponse(page);
	}
	
	// 修改/添加数据
	
	@RequestMapping(value = "/save", method=RequestMethod.POST)
	public CommonResponse save(CallInformation call , LoginUser user) throws ServletException, IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			call.setCreatedBy(user.getUserId());
			call.setLoginId(user.getUserId());
			call.setAppointStatus("0");
			call.setSerialNumber(CallDao.serialNumbers());  //新增生成流水号
			call.setComCode(user.getComCode());
			call.setDeptCode(user.getDeptCode());
			callService.save(call);
			map.put("msg", "success");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", "fail");
		}
		return ResponseUtil.successResponse(map);
	}
	
	// 批量删除数据
		@RequestMapping("/deleteCall")
	    @Transactional
	    public Map<String,Object> deleteCall(CallInformation call,LoginUser user) throws ServletException, IOException {
			Map<String,Object> result = Maps.newHashMap();
			try {
				String ids = call.getId();
				String[] idArray = ids.split(",");
				List<String> idList = Lists.newArrayList();
				for(String id : idArray) {
					if(id!=null && !id.equals("")) {
						idList.add(id);
					}
				}
				callService.batchDeleteCall(idList);		
				result.put("state", true);
				result.put("msg", "删除成功!");
			}catch(Exception e) {
				e.printStackTrace();
				result.put("state", false);
				result.put("msg", "删除失败!");
			}
			return result;
		}
	
	@RequestMapping(value = "findLoginName")
	public CommonResponse form(CallInformation call, LoginUser user) throws IOException {
		Map<String,Object> result = Maps.newHashMap();
		result.put("login", user.getUserName());
		return ResponseUtil.successResponse(result);
	}
	
	//指定处理人
	@RequestMapping(value = "updateAppointStatus")
	public CommonResponse updateStatus(CallInformation call) throws  IOException, ServletException {
		Map<String, Object> map = new HashMap<String, Object>();
		String userCode = call.getOperatorId();
		if(userCode != null){
			CtlUser cc = new CtlUser();
			cc.setUserCode(userCode);
			List<CtlUser> lists = userService.findList(cc);
			call.setOperatorName(lists.get(0).getUserName());
		}
		try {
			callService.updateAppointStatus(call);
			map.put("msg", "success");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", "fail");
		}
		return ResponseUtil.successResponse(map);
	}
	
	//更新处理状态
	@RequestMapping(value = "updateDealStatus")
	public CommonResponse updateDealStatus(CallInformation call) throws  IOException, ServletException {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			callService.updateDealStatus(call);
			map.put("msg", "success");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", "fail");
		}
		return ResponseUtil.successResponse(map);
	}
	
	//模糊查询会员姓名
	@RequestMapping(value = "showName")
	public CommonResponse show(CallInformation call, LoginUser user) throws IOException {
		List<Member> mlist= callService.queryName(call.getCaller());
		return ResponseUtil.successResponse(mlist);
	}
	
	//查询会员指定区域
	@RequestMapping(value = "findAddress")
	public Map<String,Object> findAddress(@RequestParam("id") String id) throws IOException {
		return  callService.queryAddress(id);
	}
	
	// 查询指定人数据并分页
	@RequestMapping(value = "/callAppointList", method=RequestMethod.POST)
	public CommonResponse callAppointList(
            @RequestParam(value = "pageNo", required = true,defaultValue="0") Integer pageNo ,
            @RequestParam(value = "pageSize", required = true,defaultValue="5") Integer pageSize,
            HttpServletRequest request, HttpServletResponse response, CallCommon call
			)throws IOException, ServletException {
		Page<CallCommon> page = callService.findPage(new Page<CallCommon>(pageNo, pageSize), call);
		return ResponseUtil.successResponse(page);
	}
	
	// 导出数据到EXCEL
	@DataAuthority(tableAlias = "a")
	@RequestMapping(value = "/callExport", method=RequestMethod.GET)
	@PermessionLimit(limit=false)
	public void export(CallInformation call, String token, HttpServletResponse response)throws Exception{
        Claims claims = JwtUtil.parseJWT(token);
        String json = claims.getSubject();
        LoginUser user = JSONObject.parseObject(json, LoginUser.class);
        if("a".equals(call.getType())){
        	//查询已指定的来电
        	call.setAppointStatus("1");
        	List<String>  role=callService.queryRole(user.getUserId()); 
			role.forEach(e->{
			       if(!"wy_jl".equals(e)){
				       if(StringUtils.isNotBlank(call.getType())){
							call.setOperatorId(user.getUserId());
						}
			       }else{
			    	   call.setOperatorId("");
			    	   call.setType("");
			       }
			});
        }
		callService.export(call, response);
	}

}
