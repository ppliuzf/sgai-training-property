package com.sgai.property.mdm.entity;

import org.hibernate.validator.constraints.Length;
import com.sgai.common.persistence.BoEntity;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
    * ClassName: MdmBrandInfo  
    * com.sgai.property.commonService.vo;(品牌信息实体类)
    * @author yangyz  
    * Date 2017年11月24日  
    * Company 首自信--智慧城市创新中心
 */
public class MdmBrandInfo extends BoEntity<MdmBrandInfo> {
	
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "品牌编码")
	private String brandCode;		// 品牌编码
	@ApiModelProperty(value = "品牌名称")
	private String brandName;		// 品牌名称
	@ApiModelProperty(value = "品牌说明")
	private String brandDesc;		// 品牌说明
	@ApiModelProperty(value = "可用标识")
	private String enabledFlag;		// 1.选项为:'Y':是'N':否默认为'Y'
	
	public MdmBrandInfo() {
		super();
	}

	public MdmBrandInfo(String id){
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
	
	@Length(min=0, max=512, message="品牌说明长度必须介于 0 和 512 之间")
	public String getBrandDesc() {
		return brandDesc;
	}

	public void setBrandDesc(String brandDesc) {
		this.brandDesc = brandDesc;
	}
	
	//@选项为:'Y':是'N':否默认为'Y'长度必须介于 1 和 1 之间")
	public String getEnabledFlag() {
		return enabledFlag;
	}

	public void setEnabledFlag(String enabledFlag) {
		this.enabledFlag = enabledFlag;
	}

}