package com.cxdai.portal.account.controller;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cxdai.base.entity.Configuration;
import com.cxdai.common.Dictionary;
import com.cxdai.common.page.Page;
import com.cxdai.common.report.JasperExportUtils;
import com.cxdai.common.report.ReportData;
import com.cxdai.portal.account.service.AccountOperateRecordService;
import com.cxdai.portal.account.vo.AccountOperateVO;
import com.cxdai.portal.base.BaseController;
import com.cxdai.portal.statics.BusinessConstants;
import com.cxdai.security.ShiroUser;
import com.cxdai.utils.DateUtils;

/**
 * <p>
 * Description:我的帐号操作记录控制,即资金明细<br />
 * </p>
 * 
 * @title AccountOperateRecordController.java
 * @package com.cxdai.portal.account.controller
 * @author hujianpan
 * @version 0.1 2014年8月13日
 */
@Controller
@RequestMapping(value = "/accOpertingRecord")
public class AccountOperateRecordController extends BaseController{


	public Logger logger = Logger.getLogger(AccountOperateRecordController.class);

	@Autowired
	private AccountOperateRecordService accountOperateRecordService;

	/**
	 * <p>
	 * Description:显示账号操作日志详情信息<br />
	 * </p>
	 * @author 胡建盼
	 * @version 0.1 2014年8月15日
	 * @param request
	 * @param pageSize
	 * @param pageNum
	 * @return
	 * ModelAndView
	 */
	@SuppressWarnings("unchecked")
	// 判断是否登录 
	@RequiresAuthentication
	@RequestMapping(value="showPaymentDetail")
	public ModelAndView showPaymentDetail(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("/account/account/showPaymentDetail");
		ShiroUser shiroUser = currentUser();
		//没有登入
		if(null == shiroUser){
			forword(BusinessConstants.TOP_HOME_ADDRESS);
		}
		Integer accountId = Integer.valueOf(request.getParameter("accountId"));
		AccountOperateVO accountOperateVO =accountOperateRecordService.searchPaymentDetail(accountId);
		//如果查看的不是本人的资金明细，主要为防止使用拦截工具串改参数查看别人的资金明细详情
		if(accountOperateVO.getUserId()>0&&accountOperateVO.getUserId()==shiroUser.getUserId()){
			mv.addObject("accountOperateVO", accountOperateVO);
		}else{
			forword(BusinessConstants.TOP_HOME_ADDRESS);
		}
		return mv; 
	}
	
	/**
	 * <p>
	 * Description:进入资金明细页面<br />
	 * </p>
	 * @author 胡建盼
	 * @version 0.1 2014年8月15日
	 * @param request
	 * @param pageSize
	 * @param pageNum
	 * @return
	 * ModelAndView
	 */
	@SuppressWarnings("unchecked")
	// 判断是否登录 
	@RequiresAuthentication
	@RequestMapping(value="paymentDetails/{pageNum}")
	public ModelAndView paymentDetails(HttpServletRequest request, @PathVariable int pageNum){
		ModelAndView mv = new ModelAndView("/account/account/payment-details");
	
		ShiroUser shiroUser = currentUser();
		//没有登入
		if(null == shiroUser){
			forword(BusinessConstants.TOP_HOME_ADDRESS);
		}
		//获取账户操作类型
		Collection<Configuration> configurationList = Dictionary.getValues(1);
		mv.addObject("configurationList", configurationList);		
		if(pageNum == 0){
			pageNum = 1;
		}
		
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String type = request.getParameter("type");
		
		//isTimeRangeTooDiff(startTime, endTime);
		
		//查询账户操作记录，即资金明细
		List<AccountOperateVO> listUAVO = null;
		int totalCount = 0;
		int totalPage =0;
		Page page =accountOperateRecordService.searchPageUserAccountList(shiroUser.getUserName(),startTime ,endTime ,type ,pageNum, BusinessConstants.DEFAULT_PAGE_SIZE );
		if(null != page && page.getPageSize() > 0){
			listUAVO =(List<AccountOperateVO>) page.getResult();
			if(page.getTotalCount() > 0){
				totalCount = page.getTotalCount();
				totalPage=page.getTotalPage();
				pageNum = page.getPageNo();
				
			}
			
		}
		
		request.setAttribute("totalCount", totalCount);
		mv.addObject("pageNum", pageNum);
		mv.addObject("totalPage", totalPage);
		mv.addObject("listUAVO", listUAVO);
		mv.addObject("startTime", startTime);
		mv.addObject("endTime", endTime);
		mv.addObject("type", type);
		mv.addObject("page", page);
		
		
		return mv; 
	
		
	}
	/**
	 * 
	 * <p>
	 * Description:判断参数是否为空<br />
	 * </p>
	 * @author 胡建盼
	 * @version 0.1 2014年8月28日
	 * @param src
	 * @return
	 * Boolean
	 */
	private Boolean isNull(Object src){
		if(null == src || "".equals(src.toString().trim())){
			return true;
		}
		return false;
	}
	/**
	 * 
	 * <p>
	 * Description:校验时间参数是否在一个月以内，避免服务器压力太大<br />
	 * </p>
	 * @author 胡建盼
	 * @version 0.1 2014年8月28日
	 * @param startTime
	 * @param endTime
	 * void
	 */
	private void isTimeRangeTooDiff(String startTime, String endTime) {
		if(isNull(startTime) || isNull(endTime)){
			return ;
		}
		Date endDate = DateUtils.parse(endTime, DateUtils.YMD_DASH);
		Date beginDate = DateUtils.parse(startTime, DateUtils.YMD_DASH);
		
		int day = DateUtils.dayDiff(endDate,beginDate );
		if(BusinessConstants.BEGINDATE_ENDDATE_RANGE <  day ){
			forword(BusinessConstants.NO_PAGE_FOUND_404);
		}
	}
	/**
	 * <p>
	 * Description:查询按钮<br />
	 * </p>
	 * @author 胡建盼
	 * @version 0.1 2014年8月15日
	 * @param request
	 * @param pageSize
	 * @param pageNum
	 * @return
	 * ModelAndView
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="searchpaymentDetails/{pageNum}")
	public ModelAndView searchpaymentDetails(HttpServletRequest request, @PathVariable int pageNum) throws Exception{
		ModelAndView mv = new ModelAndView("/account/account/payment_detailsInner");
	
		ShiroUser shiroUser = currentUser();
		//没有登入
		if(null == shiroUser){
			forword(BusinessConstants.TOP_HOME_ADDRESS);
		}
		//获取账户操作类型
		Collection<Configuration> configurationList = Dictionary.getValues(1);
		mv.addObject("configurationList", configurationList);		
		if(pageNum == 0){
			pageNum = 1;
		}
		
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String type = request.getParameter("type");
		//非法参数时抛异常
		if(startTime != null && !"".equals(startTime)){
			if(startTime.length()!=10){
				throw new Exception();
		     }
		}
		if(endTime != null && !"".equals(endTime)){
			if(endTime.length()!=10){
				throw new Exception();
			}
		}
		if(type != null && !"".equals(type)){
			String tempValue = Dictionary.getValue(1,type);
			if(StringUtils.isEmpty(tempValue)){
				throw new Exception();
			}
		}
		//查询账户操作记录，即资金明细
		List<AccountOperateVO> listUAVO = null;
		int totalCount = 0;
		int totalPage =0;
		Page page =accountOperateRecordService.searchPageUserAccountList(shiroUser.getUserName(),startTime ,endTime ,type ,pageNum, BusinessConstants.DEFAULT_PAGE_SIZE );
		if(null != page && page.getPageSize() > 0){
			listUAVO =(List<AccountOperateVO>) page.getResult();
			if(page.getTotalCount() > 0){
				totalCount = page.getTotalCount();
				totalPage=page.getTotalPage();
				pageNum = page.getPageNo();
				
			}
			
		}
		
		request.setAttribute("totalCount", totalCount);
		mv.addObject("pageNum", pageNum);
		mv.addObject("totalPage", totalPage);
		mv.addObject("listUAVO", listUAVO);
		mv.addObject("startTime", startTime);
		mv.addObject("endTime", endTime);
		mv.addObject("type", type);
		mv.addObject("page", page);
		
		
		return mv; 
	
		
	}
	
	/**
	 * <p>
	 * Description:进入资金明细页面<br />
	 * </p>
	 * @author 胡建盼
	 * @version 0.1 2014年8月15日
	 * @param request
	 * @param pageSize
	 * @param pageNum
	 * @return
	 * ModelAndView
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="showdetail")
	public ModelAndView showDetail(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("/account/account/payment_detailsInner");
		int pageSize = BusinessConstants.DEFAULT_PAGE_SIZE;
		String pageNumStr  = request.getParameter("pageNum");
		int pageNum = 1;
		ShiroUser shiroUser = currentUser();
		//没有登入
		if(null == shiroUser){
			forword(BusinessConstants.TOP_HOME_ADDRESS);
		}
		//获取账户操作类型
		Collection<Configuration> configurationList = Dictionary.getValues(1);
		mv.addObject("configurationList", configurationList);		
		if(pageSize == 0){
			pageSize = 10;
		}
		if(pageNumStr == null && "".equals(pageNumStr)){
			pageNum = Integer.valueOf(pageNumStr);
		}
		
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String type = request.getParameter("type");
		
		//查询账户操作记录，即资金明细
		List<AccountOperateVO> listUAVO = null;
		int totalCount = 0;
		int totalPage =0;
		Page page =accountOperateRecordService.searchPageUserAccountList(shiroUser.getUserName(),startTime ,endTime ,type ,pageNum, BusinessConstants.DEFAULT_PAGE_SIZE );
		if(null != page && page.getPageSize() > 0){
			listUAVO =(List<AccountOperateVO>) page.getResult();
			if(page.getTotalCount() > 0){
				totalCount = page.getTotalCount();
				totalPage=page.getTotalPage();
				pageNum = page.getPageNo();
				
			}
			
		}
		
		request.setAttribute("totalCount", totalCount);
		mv.addObject("pageNum", pageNum);
		mv.addObject("totalPage", totalPage);
		mv.addObject("listUAVO", listUAVO);
		mv.addObject("startTime", startTime);
		mv.addObject("endTime", endTime);
		mv.addObject("type", type);
		mv.addObject("page", page);
		
		
		return mv; 
	
		
	}
	
	/**
	 * 
	 * <p>
	 * Description:导出账户操作记录，即资金明细数据。<br />
	 * </p>
	 * @author 胡建盼
	 * @version 0.1 2014年8月15日
	 * @param request
	 * @param response
	 * void
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="export/paymentDetails")
	public void exportToExcel(HttpServletRequest request,HttpServletResponse response){
		ShiroUser shiroUser = currentUser();
		//没有登入
		if(null == shiroUser){
			forword(BusinessConstants.TOP_HOME_ADDRESS);
		}
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String type = request.getParameter("type");
		String typeName = request.getParameter("typeName");
		//查询一个月的资金明细
		List<AccountOperateVO> listUAVO  =accountOperateRecordService.searchOneMonthUserAccountList(shiroUser.getUserName(),startTime ,endTime ,type );
		
		//导出数据过滤参数
		Map dto = new HashMap();
		dto.put("reportTitle", "资金记录表");
		dto.put("fromDate",startTime);
		dto.put("toDate", endTime);
		dto.put("moneyType", typeName);

		ReportData reportData = new ReportData();
		reportData.setParametersDto(dto);
		reportData.setFieldsList(listUAVO);
		reportData
				.setReportFilePath("/report/exportExcel/fpaymentDetailsExcel.jasper");

		java.io.InputStream is = request.getSession().getServletContext()
				.getResourceAsStream(reportData.getReportFilePath());
	
		// 这里可以传入pdf,excel,word,html,print导出各种类型文件
		JasperExportUtils.export(reportData.getFieldsList(),
				reportData.getParametersDto(), "excel", is, request, response,
				"资金记录表" + DateUtils.format(new Date(), DateUtils.YMD));
	
	}
	
}
