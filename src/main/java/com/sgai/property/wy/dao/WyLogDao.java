  
    /**    
    * @Title: LogDao.java  
    * @Package com.sgai.property.wy.dao
    * (用一句话描述该文件做什么)
    * @author Lenovo  
    * @date 2018年1月29日  
    * @Company 首自信--智慧城市创新中心
    * @version V1.0    
    */  
    
package com.sgai.property.wy.dao;

    import com.sgai.common.persistence.CrudDao;
    import com.sgai.property.wy.entity.WyLog;
    import org.apache.ibatis.annotations.Mapper;

    /**
        * @ClassName: LogDao
        * (这里用一句话描述这个类的作用)
        * @author heibin
        * @date 2018年1月29日
        * @Company 首自信--智慧城市创新中心
        */
    @Mapper
    public interface WyLogDao extends CrudDao<WyLog> {


            /**
            * @Title: deleteWyLog
            * (这里用一句话描述这个方法的作用)
            * @param @param wyLog
            * @param @return    参数
            * @return int    返回类型
            * @throws
            */

        int deleteWyLog(WyLog wyLog);

    }
