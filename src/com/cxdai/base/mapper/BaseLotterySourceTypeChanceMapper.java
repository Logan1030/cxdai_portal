package com.cxdai.base.mapper;

import com.cxdai.base.entity.LotterySourceTypeChance;

public interface BaseLotterySourceTypeChanceMapper {
    int insert(LotterySourceTypeChance record);

    int insertSelective(LotterySourceTypeChance record);

    LotterySourceTypeChance selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LotterySourceTypeChance record);

    int updateByPrimaryKey(LotterySourceTypeChance record);
}