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
            <ul class="col4">
            <ul class="col4"><li><a href="${basePath}/dingqibao.html" >定期宝</a></li>
            <li><a href="${basePath}/curInController.html">活期宝</a></li>
            <li><a href="${basePath}/toubiao.html"  >散标投资</a></li>
            <li><a href="${basePath}/zhaiquan.html"  class="active">债权转让</a></li></ul>
            </ul>
        </div>
	</div>
</div>
<div class="product-wrap"><!--定期宝star-->
	<div class="grid-1100">
        <div class="product-deatil">
            <h1 class="f16 bule">
            <a href="javascript:void(0);" class="deatil active" value="1">普通标转让</a>
            <a href="javascript:void(0);" class="deatil" value="2" >直通车转让</a></h1>
            <div class="tz-dqb3 clearfix">
                <div class="col clearfix">
                        <span class="fl gary2">标的状态：</span>
                        <div class="btn-box-bg">
                            <div class="btn-box4 repayType"><a href="javascript:void(0);" value="" >不限</a></div>
                            <div class="btn-box4 repayType"><a href="javascript:void(0);"  class="active" value="2">转让中</a></div>
                            <div class="btn-box4 repayType"><a href="javascript:void(0);" value="4">已转让</a></div>
                        </div>
                 </div>
                
             </div>
             <div id="dataList">
              
             </div>
             
        </div>
         
    </div>
</div>	 
 <div class="clearfix bompd20" style="height:100px;"></div>
<%@ include file="/WEB-INF/page/common/footer.jsp" %>
</body>
<script type="text/javascript">
	var BorrowType;
	var RepayType;
	var TransferStatus=2;
	var RemainingTerm;
	var OrderBy;
	var orderType = '';
	var OrderName;

	$(document).ready(function() {
		 
		loadTransferList(1);
		//获取标的状态
		$(".repayType").click(function(){ //注册click事件
			 
			$(".repayType").children('a').removeClass("active");
			$(this).siblings().removeClass("active"); //移除同辈节点的class
			$(this).children('a').addClass("active"); //给当前节点添加class
			TransferStatus = $(this).children("a").attr("value"); //赋
			if($('.product-deatil .active').attr("value")=='1'){
				searchTransferList(1);
			}else{
				searchTransferFirstList(1);
			}
		});
		$('.product-deatil .deatil').click(function(){
			 
			$(this).siblings().removeClass("active"); 
			$(this).addClass("active"); //给当前节点添加class
			if($(this).attr("value")=='1'){
				searchTransferList(1);
			}else{ 
				searchTransferFirstList(1);
			}
		});
		
	 
	});
    
	function searchTransferList(pageNum) {

		$.ajax({
			url : '${basePath}/queryTransferList1.html',
			data : {
				pageNum : pageNum,
				pageSize : 10,
				'borrowType' : BorrowType,
				'transferStatus' : TransferStatus,
				'remainingTerm' : RemainingTerm,
				'repayType' : RepayType,
				'orderBy' : OrderBy,
				'orderType' : orderType
			},
			type : 'post',
			dataType : 'text',
			success : function(data) {
				$('#dataList').empty();
				$("#dataList").html(data);
			},
			error : function(data) {
				alert("网络连接异常，请刷新页面或稍后重试！");
			}
		});
	}

	function loadTransferList(pageNum) {
		$.ajax({
			url : '${basePath}/queryTransferList1.html',
			data : {
				pageNum : pageNum,
				pageSize : 10,
				'borrowType' : BorrowType,
				'transferStatus' : TransferStatus,
				'remainingTerm' : RemainingTerm,
				'repayType' : RepayType,
				'orderBy' : OrderBy,
				'orderType' : orderType
			},
			type : 'post',
			dataType : 'text',
			success : function(data) {
				$('#dataList').empty();
				$("#dataList").html(data);
			},
			error : function(data) {
				alert("网络连接异常，请刷新页面或稍后重试！");
			}
		});
	}
	/**
	 * 查询直通车转让信息
	 */
	function searchTransferFirstList(pageNum) {
        
		$.ajax({
			url : '${basePath}/zhitongche/zhuanrang/queryTransferList1/'
					+ pageNum + '.html',
			data : {'transferStatus' : TransferStatus},
			type : 'post',
			dataType : 'text',
			success : function(data) {
				$('#dataList').empty();
				$("#dataList").html(data);
			},
			error : function(data) {
				alert("网络连接异常，请刷新页面或稍后重试！");
			}
		});
	}
</script>
</html>
