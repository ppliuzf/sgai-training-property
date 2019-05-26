  
    /**    
    * @Title: RepairRecord.java  
    * @Package com.sgai.property.wy.entity
    * (用一句话描述该文件做什么)
    * @author XJ9001  
    * @date 2018年1月24日  
    * @Company 首自信--智慧城市创新中心
    * @version V1.0    
    */  
    
package com.sgai.property.wy.entity;

    import com.fasterxml.jackson.annotation.JsonFormat;
    import com.sgai.common.persistence.BoEntity;
    import org.springframework.format.annotation.DateTimeFormat;

    import java.util.Date;


    /**  
 * @ClassName: RepairRecord  
 * @Description: 报修记录 
 * @author XJ9001  
 * @date 2018年1月24日  
 * @Company 首自信--智慧城市创新中心  
 */

public class RepairRecord extends BoEntity<RepairRecord> {
		    
		private static final long serialVersionUID = 1L;
		
		private String repairLog;  //记录状态
		@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
		@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
		private Date presentDate;  //记录时间
		
		private String repairId;  //报修ID
		
		private String cause; //记录原因

		private String appraiseNorm;  //评价标准
		
		private String maintainPersonId; //退单人ID（维修人员）

		private String maintainPersonName; //退单人名
		
		private String repairStatus;//报修状态
		
		
		public String getRepairStatus() {
			return repairStatus;
		}

		public void setRepairStatus(String repairStatus) {
			this.repairStatus = repairStatus;
		}

		public String getRepairLog() {
			return repairLog;
		}

		public void setRepairLog(String repairLog) {
			this.repairLog = repairLog;
		}
		public Date getPresentDate() {
			return presentDate;
		}

		public void setPresentDate(Date presentDate) {
			this.presentDate = presentDate;
		}

		public String getRepairId() {
			return repairId;
		}

		public void setRepairId(String repairId) {
			this.repairId = repairId;
		}

		public String getCause() {
			return cause;
		}

		public void setCause(String cause) {
			this.cause = cause;
		}

		public String getAppraiseNorm() {
			return appraiseNorm;
		}

		public void setAppraiseNorm(String appraiseNorm) {
			this.appraiseNorm = appraiseNorm;
		}

		public String getMaintainPersonId() {
			return maintainPersonId;
		}

		public void setMaintainPersonId(String maintainPersonId) {
			this.maintainPersonId = maintainPersonId;
		}

		public String getMaintainPersonName() {
			return maintainPersonName;
		}

		public void setMaintainPersonName(String maintainPersonName) {
			this.maintainPersonName = maintainPersonName;
		}
		
		

}
