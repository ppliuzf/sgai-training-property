package com.sgai.property.em.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgai.common.persistence.Page;
import com.sgai.common.service.CrudServiceExt;
import com.sgai.property.em.dao.EmReportContentDao;
import com.sgai.property.em.entity.EmReportContent;


/**
 *
    * @ClassName: EmReportContentService
    * @com.sgai.property.commonService.vo;(报案内容Service)
    * @author LiuYang
    * @date 2017年12月5日
    * @Company 首自信--智慧城市创新中心
 */
@Service
@Transactional
public class EmReportContentService extends CrudServiceExt<EmReportContentDao, EmReportContent> {

	public EmReportContent get(String id) {
		return super.get(id);
	}

	public List<EmReportContent> findList(EmReportContent emReportContent) {
		return super.findList(emReportContent);
	}

	public Page<EmReportContent> findPage(Page<EmReportContent> page, EmReportContent emReportContent) {
		return super.findPage(page, emReportContent);
	}

	@Transactional(readOnly = false)
	public void save(EmReportContent emReportContent) {
		super.save(emReportContent);
	}

	@Transactional(readOnly = false)
	public void delete(EmReportContent emReportContent) {
		super.delete(emReportContent);
	}

}
