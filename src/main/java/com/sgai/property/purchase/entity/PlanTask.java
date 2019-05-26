package com.sgai.property.purchase.entity;
import java.util.Date;
import io.swagger.annotations.ApiModelProperty;
import com.sgai.common.persistence.BoEntity;
public class PlanTask extends BoEntity<PlanTask>{

    @ApiModelProperty(value = "计划Id")
    private String planId; //计划Id
    @ApiModelProperty(value = "采购日期")
    private Date taskPurchaseDate; //采购日期
    @ApiModelProperty(value = "阶段Id")
    private String stageId; //阶段Id
    @ApiModelProperty(value = "申请人id")
    private String taskEmpId; //申请人id
    @ApiModelProperty(value = "申请理由")
    private String taskOpinion; //申请理由
    @ApiModelProperty(value = "结束日期")
    private Date taskEndDate; //结束日期
    @ApiModelProperty(value = "申请人姓名")
    private String taskEmpName; //申请人姓名
    @ApiModelProperty(value = "申请日期")
    private Date taskDate; //申请日期
    @ApiModelProperty(value = "计划用料状态(1.已通过 2.未通过)")
    private Long planMatStat; //计划用料状态(1.已通过 2.未通过)
    @ApiModelProperty(value = "数据来源(1.用料 2.计划)")
    private Long taskPlanMat; //数据来源(1.用料 2.计划)
    @ApiModelProperty(value = "需求状态(1.已处理 2.未处理)")
    private Long taskNeedStatus; //需求状态(1.已处理 2.未处理)
    @ApiModelProperty(value = "排序字段")
    private Long sort; //排序字段
    @ApiModelProperty(value = "所属部门")
    private String taskDept; //所属部门
    @ApiModelProperty(value = "申请编号")
    private String taskNo; //申请编号
    @ApiModelProperty(value = "采购名称")
    private String taskPuchaseName; //采购名称

    public String getPlanId(){  
        return planId;  
    }
      
   public void setPlanId(String planId){  
     this.planId = planId;  
    }  
    public Date getTaskPurchaseDate(){  
        return taskPurchaseDate;  
    }
      
   public void setTaskPurchaseDate(Date taskPurchaseDate){  
     this.taskPurchaseDate = taskPurchaseDate;  
    }  
    public String getStageId(){  
        return stageId;  
    }
      
   public void setStageId(String stageId){  
     this.stageId = stageId;  
    }  
    public String getTaskEmpId(){  
        return taskEmpId;  
    }
      
   public void setTaskEmpId(String taskEmpId){  
     this.taskEmpId = taskEmpId;  
    }  
    public String getTaskOpinion(){  
        return taskOpinion;  
    }
      
   public void setTaskOpinion(String taskOpinion){  
     this.taskOpinion = taskOpinion;  
    }  
    public Date getTaskEndDate(){  
        return taskEndDate;  
    }
      
   public void setTaskEndDate(Date taskEndDate){  
     this.taskEndDate = taskEndDate;  
    }  
    public String getTaskEmpName(){  
        return taskEmpName;  
    }
      
   public void setTaskEmpName(String taskEmpName){  
     this.taskEmpName = taskEmpName;  
    }  
    public Date getTaskDate(){  
        return taskDate;  
    }
      
   public void setTaskDate(Date taskDate){  
     this.taskDate = taskDate;  
    }  
    public Long getPlanMatStat(){  
        return planMatStat;  
    }
      
   public void setPlanMatStat(Long planMatStat){  
     this.planMatStat = planMatStat;  
    }  
    public Long getTaskNeedStatus(){  
        return taskNeedStatus;  
    }
      
   public void setTaskNeedStatus(Long taskNeedStatus){  
     this.taskNeedStatus = taskNeedStatus;  
    }  
    public Long getSort(){  
        return sort;  
    }
      
   public void setSort(Long sort){  
     this.sort = sort;  
    }  
    public String getTaskDept(){  
        return taskDept;  
    }
      
   public void setTaskDept(String taskDept){  
     this.taskDept = taskDept;  
    }  
    public String getTaskNo(){  
        return taskNo;  
    }
      
   public void setTaskNo(String taskNo){  
     this.taskNo = taskNo;  
    }  
    public String getTaskPuchaseName(){  
        return taskPuchaseName;  
    }
      
   public void setTaskPuchaseName(String taskPuchaseName){  
     this.taskPuchaseName = taskPuchaseName;  
    }

    public Long getTaskPlanMat() {
        return taskPlanMat;
    }

    public void setTaskPlanMat(Long taskPlanMat) {
        this.taskPlanMat = taskPlanMat;
    }
}