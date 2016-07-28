package com.cxdai.portal.fix.service;

import java.math.BigDecimal;
import java.util.List;

import com.cxdai.common.page.Page;
import com.cxdai.portal.fix.vo.FixTenderDetailCnd;
import com.cxdai.portal.fix.vo.FixTenderRealCnd;
import com.cxdai.portal.fix.vo.FixTenderRealVo;

/**
 * <p>
 * Description:定期宝认购明接口类<br />
 * </p>
 * 
 * @title FixTenderDetailService.java
 * @package com.cxdai.portal.fix1.service
 * @author caodefeng
 * @version 0.1 2015年5月14日
 */
public interface FixTenderRealService {
	
	/**
	 * 根据定期宝id分组查询用户总认购金额按第一次认购时间排序
	 * @param fixBorrowId
	 * @return
	 */
	public List<FixTenderRealVo> querySumAccountGroupByUser(Integer fixBorrowId)  throws Exception;
	
	
	/**
	 * 统计定期宝认购总次数
	 * @param id
	 * @return
	 */
	public Page queryTotalSYInfoByPage(FixTenderRealCnd fixTenderRealCnd, Page page) throws Exception;
	
	/**
	 * 统计定期宝认购总次数
	 * @param id
	 * @return
	 */
	public Page queryTotalTCInfoByPage(FixTenderRealCnd fixTenderRealCnd, Page page) throws Exception;


	public List<FixTenderRealVo> getFixTenderRealstaticByBorrowId(FixTenderRealCnd fixTenderRealCnd) throws Exception;
	
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 陈建国
	 * @version 0.1 2015年5月23日
	 * @param fixTenderRealCnd
	 * @return
	 * List<FixTenderRealVo>
	 */
	public List<FixTenderRealVo> getTcstaticByBorrowId(FixTenderRealCnd fixTenderRealCnd) throws Exception;
	
 
	/**
	 * 定期宝收益中预期收益统计
	 */
	public BigDecimal  getYqsyBySzy(FixTenderRealCnd fixTenderRealCnd) throws Exception;

}
