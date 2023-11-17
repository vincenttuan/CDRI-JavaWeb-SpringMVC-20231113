<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<%@ include file="./WEB-INF/view/guestbook/header.jspf" %>
	<title>變數讀取</title>
	</head>
	<body style="padding: 15px">
		<form class="pure-form">
			<fieldset>
				<legend>變數讀取</legend>
				讀取 request 變數: <%=request.getAttribute("arg1") %><p />
				讀取 session 變數: <%=session.getAttribute("arg2") %><p />
				讀取 application 變數: <%=application.getAttribute("arg3") %><p />
			</fieldset>
		</form>
		
	</body>
</html>