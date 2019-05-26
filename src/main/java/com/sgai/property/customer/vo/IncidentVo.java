package com.sgai.property.customer.vo;

import io.swagger.annotations.ApiModelProperty;

public class IncidentVo {
    @ApiModelProperty(value = "事件编号")
    private String incidentCode;		// 事件编号：对应于业务事件的编码，例如：应急事件、保修事件、投诉事件等
    @ApiModelProperty(value = "事件类型")
    private String incidentType;  // 事件类别：维修检修保养保修自动

    public String getIncidentCode() {
        return incidentCode;
    }

    public void setIncidentCode(String incidentCode) {
        this.incidentCode = incidentCode;
    }

    public String getIncidentType() {
        return incidentType;
    }

    public void setIncidentType(String incidentType) {
        this.incidentType = incidentType;
    }
}
