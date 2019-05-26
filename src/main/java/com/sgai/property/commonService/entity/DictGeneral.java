package com.sgai.property.commonService.entity;
import com.sgai.common.persistence.BoEntity;
import io.swagger.annotations.ApiModelProperty;

public class DictGeneral extends BoEntity<DictGeneral> {

    @ApiModelProperty(value = "字典CODE")
    private String dgCode; //字典CODE
    @ApiModelProperty(value = "字典的key")
    private String dgKey; //字典的key
    @ApiModelProperty(value = "字典key的value值")
    private String dgValue; //字典key的value值
    @ApiModelProperty(value = "字典类型 1:系统级别 2:应用级别")
    private Integer dgType; //字典类型 1:系统级别 2:应用级别
    @ApiModelProperty(value = "描述")
    private String dgDescription; //描述
    @ApiModelProperty(value = "排序")
    private Long dgSort; //排序
    @ApiModelProperty(value = "是否启用: 0:否，1:是")
    private Integer dgIsEnabled; //是否启用: 0:否，1:是
    @ApiModelProperty(value = "是否被删除1 是 0 否")
    private Integer dgIsDelete; //是否被删除1 是 0 否
    @ApiModelProperty(value = "创建时间")
    private Long createTime; //创建时间
    @ApiModelProperty(value = "更新时间")
    private Long updateTime; //更新时间

    public String getDgCode(){  
        return dgCode;  
    }
      
   public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}


public void setDgCode(String dgCode){  
     this.dgCode = dgCode;  
    }  
    public String getDgKey(){  
        return dgKey;  
    }
      
   public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}


	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
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

}