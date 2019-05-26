  
    /**    
    * @Title: RepairTargetDto.java  
    * @Package com.sgai.property.wy.entity
    * (用一句话描述该文件做什么)
    * @author Lenovo  
    * @date 2018年6月8日  
    * @Company 首自信--智慧城市创新中心
    * @version V1.0    
    */  
    
package com.sgai.property.wy.entity;

import java.util.Date;

    /**
        * @ClassName: RepairTargetDto
        * (这里用一句话描述这个类的作用)
        * @author Lenovo
        * @date 2018年6月8日
        * @Company 首自信--智慧城市创新中心
        */

    public class RepairTargetDto {
        private String type;
        private String name;
        private String count;
        private float count1;
        private Date  presentDate;
        private Date createDate;
        private Date startTime;//开始时间
        private String type2;
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
        private Date endTime;//结束时间
        public String getType() {
            return type;
        }
        public void setType(String type) {
            this.type = type;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public String getCount() {
            return count;
        }
        public void setCount(String count) {
            this.count = count;
        }
        public float getCount1() {
            return count1;
        }
        public void setCount1(float count1) {
            this.count1 = count1;
        }
        public Date getPresentDate() {
            return presentDate;
        }
        public void setPresentDate(Date presentDate) {
            this.presentDate = presentDate;
        }
        public Date getCreateDate() {
            return createDate;
        }
        public void setCreateDate(Date createDate) {
            this.createDate = createDate;
        }
        public String getType2() {
            return type2;
        }
        public void setType2(String type2) {
            this.type2 = type2;
        }

    }
