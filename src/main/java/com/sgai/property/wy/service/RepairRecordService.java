  
    /**    
    * @Title: RepairRecordService.java  
    * @Package com.sgai.property.wy.service
    * (用一句话描述该文件做什么)
    * @author XJ9001  
    * @date 2018年1月24日  
    * @Company 首自信--智慧城市创新中心
    * @version V1.0    
    */  
    
package com.sgai.property.wy.service;


    import com.sgai.common.service.CrudServiceExt;
    import com.sgai.property.wy.dao.RepairInformationDao;
    import com.sgai.property.wy.dao.RepairRecordDao;
    import com.sgai.property.wy.entity.RepairRecord;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;
    import org.springframework.transaction.annotation.Transactional;


    /**  
 * @ClassName: RepairRecordService  
 * (这里用一句话描述这个类的作用)
 * @author XJ9001  
 * @date 2018年1月24日  
 * @Company 首自信--智慧城市创新中心  
 */
@Service
public class RepairRecordService extends CrudServiceExt<RepairRecordDao, RepairRecord> {
	
	@Autowired
	private RepairInformationDao repairInformationDao;

	@Transactional(readOnly = false)
	public void save(RepairRecord repairRecord) {
		super.save(repairRecord);
		
	}
}
