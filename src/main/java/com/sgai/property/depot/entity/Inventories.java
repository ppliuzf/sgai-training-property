package com.sgai.property.depot.entity;
import java.util.Date;
import io.swagger.annotations.ApiModelProperty;
import com.sgai.common.persistence.BoEntity;
public class Inventories extends BoEntity<Inventories>{

    @ApiModelProperty(value = "差异原因")
    private String ivtDiffReason; //差异原因
    @ApiModelProperty(value = "盘点开始时间")
    private Date ivtExecBegin; //盘点开始时间
    @ApiModelProperty(value = "盘点仓库名称")
    private String whName; //盘点仓库名称
    @ApiModelProperty(value = "盘点仓库id")
    private String whId; //盘点仓库id
    @ApiModelProperty(value = "盘点标识？1：未开始；2：盘点中；3已完成")
    private Long matStat; //盘点标识？1：未开始；2：盘点中；3已完成
    @ApiModelProperty(value = "盘点执行人名称")
    private String ivtExecEmpName; //盘点执行人名称
    @ApiModelProperty(value = "盘点执行人id")
    private String ivtExecEmpId; //盘点执行人id
    @ApiModelProperty(value = "盘点结束时间")
    private Date ivtExecEnd; //盘点结束时间
    @ApiModelProperty(value = "计划结束时间")
    private Date ivtPlanEnd; //计划结束时间
    @ApiModelProperty(value = "计划开始时间")
    private Date ivtPlanBegin; //计划开始时间
    @ApiModelProperty(value = "盘点职位json")
    private String ivtPiJson; //盘点职位json
    @ApiModelProperty(value = "盘点主题")
    private String ivtTitle; //盘点主题
    @ApiModelProperty(value = "盘点单号")
    private String ivtNo; //盘点单号

    public String getIvtDiffReason(){  
        return ivtDiffReason;  
    }
      
   public void setIvtDiffReason(String ivtDiffReason){  
     this.ivtDiffReason = ivtDiffReason;  
    }  
    public Date getIvtExecBegin(){  
        return ivtExecBegin;  
    }
      
   public void setIvtExecBegin(Date ivtExecBegin){  
     this.ivtExecBegin = ivtExecBegin;  
    }  
    public String getWhName(){  
        return whName;  
    }
      
   public void setWhName(String whName){  
     this.whName = whName;  
    }  
    public String getWhId(){  
        return whId;  
    }
      
   public void setWhId(String whId){  
     this.whId = whId;  
    }  
    public Long getMatStat(){  
        return matStat;  
    }
      
   public void setMatStat(Long matStat){  
     this.matStat = matStat;  
    }  
    public String getIvtExecEmpName(){  
        return ivtExecEmpName;  
    }
      
   public void setIvtExecEmpName(String ivtExecEmpName){  
     this.ivtExecEmpName = ivtExecEmpName;  
    }  
    public String getIvtExecEmpId(){
        return ivtExecEmpId;  
    }
      
   public void setIvtExecEmpId(String ivtExecEmpId){
     this.ivtExecEmpId = ivtExecEmpId;  
    }  
    public Date getIvtExecEnd(){  
        return ivtExecEnd;  
    }
      
   public void setIvtExecEnd(Date ivtExecEnd){  
     this.ivtExecEnd = ivtExecEnd;  
    }  
    public Date getIvtPlanEnd(){  
        return ivtPlanEnd;  
    }
      
   public void setIvtPlanEnd(Date ivtPlanEnd){  
     this.ivtPlanEnd = ivtPlanEnd;  
    }  
    public Date getIvtPlanBegin(){  
        return ivtPlanBegin;  
    }
      
   public void setIvtPlanBegin(Date ivtPlanBegin){  
     this.ivtPlanBegin = ivtPlanBegin;  
    }  
    public String getIvtPiJson(){  
        return ivtPiJson;  
    }
      
   public void setIvtPiJson(String ivtPiJson){  
     this.ivtPiJson = ivtPiJson;  
    }  
    public String getIvtTitle(){  
        return ivtTitle;  
    }
      
   public void setIvtTitle(String ivtTitle){  
     this.ivtTitle = ivtTitle;  
    }  
    public String getIvtNo(){  
        return ivtNo;  
    }
      
   public void setIvtNo(String ivtNo){  
     this.ivtNo = ivtNo;  
    }  
}