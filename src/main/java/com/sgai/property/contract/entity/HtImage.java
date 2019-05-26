package com.sgai.property.contract.entity;

import com.sgai.common.persistence.BoEntity;
import io.swagger.annotations.ApiModelProperty;
public class HtImage extends BoEntity<HtImage>{
     
    @ApiModelProperty(value = "创建时间")
    private Long createTime; //创建时间
    @ApiModelProperty(value = "图片A")
    private String urlA; //图片A
    @ApiModelProperty(value = "")
    private String urlC; //
    @ApiModelProperty(value = "URL_C")
    private String urlB; //URL_C
    @ApiModelProperty(value = "更新时间")
    private Long updateTime; //更新时间
    @ApiModelProperty(value = "合同编码")
    private String contractNo; //合同编码
    @ApiModelProperty(value = "是否删除；1:是;0:否")
    private Long isDelete; //是否删除；1:是;0:否
	
    public Long getCreateTime(){  
        return createTime;  
    }
      
   public void setCreateTime(Long createTime){  
     this.createTime = createTime;  
    }  
    public String getUrlA(){  
        return urlA;  
    }
      
   public void setUrlA(String urlA){  
     this.urlA = urlA;  
    }  
    public String getUrlC(){  
        return urlC;  
    }
      
   public void setUrlC(String urlC){  
     this.urlC = urlC;  
    }  
    public String getUrlB(){  
        return urlB;  
    }
      
   public void setUrlB(String urlB){  
     this.urlB = urlB;  
    }  
    public Long getUpdateTime(){  
        return updateTime;  
    }
      
   public void setUpdateTime(Long updateTime){  
     this.updateTime = updateTime;  
    }  
    public String getContractNo(){  
        return contractNo;  
    }
      
   public void setContractNo(String contractNo){  
     this.contractNo = contractNo;  
    }  
    public Long getIsDelete(){  
        return isDelete;  
    }
      
   public void setIsDelete(Long isDelete){  
     this.isDelete = isDelete;  
    }  
}