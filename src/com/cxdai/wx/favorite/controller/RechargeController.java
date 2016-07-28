package com.cxdai.wx.favorite.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cxdai.common.page.Page;
import com.cxdai.portal.account.service.InvestReportService;
import com.cxdai.portal.account.service.RechargeRecordService;
import com.cxdai.portal.account.vo.RechargeRecordCnd;
import com.cxdai.portal.base.BaseController;
import com.cxdai.portal.statics.BusinessConstants;

@Controller
@RequestMapping(value = "/wx/recharge")
public class RechargeController extends BaseController {

	public Logger logger = Logger.getLogger(RechargeController.class);

	@Autowired
	private RechargeRecordService rechargeRecordService;
	@Autowired
	private InvestReportService investReportService;

	/**
	 * 获取充值总额
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author huangpin
	 * @version 0.1 2014年11月17日
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/chargeTotal")
	@ResponseBody
	public Map<String, Object> chargeTotal() {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			BigDecimal chargeTotal = investReportService.queryRechargeTotalByMemberId(currentUser().getUserId());
			map.put("chargeTotal", chargeTotal);
			return map;
		} catch (Exception e) {
			logger.error("微信-获取充值总额异常：", e);
		}
		return map;
	}

	/**
	 * 充值-列表
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author huangpin
	 * @version 0.1 2014年10月30日
	 * @param pageNum
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/chargeList/{pageNum}")
	@ResponseBody
	public Map<String, Object> chargeList(@PathVariable Integer pageNum) {
		try {
			if (currentUser() != null) {
				RechargeRecordCnd rechargeRecordCnd = new RechargeRecordCnd();
				rechargeRecordCnd.setUserId(Integer.valueOf(currentUser().getUserId()));
				List<?> chargeList = rechargeRecordService.queryPageListByCnd(rechargeRecordCnd, new Page(pageNum, BusinessConstants.DEFAULT_PAGE_SIZE)).getResult();
				if (chargeList != null) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("chargeList", chargeList);
					if (chargeList.size() == BusinessConstants.DEFAULT_PAGE_SIZE)
						map.put("moreDiv", "<a id='clickA'><div id='contain'><div class='f2'><h4>查看更多</h4></div></div></a>");
					return map;
				}
			}
		} catch (Exception e) {
			logger.error("微信-充值-列表获取异常", e);
		}
		return new HashMap<String, Object>();
	}
}
