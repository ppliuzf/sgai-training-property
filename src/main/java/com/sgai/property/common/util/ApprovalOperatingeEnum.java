package com.sgai.property.common.util;
/**
 * 审核操作枚举
 * @author wuzhihui
 *
 */
public enum ApprovalOperatingeEnum {
	
	START(0,"发起审核"),
	PASS(1,"审核通过"),
	NO_PASS(2,"打回"),
	NO_CHECK(3,"未审核");
	
	private Integer status;
	private String statusDesc;
	
	ApprovalOperatingeEnum(Integer status, String statusDesc) {
		this.status = status;
		this.statusDesc = statusDesc;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getStatusDesc() {
		return statusDesc;
	}
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public static String getStatusDesc(Integer status){
		String result="";
		for (ApprovalOperatingeEnum approvalOperatinge :ApprovalOperatingeEnum.values()) {
			if(approvalOperatinge.status.equals(status)){
				return approvalOperatinge.statusDesc;
			}
		}
		return result;
	}
}
