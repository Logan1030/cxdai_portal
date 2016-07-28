package com.cxdai.portal.index.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.cxdai.portal.base.BaseController;
import com.cxdai.portal.fix.service.FixBorrowService;
import com.cxdai.portal.fix.vo.DoubleElevenVo;

/**
 * @author WangQianJin
 * @title 活动Controller
 * @date 2015年11月10日
 */
@Controller
@RequestMapping(value = "/activity")
public class ActivityController extends BaseController {

	private final static Logger logger = Logger.getLogger(ActivityController.class);	

	@Autowired
	private FixBorrowService fixBorrowService;

	
	/**
	 * 双十一活动
	 * @author WangQianJin
	 * @title @param request
	 * @title @param response
	 * @title @return
	 * @date 2015年11月9日
	 */
	@RequestMapping(value = "/doubleEleven")
	public ModelAndView doubleEleven(HttpServletRequest request,HttpServletResponse response) {		
		ModelAndView mv = new ModelAndView("/index/doubleEleven");
		List<DoubleElevenVo> threeList=null;
		List<DoubleElevenVo> sixList=null;		
		try {
			//获取3月宝投资排名前10名
			threeList=fixBorrowService.queryFixForDoubleElevenList(3);
			//获取6月宝投资排名前10名
			sixList=fixBorrowService.queryFixForDoubleElevenList(6);		
		} catch (Exception e) {
			logger.error("双十一活动查询失败.", e);
			e.printStackTrace();
		}
		return mv.addObject("threeList", threeList).addObject("sixList", sixList);
	}
	/**
	 * 红包活动
	 * @author liutao
	 * @title @param request
	 * @title @param response
	 * @title @return
	 * @date 2015年12月02日
	 */
	@RequestMapping(value = "/redActivity")
	public ModelAndView redHuoDong(HttpServletRequest request,HttpServletResponse response) {		
		return new ModelAndView("/index/redActivity");
   }
	/**
	 * 双旦活动
	 * @author liutao
	 * @title @param request
	 * @title @param response
	 * @title @return
	 * @date 2015年12月22日
	 */
	@RequestMapping(value = "/doubleDan")
	public ModelAndView doubleDan(HttpServletRequest request,HttpServletResponse response) {		
		return new ModelAndView("/index/doubleDanActivity");
    }
}
