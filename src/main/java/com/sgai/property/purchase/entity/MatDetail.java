package com.sgai.property.purchase.entity;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import io.swagger.annotations.ApiModelProperty;
import com.sgai.common.persistence.Page;

@Table(name="PURCHASE_MAT_DETAIL")
public class MatDetail{

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
	protected Page<MatDetail> page;
	
    @ApiModelProperty(value = "物料名称")
    private String matName; //物料名称
    @ApiModelProperty(value = "")
    private String matTypeId; //
    @ApiModelProperty(value = "物料规格")
    private String matSpec; //物料规格
    @ApiModelProperty(value = "物料型号")
    private String matTypeCode; //物料型号
    @ApiModelProperty(value = "采购数量")
    private Long buyCount; //采购数量
    @ApiModelProperty(value = "供应商id")
    private String supplyComId; //供应商id
    @ApiModelProperty(value = "需求编号，如：CL-00000001")
    private String applyNo; //需求编号，如：CL-00000001
    @ApiModelProperty(value = "需求数量")
    private Long applyCount; //需求数量
    @ApiModelProperty(value = "订单编号，如：CA-00000001")
    private String orderNo; //订单编号，如：CA-00000001
    @ApiModelProperty(value = "供应商名称")
    private String supplyComName; //供应商名称

    public String getMatName(){  
        return matName;  
    }
      
   public void setMatName(String matName){  
     this.matName = matName;  
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
    public String getMatTypeCode(){  
        return matTypeCode;  
    }
      
   public void setMatTypeCode(String matTypeCode){  
     this.matTypeCode = matTypeCode;  
    }  
    public Long getBuyCount(){  
        return buyCount;  
    }
      
   public void setBuyCount(Long buyCount){  
     this.buyCount = buyCount;  
    }  
    public String getSupplyComId(){  
        return supplyComId;  
    }
      
   public void setSupplyComId(String supplyComId){  
     this.supplyComId = supplyComId;  
    }  
    public String getApplyNo(){  
        return applyNo;  
    }
      
   public void setApplyNo(String applyNo){  
     this.applyNo = applyNo;  
    }  
    public Long getApplyCount(){  
        return applyCount;  
    }
      
   public void setApplyCount(Long applyCount){  
     this.applyCount = applyCount;  
    }  
    public String getOrderNo(){  
        return orderNo;  
    }
      
   public void setOrderNo(String orderNo){  
     this.orderNo = orderNo;  
    }  
    public String getSupplyComName(){  
        return supplyComName;  
    }
      
   public void setSupplyComName(String supplyComName){  
     this.supplyComName = supplyComName;  
    }

public String getId() {
	return id;
}

public void setId(String id) {
	this.id = id;
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

public Page<MatDetail> getPage() {
	return page;
}

public void setPage(Page<MatDetail> page) {
	this.page = page;
}  
}