package com.sgai.property.ctl.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sgai.common.persistence.BoEntity;

    /**  
    * @ClassName: CtlDelCheck  
    * @Description: 对象删除规则实体类 
    * @author guanze
    * @date 2017年11月18日  
    * @Company 首自信--智慧城市创新中心  
    */  
    
public class CtlDelCheck extends BoEntity<CtlDelCheck> {
	
	private static final long serialVersionUID = 1L;
	private String sbsCode;		// 模块编码
	private String oprTableName;		// 被删除表
	private String tableName;		// 引用表
	private String oprColumnName;		// 被删除表的关联字段
	private String columnName;		// 引用表的关联字段
	private String oprColumnComCode;		// 机构编码
	private String columnComCode;		// 机构编码
	private String promptDesc;		// 错误描述
	private String validFlag;		// 生效标志
	
	public CtlDelCheck() {
		super();
	}

	public CtlDelCheck(String id){
		super(id);
	}

	@Length(min=1, max=10, message="sbs_code长度必须介于 1 和 10 之间")
	public String getSbsCode() {
		return sbsCode;
	}

	public void setSbsCode(String sbsCode) {
		this.sbsCode = sbsCode;
	}
	
	@Length(min=1, max=30, message="opr_table长度必须介于 1 和 30 之间")
	public String getOprTableName() {
		return oprTableName;
	}

	public void setOprTableName(String oprTableName) {
		this.oprTableName = oprTableName;
	}
	
	@Length(min=1, max=30, message="table_name长度必须介于 1 和 30 之间")
	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	@Length(min=1, max=30, message="column_name长度必须介于 1 和 30 之间")
	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	
	@Length(min=0, max=30, message="column_com_code长度必须介于 0 和 30 之间")
	public String getColumnComCode() {
		return columnComCode;
	}

	public void setColumnComCode(String columnComCode) {
		this.columnComCode = columnComCode;
	}
	
	@Length(min=0, max=255, message="prompt_desc长度必须介于 0 和 255 之间")
	public String getPromptDesc() {
		return promptDesc;
	}

	public void setPromptDesc(String promptDesc) {
		this.promptDesc = promptDesc;
	}
	
	@Length(min=0, max=1, message="valid_flag长度必须介于 0 和 1 之间")
	public String getValidFlag() {
		return validFlag;
	}

	public void setValidFlag(String validFlag) {
		this.validFlag = validFlag;
	}
	
	public String getOprColumnName() {
		return oprColumnName;
	}

	public void setOprColumnName(String oprColumnName) {
		this.oprColumnName = oprColumnName;
	}

	public String getOprColumnComCode() {
		return oprColumnComCode;
	}

	public void setOprColumnComCode(String oprColumnComCode) {
		this.oprColumnComCode = oprColumnComCode;
	}
	
}