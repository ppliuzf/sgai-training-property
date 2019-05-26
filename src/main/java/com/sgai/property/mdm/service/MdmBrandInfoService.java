package com.sgai.property.mdm.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sgai.common.persistence.Page;
import com.sgai.common.service.CrudServiceExt;
import com.sgai.property.ctl.service.CtlComRuleService;
import com.sgai.property.ctl.service.DeleteRulesUtils;
import com.sgai.property.mdm.dao.MdmBrandInfoDao;
import com.sgai.property.mdm.entity.MdmBrandInfo;

/**
 *
    * ClassName: MdmBrandInfoService
    * com.sgai.property.commonService.vo;(品牌信息业务层)
    * @author yangyz
    * Date 2017年11月24日
    * Company 首自信--智慧城市创新中心
 */
@Service
@Transactional
public class MdmBrandInfoService extends CrudServiceExt<MdmBrandInfoDao, MdmBrandInfo> {

	@Autowired
	private DeleteRulesUtils deleteRulesUtils;
	@Autowired
	private CtlComRuleService ctlComRuleService;

	public MdmBrandInfo get(String id) {
		return super.get(id);
	}

	public List<MdmBrandInfo> findList(MdmBrandInfo mdmBrandInfo) {
		return super.findList(mdmBrandInfo);
	}

	public Page<MdmBrandInfo> findPage(Page<MdmBrandInfo> page, MdmBrandInfo mdmBrandInfo) {
		return super.findPage(page, mdmBrandInfo);
	}

	@Transactional(readOnly = false)
	public void save(MdmBrandInfo mdmBrandInfo) {
		super.save(mdmBrandInfo);
	}

	@Transactional(readOnly = false)
	public void delete(MdmBrandInfo mdmBrandInfo) {
		super.delete(mdmBrandInfo);
	}

	/**
	 *
	 * saveDeviceClass:(新增或修改保存品牌信息).
	 * @param mdmBrandInfo
	 * @param classCode
	 * @return :Map<String,Object>
	 * @since JDK 1.8
	 * @author yangyz
	 */
	public Map<String, Object> saveBrandInfo(MdmBrandInfo mdmBrandInfo){
		Map<String, Object> map = new HashMap<String, Object>();
		if (mdmBrandInfo.getId()!=null && !"".equals(mdmBrandInfo.getId())) {
			super.save(mdmBrandInfo);
			map.put("msg", "success");
		}else {
			MdmBrandInfo info = new MdmBrandInfo();
			String code = ctlComRuleService.getNext("DEVICE-BRAND");
			info.setBrandCode(code);
			List<MdmBrandInfo> list = super.findList(info);
			if (list.size() > 0) {
				map.put("msg", "haveData");
			}else {
				mdmBrandInfo.setBrandCode(code);
				super.save(mdmBrandInfo);
				map.put("msg", "success");
			}
		}

		return map;
	}

	/**
	 *
	 * deleteBrandInfo:(批量删除品牌信息).
	 * @param ids
	 * @return :Map<String,Object>
	 * @since JDK 1.8
	 * @author yangyz
	 */
	public Map<String, Object> deleteBrandInfo(String ids){
		Map<String, Object> map = new HashMap<String, Object>();
		String idss[]=ids.split(",");
		List<MdmBrandInfo> list = new ArrayList<MdmBrandInfo>();
		List<String> newList = new ArrayList<String>();
		for(String id:idss){
			if(id!=null&&!id.equals("")){
				newList.add(id);
				MdmBrandInfo info=super.get(id);
				list.add(info);
			}
		}
		Map<String,String> resultMap = deleteRulesUtils.checkBeforeDelete(MdmBrandInfo.class,newList);
		if("true".equals(resultMap.get("value"))) {
			List<MdmBrandInfo> finalList = batchDelete(list);
			if (finalList.size() > 0) {
				map.put("msg", "删除成功!");
			}else {
				map.put("msg", "删除失败！");
			}
			map.put("result", "success");
		}else {
			map.put("msg", resultMap.get("description"));
			map.put("result", "fail");
		}
		return map;
	}

}
