package com.sgai.property.notice.entity;
import java.util.Date;
import io.swagger.annotations.ApiModelProperty;
import com.sgai.common.persistence.BoEntity;
public class NoticeInfoReader extends BoEntity<NoticeInfoReader>{

    @ApiModelProperty(value = "创建时间")
    private Long createTime; //创建时间
    @ApiModelProperty(value = "公告id")
    private String infoId; //公告id
    @ApiModelProperty(value = "更新时间")
    private Long updateTime; //更新时间
    @ApiModelProperty(value = "已读人名称")
    private String empName; //已读人名称
    @ApiModelProperty(value = "已读人id")
    private Long empId; //已读人id

    public Long getCreateTime(){  
        return createTime;  
    }
      
   public void setCreateTime(Long createTime){  
     this.createTime = createTime;  
    }  
    public String getInfoId(){  
        return infoId;  
    }
      
   public void setInfoId(String infoId){  
     this.infoId = infoId;  
    }  
    public Long getUpdateTime(){  
        return updateTime;  
    }
      
   public void setUpdateTime(Long updateTime){  
     this.updateTime = updateTime;  
    }  
    public String getEmpName(){  
        return empName;  
    }
      
   public void setEmpName(String empName){  
     this.empName = empName;  
    }  
    public Long getEmpId(){  
        return empId;  
    }
      
   public void setEmpId(Long empId){  
     this.empId = empId;  
    }  
}