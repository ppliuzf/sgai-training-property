package com.sgai.property.wy.web;

import com.google.common.collect.Maps;
import com.sgai.common.persistence.Page;
import com.sgai.common.web.BaseController;
import com.sgai.modules.login.annotation.PermessionLimit;
import com.sgai.modules.login.jwt.util.CommonResponse;
import com.sgai.modules.login.jwt.util.ResponseUtil;
import com.sgai.property.wy.entity.LostAndFound;
import com.sgai.property.wy.service.LostAndFoundService;
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
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**  
* @ClassName: lostAndFoundController  
* (访客记录    -- 管理服务入口)
* @author LiuXiaobing  
* @date 2018年1月18日  
* @Company 首自信--智慧城市创新中心  
*/

@RestController
@RequestMapping("/lostAndFound")
public class LostAndFoundController extends BaseController {
	
	@Autowired
	private LostAndFoundService lostAndFoundService;
	
	// 查询所有数据并分页
	@RequestMapping(value = "/lostAndFoundList", method=RequestMethod.POST)
	@PermessionLimit(limit=false)
	public CommonResponse getListLostAndFound(
            @RequestParam(value = "pageNo", required = true,defaultValue="0") Integer pageNo ,
            @RequestParam(value = "pageSize", required = true,defaultValue="10") Integer pageSize,
            HttpServletRequest request, HttpServletResponse response, LostAndFound lostAndFound )throws IOException, ServletException {
		if(lostAndFound.getDrawTime()!=null){
            Date time = lostAndFound.getDrawTime();
            Calendar c = Calendar.getInstance();
            c.setTime(time);
            c.set(Calendar.DAY_OF_YEAR,c.get(Calendar.DAY_OF_YEAR)+1);
            lostAndFound.setDrawTime(c.getTime());
        }
		Page<LostAndFound> page = lostAndFoundService.findPage(new Page<LostAndFound>(pageNo, pageSize), lostAndFound);
		return ResponseUtil.successResponse(page);
	}
	
	// 批量删除数据
	@RequestMapping("/deleteLostAndFound")
    @Transactional
    public Map<String,Object> deleteLostAndFound(LostAndFound lostAndFound) throws ServletException, IOException {
		Map<String,Object> result = Maps.newHashMap();
		try {
			String ids = lostAndFound.getId();
			String[] idArray = ids.split(",");
			//逻辑删除
			for(String id : idArray) {
				if(id!=null && !id.equals("")) {
					lostAndFound = lostAndFoundService.get(id);
					lostAndFound.setDisplay("N");
					lostAndFoundService.save(lostAndFound);
				}
			}
			/*List<String> idList = Lists.newArrayList();
			for(String id : idArray) {
				if(id!=null && !id.equals("")) {
					idList.add(id);
				}
			}
			lostAndFoundService.batchDeleteLostAndFound(idList);*/		
			result.put("state", true);
			result.put("msg", "删除成功!");
		}catch(Exception e) {
			e.printStackTrace();
			result.put("state", false);
			result.put("msg", "删除失败!");
		}
		return result;
	}
	
	// 修改/添加数据
	@RequestMapping(value = "/save", method=RequestMethod.POST)
	public CommonResponse save(LostAndFound lostAndFound) throws ServletException, IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			lostAndFound.setDisplay("Y");
			lostAndFoundService.save(lostAndFound);
			map.put("msg", "success");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", "fail");
		}
		return ResponseUtil.successResponse(map);
	}
	
	// 查询单条数据
	@RequestMapping(value = "/findLostAndFoundById", method = RequestMethod.POST)
	public CommonResponse getLostAndFound(String id) throws IOException, ServletException {
		LostAndFound lostAndFound = lostAndFoundService.get(id);
		return ResponseUtil.successResponse(lostAndFound);
	}
		
	// 导出数据到EXCEL
	@RequestMapping(value = "/lostAndFoundExport", method=RequestMethod.GET)
    @PermessionLimit(limit = false)
	public void export(LostAndFound lostAndFound, HttpServletResponse response)throws IOException{
		lostAndFoundService.export(lostAndFound, response);
	}

}
