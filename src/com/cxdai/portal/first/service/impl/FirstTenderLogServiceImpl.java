package com.cxdai.portal.first.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cxdai.common.page.Page;
import com.cxdai.portal.first.mapper.FirstTenderLogMapper;
import com.cxdai.portal.first.service.FirstTenderLogService;
import com.cxdai.portal.first.vo.FirstTenderLogCnd;
import com.cxdai.portal.first.vo.FirstTenderLogVo;

/**
 * 
 * <p>
 * Description:直通车投标日志记录业务处理类<br />
 * </p>
 * 
 * @title FirstTenderLogServiceImpl.java
 * @package com.cxdai.portal.first.service.impl
 * @author yangshijin
 * @version 0.1 2015年3月11日
 */
@Service
public class FirstTenderLogServiceImpl implements FirstTenderLogService {

	@Autowired
	private FirstTenderLogMapper firstTenderLogMapper;

	@Override
	public Page queryPageListByCnd(FirstTenderLogCnd firstTenderLogCnd, Page page) throws Exception {
		Integer totalCount = firstTenderLogMapper.queryListCountByCnd(firstTenderLogCnd);
		page.setTotalCount(totalCount);
		List<FirstTenderLogVo> list = firstTenderLogMapper.queryListByCnd(firstTenderLogCnd, page);
		page.setResult(list);
		return page;
	}

	@Override
	public FirstTenderLogVo queryById(int id) throws Exception {
		return firstTenderLogMapper.queryById(id);
	}
}
