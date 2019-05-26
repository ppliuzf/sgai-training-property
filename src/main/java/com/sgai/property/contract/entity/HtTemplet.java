package com.sgai.property.contract.entity;

import com.sgai.common.persistence.BoEntity;
import io.swagger.annotations.ApiModelProperty;
public class HtTemplet extends BoEntity<HtTemplet>{
     
    @ApiModelProperty(value = "文件名称")
    private String fileName; //文件名称
    @ApiModelProperty(value = "上传者")
    private String uploadBy; //上传者
    @ApiModelProperty(value = "描述")
    private String description; //描述
    @ApiModelProperty(value = "合同类型")
    private String typeId; //合同类型
    @ApiModelProperty(value = "FEED_ID")
    private String feedId; //FEED_ID
    @ApiModelProperty(value = "COM_NAME")
    private String comName; //COM_NAME
    @ApiModelProperty(value = "合同模板名称")
    private String name; //合同模板名称
    @ApiModelProperty(value = "合同模板下载地址")
    private String url; //合同模板下载地址
    @ApiModelProperty(value = "TOON_USER_ID")
    private Long toonUserId; //TOON_USER_ID
    @ApiModelProperty(value = "COM_ID")
    private Long comId; //COM_ID
    @ApiModelProperty(value = "合同模板编号")
    private String no; //合同模板编号
    @ApiModelProperty(value = "是否删除；1:是;0:否")
    private Long isDelete; //是否删除；1:是;0:否
	
    public String getFileName(){  
        return fileName;  
    }
      
   public void setFileName(String fileName){  
     this.fileName = fileName;  
    }  
    public String getUploadBy(){  
        return uploadBy;  
    }
      
   public void setUploadBy(String uploadBy){  
     this.uploadBy = uploadBy;  
    }  
    public String getDescription(){  
        return description;  
    }
      
   public void setDescription(String description){  
     this.description = description;  
    }  
    public String getTypeId(){  
        return typeId;  
    }
      
   public void setTypeId(String typeId){  
     this.typeId = typeId;  
    }  
    public String getFeedId(){  
        return feedId;  
    }
      
   public void setFeedId(String feedId){  
     this.feedId = feedId;  
    }  
    public String getComName(){  
        return comName;  
    }
      
   public void setComName(String comName){  
     this.comName = comName;  
    }  
    public String getName(){  
        return name;  
    }
      
   public void setName(String name){  
     this.name = name;  
    }  
    public String getUrl(){  
        return url;  
    }
      
   public void setUrl(String url){  
     this.url = url;  
    }  
    public Long getToonUserId(){  
        return toonUserId;  
    }
      
   public void setToonUserId(Long toonUserId){  
     this.toonUserId = toonUserId;  
    }  
    public Long getComId(){  
        return comId;  
    }
      
   public void setComId(Long comId){  
     this.comId = comId;  
    }  
    public String getNo(){  
        return no;  
    }
      
   public void setNo(String no){  
     this.no = no;  
    }  
    public Long getIsDelete(){  
        return isDelete;  
    }
      
   public void setIsDelete(Long isDelete){  
     this.isDelete = isDelete;  
    }  
}