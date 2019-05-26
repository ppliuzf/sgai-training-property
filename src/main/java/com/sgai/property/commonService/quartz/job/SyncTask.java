package com.sgai.property.commonService.quartz.job;/*
package com.sgai.modules.commonService.quartz.job;
import com.sgai.property.commonService.quartz.QuartzConfiguration;
import com.sgai.property.commonService.service.SyncService;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.stereotype.Component;

*/
/**
 * 定时任务，定时同步连接平台数据
 * @author 147693
 *
 *//*

@Component
@DisallowConcurrentExecution
public class SyncTask implements Job {

	private static Logger logger = LoggerFactory.getLogger(SyncTask.class);
	
	@Value("${cron.sync.increment}") //定时任务更新时间
	private String frequency;
	@Autowired
	private SyncService syncService;
	
	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		String result = syncService.sync();
		logger.info(result);
	}
	
    @Bean(name = "syncCronTriggerBean")
    public JobDetail sampleJob() {
        return JobBuilder.newJob(SyncTask.class)
                .withIdentity("syncCronTriggerBean","DEFAULT").withDescription("同步连接平台数据定时任务")
                .requestRecovery(true).storeDurably().build();
    }

    @Bean(name = "syncCronTriggerBeanTrigger")
    public CronTriggerFactoryBean sampleJobTrigger(@Qualifier("syncCronTriggerBean") JobDetail jobDetail) {
        return QuartzConfiguration.createCronTrigger(jobDetail, frequency);
    }
}
*/
