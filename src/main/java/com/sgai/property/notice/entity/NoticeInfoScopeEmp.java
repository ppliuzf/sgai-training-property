package com.sgai.property.notice.entity;
import java.util.Date;
import io.swagger.annotations.ApiModelProperty;
import com.sgai.common.persistence.BoEntity;
public class NoticeInfoScopeEmp extends BoEntity<NoticeInfoScopeEmp>{

    @ApiModelProperty(value = "创建时间")
    private Long createTime; //创建时间
    @ApiModelProperty(value = "公告id")
    private String infoId; //公告id
    @ApiModelProperty(value = "更新时间")
    private Long updateTime; //更新时间
    @ApiModelProperty(value = "人员id(发布范围内的)")
    private String empId; //人员id(发布范围内的)

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
    public String getEmpId(){
        return empId;  
    }
      
   public void setEmpId(String empId){
     this.empId = empId;  
    }  
}