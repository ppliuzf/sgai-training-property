package com.sgai.property.customer.service;

import com.google.common.collect.Lists;
import com.sgai.common.mapper.BeanMapper;
import com.sgai.common.persistence.Page;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.customer.constants.Constants;
import com.sgai.property.customer.dao.IWorkorderCostDao;
import com.sgai.property.customer.entity.WorkorderCost;
import com.sgai.property.customer.vo.WorkorderCostParam;
import com.sgai.property.customer.vo.WorkorderCostVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class WorkorderCostServiceImpl extends MoreDataSourceCrudServiceImpl<IWorkorderCostDao,WorkorderCost>{
	@Autowired
	private IWorkorderCostDao workorderCostDao;
	
	  
	    /**  工单费用列表
	    * @Title: findWorkorderCostList  
	    * @Description: (这里用一句话描述这个方法的作用)
	    * @param @param toonCode
	    * @param @param workorderCostParam
	    * @param @param pageNum
	    * @param @param pageSize
	    * @param @return    参数
	    * @return Page<WorkorderCostVO>    返回类型
	    * @throws  
	    */  
	    
	public Page<WorkorderCostVO> findWorkorderCostList(WorkorderCostParam workorderCostParam, int pageNum, int pageSize){
		Page<WorkorderCostVO> workorderCostVOs = new Page<>();
		WorkorderCost workorderCost = new WorkorderCost();
		if(workorderCostParam != null){
			workorderCost = BeanMapper.map(workorderCostParam, WorkorderCost.class);
		}
		workorderCost.setIsDelete(Constants.FALSE);
		workorderCost.setComCode(UserServletContext.getUserInfo().getComCode());
		workorderCost.setModuCode(UserServletContext.getUserInfo().getModuCode());
		Page<WorkorderCost> page=new Page<>(pageNum,pageSize);
		page.setOrderBy("CREATED_DT desc");
		Page<WorkorderCost> workorderCosts = findPage(page, workorderCost);
		if(workorderCosts != null && workorderCosts.getList() != null){
			List<WorkorderCostVO> costVOs = Lists.newArrayList();
			costVOs = BeanMapper.mapList(workorderCosts.getList(), WorkorderCostVO.class);
			workorderCostVOs.setList(costVOs);
			workorderCostVOs.setCount(workorderCosts.getCount());
			workorderCostVOs.setPageNo(pageNum);
			workorderCostVOs.setPageSize(pageSize);
		}
		return workorderCostVOs;
		
	}
	  
	    /**  保存工单费用信息
	    * @Title: saveWorkorderCost  
	    * @Description: (这里用一句话描述这个方法的作用)
	    * @param @param toonCode
	    * @param @param workorderCostParam
	    * @param @return    参数
	    * @return boolean    返回类型
	    * @throws  
	    */  
	@Transactional
	public boolean saveWorkorderCost(WorkorderCostParam workorderCostParam){
		Date currtDate = new Date();
		WorkorderCost workorderCost = new WorkorderCost();
		if(workorderCostParam != null){
			workorderCost = BeanMapper.map(workorderCostParam, WorkorderCost.class);
		}
		workorderCost.setCreatedBy(UserServletContext.getUserInfo().getUserNo()+"");
		workorderCost.setCreatedDt(currtDate);
		workorderCost.setUpdatedBy(UserServletContext.getUserInfo().getUserNo()+"");
		workorderCost.setUpdatedDt(currtDate);
		workorderCost.setComCode(UserServletContext.getUserInfo().getComCode());
		workorderCost.setModuCode(UserServletContext.getUserInfo().getModuCode());
		boolean flag = saveEntity(workorderCost);
		return flag;
	}

	  
	    /**  获取工单费用详情
	    * @Title: getWorkorderCostById  
	    * @Description: (这里用一句话描述这个方法的作用)
	    * @param @param toonCode
	    * @param @param id
	    * @param @return    参数
	    * @return WorkorderCostDetailVO    返回类型
	    * @throws  
	    */  
	    
	public WorkorderCostVO getWorkorderCostById(String id){
		WorkorderCostVO workorderCostDetailVO = new WorkorderCostVO();
		WorkorderCost workorderCost = workorderCostDao.getById(id);
		if(workorderCostDao != null){
			workorderCostDetailVO = BeanMapper.map(workorderCost, WorkorderCostVO.class);
		}
		return workorderCostDetailVO;
	}
	
	  
	    /**  更新工单费用信息
	    * @Title: updateWorkorderCost  
	    * @Description: (这里用一句话描述这个方法的作用)
	    * @param @param toonCode
	    * @param @param workorderCostDetailVO
	    * @param @return    参数
	    * @return boolean    返回类型
	    * @throws  
	    */  
	@Transactional
	public boolean updateWorkorderCost(WorkorderCostVO workorderCostDetailVO){
		WorkorderCost workorderCost = new WorkorderCost();
		if(workorderCostDetailVO != null){
			workorderCost = BeanMapper.map(workorderCostDetailVO, WorkorderCost.class);
		}
		boolean flag = false;
		workorderCost.setUpdatedBy(UserServletContext.getUserInfo().getUserNo()+"");
		workorderCost.setUpdatedDt(new Date());
		if(workorderCost != null){
			int rs = workorderCostDao.updateById(workorderCost);
			flag = rs > 0;
		}
		
		return flag;
	}
	
	  
	    /** 删除工单费用信息 
	    * @Title: deleteWorkorderCostById  
	    * @Description: (这里用一句话描述这个方法的作用)
	    * @param @param toonCode
	    * @param @param id
	    * @param @return    参数
	    * @return boolean    返回类型
	    * @throws  
	    */  
	@Transactional
	public boolean deleteWorkorderCostById(String id){
		WorkorderCost workorderCost = new WorkorderCost();
		workorderCost.setIsDelete(Constants.TRUE);
		workorderCost.setUpdatedBy(UserServletContext.getUserInfo().getUserNo()+"");
		workorderCost.setUpdatedDt(new Date());
		workorderCost.setId(id);
		int rs = workorderCostDao.updateById(workorderCost); 
		return rs > 0;
	}
	
}