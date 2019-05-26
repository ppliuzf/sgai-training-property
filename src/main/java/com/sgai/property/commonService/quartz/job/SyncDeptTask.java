package com.sgai.property.commonService.quartz.job;
import com.sgai.property.commonService.quartz.QuartzConfiguration;
import com.sgai.property.commonService.service.DeptServiceImpl;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.stereotype.Component;

/**
 * 定时任务，定时同步连接平台数据
 * @author 147693
 *
 */
@Component
@DisallowConcurrentExecution
public class SyncDeptTask implements Job {

	private static Logger logger = LoggerFactory.getLogger(SyncDeptTask.class);
	
	@Value("${cron.sync.dept.frequency}") //定时任务更新时间
	private String frequency;
	@Value("${accessToken.orgId}")
	private String orgId;
	@Autowired
	private DeptServiceImpl deptService;

	
	@Override
	public void execute(JobExecutionContext context) {
		logger.info("同步连接平台部门数据，定时任务开始执行！");
		long startTime=System.currentTimeMillis();
		deptService.syncDept(orgId);
		logger.info("同步连接平台部门数据，定时任务结束执行时间:"+(System.currentTimeMillis()-startTime)+"毫秒");
	}
	
    @Bean(name = "syncDeptCronBean")
    public JobDetail sampleJob() {
        return JobBuilder.newJob(SyncDeptTask.class)
                .withIdentity("syncDeptCronBean","DEFAULT").withDescription("同步连接平台部门数据")
                .requestRecovery(true).storeDurably().build();
    }

    @Bean(name = "syncDeptCronBeanTrigger")
    public CronTriggerFactoryBean sampleJobTrigger(@Qualifier("syncDeptCronBean") JobDetail jobDetail) {
        return QuartzConfiguration.createCronTrigger(jobDetail, frequency);
    }
}
