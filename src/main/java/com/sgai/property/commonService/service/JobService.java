package com.sgai.property.commonService.service;
import com.sgai.property.commonService.quartz.vo.JobVo;
import com.sgai.property.commonService.quartz.vo.MyDetailQuartzJobBean;
import com.sgai.property.commonService.quartz.vo.ScheduleJob;
import com.sgai.property.commonService.constants.Constants;
import org.apache.commons.lang3.StringUtils;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class JobService {
	   
    @Autowired
    private Scheduler scheduler;  
    
	//判断是否存在该任务。
	public boolean checkExists(JobVo job){
	   JobKey key = new JobKey(job.getName(),job.getGroup());  
	   try {
			return scheduler.checkExists(key);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		return true;
	}
   //添加job工作任务
	public void addJob(JobVo job){
	    if(StringUtils.isEmpty(job.getGroup())){
	        job.setGroup("DEFAULT");
        }
        JobDetail jobDetail = JobBuilder.newJob(MyDetailQuartzJobBean.class)
				.requestRecovery(true).storeDurably()
				.withDescription(job.getDesc())
				.withIdentity(job.getName(),job.getGroup()).build(); 
			jobDetail.getJobDataMap().put("targetObject","callBackJob");
			jobDetail.getJobDataMap().put("targetMethod","execute");
//        if(!job.getParamMap().isEmpty()){
//            for (Map.Entry<String, String> entry : job.getParamMap().entrySet()) {
                jobDetail.getJobDataMap().put(Constants.SCHEDULER_CALL_BACK_URL,job.getCallBackUrl());
//            }
//        }
			//表达式调度构建器
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getExpression());
			//按新的cronExpression表达式构建一个新的trigger
			CronTrigger	trigger = TriggerBuilder.newTrigger()
					.withIdentity("Cron_"+job.getName(),job.getGroup())
					.withSchedule(scheduleBuilder).build();
			try { 
				scheduler.scheduleJob(jobDetail, trigger);
			} catch (SchedulerException e) {
				e.printStackTrace();
			}
	}
	
	  //获取所有的触发器    
	public List<ScheduleJob> getSchedulerJobs(){
		try { 
			GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
			Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
			List<ScheduleJob> jobList = new ArrayList<ScheduleJob>();
			for (JobKey jobKey : jobKeys) {
			    List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
			    for (Trigger trigger : triggers) {
			        ScheduleJob job = new ScheduleJob();
			        job.setName(jobKey.getName());
			        job.setGroup(jobKey.getGroup()); 
			        job.setDesc(scheduler.getJobDetail(jobKey).getDescription());
			        Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
			        job.setStatus(triggerState.name());
			         if (trigger instanceof SimpleTrigger) {
	                    SimpleTrigger simple = (SimpleTrigger) trigger;
	                    job.setExpression("重复次数:"+ (simple.getRepeatCount() == -1 ?   
	                            "无限" : simple.getRepeatCount()) +",重复间隔:"+(simple.getRepeatInterval()/1000L)+"s");
                        jobList.add(job);
	                }
			        if (trigger instanceof CronTrigger) {
			            CronTrigger cronTrigger = (CronTrigger) trigger;
			            String cronExpression = cronTrigger.getCronExpression();
			            job.setExpression(cronExpression);  
			            jobList.add(job);
			        } 
			    }
			}
			return jobList;
		}catch(SchedulerException e) {  
            e.printStackTrace();  
        }  
        return null; 
	}
  
   
      
    //暂停任务  
    public void stopJob(String name, String group){
        JobKey key = new JobKey(name, group);  
        try {  
            scheduler.pauseJob(key);  
        } catch (SchedulerException e) {  
            e.printStackTrace();  
        }  
    }  
    
   //暂停全部任务
    public void stopAllJob(){   
        try {   
            scheduler.pauseAll();
        } catch (SchedulerException e) {  
            e.printStackTrace();  
        }  
    }  
      
    //恢复任务  
    public void restartJob(String name, String group){
        JobKey key = new JobKey(name,group);  
        try {  
            scheduler.resumeJob(key);  
        } catch (SchedulerException e) {  
            e.printStackTrace();  
        }  
    }  
    
    //恢复全部任务
    public void restartAllJob(){   
        try {  
            scheduler.resumeAll();  
        } catch (SchedulerException e) {  
            e.printStackTrace();  
        }  
    }  
      
    //立马执行一次任务  
    public void startNowJob(String name, String group){
        try {    
            JobKey key = new JobKey(name, group);  
            scheduler.triggerJob(key);   
        } catch (SchedulerException e) {  
            e.printStackTrace();  
        }  
    }  
      
    //删除任务  
    public void delJob(String name, String group){
        JobKey key = new JobKey(name,group);  
        try {  
            scheduler.deleteJob(key);  
        } catch (SchedulerException e) {  
            e.printStackTrace();  
        }  
    }  
      
    //修改触发器时间  
    public void modifyTrigger(String name, String group, String cron){
        try {   
        	  JobKey jobKey = new JobKey(name, group);  
        	  //可能存在一个job下存在多个 Trigger
        	  List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
		      for (Trigger trigger : triggers) {
		    	   if (trigger instanceof CronTrigger) {  
		    		   CronTrigger cronTrigger =(CronTrigger) trigger;
		    		   TriggerKey key =cronTrigger.getKey(); 
		    		   CronTrigger newTrigger = cronTrigger.getTriggerBuilder()
		                        .withIdentity(key)
		                        .withSchedule(CronScheduleBuilder.cronSchedule(cron))  
		                        .build();   
		               scheduler.rescheduleJob(key, newTrigger);    
	                }  
		     }   
        } catch (SchedulerException e) {  
            e.printStackTrace();  
        }  
    }  
      
    //暂停调度器  
    public void stopScheduler(){  
         try {  
            if (!scheduler.isInStandbyMode()) {  
                scheduler.standby();  
               // scheduler.st
            }  
        } catch (SchedulerException e) {  
            e.printStackTrace();  
        }  
    }  
}
