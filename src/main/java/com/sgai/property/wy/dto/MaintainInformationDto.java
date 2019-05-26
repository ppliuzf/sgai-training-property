  
    /**    
    * @Title: MaintainInformationDto.java  
    * @Package com.sgai.property.wy.dto
    * (用一句话描述该文件做什么)
    * @author XJ9001  
    * @date 2018年1月29日  
    * @Company 首自信--智慧城市创新中心
    * @version V1.0    
    */  
    
package com.sgai.property.wy.dto;

    import com.fasterxml.jackson.annotation.JsonFormat;
    import com.sgai.common.persistence.BoEntity;
    import org.springframework.format.annotation.DateTimeFormat;

    import java.util.Date;


    /**  
 * @ClassName: MaintainInformationDto  
 * (这里用一句话描述这个类的作用)
 * @author XJ9001  
 * @date 2018年1月29日  
 * @Company 首自信--智慧城市创新中心  
 */

public class MaintainInformationDto extends BoEntity<MaintainInformationDto> {
	

	  
		    /**  
		    * @Fields field:field:(用一句话描述这个变量表示什么)
		    */  
		    
		private static final long serialVersionUID = 1L;

	private String aid; 
	
	private String bid;
	
	private String personName;
	
	private String phoneNumber;
	
	private String workload;
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date appointDate;
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private String workDates;
	
	private String telepno;
	
	

	public String getTelepno() {
		return telepno;
	}

	public void setTelepno(String telepno) {
		this.telepno = telepno;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getWorkload() {
		return workload;
	}

	public void setWorkload(String workload) {
		this.workload = workload;
	}

	public String getAid() {
		return aid;
	}

	public void setAid(String aid) {
		this.aid = aid;
	}

	public String getBid() {
		return bid;
	}

	public void setBid(String bid) {
		this.bid = bid;
	}

	public Date getAppointDate() {
		return appointDate;
	}

	public void setAppointDate(Date appointDate) {
		this.appointDate = appointDate;
	}

	public String getWorkDates() {
		return workDates;
	}

	public void setWorkDates(String workDates) {
		this.workDates = workDates;
	}

	
	

}
