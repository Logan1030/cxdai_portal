package com.cxdai.portal.invest.service;

import java.util.List;
import java.util.Map;

import com.cxdai.common.page.Page;
import com.cxdai.portal.invest.vo.CollectionRepayInfoVo;
import com.cxdai.portal.invest.vo.CollectionStatisticCnd;
import com.cxdai.portal.invest.vo.CommonCollectionVo;

public interface CollectionRecordService {
	/**
	 * 
	 * <p>
	 * Description:普通待收、直通车待收查询<br />
	 * </p>
	 * 
	 * @author 胡建盼
	 * @version 0.1 2014年8月13日
	 * @param parameter
	 * @param pages
	 * @return Page
	 */
	Page queryMyCollections(Map<String, Object> parameter, Page pages);

	List<CommonCollectionVo> queryMyCollections(Boolean isStatistic, Map<String, Object> parameter);

	/**
	 * 
	 * <p>
	 * Description:直通车投标记录查询<br />
	 * </p>
	 * 
	 * @author 胡建盼
	 * @version 0.1 2014年8月13日
	 * @param parameter
	 * @param pages
	 * @return Page
	 */
	Page queryAccFirstCollections(Map<String, Object> parameter, Page pages);

	List<CommonCollectionVo> queryAccFirstCollections(Map<String, Object> parameter);

	// 计算逾期罚息
	public List<CommonCollectionVo> calculatedLateInterest(Page p, List<CommonCollectionVo> listReturn);

	/**
	 * <p>
	 * Description:更新尚未还款为已债权转让状态<br />
	 * </p>
	 * 
	 * @author 邹理享
	 * @version 0.1 2014年10月30日
	 * @param strings void
	 */
	/* void updateCollectionRecordByIds(String[] strings); */
	/**
	 * <p>
	 * Description:根据查询条件有效应收总额和人数信息<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年12月22日
	 * @param collectionStatisticCnd
	 * @return
	 * @throws Exception BigDecimal
	 */
	public CollectionRepayInfoVo queryRepayTotalByCnd(CollectionStatisticCnd collectionStatisticCnd) throws Exception;

	Boolean existsCollectionRecordByCnd(Map<String, Integer> dto);

}
