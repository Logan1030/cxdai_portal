<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>

<head>
<link href="${basePath}/css/circle.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${basePath}/js/superslide.2.1.js"></script>
<script type="text/javascript" src="${basePath}/js/common.js"></script>
</head>
<body>
		<table width="100%" border="0" cellspacing="0" cellpadding="0"  class="table center">
                  <tr class="tbl-title" align="center">
                    <td class="tdpdtitle30" width="8%">投标序号</td>
                    <td class="textr"  width="15%">发布时间</td>
                    <td class="textr" width="18%">转出价格</td>
                    <td class="textr" width="13%">奖励</td>
                    <td class="textr" width="13%">奖励比例</td>
                    <td class="tdtitl06" width="15%"></td>
                  </tr>
                  <c:forEach items="${page.result}" var="transfer" varStatus="idx">
                  
                  <tr>
                    <td class="tdtitl07 bule">
                     <c:if test="${null!=transfer.bidPassword && transfer.bidPassword!= ''}"><font color="#EE30A7">[密]</font></c:if> 
					 <a href="javascript:void(0);"  onclick="toTransfer(${transfer.id});">${transfer.ordernum}</a> 
                    </td>
                    <td class="textr" ><fmt:formatDate value="${transfer.addtime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                    <td class="textr"><fmt:formatNumber value="${transfer.accountReal}" pattern="#,##0.00" />元</td>
                    <td class="textr">
                       <fmt:formatNumber value="${transfer.awards}" pattern="#,##0.00" />元
                    </td>
                    <td class="textr">${transfer.awardApr}%</td>
                     
                    <td  class="tdtitl06">
                    <c:if test="${transfer.status==2}">
                      <button type="button" class="btn-middle btn-blue" onClick="toTransfer(${transfer.id });">转让中</button>
                    </c:if>
                    <c:if test="${transfer.status==4}">
                      <button type="button" class="btn-middle btn-ywc" onClick="toTransfer(${transfer.id });">已转让</button>
                    </c:if>
                      
                    </td>
                  </tr>
                  </c:forEach>  	
                 </table>				     
                                
				<c:if test="${page.result.size()==0}">
				       <center><b>没有可投资项目</b></center>
				</c:if>
 
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



</body>
<script type="text/javascript">
/**
 * ajax 翻页功能
 */
function findPage(pageNum){
	searchTransferFirstList(pageNum);
}

/**
 * 直通车转让详细跳转
 */
function toTransfer(transferId){
	window.open("${basePath}/zhitongche/zhuanrang/queryTransferById/"+transferId+".html"); 

};
</script>
