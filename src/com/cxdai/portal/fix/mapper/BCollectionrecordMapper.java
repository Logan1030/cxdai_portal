package com.cxdai.portal.fix.mapper;

import java.math.BigDecimal;

public interface BCollectionrecordMapper {
	/**
	 * 
	 * <p>
	 * Description:根据定期宝ID查询投标记录表中待收本金总和<br />
	 * </p>
	 * 
	 * @author 朱泳霖
	 * @version 0.1 2015年6月26日
	 * @param fixBorrowId
	 * @return Integer
	 */
	public BigDecimal queryCollectionrecordSum(Integer fixBorrowId);

}
