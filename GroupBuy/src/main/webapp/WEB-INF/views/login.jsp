<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <h2>Login</h2>
    <form action="/GroupBuy/mvc/login" method="post">
        <div>
            <label for="username">Username:</label>
            <input type="text" id="username" name="username">
        </div>
        <div>
            <label for="password">Password:</label>
            <input type="password" id="password" name="password">
        </div>
        <div>
            <button type="submit">Login</button>
        </div>
        <%-- 显示登录错误信息 --%>
        <% if (request.getParameter("error") != null) { %>
            <div style="color: red;">Invalid username or password.</div>
        <% } %>
    </form>
</body>
</html>
