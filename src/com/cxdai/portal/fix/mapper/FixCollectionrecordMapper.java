package com.cxdai.portal.fix.mapper;

import com.cxdai.base.entity.FixCollectionrecord;

/**
 * <p>
 * Description:投资人待收记录数据库接口类<br />
 * </p>
 * 
 * @title FixCollectionrecordMapper.java
 * @package com.cxdai.portal.fix.mapper
 * @author caodefeng
 * @version 0.1 2015年5月18日
 */
public interface FixCollectionrecordMapper {
	/**
	 * 添加用户待收记录
	 * @param fixCollectionrecord
	 * @return
	 */
	public Integer insertFixCollectionrecord(FixCollectionrecord fixCollectionrecord) throws Exception;
}
