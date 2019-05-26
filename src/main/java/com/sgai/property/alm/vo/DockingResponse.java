package com.sgai.property.alm.vo;

/**
 * @author ppliu
 * created in 2019/1/11 10:56
 */
public class DockingResponse {
    private String responseCommand;

    public DockingResponse() {
    }

    public DockingResponse(String responseCommand) {
        this.responseCommand = responseCommand;
    }

    public String getResponseCommand() {
        return responseCommand;
    }

    public void setResponseCommand(String responseCommand) {
        this.responseCommand = responseCommand;
    }

    public static DockingResponse ok() {
        return new DockingResponse("OK");
    }

    public static DockingResponse error() {
        return new DockingResponse("error");
    }
}
