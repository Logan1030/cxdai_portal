package com.cxdai.portal.chart.mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.cxdai.base.entity.FinanceChart;
import com.cxdai.common.page.Page;
import com.cxdai.portal.chart.vo.BorrowDataInfoVo;
import com.cxdai.portal.chart.vo.RepaymentChartCnd;
import com.cxdai.portal.chart.vo.RepaymentChartVo;

public interface FinanceChartMapper {
	/**
	 * <p>
	 * Description:查询每日累计待收本息总计，统计前30天，从昨天算起,以万为单位<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2013年12月25日
	 * @return List<Double>
	 */
	public List<Double> queryDayTotalCollectionData() throws Exception;

	/**
	 * <p>
	 * Description:查询今日借款总计<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2013年12月25日
	 * @return BigDecimal
	 */
	public BigDecimal queryNowTotalBorrowMoneyData() throws Exception;

	/**
	 * <p>
	 * Description:查询今日投资者人数<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2013年12月25日
	 * @return Integer
	 */
	public Integer queryNowDayTotalPersonsData() throws Exception;

	/**
	 * <p>
	 * Description:查询今天累计待收本息总计，以万为单位<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年9月7日
	 * @return
	 * @throws Exception
	 *             Double
	 */
	public BigDecimal queryNowCollectionData() throws Exception;

	/**
	 * <p>
	 * Description:投资者总收益：只计算抵押标的收益总额；<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2013年12月25日
	 * @return result
	 */
	public BigDecimal queryInvesterNetMoney() throws Exception;

	/**
	 * <p>
	 * Description:累计借款者人数：在网站发布过抵押标的人数总和（多次发标计算一个借款者）；<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2013年12月25日
	 * @return result
	 */
	public Integer queryTotalBorrowPersons() throws Exception;

	/**
	 * <p>
	 * Description:结清借款者人数：在网站发布过抵押标并结清还款，没有待还的人数总和；<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2013年12月25日
	 * @return result
	 */
	public Integer querySettleBorrowPersons() throws Exception;

	/**
	 * <p>
	 * Description:查询成交的借款标<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2013年12月25日
	 * @return List<BorrowDataInfoVo>
	 */
	public List<BorrowDataInfoVo> querySuccessBorrowData() throws Exception;

	/**
	 * <p>
	 * Description:查询待收借款标<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2013年12月25日
	 * @return List<BorrowDataInfoVo>
	 */
	public List<BorrowDataInfoVo> queryWaitReceiveData() throws Exception;

	/**
	 * <p>
	 * Description:正常还款总额：只计算抵押标的还款情况；<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2013年12月25日
	 * @return result
	 */
	public BigDecimal queryNormalRepaymentMoney() throws Exception;

	/**
	 * <p>
	 * Description:逾期总额：有过逾期记录的抵押标总额；<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2013年12月25日
	 * @return result
	 */
	public BigDecimal queryOverdueMoney() throws Exception;

	/**
	 * <p>
	 * Description:逾期已处理总额 指逾期后，借款者还请本息和罚息的金额额<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2013年12月25日
	 * @return result
	 */
	public BigDecimal queryOverdueYesProcessedMoney() throws Exception;

	/**
	 * <p>
	 * Description:逾期未处理总额：指逾期后，网站垫付本息，但还没有进行资产处理的金额；<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2013年12月25日
	 * @return result
	 */
	public BigDecimal queryOverdueNoProcessMoney() throws Exception;

	/**
	 * 
	 * <p>
	 * Description:查询某段时间内的抵押标还款记录<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年6月16日
	 * @param repaymentChartCnd
	 * @param page
	 * @return
	 * @throws Exception
	 *             List<RepaymentChartVo>
	 */
	public List<RepaymentChartVo> findRepaymentChartForWeekPages(
			RepaymentChartCnd repaymentChartCnd, Page page) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:查询某段时间内的抵押标还款记录数量<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年6月16日
	 * @param repaymentChartCnd
	 * @return
	 * @throws Exception
	 *             Integer
	 */
	public Integer findRepaymentChartForWeekCount(
			RepaymentChartCnd repaymentChartCnd) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:统计某段时间内的抵押标未还总金额、已还总金额、应还总金额<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年6月16日
	 * @param repaymentChartCnd
	 * @return
	 * @throws Exception
	 *             Map<String,BigDecimal>
	 */
	public List<Map<String, Object>> queryRepaymentChartForWeek(
			RepaymentChartCnd repaymentChartCnd) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:查询逾期的抵押标还款记录<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年6月16日
	 * @param repaymentChartCnd
	 * @param page
	 * @return
	 * @throws Exception
	 *             List<RepaymentChartVo>
	 */
	public List<RepaymentChartVo> findRepaymentChartForOverduePages(
			RepaymentChartCnd repaymentChartCnd, Page page) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:查询逾期的抵押标还款记录数量<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年6月16日
	 * @param repaymentChartCnd
	 * @return
	 * @throws Exception
	 *             Integer
	 */
	public Integer findRepaymentChartForOverdueCount(
			RepaymentChartCnd repaymentChartCnd) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:查询结清的抵押标还款记录<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年6月16日
	 * @param repaymentChartCnd
	 * @param page
	 * @return
	 * @throws Exception
	 *             List<RepaymentChartVo>
	 */
	public List<RepaymentChartVo> findRepaymentChartForFinishPages(
			RepaymentChartCnd repaymentChartCnd, Page page) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:查询结清的抵押标还款记录数量<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年6月16日
	 * @param repaymentChartCnd
	 * @return
	 * @throws Exception
	 *             Integer
	 */
	public Integer findRepaymentChartForFinishCount(
			RepaymentChartCnd repaymentChartCnd) throws Exception;

	/**
	 * <p>
	 * Description:查询实时财务折线图数据<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年9月9日
	 * @return
	 * @throws Exception
	 *             List<FinanceChart>
	 */
	public List<FinanceChart> queryFinanceCharts() throws Exception;
}
