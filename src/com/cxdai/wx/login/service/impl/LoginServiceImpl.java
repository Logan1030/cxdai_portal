package com.cxdai.wx.login.service.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cxdai.portal.account.mapper.InviterMapper;
import com.cxdai.portal.member.vo.MemberVo;
import com.cxdai.wx.entry.bind.mapper.BindVoMapper;
import com.cxdai.wx.login.service.LoginService;

/**
 * <p>
 * Description:登录处理类
 * </p>
 * 
 * @title AccountService.java
 * @package com.cxdai.account.service
 * @author justin.xu
 * @version 0.1 2014年4月16日
 */
@Service
public class LoginServiceImpl implements LoginService {
	@Autowired
	private BindVoMapper bindMapper;
	@Autowired
	private InviterMapper inviterMapper;

	/**
	 * 根据operId查询用户名和密码
	 */
	@Override
	public MemberVo queryMemberByWxId(Integer wId) {
		return bindMapper.selectByWxId(wId);
	}

	@Override
	public BigDecimal queryInvestTotalByUserId(Integer userId) {
		return inviterMapper.queryInvestTotalByUserId(userId);
	}

}
