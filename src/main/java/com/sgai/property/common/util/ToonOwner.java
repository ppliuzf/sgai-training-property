package com.sgai.property.common.util;

import java.io.Serializable;

public class ToonOwner implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 5323363523921403029L;
    /**
     * 应用布署在名片的feed id.
     */
    private String feed_id;
    /**
     * 应用布署在名片的公司的id
     */
    private Long company_id;
    
	public String getFeed_id() {
		return feed_id;
	}
	public void setFeed_id(String feed_id) {
		this.feed_id = feed_id;
	}
	public Long getCompany_id() {
		return company_id;
	}
	public void setCompany_id(Long company_id) {
		this.company_id = company_id;
	}
    
}
