package com.sgai.property.commonService.service;
import com.sgai.property.commonService.constants.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//@Service
public class SyncService {

	public static boolean canStart = true;
	private Logger logger = LoggerFactory.getLogger(SyncService.class);
	
	@Autowired
	private SyncEmployeesService syncEmployeesService;
	
	public String sync() {
		if(!canStart) {
			return Constants.Sync.SYNC_RESULT_ERROR;
		}
		syncEmployeesService.sync();
		return Constants.Sync.SYNC_RESULT_SUCCESS;
	}
	
	
}
