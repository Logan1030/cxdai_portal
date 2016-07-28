<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
 
	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="table center tbg">
             <tr class="tbl-title">
                <td >投标人</td>
                <td >投标金额</td>
                <td >投标时间	</td>
                <td >投资方式</td>
             </tr>
              <c:forEach items="${page.result}" var="tenderRecord" varStatus="status">
             <tr>
               <td  ><a   
                   <c:if test="${loginMember.userId == tenderRecord.userId}">style="color:blue;"</c:if>
                   <c:if test="${loginMember.userId != tenderRecord.userId}">style="color:#333;"</c:if>
                 href="${path}/accountdetail/show.html?userName=${portal:encode(portal:encode(tenderRecord.userNameEncrypt))}" target="_blank">${loginMember.userId == tenderRecord.userId?tenderRecord.username:tenderRecord.userNameSecret}<jsp:include page="/WEB-INF/page/common/showuserlevel.jsp"><jsp:param value="${tenderRecord.userLevel}" name="userLevel"/><jsp:param value="${path}" name="path"/></jsp:include></a></td>
               <td >￥<fmt:formatNumber value="${tenderRecord.account }" pattern="#,##0.##"/></td>
               <td ><fmt:formatDate value="${tenderRecord.addtimeDate }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
               <td >
                    <c:if test="${tenderRecord.platform==2 }"><img src="${basePath}/images/weixin_small.png" title="微信投标"/></c:if>
                              		<c:if test="${tenderRecord.platform!=2 && tenderRecord.tenderType==0}">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</c:if>
                              		<c:if test="${tenderRecord.tenderType==0}">手动投标
                              		</c:if>
                              		<c:if test="${tenderRecord.tenderType==1}">
                              			自动投标（第${tenderRecord.autotenderOrder}位）
                              		</c:if>
                              		<c:if test="${tenderRecord.tenderType==2 && tenderRecord.autotenderOrder != null}">
                              			<font color="red">直通车投标（第${tenderRecord.autotenderOrder}位）</font>
                              		</c:if>
                              		<c:if test="${tenderRecord.tenderType==2 && tenderRecord.autotenderOrder == null}">
                              			<a href="#" title="第${tenderRecord.firstPeriods}期投标直通车(${tenderRecord.firstBorrowScale})"><font color='red'>第${tenderRecord.firstPeriods}期投标直通车</font>..</a>
                                       </c:if> 
                              		<c:if test="${tenderRecord.tenderType==3 }">
                              			<font color='red'>定期宝(${tenderRecord.firstBorrowName})</font>
                              		</c:if>
                                   <c:if test="${tenderRecord.tenderType==4}">
                              			权证人员投标
                              		</c:if> 
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
<style>
.tbg tr:nth-of-type(odd){background:#f0f7ff;}
</style>
<script type="text/javascript">
function findPage(pageNum){
	searchTenderRecordList(pageNum);
}
</script>