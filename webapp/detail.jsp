<%@ page import="com.example.lab5.model.Product" %>
<%@ page import="com.example.lab5.dao.ProductDAO" %>
<%@ page import="java.text.DecimalFormat" %><%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 19/10/2022
  Time: 11:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<div class="container d-flex justify-content-center">
  <div>
    <div class="card" style="width:400px">
      <img class="card-img-top" src="https://cdn.hoanghamobile.com/i/productlist/dsp/Uploads/2022/09/08/1111.png" style="width:100%">
      <div class="card-body">
        <%
          HttpSession sessionn = request.getSession();
          if(sessionn.getAttribute("logged")==null)
          {
            response.sendRedirect("login");
          }
          ProductDAO productDAO = new ProductDAO();
          DecimalFormat twoPlaces = new DecimalFormat("");
          Product item =  productDAO.readProduct(Integer.parseInt(request.getParameter("idp")));;

        %>
        <p class="card-title">Mã sản phẩm:<strong><%=item.getId()%></strong></p>
        <p class="card-text">Tên sản phẩm: <strong><%=item.getName()%></strong></p>
        <p class="card-text">Giá: <strong><%=twoPlaces.format(Double.parseDouble(item.getPrice()))%></strong></p>
        <a class="btn btn-success" href="https://www.google.com/search?q=<%=productDAO.url(item.getName())%>">Tìm thông tin sản phẩm</a>
      </div>
    </div>
    <div><a href="#" onclick="history.back()">Quay lại</a></div>

  </div>
</div>
</body>
</html>
