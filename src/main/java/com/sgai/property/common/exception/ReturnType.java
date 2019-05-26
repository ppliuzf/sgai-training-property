package com.sgai.property.common.exception;

/**
 * Created by 145846 on 2016/10/10.
 */
public enum ReturnType {
    Success("0","成功"),
    Error("-1","错误"),
    Unknown("50", "未知异常"),
    Spring("40","spring异常"),
    DB("41","DB异常"),
    ThirdParty("30","第三方平台服务异常"),
    ParamIllegal("20","参数错误"),
    Unauthorized("10", "权限错误"),
    ServiceError("60", "服务接口异常"),
    CheckError("80", "审核异常"),
    TokenError("70", "TOKEN异常");
    private String code;
    private String type;
    ReturnType(String code, String type) {
        this.code = code;
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public String getType() {
        return type;
    }
}
