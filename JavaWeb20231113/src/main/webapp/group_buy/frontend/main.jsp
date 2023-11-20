<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>團購網前台首頁</title>
		<!-- header -->
		<%@include file="../include/header.jspf" %>
	</head>
	<body>
		<!-- menu -->
		<%@include file="../include/menu.jspf" %>
		
		<!-- 商品訂購 -->
		<div style="padding: 15px">
			<form method="post" action="./result.jsp" class="pure-form">
				<fieldset>
					<legend>商品訂購</legend>
					商品: <select id="productId" name="productId">
							<option value="1" selected>🍖 雞腳凍(50元/包)</option>
							<option value="2">🥤 可樂(100元/打)</option>
							<option value="3">🍔 漢堡(250元/箱)</option>
						</select><p />
					數量: <input type="number" id="amount" name="amount" value="5" /><p />
					<button type="submit" class="pure-button pure-button-primary">加入購物車</button>	
				</fieldset>
			</form>
		</div>
		
	</body>
</html>