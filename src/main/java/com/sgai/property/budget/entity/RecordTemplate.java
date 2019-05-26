package com.sgai.property.budget.entity;
import java.util.Date;
import io.swagger.annotations.ApiModelProperty;
import com.sgai.common.persistence.BoEntity;
public class RecordTemplate extends BoEntity<RecordTemplate>{
     
    @ApiModelProperty(value = "模板JSON数据")
    private String templateData; //模板JSON数据
    @ApiModelProperty(value = "计划ID")
    private String recordId; //计划ID
	
    public String getTemplateData(){  
        return templateData;  
    }
      
   public void setTemplateData(String templateData){  
     this.templateData = templateData;  
    }  
    public String getRecordId(){  
        return recordId;  
    }
      
   public void setRecordId(String recordId){  
     this.recordId = recordId;  
    }  
}