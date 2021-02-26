<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: v_lon
  Date: 2020/12/14
  Time: 21:32
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
<div class="row tm-mb-50">
    <h2 class="tm-text-primary mb-5" style="text-align: center">Upload Your item</h2>
    <form:form class="tm-contact-form mx-auto" action="editItem.htm" method="post" target="item" modelAttribute="item" enctype="multipart/form-data">
        <form:input type="hidden" name="itemId" path="itemId"/>
        <div class="form-group">
            <form:input type="text" name="itemName" class="form-control rounded-0" placeholder="Item Name" path="itemName" />
        </div>
        <div class="form-group">
            <form:input type="text" name="price" class="form-control rounded-0" placeholder="Price:$" path="price" />
        </div>
        <div class="form-group">
            <form:select class="form-control" id="contact-select" name="type" path="type">
                <form:option value ="books">Books</form:option>
                <form:option value ="games">Games</form:option>
                <form:option value="computers">Computers</form:option>
                <form:option value="sports">Sports</form:option>
                <form:option value="furniture">Furniture</form:option>
                <form:option value="other">Other</form:option>
            </form:select>
        </div>
        <div class="form-group">
            <form:textarea rows="8" name="description" class="form-control rounded-0" placeholder="Description" path="description"/>
        </div>
        <div class="form-group">
            <input type="file" class="form-control rounded-0" name="pic" accept="image/png,image/jpeg,image/jpg"/>
            <p class="tm-text-primary mb-5">Upload a picture of the item</p>
        </div>
        <div class="form-group tm-text-right">
            <button type="submit" class="btn btn-primary">Save</button>
        </div>
    </form:form>
</div>
</body>
</html>
