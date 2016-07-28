package com.cxdai.portal.electronbill.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cxdai.portal.electronbill.vo.AccountStatement;
import com.cxdai.portal.electronbill.vo.MonthlyInterst;

public interface AccountStatementMapper {

	String insert(@Param("userId") int userId, @Param("year") int year, @Param("month") int month);

	AccountStatement selectByUserId(@Param("userId") int userId, @Param("year") int year, @Param("month") int month);

	List<MonthlyInterst> selectMonthlyInterstList(@Param("userId") int userId, @Param("year") int year, @Param("month") int month);
}