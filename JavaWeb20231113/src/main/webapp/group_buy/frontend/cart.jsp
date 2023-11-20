<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>團購網-購物車</title>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css">
	</head>
	<body>
		<!-- menu -->
		<%@include file="./menu.jspf" %>
		
		<!-- 購物車列表 -->
		<div style="padding: 15px">
			<form class="pure-form">
				<fieldset>
					<legend>John 的購物車</legend>
					<table class="pure-table pure-table-bordered">
						<thead>
							<tr>
								<th>序號</th><th>品名</th><th>價格</th><th>單位</th><th>數量</th><th>修改數量</th><th>刪除</th>
							</tr>
						</thead>
						<tbody>
							
						</tbody>
					</table>
				</fieldset>
			</form>
		</div>
	</body>
</html>







