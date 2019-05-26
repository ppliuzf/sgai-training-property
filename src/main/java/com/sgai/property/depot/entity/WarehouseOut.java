package com.sgai.property.depot.entity;
import java.util.Date;
import io.swagger.annotations.ApiModelProperty;
import com.sgai.common.persistence.BoEntity;
public class WarehouseOut extends BoEntity<WarehouseOut>{

    @ApiModelProperty(value = "仓库名称")
    private String whName; //仓库名称
    @ApiModelProperty(value = "仓库类型 1：实仓，2：虚仓")
    private Long whType; //仓库类型 1：实仓，2：虚仓
    @ApiModelProperty(value = "用料申请单id")
    private String matApplyId; //用料申请单id
    @ApiModelProperty(value = "出库人id")
    private String outEmpId; //出库人id
    @ApiModelProperty(value = "仓库id")
    private String whId; //仓库id
    @ApiModelProperty(value = "调拨单id")
    private String allotId; //调拨单id
    @ApiModelProperty(value = "用料申请单名称")
    private String matApplyName; //用料申请单名称
    @ApiModelProperty(value = "出库类型？1：调拨出库；2：用料出库；3：借出出库；4：直接出库；5：销售出库")
    private Long whOutType; //出库类型？1：调拨出库；2：用料出库；3：借出出库；4：直接出库；5：销售出库
    @ApiModelProperty(value = "出库单号")
    private String whOutNo; //出库单号
    @ApiModelProperty(value = "出库时间")
    private Date outDatetime; //出库时间
    @ApiModelProperty(value = "出库人名称")
    private String outEmpName; //出库人名称
    @ApiModelProperty(value = "出库状态?1:未出库;2:部分出库;3:全部出库")
    private Long whStat; //出库状态?1:未出库;2:部分出库;3:全部出库
    @ApiModelProperty(value = "调拨单名称")
    private String allotName; //调拨单名称

    public String getWhName(){  
        return whName;  
    }
      
   public void setWhName(String whName){  
     this.whName = whName;  
    }  
    public Long getWhType(){  
        return whType;  
    }
      
   public void setWhType(Long whType){  
     this.whType = whType;  
    }  
    public String getMatApplyId(){  
        return matApplyId;  
    }
      
   public void setMatApplyId(String matApplyId){  
     this.matApplyId = matApplyId;  
    }

    public String getOutEmpId() {
        return outEmpId;
    }

    public void setOutEmpId(String outEmpId) {
        this.outEmpId = outEmpId;
    }

    public String getWhId(){
        return whId;  
    }
      
   public void setWhId(String whId){  
     this.whId = whId;  
    }  
    public String getAllotId(){  
        return allotId;  
    }
      
   public void setAllotId(String allotId){  
     this.allotId = allotId;  
    }  
    public String getMatApplyName(){  
        return matApplyName;  
    }
      
   public void setMatApplyName(String matApplyName){  
     this.matApplyName = matApplyName;  
    }  
    public Long getWhOutType(){  
        return whOutType;  
    }
      
   public void setWhOutType(Long whOutType){  
     this.whOutType = whOutType;  
    }  
    public String getWhOutNo(){  
        return whOutNo;  
    }
      
   public void setWhOutNo(String whOutNo){  
     this.whOutNo = whOutNo;  
    }  
    public Date getOutDatetime(){  
        return outDatetime;  
    }
      
   public void setOutDatetime(Date outDatetime){  
     this.outDatetime = outDatetime;  
    }  
    public String getOutEmpName(){  
        return outEmpName;  
    }
      
   public void setOutEmpName(String outEmpName){  
     this.outEmpName = outEmpName;  
    }  
    public Long getWhStat(){  
        return whStat;  
    }
      
   public void setWhStat(Long whStat){  
     this.whStat = whStat;  
    }  
    public String getAllotName(){  
        return allotName;  
    }
      
   public void setAllotName(String allotName){  
     this.allotName = allotName;  
    }  
}