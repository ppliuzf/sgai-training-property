  
    /**    
    * @Title: MemberController.java  
    * @Package com.sgai.property.wy.web
    * (用一句话描述该文件做什么)
    * @author XJ9001  
    * @date 2018年1月26日  
    * @Company 首自信--智慧城市创新中心
    * @version V1.0    
    */  
    
package com.sgai.property.wy.web;

    import com.sgai.common.persistence.Page;
    import com.sgai.common.web.BaseController;
    import com.sgai.modules.login.annotation.PermessionLimit;
    import com.sgai.modules.login.jwt.util.CommonResponse;
    import com.sgai.modules.login.jwt.util.ResponseUtil;
    import com.sgai.property.wy.entity.Exceerror;
    import com.sgai.property.wy.entity.Member;
    import com.sgai.property.wy.service.MemberService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.web.bind.annotation.*;
    import org.springframework.web.multipart.MultipartFile;
    import org.springframework.web.multipart.MultipartHttpServletRequest;

    import javax.servlet.ServletException;
    import javax.servlet.http.HttpServletRequest;
    import javax.servlet.http.HttpServletResponse;
    import java.io.IOException;
    import java.util.*;


    /**  
 * @ClassName: MemberController  
 * (这里用一句话描述这个类的作用)
 * @author XJ9001  
 * @date 2018年1月26日  
 * @Company 首自信--智慧城市创新中心  
 */
@RestController
@RequestMapping("/member")
public class MemberController extends BaseController {

	@Autowired
	private MemberService memberService;
	
	@RequestMapping(value = "/getList", method=RequestMethod.POST)
	@PermessionLimit(limit=false)
	public CommonResponse getListMember(
			Date enrollTime,
			String chineseName,

			@RequestParam(value = "pageNo", required = true,defaultValue="0") Integer pageNo ,
			@RequestParam(value = "pageSize", required = true,defaultValue="10") Integer pageSize,
			HttpServletRequest request,
			HttpServletResponse response
			)throws IOException, ServletException {
		Member member = new Member();
		member.setChineseName(chineseName);
		member.setEnrollTime(enrollTime);
		Page<Member> page = memberService.findPage(new Page<Member>(pageNo, pageSize), member);
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
	@RequestMapping(value = "/deleteMember", method=RequestMethod.POST)
	public CommonResponse delete(
			String ids
			) throws ServletException, IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map = memberService.deleteMember(ids);
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
	 * @param Member
	 * @return :Map<String,Object>
	 * @since JDK 1.8
	 * @author zxj
	 * @throws IOException 
	 * @throws ServletException
	 */
	@RequestMapping(value = "/save", method=RequestMethod.POST)
	public CommonResponse save(Member member ) throws ServletException, IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			member.setMemberCode(creatUUID());
			member.setType("1");
			memberService.save(member);
			map.put("msg", "success");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", "fail");
		}
		return ResponseUtil.successResponse(map);
	}
	
	/**
	 * 
	 * getMember
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
	@RequestMapping(value = "/findMemberById", method=RequestMethod.POST)
	public CommonResponse getMember(
			String id
			) throws IOException, ServletException {
		Member m = new Member();
		m.setId(id);
		Member member = memberService.get(m);
		return ResponseUtil.successResponse(member);
	}
	
	/**
	 * 
	 * getMember
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
	@RequestMapping(value = "/getPhoneNumber", method=RequestMethod.POST)
	public CommonResponse getPhoneNumber(
			String phoneNumber,
			HttpServletResponse response
			) throws IOException, ServletException {
		Map<String, Object> map = new HashMap<String, Object>();
		Member m = new Member();
		m.setPhoneNumber(phoneNumber);
		Member member = memberService.get(m);
		if(member != null){
			map.put("msg", "success");
		}else{
			map.put("msg", "fail");
		}
		return ResponseUtil.successResponse(map);
	}
	
	/**
	 * 
	 * getMember
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
	@RequestMapping(value = "/getChineseName", method=RequestMethod.POST)
	public CommonResponse getChineseName(
			String chineseName,
			HttpServletResponse response
			) throws IOException, ServletException {
		Map<String, Object> map = new HashMap<String, Object>();
		Member m = new Member();
		m.setChineseName(chineseName);
		Member member = memberService.get(m);
		if(member != null){
			map.put("msg", "success");
		}else{
			map.put("msg", "fail");
		}
		return ResponseUtil.successResponse(map);
	}
	
	private String creatUUID() {  
        return UUID.randomUUID().toString().replace("-", "");  
    }
	/**
	 * 导入表格
	 * @param file
	 * @param redirectAttributes
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "importBuildInfoExcel")
	@PermessionLimit(limit=false)
	@ResponseBody
	public CommonResponse importBuildInfoExcel(HttpServletRequest request) throws Exception {
		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
        MultipartFile multipartFile = multipartHttpServletRequest.getFile("upfile");
		Map<String, Object> map = new HashMap<String, Object>();
		List<Exceerror> errors =this.memberService.uploadExcelFile(multipartFile);
		if(errors==null){
			map.put("msg", "success");
		}
		if(errors!=null){
			StringBuilder stringBuilder = new StringBuilder();
			map.put("msg", "fail");
			for(Exceerror exceerror:errors){
				if("格式不正确".equals(exceerror.getColumn())){
					stringBuilder.append("文件格式不正确");
				}else if("文件没有数据".equals(exceerror.getColumn())){
					stringBuilder.append("文件没有数据");
				}else{
					stringBuilder.append("第").append(exceerror.getRow()).append("行").append(exceerror.getColumn()).append(exceerror.getError()).append("；");
				}
				}
			String a=stringBuilder.toString();
			map.put("a", a);
		}
		return ResponseUtil.successResponse(map);
	}
}
