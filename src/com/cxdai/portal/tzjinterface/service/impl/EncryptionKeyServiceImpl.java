package com.cxdai.portal.tzjinterface.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cxdai.portal.tzjinterface.exception.BusinessException;
import com.cxdai.portal.tzjinterface.mapper.EncryptionKeyMapper;
import com.cxdai.portal.tzjinterface.service.EncryptionKeyService;
import com.cxdai.portal.tzjinterface.vo.EncryptionKeyVo;

@Service
public class EncryptionKeyServiceImpl implements EncryptionKeyService{

	@Autowired
	EncryptionKeyMapper encryptionKeyMapper;
	@Override
	public EncryptionKeyVo queryNewestEncryptionKey(Integer keyId)
			throws BusinessException {
		return encryptionKeyMapper.queryNewestEncryptionKey(keyId);
	}

	

}
