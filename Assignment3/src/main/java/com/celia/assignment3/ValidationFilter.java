package com.celia.assignment3;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;

import java.io.IOException;
@WebFilter("/login")
public class ValidationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
            httpRequest.setAttribute("error", "Email and password are required fields");
            RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
            rd.forward(request, response);
        } else {
           chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
