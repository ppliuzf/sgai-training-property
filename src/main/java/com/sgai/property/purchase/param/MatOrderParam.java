package com.sgai.property.purchase.param;

import com.sgai.property.purchase.vo.MatDetailVo;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

/**
 * Created by 145811 on 2018/1/11.
 */
public class MatOrderParam {
    @ApiModelProperty(value = "id")
    private String id; //id
    @ApiModelProperty(value = "采购人名称")
    private String buyerEmpName; //采购人名称
    @ApiModelProperty(value = "采购人id")
    private Long buyerEmpId; //采购人id
    @ApiModelProperty(value = "审批意见")
    private String approveOption; //审批意见
    @ApiModelProperty(value = "状态? 1:待审核；2:已拒绝；3:已通过；4:采购中；5:已完成；6:已撤回")
    private Long orderStat; //状态? 1:待审核；2:已拒绝；3:已通过；4:采购中；5:已完成；6:已撤回
    @ApiModelProperty(value = "需求单号")
    private String applyNo; //需求单号
    @ApiModelProperty(value = "部门对象")
    private String deptJson; //部门对象
    @ApiModelProperty(value = "采购时间")
    private Date buyerTime; //采购时间
    @ApiModelProperty(value = "审批时间")
    private Date approveDate; //审批时间
    @ApiModelProperty(value = "采购部门")
    private String buyerDeptName; //采购部门
    @ApiModelProperty(value = "采购审批人名称")
    private String buyerApproveEmpName; //采购审批人名称
    @ApiModelProperty(value = "采购审批人id")
    private Long buyerApproveEmpId; //采购审批人id
    @ApiModelProperty(value = "制单日期")
    private Date orderDate; //制单日期
    @ApiModelProperty(value = "所属部门名称")
    private String applyDeptName; //所属部门名称
    @ApiModelProperty(value = "订单编号")
    private String orderNo; //订单编号
    @ApiModelProperty(value = "制单人id")
    private Long orderEmpId; //制单人id
    @ApiModelProperty(value = "制单人名称")
    private String orderEmpName; //制单人名称
    @ApiModelProperty(value = "需求订单?1:需求订单;2:主动采购")
    private Long orderType; //需求订单?1:需求订单;2:主动采购
    ///
    @ApiModelProperty(value = "物料明细")
    private List<MatDetailParam> matDetailParamList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApplyNo() {
        return applyNo;
    }

    public void setApplyNo(String applyNo) {
        this.applyNo = applyNo;
    }

    public String getApproveOption() {
        return approveOption;
    }

    public void setApproveOption(String approveOption) {
        this.approveOption = approveOption;
    }

    public Long getBuyerEmpId() {
        return buyerEmpId;
    }

    public void setBuyerEmpId(Long buyerEmpId) {
        this.buyerEmpId = buyerEmpId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }


    public Long getOrderStat() {
        return orderStat;
    }

    public void setOrderStat(Long orderStat) {
        this.orderStat = orderStat;
    }

    public String getBuyerEmpName() {
        return buyerEmpName;
    }

    public void setBuyerEmpName(String buyerEmpName) {
        this.buyerEmpName = buyerEmpName;
    }

    public Long getOrderEmpId() {
        return orderEmpId;
    }

    public void setOrderEmpId(Long orderEmpId) {
        this.orderEmpId = orderEmpId;
    }

    public Long getOrderType() {
        return orderType;
    }

    public void setOrderType(Long orderType) {
        this.orderType = orderType;
    }

    public String getOrderEmpName() {
        return orderEmpName;
    }

    public void setOrderEmpName(String orderEmpName) {
        this.orderEmpName = orderEmpName;
    }

    public List<MatDetailParam> getMatDetailParamList() {
        return matDetailParamList;
    }

    public void setMatDetailParamList(List<MatDetailParam> matDetailParamList) {
        this.matDetailParamList = matDetailParamList;
    }

	public Date getBuyerTime() {
		return buyerTime;
	}

	public void setBuyerTime(Date buyerTime) {
		this.buyerTime = buyerTime;
	}

	public Date getApproveDate() {
		return approveDate;
	}

	public void setApproveDate(Date approveDate) {
		this.approveDate = approveDate;
	}

	public String getBuyerDeptName() {
		return buyerDeptName;
	}

	public void setBuyerDeptName(String buyerDeptName) {
		this.buyerDeptName = buyerDeptName;
	}

	public String getBuyerApproveEmpName() {
		return buyerApproveEmpName;
	}

	public void setBuyerApproveEmpName(String buyerApproveEmpName) {
		this.buyerApproveEmpName = buyerApproveEmpName;
	}

	public Long getBuyerApproveEmpId() {
		return buyerApproveEmpId;
	}

	public void setBuyerApproveEmpId(Long buyerApproveEmpId) {
		this.buyerApproveEmpId = buyerApproveEmpId;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getApplyDeptName() {
		return applyDeptName;
	}

	public void setApplyDeptName(String applyDeptName) {
		this.applyDeptName = applyDeptName;
	}

	public String getDeptJson() {
		return deptJson;
	}

	public void setDeptJson(String deptJson) {
		this.deptJson = deptJson;
	}
    
}
