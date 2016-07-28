package com.cxdai.portal.account.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cxdai.common.page.Page;
import com.cxdai.portal.account.service.AccountService;
import com.cxdai.portal.account.vo.SearchInvestCnd;
import com.cxdai.portal.base.BaseController;
import com.cxdai.portal.borrow.service.BorrowService;
import com.cxdai.portal.borrow.service.TendRecordService;
import com.cxdai.portal.invest.service.InvestRecordService;
import com.cxdai.portal.member.service.MemberService;
import com.cxdai.portal.statics.BusinessConstants;

@Controller
public class AllTenderController extends BaseController {

	private final static Logger logger = Logger.getLogger(AllTenderController.class);

	@Autowired
	private BorrowService borrowService;

	@Autowired
	private AccountService accountService;

	@Autowired
	private TendRecordService tendRecordService;
	@Autowired
	private MemberService memberService;

	@Autowired
	private InvestRecordService investRecordService;

	/**
	 * <p>
	 * Description:分页查询投标中的借款标<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年8月13日
	 * @param request
	 * @param pageNo
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/quanbutoubiao")
	public ModelAndView forPledgebid(HttpServletRequest request, Integer pageNo) {
		ModelAndView view = new ModelAndView("/borrow/alltenderList");
		return view;
	}

	/**
	 * <p>
	 * Description:我要投标列表<br />
	 * </p>
	 * 
	 * @author chenlu
	 * @version 0.1 2014年8月19日
	 * @param request
	 * @return ModelAndView
	 */
	@RequestMapping(value = "queryalltenderList")
	public ModelAndView queryInvestList(@RequestParam Integer pageNum, @ModelAttribute SearchInvestCnd seach) {
		ModelAndView mv = new ModelAndView("borrow/alltenderDataPage");
		Page p = new Page(pageNum, BusinessConstants.DEFAULT_PAGE_SIZE);
		try {
			p = investRecordService.selectAllTenderBorrowConstainTransfer(seach, p);
		} catch (Exception e) {
			logger.error("进入我要投标出错");
			e.printStackTrace();
		}
		mv.addObject("page", p);
		return mv;
	}


}
