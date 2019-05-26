package com.sgai.property.depot.entity;
import java.util.Date;
import io.swagger.annotations.ApiModelProperty;
import com.sgai.common.persistence.BoEntity;
public class MatOrder extends BoEntity<MatOrder>{

    @ApiModelProperty(value = "采购审批人id")
    private String buyerApproveEmpId; //采购审批人id
    @ApiModelProperty(value = "审批意见")
    private String approveOption; //审批意见
    @ApiModelProperty(value = "采购人名称")
    private String buyerEmpName; //采购人名称
    @ApiModelProperty(value = "供应商名称")
    private String supplyComName; //供应商名称
    @ApiModelProperty(value = "采购人id")
    private String buyerEmpId; //采购人id
    @ApiModelProperty(value = "采购部门")
    private String buyerDeptName; //采购部门
    @ApiModelProperty(value = "需求订单?1:需求订单;2:主动采购")
    private Long orderType; //需求订单?1:需求订单;2:主动采购
    @ApiModelProperty(value = "状态? 1:待审核；2:已拒绝；3:已通过；4:采购中；5:已完成；6:已撤回")
    private Long orderStat; //状态? 1:待审核；2:已拒绝；3:已通过；4:采购中；5:已完成；6:已撤回
    @ApiModelProperty(value = "制单人名称")
    private String orderEmpName; //制单人名称
    @ApiModelProperty(value = "需求单号")
    private String applyNo; //需求单号
    @ApiModelProperty(value = "审批时间")
    private Date approveDate; //审批时间
    @ApiModelProperty(value = "是否生成订单？0：没有，1：有")
    private Long hasOrder; //是否生成订单？0：没有，1：有
    @ApiModelProperty(value = "任务Id")
    private String taskId; //任务Id
    @ApiModelProperty(value = "供应商id")
    private String supplyComId; //供应商id
    @ApiModelProperty(value = "发票状态1:未开具2:已开具")
    private String invoiceState; //发票状态1:未开具2:已开具
    @ApiModelProperty(value = "订单编号")
    private String orderNo; //订单编号
    @ApiModelProperty(value = "制单人id")
    private String orderEmpId; //制单人id
    @ApiModelProperty(value = "制单日期")
    private Date orderDate; //制单日期
    @ApiModelProperty(value = "采购审批人名称")
    private String buyerApproveEmpName; //采购审批人名称
    @ApiModelProperty(value = "采购时间")
    private Date buyerTime; //采购时间
    @ApiModelProperty(value = "部门对象")
    private String deptJson; //部门对象
    @ApiModelProperty(value = "所属部门名称")
    private String applyDeptName; //所属部门名称

    public String getBuyerApproveEmpId() {
        return buyerApproveEmpId;
    }

    public void setBuyerApproveEmpId(String buyerApproveEmpId) {
        this.buyerApproveEmpId = buyerApproveEmpId;
    }

    public String getBuyerEmpId() {
        return buyerEmpId;
    }

    public void setBuyerEmpId(String buyerEmpId) {
        this.buyerEmpId = buyerEmpId;
    }

    public String getApproveOption(){
        return approveOption;  
    }
      
   public void setApproveOption(String approveOption){  
     this.approveOption = approveOption;  
    }  
    public String getBuyerEmpName(){  
        return buyerEmpName;  
    }
      
   public void setBuyerEmpName(String buyerEmpName){  
     this.buyerEmpName = buyerEmpName;  
    }  
    public String getSupplyComName(){  
        return supplyComName;  
    }
      
   public void setSupplyComName(String supplyComName){  
     this.supplyComName = supplyComName;  
    }
    public String getBuyerDeptName(){  
        return buyerDeptName;  
    }
      
   public void setBuyerDeptName(String buyerDeptName){  
     this.buyerDeptName = buyerDeptName;  
    }  
    public Long getOrderType(){  
        return orderType;  
    }
      
   public void setOrderType(Long orderType){  
     this.orderType = orderType;  
    }  
    public Long getOrderStat(){  
        return orderStat;  
    }
      
   public void setOrderStat(Long orderStat){  
     this.orderStat = orderStat;  
    }  
    public String getOrderEmpName(){  
        return orderEmpName;  
    }
      
   public void setOrderEmpName(String orderEmpName){  
     this.orderEmpName = orderEmpName;  
    }  
    public String getApplyNo(){  
        return applyNo;  
    }
      
   public void setApplyNo(String applyNo){  
     this.applyNo = applyNo;  
    }  
    public Date getApproveDate(){  
        return approveDate;  
    }
      
   public void setApproveDate(Date approveDate){  
     this.approveDate = approveDate;  
    }  
    public Long getHasOrder(){  
        return hasOrder;  
    }
      
   public void setHasOrder(Long hasOrder){  
     this.hasOrder = hasOrder;  
    }  
    public String getTaskId(){  
        return taskId;  
    }
      
   public void setTaskId(String taskId){  
     this.taskId = taskId;  
    }  
    public String getSupplyComId(){  
        return supplyComId;  
    }
      
   public void setSupplyComId(String supplyComId){  
     this.supplyComId = supplyComId;  
    }
    public String getInvoiceState(){
        return invoiceState;
    }
      
   public void setInvoiceState(String invoiceState){  
     this.invoiceState = invoiceState;  
    }
    public String getOrderNo(){  
        return orderNo;  
    }
      
   public void setOrderNo(String orderNo){  
     this.orderNo = orderNo;  
    }

    public String getOrderEmpId() {
        return orderEmpId;
    }

    public void setOrderEmpId(String orderEmpId) {
        this.orderEmpId = orderEmpId;
    }

    public Date getOrderDate(){
        return orderDate;  
    }
      
   public void setOrderDate(Date orderDate){  
     this.orderDate = orderDate;  
    }  
    public String getBuyerApproveEmpName(){  
        return buyerApproveEmpName;  
    }
      
   public void setBuyerApproveEmpName(String buyerApproveEmpName){  
     this.buyerApproveEmpName = buyerApproveEmpName;  
    }  
    public Date getBuyerTime(){  
        return buyerTime;  
    }
      
   public void setBuyerTime(Date buyerTime){  
     this.buyerTime = buyerTime;  
    }  
    public String getDeptJson(){  
        return deptJson;  
    }
      
   public void setDeptJson(String deptJson){  
     this.deptJson = deptJson;  
    }  
    public String getApplyDeptName(){  
        return applyDeptName;  
    }
      
   public void setApplyDeptName(String applyDeptName){  
     this.applyDeptName = applyDeptName;  
    }  
}