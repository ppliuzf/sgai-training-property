package com.sgai.property.customer.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 当前时间状态信息
 *
 * @author 146584
 * @create 2017-11-02 15:43
 */
public class TimeSegStatusDto implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 388555360764119366L;
	 
    @ApiModelProperty(value = "预定时间段")
    private String bookTimes; //预定时间段
	 
	public String getBookTimes() {
		return bookTimes;
	}
	public void setBookTimes(String bookTimes) {
		this.bookTimes = bookTimes;
	}
    
	
}
