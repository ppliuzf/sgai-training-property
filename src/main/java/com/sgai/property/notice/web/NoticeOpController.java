package com.sgai.property.notice.web;

import com.sgai.property.common.web.BaseController;
import com.sgai.property.commonService.vo.Response;
import com.sgai.property.notice.job.PublishJob;
import com.sgai.property.notice.param.InfoCommitParam;
import com.sgai.property.notice.service.NoticeOpServiceImpl;
import com.sgai.property.notice.vo.NoticeInfoVo;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/noticeOp")
public class NoticeOpController extends BaseController {

	@Autowired
	private NoticeOpServiceImpl noticeOpService;

	@ApiOperation(value = "提交公告", httpMethod = "POST", notes = "提交公告")
	@RequestMapping(value = "/submit", method = RequestMethod.POST)
	public Response<Boolean> submit(
			@RequestBody InfoCommitParam infoCommitParam) {

		Response<Boolean> result = new Response<>();
		String infoId = noticeOpService.submit(infoCommitParam);
		if (StringUtils.isNotBlank(infoId)) {
			result.setData(true);
		} else {
			result.setData(false);
		}
		return result;
	}

	@RequestMapping(value = "/getDetail4Edit", method = RequestMethod.POST)
	@ApiOperation(value = "二次编辑详情", httpMethod = "POST", notes = "二次编辑详情")
	public Response<NoticeInfoVo> infoDetailByEdit(
			@RequestParam("id") String id) {

		Response<NoticeInfoVo> result = new Response<>();
		NoticeInfoVo infoDetailVo = noticeOpService.getDetail4Edit(id);
		result.setData(infoDetailVo);
		return result;
	}

	@ApiOperation(value = "更新公告", httpMethod = "POST", notes = "提交公告")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Response<Boolean> update(
			@RequestBody InfoCommitParam infoCommitParam) {

		Response<Boolean> result = new Response<>();
		String infoId = noticeOpService.update(infoCommitParam);
		if (StringUtils.isNotBlank(infoId)) {
			result.setData(true);
		} else {
			result.setData(false);
		}
		return result;
	}

	//@Autowired
	//@Qualifier("Scheduler")
	private Scheduler scheduler;

	@ApiOperation(value = "", httpMethod = "POST", notes = "")
	@RequestMapping(value = "/addTask", method = RequestMethod.POST)
	public Response<Boolean> addTask(@RequestHeader("accessToken") String access_token) {

		Response<Boolean> result = new Response<>();

		try {
			Date dt = new Date();
			dt = DateUtils.addMinutes(dt, 3);
			// 启动调度器
			scheduler.start();
			scheduler.pauseTrigger(TriggerKey.triggerKey("b7de8ebd-c66b-4574-acf6-84f358ddb2a2", "publishNoticeTime"));
			scheduler.unscheduleJob(TriggerKey.triggerKey("b7de8ebd-c66b-4574-acf6-84f358ddb2a2", "publishNoticeTime"));
			scheduler.deleteJob(JobKey.jobKey("b7de8ebd-c66b-4574-acf6-84f358ddb2a2", "publishNoticeTime"));

			// 构建job信息
			JobDetail jobDetail = JobBuilder.newJob(PublishJob.class)
					.withIdentity("b7de8ebd-c66b-4574-acf6-84f358ddb2a2", "publishNoticeTime").build();
			
			SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule();
			SimpleTrigger trigger = TriggerBuilder.newTrigger()
					.withIdentity("b7de8ebd-c66b-4574-acf6-84f358ddb2a2", "publishNoticeTime")
					.startAt(dt).withSchedule(scheduleBuilder).build();
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");    
			System.out.println(jobDetail.getKey() + " 将会在: " + dateFormat.format(dt) + "时运行，"  
		            + "重复: " + trigger.getRepeatCount() + " 次, " //获取重复的次数  
		            + "每 " + trigger.getRepeatInterval() / 1000L + " s 重复一次");  
//			// 表达式调度构建器(即任务执行的时间)
//			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0 39 9 * * ?");
//
//			// 按新的cronExpression表达式构建一个新的trigger
//			CronTrigger trigger = TriggerBuilder.newTrigger()
//					.withIdentity("b7de8ebd-c66b-4574-acf6-84f358ddb2a2", "publishNoticeTime")
//					.withSchedule(scheduleBuilder).build();
			scheduler.scheduleJob(jobDetail, trigger);

		} catch (SchedulerException e) {
			System.out.println("创建定时任务失败" + e);
		}

		result.setData(true);
		return result;
	}

}
