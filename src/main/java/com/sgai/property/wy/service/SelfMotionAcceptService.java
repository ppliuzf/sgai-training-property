  
    /**    
    * @Title: SelfMotionAcceptController.java  
    * @Package com.sgai.property.wy.web
    * (用一句话描述该文件做什么)
    * @author XJ9001  
    * @date 2018年2月24日  
    * @Company 首自信--智慧城市创新中心
    * @version V1.0    
    */  
    
package com.sgai.property.wy.service;


    import com.sgai.modules.login.jwt.bean.LoginUser;
    import com.sgai.property.wy.dto.SelfMotionAccepts;


    /**
 * @ClassName: SelfMotionAcceptController  
 * (自动受理执行)
 * @author XJ9001  
 * @date 2018年2月24日  
 * @Company 首自信--智慧城市创新中心  
 */
public class SelfMotionAcceptService {

	 /**
     * 初始化自定义定时线程
     */
    public static void initMyTimer() {
         
    	SelfMotionAccepts timer=  new SelfMotionAccepts();
        timer.start();
        System.out.println("初始化自定义定时线程");
    }
     
    /**
     * 开启自定义定时线程
     */
    public static void startMyTimer(LoginUser loginUser, RepairInformationService repairInformationService) {
    	SelfMotionAccepts.flag = true;
    	//SelfMotionAccepts.period = period;
    	SelfMotionAccepts.loginUser = loginUser;
    	SelfMotionAccepts.repairInformationService = repairInformationService;
        System.out.println("开启自定义定时线程");
    }
 
    /**
     * 停止自定义定时线程
     */
    public static void stopMyTimer() {
    	SelfMotionAccepts.flag = false;
        System.out.println("停止自定义定时线程");
    }
}
