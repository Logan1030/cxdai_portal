<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
 
${param.isHome!=null?'<h2><a href="#">如何还款</a></h2>':'<h1><a href="#">如何还款</a></h1>' }
<div class="help-title" onclick="list(50)"><a href="javascript:void(0);" id="help_title">如何还款<span></span></a></div>
         <div class="help-content" id="list50">
             <p>1. 当账户余额不足支付当期还款时，您可先通过网银为账户充值；</p>
             <p>2. 充值完成后，您可以点击“立即还款”。</p> 
            <p class="up" onclick="list(50)"><a href="javascript:void(0);" id="help_title">&nbsp;</a></p>
         </div>        
         <div class="help-title" onclick="list(51)"><a href="javascript:void(0);" id="help_title">还款提醒<span></span></a></div>
         <div class="help-content" id="list51">
             <p>您会在还款日的前一天以电子邮件的方式收到还款提醒。</p> 
             <p class="up" onclick="list(51)"><a href="javascript:void(0);" id="help_title">&nbsp;</a></p>
         </div>        
         <div class="help-title" onclick="list(52)"><a href="javascript:void(0);" id="help_title">提前还款<span></span></a></div>
         <div class="help-content" id="list52">
             <p>官方标提前还款规则：</p> 
             <p>登录国诚金融网站，打开“我的账户”>>“融资管理”>>“待还列表”页面，点击“立即还款”进行还款操作。</p> 
             <p>提前还款的用户需要支付给出借人应得的本金和利息，另外会额外支付给出借人2天的利息为补偿。</p> 
             <p>提前还款金额公式如下：</p> 
             <p>提前还款应还金额= 剩余本金+资金实际占用利息+2天利息。</p> 
             <%-- <p>净值标提前还款规则：</p> 
             <p>提前还款的用户需要支付给出借人全额本金和利息。</p> --%> 
             <p class="up" onclick="list(52)"><a href="javascript:void(0);" id="help_title">&nbsp;</a></p>
         </div>        
         <div class="help-title" onclick="list(54)"><a href="javascript:void(0);" id="help_title">逾期还款<span></span></a></div>
         <div class="help-content" id="list54">
              <p>如果逾期还款，您要承担罚息。罚息按天收取，每天的罚息为剩余本息的0.1%。国诚金融强烈建议您避免逾期还款，一旦发生逾期请尽快还清借款。</p> 
             </p>
             <p class="up" onclick="list(54)"><a href="javascript:void(0);" id="help_title">&nbsp;</a></p>

</div>
