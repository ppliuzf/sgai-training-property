  
    /**    
    * @Title: CarManage.java  
    * @Package com.sgai.property.wy.entity
    * (用一句话描述该文件做什么)
    * @author XJ9001  
    * @date 2018年2月1日  
    * @Company 首自信--智慧城市创新中心
    * @version V1.0    
    */  
    
package com.sgai.property.wy.entity;

import com.sgai.common.persistence.BoEntity;


    /**  
 * @ClassName: CarManage  
 * (这里用一句话描述这个类的作用)
 * @author XJ9001  
 * @date 2018年2月1日  
 * @Company 首自信--智慧城市创新中心  
 */

public class CarManage extends BoEntity<CarManage> {

		  
		    /**  
		    * @Fields field:field:(用一句话描述这个变量表示什么)
		    */  
		    
		private static final long serialVersionUID = 1L;
		
		private String memberId; //客户ID
		
		private String memberName;  //客户名
		
		private String memberIdCard;  //客户证件号
		
		private String carNumber;  //车牌号

		private String carColor;  //车辆颜色
		
		private String carModel;  //车辆型号 
		
		private String preservePersonId;  //维护人ID
		
		private String preservePersonName;  //维护人名
		
		private String remark;  //备注
		
		private String loadNumber;  //核载人数
		
		private String carType;  //车辆类型

		public String getMemberId() {
			return memberId;
		}

		public void setMemberId(String memberId) {
			this.memberId = memberId;
		}

		public String getMemberName() {
			return memberName;
		}

		public void setMemberName(String memberName) {
			this.memberName = memberName;
		}

		public String getCarNumber() {
			return carNumber;
		}

		public void setCarNumber(String carNumber) {
			this.carNumber = carNumber;
		}

		public String getCarColor() {
			return carColor;
		}

		public void setCarColor(String carColor) {
			this.carColor = carColor;
		}

		public String getCarModel() {
			return carModel;
		}

		public void setCarModel(String carModel) {
			this.carModel = carModel;
		}

		public String getPreservePersonId() {
			return preservePersonId;
		}

		public void setPreservePersonId(String preservePersonId) {
			this.preservePersonId = preservePersonId;
		}

		public String getPreservePersonName() {
			return preservePersonName;
		}

		public void setPreservePersonName(String preservePersonName) {
			this.preservePersonName = preservePersonName;
		}

		public String getRemark() {
			return remark;
		}

		public void setRemark(String remark) {
			this.remark = remark;
		}

		public String getMemberIdCard() {
			return memberIdCard;
		}

		public void setMemberIdCard(String memberIdCard) {
			this.memberIdCard = memberIdCard;
		}

		public String getLoadNumber() {
			return loadNumber;
		}

		public void setLoadNumber(String loadNumber) {
			this.loadNumber = loadNumber;
		}

		public String getCarType() {
			return carType;
		}

		public void setCarType(String carType) {
			this.carType = carType;
		}
		
		
}
