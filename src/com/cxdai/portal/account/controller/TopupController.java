package com.cxdai.portal.account.controller;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cxdai.base.entity.Configuration;
import com.cxdai.common.Dictionary;
import com.cxdai.common.page.Page;
import com.cxdai.common.report.JasperExportUtils;
import com.cxdai.common.report.ReportData;
import com.cxdai.common.statics.Constants;
import com.cxdai.portal.account.service.AccountService;
import com.cxdai.portal.account.service.CashRecordService;
import com.cxdai.portal.account.service.DrawLogRecordService;
import com.cxdai.portal.account.service.InvestReportService;
import com.cxdai.portal.account.service.RechargeRecordService;
import com.cxdai.portal.account.vo.AccountVo;
import com.cxdai.portal.account.vo.CashRecordCnd;
import com.cxdai.portal.account.vo.CashRecordVo;
import com.cxdai.portal.account.vo.RechargeRecordCnd;
import com.cxdai.portal.account.vo.TurnDrawLogVO;
import com.cxdai.portal.base.BaseController;
import com.cxdai.portal.base.MessageBox;
import com.cxdai.portal.member.service.BankInfoService;
import com.cxdai.portal.member.service.MemberService;
import com.cxdai.portal.member.service.MobileApproService;
import com.cxdai.portal.member.service.RealNameApproService;
import com.cxdai.portal.member.vo.BankInfoVo;
import com.cxdai.portal.member.vo.MemberApproVo;
import com.cxdai.portal.member.vo.MemberCnd;
import com.cxdai.portal.member.vo.MemberVo;
import com.cxdai.portal.member.vo.MobileApproVo;
import com.cxdai.portal.member.vo.RealNameApproVo;
import com.cxdai.portal.sms.service.SmsSendService;
import com.cxdai.portal.sms.service.SmsTemplateService;
import com.cxdai.portal.statics.BusinessConstants;
import com.cxdai.security.ShiroUser;
import com.cxdai.utils.DateUtils;
import com.cxdai.utils.exception.AppException;

/**
 * <p>
 * Description:用户充值Controller<br />
 * </p>
 * @title TopupController.java
 * @package com.cxdai.portal.account.controller
 * @author justin.xu
 * @version 0.1 2014年5月21日
 */
@Controller
@RequestMapping(value = "/account/topup")
public class TopupController extends BaseController {
	private static final Logger logger = Logger.getLogger(TopupController.class);
	@Autowired
	private InvestReportService investReportService;
	@Autowired
	private RealNameApproService realNameApproService;
	@Autowired
	private AccountService accountService;
	@Autowired
	private RechargeRecordService rechargeRecordService;
	@Autowired
	private CashRecordService cashRecordService;
	@Autowired
	private MobileApproService mobileApproService;
	@Autowired
	private SmsTemplateService smsTemplateService;
	@Autowired
	private SmsSendService smsSendService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private BankInfoService bankInfoService;

	/** begin add by hujianpan */
	@Autowired
	DrawLogRecordService drawLogRecordService;

	/** end add by hujianpan */

	/**
	 * <p>
	 * Description:跳转到充值菜单页面<br />
	 * </p>
	 * @author justin.xu
	 * @version 0.1 2014年5月21日
	 * @param request
	 * @return ModelAndView
	 */
	@RequestMapping(value = "toTopupIndex")
	@RequiresAuthentication
	public ModelAndView toTopupMain() throws Exception {
		ModelAndView mv = new ModelAndView("account/topup/topupIndex");
		ShiroUser shiroUser = currentUser();
		if (super.judgeBlackByType(BusinessConstants.BLACK_TYPE_ONLINE_RECHARGE)) {
			mv = new ModelAndView("redirect:/myaccount/toIndex.html");
			return mv;
		}
		// 查询用户认证信息
		MemberApproVo memberApproVo = memberService.queryMemberApproByUserId(shiroUser.getUserId());
		if ((memberApproVo == null || memberApproVo.getEmailChecked() == null || memberApproVo.getEmailChecked() != 1)
				&& (memberApproVo.getMobilePassed() == null || memberApproVo.getMobilePassed() != 1)) {
			mv.addObject("errorCode", "-1");
			return mv;
		}
		// 判断是否通过了实名认证
		if (null == memberApproVo.getNamePassed() || memberApproVo.getNamePassed() != Constants.REALNAME_APPR_ISPASSED_PASSED) {
			mv.addObject("errorCode", "-2");
			return mv;
		}

		// 检查是否绑定了银行卡
		BankInfoVo bankInfoVo = bankInfoService.getUserCurrentCard(shiroUser.getUserId());
		if (null == bankInfoVo) {
			mv.addObject("errorCode", "-3");
			return mv;
		}

		// 设置菜单名
		mv.addObject(BusinessConstants.ACCOUNT_FIRST_MENU, BusinessConstants.LEFT_MENU_ZJ);
		mv.addObject(BusinessConstants.ACCOUNT_SECOND_MENU, BusinessConstants.LEFT_MENU_ZJ_TOPUP);
		return mv;
	}

	@RequestMapping(value = "topupVaild")
	@RequiresAuthentication
	@ResponseBody
	public MessageBox topupVaild() {
		try {
			ShiroUser shiroUser = currentUser();
			if (super.judgeBlackByType(BusinessConstants.BLACK_TYPE_ONLINE_RECHARGE)) {
				return MessageBox.build("0", "网络连接异常，请刷新页面或稍后重试！");
			}
			// 查询用户认证信息
			MemberApproVo memberApproVo = memberService.queryMemberApproByUserId(shiroUser.getUserId());
			if ((memberApproVo == null || memberApproVo.getEmailChecked() == null || memberApproVo.getEmailChecked() != 1)
					&& (memberApproVo.getMobilePassed() == null || memberApproVo.getMobilePassed() != 1)) {
				return MessageBox.build("0", "请先进行手机认证！");
			}
			// 判断是否通过了实名认证
			if (null == memberApproVo.getNamePassed() || memberApproVo.getNamePassed() != Constants.REALNAME_APPR_ISPASSED_PASSED) {
				return MessageBox.build("0", "请先进行实名认证！");
			}

			// 检查是否绑定了银行卡
			BankInfoVo bankInfoVo = bankInfoService.getUserCurrentCard(shiroUser.getUserId());
			if (null == bankInfoVo) {
				return MessageBox.build("0", "请先绑定银行卡！");
			}
		} catch (Exception e) {
			return MessageBox.build("0", "网络连接异常，请刷新页面或稍后重试！");
		}

		return MessageBox.build("1", "success");
	}

	/**
	 * <p>
	 * Description:进入充值页面<br />
	 * </p>
	 * @author justin.xu
	 * @version 0.1 2014年5月21日
	 * @param request
	 * @param session
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/toTopupInner")
	@RequiresAuthentication
	public ModelAndView toTopup(HttpServletRequest request, HttpSession session) throws Exception {
		ModelAndView mv = new ModelAndView("account/topup/topup");
		ShiroUser shiroUser = currentUser();
		if (super.judgeBlackByType(BusinessConstants.BLACK_TYPE_ONLINE_RECHARGE)) {
			mv = new ModelAndView("redirect:/account/topup/toTopupIndex.html");
			return mv;
		}
		// 充值总额
		BigDecimal rechangeTotalMoney = investReportService.queryRechargeTotalByMemberId(shiroUser.getUserId());
		// 提现总额
		BigDecimal cashTotalMoney = investReportService.queryCashTotalByMemberId(shiroUser.getUserId());
		// 净充值总额
		BigDecimal purenessRechangeTotalMoney = rechangeTotalMoney.subtract(cashTotalMoney);
		// 查询实名认证对象
		RealNameApproVo realNameAppro = realNameApproService.queryRealNameApproByUserId(shiroUser.getUserId());
		if (realNameAppro != null && !realNameAppro.getRealName().equals("")) {
			String str1 = realNameAppro.getRealName().substring(realNameAppro.getRealName().length() - 1);
			String str2 = "";
			for (int i = 0; i < realNameAppro.getRealName().length() - 1; i++) {
				str2 = str2 + "*";
			}
			realNameAppro.setRealName(str2 + str1);
		}
		// 当前用户资金帐号
		AccountVo accountVo = accountService.queryAccountByUserId(shiroUser.getUserId());
		mv.addObject("rechangeTotalMoney", rechangeTotalMoney);
		mv.addObject("cashTotalMoney", cashTotalMoney);
		mv.addObject("purenessRechangeTotalMoney", purenessRechangeTotalMoney);
		mv.addObject("realNameAppro", realNameAppro);
		mv.addObject("account", accountVo);
		mv.addObject("shiroUser", shiroUser);
		return mv;
	}

	/**
	 * <p>
	 * Description:查询当前用户的充值明细--內部頁面.<br />
	 * </p>
	 * @author 胡建盼
	 * @version 0.1 2014年8月6日
	 * @param request
	 * @param pageSize
	 * @param pageNum
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/toRechargeRecordInner/{pageNum}")
	public ModelAndView toRechargeRecordInner(HttpServletRequest request, @PathVariable int pageNum) {
		ModelAndView mv = new ModelAndView("account/account/rechargeRecord-listInner");
		RechargeRecordCnd rechargeRecordCnd = new RechargeRecordCnd();
		String status = request.getParameter("status");
		String beginTime = request.getParameter("beginTime");
		String endTime = request.getParameter("endTime");
		if (!StringUtils.isEmpty(status)) {
			try {
				rechargeRecordCnd.setRechargeStatus(Integer.valueOf(status));
			} catch (Exception e) {
				status = StringUtils.EMPTY;
				rechargeRecordCnd.setRechargeStatus(null);
			}
		} else {
			status = StringUtils.EMPTY;
		}
		if (!StringUtils.isEmpty(beginTime)) {
			try {
				rechargeRecordCnd.setBeginTime(DateUtils.parse(beginTime + " 00:00:00", DateUtils.YMD_HMS));
			} catch (Exception e) {
				rechargeRecordCnd.setBeginTime(null);
			}
		}
		if (!StringUtils.isEmpty(endTime)) {
			try {
				rechargeRecordCnd.setEndTime(DateUtils.parse(endTime + " 23:59:59", DateUtils.YMD_HMS));
			} catch (Exception e) {
				rechargeRecordCnd.setEndTime(null);
			}
		}
		try {
			ShiroUser shiroUser = currentUser();

			if (null == shiroUser) {
				return new ModelAndView(BusinessConstants.NO_PAGE_FOUND_404);
			}
			rechargeRecordCnd.setUserId(Integer.valueOf(shiroUser.getUserId()));
			Page p = rechargeRecordService.queryPageListByCnd(rechargeRecordCnd, new Page(pageNum, BusinessConstants.DEFAULT_PAGE_SIZE));
			/* 获取充值状态信息 */
			Collection<Configuration> configurationList = Dictionary.getValues(801);

			mv.addObject("rechargeTotal", rechargeRecordService.queryRechargeTotalByCnd(rechargeRecordCnd));
			mv.addObject("configurationList", configurationList);
			mv.addObject("page", p);
			mv.addObject("status", status);
			mv.addObject("beginTime", beginTime);
			mv.addObject("endTime", endTime);
		} catch (Exception e) {
			logger.info("查询异常。。。");
		}

		return mv;
	}

	/**
	 * 查询当前用户的提现记录.
	 * @author 胡建盼
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/toCashRecord/{pageNum}")
	public ModelAndView toCashRecord(HttpServletRequest request, @PathVariable int pageNum) {
		ModelAndView mv = new ModelAndView("account/account/cashRecord-list");
		CashRecordCnd cashRecordCnd = new CashRecordCnd();
		try {
			ShiroUser shiroUser = currentUser();
			if (null == shiroUser) {
				return new ModelAndView(BusinessConstants.NO_PAGE_FOUND_404);
			}
			cashRecordCnd.setUserId(shiroUser.getUserId());
			Page p = cashRecordService.queryPageListByCnd(cashRecordCnd, new Page(pageNum, BusinessConstants.DEFAULT_PAGE_SIZE));

			List<CashRecordVo> cashRecordList = null;
			if (p.getResult() != null) {
				cashRecordList = (List<CashRecordVo>) p.getResult();
			}

			mv.addObject("cashRecordList", cashRecordList);
			mv.addObject("pageNum", p.getPageNo());
			mv.addObject("pageSize", p.getPageSize());
			mv.addObject("totalCount", p.getTotalCount());
			mv.addObject("totalPage", p.getTotalPage());
			mv.addObject("page", p);

		} catch (Exception e) {
			logger.info("查询异常。。。");
		}

		return mv;
	}

	/**
	 * 查询当前用户的提现记录.
	 * @author 胡建盼
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/toCashRecordInner/{pageNum}")
	public ModelAndView toCashRecordInner(HttpServletRequest request, @PathVariable int pageNum) {
		ModelAndView mv = new ModelAndView("account/account/cashRecord-listInner");
		CashRecordCnd cashRecordCnd = new CashRecordCnd();
		String status = request.getParameter("status");
		String beginTime = request.getParameter("beginTime");
		String endTime = request.getParameter("endTime");
		if (!StringUtils.isEmpty(status)) {
			try {
				cashRecordCnd.setRechargeStatus(Integer.valueOf(status));
			} catch (Exception e) {
				status = StringUtils.EMPTY;
				cashRecordCnd.setRechargeStatus(null);
			}
		} else {
			status = StringUtils.EMPTY;
		}
		if (!StringUtils.isEmpty(beginTime)) {
			try {
				cashRecordCnd.setBeginTime(DateUtils.parse(beginTime + " 00:00:00", DateUtils.YMD_HMS));
			} catch (Exception e) {
				cashRecordCnd.setBeginTime(null);
			}
		}
		if (!StringUtils.isEmpty(endTime)) {
			try {
				cashRecordCnd.setEndTime(DateUtils.parse(endTime + " 23:59:59", DateUtils.YMD_HMS));
			} catch (Exception e) {
				cashRecordCnd.setEndTime(null);
			}
		}
		try {
			ShiroUser shiroUser = currentUser();
			if (null == shiroUser) {
				return new ModelAndView(BusinessConstants.NO_PAGE_FOUND_404);
			}
			cashRecordCnd.setUserId(shiroUser.getUserId());
			Page p = cashRecordService.queryPageListByCnd(cashRecordCnd, new Page(pageNum, BusinessConstants.DEFAULT_PAGE_SIZE));
			// 获取账户提现状态
			Collection<Configuration> configurationList = Dictionary.getValues(802);
			mv.addObject("cashTotal", cashRecordService.queryCashRecordTotalByCnd(cashRecordCnd));
			mv.addObject("configurationList", configurationList);
			mv.addObject("page", p);
			mv.addObject("status", status);
			mv.addObject("beginTime", beginTime);
			mv.addObject("endTime", endTime);
		} catch (Exception e) {
			logger.info("查询异常。。。");
		}

		return mv;
	}

	/**
	 * 查询当前用户的转可提明细.
	 * @author 胡建盼
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/toDrawLogRecord/{pageNum}")
	public ModelAndView toDrawLogRecord(HttpServletRequest request, @PathVariable int pageNum) {
		ModelAndView mv = new ModelAndView("account/account/turnDrawLogRecordInner");
		CashRecordCnd cashRecordCnd = new CashRecordCnd();
		try {
			String startTime = request.getParameter("startTime");
			String endTime = request.getParameter("endTime");
			ShiroUser shiroUser = currentUser();
			if (null == shiroUser) {
				return new ModelAndView(BusinessConstants.NO_PAGE_FOUND_404);
			}
			Page p = drawLogRecordService.queryDrawPageListByCnd(shiroUser.getUserId(), startTime, endTime, new Page(pageNum, BusinessConstants.DEFAULT_PAGE_SIZE));

			List<TurnDrawLogVO> drawLogRecordList = null;
			if (p.getResult() != null) {
				drawLogRecordList = (List<TurnDrawLogVO>) p.getResult();
			}

			mv.addObject("drawLogRecordList", drawLogRecordList);
			mv.addObject("pageNum", p.getPageNo());
			mv.addObject("pageSize", p.getPageSize());
			mv.addObject("totalCount", p.getTotalCount());
			mv.addObject("totalPage", p.getTotalPage());
			mv.addObject("page", p);

		} catch (Exception e) {
			logger.info("查询异常。。。");
		}

		return mv;
	}

	/**
	 * <p>
	 * Description:导出账户转可提明细记录<br />
	 * </p>
	 * @author 胡建盼
	 * @version 0.1 2014年8月14日
	 * @param request
	 * @param response void
	 */
	@RequestMapping(value = "/exportRurnDrawRecord")
	public void exportRurnDrawRecord(HttpServletRequest request, HttpServletResponse response) {
		try {
			String startTime = request.getParameter("startTime");
			String endTime = request.getParameter("endTime");
			ShiroUser shiroUser = currentUser();
			if (shiroUser == null) {
				return;
			}
			CashRecordCnd cashRecordCnd = new CashRecordCnd();
			List<TurnDrawLogVO> list = drawLogRecordService.queryDrawListByCnd(shiroUser.getUserId(), startTime, endTime);

			Map dto = new HashMap();
			dto.put("reportTitle", "账户转可提操作报表");

			ReportData reportData = new ReportData();
			reportData.setParametersDto(dto);
			reportData.setFieldsList(list);
			reportData.setReportFilePath("/report/exportExcel/fDrawLogExcel.jasper");

			java.io.InputStream is = request.getSession().getServletContext().getResourceAsStream(reportData.getReportFilePath());
			// 这里可以传入pdf,excel,word,html,print导出各种类型文件
			JasperExportUtils.export(reportData.getFieldsList(), reportData.getParametersDto(), "excel", is, request, response, "账户转可提操作报表" + DateUtils.format(new Date(), DateUtils.YMD));
		} catch (Exception e) {
			logger.info("查询异常。。。");
		}
	}

	/**
	 * <p>
	 * Description:发送手机验证码<br />
	 * </p>
	 * @author chenlu
	 * @version 0.1 2014年8月31日
	 * @param request
	 * @param session
	 * @return String
	 */
	// 判断是否登录 
	@RequiresAuthentication
	@RequestMapping(value = "/sendMsg")
	@ResponseBody
	public MessageBox sendMsg(HttpServletRequest request, HttpSession session) {
		ShiroUser shiroUser = currentUser();
		try {
			MobileApproVo mobileApproVo = mobileApproService.queryMobileApproByUserId(shiroUser.getUserId());
			String result = mobileApproService.sendMobileApprValidate(mobileApproVo.getMobileNum(), request, shiroUser.getUserName(), BusinessConstants.BANK_INFO_MODIFY_FUNCTION,
					BusinessConstants.SMS_TEMPLATE_TYPE_ADD_BANKCARD_CODE);

			if (BusinessConstants.SUCCESS.equals(result)) {
				return new MessageBox("1", "发送验证码成功！");
			} else {
				return new MessageBox("0", result);
			}
		} catch (Exception e) {
			logger.error(e.getStackTrace());
			return new MessageBox("0", "发送验证码失败，请稍后再试！");
		}

	}

	/**
	 * <p>
	 * Description:检查手机验证码输入是否正确<br />
	 * </p>
	 * @author chenlu
	 * @version 0.1 2014年8月31日
	 * @param request
	 * @param session
	 * @return String
	 */
	@RequiresAuthentication
	// 判断是否登录 
	@RequestMapping(value = "/checkMobileMsg")
	@ResponseBody
	public MessageBox checkMobileMsg(HttpServletRequest request, HttpSession session) {
		String result = BusinessConstants.SUCCESS;
		try {
			String activeCode = request.getParameter("activeCode");

			MemberVo memberVo = new MemberVo();
			try {
				ShiroUser shiroUser = currentUser();
				MemberCnd memberCnd = new MemberCnd();
				memberCnd.setId(shiroUser.getUserId());
				memberVo = memberService.queryMemberByCnd(memberCnd);
				MobileApproVo mobileApproVo = mobileApproService.queryMobileApproByUserId(shiroUser.getUserId());
				result = mobileApproService.verifyMobileCodeBeforeAddBankCard(memberVo, mobileApproVo.getMobileNum(), activeCode, request, BusinessConstants.BANK_INFO_MODIFY_FUNCTION,
						BusinessConstants.SMS_TEMPLATE_TYPE_ADD_BANKCARD_SUCCESS);
			} catch (AppException ae) {
				result = ae.getMessage();
			} catch (Exception e) {
				result = "网络连接异常，请刷新页面或稍后重试!";
				e.printStackTrace();
			}
			if (BusinessConstants.SUCCESS.equals(result)) {
				return new MessageBox("1", "短信认证成功！");
			}

		} catch (Exception e) {
			logger.error(e.getStackTrace());
			return new MessageBox("0", "验证时出现异常！");

		}
		return new MessageBox("0", result);
	}

}
