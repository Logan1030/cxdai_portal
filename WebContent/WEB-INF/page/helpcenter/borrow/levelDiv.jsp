<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
 
${param.isHome!=null?'<h2><a href="#">信用等级与额度</a></h2>':'<h1><a href="#">信用等级与额度</a></h1>' }

	<div class="help-title" onclick="list(42)">
		<a href="javascript:void(0);" id="help_title">信用分数<span></span></a>
	</div>
	<div class="help-content" id="list42">
		<p> 信用分数是国诚金融风控部门根据您上传的资料进行评估的分数。</p>
		<p class="up" onclick="list(42)">
			<a href="javascript:void(0);" id="help_title">&nbsp;</a>
		</p>
	</div>

<div class="help-title" onclick="list(43)">
	<a href="javascript:void(0);" id="help_title">信用等级<span></span></a>
</div>
<div class="help-content" id="list43">
	<p>信用等级由信用分数转化而来，每个信用等级都对应的信用分数范围，信用分数和信用等级是借款人的信用属性，也是出借人判断借款人违约风险的重要依据之一。通常来讲借款人信用等级越高，其违约率越低，相应的借款成功率越高。</p>
	<p>目前信用等级由低到高分为HR、E、D、C、B、A，具体请参考信用等级的分数区间：</p>
	<p class="help-content">
	<table>
		<tr class="help-border">
			<td>信用等级</td>
			<td><img src="${basePath }/images/HR.gif" width="34" height="34" /></td>
			<td><img src="${basePath }/images/E.gif" width="34" height="34" /></td>
			<td><img src="${basePath }/images/dd.gif" width="34" height="34" /></td>
			<td><img src="${basePath }/images/c.gif" width="34" height="34" /></td>
			<td><img src="${basePath }/images/B.gif" width="34" height="34" alt="理财产品"/></td>
			<td><img src="${basePath }/images/A.gif" width="34" height="34" /></td>
		</tr>
		<tr>
			<td>分数区间</td>
			<td>0-60</td>
			<td>60-69</td>
			<td>70-79</td>
			<td>80-89</td>
			<td>90-95</td>
			<td>95+</td>
		</tr>
	</table>
	</p>
	<p class="up" onclick="list(43)">
		<a href="javascript:void(0);" id="help_title">&nbsp;</a>
	</p>
</div>
<div class="help-title" onclick="list(44)">
	<a href="javascript:void(0);" id="help_title">信用额度<span></span></a>
</div>
<div class="help-content" id="list44">
	<p>用户的信用额度是在通过国诚金融审核员对所提供材料的审核后获得的，既是借款人单笔借款的上限也是借款者累积尚未还清借款的上限。</p>
	<p>提额申请资格说明</p>
	<p>您之前已获得信用额度，且至少已成功借款一笔，目前您名下的借款已全部结清，无在还借款。</p>
	<p>提额申请办法</p>
	<p>如果您目前的状况满足上述情况，请您重新发布借款申请，申请金额请按照实际需求填写，不限定在已获得的信用额度范围之内。</p>
	<p>例如，如果一个借款人信用额度为5万元，则在没有其他借款的情况下，用户可以发布总额最高为5万元的借款请求。也可以分多次发布借款请求，但尚未还清借款（以整笔借款金额计算）的总额不得超过5万元。</p>
	<p class="up" onclick="list(44)">
		<a href="javascript:void(0);" id="help_title">&nbsp;</a>
	</p>
</div>
 
