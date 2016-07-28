<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

 
		 ${param.isHome!=null?'<h2><a href="#">收益与费用</a></h2>':'<h1><a href="#">收益与费用</a></h1>' }
		 
         <div class="help-title" onclick="list(1)"><a href="javascript:void(0);" id="help_title">投标直通车收益<span></span></a></div>
         <div class="help-content" id="interestMoneyExpand1">
             <p> 预期年化收益率14%-16%。 </p> 
             <p class="up" onclick="list(1)"><a href="javascript:void(0);" id="help_title">&nbsp;</a></p>
         </div>        
         <div class="help-title" onclick="list(2)"><a href="javascript:void(0);" id="help_title">抵押标收益<span></span></a></div>
         <div class="help-content" id="interestMoneyExpand2">
             <p>目前平台的年化率大于12%。</p> 
             <p class="up" onclick="list(2)"><a href="javascript:void(0);" id="help_title">&nbsp;</a></p>
         </div>        
         <%-- <div class="help-title" onclick="list(3)"><a href="javascript:void(0);" id="help_title">净值标收益<span></span></a></div>
         <div class="help-content" id="interestMoneyExpand3">
             <p>年利率区间6%-24%，具体收益由您所投资的借款标利率确定。</p> 
             <p class="up" onclick="list(3)"><a href="javascript:void(0);" id="help_title">&nbsp;</a></p>
         </div> --%>        
         <div class="help-title" onclick="list(4)"><a href="javascript:void(0);" id="help_title">充值提现费用<span></span></a></div>
         <div class="help-content" id="interestMoneyExpand4">
                
                <p>充值费用：通过“网银在线、连连支付、富友支付”充值免手续费。通过新浪支付充值，第三方支付平台收取千分之二的手续费（最低1分）。</p>
                
                <p>提现费用：充值投标回款金额提现为每笔（最低100元最高5万元，申请时可提交最高50万，但按照10笔打款并收取相应手续费 ）代银行收取2元提现手续费，<label style="color:red;">每月前三笔提现免提现费</label>；充值未投标金额申请提现需将受限金额转化为可提现金额，转化费用为总额的0.5%。但新注册用户首次充值未经投标将无法转可提。</p>
               
             <p class="up" onclick="list(4)"><a href="javascript:void(0);" id="help_title">&nbsp;</a></p>
         </div>
         
<script type="text/javascript">
	function list(i) {
		$("#interestMoneyExpand" + i).animate({
			height : 'toggle'
		});

	}
</script>     