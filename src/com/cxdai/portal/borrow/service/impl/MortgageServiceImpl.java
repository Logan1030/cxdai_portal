package com.cxdai.portal.borrow.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cxdai.portal.borrow.mapper.MortgageMapper;
import com.cxdai.portal.borrow.service.MortgageService;
import com.cxdai.portal.borrow.vo.MortgageVo;

@Service
public class MortgageServiceImpl implements MortgageService {

	@Autowired
	private MortgageMapper mortgageMapper;

	@Override
	public MortgageVo getMortgageByBorrowId(Integer borrowId) throws Exception {
		return mortgageMapper.getMortgageByBorrowId(borrowId);
	}

}
