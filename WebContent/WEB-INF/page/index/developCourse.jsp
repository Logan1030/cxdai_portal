<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>国诚金融-关于我们-发展历程</title>
<meta name="keywords" content="关于" />
<meta name="description" content="是专业的互联网金融公司，成立于2013年，总部位于国际金融中心上海。以其自主研发的技术平台和规范、透明的运作方式，成为成长最快、口碑最佳的平台之一。关于详情请登陆www.gcjr.com。">
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
		$("#course").addClass("active");
	});
</script>
</head>
<body>
	<%@ include file="/WEB-INF/page/common/header.jsp" %> 
	<div class="aboutus-bigbox">
		 <%@ include file="/WEB-INF/page/index/about-leftmain.jsp"%> 
		<div class="abt-content-box about-gsjj">
			<div class=" abt-content-box fzlcbox ">
				<h1>发展历程</h1>
				<div>
					<p>2014年7月11日</p>
					<p>国诚金融累计成交总额为137，418万元</p>
					<p>投资者总收益超过3200万元</p>
					<p>在网贷之家数据总成交榜上排名第五</p>
				</div>
			</div>
		</div>
		<div class="ab-zhengjian">
			<div class="abt-content-box">
				<img src="${basePath}/images/v5/aboutus/fzlcimg.png" />
			</div>
			<div class="abt-content-box fzlc-timebox">
				<h1>公司发展历程</h1>
				<ul>
					<li class="fzlctimeback-t"></li>
					<li class="time-listback"><p>2015-08-06</p> <span>当选上海市互联网金融行业协会理事单位
					</span></li>
					<li class="time-listback"><p>2015-07-16</p> <span>成为上海金融信息行业协会首批理事单位
					</span></li>
					<li class="time-listback"><p>2015-07-07</p> <span>平台累计交易总额突破40亿
					</span></li>
					<li class="time-listback"><p>2015-04-28</p> <span>国诚金融首次举办千人规模的互联网金融合作发展大会</span></li>
					<li class="time-listback"><p>2015-01-30</p> <span
						style="padding-top: 15px; *padding-top: 2px;">获《2015年中关村互联网金融峰会》——”2014
							年度中国互联网金融最具有竞争力企业、2014年度中国互联网金融最佳风控企业“两项殊荣</span></li>
					<li class="time-listback"><p>2015-01-22</p> <span>被《东方财经·实体经济与创新金融年度盛典》评为“2014年
							最受投资人信任网贷平台”</span></li>
					<li class="time-listback"><p>2015-01-17</p> <span>获“第12届中国财经风云榜”——最佳品牌营销互联网金融平台奖</span></li>
					<li class="fzlctimeback-c1"></li>
					<li class="time-listback"><p>2014-07-05</p> <span>冠名第一财经栏目——《金融译时代》</span></li>
					<li class="time-listback"><p>2014-07-03</p> <span>成为上海市信息服务行业协会会员单位</span></li>
					<li class="time-listback"><p>2014-07-02</p> <span>与平安银行签订战略合作协议</span></li>
					<li class="time-listback"><p>2014-06-27</p> <span>参加首届互联网金融博览会</span></li>
					<li class="time-listback"><p>2014-06-11</p> <span>乔迁凯德龙之梦32楼新办公室</span></li>
					<li class="time-listback"><p>2014-05-15</p> <span>与招商签订风险备用金托管协议</span></li>
					<li class="time-listback"><p>2014-05-27</p> <span>注册资本增资至5000万元</span></li>
					<li class="time-listback"><p>2014-03-25</p> <span>正式推出投标直通车功能</span></li>
					<li class="time-listback"><p>2014-03-22</p> <span>投资者监督委员会正式成立</span></li>
					<li class="time-listback"><p>2014-03-18</p> <span>累计成交金额突破5亿元，待收突破2亿元</span></li>
					<li class="time-listback"><p>2014-03-13</p> <span>聘请上海瀛东律师事务所担任公司法律顾问</span></li>
					<li class="time-listback"><p>2014-01-02</p> <span>平台(2.0版本)全新改版上线</span></li>
					<li class="fzlctimeback-c"></li>
					<li class="time-listback"><p>2013-12-30</p> <span>发布公司注册资本增资公告和风险备用金账户</span></li>
					<li class="time-listback"><p>2013-12-20</p> <span>待收金额突破1亿元</span></li>
					<li class="time-listback"><p>2013-12-12</p> <span>累计交易总额(抵押标)突破1亿元</span></li>
					<li class="time-listback"><p>2013-12-12</p> <span>平台当日交易915万元，成为上线以来最大单日交易额</span></li>
					<li class="time-listback"><p>2013-11-22</p> <span>参加第十一届上海理财博览会</span></li>
					<li class="time-listback"><p>2013-11-20</p> <span>首次投资者见面会在上海举行</span></li>
					<li class="time-listback"><p>2013-09-26</p> <span>自主开发的在线P2P网贷平台正式上线</span></li>
					<li class="time-listback"><p>2013-07-01</p> <span>办公地点正式迁至上海虹口区龙之梦A座27楼</span></li>
					<li class="time-listback"><p>2013-05-27</p> <span>上海国诚金融信息服务有限公司在上海正式注册成立，注册资本1000万元</span></li>
					<li class="fzlctimeback-b"></li>
				</ul>
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