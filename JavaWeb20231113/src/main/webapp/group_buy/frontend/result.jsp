<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>團購網-新增至購物車</title>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css">
		<link rel="stylesheet" href="<%=getServletContext().getContextPath() %>/css/group_buy.css">
	</head>
	<body>
		<!-- menu -->
		<%@include file="./menu.jspf" %>
		
		<!-- 新增結果 -->
		<div style="padding: 15px">
			<form class="pure-form">
				<fieldset>
					<legend>新增至購物車</legend>
					商品: 🍖 雞腳凍(50元/包) <p />
					數量: 5 包<p />
				</fieldset>
			</form>
		</div>
	</body>
</html>