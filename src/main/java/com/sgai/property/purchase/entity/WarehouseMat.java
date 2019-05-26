package com.sgai.property.purchase.entity;
import java.util.Date;
import io.swagger.annotations.ApiModelProperty;
import com.sgai.common.persistence.BoEntity;
public class WarehouseMat extends BoEntity<WarehouseMat>{

    @ApiModelProperty(value = "物料名称")
    private String matName; //物料名称
    @ApiModelProperty(value = "库存数量")
    private Long matNum; //库存数量
    @ApiModelProperty(value = "物料规格")
    private String matSpec; //物料规格
    @ApiModelProperty(value = "仓库id")
    private String whId; //仓库id
    @ApiModelProperty(value = "物料型号（无）")
    private String matTypeCode; //物料型号（无）
    @ApiModelProperty(value = "物料id")
    private String matTypeId; //物料id

    public String getMatName(){  
        return matName;  
    }
      
   public void setMatName(String matName){  
     this.matName = matName;  
    }  
    public Long getMatNum(){  
        return matNum;  
    }
      
   public void setMatNum(Long matNum){  
     this.matNum = matNum;  
    }  
    public String getMatSpec(){  
        return matSpec;  
    }
      
   public void setMatSpec(String matSpec){  
     this.matSpec = matSpec;  
    }  
    public String getWhId(){  
        return whId;  
    }
      
   public void setWhId(String whId){  
     this.whId = whId;  
    }  
    public String getMatTypeCode(){  
        return matTypeCode;  
    }
      
   public void setMatTypeCode(String matTypeCode){  
     this.matTypeCode = matTypeCode;  
    }  
    public String getMatTypeId(){  
        return matTypeId;  
    }
      
   public void setMatTypeId(String matTypeId){  
     this.matTypeId = matTypeId;  
    }  
}