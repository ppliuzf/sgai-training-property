  
    /**    
    * @Title: RepairInformationTask.java  
    * @Package com.sgai.property.wy.entity
    * (用一句话描述该文件做什么)
    * @author XJ9001  
    * @date 2018年3月5日  
    * @Company 首自信--智慧城市创新中心
    * @version V1.0    
    */  
    
package com.sgai.property.wy.entity;

import com.sgai.common.persistence.BoEntity;


    /**  
 * @ClassName: RepairInformationTask  
 * (这里用一句话描述这个类的作用)
 * @author XJ9001  
 * @date 2018年3月5日  
 * @Company 首自信--智慧城市创新中心  
 */

public class RepairInformationTask extends BoEntity<RepairInformationTask> {

	private static final long serialVersionUID = 1L;

    private String taskName; //任务名称

    private String taskStatus;  //任务状态

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}
    
    
}
