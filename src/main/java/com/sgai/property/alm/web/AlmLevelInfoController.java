package com.sgai.property.alm.web;

import com.sgai.common.persistence.Page;
import com.sgai.common.utils.StringUtils;
import com.sgai.common.web.BaseController;
import com.sgai.property.alm.entity.AlmLevelInfo;
import com.sgai.property.alm.service.AlmLevelInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
    * ClassName: AlmLevelInfoController  
    * com.sgai.property.commonService.vo;(报警等级Controller)
    * @author 王天尧  
    * Date 2017年11月24日  
    * Company 首自信--智慧城市创新中心
 */
@RestController
@RequestMapping(value = "${adminPath}/alm/almLevelInfo")
public class AlmLevelInfoController extends BaseController {

	@Autowired
	private AlmLevelInfoService almLevelInfoService;
	
	@ModelAttribute
	public AlmLevelInfo get(@RequestParam(required=false) String id) {
		AlmLevelInfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = almLevelInfoService.get(id);
		}
		if (entity == null){
			entity = new AlmLevelInfo();
		}
		return entity;
	}
	/**
	 * 
	 * list:(获取报警等级列表).
	 * @return :Page<AlmAlermClass>
	 * @since JDK 1.8
	 * @author 王天尧
	 */
	@RequestMapping(value="/getList")
	public Page<AlmLevelInfo> list(AlmLevelInfo almLevelInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		return almLevelInfoService.findPage(new Page<AlmLevelInfo>(request, response), almLevelInfo);
	}
	/**
	 * 
	 * getAllList:(获取所有的报警等级).
	 * @return :List<AlmLevelInfo>
	 * @since JDK 1.8
	 * @author 王天尧
	 */
	@RequestMapping(value="/getAllList")
	public List<AlmLevelInfo> getAllList(AlmLevelInfo almLevelInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		return almLevelInfoService.findList(almLevelInfo);
	}
	/**
	 * 
	 * save:(保存报警等级，包括新增和修改).
	 * @return :String
	 * @since JDK 1.8
	 * @author 王天尧
	 */
	@RequestMapping(value = "save")
	public Map<String, String> save(AlmLevelInfo almLevelInfo, Model model, RedirectAttributes redirectAttributes) {
		Map<String,String> result = new HashMap<String,String>();
		try {
			result=almLevelInfoService.saveAlmLevel(almLevelInfo, result);
		} catch (Exception e) {

			e.printStackTrace();
			result.put("msg", "faild");
		}
		return result;
		
	}
	/**
	 * 
	 * delete:(删除报警等级).
	 * @param ids 报警等级id集合（字符串拼而成）
	 * @return :Map<String,String>
	 * @since JDK 1.8
	 * @author 王天尧
	 */
	@RequestMapping(value = "delete")
	public Map<String, String> delete(AlmLevelInfo almLevelInfo,String ids, RedirectAttributes redirectAttributes) {
		Map<String,String> result = new HashMap<String,String>();
		String idArr[]=ids.split(",");
		try {
			for(String id:idArr){
				if(StringUtils.isNotEmpty(id)){
					almLevelInfo.setId(id);
					almLevelInfoService.delete(almLevelInfo);
				}
			}
			result.put("msg", "success");
		} catch (Exception e) {

			e.printStackTrace();
			result.put("msg", "faild");
		}
		return result;
		
	}

}