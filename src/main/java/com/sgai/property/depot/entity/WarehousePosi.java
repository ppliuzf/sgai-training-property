package com.sgai.property.depot.entity;
import java.util.Date;
import io.swagger.annotations.ApiModelProperty;
import com.sgai.common.persistence.BoEntity;
public class WarehousePosi extends BoEntity<WarehousePosi>{

    @ApiModelProperty(value = "关联组织json")
    private String whAreaJson; //关联组织json
    @ApiModelProperty(value = "仓库id")
    private String whId; //仓库id
    @ApiModelProperty(value = "关联组织name")
    private String whAreaName; //关联组织name
    @ApiModelProperty(value = "关联组织pid")
    private String whAreaPid; //关联组织pid
    @ApiModelProperty(value = "关联组织id")
    private String whAreaId; //关联组织id

    public String getWhAreaJson(){  
        return whAreaJson;  
    }
      
   public void setWhAreaJson(String whAreaJson){  
     this.whAreaJson = whAreaJson;  
    }  
    public String getWhId(){  
        return whId;  
    }
      
   public void setWhId(String whId){  
     this.whId = whId;  
    }  
    public String getWhAreaName(){  
        return whAreaName;  
    }
      
   public void setWhAreaName(String whAreaName){  
     this.whAreaName = whAreaName;  
    }  
    public String getWhAreaPid(){  
        return whAreaPid;  
    }
      
   public void setWhAreaPid(String whAreaPid){  
     this.whAreaPid = whAreaPid;  
    }  
    public String getWhAreaId(){  
        return whAreaId;  
    }
      
   public void setWhAreaId(String whAreaId){  
     this.whAreaId = whAreaId;  
    }  
}