package com.sgai.property.ruag.dto;

public class RuagModelCalendarVo {
		//运行策略id
		private String id;
		//策略日程id
		private String calendarId;
	    //运行策略名称
		private String title;
		//开启日期
		private String start;
		//结束日期
		private String end;
		//策略日程状态
		private String status;
		
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getStart() {
			return start;
		}
		public void setStart(String start) {
			this.start = start;
		}
		public String getEnd() {
			return end;
		}
		public void setEnd(String end) {
			this.end = end;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getCalendarId() {
			return calendarId;
		}
		public void setCalendarId(String calendarId) {
			this.calendarId = calendarId;
		}
		
		
}
