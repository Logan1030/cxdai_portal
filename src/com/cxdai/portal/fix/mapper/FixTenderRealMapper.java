package com.cxdai.portal.fix.mapper;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.cxdai.base.entity.FixTenderReal;
import com.cxdai.common.page.Page;
import com.cxdai.portal.fix.vo.FixTenderRealCnd;
import com.cxdai.portal.fix.vo.FixTenderRealVo;

/**
 * <p>
 * Description:定期宝最终认购记录数据库接口类<br />
 * </p>
 * 
 * @title FixTenderRealMapper.java
 * @package com.cxdai.portal.fix1.mapper
 * @author caodefeng
 * @version 0.1 2015年5月18日
 */
public interface FixTenderRealMapper {
	/**
	 * 添加最终认购记录
	 * @param fixTenderReal
	 */
	public Integer insertFixTenderReal(FixTenderReal fixTenderReal) throws Exception;
	
	
	/**
	 * 统计定期宝认购总次数
	 * @param id
	 * @return
	 */
	public Integer queryTotalSYCounts(FixTenderRealCnd fixTendeRealCnd);
	
	/**
	 * 统计定期宝认购总次数
	 * @param id
	 * @return
	 */
	public List<FixTenderRealVo> queryTotalSYByPage(FixTenderRealCnd fixTendeRealCnd,Page page);
	
	
	/**
	 * 统计定期宝认购总次数
	 * @param id
	 * @return
	 */
	public Integer queryTotalTCCounts(FixTenderRealCnd fixTendeRealCnd);
	
	/**
	 * 统计定期宝认购总次数
	 * @param id
	 * @return
	 */
	public List<FixTenderRealVo> queryTotalTCByPage(FixTenderRealCnd fixTendeRealCnd,Page page);
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 陈建国
	 * @version 0.1 2015年5月23日
	 * @param fixTenderDetailCnd
	 * @return
	 * FixTenderDetailVo
	 */


	public  List<FixTenderRealVo> getFixTenderRealstaticByBorrowId(FixTenderRealCnd fixTenderRealCnd);
	
	
	/**
	 * 退出信息统计
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 陈建国
	 * @version 0.1 2015年5月23日
	 * @param fixTenderRealCnd
	 * @return
	 * List<FixTenderRealVo>
	 */


	public  List<FixTenderRealVo> getTcstaticByBorrowId(FixTenderRealCnd fixTenderRealCnd);
	
	/**
	 * 查询最大排序号
	 * @return
	 */
	public Integer queryMaxOrderNum();
	
	
	/**
	 * 根据定期宝收益中的预期收益
	 * @param  
	 * @return
	 */
	public BigDecimal  getYqsyBySzy(FixTenderRealCnd fixTenderRealCnd) throws Exception;
	
	/**
	 * 根据用户ID获取有效认购总额
	 * @author WangQianJin
	 * @title @param firstBorrowId
	 * @title @param userId
	 * @title @return
	 * @title @throws Exception
	 * @date 2015年8月25日
	 */
	public Integer findAccountTotalByUserIdAndFixBorrowId(@Param("fixBorrowId") Integer fixBorrowId, @Param("userId") Integer userId) throws Exception;
	
	
}
