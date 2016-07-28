<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%> 
<%@ include file="/WEB-INF/page/common/taglib.jsp"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<script type="text/javascript" src="${basePath}/js/formValid.js"></script>
<title>修改登录密码_国诚金融</title>
</head>

<body>
<%@ include file="/WEB-INF/page/common/header.jsp"%>
<form id="loginPwdForm" name="loginPwdForm" method="post">
<div id="Container">
	<div id="Bmain">
		<div class="title">
			<span class="home"><a href="${path}/">国诚金融</a></span>
			<span><a href="${path}/myaccount/toIndex.html">我的账户</a></span>
			<span>账户管理</span>
			<span><a href="${path }/AccountSafetyCentre/safetyIndex.html">安全中心</a></span>
			<span>修改登录密码</span>
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
									<p>1. 定期更换密码可以让你的账户更加安全</p>
									<p>2. 请确保登录密码与交易密码不同！</p>
									<p>3. 建议密码采用字母和数字混合</p>
									<p>4. 密码<span style="color:red;">不短于6位，不大于20位</span></p>
								</div>
							</dd>
							<dd class="currentdd">
								<span> 当前登录密码：</span>
								<input id="oldPwd" name="oldPwd" type=password class="inputtext" dataType="Limit" min="1"  msg="当前密码不能为空" >
							</dd>
							<dd class="currentdd">
								<span> 新登录密码：</span>
								<input id="newPwd" name="newPwd" type=password class="inputtext" dataType="Limit" min="6" max="20" msg="新密码不能为空，且长度在6~20之间" maxlength="20" >
							</dd>
							<dd class="currentdd">
								<span> 确认新登录密码：</span>
								<input id="reNewPwd" name="reNewPwd" type=password class="inputtext" dataType="Repeat" to="newPwd" msg="两次密码输入不一致" maxlength="20">
							</dd>
						</dl>

						<div class="gg_btn">
							<input type="button" value="确定" id="loginPwdFormSubmitbtn" onclick="loginPwdFormSubmit()" style="cursor: pointer;"/>
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
function loginPwdFormSubmit(){
	//验证
	
	$('#loginPwdFormSubmitbtn').attr("disabled","disabled");
	if (Validator.Validate('loginPwdForm',1)==false) {
		$('#loginPwdFormSubmitbtn').removeAttr("disabled");
		return;
	}
	
	if (!checkPassWord()) {
		$('#loginPwdFormSubmitbtn').removeAttr("disabled");
		return;
	}
	
	//提交
	if(layer.confirm("确定提交吗？",function(){
		var _load = layer.load('处理中..');
		$("#loginPwdForm").ajaxSubmit({
			url : '${basePath}/account/safe/modifyLoginPwd.html',
			type : 'post',
			dataType :"json",
			success : function(result) {
				layer.close(_load);
				if (result.code == '0') {
					parent.layer.msg(result.message, 1, 5);
					$('#loginPwdFormSubmitbtn').removeAttr("disabled");
				}else{
					window.location='${path}/account/safe/modifyPwdResult.html?type=1';
				}
			},
			error : function(result) {
				layer.close(_load);
				layer.msg('网络连接超时,请您稍后重试.', 1, 5);
		    }
		});
	},'',function(){$('#loginPwdFormSubmitbtn').removeAttr("disabled");}));
	
	
}


 
/**
 * 校验密码
 */
function checkPassWord(){
	var password1 = $('#newPwd').val();
	if(password1==null || password1==""){
		layer.msg('登录密码不能为空',1, 5);
		return false;
	}else if(password1.length<6){
		layer.msg('登录密码必须大于或等于6位', 1, 5);
		return false;
	}else if(password1.length>20){
		layer.msg('登录密码不能大于20位', 1, 5 );
		return false;
	}else{
		var regx = new RegExp("^([0-9]+[A-Za-z]+|[A-Za-z]+[0-9]+)[A-Za-z0-9]*$");
		if(!regx.test(password1)){
			layer.msg('登录密码必须为数字加字母', 1, 5 );
			return false;
	    }
	}
	
	return true;
	
}






/**
 * 修改密码键盘输入事件
 */
function editNewPwdChange(){
	var newPwd = $("#newPwd").val();
	if(newPwd.trim().length == 20){
		layer.msg('密码最大长度为20位，不能再输入！', 1, 5);
	}
}
</script>
</body>
</html>