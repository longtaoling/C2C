<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: v_lon
  Date: 2020/12/14
  Time: 1:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>

    <title>Title</title>
</head>
<body>
<c:set var="user" value="${sessionScope.user}"/>
<c:set var="cart" value="${user.cart}"/>
<c:set var="totalMoney" value="0"/>
<c:choose>
    <c:when test="${cart==null||cart.size()==0}">
        <h1>Your Shopping Cart Is Empty</h1>
    </c:when>
    <c:when test="${cart!=null}">
<div id="content" style="text-align: center">
    <h1>Items in Cart</h1>

    <table  style="width:700px;margin: auto;text-align: center">
        <tr  style="background-color:#ddd">
            <th style="width:220px">Image </th>
            <th style="width:180px">Item Name </th>
            <th style="width:150px">Price </th>
            <th style="width:150px"> </th>
        </tr>
    <c:forEach var="i" begin="0" end="${cart.size()-1}">
        <tr><td><img src="img/ItemImg/${cart.get(i).picture}" alt="image" /> </td>
            <td>${cart.get(i).itemName}</td>
            <td>${cart.get(i).price}</td>
            <td> <a href="removeItemFromCart.htm?item=${cart.get(i).itemId}">Remove</a> </td>
        </tr>
        <c:set var="totalMoney" value="${totalMoney=totalMoney+cart.get(i).price}"/>
    </c:forEach>
        <tr>
            <td   height="30px"></td>
            <td style="font-weight:bold"> Total </td>
            <td  style="font-weight:bold">${totalMoney}</td>
        </tr>
    <tr><td></td>
        <td colspan="2" style="font-weight:bold;font-size: 25px"><a href="placeOrder.htm" target="_blank">Place the order</a></td>
    </tr>
    </table>
    </c:when>
</c:choose>
</div>
</body>
</html>
