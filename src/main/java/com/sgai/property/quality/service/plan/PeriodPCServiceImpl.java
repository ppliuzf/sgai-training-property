package com.sgai.property.quality.service.plan;


import com.sgai.common.persistence.Page;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.quality.dao.plan.IPeriodPCDao;
import com.sgai.property.quality.entity.plan.Period;
import com.sgai.property.quality.entity.plan.Task;
import com.sgai.property.quality.service.MoreDataSourceCrudServiceImpl;
import com.sgai.property.quality.vo.plan.PeriodParam;
import com.sgai.property.quality.vo.plan.PeriodVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 *@author 严浩淼
 *@date 2018年1月6日--下午4:33:42
 */
@Service
public class PeriodPCServiceImpl extends MoreDataSourceCrudServiceImpl<IPeriodPCDao,Period> {

	@Autowired
	private IPeriodPCDao periodDao;
	@Autowired
	private TaskServiceImpl taskService;
	
	public List<PeriodVo> getPeriodList(PeriodVo periodVo) {
		Period period =new  Period();
		BeanUtils.copyProperties(periodVo, period);
		period.setIsDelete(0L);
		period.setPage(new Page<Period>()).setOrderBy("period_sort asc,update_time asc");
		List<Period> periods =periodDao.findList(period);
		
		List<PeriodVo> periodVos =new ArrayList<PeriodVo>();
		PeriodVo notPerio = new PeriodVo();
        notPerio.setId("-1");
        notPerio.setPeriodSort(0L);
        notPerio.setPeriodName("无");
        periodVos.add(notPerio);
		if (periods.size()>0) {
			for (Period per : periods) {
				PeriodVo perVo =new  PeriodVo();
				BeanUtils.copyProperties(per, perVo);
				periodVos.add(perVo);
			}
		}
		
		return periodVos;
	}

	public Boolean saveOrUpdate(List<PeriodParam> periodList) {

		for (PeriodParam newPeriod: periodList) {
			Period period =new Period();
			BeanUtils.copyProperties(newPeriod, period);
			if(period.getId() != null && "0".equals(period.getId())) {
				period.setId(null);
			}
			period.setCreatorEiId(UserServletContext.getUserInfo().getEmCode());
			period.setCreatorEiEmpName(UserServletContext.getUserInfo().getUserName());
			period.setCreateTime(System.currentTimeMillis());
			period.setUpdateTime(System.currentTimeMillis());
			this.save(period);
		}
		
		return true;
	}

	public Boolean deletePeriodById(String id) throws Exception{
		Period period = periodDao.getById(id);
		if (period!=null) {
			Task task =new Task();
			task.setPeriodId(period.getId());
			task.setRecordId(period.getRecordId());
			List<Task> tasks =taskService.findList(task);
			if (tasks.size()>0) {
				throw new Exception("删除失败：阶段下有任务！");
			}else {
				period.setIsDelete(1L);
				period.setCreatorEiId(UserServletContext.getUserInfo().getEmCode());
				period.setCreatorEiEmpName(UserServletContext.getUserInfo().getUserName());
				period.setUpdateTime(System.currentTimeMillis());
				return	periodDao.updateById(period)>0;
			}
		}
		return false;
	}

}
