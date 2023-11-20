<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>團購網後台首頁</title>
		<!-- header -->
		<%@include file="../include/header.jspf" %>
	</head>
	<body>
		<!-- menu -->
		<%@include file="../include/menu_backend.jspf" %>
		
		<!-- 商品訂購 -->
		<div style="padding: 15px">
			<form method="post" action="./result.jsp" class="pure-form">
				<fieldset>
					<legend>商品新增</legend>
					品名: <input type="text" id="productName" name="productName" value="水果茶" placeholder="請輸入品名" required><p />
					價格: <input type="number" id="price" name="price" value="40" placeholder="請輸入價格" required /><p />
					單位: <select id="unit" name="unit">
							<option value="1" selected>包</option>
							<option value="2">捆</option>
							<option value="3">打</option>
							<option value="4">箱</option>
							<option value="5">組</option>
						</select>
					&nbsp;&nbsp;&nbsp;&nbsp;	
					上架: <input type="checkbox" id="launch" name="launch" checked />	
					<p />
					<button type="submit" class="pure-button pure-button-primary">新增</button>	
				</fieldset>
			</form>
		</div>
		
	</body>
</html>