package com.sgai.property.ruag.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgai.common.persistence.Page;
import com.sgai.common.service.CrudServiceExt;
import com.sgai.property.ruag.dao.RuagCalendarWorkModelDao;
import com.sgai.property.ruag.entity.RuagCalendarWorkModel;

/**
 *
    * @ClassName: RuagCalendarWorkModelService
    * @com.sgai.property.commonService.vo;(策略日程与策略配置之间的关系Service)
    * @author 王天尧
    * @date 2018年1月9日
    * @Company 首自信--智慧城市创新中心
 */
@Service
@Transactional
public class RuagCalendarWorkModelService extends CrudServiceExt<RuagCalendarWorkModelDao, RuagCalendarWorkModel> {

	public RuagCalendarWorkModel get(String id) {
		return super.get(id);
	}

	public List<RuagCalendarWorkModel> findList(RuagCalendarWorkModel ruagCalendarWorkModel) {
		return super.findList(ruagCalendarWorkModel);
	}

	public Page<RuagCalendarWorkModel> findPage(Page<RuagCalendarWorkModel> page, RuagCalendarWorkModel ruagCalendarWorkModel) {
		return super.findPage(page, ruagCalendarWorkModel);
	}

	@Transactional(readOnly = false)
	public void save(RuagCalendarWorkModel ruagCalendarWorkModel) {
		super.save(ruagCalendarWorkModel);
	}

	@Transactional(readOnly = false)
	public void delete(RuagCalendarWorkModel ruagCalendarWorkModel) {
		super.delete(ruagCalendarWorkModel);
	}
	/**
	 *
	    * @Title: getByCalendarId
	    * @com.sgai.property.commonService.vo;(根据策略日程id查找策略配置)
	    * @param @param id  策略日程id
	    * @param @return    参数
	    * @return List<RuagCalendarWorkModel>    返回类型
	    * @throws
	 */
	public List<RuagCalendarWorkModel> getByCalendarId(String id){
		RuagCalendarWorkModel ruagCalendarWorkModel = new RuagCalendarWorkModel();
		ruagCalendarWorkModel.setModelCalendarId(id);
		return findList(ruagCalendarWorkModel);
	}
	/**
	 *
	    * @Title: findAllOfList
	    * @com.sgai.property.commonService.vo;(查找所有的数据)
	    * @param @param ruagCalendarWorkModel
	    * @param @return    参数
	    * @return List<RuagCalendarWorkModel>    返回类型
	    * @throws
	 */
	public List<RuagCalendarWorkModel> findAllOfList(RuagCalendarWorkModel ruagCalendarWorkModel) {
		return dao.findAllOfList(ruagCalendarWorkModel);
	}
	@Transactional
	public void deleteByCalendarId(String calendarId){
		dao.deleteByCalendarId(calendarId);
	}
}
