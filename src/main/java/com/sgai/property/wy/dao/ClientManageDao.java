  
    /**    
    * @Title: ClientManageDao.java  
    * @Package com.sgai.property.wy.dao
    * (用一句话描述该文件做什么)
    * @author XJ9001  
    * @date 2018年1月31日  
    * @Company 首自信--智慧城市创新中心
    * @version V1.0    
    */  
    
package com.sgai.property.wy.dao;

    import com.sgai.common.persistence.CrudDao;
    import com.sgai.property.wy.entity.ClientManage;
    import org.apache.ibatis.annotations.Mapper;


    /**  
 * @ClassName: ClientManageDao  
 * (这里用一句话描述这个类的作用)
 * @author XJ9001  
 * @date 2018年1月31日  
 * @Company 首自信--智慧城市创新中心  
 */
@Mapper
public interface ClientManageDao extends CrudDao<ClientManage> {

}
