  
    /**    
    * @Title: RepairInformationTask.java  
    * @Package com.sgai.property.wy.service
    * (用一句话描述该文件做什么)
    * @author XJ9001  
    * @date 2018年3月5日  
    * @Company 首自信--智慧城市创新中心
    * @version V1.0    
    */  
    
package com.sgai.property.wy.service;


    import com.sgai.common.service.CrudServiceExt;
    import com.sgai.property.wy.dao.RepairInformationTaskDao;
    import com.sgai.property.wy.entity.RepairInformationTask;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;

    import java.util.List;


    /**  
 * @ClassName: RepairInformationTask  
 * (这里用一句话描述这个类的作用)
 * @author XJ9001  
 * @date 2018年3月5日  
 * @Company 首自信--智慧城市创新中心  
 */
@Service
public class RepairInformationTaskService extends CrudServiceExt<RepairInformationTaskDao, RepairInformationTask> {

	@Autowired
    private RepairInformationTaskDao repairInformationTaskDao;
		  
		    /**  
		    * @Title: getTaskList  
		    * (这里用一句话描述这个方法的作用)
		    * @param @return    参数  
		    * @return List<RepairInformationTask>    返回类型  
		    * @throws  
		    */  
		    
		public List<RepairInformationTask> getTaskList() {
			
			return repairInformationTaskDao.getTaskList();
		}

	
}
