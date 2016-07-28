package com.cxdai.base.mapper;

import com.cxdai.base.entity.LotteryGoods;

public interface BaseLotteryGoodsMapper {
    int insert(LotteryGoods record);

    int insertSelective(LotteryGoods record);

    LotteryGoods selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LotteryGoods record);

    int updateByPrimaryKey(LotteryGoods record);
}