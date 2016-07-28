package com.cxdai.portal.autoInvestConfig.mapper;

import java.util.List;

import com.cxdai.common.page.Page;
import com.cxdai.portal.autoInvestConfig.entity.FixAutoInvestRecord;

public interface FixAutoInvestRecordMapper {
   
    int insert(FixAutoInvestRecord record);
    
    public List<FixAutoInvestRecord> queryFixAutoInvestRecordListForPage(Integer userId, Page page) throws Exception;
    
    public int queryFixAutoInvestRecordCount(Integer userId) throws Exception;
}