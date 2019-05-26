  
    /**    
    * @Title: RepairInformationDto.java  
    * @Package com.sgai.property.wy.entity
    * (用一句话描述该文件做什么)
    * @author Lenovo  
    * @date 2018年6月1日  
    * @Company 首自信--智慧城市创新中心
    * @version V1.0    
    */  
    
package com.sgai.property.wy.entity;

import java.util.Date;

    /**
        * @ClassName: RepairInformationDto
        * (这里用一句话描述这个类的作用)
        * @author Lenovo
        * @date 2018年6月1日
        * @Company 首自信--智慧城市创新中心
        */

    public class RepairInformationDto {
        private Date startTime;
        private Date endTime;
        private String type;
        private String count;
        private Date createTime;
        private String repairTypeCode; // 报修类型code
        private String repairAddressCode; // 报修地址Code
        public Date getStartTime() {
            return startTime;
        }
        public void setStartTime(Date startTime) {
            this.startTime = startTime;
        }
        public Date getEndTime() {
            return endTime;
        }
        public void setEndTime(Date endTime) {
            this.endTime = endTime;
        }
        public String getType() {
            return type;
        }
        public void setType(String type) {
            this.type = type;
        }
        public String getCount() {
            return count;
        }
        public void setCount(String count) {
            this.count = count;
        }
        public Date getCreateTime() {
            return createTime;
        }
        public void setCreateTime(Date createTime) {
            this.createTime = createTime;
        }
        public String getRepairTypeCode() {
            return repairTypeCode;
        }
        public void setRepairTypeCode(String repairTypeCode) {
            this.repairTypeCode = repairTypeCode;
        }
        public String getRepairAddressCode() {
            return repairAddressCode;
        }
        public void setRepairAddressCode(String repairAddressCode) {
            this.repairAddressCode = repairAddressCode;
        }

    }
