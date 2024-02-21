<%--
  Created by IntelliJ IDEA.
  User: Celia
  Date: 2/6/2024
  Time: 11:07 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Form</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/CSS/login.css" />
</head>
<body>
<div><h2 style="color: darkblue">Login Form</h2></div>
<div class="container" >

        <form action="<%= request.getContextPath() %>/login" method="POST">


            <label for="email">Email</label>
            <input type="email" id="email" name="email" ><br><br>

            <label for="password">Password</label>
            <input type="password" id="password" name="password" ><br><br>


            <input type="submit" value="Login" style="background-color: darkblue"><br><br>

            <a href="signup.jsp" class="link">Don't have an account? Signup</a>
            <p style="color: red">${error}</p>
        </form>
    </div>

</body>
</html>
