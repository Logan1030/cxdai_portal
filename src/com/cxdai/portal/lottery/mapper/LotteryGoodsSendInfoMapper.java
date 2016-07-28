package com.cxdai.portal.lottery.mapper;

import com.cxdai.portal.lottery.vo.LotteryGoodsSendInfoVo;

public interface LotteryGoodsSendInfoMapper {

    LotteryGoodsSendInfoVo selectByPrimaryKey(Integer id);
    
    
     //实物， 领取， 提交
    public int lingqu_inst(LotteryGoodsSendInfoVo lotteryGoodsSendInfoVo);

}