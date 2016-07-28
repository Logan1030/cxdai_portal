<%@page import="com.cxdai.utils.DateUtils"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<!doctype html>
<html>
<head>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<meta charset="utf-8">
<title>新春活动</title>
<link href="${basePath}/css/newp/style.css" rel="stylesheet"
	type="text/css" />
<link href="${basePath}/css/newp/layout.css" rel="stylesheet"
	type="text/css" />

<style type="text/css">
.btn_wancheng {
	text-align: center;
	width: 166px;
	height: 53px;
	background: #ea5913;
	line-height: 53px;
}
}
</style>
<!-- 百度统计代码 -->
<script>
	var _hmt = _hmt || [];
	(function() {
		var hm = document.createElement("script");
		hm.src = "//hm.baidu.com/hm.js?4149b06464223c795b9f11534606ae1c";
		var s = document.getElementsByTagName("script")[0];
		s.parentNode.insertBefore(hm, s);
	})();
</script>
</head>

<body>


	<!-- header start   -->
	<%@ include file="/WEB-INF/page/common/header.jsp"%>
	<!-- header end     -->

	<div class="all-page">
		<div class="all-top-jarn">
			<div class="main-container">
				<div class="new-button2">
					<a href="${path}/zhinan.html"></a>
				</div>
				<div class="regbg">

					<c:if test="${source == null || source == ''}">
						<a href="${path}/member/toRegister.html" class="white-c"><button
								type="button" class="reg-btn2"></button></a>
					</c:if>
					<c:if test="${source != null && source != ''}">
						<a href="${path}/member/toRegister.html?source=${source}"
							class="white-c"><button type="button" class="reg-btn2"></button></a>
					</c:if>
				</div>
			</div>
		</div>
		<div class="money-bg2"></div>
		<div class="moneybtnbg">
			<a href="${path}/dingqibao.html" class="white-c"><button
					type="button" class="money-btn2"></button></a>

		</div>
		<div class="newbg">
			<div class="main-new">
				<div class="pd">
					<div class="f28 white-c">
						【新人专享宝】<em class="ml20 mr10"><img
							src="${path}/images/newp/images_19.png" /></em><img
							src="${path}/images/newp/images_21.png" /> <span
							class="ml20 f24">新用户均有一次投资机会，限额10,000元</span>
					</div>
					<div class="mt30 white-c main-show">
						<table>
							<tr>
								<td style="  position:relative; "><em class="f36"
									style=" color:#fff;  ">
									<c:if test="${!empty fixBorrowNew}">
	                    				${fixBorrowNew.apr}%
	                    			</c:if>
	                    			<c:if test="${empty fixBorrowNew}">
	                    				15%
	                    			</c:if>
									</em></td>
								<td><em class="f36">10000.00元</em></td>
								<td><em class="f36">
								<c:if test="${!empty fixBorrowNew}">
                    				${fixBorrowNew.lockLimit}
                    			</c:if>
                    			<c:if test="${empty fixBorrowNew}">
                    				1
                    			</c:if>
								个月</em></td>
							</tr>
							<tr>
								<td><em class="f24">现利率</em></td>
								<td><em class="f24">金额</em></td>
								<td><em class="f24">期限</em></td>
							</tr>
						</table>
					</div>
					<div class="mt50">
						<div class="progress">
							<p class="rl">
								<span class="bar" style="width:${scheduleStr}%;"></span>
							</p>
							<p class="tc white-c f20 mt20">进度${scheduleStr}%</p>
						</div>
					</div>
					<div class="mt50">
						<c:if test="${fixBorrowNew.status==3 and fixBorrowNew.flagJoin==2}">
                    		<div class="tb-btn2 mg f24"
								style="background:#bababa;cursor: pointer;">
								<a href="javascript:void(0);">预发中</a>
							</div>
                    	</c:if>                    	
						<c:if test="${fixBorrowNew.status==3 and fixBorrowNew.flagJoin==1}">
                   			<div class="tb-btn mg f24">
								<a href="javascript:openDingbao('${fixBorrowNew.id}');">投宝</a>
							</div>
                   		</c:if>                   	
						<c:if test="${fixBorrowNew.status >= 5 }">
							<div class="tb-btn2 mg f24">
								<a href="javascript:void(0);">已完成</a>
							</div>
						</c:if>
						<c:if test="${empty fixBorrowNew}">
                 			预发中
                 		</c:if>
					</div>
				</div>
			</div>
		</div>
		<div class="footg">
			<div class="main-container pt80">
				<div class="footg-c2"></div>
			</div>
		</div>
	</div>

	<div class="clearfix"></div>

	<!-- foot start -->
	<%@ include file="/WEB-INF/page/common/footer.jsp"%>
	<!-- foot end -->
</body>
	<script type="text/javascript">
		//新手专区-投标		
		function openDingbao(borrowId){
			window.open("${path}/dingqibao/"+borrowId+".html"); 
		}
		</script>
</html>
