<!DOCTYPE html>
<html >
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta charset="UTF-8">
<%@ page contentType="text/html;charset=UTF-8"%>
<head>
    <%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
    <title>欢迎页面</title>
    <%--<link rel="stylesheet" href="${pageContext.request.contextPath }/css/index.css">--%>
</head>
<%--<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />--%>
<%--<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>--%>
<body>
<h2>

   <form method="post" action="upload/imageupload" enctype="multipart/form-data">
    <p>姓名a<input type="text" name="name" placeholder="Name" required=""></p>
    <p>年龄a<input type="text" name="age" placeholder="年龄" required=""></p>
       <p> 选择文件:<input type="file" name="file">
           <p>
       <input type="submit"  value="登录">
</form>

</h2>
<img alt="静态资源图片" src="images/878c37e1269f4a87bea60edfc19ff1f3.jpg">
</body>
<script>

</script>
</html>
