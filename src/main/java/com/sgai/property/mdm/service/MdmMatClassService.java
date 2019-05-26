package com.sgai.property.mdm.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgai.common.persistence.Page;
import com.sgai.common.service.CrudServiceExt;
import com.sgai.property.mdm.dao.MdmMatClassDao;
import com.sgai.property.mdm.entity.MdmMatClass;

/**
 * 物料分类表Service
 * @author liushang
 * @version 2017-11-24
 */
@Service
@Transactional
public class MdmMatClassService extends CrudServiceExt<MdmMatClassDao, MdmMatClass> {

	public MdmMatClass get(String id) {
		return super.get(id);
	}

	public List<MdmMatClass> findList(MdmMatClass mdmMatClass) {
		return super.findList(mdmMatClass);
	}

	public Page<MdmMatClass> findPage(Page<MdmMatClass> page, MdmMatClass mdmMatClass) {
		page.setOrderBy("CREATED_DT");
		return super.findPage(page, mdmMatClass);
	}

	@Transactional(readOnly = false)
	public void save(MdmMatClass mdmMatClass) {
		super.save(mdmMatClass);
	}

	@Transactional(readOnly = false)
	public void delete(MdmMatClass mdmMatClass) {
		super.delete(mdmMatClass);
	}

	public List<MdmMatClass> findRepeatList(MdmMatClass mdmMatClass) {
		return super.dao.findRepeatList(mdmMatClass);
	}

}
