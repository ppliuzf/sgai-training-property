  
    /**    
    * @Title: NewsManageController.java  
    * @Package com.sgai.property.wy.web
    * (用一句话描述该文件做什么)
    * @author XJ9001  
    * @date 2018年2月1日  
    * @Company 首自信--智慧城市创新中心
    * @version V1.0    
    */  
    
package com.sgai.property.wy.web;

    import com.alibaba.fastjson.JSONObject;
    import com.fasterxml.jackson.core.JsonProcessingException;
    import com.sgai.common.persistence.Page;
    import com.sgai.common.web.BaseController;
    import com.sgai.modules.login.annotation.PermessionLimit;
    import com.sgai.modules.login.jwt.bean.LoginUser;
    import com.sgai.modules.login.jwt.util.CommonResponse;
    import com.sgai.modules.login.jwt.util.JwtUtil;
    import com.sgai.modules.login.jwt.util.ResponseUtil;
    import com.sgai.modules.login.support.UserServletContext;
    import com.sgai.property.wy.entity.NewsManage;
    import com.sgai.property.wy.service.CallService;
    import com.sgai.property.wy.service.NewsManageService;
    import io.jsonwebtoken.Claims;
    import org.apache.commons.lang3.StringUtils;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.web.bind.annotation.*;
    import org.springframework.web.multipart.MultipartFile;
    import org.springframework.web.multipart.MultipartHttpServletRequest;

    import javax.servlet.ServletException;
    import javax.servlet.http.HttpServletRequest;
    import javax.servlet.http.HttpServletResponse;
    import java.io.IOException;
    import java.text.SimpleDateFormat;
    import java.util.Date;
    import java.util.HashMap;
    import java.util.List;
    import java.util.Map;


    /**  
 * @ClassName: NewsManageController  
 * (这里用一句话描述这个类的作用)
 * @author XJ9001  
 * @date 2018年2月1日  
 * @Company 首自信--智慧城市创新中心  
 */
@RestController
@RequestMapping("/newsManage")
public class NewsManageController extends BaseController {
	private static final String ROOT_PATH = "D:/wy_files/";
	@Autowired
	private NewsManageService newsManageService;
	@Autowired
	private CallService callService;
	
	//消息所有数据
	@RequestMapping(value = "/getList", method=RequestMethod.POST)
	@PermessionLimit(limit=false)
	public CommonResponse getListNews(
			String newsTitle,
			String newsStatus,
			Date publishDate,
			String newsType,
			String newsCheckYesOrNo,
			String emergencyStatus,
			String type,

			@RequestParam(value = "pageNo", required = true,defaultValue="0") Integer pageNo ,
			@RequestParam(value = "pageSize", required = true,defaultValue="10") Integer pageSize,
			HttpServletRequest request,
			LoginUser user,
			HttpServletResponse response
			)throws IOException, ServletException {
		NewsManage newsManage = new NewsManage();
		if("1".equals(type)){
			 //处理页面跳转
			List<String>  role=callService.queryRole(user.getUserId()); 
			if(!role.toString().contains("wy_jl")){
				 newsManage.setNewsPublishId(user.getUserId());
			}
			/*role.forEach(e->{
			       if(!"wy_jl".equals(e)){
			    	   newsManage.setNewsPublishId(user.getUserId());
			       }
			});*/
		}
		if("2".equals(type)){
			NewsManage newsManage2=new NewsManage();
			//cover借用一下
			newsManage2.setCover(user.getUserId());
			NewsManage newsManage1=newsManageService.findCode(newsManage2);
			newsManage.setCheckerId(newsManage1.getCover());
		}
		if("3".equals(type)){
			newsManage.setReceptId(user.getUserId());
		}
		newsManage.setType(type);
		newsManage.setNewsTitle(newsTitle);
		newsManage.setNewsStatus(newsStatus);
		newsManage.setPublishDate(publishDate);
		newsManage.setNewsType(newsType);
		newsManage.setNewsCheckYesOrNo(newsCheckYesOrNo);
		newsManage.setEmergencyStatus(emergencyStatus);
		Page<NewsManage> page = newsManageService.findPage(new Page<NewsManage>(pageNo, pageSize), newsManage);
		return ResponseUtil.successResponse(page);
	}
	
	@RequestMapping(value = "/getListByCheck", method=RequestMethod.POST)
	@PermessionLimit(limit=false)
	public CommonResponse getListNewsByCheck(
			String newsTitle,
			String newsStatus,
			Date publishDate,
			String newsType,
			String newsCheckYesOrNo,

			@RequestParam(value = "pageNo", required = true,defaultValue="0") Integer pageNo ,
			@RequestParam(value = "pageSize", required = true,defaultValue="10") Integer pageSize,
			HttpServletRequest request,
			HttpServletResponse response
			)throws Exception {
		NewsManage newsManage = new NewsManage();
		newsManage.setNewsTitle(newsTitle);
		newsManage.setNewsStatus(newsStatus);
		newsManage.setPublishDate(publishDate);
		newsManage.setNewsType(newsType);
		newsManage.setNewsCheckYesOrNo(newsCheckYesOrNo);
        LoginUser user = UserServletContext.getUserInfo();
        newsManage.setCheckerId(user.getUserId());
		Page<NewsManage> page = newsManageService.findPage(new Page<>(pageNo, pageSize), newsManage);
		return ResponseUtil.successResponse(page);
	}
	
	/**
	 *  
	 * @param ids
	 * @return :Map<String,Object>
	 * @since JDK 1.8
	 * @author zxj
	 * @throws IOException 
	 * @throws ServletException
	 */
	@RequestMapping(value = "/deleteNews", method=RequestMethod.POST)
	public CommonResponse delete(
			String ids
			) throws ServletException, IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			NewsManage newsManage = new NewsManage();
			newsManage.setId(ids);
			 newsManageService.delete(newsManage);
			map.put("msg", "success");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", "fail");
		}
		return ResponseUtil.successResponse(map);
	}
	
	
	/**
	 * 
	 * save
	 * @return :Map<String,Object>
	 * @since JDK 1.8
	 * @author zxj
	 * @throws Exception 
	 */
	@RequestMapping(value = "/save", method=RequestMethod.POST)
	public CommonResponse save(NewsManage newsManage,
                               @RequestHeader("token") String token
			) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		Claims claims = JwtUtil.parseJWT(token);
        String json = claims.getSubject();
        LoginUser user = JSONObject.parseObject(json, LoginUser.class);
		try {
			if("0".equals(newsManage.getNewsCheckYesOrNo())){
				newsManage.setCheckStatus("未提交");
				newsManage.setNewsStatus("草稿");
			}
			if("1".equals(newsManage.getNewsCheckYesOrNo())){
				newsManage.setCheckStatus("不需要审核");
				newsManage.setNewsStatus("已发布");
			}
			newsManage.setNewsPublishId(user.getUserId());
			newsManage.setNewsPublishName(user.getUserName());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
			Date today = sdf.parse(sdf.format(new Date()));
			newsManage.setPublishDate(today);
			//newsManage.setCover(ROOT_PATH+newsManage.getCover());
			newsManageService.save(newsManage);
			map.put("msg", "success");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", "fail");
		}
		return ResponseUtil.successResponse(map);
	}
	@RequestMapping(value = "/save1", method=RequestMethod.POST)
	public CommonResponse save1(NewsManage newsManage,
                                @RequestHeader("token") String token
			) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		Claims claims = JwtUtil.parseJWT(token);
        String json = claims.getSubject();
        LoginUser user = JSONObject.parseObject(json, LoginUser.class);
		try {
			if("0".equals(newsManage.getNewsCheckYesOrNo())){
				newsManage.setCheckStatus("未提交");
				newsManage.setNewsStatus("已修改");
			}
			if("1".equals(newsManage.getNewsCheckYesOrNo())){
				newsManage.setCheckStatus("不需要审核");
				newsManage.setNewsStatus("已发布");
			}
			//newsManage.setNewsPublishId(user.getUserId());
			newsManage.setNewsPublishName(user.getUserName());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
			Date today = sdf.parse(sdf.format(new Date()));
			newsManage.setPublishDate(today);
			//newsManage.setCover(ROOT_PATH+newsManage.getCover());
			newsManageService.save(newsManage);
			map.put("msg", "success");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", "fail");
		}
		return ResponseUtil.successResponse(map);
	}
	/**
	 * 
	 * 
	 * @param request
	 * @param id
	 * @param response
	 * @return
	 * @throws IOException
	 * @since JDK 1.8
	 * @author zxj
	 * @throws ServletException
	 */
	@RequestMapping(value = "/findNewsById", method=RequestMethod.POST)
	public CommonResponse getGoodsBorrow(
			String id
			) throws IOException, ServletException {
		NewsManage newsManage = newsManageService.get(id);
		return ResponseUtil.successResponse(newsManage);
	}
	/**
	 * 
	 * 
	 * @param request
	 * @param id
	 * @param response
	 * @return
	 * @since JDK 1.8
	 * @author zxj
	 * @throws Exception 
	 */
	@RequestMapping(value = "/upNewsStatus", method=RequestMethod.POST)
	public CommonResponse upNewsStatus(
			String id, //消息ID
			String newsStatus, //消息状态
			String cause, //原因
			String checkStatus,
			@RequestHeader("token") String token
			) throws Exception {
		NewsManage nm = new NewsManage();
		nm.setCause(cause);
		nm.setId(id);
		if("通过".equals(checkStatus)){
			nm.setNewsStatus("正式稿");
		}else if("未通过".equals(checkStatus)){
			nm.setNewsStatus("草稿");
		}else {
			nm.setNewsStatus(newsStatus);
		}
		nm.setCheckStatus(checkStatus);
		Claims claims = JwtUtil.parseJWT(token);
        String json = claims.getSubject();
        LoginUser user = JSONObject.parseObject(json, LoginUser.class);
        //nm.setCheckerId(user.getUserId());
       // nm.setCheckerName(user.getUserName());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		Date today = sdf.parse(sdf.format(new Date()));
		nm.setCheckDate(today);
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			newsManageService.upNewsStatus(nm);
		     if(StringUtils.isNotBlank(cause)){
		    		NewsManage newsManage2=new NewsManage();
					//cover借用一下
					newsManage2.setCover(user.getUserId());
					NewsManage newsManage1=newsManageService.findCode(newsManage2);
					nm.setCheckerId(newsManage1.getCover());
		    	// nm.setCheckerId(user.getUserId());
		    	 newsManageService.insertCauseHistory(nm);
		    	 
		     }
			map.put("msg", "success");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", "fail");
		}
		return ResponseUtil.successResponse(map);
	}
	
	@RequestMapping(value = "/getWorkloadList", method=RequestMethod.POST)
	@PermessionLimit(limit=false)
	public CommonResponse getWorkloadList(
			String checkerName,

			@RequestParam(value = "pageNo", required = true,defaultValue="0") Integer pageNo ,
			@RequestParam(value = "pageSize", required = true,defaultValue="10") Integer pageSize,
			HttpServletRequest request,
			LoginUser user,
			HttpServletResponse response
			)throws IOException, ServletException {
		NewsManage newsManage = new NewsManage();
		newsManage.setCheckerName(checkerName);
		Page<NewsManage> page = newsManageService.getWorkloadList(new Page<NewsManage>(pageNo, pageSize), newsManage);
		return ResponseUtil.successResponse(page);
	}
	@RequestMapping(value = "/getHistoryList", method=RequestMethod.POST)
	@PermessionLimit(limit=false)
	public CommonResponse getHistoryList(
			String newsId,

			@RequestParam(value = "pageNo", required = true,defaultValue="0") Integer pageNo ,
			@RequestParam(value = "pageSize", required = true,defaultValue="10") Integer pageSize,
			HttpServletRequest request,
			LoginUser user,
			HttpServletResponse response
			)throws IOException, ServletException {
		NewsManage newsManage = new NewsManage();
		newsManage.setId(newsId);
		Page<NewsManage> page = newsManageService.getHistoryList(new Page<NewsManage>(pageNo, pageSize), newsManage);
		return ResponseUtil.successResponse(page);
	}
	
	
	 @RequestMapping(value = "/uploadFile")
	 @PermessionLimit(limit=false)
	 public CommonResponse uploadFile(HttpServletRequest request, @RequestHeader String token) throws JsonProcessingException {
	        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
	        MultipartFile multipartFile = multipartHttpServletRequest.getFile("upfile");
	        Claims claims = null;
	        try {
	            claims = JwtUtil.parseJWT(token);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        String json = claims.getSubject();
	        LoginUser user = JSONObject.parseObject(json, LoginUser.class);
	        String uploadPeople = user.getUserName();
	        Map<String, Object> map = new HashMap<String, Object>();
	        map=newsManageService.uploadFile(request, multipartFile, uploadPeople);
	        return  ResponseUtil.successResponse(map);
	 
	 }	
}
