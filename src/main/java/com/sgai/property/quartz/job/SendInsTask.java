package com.sgai.property.quartz.job;

import com.sgai.property.ruag.service.RuagDeviceCalendarInstctionService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**  
    * @ClassName: ScheduledJob  
    * @com.sgai.property.commonService.vo;(定时扫描联动规则表)
    * @author 王天尧  
    * @date 2017年12月19日  
    * @Company 首自信--智慧城市创新中心  
    */
public class SendInsTask implements Job {

	@Autowired
	private RuagDeviceCalendarInstctionService ruagDeviceCalendarInstctionService;
	private static String timePoint;
	public static void setTimePoint(String timePoint) {  
	    	SendInsTask.timePoint = timePoint;  
	 } 
    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
    	LoggerFactory.getLogger("ruag").info("------------------定时发送指令任务开始-------------------------");
    	try {
			ruagDeviceCalendarInstctionService.sendInstructions(timePoint);
		} catch (Exception e) {
			e.printStackTrace();
		}
		LoggerFactory.getLogger("ruag").info("------------------定时发送指令任务结束-------------------------");
		
    }
}
