<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<title>实时财务_国诚金融</title>
<!-- 图形报表js -->
<script type="text/javascript" src="${basePath}/js/highcharts/highcharts3.0.7.js" ></script>
<script type="text/javascript" src="${basePath}/js/highcharts/themes/grid.js"></script>
<script type="text/javascript" src="${basePath}/js/highcharts/myChart.js"></script>
</head>
<body>
	<!--头部开始-->
	<%@ include file="/WEB-INF/page/common/header.jsp"%>
	<!--头部结束-->
	<div class="clear"></div>
	
	<!--数据展示-->   
	 <div id="Bmain">  
           <div class="caiwutitle"><font color="#00a7e5">国诚金融</font>实时财务数据</div>
            <!-- 折线图 -->
            <div class="caiwublock" id="moneyPersonChart"> </div> 
            
            <!-- 成交分布图与待收分布图 -->
           <div id="caiwu2bg">
                    <div class="bingtu margin10" id="successBorrowChart"></div>
                    <div class="bingtu" id="waitReceiveChart"></div>
           </div>
          <!--数据展示结束-->  
          <!--数据表格展示--> 
  		  <div class="clear"></div>  
  
		   <div class="caiwubg">
		   		<!-- 金额人数数据 -->
		   		<span id="invest_cent"></span>   
		   		
		        <div class="shuoming"><a href="javascript:void(0);" onclick="showDataNameDesc();">数据名称说明</a></div>
		             <div class="clear"></div>  
		             <div id="dataNameDesc" style="display:none">
		                  <table border="0">
		                  <tr>
			                  <td width="50%" align="left">总成交金额：网站成立以来抵押标、担保标、净值标、信用标和秒标的累计交易量；</td>
			                  <td width="50%" align="left">逾期已处理总额：指信用标、担保标和抵押标逾期后，借款者还请本息的金额；</td>
		                  </tr>
		                  <tr>
			                  <td align="left">待收本息总额：包括抵押标、担保标、信用标和净值标;</td>
			                  <td align="left">累计借款者人数：在网站发布过抵押标、担保标或信用标的人数总和（多次发标计算一个借款者）；</td>
		                  </tr>
		                  <tr>
			                  <td align="left">投资者总收益：只计算抵押标、担保标和信用标的收益总额；</td>
			                  <td align="left">结清借款者人数：在网站发布过抵押标、担保标或信用标并结清还款，没有待还的人数总和；</td>
		                  </tr>
		                  <tr>
			                  <td align="left">正常还款总额：只计算抵押标、担保标和信用标的还款情况；</td>
			                  <td align="left">未结清借款者人数：在网站发布过抵押标、担保标或信用标，并且有待还的人数，以及已经还款，但实际还款时间处于三天观察期内的人数总和。</td>
		                  </tr>
		                  <tr>
			                  <td align="left">逾期总额：有过逾期记录的抵押标、担保标或信用标总额；</td>
			                  <td></td>
		                  </tr>
		                  </table>
		         </div>    
		         
		   </div>  
            
             <div class="caiwubg-title">
                  <ul>
                       <li id="week_info_li" class="men_li"><a href="javascript:void(0);" onclick="toggleChart(this,'week_info');">借款列表</a></li>
                       <li id="overdue_info_li"><a href="javascript:void(0);" onclick="toggleChart(this,'overdue_info');">逾期</a></li>
                       <li id="finish_info_li"><a href="javascript:void(0);" onclick="toggleChart(this,'finish_info');">结清</a></li> 
                  </ul>
             </div>
              <div class="clear"></div>
             <!--抵押标借款数据统计信息-->
			 <div id="borrow_main_tab"></div>
      </div>
    <!--数据表格展示结束--> 
	
 	<!--内容结束-->
	<div>
		<%@ include file="/WEB-INF/page/common/footer.jsp"%>
	</div>
	
</body>
<script type="text/javascript">
$(function(){
   	// 实时累计金额人数折线图
    showMoneyPersonChart();
	//成交分布图
	showSuccessBorrowChart();
   	//待收分布图
    showWaitReceiveChart();
	weekInfo();
	//金融人数数据
	moneyPersonData();
});
/*
* 成交分布图
*/
function showSuccessBorrowChart(){
	$.get("${basePath}/chart/finance/successBorrowChart.html",
		 function(data) {
			myChart("successBorrowChart",data,function(event){
			});
			setChartData(data);
		 }
	);
}
/**
* 待收分布图
*/
function showWaitReceiveChart(){
	$.get("${basePath}/chart/finance/waitReceiveChart.html",
		 function(data) {
			myChart("waitReceiveChart",data,function(event){
			});
			setChartData(data);
		 }
	);
}

/**
 * 实时累计金额人数折线图
 */
 function showMoneyPersonChart(){
	$.get("${basePath}/chart/finance/moneyPersonChart.html",
		function(data) {
			var moneyPersonChart = new Highcharts.Chart({
		        chart: {
		            renderTo : "moneyPersonChart"
		        },
		        title: {
		            text: '待收金额、总成交金额和投资者人数变化曲线图'
		        },
		        subtitle: {
		            //text: 'Source: WorldClimate.com'
		        },
		        xAxis: [{
		            categories: data.chartDate,
		    		type:'datetime',
		    		labels:{
		    		 	format:'{value:%Y-%m-%d}',
		    			align:'left',
						rotation:45
		         	}
		        }],
		        yAxis: [{ // Primary yAxis
		            labels: {
		                format: '{value:,f}万元',
		            },
		            title: {
		                text: '金额（万元）',
		            },
		            min:0,
		            tickInterval:30000
		        }, { // Secondary yAxis
		            title: {
		                text: '投资者人数',
		                style: {
		                    color: '#058DC7'
		                }
		            },
		            labels: {
		                format: '{value} 位',
		                style: {
		                    color: '#058DC7'
		                }
		            },
		            min:0,
		            opposite: true
		        }],
		        tooltip: {
		        	xDateFormat: '%Y-%m-%d',
		        },
		        series: [{
		            name: '投资者人数',
		            color: '#50B432',
		            type: 'spline',
		            yAxis: 1,
		            data: data.investPersons,
		            tooltip: {
		                valueSuffix: ' 位'
		            }
	
		        }, {
		            name: '总成交金额',
		            color: '#058DC7',
		            type: 'spline',
		            data: data.borrowMoney,
		            tooltip: {
		                valueSuffix: '万元'
		            }
		        },{
		            name: '待收金额',
		            color: '#ED561B',
		            type: 'spline',
		            data:data.waitReceiveMoney,
		            tooltip: {
		                valueSuffix: '万元'
		            }
		        }]
		    });
		}
	);
}

 /**
  * 金额人数数据
  */
 function moneyPersonData(){
 	$.post("${basePath}/chart/finance/moneyPersonData.html", {}, 
     	function(data) {
 			$("#invest_cent").html(data); 
 	});
 }
 
 /**
  * 是否显示数据名称说明
  */
  function showDataNameDesc(){
 	 var dataNameDesc =$("#dataNameDesc");
 	 if(dataNameDesc[0].style.display=="none"){
 		 dataNameDesc.show();
 	 }else{
 		 dataNameDesc.hide();
 	 }
 }


function toggleChart(obj,type){
	 setlicss();
	 $("#"+type+"_li").addClass("men_li");
	 if(type=='week_info'){
		 weekInfo();
	 }else if(type=='overdue_info'){
		 overdueInfo();
	 }else if(type=='finish_info'){
		 finishInfo();
	 }
}

/**
 * 本周内详情
 */
function weekInfo(){
	$("#week_info_li").addClass("men_li");
	$.ajax({
		url : '${basePath}/chart/finance/weekInfo/10.html',
		data :{
		} ,
		type : 'post',
		dataType : 'text',
		success : function(data){
			$("#borrow_main_tab").html(data); 
			$("#pageNum_search").val("");
		},
		error : function(data) {
			layer.msg("网络连接异常，请刷新页面或稍后重试！", 2, 5);
	    }
	});
}

//按搜索条件搜索
function search(){
	var search_type = $("#search_type").val();
	$.ajax({
		url : '${basePath}/chart/finance/weekInfo/10.html',
		data :{search_type:search_type
		} ,
		type : 'post',
		dataType : 'text',
		success : function(data){
			$("#borrow_main_tab").html(data);
		},
		error : function(data) {
			layer.msg("网络连接异常，请刷新页面或稍后重试！", 2, 5);
	    }
	});
}

/**
 * ajax 翻页功能
 */
function findPage(pageNo){
	turnPage(pageNo);
}

function turnPage(pageNum){
	var totalPage = $("#totalPage").val();
	if(pageNum<=0){
		pageNum = 1;
	}
	if(parseInt(pageNum)>parseInt(totalPage)){
		pageNum = totalPage;
	}
	var type=$("#type").val();
	var search_type = $("#search_type").val();
	$.ajax({
		url : "${basePath}/chart/finance/"+type+"/10.html",
		data :{pageNum:pageNum,search_type:search_type
		} ,
		type : 'post',
		dataType : 'text',
		success : function(data){
			$("#borrow_main_tab").html(data); 
			$("#pageNum_search").val("");
		},
		error : function(data) {
			layer.msg("网络连接异常，请刷新页面或稍后重试！", 2, 5);
	    }
	});
}
function turnPageByPageNo(){
	var pageNum = $("#pageNo_search").val();
	var totalPage = $("#totalPage").val();
	if(pageNum<=0){
		pageNum = 1;
	}
	if(parseInt(pageNum) > parseInt(totalPage)){
		pageNum = totalPage;
	}
	$.ajax({
		url : "${basePath}/chart/finance/"+type+"/10.html",
		data :{pageNum:pageNum,search_type:search_type
		} ,
		type : 'post',
		dataType : 'text',
		success : function(data){
			$("#borrow_main_tab").html(data); 
		},
		error : function(data) {
			layer.msg("网络连接异常，请刷新页面或稍后重试！", 2, 5);
	    }
	});
}

/**
 * 逾期详情
 */
function overdueInfo(){
	$.ajax({
		url : "${basePath}/chart/finance/overdueInfo/10.html",
		data :{
		} ,
		type : 'post',
		dataType : 'text',
		success : function(data){
			$("#borrow_main_tab").html(data); 
			$("#pageNum_search").val("");
		},
		error : function(data) {
			layer.msg("网络连接异常，请刷新页面或稍后重试！", 2, 5);
	    }
	});
}
/**
 * 结清详情
 */
function finishInfo(){
	$.ajax({
		url : "${basePath}/chart/finance/finishInfo/10.html",
		data :{
		} ,
		type : 'post',
		dataType : 'text',
		success : function(data){
			$("#borrow_main_tab").html(data); 
			$("#pageNum_search").val("");
		},
		error : function(data) {
			layer.msg("网络连接异常，请刷新页面或稍后重试！", 2, 5);
	    }
	});
}
//将li中的current样式去掉
function setlicss(){
	$("#week_info_li").removeClass("men_li");
	$("#overdue_info_li").removeClass("men_li");
	$("#finish_info_li").removeClass("men_li");
}
</script>
</html>
