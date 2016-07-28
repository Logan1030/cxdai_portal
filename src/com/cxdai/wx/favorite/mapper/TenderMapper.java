package com.cxdai.wx.favorite.mapper;

import java.util.List;

import com.cxdai.common.page.Page;
import com.cxdai.portal.borrow.vo.BorrowVo;
import com.cxdai.wx.favorite.vo.BidCountVo;

public interface TenderMapper {
	/**
	 * 投标专区-列表
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author huangpin
	 * @version 0.1 2014年11月3日
	 * @param p
	 * @return
	 * @throws Exception List<BorrowVo>
	 */
	public List<BorrowVo> bidList(Page p) throws Exception;

	/**
	 * 投标专区-统计
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author huangpin
	 * @version 0.1 2014年11月6日
	 * @return
	 * @throws Exception BidCountVo
	 */
	public BidCountVo bidCount() throws Exception;

	/**
	 * 投标专区-列表总数
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author huangpin
	 * @version 0.1 2014年11月19日
	 * @return
	 * @throws Exception Integer
	 */
	public Integer tenderBidCount() throws Exception;
}
