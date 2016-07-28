package com.cxdai.portal.first.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cxdai.common.page.Page;
import com.cxdai.common.statics.Constants;
import com.cxdai.portal.base.BaseController;
import com.cxdai.portal.first.service.FirstTenderDetailService;
import com.cxdai.portal.first.vo.FirstTenderDetailCnd;
import com.cxdai.portal.statics.BusinessConstants;
import com.cxdai.security.ShiroUser;

/**
 * <p>
 * Description:优先投标计划开通明细Controller<br />
 * </p>
 * 
 * @title FirstTenderDetailController.java
 * @package com.cxdai.portal.first.controller
 * @author justin.xu
 * @version 0.1 2014年7月15日
 */
@Controller
@RequestMapping(value = "/first/tenderdetail/")
public class FirstTenderDetailController extends BaseController {

	@Autowired
	private FirstTenderDetailService firstTenderDetailService;

	/**
	 * <p>
	 * Description:我要理财－－优先投标列表<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年3月3日
	 * @param request
	 * @param response
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/queryList/{firstBorrowId}/{pageNo}")
	public ModelAndView queryList(@PathVariable Integer pageNo, @PathVariable Integer firstBorrowId) throws Exception {
		ShiroUser shiroUser = currentUser();
		ModelAndView mv = new ModelAndView("first/tender/tenderDetailInner");
		FirstTenderDetailCnd firstTenderDetailCnd = new FirstTenderDetailCnd();
		firstTenderDetailCnd.setFirstBorrowId(firstBorrowId);
		firstTenderDetailCnd.setFirstTenderRealStatus(Constants.TENDER_REAL_STATUS_AVAILABLE);
		Page page = firstTenderDetailService.queryPageListByCnd(firstTenderDetailCnd, new Page(pageNo, BusinessConstants.DEFAULT_PAGE_SIZE));
		mv.addObject("tendDetailPage", page);
		mv.addObject("memberVo", shiroUser);
		return mv;
	}
}
