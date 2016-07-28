package com.cxdai.portal.quartz;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.cxdai.portal.account.service.FriendService;
import com.cxdai.utils.DateUtils;

/**
 * 
 * <p>
 * Description:网站奖励相关定时任务<br />
 * </p>
 * @title AwardInviterTimerService.java
 * @package com.cxdai.portal.quartz 
 * @author yangshijin
 * @version 0.1 2014年6月6日
 */
public class WebAwardTimerService{

	@Autowired
	private FriendService friendService;
	
	/**
	 * 
	 * <p>
	 * Description:邀请好友，首充1000奖励10元<br />
	 * </p>
	 * @author yangshijin
	 * @version 0.1 2014年6月6日
	 * void
	 */
	public void awardInviter(){
		System.out.println("awardInviter  Begin:" + DateUtils.format(new Date(), DateUtils.YMD_HMS)+".....");
		friendService.awardInviter();
		System.out.println("awardInviter  End:" + DateUtils.format(new Date(), DateUtils.YMD_HMS)+".....");
	}
}
