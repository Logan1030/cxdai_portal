<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>团队管理-国诚金融官网</title>
<meta name="keywords" content="团队管理" />
<meta name="description"
	content="国诚金融拥有专业的技术团队，为客户提供专业互联网理财服务平台。以其自主研发的技术平台和规范、透明的运作方式，成为成长最快、口碑最佳的平台之一。关于国诚金融详情请登陆www.gcjr.com。">
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
		$("#team").addClass("active");
	});
</script>
</head>
<body>
	<div class="aboutus-bigbox">
     <%@ include file="/WEB-INF/page/index/about-leftmain.jsp"%> 
		<div class="abt-content-box about-gsjj">
			<div class=" abt-content-box fzlcbox ">
				<h1>管理团队</h1>
				<div>
					<p>国诚金融以普惠金融的使命</p>
					<p>互联网金融创新的基因</p>
					<p>凝聚了一批充满阳光、活力 和进取精神的金融及技术精英</p>
					<p>他们是为</p>
					<p>伟大梦想而脚踏实地努力着的践行者</p>
				</div>
			</div>
		</div>
		<style>
.gltdbigbox {
	background: #fff;
	padding-bottom: 40px;
}

.bos-photo-1, .bos-photo-2, .bos-photo-3 {
	width: 246px;
	float: left;
	overflow: hidden;
	height: 320px;
}

.abt-gltdbox .bos-photo-1 {
	background: url(${basePath}/images/v5/aboutus/bosimg.png) no-repeat;
}

.abt-gltdbox .bos-photo-2 {
	background: url(${basePath}/images/v5/aboutus/bosimg.png) no-repeat -246px 0;
}

.abt-gltdbox .bos-photo-3 {
	background: url(${basePath}/images/v5/aboutus/bosimg.png) no-repeat -492px 0;
}

.abt-gltdbox dl {
	float: right;
	width: 70%;
	padding-top: 20px;
}

.abt-gltdbox dl dt {
	color: #35aaf2;
	font-size: 18px;
	line-height: 45px;
}

.abt-gltdbox dl dd {
	font-size: 14px;
	line-height: 30px;
}
</style>
         <%@ include file="/WEB-INF/page/common/header.jsp" %> 
		<div class="gltdbigbox">
			<div class="abt-content-box abt-gltdbox">
				<div class="bos-photo-1"></div>
				<dl>
					<dt>劳婕怡</dt>
					<dt style="padding-bottom: 40px;">董事长</dt>
					<dd>毕业于东北师范大学</dd>
					<dd>曾供职于浦发银行、广发银行、证大财富等金融公司。</dd>
					<dd>近10年的金融行业从业经历和累积的个人资源</dd>
					<dd>在金融业务渠道开发、业务操作、风险控制等方面有极为丰富的行业经验。</dd>
					<dd>现负责国诚金融风险控制业务端发展、财务和人事管理</dd>
				</dl>
				<div class="clearfix"></div>
			</div>
		</div>
		<!----------------------------------------------------->
		<div class="gltdbigbox" style="background: #ecf0f1;">
			<div class="abt-content-box abt-gltdbox">
				<div class="bos-photo-2"></div>
				<dl>
					<dt>王建章</dt>
					<dt style="padding-bottom: 40px;">联合创始人、CEO</dt>
					<dd>2006年毕业于长安大学，汽车运用工程专业硕士研究生</dd>
					<dd>近十年的大学从教经历</dd>
					<dd>上海第一财经《金融译时代》栏目特邀评论员，中关村互联网金融研究院、国培机构互联网金融人才培训讲师</dd>
					<dd>致力于打造一个更加“规范、透明、安全”的网贷平台</dd>
					<dd>现负责国诚金融整体战略规划和品牌建设</dd>
				</dl>
				<div class="clearfix"></div>
			</div>
		</div>
		<!----------------------------------------------------->
		<div class="gltdbigbox">
			<div class="abt-content-box abt-gltdbox">
				<div class="bos-photo-3"></div>
				<dl>
					<dt>温征</dt>
					<dt style="padding-bottom: 40px;">联合创始人、副董事长</dt>
					<dd>2015年中欧国际工商学院深造，专修工商管理</dd>
		        	<dd>2004年开始创业，开办工厂，并逐步向国际贸易转型业务遍及美洲、欧洲、中东等地区</dd>
		        	<dd>2011年开始进入互联网金融行业，并有成功的网贷平台创始经历，亲历网贷行业的起步与发展，积累了大量防</dd>
		        	<dd>范网贷平台经营风险的实践经验，精于商务合作和团队管理</dd>
		        	<dd>现负责国诚金融公司内部管理、渠道拓展和业务开发</dd>
				</dl>
				<div class="clearfix"></div>
			</div>
		</div>
		<div class="gltdbigbox" style="background:#ecf0f1;">
		    <div class="abt-content-box abt-gltdbox">
		       <div class="bos-photo-4"> </div>
		        <dl>
		        	<dt>李刚</dt>
		        	<dt style="padding-bottom:40px;">副总裁</dt>
		            <dd>毕业于太原理工大学软件工程专业，美国项目管理协会（PMI）成员 </dd>
		        	<dd>曾任中国大型综合性软件与信息服务企业项目负责人 </dd>
		        	<dd>对多个技术领域的系统架构设计、软件项目研发、系统实施等方面有着深刻的理解和丰富的实战经验。 </dd>
		        	<dd>现负责国诚金融技术系统的开发建设与运营</dd>
		         </dl>
		    <div class="clearfix"></div>
		    </div>
     </div>
		<div class="gltdbigbox" >
    <div class="abt-content-box abt-gltdbox">
       <div class="bos-photo-5"> </div>
        <dl>
        	<dt>方淑华</dt>
        	<dt style="padding-bottom:40px;">COO</dt>
            <dd>17年资深品牌运营及市场营销经验 </dd>
        	<dd>曾服务于三星、日立、凤凰光学等世界500强及上市公司，并在企业中任职企划经理、产品经理、市场总监等重要管理岗位，在企业管理和运营上有丰富的经验。 </dd>
        	<dd>现任国诚金融运营中心总监，全面负责品牌规划、客户服务、政策监督、平台体验等运营工作</dd>
         </dl>
      <div class="clearfix"></div>
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