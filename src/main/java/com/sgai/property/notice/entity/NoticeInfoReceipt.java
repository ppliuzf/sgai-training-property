package com.sgai.property.notice.entity;
import java.util.Date;
import io.swagger.annotations.ApiModelProperty;
import com.sgai.common.persistence.BoEntity;
public class NoticeInfoReceipt extends BoEntity<NoticeInfoReceipt>{

    @ApiModelProperty(value = "回执状态（0：未回执，1：已回执）")
    private Long receiptStatus; //回执状态（0：未回执，1：已回执）
    @ApiModelProperty(value = "创建时间")
    private Long createTime; //创建时间
    @ApiModelProperty(value = "回执人名称")
    private String receiptEmpName; //回执人名称
    @ApiModelProperty(value = "回执人id")
    private String receiptEmpId; //回执人id
    @ApiModelProperty(value = "公告id")
    private String infoId; //公告id
    @ApiModelProperty(value = "更新时间")
    private Long updateTime; //更新时间
    @ApiModelProperty(value = "回执时间")
    private Long receiptTime; //回执时间

    public Long getReceiptStatus(){  
        return receiptStatus;  
    }
      
   public void setReceiptStatus(Long receiptStatus){  
     this.receiptStatus = receiptStatus;  
    }  
    public Long getCreateTime(){  
        return createTime;  
    }
      
   public void setCreateTime(Long createTime){  
     this.createTime = createTime;  
    }  
    public String getReceiptEmpName(){  
        return receiptEmpName;  
    }
      
   public void setReceiptEmpName(String receiptEmpName){  
     this.receiptEmpName = receiptEmpName;  
    }  
    public String getReceiptEmpId(){
        return receiptEmpId;  
    }
      
   public void setReceiptEmpId(String receiptEmpId){
     this.receiptEmpId = receiptEmpId;  
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
    public Long getReceiptTime(){  
        return receiptTime;  
    }
      
   public void setReceiptTime(Long receiptTime){  
     this.receiptTime = receiptTime;  
    }  
}