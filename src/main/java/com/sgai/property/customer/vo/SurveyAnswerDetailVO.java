  
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
    import java.util.List;

    public class SurveyAnswerDetailVO implements Serializable {


            /**
            * @Fields field:field:(用一句话描述这个变量表示什么)
            */

        private static final long serialVersionUID = -2704103046456951697L;

        @ApiModelProperty(value = "问卷开始时间")
        private String smStartTime;
        @ApiModelProperty(value = "问卷名称")
        private String smName;
        @ApiModelProperty(value = "问卷结束时间")
        private String smEndTime;
        @ApiModelProperty(value = "问卷题目总数量")
        private Long smCount;
        @ApiModelProperty(value = "问卷题目详情")
        private List<SurveyQuestionAnswerDetailVO> surveyQuestionAnswerDetailVOs;
        public String getSmStartTime() {
            return smStartTime;
        }
        public void setSmStartTime(String smStartTime) {
            this.smStartTime = smStartTime;
        }
        public String getSmName() {
            return smName;
        }
        public void setSmName(String smName) {
            this.smName = smName;
        }
        public String getSmEndTime() {
            return smEndTime;
        }
        public void setSmEndTime(String smEndTime) {
            this.smEndTime = smEndTime;
        }
        public Long getSmCount() {
            return smCount;
        }
        public void setSmCount(Long smCount) {
            this.smCount = smCount;
        }
        public List<SurveyQuestionAnswerDetailVO> getSurveyQuestionAnswerDetailVOs() {
            return surveyQuestionAnswerDetailVOs;
        }
        public void setSurveyQuestionAnswerDetailVOs(List<SurveyQuestionAnswerDetailVO> surveyQuestionAnswerDetailVOs) {
            this.surveyQuestionAnswerDetailVOs = surveyQuestionAnswerDetailVOs;
        }

    }
