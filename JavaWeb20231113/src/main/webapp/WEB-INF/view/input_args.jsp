<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<%@ include file="./guestbook/header.jspf" %>
	<title>變數輸入</title>
	</head>
	<body style="padding: 15px">
		<form method="post" class="pure-form" action="./addArg">
			<fieldset>
				<legend>變數設定</legend>
				設定 request 變數: <input type="text" name="arg1" ><p />
				設定 session 變數: <input type="text" name="arg2" ><p />
				設定 application 變數: <input type="text" name="arg3" ><p />
				<button type="submit">設定</button>
			</fieldset>
		</form>
		
	</body>
</html>