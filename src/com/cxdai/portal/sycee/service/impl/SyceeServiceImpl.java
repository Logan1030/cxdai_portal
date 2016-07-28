package com.cxdai.portal.sycee.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cxdai.base.entity.LotteryChanceInfo;
import com.cxdai.base.entity.MemberAccumulatePoints;
import com.cxdai.base.mapper.BaseLotteryChanceInfoMapper;
import com.cxdai.base.mapper.BaseMemberAccumulatePointsMapper;
import com.cxdai.portal.base.MessageBox;
import com.cxdai.portal.member.mapper.MemberMapper;
import com.cxdai.portal.member.vo.MemberVo;
import com.cxdai.portal.red.entity.RedAccount;
import com.cxdai.portal.red.entity.RedAccountLog;
import com.cxdai.portal.red.mapper.RedAccountLogMapper;
import com.cxdai.portal.red.mapper.RedAccountMapper;
import com.cxdai.portal.sycee.entity.SyceeExchange;
import com.cxdai.portal.sycee.entity.SyceeGoods;
import com.cxdai.portal.sycee.mapper.SyceeExchangeMapper;
import com.cxdai.portal.sycee.mapper.SyceeGoodsMapper;
import com.cxdai.portal.sycee.service.SyceeService;

@Service
public class SyceeServiceImpl implements SyceeService {

	@Autowired
	SyceeGoodsMapper goodsMapper;

	@Autowired
	SyceeExchangeMapper exchangeMapper;

	@Autowired
	BaseMemberAccumulatePointsMapper userSyceeMapper;

	@Autowired
	MemberMapper memberMapper;

	@Autowired
	BaseLotteryChanceInfoMapper baseLotteryChanceInfoMapper;

	@Autowired
	RedAccountMapper redMapper;

	@Autowired
	RedAccountLogMapper redLogMapper;

	@Override
	public List<SyceeGoods> syceeClassList(int firstClass) {
		return goodsMapper.getByProperty("first_class", firstClass);
	}

	@Override
	public int getSignItem() {
		Integer id = goodsMapper.getSignItem();
		return id == null ? 0 : id;
	}

	@Override
	public SyceeGoods getGoodsInfo(int goodsId) {
		return goodsMapper.getById(goodsId);
	}

	@Override
	public int getUserSycee(int userId) {
		return userSyceeMapper.getUserSycee(userId);
	}

	@Override
	public MessageBox addExchange(int userId, int goodsId, String ip) throws Exception {
		SyceeGoods g = goodsMapper.getById(goodsId);
		int userSycee = userSyceeMapper.getUserSycee(userId);// 用户当前元宝
		int needSycee = g.getSycee() * g.getNum();// 兑换所需元宝
		if (userSycee < needSycee) {
			return new MessageBox("0", "您的元宝不足");
		}
		if (g.getShowFlag().intValue() != 1 || g.getApproveStatus().intValue() != 1) {
			return new MessageBox("0", "该商品不能兑换");// 商品处于上架&审核通过状态才可兑换
		}

		// 添加兑换记录
		SyceeExchange e = new SyceeExchange();
		e.setSyceeGoodsId(goodsId);
		e.setSycee(needSycee);
		e.setUserId(userId);
		if (g.getFirstClass().intValue() == 1) {// 线上商品
			e.setDealStatus(1);// 已处理
			e.setDealUsername("系统");
			e.setDealRemark("线上商品系统自动兑换");
		}
		if (g.getFirstClass().intValue() == 2) {// 线下商品
			e.setDealStatus(0);// 未处理
		}
		int n = exchangeMapper.add(e);
		if (n == 1) {
			// 扣除会员元宝
			memberMapper.queryMemberByIdForUpdate(userId);
			memberMapper.updateAccumulatePoints(userId, -needSycee);
			MemberVo m = memberMapper.queryMemberById(userId);

			// 记录元宝明细
			MemberAccumulatePoints memberPoint = new MemberAccumulatePoints();
			memberPoint.setAccumulatePoints(-needSycee);// 交易元宝
			memberPoint.setDetail("兑换" + g.getName());// 交易明细
			memberPoint.setType(102);// 元宝兑换商品
			memberPoint.setTargetId(g.getId());
			memberPoint.setPointSmagnification(1);
			memberPoint.setMemberId(userId);
			memberPoint.setHonor(m.getHonor());
			memberPoint.setSycee(m.getAccumulatepoints());
			userSyceeMapper.insertEntity(memberPoint);

			// 发放-抽奖机会
			if (g.getFirstClass() == 1 && g.getSecondClass() == 2) {
				LotteryChanceInfo lc = new LotteryChanceInfo();
				lc.setAddIp(ip);
				lc.setLotteryChanceRuleInfoId(10);// 元宝商城兑换
				lc.setLotteryNum(g.getNum());
				lc.setRemark("元宝商城兑换商品【" + g.getName() + "】");
				lc.setUseNum(g.getNum());
				lc.setUserId(userId);
				lc.setUsername(m.getUsername());
				baseLotteryChanceInfoMapper.insert(lc);
				return new MessageBox("1", "choujiang");// 抽奖提示框特殊写在页面
			}

			// 发放-红包
			if (g.getFirstClass() == 1 && g.getSecondClass() == 1) {
				RedAccount red = new RedAccount();
				red.setMoney(new BigDecimal(g.getMoney()));
				red.setStatus(2);// 未使用
				red.setUserId(userId);
				red.setRedType(1950);// 1950元宝兑换红包
				red.setRemark("用户使用" + needSycee + "元宝兑换红包");
				redMapper.add(red);
				int redId = red.getId();

				RedAccountLog redLog = new RedAccountLog();
				redLog.setMoney(red.getMoney());
				redLog.setRedId(redId);
				redLog.setAddip(ip);
				redLog.setStatus(2);// 未使用
				redLog.setBizType(0);// 0创建
				redLog.setRemark(red.getRemark());
				redLog.setUserId(userId);
				redLogMapper.add(redLog);
			}
			return new MessageBox("1", "恭喜兑换" + g.getName() + "成功");
		} else {
			return new MessageBox("0", "兑换失败");
		}
	}
}
