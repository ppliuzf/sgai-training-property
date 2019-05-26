  
    /**    
    * @Title: PaperConfirm.java  
    * @Package com.sgai.property.wy.entity
    * (用一句话描述该文件做什么)
    * @author Lenovo  
    * @date 2018年2月1日  
    * @Company 首自信--智慧城市创新中心
    * @version V1.0    
    */  
    
package com.sgai.property.wy.entity;

    import com.fasterxml.jackson.annotation.JsonFormat;
    import com.sgai.common.persistence.BoEntity;
    import org.springframework.format.annotation.DateTimeFormat;

    import java.util.Date;

    /**
        * @ClassName: PaperConfirm
        * (这里用一句话描述这个类的作用)
        * @author Lenovo
        * @date 2018年2月1日
        * @Company 首自信--智慧城市创新中心
        */

    public class PaperConfirm extends BoEntity<PaperConfirm> {
          /**
            * @Fields field:field:(用一句话描述这个变量表示什么)
            */

        private static final long serialVersionUID = 1L;
        private String subId;//报刊杂志ID
        private String receptUserId;//接收人ID
        private String recNumber;//份数
        @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd ")
        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
        private Date createTime;//填写时间
        public String getSubId() {
            return subId;
        }
        public void setSubId(String subId) {
            this.subId = subId;
        }
        public String getReceptUserId() {
            return receptUserId;
        }
        public void setReceptUserId(String receptUserId) {
            this.receptUserId = receptUserId;
        }
        public String getRecNumber() {
            return recNumber;
        }
        public void setRecNumber(String recNumber) {
            this.recNumber = recNumber;
        }
        public Date getCreateTime() {
            return createTime;
        }
        public void setCreateTime(Date createTime) {
            this.createTime = createTime;
        }
    }
