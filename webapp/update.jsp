<%@ page import="com.example.lab5.dao.ProductDAO" %>
<%@ page import="com.example.lab5.model.Product" %><%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 19/10/2022
  Time: 18:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ProductDAO productDAO = new ProductDAO();
    String id = request.getParameter("id");
    String name = request.getParameter("nameProductUpdate");
    String price = request.getParameter("priceProductUpdate");

    Product product = new Product();
    product.setId(Integer.parseInt(id));
    product.setName(name);
    product.setPrice(price.replace(",",""));
    productDAO.update(product);

%>
