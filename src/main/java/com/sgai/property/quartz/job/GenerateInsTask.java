package com.sgai.property.quartz.job;

import java.io.IOException;
import java.text.ParseException;

import javax.servlet.ServletException;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.sgai.property.ruag.service.RuagDeviceCalendarInstctionService;

/**  
    * @ClassName: JobTask  
    * @com.sgai.property.commonService.vo;(这里用一句话描述这个类的作用)
    * @author 王天尧  
    * @date 2017年12月20日  
    * @Company 首自信--智慧城市创新中心  
    */

public class GenerateInsTask implements Job {
	@Autowired
	private RuagDeviceCalendarInstctionService ruagDeviceCalendarInstctionService;
	@Override
	public void execute(JobExecutionContext context) {
		LoggerFactory.getLogger("ruag").info("------------------定时生成指令任务开始-------------------------");
		try {
			ruagDeviceCalendarInstctionService.generateInstructionsScheduleTask();
		} catch (ParseException e) {

			e.printStackTrace();
		} catch (SchedulerException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		} catch (ServletException e) {

			e.printStackTrace();
		}
		LoggerFactory.getLogger("ruag").info("------------------定时生成指令任务结束-------------------------");
	}
	
}
