package com.sgai.property.notice.entity;
import java.util.Date;
import io.swagger.annotations.ApiModelProperty;
import com.sgai.common.persistence.BoEntity;
public class NoticeInfoScope extends BoEntity<NoticeInfoScope>{

    @ApiModelProperty(value = "发布范围显示字符串")
    private String infoScopeStr; //发布范围显示字符串
    @ApiModelProperty(value = "创建时间")
    private Long createTime; //创建时间
    @ApiModelProperty(value = "公告发布范围（部门id）")
    private String infoScopeDeparts; //公告发布范围（部门id）
    @ApiModelProperty(value = "发布范围对象")
    private String infoScopeObject; //发布范围对象
    @ApiModelProperty(value = "公告id")
    private String infoId; //公告id
    @ApiModelProperty(value = "更新时间")
    private Long updateTime; //更新时间
    @ApiModelProperty(value = "发布范围所选人数")
    private Long infoScopeEmpNum; //发布范围所选人数
    @ApiModelProperty(value = "公告发布范围（人员id）")
    private String infoScope; //公告发布范围（人员id）

    public String getInfoScopeStr(){  
        return infoScopeStr;  
    }
      
   public void setInfoScopeStr(String infoScopeStr){  
     this.infoScopeStr = infoScopeStr;  
    }  
    public Long getCreateTime(){  
        return createTime;  
    }
      
   public void setCreateTime(Long createTime){  
     this.createTime = createTime;  
    }  
    public String getInfoScopeDeparts(){  
        return infoScopeDeparts;  
    }
      
   public void setInfoScopeDeparts(String infoScopeDeparts){  
     this.infoScopeDeparts = infoScopeDeparts;  
    }  
    public String getInfoScopeObject(){  
        return infoScopeObject;  
    }
      
   public void setInfoScopeObject(String infoScopeObject){  
     this.infoScopeObject = infoScopeObject;  
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
    public Long getInfoScopeEmpNum(){  
        return infoScopeEmpNum;  
    }
      
   public void setInfoScopeEmpNum(Long infoScopeEmpNum){  
     this.infoScopeEmpNum = infoScopeEmpNum;  
    }  
    public String getInfoScope(){  
        return infoScope;  
    }
      
   public void setInfoScope(String infoScope){  
     this.infoScope = infoScope;  
    }  
}