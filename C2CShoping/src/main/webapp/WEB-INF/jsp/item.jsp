<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: v_lon
  Date: 2020/12/9
  Time: 1:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="fontawesome/css/all.min.css">
    <link rel="stylesheet" href="css/templatemo-style.css">
    <title>Items</title>
</head>
<body>
<c:choose>
    <c:when test="${allItem==null||allItem.size()==0}">
        <h1>No result</h1>
    </c:when>
    <c:when test="${allItem!=null}">
<div class="container-fluid tm-container-content tm-mt-60">
    <div class="row tm-mb-90 tm-gallery">
<c:forEach var="i" begin="0" end="${allItem.size()-1}">
    <div class="col-xl-3 col-lg-4 col-md-6 col-sm-6 col-12 mb-5">
        <figure class="effect-ming tm-video-item">
            <img src="img/ItemImg/${allItem.get(i).picture}" alt="123" class="img-fluid" style="height:325px">
            <figcaption class="d-flex align-items-center justify-content-center">
                <a href="viewDetail.htm?item=${allItem.get(i).itemId}&username=${allItem.get(i).customer.username}" target="item">View More</a>
            </figcaption>
        </figure>
        <div class="d-flex justify-content-between tm-text-gray">
            <span class="tm-text-secondary">${allItem.get(i).itemName}</span>
            <span>$${allItem.get(i).price}</span>
        </div>
    </div>
</c:forEach>
    </div>
</div>
    </c:when>
</c:choose>
</body>
</html>
