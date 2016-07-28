package com.cxdai.portal.account.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cxdai.base.entity.CashRecordlog;
import com.cxdai.portal.account.mapper.CashRecordlogMapper;
import com.cxdai.portal.account.service.CashRecordlogService;

/**
 * <p>
 * Description:提现操作日志业务实现类<br />
 * </p>
 * 
 * @title CashRecordlogServiceImpl.java
 * @package com.cxdai.portal.account.service.impl
 * @author justin.xu
 * @version 0.1 2014年11月10日
 */
@Service
public class CashRecordlogServiceImpl implements CashRecordlogService {

	@Autowired
	private CashRecordlogMapper cashRecordlogMapper;

	@Override
	public Integer saveCashRecordlog(CashRecordlog cashRecordlog) throws Exception {
		return cashRecordlogMapper.insertCashRecordlog(cashRecordlog);
	}

}
