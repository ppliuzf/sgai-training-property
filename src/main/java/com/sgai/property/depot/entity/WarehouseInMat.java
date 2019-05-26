package com.sgai.property.depot.entity;
import java.util.Date;
import io.swagger.annotations.ApiModelProperty;
import com.sgai.common.persistence.BoEntity;
public class WarehouseInMat extends BoEntity<WarehouseInMat>{

    @ApiModelProperty(value = "所需数量")
    private Long matNeetNum; //所需数量
    @ApiModelProperty(value = "物料名称")
    private String matName; //物料名称
    @ApiModelProperty(value = "物料规格")
    private String matSpec; //物料规格
    @ApiModelProperty(value = "仓库id")
    private String whId; //仓库id
    @ApiModelProperty(value = "入库单id")
    private String whInId; //入库单id
    @ApiModelProperty(value = "实际数量")
    private Long matRealNum; //实际数量
    @ApiModelProperty(value = "物料型号（无）")
    private String matTypeCode; //物料型号（无）
    @ApiModelProperty(value = "物料id")
    private String matTypeId; //物料id

    public Long getMatNeetNum(){  
        return matNeetNum;  
    }
      
   public void setMatNeetNum(Long matNeetNum){  
     this.matNeetNum = matNeetNum;  
    }  
    public String getMatName(){  
        return matName;  
    }
      
   public void setMatName(String matName){  
     this.matName = matName;  
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
    public String getWhInId(){  
        return whInId;  
    }
      
   public void setWhInId(String whInId){  
     this.whInId = whInId;  
    }  
    public Long getMatRealNum(){  
        return matRealNum;  
    }
      
   public void setMatRealNum(Long matRealNum){  
     this.matRealNum = matRealNum;  
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