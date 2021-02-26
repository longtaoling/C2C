<%--
  Created by IntelliJ IDEA.
  User: v_lon
  Date: 2020/12/9
  Time: 0:00
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main Page</title>
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="fontawesome/css/all.min.css">
        <link rel="stylesheet" href="css/templatemo-style.css">
    <script>
        function getMoreContents() {
            var content = document.getElementById("keyword");
            if (content.value === "") {
                ClearContent();
                return;
            }
            var xmlHttp = new XMLHttpRequest();
            xmlHttp.onreadystatechange=function(){
                if(xmlHttp.readyState===4&& xmlHttp.status===200){
                    var json=eval("("+xmlHttp.responseText+")");
                    ClearContent();
                    var size=json.length;
                    for(var i=0;i<size;i++)
                    {
                        var nextNode=json[i];
                        var li =document.createElement("li");
                        li.onmouseover=function(){
                            this.className="onmouse";
                            this.style.background="skyblue"
                            document.getElementById("keyword").value=this.innerHTML;
                        }
                        li.onmouseout=function(){
                            this.className="outmouse";
                            this.style.background="white"
                        }
                        var text=document.createTextNode(nextNode);
                        li.appendChild(text);
                        document.getElementById("c").appendChild(li);
                        document.getElementById("popDiv").style.zIndex="99";
                    }
                }
            }
            var url="searchByKeyWord.json?keyWord="+escape(content.value);
            xmlHttp.open("GET",url,true);
            xmlHttp.setRequestHeader("Accept","application/json")
            xmlHttp.send();
        }
        function ClearContent() {
            document.getElementById("c").innerHTML="";
            document.getElementById("popDiv").style.zIndex="-1";
        }
    </script>
</head>
<body>
<nav class="navbar navbar-expand-lg">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">
            <i class="fas fa-film mr-2"></i>
            <c:set var="user" value="${sessionScope.user}"/>
            <c:choose>
                <c:when test="${user==null}">
                    <a class="navbar-brand" href="login.htm">Log In</a>
                    <a class="navbar-brand" href="signup.htm">Sign up</a>
                </c:when>
                <c:when test="${user!=null}">
                    <h3>Welcome,${user.username}
                        <a class="navbar-brand" href="logout.htm">Log out</a>
                    </h3>
                </c:when>
            </c:choose>
        </a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <i class="fas fa-bars"></i>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ml-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link nav-link-1" target="item" aria-current="page" href="sell.htm">I want to sell items</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link nav-link-2" target="item" href="sellItems.htm">Check out the items I am selling</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link nav-link-3" target="item" href="shoppingCart.htm">View My Cart</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<nav class="navbar navbar-expand-lg">
<div style="margin: auto">
    <ul class="navbar-nav ml-auto mb-2 mb-lg-0">
        <li class="nav-item">
            <a class="nav-link nav-link-1" target="item" aria-current="page" href="item.htm">Home Page</a>
        </li>
        <li class="nav-item">
            <a class="nav-link nav-link-2" target="item" href="books.htm">Books</a>
        </li>
        <li class="nav-item">
            <a class="nav-link nav-link-3" target="item" href="games.htm">Games</a>
        </li>
        <li class="nav-item">
            <a class="nav-link nav-link-4" target="item" href="computers.htm">Computers</a>
        </li>
        <li class="nav-item">
            <a class="nav-link nav-link-1" target="item" href="sports.htm">Sports</a>
        </li>
        <li class="nav-item">
            <a class="nav-link nav-link-1" target="item" href="furniture.htm">Furniture</a>
        </li>
        <li class="nav-item">
            <a class="nav-link nav-link-1" target="item" href="other.htm">Other</a>
        </li>
    </ul>
</div>
</nav>
<div class="tm-hero d-flex justify-content-center align-items-center" id="tm-video-container">
    <video autoplay muted loop id="tm-video">
        <source src="video/hero.mp4" type="video/mp4">
    </video>
    <form class="d-flex position-absolute tm-search-form" action="search.htm" method="GET" target="item">
        <input class="form-control tm-search-input" type="search" placeholder="Search" aria-label="Search" id="keyword" name="keyWord" onkeyup="getMoreContents()" onblur="ClearContent()" onfocus="getMoreContents()">
        <button class="btn btn-outline-success tm-search-btn" type="submit">
            <i class="fas fa-search"></i>
        </button>
    </form>
    <div id="popDiv" class="d-flex position-absolute" style="top: 54%;left:auto;width:459px;background:white;z-index:-1;">
        <ul id="c">
        </ul>
    </div>
</div>
<iframe name="item" src="item.htm" style="width: 100%;height:200%" scrolling="no"></iframe>
</body>
</html>
