package com.sgai.property.ctl.entity;
import java.util.Date;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sgai.common.persistence.BoEntity;

import io.swagger.annotations.ApiModelProperty;

/**
 * 
    * ClassName: CtlBusinessDefine  
    * Description: (子系统实体类)
    * @author 王天尧  
    * Date 2017年11月20日  
    * Company 首自信--智慧城市创新中心
 */
public class CtlBusinessDefine extends BoEntity<CtlBusinessDefine> {
	@ApiModelProperty(value = "主键")
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "系统代码")
	private String busiCode;		// 系统代码
	@ApiModelProperty(value = "系统名称")
	private String busiName;		// 系统名称
	@ApiModelProperty(value = "系统背景图片")
	private String busiIoc;		// 系统背景图片
	@ApiModelProperty(value = "系统访问主页")
	private String bannerImg;		// 系统访问主页
	@ApiModelProperty(value = "背景图片")
	private String backImg;		// 背景图片
	@ApiModelProperty(value = "显示顺序")
	private Long displayOrder;		// 显示顺序
	@ApiModelProperty(value = "系统描述")
	private String busiDesc;		// 系统描述
	private char enabledFlag;		// 1.选项为:'Y':是'N':否默认为'Y'
	private Integer version;		// 版本号
	
	public CtlBusinessDefine() {
		super();
		this.enabledFlag='Y';
	}

	public CtlBusinessDefine(String id){
		super(id);
	}
	
	
	public String getBusiCode() {
		return busiCode;
	}

	public void setBusiCode(String busiCode) {
		this.busiCode = busiCode;
	}

	public String getBusiName() {
		return busiName;
	}

	public void setBusiName(String busiName) {
		this.busiName = busiName;
	}

	public String getBusiIoc() {
		return busiIoc;
	}

	public void setBusiIoc(String busiIoc) {
		this.busiIoc = busiIoc;
	}

	public String getBusiDesc() {
		return busiDesc;
	}

	public void setBusiDesc(String busiDesc) {
		this.busiDesc = busiDesc;
	}

	@Length(min=0, max=255, message="系统访问banner长度必须介于 0 和 255 之间")
	public String getBannerImg() {
		return bannerImg;
	}

	public void setBannerImg(String bannerImg) {
		this.bannerImg = bannerImg;
	}
	
	@Length(min=0, max=255, message="背景图片长度必须介于 0 和 255 之间")
	public String getBackImg() {
		return backImg;
	}

	public void setBackImg(String backImg) {
		this.backImg = backImg;
	}
	
	@NotNull(message="显示顺序不能为空")
	public Long getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(Long displayOrder) {
		this.displayOrder = displayOrder;
	}
	public char getEnabledFlag() {
		return enabledFlag;
	}

	public void setEnabledFlag(char enabledFlag) {
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
	
}