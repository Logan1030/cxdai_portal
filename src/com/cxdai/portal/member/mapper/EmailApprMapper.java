package com.cxdai.portal.member.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cxdai.portal.member.vo.EmailApproCnd;
import com.cxdai.portal.member.vo.EmailApproVo;

/**
 * <p>
 * Description:邮箱认证数据访问类<br />
 * </p>
 * 
 * @title EmailApprMapper.java
 * @package com.cxdai.portal.member.mapper
 * @author justin.xu
 * @version 0.1 2014年4月23日
 */
public interface EmailApprMapper {
	/**
	 * <p>
	 * Description:根据条件查询对象集合<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年4月23日
	 * @param emailApproCnd
	 * @return
	 * @throws Exception
	 *             List<EmailApproVo>
	 */
	public List<EmailApproVo> queryEmailApprList(EmailApproCnd emailApproCnd)
			throws Exception;

	/**
	 * <p>
	 * Description:根据条件查询对象集合数量<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年4月23日
	 * @param emailApproCnd
	 * @return
	 * @throws Exception
	 *             Integer
	 */
	public Integer queryEmailApprCount(EmailApproCnd emailApproCnd)
			throws Exception;
}
