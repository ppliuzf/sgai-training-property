  
    /**    
    * @Title: MyApplicationRunner.java  
    * @Package com.sgai.property.wy.support
    * (用一句话描述该文件做什么)
    * @author XJ9001  
    * @date 2018年3月5日  
    * @Company 首自信--智慧城市创新中心
    * @version V1.0    
    */  
    
package com.sgai.property.wy.support;

    import com.sgai.property.wy.entity.RepairInformationTask;
    import com.sgai.property.wy.service.RepairInformationTaskService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.boot.ApplicationArguments;
    import org.springframework.boot.ApplicationRunner;
	import org.springframework.core.annotation.Order;
	import org.springframework.stereotype.Component;

	import java.util.Date;
	import java.util.List;


    /**  
 * @ClassName: MyApplicationRunner  
 * (这里用一句话描述这个类的作用)
 * @author XJ9001  
 * @date 2018年3月5日  
 * @Company 首自信--智慧城市创新中心  
 */
@Component
public class MyApplicationRunner implements ApplicationRunner {

	@Autowired
	private RepairInformationTaskService repairInformationTaskService;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		//每次项目重新启动,初始化事件自动受理和自动派单
		List<RepairInformationTask> list = repairInformationTaskService.getTaskList();
		for (RepairInformationTask repairInformationTask : list) {
			RepairInformationTask reT = new RepairInformationTask();
			reT.setId(repairInformationTask.getId());
			reT.setTaskStatus("0");
			repairInformationTaskService.save(reT);
		}
	}

}
