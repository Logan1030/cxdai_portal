package com.cxdai.base.mapper;

import com.cxdai.base.entity.LotteryGoodsLimit;

public interface BaseLotteryGoodsLimitMapper {
    int insert(LotteryGoodsLimit record);

    int insertSelective(LotteryGoodsLimit record);

    LotteryGoodsLimit selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LotteryGoodsLimit record);

    int updateByPrimaryKey(LotteryGoodsLimit record);
}