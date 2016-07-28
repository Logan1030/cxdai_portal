package com.cxdai.base.mapper;

import com.cxdai.base.entity.LotteryUseRecord;

public interface BaseLotteryUseRecordMapper {
    int insert(LotteryUseRecord record);

    int insertSelective(LotteryUseRecord record);

    LotteryUseRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LotteryUseRecord record);

    int updateByPrimaryKey(LotteryUseRecord record);
}