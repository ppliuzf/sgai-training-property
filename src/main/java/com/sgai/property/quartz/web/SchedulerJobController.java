package com.sgai.property.quartz.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sgai.common.persistence.Page;
import com.sgai.modules.login.annotation.PermessionLimit;
import com.sgai.modules.login.jwt.util.CommonResponse;
import com.sgai.modules.login.jwt.util.ResponseUtil;
import com.sgai.property.quartz.entity.ScheduleEntity;
import com.sgai.property.quartz.service.ScheduleJobService;
import org.quartz.CronExpression;
import org.quartz.SchedulerException;
import org.quartz.TriggerUtils;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: SchedulerJobController
 * @author admin
 * @date 2017年12月19日
 * @Company 首自信--智慧城市创新中心
 */
@RestController
@RequestMapping(value = "ruag/scheduler")
public class SchedulerJobController {

	@SuppressWarnings("SpringJavaAutowiredMembersInspection")
    @Autowired
	private ScheduleJobService scheduleJobService;
    
	@Value("${quartz.groupName}")
	private String  groupName;
	
	@RequestMapping("/list")
	public CommonResponse list(
			
			@RequestParam(value = "pageNo", required = true,defaultValue="0") Integer pageNo ,
		    @RequestParam(value = "pageSize", required = true,defaultValue="10") Integer pageSize,
		    @RequestParam(value = "jobName", required = false) String jobName,
			HttpServletRequest request, HttpServletResponse response)
			throws JsonProcessingException {

		//Page<CtlLogProg> page = ctlLogProgService.findPage(new Page<CtlLogProg>(pageNo, pageSize), ctlLogProg);
		//List<ScheduleEntity> scheduleEntities = scheduleJobService.getAllScheduleJob(groupName);
		Page<ScheduleEntity>  page  = scheduleJobService.getAllScheduleJobByPage(pageNo,pageSize, groupName,jobName);
		return ResponseUtil.successResponse(page);
	}

	@RequestMapping("/add")
	public CommonResponse add(String id,String jobName,String cronExpression,
			String description,String className,HttpServletRequest request, 
			HttpServletResponse response) throws JsonProcessingException {
		
		ScheduleEntity  scheduleEntity  = new ScheduleEntity();
	    scheduleEntity.setId(id);
	    scheduleEntity.setJobName(jobName);
	    scheduleEntity.setCronExpression(cronExpression);
	    scheduleEntity.setDescription(description);
	    scheduleEntity.setClassName(className);
		scheduleEntity.setJobGroup(groupName);
		if(id.equals("edit")) {
			delete(jobName,groupName,request,response);
		}else {
			if (scheduleJobService.checkExists(scheduleEntity)) {
				// 有重复的
				return ResponseUtil.customResponse(1101, "当前添加的job重复");
			}
		}
		
		if (!CronExpression.isValidExpression(scheduleEntity.getCronExpression())) {
			// corn 有误
			return ResponseUtil.customResponse(1101, "cron表达式错误");
		}
		try {
			//scheduleEntity.setClassName("com.sgai.property.quartz.job.ScheduledJob");
			scheduleEntity.setMethodName("execute");
			scheduleJobService.add(scheduleEntity);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return ResponseUtil.customResponse(1101, "处理类找不到");
		} catch (SchedulerException e) {
			e.printStackTrace();
			return ResponseUtil.customResponse(1101, "异常啦");
		}
		return ResponseUtil.successResponse("添加成功");
	}

	@RequestMapping("/getOne")
    public CommonResponse getOne(String jobName, String jobGroup,
    		HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		Page<ScheduleEntity>  page =  scheduleJobService.getOne(1, 10, jobGroup, jobName);
        return ResponseUtil.successResponse(page);
    }
	
	
    @RequestMapping("/pauseJob")
    public CommonResponse stop(String jobName, String jobGroup,
    		HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
        try {
            scheduleJobService.stopJob(jobName, jobGroup);
           return ResponseUtil.successResponse("暂停成功！");
        } catch (SchedulerException e) {
            e.printStackTrace();
            return ResponseUtil.exceptionResponse("暂停失败");
        }
    }
	
    @RequestMapping("/deleteJob")
    public CommonResponse delete(String jobName, String jobGroup,
    		HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
        try {
        	String so = request.getParameter("jobName");
            scheduleJobService.delJob(jobName, jobGroup);
            return ResponseUtil.successResponse("删除成功！");
        } catch (Exception e) {  //SchedulerException
            e.printStackTrace();
            return ResponseUtil.exceptionResponse("暂停失败");
        }
    }
    
    
    @RequestMapping("/deleteMoreJob")
    public CommonResponse deleteMoreJob(String ids,HttpServletRequest request,
    		HttpServletResponse response) throws JsonProcessingException {
       
    	String[] strs = ids.split("/");
    	for(int i=0;i<strs.length ;i++) {
    		 if(strs[i].isEmpty()) break;
    		 String[] params = strs[i].split(",");
    		 try {
    		   scheduleJobService.delJob(params[0], params[1]);
    		 } catch (Exception e) {  //SchedulerException
    	            e.printStackTrace();
    	            return ResponseUtil.exceptionResponse(params[0]+"删除失败");
    	     }
    	}
    	
    	 return ResponseUtil.successResponse("删除成功！");
    }
    
    
    @ResponseBody
    @RequestMapping("/updateCorn")
    public CommonResponse updateCorn(@RequestBody ScheduleEntity scheduleEntity,
    		HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
        // 验证cron表达式
        boolean f = CronExpression.isValidExpression(scheduleEntity
                .getCronExpression());
        if (!f) {
        	return ResponseUtil.customResponse(1101, "cron表达式错误");
        }
        try {
            scheduleJobService.modifyTrigger(scheduleEntity.getJobName(),
                    scheduleEntity.getJobGroup(), scheduleEntity.getCronExpression());
            return ResponseUtil.successResponse("修改成功！");
        } catch (SchedulerException e) {
            e.printStackTrace();
            return ResponseUtil.exceptionResponse("修改失败");
        }
    }
   
    @RequestMapping("/resumeJob")
    public CommonResponse resume(String jobName, String jobGroup,
    		HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
        try {
            scheduleJobService.restartJob(jobName, jobGroup);
            return ResponseUtil.successResponse("回复任务");
        } catch (SchedulerException e) {
            e.printStackTrace();
            return ResponseUtil.exceptionResponse("回复任务失败");
        }
    }

    /**
     * getInstanceForCron:返回cron的时间示例
     * @param request
     * @param response
     * @return
     * @throws JsonProcessingException
     * @throws ParseException :CommonResponse 
     * @since JDK 1.8
     * @author admin
     */
    @PermessionLimit(limit = false)
    @RequestMapping("/getInstanceForCron")
	public CommonResponse getInstanceForCron(HttpServletRequest request,
											 HttpServletResponse response) throws JsonProcessingException, ParseException {

		String cronExpression = request.getParameter("cronExpression");
		CronTriggerImpl cronTriggerImpl = new CronTriggerImpl();
		cronTriggerImpl.setCronExpression(cronExpression);
		List<Date> dates = TriggerUtils.computeFireTimes(cronTriggerImpl, null, 5);
		List<String> listTime = new ArrayList<String>();
		for(int i=0;i<dates.size();i++) {
			listTime.add(dates.get(i).toLocaleString());
		}
		return ResponseUtil.successResponse(listTime);
	}
}
