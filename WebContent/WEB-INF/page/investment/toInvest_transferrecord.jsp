<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>

<table width="100%" border="0" cellspacing="0" cellpadding="0" class="table center tbg">
      <tr class="tbl-title">
        <td >交易时间</td>
        <td >债权卖出者</td>
        <td >债权买入者</td>
        <td >交易金额</td>
        <td >债权详情</td>
      </tr>
        <c:forEach   var="subscribeVo"  items="${page.result}"   varStatus="index"> 
         <tr>
             <td ><fmt:formatDate value="${subscribeVo.addTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>      
             <td class="bule">
           		<c:if test="${subscribeVo.isTransfer == 0 || subscribeVo.isTransfer == 1}">
           			<a href="${path}/accountdetail/show.html?userName=${portal:encode(portal:encode(subscribeVo.transferUserNameEncrypt))}" target="_blank">${subscribeVo.transferUserNameSecret}</a>
           		</c:if>
           		<c:if test="${subscribeVo.isTransfer == 2}">
           			<a href="javascript:toFixBorrowDetail(${subscribeVo.oldFixId})">${subscribeVo.transferUserName}</a>
           		</c:if>
             </td>
             <td class="bule">
           		<c:if test="${subscribeVo.isTransfer == 0 || subscribeVo.isTransfer == 1}">
           			<a href="${path}/accountdetail/show.html?userName=${portal:encode(portal:encode(subscribeVo.userNameEncrypt))}" target="_blank">${subscribeVo.userNameSecret} </a>
           		</c:if>
           		<c:if test="${subscribeVo.isTransfer == 2}">
           			<a href="javascript:toFixBorrowDetail(${subscribeVo.newFixId})">${subscribeVo.userName}</a>
           		</c:if>
             </td>
             <td >￥${subscribeVo.account}</td>
             <td class="bule">
             	<c:if test="${subscribeVo.isTransfer == 0}"><a href="${path }/zhaiquan/${subscribeVo.transferId}.html">查看详情</a></c:if> 
             	<c:if test="${subscribeVo.isTransfer == 1}"><a href="${path }/zhitongche/zhuanrang/queryTransferById/${subscribeVo.transferId}.html">查看详情</a></c:if>
             	
             </td>
      </tr>
       </c:forEach>
</table>
 <div class="yema">
	<div class="yema_cont">
          <div class="yema rt">
              <jsp:include page="/WEB-INF/page/common/ajaxpage.jsp">
					<jsp:param name="pageNo" value="${page.pageNo}" />
					<jsp:param name="totalPage" value="${page.totalPage}" />
					<jsp:param name="hasPre" value="${page.hasPre}" />
					<jsp:param name="prePage" value="${page.prePage}" />
					<jsp:param name="hasNext" value="${page.hasNext}" />
					<jsp:param name="nextPage" value="${page.nextPage}" />
				</jsp:include>
          </div>
      </div>  
</div>
<script type="text/javascript">
function findPage(pageNum){
	searchBorrowSubscribes(pageNum);
}
/**
 * 定期宝详细
 */
function toFixBorrowDetail(fixBorrowId){
	window.open("${basePath}/dingqibao/"+fixBorrowId+".html"); 
};
$(document).ready(function(){ 
    var color="#f0f7ff"
    $(".tbtr112 tr:even").attr("bgcolor",color);//改变奇数行背景色
})
</script>