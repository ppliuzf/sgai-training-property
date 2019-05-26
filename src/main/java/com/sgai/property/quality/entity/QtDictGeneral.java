package com.sgai.property.quality.entity;

import com.sgai.common.persistence.BoEntity;
import io.swagger.annotations.ApiModelProperty;

public class QtDictGeneral extends BoEntity<QtDictGeneral> {
    @ApiModelProperty(value = "字典code")
    private String dgCode; //字典code
    @ApiModelProperty(value = "字典的key")
    private String dgKey; //字典的key
    @ApiModelProperty(value = "字典key的value")
    private String dgValue; //字典key的value
    @ApiModelProperty(value = "字典类型 1:系统级别 2:应用级别")
    private Integer dgType; //字典类型 1:系统级别 2:应用级别
    @ApiModelProperty(value = "描述")
    private String dgDescription; //描述
    @ApiModelProperty(value = "顺序")
    private Long dgSort; //顺序
    @ApiModelProperty(value = "是否启用: 0:启用，1:不启用")
    private Integer dgIsEnabled; //是否启用: 0:启用，1:不启用
    @ApiModelProperty(value = "是否被删除1 是 0 否")
    private Integer dgIsDelete; //是否被删除1 是 0 否
    @ApiModelProperty(value = "创建时间")
    private Long dgCreateTime; //创建时间
    @ApiModelProperty(value = "更新时间")
    private Long dgUpdateTime; //更新时间
    public String getDgCode(){  
        return dgCode;  
    }
      
   public void setDgCode(String dgCode){  
     this.dgCode = dgCode;  
    }  
    public String getDgKey(){  
        return dgKey;  
    }
      
   public void setDgKey(String dgKey){  
     this.dgKey = dgKey;  
    }  
    public String getDgValue(){  
        return dgValue;  
    }
      
   public void setDgValue(String dgValue){  
     this.dgValue = dgValue;  
    }  
    public Integer getDgType(){  
        return dgType;  
    }
      
   public void setDgType(Integer dgType){  
     this.dgType = dgType;  
    }  
    public String getDgDescription(){  
        return dgDescription;  
    }
      
   public void setDgDescription(String dgDescription){  
     this.dgDescription = dgDescription;  
    }  
    public Long getDgSort(){  
        return dgSort;  
    }
      
   public void setDgSort(Long dgSort){  
     this.dgSort = dgSort;  
    }  
    public Integer getDgIsEnabled(){  
        return dgIsEnabled;  
    }
      
   public void setDgIsEnabled(Integer dgIsEnabled){  
     this.dgIsEnabled = dgIsEnabled;  
    }  
    public Integer getDgIsDelete(){  
        return dgIsDelete;  
    }
      
   public void setDgIsDelete(Integer dgIsDelete){  
     this.dgIsDelete = dgIsDelete;  
    }  
    public Long getDgCreateTime(){  
        return dgCreateTime;  
    }
      
   public void setDgCreateTime(Long dgCreateTime){  
     this.dgCreateTime = dgCreateTime;  
    }  
    public Long getDgUpdateTime(){  
        return dgUpdateTime;  
    }
      
   public void setDgUpdateTime(Long dgUpdateTime){  
     this.dgUpdateTime = dgUpdateTime;  
    }  
}