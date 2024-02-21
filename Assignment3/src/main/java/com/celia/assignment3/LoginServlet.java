package com.celia.assignment3;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response){
        String email= request.getParameter("email");
        String password= request.getParameter("password");
        String dburl = "jdbc:mysql://localhost:3306/studentadmission";
        String username = "root";
        String passwd = "";
        String sql= "SELECT email,password FROM signup WHERE email=? AND password=?";

//            request.setAttribute("username","User :"+username);

            try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection(dburl, username,passwd);
                    PreparedStatement pst = con.prepareStatement(sql);
                    pst.setString(1,email);
                    pst.setString(2,password);
                    ResultSet rs = pst.executeQuery();
                if(rs.next()){
                    HttpSession session = request.getSession();
                    session.setAttribute("email",rs.getString("email"));
                    session.setAttribute("password",rs.getString("password"));

                    RequestDispatcher rd =  request.getRequestDispatcher("/admission.jsp");
                    rd.forward(request, response);
                }
                else{
                    request.setAttribute("error","Invalid Credentials!");
                    RequestDispatcher rd =  request.getRequestDispatcher("/login.jsp");
                    try {
                        rd.forward(request, response);
                    } catch (ServletException e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }

            }  catch (Exception e) {
               e.printStackTrace();
            }
        }
        }

