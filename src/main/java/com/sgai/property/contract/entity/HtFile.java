package com.sgai.property.contract.entity;
import java.util.Date;
import io.swagger.annotations.ApiModelProperty;
import com.sgai.common.persistence.BoEntity;
public class HtFile extends BoEntity<HtFile>{
     
    @ApiModelProperty(value = "附件名字")
    private String fileName; //附件名字
    @ApiModelProperty(value = "附件")
    private String fileUrl; //附件
    @ApiModelProperty(value = "合同编码")
    private String contractId; //合同编码
    @ApiModelProperty(value = "是否删除；1:是;0:否")
    private Long isDelete; //是否删除；1:是;0:否
    @ApiModelProperty(value = "标示 1 合同副本，2 补充协议 ，3 其他附件")
    private Long mark; //标示 1 合同副本，2 补充协议 ，3 其他附件
	
    public String getFileName(){  
        return fileName;  
    }
      
   public void setFileName(String fileName){  
     this.fileName = fileName;  
    }  
    public String getFileUrl(){  
        return fileUrl;  
    }
      
   public void setFileUrl(String fileUrl){  
     this.fileUrl = fileUrl;  
    }  
    public String getContractId(){  
        return contractId;  
    }
      
   public void setContractId(String contractId){  
     this.contractId = contractId;  
    }  
    public Long getIsDelete(){  
        return isDelete;  
    }
      
   public void setIsDelete(Long isDelete){  
     this.isDelete = isDelete;  
    }  
    public Long getMark(){  
        return mark;  
    }
      
   public void setMark(Long mark){  
     this.mark = mark;  
    }  
}