package com.sgai.property.budget.entity;
import java.util.Date;
import io.swagger.annotations.ApiModelProperty;
import com.sgai.common.persistence.BoEntity;
public class InputData extends BoEntity<InputData>{
     
    @ApiModelProperty(value = "创建时间")
    private Long createTime; //创建时间
    @ApiModelProperty(value = "更新时间")
    private Long updateTime; //更新时间
    @ApiModelProperty(value = "录入记录id")
    private String inputId; //录入记录id
    @ApiModelProperty(value = "支出")
    private String expend; //支出
    @ApiModelProperty(value = "预算金额")
    private String budget; //预算金额
    @ApiModelProperty(value = "预算结余")
    private String surplus; //预算结余
    @ApiModelProperty(value = "项目记录id")
    private String rsiId; //项目记录id
	
    public Long getCreateTime(){  
        return createTime;  
    }
      
   public void setCreateTime(Long createTime){  
     this.createTime = createTime;  
    }  
    public Long getUpdateTime(){  
        return updateTime;  
    }
      
   public void setUpdateTime(Long updateTime){  
     this.updateTime = updateTime;  
    }  
    public String getInputId(){  
        return inputId;  
    }
      
   public void setInputId(String inputId){  
     this.inputId = inputId;  
    }  
    public String getExpend(){  
        return expend;  
    }
      
   public void setExpend(String expend){  
     this.expend = expend;  
    }  
    public String getBudget(){  
        return budget;  
    }
      
   public void setBudget(String budget){  
     this.budget = budget;  
    }  
    public String getSurplus(){  
        return surplus;  
    }
      
   public void setSurplus(String surplus){  
     this.surplus = surplus;  
    }  
    public String getRsiId(){  
        return rsiId;  
    }
      
   public void setRsiId(String rsiId){  
     this.rsiId = rsiId;  
    }  
}