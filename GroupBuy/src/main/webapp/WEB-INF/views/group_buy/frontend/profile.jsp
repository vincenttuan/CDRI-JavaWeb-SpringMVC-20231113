<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>團購網-User Profile</title>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css">
	</head>
	<body>
		<!-- menu -->
		<%@include file="../menu.jspf" %>
		
		<div style="padding: 15px">
			<form class="pure-form" method="post" action="./change_password">
				<fieldset>
					<legend>團購網-使用者密碼變更</legend>
					舊密碼: <input type="password" id="oldPassword" name="oldPassword" required><p />
					新密碼: <input type="password" id="newPassword" name="newPassword" required><p />
					新密碼: <input type="password" id="newPassword" name="newPassword" required><p />
					<button type="submit" class="pure-button pure-button-primary">密碼變更</button>
					<p />
					<div style="color: red">${ errorMessage }</div>
				</fieldset>
			</form>
		</div>
		
	</body>
</html>