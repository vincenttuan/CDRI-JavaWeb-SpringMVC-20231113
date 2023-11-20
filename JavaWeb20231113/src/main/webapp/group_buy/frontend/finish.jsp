<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>團購網-結帳</title>
		<!-- header -->
		<%@include file="../include/header.jspf" %>
	</head>
	<body>
		<!-- menu -->
		<%@include file="../include/menu.jspf" %>
		
		<!-- 結帳列表 -->
		<div style="padding: 15px">
			<form class="pure-form">
				<fieldset>
					<legend>John 的結帳資料 (結帳時間: 2023-11-20 12:20:50)</legend>
					<table class="pure-table pure-table-bordered">
						<thead>
							<tr>
								<th>序號</th><th>品名</th><th>價格</th><th>單位</th><th>數量</th><th>小計</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>1</td><td>可樂</td><td>100</td><td>打</td><td>3</td><td>300</td>
							</tr>
							<tr>
								<td>2</td><td>漢堡</td><td>250</td><td>箱</td><td>2</td><td>500</td>
							</tr>
							<tr>
								<td>3</td><td>雞腳凍</td><td>50</td><td>包</td><td>5</td><td>250</td>
							</tr>
							<tr>
								<td colspan="5" align="right">總計</td><td>1,050</td>
							</tr>
						</tbody>
					</table>
				</fieldset>
			</form>
		</div>
	</body>
</html>







