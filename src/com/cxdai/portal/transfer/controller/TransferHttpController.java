package com.cxdai.portal.transfer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cxdai.common.page.Page;
import com.cxdai.portal.base.BaseController;
import com.cxdai.portal.statics.BusinessConstants;
import com.cxdai.portal.transfer.service.TransferService;
import com.cxdai.portal.transfer.vo.SearchTransferVo;

/**
 * <p>
 * Description:债权转让，非https请求<br />
 * </p>
 * 
 * @title TransferHttpController.java
 * @package com.cxdai.portal.transfer.controller
 * @author justin.xu
 * @version 0.1 2015年2月2日
 */
@Controller
@RequestMapping
public class TransferHttpController extends BaseController {

	Logger logger = LoggerFactory.getLogger(TransferHttpController.class);

	@Autowired
	private TransferService transferService;

	/**
	 * <p>
	 * Description:债权转让-列表查询<br />
	 * </p>
	 * 
	 * @author chenpeng
	 * @version 0.1 2014年10月21日
	 * @param request
	 * @return ModelAndView
	 */
	@RequestMapping(value = "queryTransferList")
	public ModelAndView queryTransferList(@RequestParam Integer pageNum, @ModelAttribute SearchTransferVo seach) {
		ModelAndView mv = new ModelAndView("transfer/transferGridByPage");

		Page p = null;
		try {

			p = transferService.findtransferList(seach, new Page(pageNum, BusinessConstants.DEFAULT_PAGE_SIZE));

		} catch (Exception e) {
			logger.error("进入债权转让出错");
			e.printStackTrace();
		}
		mv.addObject("page", p);
		return mv;
	}
	/**
	 * 
	 * <p>
	 * Description:官网改版---<br />
	 * </p>
	 * @author yubin
	 * @version 0.1 2016年1月11日
	 * @param pageNum
	 * @param seach
	 * @return
	 * ModelAndView
	 */
	@RequestMapping(value = "queryTransferList1")
	public ModelAndView queryTransferList1(@RequestParam Integer pageNum, @ModelAttribute SearchTransferVo seach) {
		ModelAndView mv = new ModelAndView("transfer/transferGridByPage1");

		Page p = null;
		try {

			p = transferService.findtransferList(seach, new Page(pageNum, BusinessConstants.DEFAULT_PAGE_SIZE));

		} catch (Exception e) {
			logger.error("进入债权转让出错");
			e.printStackTrace();
		}
		mv.addObject("page", p);
		return mv;
	}
}
