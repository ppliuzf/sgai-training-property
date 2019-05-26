package com.sgai.property.quality.entity;

import com.sgai.common.persistence.BoEntity;
import io.swagger.annotations.ApiModelProperty;
public class JhMonth extends BoEntity<JhMonth>{
     
    @ApiModelProperty(value = "结束时间 存 时：分")
    private String endTime; //结束时间 存 时：分
    @ApiModelProperty(value = "计划Id")
    private String testId; //计划Id
    @ApiModelProperty(value = "开始时间 存 时:分")
    private String beginTime; //开始时间 存 时:分
    @ApiModelProperty(value = "每月的结束日期   存 日")
    private String endDate; //每月的结束日期   存 日
    @ApiModelProperty(value = "每月的天  存 日")
    private String beginDate; //每月的天  存 日
    @ApiModelProperty(value = "年份")
    private String jhYear; //年份
    @ApiModelProperty(value = "开始月份")
    private String beginMonth;
    @ApiModelProperty(value = "结束月份")
    private String endMonth;
	
    public String getEndTime(){  
        return endTime;  
    }
      
   public void setEndTime(String endTime){  
     this.endTime = endTime;  
    }  
    public String getTestId(){  
        return testId;  
    }
      
   public void setTestId(String testId){  
     this.testId = testId;  
    }  
    public String getBeginTime(){  
        return beginTime;  
    }
      
   public void setBeginTime(String beginTime){  
     this.beginTime = beginTime;  
    }  
    public String getEndDate(){  
        return endDate;  
    }
      
   public void setEndDate(String endDate){  
     this.endDate = endDate;  
    }  
    public String getBeginDate(){  
        return beginDate;  
    }
      
   public void setBeginDate(String beginDate){  
     this.beginDate = beginDate;  
    }

    public String getJhYear() {
        return jhYear;
    }

    public void setJhYear(String jhYear) {
        this.jhYear = jhYear;
    }

    public String getBeginMonth() {
        return beginMonth;
    }

    public void setBeginMonth(String beginMonth) {
        this.beginMonth = beginMonth;
    }

    public String getEndMonth() {
        return endMonth;
    }

    public void setEndMonth(String endMonth) {
        this.endMonth = endMonth;
    }
}