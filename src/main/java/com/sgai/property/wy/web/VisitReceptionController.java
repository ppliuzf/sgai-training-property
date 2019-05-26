package com.sgai.property.wy.web;

import com.google.common.collect.Maps;
import com.sgai.common.persistence.Page;
import com.sgai.common.web.BaseController;
import com.sgai.modules.login.annotation.PermessionLimit;
import com.sgai.modules.login.jwt.util.CommonResponse;
import com.sgai.modules.login.jwt.util.ResponseUtil;
import com.sgai.property.wy.entity.VisitReception;
import com.sgai.property.wy.service.VisitReceptionService;
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
* @ClassName: VisitReceptionController  
* (访客记录    -- 管理服务入口)
* @author LiuXiaobing  
* @date 2018年2月1日  
* @Company 首自信--智慧城市创新中心  
*/

@RestController
@RequestMapping("/visitReception")
public class VisitReceptionController extends BaseController {
	
	@Autowired
	private VisitReceptionService visitReceptionService;
	
	// 查询所有数据并分页
	@RequestMapping(value = "/visitReceptionList", method=RequestMethod.POST)
	@PermessionLimit(limit=false)
	public CommonResponse getListVisitReception(
            @RequestParam(value = "pageNo", required = true,defaultValue="0") Integer pageNo ,
            @RequestParam(value = "pageSize", required = true,defaultValue="10") Integer pageSize,
            HttpServletRequest request, HttpServletResponse response, VisitReception visitReception
			)throws IOException, ServletException {
		if(visitReception.getAppVisitTime()!= null){
            Date time = visitReception.getAppVisitTime();
            Calendar c = Calendar.getInstance();
            c.setTime(time);
            c.set(Calendar.DAY_OF_YEAR,c.get(Calendar.DAY_OF_YEAR)+1);
            visitReception.setAppVisitTime(c.getTime());
        }
		Page<VisitReception> page = visitReceptionService.findPage(new Page<VisitReception>(pageNo, pageSize), visitReception);
		return ResponseUtil.successResponse(page);
	}
	
	// 批量删除数据
	@RequestMapping("/deleteVisitReception")
    @Transactional
    public Map<String,Object> deleteVisitReception(VisitReception visitReception) throws ServletException, IOException {
		Map<String,Object> result = Maps.newHashMap();
		try {
			String ids = visitReception.getId();
			String[] idArray = ids.split(",");
			// 逻辑删除
			for(String id : idArray) {
				if(id!=null && !id.equals("")) {
					visitReception = visitReceptionService.get(id);
					visitReception.setDisplay("N");
					visitReceptionService.save(visitReception);
				}
			}
			/*List<String> idList = Lists.newArrayList();
			for(String id : idArray) {
				if(id!=null && !id.equals("")) {
					idList.add(id);
				}
			}
			visitReceptionService.batchDeleteVisitReception(idList);*/		
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
	public CommonResponse save(VisitReception visitReception) throws ServletException, IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			visitReception.setDisplay("Y");
			visitReceptionService.save(visitReception);
			map.put("msg", "success");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", "fail");
		}
		return ResponseUtil.successResponse(map);
	}
	
	// 查询单条数据
	@RequestMapping(value = "/findVisitReceptionById", method = RequestMethod.POST)
	public CommonResponse getVisitReception(String id) throws IOException, ServletException {
		VisitReception visitReception = visitReceptionService.get(id);
		return ResponseUtil.successResponse(visitReception);
	}
		
	// 导出数据到EXCEL
	@RequestMapping(value = "/visitReceptionExport", method=RequestMethod.GET)
    @PermessionLimit(limit = false)
	public void export(VisitReception visitReception, HttpServletResponse response)throws IOException{
		visitReceptionService.export(visitReception, response);
	}

}
