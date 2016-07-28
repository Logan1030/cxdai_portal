<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>资金周转_投资理财平台_p2p网络投资理财-国诚金融官网</title>
<meta name="keywords" content="资金周转,投资理财平台,p2p网络投资理财" />
<meta name="description" content="国诚金融是中国3A信用评级互联网理财借贷平台，如果你想了解更多P2P理财、网络投资理财或者资金周转借贷信息，详情请登陆www.gcjr.com。">
<%@ include file="/WEB-INF/page/common/public2.jsp"%>
<link href="${basePath}/css/circle.css" rel="stylesheet" type="text/css" />
</head>
<body>
<!--头部开始-->
<%@ include file="/WEB-INF/page/common/header.jsp" %>
<!--头部结束-->
<div class="clear"></div>
<!--内容开始-->
<div class="product-wrap wrapperbanner"><!--banner star-->
	<div class="grid-1100">
        <div class="tz-detail">
            <img src="${basePath}/images/v5/detalibanner.png"/>
            <ul class="col4"><li><a href="${basePath}/dingqibao.html" >定期宝</a></li>
            <li><a href="${basePath}/curInController.html">活期宝</a></li>
            <li><a href="${basePath}/toubiao.html" class="active"  >散标投资</a></li>
            <li><a href="${basePath}/zhaiquan.html">债权转让</a></li></ul>
        </div>
	</div>
</div>
<div class="product-wrap"><!--定期宝star-->
	<div class="grid-1100">
        <div class="product-deatil">
            <h1 class="f16 bule">投标列表</h1>
            <div class="tz-dqb2 clearfix">
                <div class="col clearfix">
                        <span class="fl gary2">标的类型：</span>
                        <div class="btn-box-bg">
                            <div class="btn-box4 borrowType"><a href="javascript:void(0);" class="active" value=''>不限</a></div>
                            <div class="btn-box4 borrowType"><a href="javascript:void(0);" value='2'>资产抵押标</a></div>
                            <div class="btn-box4 borrowType"><a href="javascript:void(0);" value='1'>信用认证标</a></div>
                        </div>
                 </div>
                <div class="col clearfix">
                        <span class="fl gary2">标的状态：</span>
                        <div class="btn-box-bg">
                            <div class="btn-box4 limitTime"><a href="javascript:void(0);" value="" class="active">不限</a></div>
                            <div class="btn-box4 limitTime"><a href="javascript:void(0);" value="isAdvanced">预发</a></div>
                            <div class="btn-box4 limitTime"><a href="javascript:void(0);" value="isTendering">投标中</a></div>
                            <div class="btn-box4 limitTime"><a href="javascript:void(0);" value="isComplete" >已完成</a></div>
                            <div class="btn-box4 limitTime"><a href="javascript:void(0);" value="isOverdue">逾期</a></div>
                        </div>
                    </li>
                </div>
                <div class="col clearfix">
                        <span class="fl gary2">标的期限：</span>
                        <div class="btn-box-bg">
                            <div class="btn-box4 remainingTerm"><a href="javascript:void(0);" value="" class="active">不限</a></div>
                            <div class="btn-box4 remainingTerm"><a href="javascript:void(0);" value="2">1-3个月</a></div>
                            <div class="btn-box4 remainingTerm"><a href="javascript:void(0);" value="3">3- 6 个月</a></div>
                            <div class="btn-box4 remainingTerm"><a href="javascript:void(0);" value="4">6 个月以上</a></div>
                        </div>
                    </li>
                </div>
                 <div class="col clearfix">
                        <span class="fl gary2">还款方式：</span>
                        <div class="btn-box-bg">
                            <div class="btn-box4 repayType"><a href="javascript:void(0);" value="" class="active">不限</a></div>
                            <div class="btn-box4 repayType"><a href="javascript:void(0);" value="3">到期还本付息</a></div>
                            <div class="btn-box4 repayType"><a href="javascript:void(0);" value="2">按月付息到期还本</a></div>
                            <div class="btn-box4 repayType"><a href="javascript:void(0);" value="1">等额本息</a></div>
                        </div>
                    </li>
                </div>
             </div>
             <div id="investList">
              
             </div>   
        </div>
        
    </div>
</div>	 
 <div class="clearfix bompd20" style="height:100px;"></div>
<%@ include file="/WEB-INF/page/common/footer.jsp" %>
</body>

<script type="text/javascript">
var	BorrowType ;
var	RepayType ;
var	LimitTime='' ;
var	OrderBy;
var Title;
var BorrowPeople;
var orderType = '';
var OrderName;
var BeginTime;
var EndTime;
var BorrowID;
var  RemainingTerm;

//获取标题文本框内容
$(document).ready(function(){
	
		BorrowType = '${borrowT}';
		searchInvestListStart(1);	   
		if(""!=BorrowType){
			$(".borrowType").find("a").each(function(index,element){
				$(element).parent().removeClass("active");
				if($(element).attr("borrowType")==BorrowType){
					$(element).parent().addClass("active"); //给当前节点添加class
				}
			});
		}
		
		//获取标的类型
		$(".borrowType").click(function(){ //注册click事件
			$(".borrowType").children('a').removeClass("active");
			$(this).siblings().removeClass("active"); //移除同辈节点的class
			$(this).children('a').addClass("active"); //给当前节点添加class
			 	BorrowType = $(this).children("a").attr("value"); //赋值
			 	searchInvestList(1);
		});
		//获取标的状态
		$(".repayType").click(function(){ //注册click事件
			$(".repayType").children('a').removeClass("active");
			$(this).siblings().removeClass("active"); //移除同辈节点的class
			$(this).children('a').addClass("active"); //给当前节点添加class
			RepayType = $(this).children("a").attr("value"); //赋值
		 	searchInvestList(1);
		});
		//获取标的期限
		$(".limitTime").click(function(){ //注册click事件
			$(".limitTime").children('a').removeClass("active");
			$(this).siblings().removeClass("active"); //移除同辈节点的class
			$(this).children('a').addClass("active"); //给当前节点添加class
			LimitTime = $(this).children("a").attr("value"); //赋值
		 	searchInvestList(1);
		});
		//获取标的还款方式
		$(".remainingTerm").click(function(){ //注册click事件
			$(".remainingTerm").children('a').removeClass("active");
			$(this).siblings().removeClass("active"); //移除同辈节点的class
			$(this).children('a').addClass("active"); //给当前节点添加class
			RemainingTerm = $(this).children("a").attr("value"); //赋值
		 	searchInvestList(1);
		});		
});
function searchInvestList(pageNum){
	 
	$.ajax({
		url : '${basePath}/queryInvestList1.html',
		data : {pageNum:pageNum,pageSize:10,'borrowType':BorrowType, 'limitTime':LimitTime,'repayType':RepayType,'orderBy':OrderBy,'title':Title,'borrowPeople':BorrowPeople,'orderType':orderType,'beginTime':BeginTime,'endTime':EndTime,'borrowID':BorrowID,'remainingterm':RemainingTerm,'isShowNewBorrow':1},
		type : 'post',
		dataType : 'text',
		success : function(data){
			$("#investList").html(data);
		},
		error : function(data) {
			layer.msg('网络连接异常，请刷新页面或稍后重试！', 1, 5);
	    }
	});
}

function searchInvestListForIsDefault(pageNum){
	$.ajax({
		url : '${basePath}/queryInvestList1.html',
		data : {pageNum:pageNum,pageSize:10,'borrowType':BorrowType, 'limitTime':LimitTime,'repayType':RepayType,'orderBy':OrderBy,'title':Title,'borrowPeople':BorrowPeople,'orderType':orderType,'beginTime':BeginTime,'endTime':EndTime,'borrowID':BorrowID,'remainingterm':RemainingTerm,'isShowNewBorrow':0,'isdefult':1},
		type : 'post',
		dataType : 'text',
		success : function(data){
			$("#investList").html(data);
		},
		error : function(data) {
			layer.msg('网络连接异常，请刷新页面或稍后重试！', 1, 5);
	    }
	});
}


function searchInvestListStart(pageNum){
	$.ajax({
		url : '${basePath}/queryInvestList1.html',
		data : {pageNum:pageNum,pageSize:10,'borrowType':BorrowType, 'limitTime':LimitTime,'repayType':RepayType,'orderBy':OrderBy,'title':Title,'borrowPeople':BorrowPeople,'orderType':orderType,'remainingterm':RemainingTerm,'isShowNewBorrow':0,'isdefult':1},
		type : 'post',
		dataType : 'text',
		success : function(data){
			$("#investList").html(data);
		},
		error : function(data) {

	    }
	});
}

function automaticTender(){
	if(${null==portal:currentUser()}){
		window.location.href="${path}/member/toLogin.html";
	}else{
		window.location.href="${path}/myaccount/autoInvest/autoInvestMain.html?tab=2";
	}
}



</script>
</html>
