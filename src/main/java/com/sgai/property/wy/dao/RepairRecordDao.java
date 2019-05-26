  
    /**    
    * @Title: RepairRecordDao.java  
    * @Package com.sgai.property.wy.dao
    * (用一句话描述该文件做什么)
    * @author XJ9001  
    * @date 2018年1月24日  
    * @Company 首自信--智慧城市创新中心
    * @version V1.0    
    */  
    
package com.sgai.property.wy.dao;

    import com.sgai.common.persistence.CrudDao;
    import com.sgai.property.wy.entity.RepairRecord;
    import org.apache.ibatis.annotations.Mapper;


    /**  
 * @ClassName: RepairRecordDao  
 * (这里用一句话描述这个类的作用)
 * @author XJ9001  
 * @date 2018年1月24日  
 * @Company 首自信--智慧城市创新中心  
 */
@Mapper
public interface RepairRecordDao extends CrudDao<RepairRecord> {

}
