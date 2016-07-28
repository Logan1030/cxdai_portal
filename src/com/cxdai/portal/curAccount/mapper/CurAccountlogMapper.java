package com.cxdai.portal.curAccount.mapper;

import java.util.List;

import com.cxdai.common.page.Page;
import com.cxdai.portal.curAccount.entity.CurAccountlog;
import com.cxdai.portal.curAccount.vo.CurAccountLogCnd;
import com.cxdai.portal.curAccount.vo.CurAccountLogVo;

public interface CurAccountlogMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(CurAccountlog record);

	int insertSelective(CurAccountlog record);

	CurAccountlog selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(CurAccountlog record);

	int updateByPrimaryKey(CurAccountlog record);

	/**
	 * <p>
	 * Description:根据用户ID查询资金信息count <br />
	 * </p>
	 * @author HuangJun
	 * @version 0.1 2015年5月6日
	 * @param curAccountLogCnd
	 * @return
	 * Integer
	 */
	public Integer queryCurAccountLogCount(CurAccountLogCnd curAccountLogCnd);

	/**
	 * <p>
	 * Description:根据用户ID查询资金信息List<br />
	 * </p>
	 * @author HuangJun
	 * @version 0.1 2015年5月6日
	 * @param curAccountLogCnd
	 * @param page
	 * @return
	 * List<CurAccountLogVo>
	 */
	public List<CurAccountLogVo> queryCurAccountLogList(CurAccountLogCnd curAccountLogCnd, Page page);

}