  
    /**    
    * @Title: CtlAttachfileDao.java  
    * @Package com.sgai.modules.ctl.dao  
    * @Description: (用一句话描述该文件做什么)
    * @author smartcity  
    * @date 2017年12月31日  
    * @Company 首自信--智慧城市创新中心
    * @version V1.0    
    */  
    
package com.sgai.property.ctl.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import com.sgai.common.persistence.CrudDao;
import com.sgai.property.ctl.entity.CtlAttachfile;
/**  
    * @ClassName: CtlAttachfileDao  
    * @Description: (这里用一句话描述这个类的作用)
    * @author chenxing  
    * @date 2017年12月31日  
    * @Company 首自信--智慧城市创新中心  
    */
@Mapper
public interface CtlAttachfileDao extends CrudDao<CtlAttachfile>{
	void deleteAttachfiles(List<String> list);
	void saveAttachfiles(List<CtlAttachfile> list);
	void deleteByMasterFileId(Map<String, Object> params);
}
