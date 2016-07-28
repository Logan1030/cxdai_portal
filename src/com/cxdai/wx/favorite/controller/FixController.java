package com.cxdai.wx.favorite.controller;

import java.math.BigDecimal;
import java.util.Date;
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
import com.cxdai.portal.base.BaseController;
import com.cxdai.portal.curAccount.service.CurAccountService;
import com.cxdai.portal.fix.service.FixBorrowService;
import com.cxdai.portal.fix.service.FixTenderDetailService;
import com.cxdai.portal.fix.service.FixTenderRealService;
import com.cxdai.portal.fix.vo.FixBorrowCnd;
import com.cxdai.portal.fix.vo.FixBorrowVo;
import com.cxdai.portal.fix.vo.FixTenderDetailCnd;
import com.cxdai.portal.fix.vo.FixTenderRealCnd;
import com.cxdai.portal.member.service.MemberService;
import com.cxdai.portal.member.vo.MemberCnd;
import com.cxdai.portal.member.vo.MemberVo;
import com.cxdai.portal.statics.BusinessConstants;
import com.cxdai.utils.DateUtils;

/**
 * 微信-定期宝
 * <p>
 * Description:这里写描述<br />
 * </p>
 * 
 * @title FixController.java
 * @package com.cxdai.wx.favorite.controller
 * @author huangpin
 * @version 0.1 2015年7月31日
 */
@Controller
@RequestMapping(value = "/wx/fix")
public class FixController extends BaseController {
	public Logger logger = Logger.getLogger(FixController.class);

	@Autowired
	private AccountService accountService;
	@Autowired
	private FixBorrowService fixBorrowService;
	@Autowired
	private FixTenderDetailService fixTenderDetailService;
	@Autowired
	private FixTenderRealService fixTenderRealService;
	@Autowired
	private CurAccountService curAccountService;
	@Autowired
	private MemberService memberService;

	/**
	 * 微信-定期宝-列表
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author huangpin
	 * @version 0.1 2015年7月31日
	 * @param pageNum
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/list/{pageNum}")
	@ResponseBody
	public Map<String, Object> list(@PathVariable int pageNum) {
		try {
			FixBorrowCnd cnd = new FixBorrowCnd();
			List<?> list = fixBorrowService.queryFixBorrowVoList(cnd, new Page(pageNum, BusinessConstants.DEFAULT_PAGE_SIZE)).getResult();
			if (list != null && list.size() > 0) {
				Map<String, Object> map = new HashMap<String, Object>();
				if (list.size() == 10) {
					map.put("moreDiv", "<a id='clickA'><div id='contain'><div class='f2'><h4>查看更多</h4></div></div></a>");
				}
				map.put("fixList", list);
				return map;
			}
		} catch (Exception e) {
			logger.error("微信-定期宝-列表-异常", e);
		}
		return null;
	}

	/**
	 * 微信-定期宝-详情
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author huangpin
	 * @version 0.1 2015年7月31日
	 * @param fixId
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/detail/{fixId}")
	@ResponseBody
	public Map<String, Object> detail(@PathVariable int fixId) {
		try {
			// 代表查新手宝
			if (fixId == -999) {
				fixId = fixBorrowService.getNewFixBorrow().getId();
			}

			FixBorrowVo fix = fixBorrowService.getFixBorrowById(fixId);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("fix", fix);

			if (currentUser() != null) {
				int userId = currentUser().getUserId();
				// 账户余额
				map.put("useMoney", accountService.queryAccountByUserId(userId).getUseMoney());

				// 账户活期宝金额
				map.put("maxCurMoney", curAccountService.getMaxUseMoneyByNow(userId));

				// 定期宝可投金额
				FixBorrowCnd fixBorrowCnd = new FixBorrowCnd();
				fixBorrowCnd.setUserId(userId);
				fixBorrowCnd.setFixBorrowId(fixId);
				BigDecimal userRemainMoney = fixBorrowService.queryRemainAccountByUserId(fixBorrowCnd);
				map.put("userRemainMoney", userRemainMoney);

				// 是否可投新手宝
				if (fix.getAreaType() != null && fix.getAreaType().intValue() == 1) {
					Integer tenderCount = fixBorrowService.getTenderCountByUserId(userId);
					// 没有投标记录、投宝记录或者认购债转记录
					if (tenderCount.intValue() == 0) {
						map.put("isToTender", true);
					} else {
						Integer newTenderCount = fixBorrowService.getNewTenderCountByUserId(userId);
						Date onlineTime = DateUtils.parse(BusinessConstants.NEW_FIX_BORROW_ONLINE_TIME, DateUtils.YMD_HMS);
						MemberCnd memberCnd = new MemberCnd();
						memberCnd.setId(userId);
						MemberVo memberVo = memberService.queryMemberByCnd(memberCnd);
						Date regTime = DateUtils.timeStampToDate(memberVo.getAddtime());
						// 新手宝上线之后注册的用户，没投过新手宝，也可以投
						if (regTime.after(onlineTime) && newTenderCount.intValue() == 0) {
							map.put("isToTender", true);
						}
					}
				}
			}
			return map;
		} catch (Exception e) {
			logger.error("微信-定期宝-详情-异常", e);
		}
		return null;
	}

	/**
	 * 微信-我的账户-定期宝
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author huangpin
	 * @version 0.1 2015年8月4日
	 * @param opt
	 * @param pageNum
	 * @param tag
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/mylist/{opt}/{pageNum}")
	@ResponseBody
	public Map<String, Object> mylist(@PathVariable String opt, @PathVariable int pageNum, String tag) {
		try {
			List<?> list = null;
			if (currentUser() != null) {
				if ("jiaru".equals(opt)) {
					FixTenderDetailCnd cnd = new FixTenderDetailCnd();
					cnd.setTag(tag);
					cnd.setUserId(currentUser().getUserId());
					list = fixTenderDetailService.queryTotalJoinInfoByPage(cnd, new Page(pageNum, BusinessConstants.DEFAULT_PAGE_SIZE)).getResult();
				} else {
					FixTenderRealCnd fixTenderRealCnd = new FixTenderRealCnd();
					fixTenderRealCnd.setUserId(currentUser().getUserId());

					if ("shouyi".equals(opt)) {
						list = fixTenderRealService.queryTotalSYInfoByPage(fixTenderRealCnd, new Page(pageNum, BusinessConstants.DEFAULT_PAGE_SIZE))
								.getResult();
					} else if ("tuichu".equals(opt)) {
						list = fixTenderRealService.queryTotalTCInfoByPage(fixTenderRealCnd, new Page(pageNum, BusinessConstants.DEFAULT_PAGE_SIZE))
								.getResult();
					}
				}

				Map<String, Object> map = new HashMap<String, Object>();
				if (list.size() == 10) {
					map.put("moreDiv", "<a id='clickA'><div id='contain'><div class='f2'><h4>查看更多</h4></div></div></a>");
				}
				map.put("list", list);
				return map;
			}
			return null;
		} catch (Exception e) {
			logger.error("微信-我的账户-定期宝-列表-异常", e);
		}
		return null;
	}

	/**
	 * 微信-定期宝-加入记录
	 * 
	 * @param fixId
	 * @param pageNum
	 * @return
	 */
	@RequestMapping(value = "/joinList/{fixId}/{pageNum}")
	@ResponseBody
	public Map<String, Object> joinList(@PathVariable int fixId, @PathVariable int pageNum) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			if (currentUser() != null) {
				map.put("userId", currentUser().getUserId());
			}

			Page page = fixTenderDetailService.queryFixTenderDetailVoList(fixId, new Page(pageNum, 20));
			List<?> list = page.getResult();

			if (list != null && list.size() > 0) {

				if (list.size() == 20) {
					map.put("moreDiv", "<a id='clickA'><div id='contain'><div class='f2'><h4>查看更多</h4></div></div></a>");
				}
				map.put("list", list);
				map.put("count", page.getTotalCount());
				return map;
			}
		} catch (Exception e) {
			logger.error("微信-定期宝-加入列表-异常", e);
		}
		return null;
	}

}
