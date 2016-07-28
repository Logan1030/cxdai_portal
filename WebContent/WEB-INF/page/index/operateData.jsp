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
<!-- 图形报表js -->
<script type="text/javascript" src="${basePath}/js/highcharts/highcharts3.0.7.js"></script>
<script type="text/javascript" src="${basePath}/js/highcharts/themes/grid.js"></script>
<script type="text/javascript" src="${basePath}/js/highcharts/myChart.js"></script>
<script type="text/javascript"> 
$(function(){
	var nav = $('.navbar'),
		doc = $(document),
		win = $(window);
	win.scroll(function() {
		if (doc.scrollTop() > 70) {
			//alert("00")
			nav.addClass('scrolled');
		} else {
			nav.removeClass('scrolled');
		}
	});
	win.scroll();
	$("#data").addClass("active");
	// 实时累计金额人数折线图
    showMoneyPersonChart();
	//成交分布图
	showSuccessBorrowChart();
   	//待收分布图
    showWaitReceiveChart();
});
/*
* 成交分布图
*/
function showSuccessBorrowChart(){
	$.get("${basePath}/chart/finance/successBorrowChart.html?chartWidth=380&&chartHight=280",
		 function(data) {
			myChart("successBorrowChart",data,function(event){
			});
			setChartData(data);
		 }
	);
}
/**
* 待收分布图
*/
function showWaitReceiveChart(){
	$.get("${basePath}/chart/finance/waitReceiveChart.html?chartWidth=380&&chartHight=280",
		 function(data) {
			myChart("waitReceiveChart",data,function(event){
			});
			setChartData(data);
		 }
	);
}

/**
 * 实时累计金额人数折线图
 */
 function showMoneyPersonChart(){
	$.get("${basePath}/chart/finance/moneyPersonChart.html",
		function(data) {
			var moneyPersonChart = new Highcharts.Chart({
				 chart: {
			            renderTo : "moneyPersonChart"
			        },
		        title: {
		            text: '待收金额、总成交金额和投资者人数变化曲线图'
		        },
		        subtitle: {
		            //text: 'Source: WorldClimate.com'
		        },
		        xAxis: [{
		            categories: data.chartDate,
		    		type:'datetime',
		    		labels:{
		    		 	format:'{value:%Y-%m-%d}',
		    			align:'left',
						rotation:45
		         	}
		        }],
		        yAxis: [{ // Primary yAxis
		            labels: {
		                format: '{value:,f}万元',
		            },
		            title: {
		                text: '金额（万元）',
		            },
		            min:0,
		            tickInterval:30000
		        }, { // Secondary yAxis
		            title: {
		                text: '投资者人数',
		                style: {
		                    color: '#058DC7'
		                }
		            },
		            labels: {
		                format: '{value} 位',
		                style: {
		                    color: '#058DC7'
		                }
		            },
		            min:0,
		            opposite: true
		        }],
		        tooltip: {
		        	xDateFormat: '%Y-%m-%d',
		        },
		        series: [{
		            name: '投资者人数',
		            color: '#50B432',
		            type: 'spline',
		            yAxis: 1,
		            data: data.investPersons,
		            tooltip: {
		                valueSuffix: ' 位'
		            }
	
		        }, {
		            name: '总成交金额',
		            color: '#058DC7',
		            type: 'spline',
		            data: data.borrowMoney,
		            tooltip: {
		                valueSuffix: '万元'
		            }
		        },{
		            name: '待收金额',
		            color: '#ED561B',
		            type: 'spline',
		            data:data.waitReceiveMoney,
		            tooltip: {
		                valueSuffix: '万元'
		            }
		        }]
		    });
		}
	);
}

</script>
<style type="text/css">
.shujub div span {
	display: block;
	width: 200px;
}
</style>
</head>
<body>
	<%@ include file="/WEB-INF/page/common/header.jsp" %> 
	<div class="aboutus-bigbox">
     <%@ include file="/WEB-INF/page/index/about-leftmain.jsp"%> 
		<div class="abt-content-box about-gsjj">
			<div class=" abt-content-box fzlcbox ">
				<h1>国诚运营数据</h1>
				<div>
					 <p>国诚金融一直</p>
					 <p>坚持诚信、规范、专业、共赢的原则</p>
					 <p>为感谢投资者，借款人和每一位关心国诚金融平台用户的信赖</p>         
					 <p>我们会在每一季度末，每一年度末</p>  
					 <p>提供最真实的运营数据</p> 
				</div>
			</div>
		</div>
		<div class="jiandubigbox" style="padding-top: 70px;">
			<div class="abt-content-box jiandubox" style="padding: 0px;">
				<div class="jiandu-l">
					<img src="${basePath}/images/v5/aboutus/ptggimg1.jpg" />
				</div>
				<div class="jiandu-r">
					<h1 style="font-size: 24px; padding-bottom: 18px;">国诚金融2014年年报
					</h1>
					<div>发布时间 2015-02-05</div>
					<a href="http://bbs.gcjr.com/ext/nianbao.html">点击查阅</a>
				</div>
				<div class="clearfix"></div>
			</div>
			<div class="abt-content-box">
				<!--  		<div class="tzjd-menu">
        	<a class="tzjd-menua menutzjdhover" href="#">第一届</a>
        	<a class="tzjd-menua" href="#">第二届</a>
        	<a class="tzjd-menua" href="#">第三届</a>
        </div>
-->
				<ul class="tzjd-listbox">
				    <li><a href="http://bbs.gcjr.com/ext/jibaofor201503.html">2015年03月国诚金融季报</a></li>
	                <li><a href="http://bbs.gcjr.com/ext/yuebaofor201504.html">2015年04月国诚金融月报 </a></li>
	            	<li><a href="http://bbs.gcjr.com/ext/yuebaofor201505.html">2015年05月国诚金融月报</a></li>
	            	<li><a href="http://bbs.gcjr.com/ext/yuebaofor201506.html">2015年06月国诚金融月报</a></li>
	            	<li><a href="http://bbs.gcjr.com/ext/yuebaofor201507.html">2015年07月国诚金融月报</a></li>
	            	<li><a href="http://bbs.gcjr.com/ext/yuebaofor201508.html">2015年08月国诚金融月报</a></li>
	            	<li><a href="http://bbs.gcjr.com/ext/yuebaofor201509.html">2015年09月国诚金融月报</a></li>
	            	<li><a href="http://bbs.gcjr.com/ext/yuebaofor201510.html">2015年10月国诚金融月报</a></li>
	            	<li><a href="http://bbs.gcjr.com/ext/yuebaofor201511.html">2015年11月国诚金融月报</a></li>
	            	<li><a href="http://bbs.gcjr.com/ext/yuebaofor201512.html">2015年12月国诚金融月报</a></li>
				</ul>
			</div>
			<!-- <div class=" abt-content-box abt-pagebox">
				<div class="abtpage">
					<span><a class="toppage" href="#">上一页</a></span> <span><a
						class="pagexh" href="#">1</a></span> <span><a class="pagexh"
						href="#">2</a></span> <span><a class="pagexh" href="#">3</a></span> <span><a
						class="pagexh" href="#">4</a></span> <span><a class="pagexh"
						href="#">5</a></span> <span><a class="nextpage" href="#">下一页</a></span>
					<div class="clearfix"></div>
				</div>
			</div> -->
		</div>
	</div>
	<div >
		<div class="abt-content-box abt-shuju" >
			<div class="shuju-title">国诚金融实时数据曲线图</div>
			<div>
				<!-- 折线图 -->
				<div style="width:770px; height:360px; margin:0 auto; background:#eee;border:1px solid #dbdbdb;" id="moneyPersonChart"> </div> 
			    <!-- 成交分布图与待收分布图 -->
			    <div id="caiwu2bg" class="shujub"  style="margin-top: 10px;">
			       <div style="width:380px;   height:280px; float:left; background:#eee; border:1px solid #dbdbdb;margin-left: 170px;" id="successBorrowChart"></div>
			       <div style="width:370px; height:280px;float:left; background:#eee; border:1px solid #dbdbdb;" id="waitReceiveChart"></div>
			  <div class="clearfix"></div>
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