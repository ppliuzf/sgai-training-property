package com.sgai.property.meeting.entity;

import java.util.Date;
import io.swagger.annotations.ApiModelProperty;
import com.sgai.common.persistence.BoEntity;

public class Materiel extends BoEntity<Materiel>{

    @ApiModelProperty(value = "是否删除")
    private Long isDelete; //是否删除
    @ApiModelProperty(value = "物料数量")
    private Long maCount; //物料数量
    @ApiModelProperty(value = "创建时间")
    private Long createTime; //创建时间
    @ApiModelProperty(value = "物料类型id")
    private String maTypeId; //物料类型id
    @ApiModelProperty(value = "公司id")
    private String comId; //公司id
    @ApiModelProperty(value = "物料id")
    private String maId; //物料id
    @ApiModelProperty(value = "物料code")
    private String maCode; //物料code
    @ApiModelProperty(value = "更新时间")
    private Long updateTime; //更新时间
    @ApiModelProperty(value = "物料缩略图url")
    private String maThumbUrl; //物料缩略图url
    @ApiModelProperty(value = "物料名称")
    private String maName; //物料名称
    @ApiModelProperty(value = "对应关联会议id")
    private String mtId; //对应关联会议id
    @ApiModelProperty(value = "物料类型名称")
    private String maTypeName; //物料类型名称

    
    
    /**
	 * maId.
	 *
	 * @return  the maId
	 * @since   JDK 1.8
	 */
	public String getMaId() {
		return maId;
	}

	/**
	 * maId.
	 *
	 * @param   maId    the maId to set
	 * @since   JDK 1.8
	 */
	public void setMaId(String maId) {
		this.maId = maId;
	}

	/**
	 * maCode.
	 *
	 * @return  the maCode
	 * @since   JDK 1.8
	 */
	public String getMaCode() {
		return maCode;
	}

	/**
	 * maCode.
	 *
	 * @param   maCode    the maCode to set
	 * @since   JDK 1.8
	 */
	public void setMaCode(String maCode) {
		this.maCode = maCode;
	}

	public Long getIsDelete(){  
        return isDelete;  
    }
      
   public void setIsDelete(Long isDelete){  
     this.isDelete = isDelete;  
    }
    public Long getMaCount(){  
        return maCount;  
    }
      
   public void setMaCount(Long maCount){  
     this.maCount = maCount;  
    }
    public Long getCreateTime(){  
        return createTime;  
    }
      
   public void setCreateTime(Long createTime){  
     this.createTime = createTime;  
    }
    public String getMaTypeId(){  
        return maTypeId;  
    }
      
   public void setMaTypeId(String maTypeId){  
     this.maTypeId = maTypeId;  
    }
    public String getComId(){  
        return comId;  
    }
      
   public void setComId(String comId){  
     this.comId = comId;  
    }
    public Long getUpdateTime(){  
        return updateTime;  
    }
      
   public void setUpdateTime(Long updateTime){  
     this.updateTime = updateTime;  
    }
    public String getMaThumbUrl(){  
        return maThumbUrl;  
    }
      
   public void setMaThumbUrl(String maThumbUrl){  
     this.maThumbUrl = maThumbUrl;  
    }
    public String getMaName(){  
        return maName;  
    }
      
   public void setMaName(String maName){  
     this.maName = maName;  
    }
    public String getMtId(){  
        return mtId;  
    }
      
   public void setMtId(String mtId){  
     this.mtId = mtId;  
    }
    public String getMaTypeName(){  
        return maTypeName;  
    }
      
   public void setMaTypeName(String maTypeName){  
     this.maTypeName = maTypeName;  
    }
}