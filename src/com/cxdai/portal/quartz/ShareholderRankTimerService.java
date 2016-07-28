package com.cxdai.portal.quartz;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.cxdai.portal.account.service.ShareholderRankService;
import com.cxdai.utils.DateUtils;

/**
 * 
 * <p>
 * Description:定时更新股东加权排名<br />
 * </p>
 * 
 * @title ShareholderRankTimerService.java
 * @package com.cxdai.portal.quartz
 * @author yangshijin
 * @version 0.1 2014年7月4日
 */
public class ShareholderRankTimerService {
	private final static Logger logger = Logger
			.getLogger(ShareholderRankTimerService.class);

	@Autowired
	private ShareholderRankService shareholderRankService;

	public void shareholderRankTimer() {
		logger.info("shareholderRankTimer  Begin:"
				+ DateUtils.format(new Date(), DateUtils.YMD_HMS) + ".....");
		try {
			shareholderRankService.shareholderRankTimer();
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("shareholderRankTimer  End:"
				+ DateUtils.format(new Date(), DateUtils.YMD_HMS) + ".....");
	}
}
