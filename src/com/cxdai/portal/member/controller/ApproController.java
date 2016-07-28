package com.cxdai.portal.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cxdai.portal.base.BaseController;
import com.cxdai.portal.member.service.ApproService;
import com.cxdai.portal.member.service.MemberService;
import com.cxdai.portal.member.vo.MemberApproVo;
import com.cxdai.security.ShiroUser;

/**
 * <p>
 * Description:认证Controller<br />
 * </p>
 * 
 * @title EmailApproController.java
 * @package com.cxdai.portal.member.controller
 * @author justin.xu
 * @version 0.1 2014年4月23日
 */
@Controller
@RequestMapping(value = "/appro")
public class ApproController extends BaseController {

	public Logger logger = Logger.getLogger(ApproController.class);

	@Autowired
	private ApproService approService;
	@Autowired
	MemberService memberService;

	/**
	 * 
	 * <p>
	 * Description:获取各项认证<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年10月14日
	 * @param request
	 * @param session
	 * @param email
	 * @return MemberApproVo
	 */
	@RequestMapping(value = "/findAppro")
	@ResponseBody
	public MemberApproVo updateAndSendMail(HttpServletRequest request, HttpSession session, String email) {
		MemberApproVo memberApproVo = null;
		try {
			ShiroUser shiroUser = currentUser();
			memberApproVo = memberService.queryMemberApproByUserId(shiroUser.getUserId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return memberApproVo;
	}

	/**
	 * <p>
	 * Description:邮箱认证<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年4月23日
	 * @param request
	 * @param user_id
	 * @param uuid
	 * @param email
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value = "/email_appro/{userId}/{uuid}")
	public ModelAndView emailAppro(HttpServletRequest request, @PathVariable Integer userId, @PathVariable String uuid, String email) throws Exception {
		ModelAndView mv = new ModelAndView("account/approve/email/certificationEmailSuccess");
		String result = "success";
		try {
			result = approService.updateEmailAppr(userId, uuid, email, request);
			if (result.equals("success")) {
				result = "恭喜您通过了邮箱认证！";
			}else{
				email=null;
			}
		} catch (Exception e) {
			result = "网络异常，请稍候重试！";
			e.printStackTrace();
		}
		MemberApproVo memberApproVo = null;
		if (null != currentUser() && currentUser().getUserId() != null) {
			memberApproVo = memberService.queryMemberApproByUserId(currentUser().getUserId());
		}

		mv.addObject("memberApproVo", memberApproVo);
		mv.addObject("email", email).addObject("result", result);
		return mv;
	}

	/**
	 * <p>
	 * Description:邮箱认证<br />
	 * </p>
	 * 
	 * @author hujianpan
	 * @version 0.1 2014年9月3日
	 * @param request
	 * @param user_id
	 * @param uuid
	 * @param email
	 * @return String
	 */
	@RequestMapping(value = "/registEmailAppro/{userId}/{uuid}")
	public ModelAndView registEmailAppro(HttpServletRequest request, @PathVariable Integer userId, @PathVariable String uuid, String email) {
		ModelAndView mv = new ModelAndView("member/registerSucess");
		String result = memberService.activateRegistEmail(request, userId, uuid, email);
		mv.addObject("userName", result);

		return mv;
	}
}
