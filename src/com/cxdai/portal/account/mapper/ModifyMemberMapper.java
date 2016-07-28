package com.cxdai.portal.account.mapper;

import com.cxdai.portal.account.vo.ModifyMember;

public interface ModifyMemberMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ModifyMember record);

    int insertSelective(ModifyMember record);

    ModifyMember selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ModifyMember record);

    int updateByPrimaryKey(ModifyMember record);
    
    ModifyMember findMemberById(Integer userId);
}