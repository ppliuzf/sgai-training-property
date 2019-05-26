package com.sgai.property.alm.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgai.common.persistence.Page;
import com.sgai.common.service.CrudServiceExt;
import com.sgai.property.alm.dao.AlmDeviceLevelRelationDao;
import com.sgai.property.alm.entity.AlmDeviceLevelRelation;
import com.sgai.property.mdm.entity.MdmDeviceClass;
import com.sgai.property.mdm.service.MdmDeviceClassService;

/**
 *
 * ClassName: AlmDeviceLevelRelationService com.sgai.property.commonService.vo;(设备与报警等级关系Service)
 *
 * @author 王天尧 Date 2017年11月24日 Company 首自信--智慧城市创新中心
 */
@Service
@Transactional
public class AlmDeviceLevelRelationService extends CrudServiceExt<AlmDeviceLevelRelationDao, AlmDeviceLevelRelation> {

	@Autowired
	private MdmDeviceClassService mdmDeviceClassService;

	public AlmDeviceLevelRelation get(String id) {
		return super.get(id);
	}

	public List<AlmDeviceLevelRelation> findList(AlmDeviceLevelRelation almDeviceLevelRelation) {
		return super.findList(almDeviceLevelRelation);
	}

	public Page<AlmDeviceLevelRelation> findPage(Page<AlmDeviceLevelRelation> page,
			AlmDeviceLevelRelation almDeviceLevelRelation) {
		return super.findPage(page, almDeviceLevelRelation);
	}

	@Transactional(readOnly = false)
	public void save(AlmDeviceLevelRelation almDeviceLevelRelation) {
		super.save(almDeviceLevelRelation);
	}

	@Transactional(readOnly = false)
	public void delete(AlmDeviceLevelRelation almDeviceLevelRelation) {
		super.delete(almDeviceLevelRelation);
	}

	/**
	 *
	 * saveAlmDeviceLevelRelation:(保存设备报警等级关系).
	 *
	 * @param almDeviceLevelRelation
	 * @param result
	 * @return :Map<String,String>
	 * @since JDK 1.8
	 * @author 王天尧
	 */
	@Transactional(readOnly = false)
	public Map<String, String> saveAlmDeviceLevelRelation(AlmDeviceLevelRelation almDeviceLevelRelation,
			Map<String, String> result, String[] ids) {

		// 根据id是否为空判断是增加还是修改
		if (almDeviceLevelRelation.getId().equals("")) {
			// 新增
			if (check(almDeviceLevelRelation, ids)) {
				for (String id : ids) {
					// 将之前填入的*跳过（*是区分编辑还是新增用的）
					if (id.equals("*")) {
						continue;
					} else {
						AlmDeviceLevelRelation almDeviceLevelRelation2 = new AlmDeviceLevelRelation();
						MdmDeviceClass mdmDeviceClass = mdmDeviceClassService.get(id);
						almDeviceLevelRelation2.setLevelCode(almDeviceLevelRelation.getLevelCode());
						almDeviceLevelRelation2.setLevelName(almDeviceLevelRelation.getLevelName());
						almDeviceLevelRelation2.setAlermTypeCode(almDeviceLevelRelation.getAlermTypeCode());
						almDeviceLevelRelation2.setAlermTypeName(almDeviceLevelRelation.getAlermTypeName());
						almDeviceLevelRelation2.setClassCode(mdmDeviceClass.getClassCode());
						almDeviceLevelRelation2.setClassName(mdmDeviceClass.getClassName());
						almDeviceLevelRelation2.setProfCode(mdmDeviceClass.getProfCode());
						almDeviceLevelRelation2.setProfName(mdmDeviceClass.getProfName());
						almDeviceLevelRelation2.setEnabledFlag("Y");
						almDeviceLevelRelation2.setRemarks(almDeviceLevelRelation.getRemarks());
						save(almDeviceLevelRelation2);
					}
					result.put("msg", "success");
				}
			} else {
				result.put("msg", "repeat");
			}
		} else {
			// 修改
			almDeviceLevelRelation.setEnabledFlag("Y");
			AlmDeviceLevelRelation almDeviceLevelRelation2 = dao.get(almDeviceLevelRelation);
			almDeviceLevelRelation2.setAlermTypeCode(almDeviceLevelRelation.getAlermTypeCode());
			almDeviceLevelRelation2.setAlermTypeName(almDeviceLevelRelation.getAlermTypeName());
			almDeviceLevelRelation2.setLevelCode(almDeviceLevelRelation.getLevelCode());
			almDeviceLevelRelation2.setLevelName(almDeviceLevelRelation.getLevelName());
			almDeviceLevelRelation2.setRemarks(almDeviceLevelRelation.getRemarks());
			save(almDeviceLevelRelation2);
			result.put("msg", "success");
		}
		return result;
	}

	/**
	 *
	 * check:(检验唯一性的).
	 *
	 * @param almDeviceLevelRelation
	 * @param ids
	 * @return :boolean
	 * @since JDK 1.8
	 * @author 王天尧
	 */
	public boolean check(AlmDeviceLevelRelation almDeviceLevelRelation, String[] ids) {
		boolean flag = true;
		for (String id : ids) {
			// 将之前填入的*跳过（*是区分编辑还是新增用的）
			if (id.equals("*")) {
				continue;
			} else {
				MdmDeviceClass mdmDeviceClass = mdmDeviceClassService.get(id);
				AlmDeviceLevelRelation almDeviceLevelRelationNew = new AlmDeviceLevelRelation();
				almDeviceLevelRelationNew.setAlermTypeCode(almDeviceLevelRelation.getAlermTypeCode());
				almDeviceLevelRelationNew.setLevelCode(almDeviceLevelRelation.getLevelCode());
				almDeviceLevelRelationNew.setClassCode(mdmDeviceClass.getClassCode());
				almDeviceLevelRelationNew.setProfCode(mdmDeviceClass.getProfCode());
				// 判断关联关系唯一性
				List<AlmDeviceLevelRelation> findList = findList(almDeviceLevelRelationNew);
				if (findList.size() != 0) {
					flag = false;
					break;
				}

			}
		}
		return flag;

	}
}
