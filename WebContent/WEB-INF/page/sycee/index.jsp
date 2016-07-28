<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" import="org.springframework.web.util.WebUtils,com.cxdai.portal.member.controller.MemberController,java.net.URLDecoder"%>
<%@ page import = "com.cxdai.portal.statics.BusinessConstants" %>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<link href="${basePath}/css/help.css?version=<%=version%>" rel="stylesheet" type="text/css" />
<title>元宝商城_国诚金融</title>

<%
Cookie userIdCookie = WebUtils.getCookie(request, MemberController.COOKIE_LOGIN_USERID);
String userId = "";
if (userIdCookie != null) {
	userId = URLDecoder.decode(userIdCookie.getValue(),"UTF-8");
}
%>
</head>
<body style="background-color: white;">
<%@ include file="/WEB-INF/page/common/header.jsp"%>

<!--积分商城—主要内容-->
<div class="integral-windows">
	<div class="banner-landing">
		<div class="banner-l">
			<img src="${path}/images/jfbanner.png" />
		</div>
		
		<%-- 未登录 --%>
		<c:if test="${empty userSycee }">
   		<div class="landing-r" id="unloginDiv"> 
   			<a href="#" onclick="$('#loginFm').show();$('#unloginDiv').hide();" class="landing-top">立即登录</a> 
   			<a href="${path }/member/toRegister.html" class="res-bt ressc"  target="_blank" >免费注册</a> 
   		</div>
   		
   		<%-- 登录表单 --%>
   		<form id="loginFm" name="loginFm" method="post" style="display: none;">
		<div class="landing-r landing-two" style="" >
			<div style="width: 224px; margin: 0 auto;">
				<input class="landing-box" type="text" name="username"  id="username" placeholder="手机号/用户名/邮箱" value="<%=userId %>" style="color: black;" maxlength="30"/>
			</div>
			<div style="width: 224px; margin: 0 auto; margin-top: 15px;">
				<input class="landing-box landing-password" type="password" name="passwd" id="passwd" placeholder="登录密码" style="color: black;" maxlength="30"/>
			</div>
			 <div style="width:224px; margin:0 auto; margin-top:15px;display:none;"  id="codeView"  >
		      	<i class="code fr"><a href="javascript:loadimage();"><img  id="randImage" width="93px" height="42" src="${basePath}/random.jsp"/></a></i>
		        <input class="landing-box landing-code" width="131px" name="checkCode" maxlength="4" value="验证码" onfocus="if(value=='验证码'){value=''}" onblur="if(value==''){value='验证码'}"/>
            </div>
			<div class="wj-password"   >
				<div class="ckbod-l">
					<label> <input type="checkbox" id="saveidCB" name="saveidCB" <%=((userIdCookie != null) ? "checked='true'": "")%>/> 记住我 </label>
				</div>
				<div class="wj-psword-r">
					<a href="${path }/AccountSafetyCentre/safetyCentre/findPhone.html">忘记密码</a>
				</div>
			</div>
			<div class="tishino" id="loginTip"  style="margin-top:0"></div>
			<a href="javascript:login();" class="landing-top" style="margin-top: 15px;">立即登录</a>
			<div class="tishino reslj"> 
				没有账号？<a href="${path }/member/toRegister.html" target="_blank">立即注册</a>
			</div>
		</div>
		<input type="hidden" name="CSRFToken" id="CSRFToken" value="${csrf}" />
		</form>
		</c:if>
		
		<%-- 已登录 --%>
		<c:if test="${not empty userSycee }">
		<div class="landing-r landing-res">
			<div class="user-top" >
				<div class="user-l" >
					<a><img src="${path }/${userHeadImg }" /></a>
				</div>
				<div class="user-r" >
					<p class="user-r-top">我的元宝</p>
					<a href="${path }/account/sycee.html">
						<p class="user-yuanbao">
							<fmt:formatNumber value="${userSycee }" pattern="###,###" />
						</p>
					</a>
				</div>
			</div>
			<div class="touzi">
				<a href="${path }/dingqibao.html" target="_blank">去投资</a>
			</div>
			<div class="touzi qiandao">
				<c:if test="${signItem == 0}"><a onclick="layer.msg('签到帖还未发出',1,5);" style="cursor: pointer;">去签到</a></c:if>
				<c:if test="${signItem != 0}"><a href="${bbsPath }/posts/${signItem }/1" target="_blank">去签到</a></c:if>
			</div>
			<div class="touzi touzi yzsm">
				<a href="${path }/bangzhu/26.html" target="_blank">元宝详细说明</a>
			</div>
		</div>
		</c:if>
	</div>
	<div class="content-integral">
		<div class="integral-l">
			<img src="${path}/images/integral-l.png" />
		</div>
		<div class="integral-r">
			<%-- 线上商品 --%>
			<c:forEach items="${onlineGoods }" var="g">
			<div class="dh-box">
				<a class="jifen-box" style="position: relative;"  >
					<img src="${g.imgurl }" width="240" height="151" alt="${g.name }" title="${g.name }"/>
					<div class="box-md1">
						<div class="box-mdl">
							<span>兑换价格：</span>
							<span>
								<strong style="color: #d64060;">${g.sycee }</strong>元宝
							</span>
						</div>
						<input type="button" value="立即兑换" onclick="toChange(${g.sycee },${g.id })"/>
					</div>
				</a>
			</div>
			</c:forEach>
			<div class="dh-box">
					<img src="${path}/images/more-cp.png" />
			</div>
		</div>
	</div>
	<div class="content-integral" style="">
		<div class="integral-l">
			<img src="${path}/images/integral-2.png" />
		</div>
		<ul class="integral-r">
			<%-- 线下商品 --%>
			<c:forEach items="${offlineGoods }" var="g">
			<li class="dh-box">
				<a class="jifen-box" style="position: relative;">
					<img src="${g.imgurl }" width="240" height="151" alt="${g.name }" title="${g.name }"/>
					<div class="box-md1">
						<div class="box-mdl">
							<span>兑换价格：</span>
							<span>
								<strong style="color: #d64060;">${g.sycee }</strong>元宝
							</span>
						</div>
						<input type="button" value="立即兑换" onclick="toChange(${g.sycee },${g.id })"/>
					</div>
				</a>
			</li>
			</c:forEach>
			<li class="dh-box">
					<img src="${path}/images/more-cp.png" alt="敬请期待"/>
			</li>
		</ul>
	</div>
</div>
</div>
<!--积分商城—主要内容-->

<!--弹层—主要内容-->
<div id="goodsDiv" style="display: none;"><%@ include file="/WEB-INF/page/sycee/goodsDiv.jsp"%></div>

<div class="clearfix"></div>
<%@ include file="/WEB-INF/page/common/footer.jsp"%>

<script type="text/javascript">
$(function(){
	if('${counter}'>2){
		$('#codeView').css('display','block');
	}
});
/**
 * 去兑换
 */
function toChange(sycee,goodsId){
	var userSycee='${userSycee}';
	if(userSycee==''||userSycee==''){
		layer.msg('请先登录',2,5);return;
	}
	//查询商品详情
	$.ajax({
		url : '${basePath}/sycee/goods/'+goodsId+'.html',
		data : {},
		type : 'post',
		dataType : 'text',
		success : function(data) {
			$.layer({
			    type : 1,
			    title : false,
			    fix : false,
			    skin: 'layui-layer-rim', //加上边框
			    offset:['50px' , ''],
			    area : ['600','370px'],
			    page : {html : data}
			});
		},
		error : function(data) {
			layer.msg('网络连接异常，请刷新页面或稍后重试！', 1, 5);
		}
	});
}
/**
 * 提交兑换
 */
function subExchange(goodsId,goodsName,obj){
	$(obj).removeAttr("onclick");
	layer.confirm('您将兑换：'+goodsName, function(){
		$.ajax({
			url : '${basePath}/sycee/exchange/goods/'+goodsId+'.html',
			data : {},
			type : 'post',
			dataType : 'json',
			success : function(data) {
				if(data.code=='0'){
					layer.msg(data.message,2,5);
				}else{
					if(data.message=='choujiang'){
						$.ajax({
							url : "${path}/lottery_chance/queryLotteryChanceUseNumTotal.html",
							type : 'post',
							success : function(data){
								$("#chanceTotalNumTip").attr("style","display:;");
								$("#chanceTotalNumTip").html("<a href='${path }/lottery_chance/info.html' rel='nofollow'>(<em class='orange-c'>"+data+"</em>)</a>");
							}
						});
						$.layer({
							title:'兑换成功',
							dialog:{
								type:1,
								msg:'恭喜！您已使用元宝成功兑换'+goodsName+'，iPhone6、1888元大奖等你带走！',
							 	btns:2,
							 	btn: ['去抽奖','我知道了'],
							 	yes: function(index){
							 		window.location='${path}/lottery_chance/info.html';
							 	},
							 	no:function(index){
							 		//$('.xubox_layer').hide();
							 		//$('.xubox_shade').hide();
							 		window.location='${path}/sycee.html';
							 	}
							}
						 });
					}else{
						layer.msg(data.message,2,1,function(){
							window.location='${path}/sycee.html';
						});
					}
				}
			},
			error : function(data) {
				layer.msg('网络连接异常，请刷新页面或稍后重试！', 1, 5);
			}
		});
	});
	$(obj).attr("onclick","javascript:subExchange("+goodsId+",'"+goodsName+"',this);");
}
/**
 * 登录
 */
function login(){
	if($('#username').val()==''){
		layer.msg('手机号/用户名/邮箱不能为空',1,5);
		return;
	}
	if($('#passwd').val()==''){
		layer.msg('登录密码不能为空',1,5);
		return;
	}
	var _load;
	var _saveid=0;
	if($("#saveidCB")[0].checked==true){
		_saveid=1;
	}
	var headers={};
	 headers['__RequestVerificationToken'] = $('#CSRFToken').val();
	$("#loginFm").ajaxSubmit({
	    url: '${path }/member/login.html',
	    type: "POST",
	    data:{cookieusername:encodeURI($('#username').val()),saveid:_saveid},
	    headers: headers,
	    beforeSend:function(){
	    	_load = layer.load('登录中..');
	    },
	    success:function(data){
	    	$('#CSRFToken').val(data.newToken);
	    	loadimage();
	    	layer.close(_load);
	    	if (data.code == '0') {
				$("#loginTip").html(data.message);
				return;
			}else if(data.code == '3'){
				if(parseInt(data.counter)>2){
				 $('#codeView').css('display','block');
				}
				$("#loginTip").html(data.message);
				return;
			}
	    	if(data.code == '1') {
			    window.location='${path}/sycee.html';
			}else if(data.code == '2'){
				window.open("${path }/member/toCheckMemberInfo.html","_self");//前往注册认证界面
			}
	    },
		error : function() {
			layer.close(_load);
			layer.msg("网络连接超时，请您稍后重试", 2, 5);
	    } 
	 });
}
/**
 * 刷新验证码 
 */
 function loadimage() {
	document.getElementById("randImage").src = "${basePath}/random.jsp?"
			+ Math.random();
}  
</script>
</body>
</html>