package com.sgai.property.purchase.entity;
import java.util.Date;
import io.swagger.annotations.ApiModelProperty;
import com.sgai.common.persistence.BoEntity;
public class MatOrderInvoice extends BoEntity<MatOrderInvoice>{

    @ApiModelProperty(value = "订单Id")
    private String orderId; //订单Id
    @ApiModelProperty(value = "发票名称")
    private String invoiceName; //发票名称
    @ApiModelProperty(value = "地址")
    private String invoiceSite; //地址
    @ApiModelProperty(value = "纳税人识别号")
    private String tallageNumber; //纳税人识别号
    @ApiModelProperty(value = "发票金额")
    private String invoiceMoney; //发票金额
    @ApiModelProperty(value = "电话")
    private String invoicePhone; //电话

    public String getOrderId(){  
        return orderId;  
    }
      
   public void setOrderId(String orderId){  
     this.orderId = orderId;  
    }  
    public String getInvoiceName(){  
        return invoiceName;  
    }
      
   public void setInvoiceName(String invoiceName){  
     this.invoiceName = invoiceName;  
    }  
    public String getInvoiceSite(){  
        return invoiceSite;  
    }
      
   public void setInvoiceSite(String invoiceSite){  
     this.invoiceSite = invoiceSite;  
    }  
    public String getTallageNumber(){  
        return tallageNumber;  
    }
      
   public void setTallageNumber(String tallageNumber){  
     this.tallageNumber = tallageNumber;  
    }  
    public String getInvoiceMoney(){  
        return invoiceMoney;  
    }
      
   public void setInvoiceMoney(String invoiceMoney){  
     this.invoiceMoney = invoiceMoney;  
    }  
    public String getInvoicePhone(){  
        return invoicePhone;  
    }
      
   public void setInvoicePhone(String invoicePhone){  
     this.invoicePhone = invoicePhone;  
    }  
}