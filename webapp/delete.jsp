<%@ page import="com.example.lab5.dao.ProductDAO" %><%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 19/10/2022
  Time: 15:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ProductDAO productDAO = new ProductDAO();
    Integer idp = Integer.parseInt(request.getParameter("idp"));
    productDAO.deleteProduct(idp);

%>
