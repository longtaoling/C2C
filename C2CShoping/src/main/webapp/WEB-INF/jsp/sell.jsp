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
    <title>Upload Your item</title>
</head>
<body>
<div class="row tm-mb-50">
    <h2 class="tm-text-primary mb-5" style="text-align: center">Upload Your item</h2>
<form class="tm-contact-form mx-auto" method="post" target="item" enctype="multipart/form-data">
    <div class="form-group">
        <input type="text" name="itemName" class="form-control rounded-0" placeholder="Item Name" required />
    </div>
    <div class="form-group">
        <input type="text" name="price" class="form-control rounded-0" placeholder="Price:$" required />
    </div>
    <div class="form-group">
        <select class="form-control" id="contact-select" name="type">
            <option value ="books">Books</option>
            <option value ="games">Games</option>
            <option value="computers">Computers</option>
            <option value="sports">Sports</option>
            <option value="furniture">Furniture</option>
            <option value="other">Other</option>
        </select>
    </div>
    <div class="form-group">
        <textarea rows="8" name="description" class="form-control rounded-0" placeholder="Description"></textarea>
    </div>
    <div class="form-group">
        <input type="file" class="form-control rounded-0" name="pic" accept="image/png,image/jpeg,image/jpg"/>
        <p class="tm-text-primary mb-5">Upload a picture of the item</p>
    </div>
    <div class="form-group tm-text-right">
        <button type="submit" class="btn btn-primary">Submit</button>
    </div>
</form>
</div>
</body>
</html>
