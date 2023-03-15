<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 22/10/2022
  Time: 13:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Forgot password</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-6 col-lg-5">
            <h3 class="text-center text-secondary mt-5 mb-3">FORGOT PASSWORD</h3>
            <form class="border rounded w-100 mb-5 mx-auto px-3 pt-3 bg-light" method="post" action="forgot">
                <div class="form-group">
                    <label for="email">Email</label>
                    <input id="email" type="email" class="form-control" placeholder="Email" name="email">
                </div>
                <div class="form-group">
                    <button class="btn btn-success px-5">SEND</button>
                </div>
                <div class="form-group">
                    <a href="/login">Go to Login</a>
                </div>
                <div class="form-group">
                    <p style="color: red">${feedBackForgotPass}</p>
                </div>

            </form>
        </div>
    </div>
</div>
</body>
</html>
