package com.cxdai.portal.borrow.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.cxdai.base.entity.BTenderRecord;
import com.cxdai.utils.DateUtils;
import com.cxdai.utils.StrinUtils;

/**
 * <p>
 * Description:投标记录Vo<br />
 * </p>
 * 
 * @title TenderRecordVo.java
 * @package com.cxdai.portal.borrow.vo
 * @author justin.xu
 * @version 0.1 2014年9月13日
 */
public class TenderRecordVo extends BTenderRecord implements Serializable {

	private static final long serialVersionUID = 7163900380887252193L;
	private String username;
	private String email;
	private Date addtimeDate;
	/** 直通车标题 */
	private String firstBorrowName;
	/** 直通车期 数 */
	private String firstPeriods;
	private String userNameSecret;  //隐藏用户名
	private String userNameEncrypt;  //加密用户名
	
	//定期宝转让
	private BigDecimal tenderCapital;
	private BigDecimal tenderInterest;
	private BigDecimal tenderRepayAccount;
	private String borrowContractNo;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstBorrowName() {
		return firstBorrowName;
	}

	public void setFirstBorrowName(String firstBorrowName) {
		this.firstBorrowName = firstBorrowName;
	}

	public Date getAddtimeDate() {
		if (null != super.getAddtime()) {
			return DateUtils.timeStampToDate(super.getAddtime());
		}
		return addtimeDate;
	}

	public void setAddtimeDate(Date addtimeDate) {
		this.addtimeDate = addtimeDate;
	}

	public String getFirstPeriods() {
		return firstPeriods;
	}

	public void setFirstPeriods(String firstPeriods) {
		this.firstPeriods = firstPeriods;
	}
	
	public String getUserNameSecret() {
		userNameSecret = this.getUsername();
		userNameSecret = StrinUtils.stringSecretTo(userNameSecret);
		return userNameSecret;
	}
	
	public String getUserNameEncrypt() {
		userNameEncrypt = this.getUsername();
		userNameEncrypt = StrinUtils.stringEncryptEn(userNameEncrypt);
		return userNameEncrypt;
	}

	public BigDecimal getTenderCapital() {
		return tenderCapital;
	}

	public void setTenderCapital(BigDecimal tenderCapital) {
		this.tenderCapital = tenderCapital;
	}

	public BigDecimal getTenderInterest() {
		return tenderInterest;
	}

	public void setTenderInterest(BigDecimal tenderInterest) {
		this.tenderInterest = tenderInterest;
	}

	public BigDecimal getTenderRepayAccount() {
		return tenderRepayAccount;
	}

	public void setTenderRepayAccount(BigDecimal tenderRepayAccount) {
		this.tenderRepayAccount = tenderRepayAccount;
	}

	public String getBorrowContractNo() {
		return borrowContractNo;
	}

	public void setBorrowContractNo(String borrowContractNo) {
		this.borrowContractNo = borrowContractNo;
	}
	

}