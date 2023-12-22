<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Login</title>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css">
		<script type="text/javascript">
			function loginBackend() {
				var loginForm = document.getElementById('loginForm');
				loginForm.action = './login_backend'; // 後台登入路徑
				loginForm.submit();
			}
		</script>
	</head>
	<body style="padding: 15px">
		<div style="display:flex; justify-content:center; align-items: center; height:100vh;">
			<form id="loginForm" method="post" action="./login" class="pure-form">
				<fieldset>
					<legend>團購登入</legend>
					😀 帳號: <input type="text" id="username" name="username" placeholder="請輸入帳號" value="user123" required /><p />
					🔑 密碼: <input type="password" id="password" name="password" placeholder="請輸入密碼" value="pass123" required /><p />
					🔐 驗證: <input type="text" id="code" name="code"> 
					<img src="./getcode" alt="驗證碼" valign="middle">
					<p />
					<button type="submit" class="pure-button pure-button-primary">前台登入</button>
					<button type="button" class="pure-button pure-button-primary" onclick="loginBackend()" >後台登入</button>
				</fieldset>
				<div style="color: red">${ loginMessage }</div>
			</form>
		</div>
	</body>
</html>