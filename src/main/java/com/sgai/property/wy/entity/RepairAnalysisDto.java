  
    /**    
    * @Title: RepairAnalysisDto.java  
    * @Package com.sgai.property.wy.entity
    * (用一句话描述该文件做什么)
    * @author cui  
    * @date 2018年6月6日  
    * @Company 首自信--智慧城市创新中心
    * @version V1.0    
    */  
    
package com.sgai.property.wy.entity;

import java.util.Date;

    /**
        * @ClassName: RepairAnalysisDto
        * (这里用一句话描述这个类的作用)
        * @author cui
        * @date 2018年6月6日
        * @Company 首自信--智慧城市创新中心
        */

    public class RepairAnalysisDto {
            private String sumRepair;
            private String xTime;
            private Date startDate;
            private Date endDate;
            private String dateType;
            private String repairType;
            private String complainID;

            public String getComplainID() {
                return complainID;
            }
            public void setComplainID(String complainID) {
                this.complainID = complainID;
            }
            public String getRepairType() {
                return repairType;
            }
            public void setRepairType(String repairType) {
                this.repairType = repairType;
            }
            public String getSumRepair() {
                return sumRepair;
            }
            public void setSumRepair(String sumRepair) {
                this.sumRepair = sumRepair;
            }
            public String getxTime() {
                return xTime;
            }
            public void setxTime(String xTime) {
                this.xTime = xTime;
            }
            public Date getStartDate() {
                return startDate;
            }
            public void setStartDate(Date startDate) {
                this.startDate = startDate;
            }
            public Date getEndDate() {
                return endDate;
            }
            public void setEndDate(Date endDate) {
                this.endDate = endDate;
            }
            public String getDateType() {
                return dateType;
            }
            public void setDateType(String dateType) {
                this.dateType = dateType;
            }

    }
