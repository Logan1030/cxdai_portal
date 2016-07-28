package com.cxdai.portal.tzjinterface.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cxdai.portal.tzjinterface.mapper.RequesturlLogMapper;
import com.cxdai.portal.tzjinterface.service.RequesturlLogService;
import com.cxdai.portal.tzjinterface.vo.RequesturlLogVo;
import com.cxdai.utils.exception.AppException;

@Service
public class RequesturlLogServiceImpl implements  RequesturlLogService{
   
	@Autowired
	private RequesturlLogMapper requesturlLogMapper;
	
	@Override
	public RequesturlLogVo queryRequestUrlLog(Integer id) throws AppException {
		return requesturlLogMapper.queryRequestUrlLog(id);
	}

	@Override
	public Integer insertRequestUrlLog(RequesturlLogVo requesturlLogVo)
			throws AppException {
		return requesturlLogMapper.insertRequestUrlLog(requesturlLogVo);
	}

	@Override
	public Integer updateRequestUrlLog(RequesturlLogVo requesturlLogVo)
			throws AppException {
		return requesturlLogMapper.updateRequestUrlLog(requesturlLogVo);
	}

	@Override
	public Integer deleteRequestUrlLog(Integer id) throws AppException {
		return requesturlLogMapper.deleteRequestUrlLog(id);
	}

}
