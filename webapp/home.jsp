<%@page import="com.mysql.cj.xdevapi.PreparableStatement"%>
<%@ page import="java.util.List" %>
<%@page import="java.text.DecimalFormat" %>
<%@ page import="com.example.lab5.model.Product" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.lab5.dao.ProductDAO" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>DANH SÁCH SẢN PHẨM</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>




</head>
<body>
<style>
    .form-add, .showInfoProduct{
        border: 1px solid #eee;
        padding: 20px;
        border-radius: 5px;
    }
    .action:hover{
        cursor: pointer;
        text-decoration: none;
    }
</style>

<jsp:include page="header.jsp"></jsp:include>
<div class="container">
    <div class="row">
        <div class="col-md-4">
            <div class="form-add">
                <form class="form-horizontal w-100" method="post" onsubmit="return true">
                    <h3 style="color: green;">THÊM SẢN PHẨM MỚI</h3>
                    <div class="form-group">
                        <label class="control-label" for="name">Tên sản phẩm:</label>
                        <input type="text" class="form-control" id="name" placeholder="Enter name" name="nameProduct">
                    </div>
                    <div class="form-group">
                        <label class="control-label ">Giá sản phẩm:</label>
                        <input type="text" class="form-control" id="price" placeholder="Enter price" name="priceProduct">
                    </div>

                    <div class="form-group">
                        <button id="typeButtonAdd" onclick="return add()" class="btn btn-success add-student" type="submit" >Thêm</button>
                    </div>
                    <div class="alert alert-danger alert-dismissible fade show" id="alert" role="alert" style="display: none">
                        <p id="alertText"></p>
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>

                </form>
            </div>
        </div>
        <div class="col-md-8">
            <div class="showInfoProduct">
                <h3 style="color: green;">DANH SÁCH SẢN PHẨM</h3>
                <table class="table table-striped" id="tableProduct">
                    <thead>
                        <tr>
                            <th scope="col">Mã sản phẩm</th>
                            <th scope="col">Tên sản phẩm</th>
                            <th scope="col">Giá</th>
                            <th scope="col">Thao tác</th>
                        </tr>
                    </thead>
                    <tbody>
                    <%
                        ProductDAO productDAO = new ProductDAO();
                        ArrayList<Product> listProduct = productDAO.getAllProduct();
                        for(Product item:listProduct)
                        {
                            %>
                            <tr id="row<%=item.getId()%>">
                                <th><%=item.getId()%></th>
                                <td id="name<%=item.getId()%>" price="<%=item.getPrice()%>"><a href="detail.jsp?idp=<%=item.getId()%>"><%=item.getName()%></a></td>
                                <td id="price<%=item.getId()%> priceNormal"><%=item.getPrice()%></td>
                                <td>
                                    <button class="fa fa-trash" style="color:red;font-size: 24px;margin-right: 10px;" onclick="ask_to_delete(<%=item.getId()%>)"></button>
                                    <button id="modalDelete" class="fa fa-edit" style="color:blue;font-size: 24px;" onclick="edit(<%=item.getId()%>)" data-toggle="modal" data-target="#myModal" ></button>
                                </td>
                            </tr>
                        <%}%>

                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<%--XAC NHAN XOA--%>
<div id="myModal" class="modal fade" role="dialog" >
    <div class="modal-dialog">
        <!-- Modal content-->
        <div class="modal-content" style="padding:15px">
            <form class="form-horizontal w-100" method="post">
                <h3 style="color: green;">CẬP NHẬT SẢN PHẨM</h3>
                <div class="form-group">
                    <label class="control-label" for="name">Tên sản phẩm:</label>
                    <input type="text" value="okk" class="form-control" id="nameProductUpdate" placeholder="Enter name" name="nameProductUpdate">
                </div>
                <div class="form-group">
                    <label class="control-label ">Giá sản phẩm:</label>
                    <input type="text" class="form-control" id="priceProductUpdate" placeholder="Enter price" name="priceProductUpdate">
                </div>

                <div class="form-group">
                    <a id="confirm-update" class="btn btn-success add-student" onclick="do_update()">Cập nhật</a>
                </div>

            </form>
        </div>
    </div>
</div>

<script>

    function add()
    {
        let name = $('#name').val()
        let price = $('#price').val()
        if(name.trim()=='' || price.trim()=='')
        {
            document.getElementById("alert").style.display='block'
            document.getElementById("alertText").innerText='Vui lòng nhập đầy đủ thông tin'
            return false
        }
        else
            return true;
    }
    function ask_to_delete(uid)
    {
        let name = $('#name'+uid).text()
        let text = "Xóa "+name +"?"
        swal({
            title: "Xác nhận xóa",
            text: text,
            icon: "warning",
            buttons: true,
            dangerMode: false,
        }).then((value) => {
            if (value) {
                $.ajax({
                    type: "post",
                    url: "delete.jsp",
                    data: {idp: uid },
                    success: function (response)
                    {
                        swal({
                            position: 'top-end',
                            icon: 'success',
                            title: 'Xóa thành công',
                            showConfirmButton: false,
                            timer: 1000
                        }).then(()=>{
                            $('#row'+uid).remove()
                            // location.reload()
                        })
                    }
                });
            }

        });
    }

    function edit(uid)
    {
        let name = $('#name'+uid).text()
        let price = $('#name'+uid).attr('price')

        $('#nameProductUpdate').attr('value',name)
        $('#priceProductUpdate').attr('value',price)
        $('#confirm-update').attr("uid",uid)

    }

    function do_update()
    {
        let uid = $('#confirm-update').attr("uid")
        let name = $('#nameProductUpdate').val()
        let price = $('#priceProductUpdate').val()
        $.ajax({
            type: "post",
            url: "update.jsp",
            data: {
                id: uid,
                nameProductUpdate:name,
                priceProductUpdate:price
            },
            success: function (response)
            {
                swal({
                    position: 'top-end',
                    icon: 'success',
                    title: 'Cập nhật thành công',
                    showConfirmButton: false,
                    timer: 1000
                }).then(()=>{
                    location.reload()
                })
            }
        });
    }


</script>
</body>
</html>
