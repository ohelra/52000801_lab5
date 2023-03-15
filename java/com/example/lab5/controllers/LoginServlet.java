package com.example.lab5.controllers;

import com.example.lab5.dao.UserDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Arrays;

@WebServlet(name = "LoginServlet", value = "/")
public class LoginServlet extends HttpServlet
{
    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        userDAO = new UserDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        if(session.getAttribute("logged")!=null)
        {
            response.sendRedirect("/home");
            return;
        }
        Cookie arr[] =request.getCookies();
        for(Cookie o :arr)
        {
            if(o.getName().equals("emailC"))
            {
                request.setAttribute("email",o.getValue());
            }
            if(o.getName().equals("passC"))
            {
                request.setAttribute("password",o.getValue());
            }
        }
        RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
        rd.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {response.setContentType("text/html");
        HttpSession session = request.getSession();

        String email = request.getParameter("email").trim();
        String password = request.getParameter("password").trim();
        String remember = request.getParameter("remember");

        Cookie userCookie = new Cookie("emailC", email);
        Cookie passCookie = new Cookie("passC", password);
        if(userDAO.getUser(email,password)==true)
        {
            System.out.println("ok");
            session.setAttribute("accountLogged",email);
            session.setAttribute("logged",email);
            session.setAttribute("username",userDAO.readUser(email).getName());

            if (remember != null)
            {
                userCookie.setMaxAge(60 * 60 * 24 * 30);
                passCookie.setMaxAge(60 * 60 * 24 * 30);
                response.addCookie(userCookie);
                response.addCookie(passCookie);
            }


            response.sendRedirect("/home");
            return;
        }
        response.sendRedirect("/");
    }
}
