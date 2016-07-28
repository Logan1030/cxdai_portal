<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%> 
<%@ include file="/WEB-INF/page/common/taglib.jsp"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 
<html> 
<head>
<title>关于我们-国诚金融官网</title>
<meta name="keywords" content="关于国诚金融" />
<meta name="description"
	content="国诚金融是专业的互联网金融公司，成立于2013年，总部位于国际金融中心上海。以其自主研发的技术平台和规范、透明的运作方式，成为成长最快、口碑最佳的平台之一。关于国诚金融详情请登陆www.gcjr.com。">
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
		$("#introduction").addClass("active");
	});
</script>
</head>
<body>
<%@ include file="/WEB-INF/page/common/header.jsp" %> 
	<div class="aboutus-bigbox">
      <%@ include file="/WEB-INF/page/index/about-leftmain.jsp"%> 
		<div class="abt-content-box about-gsjj">
			<div class="introduce-l">
				<img src="${basePath}/images/v5/aboutus/leftimg.jpg" />
			</div>
			<div class="introduce-r">
				<p class="text-p-title" style="padding-top: 0px;">公司简介</p>
				<p class="text-p">上海国诚金融信息服务有限公司是专业的互联网金融公司（www.gcjr.com），成立于2013年5月，注册资金5000万，总部位于国际金融中心上海。</p>
				<p class="text-p">旗下P2P（个人对个人）互联网借贷信息服务平台——国诚金融，于2013年9月26日正式上线运营，专注于上海地区的房产抵押平台，以其自主研发的技术平台和规范、透明的运作方式，成为成长最快、口碑最佳的平台之一。
				</p>
				<p class="text-p">国诚金融平台自上线运营以来，严格遵守国家政策和法律对P2P网络借贷平台的规范和要求，始终恪守P2P网贷平台的中介服务本质。已成长成为最大的房产抵押网贷平台</p>
				<p class="text-p-title">企业口号</p>
				<p class="text-p">用心呵护您的财富，分享成长的快乐</p>
				<p class="text-p-title">企业理念</p>
				<p class="text-p">诚信、规范、专业、共赢</p>
				<p class="text-p-title">企业愿景</p>
				<p class="text-p">成为中国互联网金融行业的领导品牌！</p>
				<p class="text-p-title">企业使命</p>
				<p class="text-p">推动普惠金融在中国的发展，让借钱难成为历史，让人人理财成为现实！</p>
				<p class="text-p-title">企业价值</p>
				<p class="text-p">帮助中国中小微企业和个人解决融资需求，帮助投资者实现财富保值增值，
					达成借款者、投资者和平台的三方共赢！</p>
			</div>
			<div class="clearfix"></div>
		</div>
		<div class="ab-zhengjian" style="overflow: hidden;">
			<div class="abt-content-box">
				<div class="abzj-l">
					<h1>证件资照</h1>
					<ul>
						<li><strong>证件名称</strong><span> 营业执照</span></li>
						<li><strong>机构名称</strong><span> 上海国诚金融信息服务有限公司</span></li>
						<li><strong>地址</strong><span> 上海市虹口区西江湾路388号A栋29层</span></li>
						<li><strong>注册号</strong><span> 310115002122520</span></li>
					</ul>
				</div>
				<div class="abzj-r">
					<img src="${basePath}/images/v5/aboutus/zjimg1.png" />
				</div>
				<div class="clearfix"></div>
			</div>
			<div class="abt-content-box">
				<div class="abzj-l">
					<!--<h1>证件资照</h1>-->
					<ul style="padding-top: 96px;">
						<li><strong>证件名称</strong><span> 组织机构代码证</span></li>
						<li><strong>机构名称</strong><span> 上海国诚金融信息服务有限公司</span></li>
						<li><strong>代码</strong><span> 06935495-88</span></li>
					</ul>
				</div>
				<div class="abzj-r">
					<img src="${basePath}/images/v5/aboutus/zjimg2.png" />
				</div>
				<div class="clearfix"></div>
			</div>
			<%-- <div class="abt-content-box">
				<div class="abzj-l">
					<!--<h1>证件资照</h1>-->
					<ul style="padding-top: 96px;">
						<li><strong>证件名称</strong><span> 税务登记证</span></li>
						<li><strong>机构名称</strong><span> 上海国诚金融信息服务有限公司</span></li>
						<li><strong>税字</strong><span> 沪310109069354958</span></li>
					</ul>
				</div>
				<div class="abzj-r">
					<img src="${basePath}/images/v5/aboutus/zjimg3.png" />
				</div>
				<div class="clearfix"></div>
			</div> --%>
			<div class="abt-content-box"
				style="*height: 283px; margin-bottom: 60px;">
				<div class="abzj-l" style="width: 36%; *width: 40%; width: 40% \0;">
					<!--<h1>证件资照</h1>-->
					<ul style="padding-top: 96px;">
						<li><strong>证件名称</strong><span> ICP备案</span></li>
						<li><strong>网址</strong><span> www.gcjr.com</span></li>
						<li><strong>许可证号</strong><span>沪ICP备13021943号-2</span></li>
					</ul>
				</div>
				<div class="abzj-r" style="width: 60%; *width: 58%;">
					<img src="${basePath}/images/v5/aboutus/zjimg4.png" />
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
		<div class="office-hjbox">
			<div class="content-box">
				<h1>办公环境</h1>
			</div>
			<img src="${basePath}/images/v5/aboutus/officeimg.png" />
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