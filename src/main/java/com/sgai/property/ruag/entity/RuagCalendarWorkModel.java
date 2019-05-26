package com.sgai.property.ruag.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sgai.common.persistence.BoEntity;


/**
 * 
    * @ClassName: RuagCalendarWorkModel  
    * @com.sgai.property.commonService.vo;(策略日程与策略配置之间的关系Entity)
    * @author 王天尧  
    * @date 2018年1月9日  
    * @Company 首自信--智慧城市创新中心
 */
public class RuagCalendarWorkModel extends BoEntity<RuagCalendarWorkModel> {
	
	private static final long serialVersionUID = 1L;
	private String modelCalendarId;		// model_calendar_id
	private String workModelId;		// work_model_id
	public RuagCalendarWorkModel() {
		super();
	}

	public RuagCalendarWorkModel(String id){
		super(id);
	}

	@Length(min=1, max=60, message="model_calendar_id长度必须介于 1 和 60 之间")
	public String getModelCalendarId() {
		return modelCalendarId;
	}

	public void setModelCalendarId(String modelCalendarId) {
		this.modelCalendarId = modelCalendarId;
	}
	
	@Length(min=1, max=60, message="work_model_id长度必须介于 1 和 60 之间")
	public String getWorkModelId() {
		return workModelId;
	}

	public void setWorkModelId(String workModelId) {
		this.workModelId = workModelId;
	}
}