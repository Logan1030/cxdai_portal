package com.cxdai.portal.cms.mapper;

import org.apache.ibatis.annotations.Param;

import com.cxdai.portal.cms.vo.CmsArticleTag;

public interface CmsArticleTagMapper {

    int insert(CmsArticleTag record);

    int insertSelective(CmsArticleTag record);


    int updateByPrimaryKeySelective(CmsArticleTag record);

    int updateByPrimaryKey(CmsArticleTag record);

	void deleteArticleTag(@Param("articleId") Integer id, @Param("tags") String[] tagsSpl);

}