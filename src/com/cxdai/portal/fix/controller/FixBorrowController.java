package com.cxdai.portal.fix.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.druid.util.StringUtils;
import com.cxdai.common.page.Page;
import com.cxdai.portal.account.service.AccountService;
import com.cxdai.portal.account.vo.AccountVo;
import com.cxdai.portal.base.BaseController;
import com.cxdai.portal.base.MessageBox;
import com.cxdai.portal.curAccount.service.CurAccountService;
import com.cxdai.portal.fix.service.FixBorrowService;
import com.cxdai.portal.fix.service.FixRepaymentrecordService;
import com.cxdai.portal.fix.service.FixTenderDetailService;
import com.cxdai.portal.fix.service.FixTenderRealService;
import com.cxdai.portal.fix.service.FixTenderUserService;
import com.cxdai.portal.fix.vo.FixBorrowCnd;
import com.cxdai.portal.fix.vo.FixBorrowOpenCnd;
import com.cxdai.portal.fix.vo.FixBorrowStaticVo;
import com.cxdai.portal.fix.vo.FixBorrowVo;
import com.cxdai.portal.fix.vo.FixTenderDetailCnd;
import com.cxdai.portal.fix.vo.FixTenderDetailVo;
import com.cxdai.portal.fix.vo.FixTenderRealCnd;
import com.cxdai.portal.fix.vo.FixTenderRealVo;
import com.cxdai.portal.fix.vo.FixTenderUserCnd;
import com.cxdai.portal.member.service.MemberService;
import com.cxdai.portal.member.vo.MemberCnd;
import com.cxdai.portal.member.vo.MemberVo;
import com.cxdai.portal.red.entity.RedAccount;
import com.cxdai.portal.red.service.RedAccountService;
import com.cxdai.portal.statics.BusinessConstants;
import com.cxdai.security.ShiroUser;
import com.cxdai.utils.DateUtils;
import com.cxdai.utils.HttpTookit;
import com.cxdai.utils.MoneyUtil;

/**
 * <p>
 * Description:定期宝认购列表统计Controller<br />
 * </p>
 * @title FixBorrowController.java
 * @package com.cxdai.portal.fix1.controller
 * @author caodefeng
 * @version 0.1 2015年5月14日
 */
@Controller
@RequestMapping(value = "/dingqibao")
public class FixBorrowController extends BaseController {
	private final static Logger logger = Logger.getLogger(FixBorrowController.class);

	@Autowired
	private FixBorrowService fixBorrowService;

	@Autowired
	private FixTenderDetailService fixTenderDetailService;

	@Autowired
	private FixTenderRealService fixTenderRealService;

	@Autowired
	private FixTenderUserService fixTenderUserService;

	@Autowired
	private FixRepaymentrecordService fixRepaymentrecordService;

	@Autowired
	private AccountService accountService;

	@Autowired
	private CurAccountService curAccountService;

	@Autowired
	RedAccountService redService;
	
	@Autowired
	private MemberService memberService;

	/**
	 * 定期表列表查询与统计
	 * @param pageNo
	 * @return
	 */
	@RequestMapping
	public ModelAndView toFixBorrowList(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("fix/myFixBorrow");

		try {
			// 统计用户认购次数
			Integer totalJoinCounts = fixTenderDetailService.queryTotalJoinCounts();
			// 统计用户累计加入总金额
			BigDecimal totalAccountYes = fixBorrowService.querySumAccountYes();
			// 统计为用户赚取的利息
			BigDecimal totalInterest = fixRepaymentrecordService.queryTotalInterest();
			// 统计查询资金利用率
			String totalAccountInUseRate = fixBorrowService.queryTotalAccountInUseRate();

			mv.addObject("totalJoinCounts", totalJoinCounts);
			mv.addObject("totalAccountYes", totalAccountYes);
			mv.addObject("totalInterest", totalInterest);
			mv.addObject("totalAccountInUseRate", totalAccountInUseRate);
		} catch (Exception e) {
			logger.error("定期宝统计查询出错", e);
		}
		return mv;
	}

	/**
	 * 查询定期宝列表
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 陈建国
	 * @version 0.1 2015年6月14日
	 * @param pageNum
	 * @param fixBorrowCnd
	 * @return ModelAndView
	 */
	@RequestMapping(value = "queryFixBorrowList")
	public ModelAndView queryFixBorrowList(@RequestParam Integer pageNum, @ModelAttribute FixBorrowCnd fixBorrowCnd) {
		ModelAndView mv = new ModelAndView("fix/fixBorrowGridByPage");

		Page page = null;
		try {
			page = fixBorrowService.queryFixBorrowVoList(fixBorrowCnd, new Page(pageNum, BusinessConstants.DEFAULT_PAGE_SIZE));
		} catch (Exception e) {
			logger.error("定期宝列表查询出错", e);
		}
		mv.addObject("page", page);
		return mv;
	}

	/**
	 * 定期宝详细查询
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}")
	public ModelAndView detailFixBorrow(HttpServletRequest request, @PathVariable("id") Integer id) {
		ModelAndView mv = null;
		try {
			FixBorrowVo fixBorrowVo = fixBorrowService.getFixBorrowById(id);
			// 如果定期宝对象为空返回错误页面
			if (null == fixBorrowVo) {
				mv = new ModelAndView("page/common/404");
				return mv;
			}
			switch (fixBorrowVo.getStatus()) {
			case 3:
				ShiroUser shiroUser = currentUser();
				if (0 == fixBorrowVo.getJoinStatus()) {
					// 跳转至预发详细页面
					mv = new ModelAndView("fix/fixBorrowDetail_ready");
				} else {
					// 跳转至认购详细页面
					mv = new ModelAndView("fix/fixBorrowDetail_join");
				}
				// 获取账户余额
				if (null != shiroUser) {
					int userId = shiroUser.getUserId();
					AccountVo account = accountService.queryAccountByUserId(shiroUser.getUserId());
					FixBorrowCnd fixBorrowCnd = new FixBorrowCnd();
					fixBorrowCnd.setUserId(shiroUser.getUserId());
					fixBorrowCnd.setFixBorrowId(id);
					BigDecimal userRemainMoney = fixBorrowService.queryRemainAccountByUserId(fixBorrowCnd);
					AccountVo accountVo = accountService.queryAccountByUserId(shiroUser.getUserId());
					BigDecimal effectiveTenderMoney = fixBorrowService.getMaxeffectiveMoneyForTenderReal(fixBorrowVo, shiroUser.getUserId(), fixBorrowVo.getMostAccount(), accountVo);
					mv.addObject("userRemainMoney", userRemainMoney);
					mv.addObject("useMoney", account.getUseMoney());
					mv.addObject("maxCurMoney", curAccountService.getMaxUseMoneyByNow(shiroUser.getUserId()));
					mv.addObject("isExistCurAccount", curAccountService.selectByUserId(shiroUser.getUserId()) == null ? false : true);
					mv.addObject("effectiveTenderMoney", effectiveTenderMoney.setScale(2, BigDecimal.ROUND_DOWN));

					// 查询用户可用红包 新手包去除12月活动红包 和推荐人红包 1207		
					if(null!=fixBorrowVo.getAreaType()&&fixBorrowVo.getAreaType()==1){
						//1208  新手宝不能用红包
						mv.addObject("reds", null);
					}else{
						Map<String,Object> map =new HashMap<String,Object>();
						map.put("userid", userId);
						map.put("limit", fixBorrowVo.getLockLimit());
						List<RedAccount> redAccounts = redService.getMyRuleReds(map);
						mv.addObject("reds", redAccounts);
					}
					// 是否可投新手标
					mv.addObject("isToTender",isToTender(fixBorrowVo));
				}
				break;
			case 5:
				// 跳转至锁定中的详细页面
				mv = new ModelAndView("fix/fixBorrowDetail_locked");
				break;
			case 7:
				// 跳转至已结束的详细页面
				mv = new ModelAndView("fix/fixBorrowDetail_end");
				break;
			case 10:
				// 跳转至已转让的详细页面
				mv = new ModelAndView("fix/fixBorrowDetail_transfer");
				break;
			}
			mv.addObject("fixBorrowVo", fixBorrowVo);
			mv.addObject("nowTime", new Date());
			/* 计算进度条 */
			String scheduleStrNoDecimal = "";
			// 如果实际认购金额不为空
			if (null != fixBorrowVo.getAccountYes()) {
				scheduleStrNoDecimal = String.valueOf(fixBorrowVo.getAccountYes().multiply(new BigDecimal(100)).divide(fixBorrowVo.getPlanAccount(), 0, BigDecimal.ROUND_DOWN));
			} else {
				scheduleStrNoDecimal = "0";
			}
			mv.addObject("scheduleStrNoDecimal", scheduleStrNoDecimal);
		} catch (Exception e) {
			logger.error("定期宝详细查询失败!", e);
		}
		return mv;
	}
	
	/**
	 * 获取能否投宝
	 * @author WangQianJin
	 * @title @param fixBorrowVo
	 * @title @return
	 * @date 2015年12月2日
	 */
	private String isToTender(FixBorrowVo fixBorrowVo){
		String result="false";
		if (fixBorrowVo != null) {
			if (fixBorrowVo.getAreaType() != null && fixBorrowVo.getAreaType() == 1) {  //如果为新手标
				ShiroUser shiroUser = currentUser();
				if (shiroUser != null && shiroUser.getUserId() != null) {
					// 查询用户信息
					MemberCnd memberCnd = new MemberCnd();
					memberCnd.setId(shiroUser.getUserId());
					MemberVo memberVo = memberService.queryMemberByCnd(memberCnd);
					if (memberVo != null) {
						Integer tenderCount=fixBorrowService.getTenderCountByUserId(shiroUser.getUserId());						
						// 没有投标记录、投宝记录或者认购债转记录，才能投新手标
						if (tenderCount.intValue() == 0) {
							// 新手标是否可投标
							return "true";
						}
						Integer newTenderCount=fixBorrowService.getNewTenderCountByUserId(shiroUser.getUserId());
						Date onlineTime=DateUtils.parse(BusinessConstants.NEW_FIX_BORROW_ONLINE_TIME,DateUtils.YMD_HMS);
						Date regTime=DateUtils.timeStampToDate(memberVo.getAddtime());
						// 新手宝上线之后注册的用户，没投过新手宝，也可以投
						if (regTime.after(onlineTime) && newTenderCount.intValue() == 0) {
							// 新手标是否可投标
							return "true";
						}
					}
				}
			}else{
				result="true";
			}
		}
		return result;
	}

	/**
	 * 开通定期宝
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/tender/{fixBorrowId}")
	@ResponseBody
	public MessageBox tenderFixBorrow(HttpServletRequest request, FixBorrowOpenCnd fixtBorrowOpenCnd) {
		try {
			// 验证验证码
			String randCode = (String) request.getSession().getAttribute("randomCode");
			if (!randCode.equals(fixtBorrowOpenCnd.getCheckCode())) {
				return new MessageBox("0", "验证码输入有误!");
			}
			ShiroUser shiroUser = currentUser();
			// 黑名单判断
			if (super.judgeBlackByType(BusinessConstants.BLACK_TYPE_TENDER_FIX_BORROW)) {
				return MessageBox.build("0", "对不起，您无开通权限！");
			}
			
			FixBorrowVo fixBorrowVo = fixBorrowService.getFixBorrowById(fixtBorrowOpenCnd.getFixBorrowId());
			if(fixBorrowVo!=null){
				if (fixBorrowVo.getAreaType() != null && fixBorrowVo.getAreaType().intValue() == 1) { 
					String result=isToTender(fixBorrowVo);
					if ("false".equals(result)) {
						return MessageBox.build("0", "您不是新手，无法投新手宝！");
					}
				}
			}else{
				return MessageBox.build("0", "数据不存在");
			}
			
			// 开通定期宝
			String result = "";
			String ip = HttpTookit.getRealIpAddr(currentRequest());
			result = fixBorrowService.saveTenderFixtBorrow(shiroUser.getUserId(), fixtBorrowOpenCnd, shiroUser.getPlatform(), ip);
			// 定期宝满宝复审
			if ("isFull".equals(result)) {
				result = fixBorrowService.updateForReCheck(fixtBorrowOpenCnd, request, shiroUser.getPlatform());
			}
			if (!"success".equals(result)) {
				return new MessageBox("0", result);
			}
		} catch (Exception e) {
			logger.error("开通定期宝失败!", e);
			return new MessageBox("0", "网络连接异常,请刷新页面或稍后重试！");
		}
		return new MessageBox("1", "开通成功");
	}

	/**
	 * 定期宝投保明细查询
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 陈建国
	 * @version 0.1 2015年6月5日
	 * @param pageNum
	 * @param fixBorrowId
	 * @return ModelAndView
	 */
	@RequestMapping(value = "queryFixTenderDetailList")
	public ModelAndView queryFixTenderDetailList(@RequestParam Integer pageNum, @RequestParam Integer fixBorrowId) {
		ModelAndView mv = new ModelAndView("fix/fixTenderDetailGridByPage");
		Page page = null;
		try {
			page = fixTenderDetailService.queryFixTenderDetailVoList(fixBorrowId, new Page(pageNum, BusinessConstants.DEFAULT_PAGE_SIZE));
		} catch (Exception e) {
			logger.error("定期宝明细查询!", e);
		}
		mv.addObject("page", page);
		mv.addObject("fixBorrowId", fixBorrowId);
		ShiroUser shiroUser = currentUser();
		mv.addObject("loginMember", shiroUser);
		return mv;
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 陈建国
	 * @version 0.1 2015年5月21日
	 * @param request
	 * @param session
	 * @param fixtBorrowOpenCnd
	 * @return MessageBox
	 */
	@RequestMapping(value = "/myAccount")
	@ResponseBody
	public ModelAndView myAccount(HttpServletRequest request, HttpSession session, FixBorrowOpenCnd fixtBorrowOpenCnd) {
		ModelAndView mv = new ModelAndView("/fix/fixBorrowMyAccount");

		try {

			// 活期生息:根据userId 查询curAccount
			ShiroUser shiroUser = super.currentUser();
			List<FixBorrowStaticVo> retFixlists = new ArrayList<FixBorrowStaticVo>();
			FixBorrowCnd fixBorrowCnd = new FixBorrowCnd();
			// 如果登录用户不为空
			if (shiroUser != null) {
				fixBorrowCnd.setUserId(shiroUser.getUserId());
			} else {
				logger.error("当前未登陆，无当前登录用户!");
				return new ModelAndView(BusinessConstants.NO_PAGE_FOUND_404);
			}
			retFixlists = fixBorrowService.queryFixAccountInfoMap(fixBorrowCnd);
			Map<String, String> retMap = new HashMap<String, String>();
			if (retFixlists != null && retFixlists.size() > 0) {
				for (FixBorrowStaticVo fixBorrowStaticVo : retFixlists) {

					retMap.put("repayYesAccountYes", MoneyUtil.roundMoney(fixBorrowStaticVo.getRepayYesAccountYes()));
					retMap.put("capital", MoneyUtil.roundMoney(fixBorrowStaticVo.getCapital().add(fixBorrowStaticVo.getRepayYesAccountNo())));
					retMap.put("repayYesAccountNo", MoneyUtil.roundMoney(fixBorrowStaticVo.getRepayYesAccountNo()));

				}
			}
			// 统计用户加入定期宝次数（加入中，收益中，已退出）
			FixBorrowStaticVo fixBorrowStaticVo = fixBorrowService.queryFixStatusCount(fixBorrowCnd);
			// 如果统计对象不为空
			if (fixBorrowStaticVo != null) {
				String jrz = fixBorrowStaticVo.getJrz().toString();
				String syz = fixBorrowStaticVo.getSyz().toString();
				String ytc = fixBorrowStaticVo.getYtc().toString();
				retMap.put("jrz", jrz);
				retMap.put("syz", syz);
				retMap.put("ytc", ytc);
			}
			mv.addObject("retMap", retMap);
		} catch (Exception e) {
			logger.error("我的账户报错", e);
		}

		return mv;
	}

	/**
	 * <p>
	 * Description:点击tab1- 定期宝收益明细 <br />
	 * </p>
	 * @author chenjianguo
	 * @version 0.1 2015年5月5日
	 * @param request
	 * @param pageNum
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/tab1JrzDetail/{pageNum}")
	public ModelAndView tab1InterestDetail(HttpServletRequest request, @PathVariable int pageNum, @ModelAttribute FixTenderDetailCnd fixTenderDetailCnd) {
		// 返回jsp
		ModelAndView mv = new ModelAndView("/fix/fixBorrowJoin");
		String tag = fixTenderDetailCnd.getTag();
		// 收益明细首次加载tag
		String initTag = fixTenderDetailCnd.getInitTag();
		try {
			// 首次加载，查询全部记录
			if (!StringUtils.isEmpty(initTag) && initTag.equals("0") || StringUtils.isEmpty(tag)) {
				fixTenderDetailCnd.setTag(null); // 如果点击全部，则这边强制设置为null
			} else {
				if (!StringUtils.isEmpty(tag)) {
					fixTenderDetailCnd.setTag(tag);
				}
			}
			// 根据userId 查询收益明细
			ShiroUser shiroUser = super.currentUser();
			if (shiroUser != null) {
				fixTenderDetailCnd.setUserId(shiroUser.getUserId());
			} else {
				logger.info("当前未登陆，无当前登录用户!");
				return new ModelAndView(BusinessConstants.NO_PAGE_FOUND_404);
			}

			// 分页- 页数处理
			Page retPage = new Page();
			retPage = fixTenderDetailService.queryTotalJoinInfoByPage(fixTenderDetailCnd, new Page(pageNum, BusinessConstants.DEFAULT_PAGE_SIZE));
			BigDecimal totalYqsy = fixTenderDetailService.getYqsyByJrz(fixTenderDetailCnd);
			// 计算预期总收益
			mv.addObject("totalYqsy", totalYqsy);
			mv.addObject("page", retPage);
			mv.addObject("tag", tag);
		} catch (Exception e) {
			logger.error("定期宝收益明细报错", e);
		}

		return mv;

	}

	/**
	 * 收益中查询
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 陈建国
	 * @version 0.1 2015年6月5日
	 * @param request
	 * @param pageNum
	 * @param fixTenderRealCnd
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/tab2SyzDetail/{pageNum}")
	public ModelAndView tab2InterestDetail(HttpServletRequest request, @PathVariable int pageNum, @ModelAttribute FixTenderRealCnd fixTenderRealCnd) {
		// 返回jsp
		ModelAndView mv = new ModelAndView("/fix/fixBorrowSyz");
		String tag = fixTenderRealCnd.getTag();
		// 收益明细首次加载tag
		String initTag = fixTenderRealCnd.getInitTag();
		try {

			// 首次加载，查询全部记录
			if (!StringUtils.isEmpty(initTag) && initTag.equals("0") || StringUtils.isEmpty(tag)) {
				fixTenderRealCnd.setTag(null); // 如果点击全部，则这边强制设置为null
			} else {

				if (!StringUtils.isEmpty(tag)) {
					if (!StringUtils.isEmpty(tag)) {
						fixTenderRealCnd.setTag(tag);
					}
				}
			}
			// 根据userId 查询收益明细
			ShiroUser shiroUser = super.currentUser();

			if (shiroUser != null) {
				fixTenderRealCnd.setUserId(shiroUser.getUserId());
			} else {
				logger.info("当前未登陆，无当前登录用户!");
				return new ModelAndView(BusinessConstants.NO_PAGE_FOUND_404);
			}

			// 分页- 页数处理
			Page retPage = new Page();
			retPage = fixTenderRealService.queryTotalSYInfoByPage(fixTenderRealCnd, new Page(pageNum, BusinessConstants.DEFAULT_PAGE_SIZE));
			BigDecimal totalYqsy = fixTenderRealService.getYqsyBySzy(fixTenderRealCnd);
			mv.addObject("totalYqsy", totalYqsy);
			mv.addObject("page", retPage);
			mv.addObject("tag", tag);
		} catch (Exception e) {
			logger.error("收益中查询", e);
		}

		return mv;

	}

	/**
	 * 退出中查询
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 陈建国
	 * @version 0.1 2015年6月5日
	 * @param request
	 * @param pageNum
	 * @param fixTenderRealCnd
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/tab3TczDetail/{pageNum}")
	public ModelAndView tab3InterestDetail(HttpServletRequest request, @PathVariable int pageNum, @ModelAttribute FixTenderRealCnd fixTenderRealCnd) {
		// 返回jsp
		ModelAndView mv = new ModelAndView("/fix/fixBorrowTcz");

		String tag = fixTenderRealCnd.getTag();
		// 收益明细首次加载tag
		String initTag = fixTenderRealCnd.getInitTag();
		try {

			// 首次加载，查询全部记录
			if (!StringUtils.isEmpty(initTag) && initTag.equals("0") || StringUtils.isEmpty(tag)) {
				fixTenderRealCnd.setTag(null); // 如果点击全部，则这边强制设置为null
			} else {
				if (!StringUtils.isEmpty(tag)) {

					if (!StringUtils.isEmpty(tag)) {
						fixTenderRealCnd.setTag(tag);
					}
				}
			}
			// 根据userId 查询收益明细
			ShiroUser shiroUser = super.currentUser();

			if (shiroUser != null) {
				fixTenderRealCnd.setUserId(shiroUser.getUserId());
			} else {
				logger.info("当前未登陆，无当前登录用户!");
				return new ModelAndView(BusinessConstants.NO_PAGE_FOUND_404);
			}
			// 分页- 页数处理
			Page retPage = new Page();
			retPage = fixTenderRealService.queryTotalTCInfoByPage(fixTenderRealCnd, new Page(pageNum, BusinessConstants.DEFAULT_PAGE_SIZE));
			mv.addObject("page", retPage);
			mv.addObject("tag", tag);
		} catch (Exception e) {
			logger.error("退出中查询报错", e);
		}

		return mv;

	}

	/**
	 * 加入中详细页面查询
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 陈建国
	 * @version 0.1 2015年6月5日
	 * @param request
	 * @param fixBorrowId
	 * @param pageNum
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/detailJrz/{fixBorrowId}/{pageNum}")
	public ModelAndView detailJrz(HttpServletRequest request, @PathVariable int fixBorrowId, @PathVariable int pageNum) {
		// 返回jsp
		ModelAndView mv = new ModelAndView("/fix/fixBorrowJrzDtl");
		ShiroUser shiroUser = super.currentUser();
		FixTenderDetailCnd fixTenderDetailCnd = new FixTenderDetailCnd();
		fixTenderDetailCnd.setFixBorrowId(fixBorrowId);
		fixTenderDetailCnd.setUserId(shiroUser.getUserId());
		FixTenderDetailVo fixTenderDetailVo = new FixTenderDetailVo();
		try {
			fixTenderDetailVo = fixTenderDetailService.getFixTenderDetailStaticByBorrowId(fixTenderDetailCnd);
			mv.addObject("fixTenderDetailVo", fixTenderDetailVo);
			// 分页- 页数处理
			Page retPage = new Page();
			retPage = fixTenderDetailService.queryJoinInfoByPage(fixTenderDetailCnd, new Page(pageNum, BusinessConstants.DEFAULT_PAGE_SIZE));
			mv.addObject("page", retPage);

		} catch (Exception e) {
			logger.error("加入中详细页面查询", e);
		}
		return mv;

	}

	/**
	 * 收益中详细页面查询
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 陈建国
	 * @version 0.1 2015年6月5日
	 * @param request
	 * @param fixBorrowId
	 * @param pageNum
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/detailSyz/{fixBorrowId}")
	public ModelAndView detailSyz(HttpServletRequest request, @PathVariable int fixBorrowId) {
		// 返回jsp
		ModelAndView mv = new ModelAndView("/fix/fixBorrowSyzDtl");
		ShiroUser shiroUser = super.currentUser();
		FixTenderRealCnd fixTenderRealCnd = new FixTenderRealCnd();
		fixTenderRealCnd.setFixBorrowId(fixBorrowId);
		fixTenderRealCnd.setUserId(shiroUser.getUserId());
		FixTenderRealVo fixTenderRealVo = new FixTenderRealVo();
		try {
			fixTenderRealVo = fixTenderRealService.getFixTenderRealstaticByBorrowId(fixTenderRealCnd).get(0);

			mv.addObject("fixTenderRealVo", fixTenderRealVo);
		} catch (Exception e) {
			logger.error("收益中详细页面查询", e);
		}

		return mv;

	}

	/**
	 * 投标详细页面查询
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 陈建国
	 * @version 0.1 2015年6月5日
	 * @param request
	 * @param fixBorrowId
	 * @param pageNum
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/tbDetail/{fixBorrowId}/{pageNum}")
	public ModelAndView detailTb(HttpServletRequest request, @PathVariable int fixBorrowId, @PathVariable int pageNum) {
		// 返回jsp
		ModelAndView mv = new ModelAndView("/fix/fixBorrowTbDtl");
		ShiroUser shiroUser = super.currentUser();
		FixTenderUserCnd fixTenderUserCnd = new FixTenderUserCnd();
		fixTenderUserCnd.setFixBorrowId(fixBorrowId);
		fixTenderUserCnd.setUserId(shiroUser.getUserId());

		// 分页- 页数处理
		Page retPage = new Page();
		try {
			retPage = fixTenderUserService.queryTotalTBByPage(fixTenderUserCnd, new Page(pageNum, BusinessConstants.DEFAULT_PAGE_SIZE));
		} catch (Exception e) {
			logger.error("投标详细页面查询", e);
		}

		mv.addObject("fixBorrowId", fixBorrowId);
		mv.addObject("page", retPage);
		return mv;

	}

	/**
	 * 投标明细查询
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 陈建国
	 * @version 0.1 2015年6月5日
	 * @param request
	 * @param pageNum
	 * @param fixBorrowId
	 * @param fixTenderRealCnd
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/tab1tbDetail/{fixBorrowId}/{pageNum}")
	public ModelAndView tab1tbDetail(HttpServletRequest request, @PathVariable int pageNum, @PathVariable int fixBorrowId, @ModelAttribute FixTenderRealCnd fixTenderRealCnd) {
		// 返回jsp
		ModelAndView mv = new ModelAndView("/fix/fixBorrowSyzTb");
		try {

			FixTenderUserCnd fixTenderUserCnd = new FixTenderUserCnd();
			ShiroUser shiroUser = super.currentUser();
			fixTenderUserCnd.setUserId(shiroUser.getUserId());
			fixTenderUserCnd.setFixBorrowId(fixBorrowId);
			Page retPage = new Page();
			retPage = fixTenderUserService.queryTotalTBByPage(fixTenderUserCnd, new Page(pageNum, BusinessConstants.DEFAULT_PAGE_SIZE));
			mv.addObject("page", retPage);
		} catch (Exception e) {
			logger.error("投标明细查询", e);
		}

		return mv;

	}

	/**
	 * 加入中详细查询
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 陈建国
	 * @version 0.1 2015年6月5日
	 * @param request
	 * @param pageNum
	 * @param fixBorrowId
	 * @param fixTenderRealCnd
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/tab2jrDetail/{fixBorrowId}/{pageNum}")
	public ModelAndView tab2jrDetail(HttpServletRequest request, @PathVariable int pageNum, @PathVariable int fixBorrowId, @ModelAttribute FixTenderRealCnd fixTenderRealCnd) {
		// 返回jsp
		ModelAndView mv = new ModelAndView("/fix/fixBorrowSyzJr");
		try {

			FixTenderDetailCnd fixTenderDetailCnd = new FixTenderDetailCnd();

			ShiroUser shiroUser = super.currentUser();
			fixTenderDetailCnd.setUserId(shiroUser.getUserId());
			fixTenderDetailCnd.setFixBorrowId(fixBorrowId);
			// 分页- 页数处理
			Page retPage = new Page();
			retPage = fixTenderDetailService.queryJoinInfoByPage(fixTenderDetailCnd, new Page(pageNum, BusinessConstants.DEFAULT_PAGE_SIZE));
			mv.addObject("page", retPage);
		} catch (Exception e) {
			logger.error("加入中详细查询", e);
		}

		return mv;

	}

	/**
	 * 退出中详细页面
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 陈建国
	 * @version 0.1 2015年6月5日
	 * @param request
	 * @param fixBorrowId
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/detailTcz/{fixBorrowId}")
	public ModelAndView detailTcz(HttpServletRequest request, @PathVariable int fixBorrowId) {
		// 返回jsp
		ModelAndView mv = new ModelAndView("/fix/fixBorrowTczDtl");
		ShiroUser shiroUser = super.currentUser();
		FixTenderRealCnd fixTenderRealCnd = new FixTenderRealCnd();
		fixTenderRealCnd.setFixBorrowId(fixBorrowId);
		fixTenderRealCnd.setUserId(shiroUser.getUserId());
		FixTenderRealVo fixTenderRealVo = new FixTenderRealVo();
		try {
			fixTenderRealVo = fixTenderRealService.getTcstaticByBorrowId(fixTenderRealCnd).get(0);
			mv.addObject("fixTenderRealVo", fixTenderRealVo);
		} catch (Exception e) {
			logger.error("退出中详细页面查询", e);
		}
		return mv;

	}

	/**
	 * 退出中明细查询
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 陈建国
	 * @version 0.1 2015年6月14日
	 * @param request
	 * @param pageNum
	 * @param fixBorrowId
	 * @param fixTenderRealCnd
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/tab1TcztbDetail/{fixBorrowId}/{pageNum}")
	public ModelAndView tab1TcztbDetail(HttpServletRequest request, @PathVariable int pageNum, @PathVariable int fixBorrowId, @ModelAttribute FixTenderRealCnd fixTenderRealCnd) {
		// 返回jsp
		ModelAndView mv = new ModelAndView("/fix/fixBorrowSyzTb");
		try {

			FixTenderUserCnd fixTenderUserCnd = new FixTenderUserCnd();
			ShiroUser shiroUser = super.currentUser();
			fixTenderUserCnd.setUserId(shiroUser.getUserId());
			fixTenderUserCnd.setFixBorrowId(fixBorrowId);
			// 分页- 页数处理
			Page retPage = new Page();
			retPage = fixTenderUserService.queryTotalTBByPage(fixTenderUserCnd, new Page(pageNum, BusinessConstants.DEFAULT_PAGE_SIZE));
			// page 转 list
			mv.addObject("page", retPage);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return mv;

	}

	/**
	 * <p>
	 * Description:点击tab1- 定期宝收益明细 <br />
	 * </p>
	 * @author HuangJun
	 * @version 0.1 2015年5月5日
	 * @param request
	 * @param pageNum
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/tab2TczjrDetail/{fixBorrowId}/{pageNum}")
	public ModelAndView tab2TczjrDetail(HttpServletRequest request, @PathVariable int pageNum, @PathVariable int fixBorrowId, @ModelAttribute FixTenderRealCnd fixTenderRealCnd) {
		// 返回jsp
		ModelAndView mv = new ModelAndView("/fix/fixBorrowSyzJr");
		try {

			FixTenderDetailCnd fixTenderDetailCnd = new FixTenderDetailCnd();

			ShiroUser shiroUser = super.currentUser();
			fixTenderDetailCnd.setUserId(shiroUser.getUserId());
			fixTenderDetailCnd.setFixBorrowId(fixBorrowId);
			// 分页- 页数处理
			Page retPage = new Page();
			retPage = fixTenderDetailService.queryJoinInfoByPage(fixTenderDetailCnd, new Page(pageNum, BusinessConstants.DEFAULT_PAGE_SIZE));
			mv.addObject("page", retPage);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return mv;

	}

	/**
	 * 查看协议
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 陈建国
	 * @version 0.1 2015年6月9日
	 * @param request
	 * @param borrow_id
	 * @return ModelAndView
	 */
	@RequiresAuthentication
	@RequestMapping(value = "/toShowFixXiyi/{fix_borrow_id}")
	public ModelAndView toBorrowXiyi(HttpServletRequest request, @PathVariable int fix_borrow_id, String money) {
		ModelAndView mv = new ModelAndView("/fix/fixBorrow_xiyi");
		try {
			ShiroUser shiroUser = currentUser();
			Map<String, Integer> dto = new HashMap<String, Integer>();
			// 如果登录对象为空
			if (shiroUser == null) {
				return redirect("/member/toLogin.html");
			}
			Integer userId = shiroUser.getUserId();
			dto.put("userId", userId);
			dto.put("fixBorrowId", fix_borrow_id);
			String content = fixBorrowService.queryBorrowProtocol(fix_borrow_id, request.getContextPath(), userId, money);
			mv.addObject("content", content);
		} catch (Exception e) {
			logger.error("查看定期宝协议出错", e);
		}
		return mv;
	}

	/**
	 * 根据定期宝id获取当前用户的有效开通金额
	 * @author WangQianJin
	 * @title @param request
	 * @title @param id
	 * @title @return
	 * @date 2015年8月24日
	 */
	@RequestMapping(value = "/findEffectiveTenderMoney/{id}")
	@ResponseBody
	public BigDecimal findEffectiveTenderMoney(HttpServletRequest request, @PathVariable Integer id) {
		ShiroUser shiroUser = currentUser();
		BigDecimal result = new BigDecimal(0);
		try {
			// 获取优先投标信息
			FixBorrowVo fixBorrowVo = fixBorrowService.getFixBorrowById(id);
			AccountVo accountVo = accountService.queryAccountByUserId(shiroUser.getUserId());
			String isUseCurMoney = request.getParameter("isUseCurMoney");
			if (isUseCurMoney != null && !isUseCurMoney.equals("")) {
				fixBorrowVo.setIsUseCurMoney(isUseCurMoney);
			}
			// 获取投标有效金额
			result = fixBorrowService.getMaxeffectiveMoneyForTenderReal(fixBorrowVo, shiroUser.getUserId(), fixBorrowVo.getMostAccount(), accountVo);

		} catch (Exception e) {
			result = new BigDecimal(0);
		}
		return result;
	}

}
