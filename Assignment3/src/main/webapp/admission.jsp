<%--
  Created by IntelliJ IDEA.
  User: Celia
  Date: 2/16/2024
  Time: 9:50 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admission Request</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/CSS/admission.css" />

</head>
<body>
<%
    if (request.getSession().getAttribute("email")== null){
        response.sendRedirect("/login.jsp");
    }
    %>
<h3 id="user">${username}</h3>
<div><h2>Student Admission Form</h2></div>
<a href="<%= request.getContextPath() %>/logout" class="home">Logout</a>
<div class="container">
    <form action="<%= request.getContextPath() %>/admission" method="POST" enctype="multipart/form-data">
                <div class="form-columns">
                    <div class="column">
                        <label for="fullname">Full Name</label>
                        <input type="text" id="fullname" name="fullname" required>

                        <label for="gender">Gender</label>
                        <select id="gender" name="gender">
                            <option value="male">Male</option>
                            <option value="female">Female</option>
                        </select>

                        <label for="dob">Date of birth</label>
                        <input type="date" id="dob" name="dob" required>

                        <label for="email">Email</label>
                        <input type="email" id="email" name="email" required>

                        <label for="phone">Phone Number</label>
                        <input type="text" id="phone" name="phone" required>

                    </div>

                    <div class="column">
                        <label for="program">Program</label>
                        <select id="program" name="program">
                            <option value="day">Day</option>
                            <option value="evening">Evening</option>
                        </select>

                        <label for="faculty">Faculty</label>
                        <input type="text" id="faculty" name="faculty" required>

                        <label for="department">Department</label>
                        <input type="text" id="department" name="department" required>

                        <label for="picture">Passport picture (.jpg or .npg supported) </label>
                        <input type="file" id="picture" name="picture" accept=".jpg,.png" placeholder="Attach your passport picture" required>
                        <p style="color: red">${errorPic}</p>

                        <label for="certificate">Certificate (only pdf format supported)</label>
                        <input type="file" id="certificate" name="certificate" accept=".pdf" placeholder="Attach your Certicate(Diploma)" required>
                        <p style="color: red">${errorCer}</p>

                    </div>
                </div><br>

                <div class="column full-width">
                    <input type="submit" value="Submit"><br>

                </div>
        <p style="color: green"><b>${successMsg}</b></p>

            </form>
            <script>

            </script>

        </div>