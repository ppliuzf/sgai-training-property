package com.sgai.property.customer.vo;


import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class SurveyUserAnswerVO implements Serializable {

      
	    /**  
	    * @Fields field:field:(用一句话描述这个变量表示什么)
	    */  
	    
	private static final long serialVersionUID = 8214286406383669878L;
	@ApiModelProperty(value = "答案类型(0,单选,1,多选,2,文本)")
    private Long saType;
    @ApiModelProperty(value = "所填答案内容")
    private String saContent;
    @ApiModelProperty(value = "关联的问卷表ID")
    private String smId;
    @ApiModelProperty(value = "关联的问题表ID")
    private String sqId;

	public Long getSaType() {
        return saType;
    }

    public void setSaType(Long saType) {
        this.saType = saType;
    }

    public String getSaContent() {
        return saContent;
    }

    public void setSaContent(String saContent) {
        this.saContent = saContent;
    }

    public String getSmId() {
        return smId;
    }

    public void setSmId(String smId) {
        this.smId = smId;
    }

    public String getSqId() {
        return sqId;
    }

    public void setSqId(String sqId) {
        this.sqId = sqId;
    }

}