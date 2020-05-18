<%--
  Created by IntelliJ IDEA.
  User: Luke
  Date: 2020/3/5
  Time: 18:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Login</title>
    </head>
    <body style="text-align: center">
        <h3>用户登录</h3>
        <form action="${pageContext.request.contextPath}/LoginServlet?select=1" method="post">
            用户名：<input type="text" name="unames" value=""/><br/>
            密&nbsp;&nbsp;&nbsp;码：<input type="password" name="pwd"/><br/>
            <input type="submit" value="提交"/><p>${err}</p>
        </form>

    </body>
</html>
