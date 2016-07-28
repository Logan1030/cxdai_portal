<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>联系我们-国诚金融官网</title>
<meta name="keywords" content="联系我们" />
<meta name="description"
	content="在国诚金融P2P网络借贷平台投资理财过程中，如有任何疑问，请与国诚金融客服人员联系。关于国诚金融详情请登陆www.gcjr.com。">
<%@ include file="/WEB-INF/page/common/public2.jsp"%>
<script type="text/javascript">
	$(function() {
		var nav = $('.navbar'), doc = $(document), win = $(window);
		win.scroll(function() {
			if (doc.scrollTop() > 70) {
				//alert("00")
				nav.addClass('scrolled');
			} else {
				nav.removeClass('scrolled');
			}
		});
		win.scroll();
		$("#contact").addClass("active");
	});
</script>

</head>
<body>
	<%@ include file="/WEB-INF/page/common/header.jsp" %> 
	<div class="aboutus-bigbox">
    <%@ include file="/WEB-INF/page/index/about-leftmain.jsp"%> 
		<div class="abt-content-box about-gsjj">
			<div class=" abt-content-box fzlcbox ">
			 <h1>联系我们</h1>
				<div>        
					<p>如果您对国诚金融<a href="http://www.gcjr.com/" style="padding:0 10px; color:#000;">www.gcjr.com</a>平台有任何疑问</p>
					<p>或在使用过程中出现什么问题</p>
					<p>请您与国诚金融客服人员联系</p>
				</div>
			</div>
		</div>
		<div class="jiandubigbox">
			<div class="abt-content-box gc-address">
				<h1 class="contactwe">国诚金融（总部）</h1>
				<p>© Copyright 2016 www.gcjr.com</p>
				<p>公司地址：上海市虹口区西江湾路388号凯德龙之梦A座32楼</p>
				<p>联系电话：021-80100999</p>
				<p>邮编：200083</p>

				<div class="maptitle">公司地址地图</div>
				<div style="margin: 0 auto;">
					<img src="${basePath}/images/v5/aboutus/map.jpg" />
				</div>
				<h1 class="contactwe rgfw">人工服务</h1>
				<p>
					财富热线：<span style="color: #35aaf2;">400-015-6676</span>
				</p>
				<p>在线QQ：<a   target="_blank"  href="http://www.gcjr.com/onLineServices/webQQ.html">800021305</a></p>
				<p style="padding-bottom: 50px;">客服服务时间：工作日（9:00-21:30）</p>
			</div>
		</div>
		<div class="abt-content-box lianxi3">
			<h1 class="contactwe rgfw" style="border-bottom: #c5c4c4 solid 1px;">商务合作</h1>
			<p>
				如果您有意向与我们合作，请将合作意向文档发送至：国诚金融渠道负责人【刘小姐】：marketing@gcjr.com，
				<!-- <a href="mailto:xujingbo@gcjr.com" style="color: #35aaf2;">xujingbo@gcjr.com，</a> -->我司工作人员会尽快与您联系
			</p>
			<h1 class="contactwe rgfw" style="border-bottom: #c5c4c4 solid 1px;">媒体合作</h1>
			<p>
				如媒体有采访需求或合作需要，请将媒体名称、采访提纲、联系方式等相关资料发送到 marketing@gcjr.com<!-- <a href="mailto:marketing@gcjr.com" ><span style="color: #35aaf2;">marketing@gcjr.com</span></a>
				 -->或致电021-80100993（国诚金融市场部），我们将尽快与您联系
			</p>
		</div>
		<style>
</style>
		<div class="jiandubigbox">
			<div class="abt-content-box lianxi4">
				<h1 class="contactwe rgfw" style="border-bottom: #c5c4c4 solid 1px;">了解我们</h1>
				<p style="padding-top: 40px;">
					国诚金融微信账号：<span>guochengjinrong (点击屏幕下方官方微信，扫描二维码关注国诚金融微信)</span>
				</p>
				<p>
					国诚金融新浪微博： <a href="http://weibo.com/p/1006063805446108/home?from=page_100606&amp;mod=TAB#place"><span> http://weibo.com/p/1006063805446108</span></a>
				</p>
				<p>
					国诚金融腾讯微博： <a href="http://t.qq.com/guochengjinrong"><span>http://t.qq.com/guochengjinrong</span></a>
				</p>
				<p>
					国诚金融新浪博客：<a href="http://blog.sina.com.cn/u/3805446108"><span> http://blog.sina.com.cn/u/3805446108</span></a>
				</p>
			</div>
		</div>
	</div>
     <div class="clearfix"></div>
    <%@ include file="/WEB-INF/page/common/footer.jsp" %> 
	<script type="text/javascript">
		function animate() {
			$(".charts").each(function(i, item) {
				var a = parseInt($(item).attr("w"));
				$(item).animate({
					width : a + "%"
				}, 2000);
			});
		}
		animate();
	</script>
</body>
</html>