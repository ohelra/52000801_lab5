<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Register</title>
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
            <h3 class="text-center text-secondary mt-5 mb-3">User Register</h3>
            <form class="border rounded w-100 mb-5 mx-auto px-3 pt-3 bg-light" method="post" action="regist" >
                <div class="form-group">
                    <label for="username">Username</label>
                    <input id="username" type="text" class="form-control" placeholder="Username" name="username">
                </div>
                <div class="form-group">
                    <label for="email">Email</label>
                    <input id="email" type="email" class="form-control" placeholder="Email" name="email">
                </div>
                <div class="form-group">
                    <label for="password">Password</label>
                    <input id="password" type="password" class="form-control" placeholder="Password" name="password1">
                </div>
                <div class="form-group">
                    <label for="repassword">Confirm Password</label>
                    <input id="repassword" type="password" class="form-control" placeholder="Password" name="password2">
                </div>
                <div class="form-group">
                    <button class="btn btn-success px-5">Register</button>
                </div>
                <p style="color:red">${message}</p>
                <div class="form-group">
                    <a href="/">Go Back</a></p>
                </div>
            </form>

        </div>
    </div>
</div>

</body>
</html>