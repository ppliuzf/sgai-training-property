  
    /**    
    * @Title: SelfSendOrderService.java  
    * @Package com.sgai.property.wy.service
    * (用一句话描述该文件做什么)
    * @author XJ9001  
    * @date 2018年2月24日  
    * @Company 首自信--智慧城市创新中心
    * @version V1.0    
    */  
    
package com.sgai.property.wy.service;

import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.property.wy.dto.SelfSendOrders;


    /**  
 * @ClassName: SelfSendOrderService  
 * (自动派单执行)
 * @author XJ9001  
 * @date 2018年2月24日  
 * @Company 首自信--智慧城市创新中心  
 */

public class SelfSendOrderService {

	 /**
     * 初始化自定义定时线程
     */
    public static void initMyTimer() {
         
    	SelfSendOrders timer=  new SelfSendOrders();
        timer.start();
        System.out.println("初始化自定义定时线程");
    }
     
    /**
     * 开启自定义定时线程
     */
    public static void startMyTimer(LoginUser loginUser, RepairInformationService repairInformationService) {
    	SelfSendOrders.flag = true;
    	//SelfSendOrders.period = period;
    	SelfSendOrders.loginUser = loginUser;
    	SelfSendOrders.repairInformationService = repairInformationService;
        System.out.println("开启自定义定时线程");
    }
 
    /**
     * 停止自定义定时线程
     */
    public static void stopMyTimer() {
    	SelfSendOrders.flag = false;
        System.out.println("停止自定义定时线程");
    }
}
