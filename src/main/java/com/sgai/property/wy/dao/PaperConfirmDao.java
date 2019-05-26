  
    /**    
    * @Title: PaperConfirmDao.java  
    * @Package com.sgai.property.wy.dao
    * (用一句话描述该文件做什么)
    * @author Lenovo  
    * @date 2018年2月1日  
    * @Company 首自信--智慧城市创新中心
    * @version V1.0    
    */  
    
package com.sgai.property.wy.dao;

    import com.sgai.common.persistence.CrudDao;
    import com.sgai.property.wy.entity.PaperConfirm;
    import org.apache.ibatis.annotations.Mapper;

    import java.util.List;

    /**
        * @ClassName: PaperConfirmDao
        * (这里用一句话描述这个类的作用)
        * @author Lenovo
        * @date 2018年2月1日
        * @Company 首自信--智慧城市创新中心
        */
    @Mapper
    public interface PaperConfirmDao extends CrudDao<PaperConfirm> {


            /**
            * @Title: getPaperConfirmById
            * (这里用一句话描述这个方法的作用)
            * @param @param paperConfirm
            * @param @return    参数
            * @return List<PaperConfirm>    返回类型
            * @throws
            */

        List<PaperConfirm> getPaperConfirmById(PaperConfirm paperConfirm);

    }
