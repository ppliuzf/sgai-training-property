  
    /**    
    * @Title: SurveyQuestionVO.java  
    * @Package com.sgai.property.customer.vo
    * @Description: (用一句话描述该文件做什么)
    * @author syswin  
    * @date 2018年3月1日  
    * @Company 首自信--智慧城市创新中心
    * @version V1.0    
    */  
    
package com.sgai.property.customer.vo;

    import io.swagger.annotations.ApiModelProperty;

    import java.io.Serializable;

    public class SurveyQuestionAnswerDetailVO implements Serializable {


            /**
            * @Fields field:field:(用一句话描述这个变量表示什么)
            */

        private static final long serialVersionUID = -2704103046456951697L;
        @ApiModelProperty(value = "问题序号,数字,用来排序")
        private Long sqNo;
        @ApiModelProperty(value = "问卷题目总数量")
        private Long smCount;
        @ApiModelProperty(value = "所填答案内容")
        private String saContent;
        @ApiModelProperty(value = "问题标题")
        private String sqTopic;
        @ApiModelProperty(value = "问题类型(0,单选,1,多选,2,文本)")
        private Long sqType;
        @ApiModelProperty(value = "选项内容")
        private String soContent;
        public Long getSqNo() {
            return sqNo;
        }
        public void setSqNo(Long sqNo) {
            this.sqNo = sqNo;
        }
        public Long getSmCount() {
            return smCount;
        }
        public void setSmCount(Long smCount) {
            this.smCount = smCount;
        }
        public String getSaContent() {
            return saContent;
        }
        public void setSaContent(String saContent) {
            this.saContent = saContent;
        }
        public String getSqTopic() {
            return sqTopic;
        }
        public void setSqTopic(String sqTopic) {
            this.sqTopic = sqTopic;
        }
        public Long getSqType() {
            return sqType;
        }
        public void setSqType(Long sqType) {
            this.sqType = sqType;
        }
        public String getSoContent() {
            return soContent;
        }
        public void setSoContent(String soContent) {
            this.soContent = soContent;
        }


    }
