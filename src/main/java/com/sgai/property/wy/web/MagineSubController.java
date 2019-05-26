
package com.sgai.property.wy.web;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sgai.common.persistence.Page;
import com.sgai.common.web.BaseController;
import com.sgai.modules.login.annotation.PermessionLimit;
import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.modules.login.jwt.util.CommonResponse;
import com.sgai.modules.login.jwt.util.ResponseUtil;
import com.sgai.property.wy.entity.MagazineInfo;
import com.sgai.property.wy.entity.MagazineSub;
import com.sgai.property.wy.service.MagazineSubService;
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
 * @ClassName: magazineController
 * (杂志订阅)
 * @author cui
 * @date 2018年1月29日
 * @Company 首自信--智慧城市创新中心
 */
@RestController
@RequestMapping("/magazine")
public class MagineSubController extends BaseController {

	@Autowired
	private MagazineSubService magazineService;

	// 查询所有数据并分页
	@RequestMapping(value = "/magazineList", method = RequestMethod.POST)
	@PermessionLimit(limit = false)
	public CommonResponse getList(
            @RequestParam(value = "pageNo", required = true, defaultValue = "0") Integer pageNo,
            @RequestParam(value = "pageSize", required = true, defaultValue = "10") Integer pageSize,
            HttpServletRequest request, HttpServletResponse response,
            MagazineSub magazine, LoginUser user)
			throws IOException, ServletException {

		Page<MagazineSub> page = magazineService
				.findPage(new Page<MagazineSub>(pageNo, pageSize), magazine);
		return ResponseUtil.successResponse(page);
	}

	// 新增
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public CommonResponse save(MagazineSub magazine, LoginUser user)
			throws ServletException, IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			magazine.setArea(magazine.getRepairAddress());
			String[] ms = magazine.getMagazine().split(",");
			String[] ps = magazine.getPhrs().split(",");
			String[] adss = magazine.getRepairAddressCode().split("-");
 			for (int i=0; i<ms.length;i++ ) {
				magazine.setMagazine(ms[i]);
				magazine.setRepairAddressCode(adss[0]);
				List<MagazineSub> mlist =magazineService.findList(magazine);
				if(mlist.size()>0){  //验证是否领取同天同份报纸
					map.put("msg", "exist");
					map.put("mlist", mlist);
					return ResponseUtil.successResponse(map);
				}
			}
			MagazineSub nm;
				for (int i=0; i<ms.length;i++ ) {
				nm=new MagazineSub();
						nm.setId(magazine.getId());
						nm.setArea(magazine.getArea());
						nm.setRemarks(magazine.getRemarks());
						nm.setSignDept(magazine.getSignDept());
						nm.setSignDate(magazine.getSignDate());
						nm.setRepairAddressCode(magazine.getRepairAddressCode());
						nm.setSignName(user.getUserId());
						nm.setCreatedBy(user.getUserId());
						nm.setMagazine(ms[i]);
						nm.setPhr(Integer.valueOf(ps[i]));
						nm.setCheckNumber(ps[i]);
						nm.setCheckUser(user.getUserName());
				magazineService.save(nm);
			}
			map.put("msg", "success");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", "fail");
		}
		return ResponseUtil.successResponse(map);
	}

	/**
	 * 
	    * @Title: update  
	    * (修改)
	    * @throws
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public CommonResponse update(MagazineSub magazine, LoginUser user)
			throws ServletException, IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			magazine.setArea(magazine.getRepairAddress());
			magazineService.save(magazine);
			map.put("msg", "success");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", "fail");
		}
		return ResponseUtil.successResponse(map);
	}
	// 批量删除数据
	@RequestMapping("/delete")
	@Transactional
	public Map<String, Object> deletemagazine(MagazineSub magazine)
			throws ServletException, IOException {
		Map<String, Object> result = Maps.newHashMap();
		try {
			String ids = magazine.getId();
			String[] idArray = ids.split(",");
			List<String> idList = Lists.newArrayList();
			for (String id : idArray) {
				if (id != null && !id.equals("")) {
					idList.add(id);
				}
			}
			magazineService.batchDeleteMS(idList);
			result.put("state", true);
			result.put("msg", "删除成功!");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("state", false);
			result.put("msg", "删除失败!");
		}
		return result;
	}

	// 查询单条数据
	@RequestMapping(value = "/findMagazineById", method = RequestMethod.POST)
	public CommonResponse getMagazineInfo(String id)
			throws IOException, ServletException {
		MagazineSub magazine = magazineService.get(id);
		return ResponseUtil.successResponse(magazine);
	}
	// 查询所有报刊
	@RequestMapping(value = "/findAllM", method = RequestMethod.POST)
	public CommonResponse findAllM()
			throws IOException, ServletException {
		List<MagazineInfo> magazine = magazineService.findAllM();
		return ResponseUtil.successResponse(magazine);
	}
	
	//统计报刊并分页
	@RequestMapping(value = "/magazineCount", method = RequestMethod.POST)
	public Map<String,Object> magazineCount(
			@RequestParam(value = "pageNo", required = true,defaultValue="0") Integer pageNo ,
			@RequestParam(value = "pageSize", required = true,defaultValue="10") Integer pageSize,
			MagazineSub magazine)
			throws IOException, ServletException {
		Map<String,Object> map = magazineService
				.findPageCount(new Page<MagazineSub>(pageNo, pageSize), magazine);
		return  map;
	}

	// 导出报刊杂志数据到EXCEL
	@RequestMapping(value = "/magazineExport", method = RequestMethod.GET)
	@PermessionLimit(limit = false)
	public void export(MagazineSub magazine, HttpServletResponse response)
			throws IOException {
		magazineService.export(magazine, response);
	}
	
	// 导出报刊杂志统计数据到EXCEL
	@RequestMapping(value = "/magazineCountExport", method = RequestMethod.GET)
	@PermessionLimit(limit = false)
	public void magazineCountExport(MagazineSub magazine, HttpServletResponse response)
			throws IOException {
		magazineService.exportMCount(magazine, response);
	}
}
