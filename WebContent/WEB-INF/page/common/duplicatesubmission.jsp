<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%response.setStatus(10001);%>

<%-- <%
	Throwable ex = null;
	if (exception != null)
		ex = exception;
	if (request.getAttribute("javax.servlet.error.exception") != null)
		ex = (Throwable) request.getAttribute("javax.servlet.error.exception");

	//记录日志
	try{
		Logger logger = LoggerFactory.getLogger("500.jsp");
		logger.error(ex.getMessage(), ex);
	}catch(Exception e){
		e.printStackTrace();
	}
%> --%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="keywords" content="国诚金融,金融,投资,理财" />
<meta name="description" content="国诚金融提供安全、有担保的互联网理财投资服务。100%本息担保！随时可赎回！上国诚，好收益！">
<meta name="generator" content="国诚金融" />
<meta name="author" content="国诚金融" />
<meta name="copyright" content="2014 上海国诚金融信息服务有限公司" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>500</title>
<link href="${basePath}/css/style.css" rel="stylesheet" type="text/css" />
<link href="${basePath}/css/404.css" rel="stylesheet" type="text/css" />
<link rel="shortcut icon" href="${path }/images/favicon.ico" type="image/x-icon"/>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
</head>

<body>
<!--头部开始-->
<div id="header">
  <div class="inner">        			   
    <div class="logo">
            <h2><a href="${path}/"><img alt="囯诚金融" src="${basePath}/images/logo.png" width="160" height="72"></a></h2>
    </div>
    </div>
    </div>
<div class="clear"></div>
<!--头部结束-->
<!--内容开始-->
         
                  <div id="Bmain">
                       <div class="network-content">
                             <img class="network-wrong" src="${basePath}/images/500.png" width="176" height="176"/>
                             <p class="headline">服务器禁止重复提交!</p>
                             <p class="txts">抱歉，服务器禁止重复提交。<br/>
                               返回
                             <a href="${path}/">国诚金融首页</a>   
                             </p>
                       </div>
                 </div>


<!--内容结束-->
<div class="clearfix"></div>
<div class="footbg">
<div class="footer-copyright">
Copyright 2014 &copy; 上海国诚金融信息服务有限公司 版权所有 | 上海市虹口区西江湾路388号凯德龙之梦A座32楼&nbsp;  电话：021-80100999 
| 沪ICP备13021943号-1</div></div>
</body>
</html>
