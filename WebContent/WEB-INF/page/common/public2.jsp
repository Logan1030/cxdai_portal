<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
 	// 发布的版本号，每次发布版本时将此版本号加1,其主要原因是引用最新的css文件，避免缓存
    String version = "20160119";
	response.setHeader("Cache-Control","no-store"); 
	response.setHeader("Pragrma","no-cache"); 
	response.setDateHeader("Expires",0); 
%>

<title>国诚金融-中国最大的房产抵押互联网金融平台</title>
<%-- 公共meta --%>
<meta http-equiv="Cache-Control" content="no-store" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<meta name="keywords" content="投资理财,互联网金融,p2p理财投资,理财产品,网贷平台投资理财,P2P贷款平台,网络贷款,小额贷款,金融理财,理财产品,房产抵押,网络理财,P2P理财论坛" />
<meta name="description" content="国诚金融是中国投资理财最透明P2P网络借贷平台，为个人和中小微企业提供P2P理财、网络贷款、小额贷款、金融理财、抵押贷款等高效便捷的融资服务，为投资者搭建一个轻松、安全、透明的网络理财平台。">
<meta name="generator" content="国诚金融－中国最透明P2P理财平台_金融投资_网络投资选择" />
<meta name="author" content="国诚金融－中国最透明P2P理财平台_金融投资_网络投资选择" />
<meta name="copyright" content="2016 上海国诚金融信息服务有限公司" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

<%-- ICON图标 --%>
<link href="${basePath}/images/gcjr.ico" rel="shortcut icon" type="image/x-icon"/>

<%-- 公共css --%>
<link href="${basePath}/css/base.css?version=<%=version%>" rel="stylesheet" type="text/css" />

<%-- 公共js --%>
<script src="${basePath }/js/jquery-1.9.1.js?version=<%=version%>"></script>
<script src="${basePath }/js/superslide.2.1.js?version=<%=version%>"></script>
<script src="${basePath }/js/common.js?version=<%=version%>"></script>

<%-- layer --%>
<script type="text/javascript" src="${basePath }/js/layer/layer.min.js?version=<%=version%>"></script>
<script type="text/javascript" src="${basePath }/js/layer/extend/layer.ext.js?version=<%=version%>"></script>

<%--jquery --%>
<script type="text/javascript" src="${basePath }/js/jquery.form.js?version=<%=version%>"></script>


