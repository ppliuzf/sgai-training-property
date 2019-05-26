package com.sgai.property.ctl.dao;

import com.sgai.common.persistence.CrudDao;
import com.sgai.property.ctl.entity.CtlDelCheck;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
  
    /**  
    * @ClassName: CtlDelCheckDao  
    * @Description: 对象删除规则定义DAO接口
    * @author guanze  
    * @date 2017年11月18日  
    * @Company 首自信--智慧城市创新中心  
    */  
    
@Mapper
public interface CtlDelCheckDao extends CrudDao<CtlDelCheck> {
	List<CtlDelCheck> checkDelCheck(CtlDelCheck ctlDelCheck);
	Integer oprTableNameExist(Map<String, String> oprTable);
	List<Map<String, String>> findRelatList(String oprTable);
	String tableRecordExist(Map<String, String> relatList);
}