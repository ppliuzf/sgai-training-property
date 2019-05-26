  
    /**    
    * @Title: ClientManage.java  
    * @Package com.sgai.property.wy.entity
    * (用一句话描述该文件做什么)
    * @author XJ9001  
    * @date 2018年1月31日  
    * @Company 首自信--智慧城市创新中心
    * @version V1.0    
    */  
    
package com.sgai.property.wy.entity;

    import com.sgai.common.persistence.BoEntity;

    import java.util.Date;


    /**  
 * @ClassName: ClientManage  
 * (这里用一句话描述这个类的作用)
 * @author XJ9001  
 * @date 2018年1月31日  
 * @Company 首自信--智慧城市创新中心  
 */

public class ClientManage extends BoEntity<ClientManage> {

		  
		    /**  
		    * @Fields field:field:(用一句话描述这个变量表示什么)
		    */  
		    
		private static final long serialVersionUID = 1L;
		
	    private String createTime; //创建时间
	    
	    private String clientPhoneSecond; //手机号2
	    
	    private String clientSex; //性别(0 男 1 女)

	    private Date clientBirth; //出生日期

	    private String clientTypeId; //类型id
	    
	    private String clicntTypeName; //类型name
	    
	    private String updateUserName; //更新人名称
	    
	    private String clientPhoneFirst; //手机号1
	    
	    private String comId; //公司ID

	    private String comName; //公司名
	    
	    private String clientName; //客户名称

	    private Date updateTime; //更新时间
	    
	    private Long updateUserId; //更新人id

	    private String clientEmail; //邮箱

	    private String clientRankId; //级别id

	    private String clientRankName; //级别名称

		public String getCreateTime() {
			return createTime;
		}

		public void setCreateTime(String createTime) {
			this.createTime = createTime;
		}

		public String getClientPhoneSecond() {
			return clientPhoneSecond;
		}

		public void setClientPhoneSecond(String clientPhoneSecond) {
			this.clientPhoneSecond = clientPhoneSecond;
		}

		public String getClientSex() {
			return clientSex;
		}

		public void setClientSex(String clientSex) {
			this.clientSex = clientSex;
		}

		public Date getClientBirth() {
			return clientBirth;
		}

		public void setClientBirth(Date clientBirth) {
			this.clientBirth = clientBirth;
		}

		public String getClientTypeId() {
			return clientTypeId;
		}

		public void setClientTypeId(String clientTypeId) {
			this.clientTypeId = clientTypeId;
		}

		public String getClicntTypeName() {
			return clicntTypeName;
		}

		public void setClicntTypeName(String clicntTypeName) {
			this.clicntTypeName = clicntTypeName;
		}

		public String getUpdateUserName() {
			return updateUserName;
		}

		public void setUpdateUserName(String updateUserName) {
			this.updateUserName = updateUserName;
		}

		public String getClientPhoneFirst() {
			return clientPhoneFirst;
		}

		public void setClientPhoneFirst(String clientPhoneFirst) {
			this.clientPhoneFirst = clientPhoneFirst;
		}

		public String getComId() {
			return comId;
		}

		public void setComId(String comId) {
			this.comId = comId;
		}

		public String getComName() {
			return comName;
		}

		public void setComName(String comName) {
			this.comName = comName;
		}

		public String getClientName() {
			return clientName;
		}

		public void setClientName(String clientName) {
			this.clientName = clientName;
		}

		public Date getUpdateTime() {
			return updateTime;
		}

		public void setUpdateTime(Date updateTime) {
			this.updateTime = updateTime;
		}

		public Long getUpdateUserId() {
			return updateUserId;
		}

		public void setUpdateUserId(Long updateUserId) {
			this.updateUserId = updateUserId;
		}

		public String getClientEmail() {
			return clientEmail;
		}

		public void setClientEmail(String clientEmail) {
			this.clientEmail = clientEmail;
		}

		public String getClientRankId() {
			return clientRankId;
		}

		public void setClientRankId(String clientRankId) {
			this.clientRankId = clientRankId;
		}

		public String getClientRankName() {
			return clientRankName;
		}

		public void setClientRankName(String clientRankName) {
			this.clientRankName = clientRankName;
		}
	    
	    

}
