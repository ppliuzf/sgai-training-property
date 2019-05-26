  
    /**    
    * @Title: GoodsBorrowController.java  
    * @Package com.sgai.modules.goods.web  
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
    import com.sgai.property.wy.entity.GoodsBorrow;
    import com.sgai.property.wy.service.GoodsBorrowService;
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
 * @ClassName: GoodsBorrowController  
 * (这里用一句话描述这个类的作用)
 * @author XJ9001  
 * @date 2018年1月18日  
 * @Company 首自信--智慧城市创新中心  
 */
@RestController
@RequestMapping("/goodsBorrow")
public class GoodsBorrowController extends BaseController {

	@Autowired
	private GoodsBorrowService goodsBorrowService;
	
	@RequestMapping(value = "/getList", method=RequestMethod.POST)
	@PermessionLimit(limit=false)
	public CommonResponse getListGoods(
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
		GoodsBorrow goodsBorrow = new GoodsBorrow();
		goodsBorrow.setBorrowTimeFrom(borrowTimeFrom);
		goodsBorrow.setBorrowTimeTo(borrowTimeTo);
		goodsBorrow.setWatch(watch);
		goodsBorrow.setBorrower(borrower);
		goodsBorrow.setDeptName(deptName);
		goodsBorrow.setGoodsClassify(goodsClassify);
		Page<GoodsBorrow> page = goodsBorrowService.findPage(new Page<GoodsBorrow>(pageNo, pageSize), goodsBorrow);
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
	@RequestMapping(value = "/deleteGoods", method=RequestMethod.POST)
	public CommonResponse delete(
			String ids
			) throws ServletException, IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map = goodsBorrowService.deleteGoods(ids);
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
	public CommonResponse save(GoodsBorrow goodsBorrow , LoginUser loginUser ) throws ServletException, IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			goodsBorrow.setCreatedBy(loginUser.getUserId());
			goodsBorrow.setComCode(loginUser.getComCode());
			goodsBorrow.setDeptCode(loginUser.getDeptCode());
			goodsBorrowService.save(goodsBorrow);
			map.put("msg", "success");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", "fail");
		}
		return ResponseUtil.successResponse(map);
	}
	
	/**
	 * 
	 * getGoodsBorrow
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
	public CommonResponse getGoodsBorrow(
			String id
			) throws IOException, ServletException {
		GoodsBorrow goodsBorrow = goodsBorrowService.get(id);
		return ResponseUtil.successResponse(goodsBorrow);
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
	@RequestMapping(value = "/exportGoodsBorrow",method = RequestMethod.GET)
	@PermessionLimit(limit = false)
	public void export(
            HttpServletResponse response, GoodsBorrow goodsBorrow
			)throws IOException{
		goodsBorrowService.export(goodsBorrow,response);
	}
}
