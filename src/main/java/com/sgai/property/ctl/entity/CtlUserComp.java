package com.sgai.property.ctl.entity;
import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sgai.common.persistence.BoEntity;

import io.swagger.annotations.ApiModelProperty;


/**
 * 
    * ClassName: CtlUserComp  
    * Description: (用户机构关联实体类)
    * @author 王天尧  
    * Date 2017年11月20日  
    * Company 首自信--智慧城市创新中心
 */
public class CtlUserComp extends BoEntity<CtlUserComp> {
	@ApiModelProperty(value = "主键")
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "用户代码")
	private String userCode;		// 用户代码
	@ApiModelProperty(value = "机构代码")
	private String comCode;		// 机构代码
	
	public CtlUserComp() {
		super();
	}

	public CtlUserComp(String id){
		super(id);
	}

	@Length(min=1, max=30, message="user_code长度必须介于 1 和 30 之间")
	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	
	
}