package com.cxdai.portal.quickFinancing.mapper;

import com.cxdai.portal.quickFinancing.entity.QuickFinancing;

public interface QuickFinancingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(QuickFinancing record);

    int insertSelective(QuickFinancing record);

    QuickFinancing selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(QuickFinancing record);

     int updateByPrimaryKey(QuickFinancing record);
}