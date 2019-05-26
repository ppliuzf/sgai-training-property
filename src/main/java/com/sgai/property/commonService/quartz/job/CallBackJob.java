package com.sgai.property.commonService.quartz.job;

import com.sgai.property.commonService.constants.Constants;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * 定时回调方法
 * @create 2017年11月13日   15:41
 */
@Component("callBackJob")
@DisallowConcurrentExecution
public class CallBackJob implements Job {
    private static final Logger logger = LogManager.getLogger(CallBackJob.class);
    @Autowired
    RestTemplate restTemplate;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        JobDataMap dataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        String callBackUrl = dataMap.getString(Constants.SCHEDULER_CALL_BACK_URL);
         try {
             logger.info("开始执行定时回调方法:"+callBackUrl);
             restTemplate.getForObject(callBackUrl,String.class);
             logger.info("已成功执行回调方法"+callBackUrl);
         }catch (Exception e){
             e.printStackTrace();
         }
    }

}



