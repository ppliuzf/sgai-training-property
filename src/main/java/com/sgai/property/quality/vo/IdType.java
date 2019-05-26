package com.sgai.property.quality.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class IdType {
	//身份(0：普通人,1:发起人,2:审核人
		public static final Integer ID_TYPE_NORMALER=0;
		public static final Integer ID_TYPE_SATRTER=1;
		public static final Integer ID_TYPE_CHECKER=2;
		//当前用户审核状态(0：未审核,1:已审核)前置条件为是审核人身份")
		public static final Integer ID_NO_CHECK=0;
		public static final Integer ID_CHECKED=1;
		@ApiModelProperty(value = "身份(0：普通人,1:发起人,2:审核人)")
		private Integer idType;
		@ApiModelProperty(value = "当前用户审核状态(0：未审核,1:已审核)前置条件为是审核人身份")
		private Integer currentIdApprovalStatus;
		public Integer getIdType() {
			return idType;
		}
		public void setIdType(Integer idType) {
			this.idType = idType;
		}
		public Integer getCurrentIdApprovalStatus() {
			return currentIdApprovalStatus;
		}
		public void setCurrentIdApprovalStatus(Integer currentIdApprovalStatus) {
			this.currentIdApprovalStatus = currentIdApprovalStatus;
		}
}
