package com.sgai.property.quality.vo.plan;

import io.swagger.annotations.ApiModelProperty;

public class JhDayVo {
     
    @ApiModelProperty(value = "计划Id")
    private String testId; //计划Id
    @ApiModelProperty(value = "开始日期")
    private String beginDate; //开始日期
    @ApiModelProperty(value = "开始时间")
    private String beginTime; //开始时间
    @ApiModelProperty(value = "结束时间")
    private String endTime; //结束时间
    @ApiModelProperty(value = "截止日期")
    private String endDate; //截止日期
	
    public String getTestId(){  
        return testId;  
    }
      
   public void setTestId(String testId){  
     this.testId = testId;  
    }  
    public String getBeginDate(){  
        return beginDate;  
    }
      
   public void setBeginDate(String beginDate){  
     this.beginDate = beginDate;  
    }  
    public String getBeginTime(){  
        return beginTime;  
    }
      
   public void setBeginTime(String beginTime){  
     this.beginTime = beginTime;  
    }  
    public String getEndTime(){  
        return endTime;  
    }
      
   public void setEndTime(String endTime){  
     this.endTime = endTime;  
    }  
    public String getEndDate(){  
        return endDate;  
    }
      
   public void setEndDate(String endDate){  
     this.endDate = endDate;  
    }  
}