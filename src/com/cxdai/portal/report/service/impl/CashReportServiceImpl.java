package com.cxdai.portal.report.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cxdai.portal.account.mapper.CashRecordMapper;
import com.cxdai.portal.account.mapper.RechargeRecordMapper;
import com.cxdai.portal.account.vo.CashRecordCnd;
import com.cxdai.portal.account.vo.CashRecordVo;
import com.cxdai.portal.account.vo.RechargeRecordCnd;
import com.cxdai.portal.account.vo.RechargeRecordVo;
import com.cxdai.portal.report.service.CashReportService;

/**
 * <p>
 * Description:当前用户的提现记录导出excel Service<br />
 * </p>
 * 
 * @title CashReportService.java
 * @package com.cxdaiweb.report.service
 * @author justin.xu
 * @version 0.1 2014年2月19日
 */
@Service
public class CashReportServiceImpl implements CashReportService {

	@Autowired
	private RechargeRecordMapper rechargeRecordMapper;
	@Autowired
	private CashRecordMapper cashRecordMapper;

	@Override
	public List<RechargeRecordVo> queryRechargeRecordList(
			RechargeRecordCnd rechargeRecordCnd) throws Exception {
		return rechargeRecordMapper.queryRechargeRecordList(rechargeRecordCnd);
	}

	@Override
	public List<CashRecordVo> queryCashRecordList(CashRecordCnd cashRecordCnd)
			throws Exception {
		return cashRecordMapper.queryCashRecordList(cashRecordCnd);
	}

}
