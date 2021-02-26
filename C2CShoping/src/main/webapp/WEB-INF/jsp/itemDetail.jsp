<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: v_lon
  Date: 2020/12/13
  Time: 23:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="fontawesome/css/all.min.css">
    <link rel="stylesheet" href="css/templatemo-style.css">
    <title>Title</title>
</head>
<body>
<div class="container-fluid tm-container-content tm-mt-60">
    <div class="row mb-4">
        <h2 class="col-12 tm-text-primary">${item.itemName}</h2>
    </div>
    <div class="row tm-mb-90">
        <div class="col-xl-8 col-lg-7 col-md-6 col-sm-12">
            <img src="img/ItemImg/${item.picture}" alt="Image" class="img-fluid">
        </div>
        <div class="col-xl-4 col-lg-5 col-md-6 col-sm-12">
            <div class="tm-bg-gray tm-video-details">
                <p class="mb-4">
                    Price:${item.price}$
                </p>
                <c:if test="${type=='buy'}">
                <div class="text-center mb-5">
                    <a href="addToCart.htm?item=${item.itemId}" class="btn btn-primary tm-btn-big">Add to cart</a>
                </div>
                </c:if>
                <div class="mb-4 d-flex flex-wrap">
                    <div class="mr-4 mb-2">
                        <span class="tm-text-gray-dark">Type: </span><span class="tm-text-primary">${item.type}</span>
                    </div>
                </div>
                <div class="mb-4">
                    <h3 class="tm-text-gray-dark mb-3">Description:</h3>
                    <p>${item.description}</p>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
