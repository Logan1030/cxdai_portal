package com.cxdai.portal.account.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cxdai.portal.account.service.MyAccountService;
import com.cxdai.portal.account.vo.MyAccountApproMsgVo;
import com.cxdai.portal.base.BaseController;
import com.cxdai.portal.member.service.BankInfoService;
import com.cxdai.portal.member.service.EmailApprService;
import com.cxdai.portal.member.service.MemberService;
import com.cxdai.portal.member.service.MobileApproService;
import com.cxdai.portal.member.service.RealNameApproService;
import com.cxdai.portal.member.service.VIPApproService;
import com.cxdai.portal.member.vo.BankInfoVo;
import com.cxdai.portal.member.vo.EmailApproVo;
import com.cxdai.portal.member.vo.MemberCnd;
import com.cxdai.portal.member.vo.MemberVo;
import com.cxdai.portal.member.vo.MobileApproVo;
import com.cxdai.portal.member.vo.RealNameApproVo;
import com.cxdai.portal.statics.BusinessConstants;
import com.cxdai.security.ShiroUser;

/**
 * <p>
 * Description:账户安全中心<br />
 * </p>
 * 
 * @title AccountSafetyCentreController.java
 * @package com.cxdai.portal.account.controller
 * @author hujianpan
 * @version 0.1 2014年8月26日
 */
@Controller
@RequestMapping(value = "/AccountSafetyCentre")
public class AccountSafetyCentreController extends BaseController {

	public Logger logger = Logger.getLogger(AccountSafetyCentreController.class);

	@Autowired
	private MyAccountService myAccountServiceImpl;
	@Autowired
	private RealNameApproService realNameApproService;
	@Autowired
	private EmailApprService emailApprService;
	@Autowired
	private MobileApproService mobileApproService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private VIPApproService vipApproService;
	@Autowired
	private BankInfoService bankInfoService;

	/**
	 * <p>
	 * Description:进入账户安全中心主页面<br />
	 * </p>
	 * 
	 * @author 胡建盼
	 * @version 0.1 2014年8月26日
	 * @param request
	 * @return
	 * @throws Exception
	 *             ModelAndView
	 */
	@RequiresAuthentication
	@RequestMapping(value = "/safetyIndex")
	public ModelAndView show(HttpServletRequest request) throws Exception {
		MyAccountApproMsgVo myAccountApproMsgVo = new MyAccountApproMsgVo();
		ModelAndView mav = new ModelAndView("/account/safe/safetyCentre");
		ShiroUser shiroUser = currentUser();
		// 没有登入
		if (null == shiroUser) {
			forword(BusinessConstants.TOP_HOME_ADDRESS);
		}
		Integer memberId = shiroUser.getUserId();

		/*
		 * // 验证用户的认证信息是否通过（包含邮箱、实名、手机认证） myAccountApproMsgVo =
		 * myAccountServiceImpl .validateAccountAppro(memberId); if
		 * (!myAccountApproMsgVo.getResult().equals("success")) { // 保存错误信息返回到前台
		 * mav.addObject("myAccountApproMsgVo", myAccountApproMsgVo); return
		 * mav; }
		 */

		MemberCnd memberCnd = new MemberCnd();
		memberCnd.setId(memberId);
		MemberVo memberVo = memberService.queryMemberByCnd(memberCnd);
		RealNameApproVo realNameApproVo = realNameApproService.queryRealNameApproByUserId(memberId);
		EmailApproVo emailApproVo = emailApprService.queryEmailApproByUserId(memberId);
		// 手机认证
		MobileApproVo mobileAppro = mobileApproService.queryMobileApproByUserId(memberId);
		// VIPApproVo vipApproVo =
		// vipApproService.queryVIPApproByUserId(memberId);
		// 当前用户银行卡信息
		BankInfoVo currentBankCardVo = bankInfoService.getUserCurrentCard(shiroUser.getUserId());
		mav.addObject("realNameApproVo", realNameApproVo);
		mav.addObject("emailApproVo", emailApproVo);
		mav.addObject("mobileAppro", mobileAppro);
		// mav.addObject("myAccountApproMsgVo", myAccountApproMsgVo);
		mav.addObject("memberVo", memberVo);
		// mav.addObject("vipApproVo", vipApproVo);
		mav.addObject("currentBankCardVo", currentBankCardVo);
		return mav;
	}

	/**
	 * 
	 * <p>
	 * Description:进入重置登入密码界面<br />
	 * </p>
	 * 
	 * @author 胡建盼
	 * @version 0.1 2014年9月17日
	 * @return ModelAndView
	 */
	@RequiresAuthentication
	@RequestMapping(value = "/safetyCentre/enterResetLoginPwd")
	public ModelAndView enterResetLoginsPwd() {
		return new ModelAndView("/account/safe/modifyLoginPwd");
	}

	/**
	 * 
	 * <p>
	 * Description:进入找回登入密码界面<br />
	 * </p>
	 * 
	 * @author 胡建盼
	 * @version 0.1 2014年9月17日
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/safetyCentre/enterFindLoginPwd")
	public ModelAndView enterFindLoginsPwd() {

		return new ModelAndView("/account/safe/findLoginPwd");
	}

	/**
	 * 
	 * <p>
	 * Description:进入重置交易密码界面<br />
	 * </p>
	 * 
	 * @author 胡建盼
	 * @version 0.1 2014年9月17日
	 * @return ModelAndView
	 */
	@RequiresAuthentication
	@RequestMapping(value = "/safetyCentre/enterResetTransactionPwd")
	public ModelAndView enterResetPwd() {

		return new ModelAndView("/account/safe/modifyPayPwd");
	}

	/**
	 * 
	 * <p>
	 * Description:进入找回交易密码界面<br />
	 * </p>
	 * 
	 * @author 胡建盼
	 * @version 0.1 2014年9月17日
	 * @return ModelAndView
	 */
	@RequiresAuthentication
	@RequestMapping(value = "/safetyCentre/enterFindTransactionPwd")
	public ModelAndView enterfindTransactionPwd() {

		return new ModelAndView("/account/safe/findPayPwd");
	}
	/**
	 *  
	 * <p>
	 * Description:进入手机找回页面<br />
	 * </p>
	 * @author yubin
	 * @version 0.1 2015年9月24日
	 * @return
	 * ModelAndView
	 */
	@RequestMapping(value = "/safetyCentre/findPhone")
	public ModelAndView enterFindLoginsPwdByPhone() {

		return new ModelAndView("/account/safe/findPhone");
	}
	 

}
