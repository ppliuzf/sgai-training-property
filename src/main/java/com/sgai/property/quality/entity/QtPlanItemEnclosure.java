package com.sgai.property.quality.entity;

import com.sgai.common.persistence.BoEntity;
import io.swagger.annotations.ApiModelProperty;

public class QtPlanItemEnclosure  extends BoEntity<QtPlanItemEnclosure> {
	//附件类型 专业范畴：0
	public static final Integer PROFESSIONAL_CATEGORY=0;
	//附件类型 检查项标准：1
	public static final Integer TEST_ITEM=1;
	//附件类型 方案检查项：2
	public static final Integer PLAN_ITEM=2;
	//附件类型 任务检查项备注：3
	public static final Integer TASK_ITEM_REMARK=3;
    @ApiModelProperty(value = "所属id")
    private String tId; //所属id
    @ApiModelProperty(value = "附件类型")
    private Integer tType; //附件类型
    @ApiModelProperty(value = "附件")
    private String enclosure; //附件
    @ApiModelProperty(value = "创建时间")
    private Long createTime; //创建时间
    @ApiModelProperty(value = "更新时间")
    private Long updateTime; //更新时间
    @ApiModelProperty(value = "是否删除(1已删除,0未删除)")
    private Integer valid; //是否删除(1已删除,0未删除)
    public String getTId(){
        return tId;  
    }
      
   public void setTId(String tId){
     this.tId = tId;  
    }  
    public Integer getTType(){  
        return tType;  
    }
      
   public void setTType(Integer tType){  
     this.tType = tType;  
    }  
    public String getEnclosure(){  
        return enclosure;  
    }
      
   public void setEnclosure(String enclosure){  
     this.enclosure = enclosure;  
    }  
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
    public Integer getValid(){  
        return valid;  
    }
      
   public void setValid(Integer valid){  
     this.valid = valid;  
    }  
}