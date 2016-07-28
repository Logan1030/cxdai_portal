<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>

<div class="" >
	<div class="tanchuang">
		<div class="tc-l" style="height: 350px;">
			<div class="yxq-box" style="">
				<img src="${goods.imgurl }" width="240" height="151"/>
				
				<c:if test="${goods.validDay==0 }"><P  style="color:#fff;"></P></c:if>
				<c:if test="${goods.validDay>0 }"><P class="yxq" style="color:#fff;">有效期${goods.validDay }天</P></c:if>
			</div>
			<div>
				<c:if test="${not empty goods.exchangeExp and goods.exchangeExp != ''}">
					<p>兑换方法</p>
					<p style="padding-bottom: 10px;">${goods.exchangeExp }</p>
				</c:if>
				<c:if test="${not empty goods.useWay and goods.useWay != ''}">
					<p>使用方法</p>
					<p style="padding-bottom: 10px;">${goods.useWay }</p>
				</c:if>
				<c:if test="${not empty goods.useExp and goods.useExp != ''}">
					<p>使用说明</p>
					<p>${goods.useExp }</p>
				</c:if>
			</div>
		</div>
		<div class="tc-r">
			<p>
				兑换价格：<strong style="color: #d64060; font-size: 20px;"><fmt:formatNumber value="${goods.sycee }" pattern="###,###" /></strong>元宝
			</p>
			<p style="padding: 10px 0 35px 0;">
				数量：<strong style="color: #d64060; font-size: 20px;">${goods.num }</strong>个
			</p>
			<p>
			  <c:if test="${surplusSycee>=0 }">
			  <a href="javascript:;" onclick="subExchange(${goods.id },'${goods.name }',this)">立即兑换</a>
			  </c:if>
			  <c:if test="${surplusSycee<0}">
			  <a style="background-color:#B0B0AF">立即兑换</a>
			  </c:if>
			</p>
			<p style="color: #999; padding-top: 30px;">
				<c:if test="${surplusSycee>=0 }">
			 		兑换后剩余：<strong style="font-size: 20px;"><fmt:formatNumber value="${surplusSycee }" pattern="###,###" /></strong> 元宝
			  	</c:if>
			   	<c:if test="${surplusSycee<0}">
					兑换后剩余：<strong style="font-size: 20px;"></strong>元宝不足
			  	</c:if>
			</p>
			<%-- 
			<a class="guanbi" onclick="$('#goodsDiv').hide();" style="cursor: pointer;">
				<img src="${path}/images/guan.png" />
			</a>
			--%>
		</div>
	</div>
</div>
