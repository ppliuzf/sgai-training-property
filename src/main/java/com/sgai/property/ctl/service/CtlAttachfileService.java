  
    /**    
    * @Title: CtlAttachfileService.java  
    * @Package com.sgai.modules.ctl.service  
    * @Description: (用一句话描述该文件做什么)
    * @author smartcity  
    * @date 2017年12月31日  
    * @Company 首自信--智慧城市创新中心
    * @version V1.0    
    */  
    
package com.sgai.property.ctl.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.sgai.common.persistence.Page;
import com.sgai.common.service.CrudServiceExt;
import com.sgai.common.utils.IdGen;
import com.sgai.property.ctl.dao.CtlAttachfileDao;
import com.sgai.property.ctl.entity.CtlAttachfile;

/**  
    * @ClassName: CtlAttachfileService  
    * @Description: (这里用一句话描述这个类的作用)
    * @author chenxing  
    * @date 2017年12月31日  
    * @Company 首自信--智慧城市创新中心  
    */
@Service
public class CtlAttachfileService extends CrudServiceExt<CtlAttachfileDao, CtlAttachfile>{

	public Page<CtlAttachfile> getAttachfilePage(Page<CtlAttachfile> page, CtlAttachfile ctlAttachfile) {
		return super.findPage(page, ctlAttachfile);
	}
	
	/**
	 * 通过主键获取对应的附件列表
	 */
	public List<CtlAttachfile> getAttachfileList(String masterFileId){
		List<CtlAttachfile> result = Lists.newArrayList();
		if(masterFileId==null || masterFileId.equals(""))
			return result;
		CtlAttachfile entity = new CtlAttachfile();
		entity.setMasterFileId(masterFileId);
	    result = getAttachfileList(entity);
	    return result;
	}
	
	/**
	 * 限制条件放入实体类，然后通过实体类获取对应的附件列表
	 */
	public List<CtlAttachfile> getAttachfileList(CtlAttachfile entity){
		List<CtlAttachfile> result = Lists.newArrayList();
		if(entity==null)
			return result;
		result = dao.findList(entity);
		return result;
	}
	
	/**
	 * 存储附件信息，若存储路径重复，则原数据库中的附件信息将被覆盖
	 */
	public void saveAttachfile(CtlAttachfile entity) {
		if(entity==null)
			return;
		List<CtlAttachfile> list = Lists.newArrayList();
		list.add(entity);
		saveAttachfiles(list);
	}
	
	/**
	 * 存储附件信息，若存储路径重复，则原数据库中的附件信息将被覆盖
	 */
	@Transactional(readOnly = false)
	public void saveAttachfiles(List<CtlAttachfile> list) {
		if(list==null || list.size()==0)
			return;
		//删除
		List<String> fileIdList = Lists.newArrayList();
		for(CtlAttachfile file : list) {
			if(file!=null && file.getId()!=null && !file.getId().equals(""))
				fileIdList.add(file.getId());
		}
		deleteAttachfiles(fileIdList);
		//保存
		for(CtlAttachfile file : list) {
			if(file!=null && file.getId()==null)
				file.setId(IdGen.uuid());
		}
		dao.saveAttachfiles(list);
	}
	
	/**
	 * 通过附件ID删除附件
	 */
	public void deleteAttachfile(String attachfileId) {
		List<String> list = Lists.newArrayList();
		list.add(attachfileId);
		deleteAttachfiles(list);
	}
	
	/**
	 * 通过附件ID列表删除附件
	 */
	public void deleteAttachfiles(List<String> list) {
		if(list!=null && list.size()>0)
		    dao.deleteAttachfiles(list);
	}
	public void deleteByMasterFileId(Map<String,Object> params) {
			dao.deleteByMasterFileId(params);
	}
}
