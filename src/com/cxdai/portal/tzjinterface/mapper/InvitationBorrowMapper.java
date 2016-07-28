package com.cxdai.portal.tzjinterface.mapper;

import java.util.List;

import com.cxdai.portal.tzjinterface.vo.BorrowStateChangedVo;
import com.cxdai.portal.tzjinterface.vo.InvitationBorrowVo;
import com.cxdai.portal.tzjinterface.vo.TenderRecordAggVo;
import com.cxdai.portal.tzjinterface.vo.TzjTenderRecordCnd;
import com.cxdai.utils.exception.AppException;

public interface InvitationBorrowMapper {
	/**
	 * 
	 * <p>
	 * Description:查询可投标信息<br />
	 * </p>
	 * @author 胡建盼
	 * @version 0.1 2014年10月17日
	 * @return
	 * @throws AppException
	 * List<BorrowTransferLogVo>
	 */
	public List<InvitationBorrowVo> queryBorrowList() throws AppException;
	
	/**
	 * 根据查询条件获取投资记录
	 * @author WangQianJin
	 * @title @return
	 * @title @throws AppException
	 * @date 2015年11月29日
	 */
	public List<TenderRecordAggVo>  queryTenderRecord(TzjTenderRecordCnd tzjTenderRecordCnd) throws AppException;	
	
	/**
	 * 根据查询条件获取标的状态
	 * @author WangQianJin
	 * @title @param tzjTenderRecordCnd
	 * @title @return
	 * @title @throws AppException
	 * @date 2015年11月29日
	 */
	public List<BorrowStateChangedVo> queryStatusChangedBorrow(TzjTenderRecordCnd tzjTenderRecordCnd) throws AppException;

}
