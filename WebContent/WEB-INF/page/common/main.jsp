<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/page/common/public2.jsp" %>
</head>
<body>
<%@ include file="/WEB-INF/page/common/header.jsp" %>
<div class="wrapperbanner">
	<div class="fullSlide">
        <div class="bd">
            <ul>
            	<c:forEach items="${slideshowVoList}" var="slideshow" varStatus= "idxSlide" >
            	<li style="background:url(${slideshow.sImage}) center no-repeat;"><a href="${empty slideshow.imageUrl?'javascript:;':slideshow.imageUrl}"></a></li>
            	</c:forEach>
            </ul>
        </div>
        <div class="hd"><ul></ul></div>
        <span class="prev"></span>
        <span class="next"></span>
	</div><!--fullSlide end-->
    <div class="head-login-grid">
        <div class="head-login center">
            <div id="" class="login">
            	<shiro:notAuthenticated>
	                <h2>国诚年转化利率</h2>
	                <h1><strong>10</strong>%<strong>~15</strong>%</h1>
	                <h3><span>40倍</span><span style="color:#fff; padding-left:5px;">银行活期存款利息！</span></h3>
	                <button onclick="window.location='${path }/member/toRegister.html';" type="button" class="btn btn-red"><span class="icon icon-money"></span>注册抽大奖</button>
	                <h6><span>已有账号？</span><a href="${path }/member/toLogin.html?backUrl=1">立即登录</a></h6>
                </shiro:notAuthenticated>
            	<shiro:authenticated>
	            	<div class="lg-user">
	                 	<a href="${path }/myaccount/toIndex.html"><img src="${path }${portal:currentUser().headImg }"/></a>
	                </div>
	                <h3 style="padding-top:20px;"><span style="color:#fff; font-size:14px;">您好,</span><span style="color:#fa4810; font-size:14px;padding-left:5px;">${portal:currentUser().userName }</span></h3>
	                <h3 style="padding-top:10px;"><span style="color:#fff; padding-left:5px;font-size:14px;">欢迎来到国诚金融</span></h3>
	                <a href="${path }/myaccount/toIndex.html"><button type="button" class="btn btn-red"><span class=""></span>我的账户</button></a>
            	</shiro:authenticated>
            </div>
        </div>
    </div>
    <!--登录end-->
</div>
<div  class="head-money">
	<div class="grid-1100">
    	<h4>
        	<div class="w240 fl">成交金额：<strong><fmt:formatNumber value="${TotalMoney }" pattern="#,##0"/></strong><small class="f14 gary2">万元</small></div>
            <div class="w240 fl">风险备用金额：<strong><fmt:formatNumber value="${RiskMargin }" pattern="#,##0"/></strong><small class="f14 gary2">万元</small></div>
            <div class="w240 fl">昨日成交：<strong><fmt:formatNumber value="${yestDeal/10000 }" pattern="#,##0"/></strong><small class="f14 gary2">万元</small></div>
            <div class="w240 fl">聪明投资人：<strong><fmt:formatNumber value="${regCount }" pattern="#,##0"/></strong><small class="f14 gary2">人</small></div>
        </h4>
        <a href="${path }/chart/finance/show.html">更多数据>></a>
    </div>
</div>
<div class="main-security">
	<div class="security grid-1100">
    	<ul>
        	<li><a href="${path}/bangzhu.html"><span class="icon icon-an fl"></span><h4><span>专业</span>上海13年风控经验，自主研发风控系统</h4></a></li>
            <li><a href="${path}/bangzhu.html"><span class="icon icon-safe fl"></span><h4><span>安全</span>100%本息保障，光大银行资金存管</h4></a></li>
            <li><a href="${path}/bangzhu.html"><span class="icon icon-trap fl"></span><h4><span>透明</span>最全面数据披露，无审帖开放社区</h4></a></li>
            <li onclick="openVideo()" style="border-right:none;cursor: pointer;"><span class="icon icon-rideo fl"></span><h4><span style="padding-top:20px;">一分钟了解国诚金融</span></h4></li>
        </ul>
    </div>
</div>
<div class="product-wrap"><!--活期宝star-->
	<div class="grid-1100">
    	<div class="gc-col-l center">
        	<div class="new-bar">
            	<div class="gc-img-wap pric-chou"></div>
                <div class="gc-img-wap text-new"></div>
                <h4>新人注册最高可获100元</h4>
                <button class="btn btn-yellow f14" type="button" style="cursor: default;">专享15%高收益 </button>
            </div>
            <div class="huo-bar">
            	<div class="gc-img-wap picinfo"></div>
                <h6><a href="${bbsPath }/ext/hqbGonglue.html" style="text-decoration:none;" target="_blank">活期宝投资攻略>></a></h6>
            </div>
        </div>
        <div class="gc-col-m" style=" padding-bottom:4px; ">
        	<ul style=" overflow:hidden; " >
            	<li class="mrdtopl item-bd-r item-box-bd" style="float:left; border:none; ">
                	<div class="new-product">
            			<div class="title">
                        	<h1> <span class="fr">每日不定时开放</span>新手专享宝</h1>
                        </div>
                		<div class="item-cf">
                        	<div class="fl"  style=" width:60%;">
                   			  <div class="item-box " >
                                	<div  >
                                        <span class="lefttitle-in f14">年化收益</span>
                                        <span class="righttitle-in  " >
                                            <p class="item-szl " style=" width:40%;  "><fmt:formatNumber value="${fixBorrowNew.apr}" pattern="#,##0.#" /></p>
                                            <p class="item-szr f18">%</p>
                                        </span> 
                                    </div>
                                </div>
                            </div>
                            <div class="fr">
                            	<div class="item-box">
                                	<h6>期限<strong>${fixBorrowNew.lockLimit}</strong>个月</h6> 
                                </div>
                            </div>
                        </div>
                        <div  class="item-cf clearfix">
                            <div class="votebox fl">
                                <dl class="barbox">
                                    <dd class="barline"  style="width:180px">
                                        <div w="${fixBorrowNew.scheduleStrNoDecimal}" style="width:0px;" class="charts"></div>
                                    </dd>
                                </dl><span>${fixBorrowNew.scheduleStrNoDecimal}%</span>
                            </div>
                            <div class="fr">
                            	<div class="btn-box">
                                	<a href="${path}/dingqibao/${fixBorrowNew.id}.html" class="btn btn-primary">
										<c:if test="${fixBorrowNew.status==3 and fixBorrowNew.flagJoin==1}">立即加入</c:if>
										<c:if test="${fixBorrowNew.status==3 and fixBorrowNew.flagJoin==2}">敬请期待</c:if>
										<c:if test="${fixBorrowNew.status==5}">收益中</c:if>
										<c:if test="${empty fixBorrowNew}">敬请期待</c:if>
									</a>
                                </div>
                            </div>
                        </div>
            		</div>
                </li>
                <li class=" item-box-bd" style=" border-left:1px #e6e6e6 solid; margin-top:20px;"  >
                	<div class="new-product3">
                        <a href="${path}/lottery_chance/toRecommendaward.html"><img class="" src="${path}/images/v5/pic-bar_03.png"/></a>
            		</div>
                </li>
                <li class="mrdleft item-box-bdonl item-bd-r item-bd-t clearfix" style="padding-bottom:18px; *padding-bottom:22px">
                	<div class="new-product4">
            			<div class="title">
                        	<h1><span class="fr">灵活又便捷</span>活期宝</h1>
                        </div>
                		<div class="item-cf">
                        	<div class="fl" style=" width:60%;">
                   			  <div class="item-box " >
                                	<div  >
                                        <span class="lefttitle-in f14">年化收益</span>
                                        <span class="righttitle-in  " >
                                            <p class="item-szl " style=" width:40%;">5.6</p>
                                            <p class="item-szr f18">%</p>
                                        </span> 
                                    </div>
                                  
                                </div>
                            </div>
                            <div class="fr">
                            	<div class="item-box">
                                	<h6> <strong>1</strong>元起投，低门槛</h6> 
                                </div>
                            </div>
                        </div>
                        <div  class="item-cf clearfix" style="padding-top:31px;  ">
                            <div class="fl item-box"><p>每日复利，随存随取</p></div>
                            <div class="fr">
                            	<div class="btn-box">
                                	<a href="${path }/curInController.html" class="btn btn-primary">立即加入</a>
                                </div>
                            </div>
                        </div>
            		</div>
                </li>
                <li class=" item-box-bd" style="border-left:1px #e6e6e6 solid;border:none;  ">
                	<div class="new-product3" style=" padding-top:15px;">
            			<a href="${path }/sycee.html"><img src="${path}/images/v5/pic-bar_06.png"/></a>               
            		</div>
                </li>
            </ul>
        </div>
    </div>
</div><!--活期宝end-->

<div class="product-wrap clearfix"><!--定期宝star-->
<!-- 当前时间 -->
<input id="nowTime" type="hidden"  value="${nowTime}" />
	<div class="grid-1100">
    	<div class="gc-col-l center">
        	<div class="new-bar2">
                <div class="gc-img-wap text-new2"></div>
            	<div class="gc-img-wap picinfo2"></div>
                <div class="time"><a href="#">每日9:00,15:00,20:00定时开放</a></div>
            </div>
         </div>
         <div class="gc-col-m">
         	<div class="title item-bd-b" style="padding:12px 20px 8px;"><a href="${path }/dingqibao.html" class="fr bule">更多>></a><i class="icon icon-time"></i>定期宝</div>
        	<ul class="huoqb">
        		<c:forEach items="${fixList }" var="f">
            	<li>
                	<div class="new-product2">
                		<div class="item-cf">
                        	<div class="fl item-from-l">
                            	<div class="item-box">
                                    <h6>年化收益<span class="right"><fmt:formatNumber value="${f.apr}" pattern="#"/><small class="fr">%</small></span></h6>
                                </div>
                            </div>
                            <div class="votebox fl item-from-m">
                            	<h5 >项目期限<strong>${f.lockLimit}</strong>个月 </h5>
                                <dl class="barbox">
                                    <dd class="barline" style="width:212px">
                                        <div w="${f.scheduleStrNoDecimal}" style="width:0px;" class="charts"></div>
                                    </dd>
                                </dl><span>${f.scheduleStrNoDecimal}%</span>
                                <h5 class="clearfix">剩余可投余额：<strong class="pdl"><fmt:formatNumber value='${f.planAccount-f.accountYes}' pattern="###,###" /></strong>元</h5>
                            </div>
                            <div class="fr item-from-r  textr">
                            	<c:if test="${f.statusId == 2}">
                                <input id="joinStatus" type="hidden"  value="${f.joinStatus}"/>
                            	 <input id="publishTime" type="hidden" value="<fmt:formatDate value="${f.publishTime}" pattern="yyyy/MM/dd HH:mm:ss"/>">
                            	<h6 class="gary2"><span>发布时间还有</span><span class="oren" id="remainingTime">&nbsp;&nbsp;</span>  </h6>
                            	</c:if>
                            	<div class="btn-box pdtop10">
                            		<c:if test="${f.statusId == 1}">
                            			<a href="${path}/dingqibao/${f.id}.html" class="btn btn-primary" style="cursor: pointer;">加入中</a>
                            		</c:if>
                            		<c:if test="${f.statusId == 2}">
                            			<a href="${path}/dingqibao/${f.id}.html" class="btn btn-jqqq" style="cursor: pointer;">敬请期待</a>
                            		</c:if>
                            		<c:if test="${f.statusId == 3}">
                            			<a href="${path}/dingqibao/${f.id}.html" class="btn btn-gcsyz" style="cursor: pointer;">收益中</a>
                            		</c:if>
                            		<c:if test="${f.statusId == 4}">
                            			<a href="${path}/dingqibao/${f.id}.html" class="btn btn-jqqq" style="cursor: pointer;">已退出</a>
                            		</c:if>
                                </div>
                            </div>
                        </div>
            		</div>
                </li>
                </c:forEach>
              </ul>
        </div>
    </div>
</div><!--定期宝end-->

<div class="product-wrap clearfix"><!--债权star-->
	<div class="grid-1100">
    	<div class="gc-col-l center">
        	<div class="new-bar3">
                <div class="gc-img-wap text-new3"></div>
            	<div class="gc-img-wap picinfo"></div>
                <div class="time"><a href="${path }/toubiao.html" style="cursor: pointer;">查看更多>></a></div>
            </div>
         </div>
         <div class="gc-col-m">
         	<table class="table  tbtr">
		    <thead>
		        <tr>
		          <td>借款标题</td>
		          <td>发布时间</td>
		          <td align="right">金额</td>
		          <td>年利率</td>
		          <td>期限</td>
		          <td align="right">进度</td>
		          <td>&nbsp;</td>
		        </tr>
		    </thead>
		    <tbody>
		    	<c:forEach items="${mixedBorrowList.result}" var="b" varStatus="idx" end="7" >
		        <tr onclick="window.location='${path}/toubiao/${b.id}.html';" style="cursor: pointer;">
		          <td align="left">
		          	<span class="icon 
			          	<c:if test="${b.borrowtype==1 }">icon-xin</c:if>
			          	<c:if test="${b.borrowtype==2 }">icon-di</c:if>
	                	<c:if test="${b.borrowtype==5 }">icon-bao</c:if> pdr">
	                </span>
		          	${fn:substring(b.subName,0,6)}<c:if test="${fn:length(b.subName)>6}">..</c:if>
          		  </td>
		          <td><c:if test="${b.status==1}">${b.timingBorrowTimeStr}</c:if><c:if test="${b.status!=1}">${b.publishTimeStr}</c:if></td>
		          <td align="right"><fmt:formatNumber value="${b.account}" pattern="#,##0.00" />元</td>
		          <td><fmt:formatNumber value="${b.apr}" pattern="#,##0.##"/>%</td>
		          <td>${b.timeLimit }个月</td>
		          <td>
		          	<div class="votebox fr" style="width:110px; overflow:hidden">
		                <dl class="barbox fl">
		                    <dd class="barline"  style="width:60px;">
		                        <div w="${b.scheduleStrNoDecimal}" style="width:0px;" class="charts"></div>
		                    </dd>   
		                </dl><span class="per fr">${b.scheduleStrNoDecimal}%</span>
		          	</div>
		          </td>
		          <td>
		          	<c:if test="${b.status==1}">
						<button type="button" class=" btn-small btn-yfz">预发中</button>
					</c:if>
					<c:if test="${b.status==42}">
						<button type="button" class=" btn-small btn-ywc">已垫付</button>
					</c:if>
					<c:if test="${b.status==2 }">
						<button type="button" class=" btn-small btn-blue">${portal:desc(100, b.status)}</button>
					</c:if>
					<c:if test="${b.status==4 }">
						<button type="button" class=" btn-small btn-gcsyz">${portal:desc(100, b.status)}</button>
					</c:if>
					<c:if test="${b.status==5 }">
						<button type="button" class=" btn-small btn-ywc">${portal:desc(100, b.status)}</button>
					</c:if>
		          </td>
		        </tr>
		        </c:forEach>
			</tbody>
			</table>
		</div>
	</div>
</div>
<div class="product-wrap clearfix"><!--国诚资讯star-->
	<div class="grid-1100 information">
    	<h4 class="f14">国诚动态</h4>
        <div class="comp-gc fl">
        	<h5 class="f12 gary bold">公司动态</h5>
            <ul class="cmp">
            	<c:forEach items="${dongtaiList }" var="d" varStatus="n">
            	<li <c:if test="${n.count==3 }">style="margin-right:0px"</c:if>>
            		<a href="${d.imageUrl}" target="_blank" title="${d.title }" style="cursor: pointer;"><div class="out-img"><img class="img-radius" src="${d.sImage}" width="222px"/></div></a>
            		<h6 class="f14 lh30" title="${d.title }"><a href="${d.imageUrl}" target="_blank" class="sydt-text">${fn:substring(d.title,0,15)}<c:if test="${fn:length(d.title)>15}">..</c:if></a></h6>
            	</li>
           		</c:forEach>
            </ul>
        </div>
        <div class="notice-gc fr">
        	<h5 class="f12 gary bold"><a href="${path }/gonggao.html" class="fr f12">更多>></a>国诚公告</h5>
        	<ul>
        		<c:forEach items="${noticePage.result}" var="n" end="5">
					<li><a href="${path}/gonggao/${n.id}.html" title="${n.title }">${fn:substring(n.title,0,15)}<c:if test="${fn:length(n.title)>15}">..</c:if></a></li>
				</c:forEach>
            </ul>
        </div>
    </div>
</div>
<div class="product-wrap clearfix"><!--媒体报道star-->
	<div class="grid-1100 medpeo">
    	<h5 class="f14 textl">媒体报道</h5>
        <div class="bank-link">
        	<a target="_blank" href="http://sh.xinhuanet.com/2015-10/27/c_134754903.htm?from=singlemessage&isappinstalled=0"><img alt="投资理财" src="${path}/images/v5/blank/bank-logo_07.jpg"/></a>
			<a target="_blank" href="http://bgimg.ce.cn/xwzx/gnsz/gdxw/201508/26/t20150826_6329617.shtml"><img alt="抵押贷款" src="${path}/images/v5/blank/bank-logo_19.jpg"/></a>			
			<a target="_blank" onclick="_hmt.push(['_trackEvent', 'imagelink', 'click', 'Linode'])"  href="http://v.ifeng.com/news/finance/201507/01cdf3af-eafb-48f9-8696-7dd8ffa5c053.shtml"><img title="理财小知识" alt="理财小知识" src="${path}/images/v5/blank/bank-logo_20.jpg"/></a>
           	<a target="_blank" onclick="_hmt.push(['_trackEvent', 'imagelink', 'click', 'Linode'])"  href="http://v.qq.com/page/x/x/n/x0154z5xgxn.html"><img alt="抵押贷款" src="${path}/images/v5/blank/bank-logo_03.jpg"/></a>
            <a style="margin-right:0;" target="_blank" href="http://v.youku.com/v_show/id_XOTQ5NDkyMDIw.html"><img alt="互联网金融" src="${path}/images/v5/blank/bank-logo_05.jpg"/></a>
       	 	<a target="_blank" href="http://www.sh.chinanews.com/news/20150428/836597.html"><img alt="投资理财" src="${path}/images/v5/blank/bank-logo_09.jpg"/></a>
			<a target="_blank" href="http://www.labour-daily.cn/ldb/node41/node2151/20150730/n47090/n47097/u1ai241824.html?from=singlemessage&isappinstalled=0 "><img alt="贷款平台" src="${path}/images/v5/blank/bank-logo_23.jpg"/></a>
            <a target="_blank" href="http://www.shbiz.com.cn/Item/253594.aspx"><img alt="贷款平台" src="${path}/images/v5/blank/bank-logo_18.jpg"/></a>
            <a target="_blank" href="http://news.163.com/14/1017/16/A8P8KC9U00014SEH.html"><img alt="互联网金融" src="${path}/images/v5/blank/bank-logo_11.jpg"/></a>
            <a style="margin-right:0;" target="_blank" href="http://finance.china.com/fin/lc/201501/29/6363687.html"><img alt="理财小知识" src="${path}/images/v5/blank/bank-logo_21.jpg"/></a>
        </div>
    </div>
</div>
<div class="product-wrap clearfix" style="*margin-bottom:20px;"><!--合作伙伴star-->
	<div class="grid-1100 medpeo">
    	<h5 class="f14 textl">合作伙伴</h5>
        <div class="bank-link2">
        	<div class="swap_pic">
                <div class="box">
                    <ul class="pics" id="pics">
                        <li>
                            <a href="http://www.touzhijia.cn/" title="投之家" rel="nofollow" target="_blank"><img src="${path }/images/v5/blank/bank_03.jpg" alt="理财小知识"></a>
							<a href="http://www.cmbchina.com/" rel="nofollow" title="招商银行" target="_blank"><img src="${path }/images/v5/blank/bank_05.jpg" alt="抵押贷款"/></a>
							<a href="http://bank.pingan.com/index.shtml" rel="nofollow" title="平安银行" target="_blank"><img src="${path }/images/v5/blank/bank_07.jpg" alt="贷款平台"/></a>
							<a href="http://www.chinabank.com.cn/" rel="nofollow" title="网银在线" target="_blank"><img src="${path }/images/v5/blank/bank_09.jpg" alt="互联网金融"/></a>
							<a href="https://www.weicaifu.com/" rel="nofollow" title="微财富" target="_blank"><img src="${path }/images/v5/blank/bank_11.jpg" alt="投资理财"/></a>
							<a href="http://www.xs-pawn.com/" rel="nofollow" title="新盛典当" target="_blank"><img src="${path }/images/v5/blank/bank_13.jpg" alt="抵押贷款"/></a>
							<a href="http://www.yicai.com/" rel="nofollow" title="第一财经" target="_blank"><img src="${path }/images/v5/blank/bank_15.jpg" alt="理财小知识"/></a>
							<a style="margin-right:0px;" href="http://men.sohu.com/s2014/jinrong/index.shtml" title="金融e时代" rel="nofollow" target="_blank"><img src="${path }/images/v5/blank/bank_17.jpg" alt="贷款平台"/></a>
                        </li>
                    </ul>
                </div>
			</div>
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/page/common/footer.jsp" %>
</body>
<script type="text/javascript">
/**
 * 一分钟了解国诚金融
 */
function openVideo(){
	var content = '<object type="application/x-shockwave-flash" data="http://player.youku.com/player.php/sid/XMTI4MDA2ODI3Ng==/v.swf" width="600px" height="420px" id="youku-player">'
		+ '<param name="allowFullScreen" value="true">'
		+ '<param name="allowScriptAccess" value="always">'
		+ '<param name="movie" value="http://player.youku.com/player.php/sid/XMTI4MDA2ODI3Ng==/v.swf">'
		+ '<param name="flashvars" value="imglogo=&amp;paid=0&amp;partnerId=0d3ce735f648f788&amp;isAutoPlay=true&amp;styleid=0">'
		+ '</object>';
		$.layer({
	    type: 1,
	    title: false,
	    area: ['auto', 'auto'],
	    border: [0], //去掉默认边框
	    shade: [0], //去掉遮罩
	    offset: ['20%',''], 
	    page: {
	        html: content
	    },
	    close : function(index){
			layer.close(index);
		}
	});
}
/**秒数*/
var SysSecond;
/**循环计算倒计时*/
var InterValObj;

 $(function() {
	var borrowStatus = $("#joinStatus").val();
	var nowTime = $("#nowTime").val();
	/**预发标,发标剩余时间*/
	if (borrowStatus == "0") {
		var publishTime = $("#publishTime").val();
		//这里获取倒计时的起始时间 
		SysSecond = parseFloat(new Date(publishTime).getTime() / 1000)
				- parseFloat(new Date(nowTime).getTime() / 1000);
		//SysSecond = parseInt((new Date('<fmt:formatDate value="${firstBorrow.endTime}" pattern="yyyy/MM/dd HH:mm:ss"/>').getTime() / 1000) - new Date('${nowTime}').getTime() / 1000); //这里获取倒计时的起始时间 
		//剩余时间
		InterValObj = window.setInterval(SetRemainTime, 1000); //间隔函数，1秒执行 
	}
}); 
/**
 * 倒计时
 */
function SetRemainTime() {
	if (SysSecond > 0) {
		SysSecond = SysSecond - 1;
		var second = Math.floor(SysSecond % 60); // 计算秒     
		var minute = Math.floor((SysSecond / 60) % 60); //计算分 
		var hour = Math.floor((SysSecond / 3600) % 24); //计算小时 
		var day = Math.floor((SysSecond / 3600) / 24); //计算天 
		if (day > 0) {
			$("#remainingTime").html(
					'<strong>' + day + '</strong>天<strong>' + hour
							+ '</strong>时<strong>' + minute
							+ '</strong>分<strong>' + second + '</strong>秒');

		}else if (hour > 0) {
			$("#remainingTime").html(
					'<strong>' + hour
							+ '</strong>时<strong>' + minute
							+ '</strong>分<strong>' + second + '</strong>秒');

		}else if (minute > 0) {
			$("#remainingTime").html(
					'<strong>' + minute
							+ '</strong>分<strong>' + second + '</strong>秒');

		} else {
			$("#remainingTime").html(
					'<strong>' + second + '</strong>秒');
		}

	} else {//剩余时间小于或等于0的时候，就停止间隔函数 
		window.clearInterval(InterValObj);
		//这里可以添加倒计时时间为0后需要执行的事件 
		//$("#remainingTime").html("0天0时0分0秒");
		window.location.reload();
	}
}
</script>