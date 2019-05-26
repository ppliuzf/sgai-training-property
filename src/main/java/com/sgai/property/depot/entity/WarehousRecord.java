package com.sgai.property.depot.entity;
import java.util.Date;
import io.swagger.annotations.ApiModelProperty;
import com.sgai.common.persistence.BoEntity;
public class WarehousRecord extends BoEntity<WarehousRecord>{

    @ApiModelProperty(value = "id")
    private String id;
    @ApiModelProperty(value = "仓库id")
    private String whId; //仓库id
    @ApiModelProperty(value = "")
    private String moduCode; //
    @ApiModelProperty(value = "物料id")
    private String matTypeId; //物料id
    @ApiModelProperty(value = "物料规格")
    private String matSpec; //物料规格
    @ApiModelProperty(value = "实际数量")
    private Long matRealNum; //实际数量
    @ApiModelProperty(value = "物料编号")
    private String matNo; //物料编号
    @ApiModelProperty(value = "订单号")
    private String orderNumber; //订单号
    @ApiModelProperty(value = "物料型号（无）")
    private String matTypeCode; //物料型号（无）
    @ApiModelProperty(value = "")
    private String comCode; //
    @ApiModelProperty(value = "差异量")
    private Long matDiffNum; //差异量
    @ApiModelProperty(value = "订单号虚拟Id")
    private String orderId; //订单号虚拟Id
    @ApiModelProperty(value = "所需数量")
    private Long matNeetNum; //所需数量
    @ApiModelProperty(value = "库存数量（账存数量）")
    private Long matNum; //库存数量（账存数量）
    @ApiModelProperty(value = "仓库名称")
    private String whName; //仓库名称
    @ApiModelProperty(value = "物料名称")
    private String matName; //物料名称
    @ApiModelProperty(value = "仓库类型")
    private String whType; //仓库类型


    public String getWhId(){  
        return whId;  
    }
      
   public void setWhId(String whId){  
     this.whId = whId;  
    }  
    public String getModuCode(){  
        return moduCode;  
    }
      
   public void setModuCode(String moduCode){  
     this.moduCode = moduCode;  
    }  
    public String getMatTypeId(){  
        return matTypeId;  
    }
      
   public void setMatTypeId(String matTypeId){  
     this.matTypeId = matTypeId;  
    }  
    public String getMatSpec(){  
        return matSpec;  
    }
      
   public void setMatSpec(String matSpec){  
     this.matSpec = matSpec;  
    }  
    public Long getMatRealNum(){  
        return matRealNum;  
    }
      
   public void setMatRealNum(Long matRealNum){  
     this.matRealNum = matRealNum;  
    }  
    public String getMatNo(){  
        return matNo;  
    }
      
   public void setMatNo(String matNo){  
     this.matNo = matNo;  
    }  
    public String getOrderNumber(){  
        return orderNumber;  
    }
      
   public void setOrderNumber(String orderNumber){  
     this.orderNumber = orderNumber;  
    }  
    public String getMatTypeCode(){  
        return matTypeCode;  
    }
      
   public void setMatTypeCode(String matTypeCode){  
     this.matTypeCode = matTypeCode;  
    }  
    public String getComCode(){  
        return comCode;  
    }
      
   public void setComCode(String comCode){  
     this.comCode = comCode;  
    }  
    public Long getMatDiffNum(){  
        return matDiffNum;  
    }
      
   public void setMatDiffNum(Long matDiffNum){  
     this.matDiffNum = matDiffNum;  
    }  
    public String getOrderId(){  
        return orderId;  
    }
      
   public void setOrderId(String orderId){  
     this.orderId = orderId;  
    }  
    public Long getMatNeetNum(){  
        return matNeetNum;  
    }
      
   public void setMatNeetNum(Long matNeetNum){  
     this.matNeetNum = matNeetNum;  
    }  
    public Long getMatNum(){  
        return matNum;  
    }
      
   public void setMatNum(Long matNum){  
     this.matNum = matNum;  
    }  
    public String getWhName(){  
        return whName;  
    }
      
   public void setWhName(String whName){  
     this.whName = whName;  
    }  
    public String getMatName(){  
        return matName;  
    }
      
   public void setMatName(String matName){  
     this.matName = matName;  
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getWhType() {
        return whType;
    }

    public void setWhType(String whType) {
        this.whType = whType;
    }
}