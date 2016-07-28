package com.cxdai.portal.member.mapper;

import org.apache.ibatis.annotations.Param;

import com.cxdai.portal.member.vo.BlackListVo;

/**
 * 
 * <p>
 * Description:黑名单类数据访问类<br />
 * </p>
 * 
 * @title BlackListMapper.java
 * @package com.cxdai.base.mapper
 * @author yangshijin
 * @version 0.1 2015年1月25日
 */
public interface BlackListMapper {

	/**
	 * 
	 * <p>
	 * Description:根据id查询对象<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2015年1月25日
	 * @param id
	 * @return
	 * @throws Exception BlackListVo
	 */
	public BlackListVo queryById(Integer id) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:根据id查询对象，并锁定<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2015年1月25日
	 * @param id
	 * @return
	 * @throws Exception BlackListVo
	 */
	public BlackListVo queryByIdForUpdate(Integer id) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:根据userId和type查询是否已存在禁止中的记录<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2015年1月25日
	 * @param userId
	 * @param type
	 * @return
	 * @throws Exception Integer
	 */
	public Integer queryCountByUserIdAndType(@Param("userId") Integer userId, @Param("type") Integer type) throws Exception;
}
