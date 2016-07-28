package com.cxdai.base.mapper;

import com.cxdai.base.entity.LotteryGoodsSendInfo;

public interface BaseLotteryGoodsSendInfoMapper {
    int insert(LotteryGoodsSendInfo record);

    int insertSelective(LotteryGoodsSendInfo record);

    LotteryGoodsSendInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LotteryGoodsSendInfo record);

    int updateByPrimaryKey(LotteryGoodsSendInfo record);
}