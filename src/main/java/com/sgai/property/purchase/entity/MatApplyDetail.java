package com.sgai.property.purchase.entity;

import com.sgai.common.persistence.Page;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;
/**
 * 物料采购申请
 * @author hoocen
 * @date 2019-01-07
 */
@Entity
@Table(name = "purchase_mat_apply_detail")
public class MatApplyDetail {
	
	@Id
	private String id;
	protected String remarks; // 备注
	protected String createdBy; // 创建者
	protected Date createdDt; // 创建日期
	protected String updatedBy; // 更新者
	protected Date updatedDt; // 更新日期
	protected String delFlag; // 删除标记（0：正常；1：删除；2：审核）
	protected Integer version; //数据版本 初始化为1
	protected String comCode; //机构代码
	protected String moduCode; //模块代码
	@Transient
	private Page<MatApplyDetail> page;

    @ApiModelProperty(value = "申请理由")
    private String applyReason; //申请理由
    @ApiModelProperty(value = "审批意见")
    private String approveOption; //审批意见
    @ApiModelProperty(value = "申请日期")
    private Date applyDate; //申请日期
    @ApiModelProperty(value = "申请人id")
    private String applyEmpId; //申请人id
    @ApiModelProperty(value = "是否生成订单？0：没有，1：有")
    private Long hasOrder; //是否生成订单？0：没有，1：有
    @ApiModelProperty(value = "审批时间")
    private Date approveDate; //审批时间
    @ApiModelProperty(value = "1:已提交；2:已通过；3:已拒绝；4:已撤回")
    private Long matStat; //1:已提交；2:已通过；3:已拒绝；4:已撤回
    @ApiModelProperty(value = "所属部门名称")
    private String applyDeptName; //所属部门名称
    @ApiModelProperty(value = "申请人名称")
    private String applyEmpName; //申请人名称
    @ApiModelProperty(value = "要求领取日期")
    private Date supplyDate; //要求领取日期
    @ApiModelProperty(value = "用料审批人名称")
    private String approveEmpName; //用料审批人名称
    @ApiModelProperty(value = "用料审批人id")
    private String approveEmpId; //用料审批人id
    @ApiModelProperty(value = "申请编号")
    private String applyNo; //申请编号
    @ApiModelProperty(value = "采购单类型（0：全部采购，1：部分采购，2：已出库）")
    private String purchasetype; //采购单类型（0：全部采购，1：部分采购，2：已出库）


    public String getApplyReason() {
        return applyReason;
    }

    public void setApplyReason(String applyReason) {
        this.applyReason = applyReason;
    }

    public String getApproveOption() {
        return approveOption;
    }

    public void setApproveOption(String approveOption) {
        this.approveOption = approveOption;
    }

    public Date getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    public String getApplyEmpId() {
        return applyEmpId;
    }

    public void setApplyEmpId(String applyEmpId) {
        this.applyEmpId = applyEmpId;
    }

    public Long getHasOrder() {
        return hasOrder;
    }

    public void setHasOrder(Long hasOrder) {
        this.hasOrder = hasOrder;
    }

    public Date getApproveDate() {
        return approveDate;
    }

    public void setApproveDate(Date approveDate) {
        this.approveDate = approveDate;
    }

    public Long getMatStat() {
        return matStat;
    }

    public void setMatStat(Long matStat) {
        this.matStat = matStat;
    }

    public String getApplyDeptName() {
        return applyDeptName;
    }

    public void setApplyDeptName(String applyDeptName) {
        this.applyDeptName = applyDeptName;
    }

    public String getApplyEmpName() {
        return applyEmpName;
    }

    public void setApplyEmpName(String applyEmpName) {
        this.applyEmpName = applyEmpName;
    }

    public Date getSupplyDate() {
        return supplyDate;
    }

    public void setSupplyDate(Date supplyDate) {
        this.supplyDate = supplyDate;
    }

    public String getApproveEmpName() {
        return approveEmpName;
    }

    public void setApproveEmpName(String approveEmpName) {
        this.approveEmpName = approveEmpName;
    }

    public String getApproveEmpId() {
        return approveEmpId;
    }

    public void setApproveEmpId(String approveEmpId) {
        this.approveEmpId = approveEmpId;
    }

    public String getApplyNo() {
        return applyNo;
    }

    public void setApplyNo(String applyNo) {
        this.applyNo = applyNo;
    }

    public String getPurchasetype() {
        return purchasetype;
    }

    public void setPurchasetype(String purchasetype) {
        this.purchasetype = purchasetype;
    }

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDt() {
		return createdDt;
	}

	public void setCreatedDt(Date createdDt) {
		this.createdDt = createdDt;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDt() {
		return updatedDt;
	}

	public void setUpdatedDt(Date updatedDt) {
		this.updatedDt = updatedDt;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getComCode() {
		return comCode;
	}

	public void setComCode(String comCode) {
		this.comCode = comCode;
	}

	public String getModuCode() {
		return moduCode;
	}

	public void setModuCode(String moduCode) {
		this.moduCode = moduCode;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Page<MatApplyDetail> getPage() {
		return page;
	}

	public void setPage(Page<MatApplyDetail> page) {
		this.page = page;
	}
}