
package com.sgai.property.ctl.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.sgai.common.persistence.BoEntity;

/**
 * 参数定义Entity
 * @author liushang
 * @version 2017-11-08
 */
public class CtlParam extends BoEntity<CtlParam> {
	
	private static final long serialVersionUID = 1L;
	private String sbsCode;		// 子系统代码
	private String paraCode;		// 参数代码
	private String paraDesc;		// 参数描述
	private String paraLevel;		// 1.选项为:'S':系统级'E':企业级2.系统级是平台级的意思，是指所有的企业都一样的参数，不是子系统级的参数的意思；企业级参数是各个企业不同的参数。
	private String paraDatatype;		// 1.选项为:'varchar':字符型'integer':整数型'number':数值型'date':日期型默认为
	private String defauleValue;		// 缺省值
	/*自定属性*/
	private String sbsName;//联合查询取回的子系统名称
	
	public CtlParam() {
		super();
	}

	public CtlParam(String id){
		super(id);
	}

	@Length(min=0, max=10, message="sbs_id长度必须介于 0 和 10 之间")
	public String getSbsCode() {
		return sbsCode;
	}

	public void setSbsCode(String sbsCode) {
		this.sbsCode = sbsCode;
	}
	
	@Length(min=0, max=30, message="para_id长度必须介于 0 和 30 之间")
	public String getParaCode() {
		return paraCode;
	}

	public void setParaCode(String paraCode) {
		this.paraCode = paraCode;
	}
	
	@Length(min=0, max=100, message="para_desc长度必须介于 0 和 100 之间")
	public String getParaDesc() {
		return paraDesc;
	}

	public void setParaDesc(String paraDesc) {
		this.paraDesc = paraDesc;
	}
	
	//@系统级是平台级的意思，是指所有的企业都一样的参数，不是子系统级的参数的意思；企业级参数是各个企业不同的参数。长度必须介于 0 和 1 之间")
	public String getParaLevel() {
		return paraLevel;
	}

	public void setParaLevel(String paraLevel) {
		this.paraLevel = paraLevel;
	}
	
	//@选项为:'varchar':字符型'integer':整数型'number':数值型'date':日期型默认为长度必须介于 0 和 10 之间")
	public String getParaDatatype() {
		return paraDatatype;
	}

	public void setParaDatatype(String paraDatatype) {
		this.paraDatatype = paraDatatype;
	}
	
	@Length(min=0, max=30, message="defaule_value长度必须介于 0 和 30 之间")
	public String getDefauleValue() {
		return defauleValue;
	}

	public void setDefauleValue(String defauleValue) {
		this.defauleValue = defauleValue;
	}
	
	public String getSbsName() {
		return sbsName;
	}

	public void setSbsName(String sbsName) {
		this.sbsName = sbsName;
	}
	
}