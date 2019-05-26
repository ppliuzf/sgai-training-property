
    /**
    * @Title: KeyBorrow.java
    * @Package com.sgai.modules.key.entity
    * (用一句话描述该文件做什么)
    * @author XJ9001
    * @date 2018年1月18日
    * @Company 首自信--智慧城市创新中心
    * @version V1.0
    */

package com.sgai.property.wy.entity;

    import com.fasterxml.jackson.annotation.JsonFormat;
    import com.sgai.common.persistence.BoEntity;
	import com.sgai.modules.login.support.UserServletContext;
	import org.springframework.format.annotation.DateTimeFormat;

    import java.util.Date;


    /**
    * @ClassName: KeyBorrow
    * (这里用一句话描述这个类的作用)
    * @author XJ9001
    * @date 2018年1月23日
    * @Company 首自信--智慧城市创新中心
    */

public class KeyBorrow extends BoEntity<KeyBorrow> {

private static final long serialVersionUID = 1L;

	private String borrowManager;  //借用经办人

	private Date borrowDate;  // borrow_date 借出日期

	private String deptCodes;  // dept_codes 部门代码1

	private String deptName;  // dept_name 部门名称

	private String borrower;  // borrower 借用人

	private String amount;    // amount 数量
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date borrowTime; // borrow_time 借出时间
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date regainTime; // regain_time 收回时间

	private String watch;  // 归还人

	private String remark; //备注

	private String phone; //联系电话

	private String manager; //归还经办人

	private String goodsClassify; //物品分类

	private String position; //部位

	private String purpose; //用途

	private String regainDept; //归还人部门

	private String regainAmount; //归还数量

	private String PositionCode; //部位Code

	private Date borrowTimeFrom; //借用时间从

	private Date borrowTimeTo;  //借用时间到

	private String comCode; // 机构代码

	private String deptCode; // 部门代码

	public String getComCode() {
		return comCode;
	}
	private String loginUserCode = UserServletContext.getUserInfo().getUserId();

	public void setComCode(String comCode) {
		this.comCode = comCode;
	}

	public String getDeptCodes() {
		return deptCodes;
	}

	public void setDeptCodes(String deptCodes) {
		this.deptCodes = deptCodes;
	}

	public Date getBorrowDate() {
		return borrowDate;
	}

	public void setBorrowDate(Date borrowDate) {
		this.borrowDate = borrowDate;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getBorrower() {
		return borrower;
	}

	public void setBorrower(String borrower) {
		this.borrower = borrower;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getWatch() {
		return watch;
	}

	public void setWatch(String watch) {
		this.watch = watch;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getGoodsClassify() {
		return goodsClassify;
	}

	public void setGoodsClassify(String goodsClassify) {
		this.goodsClassify = goodsClassify;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getBorrowManager() {
		return borrowManager;
	}

	public void setBorrowManager(String borrowManager) {
		this.borrowManager = borrowManager;
	}
	public Date getBorrowTime() {
		return borrowTime;
	}

	public void setBorrowTime(Date borrowTime) {
		this.borrowTime = borrowTime;
	}
	public Date getRegainTime() {
		return regainTime;
	}

	public void setRegainTime(Date regainTime) {
		this.regainTime = regainTime;
	}

	public String getRegainDept() {
		return regainDept;
	}

	public void setRegainDept(String regainDept) {
		this.regainDept = regainDept;
	}

	public String getRegainAmount() {
		return regainAmount;
	}

	public void setRegainAmount(String regainAmount) {
		this.regainAmount = regainAmount;
	}

	public String getPositionCode() {
		return PositionCode;
	}

	public void setPositionCode(String positionCode) {
		PositionCode = positionCode;
	}

	public Date getBorrowTimeFrom() {
		return borrowTimeFrom;
	}

	public void setBorrowTimeFrom(Date borrowTimeFrom) {
		this.borrowTimeFrom = borrowTimeFrom;
	}

	public Date getBorrowTimeTo() {
		return borrowTimeTo;
	}

	public void setBorrowTimeTo(Date borrowTimeTo) {
		this.borrowTimeTo = borrowTimeTo;
	}

		public String getLoginUserCode() {
			return loginUserCode;
		}

		public void setLoginUserCode(String loginUserCode) {
			this.loginUserCode = loginUserCode;
		}
	}
