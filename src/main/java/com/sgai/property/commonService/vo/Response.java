package com.sgai.property.commonService.vo;

import com.sgai.property.common.exception.BusinessException;
import com.sgai.property.common.exception.ReturnType;

/**
 * Created by 145846 on 2016/10/10.
 */
public class Response<T> {
//初始化的时候先给出默认值，等抛出异常的时候会覆盖code和message的值
    private String code= ReturnType.Success.getCode();
    private String message=ReturnType.Success.getType();
    private T data;
    public Response(T  data){
           this.code = ReturnType.Success.getCode();
           this.message =ReturnType.Success.getType();
           this.data = data;
    }
    public Response(ReturnType  returnType, T  data){
        this.code = returnType.getCode();
        this.message =returnType.getType();
        this.data = data;
    }
    public Response(BusinessException exception){
        this.code = exception.getCode();
        this.message =exception.getType();
        this.data = (T) exception.getMessage();
    }
    public Response(){ }
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
