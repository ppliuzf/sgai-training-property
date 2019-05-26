package com.sgai.property.ctl.entity;
import java.util.Date;
import org.hibernate.validator.constraints.Length;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sgai.common.persistence.BoEntity;

import io.swagger.annotations.ApiModelProperty;

/**
 * 
    * ClassName: CtlComBusiRelation  
    * Description: (子系统配置实体类)
    * @author 王天尧  
    * Date 2017年11月20日  
    * Company 首自信--智慧城市创新中心
 */
public class CtlComBusiRelation extends BoEntity<CtlComBusiRelation> {
	@ApiModelProperty(value = "主键")
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "机构代码")
	private String comCode;		// 机构代码
	@ApiModelProperty(value = "机构名称")
	private String comName;		// 机构名称
	@ApiModelProperty(value = "系统代码")
	private String busiCode;		// 系统代码
	@ApiModelProperty(value = "系统名称")
	private String busiName;		// 系统名称
	
	public CtlComBusiRelation() {
		super();
	}

	public CtlComBusiRelation(String id){
		super(id);
	}

	@Length(min=1, max=10, message="机构代码长度必须介于 1 和 10 之间")
	public String getComCode() {
		return comCode;
	}

	public void setComCode(String comCode) {
		this.comCode = comCode;
	}
	
	@Length(min=1, max=60, message="机构名称长度必须介于 1 和 60 之间")
	public String getComName() {
		return comName;
	}

	public void setComName(String comName) {
		this.comName = comName;
	}
	
	@Length(min=1, max=10, message="系统代码长度必须介于 1 和 10 之间")
	public String getBusiCode() {
		return busiCode;
	}

	public void setBusiCode(String busiCode) {
		this.busiCode = busiCode;
	}
	
	@Length(min=1, max=60, message="系统名称长度必须介于 1 和 60 之间")
	public String getBusiName() {
		return busiName;
	}

	public void setBusiName(String busiName) {
		this.busiName = busiName;
	}
	
	
	
}