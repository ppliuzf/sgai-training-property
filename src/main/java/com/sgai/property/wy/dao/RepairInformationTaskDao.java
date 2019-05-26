  
    /**    
    * @Title: RepairInformationTaskDao.java  
    * @Package com.sgai.property.wy.dao
    * (用一句话描述该文件做什么)
    * @author XJ9001  
    * @date 2018年3月5日  
    * @Company 首自信--智慧城市创新中心
    * @version V1.0    
    */  
    
package com.sgai.property.wy.dao;


    import com.sgai.common.persistence.CrudDao;
    import com.sgai.property.wy.entity.RepairInformationTask;
    import org.apache.ibatis.annotations.Mapper;

    import java.util.List;


    /**  
 * @ClassName: RepairInformationTaskDao  
 * (这里用一句话描述这个类的作用)
 * @author XJ9001  
 * @date 2018年3月5日  
 * @Company 首自信--智慧城市创新中心  
 */
@Mapper
public interface RepairInformationTaskDao extends CrudDao<RepairInformationTask> {

		  
		    /**  
		    * @Title: getTaskList  
		    * (这里用一句话描述这个方法的作用)
		    * @param @return    参数  
		    * @return List<RepairInformationTask>    返回类型  
		    * @throws  
		    */  
		    
		List<RepairInformationTask> getTaskList();

		 

}
