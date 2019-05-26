package com.sgai.property.supplier.entity;

import com.sgai.common.persistence.BoEntity;
import io.swagger.annotations.ApiModelProperty;
public class GysFile extends BoEntity<GysFile>{
     
    @ApiModelProperty(value = "附件")
    private String fileUrl; //附件
    @ApiModelProperty(value = "附件名字")
    private String fileName; //附件名字
    @ApiModelProperty(value = "供应商ID")
    private String supplierId; //
    @ApiModelProperty(value = "是否删除；1:是;0:否")
    private Long isDelete; //是否删除；1:是;0:否
	
    public String getFileUrl(){  
        return fileUrl;  
    }
      
   public void setFileUrl(String fileUrl){  
     this.fileUrl = fileUrl;  
    }  
    public String getFileName(){  
        return fileName;  
    }
      
   public void setFileName(String fileName){  
     this.fileName = fileName;  
    }  
    public String getSupplierId(){  
        return supplierId;  
    }
      
   public void setSupplierId(String supplierId){  
     this.supplierId = supplierId;  
    }  
    public Long getIsDelete(){  
        return isDelete;  
    }
      
   public void setIsDelete(Long isDelete){  
     this.isDelete = isDelete;  
    }  
}