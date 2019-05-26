package com.sgai.property.commonService.quartz.vo;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.lang.reflect.Method;

@PersistJobDataAfterExecution
@DisallowConcurrentExecution 
public class MyDetailQuartzJobBean extends QuartzJobBean {
	private String targetObject;
	private String targetMethod;
	@Autowired
	private ApplicationContext ctx;

	@Override
	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {
		try {  
			Object otargetObject = ctx.getBean(targetObject);
			Method m = null;
			try {
				m = otargetObject.getClass().getMethod(targetMethod,
						JobExecutionContext.class); 
				m.invoke(otargetObject, context);
			} catch (SecurityException e) {
				 e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new JobExecutionException(e);
		}
	}

	public void setApplicationContext(ApplicationContext applicationContext) {
		this.ctx = applicationContext;
	}

	public void setTargetObject(String targetObject) {
		this.targetObject = targetObject;
	}

	public void setTargetMethod(String targetMethod) {
		this.targetMethod = targetMethod;
	}

}
