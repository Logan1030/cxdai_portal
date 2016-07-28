<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<p class="red pd20 f16">债权剩余价值总额：<fmt:formatNumber value="${borrowCollectionAccountSum}" pattern="#,##0.00" />元   直通车可用余额：<fmt:formatNumber value="${useMoney}" pattern="#,##0.00" />元</p>
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="table center tbg">
         <tr class="tbl-title">
           <td>借款标题</td>
           <td>债权剩余价值</td>
           <td>剩余本金</td>
           <td>待收利息</td>
         </tr>
         <c:forEach items="${page.result}" var="transferBorrowDetail" varStatus="idx">
         <c:set var="btype" value="${transferBorrowDetail.borrowtype}" scope="request"/>
             <c:choose>   
				<c:when test="${btype == 1}">
					<c:set var="cls" value="icon-xin" scope="request"  />
				</c:when>   
				<c:when test="${btype == 2}">
					<c:set var="cls" value="icon-di" scope="request"  /> 
				</c:when>
				<c:when test="${btype == 3}">
					<c:set var="cls" value="icon-jing" scope="request"  />  
				</c:when>
				<c:when test="${btype == 4}">
					<c:set var="cls" value="icon-miao" scope="request"  />   
				</c:when>
				<c:when test="${btype == 5}">
					<c:set var="cls" value="icon-bao" scope="request"  />     
				</c:when>
				<c:otherwise></c:otherwise> 
		  </c:choose>
         <tr>
           <td><i class="icon ${cls} pdr"></i><a href="javascript:void(0);" style="color: #333" onclick="goToInvest(${transferBorrowDetail.borrowId})">${transferBorrowDetail.borrowName}</a></td>
           <td >￥<fmt:formatNumber value="${transferBorrowDetail.borrowCollectionAccount}" pattern="#,##0.00" /> </td>
		   <td >￥<fmt:formatNumber value="${transferBorrowDetail.borrowCollectionCapital}" pattern="#,##0.00" /> </td>
		   <td >￥<fmt:formatNumber value="${transferBorrowDetail.borrowCollectionInterest}" pattern="#,##0.00" /> </td>
         </tr>
         </c:forEach>
          
       
       
        
    </table>
                  


	<jsp:include page="/WEB-INF/page/common/ajaxpage.jsp">
		<jsp:param name="pageNo" value="${page.pageNo}" />
		<jsp:param name="totalPage" value="${page.totalPage}" />
		<jsp:param name="hasPre" value="${page.hasPre}" />
		<jsp:param name="prePage" value="${page.prePage}" />
		<jsp:param name="hasNext" value="${page.hasNext}" />
		<jsp:param name="nextPage" value="${page.nextPage}" />
	</jsp:include>		
		
<script type="text/javascript">
/*
 * 跳转投标页面
 */
function goToInvest(borrowId){
	window.open("${path}/toubiao/"+borrowId+".html"); 
};

/**
 * ajax 翻页功能
 */
function findPage(pageNo){
	window.parent.turnTransferBorrowListParent(pageNo);
}
</script>