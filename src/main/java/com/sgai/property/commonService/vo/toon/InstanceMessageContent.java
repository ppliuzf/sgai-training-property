package com.sgai.property.commonService.vo.toon;

import java.io.Serializable;

public class InstanceMessageContent implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3963779475950315205L;
	
	private Integer catalogId;
	
	private String catalog;
	
	private String subCatalog;
	
	private Integer subCatalogId;
	
	private Integer headFlag;
	
	private String headFeed;
	
	private Integer finishFlag;
	
	private String summary;
	
	private Integer actionType;
	
	private Integer bubbleFlag;
	
	private String bizNo;
	
	private Integer showFlag;
	
	private Long expireTime;
	
	private Integer contentType;
	
	private String content;
	
	private String appInfo;
	
	private String senderName;

	public Integer getCatalogId() {
		return catalogId;
	}

	public void setCatalogId(Integer catalogId) {
		this.catalogId = catalogId;
	}

	public String getCatalog() {
		return catalog;
	}

	public void setCatalog(String catalog) {
		this.catalog = catalog;
	}

	public String getSubCatalog() {
		return subCatalog;
	}

	public void setSubCatalog(String subCatalog) {
		this.subCatalog = subCatalog;
	}

	public Integer getSubCatalogId() {
		return subCatalogId;
	}

	public void setSubCatalogId(Integer subCatalogId) {
		this.subCatalogId = subCatalogId;
	}

	public Integer getHeadFlag() {
		return headFlag;
	}

	public void setHeadFlag(Integer headFlag) {
		this.headFlag = headFlag;
	}

	public String getHeadFeed() {
		return headFeed;
	}

	public void setHeadFeed(String headFeed) {
		this.headFeed = headFeed;
	}

	public Integer getFinishFlag() {
		return finishFlag;
	}

	public void setFinishFlag(Integer finishFlag) {
		this.finishFlag = finishFlag;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Integer getActionType() {
		return actionType;
	}

	public void setActionType(Integer actionType) {
		this.actionType = actionType;
	}

	public Integer getBubbleFlag() {
		return bubbleFlag;
	}

	public void setBubbleFlag(Integer bubbleFlag) {
		this.bubbleFlag = bubbleFlag;
	}

	public String getBizNo() {
		return bizNo;
	}

	public void setBizNo(String bizNo) {
		this.bizNo = bizNo;
	}

	public Integer getShowFlag() {
		return showFlag;
	}

	public void setShowFlag(Integer showFlag) {
		this.showFlag = showFlag;
	}

	public Long getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Long expireTime) {
		this.expireTime = expireTime;
	}

	public Integer getContentType() {
		return contentType;
	}

	public void setContentType(Integer contentType) {
		this.contentType = contentType;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAppInfo() {
		return appInfo;
	}

	public void setAppInfo(String appInfo) {
		this.appInfo = appInfo;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

}
