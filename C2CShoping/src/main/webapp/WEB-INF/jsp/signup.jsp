<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: v_lon
  Date: 2020/12/8
  Time: 0:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
    <title>Signin</title>
    <style>
        body{
            text-align: center
        }
        .form-group{
            text-align: center;
            margin-left: auto;
            margin-right: auto;
        }
        form{
            width:30%;
            margin: 10% auto auto;
        }
    </style>
</head>
<body>
<form method="post" role="form">
    <div class="form-group">
        <lable for="Username" >Username:</lable>
        <input type="text" class="form-control" name="username"><br>
    </div>
    <div class="form-group">
        <lable for="Password">Password:</lable>
        <input type="password" class="form-control" name="password"><br>
    </div>
    <div class="form-group">
        <lable for="repw">Re-enter Paswword:</lable>
        <input type="password" class="form-control" name="rePw"><br>
    </div>
    <div class="form-group">
        <lable for="Email">Email:</lable>
        <input type="text" class="form-control" name="email"><br>
    </div>
    <div class="form-group">
        <lable for="Phone">Phone:</lable>
        <input type="text" class="form-control" name="phoneNum"><br>
    </div>
    <input type="submit"  value="Submit" class="btn">
</form>
</body>
</html>
