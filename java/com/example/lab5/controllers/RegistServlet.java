package com.example.lab5.controllers;

import com.example.lab5.dao.UserDAO;
import com.example.lab5.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "RegistServlet", value = "/regist")
public class RegistServlet extends HttpServlet {
    private UserDAO userDAO;

    @Override
    public void init()
    {
        userDAO = new UserDAO();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(session.getAttribute("logged")!=null)
        {
            response.sendRedirect("/home");
        }
        else
        {
            RequestDispatcher rd = request.getRequestDispatcher("regist.jsp");
            rd.forward(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String username = request.getParameter("username").trim();
        String email = request.getParameter("email").trim();
        String password = request.getParameter("password1").trim();
        String rePassword = request.getParameter("password2").trim();

        String []err = {"Vui lòng nhập đầy đủ thông tin","Email đã tồn tại","Mật khẩu nhập lại không hợp lệ","Đăng ký tài khoản thành công"};
        if(!password.equals(rePassword))
        {
            request.setAttribute("message", err[2]);
        }
        else if(userDAO.getUserbyEmail(email)==true){
            request.setAttribute("message",err[1]);
        }
        else if(username.equals("")||email.equals("")||password.equals(""))
        {
            request.setAttribute("message", err[0]);
        }
        else
        {
            User user = new User();
            user.setName(username);
            user.setEmail(email);
            user.setPass(password);

            userDAO.register(user);
            request.setAttribute("message", err[3]);
        }
        RequestDispatcher rd = request.getRequestDispatcher("regist.jsp");
        rd.forward(request,response);
    }
}
