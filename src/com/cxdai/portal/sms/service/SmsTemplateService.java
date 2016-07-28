package com.cxdai.portal.sms.service;

import com.cxdai.portal.sms.vo.SmsTemplateVo;

/**
 * <p>
 * Description:短信模板业务类<br />
 * </p>
 * 
 * @title SmsTemplateService.java
 * @package com.cxdai.portal.sms.service
 * @author justin.xu
 * @version 0.1 2014年4月29日
 */
public interface SmsTemplateService {
	/**
	 * <p>
	 * Description:根据类型查询有效模板<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年4月29日
	 * @param type
	 * @return
	 * @throws Exception
	 *             SmsTemplate
	 */
	public SmsTemplateVo querySmsTemplateByType(Integer type) throws Exception;
}
