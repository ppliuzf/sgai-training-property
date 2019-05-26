package com.sgai.property.customer.entity;

import com.sgai.common.persistence.BoEntity;
import io.swagger.annotations.ApiModelProperty;

public class UploadRecordInfo extends BoEntity<UploadRecordInfo>{

    @ApiModelProperty(value = "导入结果")
    private String urResult; //导入结果
    @ApiModelProperty(value = "导入人姓名")
    private String urUserName; //导入人姓名
    @ApiModelProperty(value = "导入分类(1 个人 2 机构)")
    private Long urClass; //导入分类(1 个人 2 机构)
    @ApiModelProperty(value = "是否删除")
    private Long urIsDelete; //是否删除
    @ApiModelProperty(value = "文件名称")
    private String urWordName; //文件名称
    @ApiModelProperty(value = "导入方式")
    private String urType; //导入方式
    @ApiModelProperty(value = "导入时间")
    private Long urTime; //导入时间
    @ApiModelProperty(value = "导入人Id")
    private Long urUserId; //导入人Id
    @ApiModelProperty(value = "导入状态")
    private String urStatus; //导入状态

    public String getUrResult(){  
        return urResult;  
    }
      
   public void setUrResult(String urResult){  
     this.urResult = urResult;  
    }
    public String getUrUserName(){  
        return urUserName;  
    }
      
   public void setUrUserName(String urUserName){  
     this.urUserName = urUserName;  
    }
    public Long getUrClass(){  
        return urClass;  
    }
      
   public void setUrClass(Long urClass){  
     this.urClass = urClass;  
    }
    public Long getUrIsDelete(){  
        return urIsDelete;  
    }
      
   public void setUrIsDelete(Long urIsDelete){  
     this.urIsDelete = urIsDelete;  
    }
    public String getUrWordName(){  
        return urWordName;  
    }
      
   public void setUrWordName(String urWordName){  
     this.urWordName = urWordName;  
    }
    public String getUrType(){  
        return urType;  
    }
      
   public void setUrType(String urType){  
     this.urType = urType;  
    }
    public Long getUrTime(){  
        return urTime;  
    }
      
   public void setUrTime(Long urTime){  
     this.urTime = urTime;  
    }
    public Long getUrUserId(){  
        return urUserId;  
    }
      
   public void setUrUserId(Long urUserId){  
     this.urUserId = urUserId;  
    }
    public String getUrStatus(){  
        return urStatus;  
    }
      
   public void setUrStatus(String urStatus){  
     this.urStatus = urStatus;  
    }
}