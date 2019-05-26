  
    /**    
    * @Title: KeyController.java  
    * @Package com.sgai.modules.key.web  
    * (用一句话描述该文件做什么)
    * @author XJ9001  
    * @date 2018年1月18日  
    * @Company 首自信--智慧城市创新中心
    * @version V1.0    
    */  
    
package com.sgai.property.wy.web;


    import com.sgai.common.persistence.Page;
    import com.sgai.common.web.BaseController;
    import com.sgai.modules.login.annotation.DataAuthority;
    import com.sgai.modules.login.annotation.PermessionLimit;
    import com.sgai.modules.login.jwt.bean.LoginUser;
    import com.sgai.modules.login.jwt.util.CommonResponse;
    import com.sgai.modules.login.jwt.util.ResponseUtil;
    import com.sgai.property.wy.entity.KeyBorrow;
    import com.sgai.property.wy.service.KeyBorrowService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.web.bind.annotation.*;

    import javax.servlet.ServletException;
    import javax.servlet.http.HttpServletRequest;
    import javax.servlet.http.HttpServletResponse;
    import java.io.IOException;
    import java.util.Date;
    import java.util.HashMap;
    import java.util.Map;


    /**  
 * @ClassName: KeyController  
 * (门卡/钥匙借用)
 * @author XJ9001  
 * @date 2018年1月18日  
 * @Company 首自信--智慧城市创新中心  
 */
@RestController
@RequestMapping("/keyBorrow")
public class KeyController extends BaseController {
	
	@Autowired
	private KeyBorrowService keyBorrowService;
	
	@RequestMapping(value = "/getList", method=RequestMethod.POST)
	public CommonResponse getListKey(
			Date borrowTimeFrom,
			Date borrowTimeTo,
			String watch,
			String borrower,
			String deptName,
			String goodsClassify,

			@RequestParam(value = "pageNo", required = true,defaultValue="0") Integer pageNo ,
			@RequestParam(value = "pageSize", required = true,defaultValue="10") Integer pageSize,
			HttpServletRequest request,
			HttpServletResponse response
			)throws IOException, ServletException {
		KeyBorrow keyBorrow = new KeyBorrow();
		keyBorrow.setBorrowTimeFrom(borrowTimeFrom);
		keyBorrow.setBorrowTimeTo(borrowTimeTo);
		keyBorrow.setWatch(watch);
		keyBorrow.setBorrower(borrower);
		keyBorrow.setDeptName(deptName);
		keyBorrow.setGoodsClassify(goodsClassify);
		Page<KeyBorrow> page = keyBorrowService.findPage(new Page<KeyBorrow>(pageNo, pageSize), keyBorrow);
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
	@RequestMapping(value = "/deleteKey", method=RequestMethod.POST)
	public CommonResponse deleteKey(
			String ids
			) throws ServletException, IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map = keyBorrowService.deleteKey(ids);
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
	 * @param GoodsBorrow
	 * @return :Map<String,Object>
	 * @since JDK 1.8
	 * @author zxj
	 * @throws IOException 
	 * @throws ServletException
	 */
	@RequestMapping(value = "/save", method=RequestMethod.POST)
	public CommonResponse save(KeyBorrow keyBorrow, LoginUser loginUser ) throws ServletException, IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			keyBorrow.setCreatedBy(loginUser.getUserId());
			keyBorrow.setComCode(loginUser.getComCode());
			keyBorrow.setDeptCode(loginUser.getDeptCode());
			keyBorrowService.save(keyBorrow);
			map.put("msg", "success");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", "fail");
		}
		return ResponseUtil.successResponse(map);
	}
	
	/**
	 * 
	 * getKeyBorrow
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
	@RequestMapping(value = "/findGodosById", method=RequestMethod.POST)
	public CommonResponse getKeyBorrow(
			String id
			) throws IOException, ServletException {
		KeyBorrow keyBorrow = keyBorrowService.get(id);
		return ResponseUtil.successResponse(keyBorrow);
	}
	
	
	
	/**
	 * 
	 * export
	 * 
	 * @throws IOException
	 * @since JDK 1.8
	 * @author zxj
	 * @throws IOException 
	 */
	@DataAuthority(tableAlias = "a")
	@RequestMapping(value = "/exportKeyBorrow",method = RequestMethod.GET)
	@PermessionLimit(limit = false)
	public void export(
            HttpServletResponse response, KeyBorrow keyBorrow
			)throws IOException{
		keyBorrowService.export(keyBorrow,response);
	}
	
	
}
