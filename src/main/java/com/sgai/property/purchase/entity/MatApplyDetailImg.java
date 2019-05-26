package com.sgai.property.purchase.entity;
import java.util.Date;
import io.swagger.annotations.ApiModelProperty;
import com.sgai.common.persistence.BoEntity;
public class MatApplyDetailImg extends BoEntity<MatApplyDetailImg>{

    @ApiModelProperty(value = "url")
    private String imgUrl; //url
    @ApiModelProperty(value = "用料申请单id")
    private String applyId; //用料申请单id

    public String getImgUrl(){  
        return imgUrl;  
    }
      
   public void setImgUrl(String imgUrl){  
     this.imgUrl = imgUrl;  
    }  
    public String getApplyId(){  
        return applyId;  
    }
      
   public void setApplyId(String applyId){  
     this.applyId = applyId;  
    }  
}