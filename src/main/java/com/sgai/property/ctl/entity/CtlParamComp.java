
package com.sgai.property.ctl.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.sgai.common.persistence.BoEntity;

/**
 * 机构参数维护Entity
 * @author liushang
 * @version 2017-11-10
 */
public class CtlParamComp extends BoEntity<CtlParamComp> {
	
	private static final long serialVersionUID = 1L;
	private String comCode;		// 机构代码
	private String sbsCode;		// 子系统代码
	private String paraCode;		// 参数代码
	private String paraValue;		// 参数值
	private Date beginCreatedDt;		// 开始 创建日期
	private Date endCreatedDt;		// 结束 创建日期
	
	/*自定义属性*/
	private String remark;//参数备注
	private String comName;
	private String sbsName;
	public CtlParamComp() {
		super();
	}

	public CtlParamComp(String id){
		super(id);
	}

	@Length(min=1, max=10, message="com_code长度必须介于 1 和 10 之间")
	public String getComCode() {
		return comCode;
	}

	public void setComCode(String comCode) {
		this.comCode = comCode;
	}
	
	@Length(min=1, max=10, message="sbs_code长度必须介于 1 和 10 之间")
	public String getSbsCode() {
		return sbsCode;
	}

	public void setSbsCode(String sbsCode) {
		this.sbsCode = sbsCode;
	}
	
	@Length(min=1, max=30, message="para_code长度必须介于 1 和 30 之间")
	public String getParaCode() {
		return paraCode;
	}

	public void setParaCode(String paraCode) {
		this.paraCode = paraCode;
	}
	
	@Length(min=1, max=30, message="para_value长度必须介于 1 和 30 之间")
	public String getParaValue() {
		return paraValue;
	}

	public void setParaValue(String paraValue) {
		this.paraValue = paraValue;
	}
	
	
	public Date getBeginCreatedDt() {
		return beginCreatedDt;
	}

	public void setBeginCreatedDt(Date beginCreatedDt) {
		this.beginCreatedDt = beginCreatedDt;
	}
	
	public Date getEndCreatedDt() {
		return endCreatedDt;
	}

	public void setEndCreatedDt(Date endCreatedDt) {
		this.endCreatedDt = endCreatedDt;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getComName() {
		return comName;
	}

	public void setComName(String comName) {
		this.comName = comName;
	}

	public String getSbsName() {
		return sbsName;
	}

	public void setSbsName(String sbsName) {
		this.sbsName = sbsName;
	}

		
}