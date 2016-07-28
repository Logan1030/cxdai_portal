package com.cxdai.portal.member.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cxdai.portal.member.mapper.StaffMapper;
import com.cxdai.portal.member.service.StaffService;
import com.cxdai.portal.member.vo.StaffCnd;
import com.cxdai.portal.member.vo.StaffVo;

@Service
public class StaffServiceImpl implements StaffService {
	public Logger logger = Logger.getLogger(StaffServiceImpl.class);

	@Autowired
	private StaffMapper staffMapper;

	@Override
	public List<StaffVo> queryStaffList(StaffCnd staffCnd) throws Exception {
		return staffMapper.queryStaffList(staffCnd);
	}

}
