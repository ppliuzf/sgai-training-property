package com.sgai.property.mdm.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sgai.common.persistence.BoEntity;
import com.sgai.property.util.ExcelField;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 物料信息表Entity
 * @author liushang
 * @version 2017-11-24
 */
public class MdmMatInfo extends BoEntity<MdmMatInfo> {

	private static final long serialVersionUID = 1L;
	private String brandCode;		// 品牌编码
	private String brandName;		// 品牌名称
	private String matCode;		// 物料编码
	@ExcelField(title = "物料名称",type = 1,sort = 1,align = 2)
	private String matName;		// 物料名称
	private String matTypeCode;		// 物料分类，引用物料分类表
	@ExcelField(title = "型号规格",type = 1,sort = 1,align = 2)
	private String spec;		// 型号规格
	private String unit;		// 引用数据字典
	@ExcelField(title = "存量上限",type = 1,sort = 1,align = 2)
	private Double stockLimit;		// 存量上限
	@ExcelField(title = "存量下限",type = 1,sort = 1,align = 2)
	private Double stockOffline;		// 存量下限
	private String matUse;		// 引用数据字典
	private String enabledFlag;		// 生效标识：1.选项为:'Y':是'N':否默认为'Y'
	private Integer version;		// 版本号
//	private Date updatedDt;		// 修改时间
//	private String updatedBy;		// 修改人
//	private Date createdDt;		// 创建日期
//	private String createdBy;		// 创建者
	private String remarks;		//备注

	//展示字段"matTypeName","matName","specName","unitName","stockLimit","stockOffline","matUseName","remarks"
	@ExcelField(title = "物料分类",type = 1,sort = 1,align = 2)
	private String matTypeName;
	private String specName;
	@ExcelField(title = "计量单位",type = 1,sort = 1,align = 2)
	private String unitName;
	@ExcelField(title = "用途",type = 1,sort = 1,align = 2)
	private String matUseName;

	public MdmMatInfo() {
		super();
	}

	public MdmMatInfo(String id){
		super(id);
	}

	@Length(min=0, max=60, message="品牌编码长度必须介于 0 和 60 之间")
	public String getBrandCode() {
		return brandCode;
	}

	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
	}

	@Length(min=0, max=60, message="品牌名称长度必须介于 0 和 60 之间")
	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	@Length(min=0, max=32, message="物料编码长度必须介于 0 和 32 之间")
	public String getMatCode() {
		return matCode;
	}

	public void setMatCode(String matCode) {
		this.matCode = matCode;
	}

	@Length(min=0, max=64, message="物料名称长度必须介于 0 和 64 之间")
	public String getMatName() {
		return matName;
	}

	public void setMatName(String matName) {
		this.matName = matName;
	}

	@Length(min=0, max=32, message="物料分类，引用物料分类表长度必须介于 0 和 32 之间")
	public String getMatTypeCode() {
		return matTypeCode;
	}

	public void setMatTypeCode(String matTypeCode) {
		this.matTypeCode = matTypeCode;
	}

	@Length(min=0, max=32, message="型号规格长度必须介于 0 和 32 之间")
	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	@Length(min=0, max=32, message="引用数据字典长度必须介于 0 和 32 之间")
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Double getStockLimit() {
		return stockLimit;
	}

	public void setStockLimit(Double stockLimit) {
		this.stockLimit = stockLimit;
	}

	public Double getStockOffline() {
		return stockOffline;
	}

	public void setStockOffline(Double stockOffline) {
		this.stockOffline = stockOffline;
	}

	@Length(min=0, max=32, message="引用数据字典长度必须介于 0 和 32 之间")
	public String getMatUse() {
		return matUse;
	}

	public void setMatUse(String matUse) {
		this.matUse = matUse;
	}

	//@选项为:'Y':是'N':否默认为'Y'长度必须介于 1 和 1 之间")
	public String getEnabledFlag() {
		return enabledFlag;
	}

	public void setEnabledFlag(String enabledFlag) {
		this.enabledFlag = enabledFlag;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getUpdatedDt() {
		return updatedDt;
	}

	public void setUpdatedDt(Date updatedDt) {
		this.updatedDt = updatedDt;
	}

	@Length(min=0, max=32, message="修改人长度必须介于 0 和 32 之间")
	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="创建日期不能为空")
	public Date getCreatedDt() {
		return createdDt;
	}

	public void setCreatedDt(Date createdDt) {
		this.createdDt = createdDt;
	}

	@Length(min=0, max=32, message="创建者长度必须介于 0 和 32 之间")
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getMatTypeName() {
		return matTypeName;
	}

	public void setMatTypeName(String matTypeName) {
		this.matTypeName = matTypeName;
	}

	public String getSpecName() {
		return specName;
	}

	public void setSpecName(String specName) {
		this.specName = specName;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getMatUseName() {
		return matUseName;
	}

	public void setMatUseName(String matUseName) {
		this.matUseName = matUseName;
	}

}
