package com.celia.assignment3;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;

import java.io.IOException;
@WebFilter("/signup")
public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization code if needed
    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Check if email parameter is present and in correct format
        String email = httpRequest.getParameter("email");
        if (email == null || !isValidEmail(email)) {
            request.setAttribute("error","Invalid Email!");
            RequestDispatcher rd =  request.getRequestDispatcher("/signup.jsp");
            rd.forward(request, response);
            return;
        }

        // Check if password parameter is present and not weak
        String password = httpRequest.getParameter("password");
        if (password == null || isWeakPassword(password)) {
            request.setAttribute("error","Weak password!Use at least 6 characters");
            RequestDispatcher rd =  request.getRequestDispatcher("/signup.jsp");
            rd.forward(request, response);
            return;
        }

        // Continue the chain if authenticated and email is in correct format
        chain.doFilter(request, response);
        RequestDispatcher rd =  request.getRequestDispatcher("/signup.jsp");
        rd.forward(request, response);
    }

    private boolean isValidEmail(String email) {
        // Regular expression pattern for email validation
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }

    private boolean isWeakPassword(String password) {
        // Example: Password is considered weak if its length is less than 8 characters
        return password.length() < 6;
    }

    @Override
    public void destroy() {
        // Cleanup code if needed
    }

}
