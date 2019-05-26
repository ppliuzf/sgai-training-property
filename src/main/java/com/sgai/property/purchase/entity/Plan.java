package com.sgai.property.purchase.entity;
import java.util.Date;
import io.swagger.annotations.ApiModelProperty;
import com.sgai.common.persistence.BoEntity;
public class Plan extends BoEntity<Plan>{

    @ApiModelProperty(value = "采购计划部门ID")
    private String planDeptId; //采购计划部门ID
    @ApiModelProperty(value = "审批意见")
    private String approveOption; //审批意见
    @ApiModelProperty(value = "计划创建人id")
    private String planEmpId; //计划创建人id
    @ApiModelProperty(value = "计划类型")
    private Long planType; //计划类型
    @ApiModelProperty(value = "状态? 1:待提交；2::待审核；3:已拒绝；4:已通过")
    private Long planStat; //状态? 1:待提交；2::待审核；3:已拒绝；4:已通过
    @ApiModelProperty(value = "描述")
    private String planDescribe; //描述
    @ApiModelProperty(value = "审批时间")
    private Date approveDate; //审批时间
    @ApiModelProperty(value = "采购审核人名称")
    private String buyerEmpName; //采购审核人名称
    @ApiModelProperty(value = "计划创建日期")
    private Date planDate; //计划创建日期
    @ApiModelProperty(value = "计划名称")
    private String planName; //计划名称
    @ApiModelProperty(value = "计划创建人名称")
    private String planEmpName; //计划创建人名称
    @ApiModelProperty(value = "采购计划部门")
    private String planDept; //采购计划部门

    public String getPlanDeptId(){  
        return planDeptId;  
    }
      
   public void setPlanDeptId(String planDeptId){  
     this.planDeptId = planDeptId;  
    }  
    public String getApproveOption(){  
        return approveOption;  
    }
      
   public void setApproveOption(String approveOption){  
     this.approveOption = approveOption;  
    }

    public String getPlanEmpId() {
        return planEmpId;
    }

    public void setPlanEmpId(String planEmpId) {
        this.planEmpId = planEmpId;
    }

    public Long getPlanType(){
        return planType;  
    }
      
   public void setPlanType(Long planType){  
     this.planType = planType;  
    }  
    public Long getPlanStat(){  
        return planStat;  
    }
      
   public void setPlanStat(Long planStat){  
     this.planStat = planStat;  
    }  
    public String getPlanDescribe(){  
        return planDescribe;  
    }
      
   public void setPlanDescribe(String planDescribe){  
     this.planDescribe = planDescribe;  
    }  
    public Date getApproveDate(){  
        return approveDate;  
    }
      
   public void setApproveDate(Date approveDate){  
     this.approveDate = approveDate;  
    }  
    public String getBuyerEmpName(){  
        return buyerEmpName;  
    }
      
   public void setBuyerEmpName(String buyerEmpName){  
     this.buyerEmpName = buyerEmpName;  
    }  
    public Date getPlanDate(){  
        return planDate;  
    }
      
   public void setPlanDate(Date planDate){  
     this.planDate = planDate;  
    }  
    public String getPlanName(){  
        return planName;  
    }
      
   public void setPlanName(String planName){  
     this.planName = planName;  
    }  
    public String getPlanEmpName(){  
        return planEmpName;  
    }
      
   public void setPlanEmpName(String planEmpName){  
     this.planEmpName = planEmpName;  
    }  
    public String getPlanDept(){  
        return planDept;  
    }
      
   public void setPlanDept(String planDept){  
     this.planDept = planDept;  
    }  
}