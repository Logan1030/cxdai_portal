<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
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
       $("#supervise").addClass("active");
	});
	function removeDivCss(){
		$("#con_one_1").attr("style","display:none;");
		$("#con_one_2").attr("style","display:none;");
		$("#con_one_3").attr("style","display:none;");
	}

	function setTab(name,cursel){
		removeDivCss();
		if(cursel==1){
			 $("#one1").removeAttr("tzjd-menua");
			 $("#one1").attr("class","tzjd-menua menutzjdhover");
			 $("#one2").removeAttr("tzjd-menua menutzjdhover");
			 $("#one2").attr("class","tzjd-menua");
			 $("#one3").removeAttr("tzjd-menua menutzjdhover");
			 $("#one3").attr("class","tzjd-menua");
		}
		if(cursel==2){
			 $("#one2").removeAttr("tzjd-menua");
			 $("#one2").attr("class","tzjd-menua menutzjdhover");
			 $("#one1").removeAttr("tzjd-menua menutzjdhover");
			 $("#one1").attr("class","tzjd-menua");
			 $("#one3").removeAttr("tzjd-menua menutzjdhover");
			 $("#one3").attr("class","tzjd-menua");
		}
		if(cursel==3){
			 $("#one3").removeAttr("tzjd-menua");
			 $("#one3").attr("class","tzjd-menua menutzjdhover");
			 $("#one2").removeAttr("tzjd-menua menutzjdhover");
			 $("#one2").attr("class","tzjd-menua");
			 $("#one1").removeAttr("tzjd-menua menutzjdhover");
			 $("#one1").attr("class","tzjd-menua");
		}
	  /*   $("#one"+cursel).attr("style","background:#36abf6; color:#fff;"); */
		$("#con_one_"+cursel).attr("style","display:;");
	}
</script>
</head>
<body>
	<%@ include file="/WEB-INF/page/common/header.jsp" %> 
	<div class="aboutus-bigbox">
   <%@ include file="/WEB-INF/page/index/about-leftmain.jsp"%> 
		<div class="abt-content-box about-gsjj">
			<div class=" abt-content-box fzlcbox ">
				<h1>投资监督</h1>
				<div>
					<p>"投监会" 即 "投资人监督管理委员会"的简称</p>
					<p>投监会成员由投资人从自己的队伍中民主选出 </p>
					<p>组织和代表投资人不定期到平台进行查标沟通</p> 
					<p>为更多的投资人传递平台的真实信息，公正公开</p>    
				</div>
			</div>
		</div>
		<div class="jiandubigbox">
			<div class="abt-content-box jiandubox">
				<div class="jiandu-l">
					<img src="${basePath}/images/v5/aboutus/tzjdimg.png" />
				</div>
				<div class="jiandu-r">
					<h1>国诚投资监督管理委员会</h1>
					<p>国诚金融第一届投资者监督委员会于2014.3.22成立，该平台的投监会6个月为一届。投监会维护着投资人的集体利益，代表投资人不定期的到国诚金融查标，起到监督国诚、帮助国诚一起成长的作用。</p>
				</div>
				<div class="clearfix"></div>
			</div>
			<div class="abt-content-box">
				<div class="tzjd-menu">
					<li id="one1" class="tzjd-menua menutzjdhover" onclick="setTab('one',1)">第一届</li>
					<li id="one2" class="tzjd-menua" onclick="setTab('one',2)">第二届</li>
					<li id="one3" class="tzjd-menua" onclick="setTab('one',3)">第三届</li>
				</div>
				<div id="con_one_1">
					<ul class="tzjd-listbox">
						<li><a href="http://bbs.gcjr.com/posts/47185/1 ">2014-03-22
								第一届投监会成立</a></li>
						<li><a href="http://bbs.gcjr.com/posts/49482/1">2014-04-02 
								查标模板定稿</a></li>
						<li><a href="http://bbs.gcjr.com/posts/49530/1">2014-04-02 投监会基金4月公示</a></li>
						<li><a href="http://bbs.gcjr.com/posts/59310/1">2014-04-29
								投监会基金4月收支公示</a></li>
						<li><a href="http://bbs.gcjr.com/posts/63710/1">2014-05-29
								投监会基金5月账务公开</a></li>
						<li><a href="http://bbs.gcjr.com/posts/64222/1">2014-06-09
								投监会基金支出方案修订</a></li>
						<li><a href="http://bbs.gcjr.com/posts/64989/1">2014-07-01
								投监会基金6月财务公开</a></li>
						<li><a href="http://bbs.gcjr.com/posts/65291/1">2014-07-17 投监会成员变动公告</a></li>
						<li><a href="http://bbs.gcjr.com/posts/65452/1">2014-07-27
								有关“D04010奉贤房产抵押借款”标查标情况</a></li>
						<li><a href="http://bbs.gcjr.com/posts/65468/1">2014-07-28
								7.26日投监会奉贤金山房产交易中心产调报告</a></li>
						<li><a href="http://bbs.gcjr.com/posts/65474/1">2014-07-28
								投监会基金7月账务公开</a></li>
						<li><a href="http://bbs.gcjr.com/posts/65499/1">
								2014-07-29 奉贤 金山产调报告</a></li>
						<li><a href="http://bbs.gcjr.com/posts/65677/1">
								2014-08-07 8月7日长宁区房产抵押标产调实录</a></li>
					</ul>
				</div>
				<div id="con_one_2" style="display: none;">
					<ul class="tzjd-listbox">
						<li><a href="http://bbs.gcjr.com/posts/66459/1">2014-09-23
								第二届投监会1号产调报告</a></li>
						<li><a href="http://bbs.gcjr.com/posts/66462/1">2014-09-23
								投监会9月财务报告</a></li>
						<li><a href="http://bbs.gcjr.com/posts/66771/1">2014-10-19
								10月18日查标报告</a></li>
						<li><a href="http://bbs.gcjr.com/posts/67013/1">2014-11-11
								11月9日虹口区实地产调活动报告</a></li>
						<li><a href="http://bbs.gcjr.com/posts/68032/1">2015-02-08
								2015年2月5日投监会查标纪实</a></li>
						<li><a href="http://bbs.gcjr.com/posts/69440/1">2015-06-07
								2015年6月7日查标情况小结</a></li>
					</ul>
				</div>
				<div id="con_one_3" style="display: none;">
					<ul class="tzjd-listbox">
						<li><a href="http://bbs.gcjr.com/posts/69292/1">2015-5-22
								第三届投监会换届选举</a></li>
					</ul>
				</div>
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