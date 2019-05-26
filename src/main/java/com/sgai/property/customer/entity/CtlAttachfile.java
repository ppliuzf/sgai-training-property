package com.sgai.property.customer.entity;

import com.sgai.common.persistence.BoEntity;

/**
 * 附件管理Entity
 * @author chenxing
 * @version 2017-12-31
 */
public class CtlAttachfile extends BoEntity<CtlAttachfile> {
	
	private static final long serialVersionUID = 1L;
	private String masterFileType; //文件所属功能模块名称
	private String keyDesc; //文件描述
	private String masterFileId; //主表id
	private String uploadSession; //用户token
	private String fileName; //文件名称
	private String fileLoc; //文件存储路径
	private String uploadTime; //文件上传时间
	private String contentType; //文件MIME类型
	private String fileSize; //文件大小,单位byte
	
	public String getMasterFileType() {
		return masterFileType;
	}
	public void setMasterFileType(String masterFileType) {
		this.masterFileType = masterFileType;
	}
	public String getKeyDesc() {
		return keyDesc;
	}
	public void setKeyDesc(String keyDesc) {
		this.keyDesc = keyDesc;
	}
	public String getMasterFileId() {
		return masterFileId;
	}
	public void setMasterFileId(String masterFileId) {
		this.masterFileId = masterFileId;
	}
	public String getUploadSession() {
		return uploadSession;
	}
	public void setUploadSession(String uploadSession) {
		this.uploadSession = uploadSession;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileLoc() {
		return fileLoc;
	}
	public void setFileLoc(String fileLoc) {
		this.fileLoc = fileLoc;
	}
	public String getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(String uploadTime) {
		this.uploadTime = uploadTime;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public String getFileSize() {
		return fileSize;
	}
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

}