<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>團購網-後台報表</title>
		<!-- header -->
		<%@include file="../include/header.jspf" %>
	</head>
	<body>
		<!-- menu -->
		<%@include file="../include/menu_backend.jspf" %>
		
		<div style="padding: 15px">
			<table>
				<tr>
					<td valign="top">
						<!-- report list -->
						<%@include file="./report_list.jspf" %>
					</td>
					<td valign="top" style="padding-left: 15px">
						<!-- report detial -->
						<%@include file="./report_detial.jspf" %>
					</td>
				</tr>
			</table>
		</div>
	</body>
</html>