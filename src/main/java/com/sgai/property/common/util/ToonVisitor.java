package com.sgai.property.common.util;

import java.io.Serializable;

public class ToonVisitor implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 5323363523921403029L;
    /**
     * 访问者的feed id
     */
    private String feed_id;
    /**
     * 访问者的用户id
     */
    private Long user_id;
	public String getFeed_id() {
		return feed_id;
	}
	public void setFeed_id(String feed_id) {
		this.feed_id = feed_id;
	}
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
}
