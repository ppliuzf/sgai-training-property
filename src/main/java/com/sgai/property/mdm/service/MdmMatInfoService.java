package com.sgai.property.mdm.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgai.common.persistence.Page;
import com.sgai.common.service.CrudServiceExt;
import com.sgai.property.mdm.dao.MdmMatInfoDao;
import com.sgai.property.mdm.entity.MdmMatClass;
import com.sgai.property.mdm.entity.MdmMatInfo;

/**
 * 物料信息表Service
 * @author liushang
 * @version 2017-11-24
 */
@Service
@Transactional
public class MdmMatInfoService extends CrudServiceExt<MdmMatInfoDao, MdmMatInfo> {

	public MdmMatInfo get(String id) {
		return super.get(id);
	}

	public List<MdmMatInfo> findList(MdmMatInfo mdmMatInfo) {
		return super.findList(mdmMatInfo);
	}

	public Page<MdmMatInfo> findPage(Page<MdmMatInfo> page, MdmMatInfo mdmMatInfo) {
		return super.findPage(page, mdmMatInfo);
	}

	@Transactional(readOnly = false)
	public void save(MdmMatInfo mdmMatInfo) {
		super.save(mdmMatInfo);
	}

	@Transactional(readOnly = false)
	public void delete(MdmMatInfo mdmMatInfo) {
		super.delete(mdmMatInfo);
	}

	@Transactional(readOnly = false)
	public List<MdmMatInfo> batchDelete(List<MdmMatInfo> objs) {
		return super.batchDelete(objs);
	}

	public List<Map<String, String>> getMatTypeList(MdmMatClass mdmMatClass) {
		return super.dao.getMatTypeList(mdmMatClass);
	}

	public List<Map<String, String>> getSpecList() {
		return super.dao.getSpecList();
	}

	public List<Map<String, String>> getUnitList() {
		return super.dao.getUnitList();
	}

	public List<Map<String, String>> getMatUseList() {
		return super.dao.getMatUseList();
	}

	public Page<MdmMatInfo> getListMdmMatInfoByMatName(Page<MdmMatInfo> page, MdmMatInfo mdmMatInfo) {
		MdmMatInfo matInfo = new MdmMatInfo();
		matInfo.setMatUse(mdmMatInfo.getMatUse());
		Page<MdmMatInfo> pageMat = super.findPage(page, matInfo);

		if (null != mdmMatInfo.getMatName() && !"".equals(mdmMatInfo.getMatName())) {
			String[] matName = mdmMatInfo.getMatName().split("，");
			for(String name:matName) {
				if(name != null && !"".equals(name)) {
					for(MdmMatInfo info:pageMat.getList()) {
						if (name.equals(info.getMatName())) {
							pageMat.getList().remove(info);
							break;
						}
					}
				}
			}
		}
		return pageMat;
	}

	public List<MdmMatInfo> findRepeatList(MdmMatInfo mdmMatInfo) {
		mdmMatInfo.preGet();
		return super.dao.findRepeatList(mdmMatInfo);
	}
}
