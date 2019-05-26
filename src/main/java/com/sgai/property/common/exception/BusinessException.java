package com.sgai.property.common.exception;

public class BusinessException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private String code;
    private String message;
    private String type;
    public BusinessException(String code,String type, String message) {
        this.code = code;
        this.type = type;
        this.message = message;
    }
    public BusinessException(ReturnType returnType, String message ) {
        this.code = returnType.getCode();
        this.type = returnType.getType();
        this.message = message;
    }
    public BusinessException(ReturnType  returnType, String message,Exception e) {
        this.code = returnType.getCode();
        this.type = returnType.getType();
        this.message = message;
    }
	public String getCode() {
        return code;
    }
 
    public void setCode(String code) {
        this.code = code;
    }
 
    public String getMessage() {
        return message;
    }
 
    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
