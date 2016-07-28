package com.cxdai.wx.favorite.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cxdai.common.page.Page;
import com.cxdai.portal.account.service.AccountService;
import com.cxdai.portal.account.vo.AccountVo;
import com.cxdai.portal.base.BaseController;
import com.cxdai.portal.borrow.service.BorrowDetailService;
import com.cxdai.portal.borrow.service.BorrowService;
import com.cxdai.portal.borrow.service.TendRecordService;
import com.cxdai.portal.borrow.vo.BorrowVo;
import com.cxdai.portal.curAccount.service.CurAccountService;
import com.cxdai.portal.invest.service.InvestRecordService;
import com.cxdai.portal.statics.BusinessConstants;
import com.cxdai.portal.transfer.service.TransferService;
import com.cxdai.wx.account.service.SafeCenterService;
import com.cxdai.wx.favorite.service.TenderService;

/**
 * 我的最爱-普通投标专区
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title TenderController.java
 * @package com.cxdai.wx.favorite.controller
 * @author huangpin
 * @version 0.1 2014年10月27日
 */
@Controller(value = "wxTenderController")
@RequestMapping(value = "/wx/tender")
public class TenderController extends BaseController {
	public Logger logger = Logger.getLogger(TenderController.class);

	@Autowired
	private InvestRecordService investRecordService;
	@Autowired
	private BorrowDetailService borrowDetailService;
	@Autowired
	private TendRecordService tenderRecordService;
	@Autowired
	private AccountService accountService;
	@Autowired
	private BorrowService borrowService;
	@Autowired
	private TenderService tenderService;
	@Autowired
	private SafeCenterService safeCenterService;
	@Autowired
	private CurAccountService curAccountService;
	@Autowired
	private TransferService transferService;

	/**
	 * 普通投标专区-列表
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author huangpin
	 * @version 0.1 2014年10月26日
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/bidList/{pageNum}")
	@ResponseBody
	public Map<String, Object> bidList(@PathVariable Integer pageNum) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<?> bidList = tenderService.bidList(new Page(pageNum, 10));
			if (bidList != null) {
				map.put("bidList", bidList);
				if (bidList.size() == BusinessConstants.DEFAULT_PAGE_SIZE)
					map.put("moreDiv", "<a id='clickA'><div id='contain'><div class='f2'><h4>查看更多</h4></div></div></a>");
			}
		} catch (Exception e) {
			logger.error("微信-普通投标专区-列表获取异常", e);
		}
		return map;
	}

	/**
	 * 普通投标专区-基本信息
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author huangpin
	 * @version 0.1 2014年10月27日
	 * @param borrowId
	 * @return Map<String,?>
	 */
	@RequestMapping(value = "/bid/{borrowId}")
	@ResponseBody
	public Map<String, Object> bid(@PathVariable Integer borrowId) {
		Map<String, Object> map = null;
		try {
			map = (Map<String, Object>) borrowDetailService.queryBorrowDetailInfo(borrowId);

			if (currentUser() != null && map != null) {
				int userId = currentUser().getUserId();
				map.put("currentUser", currentUser());
				map.put("useMoney", accountService.queryAccountByUserId(userId).getUseMoney());
				// 新手标是否可投标
				if (tenderRecordService.getTenderPowderCountByUserId(userId) > 0 || transferService.querySubscribesCountByUserId(userId) > 0) {
					map.put("isNewUser", "false");
				} else {
					map.put("isNewUser", "true");
				}
			}
			return map;
		} catch (Exception e) {
			logger.error("微信-普通投标专区-基本信息异常", e);
		}
		return new HashMap<String, Object>();
	}

	/**
	 * 普通投标专区-投标列表
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author huangpin
	 * @version 0.1 2014年10月27日
	 * @param borrowId
	 * @param pageNum
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/bid/tenderList/{borrowId}/{pageNum}")
	@ResponseBody
	public Map<String, Object> bidTenderList(@PathVariable Integer borrowId, @PathVariable Integer pageNum) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<?> tenderList = tenderRecordService.queryTenderRecordByBorrowId(borrowId, new Page(pageNum, BusinessConstants.DEFAULT_PAGE_SIZE)).getResult();
			if (tenderList != null) {
				map.put("tenderList", tenderList);
				if (tenderList.size() == BusinessConstants.DEFAULT_PAGE_SIZE)
					map.put("moreDiv", "<a id='clickA'><div id='contain'><div class='f2'><h4>查看更多</h4></div></div></a>");
			}
			map.put("currentUser", currentUser());
		} catch (Exception e) {
			logger.error("微信-普通投标专区-投标列表获取异常", e);
		}
		return map;
	}

	/**
	 * 普通投标专区-填写投标信息
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author huangpin
	 * @version 0.1 2014年10月28日
	 * @param borrowId
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/toTender/{borrowId}")
	@ResponseBody
	public Map<String, Object> toTender(@PathVariable Integer borrowId) {
		try {
			Map<String, Object> map = safeCenterService.certificationCheck(currentUser(), "mobile", "bank");
			if (map != null)
				return map;

			map = new HashMap<String, Object>();

			Integer userId = currentUser().getUserId();
			AccountVo accountVo = accountService.queryAccountByUserId(userId);
			map.put("useMoney", accountVo.getUseMoney());

			BorrowVo borrow = borrowService.selectByPrimaryKey(borrowId);
			map.put("borrow", borrow);
			BigDecimal mostAccount = borrow.getMostAccount() == null ? new BigDecimal(0) : borrow.getMostAccount();

			// 获取投标有效金额
			BigDecimal effectiveTenderMoney = tenderRecordService.getMaxeffectiveMoney(borrow, userId, mostAccount.compareTo(BigDecimal.ZERO) < 1 ? borrow.getAccount() : mostAccount, accountVo);
			map.put("effectiveTenderMoney", effectiveTenderMoney);

			map.put("alsoNeed", borrow.getAccount().subtract(borrow.getAccountYes()).setScale(2, BigDecimal.ROUND_DOWN));

			// 活期宝相关
			map.put("maxCurMoney", curAccountService.getMaxUseMoneyByNow(userId));
			map.put("isExistCurAccount", curAccountService.selectByUserId(userId) == null ? false : true);

			return map;
		} catch (Exception e) {
			logger.error("微信-普通投标专区-填写投标信息异常", e);
		}
		return new HashMap<String, Object>();
	}
}
