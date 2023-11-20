<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>團購網-新增至購物車</title>
		<!-- header -->
		<%@include file="../include/header.jspf" %>
	</head>
	<body>
		<!-- menu -->
		<%@include file="../include/menu.jspf" %>
		
		<!-- 新增結果 -->
		<div style="padding: 15px">
			<form class="pure-form">
				<fieldset>
					<legend>新增至購物車</legend>
					商品: 🍖 雞腳凍(50元/包) <p />
					數量: 5 包<p />
				</fieldset>
				<button type="button" onclick="window.location.href='./main.jsp';" class="pure-button pure-button-primary">回上一頁</button>
			</form>
		</div>
	</body>
</html>