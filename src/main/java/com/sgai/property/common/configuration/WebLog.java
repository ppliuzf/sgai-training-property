package com.sgai.property.common.configuration;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.sgai.common.utils.IpUtils;
import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.ctl.entity.CtlLogProg;
import com.sgai.property.ctl.service.CtlLogProgService;

/**  
* @ClassName: WebLog  
* @Description: (这里用一句话描述这个类的作用)
* @author admin  
* @date 2017年12月30日  
* @Company 首自信--智慧城市创新中心  
*/
@Aspect
@Component
public class WebLog {

	@Autowired
	private CtlLogProgService  ctlLogProgService;
	
	private static SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

     @Pointcut("execution(public * com.sgai.modules.*.web..*.*(..))")
     public void webLog(){}
     
     @AfterReturning("webLog()")
     public void doBefore(JoinPoint joinPoint){
    	 
         ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
         HttpServletRequest request = attributes.getRequest();
         LoginUser user  = UserServletContext.getUserInfo();
         if(user !=null) {
	    	 CtlLogProg  logProg = new CtlLogProg();
	    	 logProg.setSessionId(request.getSession().getId());
	         logProg.setComId(user.getComCode());
	         logProg.setUserCode(user.getUserId());
	         logProg.setUserName(user.getUserName());
	         logProg.setRunTime(sdf.format(new Date()));
	         logProg.setRequestUrl(request.getRequestURL().toString());
	         logProg.setClassMethod(joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
	         logProg.setRequestType(request.getMethod());
	         logProg.setRemoteAddr(IpUtils.getIP(request));
	         ctlLogProgService.save(logProg);
         }
     }
}
