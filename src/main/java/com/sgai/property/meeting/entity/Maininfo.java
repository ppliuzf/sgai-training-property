package com.sgai.property.meeting.entity;

import java.util.Date;

import com.sgai.common.persistence.BoEntity;

import io.swagger.annotations.ApiModelProperty;

public class Maininfo extends BoEntity<Maininfo>{

    @ApiModelProperty(value = "创建人ID")
    private String createEiId; //创建人ID
    @ApiModelProperty(value = "提前分钟数")
    private Integer miRemindMin; //提前分钟数
    @ApiModelProperty(value = "创建时间")
    private Long createTime; //创建时间
    @ApiModelProperty(value = "是否周例会1 是 0否")
    private Long miIsWeekMeeting; //是否周例会1 是 0否
    @ApiModelProperty(value = "会议日期")
    private Long miMtDate; //会议日期
    @ApiModelProperty(value = "主持人")
    private String compereEiName; //主持人
    @ApiModelProperty(value = "会议简要")
    private String miMtContent; //会议简要
    @ApiModelProperty(value = "会议时间段")
    private String miMtTimeSeg; //会议时间段
    @ApiModelProperty(value = "公司ID")
    private String comId; //公司ID
    @ApiModelProperty(value = "创建人")
    private String createEiName; //创建人
    @ApiModelProperty(value = "是否开启提醒")
    private Integer miIsRemind; //是否开启提醒
    @ApiModelProperty(value = "1 未开始 2执行中 3已结束 4已逾期5已取消")
    private Integer miStatus; //1 未开始 2执行中 3已结束 4已逾期5已取消
    @ApiModelProperty(value = "重复次数")
    private Long miRepeatNum; //重复次数
    @ApiModelProperty(value = "会议主题")
    private String miMtSubject; //会议主题
    @ApiModelProperty(value = "更新时间")
    private Long updateTime; //更新时间
    @ApiModelProperty(value = "会议室ID")
    private String rrId; //会议室ID
    @ApiModelProperty(value = "是否删除1 是 0 否")
    private Long isDelete; //是否删除1 是 0 否
    @ApiModelProperty(value = "主持人ID")
    private Long compereEiId; //主持人ID
    @ApiModelProperty(value = "会议开始时间")
    private Date miStartTime; //
    @ApiModelProperty(value = "会议结束时间")
    private Date miEndTime; //

    public Date getMiEndTime() {
        return miEndTime;
    }

    public void setMiEndTime(Date miEndTime) {
        this.miEndTime = miEndTime;
    }

    public String getCreateEiId(){
        return createEiId;  
    }
      
   public void setCreateEiId(String createEiId){  
     this.createEiId = createEiId;  
    }
    public Integer getMiRemindMin(){  
        return miRemindMin;  
    }
      
   public void setMiRemindMin(Integer miRemindMin){  
     this.miRemindMin = miRemindMin;  
    }
    public Long getCreateTime(){  
        return createTime;  
    }
      
   public void setCreateTime(Long createTime){  
     this.createTime = createTime;  
    }
    public Long getMiIsWeekMeeting(){  
        return miIsWeekMeeting;  
    }
      
   public void setMiIsWeekMeeting(Long miIsWeekMeeting){  
     this.miIsWeekMeeting = miIsWeekMeeting;  
    }
    public Long getMiMtDate(){  
        return miMtDate;  
    }
      
   public void setMiMtDate(Long miMtDate){  
     this.miMtDate = miMtDate;  
    }
    public String getCompereEiName(){  
        return compereEiName;  
    }
      
   public void setCompereEiName(String compereEiName){  
     this.compereEiName = compereEiName;  
    }
    public String getMiMtContent(){  
        return miMtContent;  
    }
      
   public void setMiMtContent(String miMtContent){  
     this.miMtContent = miMtContent;  
    }
    public String getMiMtTimeSeg(){  
        return miMtTimeSeg;  
    }
      
   public void setMiMtTimeSeg(String miMtTimeSeg){  
     this.miMtTimeSeg = miMtTimeSeg;  
    }
    public String getComId(){  
        return comId;  
    }
      
   public void setComId(String comId){  
     this.comId = comId;  
    }
    public String getCreateEiName(){  
        return createEiName;  
    }
      
   public void setCreateEiName(String createEiName){  
     this.createEiName = createEiName;  
    }
    public Integer getMiIsRemind(){  
        return miIsRemind;  
    }
      
   public void setMiIsRemind(Integer miIsRemind){  
     this.miIsRemind = miIsRemind;  
    }
    public Integer getMiStatus(){  
        return miStatus;  
    }
      
   public void setMiStatus(Integer miStatus){  
     this.miStatus = miStatus;  
    }
    public Long getMiRepeatNum(){  
        return miRepeatNum;  
    }
      
   public void setMiRepeatNum(Long miRepeatNum){  
     this.miRepeatNum = miRepeatNum;  
    }
    public String getMiMtSubject(){  
        return miMtSubject;  
    }
      
   public void setMiMtSubject(String miMtSubject){  
     this.miMtSubject = miMtSubject;  
    }
    public Long getUpdateTime(){  
        return updateTime;  
    }
      
   public void setUpdateTime(Long updateTime){  
     this.updateTime = updateTime;  
    }
    public String getRrId(){  
        return rrId;  
    }
      
   public void setRrId(String rrId){  
     this.rrId = rrId;  
    }
    public Long getIsDelete(){  
        return isDelete;  
    }
      
   public void setIsDelete(Long isDelete){  
     this.isDelete = isDelete;  
    }
    public Long getCompereEiId(){  
        return compereEiId;  
    }
      
   public void setCompereEiId(Long compereEiId){  
     this.compereEiId = compereEiId;  
    }
    public Date getMiStartTime(){  
        return miStartTime;  
    }
      
   public void setMiStartTime(Date miStartTime){  
     this.miStartTime = miStartTime;  
    }
}