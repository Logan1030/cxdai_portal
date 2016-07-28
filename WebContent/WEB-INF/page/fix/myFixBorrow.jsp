<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>资金周转_投资理财平台_p2p网络投资理财-国诚金融官网</title>
<meta name="keywords" content="资金周转,投资理财平台,p2p网络投资理财" />
<meta name="description" content="国诚金融是中国3A信用评级互联网理财借贷平台，如果你想了解更多P2P理财、网络投资理财或者资金周转借贷信息，详情请登陆www.gcjr.com。">
<%@ include file="/WEB-INF/page/common/public2.jsp"%> 
</head>

<body>
<!--头部开始-->
<%@ include file="/WEB-INF/page/common/header.jsp"%>

<div class="product-wrap wrapperbanner">
	<div class="grid-1100">
       <div class="tz-detail">
           <img src="${path}/images/v5/detalibanner.png?version=<%=version%>"/>
           <ul class="col4"><li><a href="${path}/dingqibao.html" class="active">定期宝</a></li><li><a href="${path}/curInController.html">活期宝</a></li><li><a href="${path}/toubiao.html" >散标投资</a></li><li><a href="${path}/zhaiquan.html">债权转让</a></li></ul>
       </div>
	</div>
</div>
<div class="product-wrap">
	<div class="grid-1100">
		<div class="product-deatil">
			<h1 class="f16 bule">定期宝投资列表</h1>
            <div class="tz-dqb clearfix">
                <ul class="col4 center">
                    <li><div class=" pdtb10 center"><span class="gc-img-wap tz-icon01"></span></div><h2 class="f20 oren">${totalJoinCounts}次</h2><h4 class="f14">累计加入人次</h4></li>
                    <li><div class=" pdtb10 center"><span class="gc-img-wap tz-icon02"></span></div><h2 class="f20 oren"><fmt:formatNumber value="${totalAccountYes}" pattern="#,##0.##" />元</h2><h4 class="f14">累计金额</h4></li>
                    <li><div class=" pdtb10 center"><span class="gc-img-wap tz-icon03"></span></div><h2 class="f20 oren"><fmt:formatNumber value="${totalInterest}" pattern="#,##0.##" />元</h2><h4 class="f14">累计为用户赚取</h4></li>
                    <li><div class=" pdtb10 center"><span class="gc-img-wap tz-icon04"></span></div><h2 class="f20 oren">${fn:replace(totalAccountInUseRate,'%','')}%</h2><h4 class="f14">资金利用率</h4></li>
                </ul>
             </div>
             <div id="fixBorrowList"></div>
		</div>
	</div>
</div>
<%@ include file="/WEB-INF/page/common/footer.jsp"%>
</body>

<script type="text/javascript">

	
	$(document).ready(function() {
		searchFixBorrowListStart(1);
	});

	function searchFixBorrowListStart(pageNum) {
		$.ajax({
			url : '${basePath}/dingqibao/queryFixBorrowList.html',
			data : {
				pageNum : pageNum,
				pageSize : 10
			},
			type : 'post',
			dataType : 'text',
			success : function(data) {
				$("#fixBorrowList").html(data);
			},
			error : function(data) {
				alert("网络连接异常，请刷新页面或稍后重试！");
			}
		});
	}
</script>
</html>
