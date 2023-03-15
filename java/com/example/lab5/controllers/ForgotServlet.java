package com.example.lab5.controllers;

import com.example.lab5.dao.UserDAO;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Properties;

@WebServlet(name = "ForgotServlet", value = "/forgot")
public class ForgotServlet extends HttpServlet {
    UserDAO userDAO;
    String resultMessage="";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(session.getAttribute("logged")!=null)
        {
            response.sendRedirect("/home");
            return;
        }
        RequestDispatcher rd = request.getRequestDispatcher("forgot.jsp");
        rd.forward(request,response);
    }
    public void init()
    {
        userDAO = new UserDAO();
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Recipient's email ID needs to be mentioned.


        String username = "daylataikhoanguiemail@gmail.com";
        String password = "nmwawbjhihmeebkj";

        // Get system properties
        Properties properties = new Properties();

        // Setup mail server
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.auth", "true");


        Session session = Session.getInstance(properties,new javax.mail.Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username,password);
            }
        });

        try {
            String email = request.getParameter("email");
            if(userDAO.getUserbyEmail(email)==false)
            {
                resultMessage="Email is not exists";
            }
            else
            {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(username));
                message.addRecipient(
                        Message.RecipientType.TO,
                        new InternetAddress(email)
                );
                message.setSubject("YOUR PASSWORD");
                String pass = userDAO.readUser(email).getPass();
                message.setText(pass);
                Transport.send(message);
                resultMessage="Check your email";
            }

        }catch (Exception e)
        {
            resultMessage = "There were an error: " + e.getMessage();
        }
        request.setAttribute("feedBackForgotPass", resultMessage);
        request.getRequestDispatcher("forgot.jsp").forward(request,response);
    }
}
