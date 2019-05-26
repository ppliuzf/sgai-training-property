  
    /**    
    * @Title: ClientManageService.java  
    * @Package com.sgai.property.wy.service
    * (用一句话描述该文件做什么)
    * @author XJ9001  
    * @date 2018年1月31日  
    * @Company 首自信--智慧城市创新中心
    * @version V1.0    
    */  
    
package com.sgai.property.wy.service;

    import com.sgai.common.service.CrudServiceExt;
    import com.sgai.property.wy.dao.ClientManageDao;
    import com.sgai.property.wy.entity.ClientManage;
    import org.springframework.stereotype.Service;

    import java.util.Map;


    /**  
 * @ClassName: ClientManageService  
 * (这里用一句话描述这个类的作用)
 * @author XJ9001  
 * @date 2018年1月31日  
 * @Company 首自信--智慧城市创新中心  
 */
@Service
public class ClientManageService extends CrudServiceExt<ClientManageDao, ClientManage> {

		  
		    /**  
		    * @Title: deleteClient  
		    * (这里用一句话描述这个方法的作用)
		    * @param @param ids
		    * @param @return    参数  
		    * @return Map<String,Object>    返回类型  
		    * @throws  
		    */  
		    
		public Map<String, Object> deleteClient(String ids) {
			//
			return null;
		}

}
