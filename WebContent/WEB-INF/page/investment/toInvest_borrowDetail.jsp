<%@page import="com.cxdai.utils.DateUtils"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>

			<!-- 当前时间 -->
		    <input type="hidden" id="nowTime" value="<fmt:formatDate value="${nowTime }" pattern="yyyy/MM/dd HH:mm:ss"/>"/>
		    <!-- 标时间 -->
		    <input type="hidden" id="borrow_addtime_hide" value="<fmt:formatDate value="${borrow.publishTimeDate }" pattern="yyyy/MM/dd HH:mm:ss"/>"/>
			 
			<c:set var="btype" value="${borrow.borrowtype}" scope="request"/>
             <c:choose>   
				<c:when test="${btype == 1}">
					<c:set var="cls" value="icon-xin" scope="request"  />
				</c:when>   
				<c:when test="${btype == 2}">
					<c:set var="cls" value="icon-di" scope="request"  /> 
				</c:when>
				<c:when test="${btype == 3}">
					<c:set var="cls" value="icon-jing" scope="request"  />  
				</c:when>
				<c:when test="${btype == 4}">
					<c:set var="cls" value="icon-miao" scope="request"  />   
				</c:when>
				<c:when test="${btype == 5}">
					<c:set var="cls" value="icon-bao" scope="request"  />     
				</c:when>
				<c:otherwise></c:otherwise> 
		  </c:choose>
			  
			<div class="grid-1100">
    	<div class="product-deatil">
        	<h1 class="f16"><small class="fr">【借款标编号】${borrow.contractNo}</small><span><i class="icon ${cls } pdr"></i>
        	            ${fn:substring(borrow.name,0,19)}
						<c:if test="${fn:length(borrow.name)>19}">..</c:if></span>
						<span class="oren">
						<c:if test="${borrow.pledgeType == 1}"> 【新增】 </c:if>
						<c:if test="${borrow.pledgeType == 2}"> 【续贷】 </c:if>
						<c:if test="${borrow.pledgeType == 3}"> 【资产处理】 </c:if>
						 </span><span class="bule">
						<a href="${path}/accountdetail/show.html?userName=${portal:encode(portal:encode(borrow.userNameEncrypt))}" target="_blank">
		                	<c:choose>
							<c:when test="${borrow.borrowtype == 1 || borrow.borrowtype == 2 || borrow.borrowtype == 5}">
								${borrow.username}
							</c:when>
							<c:otherwise>
								${borrow.userNameSecret }
							</c:otherwise>
							</c:choose>					
						</a></span></h1>
            <div class="detail-col-l fl item-bd-r">
            	<ul class="item-bd-b">
                	<li class="bdr percentbig"><span class="f14 gary">年化收益率</span>
                	<span class="oren precent line120">
                	<c:if test="${borrow.id==add5}">
					    ${borrow.apr-5 }
					    <span style="color:red;">+5</span> 
					 </c:if>
					 
					  <c:if test="${borrow.id==add2}">
					    ${borrow.apr-2 } 
					    <span style="color:red;">+2</span> 
					 </c:if>
					 
					 <c:if test="${! (borrow.id==add5 or borrow.id==add2 or borrow.apr == '15.71') }">
					 ${borrow.apr}
					 </c:if>
					 <c:if test="${borrow.apr == '15.71'}">
					    <fmt:formatNumber value="${borrow.apr-0.71}" pattern="#,##0.##"/> 
					    <span style="color:red;">+0.71</span> 
					 </c:if>
                	 <small class="f18 oren">%</small></li>
                	<li class="percentbig line36 word">
                    	<p><span>项目期限</span><label>
                    	<c:if test="${borrow.borrowtype==4}">秒还</c:if> 
					    <c:if test="${borrow.borrowtype!=4 && borrow.style !=4 }">
					    	<strong>${borrow.timeLimit }</strong>个月 
					    </c:if> 
					    <c:if test="${borrow.borrowtype!=4 && borrow.style ==4 }">
					    	<strong>${borrow.timeLimit }</strong>天
					    </c:if></label></p>
                        <p><span>开放金额 </span><label><strong><fmt:formatNumber value="${borrow.account }"
												pattern="#,##0.##" /></strong>元</label></p>
                   </li>
                </ul>
                <ul class="detailword">
                	<li style="padding-right:346px;"><h6>投标类型&nbsp;&nbsp;&nbsp;&nbsp;${portal:desc(300, borrow.borrowtype)}</h6></li>
                	<li><h6>还款方式&nbsp;&nbsp;&nbsp;&nbsp;${portal:desc(400, borrow.style)}
                	<c:if test="${borrow.style ==4 }">
									    	<font color="#F00000" style="display:none;">（即到期一次性还本息）</font>
									    </c:if></h6></li>
                    <li style="padding-right:228px;"><h6>投资100元，完成后可获利收益<fmt:formatNumber value="${inter.interest}" pattern="#,##0.00" />元</h6></li>
                	<c:if test="${borrow.status==2}">
                	<li><h6>剩余时间&nbsp;&nbsp;&nbsp;&nbsp;<label id="borrow_remainingTime"></label></h6></li>
                	</c:if>
                </ul>
             </div>
             <!-- 左上角结束-->
             
             <!-- 预发标 -->
			 <c:if test="${borrow.status==1}">
			    <div class="detail-col-r fr">
             	<div class="detali-bg detali-syz">
                	
                     <div class="pd30">
                	<h5 class="f14 line32">开始时间</h5>
                    <h2>
                    <span class="f24 pd20"><fmt:formatDate value="${borrow.timingBorrowTimeDate}" pattern="yyyy-MM-dd"/></span>
                    <span class="f24"><fmt:formatDate value="${borrow.timingBorrowTimeDate}" pattern="HH:mm:ss"/></span></h2>
					<div class="btn-box">
                         <a href="#" class="btn btn-tmbg f18" style="width:100%; cursor:default">敬请期待</a>
                    </div>
                    </div>
                    
                    <i class="gc-img-wap syz"></i>
                </div>
                </div>
			 </c:if>
             <!-- 投标中 -->
             <c:if test="${borrow.status==2}">
             <div class="detail-col-r fr">
                <div class="detail-join-m">
                    <div class="clearfix pdt10"><span class="f14">剩余可投<strong class="oren f18 pdla">￥<fmt:formatNumber value="${borrow.account-borrow.accountYes }" pattern="#,##0.##" /></strong>元 </span></div>
                    <div class="clearfix pdt10"><a target="_blank" href="${basePath}/account/topup/toTopupIndex.html" class="fr bd">充值</a>
                    <span class="f14 gary2">账户余额<strong class="f14 pdla gary2">
                     <c:if test="${loginMember!=null}">
				      <fmt:formatNumber value="${useMoney }" pattern="#,##0.##" /> 
					  </strong>元</c:if>
					  <c:if test="${loginMember==null}">
					     <a href="${path}/member/toLogin.html?backUrl=1" class="pdla login">登录</a>后可见</span>
					   </c:if>
					  </span>
					 </div>
					<c:if test="${loginMember!=null && isExistCurAccount == true}"> 
	                    <div class="clearfix pdt10">
	                    <input type="checkbox" name = "isUseCurMoney" id="isUseCurMoney" value="0" onclick="useCurMoney();" checked="checked" class="checkbox-btn"  /><span class="gary2 f14 ">
	                                                  使用活期宝余额：<strong class="f14 pdla gary2"><fmt:formatNumber value="${maxCurMoney}"  pattern="#,##0.00" /></strong>元</span> </div>
                    </c:if>
                    <div class="clearfix pdt10"><span class="f14 pdra">投标金额</span><input name="tenderMoney" id="pay_money" class="form-inpyt-sm yuan2" style="width:160px" type="text" placeholder="请输入加入金额">
                    <c:if test="${loginMember!=null}">
                        <a href="javascript:enterMomey();" style="padding:2px 0 0 5px;">全额加入</a>
                    </c:if>
                    </div>
                    <div class="clearfix pdt26">
                        <div class="btn-box2">
                            <c:if test="${borrow.isAutotender == 0 }">
								<c:if test="${isToTender != null && isToTender == false }">
								<input  style="width:255px; height:40px; background:#999999; text-align:center; border:0px; color:#FFFFFF; font-size:14px;" type="button" value="投&nbsp;标"/>
								</c:if>
								<c:if test="${isToTender == null || isToTender == true }">
								<input class="btn btn-join f18" type="button" value="立即投资" onclick="toTender1();"/>
								</c:if>
							</c:if>
							<c:if test="${borrow.isAutotender == 1 }">
								<!-- 自动投标中 -->
								<input class="btn btn-join f18" type="button" value="自动投标中,请稍后..." disabled="disabled" />
							</c:if>
                            
                        </div>
                     </div>
                </div>
             </div>
             </c:if>
             <!-- 还款中 -->
			 <c:if test="${borrow.status==4}">
			 <div class="detail-col-r fr">
             	<div class="detali-zrz detali-bg">
                	<h5 class="f14">
                    	<p>待还本息<strong>￥<fmt:formatNumber
											value="${borrowDetailRepayVo.waitToPayMoney }" pattern="#,##0.00" /></strong>元</p>
                        <p><span class="f32 oren">${borrowDetailRepayVo.remainPeriods }</span>期<br/>剩余期数</p>
                        <p><span class="f32 oren"><fmt:formatDate
											value="${borrowDetailRepayVo.repaymentTimeDate}" pattern="yyyy-MM-dd" /></span><br/>下一期还款日</p>
                    </h5>
                    <i class="gc-img-wap hkz"></i>
                </div>
             </div>
			 </c:if>
			 <!-- 还款结束 -->
			 <c:if test="${borrow.status==5}">
			  <div class="detail-col-r fr">
             	<div class="detali-zrz detali-bg">
                	<h5 class="f14">
                        <p><span class="f24 oren">
                        <fmt:formatNumber value="${repaymentYesAccountTotal}" pattern="#,##0.00" /></span>元<br/>已还本息 </p>
                        <p><span class="f32 oren">${borrow.tenderTimes }</span><br/>加入人次</p>
                    </h5>
                    <i class="gc-img-wap yhk"></i>
                </div>
                </div>
			 </c:if>
			  <!-- 已垫付-->
			 <c:if test="${borrow.status==42}">
			  <div class="detail-col-r fr">
             	<div class="detali-zrz detali-bg">
                	<h5 class="f14">
                    	<p>垫付金额<strong>￥<fmt:formatNumber
											value="${borrowDetailWebPayVo.advanceYesAccount}" pattern="#,##0.00" /> </strong>元</p>
                        <p>第<span class="f32 oren">${borrowDetailWebPayVo.webPayOrder }</span>期<br/>垫付期数</p>
                        <p><span class="f32 oren"><fmt:formatDate
											value="${borrowDetailWebPayVo.advanceTime }"
											pattern="yyyy-MM-dd" /></span><br/>垫付日期</p>
                    </h5>
                    <i class="gc-img-wap ydf"></i>
                </div>
              </div>
			 </c:if>
			 <c:if test="${borrow.status==-1}">
			     <div class="detail-col-r fr">
             	<div class="detali-zrz detali-bg">
                	<h5 class="f14">
                        <p><span class="f24 oren">已流标</span><br/>标的状态 </p>
                        <p><span class="f32 oren"><fmt:formatDate value="${borrow.cancelTimeDate}" pattern="yyyy-MM-dd"/></span><br/>流标时间</p>
                    </h5>
                    <i class="gc-img-wap ylb"></i>
                </div>
                </div>
			 </c:if>
			 <c:if test="${borrow.status==-2}">
			     <div class="detail-col-r fr">
             	<div class="detali-zrz detali-bg">
                	<h5 class="f14">
                        <p><span class="f24 oren">已撤标</span><br/>标的状态 </p>
                        <p><span class="f32 oren">
	                        <fmt:formatDate value="${borrow.cancelTimeDate}" pattern="yyyy-MM-dd"/>
							<c:if test="${borrow.cancelTimeDate == null || borrow.cancelTimeDate == ''}">无</c:if>
                        </span><br/>撤标时间</p>
                    </h5>
                    <i class="gc-img-wap ycb"></i>
                </div>
                </div>
			 </c:if>
         </div>
    </div>
    
<!--弹层star--->
<form action="" method="post" id="tenderBorrowForm">
	<input type="hidden" id="effectiveTenderMoney" value="${effectiveTenderMoney }"/>
	<input type="hidden" name="borrowid" value="${borrow.id}"/>
	<input type="hidden" name="${borrow.uuid }" value="${borrow.uuid}"/>
	<input type="hidden" name="tenderMoney" id="pay_money2"/>
	<input type="hidden" name = "isUseCurMoney" id="isUseCurMoney1" />
	
<div class="layer-jion">
	<div class="cont-word">
    	<div class="title"><h4>投资散标</h4><div style="cursor: pointer;" class="icon icon-close fr"></div></div>
        <div class="form-info-layer">
        	<div class="form-col3">
                <label for="" class="colleft form-lable">投标金额 </label>
                <span class="fl money"><strong class="oren f14"  id="pay_money1"></strong>元</span>
            </div>
            
        	<div class="form-col2">
                <label for="" class="colleft form-lable">交易密码</label>
                <input type="password"  name="payPassword" id="payPassword" style="width:120px" class="colright form-inpyt-sm"  placeholder="输入交易密码"><a target="_blank" href="${path}/AccountSafetyCentre/safetyCentre/enterFindTransactionPwd.html" class="fl pdlr10 line32">忘记密码</a>
            </div>
            <c:if test="${borrow.bidPassword != null && borrow.bidPassword != ''}">	
            <div class="form-col2">
                <label for="" class="colleft form-lable">定向密码</label>
                <input type="password"  name="bidPassword" id="bidPassword" style="width:120px" class="colright form-inpyt-sm"  placeholder="请输入认购密码">
            </div>
            </c:if>
            <div class="form-col2">
                <label for="" class="colleft form-lable">验证码</label>
                <input type="text"  name="checkCode" id="checkCode" maxlength="4"  style="width:120px" class="colright form-inpyt-sm"  placeholder="验证码">
                <a href="javascript:loadimage();" class="fl" style="float:right;padding-right: 45px;padding-top: 10px;"><img name="randImage" id="randImage" 
					src="${basePath}/random.jsp"/></a>
            </div>
         
            <div class="form-col2">
            <button type="button" class="remove">取消</button><button type="button" class="enter" onClick="tender_borrow();">确认</button>
            </div>
         </div>
    </div> 
</div>
</form>
<!--弹层end--->
<script type="text/javascript">
/**秒数*/
var SysSecond; 
/**循环计算倒计时*/
var InterValObj;
//页面 当前时间
var addNowTime=0;
//当前循环对象
var addInterValObj;

$(function(){
	var borrowStatus = '${borrow.status}';
	var nowTime = $("#nowTime").val(); 
	/**预发标,发标剩余时间*/
	if(borrowStatus=="2"){
		var validTime = '${borrow.validTime}';
		var borrow_addtime_hide = $("#borrow_addtime_hide").val();
		//这里获取倒计时的起始时间 
		SysSecond = parseFloat(new Date(borrow_addtime_hide).getTime()/1000+3600*24*parseFloat(validTime))-parseFloat(new Date(nowTime).getTime()/1000);
		//剩余时间
		InterValObj = window.setInterval(SetRemainTime, 1000); //间隔函数，1秒执行 
	} else if (borrowStatus=="1") {
		 $("#borrow_remainingTime").html("无");
	}
	//改变按钮状态
	changeBtnState();
	$('.remove').click(function(){
		$(".layer-jion").hide();
		$(".cont-word").animate({top:"0px"});
	});
});
/**
 * 倒计时
 */
function SetRemainTime() { 
	  if (SysSecond > 0) { 
		   SysSecond = SysSecond - 1; 
		   var second = Math.floor(SysSecond % 60);             // 计算秒     
		   var minute = Math.floor((SysSecond / 60) % 60);      //计算分 
		   var hour = Math.floor((SysSecond / 3600) % 24);      //计算小时 
		   var day = Math.floor((SysSecond / 3600) / 24);        //计算天 
		   $("#borrow_remainingTime").html('<span>'+day+'</span>天<span>'+hour+'</span>时<span >'+minute+'</span>分<span>'+second+'</span>秒'); 
	  } else {//剩余时间小于或等于0的时候，就停止间隔函数 
		   window.clearInterval(InterValObj); 
		   //这里可以添加倒计时时间为0后需要执行的事件 
		   $("#borrow_remainingTime").html("0天0时0分0秒"); 
	  } 
} 

/**
 * 投标方法
 */
function toTender(){
	 if(${null==portal:currentUser()}){
		 layer.msg("请先登录", 1, 5,function(){
			 window.location.href="${path}/member/toLogin.html";
		 });
		 return;
	 }
	 if(${portal:hasRole("borrow")}){
		 layer.msg("您是借款用户,不能进行此操作", 1, 5);
		 return;
	 };
	 
	 $.ajax({
		url : '${basePath}/appro/findAppro.html',
		data : {},
		type : 'post',
		success : function(data){
			if(data == null || (data.emailChecked != 1 && data.mobilePassed != 1)){
				layer.alert('请先进行邮箱或手机认证！', 5, "温馨提示",function(){
					location.href="${path }/AccountSafetyCentre/safetyIndex.html";
		    	});
			}else if(data.namePassed != 1){
				layer.alert('请先进行实名认证！！', 5, "温馨提示",function(){
					location.href="${path }/AccountSafetyCentre/safetyIndex.html";
		    	});
			}else{
				tenderto();
			}
		},
		error : function(data) {
			layer.msg("网络连接异常，请刷新页面或稍后重试！", 2, 5);
		}
	});	
}
/**
 * 投标方法1
 */
function toTender1(){
	 if(${null==portal:currentUser()}){
		 layer.msg("请先登录", 1, 5,function(){
			 window.location.href="${path}/member/toLogin.html?backUrl=1";
		 });
		 return;
	 }
	 if(${portal:hasRole("borrow")}){
		 layer.msg("您是借款用户,不能进行此操作", 1, 5);
		 return;
	 };
	 
	 $.ajax({
		url : '${basePath}/appro/findAppro.html',
		data : {},
		type : 'post',
		success : function(data){
			if(data == null || (data.emailChecked != 1 && data.mobilePassed != 1)){
				layer.alert('请先进行邮箱或手机认证！', 5, "温馨提示",function(){
					location.href="${path }/AccountSafetyCentre/safetyIndex.html";
		    	});
			}else if(data.namePassed != 1){
				layer.alert('请先进行实名认证！！', 5, "温馨提示",function(){
					location.href="${path }/AccountSafetyCentre/safetyIndex.html";
		    	});
			}else{
				tenderto();
			}
		},
		error : function(data) {
			layer.msg("网络连接异常，请刷新页面或稍后重试！", 2, 5);
		}
	});	
}
function tenderto(){
	$.ajax({
		url : '${basePath}/tender/judgTender.html?borrowId=${borrow.id}',
		data : {type:1},
		type : 'post',
		dataType : 'json',
		success : function(data){
			if("0"==data.code || "-1"==data.code || "-2"==data.code || "-3"==data.code || "-4"==data.code || "-5"==data.code || "-6"==data.code){
				layer.msg(data.message, 2, 5,function(){
				    if("-1"==data.code){
				    	window.location.href="${path}/AccountSafetyCentre/safetyIndex.html";
				    }else if("-2"==data.code){
				    	window.location.href="${path}/account/approve/realname/toRealNameAppro.html";
				    }else if("-4"==data.code){
				    	window.location.href="${path}/account/safe/toSetPayPwd.html";
				    }else if("-5"==data.code){
				    	window.location.href="${path}/bankinfo/toBankCard.html";
				    }if("-6" == data.code){
					}
				});
				return;
			}
			if("-99" == data.code){
				return;
			}
			if(!validateTenderData1()){
				return;
			}
			$(".layer-jion").show();
			$(".cont-word .contls-box").animate({top:"20px"}); 	
			$('#checkCode').val('');
			$('#bidPassword').val('');
			$('#payPassword').val('');
		},
		error : function(data) {
			layer.msg("网络连接异常，请刷新页面或稍后重试！", 2, 5);
		}
	});	
}

/**
 * 改变按钮状态
 */
function changeBtnState(){
	//当前时间
	var nowTimeStr = '<fmt:formatDate value="${nowTime}" pattern="yyyy/MM/dd HH:mm:ss"/>';
	var publishTimeStr = '<fmt:formatDate value="${borrow.timingBorrowTimeDate}" pattern="yyyy/MM/dd HH:mm:ss"/>';
	addInterValObj = window.setInterval(function (){setOpenTime(nowTimeStr,publishTimeStr);}, 1000); //间隔函数，1秒执行
}

/**
 * 设置启动开通时间
 */
function setOpenTime(nowTimeStr,publishTimeStr) {
	//是否结束循环
	var isQuitLoop = true;
	//时间加1秒
	if(addNowTime>0){
		addNowTime = addNowTime+1;
	}else{
		addNowTime = new Date(nowTimeStr).getTime()/1000+1;
	}
	//循环发布时间
   	var publishTime  = new Date(publishTimeStr).getTime()/1000;
	var status = ${borrow.status};
	//如果有状态为1并且当前时间小于发布时间的继续循环
	if(status==1 && parseInt(addNowTime) < parseInt(publishTime)){
		isQuitLoop = false;
	}
   	if(parseInt(addNowTime) < parseInt(publishTime)){
   		 if(status==1){
			 var remainSecond = parseInt(publishTime)-parseInt(addNowTime);
			 if(remainSecond<=1800){
				 if(remainSecond<=59){
					 $("#remainSecond").val(remainSecond+"秒");
				 }else{
					$("#remainSecond").val(parseInt(remainSecond/60)+"分"+remainSecond%60+"秒");						 
				 }
			 }
		 }
   	}
	//停止循环
	if(isQuitLoop){
		window.clearInterval(addInterValObj);
	}
} 
/**
 * 刷新验证码
 */
function loadimage() {
	document.getElementById("randImage").src = "${basePath}/random.jsp?" + Math.random();
}
/**
 * 验证投标数据
 */
function validateTenderData1(){
	var msg = "";
	var pay_money = Number($("#pay_money").val());
	var alsoNeed = Number("${alsoNeed}");
	var payPassword = $("#payPassword").val();	
	var lowestAccount = Number("${borrow.lowestAccount}");
	var effectiveTenderMoney = Number($("#effectiveTenderMoney").val());
	var reg= /^\d+[\.]?\d{0,2}$/g;
	var zfdsReg = /^(([1-9]\d{0,9})|0)(\.\d{1,2})?$/;;//金额的正则表达式

	if(pay_money==null || pay_money==""){
		msg = msg + "-投标金额未填写！<br/>";
	}else if(!zfdsReg.test(pay_money)){
		msg = msg + "-输入金额有误！<br/>";
	}else if(pay_money==0){
		msg = msg + "-投标金额不能为0！<br/>";
	}else if(!reg.test(pay_money)){
		msg = msg + "-只能保留2位小数！<br/>";
	}else {
		var accountUseMoney = Number("${account.useMoney}");
		var maxCurMoney = Number("${maxCurMoney}");
		if($("#isUseCurMoney").val() == 1){
			if(new Number(accountUseMoney + maxCurMoney) <= 0){
				msg = msg + "-您的可用余额不足！<br/>";
			}
		}else{
			if(accountUseMoney <= 0){
				msg = msg + "-您的可用余额不足！<br/>";
			}
		}
		if(lowestAccount > effectiveTenderMoney && pay_money != effectiveTenderMoney){
			msg = msg + "-投标金额只能为"+effectiveTenderMoney+"元！<br/>";
		}
		if(effectiveTenderMoney >= lowestAccount && pay_money < lowestAccount){
			msg = msg + "-投标金额不能少于"+lowestAccount+"元！<br/>";
		}
	}
	if(alsoNeed!= 0 && pay_money > alsoNeed){
		msg = msg + "-最大投标金额为"+alsoNeed+"！<br/>";
	}
	if(msg!=""){
		layer.msg(msg,2,5);
		return false;
	}
	$('#pay_money1').html(formatMoney(pay_money,2));
	$('#pay_money2').val(pay_money);
	return true;
}
/**
 * 验证投标数据
 */
function validateTenderData2(){
	var msg = "";
	var pay_money = Number($("#pay_money").val());
	var alsoNeed = Number("${alsoNeed}");
	var payPassword = $("#payPassword").val();
	var checkCode = $("#checkCode").val();
	
	var lowestAccount = Number("${borrow.lowestAccount}");
	var effectiveTenderMoney = Number($("#effectiveTenderMoney").val());
	var reg= /^\d+[\.]?\d{0,2}$/g;
	var zfdsReg = /^(([1-9]\d{0,9})|0)(\.\d{1,2})?$/;;//金额的正则表达式

	if(pay_money==null || pay_money==""){
		msg = msg + "-投标金额未填写！<br/>";
	}else if(!zfdsReg.test(pay_money)){
		msg = msg + "-输入金额有误！<br/>";
	}else if(pay_money==0){
		msg = msg + "-投标金额不能为0！<br/>";
	}else if(!reg.test(pay_money)){
		msg = msg + "-只能保留2位小数！<br/>";
	}else {
		var accountUseMoney = Number("${account.useMoney}");
		var maxCurMoney = Number("${maxCurMoney}");
		if($("#isUseCurMoney").val() == 1){
			if(new Number(accountUseMoney + maxCurMoney) <= 0){
				msg = msg + "-您的可用余额不足！<br/>";
			}
		}else{
			if(accountUseMoney <= 0){
				msg = msg + "-您的可用余额不足！<br/>";
			}
		}
		if(lowestAccount > effectiveTenderMoney && pay_money != effectiveTenderMoney){
			msg = msg + "-投标金额只能为"+effectiveTenderMoney+"元！<br/>";
		}
		if(effectiveTenderMoney >= lowestAccount && pay_money < lowestAccount){
			msg = msg + "-投标金额不能少于"+lowestAccount+"元！<br/>";
		}
	}
	if(alsoNeed!= 0 && pay_money > alsoNeed){
		msg = msg + "-最大投标金额为"+alsoNeed+"！<br/>";
	}
    if(payPassword == null || payPassword == ""){
		msg = msg + "-交易密码未填写！<br/>";
	}
	if(checkCode==null || checkCode==""){
		msg = msg + "-验证码未填写！<br/>";
	}
	if('${!empty borrow.bidPassword}'=='true'){
		var bidPassword = $("#bidPassword").val();
		if(bidPassword == null || bidPassword == ""){
			msg = msg + "-定向标密码未填写！<br/>";
		}
	}
		
	if(msg!=""){
		layer.msg(msg,2,5);
		return false;
	}
	return true;
}
/**
 * 使用活期宝金额
 */
function useCurMoney(){
	var isUseCurMoney = $("#isUseCurMoney").val();
	if(isUseCurMoney == 0){
		$("#isUseCurMoney").val(1);
		$("#isUseCurMoney").attr("checked","checked");
	}else{
		$("#isUseCurMoney").val(0);
		$("#isUseCurMoney").removeAttr("checked");
	}
	$.ajax({
		url : '${basePath}/borrow/borrow/findEffectiveTenderMoney/${borrow.id}.html',
		data : {isUseCurMoney:$("#isUseCurMoney").val()},
		type : 'post',
		dataType : 'json',
		success : function(data){
			$("#effectiveTenderMoney").val(data);
		},
		error : function(data) {
			layer.msg("网络连接异常，请刷新页面或稍后重试！", 2, 5);
		}
	});
}
$(function() {
	useCurMoney();//2015.12.18 默认勾选活期宝
});
var _load;
//自动输入投标金额
function enterMomey(){
	$.ajax({
		url : '${basePath}/borrow/borrow/findEffectiveTenderMoney/${borrow.id}.html',
		data : {isUseCurMoney:$("#isUseCurMoney").val()},
		type : 'post',
		dataType : 'json',
		success : function(data){
			if(data != null){
				var effectiveTenderMoney = data;
				if(effectiveTenderMoney<=0){
					layer.msg("您的金额不符合要求，不能投标。",2,5);
				}else{
					$("#pay_money").val(effectiveTenderMoney);
				}
			}else{
				layer.msg("您的金额不符合要求，不能投标。",2,5);
			}
		},
		error : function(data) {
			layer.msg("网络连接异常，请刷新页面或稍后重试！", 2, 5);
		}
	});	
}
/**
 * 投标
 */
function tender_borrow(){
	var xs_tag = '${xs_tag}';
    if(!validateTenderData2()){
    	return;
    }
    var isUseCurMoney= $("#isUseCurMoney")[0].checked;
    if(isUseCurMoney){
    	$('#isUseCurMoney1').val('1');
    }else{
    	$('#isUseCurMoney1').val('0');
    }
     
	if(layer.confirm("确定要投标吗？",function(){
		$("#btnTenderBorrow").removeAttr("onclick");
		_load = layer.load('处理中..');
		$("#tenderBorrowForm").ajaxSubmit({
		    url : '${basePath}/borrow/borrow/tenderBorrow.html',
		    type: "POST",
		    success:function(msg){
		    	layer.close(_load);
		    	if(msg.code=="1"){
		    		layer.msg("投标成功！",1,1);
		    		if(xs_tag=='9'){
		    			// 新手专区  新手标
		    			window.parent.location.href="${path}/newPeopleArea/newPeopleAreaBiao.html";
		    		}else{
		    			window.parent.location.href="${path}/toubiao/${borrow.id}.html";
		    		}
		    	}else{
		    		loadimage();
		    		if(msg.message != ''){
		    			layer.msg(msg.message, 2, 5);
		    		}
		    		$("#btnTenderBorrow").attr("onclick","tender_borrow()");
		    	}
		    },
			error : function() {
				layer.close(_load);
				loadimage();
				$("#btnTenderBorrow").attr("onclick","tender_borrow()");
				layer.msg("网络连接超时，请您稍后重试", 2, 5);
		    } 
		 });
	},'',function(){
		$("#btnTenderBorrow").attr("onclick","tender_borrow()");
	}));
}
function formatMoney(mVal, iAccuracy){  
    var fTmp = 0.00;//临时变量  
    var iFra = 0;//小数部分  
    var iInt = 0;//整数部分  
    var aBuf = new Array(); //输出缓存  
    var bPositive = true; //保存正负值标记(true:正数)  
    /** 
     * 输出定长字符串，不够补0 
     * <li>闭包函数</li> 
     * @param int iVal 值 
     * @param int iLen 输出的长度 
     */  
    function funZero(iVal, iLen){  
        var sTmp = iVal.toString();  
        var sBuf = new Array();  
        for(var i=0,iLoop=iLen-sTmp.length; i<iLoop; i++)  
            sBuf.push('0');  
        sBuf.push(sTmp);  
        return sBuf.join('');  
    };  

    if (typeof(iAccuracy) === 'undefined')  
        iAccuracy = 2;  
    bPositive = (mVal >= 0);//取出正负号  
    fTmp = (isNaN(fTmp = parseFloat(mVal))) ? 0 : Math.abs(fTmp);//强制转换为绝对值数浮点  
    //所有内容用正数规则处理  
    iInt = parseInt(fTmp); //分离整数部分  
    iFra = parseInt((fTmp - iInt) * Math.pow(10,iAccuracy) + 0.5); //分离小数部分(四舍五入)  

    do{  
        aBuf.unshift(funZero(iInt % 1000, 3));  
    }while((iInt = parseInt(iInt/1000)));  
    aBuf[0] = parseInt(aBuf[0]).toString();//最高段区去掉前导0  
    return ((bPositive)?'':'-') + aBuf.join(',') +'.'+ ((0 === iFra)?'00':funZero(iFra, iAccuracy));  
}
</script>
