package com.sgai.property.wy.entity;

import java.util.Date;

/**  
    * @ClassName: RepairCategoryDto  
    * (报修类目实体)
    * @author Lenovo  
    * @date 2018年6月13日  
    * @Company 首自信--智慧城市创新中心  
    */

public class RepairCategoryDto {
	private Date startTime;//开始时间
	
	private Date endTime;//结束时间
	
	private String dynamicCategory;//动态类目
	
	private String type;//动态指标
	
	private String name;
	
	private String count;
	
	private float count1;
	
	private String dynamicTimeType;
	
	private String parentCode;//父类id
	
	private String type1;//用来区别要不要取前十
	private String type2;//用来区分是报修还是投诉
	
	private String parentName;
	
	public String getType2() {
		return type2;
	}

	public void setType2(String type2) {
		this.type2 = type2;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	private String typeId;//类型id
	
	public String getDynamicTimeType() {
		return dynamicTimeType;
	}

	public void setDynamicTimeType(String dynamicTimeType) {
		this.dynamicTimeType = dynamicTimeType;
	}

	public float getCount1() {
		return count1;
	}

	public void setCount1(float count1) {
		this.count1 = count1;
	}

	private String name1;

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getDynamicCategory() {
		return dynamicCategory;
	}

	public void setDynamicCategory(String dynamicCategory) {
		this.dynamicCategory = dynamicCategory;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getName1() {
		return name1;
	}

	public void setName1(String name1) {
		this.name1 = name1;
	}

	public String getType1() {
		return type1;
	}

	public void setType1(String type1) {
		this.type1 = type1;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

}
