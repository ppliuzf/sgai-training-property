  
    /**    
    * @Title: NewsManage.java  
    * @Package com.sgai.property.wy.entity
    * (用一句话描述该文件做什么)
    * @author XJ9001  
    * @date 2018年2月1日  
    * @Company 首自信--智慧城市创新中心
    * @version V1.0    
    */  
    
package com.sgai.property.wy.entity;

    import com.fasterxml.jackson.annotation.JsonFormat;
    import com.sgai.common.persistence.BoEntity;
    import org.springframework.format.annotation.DateTimeFormat;

    import java.util.Date;


    /**  
 * @ClassName: NewsManage  
 * (这里用一句话描述这个类的作用)
 * @author XJ9001  
 * @date 2018年2月1日  
 * @Company 首自信--智慧城市创新中心  
 */

public class NewsManage extends BoEntity<NewsManage> {

		  
		    /**  
		    * @Fields field:field:(用一句话描述这个变量表示什么)
		    */  
		    
		private static final long serialVersionUID = 1L;
		
		private String newsTitle;  //消息标题
		
		private String newsAbstract;  //消息摘要
		
		@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
		@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
		private Date publishDate;  //发布时间
		
		private String newsType;  //消息类型
		
		private String newsStatus;  //消息状态
		
		private String newsContent;  //消息内容
		
	/*	private String newsSubmitterId; //提交人ID
		
		private String newsSubmitterName; //提交人name
*/		
		private String newsPublishId;  //发布人ID
		
		private String newsPublishName; //发布人name
		
		private String newsCheckYesOrNo; //是否审核 0：是 1：否
		
		private String checkerName; //审核人姓名
		
		private String checkerId;  //审核人ID
		
		@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
		@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
		private Date checkDate;  //审核时间
		
		private String cause;  //原因
		
		
		private String checkStatus;//审核状态
		
		private String emergencyStatus;//紧急程度 0：一般 1：紧急
		
		private String range;//范围
		
		private String rangeUserId;//范围用户Id
		
		private String receptName;//发布给的接收人姓名
		
		private String receptId;//发布给的接收人id
		
		private String cover;//封面
		
		private String type;
		
		private String historyId;
		

		public String getHistoryId() {
			return historyId;
		}

		public void setHistoryId(String historyId) {
			this.historyId = historyId;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getCover() {
			return cover;
		}

		public void setCover(String cover) {
			this.cover = cover;
		}

		public String getReceptName() {
			return receptName;
		}

		public void setReceptName(String receptName) {
			this.receptName = receptName;
		}

		public String getReceptId() {
			return receptId;
		}

		public void setReceptId(String receptId) {
			this.receptId = receptId;
		}

		public String getRange() {
			return range;
		}

		public void setRange(String range) {
			this.range = range;
		}

		public String getRangeUserId() {
			return rangeUserId;
		}

		public void setRangeUserId(String rangeUserId) {
			this.rangeUserId = rangeUserId;
		}

		public String getEmergencyStatus() {
			return emergencyStatus;
		}

		public void setEmergencyStatus(String emergencyStatus) {
			this.emergencyStatus = emergencyStatus;
		}

		public String getCheckStatus() {
			return checkStatus;
		}

		public void setCheckStatus(String checkStatus) {
			this.checkStatus = checkStatus;
		}

		public String getNewsTitle() {
			return newsTitle;
		}

		public void setNewsTitle(String newsTitle) {
			this.newsTitle = newsTitle;
		}

		public String getNewsAbstract() {
			return newsAbstract;
		}

		public void setNewsAbstract(String newsAbstract) {
			this.newsAbstract = newsAbstract;
		}

		public Date getPublishDate() {
			return publishDate;
		}

		public void setPublishDate(Date publishDate) {
			this.publishDate = publishDate;
		}

		public String getNewsType() {
			return newsType;
		}

		public void setNewsType(String newsType) {
			this.newsType = newsType;
		}

		public String getNewsStatus() {
			return newsStatus;
		}

		public void setNewsStatus(String newsStatus) {
			this.newsStatus = newsStatus;
		}

		public String getNewsContent() {
			return newsContent;
		}

		public void setNewsContent(String newsContent) {
			this.newsContent = newsContent;
		}

		public String getNewsPublishId() {
			return newsPublishId;
		}

		public void setNewsPublishId(String newsPublishId) {
			this.newsPublishId = newsPublishId;
		}

		public String getNewsPublishName() {
			return newsPublishName;
		}

		public void setNewsPublishName(String newsPublishName) {
			this.newsPublishName = newsPublishName;
		}

		public String getNewsCheckYesOrNo() {
			return newsCheckYesOrNo;
		}

		public void setNewsCheckYesOrNo(String newsCheckYesOrNo) {
			this.newsCheckYesOrNo = newsCheckYesOrNo;
		}

		public String getCheckerName() {
			return checkerName;
		}

		public void setCheckerName(String checkerName) {
			this.checkerName = checkerName;
		}

		public String getCheckerId() {
			return checkerId;
		}

		public void setCheckerId(String checkerId) {
			this.checkerId = checkerId;
		}

		public String getCause() {
			return cause;
		}

		public void setCause(String cause) {
			this.cause = cause;
		}

		public Date getCheckDate() {
			return checkDate;
		}

		public void setCheckDate(Date checkDate) {
			this.checkDate = checkDate;
		}

		
		
		
		
		
		
		
		

}
