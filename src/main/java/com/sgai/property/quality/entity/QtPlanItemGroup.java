package com.sgai.property.quality.entity;

import com.sgai.common.persistence.BoEntity;
import io.swagger.annotations.ApiModelProperty;

public class QtPlanItemGroup extends BoEntity<QtPlanItemGroup> {
	//未分组默认排序
    public static Integer NORMAL_SORT=0;
    @ApiModelProperty(value = "所属方案id")
    private String tpId; //所属方案id
    @ApiModelProperty(value = "分组名称")
    private String pigName; //分组名称
    @ApiModelProperty(value = "分组在方案中排序")
    private Integer pigSort; //分组在方案中排序
    @ApiModelProperty(value = "创建时间")
    private Long createTime; //创建时间
    @ApiModelProperty(value = "更新时间")
    private Long updateTime; //更新时间
    @ApiModelProperty(value = "是否删除(1:已删除,0:未删除)")
    private Integer valid; //是否删除(1:已删除,0:未删除)
    public String getTpId(){
        return tpId;  
    }
      
   public void setTpId(String tpId){
     this.tpId = tpId;  
    }  
    public String getPigName(){  
        return pigName;  
    }
      
   public void setPigName(String pigName){  
     this.pigName = pigName;  
    }  
    public Integer getPigSort(){  
        return pigSort;  
    }
      
   public void setPigSort(Integer pigSort){  
     this.pigSort = pigSort;  
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