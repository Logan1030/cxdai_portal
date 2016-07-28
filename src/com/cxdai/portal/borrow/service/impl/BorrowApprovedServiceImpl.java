package com.cxdai.portal.borrow.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cxdai.portal.borrow.mapper.BorrowApprovedMapper;
import com.cxdai.portal.borrow.service.BorrowApprovedService;
import com.cxdai.portal.borrow.vo.BorrowApprovedVo;

/**
 * <p>
 * Description:借款标审核业务实现类<br />
 * </p>
 * 
 * @title BorrowApprovedServiceImpl.java
 * @package com.cxdai.portal.borrow.service.impl
 * @author justin.xu
 * @version 0.1 2014年8月30日
 */
@Service
public class BorrowApprovedServiceImpl implements BorrowApprovedService {

	@Autowired
	private BorrowApprovedMapper borrowApprovedMapper;

	@Override
	public BorrowApprovedVo selectByBorrowIdForUpdate(Integer borrowId)
			throws Exception {
		return borrowApprovedMapper.selectByBorrowIdForUpdate(borrowId);
	}

}
