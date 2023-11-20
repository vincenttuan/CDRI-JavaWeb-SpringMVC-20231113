<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>團購網-新增至商品資料庫</title>
		<!-- header -->
		<%@include file="../include/header.jspf" %>
	</head>
	<body>
		<!-- menu -->
		<%@include file="../include/menu_backend.jspf" %>
		
		<!-- 新增結果 -->
		<div style="padding: 15px">
			<form class="pure-form">
				<fieldset>
					<legend>新增至商品資料庫</legend>
					商品: 🍺 啤酒 <p />
					價格: 400<p />
					單位: 箱<p />
					上架: V<p />
				</fieldset>
				<button type="button" onclick="window.location.href='./main.jsp';" class="pure-button pure-button-primary">回上一頁</button>
			</form>
		</div>
	</body>
</html>