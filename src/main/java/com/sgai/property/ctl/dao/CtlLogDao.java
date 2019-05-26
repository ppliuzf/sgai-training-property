package com.sgai.property.ctl.dao;

import com.sgai.common.persistence.CrudDao;
import com.sgai.property.ctl.entity.CtlLog;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
  
    /**  
    * @ClassName: CtlLogDao  
    * @Description: 在线用户管理DAO接口
    * @author guanze  
    * @date 2017年11月18日  
    * @Company 首自信--智慧城市创新中心  
    */  
    
@Mapper
public interface CtlLogDao extends CrudDao<CtlLog> {
	List<Map<String,String>> findLogDetail(CtlLog ctlLog);
	List<Map<String, String>> getComList();
	List<Map<String, String>> getComFromUserList(String comCode);
}