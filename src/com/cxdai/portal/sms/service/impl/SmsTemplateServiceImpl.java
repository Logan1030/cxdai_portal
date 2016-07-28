package com.cxdai.portal.sms.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cxdai.common.statics.Constants;
import com.cxdai.portal.sms.mapper.SmsTemplateMapper;
import com.cxdai.portal.sms.service.SmsTemplateService;
import com.cxdai.portal.sms.vo.SmsTemplateCnd;
import com.cxdai.portal.sms.vo.SmsTemplateVo;

@Service
public class SmsTemplateServiceImpl implements SmsTemplateService {
	public Logger logger = Logger.getLogger(SmsTemplateServiceImpl.class);

	@Autowired
	private SmsTemplateMapper smsTemplateMapper;

	@Override
	public SmsTemplateVo querySmsTemplateByType(Integer type) throws Exception {
		SmsTemplateCnd smsTemplateCnd = new SmsTemplateCnd();
		smsTemplateCnd.setType(type);
		smsTemplateCnd.setFlag(Constants.SMS_TEMPLATE_FLAG_YES);
		List<SmsTemplateVo> list = smsTemplateMapper
				.querySmsTemplateList(smsTemplateCnd);
		if (null != list && list.size() == 1) {
			return list.get(0);
		}
		return null;
	}
}
