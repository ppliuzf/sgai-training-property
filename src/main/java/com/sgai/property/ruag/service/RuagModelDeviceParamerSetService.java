package com.sgai.property.ruag.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgai.common.persistence.Page;
import com.sgai.common.service.CrudServiceExt;
import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.ruag.dao.RuagModelDeviceParamerSetDao;
import com.sgai.property.ruag.entity.RuagModelDeviceParamerSet;

/**
 *
    * @ClassName: RuagModelDeviceParamerSetService
    * @com.sgai.property.commonService.vo;(模式设备参数设置Service)
    * @author 王天尧
    * @date 2018年1月3日
    * @Company 首自信--智慧城市创新中心
 */
@Service
@Transactional
public class RuagModelDeviceParamerSetService extends CrudServiceExt<RuagModelDeviceParamerSetDao, RuagModelDeviceParamerSet> {

	public void updateParamter(RuagModelDeviceParamerSet ruagModelDeviceParamerSet) {
		dao.updateParamter(ruagModelDeviceParamerSet);
	}
	public RuagModelDeviceParamerSet get(String id) {
		return super.get(id);
	}

	public List<RuagModelDeviceParamerSet> findList(RuagModelDeviceParamerSet ruagModelDeviceParamerSet) {
		return super.findList(ruagModelDeviceParamerSet);
	}

	public Page<RuagModelDeviceParamerSet> findPage(Page<RuagModelDeviceParamerSet> page, RuagModelDeviceParamerSet ruagModelDeviceParamerSet) {
		return super.findPage(page, ruagModelDeviceParamerSet);
	}

	@Transactional(readOnly = false)
	public void save(RuagModelDeviceParamerSet ruagModelDeviceParamerSet) {
		super.save(ruagModelDeviceParamerSet);
	}

	@Transactional(readOnly = false)
	public void delete(RuagModelDeviceParamerSet ruagModelDeviceParamerSet) {
		super.delete(ruagModelDeviceParamerSet);
	}
	/**
	 *
	    * @Title: deleteByModelId
	    * @com.sgai.property.commonService.vo;(通过运行策略id删除参数设置)
	    * @param @param ruagModelDeviceParamerSet    参数
	    * @return void    返回类型
	    * @throws
	 */
	@Transactional(readOnly = false)
	public void deleteByModelId(RuagModelDeviceParamerSet ruagModelDeviceParamerSet) {
		LoginUser user = UserServletContext.getUserInfo();
		ruagModelDeviceParamerSet.setComCode(user.getComCode());
		ruagModelDeviceParamerSet.setModuCode(user.getModuCode());
		dao.deleteByModelId(ruagModelDeviceParamerSet);
	}
	/**
	 *
	    * @Title: findAllOfList
	    * @com.sgai.property.commonService.vo;(查询出所有的数据不根据模块和机构划分)
	    * @param @param ruagModelDeviceParamerSet
	    * @param @return    参数
	    * @return List<RuagModelDeviceParamerSet>    返回类型
	    * @throws
	 */
	public List<RuagModelDeviceParamerSet> findAllOfList(RuagModelDeviceParamerSet ruagModelDeviceParamerSet) {
		return dao.findAllOfList(ruagModelDeviceParamerSet);
	}

	public List<RuagModelDeviceParamerSet> findDevicesById(String id) {
		return dao.findDevicesById(id);
	}
}
