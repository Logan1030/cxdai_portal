package com.cxdai.portal.red.service;

import java.util.List;
import java.util.Map;

import com.cxdai.base.entity.Member;
import com.cxdai.common.page.Page;
import com.cxdai.portal.red.entity.RedAccount;
import com.cxdai.portal.red.entity.RedAccountCnd;
import com.cxdai.portal.red.entity.RedAccountLog;

public interface RedAccountService {

	public int getUnuseCount(int userId);

	/**
	 * 查询我的红包，每种面额取一个到期时间最近的
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author huangpin
	 * @version 0.1 2015年11月6日
	 * @param userId
	 * @return List<RedAccount>
	 */
	List<RedAccount> getMyReds(int userId);

	public Page queryPageByExpiredRed_hb(RedAccountCnd redAccountCnd, Page page) throws Exception;

	public List<RedAccount> queryPageByOpen_hb(int userId) throws Exception;

	public void updateRedDotState(Integer userId) throws Exception;

	public RedAccount queryRedDotState(Integer userId) throws Exception;

	public RedAccountLog updateRedByZhitongche(int redId, String ip, int USEBIZ_ID) throws Exception;

	List<RedAccount> getMyRuleReds(Map map);

	/**
	 * <p>
	 * Description:发送红包模块<br />
	 * </p>
	 * 
	 * @author yubin
	 * @version 0.1 2015年12月17日
	 * @throws Exception
	 *             void
	 */
	public void insertRedByRegister(Member member, String ip, String mobileNum) throws Exception;
}
