package com.sgai.property.customer.vo;


import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

public class SurveyUserAnswerPcVO implements Serializable {

      
	    /**  
	    * @Fields field:field:{}(用一句话描述这个变量表示什么)
	    */  
	    
	private static final long serialVersionUID = 8214286406383669878L;
	private List<SurveyUserAnswerVO> surveyUserAnswerVOs;
    @ApiModelProperty(value = "问卷人姓名")
    private String userName;
    @ApiModelProperty(value = "问卷人手机号")
    private String userPhone;

    public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public List<SurveyUserAnswerVO> getSurveyUserAnswerVOs() {
		return surveyUserAnswerVOs;
	}

	public void setSurveyUserAnswerVOs(List<SurveyUserAnswerVO> surveyUserAnswerVOs) {
		this.surveyUserAnswerVOs = surveyUserAnswerVOs;
	}

	

}