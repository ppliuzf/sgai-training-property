package com.sgai.property.purchase.entity;
import java.util.Date;
import io.swagger.annotations.ApiModelProperty;
import com.sgai.common.persistence.BoEntity;
public class DepotWarehouse extends BoEntity<DepotWarehouse>{

    @ApiModelProperty(value = "仓库编号")
    private String whNo; //仓库编号
    @ApiModelProperty(value = "仓库维度")
    private String whLongitude; //仓库维度
    @ApiModelProperty(value = "仓库类型 1：实仓，2：虚仓")
    private Long whType; //仓库类型 1：实仓，2：虚仓
    @ApiModelProperty(value = "管理员id")
    private String whEmpId; //管理员id
    @ApiModelProperty(value = "管理员名称")
    private String whEmpName; //管理员名称
    @ApiModelProperty(value = "备注")
    private String whDesc; //备注
    @ApiModelProperty(value = "仓库地址")
    private String whAddress; //仓库地址
    @ApiModelProperty(value = "关联组织")
    private String whDept; //关联组织
    @ApiModelProperty(value = "联系电话")
    private String whEmpPhone; //联系电话
    @ApiModelProperty(value = "仓库名称")
    private String whName; //仓库名称
    @ApiModelProperty(value = "仓库经度")
    private String whLatitude; //仓库经度

    public String getWhNo(){  
        return whNo;  
    }
      
   public void setWhNo(String whNo){  
     this.whNo = whNo;  
    }  
    public String getWhLongitude(){  
        return whLongitude;  
    }
      
   public void setWhLongitude(String whLongitude){  
     this.whLongitude = whLongitude;  
    }  
    public Long getWhType(){  
        return whType;  
    }
      
   public void setWhType(Long whType){  
     this.whType = whType;  
    }  
    public String getWhEmpId(){  
        return whEmpId;  
    }
      
   public void setWhEmpId(String whEmpId){  
     this.whEmpId = whEmpId;  
    }  
    public String getWhEmpName(){  
        return whEmpName;  
    }
      
   public void setWhEmpName(String whEmpName){  
     this.whEmpName = whEmpName;  
    }  
    public String getWhDesc(){  
        return whDesc;  
    }
      
   public void setWhDesc(String whDesc){  
     this.whDesc = whDesc;  
    }  
    public String getWhAddress(){  
        return whAddress;  
    }
      
   public void setWhAddress(String whAddress){  
     this.whAddress = whAddress;  
    }  
    public String getWhDept(){  
        return whDept;  
    }
      
   public void setWhDept(String whDept){  
     this.whDept = whDept;  
    }  
    public String getWhEmpPhone(){  
        return whEmpPhone;  
    }
      
   public void setWhEmpPhone(String whEmpPhone){  
     this.whEmpPhone = whEmpPhone;  
    }  
    public String getWhName(){  
        return whName;  
    }
      
   public void setWhName(String whName){  
     this.whName = whName;  
    }  
    public String getWhLatitude(){  
        return whLatitude;  
    }
      
   public void setWhLatitude(String whLatitude){  
     this.whLatitude = whLatitude;  
    }  
}