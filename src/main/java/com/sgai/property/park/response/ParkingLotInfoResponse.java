package com.sgai.property.park.response;

/**
 * 查询车场信息
 * 
 * @author zhangyalei
 *
 */
public class ParkingLotInfoResponse {

	private int resCode; // 返回值，0为成功，其他为失败

	private String resMsg; // 返回说明

	private int totalNum; // 总车位

	private int totalStopNum; // 总已停车位

	private int totalRemainNum; // 总剩余车位

	private String parkID; // 车场ID

	private String parkName; // 车场名称

	private String chargeRuleDesc; // 车场收费标准

	public int getResCode() {
		return resCode;
	}

	public void setResCode(int resCode) {
		this.resCode = resCode;
	}

	public String getResMsg() {
		return resMsg;
	}

	public void setResMsg(String resMsg) {
		this.resMsg = resMsg;
	}

	public int getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}

	public int getTotalStopNum() {
		return totalStopNum;
	}

	public void setTotalStopNum(int totalStopNum) {
		this.totalStopNum = totalStopNum;
	}

	public int getTotalRemainNum() {
		return totalRemainNum;
	}

	public void setTotalRemainNum(int totalRemainNum) {
		this.totalRemainNum = totalRemainNum;
	}

	public String getParkID() {
		return parkID;
	}

	public void setParkID(String parkID) {
		this.parkID = parkID;
	}

	public String getParkName() {
		return parkName;
	}

	public void setParkName(String parkName) {
		this.parkName = parkName;
	}

	public String getChargeRuleDesc() {
		return chargeRuleDesc;
	}

	public void setChargeRuleDesc(String chargeRuleDesc) {
		this.chargeRuleDesc = chargeRuleDesc;
	}

}
