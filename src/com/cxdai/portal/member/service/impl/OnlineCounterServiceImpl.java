package com.cxdai.portal.member.service.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cxdai.base.entity.Member;
import com.cxdai.base.entity.OnlineCounter;
import com.cxdai.base.mapper.BaseOnlineCounterMapper;
import com.cxdai.common.page.Page;
import com.cxdai.portal.member.mapper.OnlineCounterMapper;
import com.cxdai.portal.member.service.OnlineCounterService;
import com.cxdai.portal.member.util.BaiDuIp;
import com.cxdai.portal.member.vo.OnlineCounterCnd;
import com.cxdai.portal.member.vo.OnlineCounterVo;

@Service
public class OnlineCounterServiceImpl implements OnlineCounterService {

	@Autowired
	private OnlineCounterMapper onlineCounterMapper;
	@Autowired
	private BaseOnlineCounterMapper baseOnlineCounterMapper;

	@Override
	public List<OnlineCounterVo> queryListByCnd(OnlineCounterCnd onlineCounterCnd) throws Exception {
		return onlineCounterMapper.queryListByCnd(onlineCounterCnd);
	}

	@Override
	public OnlineCounter insertOnlineCounter(Member member, String system, HttpServletRequest request, HttpSession session) throws Exception {
		OnlineCounter onlineCounter = new OnlineCounter();
		Date nowtime = new Date();
		String ip = member.getIp();
		// 调用百度api获得位置
		BaiDuIp.IpAddr ipAddr = BaiDuIp.queryAddrByIp(ip);

		onlineCounter.setUserId(member.getId());
		onlineCounter.setUsername(member.getUsername());
		onlineCounter.setAddtime(nowtime);
		onlineCounter.setLogintime(nowtime);
		onlineCounter.setLastexisttime(nowtime);
		onlineCounter.setSessionid(session.getId());
		onlineCounter.setLastupdatetime(nowtime);
		onlineCounter.setAddip(ip);
		onlineCounter.setSystem(system);
		onlineCounter.setArea(ipAddr.getArea());
		onlineCounter.setProvince(ipAddr.getProvince());
		onlineCounter.setCity(ipAddr.getCity());
		onlineCounter.setPlatform(member.getPlatform());
		baseOnlineCounterMapper.insertEntity(onlineCounter);
		return onlineCounter;
	}

	@Override
	public OnlineCounterVo queryLastOnlineCounterByUserId(Integer userId) throws Exception {
		return onlineCounterMapper.queryLastOnlineCounterByUserId(userId);
	}

	@Override
	public Page queryPageByCnd(Integer userId, Page page) {
		page.setResult(onlineCounterMapper.queryListByUserId(userId, page));
		return page;
	}

}
