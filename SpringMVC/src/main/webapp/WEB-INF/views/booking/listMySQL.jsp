<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Booking MySQL List</title>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css" />
	</head>
	<body>
		Booking MySQL List
		<table class="pure-table pure-table-bordered">
			<thead>
				<tr><th>cancel</th><th>bookingId</th><th>roomId</th><th>username</th><th>booking date</th><th>create date</th></tr>
			</thead>
			<tbody>
				<c:forEach var="booking" items="${ bookings }">
					<tr>
						<form method="post" action="/SpringMVC/mvc/bookingMySQL/${ booking.bookingId }/updateName">
							<td>
								<a href="javascript:void(0)" 
								   onClick="location.href='/SpringMVC/mvc/bookingMySQL/cancelBooking/${ booking.bookingId }'"	
								   title="按我一下可以取消">
									Cancel
								</a>
							</td>
							<td>${ booking.bookingId }</td>
							<td>${ booking.roomId }</td>
							<td>
								<input type="text" id="name${ booking.bookingId }" name="name" value="${ booking.username }" size="10">
								<button type="button"
										onClick="location.href='/SpringMVC/mvc/bookingMySQL/${ booking.bookingId }/updateName?name=' + document.getElementById('name${ booking.bookingId }').value">
									更名 1
								</button>
								<button type="submit">更名 2</button>
							</td>
							<td>${ booking.bookingDate }</td>
							<td>${ booking.createDate }</td>
						</form>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</body>
</html>