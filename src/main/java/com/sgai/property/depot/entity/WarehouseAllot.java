package com.sgai.property.depot.entity;
import java.util.Date;
import io.swagger.annotations.ApiModelProperty;
import com.sgai.common.persistence.BoEntity;
public class WarehouseAllot extends BoEntity<WarehouseAllot>{

    @ApiModelProperty(value = "调拨人id")
    private Long allotEmpId; //调拨人id
    @ApiModelProperty(value = "调拨状态?1:未调拨;2:部分调拨;3:全部调拨")
    private Long whAllotStat; //调拨状态?1:未调拨;2:部分调拨;3:全部调拨
    @ApiModelProperty(value = "调拨理由")
    private String allotReason; //调拨理由
    @ApiModelProperty(value = "调拨单编号")
    private String allotNo; //调拨单编号
    @ApiModelProperty(value = "调拨时间")
    private Date allotDatetime; //调拨时间
    @ApiModelProperty(value = "调拨人名称")
    private String allotEmpName; //调拨人名称
    @ApiModelProperty(value = "调拨人部门名称")
    private String allotDeptName; //调拨人部门名称
    @ApiModelProperty(value = "调拨单名称")
    private String allotName; //调拨单名称
    @ApiModelProperty(value = "启用状态？0:未启用 ；1:出库启用；2:入库启用 ;3:启用并且未出库")
    private Long hasOrder; //启用状态？0:未启用 ；1:出库启用；2:入库启用

    public Long getAllotEmpId(){  
        return allotEmpId;  
    }
      
   public void setAllotEmpId(Long allotEmpId){  
     this.allotEmpId = allotEmpId;  
    }  
    public Long getWhAllotStat(){  
        return whAllotStat;  
    }
      
   public void setWhAllotStat(Long whAllotStat){  
     this.whAllotStat = whAllotStat;  
    }  
    public String getAllotReason(){  
        return allotReason;  
    }
      
   public void setAllotReason(String allotReason){  
     this.allotReason = allotReason;  
    }  
    public String getAllotNo(){  
        return allotNo;  
    }
      
   public void setAllotNo(String allotNo){  
     this.allotNo = allotNo;  
    }  
    public Date getAllotDatetime(){  
        return allotDatetime;  
    }
      
   public void setAllotDatetime(Date allotDatetime){  
     this.allotDatetime = allotDatetime;  
    }  
    public String getAllotEmpName(){  
        return allotEmpName;  
    }
      
   public void setAllotEmpName(String allotEmpName){  
     this.allotEmpName = allotEmpName;  
    }  
    public String getAllotDeptName(){  
        return allotDeptName;  
    }
      
   public void setAllotDeptName(String allotDeptName){  
     this.allotDeptName = allotDeptName;  
    }  
    public String getAllotName(){  
        return allotName;  
    }
      
   public void setAllotName(String allotName){  
     this.allotName = allotName;  
    }

    public Long getHasOrder() {
        return hasOrder;
    }

    public void setHasOrder(Long hasOrder) {
        this.hasOrder = hasOrder;
    }
}