package com.sgai.property.alm.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgai.common.persistence.Page;
import com.sgai.common.service.CrudServiceExt;
import com.sgai.property.alm.dao.AlmLevelInfoDao;
import com.sgai.property.alm.entity.AlmLevelInfo;

/**
 *
    * ClassName: AlmLevelInfoService
    * com.sgai.property.commonService.vo;(报警等级Service)
    * @author 王天尧
    * Date 2017年11月24日
    * Company 首自信--智慧城市创新中心
 */
@Service
@Transactional
public class AlmLevelInfoService extends CrudServiceExt<AlmLevelInfoDao, AlmLevelInfo> {

	public AlmLevelInfo get(String id) {
		return super.get(id);
	}

	public List<AlmLevelInfo> findList(AlmLevelInfo almLevelInfo) {
		return super.findList(almLevelInfo);
	}

	public Page<AlmLevelInfo> findPage(Page<AlmLevelInfo> page, AlmLevelInfo almLevelInfo) {
		return super.findPage(page, almLevelInfo);
	}

	@Transactional(readOnly = false)
	public void save(AlmLevelInfo almLevelInfo) {
		super.save(almLevelInfo);
	}

	@Transactional(readOnly = false)
	public void delete(AlmLevelInfo almLevelInfo) {
		super.delete(almLevelInfo);
	}
	/**
	 *
	 * saveAlmLevel:(保存报警等级).
	 * @param almLevelInfo
	 * @param result
	 * @return :Map<String,String>
	 * @since JDK 1.8
	 * @author 王天尧
	 */
	@Transactional(readOnly = false)
	public Map<String, String> saveAlmLevel(AlmLevelInfo almLevelInfo,Map<String,String> result) {

		AlmLevelInfo almLevelInfoNew = new AlmLevelInfo();
		almLevelInfoNew.setLevelCode(almLevelInfo.getLevelCode());
		List<AlmLevelInfo> findList = findList(almLevelInfoNew);
		//根据id是否为空判断是增加还是修改
		if(almLevelInfo.getId().equals("")) {
			//报警分类编码的唯一性判断
			if(findList.size()==0) {
				//新增
				save(almLevelInfo);
				result.put("msg", "success");
			}else {
				result.put("msg", "repeat");
			}
		}else {
			//修改
			save(almLevelInfo);
			result.put("msg", "success");
		}
		return result;
	}
}
