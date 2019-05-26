package com.sgai.property.meeting.task;

import java.text.ParseException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.sgai.property.meeting.service.MaininfoServiceImpl;

@Component
public class MeetingStateTask {
    private static final Logger logger = LogManager.getLogger(MeetingStateTask.class);

    @Autowired
	private MaininfoServiceImpl maininfoService;

    @Scheduled(initialDelay=1000 * 10, fixedRate=1000 * 60 )
    public void schedulerUpdateMeetingState(){
        //更新会议状态
        logger.debug("--更新会议状态 开始--");
        Long startTime = System.currentTimeMillis();
        try {
			maininfoService.schedulerUpdateMeetingState();
		} catch (ParseException e) {
			//  Auto-generated catch block
			e.printStackTrace();
		}
    }
}