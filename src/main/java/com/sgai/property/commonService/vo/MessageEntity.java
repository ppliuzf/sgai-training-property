package com.sgai.property.commonService.vo;

import java.io.Serializable;

/**
 * 消息bean
 *
 * @author 146584
 * @create 2017-11-07 16:16
 */
public class MessageEntity implements Serializable {

    private String toFeedId;      //显示谁的名片
    private String toonUserId;    //接收人的toonUserId  决定发给谁的凭证
    private String message;       //消息内容
    private String appId;         //应用ID
    private String appSecret;     //应用密钥
    private String toonUrl;       //toon通知 点击访问地址
    private String showHeadFlag;  //是否显示用户头像
    private String bizNo;         //消息编号 (消息回显时候能用到)
    private int finishFlag;       //1 显示已完成 0 不显示
    private String subCatalog;    //消息头
    private String summary;
    private String msgType; //消息类型，默认为51
    private Integer catalogId; //类型ID，默认为142

    public String getSubCatalog() {
        return subCatalog;
    }

    public MessageEntity setSubCatalog(String subCatalog) {
        this.subCatalog = subCatalog;
        return this;
    }

    public String getToFeedId() {
        return toFeedId;
    }

    public MessageEntity setToFeedId(String toFeedId) {
        this.toFeedId = toFeedId;
        return this;
    }

    public String getToonUserId() {
        return toonUserId;
    }

    public MessageEntity setToonUserId(String toonUserId) {
        this.toonUserId = toonUserId;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public MessageEntity setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getAppId() {
        return appId;
    }

    public MessageEntity setAppId(String appId) {
        this.appId = appId;
        return this;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public MessageEntity setAppSecret(String appSecret) {
        this.appSecret = appSecret;
        return this;
    }

    public String getToonUrl() {
        return toonUrl;
    }

    public MessageEntity setToonUrl(String toonUrl) {
        this.toonUrl = toonUrl;
        return this;
    }

    public String getShowHeadFlag() {
        return showHeadFlag;
    }

    public MessageEntity setShowHeadFlag(String showHeadFlag) {
        this.showHeadFlag = showHeadFlag;
        return this;
    }

    public String getBizNo() {
        return bizNo;
    }

    public MessageEntity setBizNo(String bizNo) {
        this.bizNo = bizNo;
        return this;
    }

    public int getFinishFlag() {
        return finishFlag;
    }

    public MessageEntity setFinishFlag(int finishFlag) {
        this.finishFlag = finishFlag;
        return this;
    }

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public Integer getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(Integer catalogId) {
        this.catalogId = catalogId;
    }
}
