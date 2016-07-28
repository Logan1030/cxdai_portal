package com.cxdai.portal.curAccount.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cxdai.common.page.Page;
import com.cxdai.portal.curAccount.entity.CurAccount;
import com.cxdai.portal.curAccount.entity.CurAccountlog;
import com.cxdai.portal.curAccount.mapper.CurAccountlogMapper;
import com.cxdai.portal.curAccount.service.CurAccountLogService;
import com.cxdai.portal.curAccount.vo.CurAccountLogCnd;
import com.cxdai.portal.curAccount.vo.CurAccountLogVo;

/***
 * 
 * <p>
 * Description:活期宝账户操作日志记录 BLL <br />
 * </p>
 * 
 * @title CurAccountLogServiceImpl.java
 * @package com.cxdai.portal.curAccount.service.impl
 * @author HuangJun
 * @version 0.1 2015年4月27日
 */
@Service
public class CurAccountLogServiceImpl implements CurAccountLogService {

	Logger logger = LoggerFactory.getLogger(CurAccountLogServiceImpl.class);

	@Autowired
	private CurAccountlogMapper curAccountlogMapper;

	/**
	 * 资金信息 - 分页 (non-Javadoc)
	 * 
	 * @see com.cxdai.portal.curAccount.service.CurAccountLogService#queryCurAccountLogByPage(java.util.Map,
	 *      com.cxdai.common.page.Page)
	 */
	public Page queryCurAccountLogByPage(CurAccountLogCnd curAccountLogCnd, Page page) throws Exception {
		int curCount = 0;
		curCount = curAccountlogMapper.queryCurAccountLogCount(curAccountLogCnd);
		page.setTotalCount(curCount);
		List<CurAccountLogVo> retLst = curAccountlogMapper.queryCurAccountLogList(curAccountLogCnd, page);
		if (retLst != null && retLst.size() > 0) {
			page.setResult(retLst);
		}
		return page;
	}

	@Override
	public void saveCurAccountLogByParams(CurAccount curAccount, Integer userId, BigDecimal money, String remark, String addIp, Integer type,
			Integer adduser) throws Exception {
		CurAccountlog curAccountlog = new CurAccountlog();
		curAccountlog.setUserId(userId);
		curAccountlog.setType(type);
		curAccountlog.setMoney(money);
		curAccountlog.setTotal(curAccount.getTotal());
		curAccountlog.setUseMoney(curAccount.getUseMoney());
		curAccountlog.setNoUseMoney(curAccount.getNoUseMoney());
		curAccountlog.setInterestTotal(curAccount.getInterestTotal());
		curAccountlog.setInterestYesterday(curAccount.getInterestYesterday());
		curAccountlog.setAdduser(adduser);
		curAccountlog.setAddip(addIp);
		curAccountlog.setAddtime(new Date());
		curAccountlog.setRemark(remark);
		curAccountlogMapper.insert(curAccountlog);
	}

}
