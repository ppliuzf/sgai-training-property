package com.sgai.property.notice.job;

import java.util.Date;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sgai.property.notice.constants.Constants;
import com.sgai.property.notice.dao.INoticeInfoDao;
import com.sgai.property.notice.dao.INoticeInfoTimePublishDao;
import com.sgai.property.notice.entity.NoticeInfo;
import com.sgai.property.notice.entity.NoticeInfoTimePublish;

@Service
public class PublishJob implements Job {

	private static Logger logger = LogManager.getLogger(PublishJob.class);

	@Autowired
	private INoticeInfoDao infoDao;

	@Autowired
	private INoticeInfoTimePublishDao timePublishDao;

	/**
	 * 调度处理
	 */
	@Override
	public void execute(JobExecutionContext arg0) {
		// INoticeInfoDao infoDao = SpringUtil.getBean(INoticeInfoDao.class);
		// INoticeInfoTimePublishDao timePublishDao =
		// SpringUtil.getBean(INoticeInfoTimePublishDao.class);

		// Auto-generated method stub
		Date dt = new Date();
		arg0.getJobDetail().getKey().getGroup();// 分类
		arg0.getJobDetail().getKey().getName();// 主键
		NoticeInfo info = infoDao.getById(arg0.getJobDetail().getKey().getName());
		info.setInfoStatus(Constants.Notice.YFB.longValue());
		info.setPublishTime(dt.getTime());
		info.setUpdateTime(dt.getTime());
		info.setUpdatedDt(dt);
		infoDao.updateById(info);

		NoticeInfoTimePublish timePublish = timePublishDao.getById(info.getId());
		timePublish.setUpdateTime(dt.getTime());
		timePublish.setUpdatedDt(dt);
		timePublish.setInfoIsPublished(1L);
		timePublishDao.updateById(timePublish);
	}

}
