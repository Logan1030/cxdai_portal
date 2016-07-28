<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

${param.isHome!=null?'<h2><a href="#">筹款与提现</a></h2>':'<h1><a href="#">筹款与提现</a></h1>' }
<div class="help-title" onclick="list(48)">
	<a href="javascript:void(0);" id="help_title">发标筹款<span></span></a>
</div>
<div class="help-content" id="list48">
	<p>审核成功后，您将得到相应的信用等级和信用额度同时您可以在国诚官网进行发标筹款。</p>
	<p>您的借款金额将于1-3天内为您筹集完成。</p>
	<p class="up" onclick="list(48">
		<a href="javascript:void(0);" id="help_title">&nbsp;</a>
	</p>
</div>
<div class="help-title" onclick="list(49)">
	<a href="javascript:void(0);" id="help_title">满标提现<span></span></a>
</div>
<div class="help-content" id="list49">
	<p>满标后您可以申请提现，资金将在1-2个工作日内到达您指定的银行账户。</p>
	<p class="up" onclick="list(49)">
		<a href="javascript:void(0);" id="help_title">&nbsp;</a>
	</p>
</div> 