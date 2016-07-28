package com.cxdai.portal.newP.controller;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cxdai.common.Dictionary;
import com.cxdai.portal.base.BaseController;
import com.cxdai.portal.borrow.service.BorrowService;
import com.cxdai.portal.borrow.service.TendRecordService;
import com.cxdai.portal.borrow.vo.BorrowVo;
import com.cxdai.portal.fix.service.FixBorrowService;
import com.cxdai.portal.fix.vo.FixBorrowVo;
import com.cxdai.portal.lottery.service.LotteryChanceInfoService;
import com.cxdai.portal.lottery.service.LotteryUseRecordService;
import com.cxdai.portal.member.service.MemberService;
import com.cxdai.portal.member.vo.MemberApproVo;
import com.cxdai.portal.newP.service.NewPService;
import com.cxdai.portal.statics.BusinessConstants;
import com.cxdai.portal.transfer.service.TransferService;
import com.cxdai.security.ShiroUser;

/***
 * 
 * <p>
 * Description: 新手专区 业务处理 <br />
 * </p>
 * 
 * @title NewPeopleController.java
 * @package com.cxdai.portal.newP.controller
 * @author HuangJun /newPeopleAreaUrl/newPeopleArea/newPeopleArea_xinshoubiao
 * @version 0.1 2015年4月15日
 */
@Controller
@RequestMapping(value = "/newPeopleArea")
public class NewPeopleController extends BaseController {

	@Autowired
	private NewPService newPService;

	@Autowired
	private BorrowService borrowService;

	@Autowired
	private LotteryChanceInfoService lotteryChanceInfoService;
	@Autowired
	private LotteryUseRecordService lotteryUseRecordService;
	@Autowired
	MemberService memberService;

	@Autowired
	private TendRecordService tenderRecordService;

	@Autowired
	private TransferService transferService;
	
	@Autowired
	private FixBorrowService fixBorrowService;

	/**
	 * 
	 * <p>
	 * Description:新手专区主页header.jsp首次跳转到这里<br />
	 * </p>
	 * 
	 * @author HuangJun
	 * @version 0.1 2015年4月8日
	 * @return
	 * @throws Exception
	 *             ModelAndView
	 */
	@RequestMapping(value = "/newPeopleAreaBiao")
	public ModelAndView newPFirst(HttpServletRequest request) throws Exception {
		extendLinkSource();
		ModelAndView mv = new ModelAndView("/newp/newPeoplePage");
		ShiroUser shiroUser = super.currentUser();
		if (shiroUser != null) {
			MemberApproVo memberApproVo = memberService.queryMemberApproByUserId(shiroUser.getUserId());
			mv.addObject("memberApproVo", memberApproVo);
			mv.addObject(
					"mobileChanceCount",
					lotteryChanceInfoService.selectCountByCodeAndUserId(BusinessConstants.LOTTERY_CHANCE_RULE_INFO_CODE_TYPE_NEW_REGISTER,
							shiroUser.getUserId()));
			mv.addObject("realNameChanceCount", lotteryChanceInfoService.selectCountByCodeAndUserId(
					BusinessConstants.LOTTERY_CHANCE_RULE_INFO_CODE_TYPE_REAL_NAME_APPRO, shiroUser.getUserId()));
			mv.addObject(
					"firstInvestChanceCount",
					lotteryChanceInfoService.selectCountByCodeAndUserId(BusinessConstants.LOTTERY_CHANCE_RULE_INFO_CODE_TYPE_FIRST_INVEST,
							shiroUser.getUserId()));
			// 查询该用户的投资总金额
			BigDecimal totalInvestAccount = lotteryChanceInfoService.queryInvestTotalByUserId(shiroUser.getUserId());
			if (totalInvestAccount.compareTo(BigDecimal.ZERO) == 0) {
				mv.addObject("totalInvestAccount", BigDecimal.ZERO);
			} else {
				mv.addObject("totalInvestAccount", totalInvestAccount);
			}

			// 是否新手
			boolean isNewShou = true;
			if (tenderRecordService.getTenderPowderCountByUserId(shiroUser.getUserId()) > 0) {
				isNewShou = false;
			}
			if (transferService.querySubscribesCountByUserId(shiroUser.getUserId()) > 0) {
				isNewShou = false;
			}
			mv.addObject("isNewShou", isNewShou);
		}
		// ------------------------------------

		// 新手专享，新手标
//		BorrowVo borrowVo = newPService.getAdvancedNew();
//		if (borrowVo != null) {
//			mv.addObject("borrowVo", borrowVo);
//			mv.addObject("scheduleStr", borrowVo.getScheduleStrNoDecimal());
//		}
		// 新增新手宝记录
		FixBorrowVo fixBorrowNew=null;
		try {
			fixBorrowNew = fixBorrowService.getNewFixBorrow();
			if (fixBorrowNew != null) {
				mv.addObject("fixBorrowNew", fixBorrowNew);
				mv.addObject("scheduleStr", fixBorrowNew.getScheduleStrNoDecimal());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		mv.addObject("source", request.getParameter("source"));
		return mv;
	}

	/**
	 * 
	 * <p>
	 * Description:处理外部链接推广来源注册<br />
	 * </p>
	 * 
	 * @author 胡建盼
	 * @version 0.1 2014年9月1日
	 * @param request
	 *            void
	 */
	private void extendLinkSource() {
		String extendLinkSourceName = (currentRequest().getParameter("source") == null) ? "39" : currentRequest().getParameter("source");// 链接来源
		String tid = currentRequest().getParameter("tid");// 如果来源于易瑞通，则为必有参数
		if (null != tid) {
			currentRequest().getSession().setAttribute("tid", tid);
		}
		if (!StringUtils.isEmpty(extendLinkSourceName) && StringUtils.isEmpty(currentRequest().getSession().getAttribute("linkSourceValue"))) {
			String linkSourceValue = Dictionary.getValue(1100, extendLinkSourceName.trim());
			currentRequest().getSession().setAttribute("linkSourceValue", linkSourceValue);
		}
	}

}
