package com.cxdai.portal.account.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cxdai.common.page.Page;
import com.cxdai.portal.account.service.RechargeRecordService;
import com.cxdai.portal.account.vo.RechargeRecordCnd;
import com.cxdai.portal.base.BaseController;
import com.cxdai.portal.statics.BusinessConstants;
import com.cxdai.security.ShiroUser;

/**
 * <p>
 * Description:充值记录控制类<br />
 * </p>
 * 
 * @title RechargeRecordController.java
 * @package com.cxdai.portal.account.controller
 * @author justin.xu
 * @version 0.1 2014年6月4日
 */
@Controller
@RequestMapping(value = "/myaccount/rechargeRecord")
public class RechargeRecordController extends BaseController {

	@Autowired
	private RechargeRecordService rechargeRecordService;

	/**
	 * <p>
	 * Description:查询当前用户的充值记录.<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年6月4日
	 * @param request
	 * @param id
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/queryPageList/{pageNo}")
	public ModelAndView queryPageList(HttpServletRequest request,
			@PathVariable Integer pageNo) throws Exception {
		ModelAndView mv = new ModelAndView(
				"account/recharge/rechargeRecordList");
		ShiroUser shiroUser = currentUser();
		RechargeRecordCnd rechargeRecordCnd = new RechargeRecordCnd();
		rechargeRecordCnd.setUserId(shiroUser.getUserId());
		Page page = rechargeRecordService.queryPageListByCnd(rechargeRecordCnd,
				new Page(pageNo, BusinessConstants.DEFAULT_PAGE_SIZE));
		mv.addObject("page", page);
		return mv;
	}
}
