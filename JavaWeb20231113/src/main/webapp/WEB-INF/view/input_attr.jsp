<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css">
	<title>變數輸入</title>
	</head>
	<body style="padding: 15px">
		<form method="post" class="pure-form" action="./servlet/addArg">
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