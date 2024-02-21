package com.celia.assignment3;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.http.Part;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import static java.sql.DriverManager.getConnection;

@WebServlet("/admission")
@MultipartConfig
public class AdmissionServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fullname = request.getParameter("fullname");
        String gender = request.getParameter("gender");
        String dob = request.getParameter("dob");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String program = request.getParameter("program");
        String faculty = request.getParameter("faculty");
        String department = request.getParameter("department");
        String picture = request.getParameter("picture");
        String certificate = request.getParameter("certificate");
        String dburl = "jdbc:mysql://localhost:3306/studentadmission";
        String username = "root";
        String passwd = "";
        String sql= "insert into student_admission values (?,?,?,?,?,?,?,?,?,?)";

        Part picturePart = request.getPart("picture");
        Part certificatePart = request.getPart("certificate");

        if (!isValidImageFile(picturePart)) {
            request.setAttribute("errorPic", "Invalid file format! Picture must be JPG or PNG");
            RequestDispatcher rd = request.getRequestDispatcher("/admission.jsp");
            rd.forward(request, response);
            return;
        }
        if (!isValidPdfFile(certificatePart)) {
            request.setAttribute("errorCer", "Invalid file format! The certificate must be PDF.");
            RequestDispatcher rd = request.getRequestDispatcher("/admission.jsp");
            rd.forward(request, response);
            return;
        }

        String pictureName = picturePart.getSubmittedFileName();
        String certificateName = certificatePart.getSubmittedFileName();
        String pictureUploadPath = "C:/Users/Celia/IdeaProjects/Assignment3/src/main/webapp/uploads/" + pictureName;
        String certificateUploadPath = "C:/Users/Celia/IdeaProjects/Assignment3/src/main/webapp/uploads/" + certificateName;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = getConnection(dburl,username,passwd);
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1,fullname);
            pst.setString(2,gender);
            pst.setString(3,dob);
            pst.setString(4,email);
            pst.setString(5,phone);
            pst.setString(6,program);
            pst.setString(7,faculty);
            pst.setString(8,department);
            pst.setString(9,pictureName);
            pst.setString(10,certificateName);

            FileOutputStream pictureFos = new FileOutputStream(pictureUploadPath);
            FileOutputStream certificateFos = new FileOutputStream(certificateUploadPath);
            InputStream pictureIns = picturePart.getInputStream();
            InputStream certificateIns = certificatePart.getInputStream();
            byte[] picturedata = new byte[pictureIns.available()];
            byte[] certificatedata = new byte[certificateIns.available()];
            pictureIns.read(picturedata);
            certificateIns.read(certificatedata);
            pictureFos.write(picturedata);
            certificateFos.write(certificatedata);
            pictureFos.close();
            certificateFos.close();

            if(pst.executeUpdate()>0){
                sendEmail(email,"admission Form","Hello World");

            request.setAttribute("successMsg","Submitted successfully!!");
            RequestDispatcher rd= request.getRequestDispatcher("/admission.jsp");
            rd.forward(request, response);}
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    private boolean isValidImageFile(Part part) {
        String fileName = getFileName(part);
        if (fileName != null && (fileName.endsWith(".jpg") || fileName.endsWith(".png"))) {
            return true;
        }
        return false;
    }

    private boolean isValidPdfFile(Part part) {
        String fileName = getFileName(part);
        if (fileName != null && fileName.endsWith(".pdf")) {
            return true;
        }
        return false;
    }

    private String getFileName(Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

    private void sendEmail(String to, String subject, String body) {
        // Set mail properties
        java.util.Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", " smtp.gmail.com");
        properties.setProperty("mail.smtp.port", "587");
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.smtp.tsl.trust","smtp.gmail.com");


        // Create session

        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("neevecelia@gmail.com", "rdqe csvo wzaj zgdj");
            }
        });

        try {
            // Create message
            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress("neevecelia@gmail.com"));
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            mimeMessage.setSubject(subject);
            mimeMessage.setText(body);
            // Send the message
            Transport.send(mimeMessage);

            System.out.println("Email sent successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
