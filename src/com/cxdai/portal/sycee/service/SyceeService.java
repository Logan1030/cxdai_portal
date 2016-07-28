package com.cxdai.portal.sycee.service;

import java.util.List;

import com.cxdai.portal.base.MessageBox;
import com.cxdai.portal.sycee.entity.SyceeGoods;

public interface SyceeService {

	/**
	 * 元宝商品分类列表
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author huangpin
	 * @version 0.1 2015年10月23日
	 * @param firstClass
	 * @return List<SyceeGoods>
	 */
	List<SyceeGoods> syceeClassList(int firstClass);

	/**
	 * 查询论坛签到帖，没有返回0
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author huangpin
	 * @version 0.1 2015年10月23日
	 * @return int
	 */
	int getSignItem();

	/**
	 * 商品详情
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author huangpin
	 * @version 0.1 2015年10月24日
	 * @param goodsId
	 * @return SyceeGoods
	 */
	SyceeGoods getGoodsInfo(int goodsId);

	/**
	 * 查询会员当前元宝
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author huangpin
	 * @version 0.1 2015年10月26日
	 * @param userId
	 * @return int
	 */
	int getUserSycee(int userId);

	/**
	 * 兑换商品
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author huangpin
	 * @version 0.1 2015年10月26日
	 * @param userId
	 * @param goodsId
	 * @return
	 * @throws Exception MessageBox
	 */
	MessageBox addExchange(int userId, int goodsId, String ip) throws Exception;
}
