package com.cxdai.portal.member.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.cxdai.base.entity.Bank;
import com.cxdai.base.entity.BankInfo;
import com.cxdai.base.entity.BankinfoLog;
import com.cxdai.common.statics.Constants;
import com.cxdai.portal.account.service.AccountService;
import com.cxdai.portal.account.vo.AccountVo;
import com.cxdai.portal.base.BaseController;
import com.cxdai.portal.base.MessageBox;
import com.cxdai.portal.lianlian.service.LianlianpayWapService;
import com.cxdai.portal.lianlian.vo.LlWapBankcardInfo;
import com.cxdai.portal.member.service.BankInfoService;
import com.cxdai.portal.member.service.BankcardChangeService;
import com.cxdai.portal.member.service.MemberService;
import com.cxdai.portal.member.service.MobileApproService;
import com.cxdai.portal.member.service.RealNameApproService;
import com.cxdai.portal.member.vo.BankInfoVo;
import com.cxdai.portal.member.vo.BankcardChange;
import com.cxdai.portal.member.vo.BankcardPic;
import com.cxdai.portal.member.vo.MemberApproVo;
import com.cxdai.portal.member.vo.MemberCnd;
import com.cxdai.portal.member.vo.MemberVo;
import com.cxdai.portal.member.vo.MobileApproVo;
import com.cxdai.portal.member.vo.RealNameApproVo;
import com.cxdai.portal.statics.BusinessConstants;
import com.cxdai.portal.util.JsonUtils;
import com.cxdai.security.ShiroUser;
import com.cxdai.utils.HttpTookit;

/**
 * <p>
 * Description:银行卡管理Controller<br />
 * </p>
 * 
 * @title BankInfoController.java
 * @package com.cxdai.portal.member.controller
 * @author justin.xu
 * @version 0.1 2014年6月19日
 */
@Controller
@RequestMapping(value = "/bankinfo")
public class BankInfoController extends BaseController {

	public Logger logger = Logger.getLogger(BankInfoController.class);

	@Autowired
	private BankInfoService bankInfoService;
	@Autowired
	private MobileApproService mobileApproService;
	@Autowired
	private MemberService memberService;
	@Autowired
	RealNameApproService realNameApproService;
	@Autowired
	BankcardChangeService changeService;
	@Autowired
	private LianlianpayWapService lianlianpayWapService;
	@Autowired
	private AccountService accountService;

	/**
	 * 去换卡页面
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author huangpin
	 * @version 0.1 2015年4月9日
	 * @return ModelAndView
	 */
	@RequiresAuthentication
	@RequestMapping(value = "initChangeCard")
	@ResponseBody
	public MessageBox initChangeCard() {
		String msg = "";
		try {
			msg = changeService.bankinfoCheck(currentUser().getUserId());
			if (msg == null) {
				return MessageBox.build("1", "/bankinfo/toChangeCard.html");
			}
		} catch (Exception e) {
			logger.error("去换卡页面异常", e);
		}
		return MessageBox.build("0", msg);
	}

	@RequiresAuthentication
	@RequestMapping(value = "toChangeCard")
	public ModelAndView toChangeCard() {
		return new ModelAndView("/account/bankinfo/changeBankCard");
	}

	/**
	 * 换卡-下一步
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author huangpin
	 * @version 0.1 2015年4月9日
	 * @param change
	 * @return MessageBox
	 */
	@RequiresAuthentication
	@RequestMapping(value = "subChange")
	@ResponseBody
	public MessageBox subChange(BankcardChange change) {

		if (!StringUtils.isEmpty(change.getUpdateReason()) && "其他原因".equals(change.getUpdateReason())) {
			AccountVo accountVo = null;
			try {
				accountVo = accountService.queryAccountByUserId(currentUser().getUserId());
			} catch (Exception e) {
				return MessageBox.build("0", "网络异常！");
			}
			if (null == accountVo) {
				return MessageBox.build("0", "账户异常，请联系客服！");
			}
			if (accountVo.getTotal().doubleValue() <= 100) {
				if (StringUtils.isEmpty(change.getRemark())) {
					return MessageBox.build("0", "请在备注中说明换卡原因！");
				}
			} else {
				return MessageBox.build("0", "请保持账户资产在100元（含）以内，再进行更换卡！");
			}
		}
		try {
			MessageBox msg = changeService.addCheckErrorLog(change, currentUser(), HttpTookit.getRealIpAddr(currentRequest()));
			if (msg != null) {
				return msg;
			}
		} catch (Exception e) {
			logger.error("提交银行卡更换信息第一步异常", e);
			return MessageBox.build("0", "提交异常");
		}
		change.setAddIp(HttpTookit.getRealIpAddr(currentRequest()));
		change.setUserId(currentUser().getUserId());
		currentSession().setAttribute("updateReason", change.getUpdateReason());
		currentSession().setAttribute("tempChange", change);
		currentSession().setAttribute("tempChangePics", new ArrayList<BankcardPic>());
		return MessageBox.build("1", "/bankinfo/initUploadPics");
	}

	/**
	 * 换卡-去上传资料页面
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author huangpin
	 * @version 0.1 2015年4月9日
	 * @return ModelAndView
	 */
	@RequiresAuthentication
	@RequestMapping(value = "initUploadPics")
	public ModelAndView initUploadPics() {
		return new ModelAndView("account/bankinfo/uploadPics").addObject("pics", currentSession().getAttribute("tempChangePics"));
	}

	/**
	 * <p>
	 * Description:加载银行卡资料信息<br />
	 * </p>
	 * 
	 * @author 邹理享
	 * @version 0.1 2015年4月29日
	 * @return ModelAndView
	 */
	@RequiresAuthentication
	@RequestMapping(value = "editInitUploadPics")
	public ModelAndView editInitUploadPics(@RequestParam(value = "isEdit", required = false) Integer isEdit) {

		if (isEdit != null && isEdit == 0) {
			// 如果是点击修改链接 先清空内存tempChangePics
			currentSession().removeAttribute("tempChangePics");
			currentSession().removeAttribute("waitDeleteIds");
		}

		// 如果是临时新增或删除 那么 直接返回
		if (currentSession().getAttribute("tempChangePics") != null) {
			return new ModelAndView("account/bankinfo/editUploadPics").addObject("pics", currentSession().getAttribute("tempChangePics"));
		}

		// 资料重新上传
		List<BankcardPic> pics = changeService.queryBankcardPicsByUserId(currentUser().getUserId());

		if (pics.isEmpty()) {
			logger.error("editUploadPics---" + currentUser().getUserId() + "没有获取到上传的银行卡资料信息");
			return forword(BusinessConstants.NO_PAGE_FOUND_404);
		}

		currentSession().removeAttribute("tempChangePics");
		currentSession().removeAttribute("tempChange");
		currentSession().setAttribute("tempChangePics", pics);
        
		String updateReason = changeService.getReasonByUserId(currentUser().getUserId());
		currentSession().setAttribute("updateReason", updateReason);
		
		// 加载银行卡变动信息
		BankcardChange change = new BankcardChange();
		BankcardPic bankcardPic = pics.get(0);
		Integer bcId = bankcardPic.getBcId();
		change.setId(bcId);
		change.setAddIp(HttpTookit.getRealIpAddr(currentRequest()));
		change.setUserId(currentUser().getUserId());
		currentSession().setAttribute("tempChange", change);

		return new ModelAndView("account/bankinfo/editUploadPics").addObject("pics", pics);
	}



	/**
	 * <p>
	 * Description:删除银行卡<br />
	 * </p>
	 * 
	 * @author 邹理享
	 * @version 0.1 2015年4月29日
	 * @return MessageBox
	 */
	@SuppressWarnings("unchecked")
	@RequiresAuthentication
	@RequestMapping(value = "editRemovePic/{id}")
	public ModelAndView editRemovePic(@PathVariable("id") int index) {
		try {


			List<BankcardPic> pics = (List<BankcardPic>) currentSession().getAttribute("tempChangePics");

			BankcardPic bankcardPic = pics.get(index);
			
			if (bankcardPic.getId() != null) {
				// 数据库删掉
				// changeService.removePicById(bankcardPic.getId());
				Object object = currentSession().getAttribute("waitDeleteIds");
				if (object == null) {
					List<Integer> ids = new ArrayList<Integer>();
					ids.add(bankcardPic.getId());
					currentSession().setAttribute("waitDeleteIds", ids);
				} else {
					List<Integer> ids = (List<Integer>) object;
					ids.add(bankcardPic.getId());
				}

			}
				// 后来新增 直接在内存中删掉
			pics.remove(index);

			return new ModelAndView("account/bankinfo/editUploadPics").addObject("pics", pics);
		} catch (Exception e) {
			logger.error("editRemovePic---换卡-提交审核异常", e);
			return forword(BusinessConstants.NO_PAGE_FOUND_404);
		}

	}

	/**
	 * <p>
	 * Description:重新补充提交银行卡资料<br />
	 * </p>
	 * 
	 * @author 邹理享
	 * @version 0.1 2015年4月29日
	 * @return MessageBox
	 */
	@SuppressWarnings("unchecked")
	@RequiresAuthentication
	@RequestMapping(value = "editPics")
	@ResponseBody
	public MessageBox editPics() {
		try {
			List<BankcardPic> pics = (List<BankcardPic>) currentSession().getAttribute("tempChangePics");
			BankcardChange change = (BankcardChange) currentSession().getAttribute("tempChange");
			List<Integer> waitDeleteIds = (List<Integer>) currentSession().getAttribute("waitDeleteIds");
			MessageBox mb = changeService.saveEditPics(waitDeleteIds, change, pics, currentUser(), HttpTookit.getRealIpAddr(currentRequest()));
			if(!mb.getCode().equals("1")){
				return mb;
			}
			currentSession().removeAttribute("tempChange");
			currentSession().removeAttribute("tempChangePics");
			currentSession().removeAttribute("waitDeleteIds");
			
			return mb;
		} catch (Exception e) {
			logger.error("换卡-提交审核异常", e);
		}
		return null;
	}

	/**
	 * 换卡-上传图片
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author huangpin
	 * @version 0.1 2015年4月9日
	 * @param files
	 * @param picType
	 * @return String
	 */
	@RequestMapping(value = "uploadPics", produces = { "text/plain;charset=UTF-8" })
	@ResponseBody
	@RequiresAuthentication
	public String uploadPics(@RequestParam("files") MultipartFile[] files, @RequestParam String picType) {
		MessageBox msg = null;
		try {
			BankcardChange change = (BankcardChange) currentSession().getAttribute("tempChange");
			if (change == null) {
				msg = MessageBox.build("2", "请填写更换申请");
			} else {
				msg = changeService.addPics(files, change, picType, currentRequest(), currentUser().getUserId());
			}
		} catch (Exception e) {
			logger.error("资料上传异常", e);
			msg = MessageBox.build("0", "资料上传异常");
		}
		return JsonUtils.bean2Json(msg);
	}

	/**
	 * 换卡-删除图片
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author huangpin
	 * @version 0.1 2015年4月9日
	 * @param id
	 * @return ModelAndView
	 */
	@RequiresAuthentication
	@RequestMapping(value = "removePic/{id}")
	public ModelAndView removePic(@PathVariable("id") int id) {
		List<BankcardPic> pics = (List<BankcardPic>) currentSession().getAttribute("tempChangePics");
		pics.remove(id);
		currentSession().setAttribute("tempChangePics", pics);
		return new ModelAndView("account/bankinfo/uploadPics").addObject("pics", pics);
	}

	/**
	 * 换卡-提交审核
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author huangpin
	 * @version 0.1 2015年4月9日
	 * @return MessageBox
	 */
	@RequiresAuthentication
	@RequestMapping(value = "addChange")
	@ResponseBody
	public MessageBox addChange() {
		try {
			List<BankcardPic> pics = (List<BankcardPic>) currentSession().getAttribute("tempChangePics");
			BankcardChange change = (BankcardChange) currentSession().getAttribute("tempChange");
			MessageBox mb = changeService.add(change, pics, currentUser(), HttpTookit.getRealIpAddr(currentRequest()));
		    //如果錯誤，則不刪除緩存
			if(!mb.getCode().equals("1")){
				return mb;
			}
			currentSession().removeAttribute("tempChange");
			currentSession().removeAttribute("tempChangePics");
			return mb;
		} catch (Exception e) {
			logger.error("换卡-提交审核异常", e);
		}
		return null;
	}


	/**
	 * <p>
	 * Description:进入银行卡管理页面<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年6月19日
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception ModelAndView
	 */
	@RequiresAuthentication
	@RequestMapping(value = "toBankCard")
	public ModelAndView getcash(HttpServletRequest request, HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView("account/bankinfo/bankInfoIndex");
		ShiroUser shiroUser = currentUser();
		MemberCnd memberCnd = new MemberCnd();
		memberCnd.setId(shiroUser.getUserId());
		MemberVo memberVo = memberService.queryMemberByCnd(memberCnd);

		String token = System.currentTimeMillis() + new Random().nextInt() + "";
		request.getSession().setAttribute("token", token);
		mav.addObject("c_token", token);

		// 查询用户认证信息
		MemberApproVo memberApproVo = memberService.queryMemberApproByUserId(shiroUser.getUserId());
		// 您还没有进行手机认证，请先进行手机认证
		if (null == memberApproVo.getMobilePassed() || memberApproVo.getMobilePassed() != Constants.YES) {
			mav.addObject("errorCode", "-3");
			return mav;
		}
		// 判断是否通过了实名认证
		if (null == memberApproVo.getNamePassed() || memberApproVo.getNamePassed() != Constants.REALNAME_APPR_ISPASSED_PASSED) {
			mav.addObject("errorCode", "-1");
			return mav;
		}

		// 如果交易密码为空，则跳到交易密码页面
		if (null == memberVo.getPaypassword() || "".equals(memberVo.getPaypassword())) {
			mav.addObject("errorCode", "-4");
			return mav;
		}

		// 当前用户银行卡信息
		List<BankInfoVo> list = bankInfoService.queryBankInfosByUserId(shiroUser.getUserId());
		// mav.addObject("bankInfos", list);
		if (list != null && list.size() > 0)
			mav.addObject("bc", list.get(0));

		// 查询银行卡数量；
		int cardNum = bankInfoService.querytBankCardNum(shiroUser.getUserId());
		mav.addObject("cardNum", cardNum);

		// 查询银行卡信息日志表-用户锁定的记录
		BankinfoLog bankinfoLog = bankInfoService.querytBankCardLogLock(shiroUser.getUserId());
		mav.addObject("bankinfoLog", bankinfoLog);

		String blAddTime = "";
		if (bankinfoLog != null) {
			blAddTime = DateFormatUtils.format(bankinfoLog.getAddTime(), "yyyy-MM-dd HH:mm:ss");
		}

		mav.addObject("blAddTime", blAddTime);

		return mav;
	}

	@RequiresAuthentication
	@RequestMapping(value = "vaild")
	@ResponseBody
	public MessageBox vaild() {
		ShiroUser shiroUser = currentUser();

		try {
			// 如果交易密码为空，则跳到交易密码页面
			MemberCnd memberCnd = new MemberCnd();
			memberCnd.setId(shiroUser.getUserId());
			MemberVo memberVo = memberService.queryMemberByCnd(memberCnd);
			if (null == memberVo.getPaypassword() || "".equals(memberVo.getPaypassword())) {
				return MessageBox.build("-1", "请先设置交易密码.");
			}

			// 您还没有进行手机认证，请先进行手机认证
			MemberApproVo memberApproVo = memberService.queryMemberApproByUserId(shiroUser.getUserId());
			if (null == memberApproVo.getMobilePassed() || memberApproVo.getMobilePassed() != Constants.YES) {
				return MessageBox.build("-2", "请先进行手机认证.");
			}

			// 判断是否通过了实名认证
			if (null == memberApproVo.getNamePassed() || memberApproVo.getNamePassed() != Constants.REALNAME_APPR_ISPASSED_PASSED) {
				return MessageBox.build("-3", "请先进行实名认证.");
			}
		} catch (Exception e) {
			return MessageBox.build("0", "网络连接异常，请刷新页面或稍后重试！");
		}

		return MessageBox.build("1", "success");
	}

	/**
	 * <p>
	 * Description:保存银行卡信息<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年6月24日
	 * @param bankInfo
	 * @param payPassword
	 * @param request
	 * @return String
	 */
	@RequestMapping(value = "saveBankcard")
	public @ResponseBody
	String saveBankcard(BankInfo bankInfo, String payPassword, HttpServletRequest request) {
		String result = "success";
		try {
			String activeCode = request.getParameter("activeCode");
			ShiroUser shiroUser = currentUser();
			MemberCnd memberCnd = new MemberCnd();
			memberCnd.setId(shiroUser.getUserId());

			bankInfo.setUserId(currentUser().getUserId());
			result = bankInfoService.saveBankcard(bankInfo, payPassword, activeCode);
			if (!"success".equals(result)) {
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "网络连接异常，请刷新页面或稍后重试";
		}
		return result;
	}

	/**
	 * <p>
	 * Description:修改银行卡信息根据当前用户的手机号发送验证码<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年4月30日
	 * @param request
	 * @param session
	 * @return String
	 */
	@RequestMapping(value = "sendMsg")
	@ResponseBody
	public MessageBox sendResetMsg(HttpServletRequest request, HttpSession session) {
		try {
			ShiroUser shiroUser = currentUser();
			MemberCnd memberCnd = new MemberCnd();
			memberCnd.setId(shiroUser.getUserId());
			MemberVo memberVo = memberService.queryMemberByCnd(memberCnd);
			MobileApproVo mobileApproVo = mobileApproService.queryMobileApproByUserId(shiroUser.getUserId());

			String phone = request.getParameter("phone");
			if (phone == null || "".equals(phone)) {
				return MessageBox.build("0", "请输入手机号!");
			}
			if (StringUtils.isNotEmpty(phone) && !mobileApproVo.getMobileNum().equals(phone.trim())) {
				return MessageBox.build("0", "输入手机号与绑定手机不一致!");
			}

			String result = bankInfoService.sendBankInfoValidate(mobileApproVo.getMobileNum(), request, memberVo, BusinessConstants.BANK_INFO_MODIFY_FUNCTION);
			return MessageBox.build("success".equals(result) ? "1" : "0", result);
		} catch (Exception e) {
			return MessageBox.build("0", "网络连接异常，请刷新页面或稍后重试！");
		}
	}

	@RequestMapping(value = "addBankCard")
	public ModelAndView addBankCard() throws Exception {
		ModelAndView mav = new ModelAndView("account/addBankCard");
		ShiroUser shiroUser = currentUser();
		// 查询用户认证信息
		MemberApproVo memberApproVo = memberService.queryMemberApproByUserId(shiroUser.getUserId());
		if ((memberApproVo == null || memberApproVo.getEmailChecked() == null || memberApproVo.getEmailChecked() != 1)
				&& (memberApproVo.getMobilePassed() == null || memberApproVo.getMobilePassed() != 1)) {
			return redirect("/AccountSafetyCentre/safetyIndex.html");
		}
		// 判断是否通过了实名认证
		if (null == memberApproVo.getNamePassed() || memberApproVo.getNamePassed() != Constants.REALNAME_APPR_ISPASSED_PASSED) {
			return redirect("/account/approve/realname/toRealNameAppro.html");
		}
		// // 当前用户银行卡信息
		// List<BankInfoVo> list =
		// bankInfoService.queryBankInfosByUserId(shiroUser.getUserId());
		// mav.addObject("bankInfos", list);
		// 实名认证
		RealNameApproVo realNameAppro = realNameApproService.queryRealNameApproByUserId(shiroUser.getUserId());
		mav.addObject("realNameAppro", realNameAppro);
		return mav;
	}

	@RequestMapping(value = "isPhoneValidated")
	@ResponseBody
	public String isPhoneValidated() throws Exception {
		String result = "yes";
		try {
			ShiroUser shiroUser = currentUser();
			// 手机认证
			MobileApproVo mobileAppro = mobileApproService.queryMobileApproByUserId(shiroUser.getUserId());

			if (mobileAppro == null || mobileAppro.getPassed() != 1) {
				result = "no";
			}

		} catch (Exception e) {
			result = "网络连接异常，请刷新页面或稍后重试!";
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping(value = "saveChinabank")
	@ResponseBody
	@RequiresAuthentication
	public MessageBox saveChinabank(HttpServletRequest request) {
		try {
			List<BankInfoVo> list = bankInfoService.queryBankInfosByUserId(currentUser().getUserId());
			if (list.size() > 0) {
				return MessageBox.build("0", "已存在银行卡，无法再新增！");
			}
		} catch (Exception e1) {
			logger.error("保存银行卡信息失败.", e1);
		}
		String bankCode = request.getParameter("bankCode");
		// String cnapsCode = request.getParameter("cnapsCode");
		String cardNum = request.getParameter("cardNum");
		String payPassword = request.getParameter("payPassword");
		// String branch = request.getParameter("branch");
		String activeCode = request.getParameter("activeCode");
		// Long _cnapsCode = null;
		String msg = null;
		if (StringUtils.isEmpty(bankCode) || StringUtils.isEmpty(removerSpace(bankCode))) {
			return MessageBox.build("0", "开户行不能为空！");
		} else {
			bankCode = removerSpace(bankCode);
		}
		if (StringUtils.isEmpty(cardNum) || StringUtils.isEmpty(removerSpace(cardNum))) {
			return MessageBox.build("0", "银行卡号不能为空！");
		} else if (!isNumber(cardNum)) {
			return new MessageBox("0", "银行卡号除分隔符为空格外，只能为数字！");
		} else {
			/* *************************************************************************************
			 * 去掉空格，客户在界面输入时习惯用空格作为分隔符， 已便确认 ***********************************
			 * 例如 xxxx xxxx xxxx xxxxx ====> xxxxxxxxxxxxxxxxx
			 * ************************************************
			 */
			cardNum = removerSpace(cardNum);
		}
		if (StringUtils.isEmpty(payPassword)) {
			return MessageBox.build("0", "支付密码不能为空！");
		}
		if (StringUtils.isEmpty(activeCode) || StringUtils.isEmpty(removerSpace(activeCode))) {
			return MessageBox.build("0", "手机验证码不能为空！");
		}

		try {
			LlWapBankcardInfo llWapBankcardInfo = null;
			BankInfo bankInfo = new BankInfo();
			if (currentUser().getIsFinancialUser() == 1) {
				llWapBankcardInfo = lianlianpayWapService.queryBankcardInfo(cardNum);
				if (!llWapBankcardInfo.getResultMsg().equals(BusinessConstants.SUCCESS)) {
					return MessageBox.build("0", llWapBankcardInfo.getResultMsg());
				}
				if (llWapBankcardInfo.getBank_code() == null) {
					return MessageBox.build("0", "开户行不存在！");
				}
				if (!bankCode.equals(llWapBankcardInfo.getBank_code())) {
					return MessageBox.build("0", "所选开户行与卡号所在开户行不一致！");
				}
				bankInfo.setBankCode(llWapBankcardInfo.getBank_code());
				bankInfo.setBankName(llWapBankcardInfo.getBank_name());
			} else {
				bankInfo.setBankCode(bankCode);
				List<Bank> bankList = bankInfoService.queryBankList(null);
				for (Bank vo : bankList) {
					if (bankCode.equals(vo.getBankCode())) {
						bankInfo.setBankName(vo.getBankName());
					}
				}
			}

			bankInfo.setUserId(currentUser().getUserId());
			bankInfo.setCardNum(cardNum);

			msg = bankInfoService.saveBankcard(bankInfo, payPassword, removerSpace(activeCode));
			if ("success".equals(msg)) {
				ShiroUser shiroUser = currentUser();
				MemberCnd memberCnd = new MemberCnd();
				memberCnd.setId(shiroUser.getUserId());
				MemberVo memberVo = memberService.queryMemberByCnd(memberCnd);
				MobileApproVo mobileApproVo = mobileApproService.queryMobileApproByUserId(shiroUser.getUserId());
				if (mobileApproVo == null || mobileApproVo.getPassed() == null || mobileApproVo.getPassed() == 0) {
					return MessageBox.build("0", "手机未认证.");
				}
				return MessageBox.build("1", "保存成功.");
			}
		} catch (Exception e) {
			logger.error("保存银行卡信息失败.", e);
			msg = "保存失败.";
		}
		return MessageBox.build("0", msg);
	}

	private Boolean isNumber(String sourceInput) {
		if (StringUtils.isEmpty(sourceInput)) {
			return Boolean.FALSE;
		}
		String removeSpaceStr = removerSpace(sourceInput);
		if (StringUtils.isEmpty(removeSpaceStr)) {
			return Boolean.FALSE;
		}
		Pattern p = Pattern.compile("^[0-9]*");
		Matcher m = p.matcher(removeSpaceStr);
		return m.matches();
	}

	/**
	 * <p>
	 * Description:去除各种空格，包括全角输入下的空格<br />
	 * </p>
	 * 
	 * @author hujianpan
	 * @version 0.1 2015年3月19日
	 * @param cardNum
	 * @return String
	 */
	private String removerSpace(String cardNum) {
		String input = cardNum.trim().replaceAll(" ", "").replaceAll("　", "").replaceAll("	", "");
		return input;
	}

	@RequestMapping(value = "lockBankCard")
	@ResponseBody
	@RequiresAuthentication
	// 锁定银行卡新增功能
	public MessageBox lockBankCard(HttpServletRequest request, HttpSession session) {
		return MessageBox.build("0", "无法锁定银行卡");
		/*
		 * String msg = null; ShiroUser shiroUser = currentUser(); String
		 * c_token = request.getParameter("c_token"); String s_token = (String)
		 * request.getSession().getAttribute("token"); try { if (c_token != null
		 * && s_token != null) { if (c_token.equals(s_token)) { msg =
		 * bankInfoService.insertBankCardLock(shiroUser.getUserId()); if
		 * (msg.equals("success")) { return MessageBox.build("1", "锁定成功."); }
		 * else { return MessageBox.build("0", msg); } } else { return
		 * MessageBox.build("0", "不能重复提交"); } } else { return
		 * MessageBox.build("0", "锁定失败.."); } } catch (Exception e) {
		 * logger.error("锁定银行卡操作失败.", e); msg = "锁定失败"; e.printStackTrace(); }
		 * finally { request.getSession().removeAttribute("token"); } return
		 * MessageBox.build("0", msg);
		 */
	}

	@RequestMapping(value = "/queryProvinceList")
	@ResponseBody
	public List<Bank> queryProvinceList() {
		return bankInfoService.queryProvinceList();
	}

	@RequestMapping(value = "/queryCityList")
	@ResponseBody
	public List<Bank> queryCityList(@RequestParam("province") String province) {
		if (StringUtils.isEmpty(province)) {
			return Collections.emptyList();
		}
		return bankInfoService.queryCityList(province);
	}

	@RequestMapping(value = "/queryDistrictList")
	@ResponseBody
	public List<Bank> queryDistrictList(@RequestParam("city") String city) {
		if (StringUtils.isEmpty(city)) {
			return Collections.emptyList();
		}
		return bankInfoService.queryDistrictList(city);
	}

	@RequestMapping(value = "/queryBankList")
	@ResponseBody
	public List<Bank> queryBankList(@RequestParam("district") String district) {
		// if (StringUtils.isEmpty(district)) {
		// return Collections.emptyList();
		// }
		return bankInfoService.queryBankList(district);
	}

	@RequestMapping(value = "/queryBranchList")
	@ResponseBody
	public List<Bank> queryBranchList(@RequestParam("district") String district, @RequestParam("bankCode") String bankCode, @RequestParam("branchKey") String branchKey) {
		try {
			if (StringUtils.isEmpty(district) || StringUtils.isEmpty(bankCode)) {
				return Collections.emptyList();
			}
			return bankInfoService.queryBranchList(district, bankCode, branchKey);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * <p>
	 * Description:前台银行卡删除功能，---废弃<br />
	 * </p>
	 * 
	 * @author 陈鹏
	 * @version 0.1 2014年11月25日
	 * @param
	 * @return String
	 */

	@RequestMapping(value = "removeBankCard/{id}")
	@ResponseBody
	public MessageBox removeBankCard(@PathVariable("id") Integer id, HttpServletRequest request) {
		return new MessageBox("0", "不可删除银行卡");
		/*
		 * try { ShiroUser shiroUser = currentUser(); if (shiroUser == null) {
		 * return new MessageBox("0", "请登录"); } String result =
		 * bankInfoService.saveDeleteBankinfoById(shiroUser.getUserId(),
		 * Integer.valueOf(id)); if (!result.equals(BusinessConstants.SUCCESS))
		 * { return new MessageBox("0", result); } } catch (Exception e) {
		 * logger.error("删除银行卡失败.", e); return new MessageBox("0",
		 * "网络连接异常,请刷新页面或稍后重试！"); } return new MessageBox("1", "删除银行卡成功");
		 */
	}

	@RequestMapping(value = "/queryInfoByCnapsCode")
	@ResponseBody
	public List<Bank> queryInfoByCnapsCode(@RequestParam(value = "cnapsCode", required = true) String cnapsCode) {
		if (StringUtils.isEmpty(cnapsCode)) {
			return null;
		}

		if (!StringUtils.isNumeric(cnapsCode)) {
			return null;
		}
		List<Bank> banks = bankInfoService.queryBankInfosByCnapsCode(Long.parseLong(cnapsCode));
		return banks;
	}

	@RequestMapping(value = "validateBankcard/{cardNum}")
	@ResponseBody
	public LlWapBankcardInfo validateBankcard(@PathVariable("cardNum") String cardNum, HttpServletRequest request) {
		LlWapBankcardInfo llWapBankcardInfo = null;
		try {
			llWapBankcardInfo = lianlianpayWapService.queryBankcardInfo(cardNum);
		} catch (Exception e) {
			logger.error("校验银行卡号信息异常", e);
			llWapBankcardInfo.setResultMsg("校验银行卡号信息异常");
		}
		return llWapBankcardInfo;
	}
}
