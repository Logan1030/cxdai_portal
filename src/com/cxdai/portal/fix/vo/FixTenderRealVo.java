package com.cxdai.portal.fix.vo;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.cxdai.base.entity.FixTenderReal;
import com.cxdai.portal.statics.BusinessConstants;
import com.cxdai.utils.DateUtils;

/**
 * <p>
 * Description:定期宝认购明细类<br />
 * </p>
 * @title FixTenderDetailVo.java
 * @package com.cxdai.portal.fix1.vo
 * @author caodefeng
 * @version 0.1 2015年5月14日
 */
public class FixTenderRealVo extends FixTenderReal {

	/**
	 * 用户认购总额
	 */
	private BigDecimal sumAccount;

	/**
	 * 投标次数
	 */
	private Integer cnt;

	/**
	 * 用户名字
	 */
	private String userName;

	/**
	 * 定期宝名称
	 */
	private String name;

	/**
	 * 定期宝利率
	 */

	private BigDecimal apr;

	/**
	 * 锁定期限
	 */

	private Date lockEndTime;
	/**
	 * 投宝方式
	 */
	private String tenderType;//认购方式【0：手动投宝，1：自动投宝】，设默认值为0
	/**
	 * 红包金额
	 */
	private BigDecimal redMoney;
	private BigDecimal hasReturnMoney;
	private Integer lockLimit;

	private String contractNo;
	
	/**
	 * 专区类型【0：普通专区，1：新手专区】
	 */
	private Integer areaType;
	/**
	 * 新手专区转普通专区时间
	 */
	private Date areaChangeTime;	
	
	public Integer getAreaType() {
		return areaType;
	}


	public void setAreaType(Integer areaType) {
		this.areaType = areaType;
	}


	public Date getAreaChangeTime() {
		return areaChangeTime;
	}


	public void setAreaChangeTime(Date areaChangeTime) {
		this.areaChangeTime = areaChangeTime;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getHasReturnMoneyStr() {
		if (hasReturnMoney != null)
			return BusinessConstants.decimalPercentDf.format(hasReturnMoney);
		return "";

	}

	public Date getLockEndTime() {
		return lockEndTime;
	}

	public void setLockEndTime(Date lockEndTime) {
		this.lockEndTime = lockEndTime;
	}

	public String getLockEndTimeStr() {

		String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(lockEndTime);
		return date;
	}

	public int getRemainDay() {
		try {
			return DateUtils.dayDiffByDay(lockEndTime, new Date());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public BigDecimal getHasReturnMoney() {
		return hasReturnMoney;
	}

	public void setHasReturnMoney(BigDecimal hasReturnMoney) {
		this.hasReturnMoney = hasReturnMoney;
	}

	public String getYqsyStr() {

		return BusinessConstants.decimalPercentDf.format(this.getAccount().multiply(this.getApr()).divide(new BigDecimal(1200), 3, BigDecimal.ROUND_HALF_UP)
				.multiply(new BigDecimal(this.getLockLimit())));
	}

	public String getAprStr() {
		return apr + "%";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getApr() {
		return apr;
	}

	public void setApr(BigDecimal apr) {
		this.apr = apr;
	}

	public Integer getLockLimit() {
		return lockLimit;
	}

	public void setLockLimit(Integer lockLimit) {
		this.lockLimit = lockLimit;
	}

	public BigDecimal getSumAccount() {
		return sumAccount;
	}

	public void setSumAccount(BigDecimal sumAccount) {
		this.sumAccount = sumAccount;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getCnt() {
		return cnt;
	}

	public void setCnt(Integer cnt) {
		this.cnt = cnt;
	}

	// 加入金额
	public String getAccountStr() {
		return BusinessConstants.decimalPercentDf.format(this.getAccount());

	}
	public String getTenderType() {
		return tenderType;
	}
	public void setTenderType(String tenderType) {
		this.tenderType = tenderType;
	}


	public BigDecimal getRedMoney() {
		return redMoney;
	}


	public void setRedMoney(BigDecimal redMoney) {
		this.redMoney = redMoney;
	}
}
