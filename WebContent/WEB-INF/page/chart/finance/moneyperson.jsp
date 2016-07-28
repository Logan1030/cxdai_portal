<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

    <table>
      <tr>
         <td colspan="2"><div align="center"> 总成交金额：</div></td>
         <td width="247" ><div align="center"><font color="#00a7e5">￥
            <fmt:formatNumber value="${resultMap.totalSuccessMoney }" pattern="#,##0.##" /></font></div></td>
         <td colspan="2"><div align="center">待收本息总额：</div></td>
         <td ><div align="center"><font color="#00a7e5">￥<fmt:formatNumber value="${resultMap.totalCollectionMoney }" pattern="#,##0.##" /></font></div></td>
      </tr>
      <tr>
         <td width="102" rowspan="5"><div align="center">其中：</div></td>
         <td width="99" ><div align="center">抵押标：</div></td>
         <td ><div align="center"><font color="#00a7e5">￥<fmt:formatNumber value="${resultMap.pledgeSuccessMoney}" pattern="#,##0.##" /></font></div></td>
         <td width="102" rowspan="4"><div align="center">其中：</div></td>
         <td width="92" ><div align="center">抵押标：</div></td>
         <td  ><div align="center"><font color="#00a7e5">￥<fmt:formatNumber value="${resultMap.pledgeCollectionMoney}" pattern="#,##0.##" /></font></div></td>
      </tr> 
      <tr>
         <td width="99" ><div align="center">净值标：</div></td>
         <td><div align="center"><font color="#00a7e5">￥<fmt:formatNumber value="${resultMap.netSuccessMoney}" pattern="#,##0.##" /></font></div></td>
         <td width="92" ><div align="center">净值标：</div></td>
         <td  ><div align="center"><font color="#00a7e5">￥<fmt:formatNumber value="${resultMap.netCollectionMoney}" pattern="#,##0.##" /></font></div></td>
      </tr>
      <tr>
        <td width="99" ><div align="center">信用标：</div></td>
        <td><div align="center"><font color="#00a7e5">￥<fmt:formatNumber value="${resultMap.recommendSuccessMoney}" pattern="#,##0.##" /></font></div></td>
        <td width="92" ><div align="center">信用标：</div></td>
        <td  ><div align="center"><font color="#00a7e5">￥<fmt:formatNumber value="${resultMap.recommendCollectionMoney}" pattern="#,##0.##" /></font></div></td>
      </tr>
      <tr>
        <td width="99" ><div align="center">担保标：</div></td>
        <td><div align="center"><font color="#00a7e5">￥<fmt:formatNumber value="${resultMap.guaranteeSuccessMoney}" pattern="#,##0.##" /></font></div></td>
        <td width="92" ><div align="center">担保标：</div></td>
        <td  ><div align="center"><font color="#00a7e5">￥<fmt:formatNumber value="${resultMap.guaranteeCollectionMoney}" pattern="#,##0.##" /></font></div></td>
      </tr>               
      <tr>
        <td width="99" ><div align="center">秒标：</div></td>
        <td><div align="center"><font color="#00a7e5">￥<fmt:formatNumber value="${resultMap.secondSuccessMoney}" pattern="#,##0.##" /></font></div></td>
        <td width="92" colspan="2" ><div align="center">投资者总收益：</div></td>
        <td  ><div align="center"><font color="#00a7e5">￥<fmt:formatNumber value="${resultMap.investerNetMoney}" pattern="#,##0.##" /></font></div></td>
      </tr>
      <tr>
        <td colspan="2"><div align="center"> 正常还款总额：</div></td>
        <td width="247" ><div align="center"><font color="#00a7e5">￥<fmt:formatNumber value="${resultMap.normalRepaymentMoney}" pattern="#,##0.##" /></font></div></td>
        <td colspan="2"><div align="center">累计借款者人数：</div></td>
        <td ><div align="center"><font color="#00a7e5"><fmt:formatNumber value="${resultMap.totalBorrowPersons}" pattern="#,##0.##" /></font></div></td>
      </tr>
      <tr>
         <td colspan="2"><div align="center"> 逾期总额：</div></td>
         <td width="247" ><div align="center"><font color="#00a7e5">￥<fmt:formatNumber value="${resultMap.overdueMoney}" pattern="#,##0.##" /></font></div></td>
         <td colspan="2"><div align="center">结清借款者人数：</div></td>
         <td ><div align="center"><font color="#00a7e5"><fmt:formatNumber value="${resultMap.settleBorrowPersons}" pattern="#,##0.##" /></font></div></td>
      </tr>
      <tr>
        <td colspan="2"><div align="center"> 逾期已处理总额：</div></td>
        <td width="247" ><div align="center"><font color="#00a7e5">￥<fmt:formatNumber value="${resultMap.overdueYesProcessedMoney}" pattern="#,##0.##" /></font></div></td>
        <td colspan="2"><div align="center">未结清借款者人数：</div></td>
        <td ><div align="center"><font color="#00a7e5"><fmt:formatNumber value="${resultMap.unclearedBorrowPersons}" pattern="#,##0.##" /></font></div></td>
      </tr>
   </table>
