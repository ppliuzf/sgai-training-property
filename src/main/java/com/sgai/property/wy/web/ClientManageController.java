  
    /**    
    * @Title: ClientManageController.java  
    * @Package com.sgai.property.wy.web
    * (用一句话描述该文件做什么)
    * @author XJ9001  
    * @date 2018年1月31日  
    * @Company 首自信--智慧城市创新中心
    * @version V1.0    
    */  
    
package com.sgai.property.wy.web;

    import com.sgai.common.persistence.Page;
    import com.sgai.common.web.BaseController;
    import com.sgai.modules.login.annotation.PermessionLimit;
    import com.sgai.modules.login.jwt.util.CommonResponse;
    import com.sgai.modules.login.jwt.util.ResponseUtil;
    import com.sgai.property.wy.entity.ClientManage;
    import com.sgai.property.wy.service.ClientManageService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.web.bind.annotation.*;

    import javax.servlet.ServletException;
    import javax.servlet.http.HttpServletRequest;
    import javax.servlet.http.HttpServletResponse;
    import java.io.IOException;
    import java.util.HashMap;
    import java.util.Map;


    /**  
 * @ClassName: ClientManageController  
 * (这里用一句话描述这个类的作用)
 * @author XJ9001  
 * @date 2018年1月31日  
 * @Company 首自信--智慧城市创新中心  
 */
@RestController
@RequestMapping("/clientManage")
public class ClientManageController extends BaseController {
	
	@Autowired
	private ClientManageService clientManageService;
	
	
	@RequestMapping(value = "/getList", method=RequestMethod.POST)
	@PermessionLimit(limit=false)
	public CommonResponse getListGoods(
			String clientName,
			String clientTypeId,
			String clientRankId,

			@RequestParam(value = "pageNo", required = true,defaultValue="0") Integer pageNo ,
			@RequestParam(value = "pageSize", required = true,defaultValue="10") Integer pageSize,
			HttpServletRequest request,
			HttpServletResponse response
			)throws IOException, ServletException {
		ClientManage clientManage = new ClientManage();
		clientManage.setClientName(clientName);
		clientManage.setClientTypeId(clientTypeId);
		clientManage.setClientRankId(clientRankId);
		Page<ClientManage> page = clientManageService.findPage(new Page<ClientManage>(pageNo, pageSize), clientManage);
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
	@RequestMapping(value = "/deleteClient", method=RequestMethod.POST)
	public CommonResponse delete(
			String ids
			) throws ServletException, IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map = clientManageService.deleteClient(ids);
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
	 * @param RepairInformation
	 * @return :Map<String,Object>
	 * @since JDK 1.8
	 * @author zxj
	 * @throws IOException 
	 * @throws ServletException
	 */
	@RequestMapping(value = "/saveClient", method=RequestMethod.POST)
	public CommonResponse saveClient(ClientManage clientManage) throws ServletException, IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			clientManageService.save(clientManage);
			map.put("msg", "success");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", "fail");
		}
		return ResponseUtil.successResponse(map);
	}
}
