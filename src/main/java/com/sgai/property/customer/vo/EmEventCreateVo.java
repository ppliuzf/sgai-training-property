  
    /**    
    * @Title: EmEventCreateVo.java  
    * @Package com.sgai.property.customer.vo
    * @Description: (用一句话描述该文件做什么)
    * @author syswin  
    * @date 2018年6月14日  
    * @Company 首自信--智慧城市创新中心
    * @version V1.0    
    */  
    
package com.sgai.property.customer.vo;

    import com.sgai.property.ctl.entity.CtlAttachfile;
    import io.swagger.annotations.ApiModelProperty;

    import java.io.Serializable;
    import java.util.List;

    /**
        * @ClassName: EmEventCreateVo
        * @Description: (这里用一句话描述这个类的作用)
        * @author syswin
        * @date 2018年6月14日
        * @Company 首自信--智慧城市创新中心
        */

    public class EmEventCreateVo implements Serializable {


            /**
            * @Fields field:field:(用一句话描述这个变量表示什么)
            */

        private static final long serialVersionUID = 8602279082586986667L;

        @ApiModelProperty(value = "编号")
        private String emCode;		// 编号
        @ApiModelProperty(value = "类型")
        private String emType;		//类型 TS
        @ApiModelProperty(value = "投诉人，报案人")
        private String emPerson;		// 投诉人，报案人
        @ApiModelProperty(value = "联系电话")
        private String telphone;		// 联系电话
        @ApiModelProperty(value = "投诉地址")
        private String address;		// 投诉地址
        @ApiModelProperty(value = "投诉时间")
        private String emTime;		// 投诉时间
        @ApiModelProperty(value = "投诉内容")
        private String emContent;		// 投诉内容
        @ApiModelProperty(value = "说明")
        private String desc;		// 说明
        @ApiModelProperty(value = "附件")
        private List<CtlAttachfile> files;
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
        public String getEmPerson() {
            return emPerson;
        }
        public void setEmPerson(String emPerson) {
            this.emPerson = emPerson;
        }
        public String getTelphone() {
            return telphone;
        }
        public void setTelphone(String telphone) {
            this.telphone = telphone;
        }
        public String getAddress() {
            return address;
        }
        public void setAddress(String address) {
            this.address = address;
        }
        public String getEmTime() {
            return emTime;
        }
        public void setEmTime(String emTime) {
            this.emTime = emTime;
        }
        public String getEmContent() {
            return emContent;
        }
        public void setEmContent(String emContent) {
            this.emContent = emContent;
        }
        public String getDesc() {
            return desc;
        }
        public void setDesc(String desc) {
            this.desc = desc;
        }
        public List<CtlAttachfile> getFiles() {
            return files;
        }
        public void setFiles(List<CtlAttachfile> files) {
            this.files = files;
        }


    }
