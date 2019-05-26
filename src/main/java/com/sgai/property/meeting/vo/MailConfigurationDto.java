package com.sgai.property.meeting.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author 146584
 * @date 2017-11-22 11:30
 */
public class MailConfigurationDto {

    @ApiModelProperty(value = "主键")
    private String mcId; //主键
    @ApiModelProperty(value = "邮件账户")
    private String mcAccount; //邮件账户
    @ApiModelProperty(value = "邮箱密码")
    private String mcPassword; //邮箱密码
    @ApiModelProperty(value = "服务器地址")
    private String mcIp; //服务器地址
    @ApiModelProperty(value = "服务器端口")
    private String mcPort; //服务器端口
    @ApiModelProperty(value = "收件看到名称")
    private String mcEmailName; //收件看到名称
    @ApiModelProperty(value = "是否需要发送状态 1 是 0否")
    private Integer isSend; //是否需要发送状态 1 是 0否

    public String getMcId() {
        return mcId;
    }

    public MailConfigurationDto setMcId(String mcId) {
        this.mcId = mcId;
        return this;
    }

    public String getMcAccount() {
        return mcAccount;
    }

    public MailConfigurationDto setMcAccount(String mcAccount) {
        this.mcAccount = mcAccount;
        return this;
    }

    public String getMcPassword() {
        return mcPassword;
    }

    public MailConfigurationDto setMcPassword(String mcPassword) {
        this.mcPassword = mcPassword;
        return this;
    }

    public String getMcIp() {
        return mcIp;
    }

    public MailConfigurationDto setMcIp(String mcIp) {
        this.mcIp = mcIp;
        return this;
    }

    public String getMcPort() {
        return mcPort;
    }

    public MailConfigurationDto setMcPort(String mcPort) {
        this.mcPort = mcPort;
        return this;
    }

    public String getMcEmailName() {
        return mcEmailName;
    }

    public MailConfigurationDto setMcEmailName(String mcEmailName) {
        this.mcEmailName = mcEmailName;
        return this;
    }

    public Integer getIsSend() {
        return isSend;
    }

    public MailConfigurationDto setIsSend(Integer isSend) {
        this.isSend = isSend;
        return this;
    }
}
