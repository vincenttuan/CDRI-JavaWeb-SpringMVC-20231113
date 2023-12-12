<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sp" uri="http://www.springframework.org/tags/form" %> 
<!DOCTYPE html>
<!-- 
設計一個表單
id: 序號(input hidden)
姓名: name (input text)
年齡: age (input number)
生日: birth (input date)
學歷: education [小學, 國中, 高中, 大學, 研究所] 下拉選單(單選)
性別: sex [男, 女] radio button (單選)
興趣: interests [看書, 跑步, 飛控, 看電影] checkboxs (多選)  
履歷: resume (text area)
 -->
<html>
	<head>
		<meta charset="UTF-8">
		<title>User Spring From</title>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css" />
		<style type="text/css">
			.element-margin {
				margin-right:2px;
				margin-left:5px;
			}
		</style>
	</head>
	<body style="padding: 15px">
		<sp:form modelAttribute="user" method="post" action="" class="pure-form">
			<fieldset>
				<legend>User Spring Form</legend>
				<!-- 表單元素 -->
				姓名: <sp:input path="name" type="text" /><p />
				年齡: <sp:input path="age" type="number" /><p />
				生日: <sp:input path="birth" type="date" /><p />
				學歷: <sp:select path="educationId" 
								items="${ educations }"
								itemLabel="name"
								itemValue="id" /><p />
				性別: <sp:radiobuttons path="sexId" 
								items="${ sexs }"
								itemLabel="name"
								itemValue="id" 
								cssClass="element-margin" /><p />	
				興趣: <sp:checkboxes path="interestIds" 
									items="${ interests }"
									itemLabel="name"
									itemValue="id" 
									cssClass="element-margin" /><p />	
				履歷: <sp:textarea path="resume" /><p />									
			</fieldset>
		</sp:form>
	</body>
</html>