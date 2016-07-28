<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
 
 ${param.isHome!=null?'<h2><a href="#">如何申请</a></h2>':'<h1><a href="#">如何申请</a></h1>' }
         <div class="help-title" onclick="list(6)"><a href="javascript:void(0);" id="help_title">如何申请<span></span></a></div>
         <div class="help-content" id="list6">
             <p>不同产品的申请人需要符合不同的申请条件。</p> 
             <p>您可以通过点击“产品介绍”进行了解相关信息，在国诚金融网站申请借款需要抵押和担保。</p> 
             <p class="up" onclick="list(6)"><a href="javascript:void(0);" id="help_title">&nbsp;</a></p>
         </div>        
         <div class="help-title" onclick="list(7)"><a href="javascript:void(0);" id="help_title">申请步骤<span></span></a></div>
         <div class="help-content" id="list7">
             <p>1，填写借款申请</p> 
             <p>2，填写个人信息</p> 
             <p>3，上传认证资料</p> 
             <p>4，通过审核</p> 
             <p>5，筹集借款</p> 
             <p>6，获得借款</p> 
             <p class="up" onclick="list(7)"><a href="javascript:void(0);" id="help_title">&nbsp;</a></p>
         </div>        
         <div class="help-title" onclick="list(8)"><a href="javascript:void(0);" id="help_title">申请金额<span></span></a></div>
         <div class="help-content" id="list8">
             <p>您可申请的借款额度范围是大于3000元（请您按照实际需求填写）。</p> 
             <p>您可获得的借款额度将在信审部门审核完成后，由您的整体资质决定（可能与您申请的借款金额不一致）。</p> 
             <p class="up" onclick="list(8)"><a href="javascript:void(0);" id="help_title">&nbsp;</a></p>
         </div>        
         <div class="help-title" onclick="list(9)"><a href="javascript:void(0);" id="help_title">借款期限与还款方式<span></span></a></div>
         <div class="help-content" id="list9">
             <p>目前您可选择的借款期限为1-12个月。</p> 
             <p>国诚金融网站的还款方式为等额本息， 到期还本付息，按月付息到期还本，按天计息到期还本付息（点击借款<a  href="${path}/borrow/calculator/toCounter.html"  class="blue"  >计算器</a>设置并计算，查看还款明细）。</p> 
             <p class="up" onclick="list(9)"><a href="javascript:void(0);" id="help_title">&nbsp;</a></p>
         </div>        
