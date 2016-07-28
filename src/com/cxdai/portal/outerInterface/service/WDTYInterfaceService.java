package com.cxdai.portal.outerInterface.service;

/**
 * 
 * <p>
 * Description:网贷天眼调用接口<br />
 * </p>
 * 
 * @title WDTYInterfaceService.java
 * @package com.cxdai.portal.outerInterface.service
 * @author yangshijin
 * @version 0.1 2014年8月21日
 */
public interface WDTYInterfaceService {
	/**
	 * 
	 * <p>
	 * Description:每日成交量数据(仅统计净值标和抵押标)（网贷天眼调用接口）<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年8月19日
	 * @param ip
	 * @param url
	 * @return
	 * @throws Exception
	 *             String
	 */
	public String transactionInfo(String ip, String url, String date)
			throws Exception;

	/**
	 * 
	 * <p>
	 * Description:平台贷款数据(仅统计净值标和抵押标)（网贷天眼调用接口）<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年8月19日
	 * @param ip
	 * @param url
	 * @return
	 * @throws Exception
	 *             String
	 */
	public String loanInfo(String ip, String url, String date) throws Exception;

}
