package com.cxdai.portal.sycee.controller;

import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cxdai.portal.base.BaseController;
import com.cxdai.portal.base.MessageBox;
import com.cxdai.portal.member.service.MemberService;
import com.cxdai.portal.member.vo.MemberCnd;
import com.cxdai.portal.member.vo.MemberVo;
import com.cxdai.portal.sycee.entity.SyceeGoods;
import com.cxdai.portal.sycee.service.SyceeService;
import com.cxdai.utils.CSRFTokenManager;
import com.cxdai.utils.HttpTookit;

/**
 * 元宝商城
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title SyceeController.java
 * @package com.cxdai.portal.sycee.controller
 * @author huangpin
 * @version 0.1 2015年10月23日
 */
@Controller
@RequestMapping(value = "/sycee")
public class SyceeController extends BaseController {

	private static final Logger logger = Logger.getLogger(SyceeController.class);

	@Autowired
	private SyceeService syceeService;

	@Autowired
	private MemberService memberService;

	// @RequestMapping(value = "")
	// public ModelAndView waiting() {
	// return new ModelAndView("/sycee/waiting");
	// }

	/**
	 * 元宝商城-首页
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author huangpin
	 * @version 0.1 2015年10月23日
	 * @return ModelAndView
	 */
	@RequestMapping(value = "")
	public ModelAndView main() {
		ModelAndView mv = new ModelAndView("/sycee/index");
		try {
			// 登录用户
			if (currentUser() != null) {
				MemberCnd memberCnd = new MemberCnd();
				memberCnd.setId(currentUser().getUserId());
				MemberVo m = memberService.queryMemberByCnd(memberCnd);
				mv.addObject("userSycee", m.getAccumulatepoints());// 总元宝
				mv.addObject("userHeadImg", currentUser().getHeadImg());// 用户头像
				mv.addObject("signItem", syceeService.getSignItem());// 签到帖
			}
			// 线上商品
			mv.addObject("onlineGoods", syceeService.syceeClassList(1));
			// 线下商品
			mv.addObject("offlineGoods", syceeService.syceeClassList(2));
			mv.addObject("csrf", CSRFTokenManager.getTokenForSession());
		} catch (Exception e) {
			logger.error("元宝商城首页加载异常", e);
		}
		return mv;
	}

	/**
	 * 商品详情，@RequiresAuthentication登录才可访问
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author huangpin
	 * @version 0.1 2015年10月24日
	 * @param goodsId
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/goods/{goodsId}")
	@RequiresAuthentication
	public ModelAndView syceeGoodsInfo(@PathVariable("goodsId") int goodsId) {
		ModelAndView mv = new ModelAndView("/sycee/goodsDiv");
		SyceeGoods goods = syceeService.getGoodsInfo(goodsId);
		mv.addObject("goods", goods);
		mv.addObject("surplusSycee", syceeService.getUserSycee(currentUser().getUserId()) - goods.getSycee() * goods.getNum());// 兑换后剩余元宝
		return mv;
	}

	/**
	 * 兑换商品提交
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author huangpin
	 * @version 0.1 2015年10月26日
	 * @param goodsId
	 * @return MessageBox
	 */
	@RequestMapping(value = "/exchange/goods/{goodsId}")
	@RequiresAuthentication
	@ResponseBody
	public MessageBox subExchange(@PathVariable("goodsId") int goodsId) {
		try {
			String ip = HttpTookit.getRealIpAddr(currentRequest());
			return syceeService.addExchange(currentUser().getUserId(), goodsId, ip);
		} catch (Exception e) {
			logger.error("【用户" + currentUser().getUserId() + "】元宝兑换商品异常", e);
			return new MessageBox("0", "兑换失败");
		}
	}
}
