package com.celia.assignment3;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.*;

@WebServlet("/signup")
public class signupServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response){
        String email= request.getParameter("email");
        String password= request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String dburl = "jdbc:mysql://localhost:3306/studentadmission";
        String username = "root";
        String passwd = "";
        String sql= "insert into signup values (?,?)";

        if(password.equals(confirmPassword) ){
//            request.setAttribute("username","User :"+username);

            try {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                Connection con = DriverManager.getConnection(dburl, username,passwd);
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setString(1,email);
                pst.setString(2,password);
                if (pst.executeUpdate()>0){
                    RequestDispatcher rd =  request.getRequestDispatcher("/login.jsp");
                    try {
                        rd.forward(request, response);
                    } catch (ServletException e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }


            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else{
            request.setAttribute("error"," The password must match");
            RequestDispatcher rd =  request.getRequestDispatcher("/signup.jsp");
            try {
                rd.forward(request, response);
            } catch (ServletException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
