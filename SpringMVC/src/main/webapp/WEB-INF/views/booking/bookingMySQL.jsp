<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>     
<!DOCTYPE html>
<!--
	+-------+-------------+
	| 預約表單 |
	| xxxxx |
	+-------+    回應結果
	| 取消預約 |
	| xxxxx |
	+-------+
	| 查詢預約 |
	+-------+
	|新增會議室|
	+-------+-------------+
 -->
<html>
	<head>
		<meta charset="UTF-8">
		<title>Booking MySQL</title>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css" />
	</head>
	<body style="padding: 15px;">
		${ rooms }<p />
		<table>
			<tr>
				<!-- 預約表單 -->
				<td valign="top">
					<form class="pure-form" method="post" action="/SpringMVC/mvc/bookingMySQL/bookRoom" target="resultFrame">
						<fieldset>
							<legend>預約表單</legend>
							會議室: <select name="roomId">
									<c:forEach items="${ rooms }" var="room">
										<option value="${ room.roomId }">${ room.roomName } (${ room.roomSize }人)</option>
									</c:forEach>
								  </select><p />
							預約人: <input type="text" name="name" placeholder="請輸入訂閱人姓名" required /><p />
							預約日: <input type="date" name="date" required /><p />
							<button type="submit" class="pure-button pure-button-primary">訂閱</button>
						</fieldset>	  
					</form>
				</td>
				<!-- 回應 -->
				<td rowspan="4" valign="top" style="padding-left: 15px;">
					<iframe name="resultFrame" style="border: 0px solid #cccccc" width="1200px" height="500px"></iframe>
				</td>
			</tr>
			<tr>
				<!-- 取消預約 -->
				<!-- http://localhost:8080/SpringMVC/mvc/bookingMySQL/cancelBooking/1 -->
				<!-- http://localhost:8080/SpringMVC/mvc/bookingMySQL/cancelBooking/2 -->
				<td valign="top">
					<form class="pure-form">
						<fieldset>
							<legend>取消預約</legend>
							<input type="number" name="bookingId" id="bookingId" value="1" required />
							<a href="javascript:void(0);" 
							   onClick="window.top.resultFrame.location.href='/SpringMVC/mvc/bookingMySQL/cancelBooking/' + document.getElementById('bookingId').value"	
							   class="pure-button pure-button-primary">
								取消預約
							</a>
						</fieldset>
					</form>
				</td>
			</tr>
			<tr>
				<!-- 查詢預約 -->
				<td valign="top">
					<a href="/SpringMVC/mvc/bookingMySQL/viewBookings" 
					   class="pure-button pure-button-primary" 
					   target="resultFrame">查詢預約</a>
				</td>
			</tr>
			<tr>
				<!-- 新增會議室 -->
				<td valign="top">
					<form method="POST" action="/SpringMVC/mvc/bookingMySQL/room" class="pure-form" target="resultFrame">
						<fieldset>
							<legend>新增會議室</legend>
							會議室Id: <input type="number" id="roomId" name="roomId"><p />
							會議室名稱: <input type="text" id="roomName" name="roomName"><p />
							會議室人數: <input type="number" id="roomSize" name="roomSize"><p />
							<button type="submit" class="pure-button pure-button-primary">新增</button>
						</fieldset>
					</form>
				</td>
			</tr>
		</table>
		
	</body>
</html>