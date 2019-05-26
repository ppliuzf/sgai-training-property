  
    /**    
    * @Title: NewsManageDao.java  
    * @Package com.sgai.property.wy.dao
    * (用一句话描述该文件做什么)
    * @author XJ9001  
    * @date 2018年2月1日  
    * @Company 首自信--智慧城市创新中心
    * @version V1.0    
    */  
    
package com.sgai.property.wy.dao;

    import com.sgai.common.persistence.CrudDao;
    import com.sgai.property.wy.entity.NewsManage;
    import org.apache.ibatis.annotations.Mapper;

    import java.util.List;


    /**  
 * @ClassName: NewsManageDao  
 * (这里用一句话描述这个类的作用)
 * @author XJ9001  
 * @date 2018年2月1日  
 * @Company 首自信--智慧城市创新中心  
 */
@Mapper
public interface NewsManageDao extends CrudDao<NewsManage> {

		  
		    /**  
		    * @Title: upNewsStatus  
		    * (这里用一句话描述这个方法的作用)
		    * @param @param nm    参数  
		    * @return void    返回类型  
		    * @throws  
		    */  
		    
		void upNewsStatus(NewsManage nm);

			  
			    /**  
			    * @Title: getWorkloadList  
			    * (这里用一句话描述这个方法的作用)
			    * @param @param newsManage
			    * @param @return    参数  
			    * @return List<NewsManage>    返回类型  
			    * @throws  
			    */  
			    
			List<NewsManage> getWorkloadList(NewsManage newsManage);


				  
				    /**  
				    * @Title: findCode  
				    * (这里用一句话描述这个方法的作用)
				    * @param @param newsManage2
				    * @param @return    参数  
				    * @return NewsManage    返回类型  
				    * @throws  
				    */  
				    
				NewsManage findCode(NewsManage newsManage2);


					  
					    /**  
					    * @Title: insertCauseHistory  
					    * (这里用一句话描述这个方法的作用)
					    * @param @param nm    参数  
					    * @return void    返回类型  
					    * @throws  
					    */  
					    
					void insertCauseHistory(NewsManage nm);


						  
						    /**  
						    * @Title: getHistoryList  
						    * (这里用一句话描述这个方法的作用)
						    * @param @param newsManage
						    * @param @return    参数  
						    * @return List<NewsManage>    返回类型  
						    * @throws  
						    */  
						    
						List<NewsManage> getHistoryList(NewsManage newsManage);

			  

}
