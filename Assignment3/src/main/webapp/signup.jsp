<%--
  Created by IntelliJ IDEA.
  User: Celia
  Date: 2/16/2024
  Time: 10:07 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Form</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/CSS/signup.css" />
</head>
<body>
<div><h2 style="color: darkblue">Signup Form</h2></div>
<div class="container" >

    <form action="<%= request.getContextPath() %>/signup" method="POST">

        <label for="email">Email</label>
        <input type="email" id="email" name="email" required><br><br>

        <label for="password">Password</label>
        <input type="password" id="password" name="password" required><br><br>

        <label for="confirmPassword">Confirm Password</label>
        <input type="password" id="confirmPassword" name="confirmPassword" required><br><br>


        <input type="submit" value="Signup" style="background-color: darkblue"><br><br>

        <a href="login.jsp" class="link">Already have an account? Login</a>
        <p style="color: red">${error}</p>
        <p style="color: red">${errorMessage}</p>
    </form>
</div>

</body>
</html>
