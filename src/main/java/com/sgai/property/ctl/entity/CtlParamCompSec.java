package com.sgai.property.ctl.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sgai.common.persistence.BoEntity;




   /** 
    * ClassName: CtlParamCompSec  
    * Description: (这里用一句话描述这个类的作用)
    * @author admin  
    * Date 2017年11月18日  
    * Company 首自信--智慧城市创新中心
    */  
public class CtlParamCompSec extends BoEntity<CtlParamCompSec> {
	
	private static final long serialVersionUID = 1L;
	private String comCode;		// com_code
	private String sbsCode;		// sbs_code
	private String paraCode;		// para_code
	private String paraValue;		// para_value
	private Date beginCreatedDt;		// 开始 创建日期
	private Date endCreatedDt;		// 结束 创建日期
	
	
	public CtlParamCompSec() {
		super();
	}

	public CtlParamCompSec(String id){
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
	
	@Length(min=0, max=30, message="para_value长度必须介于 0 和 30 之间")
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

		
}