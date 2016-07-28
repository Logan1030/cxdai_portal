package com.cxdai.portal.account.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cxdai.common.page.Page;
import com.cxdai.portal.account.mapper.AccountOperateRecordMapper;
import com.cxdai.portal.account.service.AccountOperateRecordService;
import com.cxdai.portal.account.vo.AccountOperateVO;
import com.cxdai.utils.DateUtils;

@Service
public class AccountOperateRecordServiceImpl implements AccountOperateRecordService{

	@Autowired
	private AccountOperateRecordMapper accountOperateRecordMapper;
	
	@Override
	public Page searchPageUserAccountList(String username, String startTime,
			String endTime, String type, Integer curPage, Integer pageSize)
			{
		//时间参数格式转换
		if(startTime != null && !"".equals(startTime)){
			startTime = com.cxdai.utils.DateUtils.date2TimeStamp(startTime);
		}
		if(endTime != null && !"".equals(endTime)){
			endTime = com.cxdai.utils.DateUtils.dayOffset(DateUtils.parse(endTime, DateUtils.YMD_DASH),1).getTime()+"";
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("startTime", startTime);
		params.put("endTime", endTime);
		params.put("type", type);
		params.put("username", username);
		Page page = new Page(curPage, pageSize);
		int totalCount = accountOperateRecordMapper
				.countAccOpertRecord(params);
		page.setTotalCount(totalCount);
		List<AccountOperateVO> list = accountOperateRecordMapper.queryAccOpertRecordList(
				params, page);
		page.setResult(list);
		return page;
	}

	@Override
	public Integer countUserAccount(String username, String startTime,
			String endTime, String type) {
		return null;
	}

	@Override
	public List<AccountOperateVO> searchOneMonthUserAccountList(String username,
			String startTime, String endTime, String type) {
		
		if(startTime != null && !"".equals(startTime)){
			startTime = com.cxdai.utils.DateUtils.date2TimeStamp(startTime);
		}
		if(endTime != null && !"".equals(endTime)){
			endTime = com.cxdai.utils.DateUtils.dayOffset(DateUtils.parse(endTime, DateUtils.YMD_DASH),1).getTime()+"";
		}
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("startTime", startTime);
		params.put("endTime", endTime);
		params.put("type", type);
		params.put("username", username);
		//
		List<AccountOperateVO> list = accountOperateRecordMapper.queryOneMonthAccOpertRecordList(
				params);
		return list;
	}

	@Override
	public AccountOperateVO searchPaymentDetail(Integer accountId) {
		return accountOperateRecordMapper.searchPaymentDetail(accountId);
	}

}
