  
    /**    
    * @Title: CarManageController.java  
    * @Package com.sgai.property.wy.web
    * (用一句话描述该文件做什么)
    * @author XJ9001  
    * @date 2018年2月1日  
    * @Company 首自信--智慧城市创新中心
    * @version V1.0    
    */  
    
package com.sgai.property.wy.web;

    import com.sgai.common.persistence.Page;
    import com.sgai.common.web.BaseController;
    import com.sgai.modules.login.annotation.PermessionLimit;
    import com.sgai.modules.login.jwt.bean.LoginUser;
    import com.sgai.modules.login.jwt.util.CommonResponse;
    import com.sgai.modules.login.jwt.util.ResponseUtil;
    import com.sgai.property.wy.entity.CarManage;
    import com.sgai.property.wy.entity.Exceerror;
    import com.sgai.property.wy.service.CarManageService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.web.bind.annotation.*;
    import org.springframework.web.multipart.MultipartFile;
    import org.springframework.web.multipart.MultipartHttpServletRequest;

    import javax.servlet.ServletException;
    import javax.servlet.http.HttpServletRequest;
    import javax.servlet.http.HttpServletResponse;
    import java.io.IOException;
    import java.util.HashMap;
    import java.util.List;
    import java.util.Map;


    /**  
 * @ClassName: CarManageController  
 * (这里用一句话描述这个类的作用)
 * @author XJ9001  
 * @date 2018年2月1日  
 * @Company 首自信--智慧城市创新中心  
 */
@RestController
@RequestMapping("/carManage")
public class CarManageController extends BaseController {

	@Autowired
	private CarManageService carManageService;
	
	@RequestMapping(value = "/getList", method=RequestMethod.POST)
	@PermessionLimit(limit=false)
	public CommonResponse getListCars(
			String memberId,
			String memberName,
			String carColor,
			String carNumber,
			String carModel,

			@RequestParam(value = "pageNo", required = true,defaultValue="0") Integer pageNo ,
			@RequestParam(value = "pageSize", required = true,defaultValue="10") Integer pageSize,
			HttpServletRequest request,
			HttpServletResponse response
			)throws IOException, ServletException {
		CarManage carManage = new CarManage();
		carManage.setMemberId(memberId);
		carManage.setMemberName(memberName);
		carManage.setCarColor(carColor);
		carManage.setCarNumber(carNumber);
		carManage.setCarModel(carModel);
		Page<CarManage> page = carManageService.findPage(new Page<CarManage>(pageNo, pageSize), carManage);
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
	@RequestMapping(value = "/deleteCars", method=RequestMethod.POST)
	public CommonResponse delete(
			String ids
			) throws ServletException, IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map = carManageService.deleteCars(ids);
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
	 * @param carManage
	 * @return :Map<String,Object>
	 * @since JDK 1.8
	 * @author zxj
	 * @throws IOException 
	 * @throws ServletException
	 */
	@RequestMapping(value = "/save", method=RequestMethod.POST)
	public CommonResponse save(CarManage carManage) throws ServletException, IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			carManageService.save(carManage);
			map.put("msg", "success");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", "fail");
		}
		return ResponseUtil.successResponse(map);
	}
	
	/**
	 * 
	 * getCars
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
	@RequestMapping(value = "/findCarsById", method=RequestMethod.POST)
	public CommonResponse getCars(
			String id
			) throws IOException, ServletException {
		CarManage c = new CarManage();
		c.setId(id);
		CarManage carManage = carManageService.get(c);
		return ResponseUtil.successResponse(carManage);
	}
	
	/**
	 * 
	 * getCars
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
	@RequestMapping(value = "/getCheckCarByNumber", method=RequestMethod.POST)
	public CommonResponse getCheckCarByNumber(
			String carNumber
			) throws IOException, ServletException {
		Map<String, Object> map = new HashMap<String, Object>();
		CarManage c = new CarManage();
		c.setCarNumber(carNumber);
		CarManage carManage = carManageService.get(c);
		if(carManage != null){
			map.put("msg", "success");
		}else{
			map.put("msg", "fail");
		}
		return ResponseUtil.successResponse(map);
	}
	
	
	/**
	 * 导入表格
	 * @param file
	 * @param redirectAttributes
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "importBuildInfoExcel")
	
	@ResponseBody
	public CommonResponse importBuildInfoExcel(HttpServletRequest request, LoginUser user) throws Exception {
		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
        MultipartFile multipartFile = multipartHttpServletRequest.getFile("upfile");
		Map<String, Object> map = new HashMap<String, Object>();
		List<Exceerror> errors =this.carManageService.uploadExcelFile(multipartFile,user.getUserName());
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
