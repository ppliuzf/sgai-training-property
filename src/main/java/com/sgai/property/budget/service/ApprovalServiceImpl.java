package com.sgai.property.budget.service;

import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.budget.dao.IRecordDao;
import com.sgai.property.budget.entity.Record;
import com.sgai.property.budget.vo.ApprovalParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApprovalServiceImpl extends MoreDataSourceCrudServiceImpl<IRecordDao,Record>{
	@Autowired
	private IRecordDao recordDao;

	public Boolean check(ApprovalParam param) {
		Record record =new Record();
		
		BeanUtils.copyProperties(param, record);
		record.setApproverId(UserServletContext.getUserInfo().getUserNo());
		record.setApproverName(UserServletContext.getUserInfo().getUserName());
		record.setApprovalTime(System.currentTimeMillis());
		record.setUpdateTime(System.currentTimeMillis());
		
		recordDao.updateById(record);
		return true;
	}

	public Boolean submitOrRevoke(ApprovalParam param) {
		Record record =new Record();
		
		BeanUtils.copyProperties(param, record);
		record.setUpdateTime(System.currentTimeMillis());
		
		recordDao.updateById(record);
		return true;
	}

}