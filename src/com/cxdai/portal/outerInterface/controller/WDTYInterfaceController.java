package com.cxdai.portal.outerInterface.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cxdai.portal.outerInterface.service.WDTYInterfaceService;
import com.cxdai.utils.HttpTookit;

/**
 * 
 * <p>
 * Description:网贷天眼接口Controller<br />
 * </p>
 * 
 * @title WDTYInterfaceController.java
 * @package com.cxdai.portal.outerInterface.controller
 * @author yangshijin
 * @version 0.1 2014年8月19日
 */
@Controller
@RequestMapping(value = "/wdty/api/")
public class WDTYInterfaceController {

	@Autowired
	private WDTYInterfaceService wdtyInterfaceService;

	/**
	 * 
	 * <p>
	 * Description:外部访问接口测试页面<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年8月19日
	 * @param request
	 * @return ModelAndView
	 */
	@RequestMapping(value = "to_interface_testing")
	public ModelAndView toInterfaceTesting(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("test");
		return mv;
	}

	/**
	 * 
	 * <p>
	 * Description:每日成交量数据(仅统计净值标和抵押标)（网贷天眼调用接口）<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年8月19日
	 * @param request
	 * @return String
	 */
	@RequestMapping(value = "interface/transaction_info")
	public @ResponseBody
	String transaction_info(HttpServletRequest request) {
		String result = "-2";
		try {
			String date = request.getParameter("date"); // 格式为:yyyy-MM-dd
			result = wdtyInterfaceService.transactionInfo(
					HttpTookit.getRealIpAddr(request),
					request.getHeader("Referer"), date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 平台贷款数据(仅统计净值标和抵押标)（网贷天眼调用接口）
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "interface/loan_info")
	public @ResponseBody
	String loan_info(HttpServletRequest request) {
		String result = "-2";
		try {
			String date = request.getParameter("date"); // 格式为:yyyy-MM-dd
			result = wdtyInterfaceService.loanInfo(
					HttpTookit.getRealIpAddr(request),
					request.getHeader("Referer"), date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
