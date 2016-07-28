package com.cxdai.portal.account.service;

import java.math.BigDecimal;
import java.util.Map;

/**
 * <p>
 * Description:我的账户统计信息业务类<br />
 * </p>
 * 
 * @title MyAccountReportService.java
 * @package com.cxdai.portal.account.service
 * @author justin.xu
 * @version 0.1 2014年5月14日
 */
public interface MyAccountReportService {
	/**
	 * <p>
	 * Description:我的帐号详情信息<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年5月14日
	 * @param memberId
	 * @return
	 * @throws Exception
	 *             Map<String,BigDecimal>
	 */
	public Map<String, BigDecimal> queryUserAccountDetail(Integer memberId)
			throws Exception;

	/**
	 * <p>
	 * Description:我的投资详情信息<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年5月14日
	 * @param memberId
	 * @return
	 * @throws Exception
	 *             Map<String,Object>
	 */
	public Map<String, Object> queryUserInvestDetail(Integer memberId)
			throws Exception;

	/**
	 * <p>
	 * Description:我的借款详情信息<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年5月14日
	 * @param memberId
	 * @return
	 * @throws Exception
	 *             Map<String,BigDecimal>
	 */
	public Map<String, Object> queryUserBorrowDetail(Integer memberId)
			throws Exception;

}
