package com.cxdai.portal.account.vo;

import java.io.Serializable;

import com.cxdai.portal.borrow.vo.BorrowVo;
import com.cxdai.portal.collection.vo.BCollectionRecordVo;
import com.cxdai.portal.repayment.vo.BRepaymentRecordVo;

/**
 * <p>
 * Description:计算待收罚息Vo<br />
 * </p>
 * 
 * @title AccountVo.java
 * @package com.cxdai.portal.account.vo
 * @author justin.xu
 * @version 0.1 2014年4月25日
 */
public class UnReceiveInterestVo implements Serializable {
	private static final long serialVersionUID = -7100330584784625365L;
	/** 主键Id */
	private Integer id;
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/** 待收记录 */
	private BCollectionRecordVo bcollectionRecordVo;
	/** 借款标 */
	private BorrowVo borrowVo;
	/** 待还记录 */
	private BRepaymentRecordVo brepaymentRecordVo;

	public BCollectionRecordVo getBcollectionRecordVo() {
		return bcollectionRecordVo;
	}

	public void setBcollectionRecordVo(BCollectionRecordVo bcollectionRecordVo) {
		this.bcollectionRecordVo = bcollectionRecordVo;
	}

	public BorrowVo getBorrowVo() {
		return borrowVo;
	}

	public void setBorrowVo(BorrowVo borrowVo) {
		this.borrowVo = borrowVo;
	}

	public BRepaymentRecordVo getBrepaymentRecordVo() {
		return brepaymentRecordVo;
	}

	public void setBrepaymentRecordVo(BRepaymentRecordVo brepaymentRecordVo) {
		this.brepaymentRecordVo = brepaymentRecordVo;
	}

}
