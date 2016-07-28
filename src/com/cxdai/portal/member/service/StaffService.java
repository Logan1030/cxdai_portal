package com.cxdai.portal.member.service;

import java.util.List;

import com.cxdai.portal.member.vo.StaffCnd;
import com.cxdai.portal.member.vo.StaffVo;

/**
 * <p>
 * Description:员工业务类<br />
 * </p>
 * 
 * @title StaffService.java
 * @package com.cxdai.portal.member.service
 * @author justin.xu
 * @version 0.1 2014年4月25日
 */
public interface StaffService {

	/**
	 * <p>
	 * Description:根据条件查询对象集合<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年4月23日
	 * @param staffCnd
	 * @return
	 * @throws Exception
	 *             List<StaffVo>
	 */
	public List<StaffVo> queryStaffList(StaffCnd staffCnd) throws Exception;
}
