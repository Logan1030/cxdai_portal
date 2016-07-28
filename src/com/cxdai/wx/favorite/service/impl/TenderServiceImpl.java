package com.cxdai.wx.favorite.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cxdai.common.page.Page;
import com.cxdai.portal.borrow.vo.BorrowVo;
import com.cxdai.wx.favorite.mapper.TenderMapper;
import com.cxdai.wx.favorite.service.TenderService;
import com.cxdai.wx.favorite.vo.BidCountVo;

@Service
public class TenderServiceImpl implements TenderService {

	@Autowired
	private TenderMapper tenderMapper;

	public List<BorrowVo> bidList(Page p) throws Exception {
		List<BorrowVo> list = tenderMapper.bidList(p);
		list = BorrowVo.handleBorrow(list);
		return list;
	}

	public BidCountVo bidCount() throws Exception {
		return tenderMapper.bidCount();
	}

	public Integer tenderBidCount() throws Exception {
		return tenderMapper.tenderBidCount();
	}
}
