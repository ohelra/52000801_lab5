package com.example.lab5.controllers;

import com.example.lab5.dao.ProductDAO;
import com.example.lab5.dao.UserDAO;
import com.example.lab5.model.Product;
import com.mysql.cj.Session;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "HomeServlet", value = "/home")
public class HomeServlet extends HttpServlet
{
    ProductDAO productDAO;
    @Override
    public void init() throws ServletException {
        productDAO = new ProductDAO();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        if(session.getAttribute("logged")==null)
        {
            response.sendRedirect("/login");
            return;
        }
        request.getRequestDispatcher("home.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String nameProduct = request.getParameter("nameProduct").trim();
        String priceProduct = request.getParameter("priceProduct").trim();


        if(nameProduct!="" && priceProduct!="" )
        {
            Product product = new Product(nameProduct,priceProduct);
            productDAO.addProduct(product);
        }
        response.sendRedirect("home");
    }


}
