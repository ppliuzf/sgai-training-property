  
    /**    
    * @Title: EmEventInfoVo.java  
    * @Package com.sgai.property.customer.vo
    * @Description: (用一句话描述该文件做什么)
    * @author syswin  
    * @date 2018年6月12日  
    * @Company 首自信--智慧城市创新中心
    * @version V1.0    
    */  
    
package com.sgai.property.customer.vo;

    import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
    import com.fasterxml.jackson.databind.annotation.JsonSerialize;
    import com.sgai.common.utils.DateJsonDeserializer;
    import com.sgai.common.utils.DateJsonSerializer;
    import io.swagger.annotations.ApiModelProperty;

    import java.io.Serializable;
    import java.util.Date;

    /**
        * @ClassName: EmEventInfoVo
        * @Description: (这里用一句话描述这个类的作用)
        * @author syswin
        * @date 2018年6月12日
        * @Company 首自信--智慧城市创新中心
        */

    public class EmEventInfoVo implements Serializable {


            /**
            * @Fields field:field:(用一句话描述这个变量表示什么)
            */

        private static final long serialVersionUID = -3460469793159684553L;
        @ApiModelProperty(value = "流程实例id 流程实例编号，目前传emCode")
        private String instanceId;		// 流程实例编号，目前传emCode
        @ApiModelProperty(value = "emCode（事件编码）")
        private String emCode;		// 对应于业务事件的编码，例如：应急事件、保修事件、投诉事件等
        @ApiModelProperty(value = "emType（事件类型）事件类型TS、AB、WX")
        private String emType;		// 保修事件投诉事件报警事件
        @ApiModelProperty(value = "核实人、投诉人")
        private String person;		// 核实人
        @ApiModelProperty(value = "时间")
        @JsonSerialize(using=DateJsonSerializer.class)
        @JsonDeserialize(using=DateJsonDeserializer.class)
        private Date date;		// 时间
        @ApiModelProperty(value = "内容")
        private String content;		// 内容
        @ApiModelProperty(value = "是否上报")
        private String isReport;		// 是否上报
        @ApiModelProperty(value = "预案选择")
        private String choosePlan;		// 预案选择
        @ApiModelProperty(value = "是否完成")
        private String endFlag;		// 未完成：N完成：Y
        @ApiModelProperty(value = "是否可用")
        private String enabledFlag;		// 1.选项为:'Y':是'N':否默认为'Y'
        @ApiModelProperty(value = "版本号")
        private Integer version;		// 版本号
        @ApiModelProperty(value = "修改时间")
        private Date updatedDt;		// 修改时间
        @ApiModelProperty(value = "修改人")
        private String updatedBy;		// 修改人
        @ApiModelProperty(value = "创建日期")
        private Date createdDt;		// 创建日期
        @ApiModelProperty(value = "创建者")
        private String createdBy;		// 创建者
        public String getInstanceId() {
            return instanceId;
        }
        public void setInstanceId(String instanceId) {
            this.instanceId = instanceId;
        }
        public String getEmCode() {
            return emCode;
        }
        public void setEmCode(String emCode) {
            this.emCode = emCode;
        }
        public String getEmType() {
            return emType;
        }
        public void setEmType(String emType) {
            this.emType = emType;
        }
        public String getPerson() {
            return person;
        }
        public void setPerson(String person) {
            this.person = person;
        }
        public Date getDate() {
            return date;
        }
        public void setDate(Date date) {
            this.date = date;
        }
        public String getContent() {
            return content;
        }
        public void setContent(String content) {
            this.content = content;
        }
        public String getIsReport() {
            return isReport;
        }
        public void setIsReport(String isReport) {
            this.isReport = isReport;
        }
        public String getChoosePlan() {
            return choosePlan;
        }
        public void setChoosePlan(String choosePlan) {
            this.choosePlan = choosePlan;
        }
        public String getEndFlag() {
            return endFlag;
        }
        public void setEndFlag(String endFlag) {
            this.endFlag = endFlag;
        }
        public String getEnabledFlag() {
            return enabledFlag;
        }
        public void setEnabledFlag(String enabledFlag) {
            this.enabledFlag = enabledFlag;
        }
        public Integer getVersion() {
            return version;
        }
        public void setVersion(Integer version) {
            this.version = version;
        }
        public Date getUpdatedDt() {
            return updatedDt;
        }
        public void setUpdatedDt(Date updatedDt) {
            this.updatedDt = updatedDt;
        }
        public String getUpdatedBy() {
            return updatedBy;
        }
        public void setUpdatedBy(String updatedBy) {
            this.updatedBy = updatedBy;
        }
        public Date getCreatedDt() {
            return createdDt;
        }
        public void setCreatedDt(Date createdDt) {
            this.createdDt = createdDt;
        }
        public String getCreatedBy() {
            return createdBy;
        }
        public void setCreatedBy(String createdBy) {
            this.createdBy = createdBy;
        }



    }
