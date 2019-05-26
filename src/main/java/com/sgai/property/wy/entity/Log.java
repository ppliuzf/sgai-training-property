  
    /**    
    * @Title: Log.java  
    * @Package com.sgai.property.wy.entity
    * (用一句话描述该文件做什么)
    * @author Lenovo  
    * @date 2018年1月29日  
    * @Company 首自信--智慧城市创新中心
    * @version V1.0    
    */  
    
package com.sgai.property.wy.entity;

    import com.sgai.common.persistence.BoEntity;

    import java.util.Date;

    /**
        * @ClassName: Log
        * (这里用一句话描述这个类的作用)
        * @author heibin
        * @date 2018年1月29日
        * @Company 首自信--智慧城市创新中心
        */

    public class Log extends BoEntity<Log> {

            /**
            * @Fields field:field:(用一句话描述这个变量表示什么)
            */

        private static final long serialVersionUID = 1L;
        private String userId;//记录人id
        private Date createTime;//记录时间
        private String area;//区域
        private String hour;//记录小时分钟
        private String content;//工作内容
        private String processing_person_id;//处理人id
        private String describe;//处理描述
        private String remarks;//备注
        public String getUserId() {
            return userId;
        }
        public void setUserId(String userId) {
            this.userId = userId;
        }
        public Date getCreateTime() {
            return createTime;
        }
        public void setCreateTime(Date createTime) {
            this.createTime = createTime;
        }
        public String getArea() {
            return area;
        }
        public void setArea(String area) {
            this.area = area;
        }
        public String getHour() {
            return hour;
        }
        public void setHour(String hour) {
            this.hour = hour;
        }
        public String getContent() {
            return content;
        }
        public void setContent(String content) {
            this.content = content;
        }
        public String getProcessing_person_id() {
            return processing_person_id;
        }
        public void setProcessing_person_id(String processing_person_id) {
            this.processing_person_id = processing_person_id;
        }
        public String getDescribe() {
            return describe;
        }
        public void setDescribe(String describe) {
            this.describe = describe;
        }
        public String getRemarks() {
            return remarks;
        }
        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

    }
