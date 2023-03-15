package com.example.lab5.controllers;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LogoutServlet", value = "/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();
//        Cookie emailC = new Cookie("emailC","");
//        Cookie passC = new Cookie("passC","");
//
//        emailC.setMaxAge(0);
//        passC.setMaxAge(0);
//        response.addCookie(emailC);
//        response.addCookie(passC);

        response.sendRedirect("/");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
