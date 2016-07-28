<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <title>团队活动-国诚金融官网</title>
	<meta name="keywords" content="团队活动" />
	<meta name="description" content="国诚金融拥有专业的技术团队，为客户提供专业互联网理财服务平台。以其自主研发的技术平台和规范、透明的运作方式，成为成长最快、口碑最佳的平台之一。关于国诚金融详情请登陆www.gcjr.com。">
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<link href="${basePath}/css/help.css?version=<%=version%>" rel="stylesheet" type="text/css" />
</head>
<body>
<%@ include file="/WEB-INF/page/common/header.jsp" %>
<div id="Container">
<div id="Bmain">
  <div class="title">
    <span class="home"><a href="${path}/">国诚金融</a></span><span><a href="${path }/jianjie.html">关于我们</a></span><span>团队活动</span>
  </div>
        <div id="menu_centert">
           <%@ include file="/WEB-INF/page/index/about-leftmain.jsp"%> 
           
  
	<div id="about">
	<div class="report">
    <!--list-->
    <!--list end-->
    <!--list-->
	      <div class="report-box borderbottom"><a href="${bbsPath}/posts/65469/1">      
            <div class="report-photo border fl"><img src="${basePath}/images/01.jpg" alt=""></div>
               <div class="report-text">
                 <h2 class="report-tit f20 blues">拓展训练</h2>
                    <p>7月26—27号，以“凝聚你我，共创未来”为主题，国诚&amp;国阳精英团队2014年拓展培训在嘉兴梅花洲激情上演！</p>       
               </div></a> 
          </div>  
    <!--list end-->
<!--list-->
	      <div class="report-box borderbottom"><a href="http://mp.weixin.qq.com/s?__biz=MjM5NTAyNDcyOA==&amp;mid=200723108&amp;idx=1&amp;sn=44c4565fae03fbc730f0c2f1cf50cf8e#rd">         
            <div class="report-photo border fl"><img src="${basePath}/images/04.jpg" alt=""></div>
               <div class="report-text">
                 <h2 class="report-tit f20 blues">中秋节</h2>
                 <p>每逢佳节倍思亲，2014年中秋节国诚金融大家庭齐聚一堂，抢红包、做游戏其乐融融。</p>
               </div></a> 
          </div>  
    <!--list end-->
    <!--list-->
	      <div class="report-box borderbottom"><a href="${bbsPath}/posts/66559/1">       
            <div class="report-photo border fl"><img src="${basePath}/images/02.jpg" alt=""></div>
               <div class="report-text">
                 <h2 class="report-tit f20 blues">上线一周年 内部员工PPT大赛</h2>
                 <p>2014年9月26日，国诚金融上线一周年，公司举行了以“我心目中投资人最关心的问题”的PPT大赛，内部员工各显神通。</p>
               </div></a>
          </div>  
    <!--list end-->
    <!--list-->
	      <div class="report-box borderbottom"><a href="${bbsPath}/posts/66883/1">      
            <div class="report-photo border fl"><img src="${basePath}/images/03.jpg" alt=""></div>
               <div class="report-text">
                 <h2 class="report-tit f20 blues">生日会</h2>
                 <p>公司每个月末都有生日会，大家一起做游戏、吃蛋糕，当然最重要的是为寿星们过生日，真正的生日齐分享、快乐心连心。</p>
               </div></a>
          </div>  
    <!--list end-->
<!--    
<div class="page"><span class="disabled"> < </span><span class="current">1</span><a href="#?page=2">2</a><a href="#?page=3">3</a><a href="#?page=4">4</a><a href="#?page=5">5</a><a href="#?page=6">6</a><a href="#?page=7">7</a>...<a href="#?page=199">199</a><a href="#?page=200">200</a><a href="#?page=2"> > </a></div>-->
    
    </div>
    </div>
 </div></div></div>

<%@ include file="/WEB-INF/page/common/footer.jsp" %>
<script type="text/javascript">
$(function(){
	$("#teamactivity").addClass("sec");
});
</script>
</body>
</html>