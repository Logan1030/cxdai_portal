<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%> 
<%@ include file="/WEB-INF/page/common/taglib.jsp"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<script type="text/javascript" src="${basePath}/js/formValid.js"></script>
<link href="${basePath}/css/login.css" rel="stylesheet" type="text/css" />
<title>找回登录密码_国诚金融</title>
</head>

<body>
<%@ include file="/WEB-INF/page/common/header.jsp"%>

<form id="findPwdForm" name="findPwdForm" action="findPwdForm">
<div id="Bmain">
	<div class="login_tit loginbg">找回登录密码</div>
	<div class="login_main loginbg">
		<div class="login_mainbox ">
			<dl>
				<dd>
					<span><font color="#22adce">*</font> 用户名：</span> 
					<input name="username" type=text dataType="Limit" min="1" max="20" msg="请输入用户名" maxlength="20">
				</dd>
				<dd>
					<span><font color="#22adce">* </font>真实姓名：</span> 
					<input name="realname" type=text dataType="Limit" min="1" max="20" msg="请输入真实姓名" maxlength="20">
				</dd>
				<dd>
					<span><font color="#22adce">*</font> 身份证：</span> 
					<input name="idCard" type=text dataType="Limit" min="1" max="20" msg="请输入身份证" maxlength="20">
				</dd>
				<dd style="width: 500px;">
					<span><font color="#22adce">*</font> 验证码：</span> 
					<input id="checkcode" name="checkcode" type="text" dataType="Require" msg="请输入验证码" maxlength="4" style="width:100px;"/>
					<img onclick="loadimage()" name="randImage" id="randImage" src="${basePath}/random.jsp" style="cursor: pointer;" border="0" align="middle" />
				</dd>
				<dd>
					<span>&nbsp;</span>
					<input name="findType" value="email" type="radio" checked="checked"/>使用绑定邮箱找回密码&nbsp;&nbsp;&nbsp;
					<input name="findType" value="mobile" type="radio" />使用绑定手机找回密码
				</dd>
				<dd></dd>
				<dd>
					<input type="button" id="formSubmitbtn" class="login_btn" value="提交" onclick="formSubmit()" style="cursor: pointer;"/>
				</dd>
				<dd></dd>
				<dd></dd>
				<dd>
					<img src="${basePath }/images/tip.png" width="16" height="16" />&nbsp;若您无法使用上述方法找回密码，请联系客服400-015-6676
				</dd>
			</dl>
		</div>
	</div>

</div>
</form>

<%@ include file="/WEB-INF/page/common/footer.jsp"%>

<script type="text/javascript">
/**
 * 表单提交
 */
function formSubmit(){
	//验证
	
	$('#formSubmitbtn').attr("disabled","disabled");
	if (Validator.Validate('findPwdForm',1)==false) {
		$('#formSubmitbtn').removeAttr("disabled");
		return;
	}
	//提交
	if(layer.confirm("确定提交吗？",function(){
		var _load = layer.load('处理中..');
		$("#findPwdForm").ajaxSubmit({
			url : '${basePath}/account/safe/findLoginPwd.html',
			type : 'post',
			dataType :"json",
			success : function(result) {
				$('#formSubmitbtn').removeAttr("disabled");
				layer.close(_load);
				if (result.code == '0') {
					parent.layer.msg(result.message, 1, 5);
					loadimage();
				}else{
					window.location='${path}/account/safe/findPwdResult.html?pwdType=login&findType='+result.message;
				}
			},
			error : function(result) {
				$('#formSubmitbtn').removeAttr("disabled");
				layer.close(_load);
				layer.msg('网络连接超时,请您稍后重试.', 1, 5);
		    }
		});
	},'',function(){$('#formSubmitbtn').removeAttr("disabled");}));
	
}

/**
 * 刷新验证码 
 */
function loadimage() {
	document.getElementById("randImage").src = "${basePath}/random.jsp?" + Math.random();
}
</script>
</body>
</html>