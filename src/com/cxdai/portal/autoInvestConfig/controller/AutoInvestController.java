package com.cxdai.portal.autoInvestConfig.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cxdai.base.entity.AutoInvestConfig;
import com.cxdai.common.page.Page;
import com.cxdai.portal.account.service.AccountService;
import com.cxdai.portal.account.vo.AccountVo;
import com.cxdai.portal.autoInvestConfig.service.AutoInvestConfigRecordService;
import com.cxdai.portal.autoInvestConfig.service.AutoInvestService;
import com.cxdai.portal.autoInvestConfig.vo.AutoInvestConfigRecordCnd;
import com.cxdai.portal.autoInvestConfig.vo.AutoInvestConfigRecordVo;
import com.cxdai.portal.autoInvestConfig.vo.AutoInvestConfigVo;
import com.cxdai.portal.base.BaseController;
import com.cxdai.portal.base.MessageBox;
import com.cxdai.portal.member.service.MemberService;
import com.cxdai.portal.member.service.VipLevelService;
import com.cxdai.portal.statics.BusinessConstants;
import com.cxdai.security.ShiroUser;
import com.cxdai.utils.DateUtils;

/**
 * 
 * <p>
 * Description:自动投资<br />
 * </p>
 * @title AutoInvestController.java
 * @package com.cxdai.portal.autoInvestConfig.controller 
 * @author yubin
 * @version 0.1 2015年11月19日
 */
@Controller
@RequestMapping(value = "/myaccount/autoInvest")
public class AutoInvestController extends BaseController {
    
	private static final Logger logger=Logger.getLogger(AutoInvestController.class);
	@Autowired
	private AutoInvestService autoInvestService;

	@Autowired
	private AccountService accountService;

	@Autowired
	private MemberService memberService;

	@Autowired
	private AutoInvestConfigRecordService autoInvestConfigRecordService;

	@Autowired
	private VipLevelService vipLevelService;

	 

	/**
	 * 
	 * <p>
	 * Description:进入自动投标设置页面<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年8月22日
	 * @return String
	 */
	@RequiresAuthentication
	@RequestMapping(value = "/autoInvestMain")
	public String autoInvestMain() {
		return "account/autoInvest/autoInvest-main";
	}

	/**
	 * 进入自动投标设置列表
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequiresAuthentication
	@RequestMapping(value = "/autoInvestList")
	public String autoInvestList(HttpServletRequest request) throws Exception {
		ShiroUser shiroUser = currentUser();
		List<AutoInvestConfigVo> autoInvestConfigVoList = autoInvestService.queryListByUserId(shiroUser.getUserId());
		request.setAttribute("autoInvestConfigVoList", autoInvestConfigVoList);
	
		for (AutoInvestConfigVo autoInvestConfigVo : autoInvestConfigVoList) {
			if (autoInvestConfigVo.getStatus() == 1) {
				if (autoInvestConfigVo.getAutoType() == BusinessConstants.AUTO_TYPE_ONE) {
					String uptime_str = DateUtils.timeStampToDate(autoInvestConfigVo.getUptime().substring(0, 10), DateUtils.YMD_DASH);
					String reload_uptime = DateUtils.format(DateUtils.dayOffset(DateUtils.parse(uptime_str, DateUtils.YMD_DASH), 90),
							DateUtils.YMD_DASH);
					request.setAttribute("reload_uptime", reload_uptime);
				}

				if (autoInvestConfigVo.getAutoType() == BusinessConstants.AUTO_TYPE_TWO) {
					String uptime_str = DateUtils.timeStampToDate(autoInvestConfigVo.getUptime().substring(0, 10), DateUtils.YMD_DASH);
					String reload_uptime = DateUtils.format(DateUtils.dayOffset(DateUtils.parse(uptime_str, DateUtils.YMD_DASH), 90),
							DateUtils.YMD_DASH);
					request.setAttribute("reload_queuingTime", reload_uptime);
				}
			}
		}
		AccountVo accountVo = accountService.queryAccountByUserId(shiroUser.getUserId());
		request.setAttribute("accountVo", accountVo);
		if (vipLevelService.getIsSvipByUserId(shiroUser.getUserId())) {
			request.setAttribute("isSvip", "yes");
		}
		return "account/autoInvest/autoInvest-list";
	}

	 

	/**
	 * 
	 * <p>
	 * Description:新增或修改自动投标信息<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年6月18日
	 * @param request
	 * @param autoInvestConfig
	 * @return
	 * @throws Exception
	 *             String
	 */
	@RequiresAuthentication
	@RequestMapping(value = "/saveAutoInvest")
	public String saveAutoInvest(HttpServletRequest request, @ModelAttribute AutoInvestConfig autoInvestConfig) throws Exception {
		ShiroUser shiroUser = currentUser();
		if (super.judgeBlackByType(BusinessConstants.BLACK_TYPE_SET_AUTO_INVEST_CONFIG)) {
			return "redirect:/myaccount/autoInvest/autoInvestMain.html";
		}
		if (shiroUser.getIsFinancialUser().intValue() == 0) {
			request.getSession().setAttribute("msg", "抱歉！您借款用户，无法使用该项服务。");
			return "redirect:/myaccount/autoInvest/autoInvestMain.html";
		}
		// 添加新的自动投标记录时
		if (autoInvestConfig.getId() == null) {
			int count = autoInvestService.queryCountByUserId(shiroUser.getUserId());
			if (count >= 1) {
				request.getSession().setAttribute("msg", "您已经设置了1条规则，无法再新增规则！");
				return "redirect:/myaccount/autoInvest/autoInvestMain.html";
			}
		}
		String borrow_style_status = request.getParameter("borrow_style_status");
		if (borrow_style_status != null && request.getParameter("borrow_style_status").equals("0")) {
			autoInvestConfig.setBorrow_type(0);
		}

		// 新增或修改规则
		autoInvestConfig.setPlatform(shiroUser.getPlatform());
		String msg = autoInvestService.insertOrUpdate(shiroUser.getUserId(), autoInvestConfig, request.getRemoteAddr());

		request.getSession().setAttribute("msg", msg);
		return "redirect:/myaccount/autoInvest/autoInvestMain.html";
	}
    
	/**
	 * 
	 * <p>
	 * Description:启用或停用记录<br />
	 * </p>
	 * @author yubin
	 * @version 0.1 2015年11月17日
	 * @param id
	 * @param status
	 * @return
	 * @throws Exception
	 * MessageBox
	 */
	@RequiresAuthentication
	@RequestMapping(value = "/updateAutoInvest")
	@ResponseBody
	public MessageBox updateAutoInvest(@RequestParam Integer id,@RequestParam Integer status) throws Exception {
		 
		String result="";
		try {
			if (id != null && status != null) {
				ShiroUser shiroUser = currentUser();
				
				if (super.judgeBlackByType(BusinessConstants.BLACK_TYPE_SET_AUTO_INVEST_CONFIG) && status.intValue() == 1) {
					return new MessageBox("0", "禁止设置自动投标");
				}
				if (shiroUser.getIsFinancialUser().intValue() == 0) {
					 
					return new MessageBox("0", "抱歉！您借款用户，无法使用该项服务。");
				}
				// 启用或停用规则
				result= autoInvestService.EnableOrDisableRecord(id, status, shiroUser);
				if(BusinessConstants.ENABLE.equals(result)){
					return new MessageBox("1", "【启用】成功");
				}
				if(BusinessConstants.DISABLE.equals(result)){
					return new MessageBox("1", "【停用】成功");
				}
			} else {
				    return new MessageBox("0", "请求参数不存在！");	 
			}
		} catch (Exception e) {
		 
			logger.error("启用或停用记录", e);
			return new MessageBox("0", "请求出错");	 
			 
		}
		return new MessageBox("0",result);
	}

	/**
	 * 进入修改自动投标信息页面.
	 * 
	 * @param request
	 * @return
	 */
	@RequiresAuthentication
	@RequestMapping(value = "/toAutoInvestUpdate/{id}")
	public String toAutoInvestUpdate(HttpServletRequest request, @PathVariable int id) {
		try {
			AutoInvestConfig autoInvestConfig = autoInvestService.queryById(id);
			ShiroUser shiroUser = currentUser();
			AccountVo accountVo = accountService.queryAccountByUserId(shiroUser.getUserId());
			request.setAttribute("autoInvestConfig", autoInvestConfig);
			request.setAttribute("accountVo", accountVo);
			Map<String, String> autoTypeMap = new HashMap<String, String>();
			if (autoInvestConfig.getAutoType() == BusinessConstants.AUTO_TYPE_ONE) {
				autoTypeMap.put("autoTypeOne", "1");
			}
			if (autoInvestConfig.getAutoType() == BusinessConstants.AUTO_TYPE_TWO) {
				autoTypeMap.put("autoTypeTwo", "2");
			}
			request.setAttribute("autoTypeMap", autoTypeMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "account/autoInvest/autoInvest-add";
	}

	/**
	 * 
	 * <p>
	 * Description:删除自动投标记录<br />
	 * </p>
	 * @author yubin
	 * @version 0.1 2015年11月17日
	 * @param request
	 * @param id
	 * @return
	 * MessageBox
	 */
	@RequiresAuthentication
	@RequestMapping(value = "/delAutoInvest/{id}")
	@ResponseBody
	public MessageBox delAutoInvest(@PathVariable int id) {
		String result="";
		try {
			ShiroUser shiroUser=currentUser();
			result = autoInvestService.delAutoInvest(id, shiroUser);
		    if(BusinessConstants.SUCCESS.equals(result)){
		    	return new MessageBox("1","删除成功");
		    }
		} catch (Exception e) {
			logger.error("删除自动投标记录", e);
			return new MessageBox("0","删除失败"); 
		}
		return new MessageBox("0",result);
	}
  
	/**
	 * 自动规则日志信息列表
	 * 
	 * @param request
	 * @param pageSize
	 * @return
	 */
	@RequiresAuthentication
	@RequestMapping(value = "/queryAutoTenterLog/{pageSize}")
	public ModelAndView queryAutoTenterLog(HttpServletRequest request, @PathVariable int pageSize) {
		ModelAndView mv = new ModelAndView("account/autoInvest/autoInvestLog-list");
		ShiroUser shiroUser = currentUser();
		String pageNum_str = request.getParameter("pageNum");
		int pageNum = 1;
		if (pageNum_str != null && !pageNum_str.equals("")) {
			pageNum = Integer.parseInt(pageNum_str);
		}
		AutoInvestConfigRecordCnd autoInvestConfigRecordCnd = new AutoInvestConfigRecordCnd();
		autoInvestConfigRecordCnd.setUser_id(shiroUser.getUserId());
		try {
			Page page = autoInvestConfigRecordService.queryListByAutoInvestConfigRecordCnd(autoInvestConfigRecordCnd, pageNum, pageSize);
			AccountVo accountVo = accountService.queryAccountByUserId(shiroUser.getUserId());
			request.setAttribute("page", page);
			request.setAttribute("accountVo", accountVo);
		} catch (Exception e) {
			logger.error("删除自动投标记录", e);
		}
		return mv;
	}

	/**
	 * 查询自动规则日志详情.
	 * 
	 * @param request
	 * @param id
	 * @return
	 */
	@RequiresAuthentication
	@RequestMapping(value = "/queryAutoInvestConfigRecordDetail/{id}")
	public ModelAndView queryAutoInvestConfigRecordDetail(HttpServletRequest request, @PathVariable Integer id) {
		ModelAndView mv = new ModelAndView("account/autoInvest/autoInvestLog-detail");
		try {
			AutoInvestConfigRecordVo autoInvestConfigRecordVo = autoInvestConfigRecordService.queryForVoById(id);
			AccountVo accountVo = accountService.queryAccountByUserId(autoInvestConfigRecordVo.getUser_id());
			mv.addObject("autoInvestConfigRecord", autoInvestConfigRecordVo);
			mv.addObject("accountVo", accountVo);
			mv.addObject("pageNo", request.getParameter("pageNo"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}
	/**
	 * 
	 * <p>
	 * Description:自动投资页面<br />
	 * </p>
	 * @author yubin
	 * @version 0.1 2015年11月16日
	 * @return
	 * String
	 */
	@RequiresAuthentication
	@RequestMapping(value = "/autoInvestSeting")
	public ModelAndView autoInvestSeting(@RequestParam(value="id", required=false) Integer id ) {
		ModelAndView mv=new ModelAndView("account/autoInvest/autoInvest-seting");
		ShiroUser shiroUser=currentUser();
		if(shiroUser==null){
			return new ModelAndView(BusinessConstants.ERROR);
		}
		try {
			AccountVo accountVo=accountService.queryAccountByUserId(shiroUser.getUserId());
			mv.addObject("accountVo",accountVo);
			if(id!=null){
				AutoInvestConfig autoInvestConfig = autoInvestService.queryById(id);
				mv.addObject("autoInvestConfig", autoInvestConfig);
			}
		} catch (Exception e) {
			logger.error("自动投资页面异常", e);
		}
		return mv;
	}
	/**
	 * 
	 * <p>
	 * Description:添加或修改自动投资功能<br />
	 * </p>
	 * @author yubin
	 * @version 0.1 2015年11月17日
	 * @return
	 * MessageBox
	 */
	@RequiresAuthentication
	@RequestMapping(value = "/addAutoInvest")
	@ResponseBody
	public MessageBox addAutoInvest(@ModelAttribute AutoInvestConfig autoInvestConfig,HttpServletRequest request ){
		ShiroUser shiroUser = currentUser();
		String msg="";
		try {
	     
			if (super.judgeBlackByType(BusinessConstants.BLACK_TYPE_SET_AUTO_INVEST_CONFIG)) {
				return  new MessageBox("0", "禁止设置自动投标");
			}
			if (shiroUser.getIsFinancialUser().intValue() == 0) {
				return  new MessageBox("0", "抱歉！您借款用户，无法使用该项服务。");
				
			}
			// 添加新的自动投标记录时
			if (autoInvestConfig.getId() == null) {
				int count = autoInvestService.queryCountByUserId(shiroUser.getUserId());
				if (count >= 1) {
					return  new MessageBox("0", "您已经设置了1条规则，无法再新增规则！");
				}
			}
			String borrow_style_status = request.getParameter("borrow_style_status");
	
			if (borrow_style_status != null && request.getParameter("borrow_style_status").equals("0")) {
				autoInvestConfig.setBorrow_type(0);
			}

			// 新增或修改规则
			autoInvestConfig.setPlatform(shiroUser.getPlatform());
			   msg = autoInvestService.insertOrUpdate(shiroUser.getUserId(), autoInvestConfig, request.getRemoteAddr());
            if(msg.equals("ADD_SUCCESS")){
            	return new MessageBox("1", "新增成功");
            }
			if(msg.equals("UPDATE_SUCCESS")){
				return new MessageBox("1", "修改成功");
			}
		} catch (Exception e) {
			logger.error("添加或修改自动投资功能异常", e);
		}
		return new MessageBox("0", msg);
	}
	/**
	 * 
	 * <p>
	 * Description:判断<br />
	 * </p>
	 * @author yubin
	 * @version 0.1 2015年11月17日
	 * @return
	 * MessageBox
	 */
	@RequiresAuthentication
	@RequestMapping(value = "/judegOverTen")
	@ResponseBody
	public MessageBox judegOverTen(@RequestParam(value="id", required=false) Integer id){
		ShiroUser shiroUser=currentUser();
		try {
			if(shiroUser!=null&&id!=null){
			 
				AutoInvestConfig autoInvestConfig = autoInvestService.queryById(id);
				if(autoInvestConfig.getUser_id()!=shiroUser.getUserId()){
					return new MessageBox("0", "非法行为");
				}
			    return new MessageBox("1", "");
			} 
		}catch (Exception e) {
				logger.error("判断异常", e);
		}
		return new MessageBox("0", "操作非法");	
	}
}
