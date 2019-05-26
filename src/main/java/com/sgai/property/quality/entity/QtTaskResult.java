package com.sgai.property.quality.entity;
import java.util.Date;
import io.swagger.annotations.ApiModelProperty;
import com.sgai.common.persistence.BoEntity;
public class QtTaskResult extends BoEntity<QtTaskResult>{
     
    @ApiModelProperty(value = "创建时间")
    private Long createTime; //创建时间
    @ApiModelProperty(value = "是否删除(0未删除,1已删除)")
    private Long valid; //是否删除(0未删除,1已删除)
    @ApiModelProperty(value = "状态（0:未检查,1:合格,2:缺陷）")
    private Long tiStatus; //状态（0:未检查,1:合格,2:缺陷）
    @ApiModelProperty(value = "模板名称")
    private String tpName; //模板名称

    @ApiModelProperty(value = "组织ID")
    private Long comId; //组织ID
    @ApiModelProperty(value = "检测时间")
    private Long tiCheckTime; //检测时间
    @ApiModelProperty(value = "执行人名称")
    private String tiExecutorName; //执行人名称
    @ApiModelProperty(value = "计划Id")
    private String recordId; //计划Id
    @ApiModelProperty(value = "模板ID")
    private String tpId; //模板ID

    @ApiModelProperty(value = "更新时间")
    private Long updateTime; //更新时间
    @ApiModelProperty(value = "是否发起缺陷整改(0:未发起整改,1:已发起整改)")
    private Long tiHasDefect; //是否发起缺陷整改(0:未发起整改,1:已发起整改)
    @ApiModelProperty(value = "执行人ID")
    private String tiExecutorId; //执行人ID
    @ApiModelProperty(value = "任务项名称")
    private String tiName; //任务项名称
    @ApiModelProperty(value = "原始任务项Id")
    private String tiId; //原始任务项Id
    @ApiModelProperty(value = "是否答题(0未检测,1已检测)")
    private Long tiIsSubmit; //是否答题(0未检测,1已检测)
    @ApiModelProperty(value = "填入的检测结果")
    private String tiInputResult; //填入的检测结果
    @ApiModelProperty(value = "日期时间")
    private Long dateTime; //填入的检测结果
    public Long getComId(){  
        return comId;  
    }

	public Long getCreateTime(){  
        return createTime;  
    }

	public Long getDateTime() {
		return dateTime;
	}
      
   public String getRecordId(){  
    return recordId;  
}  
    public Long getTiCheckTime(){  
        return tiCheckTime;  
    }
      
   public String getTiExecutorId(){  
    return tiExecutorId;  
}  
    public String getTiExecutorName(){  
        return tiExecutorName;  
    }
      
   public Long getTiHasDefect(){  
    return tiHasDefect;  
}  
    public String getTiId(){  
        return tiId;  
    }
      
   public String getTiInputResult(){  
    return tiInputResult;  
}  
    public Long getTiIsSubmit(){  
        return tiIsSubmit;  
    }
      
   public String getTiName(){  
    return tiName;  
}  
    public Long getTiStatus(){  
        return tiStatus;  
    }
      
   public String getTpId(){  
    return tpId;  
}  
    public String getTpName(){  
        return tpName;  
    }
      
   public Long getUpdateTime(){  
    return updateTime;  
}  
    public Long getValid(){  
        return valid;  
    }
      
   public void setComId(Long comId){  
     this.comId = comId;  
    }  
    public void setCreateTime(Long createTime){  
	     this.createTime = createTime;  
	    }
      
   public void setDateTime(Long dateTime) {
	this.dateTime = dateTime;
}  
    public void setRecordId(String recordId){  
	     this.recordId = recordId;  
	    }
      
   public void setTiCheckTime(Long tiCheckTime){  
     this.tiCheckTime = tiCheckTime;  
    }  
    public void setTiExecutorId(String tiExecutorId){  
	     this.tiExecutorId = tiExecutorId;  
	    }
      
   public void setTiExecutorName(String tiExecutorName){  
     this.tiExecutorName = tiExecutorName;  
    }  
    public void setTiHasDefect(Long tiHasDefect){  
	     this.tiHasDefect = tiHasDefect;  
	    }
      
   public void setTiId(String tiId){  
     this.tiId = tiId;  
    }  
    public void setTiInputResult(String tiInputResult){  
	     this.tiInputResult = tiInputResult;  
	    }
      
   public void setTiIsSubmit(Long tiIsSubmit){  
     this.tiIsSubmit = tiIsSubmit;  
    }  
    public void setTiName(String tiName){  
	     this.tiName = tiName;  
	    }
      
   public void setTiStatus(Long tiStatus){  
     this.tiStatus = tiStatus;  
    }  
    public void setTpId(String tpId){  
	     this.tpId = tpId;  
	    }
      
   public void setTpName(String tpName){  
     this.tpName = tpName;  
    }  
    public void setUpdateTime(Long updateTime){  
	     this.updateTime = updateTime;  
	    }
      
   public void setValid(Long valid){  
     this.valid = valid;  
    }  
}