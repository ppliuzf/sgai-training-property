package com.sgai.property.wy.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.collect.Maps;
import com.sgai.common.persistence.Page;
import com.sgai.common.web.BaseController;
import com.sgai.modules.login.annotation.PermessionLimit;
import com.sgai.modules.login.jwt.util.CommonResponse;
import com.sgai.modules.login.jwt.util.ResponseUtil;
import com.sgai.property.wy.entity.MagazineInfo;
import com.sgai.property.wy.service.MagazineInfoService;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**  
* @ClassName: MagazineController  
* (访客记录    -- 管理服务入口)
* @author LXB  
* @date 2018年1月18日  
* @Company 首自信--智慧城市创新中心  
*/

@RestController
@RequestMapping("/magazineInfo")
public class MagazineInfoController extends BaseController {
	
	@Autowired
	private MagazineInfoService magazineInfoService;
	
	// 查询所有数据并分页
	@RequestMapping(value = "/magazineInfoList", method=RequestMethod.POST)
	@PermessionLimit(limit=false)
	public CommonResponse getListMagazineInfo(
            @RequestParam(value = "pageNo", required = true,defaultValue="0") Integer pageNo ,
            @RequestParam(value = "pageSize", required = true,defaultValue="10") Integer pageSize,
            HttpServletRequest request, HttpServletResponse response, MagazineInfo magazineInfo
			)throws IOException, ServletException {
		Page<MagazineInfo> page = magazineInfoService.findPage(new Page<MagazineInfo>(pageNo, pageSize), magazineInfo);
		return ResponseUtil.successResponse(page);
	}
	
	// 删除数据
	@RequestMapping("/deleteMagazineInfo")
    @Transactional
    public Map<String,Object> deleteMagazineInfo(MagazineInfo magazineInfo) throws ServletException, IOException {
		Map<String,Object> result = Maps.newHashMap();
		try {
			String ids = magazineInfo.getId();
			String[] idArray = ids.split(",");
			//逻辑删除
			for(String id : idArray) {
				if(id!=null && !id.equals("")) {
					magazineInfo = magazineInfoService.get(id);
					magazineInfo.setDisplay("N");
					magazineInfoService.save(magazineInfo);
				}
			}
			/*List<String> idList = Lists.newArrayList();
			for(String id : idArray) {
				if(id!=null && !id.equals("")) {
					idList.add(id);
				}
			}
			magazineInfoService.batchDeleteMagazineInfo(idList);*/		
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
	public CommonResponse save(MagazineInfo magazineInfo){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			magazineInfo.setCreatedDate("" + new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			magazineInfo.setDisplay("Y");
			magazineInfoService.save(magazineInfo);
			map.put("msg", "success");
		} catch (Exception e) {
			e.printStackTrace();	
			map.put("msg", "exist");
		}
		CommonResponse commonResponse = null;
		try {
			commonResponse = ResponseUtil.successResponse(map);
		} catch (JsonProcessingException e) {
			map.put("msg", "fail");
			e.printStackTrace();
		}
		return commonResponse;
		
	}
	
	// 查询单条数据
	@RequestMapping(value = "/findMagazineInfoById", method = RequestMethod.POST)
	public CommonResponse getMagazineInfo(String id) throws IOException, ServletException {
		MagazineInfo magazineInfo = magazineInfoService.get(id);
		return ResponseUtil.successResponse(magazineInfo);
	}
		
	// 导出数据到EXCEL
	@RequestMapping(value = "/magazineInfoExport", method=RequestMethod.GET)
    @PermessionLimit(limit = false)
	public void export(MagazineInfo magazineInfo, HttpServletResponse response)throws IOException{
		magazineInfoService.export(magazineInfo, response);
	}

}
