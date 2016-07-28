<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
	<div class="topbanner"><div class="cont-toptext"><img src="${basePath}/images/v5/aboutus/toptext.png"/></div> </div>
    <div class="aboutus-menu-box">
        <ul class="abt-content-box aboutus-menu"  style=" padding-top:0px;   " >
         	<li id="introduction"><a class="normal" href="javascript:forJump('${path}/jianjie.html','introduction')" rel="nofollow">公司简介</a></li>
            <li id="course"><a class="normal" href="javascript:forJump('${path}/jianjie/course.html','course')" rel="nofollow">发展历程</a></li>
            <li id="team"><a class="normal"  href="javascript:forJump('${path}/jianjie/tuandui.html','team')" rel="nofollow">管理团队</a></li>
            <li id="data"><a class="normal" href="javascript:forJump('${path}/jianjie/data.html','data')" rel="nofollow">运营数据</a></li>
            <li id="supervise"><a class="normal" href="javascript:forJump('${path}/jianjie/supervise.html','supervise')" rel="nofollow">投资监督</a></li>
            <li id="baodao"><a class="normal" href="javascript:forJump('${path}/baodao.html','baodao')" rel="nofollow">媒体报道</a></li>
            <li id="gonggao"><a class="normal" href="javascript:forJump('${path}/gonggao.html','gonggao')" rel="nofollow">平台公告</a></li>
            <li id="contact"><a class="normal" href="javascript:forJump('${path}/jianjie/lianxi.html','contact')" rel="nofollow">联系我们</a></li>
        </ul>
    </div>
<script>
function forJump(url,id){
	removeCss();
	$("#"+id).addClass("active");
	window.open(url,"_self");
}
function removeCss(){
	$("#introduction").removeClass("normal");
	$("#course").removeClass("normal");
	$("#team").removeClass("normal");
	$("#data").removeClass("normal");
	$("#supervise").removeClass("normal");
	$("#baodao").removeClass("normal");
	$("#gonggao").removeClass("normal");
	$("#contact").removeClass("normal");
}
</script>
