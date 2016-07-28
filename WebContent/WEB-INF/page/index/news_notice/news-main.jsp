<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/page/common/public2.jsp"%>
<title>关于我们-${type=='0'?'平台公告':'媒体报道'}-国诚金融</title>
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
		if('${type}'==1){
			$("#baodao").addClass("active");	
		}
		if('${type}'==0){
			$("#gonggao").addClass("active");	
		}
		findPage(1);
	});
	/**
	 * 查看详情
	 */
	function findDetail(type, id) {
		$.post("${basePath}/baodao/hits/" + id + ".html", {},
				function(data) {
					var typeUrl = "";
					if (type == '0') {
						typeUrl = "gonggao";
					} else if (type == '1') {
						typeUrl = "baodao";
					}
					if (data == 'success') {
						window.open("${path}/" + typeUrl + "/" + id + ".html",
								"_self");
					} else {
						layer.alert("网络异常,请稍后重试...");
					}
				});
	}

	/**
	 * ajax 翻页功能
	 */
	function findPage(pageNo) {
		turnPageParent(pageNo);
	}

	/**
	 * 列表翻页
	 */
	function turnPageParent(pageNo) {
		var typeUrl = '';
		var type = '${type}';
		if (type == '0') {
			typeUrl = "gonggao";
		} else if (type == '1') {
			typeUrl = "baodao";
		}
		$.ajax({
			url : '${basePath}/' + typeUrl + '/queryList/15.html?pageNo='
					+ pageNo,
			data : {
				title : $("#title").val()
			},
			type : 'post',
			dataType : 'text',
			success : function(data) {
				$("#main_content").html(data);
			},
			error : function(data) {
				layer.msg('网络连接异常，请刷新页面或稍后重试！', 1, 5);
			}
		});
	}
</script>
</head>
<body style="background: #f9f9f9;">
	<!--头部开始-->
	<%@ include file="/WEB-INF/page/common/header.jsp" %>
	<!--头部结束-->
	<div class="aboutus-bigbox">
    <%@ include file="/WEB-INF/page/index/about-leftmain.jsp"%> 
		<div class="abt-content-box about-gsjj">
			<div class=" abt-content-box fzlcbox ">
				<c:if test="${type==1}">
					<h1>媒体报道</h1>
					<div>
					     <p>国诚金融快速成长和迅猛发展</p>
						 <p>吸引了各界人士的关注</p>
						 <p>更多详情</p>         
						 <p>您可透过媒体朋友们的报道更加深入地对我们进行了解</p>         
 
					</div>
				</c:if>
				<c:if test="${type==0}">
					<h1>平台公告</h1>
					<div>
					    <p>国诚金融为了保障投资人的权益</p>
						<p>一直秉承透明化运营</p>
						<p>任何重大事项将会第一时间公布公开 </p>
						<p>敬请持续关注</p>         
   
					</div>
				</c:if>
			</div>
		</div>
		<div class="jiandubigbox" style="padding-top: 70px;">
			<div class=" abt-content-box ${type==0?'seachptggbox':'seachmtbdbox'}">
				<div class="seachcont">
					<input class="shuruk"  name="title" id="title" placeholder="输入标题" />
					<input class="abtbtn" type="button" value="搜&nbsp;索" onclick="turnPageParent(1);"  />
				</div>
			</div>
			 <div id="main_content"  class="abt-content-box"  ></div>
		</div>
	</div>
	 <div class="clearfix"></div>
    <%@ include file="/WEB-INF/page/common/footer.jsp" %> 
	<script language="javascript">
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
