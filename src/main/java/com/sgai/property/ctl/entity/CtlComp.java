package com.sgai.property.ctl.entity;

import org.hibernate.validator.constraints.Length;
import com.sgai.common.persistence.BoEntity;

import io.swagger.annotations.ApiModelProperty;

/**
 * 
    * ClassName: CtlComp  
    * Description: (机构实体类)
    * @author yangyz  
    * Date 2017年11月18日  
    * Company 首自信--智慧城市创新中心
 */
public class CtlComp extends BoEntity<CtlComp> {
	
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "机构代码")
	private String comCode;		// com_code 机构代码
	@ApiModelProperty(value = "机构名称")
	private String comName;		// com_name 机构名称
	@ApiModelProperty(value = "是否真实")
	private String dummyFlag;		// 1选项为:'Y':是'N':否默认为'N'2.表示该结点企业是否是真实的企业
	@ApiModelProperty(value = "可用标识")
	private String enabledFlag;		// 1.选项为:'Y':是'N':否默认为'Y'2.备用,先不做处理
	@ApiModelProperty(value = "机构简称")
	private String comNameAbbr;		// com_name_abbr 机构简称
	@ApiModelProperty(value = "机构地址")
	private String comAddr;		// com_addr 机构地址
	@ApiModelProperty(value = "邮政编码")
	private String zip;		// zip 邮政编码
	@ApiModelProperty(value = "电话号码")
	private String tel;		// tel 电话号码
	@ApiModelProperty(value = "传真")
	private String fax;		// fax 传真
	@ApiModelProperty(value = "电子邮件")
	private String email;		// email 电子邮件
	@ApiModelProperty(value = "企业网站")
	private String url;		// url 企业网站
	@ApiModelProperty(value = "开户银行")
	private String bank;		// bank 开户银行
	@ApiModelProperty(value = "开户账号")
	private String acct;		// acct 开户账号
	@ApiModelProperty(value = "法人代表")
	private String lp;		// lp 法人代表
	@ApiModelProperty(value = "机构负责")
	private String comResp;		// com_resp 机构负责人
	@ApiModelProperty(value = "备注")
	private String remark;		// remark 备注
	@ApiModelProperty(value = "版本号")
	private Integer version;		// 版本号

	private String busiCode;    //  子系统编码
	
	
	
	public CtlComp() {
		super();
	}

	public CtlComp(String id){
		super(id);
	}

	@Length(min=1, max=10, message="com_code长度必须介于 1 和 10 之间")
	public String getComCode() {
		return comCode;
	}

	public void setComCode(String comCode) {
		this.comCode = comCode;
	}
	
	@Length(min=0, max=60, message="com_name长度必须介于 0 和 60 之间")
	public String getComName() {
		return comName;
	}

	public void setComName(String comName) {
		this.comName = comName;
	}
	
	//@表示该结点企业是否是真实的企业长度必须介于 1 和 1 之间")
	public String getDummyFlag() {
		return dummyFlag;
	}

	public void setDummyFlag(String dummyFlag) {
		this.dummyFlag = dummyFlag;
	}
	
	//@备用,先不做处理长度必须介于 1 和 1 之间")
	public String getEnabledFlag() {
		return enabledFlag;
	}

	public void setEnabledFlag(String enabledFlag) {
		this.enabledFlag = enabledFlag;
	}
	
	@Length(min=0, max=20, message="com_name_abbr长度必须介于 0 和 20 之间")
	public String getComNameAbbr() {
		return comNameAbbr;
	}

	public void setComNameAbbr(String comNameAbbr) {
		this.comNameAbbr = comNameAbbr;
	}
	
	@Length(min=0, max=100, message="com_addr长度必须介于 0 和 100 之间")
	public String getComAddr() {
		return comAddr;
	}

	public void setComAddr(String comAddr) {
		this.comAddr = comAddr;
	}
	
	@Length(min=0, max=60, message="zip长度必须介于 0 和 60 之间")
	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}
	
	@Length(min=0, max=60, message="tel长度必须介于 0 和 60 之间")
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
	
	@Length(min=0, max=60, message="fax长度必须介于 0 和 60 之间")
	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}
	
	@Length(min=0, max=60, message="email长度必须介于 0 和 60 之间")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Length(min=0, max=60, message="url长度必须介于 0 和 60 之间")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	@Length(min=0, max=60, message="bank长度必须介于 0 和 60 之间")
	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}
	
	@Length(min=0, max=60, message="acct长度必须介于 0 和 60 之间")
	public String getAcct() {
		return acct;
	}

	public void setAcct(String acct) {
		this.acct = acct;
	}
	
	@Length(min=0, max=20, message="lp长度必须介于 0 和 20 之间")
	public String getLp() {
		return lp;
	}

	public void setLp(String lp) {
		this.lp = lp;
	}
	
	@Length(min=0, max=60, message="com_resp长度必须介于 0 和 60 之间")
	public String getComResp() {
		return comResp;
	}

	public void setComResp(String comResp) {
		this.comResp = comResp;
	}
	
	@Length(min=0, max=60, message="remark长度必须介于 0 和 60 之间")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getBusiCode() {
		return busiCode;
	}

	public void setBusiCode(String busiCode) {
		this.busiCode = busiCode;
	}
	
}