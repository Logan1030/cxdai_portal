<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%> 
<%@ include file="/WEB-INF/page/common/taglib.jsp"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<script type="text/javascript" src="${basePath}/js/formValid.js"></script>
<title>设置交易密码_国诚金融</title>
</head>

<body>
<%@ include file="/WEB-INF/page/common/header.jsp"%>
<form id="payPwdForm" name="payPwdForm" method="post">
<div id="Container">
	<div id="Bmain">
		<div class="title">
			<span class="home"><a href="${path}/">国诚金融</a></span>
			<span><a href="${path}/myaccount/toIndex.html">我的账户</a></span>
			<span>账户管理</span>
			<span><a href="${path }/AccountSafetyCentre/safetyIndex.html">安全中心</a></span>
			<span>设置交易密码</span>
		</div>
		<div id="menu_centert">
			<%@ include file="/WEB-INF/page/account/myAccount_leftMenu.jsp"%>
			<div class="lb_waikuang border whitebg">
				<div class="safetoptit">安全中心</div>

				<div class="safe">
					<div class="safebox450">
						<dl>
							<dd>
								<div class="tip clear">
									<h3>温馨提示：</h3>
									<p>1. 请确保交易密码与登录密码不同！</p>
									<p>2. 建议密码采用字母和数字混合</p>
									<p>3. 密码必须<span style="color:red;">不短于6位，不大于12位</span></p>
								</div>
							</dd>
							<dd class="currentdd">
								<span> 交易密码：</span>
								<input id="newPwd" name="newPwd" type=password class="inputtext" dataType="Limit" min="6" max="12" msg="密码不能为空，且长度在6~12之间" maxlength="12">
							</dd>
							<dd class="currentdd">
								<span> 确认交易密码：</span>
								<input id="reNewPwd" name="reNewPwd" type=password class="inputtext" dataType="Repeat" to="newPwd" msg="两次密码输入不一致" maxlength="12">
							</dd>
						</dl>

						<div class="gg_btn">
							<input type="button" id="payPwdFormSubmitBtn" value="确定" onclick="payPwdFormSubmit()" style="cursor: pointer;"/>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="clearfix"></div>
</form>
<%@ include file="/WEB-INF/page/common/footer.jsp"%>
<script type="text/javascript">
/**
 * 表单提交
 */
function payPwdFormSubmit(){
	$('#payPwdFormSubmitBtn').attr("disabled","disabled");
	//验证
	if (Validator.Validate('payPwdForm',1)==false) {
		$('#payPwdFormSubmitBtn').removeAttr("disabled"); 
		return;
	}
	
	//提交
	
	if(layer.confirm("确定提交吗？",function(){
		var _load = layer.load('处理中..');
		$("#payPwdForm").ajaxSubmit({
			url : '${basePath}/account/safe/setPayPwd.html',
			type : 'post',
			dataType :"json",
			success : function(result) {
				$('#payPwdFormSubmitBtn').removeAttr("disabled"); 
				layer.close(_load);
				if (result.code == '0') {
					parent.layer.alert(result.message);
				}else{
					parent.layer.msg(result.message, 1, 1);
					window.location='${path}/AccountSafetyCentre/safetyIndex.html';
				}
			},
			error : function(result) {
				$('#payPwdFormSubmitBtn').removeAttr("disabled"); 
				layer.close(_load);
				layer.msg('网络连接超时,请您稍后重试.', 1, 5);
		    }
		});
	},'',function(){$('#payPwdFormSubmitBtn').removeAttr("disabled"); }));
	
	
}
</script>
</body>
</html>