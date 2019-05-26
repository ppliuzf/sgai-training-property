package com.sgai.property.commonService.vo.toon;

public class ToonAuthInfoReq {
	
	private String authAppId;
	
	private String authAppType;
	
	private String authAppSecret;
	
	private String authSignType;

	public String getAuthAppId() {
		return authAppId;
	}

	public void setAuthAppId(String authAppId) {
		this.authAppId = authAppId;
	}

	public String getAuthAppType() {
		return authAppType;
	}

	public void setAuthAppType(String authAppType) {
		this.authAppType = authAppType;
	}

	public String getAuthAppSecret() {
		return authAppSecret;
	}

	public void setAuthAppSecret(String authAppSecret) {
		this.authAppSecret = authAppSecret;
	}

	public String getAuthSignType() {
		return authSignType;
	}

	public void setAuthSignType(String authSignType) {
		this.authSignType = authSignType;
	}

}
