<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: v_lon
  Date: 2020/12/10
  Time: 2:25
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
<c:set var="items" value="${sessionScope.user.sellItems}"/>
<c:choose>
    <c:when test="${items==null||items.size()==0}">
        <h1>You have no items on sale!</h1>
    </c:when>
    <c:when test="${items!=null}">
        <div class="container-fluid tm-container-content tm-mt-60">
            <div class="row tm-mb-90 tm-gallery">
                <c:forEach var="i" begin="0" end="${items.size()-1}">
                    <div class="col-xl-3 col-lg-4 col-md-6 col-sm-6 col-12 mb-5">
                        <figure class="effect-ming tm-video-item">
                            <img src="img/ItemImg/${items.get(i).picture}" alt="123" class="img-fluid" style="height:325px">
                        </figure>
                        <div class="d-flex justify-content-between tm-text-gray">
                            <span class="tm-text-secondary">${items.get(i).itemName}</span>
                            <span>$${items.get(i).price}</span>
                        </div>
                        <div class="d-flex justify-content-between">
                       <span><a href="editItem.htm?item=${items.get(i).itemId}">Edit</a></span>
                        <span><a href="removeItem.htm?itemID=${items.get(i).itemId}">Remove</a></span>
                        </div>
                    </div>
                </c:forEach>
        </div>
        </div>
    </c:when>
</c:choose>
</body>
</html>
