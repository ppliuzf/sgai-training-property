package com.sgai.property.ctl.entity;

import com.sgai.common.persistence.BoEntity;
import org.hibernate.validator.constraints.Length;

/**
 * 机构子系统关系Entity
 * @author admin
 * @version 2018-03-28
 */
public class CtlCompBusi extends BoEntity<CtlCompBusi> {
	
	private static final long serialVersionUID = 1L;
	private String busiCode;		// busi_code

	public CtlCompBusi() {
		super();
	}

	public CtlCompBusi(String id){
		super(id);
	}

	@Length(min=1, max=30, message="busi_code长度必须介于 1 和 30 之间")
	public String getBusiCode() {
		return busiCode;
	}

	public void setBusiCode(String busiCode) {
		this.busiCode = busiCode;
	}
	
}