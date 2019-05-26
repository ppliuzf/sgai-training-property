package com.sgai.property.customer.task;

import com.sgai.property.customer.service.SurveyServiceImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


/**
 * 问卷调查定时任务
 *
 * @author Hou
 * @create 2018-03-15 13:41
 */
@Component
public class SurveyStatusTask {
    private static final Logger logger = LogManager.getLogger(SurveyStatusTask.class);

    @Autowired
    private SurveyServiceImpl surveyService;

    @Scheduled(initialDelay=1000 * 10, fixedRate=1000 * 60)
    public void reportCurrentTime(){
        //更新问卷调查状态
        logger.debug("--更新问卷调查状态 开始--");
        Long startTime = System.currentTimeMillis();
        surveyService.updateSurveyStatusByTask(startTime);
        logger.info("--更新问卷调查状态 完成---> 所用时间:"+(System.currentTimeMillis() - startTime)+"ms");
    }
}
