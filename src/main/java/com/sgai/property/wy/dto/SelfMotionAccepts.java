  
    /**    
    * @Title: SelfMotionAccepts.java  
    * @Package com.sgai.property.wy.dto
    * (用一句话描述该文件做什么)
    * @author XJ9001  
    * @date 2018年2月24日  
    * @Company 首自信--智慧城市创新中心
    * @version V1.0    
    */  
    
package com.sgai.property.wy.dto;



import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.property.wy.service.RepairInformationService;


    /**  
 * @ClassName: SelfMotionAccepts  
 * (自动受理任务类)
 * @author XJ9001  
 * @date 2018年2月24日  
 * @Company 首自信--智慧城市创新中心  
 */

public class SelfMotionAccepts extends Thread {
	

	//开关控制标志位
    public static boolean flag = false;             
    //开始时间
    public static Long startTime = 1000*30*60L;
    //循环时间
    public static Long period    = 1000*30*60L;     
    //当前登录人
    public static LoginUser loginUser;
    //
    public static RepairInformationService repairInformationService;
    
    
    @Override
    public void run() {
        while(true)
        {
            try {
                //开始时间
                Thread.sleep(startTime);
                //只有当flag为true时，执行
                while(flag)
                {
                    //业务逻辑处理块                  
                	repairInformationService.selfMotionAccept(loginUser);
                	System.out.println("自动受理执行中");
                    //循环时间
                    Thread.sleep(period);
                }
                //当flag为false时，线程休息中
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (Exception e) {
				e.printStackTrace();
			}
        }
    }
}
