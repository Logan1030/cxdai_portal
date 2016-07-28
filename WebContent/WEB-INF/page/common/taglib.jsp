<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%-- 标签库 --%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="springform" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%-- 字符串处理 --%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%-- 格式化 --%>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>

<%-- 自定义标签 --%>
<%@ taglib prefix="portal" uri="/WEB-INF/ELTag.tld"%>
<c:if test="${pageContext.request.serverPort=='80' || pageContext.request.serverPort=='443'}">
	<c:set var="basePath" value="${pageContext.request.scheme}://${pageContext.request.serverName}${pageContext.request.contextPath}" />
</c:if>
<c:if test="${pageContext.request.serverPort!='80' && pageContext.request.serverPort!='443'}">
	<c:set var="basePath" value="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}" />
</c:if>


<%-- 公共变量 --%>
<%-- 生产：http://www.gcjr.com 模拟生产 http://192.168.1.212 http://140.207.113.250:60000 本地：${pageContext.request.contextPath} 
              测试:http://192.168.1.154/cxdai_portal 集群测试环境 http://192.168.2.202 --%>
<c:set var="path" value="${pageContext.request.contextPath}" /> 
<%-- bbs路径 --%>
<%-- 生产:http://bbs.gcjr.com 模拟生产:http://192.168.1.215 测试:http://192.168.1.154:8084/cxdai_bbs 200服务器 http://192.168.1.200:8088   本地：--%>
<c:set var="bbsPath" value="http://192.168.1.154:8084/cxdai_bbs" />

