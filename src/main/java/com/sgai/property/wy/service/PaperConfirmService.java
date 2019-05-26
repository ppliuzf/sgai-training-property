  
    /**    
    * @Title: PaperConfirmService.java  
    * @Package com.sgai.property.wy.service
    * (用一句话描述该文件做什么)
    * @author Lenovo  
    * @date 2018年2月1日  
    * @Company 首自信--智慧城市创新中心
    * @version V1.0    
    */  
    
package com.sgai.property.wy.service;

    import com.sgai.common.persistence.Page;
    import com.sgai.common.service.CrudServiceExt;
    import com.sgai.property.wy.dao.PaperConfirmDao;
    import com.sgai.property.wy.entity.PaperConfirm;
    import org.springframework.stereotype.Service;

    import java.util.List;

    /**
        * @ClassName: PaperConfirmService
        * (这里用一句话描述这个类的作用)
        * @author Lenovo
        * @date 2018年2月1日
        * @Company 首自信--智慧城市创新中心
        */
    @Service
    public class PaperConfirmService extends CrudServiceExt<PaperConfirmDao,PaperConfirm> {


            /**
            * @Title: findPage
            * (这里用一句话描述这个方法的作用)
            * @param @param page
            * @param @param paperConfirm
            * @param @return    参数
            * @return Page<PaperConfirm>    返回类型
            * @throws
            */

        public Page<PaperConfirm> findPage(Page<PaperConfirm> page, PaperConfirm paperConfirm) {
            return super.findPage(page, paperConfirm);
        }


                /**
                * @Title: getPaperConfirmById
                * (这里用一句话描述这个方法的作用)
                * @param @param paperConfirm
                * @param @return    参数
                * @return List<PaperConfirm>    返回类型
                * @throws
                */

            public List<PaperConfirm> getPaperConfirmById(PaperConfirm paperConfirm) {
                return dao.getPaperConfirmById(paperConfirm);
            }


    }
